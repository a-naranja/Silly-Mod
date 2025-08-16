package data.scSkills;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipVariantAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import second_in_command.SCData;
import second_in_command.specs.SCBaseSkillPlugin;

public class silly_skill6 extends SCBaseSkillPlugin {
    @Override
    public String getAffectsString() {
        return "Cruisers";
    }

    @Override
    public void addTooltip(SCData scData, TooltipMakerAPI ff) {
        ff.addPara("Increases missile damage by 15%% and fighter replacement by 20%%",0f, Misc.getHighlightColor(),Misc.getHighlightColor());
    }
    @Override
    public void applyEffectsBeforeShipCreation(SCData data, MutableShipStatsAPI stats, ShipVariantAPI variant, ShipAPI.HullSize hullSize, String id){
        if(hullSize== ShipAPI.HullSize.CRUISER){
            stats.getMissileWeaponDamageMult().modifyPercent(id,20f);
            stats.getFighterRefitTimeMult().modifyPercent(id, 0.70f);
        }
    }
}