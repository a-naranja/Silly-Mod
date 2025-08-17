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
        ff.addPara("Increases missile health by 50%%",0f, Misc.getHighlightColor(),Misc.getHighlightColor());
        ff.addPara("Increases fighter hull by 50%%",0f, Misc.getHighlightColor(),Misc.getHighlightColor());
    }
    @Override
    public void applyEffectsBeforeShipCreation(SCData data, MutableShipStatsAPI stats, ShipVariantAPI variant, ShipAPI.HullSize hullSize, String id){
        if(hullSize== ShipAPI.HullSize.CRUISER){
            stats.getMissileHealthBonus().modifyPercent("Support Cruiser Training",50f);
        }
    }
    public void applyEffectsToFighterSpawnedByShip(SCData data,ShipAPI fighter,ShipAPI ship,String id) {
        if(ship.isCruiser()){
            MutableShipStatsAPI fstats = fighter.getMutableStats();
            fstats.getHullBonus().modifyPercent("Support Cruiser Training", 50f);
        }
    }
    public void  onActivation(SCData data){
        data.getFleet().getStats().getDynamic().getMod("ground_attack_mod").modifyPercent("Support Cruiser Training", 20.0F, "Support Cruiser Training");
    }
    public void onDeactivation(SCData data){
        data.getFleet().getStats().getDynamic().getMod("ground_attack_mod").unmodify("Support Cruiser Training");
    }
}