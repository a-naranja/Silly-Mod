package data.scripts.aotdvok;


import data.kaysaar.aotd.vok.campaign.econ.industry.MiningMegaplex;

public class aotdIndFixes extends MiningMegaplex {
     public void apply(){
         super.apply();
         if(market.hasCondition("silly_particles_trace")){
             this.supply("silly_particles",market.getSize()+2);
         }
         if(market.hasCondition("silly_particles_moderate")){
             this.supply("silly_particles",market.getSize()+3);
         }
         if(market.hasCondition("silly_particles_abundant")){
             this.supply("silly_particles",market.getSize()+4);
         }
     }
    public void unapply() {
        super.unapply();
    }
}