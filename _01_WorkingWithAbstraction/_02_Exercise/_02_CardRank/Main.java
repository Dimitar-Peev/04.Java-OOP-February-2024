package _01_WorkingWithAbstraction._02_Exercise._02_CardRank;

public class Main {
    public static void main(String[] args) {
        CardRank[] cardRanks = CardRank.values();
        System.out.println("Card Ranks:");
        for (CardRank cardRank : cardRanks) {
            System.out.println(String.format("Ordinal value: %d; Name value: %s",
                    cardRank.ordinal(), cardRank.name()));
        }
    }
}
