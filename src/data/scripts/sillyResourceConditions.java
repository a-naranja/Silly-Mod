package data.scripts;

import com.fs.starfarer.api.impl.campaign.econ.ResourceDepositsCondition;

public class sillyResourceConditions extends ResourceDepositsCondition {
    static {
        COMMODITY.put("silly_particles_trace", "silly_particles");
        COMMODITY.put("silly_particles_moderate", "silly_particles");
        COMMODITY.put("silly_particles_abundant", "silly_particles");
        MODIFIER.put("silly_particles_trace", -1);
        MODIFIER.put("silly_particles_moderate", 0);
        MODIFIER.put("silly_particles_abundant", +1);
        INDUSTRY.put("silly_particles", "mining");
        BASE_MODIFIER.put("silly_particles", -2);
    }
}