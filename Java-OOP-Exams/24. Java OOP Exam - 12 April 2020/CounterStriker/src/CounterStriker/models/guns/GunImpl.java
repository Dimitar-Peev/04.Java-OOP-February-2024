package CounterStriker.models.guns;

import static CounterStriker.common.ExceptionMessages.*;

public abstract class GunImpl implements Gun {

    private String name;
    private int bulletsCount;

    protected GunImpl(String name, int bulletsCount) {
        this.setName(name);
        this.setBulletsCount(bulletsCount);
    }

    @Override
    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        if (name == null || name.trim().isBlank()) {
            throw new NullPointerException(INVALID_GUN_NAME);
        }

        this.name = name;
    }

    @Override
    public int getBulletsCount() {
        return this.bulletsCount;
    }

    protected void setBulletsCount(int bulletsCount) {
        if (bulletsCount < 0) {
            throw new IllegalArgumentException(INVALID_GUN_BULLETS_COUNT);
        }

        this.bulletsCount = bulletsCount;
    }

    @Override
    public abstract int fire();

}
