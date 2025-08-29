package easterRaces;

import easterRaces.core.EngineImpl;
import easterRaces.core.interfaces.Controller;
import easterRaces.core.ControllerImpl;
import easterRaces.entities.cars.Car;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.racers.Race;
import easterRaces.io.*;
import easterRaces.repositories.CarRepository;
import easterRaces.repositories.DriverRepository;
import easterRaces.repositories.RaceRepository;
import easterRaces.repositories.interfaces.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Repository<Car> motorcycleRepository = new CarRepository();
        Repository<Race> raceRepository = new RaceRepository();
        Repository<Driver> riderRepository = new DriverRepository();

        Controller controller = new ControllerImpl(riderRepository, motorcycleRepository, raceRepository);

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();
    }
}
