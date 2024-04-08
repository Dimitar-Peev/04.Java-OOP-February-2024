package climbers.models.climber;

public class WallClimber extends BaseClimber {

    private static final double INITIAL_STRENGTH = 90;

    public WallClimber(String name) {
        super(name, INITIAL_STRENGTH);
    }
}
