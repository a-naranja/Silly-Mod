package com.fs.starfarer.api.impl.campaign.ids;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.ImportantPeopleAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.FullName.Gender;

public class silly_People extends People{
    //name people
    public static String MRSILLY = "mr_silly";

    //very important people
    public static PersonAPI getPerson(String id) {
        return Global.getSector().getImportantPeople().getPerson(id);
    }

    //make em
    public static void create() {
        createFactionLeaders();
        createMiscCharacters();
    }

    public static void createFactionLeaders(){
        ImportantPeopleAPI ip = Global.getSector().getImportantPeople();
        MarketAPI market = null;
        market = Global.getSector().getEconomy().getMarket("lemat_market");
        if (market != null) {
            PersonAPI person = Global.getFactory().createPerson();
            person.setId(MRSILLY);
            person.setFaction("doohickeyCorp");
            person.setGender(Gender.MALE);
            person.setRankId(Ranks.FACTION_LEADER);
            person.setPostId(Ranks.POST_FACTION_LEADER);
            person.setImportance(PersonImportance.VERY_HIGH);
            person.getName().setFirst("Mr.");
            person.getName().setLast("Silly");
            person.setPortraitSprite("graphics/characters/mrsilly.png");
            person.getStats().setSkillLevel("bulk_transport", 1.0F);
            person.getStats().setSkillLevel("industrial_planning", 1.0F);
            market.setAdmin(person);
            market.getCommDirectory().addPerson(person, 0);
            market.addPerson(person);
            ip.addPerson(person);
        }
    }
    public static void assignPost(MarketAPI market, String postId, PersonAPI person) {
        for(PersonAPI curr : market.getPeopleCopy()) {
            if (postId.equals(curr.getPostId())) {
                market.removePerson(curr);
                market.getCommDirectory().removePerson(curr);
            }
        }

        person.setPostId(postId);
        market.addPerson(person);
        market.getCommDirectory().addPerson(person);
    }
}
