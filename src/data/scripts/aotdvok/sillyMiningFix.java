package data.scripts.aotdvok;

import data.ids.sillyCommodities;
import data.kaysaar.aotd.vok.campaign.econ.industry.MiningMegaplex;

public class sillyMiningFix extends MiningMegaplex {
    @Override
    public void apply(){
        int bonus = -4;
        if (this.special != null && this.special.getId().equals("mantle_bore")) {
            bonus = 3;
        }
        if(market.hasCondition("silly_particles_trace")){
            supply(sillyCommodities.SILLY_PARTICLES,market.getSize()+bonus);
        } else if (market.hasCondition("silly_particles_moderate")) {
            supply(sillyCommodities.SILLY_PARTICLES,1+market.getSize()+bonus);
        } else if (market.hasCondition("silly_particles_abundant")){
            supply(sillyCommodities.SILLY_PARTICLES,2+market.getSize()+bonus);
        }
    }
}