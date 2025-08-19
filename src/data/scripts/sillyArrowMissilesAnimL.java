package data.scripts;

import com.fs.starfarer.api.combat.*;

public class sillyArrowMissilesAnimL implements EveryFrameWeaponEffectPlugin {

    public float offset(){
        return 10f;
    }
    float ox1;
    float oy1;
    boolean runOnce = false;
    @Override
    public void advance(float amount, CombatEngineAPI engine, WeaponAPI weapon) {
        ShipAPI ship = weapon.getShip();
        if(engine.isPaused())return;
        if(!ship.isAlive())return;
        if(!runOnce){
            ox1 = weapon.getSprite().getCenterX();
            oy1 = weapon.getSprite().getCenterY();
            runOnce=true;
        }
        ShipSystemAPI sys = ship.getSystem();
        float dir = Math.signum(weapon.getSlot().getLocation().getX());
        float l1 = sys.getEffectLevel();
        float l2 = l1*l1;
        weapon.getSprite().setCenterX(ox1+l2*offset());
        weapon.getSprite().setCenterY(oy1-l2*offset());
    }
}