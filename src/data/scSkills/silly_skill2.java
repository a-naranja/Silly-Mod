package data.scSkills;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import second_in_command.SCData;
import second_in_command.specs.SCBaseSkillPlugin;

public class silly_skill2 extends SCBaseSkillPlugin {
    @Override
    public String getAffectsString() {
        return "fleet";
    }

    @Override
    public void addTooltip(SCData scData, TooltipMakerAPI tt) {
        tt.addPara("Increases the post-battle salvage by 20%%",0f,Misc.getHighlightColor(), Misc.getHighlightColor());
        tt.addPara("Decreases the fleet-wide sensor profile by 20%%",0f,Misc.getHighlightColor(), Misc.getHighlightColor());
    }

    @Override
    public void onActivation(SCData data){
        data.getFleet().getStats().getDynamic().getStat("salvage_value_bonus_fleet_not_rare").modifyFlat("silly_skill2", 0.2F);
        data.getFleet().getStats().getDetectedRangeMod().modifyMult("silly_skill2", 0.80f,"Guerrilla Warfare");
    }
    @Override
    public void onDeactivation(SCData data){
        data.getFleet().getStats().getDynamic().getStat("salvage_value_bonus_fleet_not_rare").unmodify("silly_skill2");
        data.getFleet().getStats().getDetectedRangeMod().unmodify("silly_skill2");
    }
}