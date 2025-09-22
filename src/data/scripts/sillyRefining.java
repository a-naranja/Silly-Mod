package data.scripts;

import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.util.Pair;
import data.ids.sillyCommodities;

public class sillyRefining extends BaseIndustry {

    public void apply(){
        super.apply(true);
        int size = market.getSize();
        demand(Commodities.MARINES,size -3);
        demand(Commodities.HEAVY_MACHINERY, size -1);
        demand(sillyCommodities.SILLY_PARTICLES, size);
        Pair<String, Integer> deficit = getMaxDeficit(Commodities.HEAVY_MACHINERY, sillyCommodities.SILLY_PARTICLES);
        applyDeficitToProduction(1, deficit, sillyCommodities.REFINED_SILLY_PARTICLES);
        if (isFunctional()) {
            supply(sillyCommodities.REFINED_SILLY_PARTICLES, size -2);
        } else if (!isFunctional()){
            supply.clear();
        }
    }
    @Override
    public void unapply() {
        super.unapply();
    }
    public float getPatherInterest() {
        return 2f + super.getPatherInterest();
    }
}
