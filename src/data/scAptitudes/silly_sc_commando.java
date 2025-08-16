package data.scAptitudes;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import second_in_command.SCData;
import second_in_command.misc.SCExtensionsKt;
import second_in_command.specs.SCAptitudeSection;
import second_in_command.specs.SCBaseAptitudePlugin;

import java.util.Objects;

public class silly_sc_commando extends SCBaseAptitudePlugin {
    @Override
    public void addCodexDescription(TooltipMakerAPI tt){
        tt.addPara("This aptitude brings back the central focus on Cruisers, improving upon their strenghts alone or with backup",0f, Misc.getTextColor(),Misc.getHighlightColor());
        tt.addSpacer(10f);
        SCExtensionsKt.addPara(tt, "Any tech tier can be used with the Commando aptitude and many other aptitudes complement it well");
    }
    @Override
    public String getOriginSkillId() {
        return "silly_skill1";
    }
    @Override
    public void createSections() {
        SCAptitudeSection section1 = new SCAptitudeSection(true, 0, "leadership1");
        section1.addSkill("silly_skill2");
        section1.addSkill("silly_skill3");
        section1.addSkill("silly_skill4");
        section1.addSkill("silly_skill5");
        section1.addSkill("silly_skill6");
        addSection(section1); // Finalize the section by adding it.

        SCAptitudeSection section2 = new SCAptitudeSection(false, 3, "leadership2");
        section2.addSkill("silly_skill7");
        section2.addSkill("silly_skill8");
        addSection(section2);

        SCAptitudeSection section3 = new SCAptitudeSection(false, 4, "leadership3");
        section3.addSkill("silly_skill9");
        section3.addSkill("silly_skill10");
        addSection(section3);
    }
    //Should be around 1 on average.
    @Override
    public Float getNPCFleetSpawnWeight(SCData data, CampaignFleetAPI fleet) {
        if (fleet.getNumCruisers()>=5 && fleet.getNumCapitals()<=2) {
            return 1f;
        } else return 0.1f;
    }
    @Override
    public Float getMarketSpawnweight(MarketAPI market){
        float weight = this.spec.getSpawnWeight();
        if(Objects.equals(market.getFaction().getId(), "DoohickeyCorp")){
            weight *= 4f;
        }return weight;
    }
}