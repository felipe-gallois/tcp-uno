package tcp_uno.game;

import java.util.Stack;

/**
 * The class <code>PileOfCards</code> is a generic class that represents a
 * stack of cards
 */
public abstract class PileOfCards {
    protected final Stack<Card> cards;

    /**
     * Creates an empty <code>PileOfCards</code> object
     */
    public PileOfCards() {
        cards = new Stack<Card>();
    }

    /**
     * Checks if the <code>PileOfCards</code> does not contain any card
     * 
     * @return if the <code>PileOfCards</code> does not contain any card
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
