package data.shipSystems;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;

public class sillyJump extends BaseShipSystemScript {
    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		if (state == ShipSystemStatsScript.State.OUT) {
			stats.getMaxSpeed().unmodify("Silly Jump"); // to slow down ship to its regular top speed while powering down
		} else {
			stats.getMaxSpeed().modifyFlat("Silly Jump", 500f * effectLevel);
			stats.getAcceleration().modifyFlat("Silly Jump", 10000f * effectLevel);
		}
    }
    public void unapply(MutableShipStatsAPI stats, String id) {
		stats.getMaxSpeed().unmodify("Silly Jump");
		stats.getMaxTurnRate().unmodify("Silly Jump");
		stats.getTurnAcceleration().unmodify("Silly Jump");
		stats.getAcceleration().unmodify("Silly Jump");
		stats.getDeceleration().unmodify("Silly Jump");
    }
}
