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
public class TraverseSpotsTask extends FisherTask {

    public TraverseSpotsTask(final FisherScript script) {
        super(script);
    }

    /**
     * Activate if we are far away from the fishing spot.
     * @return true if we are far away from the spot.
     */
    @Override
    public boolean activate() {
        return ctx.pathing.distanceTo(getState().getSpot().getWalkLocation()) > 4;
    }

    /**
     * Traverse to the fishing spots.
     * Check if we are far away from the spot, if so, walk the path.
     * If we are close, just step to the spot.
     */
    @Override
    public void execute() {
        final double distance = ctx.pathing.distanceTo(getState().getSpot().getWalkLocation());
        if (distance > 16) {
            ctx.pathing.walkPath(FisherConstants.BANK_TO_SPOTS_PATH);
        } else {
            ctx.pathing.step(getState().getSpot().getWalkLocation());
        }
        ctx.sleep(500);
    }

    @Override
    public String status() {
        return "Traversing to spots...";
    }
}
