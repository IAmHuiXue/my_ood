package card_game;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();

    public Card beatable(Card card) {
        for (Card c : cards) {
            if (c.compareTo(card) < 0) {
                return c;
            }
        }
        return null;
    }

    public boolean outOfCard() {
        return cards.size() == 0;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card placeCard(Card card) {
        cards.remove(card);
        return card;
    }

    // random place a card
    public Card placeCard() {
        return cards.remove(cards.size() - 1);
    }

    public int numOfCard() {
        return cards.size();
    }

//    public boolean play(Hand hand) {
//        Card card = hand.placeCard();
//        Card c = beatable(card);
//        if (c != null) {
//            placeCard(c);
//        }
//    }




}
