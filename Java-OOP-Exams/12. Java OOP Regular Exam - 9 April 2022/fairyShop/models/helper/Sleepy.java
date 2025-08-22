package fairyShop.models.helper;

public class Sleepy extends BaseHelper {
    private static final int INITIAL_ENERGY = 50;
    private static final int WORKING_ENERGY = 10 + 5;

    public Sleepy(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void work() {
        super.setEnergy(Math.max(super.getEnergy() - WORKING_ENERGY, 0));
    }
}
