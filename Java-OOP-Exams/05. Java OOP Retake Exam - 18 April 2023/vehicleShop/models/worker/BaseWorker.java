package vehicleShop.models.worker;

import vehicleShop.common.ExceptionMessages;
import vehicleShop.models.tool.Tool;

import java.util.ArrayList;
import java.util.Collection;

import static vehicleShop.common.ExceptionMessages.*;

public abstract class BaseWorker implements Worker {
    private String name; 
    private int strength; 
    private Collection<Tool> tools; 

    public BaseWorker(String name, int strength) {
        this.setName(name);
        this.setStrength(strength);
        this.setTools();
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(WORKER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    protected void setStrength(int strength) {
        if (strength < 0) {
            throw new IllegalArgumentException(WORKER_STRENGTH_LESS_THAN_ZERO);
        }
        this.strength = strength;
    }

    @Override
    public Collection<Tool> getTools() {
        return this.tools;
    }

    private void setTools() {
        this.tools = new ArrayList<>();
    }

    @Override
    public void working() {
        setStrength(Math.max(0, getStrength() - 10));
    }

    @Override
    public void addTool(Tool tool) {
        this.tools.add(tool);
    }

    @Override
    public boolean canWork() {
        return this.strength > 0;
    }

    @Override
    public String toString() {
        long leftTools = this.tools.stream().filter(tool -> tool.getPower() > 0).count();
        String sb = "Name: " + name + ", Strength: " + strength + System.lineSeparator() +
                "Tools: " + leftTools + " fit left" + System.lineSeparator();
        return sb.trim();
    }
}
