package data.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import org.magiclib.util.MagicAnim;

public class sillyArrowMissilesAnimL implements EveryFrameWeaponEffectPlugin {

    public void advance(float amount, CombatEngineAPI engine, WeaponAPI weapon){
        ShipAPI ship = weapon.getShip();
        ShipSystemAPI system = ship.getSystem();
        //float moveX = MagicAnim.smoothNormalizeRange(system.getEffectLevel(), 0f,0.2f)*2;
        //float in = MagicAnim.cycle(system.getEffectLevel(),0.8f,1f);
        float defaultX = weapon.getSprite().getCenterX();
        if(!Global.getCombatEngine().isPaused()){
            if(system.isChargeup()){
                float open = defaultX+6;
                weapon.getSprite().setCenterX(open);
            } else weapon.getSprite().setCenterX(defaultX);
        }
    }
}