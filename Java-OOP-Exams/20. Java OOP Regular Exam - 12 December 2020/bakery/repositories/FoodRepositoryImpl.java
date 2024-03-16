package bakery.repositories;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.repositories.interfaces.FoodRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FoodRepositoryImpl implements FoodRepository<BakedFood> {
    private Collection<BakedFood> models;

    public FoodRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public BakedFood getByName(String name) {
        return this.models.stream()
                .filter(food -> food.getName().equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public Collection<BakedFood> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(BakedFood model) {
        this.models.add(model);
    }
}
