package me.remie.ikov.fisher.tasks.impl;

import me.remie.ikov.fisher.FisherScript;
import me.remie.ikov.fisher.tasks.FisherTask;
import me.remie.ikov.fisher.types.FishingSpot;
import simple.api.wrappers.SimpleNpc;

/**
 * Created by Reminisce on Feb 18, 2023 at 3:34 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707 <138751815847116800>
 */
public class FishingTask extends FisherTask {

    public FishingTask(final FisherScript script) {
        super(script);
    }

    /**
     * As this is the last task, we want to activate it all the time, as we assume we are fishing.
     * @return true
     */
    @Override
    public boolean activate() {
        return true;
    }

    /**
     * Interacts with the fishing spot, and waits for the animation to start.
     * If the animation does not start, we assume we are not at the fishing spot, and we try again.
     * If the animation does start, we set the last animation time to the current time.
     * This is used to determine if we're already fishing, or if we need to interact with the fishing spot again.
     */
    @Override
    public void execute() {
        final FishingSpot spot = getState().getSpot();
        if (ctx.players.getLocal().getAnimation() == spot.getAnimationId()) {
            setStatus("Fishing " + spot.getName() + "...");
            getState().setLastAnimation(System.currentTimeMillis());
        }
        if (System.currentTimeMillis() - getState().getLastAnimation() > 2000) {
            final SimpleNpc spotNpc = ctx.npcs.populate().filter(spot.getNpcId()).nextNearest();
            if (spotNpc != null) {
                setStatus("Interacting with " + spot.getName() + "...");
                spotNpc.interact(spot.getActionId());
                if (ctx.onCondition(() -> ctx.players.getLocal().getAnimation() == spot.getAnimationId(), 250, 10)) {
                    getState().setLastAnimation(System.currentTimeMillis());
                }
            }
        }
    }

    @Override
    public String status() {
        return "Fishing...";
    }
}
