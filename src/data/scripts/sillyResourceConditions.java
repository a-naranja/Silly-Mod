package data.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.econ.BaseHazardCondition;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.loading.IndustrySpecAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class sillyResourceConditions extends BaseHazardCondition {
    public static Map<String, String> COMMODITY = new HashMap();
    public static Map<String, Integer> MODIFIER = new HashMap();
    public static Map<String, String> INDUSTRY = new HashMap();
    public static Map<String, Integer> BASE_MODIFIER = new HashMap();
    public static Set<String> BASE_ZERO = new HashSet();

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

    public void apply(String id) {
        super.apply(id);
        String commodityId = (String)COMMODITY.get(this.condition.getId());
        if (commodityId != null) {
            Integer mod = (Integer)MODIFIER.get(this.condition.getId());
            if (mod != null) {
                Integer baseMod = (Integer)BASE_MODIFIER.get(commodityId);
                if (baseMod != null) {
                    String industryId = (String)INDUSTRY.get(commodityId);
                    if (industryId != null) {
                        Industry industry = this.market.getIndustry(industryId);
                        if (industry == null) {
                            if ("farming".equals(industryId)) {
                                industryId = "aquaculture";
                                industry = this.market.getIndustry(industryId);
                            }

                            if (industry == null) {
                                return;
                            }
                        }

                        int size = this.market.getSize();
                        if (BASE_ZERO.contains(commodityId)) {
                            size = 0;
                        }

                        int base = size + baseMod;
                        if (industry.isFunctional()) {
                            industry.supply(id + "_0", commodityId, base, "Base value for colony size");
                            industry.supply(id + "_1", commodityId, mod, Misc.ucFirst(this.condition.getName().toLowerCase()));
                        } else {
                            industry.getSupply(commodityId).getQuantity().unmodifyFlat(id + "_0");
                            industry.getSupply(commodityId).getQuantity().unmodifyFlat(id + "_1");
                        }
                    }
                }
            }
        }
    }

    public void unapply(String id) {
        super.unapply(id);
    }
}