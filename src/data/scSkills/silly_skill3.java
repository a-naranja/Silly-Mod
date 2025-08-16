package data.scSkills;

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
        tt.addPara("Increases the fleet burn by 1 and makes combat points capture faster", 0f, Misc.getHighlightColor(),Misc.getHighlightColor());
    }
    @Override
    public void onActivation(SCData data) {
        data.getFleet().getStats().getFleetwideMaxBurnMod().modifyFlat("silly_skill3", 1.0F, "Strategic Redeployment");
        data.getFleet().getStats().getDynamic().getMod("sustained_burn_bonus").modifyFlat("silly_skill3", 1.0F, "Strategic Redeployment");
    }
    @Override
    public void onDeactivation(SCData data){
        data.getFleet().getStats().getFleetwideMaxBurnMod().unmodify("silly_skill3");
        data.getFleet().getStats().getDynamic().getMod("sustained_burn_bonus").unmodify("silly_skill3");
    }
}
