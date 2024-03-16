package glacialExpedition.repositories;

import glacialExpedition.models.explorers.Explorer;

import java.util.*;

public class ExplorerRepository implements Repository<Explorer> {
    private Map<String, Explorer> explorers;

    public ExplorerRepository() {
        this.explorers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Explorer> getCollection() {
        return Collections.unmodifiableCollection(this.explorers.values());
    }

    @Override
    public void add(Explorer entity) {
        this.explorers.put(entity.getName(), entity);
    }

    @Override
    public boolean remove(Explorer entity) {
        return this.explorers.remove(entity.getName()) != null;
    }

    @Override
    public Explorer byName(String name) {
        return this.explorers.get(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        explorers.values().forEach(explorer -> sb.append(explorer).append(System.lineSeparator()));
        return sb.toString();
    }
}
