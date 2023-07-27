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
        assertNotNull(board.getTopCard());
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

        // Checking if the initial state was restored
        assertNotNull(board.getTopCard());
        assertEquals(0, board.getCurrentPlayerIdx());
        assertEquals(GameDirection.CLOCKWISE, board.getDirection());
    }

    @Test
    public void testAdvancePlayerClockwise() {
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
    public void testAdvancePlayerCounterClockwise() {
        board.reverse();
        board.advancePlayer();
        assertEquals(3, board.getCurrentPlayerIdx());
        board.advancePlayer();
        assertEquals(2, board.getCurrentPlayerIdx());
        board.advancePlayer();
        assertEquals(1, board.getCurrentPlayerIdx());
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
        assertEquals(RED_SKIP, board.getTopCard());
    }

    @Test
    public void testAddBlackCardToDiscardPileRequiresColor() {
        
    }

    @Test
    public void testPlayerMakeDraw() {
        Player player = new Player();

        board.makeDraw(player, 1);
        assertEquals(1, player.handSize());

        board.makeDraw(player, 2);
        assertEquals(3, player.handSize());
    }
}
