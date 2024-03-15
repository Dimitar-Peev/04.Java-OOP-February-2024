package bakery.repositories;

import bakery.repositories.interfaces.FoodRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FoodRepositoryImpl<T> implements FoodRepository<T> {
    private Collection<T> models;

    public FoodRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public T getByName(String name) {
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(T model) {
        this.models.add(model);
    }
}
