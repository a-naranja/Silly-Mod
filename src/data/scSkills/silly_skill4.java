package data.scSkills;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipVariantAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import second_in_command.SCData;
import second_in_command.specs.SCBaseSkillPlugin;

public class silly_skill4 extends SCBaseSkillPlugin {

    @Override
    public String getAffectsString() {
        return "Cruisers";
    }

    @Override
    public void addTooltip(SCData scData, TooltipMakerAPI tt) {
        tt.addPara("Improves autofire aim accuracy",0f,Misc.getHighlightColor(),Misc.getHighlightColor());
        tt.addPara("Deals 50%% more damage to missiles and fighters",0f, Misc.getHighlightColor(),Misc.getHighlightColor());
        tt.addPara("Increases speed and acceleration by 10%%",0f, Misc.getHighlightColor(),Misc.getHighlightColor());
    }
    @Override
    public void applyEffectsBeforeShipCreation(SCData data,MutableShipStatsAPI stats,ShipVariantAPI variant,ShipAPI.HullSize hullSize,String id){
        if(hullSize == ShipAPI.HullSize.CRUISER){
            String desc = "Light Cruiser Training";
            stats.getAutofireAimAccuracy().modifyFlat(desc, 0.25f);
            stats.getDamageToFighters().modifyPercent(desc, 50f);
            stats.getDamageToMissiles().modifyPercent(desc, 50f);
            stats.getMaxSpeed().modifyPercent(desc,10f);
            stats.getAcceleration().modifyPercent(desc,10f);
        }
    }
}
