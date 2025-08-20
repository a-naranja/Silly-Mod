package data.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import data.ids.sillyCommodities;
import data.ids.sillyFactions;

public class researchPark extends BaseIndustry {

    protected transient SubmarketAPI saved = null;

    @Override
    public void apply() {
        super.apply(true);
        int size = market.getSize();
        demand(Commodities.SHIPS, size -1);
        demand(Commodities.METALS, size);
        demand(Commodities.RARE_METALS, size -1);
        demand(Commodities.DRUGS, size -1);
        demand(sillyCommodities.SILLY_PARTICLES, size);
        if(!isFunctional()){
            supply.clear();
            unapply();
        }
        if(isFunctional()){
            supply(Commodities.SHIPS, 1);
            supply(Commodities.HAND_WEAPONS,1);
        }
        if (isFunctional()) {
            SubmarketAPI open = market.getSubmarket(Submarkets.GENERIC_MILITARY);
            if (open == null) {
                if (saved != null) {
                    market.addSubmarket(saved);
                } else {
                    market.addSubmarket(Submarkets.GENERIC_MILITARY);
                    SubmarketAPI mil = market.getSubmarket(Submarkets.GENERIC_MILITARY);
                    mil.setFaction(Global.getSector().getFaction(sillyFactions.DOOHICKEYELITE));
                    Global.getSector().getEconomy().forceStockpileUpdate(market);
                }
            }
        } else if (market.isPlayerOwned()) {
            market.removeSubmarket(Submarkets.GENERIC_MILITARY);
        }
        getIncome().modifyFlat("Silly Research Park", 42000f);
    }
    @Override
    public void unapply() {
        //unapply things when industry is demolished
        super.unapply();
        SubmarketAPI mil = market.getSubmarket(Submarkets.GENERIC_MILITARY);
        saved = mil;
        market.removeSubmarket(Submarkets.GENERIC_MILITARY);
        getIncome().unmodify("Silly Research Park");
    }
}
