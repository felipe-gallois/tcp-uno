package tcp_uno;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import tcp_uno.game.CardColor;
import tcp_uno.game.CardValue;
import tcp_uno.game.Card;
import tcp_uno.game.Player;

public class PlayerTest {
    private static final CardColor CARD_COLOR = CardColor.GREEN;
    private static final CardValue CARD_VALUE = CardValue.NUM_4;
    private static final CardColor CARD_COLOR_2 = CardColor.RED;
    private static final CardValue CARD_VALUE_2 = CardValue.REVERSE;

    private static Player player;
    private static Card card_1;
    private static Card card_2;
    private static final Card YELLOW_4 = new Card(CardColor.YELLOW, CardValue.NUM_4);

    @BeforeClass
    public static void setup() {
        player = new Player();

        card_1 = new Card(CARD_COLOR, CARD_VALUE);
        card_2 = new Card(CARD_COLOR_2, CARD_VALUE_2);
        
        player.addToHand(card_1);
        player.addToHand(card_2);
    }

    @Test
    public void testHandScoreIsCalculatedCorrectly() {
        assertEquals(card_1.getScore() + card_2.getScore(), player.handScore());
    }

    @Test
    public void testSayUno() {
        assertFalse(player.saidUno());
        player.sayUno();
        assertTrue(player.saidUno());
        player.addToHand(YELLOW_4);
        assertFalse(player.saidUno());
    }
}
