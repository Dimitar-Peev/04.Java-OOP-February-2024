package unitTesting;

import java.util.*;

public class RaceEntry {
    private static final String EXISTING_RIDER = "Rider %s is already added";
    private static final String RIDER_INVALID = "Rider cannot be null.";
    private static final String RIDER_ADDED = "Rider %s added in race.";
    private static final int MIN_PARTICIPANTS = 2;
    private static final String RACE_INVALID = "The race cannot start with less than %d participants.";

    private Map<String, UnitRider> riders;

    public RaceEntry() {
        this.riders = new LinkedHashMap<>();
    }

    public String addRider(UnitRider rider) {
        if (rider == null) {
            throw new NullPointerException(RIDER_INVALID);
        }

        if (this.riders.containsKey(rider.getName())) {
            throw new IllegalArgumentException(String.format(EXISTING_RIDER, rider.getName()));
        }

        this.riders.put(rider.getName(), rider);
        return String.format(RIDER_ADDED, rider.getName());
    }

    public double calculateAverageHorsePower() {
        if (this.riders.size() < MIN_PARTICIPANTS) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, MIN_PARTICIPANTS));
        }

        double averageHorsePower = this.riders
                .values()
                .stream()
                .mapToInt(r -> r.getMotorcycle().getHorsePower())
                .average()
                .getAsDouble();

        return averageHorsePower;
    }

    public Collection<UnitRider> getRiders() {
        return Collections.unmodifiableCollection(this.riders.values());
    }
}
