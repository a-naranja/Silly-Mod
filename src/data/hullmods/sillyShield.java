package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.ShipAPI;

public class sillyShield extends BaseHullMod {
    public String sillyShield = "sillyShield";
    public void applyEffectsAfterShipCreation(ShipAPI ship, String id){
        if(!ship.getCustomData().containsKey("appliedShield")){
            ship.getShield().setRadius(ship.getShield().getRadius(), "graphics/fx/sillyShieldFX.png", "graphics/fx/sillyShieldFX.png");
            ship.setCustomData("appliedShield",1);
        }
    }
}