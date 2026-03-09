package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipSystemAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

public class sillyNoSystem extends BaseHullMod {
    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        //kinda "hacky" as the screen gets a stupid long bar for cooldown but should work with most if not all system types(?)
        //ship.setShipSystemDisabled(true);
        ship.getSystem().forceState(ShipSystemAPI.SystemState.COOLDOWN,9999f);
    }
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getSuppliesPerMonth().modifyPercent("Disabled Shipsystem",-25f);
    }
    //description helpers
    @Override
    public boolean shouldAddDescriptionToTooltip(ShipAPI.HullSize hullSize, ShipAPI ship, boolean isForModSpec) {
        return false;
    }
    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        tooltip.addPara("Skillfully jams the ship's system using blunt force, software bloat and/or more.", 10f);
        tooltip.addPara("Reduces monthly supply maintenance by 25%", Misc.getPositiveHighlightColor(),3f);
    }
}
