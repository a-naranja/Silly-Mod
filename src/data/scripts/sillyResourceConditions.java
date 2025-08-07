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

    public Map<String, String> getTokenReplacements() {
        return super.getTokenReplacements();
    }

    public String[] getHighlights() {
        return super.getHighlights();
    }

    protected void createTooltipAfterDescription(TooltipMakerAPI tooltip, boolean expanded) {
        super.createTooltipAfterDescription(tooltip, expanded);
        String commodityId = (String)COMMODITY.get(this.condition.getId());
        if (commodityId != null) {
            Integer mod = (Integer)MODIFIER.get(this.condition.getId());
            if (mod != null) {
                CommoditySpecAPI spec = Global.getSettings().getCommoditySpec(commodityId);
                String industryId = (String)INDUSTRY.get(commodityId);
                if (commodityId.equals("food") && this.condition.getId().equals("water_surface") && industryId.equals("farming")) {
                    industryId = "aquaculture";
                } else if (commodityId.equals("lobster") && this.condition.getId().equals("volturnian_lobster_pens") && industryId.equals("farming")) {
                    industryId = "aquaculture";
                }

                IndustrySpecAPI ind = Global.getSettings().getIndustrySpec(industryId);
                String str = "" + mod;
                if (mod > 0) {
                    str = "+" + mod;
                }

                String text = "";
                if (mod == 0) {
                    text = "No bonuses or penalties to " + spec.getName().toLowerCase() + " production (" + ind.getName() + ")";
                } else {
                    text = str + " " + spec.getName().toLowerCase() + " production (" + ind.getName() + ")";
                }

                float pad = 10.0F;
                tooltip.addPara(text, pad, Misc.getHighlightColor(), new String[]{str});
            }
        }

    }

    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        float qty = 0.0F;
        if ("farmland_poor".equals(this.condition.getId())) {
            qty = 5.0F;
        } else if ("farmland_adequate".equals(this.condition.getId())) {
            qty = 10.0F;
        } else if ("farmland_rich".equals(this.condition.getId())) {
            qty = 20.0F;
        } else if ("farmland_bountiful".equals(this.condition.getId())) {
            qty = 30.0F;
        } else if ("water_surface".equals(this.condition.getId())) {
            qty = 10.0F;
        }

        if (qty > 0.0F) {
            incoming.add("luddic_church", qty);
        }

    }
}
