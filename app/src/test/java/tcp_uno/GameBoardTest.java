package tcp_uno;

import org.junit.Before;
import org.junit.Test;
import tcp_uno.game.*;

import static org.junit.Assert.*;

public class GameBoardTest {
    private GameBoard board;
    private final static int NUM_PLAYERS = 4;
    private final static Card RED_SKIP = new Card(CardColor.RED, CardValue.SKIP);

    @Before
    public void setUp() {
        board = new GameBoard(NUM_PLAYERS);
    }

    @Test
    public void testGameBoardInitialState() {
        DiscardPile discardPile = board.getDiscardPile();
        assertNotNull(discardPile.top());
        assertEquals(0, board.getCurrentPlayerIdx());
        assertEquals(GameDirection.CLOCKWISE, board.getDirection());
    }

    @Test
    public void testReset() {
        // Applying some operations to change the board state
        board.addToDiscardPile(RED_SKIP);
        board.advancePlayer();
        board.reverse();

        board.reset();

        // Checking if the initial state was restores
        DiscardPile discardPile = board.getDiscardPile();
        assertNotNull(discardPile.top());
        assertEquals(0, board.getCurrentPlayerIdx());
        assertEquals(GameDirection.CLOCKWISE, board.getDirection());
    }

    @Test
    public void testAdvancePlayerClockwise() {
        assertEquals(0, board.getCurrentPlayerIdx());
        board.advancePlayer();
        assertEquals(1, board.getCurrentPlayerIdx());
        board.advancePlayer();
        assertEquals(2, board.getCurrentPlayerIdx());
        board.advancePlayer();
        assertEquals(3, board.getCurrentPlayerIdx());
        board.advancePlayer();
        assertEquals(0, board.getCurrentPlayerIdx());
    }

    @Test
    public void testReverse() {
        assertEquals(GameDirection.CLOCKWISE, board.getDirection());
        board.reverse();
        assertEquals(GameDirection.COUNTER_CLOCKWISE, board.getDirection());
    }

    @Test
    public void testAddCardToDiscardPile() {
        board.addToDiscardPile(RED_SKIP);
        DiscardPile discardPile = board.getDiscardPile();
        assertEquals(RED_SKIP, discardPile.top());
    }
}
