package data.scripts.aotdvok;

import data.ids.sillyCommodities;
import data.kaysaar.aotd.vok.campaign.econ.globalproduction.models.GPManager;

public class sillyGPManager extends GPManager {

    public static void reloadCommoditiesMap() {
        //doesnt work... yet
        GPManager.getCommodities().put(sillyCommodities.REFINED_SILLY_PARTICLES, GPResourceType.COMMODITY);
    }
}