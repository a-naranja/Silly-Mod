package data.scSkills;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipVariantAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import second_in_command.SCData;
import second_in_command.specs.SCBaseSkillPlugin;

public class silly_skill9 extends SCBaseSkillPlugin {
    @Override
    public String getAffectsString() {
        return "fleet";
    }

    @Override
    public void addTooltip(SCData scData, TooltipMakerAPI tt) {
        tt.addPara("Decreases monthly maintenance by 10%%",0f, Misc.getHighlightColor(),Misc.getHighlightColor());
        tt.addPara("Increases damage dealt by 10%%",0f,Misc.getHighlightColor(),Misc.getHighlightColor());
    }
    @Override
    public void applyEffectsBeforeShipCreation(SCData data, MutableShipStatsAPI stats, ShipVariantAPI variant, ShipAPI.HullSize hullSize, String id){
        stats.getSuppliesPerMonth().modifyMult("Task Force",0.9f);
        stats.getEnergyWeaponDamageMult().modifyPercent("Task Force",10f);
        stats.getBallisticWeaponDamageMult().modifyPercent("Task Force",10f);
        stats.getMissileWeaponDamageMult().modifyPercent("Task Force",10f);
    }
}
