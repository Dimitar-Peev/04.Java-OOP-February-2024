package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission {

    public MissionImpl() {
    }

    @Override
    public void explore(State state, Collection<Explorer> explorers) {

        Collection<String> stateExhibits = state.getExhibits();

        for (Explorer explorer : explorers) {
            while (explorer.canSearch() && stateExhibits.iterator().hasNext()) {
                explorer.search();
                String currentExhibit = stateExhibits.iterator().next();
                explorer.getSuitcase().getExhibits().add(currentExhibit);
                stateExhibits.remove(currentExhibit);
            }
        }


    }
}
