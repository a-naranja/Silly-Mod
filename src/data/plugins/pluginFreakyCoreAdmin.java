package data.plugins;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.AICoreAdminPlugin;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.impl.campaign.ids.Skills;
import data.ids.sillySkillsID;

public class pluginFreakyCoreAdmin implements AICoreAdminPlugin {
    public PersonAPI createPerson(String aiCoreId, String factionId, long seed) {
        PersonAPI person = Global.getFactory().createPerson();
        person.setFaction(factionId);
        person.setAICoreId(aiCoreId);
        person.setName(new FullName("Freaky Core", "", FullName.Gender.ANY));
        person.setPortraitSprite("graphics/portraits/sillyFCportrait.png");

        person.setRankId(null);
        person.setPostId(Ranks.POST_ADMINISTRATOR);

        person.getStats().setSkillLevel(Skills.INDUSTRIAL_PLANNING, 1);
        person.getStats().setSkillLevel(sillySkillsID.SKILL_RECYCLE, 1);

        return person;
    }
}
