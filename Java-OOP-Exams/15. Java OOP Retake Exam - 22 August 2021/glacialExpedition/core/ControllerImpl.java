package glacialExpedition.core;

import glacialExpedition.models.explorers.*;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private ExplorerRepository explorers;
    private StateRepository states;
    private int exploredStatesCount;

    public ControllerImpl() {
        this.explorers = new ExplorerRepository();
        this.states = new StateRepository();
        this.exploredStatesCount = 0;
    }

    @Override
    public String addExplorer(String type, String explorerName) {
		
         Explorer explorer = ExplorerFactory.createExplorer(type, explorerName);

        explorers.add(explorer);

        return String.format(EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {

        State state = new StateImpl(stateName);

        Collections.addAll(state.getExhibits(), exhibits);

        this.states.add(state);

        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {

        Explorer explorerToRemove = explorers.byName(explorerName);

        if (explorerToRemove == null) {
            throw new IllegalArgumentException(String.format(EXPLORER_DOES_NOT_EXIST, explorerName));
        } else {
            this.explorers.remove(explorerToRemove);
        }

        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {

        State state = this.states.byName(stateName);

        // You call each of the explorers and pick only the ones that have energy above 50 units.
        List<Explorer> suitableExplorers = this.explorers.getCollection()
                .stream().filter(e -> e.getEnergy() > 50).collect(Collectors.toList());

        if (suitableExplorers.isEmpty()) {
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        } else {
            Mission mission = new MissionImpl();
            mission.explore(state, suitableExplorers);
        }

        long retiredExplorers = suitableExplorers.stream().filter(e -> e.getEnergy() == 0).count();

        this.exploredStatesCount++;

        states.remove(state);

        return String.format(STATE_EXPLORER, stateName, retiredExplorers);
    }

    @Override
    public String finalResult() {
        StringBuilder text = new StringBuilder();
        text.append(String.format(FINAL_STATE_EXPLORED, exploredStatesCount)).append(System.lineSeparator());
        text.append(FINAL_EXPLORER_INFO).append(System.lineSeparator());

        text.append(explorers.toString());
        return text.toString().trim();
    }
}
