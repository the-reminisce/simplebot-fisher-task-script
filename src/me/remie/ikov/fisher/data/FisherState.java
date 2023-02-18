package me.remie.ikov.fisher.data;

import me.remie.ikov.fisher.FisherScript;
import me.remie.ikov.fisher.types.FishingSpot;
import me.remie.ikov.fisher.types.FishingTool;
import simple.api.ClientAccessor;
import simple.api.ClientContext;
import simple.api.filters.SimpleSkills;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Reminisce on Feb 15, 2023 at 10:30 PM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707 <138751815847116800>
 */
public class FisherState extends ClientAccessor<ClientContext> {

    private final FisherScript script;

    private final Random random = new Random();

    private int fishCaught = 0;
    private int cluesFound = 0;
    private long lastAnimation = 0;

    private boolean toolBeltFetched = false;
    private final List<Integer> toolBelt = new ArrayList<>();

    public FisherState(FisherScript script) {
        super(script.ctx);
        this.script = script;
    }

    /**
     * Gets the number of fish caught.
     *
     * @return fish caught
     */
    public int getFishCaught() {
        return this.fishCaught;
    }

    /**
     * Increments the number of fish caught by 1.
     */
    public void incrementFishCaught() {
        this.fishCaught++;
    }

    /**
     * Gets the number of clues found.
     *
     * @return clues found
     */
    public int getCluesFound() {
        return this.cluesFound;
    }

    /**
     * Increments the number of clues found by 1.
     */
    public void incrementCluesFound() {
        this.cluesFound++;
    }

    /**
     * Gets the last animation time.
     *
     * @return last animation time
     */
    public long getLastAnimation() {
        return this.lastAnimation;
    }

    /**
     * Sets the last animation time.
     *
     * @param lastAnimation last animation time
     */
    public void setLastAnimation(long lastAnimation) {
        this.lastAnimation = lastAnimation;
    }

    /**
     * Gets if the tool belt been fetched.
     *
     * @return tool belt fetched
     */
    public boolean isToolBeltFetched() {
        return this.toolBeltFetched;
    }

    /**
     * Sets the tool belt fetched state to true.
     */
    public void setToolBeltFetched() {
        this.toolBeltFetched = true;
    }

    /**
     * Gets the tool belt.
     *
     * @return tool belt
     */
    public List<Integer> getToolBelt() {
        return this.toolBelt;
    }

    /**
     * Checks if the tool belt contains a tool.
     *
     * @param id tool id
     * @return tool belt contains tool
     */
    public boolean hasTool(final int id) {
        return this.toolBelt.contains(id);
    }

    /**
     * Checks if the tool belt contains a tool.
     *
     * @param tool tool
     * @return tool belt contains tool
     */
    public boolean hasTool(final FishingTool tool) {
        return hasTool(tool.getId());
    }

    /**
     * Gets the best fishing spot for the player.
     * If progressive fishing is enabled, it will return the best spot for the player's level.
     * If progressive fishing is disabled, it will return the spot set in the settings.
     * If the player's level is too low for any spot, it will return null.
     * If we're progressive fishing we want to wait for a bit of a higher level to increase our chances of catching a fish.
     *
     * @return best fishing spot
     */
    public FishingSpot getSpot() {
        if (script.getSettings().isProgressiveFishing()) {
            FishingSpot bestSpot = null;
            for (FishingSpot spot : FishingSpot.values()) {
                if (ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING) >= spot.getRequiredLevel() + 7) {
                    if (bestSpot == null || spot.getRequiredLevel() > bestSpot.getRequiredLevel()) {
                        bestSpot = spot;
                    }
                }
            }
            return bestSpot;
        }
        return script.getSettings().getFishingSpot();
    }

    /**
     * Returns a random number between min and max, uniformly distributed.
     *
     * @param min minimum value
     * @param max maximum value
     * @return random number
     */
    public int nextRandom(int min, int max) {
        return random.nextInt(max - min) + min;
    }

}
