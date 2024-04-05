package climbers.core;

import climbers.models.climber.*;
import climbers.models.climbing.ClimbingImpl;
import climbers.models.mountain.Mountain;
import climbers.models.mountain.MountainImpl;
import climbers.repositories.*;
import climbers.models.climbing.Climbing;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static climbers.common.ConstantMessages.*;
import static climbers.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private final ClimberRepository climberRepository;
    private final MountainRepository mountainRepository;
    private Climbing climbing;
    private int conqueredPeaks;

    public ControllerImpl() {
        this.climberRepository = new ClimberRepository();
        this.mountainRepository = new MountainRepository();
        this.climbing = new ClimbingImpl();
        this.conqueredPeaks = 0;
    }

    @Override
    public String addClimber(String type, String climberName) {
        Climber climber = null;

        switch (type) {
            case "RockClimber":
                climber = new RockClimber(climberName);
                break;
            case "WallClimber":
                climber = new WallClimber(climberName);
                break;
            default:
                throw new IllegalArgumentException(CLIMBER_INVALID_TYPE);
        }

        climberRepository.add(climber);
        return String.format(CLIMBER_ADDED, type, climberName);
    }

    @Override
    public String addMountain(String mountainName, String... peaks) {
        Mountain mountain = new MountainImpl(mountainName);

        mountain.getPeaksList().addAll(Arrays.asList(peaks));

        this.mountainRepository.add(mountain);

        return String.format(MOUNTAIN_ADDED, mountainName);
    }

    @Override
    public String removeClimber(String climberName) {
        Climber climberToRemove = this.climberRepository.byName(climberName);

        if (climberToRemove == null) {
            throw new IllegalArgumentException(String.format(CLIMBER_DOES_NOT_EXIST, climberName));
        }

        this.climberRepository.remove(climberToRemove);

        return String.format(CLIMBER_REMOVE, climberName);
    }

    @Override
    public String startClimbing(String mountainName) {
        Collection<Climber> availableClimbers = this.climberRepository.getCollection();

        if (availableClimbers.isEmpty()) {
            throw new IllegalArgumentException(THERE_ARE_NO_CLIMBERS);
        }

        Climbing climbing = new ClimbingImpl();
        Mountain mountain = mountainRepository.byName(mountainName);
        climbing.conqueringPeaks(mountain, availableClimbers);
        long removed = availableClimbers.stream().filter(d -> d.getStrength() == 0).count();
        conqueredPeaks++;

        return String.format(PEAK_CLIMBING, mountainName, removed);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(FINAL_MOUNTAIN_COUNT, conqueredPeaks)).append(System.lineSeparator());
        sb.append(FINAL_CLIMBERS_STATISTICS).append(System.lineSeparator());
        this.climberRepository.getCollection().forEach(e -> {
            sb.append(String.format(FINAL_CLIMBER_NAME, e.getName())).append(System.lineSeparator());
            sb.append(String.format(FINAL_CLIMBER_STRENGTH, e.getStrength())).append(System.lineSeparator());
            sb.append(String.format(e.getRoster().getPeaks().isEmpty()
                    ? String.format(FINAL_CLIMBER_PEAKS, "None")
                    : FINAL_CLIMBER_PEAKS, e.getRoster().getPeaks().stream().map(String::valueOf).collect(Collectors.joining(", "))));
            sb.append(System.lineSeparator());
        });

        return sb.toString().trim();
    }
}
