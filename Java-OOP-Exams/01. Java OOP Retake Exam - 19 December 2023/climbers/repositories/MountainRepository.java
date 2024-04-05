package climbers.repositories;

import climbers.models.mountain.Mountain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MountainRepository implements Repository<Mountain> {
    private final Collection<Mountain> mountains;

    public MountainRepository() {
        this.mountains = new ArrayList<>();
    }

    @Override
    public Collection<Mountain> getCollection() {
        return Collections.unmodifiableCollection(this.mountains);
    }

    @Override
    public void add(Mountain mountain) {
        this.mountains.add(mountain);
    }

    @Override
    public boolean remove(Mountain mountain) {
        return this.mountains.remove(mountain);
    }

    @Override
    public Mountain byName(String name) {
        return this.mountains.stream().filter(m -> m.getName().equals(name)).findFirst().orElse(null);
    }
}
