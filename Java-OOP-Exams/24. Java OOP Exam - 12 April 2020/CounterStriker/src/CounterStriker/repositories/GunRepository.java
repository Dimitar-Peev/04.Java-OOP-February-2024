package CounterStriker.repositories;

import CounterStriker.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static CounterStriker.common.ExceptionMessages.*;

public class GunRepository implements Repository<Gun> {

    private List<Gun> models;

    public GunRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return this.models;
    }

    @Override
    public void add(Gun model) {
        if (model == null) {
            throw new NullPointerException(INVALID_GUN_REPOSITORY);
        }

        this.models.add(model);
    }

    @Override
    public boolean remove(Gun model) {
        return this.getModels().remove(model);
    }

    @Override
    public Gun findByName(String name) {
        return this.getModels().stream().filter(gun -> gun.getName().equals(name)).findFirst().orElse(null);
    }
}
