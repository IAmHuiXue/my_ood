package card_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> cards;
    private int toBeDealtIndex;
    private static final Random random = new Random();

    public Deck() {
        toBeDealtIndex = 0;
        cards = new ArrayList<>();
        // 3 suits, 0 - 9 of each
        for (int i = 0; i < 10; i++) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(suit, i));
            }
        }
    }

    public void shuffle() {
        // i --> [0, size - 2] j --> [i, size - 1]
        for (int i = 0; i < cards.size() - 1; i++) {
            int j = random.nextInt(cards.size() - i) + i;
            Card c1 = cards.get(i);
            Card c2 = cards.get(j);
            cards.set(i, c2);
            cards.set(j, c1);
        }
    }

    public Card[] dealHand(int n) {
        // n > 0
        if (remainingCards() < n) {
            return null;
        }
        Card[] cards = new Card[n];
        for (int i = 0; i < n; i++) {
            cards[i] = dealCard();
        }
        return cards;
    }

    private int remainingCards() {
        return cards.size() - toBeDealtIndex;
    }

    private Card dealCard() {
        return remainingCards() > 0 ? cards.get(toBeDealtIndex++) : null;
    }

}
