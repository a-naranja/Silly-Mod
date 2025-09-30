package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;

public class sillyDual extends BaseHullMod {
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id){
        stats.getBallisticWeaponDamageMult().modifyMult("Chopped", 0.4f);
        stats.getBallisticRoFMult().modifyMult("Chopped", 2f);

        stats.getEnergyWeaponDamageMult().modifyMult("Cooked",1.8f);
        stats.getEnergyRoFMult().modifyMult("Cooked",0.5f);
    }
    public String getDescriptionParam(int index, ShipAPI.HullSize hullSize) {
        if (index == 0) return "40%";
        if (index == 1) return "80%";
        return null;
    }
}