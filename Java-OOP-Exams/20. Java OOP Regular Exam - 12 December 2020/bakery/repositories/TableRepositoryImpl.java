package bakery.repositories;

import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.TableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TableRepositoryImpl implements TableRepository<Table> {
    private Collection<Table> models;

    public TableRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Table model) {
        this.models.add(model);
    }

    @Override
    public Table getByNumber(int tableNumber) {
        return this.models.stream().filter(t -> t.getTableNumber() == tableNumber).findFirst().orElse(null);
    }
}
