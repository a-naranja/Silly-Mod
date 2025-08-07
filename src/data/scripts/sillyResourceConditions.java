package data.scripts;

import com.fs.starfarer.api.impl.campaign.econ.BaseHazardCondition;

import java.util.HashMap;
import java.util.Map;

public class sillyResourceConditions extends BaseHazardCondition {
    public static Map<String, String> COMMODITY = new HashMap();
    public static Map<String, Integer> MODIFIER = new HashMap();
    public static Map<String, String> INDUSTRY = new HashMap();
    public static Map<String, Integer> BASE_MODIFIER = new HashMap();

    static {
        COMMODITY.put("silly_particles_sparse", "silly_particles");
        COMMODITY.put("silly_particles_moderate", "silly_particles");
        COMMODITY.put("silly_particles_abundant", "silly_particles");

        MODIFIER.put("silly_particles_sparse", -1);
        MODIFIER.put("silly_particles_moderate", 0);
        MODIFIER.put("silly_particles_abundant", +1);

        INDUSTRY.put("silly_particles", "mining");

        BASE_MODIFIER.put("silly_particles", -2);
    }
}
