package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceRepository<T extends Race> implements Repository<T> {
    private Collection<T> data;

    public RaceRepository() {
        this.data = new ArrayList<>();
    }

    @Override
    public T getByName(String name) {
        return this.data.stream().filter(race -> race.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(this.data);
    }

    @Override
    public void add(T model) {
        this.data.add(model);
    }

    @Override
    public boolean remove(T model) {
        return this.data.removeIf(race -> race.getName().equals(model.getName()));
    }
}
