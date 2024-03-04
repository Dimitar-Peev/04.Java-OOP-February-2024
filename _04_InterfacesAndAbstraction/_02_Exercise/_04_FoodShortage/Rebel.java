package _04_FoodShortage;

import _04_FoodShortage.interfaces.Buyer;
import _04_FoodShortage.interfaces.Person;

public class Rebel implements Person, Buyer {
    private static final int INCREASE_FOOD_WITH_FIVE = 5;
    private String name;
    private int age;
    private String group;
    private int food;

    public Rebel(String name, int age, String group) {
        this.setName(name);
        this.setAge(age);
        this.setGroup(group);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    private void increaseFood() {
        this.food += INCREASE_FOOD_WITH_FIVE;
    }

    @Override
    public void buyFood() {
        this.increaseFood();
    }

    @Override
    public int getFood() {
        return this.food;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }
}
