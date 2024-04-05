package christmasRaces.repositories;

import christmasRaces.entities.cars.Car;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CarRepository implements Repository<Car> {
    private Map<String, Car> carMap;

    public CarRepository() {
        this.carMap = new LinkedHashMap<>();
    }

    @Override
    public Car getByName(String name) {
        return this.carMap.get(name);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(this.carMap.values());
    }

    @Override
    public void add(Car car) {
        this.carMap.putIfAbsent(car.getModel(), car);
    }

    @Override
    public boolean remove(Car car) {
        return this.carMap.remove(car.getModel()) != null;
    }
}
