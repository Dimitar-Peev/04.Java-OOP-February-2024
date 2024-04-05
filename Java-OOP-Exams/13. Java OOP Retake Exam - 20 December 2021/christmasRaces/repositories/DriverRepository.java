package christmasRaces.repositories;

import christmasRaces.entities.drivers.Driver;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DriverRepository implements Repository<Driver> {
    private Map<String, Driver> driverMap;

    public DriverRepository() {
        this.driverMap = new LinkedHashMap<>();
    }

    @Override
    public Driver getByName(String name) {
        return this.driverMap.get(name);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(this.driverMap.values());
    }

    @Override
    public void add(Driver driver) {
        this.driverMap.putIfAbsent(driver.getName(), driver);
    }

    @Override
    public boolean remove(Driver driver) {
        return this.driverMap.remove(driver.getName()) != null;
    }
}
