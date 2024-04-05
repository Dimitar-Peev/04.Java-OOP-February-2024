package vehicleShop.models.tool;

import vehicleShop.common.ExceptionMessages;

import static vehicleShop.common.ExceptionMessages.TOOL_POWER_LESS_THAN_ZERO;

public class ToolImpl implements Tool {
    private int power;

    public ToolImpl(int power) {
        this.setPower(power);
    }

    private void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(TOOL_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void decreasesPower() {
        setPower(Math.max(0, getPower() - 5));
    }

    @Override
    public boolean isUnfit() {
        return this.getPower() == 0;
    }
}
