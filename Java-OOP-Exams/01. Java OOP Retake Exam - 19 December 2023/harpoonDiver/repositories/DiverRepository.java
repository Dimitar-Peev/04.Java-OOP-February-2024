package harpoonDiver.repositories;

import harpoonDiver.models.diver.Diver;

import java.util.*;

public class DiverRepository implements Repository<Diver> {
      private Collection<Diver> divers;

    public DiverRepository() {
              this.divers = new ArrayList<>();
    }

    @Override
    public Collection<Diver> getCollection() {
          return Collections.unmodifiableCollection(this.divers);
    }

    @Override
    public void add(Diver diver) {
             this.divers.add(diver);
    }

    @Override
    public boolean remove(Diver diver) {
        return this.divers.remove(diver);
    }

    @Override
    public Diver byName(String name) {
        return this.divers.stream().filter(d -> d.getName().equals(name)).findFirst().orElse(null);
    }
}
