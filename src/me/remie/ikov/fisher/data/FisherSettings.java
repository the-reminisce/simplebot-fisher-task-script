package me.remie.ikov.fisher.data;

import me.remie.ikov.fisher.types.FishingSpot;

/**
 * Created by Reminisce on Feb 15, 2023 at 10:30 PM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707 <138751815847116800>
 */
public class FisherSettings {

    private final boolean powerFishing;
    private final boolean progressiveFishing;
    private final FishingSpot fishingSpot;

    public FisherSettings(final FishingSpot fishingSpot, final boolean powerFishing, final boolean progressiveFishing) {
        this.fishingSpot = fishingSpot;
        this.powerFishing = powerFishing;
        this.progressiveFishing = progressiveFishing;
    }

    /**
     * Gets the power fishing setting
     * @return the power fishing setting
     */
    public boolean isPowerFishing() {
        return powerFishing;
    }

    /**
     * Gets the progressive fishing setting
     * @return the progressive fishing setting
     */
    public boolean isProgressiveFishing() {
        return progressiveFishing;
    }

    /**
     * Gets the fishing spot setting
     * @return the fishing spot
     */
    public FishingSpot getFishingSpot() {
        return fishingSpot;
    }

}
