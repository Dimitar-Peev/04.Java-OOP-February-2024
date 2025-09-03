package cresla.entities.modules;

import cresla.interfaces.EnergyModule;

import static cresla.common.ConstantMessages.ENERGY_INFO;

public abstract class BaseEnergyModule extends BaseModule implements EnergyModule {
    private int energyOutput;

    protected BaseEnergyModule(int id, int energyOutput) {
        super(id);
        this.energyOutput = energyOutput;
    }

    @Override
    public int getEnergyOutput() {
        return this.energyOutput;
    }

    @Override
    public String toString() {
        return super.toString() + ENERGY_INFO.formatted(this.getEnergyOutput());
    }
}
