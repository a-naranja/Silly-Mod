package data.shipSystems;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;

public class sillySwitch extends BaseShipSystemScript {

    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
        if (state == ShipSystemStatsScript.State.OUT) {
            stats.getMaxSpeed().unmodify("Silly Switch"); // to slow down ship to its regular top speed while powering down
        } else {
            stats.getMaxSpeed().modifyMult("Silly Switch", 2f * effectLevel);
            stats.getAcceleration().modifyPercent("Silly Switch", 50f * effectLevel);
        }
    }
    public void unapply(MutableShipStatsAPI stats, String id) {
        stats.getMaxSpeed().unmodify("Silly Switch");
        stats.getMaxTurnRate().unmodify("Silly Switch");
        stats.getTurnAcceleration().unmodify("Silly Switch");
        stats.getAcceleration().unmodify("Silly Switch");
        stats.getDeceleration().unmodify("Silly Switch");
    }
}
