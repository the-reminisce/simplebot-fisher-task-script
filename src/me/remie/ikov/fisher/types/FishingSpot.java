package me.remie.ikov.fisher.types;

import simple.api.coords.WorldPoint;

/**
 * Created by Reminisce on Feb 18, 2023 at 2:57 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707 <138751815847116800>
 */
public enum FishingSpot {

    SHRIMP("Shrimp", FishingTool.SMALL_FISHING_NET, new WorldPoint(2836, 3432, 0), 1, 316, 20, 621),
    LOBSTER("Lobster", FishingTool.LOBSTER_POT, new WorldPoint(2853, 3424, 0), 40, 324, 20, 619),
    SWORDFISH("Swordfish", FishingTool.HARPOON, new WorldPoint(2853, 3424, 0), 50, 324, 225, 618),
    SHARK("Shark", FishingTool.HARPOON, new WorldPoint(2855, 3424, 0), 76, 334, 225, 618),
    MANTA_RAY("Manta Ray", FishingTool.BIG_FISHING_NET, new WorldPoint(2855, 3424, 0), 79, 334, 20, 621);

    private final String name;
    private final FishingTool tool;
    private final WorldPoint walkLocation;
    private final int requiredLevel;
    private final int npcId;
    private final int actionId;
    private final int animationId;

    FishingSpot(String name, FishingTool tool, WorldPoint walkLocation, int requiredLevel, int npcId, int actionId, int animationId) {
        this.name = name;
        this.tool = tool;
        this.walkLocation = walkLocation;
        this.requiredLevel = requiredLevel;
        this.npcId = npcId;
        this.actionId = actionId;
        this.animationId = animationId;
    }

    /**
     * Gets the name of the fishing spot.
     * @return The name of the fishing spot.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the tool required to fish at this spot.
     * @return The tool required to fish at this spot.
     */
    public FishingTool getTool() {
        return tool;
    }

    /**
     * Gets the required level to fish at this spot.
     * @return The required level to fish at this spot.
     */
    public int getRequiredLevel() {
        return requiredLevel;
    }

    /**
     * Gets the NPC ID of the fishing spot.
     * @return The NPC ID of the fishing spot.
     */
    public int getNpcId() {
        return npcId;
    }

    /**
     * Gets the action ID of the fishing spot.
     * @return The action ID of the fishing spot.
     */
    public int getActionId() {
        return actionId;
    }

    /**
     * Gets the walk location of the fishing spot.
     * @return The walk location of the fishing spot.
     */
    public WorldPoint getWalkLocation() {
        return walkLocation;
    }

    /**
     * Gets the animation ID of the fishing spot.
     * @return The animation ID of the fishing spot.
     */
    public int getAnimationId() {
        return animationId;
    }

    /**
     * Gets the fishing spot name to be used in the gui combobox.
     * @return The fishing spot name to be used in the gui combobox.
     */
    @Override
    public String toString() {
        return name;
    }

}
