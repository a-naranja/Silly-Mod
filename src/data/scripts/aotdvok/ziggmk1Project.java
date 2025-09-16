package data.scripts.aotdvok;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.kaysaar.aotd.vok.scripts.research.AoTDMainResearchManager;
import data.kaysaar.aotd.vok.scripts.specialprojects.models.AoTDSpecialProject;

public class ziggmk1Project extends AoTDSpecialProject {
    public void createRewardSection(TooltipMakerAPI tooltip, float width) {
        tooltip.addPara("Gain Ziggurat Mk.I vessel design blueprint", Misc.getPositiveHighlightColor(), 5.0F);
    }

    public boolean checkIfProjectShouldUnlock() {
        return Global.getSector().getMemory().is("$sillyZiggBounty", true) && AoTDMainResearchManager.getInstance().isResearchedForPlayer("aotd_tech_stella_manufactorium");
    }

    public Object grantReward() {
        MarketAPI gatheringPoint = Global.getSector().getPlayerFaction().getProduction().getGatheringPoint();
        if (gatheringPoint == null) {
            gatheringPoint = (MarketAPI)Misc.getPlayerMarkets(true).get(0);
        }
        //yea eh pray that it works
        CargoAPI cargo = gatheringPoint.getSubmarket("storage").getCargo();
        SpecialItemData bp = new SpecialItemData("silly_ziggBP","silly_ziggBP");
        cargo.addSpecial(bp,1);
        return null;
    }
}
