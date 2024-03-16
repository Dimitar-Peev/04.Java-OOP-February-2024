package bakery.repositories;

import bakery.entities.drinks.interfaces.Drink;
import bakery.repositories.interfaces.DrinkRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DrinkRepositoryImpl implements DrinkRepository<Drink> {
    private Collection<Drink> models;

    public DrinkRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Drink getByNameAndBrand(String drinkName, String drinkBrand) {
        return this.models.stream()
                .filter(d -> d.getName().equals(drinkName) && d.getBrand().equals(drinkBrand))
                .findFirst().orElse(null);
    }

    @Override
    public Collection<Drink> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Drink model) {
        this.models.add(model);
    }
}
