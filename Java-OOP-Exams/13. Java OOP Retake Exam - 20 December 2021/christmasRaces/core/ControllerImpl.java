package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        if (this.driverRepository.getByName(driver) != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }

        Driver newDriver = new DriverImpl(driver);
        this.driverRepository.add(newDriver);

        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        if (this.carRepository.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }

        Car car = null;

        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                break;
            default:
                throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }

        this.carRepository.add(car);
        String carType = car.getClass().getSimpleName();
        return String.format(CAR_CREATED, carType, model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = this.driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        Car car = this.carRepository.getByName(carModel);
        if (car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        Driver driver = this.driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = this.raceRepository.getByName(name);

        if (race != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }

        Race newRace = new RaceImpl(name, laps);
        this.raceRepository.add(newRace);
        return String.format(RACE_CREATED, name);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        if (race.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        List<Driver> drivers = race.getDrivers().stream().collect(Collectors.toList());
        Map<Driver, Double> winnersMap = new LinkedHashMap<>();

        drivers.forEach(driver->{
                winnersMap.put(driver, driver.getCar().calculateRacePoints(race.getLaps()));
        });

        Map<Driver, Double> sorted = winnersMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Driver firstPlace = null, secondPlace = null, thirdPlace = null;
        int index = 1;

        for (Map.Entry<Driver, Double> driverDoubleEntry : sorted.entrySet()) {
            if (index == 1) {
                firstPlace = driverDoubleEntry.getKey();
            }
            if (index == 2) {
                secondPlace = driverDoubleEntry.getKey();
            }
            if (index == 3) {
                thirdPlace = driverDoubleEntry.getKey();
            }
            index++;
        }

        String output = String.format(DRIVER_FIRST_POSITION, firstPlace.getName(), race.getName()) + System.lineSeparator() +
                String.format(DRIVER_SECOND_POSITION, secondPlace.getName(), race.getName()) + System.lineSeparator() +
                String.format(DRIVER_THIRD_POSITION, thirdPlace.getName(), race.getName()) + System.lineSeparator();

        return output.trim();
    }
}
