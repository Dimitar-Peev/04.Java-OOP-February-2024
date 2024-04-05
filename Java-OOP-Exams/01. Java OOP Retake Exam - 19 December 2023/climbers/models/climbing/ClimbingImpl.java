package climbers.models.climbing;

import climbers.models.climber.Climber;
import climbers.models.mountain.Mountain;

import java.util.Collection;

public class ClimbingImpl implements Climbing {
    @Override
    public void conqueringPeaks(Mountain mountain, Collection<Climber> climbers) {
        // вземам списъка с върховете
        Collection<String> mountains = mountain.getPeaksList();
        // почвам да обхождам със всеки алпинист
        for (Climber climber : climbers) {
            while (climber.canClimb() && mountains.iterator().hasNext()) {
                climber.climb();
                String currentPeak = mountains.iterator().next();
                climber.getRoster().getPeaks().add(currentPeak);
                mountains.remove(currentPeak);
            }
        }

    }
}
