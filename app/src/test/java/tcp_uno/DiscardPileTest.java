package tcp_uno;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import tcp_uno.game.Card;
import tcp_uno.game.CardColor;
import tcp_uno.game.CardValue;
import tcp_uno.game.DiscardPile;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class DiscardPileTest {
    private final Card wild = new Card(CardColor.BLACK, CardValue.WILD);
    private final Card yellow4 = new Card(CardColor.YELLOW, CardValue.NUM_4);

    private final Card yellow5 = new Card(CardColor.YELLOW, CardValue.NUM_5);
    private final Card blue4 = new Card(CardColor.BLUE, CardValue.NUM_4);
    private final Card green3 = new Card(CardColor.GREEN, CardValue.NUM_3);
    private final Card redDraw2 = new Card(CardColor.RED, CardValue.DRAW_2);

    @Test
    public void testInitialCardOnTop() {
        DiscardPile discardPile = new DiscardPile(yellow4);
        assertEquals(discardPile.top(), yellow4);
    }

    @Test
    public void testCardPutIsOnTop() {
        DiscardPile discardPile = new DiscardPile(yellow4);
        discardPile.putCard(yellow5);
        assertEquals(discardPile.top(), yellow5);
    }

    @Test
    public void testCanPlayCardWithSameNumber() {
        DiscardPile discardPile = new DiscardPile(yellow4);
        assertTrue(discardPile.canPlayCard(blue4));
    }

    @Test
    public void testCanPlayCardWithSameColor() {
        DiscardPile discardPile = new DiscardPile(yellow4);
        assertTrue(discardPile.canPlayCard(yellow5));
    }

    @Test
    public void testCannotPlayIncompatibleCard() {
        DiscardPile discardPile = new DiscardPile(yellow5);
        assertFalse(discardPile.canPlayCard(blue4));
    }

    @Test
    public void testCanPlayWildOnAnyColor() {
        DiscardPile discardPile;

        discardPile = new DiscardPile(yellow5);
        assertTrue(discardPile.canPlayCard(wild));

        discardPile = new DiscardPile(blue4);
        assertTrue(discardPile.canPlayCard(wild));

        discardPile = new DiscardPile(green3);
        assertTrue(discardPile.canPlayCard(wild));

        discardPile = new DiscardPile(redDraw2);
        assertTrue(discardPile.canPlayCard(wild));

        discardPile = new DiscardPile(wild);
        assertTrue(discardPile.canPlayCard(wild));
    }

    @Test
    public void testCanPlayCardOnWildOnlyIfCurrentColorMatches() {
        DiscardPile discardPile = new DiscardPile(wild);
        discardPile.setCurrentColor(CardColor.RED);

        assertTrue(discardPile.canPlayCard(redDraw2));
        assertFalse(discardPile.canPlayCard(blue4));
    }

    @Test
    public void testRetrieveRemovesAllCardsExceptTop() {
        DiscardPile discardPile = new DiscardPile(yellow5);
        discardPile.putCard(yellow4);
        discardPile.putCard(wild);
        discardPile.setCurrentColor(CardColor.RED);
        discardPile.putCard(redDraw2);

        List<Card> retrievedCards = discardPile.retrieve();
        assertThat(retrievedCards, CoreMatchers.hasItems(yellow4, yellow5, wild));
        assertEquals(discardPile.top(), redDraw2);
    }

    @Test
    public void testFlushRemovesAllCards() {
        DiscardPile discardPile = new DiscardPile(yellow5);
        discardPile.putCard(yellow4);
        discardPile.putCard(wild);
        discardPile.setCurrentColor(CardColor.RED);
        discardPile.putCard(redDraw2);

        List<Card> retrievedCards = discardPile.flush();
        assertThat(retrievedCards, CoreMatchers.hasItems(yellow4, yellow5, wild, redDraw2));
    }
}
