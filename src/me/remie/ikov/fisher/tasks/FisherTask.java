package me.remie.ikov.fisher.tasks;

import me.remie.ikov.fisher.FisherScript;
import me.remie.ikov.fisher.data.FisherSettings;
import me.remie.ikov.fisher.data.FisherState;
import simple.api.script.task.Task;

/**
 * Created by Reminisce on Feb 15, 2023 at 10:30 PM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707 <138751815847116800>
 */
public abstract class FisherTask extends Task {

    public final FisherScript script;

    public FisherTask(FisherScript script) {
        super(script.ctx);
        this.script = script;
    }

    /**
     * Gets the script instance.
     *
     * @return the script instance.
     */
    public FisherScript getScript() {
        return script;
    }

    /**
     * Gets the settings instance. This holds all the settings for the script.
     *
     * @return the settings instance.
     */
    public FisherSettings getSettings() {
        return script.getSettings();
    }

    /**
     * Gets the state instance. This holds all the state specific variables for the script.
     *
     * @return the state instance.
     */
    public FisherState getState() {
        return script.getState();
    }

    /**
     * Sets the status of the script. This is a helper since it's not exposed in the task class.
     *
     * @param status the status to set.
     */
    public void setStatus(String status) {
        script.setScriptStatus(status);
    }

}
