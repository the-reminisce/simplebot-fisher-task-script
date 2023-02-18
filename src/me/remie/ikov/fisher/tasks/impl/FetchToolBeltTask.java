package me.remie.ikov.fisher.tasks.impl;

import me.remie.ikov.fisher.FisherScript;
import me.remie.ikov.fisher.tasks.FisherTask;
import simple.api.wrappers.SimpleItem;

/**
 * Created by Reminisce on Feb 18, 2023 at 3:05 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707 <138751815847116800>
 */
public class FetchToolBeltTask extends FisherTask {

    public FetchToolBeltTask(final FisherScript script) {
        super(script);
    }

    @Override
    public boolean activate() {
        return !getState().isToolBeltFetched();
    }

    /**
     * Fetches the tool belt and adds the items to the state.
     * If the tool belt is not open, it will open it.
     * If the tool belt is open, it will add the items to the state.
     * It will check if the required tool is in the tool belt, if not, it will stop the script.
     */
    @Override
    public void execute() {
        if (ctx.toolBelt.open()) {
            ctx.sleep(1000, 1500);
            for (SimpleItem item : ctx.toolBelt.populate()) {
                getState().getToolBelt().add(item.getId());
            }
            getState().setToolBeltFetched();
            ctx.toolBelt.close();
            ctx.onCondition(() -> !ctx.toolBelt.opened(), 150, 10);
            if (!getState().hasTool(getState().getSpot().getTool())) {
                ctx.log("You do not have the required tool in your tool belt, stopping script.");
                ctx.stopScript();
            }
        }
    }

    @Override
    public String status() {
        return "Fetching tool belt";
    }
}
