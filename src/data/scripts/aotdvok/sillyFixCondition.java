package data.scripts.aotdvok;

import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.impl.campaign.econ.ResourceDepositsCondition;

public class sillyFixCondition extends ResourceDepositsCondition {
    public void apply(String id){
        for(Industry industry : market.getIndustries()){
            if(market.hasCondition("silly_particles_aotd")){
                if(industry.getId().equals("mining_megaplex")){
                    industry.getSupply("silly_particles").getQuantity().modifyFlat("sillyAotdFix",7);
                }
            }
        }
    }
    public boolean showIcon() {
        return false;
    }
}
