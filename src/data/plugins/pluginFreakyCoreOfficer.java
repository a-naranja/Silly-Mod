package data.plugins;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.AICoreOfficerPlugin;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import data.ids.sillySkillsID;

import java.awt.*;
import java.util.Random;




public class pluginFreakyCoreOfficer implements AICoreOfficerPlugin {

    @Override
    public PersonAPI createPerson(String aiCoreId, String factionId, Random random) {
        /*
        this is where the AI core is created!
        our mod only has one AI core, so it's fine for createPerson to always return the same one
        however, if your mod adds multiple, use a switch with the aiCoreId to determine which person to create
         */
        CommoditySpecAPI spec = Global.getSettings().getCommoditySpec(aiCoreId);

        PersonAPI core = Global.getFactory().createPerson();
        core.setName(new FullName(spec.getName(), "", FullName.Gender.ANY)); // sets name to be the same as the item name
        core.setPortraitSprite("graphics/portraits/sillyFCportrait.png"); // this image must be loaded already, such as in settings.json
        core.setPersonality(Personalities.RECKLESS); // doesn't actually matter for Alex code reasons, but also because of the below method
        core.setRankId(Ranks.SPACE_CAPTAIN);
        core.setPostId(null);

        MutableCharacterStatsAPI stats = core.getStats();
        stats.setSkillLevel(sillySkillsID.SKILL_STICKY, 1);
        stats.setSkillLevel(sillySkillsID.SKILL_QUARTERMASTER, 1);
        /*
        assign skills here to stats
        note that stats.level is always equal to the core's base maximum skills
         */
        stats.setLevel(4);

        core.setAICoreId(aiCoreId); // this is very important, it's what makes the person an AI core and causes the core itself to be consumed/refunded when moving the officer around
        core.getMemoryWithoutUpdate().set(AUTOMATED_POINTS_MULT, 1.5f); // point multiplier our AI core uses. note that an unset value defaults to 1f (x1.0)
        return core;
    }

    @Override
    public void createPersonalitySection(PersonAPI person, TooltipMakerAPI tooltip) {
        /*
        this section is purely cosmetic! feel free to write whatever you'd like, really.
        AI cores, by default, are reckless+. changing this requires extra coding knowledge.
        Note that this only runs on the ai cores created by our plugin
         */
        Color text = person.getFaction().getBaseUIColor();
        Color bg = person.getFaction().getDarkUIColor();
        CommoditySpecAPI spec = Global.getSettings().getCommoditySpec(person.getAICoreId());
        tooltip.addSectionHeading("Personality: Kinda Freaky", text, bg, Alignment.MID, 20f);
        tooltip.addPara(
                "In combat, the " + spec.getName() + " will enjoy the view (of unrelated footage). " + "It is, usually, not as competent as a regular AI.",
                10f
        );
    }

    /*
    ok, this is all fine and dandy, but how do we get our AI core spawning into a fleet so i can fight it?
    there are several ways, my preferred one is a FleetInflationListener
     */
}
