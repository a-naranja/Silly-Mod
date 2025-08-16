package data.scSkills;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipVariantAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import second_in_command.SCData;
import second_in_command.specs.SCBaseSkillPlugin;

public class silly_skill8 extends SCBaseSkillPlugin {
    @Override
    public String getAffectsString() {
        return "Frigates and Destroyers";
    }

    @Override
    public void addTooltip(SCData scData, TooltipMakerAPI tt) {
        tt.addPara("Increases the combat readiness by 10%%",0f, Misc.getHighlightColor(),Misc.getHighlightColor());
        tt.addPara("Decreases the damage received by 15%%",0f,Misc.getHighlightColor(),Misc.getHighlightColor());
    }
    @Override
    public void applyEffectsBeforeShipCreation(SCData data, MutableShipStatsAPI stats, ShipVariantAPI variant, ShipAPI.HullSize hullSize, String id){
        if(hullSize== ShipAPI.HullSize.DESTROYER||hullSize== ShipAPI.HullSize.FRIGATE){
            stats.getMaxCombatReadiness().modifyFlat(id,0.10f);
            stats.getHullDamageTakenMult().modifyMult(id,0.9f);
            stats.getArmorDamageTakenMult().modifyMult(id,0.9f);
            stats.getShieldDamageTakenMult().modifyMult(id,0.9f);
        }
    }
}
