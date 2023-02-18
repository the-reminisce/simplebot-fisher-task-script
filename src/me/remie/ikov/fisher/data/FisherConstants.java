package me.remie.ikov.fisher.data;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

/**
 * Created by Reminisce on Feb 15, 2023 at 10:30 PM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707 <138751815847116800>
 */
public class FisherConstants {

    /**
     * The names of the tools we can use to fish.
     */
    public static final String[] TOOL_NAMES = {
            "lobster pot", "small fishing net", "big fishing net", "harpoon"
    };

    /**
     * The names of items we should avoid dropping if we have them in our inventory and are power fishing.
     */
    public static final String[] AVOID_DROP_NAMES = {
            "clue", "lobster pot", "small fishing net", "big fishing net", "harpoon"
    };

    /**
     * The tile to walk to in the bank.
     */
    public static final WorldPoint BANK_TILE = new WorldPoint(2809, 3441, 0);

    /**
     * The area that defines catherby.
     */
    public static final WorldArea CATHERBY_AREA = new WorldArea(2802, 3414, 62, 33, 0);

    /**
     * The path from the bank to the fishing spots.
     */
    public static final WorldPoint[] BANK_TO_SPOTS_PATH = {
            new WorldPoint(2809, 3441, 0),
            new WorldPoint(2809, 3437, 0),
            new WorldPoint(2813, 3437, 0),
            new WorldPoint(2818, 3438, 0),
            new WorldPoint(2822, 3439, 0),
            new WorldPoint(2826, 3438, 0),
            new WorldPoint(2830, 3438, 0),
            new WorldPoint(2834, 3436, 0),
            new WorldPoint(2837, 3435, 0),
            new WorldPoint(2841, 3433, 0),
            new WorldPoint(2845, 3432, 0),
            new WorldPoint(2849, 3431, 0),
            new WorldPoint(2852, 3429, 0),
            new WorldPoint(2855, 3427, 0)
    };


}
