package climbers.models.climber;

public class WallClimber extends BaseClimber {
    private static final int DECREASE = 30;

    public WallClimber(String name) {
        super(name, 90);
    }

    @Override
    public void climb() {
        setStrength(Math.max(0, getStrength() - DECREASE));
    }
}
