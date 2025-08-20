package data.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.BaseCampaignEventListener;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import data.ids.sillyFactions;


public class linkRelations extends BaseCampaignEventListener {
    public linkRelations(boolean permaRegister) {
        super(permaRegister);
    }
    @Override
    public void reportPlayerReputationChange(String factionId, float change) {
        if (factionId.equals(sillyFactions.DOOHICKEYCORP)) {
            Global.getSector().getFaction(sillyFactions.DOOHICKEYELITE).adjustRelationship(Factions.PLAYER, change);
        }
        if (factionId.equals(sillyFactions.DOOHICKEYELITE)) {
            Global.getSector().getFaction(sillyFactions.DOOHICKEYCORP).adjustRelationship(Factions.PLAYER, change);
        }
    }
    //add corp rels to elite just in case elite spawns so they have the same enemies
    @Override
    public void reportEconomyTick (int iterIndex) {
        if (Global.getSector().getFaction(sillyFactions.DOOHICKEYCORP) != null && Global.getSector().getFaction(sillyFactions.DOOHICKEYELITE) != null) {
            FactionAPI corp = Global.getSector().getFaction(sillyFactions.DOOHICKEYCORP);
            FactionAPI elite = Global.getSector().getFaction(sillyFactions.DOOHICKEYELITE);
            for(FactionAPI faction:Global.getSector().getAllFactions()) {
                elite.setRelationship(faction.getId(), corp.getRelationship(faction.getId()));
            }
        }
    }
}