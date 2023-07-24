package tcp_uno;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import tcp_uno.game.DrawCards;
import tcp_uno.game.GameAction;
import tcp_uno.game.GameBoard;
import tcp_uno.game.Player;

public class DrawCardsTest {
    private static GameBoard board;
    private static Player player;

    @BeforeClass
    public static void setUp() {
        board = new GameBoard(4);
        player = new Player();
    }

    @Test
    public void testDrawingTwoCardsWorksCorrectly() {
        GameAction action = new DrawCards(player, board, 2);
        action.execute();
        assertEquals(2, player.getHand().size());
    }
}
