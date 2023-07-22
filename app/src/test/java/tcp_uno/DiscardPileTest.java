package tcp_uno;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import tcp_uno.game.Card;
import tcp_uno.game.CardColor;
import tcp_uno.game.CardEffect;
import tcp_uno.game.DiscardPile;
import tcp_uno.game.InvalidDiscardException;

import java.util.ArrayList;

import static junit.framework.TestCase.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class DiscardPileTest {
    private final Card wild = new Card(CardColor.BLACK, CardEffect.WILD);
    private final Card yellow4 = new Card(CardColor.YELLOW, CardEffect.NUM_4);

    private final Card yellow5 = new Card(CardColor.YELLOW, CardEffect.NUM_5);
    private final Card blue4 = new Card(CardColor.BLUE, CardEffect.NUM_4);
    private final Card green3 = new Card(CardColor.GREEN, CardEffect.NUM_3);
    private final Card redDraw2 = new Card(CardColor.RED, CardEffect.DRAW_2);

    @Test
    public void testInitialCardOnTop() {
        DiscardPile discardPile = new DiscardPile(yellow4);
        assertEquals(discardPile.getTopCard(), yellow4);
    }

    @Test
    public void testCardPutIsOnTop() {
        DiscardPile discardPile = new DiscardPile(yellow4);
        discardPile.pushCard(yellow5);
        assertEquals(discardPile.getTopCard(), yellow5);
    }

    @Test
    public void testCanPlayCardWithSameNumber() {
        DiscardPile discardPile = new DiscardPile(yellow4);
        discardPile.pushCard(blue4);
    }

    @Test
    public void testCanPlayCardWithSameColor() {
        DiscardPile discardPile = new DiscardPile(yellow4);
        discardPile.pushCard(yellow5);
    }

    @Test(expected = InvalidDiscardException.class)
    public void testCannotPlayIncompatibleCard() {
        DiscardPile discardPile = new DiscardPile(yellow5);
        discardPile.pushCard(blue4);
    }

    @Test
    public void testCanPlayWildOnAnyColor() {
        DiscardPile discardPile;

        discardPile = new DiscardPile(yellow5);
        discardPile.pushCard(wild);

        discardPile = new DiscardPile(blue4);
        discardPile.pushCard(wild);

        discardPile = new DiscardPile(green3);
        discardPile.pushCard(wild);

        discardPile = new DiscardPile(redDraw2);
        discardPile.pushCard(wild);

        discardPile = new DiscardPile(wild);
        discardPile.pushCard(wild);
    }

    @Test
    public void testRetrieveRemovesAllCardsExceptTop() {
        DiscardPile discardPile = new DiscardPile(yellow5);
        discardPile.pushCard(yellow4);
        discardPile.pushCard(wild);
        discardPile.pushCard(redDraw2);

        ArrayList<Card> retrievedCards = discardPile.retrieveExcessCards();
        assertThat(retrievedCards, CoreMatchers.hasItems(yellow4, yellow5, wild));
        assertEquals(discardPile.getTopCard(), redDraw2);
    }

    @Test
    public void testFlushRemovesAllCards() {
        DiscardPile discardPile = new DiscardPile(yellow5);
        discardPile.pushCard(yellow4);
        discardPile.pushCard(wild);
        discardPile.pushCard(redDraw2);

        ArrayList<Card> retrievedCards = discardPile.flush();
        assertThat(retrievedCards, CoreMatchers.hasItems(yellow4, yellow5, wild, redDraw2));
    }
}
