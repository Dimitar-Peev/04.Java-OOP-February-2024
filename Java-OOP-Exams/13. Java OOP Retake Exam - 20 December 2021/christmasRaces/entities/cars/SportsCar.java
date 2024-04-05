package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar {
    private static final double CUBIC_CENTIMETERS = 3000;
    private static final int MIN_HP = 250;
    private static final int MAX_HP = 450;

    public SportsCar(String model, int horsePower) {
        super(model, setHP(horsePower), CUBIC_CENTIMETERS);
    }

    private static int setHP(int horsePower) {
        if (horsePower < MIN_HP || horsePower > MAX_HP) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
        return horsePower;
    }
}
