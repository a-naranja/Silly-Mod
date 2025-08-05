package com.fs.starfarer.api.impl.campaign.ids;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.ImportantPeopleAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class silly_People extends People{
    public void onNewGame() {
        ImportantPeopleAPI importantP = Global.getSector().getImportantPeople();
        MarketAPI market = null;

        market = Global.getSector().getEconomy().getMarket("lemat_market");
        if (market != null) {
            PersonAPI person = Global.getFactory().createPerson();
            person.setId("silly_mrsilly");
            person.setFaction("doohickeyCorp");
            person.setGender(FullName.Gender.MALE);
            person.setRankId("factionLeader");
            person.setPostId("factionLeader");
            person.setImportance(PersonImportance.VERY_HIGH);
            person.getName().setFirst("Mr.");
            person.getName().setLast("Silly");
            person.setPortraitSprite("graphics/characters/mrsilly.png");
            person.getStats().setSkillLevel("electronic_warfare", 2.0F);
            person.getStats().setSkillLevel("energy_weapon_mastery", 2.0F);
            person.getStats().setSkillLevel("gunnery_implants", 3.0F);
            person.getStats().setLevel(6);
            person.setPersonality("steady");
            market.getCommDirectory().addPerson(person, 0);
            market.addPerson(person);
            importantP.addPerson(person);
        }
    }
}
