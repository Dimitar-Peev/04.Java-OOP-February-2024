package handball.core;

import handball.entities.equipment.*;
import handball.entities.gameplay.*;
import handball.entities.team.*;
import handball.repositories.*;

import java.util.ArrayList;
import java.util.Collection;

import static handball.common.ConstantMessages.*;
import static handball.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private final Repository equipment;
    private final Collection<Gameplay> gameplays;

    public ControllerImpl() {
        this.equipment = new EquipmentRepository();
        this.gameplays = new ArrayList<>();
    }

    @Override
    public String addGameplay(String gameplayType, String gameplayName) {
        Gameplay gameplay = null;

        switch (gameplayType) {
            case "Indoor":
                gameplay = new Indoor(gameplayName);
                break;
            case "Outdoor":
                gameplay = new Outdoor(gameplayName);
                break;
            default:
                throw new NullPointerException(INVALID_GAMEPLAY_TYPE);
        }

        gameplays.add(gameplay);
        return String.format(SUCCESSFULLY_ADDED_GAMEPLAY_TYPE, gameplayType);
    }

    @Override
    public String addEquipment(String equipmentType) {

        Equipment equipment = null;

        switch (equipmentType) {
            case "Kneepad":
                equipment = new Kneepad();
                break;
            case "ElbowPad":
                equipment = new ElbowPad();
                break;
            default:
                throw new IllegalArgumentException(INVALID_EQUIPMENT_TYPE);
        }

        this.equipment.add(equipment);
        return String.format(SUCCESSFULLY_ADDED_EQUIPMENT_TYPE, equipmentType);
    }

    @Override
    public String equipmentRequirement(String gameplayName, String equipmentType) {

        Equipment equipment = this.equipment.findByType(equipmentType);
        if (equipment == null) {
            throw new IllegalArgumentException(String.format(NO_EQUIPMENT_FOUND, equipmentType));
        }

        Gameplay gameplay = getGameplayByName(gameplayName);
        gameplay.addEquipment(equipment);
        this.equipment.remove(equipment);
        return String.format(SUCCESSFULLY_ADDED_EQUIPMENT_IN_GAMEPLAY, equipmentType, gameplay.getName());
    }

    private Gameplay getGameplayByName(String gameplayName) {
        return this.gameplays.stream().filter(g -> g.getName().equals(gameplayName)).findFirst().orElse(null);
    }

    @Override
    public String addTeam(String gameplayName, String teamType, String teamName, String country, int advantage) {
        Team team = null;

        switch (teamType) {
            case "Bulgaria":
                team = new Bulgaria(teamName, country, advantage);
                break;
            case "Germany":
                team = new Germany(teamName, country, advantage);
                break;
            default:
                throw new IllegalArgumentException(INVALID_TEAM_TYPE);
        }

        Gameplay gameplay = getGameplayByName(gameplayName);

        String output = GAMEPLAY_NOT_SUITABLE;

        if (isCompatible(teamType, gameplay)) {
            output = String.format(SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY, teamType, gameplayName);
            gameplay.addTeam(team);
        }

        return output;

    }
	
    private boolean isCompatible(String teamType, Gameplay gameplay) {
        String gameplayType = gameplay.getClass().getSimpleName();
        return (teamType.equals("Germany") && gameplayType.equals("Indoor"))
                || (teamType.equals("Bulgaria") && gameplayType.equals("Outdoor"));
    }

    @Override
    public String playInGameplay(String gameplayName) {
        Gameplay gameplay = getGameplayByName(gameplayName);
        Collection<Team> teamsList = gameplay.getTeam();
        int sum = 0;
        for (Team team : teamsList) {
            team.play();
            sum++;
        }
        return String.format(TEAMS_PLAYED, sum);
    }

    @Override
    public String percentAdvantage(String gameplayName) {
        Gameplay gameplay = getGameplayByName(gameplayName);
        Collection<Team> teamsList = gameplay.getTeam();
        int totalSum = teamsList.stream().mapToInt(Team::getAdvantage).sum();
        return String.format(ADVANTAGE_GAMEPLAY, gameplayName, totalSum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        this.gameplays.forEach(e -> sb.append(e.toString()).append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
