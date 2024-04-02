package harpoonDiver.models.diver;

import harpoonDiver.models.seaCatch.BaseSeaCatch;
import harpoonDiver.models.seaCatch.SeaCatch;

import static harpoonDiver.common.ConstantMessages.*;
import static harpoonDiver.common.ExceptionMessages.*;

public abstract class BaseDiver implements Diver {
    private static final int DEFAULT_OXYGEN_CONSUMPTING = 30;
    private String name;
    private double oxygen;
    private SeaCatch seaCatch;

    protected BaseDiver(String name, double oxygen) {
        this.setName(name);
        this.setOxygen(oxygen);
        this.seaCatch = new BaseSeaCatch();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(DIVER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(DIVER_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getOxygen() {
        return this.oxygen;
    }

    @Override
    public boolean canDive() {
        return oxygen > 0;
    }

    @Override
    public SeaCatch getSeaCatch() {
        if (this.seaCatch == null) {
            this.initSeaCatch();
        }
        return this.seaCatch;
    }

    private void initSeaCatch() {
        this.seaCatch = new BaseSeaCatch();
    }

    @Override
    public void shoot() {
        this.oxygen = Math.max(0, this.oxygen - DEFAULT_OXYGEN_CONSUMPTING);
    }

    @Override
    public String toString() {
        String template = FINAL_DIVER_NAME + System.lineSeparator() +
                FINAL_DIVER_OXYGEN + System.lineSeparator() +
                FINAL_DIVER_CATCH;
        return String.format(template,
                this.getName(),
                this.getOxygen(),
                this.getSeaCatch().getSeaCreatures().isEmpty() ? "None" : String.join(", ",this.getSeaCatch().getSeaCreatures()));
    }
}
