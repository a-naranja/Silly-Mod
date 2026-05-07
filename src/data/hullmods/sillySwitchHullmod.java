package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;

public class sillySwitchHullmod extends BaseHullMod {
    public String sillySwitchHullmod = "sillySwtichHullmod";

    @Override
    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        super.applyEffectsAfterShipCreation(ship, id);
        //declaring weapons
        WeaponAPI tpc1 = null;
        WeaponAPI tpc2 = null;
        WeaponAPI flapL = null;
        WeaponAPI flapR = null;
        //getting weapons
        for(WeaponAPI w: ship.getAllWeapons()){
            if(w.getSlot().getId().contains("tpc1")){
                tpc1=w;
                break;
            }
        }
        if(tpc1==null)return;

        for(WeaponAPI w: ship.getAllWeapons()){
            if(w.getSlot().getId().contains("tpc2")){
                tpc2=w;
                break;
            }
        }
        if(tpc2==null)return;

        for(WeaponAPI w2: ship.getAllWeapons()){
            if(w2.getSlot().getId().contains("flapL")){
                flapL=w2;
                break;
            }
        }
        if(flapL==null)return;

        for(WeaponAPI w2: ship.getAllWeapons()){
            if(w2.getSlot().getId().contains("flapR")){
                flapR=w2;
                break;
            }
        }
        if(flapR==null)return;

        if (ship.getSystem().isOn()){ //VROOM VROOM!
            ship.getEngineController().setFlameLevel(ship.getEngineController().getShipEngines().get(12).getEngineSlot(),1);
            ship.getEngineController().setFlameLevel(ship.getEngineController().getShipEngines().get(13).getEngineSlot(),1);
            tpc1.setForceDisabled(true);
            tpc2.setForceDisabled(true);
            flapL.setCurrAngle(+45);
            flapR.setCurrAngle(-45);
        } else if (!ship.getSystem().isOn()) { //FIGHT
            ship.getEngineController().setFlameLevel(ship.getEngineController().getShipEngines().get(12).getEngineSlot(),0);
            ship.getEngineController().setFlameLevel(ship.getEngineController().getShipEngines().get(13).getEngineSlot(),0);
            tpc1.setForceDisabled(false);
            tpc2.setForceDisabled(true);
            flapL.setCurrAngle(0);
            flapR.setCurrAngle(0);

        }
    }
}
