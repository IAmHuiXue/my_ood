package card_game;

public class Game {
    /**
     * We have a deck of cards. Each card is either red, blue or green, and has a "power" attached to it (0-9). Thus, we have cards like "red 2" and "blue 7" and "green 0".
     * The deck will only have one of each type of card. Each player is dealt a hand of n cards to start. The game starts with some player randomly placing a card. The next player
     * must play a card that can "beat" the previously played card. That is, red beats blue, blue beats green, and green beats red. For example, if the most recently placed card is "green 2",
     * the next player can either play any blue card ("blue beats green") or any green card with power > 2 (i.e. "green 7"). If the next player is unable to place a card, they must draw one from the deck.
     * The game continues until a player is out of cards, or the deck is empty, at which point the winner is the player with the least number of cards in their hand.
     *
     *
     * Part 1: Write some function that will deal you a hand of n cards
     * Part 2: Write some function that can determine if you can beat some given card using your hand of cards
     * Part 3: Write a `play` function which accepts a hand of cards as input and will play against the computer until one player wins or loses.
     */

    Deck deck;
    Hand[] hands;

    public Game(int numOfPlayers) {
        hands = new Hand[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            hands[i] = new Hand();
        }
    }
    void initializeDeck() {
        deck = new Deck();
        deck.shuffle();
    }
    boolean initialDeal() {
        // initially, deal 3 cards to each hand
        for (Hand hand : hands) {
            Card[] cards = deck.dealHand(3);
            if (cards == null) {
                return false;
            }
            for (Card card : cards) {
                hand.addCard(card);
            }
        }
        return true;
    }
    void play() {
        initializeDeck();

        if (!initialDeal()) {
            System.out.println("Error, out of cards.");
            return;
        }

        hands[0].placeCard();





    }

}





