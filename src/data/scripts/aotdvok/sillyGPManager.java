package data.scripts.aotdvok;

import data.kaysaar.aotd.vok.campaign.econ.globalproduction.models.GPManager;

public class sillyGPManager extends GPManager {
    static {
        commodities.put("silly_particles", GPManager.GPResourceType.COMMODITY);
        commodities.put("silly_refined_particles", GPManager.GPResourceType.COMMODITY);
    }
}