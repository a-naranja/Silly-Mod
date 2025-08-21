package data.achievos;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.util.Misc;
import data.ids.sillyIndustries;
import org.magiclib.achievements.MagicAchievement;


public class sillyAchiev extends MagicAchievement {
    boolean condition = false;

    public void onSaveGameLoaded(boolean isComplete) {
        super.onSaveGameLoaded(isComplete);
        if (!isComplete) {
            Global.getSector().getListenerManager().addListener(this, true);
        }
    }

    public void advanceAfterInterval(float amount) {
        for(MarketAPI playerMarket : Misc.getPlayerMarkets(true)) {
            if (playerMarket.hasIndustry(sillyIndustries.SILLY_RESEARCH_PARK) && playerMarket.hasIndustry(Industries.MINING)&&
                    (playerMarket.hasCondition("silly_particles_trace")|| playerMarket.hasCondition("silly_particles_moderate")|| playerMarket.hasCondition("silly_particles_abundant"))) {
                condition = true;
            }
        }
        if (condition){
            this.completeAchievement();
        }
    }

    public void onDestroyed() {
        super.onDestroyed();
        Global.getSector().getListenerManager().removeListener(this);
    }
}
