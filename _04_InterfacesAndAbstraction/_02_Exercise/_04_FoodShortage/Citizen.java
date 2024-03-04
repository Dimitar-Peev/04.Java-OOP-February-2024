package _04_FoodShortage;

import _04_FoodShortage.interfaces.Birthable;
import _04_FoodShortage.interfaces.Buyer;
import _04_FoodShortage.interfaces.Identifiable;
import _04_FoodShortage.interfaces.Person;

public class Citizen implements Person, Birthable, Identifiable, Buyer {
    private static final int INCREASE_FOOD_WITH_TEN = 10;
    private String name;
    private int age;
    private String id;
    private String birthDate;
    private int food;


    public Citizen(String name, int age, String id, String birthDate) {
        this.setName(name);
        this.setAge(age);
        this.setId(id);
        this.setBirthDate(birthDate);
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void buyFood() {
        this.increaseFood();
    }

    @Override
    public int getFood() {
        return this.food;
    }

    private void increaseFood() {
        this.food += INCREASE_FOOD_WITH_TEN;
    }
}
