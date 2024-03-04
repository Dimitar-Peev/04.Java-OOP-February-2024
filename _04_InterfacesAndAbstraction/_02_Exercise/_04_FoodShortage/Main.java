package _04_FoodShortage;

import _04_FoodShortage.interfaces.Buyer;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, Buyer> buyers = new LinkedHashMap<>();
        Buyer buyer = null;

        for (int i = 0; i < n; i++) {
            String[] inputArr = scanner.nextLine().split("\\s+");
            String name = inputArr[0];
            int age = Integer.parseInt(inputArr[1]);

            switch (inputArr.length){
                case 4:
                    String id = inputArr[2];
                    String birthdate = inputArr[3];
                    buyer = new Citizen(name, age, id, birthdate);
                    break;
                case 3:
                    String group = inputArr[2];
                    buyer = new Rebel(name, age, group);
                    break;
            }

            buyers.put(name, buyer);
        }

        String name = scanner.nextLine();
        while (!"End".equals(name)) {
            if (buyers.containsKey(name)) {
                Buyer person = buyers.get(name);
                person.buyFood();
            }
            name = scanner.nextLine();
        }

        int totalFoodSum = buyers.values().stream().mapToInt(Buyer::getFood).sum();
        System.out.println(totalFoodSum);
    }
}

