package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static zoo.common.ExceptionMessages.*;

public abstract class BaseArea implements Area{
    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    protected BaseArea(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }
    private void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Animal> getAnimals() { // TODO must be only this.animals
        return Collections.unmodifiableCollection(this.animals);
    }

    @Override
    public Collection<Food> getFoods() { // TODO must be only this.foods
        return Collections.unmodifiableCollection(this.foods);
    }

    @Override
    public int sumCalories() {
        return this.foods.stream().mapToInt(Food::getCalories).sum();
    }

    @Override
    public void addAnimal(Animal animal) {
        if (animals.size() == capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        this.animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void feed() { // what does the animal eat?
        this.animals.forEach(Animal::eat);
    }

    @Override
    public String getInfo() {
        String animalOuput = animals.isEmpty()
                ? "none"
                : animals.stream().map(Animal::getName).collect(Collectors.joining(" "));

        return String.format("%s (%s):%n" +
                "Animals: %s%n" +
                "Foods: %d%n" +
                "Calories: %d",
                getName(),getClass().getSimpleName(),
                animalOuput,
                foods.size(),
                sumCalories());
    }
}
