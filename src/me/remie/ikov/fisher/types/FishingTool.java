package me.remie.ikov.fisher.types;

/**
 * Created by Reminisce on Feb 18, 2023 at 2:57 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707 <138751815847116800>
 */
public enum FishingTool {

    SMALL_FISHING_NET("Small fishing net", 303),
    FISHING_ROD("Fishing rod", 307),
    OILY_FISHING_ROD("Oily fishing rod", 1585),
    FLY_FISHING_ROD("Fly fishing rod", 309),
    LOBSTER_POT("Lobster pot", 301),
    HARPOON("Harpoon", 311),
    BIG_FISHING_NET("Big fishing net", 305),
    KARAMBWAN_VESSEL("Karambwan vessel", 3157);

    private final String name;
    private final int id;

    FishingTool(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Gets the name of the tool.
     *
     * @return the name of the tool.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the id of the tool.
     *
     * @return the id of the tool.
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

}
