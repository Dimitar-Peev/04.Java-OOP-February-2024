package parkingSystem;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SoftPark {
    private Map<String, Car> parking;

    public SoftPark() {
        this.parking = new HashMap<>();

        this.parking.put("A1", null);
        this.parking.put("A2", null);
        this.parking.put("A3", null);
        this.parking.put("A4", null);
        this.parking.put("B1", null);
        this.parking.put("B2", null);
        this.parking.put("B3", null);
        this.parking.put("B4", null);
        this.parking.put("C1", null);
        this.parking.put("C2", null);
        this.parking.put("C3", null);
        this.parking.put("C4", null);
    }

    public Map<String, Car> getParking() {
        return Collections.unmodifiableMap(this.parking);
    }

    public String parkCar(String parkSpot, Car car) {
        if (!this.parking.containsKey(parkSpot)) {
            throw new IllegalArgumentException("Parking spot doesn't exists!");
        }

        if (this.parking.get(parkSpot) != null) {
            throw new IllegalArgumentException("Parking spot is already taken!");
        }

        boolean carExist = true;
        Car optionalCar = this.parking.values().stream().filter(c -> c != null).findAny().orElse(null);

        if (optionalCar == null) {
            carExist = false;
        }

        if (carExist) {
            throw new IllegalArgumentException("Car is already parked!");
        }

        this.parking.put(parkSpot, car);
        return String.format("Car:%s parked successfully!", car.getRegistrationNumber());
    }

    public String removeCar(String parkSpot, Car car) {
        if (!this.parking.containsKey(parkSpot)) {
            throw new IllegalArgumentException("Parking spot doesn't exists!");
        }

        if (this.parking.get(parkSpot) != car) {
            throw new IllegalArgumentException("Car for that spot doesn't exists!");
        }

        this.parking.put(parkSpot, null);
        return String.format("Remove car:%s successfully!", car.getRegistrationNumber());
    }
}