package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipSystemAPI;

public class sillyPhaseModules extends BaseHullMod {
    public static String PHASE_MODULES = "phase_modules";

    public void advanceInCombat(ShipAPI ship, float amount){
        super.advanceInCombat(ship, amount);
        for(ShipAPI module : ship.getChildModulesCopy()) {
            if (module.getStationSlot() != null && module.isAlive() && ship.isPhased()) {
                module.setPhased(true);
                module.getPhaseCloak().forceState(ShipSystemAPI.SystemState.ACTIVE, amount);
            } else {
                module.setPhased(false);
                module.getPhaseCloak().forceState(ShipSystemAPI.SystemState.IDLE, amount);
            }
        }
    }
}