package cresla.entities.reactors;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Reactor;

import static cresla.common.ConstantMessages.REACTOR_INFO;

public abstract class BaseReactor implements Reactor {
    private int id;
    private Container container;

    protected BaseReactor(int id, int moduleCapacity) {
        this.id = id;
        this.container = new ModuleContainer(moduleCapacity);
    }

    @Override
    public long getTotalEnergyOutput() {
        long energyOutput = this.container.getTotalEnergyOutput();

        energyOutput = getEnergyOutput(energyOutput);

        return energyOutput;
    }

    private long getEnergyOutput(long energyOutput) {
        if (energyOutput > getTotalHeatAbsorbing()) {
            energyOutput = 0;
        }
        return energyOutput;
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return this.container.getTotalHeatAbsorbing();
    }

    @Override
    public int getModuleCount() {
        return this.container.getModuleByInputCount();
    }

    @Override
    public void addEnergyModule(EnergyModule energyModule) {
        this.container.addEnergyModule(energyModule);
    }

    @Override
    public void addAbsorbingModule(AbsorbingModule absorbingModule) {
        this.container.addAbsorbingModule(absorbingModule);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return REACTOR_INFO.formatted(
                this.getClass().getSimpleName(), this.getId(),
                this.getTotalEnergyOutput(),
                this.getTotalHeatAbsorbing(),
                this.getModuleCount());
    }
}
