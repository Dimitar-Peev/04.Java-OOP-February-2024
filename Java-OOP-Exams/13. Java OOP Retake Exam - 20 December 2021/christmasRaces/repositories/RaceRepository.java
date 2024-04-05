package christmasRaces.repositories;

import christmasRaces.entities.races.Race;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class RaceRepository implements Repository<Race> {
    private Map<String, Race> raceMap;

    public RaceRepository() {
        this.raceMap = new LinkedHashMap<>();
    }

    @Override
    public Race getByName(String name) {
        return this.raceMap.get(name);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(this.raceMap.values());
    }

    @Override
    public void add(Race model) {
        this.raceMap.putIfAbsent(model.getName(), model);
    }

    @Override
    public boolean remove(Race model) {
        return this.raceMap.remove(model.getName()) != null;
    }
}
