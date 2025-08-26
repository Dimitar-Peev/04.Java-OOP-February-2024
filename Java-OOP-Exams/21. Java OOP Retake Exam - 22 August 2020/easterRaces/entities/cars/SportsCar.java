package easterRaces.entities.cars;


import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar {
    private static final double CUBIC_CENTIMETERS = 3000;
    private static final double HORSEPOWER_MIX = 250;
    private static final double HORSEPOWER_MAX = 450;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    protected void setHorsePower(int horsePower) {
        if (horsePower < HORSEPOWER_MIX || horsePower > HORSEPOWER_MAX) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
        super.setHorsePower(horsePower);
    }
}