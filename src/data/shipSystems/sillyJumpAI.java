package data.shipSystems;

import com.fs.starfarer.api.combat.*;
import org.lwjgl.util.vector.Vector2f;

public class sillyJumpAI implements ShipSystemAIScript {
    protected ShipAPI ship;
    protected CombatEngineAPI engine;
    protected ShipwideAIFlags flags;
    protected ShipSystemAPI system;
    @Override
    public void init(ShipAPI ship, ShipSystemAPI system, ShipwideAIFlags flags, CombatEngineAPI engine) {
        this.ship = ship;
        this.flags = flags;
        this.engine = engine;
        this.system = system;
    }

    @Override
    public void advance(float amount, Vector2f missileDangerDir, Vector2f collisionDangerDir, ShipAPI target) {
        if(ship.getSystem().canBeActivated()){
            ship.giveCommand(ShipCommand.USE_SYSTEM,null, 0);
        }
    }
}
