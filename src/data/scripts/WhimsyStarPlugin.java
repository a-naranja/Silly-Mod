//package tells where it goes, dots (.) indicate folders like slash (/)
package data.scripts;

//import starsector api
import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.rules.MemKeys;
import com.fs.starfarer.api.impl.campaign.ids.*;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import data.ids.sillyFactions;
import data.ids.sillyIndustries;

//em fokin colours
import java.awt.*;


public class WhimsyStarPlugin extends BaseModPlugin {
    @Override
    public void onNewGame() {
		//call creation
		SectorAPI sector = Global.getSector();
		//give name to system
		StarSystemAPI system = sector.createStarSystem("Whimsy");
		system.setBackgroundTextureFilename("graphics/backgrounds/sillyBG2.jpg");

        //make sun
		PlanetAPI star = system.initStar(
			"whimsy",
			"star_yellow", 
			700, 
			36000,
			33000,
			500);
		//asteroid attempt, again
		system.addAsteroidBelt(star, 100, 4000, 200, 120, 210, Terrain.ASTEROID_BELT, "Silly Belt");
		system.addRingBand(star, "misc", "rings_asteroids0", 256f, 0, Color.WHITE, 256f, 4000, 150f, null, null);
		//make funni orbiting things, comments disable fancy tags :thinking:
		SectorEntityToken relay = system.addCustomEntity(
				 "whimsy_relay", // id
				 "Whimsy Relay", // fancy name, blank defaults to generic
				 "comm_relay_makeshift", // type of object, check data/config/custom_entities
				 "doohickeyCorp"); // faction id
		relay.setCircularOrbitPointingDown(star, 169, 2500, 160);

		SectorEntityToken stable_location = system.addCustomEntity(
				 "whimsy_stable",
				 "Stable Location",
				 "stable_location",
				 "neutral");
		stable_location.setCircularOrbit(star, 222, 6000, 500);

		SectorEntityToken whimsy_gate = system.addCustomEntity(
				"whimsy_gate",
				 "Whimsy Gate",
				 "inactive_gate",
				 null);
		whimsy_gate.setCircularOrbit( star, 120, 4500, 200);

		//start creating planets. First planet...
		PlanetAPI lemat = system.addPlanet("lemat", star, "Lemat", "jungle", 33, 200, 3000, 350);
		//give it to a faction
		lemat.setFaction(sillyFactions.DOOHICKEYCORP);
		//now market...
		MarketAPI lemat_market = Global.getFactory().createMarket("lemat_market", "Lemat", 4);
		lemat_market.setFactionId(sillyFactions.DOOHICKEYCORP);
		//make it populated
		lemat_market.setPlanetConditionMarketOnly(false);
		//add conditions
		lemat_market.addCondition(Conditions.HOT);
		lemat_market.addCondition(Conditions.HABITABLE);
		lemat_market.addCondition(Conditions.FARMLAND_POOR);
		lemat_market.addCondition("silly_particles_trace");
		lemat_market.addCondition(Conditions.INIMICAL_BIOSPHERE);
		lemat_market.addCondition(Conditions.POPULATION_3);
		lemat_market.addCondition(Conditions.FREE_PORT);
		//add industry if needed, no need if it's not populated
		lemat_market.addIndustry(Industries.POPULATION);
		lemat_market.addIndustry(Industries.SPACEPORT);
		lemat_market.addIndustry(Industries.FARMING);
		lemat_market.addIndustry(Industries.PATROLHQ);
		lemat_market.addIndustry(Industries.HEAVYINDUSTRY);
		lemat_market.addIndustry(sillyIndustries.SILLY_STATION);
		lemat_market.addIndustry(sillyIndustries.SILLY_RESEARCH_PARK);
		//add submarkets for open market, black market and storage; maybe add custom doohickey or silly later?
		lemat_market.addSubmarket(Submarkets.SUBMARKET_OPEN);
		lemat_market.addSubmarket(Submarkets.SUBMARKET_BLACK);
		lemat_market.addSubmarket(Submarkets.GENERIC_MILITARY);
		lemat_market.addSubmarket(Submarkets.SUBMARKET_STORAGE);
		//set market to the planet so it knows it's not a station or something
		lemat_market.setPrimaryEntity(lemat);
		//yea... tell the planet it is a market too
		lemat.setMarket(lemat_market);
		//make it already surveyed on game creation, or not. It's a known planet so put FULL
		lemat_market.setSurveyLevel(MarketAPI.SurveyLevel.FULL);
		//call eco api, make the planet into economy. Failing to do so will make commodities price weird or crash the game (bad)
		EconomyAPI globalEconomy = Global.getSector().getEconomy();
		globalEconomy.addMarket(lemat_market, true);

		PlanetAPI skeleton = system.addPlanet("skeleton", star, "Skeleton", "barren", 150, 100, 2200, 126);
		MarketAPI skeleton_market = Global.getFactory().createMarket("skeleton_market", skeleton.getName(), 0);
		//make it NOT populated
		skeleton_market.setPlanetConditionMarketOnly(true);
		//add conditions
		skeleton_market.addCondition(Conditions.NO_ATMOSPHERE);
		skeleton_market.addCondition(Conditions.LOW_GRAVITY);
		skeleton_market.addCondition(Conditions.HOT);
		skeleton_market.addCondition(Conditions.ORE_RICH);
		skeleton_market.addCondition(Conditions.RARE_ORE_SPARSE);
		skeleton_market.setPrimaryEntity(skeleton);
		skeleton.setMarket(skeleton_market);
		skeleton_market.setSurveyLevel(MarketAPI.SurveyLevel.FULL);

		PlanetAPI traitor = system.addPlanet("traitor", star, "Traitor", "frozen", 300, 300, 5000, 1100);
		MarketAPI traitor_market = Global.getFactory().createMarket("traitor_market", traitor.getName(), 0);
		traitor_market.setPlanetConditionMarketOnly(true);
		traitor_market.addCondition(Conditions.VERY_COLD);
		traitor_market.addCondition(Conditions.THIN_ATMOSPHERE);
		traitor_market.addCondition("silly_particles_abundant");
		traitor_market.addCondition(Conditions.ORE_MODERATE);
		traitor_market.addCondition(Conditions.RARE_ORE_MODERATE);
		traitor_market.addCondition(Conditions.RUINS_SCATTERED);
		traitor_market.setPrimaryEntity(traitor);
		traitor.setMarket(traitor_market);
		traitor_market.setSurveyLevel(MarketAPI.SurveyLevel.FULL);

		system.autogenerateHyperspaceJumpPoints(true, true);

		//remember to add descriptions in a data/strings/descriptions.csv
		//column 1(id) for id, column 2(type) MUST be CUSTOM, column 3(text 1) for description shown to the player
		//column 4(text 2) for title when mouse is over it, column 5(text 3) shows when approaching planet so uhh no idea
		//planet_id.setCustomDescriptionId("column_1_id");
		lemat.setCustomDescriptionId("lemat_description");

		//add relations with other factions
		Global.getSector().getFaction(Factions.INDEPENDENT).setRelationship(sillyFactions.DOOHICKEYCORP, 0.25f);
		Global.getSector().getFaction(Factions.HEGEMONY).setRelationship(sillyFactions.DOOHICKEYCORP, 0.10f);
		Global.getSector().getFaction(sillyFactions.DOOHICKEYCORP).setRelationship(Factions.INDEPENDENT, 0.25f);
		Global.getSector().getFaction(sillyFactions.DOOHICKEYCORP).setRelationship(Factions.HEGEMONY, 0.10f);
		Global.getSector().getFaction(sillyFactions.DOOHICKEYCORP).setRelationship(Factions.PIRATES, RepLevel.HOSTILE);
		Global.getSector().getFaction(sillyFactions.DOOHICKEYCORP).setRelationship(Factions.TRITACHYON, RepLevel.SUSPICIOUS);
		Global.getSector().getFaction(sillyFactions.DOOHICKEYCORP).setRelationship(Factions.PERSEAN, RepLevel.SUSPICIOUS);

    }
	@Override
	public void onGameLoad(boolean newGame){
		super.onGameLoad(newGame);
		Global.getSector().getListenerManager().addListener(new sillyDescriptions(), true);
		Global.getSector().getListenerManager().addListener(new linkRelations(true), true);
	}
}
