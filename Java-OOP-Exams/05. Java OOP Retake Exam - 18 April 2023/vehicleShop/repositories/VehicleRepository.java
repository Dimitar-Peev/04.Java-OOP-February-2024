package vehicleShop.repositories;

import vehicleShop.models.vehicle.Vehicle;

import java.util.*;

public class VehicleRepository implements Repository<Vehicle> {
    private Map<String, Vehicle> vehicles;

    public VehicleRepository() {
        this.vehicles = new LinkedHashMap<>();
    }

    @Override
    public Collection<Vehicle> getWorkers() {
        return Collections.unmodifiableCollection(this.vehicles.values());
    }

    @Override
    public void add(Vehicle vehicle) {
        this.vehicles.putIfAbsent(vehicle.getName(), vehicle);
    }

    @Override
    public boolean remove(Vehicle vehicle) {
        return this.vehicles.remove(vehicle) != null;
    }

    @Override
    public Vehicle findByName(String name) {
        return this.vehicles.get(name);
    }
}
