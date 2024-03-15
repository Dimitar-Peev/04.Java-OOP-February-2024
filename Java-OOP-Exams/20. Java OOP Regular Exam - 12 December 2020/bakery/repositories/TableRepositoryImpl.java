package bakery.repositories;

import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.TableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TableRepositoryImpl<T> implements TableRepository<T> {
    //  public class TableRepositoryImpl implements TableRepository<Table> {
    private Collection<T> models;

    public TableRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(T model) {
        this.models.add(model);
    }

    @Override
    public T getByNumber(int tableNumber) {
        return null;
    }
}
