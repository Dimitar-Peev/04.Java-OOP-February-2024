package fairyShop.repositories;

import fairyShop.models.present.Present;

import java.util.*;

public class PresentRepository implements Repository<Present> {

    private List<Present> presents = new ArrayList<>();

    @Override
    public Collection<Present> getModels() {
        return Collections.unmodifiableList(this.presents);
    }

    @Override
    public void add(Present model) {
        presents.add(model);
    }

    @Override
    public boolean remove(Present model) {
        return presents.remove(model);
    }

    @Override
    public Present findByName(String name) {
        return this.presents.stream().filter(present -> present.getName().equals(name)).findFirst().orElse(null);
    }
}
