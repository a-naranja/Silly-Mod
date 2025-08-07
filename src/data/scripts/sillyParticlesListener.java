package data.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class sillyParticlesListener implements EconomyAPI.EconomyUpdateListener{

    @Override
    public void commodityUpdated(String s) {

    }

    @Override
    public void economyUpdated() {
        //let's make names so its more readable
        String commodity = "silly_particles";
        String industry = "mining";
        String source = "sillyParticlesListener";
        //String demandIndustry = "whatever my g";
        //now we loop all the markets to add whimsy
        for (MarketAPI market : Global.getSector().getEconomy().getMarketsCopy()){
            //check for industry (anti-crash)
            if (market.hasIndustry(industry)){
                //finally we add stuff to the industry
                market.getIndustry(industry).getSupply(commodity).getQuantity().modifyFlat(source, -2);
            }
        }
    }

    @Override
    public boolean isEconomyListenerExpired() {
        return false;
    }
}
