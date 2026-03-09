package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import java.awt.*;

public class sillyEngineInjection  extends BaseHullMod {
    //stat change
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getEngineHealthBonus().modifyMult("Silly Engine Injection", 0.3f);
        stats.getCombatEngineRepairTimeMult().modifyMult("Silly Engine Injection", 0.3f);
    }
    //funny engine
    private Color color = new Color(255,100,255,255);
    public void advanceInCombat(ShipAPI ship, float amount) {
        ship.getEngineController().fadeToOtherColor(this, color, null, 1f, 0.8f);
    }

    //description helpers
    @Override
    public boolean shouldAddDescriptionToTooltip(ShipAPI.HullSize hullSize, ShipAPI ship, boolean isForModSpec) {
        return false;
    }
    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        tooltip.addPara("Adulterates the engine's combustible with Silly Particles, improving quickstart but reducing stability.", 10f);
        tooltip.addPara("Reduce engine repair speed to 1/3", Misc.getPositiveHighlightColor(),10f);
        tooltip.addPara("Reduce engine durability to 1/3", Misc.getNegativeHighlightColor(),10f);
    }
}
