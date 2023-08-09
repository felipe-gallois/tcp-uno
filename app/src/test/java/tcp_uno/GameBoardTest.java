package tcp_uno;

import org.junit.Before;
import org.junit.Test;
import tcp_uno.game.*;

import static org.junit.Assert.*;

public class GameBoardTest {
    private GameBoard board;
    private final int NUM_PLAYERS = 4;
    private final Card RED_SKIP = new Card(CardColor.RED, CardValue.SKIP);
    private final Card WILD_DRAW_4 = new Card(CardColor.BLACK, CardValue.WILD_DRAW_4);
    private final Card RED_0 = new Card(CardColor.RED, CardValue.NUM_0);
    private final Card YELLOW_SKIP = new Card(CardColor.YELLOW, CardValue.SKIP);
    private final Card GREEN_4 = new Card(CardColor.GREEN, CardValue.NUM_4);

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

    @Test(expected = RequiresColorChoiceException.class)
    public void testAddBlackCardToDiscardPileRequiresColor() {
        board.addToDiscardPile(WILD_DRAW_4);
    }

    @Test
    public void testPlayerMakeDraw() {
        Player player = new Player();

        board.makeDraw(player, 1);
        assertEquals(1, player.handSize());

        board.makeDraw(player, 2);
        assertEquals(3, player.handSize());
    }

    @Test
    public void testCurrentPlayerDidDraw() {
        Player player = board.getCurrentPlayer();
        assertFalse(board.currentPlayerDidDraw());
        
        board.makeDraw(player, 1);
        assertTrue(board.currentPlayerDidDraw());

        board.advancePlayer();
        assertFalse(board.currentPlayerDidDraw());
    }

    @Test
    public void testGetTopCard() {
        board.addToDiscardPile(RED_SKIP);
        assertEquals(board.getTopCard(), RED_SKIP);
    }

    @Test
    public void testCanBePlayed() {
        board.addToDiscardPile(RED_SKIP);
        assertTrue(board.canBePlayed(WILD_DRAW_4));
        assertTrue(board.canBePlayed(RED_SKIP));
        assertTrue(board.canBePlayed(RED_0));
        assertTrue(board.canBePlayed(YELLOW_SKIP));
        assertFalse(board.canBePlayed(GREEN_4));

        board.addToDiscardPile(WILD_DRAW_4, CardColor.RED);
        assertTrue(board.canBePlayed(WILD_DRAW_4));
        assertTrue(board.canBePlayed(RED_SKIP));
        assertTrue(board.canBePlayed(RED_0));
        assertFalse(board.canBePlayed(YELLOW_SKIP));
        assertFalse(board.canBePlayed(GREEN_4));
    }
}
