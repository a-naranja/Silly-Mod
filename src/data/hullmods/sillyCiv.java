package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.campaign.ids.HullMods;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

public class sillyCiv extends BaseHullMod {
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        boolean sMod = isSMod(stats);

        stats.getSensorStrength().modifyMult("Civilian Conversion",0.5f);
        stats.getSensorProfile().modifyMult("Civilian Conversion", 2f);
        stats.getSuppliesPerMonth().modifyPercent("Civilian Conversion",-50f);

        if (!sMod) {
            stats.getMaxBurnLevel().modifyFlat("Civilian Conversion", -1);
            stats.getFluxCapacity().modifyMult("Civilian Conversion", 0.5f);
            stats.getFluxDissipation().modifyMult("Civilian Conversion",0.5f);
        }

    }
    public String getDescriptionParam(int index, ShipAPI.HullSize hullSize) {
        if (index == 0) return "worsens";
        if (index == 1) return "reduces burn by 1";
        if (index == 2) return "halves";
        return null;
    }

    @Override
    public boolean isApplicableToShip(ShipAPI ship) {
        return !ship.getVariant().hasHullMod(HullMods.CIVGRADE) && super.isApplicableToShip(ship);
    }

    @Override
    public String getUnapplicableReason(ShipAPI ship) {
        if (ship.getVariant().hasHullMod(HullMods.CIVGRADE)) {
            return "Can only be installed on military-grade hulls";
        }
        return super.getUnapplicableReason(ship);
    }

    //description helpers
    @Override
    public boolean shouldAddDescriptionToTooltip(ShipAPI.HullSize hullSize, ShipAPI ship, boolean isForModSpec) {
        return false;
    }
    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        tooltip.addPara("Removes many military-grade subsystems and equipment to reduce supply consumption." +
                " This worsens the ships sensors, flux grid and burn. Does not make the ship a civilian-hull for" +
                " other bonuses, as this is a conversion.", 10f);
        tooltip.addPara("Half monthly supply cost for this ship.", Misc.getPositiveHighlightColor(),3f);
        tooltip.addPara("Reduces sensor strength, flux capacity and dissipation, maximum burn and increases sensor profile.", Misc.getNegativeHighlightColor(),3f);
    }
}
