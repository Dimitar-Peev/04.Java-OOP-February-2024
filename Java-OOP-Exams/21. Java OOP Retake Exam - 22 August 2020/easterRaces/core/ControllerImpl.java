package easterRaces.core;

import easterRaces.core.interfaces.Controller;
import easterRaces.entities.cars.*;
import easterRaces.entities.drivers.*;
import easterRaces.entities.racers.*;
import easterRaces.repositories.interfaces.*;

import java.util.List;

import static easterRaces.common.ExceptionMessages.*;
import static easterRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Car> cars;
    private Repository<Driver> drivers;
    private Repository<Race> races;

    public ControllerImpl(Repository<Driver> drivers, Repository<Car> cars, Repository<Race> races) {
        this.cars = cars;
        this.drivers = drivers;
        this.races = races;
    }

    @Override
    public String createDriver(String driver) {
        if (this.drivers.getByName(driver) != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }

        Driver newDriver = new DriverImpl(driver);
        this.drivers.add(newDriver);
        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        if (this.cars.getByName(model) != null) {
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
                throw new IllegalArgumentException(INVALID_CAR_TYPE);
        }

        this.cars.add(car);
        return String.format(CAR_CREATED, type + "Car", model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        if (this.drivers.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        if (this.cars.getByName(carModel) == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }

        Driver driver = this.drivers.getByName(driverName);
        Car car = this.cars.getByName(carModel);

        driver.addCar(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        if (this.races.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        if (this.drivers.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        Race race = this.races.getByName(raceName);
        Driver driver = this.drivers.getByName(driverName);

        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driver.getName(), race.getName());
    }

    @Override
    public String createRace(String name, int laps) {
        if (this.races.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }

        Race race = new RaceImpl(name, laps);
        this.races.add(race);
        return String.format(RACE_CREATED, race.getName());
    }

    @Override
    public String startRace(String raceName) {
        if (this.races.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        Race race = this.races.getByName(raceName);
        if (race.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        List<Driver> listOfWinners = race.getDrivers().stream()
                .sorted((f, s) -> Double.compare(s.getCar().calculateRacePoints(race.getLaps()),
                        f.getCar().calculateRacePoints(race.getLaps()))).limit(3).toList();

        this.races.remove(race);

        return String.format(DRIVER_FIRST_POSITION, listOfWinners.get(0).getName(), race.getName()) +
                System.lineSeparator() +
                String.format(DRIVER_SECOND_POSITION, listOfWinners.get(1).getName(), race.getName()) +
                System.lineSeparator() +
                String.format(DRIVER_THIRD_POSITION, listOfWinners.get(2).getName(), race.getName());
    }
}
