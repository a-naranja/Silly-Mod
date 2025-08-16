package data.shipSystems;

import com.fs.starfarer.api.impl.combat.MoteControlScript;

import java.awt.*;

public class protoMoteScript extends MoteControlScript {

    static {
        MoteData normal = new MoteData();
        normal.jitterColor = new Color(255, 125, 75, 255);
        normal.empColor = new Color(255, 125, 75, 255);
        normal.maxMotes = MAX_MOTES;
        normal.antiFighterDamage = ANTI_FIGHTER_DAMAGE;
        normal.impactSound = "mote_attractor_impact_normal";
        normal.loopSound = "mote_attractor_loop";
        MOTE_DATA.put(MOTELAUNCHER, normal);
        MoteData hf = new MoteData();
        hf.jitterColor = new Color(255, 125, 75, 255);
        hf.empColor = new Color(255, 125, 75, 255);
        hf.maxMotes = MAX_MOTES_HF;
        hf.antiFighterDamage = ANTI_FIGHTER_DAMAGE_HF;
        hf.impactSound = "mote_attractor_impact_damage";
        hf.loopSound = "mote_attractor_loop_dark";
        MOTE_DATA.put(MOTELAUNCHER_HF, hf);
    }
}