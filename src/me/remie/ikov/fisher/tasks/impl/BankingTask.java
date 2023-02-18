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
public class BankingTask extends FisherTask {

    public BankingTask(final FisherScript script) {
        super(script);
    }

    /**
     * Checks if the player has a full inventory
     * @return true if the player has a full inventory
     */
    @Override
    public boolean activate() {
        return ctx.inventory.inventoryFull();
    }

    /**
     * Deposits all items in the inventory except for the fishing tool and closes the bank
     * When banking we want to ignore deposit boxs, so we use the openBank method that takes a boolean
     */
    @Override
    public void execute() {
        if (ctx.bank.openBank(true)) {
            ctx.bank.depositAllExcept(FisherConstants.TOOL_NAMES);
            ctx.sleep(1000, 2000);
            ctx.bank.closeBank();
            ctx.onCondition(() -> !ctx.bank.bankOpen(), 250, 10);
        } else {
            ctx.sleep(750, 1500);
        }
    }

    @Override
    public String status() {
        return "Banking...";
    }
}
