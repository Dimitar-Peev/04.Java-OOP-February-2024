package climbers.models.climber;

public class RockClimber extends BaseClimber{
    private static final int DECREASE = 60;
    public RockClimber(String name) {
        super(name, 120);
    }

    @Override
    public void climb() {
        setStrength(Math.max(0, getStrength() - DECREASE));
    }
}
