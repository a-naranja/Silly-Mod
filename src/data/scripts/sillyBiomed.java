package data.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.ids.sillyCommodities;
import data.kaysaar.aotd.vok.scripts.research.AoTDMainResearchManager;

public class sillyBiomed extends BaseIndustry {

    String name = "RSP Biomedical Laboratory";

    @Override
    public void apply() {
        super.apply(true);
        int size = market.getSize();
        demand(Commodities.ORGANICS, size -1);
        demand(sillyCommodities.REFINED_SILLY_PARTICLES, size -2);
        if(isFunctional()&& getAllDeficit().isEmpty()){
            market.getHazard().modifyFlat(name, -0.15f);
        }
    }

    @Override
    public void unapply() {
        super.unapply();
        market.getHazard().unmodifyFlat(name);
    }
    public float getPatherInterest() {
        return 2f + super.getPatherInterest();
    }

    protected void addPostSupplySection(TooltipMakerAPI tooltip, boolean hasSupply, IndustryTooltipMode mode) {
        addPostDescriptionSection(tooltip, mode);
        if (isFunctional()&& getAllDeficit().isEmpty()){
            tooltip.addPara("Reducing Hazard Rating by 15%", Misc.getPositiveHighlightColor(),10f);
        }
    }

    @Override
    public boolean canImprove() {
        return false;
    }

    @Override
    public boolean canInstallAICores() {
        return false;
    }

    //aotd check
    //public boolean isAvailableToBuild(){
        //if(Global.getSettings().getModManager().isModEnabled("aotd_vok")){
            //return AoTDMainResearchManager.getInstance().isAvailableForThisMarket("silly_biomed_tech", this.market);
        //} else return true;
    //}
}