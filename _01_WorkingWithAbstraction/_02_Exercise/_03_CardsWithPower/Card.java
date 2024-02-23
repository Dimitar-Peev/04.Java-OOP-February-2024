package _01_WorkingWithAbstraction._02_Exercise._03_CardsWithPower;

public class Card {
    private CardRank rank;
    private CardSuit suit;
    private int power;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
        calculatePower();
    }

    private void calculatePower() {
        CardRank.valueOf(this.rank.name()).getValue();
        this.power = rank.getValue() + suit.getValue();
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d",
                this.rank,this.suit.name(),this.power);
    }
}
