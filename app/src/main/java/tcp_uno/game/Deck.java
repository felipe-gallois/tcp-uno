package tcp_uno.game;

import java.util.Collections;
import java.util.ArrayList;

/**
 * The class <code>Deck</code> represents a deck where cards can be drawn from
 */
public class Deck extends PileOfCards{
    /**
     * Creates a new empty <code>Deck</code> object
     */
    public Deck() {
        super();
    }

    /**
     * Pops a card from the top of the <code>Deck</code>
     * 
     * @return the card from the top of the <code>Deck</code>
     * 
     * @exception EmptyDeckException if the deck is empty
     */
    public Card drawCard() {
        if (cards.isEmpty())
            throw new EmptyDeckException();
        return cards.pop();
    }

    /**
     * Shuffles the <code>Deck</code>
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Pushes back a list of cards into the deck
     * 
     * @param cards the list of cards
     */
    public void restock(ArrayList<Card> cards) {
        for (Card card : cards) {
            this.cards.push(card);
        }
    }
}
