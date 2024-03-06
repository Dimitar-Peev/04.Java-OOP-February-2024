package _01_Lab._04_WildFarm;

public class Cat extends Felime {
    private String breed;

    public Cat(String animalName, String animalType, Double animalWeight, String livingRegion, String breed) {
        super(animalName, animalType, animalWeight, livingRegion);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public boolean canEat(Food food) {
        return true;
    }

    @Override
    public String toString() {
        String baseToString = super.toString();

        String left = baseToString.substring(0, baseToString.indexOf(" ") + 1);
        String right = baseToString.substring(baseToString.indexOf(" "));

        return left + breed + "," + right;
    }
}