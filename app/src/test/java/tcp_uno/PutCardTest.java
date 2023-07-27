package tcp_uno;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import tcp_uno.game.Card;
import tcp_uno.game.CardColor;
import tcp_uno.game.CardValue;
import tcp_uno.game.GameBoard;
import tcp_uno.game.Player;
import tcp_uno.game.PutCard;

public class PutCardTest {
    private final Card yellow4 = new Card(CardColor.YELLOW, CardValue.NUM_4);
    private final Card yellow5 = new Card(CardColor.YELLOW, CardValue.NUM_5);
    private final Card blue4 = new Card(CardColor.BLUE, CardValue.NUM_4);
    
    private GameBoard board;
    private Player player;
    private PutCard putCard;

    @Before
    public void setUp() {
        board = new GameBoard(4);
        player = new Player();

        player.addToHand(yellow5);
        player.addToHand(blue4);
    }

    @Test
    public void testPutCardGoesToDiscardPile() {
        putCard = new PutCard(player, board, yellow5);
        putCard.execute();
        assertEquals(yellow5, board.getTopCard());
    }

    @Test
    public void testPlayerCannotPutCardNotInTheirHand() {
        putCard = new PutCard(player, board, yellow4);
        putCard.execute();
        assertNotEquals(yellow4, board.getTopCard());
    }
}
