package data.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.BaseCustomUIPanelPlugin;
import com.fs.starfarer.api.graphics.SpriteAPI;


public class customUmaUI extends BaseCustomUIPanelPlugin {

    private SpriteAPI uma;
    private SpriteAPI uma1;
    private SpriteAPI uma2;
    private SpriteAPI[] umas;

    public customUmaUI(){
        uma = Global.getSettings().getSprite("graphics/uma.png");
        uma1 = Global.getSettings().getSprite("graphics/uma1.png");
        uma2 = Global.getSettings().getSprite("graphics/uma2.png");
        umas = new SpriteAPI[]{uma, uma1, uma2};

    }
    int i=0;
    public void render(float alphaMult) {
        umas[i].renderAtCenter(5,5);
        umas[i].setAlphaMult(200f);
    }
    public void advance(float amount) {
        advance(1f);
        umas[i].renderAtCenter(5,5);
        i++;
    }

}
