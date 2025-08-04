package aquarium.models.aquariums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import aquarium.models.decorations.Decoration;
import aquarium.models.fish.Fish;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().length() < 1) {
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int calculateComfort() {
        return this.decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() >= this.getCapacity()) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        this.fish.forEach(Fish::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s (%s):", this.getName(), this.getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Fish: %s", this.fish.isEmpty() ? "none" : getFishInfo()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Decorations: %d", this.decorations.size()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Comfort: %d", this.calculateComfort()));

        return sb.toString().trim();
    }

    private String getFishInfo() {
        StringBuilder sb = new StringBuilder();
        this.fish.forEach(f -> sb.append(f.getName()).append(" "));
        return sb.toString().trim();
    }

    @Override
    public Collection<Fish> getFish() {
        return Collections.unmodifiableCollection(this.fish);
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return Collections.unmodifiableCollection(this.decorations);
    }
}
