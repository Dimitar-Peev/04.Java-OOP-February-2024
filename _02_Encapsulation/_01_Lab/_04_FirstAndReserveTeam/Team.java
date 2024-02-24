package _04_FirstAndReserveTeam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {

    private String name;
    private List<Person> firstTeam;
    private List<Person> reserveTeam;

    public Team(String name) {
        this(name, new ArrayList<>(), new ArrayList<>());
    }

    private Team(String name, List<Person> firstTeam, List<Person> reserveTeam) {
        this.setName(name);
        this.firstTeam = firstTeam;
        this.reserveTeam = reserveTeam;
    }

    public void addPlayer(Person person) {
        if (person.getAge() < 40) {
            this.firstTeam.add(person);
        } else {
            this.reserveTeam.add(person);
        }

    }

    public String getName() {
        return name;
    }

    public List<Person> getFirstTeam() {
        return Collections.unmodifiableList(this.firstTeam);
    }

    public List<Person> getReserveTeam() {
        return Collections.unmodifiableList(this.reserveTeam);
    }

    public void setName(String name) {
        this.name = name;
    }
}
