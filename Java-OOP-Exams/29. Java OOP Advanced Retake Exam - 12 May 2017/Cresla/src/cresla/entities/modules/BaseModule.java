package cresla.entities.modules;

import cresla.interfaces.Module;

import static cresla.common.ConstantMessages.MODULE_INFO;

public abstract class BaseModule implements Module {
    private int id;

    protected BaseModule(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return MODULE_INFO.formatted(this.getClass().getSimpleName(), this.getId());
    }
}
