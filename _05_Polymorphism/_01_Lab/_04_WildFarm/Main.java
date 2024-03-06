package _01_Lab._04_WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();

        String input = scanner.nextLine();
        while (!"End".equals(input)) {

            Animal animal = createAnimal(input);
            Food food = createFood(scanner.nextLine());
            animal.makeSound();

            try {
                animal.eat(food);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            animals.add(animal);

            input = scanner.nextLine();
        }

        animals.forEach(System.out::println);

    }

    private static Animal createAnimal(String line) {
        String[] tokens = line.split("\\s+");

        String type = tokens[0];
        String name = tokens[1];
        Double weight = Double.parseDouble(tokens[2]);
        String livingRegion = tokens[3];

        Animal animal = null;

        if (type.equals("Cat")) {
            animal = new Cat(name, type, weight, livingRegion, tokens[4]);
        } else if (type.equals("Mouse")) {
            animal = new Mouse(name, type, weight, livingRegion);
        } else if (type.equals("Tiger")) {
            animal = new Tiger(name, type, weight, livingRegion);
        } else {
            animal = new Zebra(name, type, weight, livingRegion);
        }

        return animal;
    }


    private static Food createFood(String input) {
        String[] foodsArr = input.split("\\s+");

        String foodType = foodsArr[0];
        Integer quantity = Integer.parseInt(foodsArr[1]);

        return foodType.equals("Meat") ? new Meat(quantity) : new Vegetable(quantity);
    }
}
