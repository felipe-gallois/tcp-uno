package tcp_uno.game;

import java.util.ArrayList;

/**
 * The <code>DiscardPile</code> class represents a pile of cards where cards
 * are discarded
 */
public class DiscardPile extends PileOfCards {
    /**
    * Creates an empty <code>DiscardPile</code>
    *
    * @param firstCard the first card to be pushed into the pile
    */
    public DiscardPile(Card firstCard) {
        super();
        pushCard(firstCard);
    }

    /**
     * Pushes a card to the top of the pile
     * 
     * @param card the card to be pushed
     * 
     * @exception InvalidDiscardException if the card to be pushed is not a
     * valid play
     */
    public void pushCard(Card card) {
        if (canPlayCard(card)) {
            cards.push(card);
        } else {
            throw new InvalidDiscardException();
        }
    }

    /**
     * Retrieves all the cards from the pile, except the topmost one
     * 
     * @return a list of cards from the pile
     * 
     * @exception EmptyDiscardPileException if pile is empty
     */
    public ArrayList<Card> retrieveExcessCards() {
        if (cards.empty()) {
            throw new EmptyDiscardPileException();
        }

        Card topCard = cards.pop();
        ArrayList<Card> cards = this.flush();
        this.pushCard(topCard);
        return cards;
    }

    /**
     * Gets the card at the top of the <code>DiscardPile</code>
     * 
     * @return the card at the top of the <code>DiscardPile</code>
     * 
     * @exception EmptyDiscardPileException if pile is empty
     */
    public Card getTopCard() {
        if (cards.empty()) {
            throw new EmptyDiscardPileException();
        }

        return cards.peek();
    }

    /**
     * Flushes all the cards from the pile
     * 
     * @return a list of flushed cards
     */
    public ArrayList<Card> flush() {
        ArrayList<Card> pileCards = new ArrayList<>();
        while (!isEmpty()) {
            pileCards.add(cards.pop());
        }
        return pileCards;
    }

    private boolean canPlayCard(Card card) {
        Card topCard = cards.peek();

        boolean colorOk = card.getColor() == topCard.getColor() || card.getColor() == CardColor.BLACK;
        boolean effectOk = card.getEffect() == topCard.getEffect();

        return colorOk || effectOk;
    }
}
