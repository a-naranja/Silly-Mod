package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

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
    //description helpers
    @Override
    public boolean shouldAddDescriptionToTooltip(ShipAPI.HullSize hullSize, ShipAPI ship, boolean isForModSpec) {
        return false;
    }
    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        tooltip.addPara("Special modifications made to this ship alter the usual performance of weapons depending" +
                " on their type. Missiles are not affected.", 10f);
        tooltip.addPara("Ballistics double their rate of fire, energy weapons increase their damage by 80%", Misc.getPositiveHighlightColor(),3f);
        tooltip.addPara("Ballistics reduce their damage by 60%, energy weapons reduce their rate of fire to 50%", Misc.getNegativeHighlightColor(),3f);
    }
}