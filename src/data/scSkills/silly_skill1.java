package data.scSkills;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipVariantAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import second_in_command.SCData;
import second_in_command.specs.SCBaseSkillPlugin;

public class silly_skill1 extends SCBaseSkillPlugin {
    @Override
    public String getAffectsString() {
        return "all Cruisers in the fleet";
    }

    @Override
    public void addTooltip(SCData data, TooltipMakerAPI tooltip) {
        tooltip.addPara("Increases combat readiness by 15%%", 0f, Misc.getHighlightColor(), Misc.getHighlightColor());
    }

    @Override
    public void applyEffectsBeforeShipCreation(SCData data, MutableShipStatsAPI stats, ShipVariantAPI variant, ShipAPI.HullSize hullSize, String id) {
        if (hullSize == ShipAPI.HullSize.CRUISER) {
            stats.getMaxCombatReadiness().modifyFlat(id, 0.10f);
        }
    }
}