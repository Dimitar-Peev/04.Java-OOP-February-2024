package harpoonDiver.repositories;

import harpoonDiver.models.divingSite.DivingSite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DivingSiteRepository implements Repository<DivingSite> {
    private Collection<DivingSite> divingSites; // TODO checkname

    public DivingSiteRepository() {
        this.divingSites = new ArrayList<>();
    }

    @Override
    public Collection<DivingSite> getCollection() {
        return Collections.unmodifiableCollection(this.divingSites);
    }

    @Override
    public void add(DivingSite divingSite) {
        this.divingSites.add(divingSite);
    }

    @Override
    public boolean remove(DivingSite divingSite) {
        return this.divingSites.remove(divingSite);
    }

    @Override
    public DivingSite byName(String name) {
        return this.divingSites.stream()
                .filter(divingSite -> divingSite.getName().equals(name))
                .findFirst().orElse(null);
    }
}
