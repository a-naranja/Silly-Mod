package data.scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;

//yeah we dumpin everything for game loads here yeaa give em those loadsss
//fml
public class sillyStartupPlugin extends BaseModPlugin {
    @Override
    public void onGameLoad(boolean newGame){
        super.onGameLoad(newGame);
        Global.getSector().getEconomy().addUpdateListener(new sillyParticlesListener());
    }
}
