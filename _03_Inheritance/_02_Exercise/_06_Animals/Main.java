package _03_Inheritance._02_Exercise._06_Animals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Animal> animals = new ArrayList<>();

        while (!"Beast!".equals(input)) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);

            try {
                switch (input) {
                    case "Dog":
                        String gender = tokens[2];
                        Dog dog = new Dog(name, age, gender);
                        animals.add(dog);
                        break;
                    case "Cat":
                        gender = tokens[2];
                        Cat cat = new Cat(name, age, gender);
                        animals.add(cat);
                        break;
                    case "Frog":
                        gender = tokens[2];
                        Frog frog = new Frog(name, age, gender);
                        animals.add(frog);
                        break;
                    case "Kittens":
                        Kitten kitten = new Kitten(name, age);
                        animals.add(kitten);
                        break;
                    case "Tomcat":
                        Tomcat tomcat = new Tomcat(name, age);
                        animals.add(tomcat);
                        break;
                    default:
                        System.out.println("Invalid input!");
                        break;
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Invalid input!");
            }

            input = scanner.nextLine();
        }

        animals.forEach(System.out::println);
    }
}
