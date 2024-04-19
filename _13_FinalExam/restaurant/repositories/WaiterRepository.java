package restaurant.repositories;

import restaurant.models.waiter.Waiter;

import java.util.*;

public class WaiterRepository implements Repository<Waiter> {
    private Map<String, Waiter> waiters;

    public WaiterRepository() {
        this.waiters = new LinkedHashMap<>();
    }

    @Override
    public Collection<Waiter> getCollection() {
        return Collections.unmodifiableCollection(this.waiters.values());
    }

    @Override
    public void add(Waiter waiter) {
        this.waiters.putIfAbsent(waiter.getName(), waiter);
    }

    @Override
    public boolean remove(Waiter waiter) {
        return this.waiters.remove(waiter.getName()) != null;
    }

    @Override
    public Waiter byName(String name) {
        return this.waiters.get(name);
    }
}
