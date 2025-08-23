package fairyShop.models.instrument;

import static fairyShop.common.ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO;

public class InstrumentImpl implements Instrument {
    private static final int POWER_FOR_USE = 10;

    private int power;

    public InstrumentImpl(int power) {
        this.setPower(power);
    }

    private void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(INSTRUMENT_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void use() {
        this.setPower(Math.max(this.getPower() - POWER_FOR_USE, 0));
    }

    @Override
    public boolean isBroken() {
        return this.getPower() == 0;
    }
}
