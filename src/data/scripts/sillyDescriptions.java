package data.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.listeners.CommodityTooltipModifier;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import java.awt.*;

public class sillyDescriptions implements CommodityTooltipModifier {
    @Override
    public void addSectionAfterPrice(TooltipMakerAPI info, float width, boolean expanded, CargoStackAPI stack){
        if(stack.getCommodityId().equals("food")){
            Color mark = Misc.getHighlightColor();
            info.addPara("Gee, I sure hope no one is %s around with the food...", 5, mark, "horsing");
            if(expanded){
                info.addImage(Global.getSettings().getSpriteName("icons", "uma"), 5);
            }
        }
    }
}