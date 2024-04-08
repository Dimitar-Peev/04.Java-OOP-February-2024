package climbers.core;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import climbers.models.climber.Climber;
import climbers.models.climber.RockClimber;
import climbers.models.climber.WallClimber;
import climbers.models.climbing.ClimbingImpl;
import climbers.models.mountain.Mountain;
import climbers.models.mountain.MountainImpl;
import climbers.repositories.ClimberRepository;
import climbers.repositories.MountainRepository;
import climbers.repositories.Repository;

import static climbers.common.ConstantMessages.*;
import static climbers.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private final Repository<Climber> climberRepository;
    private final Repository<Mountain> mountainRepository;
    private int mountainsCount;

    public ControllerImpl() {
        this.climberRepository = new ClimberRepository();
        this.mountainRepository = new MountainRepository();
    }

    @Override
    public String addClimber(String type, String climberName) {

        Climber climber;

        switch (type) {
            case "WallClimber":
                climber = new WallClimber(climberName);
                break;
            case "RockClimber":
                climber = new RockClimber(climberName);
                break;
            default:
                throw new IllegalArgumentException(CLIMBER_INVALID_TYPE);
        }

        this.climberRepository.add(climber);

        return String.format(CLIMBER_ADDED, type, climberName);
    }

    @Override
    public String addMountain(String mountainName, String... peaks) {

        Mountain mountain = new MountainImpl(mountainName);

        mountain.getPeaksList().addAll(List.of(peaks));

        this.mountainRepository.add(mountain);

        return String.format(MOUNTAIN_ADDED, mountainName);
    }

    @Override
    public String removeClimber(String climberName) {

        Climber climber = this.climberRepository.byName(climberName);
		
        if (null == climber) {
            throw new IllegalArgumentException(String.format(CLIMBER_DOES_NOT_EXIST, climberName));
        }

        this.climberRepository.remove(climber);

        return String.format(CLIMBER_REMOVE, climberName);
    }

    @Override
    public String startClimbing(String mountainName) {
        Collection<Climber> climbers = this.climberRepository.getCollection();

        if (climbers.isEmpty()) {
            throw new IllegalArgumentException(THERE_ARE_NO_CLIMBERS);
        }

        Mountain mountain = this.mountainRepository.byName(mountainName);
        ClimbingImpl climbing = new ClimbingImpl();
        climbing.conqueringPeaks(mountain, climbers);

        long removedClimbers = climbers.stream().filter(climber -> !climber.canClimb()).count();
        this.mountainsCount++;
        return String.format(PEAK_CLIMBING, mountainName, removedClimbers);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(FINAL_MOUNTAIN_COUNT, mountainsCount));
        sb.append(System.lineSeparator());
        sb.append(FINAL_CLIMBERS_STATISTICS);

        this.climberRepository
        .getCollection()
        .forEach(climber -> {

            sb.append(System.lineSeparator());
            sb.append(String.format(FINAL_CLIMBER_NAME, climber.getName()));

            sb.append(System.lineSeparator());
            sb.append(String.format(FINAL_CLIMBER_STRENGTH, climber.getStrength()));

            sb.append(System.lineSeparator());

            Collection<String> peaks = climber.getRoster().getPeaks();

            String conqueredPeaks = peaks.isEmpty() ?
                                    "None" :
                                    String.join(FINAL_CLIMBER_FINDINGS_DELIMITER, peaks);

            sb.append(String.format(FINAL_CLIMBER_PEAKS, conqueredPeaks));
        });

        return sb.toString();
    }
}
