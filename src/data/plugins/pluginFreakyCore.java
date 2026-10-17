package data.plugins;

import com.fs.starfarer.api.PluginPick;
import com.fs.starfarer.api.campaign.AICoreAdminPlugin;
import com.fs.starfarer.api.campaign.AICoreOfficerPlugin;
import com.fs.starfarer.api.campaign.BaseCampaignPlugin;
import data.ids.sillyCommodities;

public class pluginFreakyCore extends BaseCampaignPlugin {
    @Override
    public String getId() {
        return "freakyCorePluginID";
    }

    @Override
    public PluginPick<AICoreOfficerPlugin> pickAICoreOfficerPlugin(String commodityId) {
        /*
        This runs when we open the ai core assignment menu
        It attempts to look through commodities in our cargo and create ai core officers out of them
        If the commodity id of the item is our ai core ID, return our person
         */
        if (commodityId.equals(sillyCommodities.FREAKY_CORE)){
            return new PluginPick<>(new pluginFreakyCoreOfficer(), PickPriority.MOD_SPECIFIC);

        }
        return null; // returning null means we don't want to handle this
    }

    @Override
    public PluginPick<AICoreAdminPlugin> pickAICoreAdminPlugin(String commodityId) {
        if(commodityId.equals(sillyCommodities.FREAKY_CORE)){
            return new PluginPick<AICoreAdminPlugin>(new pluginFreakyCoreAdmin(), PickPriority.MOD_SPECIFIC);
        }
        return null;
    }
}
