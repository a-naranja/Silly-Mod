package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.ShipAIPlugin;
import com.fs.starfarer.api.combat.ShipAPI;

public class sillyRemoveAI extends BaseHullMod {
    public String NO_AI = "silly_no_ai";
    public void advanceInCombat(ShipAPI ship, float amount){
        super.advanceInCombat(ship, amount);
        ship.setShipAI(null);
    }
}