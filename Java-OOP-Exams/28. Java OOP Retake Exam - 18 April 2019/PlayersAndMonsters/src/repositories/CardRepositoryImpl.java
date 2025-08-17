package repositories;

import models.cards.interfaces.Card;
import repositories.interfaces.CardRepository;

import java.util.*;

import static common.ExceptionMessages.*;

public class CardRepositoryImpl implements CardRepository {

    private Map<String, Card> cards;

    public CardRepositoryImpl() {
        this.cards = new LinkedHashMap<>();
    }

    @Override
    public int getCount() {
        return this.cards.size();
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(new ArrayList<>(this.cards.values()));
    }

    @Override
    public void add(Card card) {
        isNotNullCard(card);

        String cardName = card.getName();
        if (this.cards.containsKey(cardName)) {
            throw new IllegalArgumentException(String.format(CARD_EXIST, cardName));
        }

        this.cards.put(cardName, card);
    }

    @Override
    public boolean remove(Card card) {
        isNotNullCard(card);

        return this.cards.remove(card.getName()) != null;
    }

    @Override
    public Card find(String name) {
        Card card = null;

        if (this.cards.containsKey(name)) {
            card = this.cards.get(name);
        }

        return card;
    }

    private void isNotNullCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException(CARD_NULL);
        }
    }
}
