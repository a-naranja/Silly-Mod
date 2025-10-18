package data.scripts.aotdvok;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.listeners.EconomyTickListener;
import com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin;



public class aotdIndFixes extends BaseMarketConditionPlugin implements EconomyTickListener {
    public void addConditionFix(){
        for(MarketAPI market : Global.getSector().getEconomy().getMarketsCopy()){
            if((market.hasCondition("silly_particles_trace")|| market.hasCondition("silly_particles_moderate")|| market.hasCondition("silly_particles_abundant"))&& !market.hasCondition("silly_particles_aotd")){
                market.addCondition("silly_particles_aotd");
            }
        }
    }
    //

    @Override
    public void reportEconomyTick(int iterIndex) {
        addConditionFix();
    }

    @Override
    public void reportEconomyMonthEnd() {

    }
}