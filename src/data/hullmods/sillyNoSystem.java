package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipSystemAPI;

public class sillyNoSystem extends BaseHullMod {
    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        //ship.setShipSystemDisabled(true);
        ship.getSystem().forceState(ShipSystemAPI.SystemState.COOLDOWN,9999f);
    }
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getSuppliesPerMonth().modifyPercent("Disabled Shipsystem",-25f);
    }
}
