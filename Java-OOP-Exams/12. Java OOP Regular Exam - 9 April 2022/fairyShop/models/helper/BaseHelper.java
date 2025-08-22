package fairyShop.models.helper;

import fairyShop.models.instrument.Instrument;

import java.util.ArrayList;
import java.util.Collection;

import static fairyShop.common.ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY;

public abstract class BaseHelper implements Helper {
    private static final int WORKING_ENERGY = 10;

    private String name;
    private int energy;
    private Collection<Instrument> instruments;

    protected BaseHelper(String name, int energy) {
        this.setName(name);
        this.setEnergy(energy);
        this.instruments = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(HELPER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public void work() {
        this.energy = Math.max(this.getEnergy() - WORKING_ENERGY, 0);
    }

    @Override
    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        return this.getEnergy() > 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return this.instruments;
    }
}
