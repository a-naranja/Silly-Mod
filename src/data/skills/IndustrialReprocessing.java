package data.skills;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.CharacterStatsSkillEffect;
import com.fs.starfarer.api.characters.MarketSkillEffect;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.impl.campaign.ids.Stats;

public class IndustrialReprocessing {
        public static int demandReduction = 1;
        public static float upkeepReduction = (-20f);

    public static class Level1 implements CharacterStatsSkillEffect {
        public void apply(MutableCharacterStatsAPI stats, String id, float level) {
            stats.getDynamic().getMod(Stats.DEMAND_REDUCTION_MOD).modifyFlat(id, demandReduction);
        }

        public void unapply(MutableCharacterStatsAPI stats, String id) {
            stats.getDynamic().getMod(Stats.DEMAND_REDUCTION_MOD).unmodifyFlat(id);
        }

        public String getEffectDescription(float level) {
            return "All industries demand " + demandReduction + " less unit of all the commodities they require";
        }

        public String getEffectPerLevelDescription() {
            return null;
        }

        public ScopeDescription getScopeDescription() {
            return ScopeDescription.GOVERNED_OUTPOST;
        }
    }
    public static class Level2 implements MarketSkillEffect {
		public void apply(MarketAPI market, String id, float level) {
			market.getUpkeepMult().modifyMult(id, upkeepReduction, "Industrial planning");
		}

		public void unapply(MarketAPI market, String id) {
			market.getUpkeepMult().unmodifyMult(id);
		}

		public String getEffectDescription(float level) {
			return (int)upkeepReduction + "% upkeep for colonies";
		}

		public String getEffectPerLevelDescription() {
			return null;
		}

		public ScopeDescription getScopeDescription() {
			return ScopeDescription.GOVERNED_OUTPOST;
		}
	}
}
