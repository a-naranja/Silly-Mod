package data.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.CustomUIPanelPlugin;
import com.fs.starfarer.api.campaign.listeners.CommodityTooltipModifier;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import data.scripts.customUmaUI;

import java.awt.*;

public class sillyDescriptions implements CommodityTooltipModifier {
    @Override
    public void addSectionAfterPrice(TooltipMakerAPI info, float width, boolean expanded, CargoStackAPI stack){
        String[] umas = {"uma", "uma1", "uma2"};
        int i = 0;
        if(stack.getCommodityId().equals("food")){
            Color mark = Misc.getHighlightColor();
            info.addPara("Gee, I sure hope no one is %s around with the food...", 5, mark, "horsing");
            if(expanded){
                info.addImage(Global.getSettings().getSpriteName("icons", umas[i]), 5);
                //info.addCustom(Global.getSettings().createCustom(50f,50f,customUmaUI),5f);
            }
        }
    }
}