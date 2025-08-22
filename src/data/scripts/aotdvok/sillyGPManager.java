package data.scripts.aotdvok;

import data.kaysaar.aotd.vok.campaign.econ.globalproduction.models.GPManager;

public class sillyGPManager extends GPManager {
    static {
        commodities.add("silly_particles");
        commodities.add("silly_refined_particles");
    }
}