package data.scSkills;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipVariantAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import second_in_command.SCData;
import second_in_command.specs.SCBaseSkillPlugin;

public class silly_skill3 extends  SCBaseSkillPlugin{

    @Override
    public String getAffectsString() {
        return "fleet";
    }

    @Override
    public void addTooltip(SCData scData, TooltipMakerAPI tt) {
        tt.addPara("Increases burn level by 1 (also applies to sustained)", 0f, Misc.getHighlightColor(),Misc.getHighlightColor());
        tt.addPara("Capturing points in combat is 2 times faster", 0f, Misc.getHighlightColor(),Misc.getHighlightColor());
    }
    @Override
    public void applyEffectsBeforeShipCreation(SCData data,MutableShipStatsAPI stats,ShipVariantAPI variant, ShipAPI.HullSize hullSize,String id) {
        stats.getDynamic().getStat("ship_objective_cap_rate_mult").modifyMult("Strategic Redeployment",2f);
    }
    @Override
    public void onActivation(SCData data) {
        data.getFleet().getStats().getFleetwideMaxBurnMod().modifyFlat("Strategic Redeployment", 1.0F, "Strategic Redeployment");
        data.getFleet().getStats().getDynamic().getMod("sustained_burn_bonus").modifyFlat("Strategic Redeployment", 1.0F, "Strategic Redeployment");
    }
    @Override
    public void onDeactivation(SCData data){
        data.getFleet().getStats().getFleetwideMaxBurnMod().unmodify("Strategic Redeployment");
        data.getFleet().getStats().getDynamic().getMod("sustained_burn_bonus").unmodify("Strategic Redeployment");
    }
}
