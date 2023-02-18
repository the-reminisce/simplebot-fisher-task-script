package me.remie.ikov.fisher;

import me.remie.ikov.fisher.data.FisherSettings;
import me.remie.ikov.fisher.data.FisherState;
import me.remie.ikov.fisher.helpers.FisherExperienceTracker;
import me.remie.ikov.fisher.helpers.PaintHelper;
import me.remie.ikov.fisher.tasks.impl.BankingTask;
import me.remie.ikov.fisher.tasks.impl.DropItemsTask;
import me.remie.ikov.fisher.tasks.impl.FetchToolBeltTask;
import me.remie.ikov.fisher.tasks.impl.FishingTask;
import me.remie.ikov.fisher.tasks.impl.LostTask;
import me.remie.ikov.fisher.tasks.impl.TraverseBankTask;
import me.remie.ikov.fisher.tasks.impl.TraverseSpotsTask;
import simple.api.events.ChatMessageEvent;
import simple.api.filters.SimpleSkills;
import simple.api.listeners.SimpleMessageListener;
import simple.api.script.Category;
import simple.api.script.LoopingScript;
import simple.api.script.ScriptManifest;
import simple.api.script.interfaces.SimplePaintable;
import simple.api.script.task.Task;
import simple.api.script.task.TaskScript;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Reminisce on Feb 15, 2023 at 10:30 PM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707 <138751815847116800>
 */

@ScriptManifest(author = "Reminisce", name = "RCatherby Fisher - Ikov",
        category = Category.FISHING, version = "0.0.1",
        description = "Catherby fishing script for Ikov. Must start script with all tools for selected spot in toolbelt.",
        discord = "Reminisce#1707", servers = {"Ikov"})
public class FisherScript extends TaskScript implements LoopingScript, SimplePaintable, SimpleMessageListener, MouseListener {

    private final List<Task> tasks = new ArrayList<>();
    private long startTime;
    private boolean started = false;

    private FisherFrame frame;
    private FisherState state;
    private FisherSettings settings;
    private PaintHelper paintHelper;
    private FisherExperienceTracker expTracker;

    /**
     * This method is called when the script is started.
     *
     * @return True if the script should continue to start its process, false if it should not and re-run the onExecute method.
     */
    @Override
    public boolean onExecute() {
        this.state = new FisherState(this);
        this.startTime = System.currentTimeMillis();
        setScriptStatus("Waiting to start...");
        this.expTracker = new FisherExperienceTracker(ctx);
        this.expTracker.start(SimpleSkills.Skill.FISHING);

        setupPaint();

        /**
         * This method adds tasks to a list and processes them in the order they were added.
         * If a task can be activated, it will be executed, and the script will stop processing any remaining tasks in the list.
         * If a task cannot be activated, the script will move on to the next task in the list.
         * If the script is set to prioritize tasks, it will stop processing the list after a task is activated.
         * If the script is not set to prioritize tasks, it will continue processing the list after a task is activated.
         * In summary, this method processes tasks in a sequential order, stops processing
         * if a task can be activated, and handles the prioritization of tasks based on script settings.
         */
        this.tasks.addAll(Arrays.asList(
                new FetchToolBeltTask(this),
                new LostTask(this),
                new DropItemsTask(this),
                new TraverseBankTask(this),
                new BankingTask(this),
                new TraverseSpotsTask(this),
                new FishingTask(this)
        ));

        this.frame = new FisherFrame(this);
        this.frame.setVisible(true);

        return true;
    }

    /**
     * This method is required to be implemented by the script to return the tasks that the script will process.
     *
     * @return The list of tasks that the script will process.
     */
    @Override
    public List<Task> tasks() {
        return this.tasks;
    }

    /**
     * This method determines if the script should prioritize tasks.
     * If it returns true, it will process the tasks in the order they are added to the list.
     * It creates a more linear flow to the script, such that if a task is able to be activated, it will execute and stop the script from processing other tasks
     * that are in the list after it.
     *
     * @return True if the script should prioritize tasks, false if it should not.
     */
    @Override
    public boolean prioritizeTasks() {
        return true;
    }

    /**
     * This method is called every time the script is processed.
     * It will check if the script has started and if it has, it will call the super method.
     * The super method will call the tasks and process them.
     */
    @Override
    public void onProcess() {
        if (!started) {
            return;
        }
        super.onProcess();
    }

    /**
     * This method is called when the script is terminated.
     * It will close the GUI and set the frame to null.
     */
    @Override
    public void onTerminate() {
        if (this.frame != null) {
            this.frame.setVisible(false);
            this.frame = null;
        }
    }

    /**
     * This method is called on script execution.
     * It creates a new instance of the paint helper and adds the lines to it.
     * The lines are added in the order we want them to be displayed.
     * The paint helper will then handle the painting of the lines.
     * We only want to call this method once, so we check if the paint helper is null.
     * If it is not null, we return and do not call the method again.
     * This is to prevent the lines from being added multiple times.
     * We don't add default lines here, we add them in the paint helper class such as the runtime and script status.
     */
    private void setupPaint() {
        if (this.paintHelper != null) {
            return;
        }
        this.paintHelper = new PaintHelper(this);
        paintHelper.addLine(() -> "Fish/h: " + ctx.paint.formatValue(getState().getFishCaught()) +
                " (" + ctx.paint.formatValue(ctx.paint.valuePerHour(getState().getFishCaught(), this.startTime)) + ")");
        paintHelper.addLine(() -> "Clues/h: " + ctx.paint.formatValue(getState().getCluesFound()) +
                " (" + ctx.paint.formatValue(ctx.paint.valuePerHour(getState().getCluesFound(), this.startTime)) + ")");
        paintHelper.addLine(() -> "Exp/h: " + ctx.paint.formatValue(expTracker.totalGainedXP()) + " (" + ctx.paint.formatValue(expTracker.totalGainedXPPerHour()) + ") "
        + "(" + ctx.paint.formatValue(expTracker.totalGainedLevels()) + ")");
        paintHelper.addLine(() -> "TTL: " + ctx.paint.formatTime(expTracker.timeToLevel(SimpleSkills.Skill.FISHING)));
    }

    /**
     * This method is called when the user clicks the start button on the GUI. It will set the settings and start the script.
     *
     * @param settings The settings that the user has selected in the GUI.
     */
    public void startScript(FisherSettings settings) {
        this.settings = settings;
        this.started = true;
        this.startTime = System.currentTimeMillis();
        setScriptStatus("Starting script...");
        this.frame.setVisible(false);
    }

    /**
     * This method is the listener for all chat messages we receive in the game.
     *
     * @param event The event that is fired when a chat message is received.
     */
    @Override
    public void onChatMessage(final ChatMessageEvent event) {
        if (!(event.getMessageType() == 0 && event.getSender().equals(""))) {
            return;
        }
        final String message = event.getMessage().toLowerCase();
        if (message.contains("you manage to catch a")) {
            getState().incrementFishCaught();
        } else if (message.contains("you catch a clue bottle")) {
            getState().incrementCluesFound();
        }
    }

    /**
     * This method is called when the script is painted.
     * It will call the paint helper to draw the paint.
     * We want to check if the paint helper is null, because if it is, we don't want to draw the paint.
     * This is to prevent a null pointer exception.
     *
     * @param g The graphics object that is used to draw the paint.
     */
    @Override
    public void onPaint(final Graphics2D g) {
        if (paintHelper == null) {
            return;
        }
        paintHelper.drawPaint(g);
    }

    /**
     * The loop duration is the amount of time in milliseconds that the script will wait before executing the next loop.
     *
     * @return the loop duration in milliseconds
     */
    @Override
    public int loopDuration() {
        return 600;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed)
     * This is used to toggle the paint on and off. We check if the mouse click was within the bounds of the paint.
     * If it was, we invert the drawingPaint boolean.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(final MouseEvent e) {
        if (paintHelper == null) {
            return;
        }
        paintHelper.handleMouseClick(e);
    }

    @Override
    public void mousePressed(final MouseEvent e) {

    }

    @Override
    public void mouseReleased(final MouseEvent e) {

    }

    @Override
    public void mouseEntered(final MouseEvent e) {

    }

    @Override
    public void mouseExited(final MouseEvent e) {

    }

    /**
     * Gets the start time of the script.
     *
     * @return the start time
     */
    public long getStartTime() {
        return this.startTime;
    }

    /**
     * Gets our script settings object that was set when the script was started from the GUI.
     *
     * @return the script settings
     */
    public FisherSettings getSettings() {
        return this.settings;
    }

    /**
     * Gets the script state object that contains all the information about the current state of the script.
     *
     * @return the script state
     */
    public FisherState getState() {
        return this.state;
    }

}
