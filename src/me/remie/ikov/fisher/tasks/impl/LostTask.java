package me.remie.ikov.fisher.tasks.impl;

import me.remie.ikov.fisher.FisherScript;
import me.remie.ikov.fisher.data.FisherConstants;
import me.remie.ikov.fisher.tasks.FisherTask;

/**
 * Created by Reminisce on Feb 18, 2023 at 2:01 PM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707 <138751815847116800>
 */
public class LostTask extends FisherTask {

    public LostTask(final FisherScript script) {
        super(script);
    }

    /**
     * Activate if we are not in the catherby area.
     * @return true if we are not in the catherby area.
     */
    @Override
    public boolean activate() {
        return !FisherConstants.CATHERBY_AREA.within();
    }

    /**
     * We are lost, stop the script.
     */
    @Override
    public void execute() {
        ctx.log("We are lost, stopping script.");
        ctx.stopScript();
    }

    @Override
    public String status() {
        return "We are lost...";
    }

}
