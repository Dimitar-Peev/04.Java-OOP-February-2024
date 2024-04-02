package harpoonDiver.models.diver;

public class DeepWaterDiver extends BaseDiver{
    private static final int INITIAL_UNITS_OF_OXYGEN = 90;
    public DeepWaterDiver(String name) {
        super(name, INITIAL_UNITS_OF_OXYGEN);
    }
}
