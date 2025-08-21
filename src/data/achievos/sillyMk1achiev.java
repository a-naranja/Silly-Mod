package data.achievos;

import com.fs.starfarer.api.Global;
import org.magiclib.achievements.MagicAchievement;

public class sillyMk1achiev extends MagicAchievement {

    public void onSaveGameLoaded(boolean isComplete) {
        super.onSaveGameLoaded(isComplete);
        if (!isComplete) {
            Global.getSector().getListenerManager().addListener(this, true);
        }
    }

    public void advanceAfterInterval(float amount) {
        if (Global.getSector().getMemoryWithoutUpdate().getBoolean("$sillyZiggBounty_succeeded")){
            this.completeAchievement();
        }
    }

    public void onDestroyed() {
        super.onDestroyed();
        Global.getSector().getListenerManager().removeListener(this);
    }
}
