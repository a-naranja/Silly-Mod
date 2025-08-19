package data.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import org.magiclib.util.MagicAnim;

public class sillyArrowMissilesAnimR implements EveryFrameWeaponEffectPlugin {
    private float defaultX;
    private float defaultY;

    public void getPos(WeaponAPI weapon){
        defaultX = weapon.getSprite().getCenterX();
        defaultY = weapon.getSprite().getCenterY();
    }
    public void advance(float amount, CombatEngineAPI engine, WeaponAPI weapon){
        ShipAPI ship = weapon.getShip();
        ShipSystemAPI system = ship.getSystem();
        //float moveX = MagicAnim.smoothNormalizeRange(system.getEffectLevel(), 0f,0.2f)*2;
        //float in = MagicAnim.cycle(system.getEffectLevel(),0.8f,1f);
        float open = defaultX+2;
        if(!Global.getCombatEngine().isPaused()){
            if(system.getState().equals(ShipSystemAPI.SystemState.IDLE)){
                weapon.getSprite().setCenter(defaultX,defaultY);
            } else if (system.getState().equals(ShipSystemAPI.SystemState.ACTIVE)) {
                weapon.getSprite().setCenterX(open);
            }
        }
    }
}