package data.hullmods;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.campaign.ids.Stats;

public class sillyGremSpawner extends BaseHullMod {

    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getDynamic().getStat(Stats.EXPLOSION_DAMAGE_MULT).modifyMult(id, 0f);
        stats.getDynamic().getStat(Stats.EXPLOSION_RADIUS_MULT).modifyMult(id, 0f);
    }

    @Override
    public void advanceInCombat(ShipAPI ship, float amount) {
        super.advanceInCombat(ship, amount);

        if(ship.isHulk()){
            CombatEngineAPI engine = Global.getCombatEngine();
            engine.getFleetManager(ship.getOriginalOwner()).spawnShipOrWing("silly_burden_Custom", ship.getLocation(), ship.getFacing());
            engine.removeEntity(ship);
        }
    }
}