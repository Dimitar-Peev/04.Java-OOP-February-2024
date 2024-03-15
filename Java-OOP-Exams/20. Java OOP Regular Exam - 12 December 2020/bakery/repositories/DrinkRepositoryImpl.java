package bakery.repositories;

import bakery.repositories.interfaces.DrinkRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DrinkRepositoryImpl<T> implements DrinkRepository<T> {
    private Collection<T> models;

    public DrinkRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public T getByNameAndBrand(String drinkName, String drinkBrand) {
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
