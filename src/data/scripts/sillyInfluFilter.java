package data.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.ids.sillyCommodities;
import data.kaysaar.aotd.vok.scripts.research.AoTDMainResearchManager;

public class sillyInfluFilter extends BaseIndustry {

    String name = "Whimsy Filter";

    @Override
    public void apply() {
        super.apply(true);
        int size = market.getSize();
        demand(Commodities.HEAVY_MACHINERY, size -2);
        demand(sillyCommodities.REFINED_SILLY_PARTICLES, size);
        if(isFunctional()&& getAllDeficit().isEmpty()){
            market.getIncomeMult().modifyMult(name,1.2f,"Whimsy Filter improves negotiations");
        }
    }

    @Override
    public void unapply() {
        super.unapply();
        market.getIncomeMult().unmodifyMult(name);
    }

    public float getPatherInterest() {
        return 2f + super.getPatherInterest();
    }

    protected void addPostSupplySection(TooltipMakerAPI tooltip, boolean hasSupply, IndustryTooltipMode mode) {
        addPostDescriptionSection(tooltip, mode);
        if (isFunctional()&& getAllDeficit().isEmpty()){
            tooltip.addPara("Improving income by 20%", Misc.getPositiveHighlightColor(),10f);
        } else if (isFunctional()&& !getAllDeficit().isEmpty()){
            tooltip.addPara("Shortage! No effects!", Misc.getNegativeHighlightColor(), 10f);
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
    public boolean isAvailableToBuild() {
        if (Global.getSettings().getModManager().isModEnabled("aotd_vok") && !market.hasCondition("habitable")) {
            return AoTDMainResearchManager.getInstance().isAvailableForThisMarket("silly_filter_tech", this.market);
        } else return !market.hasCondition("habitable");
    }
    public String getUnavailableReason() {
        return "Can not be built on habitable planets!";
    }
    public boolean showWhenUnavailable() {
        if(Global.getSettings().getModManager().isModEnabled("aotd_vok")){
            return AoTDMainResearchManager.getInstance().isAvailableForThisMarket("silly_filter_tech", this.market);
        } else return true;
    }
}