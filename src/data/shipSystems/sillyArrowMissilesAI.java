package data.shipSystems;

import com.fs.starfarer.api.combat.*;
import org.lwjgl.util.vector.Vector2f;

public class sillyArrowMissilesAI implements ShipSystemAIScript {
    protected ShipAPI ship;
    protected CombatEngineAPI engine;
    protected ShipwideAIFlags flags;
    protected ShipSystemAPI system;
    protected float systemFluxPerSecond;

    @Override
    public void init(ShipAPI ship, ShipSystemAPI system, ShipwideAIFlags flags, CombatEngineAPI engine) {
        this.ship = ship;
        this.flags = flags;
        this.engine = engine;
        this.system = system;
        systemFluxPerSecond = system.getFluxPerSecond();
    }

    @Override
    public void advance(float amount, Vector2f missileDangerDir, Vector2f collisionDangerDir, ShipAPI target) {
        if(!ship.getFluxTracker().isOverloadedOrVenting()&& target != null && ship.areAnyEnemiesInRange()){
            ship.giveCommand(ShipCommand.USE_SYSTEM,null,0);
        }
    }
}
