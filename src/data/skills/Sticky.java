package data.skills;

import com.fs.starfarer.api.characters.AfterShipCreationSkillEffect;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.listeners.AdvanceableListener;
import org.lazywizard.lazylib.combat.AIUtils;

import java.awt.*;
import java.util.List;

public class Sticky {
    //this is the listener and calcs for later
    public static class stickyCalcs implements AdvanceableListener{
        protected ShipAPI ship;
        protected String id;

        public stickyCalcs(ShipAPI ship, String id) {
            this.ship = ship;
            this.id = id;
        }

        @Override
        public void advance(float amount) {
            MutableShipStatsAPI stats = ship.getMutableStats();
            List<ShipAPI> enemies = AIUtils.getNearbyEnemies(ship,350f);

            if(ship.isAlive()&&!enemies.isEmpty()) {
                stats.getAcceleration().modifyMult(id,2f);
                stats.getDeceleration().modifyMult(id,2f);
                stats.getTurnAcceleration().modifyMult(id,2f);
                stats.getMaxTurnRate().modifyMult(id,2f);
                stats.getMaxSpeed().modifyMult(id,1.2f);
                ship.setJitterUnder(this,Color.PINK,1f,7,7f);

            } else {
                stats.getAcceleration().unmodifyMult(id);
                stats.getDeceleration().unmodifyMult(id);
                stats.getTurnAcceleration().unmodifyMult(id);
                stats.getMaxTurnRate().unmodifyMult(id);
                stats.getMaxSpeed().unmodifyMult(id);
            }
        }
    }
    //this is uhh description
    public static class Level1 implements ShipSkillEffect{


        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {
        }


        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {
        }


        public String getEffectDescription(float level) {
            return "When enemies are at 350 units or closer:";
        }


        public String getEffectPerLevelDescription() {
            return null;
        }


        public ScopeDescription getScopeDescription() {
            return ScopeDescription.PILOTED_SHIP;
        }
    }

    //this applies the listener so we actually get a buff
    public static class Level2 implements AfterShipCreationSkillEffect {
        public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.addListener(new stickyCalcs(ship, id));
        }
        public void unapplyEffectsAfterShipCreation(ShipAPI ship, String id) {
            ship.removeListenerOfClass(stickyCalcs.class);
        }

        public void apply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id, float level) {}
        public void unapply(MutableShipStatsAPI stats, ShipAPI.HullSize hullSize, String id) {}

        public String getEffectDescription(float level) {
            return "Acceleration doubled, Speed +20%";
        }
        public String getEffectPerLevelDescription() {
            return null;
        }
        public ScopeDescription getScopeDescription() {
            return ScopeDescription.PILOTED_SHIP;
        }
    }

}
