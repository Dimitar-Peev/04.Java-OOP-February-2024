package _01_Lab._04_WildFarm;

public class Tiger extends Felime {
    public Tiger(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public boolean canEat(Food food) {
        return food.getClass().getSimpleName().equals("Meat");
    }
}
