package _01_WorkingWithAbstraction._02_Exercise._03_CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String cardRank = scanner.nextLine();
        String cardSuit = scanner.nextLine();

        Card card = new Card(CardRank.valueOf(cardRank), CardSuit.valueOf(cardSuit));
        System.out.println(card);
    }
}
