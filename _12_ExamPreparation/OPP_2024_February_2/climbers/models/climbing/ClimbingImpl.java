package climbers.models.climbing;

import java.util.Collection;

import climbers.models.climber.Climber;
import climbers.models.mountain.Mountain;

public class ClimbingImpl implements Climbing {

    @Override
    public void conqueringPeaks(Mountain mountain, Collection<Climber> climbers) {

        Collection<String> peaksList = mountain.getPeaksList();

        climbers.forEach(climber -> {

            while (climber.canClimb() && peaksList.iterator().hasNext()) {
                climber.climb();

                String peak = peaksList.iterator().next();
                climber.getRoster().getPeaks().add(peak);
                peaksList.remove(peak);
            }
        });


    }
}
