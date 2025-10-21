package data.hullmods;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.ShipAPI;

public class sillyGremSpawner extends BaseHullMod {
    //dont use, doesnt work, crashes
    @Override
    public void advanceInCombat(ShipAPI ship, float amount) {
        super.advanceInCombat(ship, amount);
        boolean once = false;
        if(ship.isHulk()&& !once){
            CombatEngineAPI engine = Global.getCombatEngine();
            engine.getFleetManager(ship.getOriginalOwner()).spawnShipOrWing("silly_gremmk1_custom", ship.getLocation(), ship.getFacing());
            once = true;
        }
    }
}
