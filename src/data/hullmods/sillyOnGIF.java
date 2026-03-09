package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import videolib.projector.AutoTexProjector.AutoTexProjectorAPI;
import videolib.VideoPaths;

public class sillyOnGIF extends BaseHullMod {

    //this is a "placeholder" to use videolib overrider WITH TOGGLE, check settings.json too for permanent override
    boolean run=false;
    AutoTexProjectorAPI projector = VideoPaths.getAutoTexProjectorOverride("graphics/ships/skong.png");
    int videoTextureId = projector.getCurrentTextureId(); // store this somewhere for later

    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        projector.setCurrentTextureId(projector.getOriginalTextureId(), true);
    }

    public void advanceInCombat(ShipAPI ship, float amount){
        super.advanceInCombat(ship, amount);
        if (ship.getSystem().isActive() && !run){
            projector.setCurrentTextureId(videoTextureId, false);
            run = true;
        } else if (!ship.getSystem().isOn()&&run){
            projector.setCurrentTextureId(projector.getOriginalTextureId(), true);
            run = false;
        }
    }
}