package data.scripts.aotdvok;

import data.ids.sillyCommodities;
import data.kaysaar.aotd.vok.campaign.econ.globalproduction.models.GPManager;

public class sillyGPManager extends GPManager {
    public static void reloadCommoditiesMap() {
        //doesnt work either go shit on cakesarr not me
        //commodities.put(sillyCommodities.REFINED_SILLY_PARTICLES, GPManager.GPResourceType.COMMODITY);
        GPManager.commodities.put("silly_refined_particles", GPManager.GPResourceType.COMMODITY);
    }
}