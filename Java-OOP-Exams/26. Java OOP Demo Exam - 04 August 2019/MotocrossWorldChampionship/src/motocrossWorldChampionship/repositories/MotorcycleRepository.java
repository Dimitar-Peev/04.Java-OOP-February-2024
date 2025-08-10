package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MotorcycleRepository<T extends Motorcycle> implements Repository<T> {
    private Collection<T> data;

    public MotorcycleRepository() {
        this.data = new ArrayList<>();
    }

    @Override
    public T getByName(String name) {
        return this.data.stream().filter(motorcycle -> motorcycle.getModel().equals(name)).findFirst().orElse(null);
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
        return this.data.removeIf(motorcycle -> motorcycle.getModel().equals(model.getModel()));
    }
}
