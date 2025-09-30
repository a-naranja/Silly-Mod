package data.skills;

import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;

public class Quartermaster {

    public static float cargoBoni = 1.1f;
    public static float fuelBoni = 1.1f;

    public static class Level1 implements ShipSkillEffect{


        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getCargoMod().modifyMult(id,cargoBoni);
        }


        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getCargoMod().unmodify(id);
        }


        public String getEffectDescription(float level) {
            return "+10% cargo capacity";
        }


        public String getEffectPerLevelDescription() {
            return null;
        }


        public ScopeDescription getScopeDescription() {
            return ScopeDescription.PILOTED_SHIP;
        }
    }
    public static class Level2 implements ShipSkillEffect{
        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
            stats.getFuelMod().modifyMult(id,fuelBoni);
        }


        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
            stats.getFuelMod().unmodify(id);
        }


        public String getEffectDescription(float level) {
            return "+10% fuel capacity";
        }


        public String getEffectPerLevelDescription() {
            return null;
        }


        public ScopeDescription getScopeDescription() {
            return ScopeDescription.PILOTED_SHIP;
        }
    }
}
