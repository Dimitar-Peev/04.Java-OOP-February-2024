package cresla.core;

import cresla.entities.modules.CooldownSystem;
import cresla.entities.modules.CryogenRod;
import cresla.entities.modules.HeatProcessor;
import cresla.entities.reactors.CryoReactor;
import cresla.entities.reactors.HeatReactor;
import cresla.interfaces.Module;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Manager;
import cresla.interfaces.Reactor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cresla.common.ConstantMessages.*;

public class ManagerImpl implements Manager {
    private static int id;
    private Map<Integer, Reactor> reactors;
    private Map<Integer, Module> modules;

    public ManagerImpl() {
        ManagerImpl.id = 1;
        this.reactors = new HashMap<>();
        this.modules = new HashMap<>();
    }

    @Override
    public String reactorCommand(List<String> arguments) {
        String reactorType = arguments.get(0);
        int additionalParameter = Integer.parseInt(arguments.get(1));
        int moduleCapacity = Integer.parseInt(arguments.get(2));

        Reactor reactor = null;

        switch (reactorType) {
            case "Cryo":
                reactor = new CryoReactor(id, moduleCapacity, additionalParameter);
                break;
            case "Heat":
                reactor = new HeatReactor(id, moduleCapacity, additionalParameter);
                break;
        }

        this.reactors.putIfAbsent(id, reactor);

        return REACTOR_CREATED.formatted(reactorType, newId());
    }

    @Override
    public String moduleCommand(List<String> arguments) {
        int reactorId = Integer.parseInt(arguments.get(0));
        String type = arguments.get(1);
        int additionalParameter = Integer.parseInt(arguments.get(2));

        Module module = null;

        switch (type) {
            case "CryogenRod":
                module = new CryogenRod(id, additionalParameter);
                break;
            case "HeatProcessor":
                module = new HeatProcessor(id, additionalParameter);
                break;
            case "CooldownSystem":
                module = new CooldownSystem(id, additionalParameter);
                break;
        }

        if ("CryogenRod".equals(type)) {
            this.reactors.get(reactorId).addEnergyModule((EnergyModule) module);
        } else {
            this.reactors.get(reactorId).addAbsorbingModule((AbsorbingModule) module);
        }

        this.modules.putIfAbsent(id, module);

        return MODULE_ADDED.formatted(type, newId(), reactorId);
    }

    @Override
    public String reportCommand(List<String> arguments) {
        int objectId = Integer.parseInt(arguments.get(0));

        String output = "";
        if (this.reactors.containsKey(objectId)) {
            output = this.reactors.get(objectId).toString();
        } else if (this.modules.containsKey(objectId)) {
            output = this.modules.get(objectId).toString();
        }

        return output;
    }

    @Override
    public String exitCommand(List<String> arguments) {
        int cryoCount = 0;
        int heatReactor = 0;
        int energyModulesCount = 0;
        int absorbingModulesCount = 0;
        long totalEnergyOutput = 0;
        long totalHeatAbsorbing = 0;

        for (Map.Entry<Integer, Reactor> entry : reactors.entrySet()) {
            if (entry.getValue().getClass().getSimpleName().contains("Cryo")) {
                cryoCount++;
            } else {
                heatReactor++;
            }
            totalEnergyOutput += entry.getValue().getTotalEnergyOutput();
            totalHeatAbsorbing += entry.getValue().getTotalHeatAbsorbing();
        }

        for (Map.Entry<Integer, Module> entry : modules.entrySet()) {
            if (entry.getValue().getClass().getSuperclass().getSimpleName().contains("Energy")) {
                energyModulesCount++;
            } else {
                absorbingModulesCount++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Cryo Reactors: ").append(cryoCount).append(System.lineSeparator());
        sb.append("Heat Reactors: ").append(heatReactor).append(System.lineSeparator());
        sb.append("Energy Modules: ").append(energyModulesCount).append(System.lineSeparator());
        sb.append("Absorbing Modules: ").append(absorbingModulesCount).append(System.lineSeparator());
        sb.append("Total Energy Output: ").append(totalEnergyOutput).append(System.lineSeparator());
        sb.append("Total Heat Absorbing: ").append(totalHeatAbsorbing);

        return sb.toString();
    }

    private static int newId() {
        return id++;
    }
}