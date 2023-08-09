package tcp_uno;

import org.junit.Before;
import org.junit.Test;
import tcp_uno.game.Card;
import tcp_uno.game.Deck;
import tcp_uno.game.DiscardPile;

import static junit.framework.TestCase.assertEquals;

public class DeckTest {
    private Deck deck;

    @Before
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void testNewDeckContainsAllCards() {
        int numCards = 0;
        while (!deck.isEmpty()) {
            deck.draw();
            numCards++;
        }
        assertEquals(108, numCards);
    }

    @Test
    public void testDeckRestockKeepsNumberOfCards() {
        DiscardPile discardPile = new DiscardPile(deck.draw());
        while (!deck.isEmpty()) {
            Card card = deck.draw();
            discardPile.putCard(card);
        }
        deck.restock(discardPile);

        int numCards = 0;
        while (!deck.isEmpty()) {
            deck.draw();
            numCards++;
        }
        assertEquals(107, numCards);
    }
}
