package climbers.models.climber;

public class RockClimber extends BaseClimber {

    private static final double INITIAL_STRENGTH = 120;

    public RockClimber(String name) {
        super(name, INITIAL_STRENGTH);
    }

    public void doSomething(){
        System.out.printf("Do something!");
    }
}
