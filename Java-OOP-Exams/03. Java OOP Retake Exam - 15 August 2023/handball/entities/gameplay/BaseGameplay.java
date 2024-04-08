package handball.entities.gameplay;

import handball.entities.equipment.Equipment;
import handball.entities.team.Team;

import java.util.ArrayList;
import java.util.Collection;

import static handball.common.ExceptionMessages.*;

public abstract class BaseGameplay implements Gameplay {
    private String name;
    private int capacity;
    private Collection<Equipment> equipments;
    private Collection<Team> teams;

    protected BaseGameplay(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.equipments = new ArrayList<>();
        this.teams = new ArrayList<>();
    }

    private void setName(String name) {
        if (null == name || name.isBlank() || name.isEmpty()) {
            throw new NullPointerException(GAMEPLAY_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int allProtection() {
        int sum = 0;
        for (Equipment equipment : this.equipments) {
            sum += equipment.getProtection();
        }
        return sum;
    }

    @Override
    public void addTeam(Team team) {
        this.teams.add(team);
    }

    @Override
    public void removeTeam(Team team) {
        this.teams.remove(team);
    }

    @Override
    public void addEquipment(Equipment equipment) {
        this.equipments.add(equipment);
    }

    @Override
    public void teamsInGameplay() {
//        for (Team team : teams) {
//            team.play();
//        }
        this.teams.forEach(Team::play);
    }

    @Override
    public Collection<Team> getTeam() {
        return this.teams;
    }

    @Override
    public Collection<Equipment> getEquipments() {
        return this.equipments;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s %s%n", getName(), getClass().getSimpleName()));
        stringBuilder.append("Team:");
        if (teams.isEmpty()) {
            stringBuilder.append("None");
        } else {
            teams.forEach(team -> stringBuilder.append(" ").append(team.getName()));
        }
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(String.format("Equipment: %d, Protection: %d", getEquipments().size(), allProtection()));
        return stringBuilder.toString().trim();
    }
}
