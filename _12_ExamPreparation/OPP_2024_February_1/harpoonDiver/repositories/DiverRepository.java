package harpoonDiver.repositories;

import harpoonDiver.models.diver.Diver;

import java.util.*;

public class DiverRepository implements Repository<Diver> {
    //  private Collection<Diver> divers;
    private final Map<String, Diver> diverMap; //TODO checkname

    public DiverRepository() {
        //      this.divers = new ArrayList<>();
        this.diverMap = new LinkedHashMap<>();
    }

    @Override
    public Collection<Diver> getCollection() {
        //  return Collections.unmodifiableCollection(this.divers);
        return Collections.unmodifiableCollection(this.diverMap.values());

    }

    @Override
    public void add(Diver diver) {
        //     this.divers.add(diver);
        this.diverMap.putIfAbsent(diver.getName(), diver);
    }

    @Override
    public boolean remove(Diver diver) {
//        return this.divers.remove(diver);
        return this.diverMap.remove(diver.getName()) != null;
//        if (divers.containsKey(diver.getName())) {
//            divers.remove(diver);
//            return true;
//        }
//        return false;
    }

    @Override
    public Diver byName(String name) {
//        return this.divers.stream().filter(d -> d.getName().equals(name)).findFirst().orElse(null);
        return this.diverMap.get(name);

//        if (divers.containsKey(name)) {
//            return divers.get(name);
//        }
//        return null;
    }
}
