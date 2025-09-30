package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.campaign.ids.HullMods;

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
}
