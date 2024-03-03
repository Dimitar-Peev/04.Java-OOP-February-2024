package _02_Exercise._05_FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.setName(name);
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String name) {
        if (this.players.stream().noneMatch(e -> e.getName().equals(name))) {
            System.out.println(String.format("Player %s is not in %s team.", name, this.name));
        }

  //      this.players.remove(this.players.stream().filter(e -> e.getName().equals(name)).findFirst().orElseThrow());
        this.players.removeIf(player -> name.equals(player.getName()));
    }

    public double getRating() {
        return this.players.stream().mapToDouble(Player::overallSkillLevel).average().orElse(0);
    }
}
