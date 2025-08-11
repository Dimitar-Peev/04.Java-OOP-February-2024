package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.entities.*;
import motocrossWorldChampionship.entities.interfaces.*;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static motocrossWorldChampionship.common.ExceptionMessages.*;
import static motocrossWorldChampionship.common.OutputMessages.*;

public class ChampionshipControllerImpl implements ChampionshipController {
    private Repository<Rider> riderRepository;
    private Repository<Motorcycle> motorcycleRepository;
    private Repository<Race> raceRepository;

    public ChampionshipControllerImpl(Repository<Rider> riderRepository,
                                      Repository<Motorcycle> motorcycleRepository,
                                      Repository<Race> raceRepository) {
        this.riderRepository = riderRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createRider(String riderName) {
        if (this.riderRepository.getByName(riderName) != null) {
            throw new IllegalArgumentException(String.format(RIDER_EXISTS, riderName));
        }

        Rider rider = new RiderImpl(riderName);
        this.riderRepository.add(rider);
        return String.format(RIDER_CREATED, riderName);
    }

    @Override
    public String createMotorcycle(String type, String model, int horsePower) {
        if (this.motorcycleRepository.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(MOTORCYCLE_EXISTS, model));
        }

        Motorcycle motorcycle = null;
        if ("Power".equals(type)) {
            motorcycle = new PowerMotorcycle(model, horsePower);
        } else if ("Speed".equals(type)) {
            motorcycle = new SpeedMotorcycle(model, horsePower);
        } else {
            throw new IllegalArgumentException(String.format(MOTORCYCLE_INVALID));
        }

        this.motorcycleRepository.add(motorcycle);
        return String.format(MOTORCYCLE_CREATED, type + "Motorcycle", model);
    }

    @Override
    public String addMotorcycleToRider(String riderName, String motorcycleModel) {
        Rider rider = this.riderRepository.getByName(riderName);
        if (rider == null) {
            throw new NullPointerException(String.format(RIDER_NOT_FOUND, riderName));
        }

        Motorcycle motorcycle = this.motorcycleRepository.getByName(motorcycleModel);
        if (motorcycle == null) {
            throw new NullPointerException(String.format(MOTORCYCLE_NOT_FOUND, motorcycleModel));
        }

        rider.addMotorcycle(motorcycle);
        return String.format(MOTORCYCLE_ADDED, riderName, motorcycleModel);
    }

    @Override
    public String addRiderToRace(String raceName, String riderName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new NullPointerException(String.format(RACE_NOT_FOUND, raceName));
        }

        Rider rider = this.riderRepository.getByName(riderName);
        if (rider == null) {
            throw new NullPointerException(String.format(RIDER_NOT_FOUND, riderName));
        }

        race.addRider(rider);
        return String.format(RIDER_ADDED, riderName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new NullPointerException(String.format(RACE_NOT_FOUND, raceName));
        }

        if (race.getRiders().size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        List<Rider> winners = race.getRiders()
                .stream()
                .sorted((f, s) -> Double.compare(s.getMotorcycle().calculateRacePoints(race.getLaps()),
                        f.getMotorcycle().calculateRacePoints(race.getLaps())))
                .limit(3)
                .collect(Collectors.toList());

        winners.get(0).winRace();

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(RIDER_FIRST_POSITION, winners.get(0).getName(), race.getName()));
        sb.append(System.lineSeparator());
        sb.append(String.format(RIDER_SECOND_POSITION, winners.get(1).getName(), race.getName()));
        sb.append(System.lineSeparator());
        sb.append(String.format(RIDER_THIRD_POSITION, winners.get(2).getName(), race.getName()));

        this.raceRepository.remove(race);
        return sb.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        if (this.raceRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }

        Race race = new RaceImpl(name, laps);
        this.raceRepository.add(race);
        return String.format(RACE_CREATED, name);
    }
}
