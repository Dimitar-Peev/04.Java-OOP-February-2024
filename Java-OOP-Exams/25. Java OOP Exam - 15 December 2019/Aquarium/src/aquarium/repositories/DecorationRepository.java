package aquarium.repositories;

import java.util.ArrayList;
import java.util.Collection;

import aquarium.models.decorations.Decoration;

public class DecorationRepository implements Repository {

    private Collection<Decoration> decorations;

    public DecorationRepository() {
        this.decorations = new ArrayList<>();
    }

    @Override
    public void add(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public boolean remove(Decoration decoration) {
        return this.decorations.remove(decoration);
    }

    @Override
    public Decoration findByType(String type) {
        return this.decorations.stream().filter(d -> d.getClass().getSimpleName().equals(type)).findFirst().orElse(null);
    }
}
