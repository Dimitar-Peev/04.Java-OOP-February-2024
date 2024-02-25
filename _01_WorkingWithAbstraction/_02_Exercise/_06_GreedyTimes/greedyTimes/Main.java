package greedyTimes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        long bagCapacity = Long.parseLong(scanner.nextLine());
        String[] inputArr = scanner.nextLine().split("\\s+");

        Bag bag = new Bag(bagCapacity);

        for (int i = 0; i < inputArr.length; i += 2) {
            String item = inputArr[i];
            long quantity = Long.parseLong(inputArr[i + 1]);

            if (item.length() == 3) {
                bag.addCash(item, quantity);
            } else if (item.toLowerCase().endsWith("gem")) {
                bag.addGems(item, quantity);
            } else if (item.toLowerCase().equals("gold")) {
                bag.addGold(quantity);
            }
        }

        bag.printContent();

    }
}