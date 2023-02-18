package me.remie.ikov.fisher.tasks.impl;

import me.remie.ikov.fisher.FisherScript;
import me.remie.ikov.fisher.data.FisherConstants;
import me.remie.ikov.fisher.tasks.FisherTask;

/**
 * Created by Reminisce on Feb 18, 2023 at 3:36 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707 <138751815847116800>
 */
public class DropItemsTask extends FisherTask {

    public DropItemsTask(final FisherScript script) {
        super(script);
    }

    /**
     * Check if we're in power fishing mode and our inventory is full.
     * @return true if we're in power fishing mode and our inventory is full.
     */
    @Override
    public boolean activate() {
        return getSettings().isPowerFishing() && ctx.inventory.inventoryFull();
    }

    /**
     * Drop all items in our inventory except for the ones we want to keep.
     */
    @Override
    public void execute() {
        ctx.inventory.populate().omitContains(FisherConstants.AVOID_DROP_NAMES);
        ctx.inventory.dropItems();
        ctx.sleep(1000, 2000);
    }

    @Override
    public String status() {
        return "Dropping items...";
    }
}
