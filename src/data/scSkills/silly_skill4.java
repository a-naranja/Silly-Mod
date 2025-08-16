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
        tt.addPara("Improves the Cruisers autofire aim accuracy",0f,Misc.getHighlightColor(),Misc.getHighlightColor());
        tt.addPara("Deals 50%% more damage to missiles and fighters",0f, Misc.getHighlightColor(),Misc.getHighlightColor());
    }
    @Override
    public void applyEffectsBeforeShipCreation(SCData data,MutableShipStatsAPI stats,ShipVariantAPI variant,ShipAPI.HullSize hullSize,String id){
        if(hullSize == ShipAPI.HullSize.CRUISER){
            stats.getAutofireAimAccuracy().modifyFlat(id, 0.25f);
            stats.getDamageToFighters().modifyPercent(id, 50f);
            stats.getDamageToMissiles().modifyPercent(id, 50f);
        }
    }
}
