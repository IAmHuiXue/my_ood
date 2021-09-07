package card_game;

public class Card implements Comparable<Card> {
    private final Suit suit;
    private final int value;

    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Card o) {
        if (this.suit == Suit.RED && o.suit == Suit.GREEN) {
            return 1;
        }
        if (this.suit == Suit.GREEN && o.suit == Suit.RED ) {
            return -1;
        }
        if (this.suit.ordinal() < o.suit.ordinal()) {
            return -1;
        }
        if (this.suit.ordinal() > o.suit.ordinal()) {
            return 1;
        }
        return Integer.compare(o.value, this.value);
    }
}
