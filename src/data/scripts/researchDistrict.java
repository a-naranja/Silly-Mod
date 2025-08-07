package data.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import data.ids.sillyFactions;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;

import static com.fs.starfarer.api.campaign.RepLevel.getRepInt;


public class researchDistrict extends BaseIndustry {

    protected transient SubmarketAPI saved = null;

    public void apply(){
        //apply things when industry is built
        super.apply(true);

        //declare vars, save time
        int size = market.getSize();
        //ask for stuff, you can also use supply(Commodities. to add supply but eh not now not for this
        demand(Commodities.SHIPS, size -2);
        demand(Commodities.METALS, size -1);
        demand(Commodities.RARE_METALS, size -2);
        demand(Commodities.DRUGS, size -3);
        //add doohikey mil market if built on player colonies
        if (isFunctional() && market.isPlayerOwned()) {
            SubmarketAPI open = market.getSubmarket(Submarkets.GENERIC_MILITARY);
            if (open == null) {
                if (saved != null) {
                    market.addSubmarket(saved);
                } else {
                    market.addSubmarket(Submarkets.GENERIC_MILITARY);
                    SubmarketAPI mil = market.getSubmarket(Submarkets.GENERIC_MILITARY);
                    mil.setFaction(Global.getSector().getFaction(sillyFactions.DOOHICKEYCORP));
                    Global.getSector().getEconomy().forceStockpileUpdate(market);
                }
            }
        } else if (market.isPlayerOwned()) {
            market.removeSubmarket(Submarkets.GENERIC_MILITARY);
        }
    }
    @Override
    public void unapply() {
        //unapply things when industry is demolished
        super.unapply();
        if (market.isPlayerOwned()) {
            SubmarketAPI mil = market.getSubmarket(Submarkets.GENERIC_MILITARY);
            saved = mil;
            market.removeSubmarket(Submarkets.GENERIC_MILITARY);
        }
    }
}