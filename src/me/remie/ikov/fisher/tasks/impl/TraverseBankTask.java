package me.remie.ikov.fisher.tasks.impl;

import me.remie.ikov.fisher.FisherScript;
import me.remie.ikov.fisher.data.FisherConstants;
import me.remie.ikov.fisher.tasks.FisherTask;

/**
 * Created by Reminisce on Feb 18, 2023 at 3:33 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707 <138751815847116800>
 */
public class TraverseBankTask extends FisherTask {

    public TraverseBankTask(final FisherScript script) {
        super(script);
    }

    /**
     * Activate if we are full and not at the bank.
     * @return true if we are full and not at the bank.
     */
    @Override
    public boolean activate() {
        return ctx.inventory.inventoryFull() && ctx.pathing.distanceTo(FisherConstants.BANK_TILE) > 8;
    }

    /**
     * Traverse to the bank. We use the pathing API to walk to the bank.
     * We also sleep for 500ms to allow the pathing API to do its thing.
     * This is not required, but it is recommended.
     * We also walk the reverse path when we are walking to the bank, as the path is from the bank to the fishing spot.
     */
    @Override
    public void execute() {
        ctx.pathing.walkPath(FisherConstants.BANK_TO_SPOTS_PATH, true);
        ctx.sleep(500);
    }

    @Override
    public String status() {
        return "Traversing to bank...";
    }
}
