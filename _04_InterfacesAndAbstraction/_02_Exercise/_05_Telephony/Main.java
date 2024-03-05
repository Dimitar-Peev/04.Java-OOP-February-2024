package _02_Exercise._05_Telephony;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<String> numbers = List.of(scanner.nextLine().split("\\s+"));
        List<String> sites = List.of(scanner.nextLine().split("\\s+"));

        Smartphone smartphone = new Smartphone(numbers, sites);

        System.out.println(smartphone.call());
        System.out.println(smartphone.browse());
    }
}
