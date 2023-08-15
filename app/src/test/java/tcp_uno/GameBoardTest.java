package tcp_uno;

import org.junit.Before;
import org.junit.Test;
import tcp_uno.game.*;

import java.util.List;

import static org.junit.Assert.*;

public class GameBoardTest {
    private GameBoard board;
    private final static Card RED_SKIP = new Card(CardColor.RED, CardValue.SKIP);
    private final static Card WILD_DRAW_4 = new Card(CardColor.BLACK, CardValue.WILD_DRAW_4);
    private final static Card RED_0 = new Card(CardColor.RED, CardValue.NUM_0);
    private final static Card YELLOW_SKIP = new Card(CardColor.YELLOW, CardValue.SKIP);
    private final static Card GREEN_4 = new Card(CardColor.GREEN, CardValue.NUM_4);

    @Before
    public void setUp() {
        board = new GameBoard(List.of(new Player(), new Player(), new Player(), new Player()));
    }

    @Test
    public void testGameBoardInitialState() {
        assertNotNull(board.getDiscardPile().top());
        assertEquals(0, board.getCurrentPlayerIdx());
        assertEquals(GameDirection.CLOCKWISE, board.getDirection());
    }

    @Test
    public void testReset() {
        // Applying some operations to change the board state
        board.getDiscardPile().putCard(RED_SKIP);
        board.advancePlayer();
        board.reverse();

        board.reset();

        // Checking if the initial state was restored
        assertNotNull(board.getDiscardPile().top());
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
    public void testNextPlayerIndex() {
        assertEquals(1, board.getNextPlayerIdx());
        board.reverse();
        assertEquals(3, board.getNextPlayerIdx());
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
        board.getDiscardPile().putCard(RED_SKIP);
        assertEquals(RED_SKIP, board.getDiscardPile().top());
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
        board.getDiscardPile().putCard(RED_SKIP);
        assertEquals(board.getDiscardPile().top(), RED_SKIP);
    }

    @Test
    public void testCanBePlayed() {
        board.getDiscardPile().putCard(RED_SKIP);
        assertTrue(board.getDiscardPile().acceptsCard(WILD_DRAW_4));
        assertTrue(board.getDiscardPile().acceptsCard(RED_SKIP));
        assertTrue(board.getDiscardPile().acceptsCard(RED_0));
        assertTrue(board.getDiscardPile().acceptsCard(YELLOW_SKIP));
        assertFalse(board.getDiscardPile().acceptsCard(GREEN_4));

        board.getDiscardPile().putCard(WILD_DRAW_4, CardColor.RED);
        assertTrue(board.getDiscardPile().acceptsCard(WILD_DRAW_4));
        assertTrue(board.getDiscardPile().acceptsCard(RED_SKIP));
        assertTrue(board.getDiscardPile().acceptsCard(RED_0));
        assertFalse(board.getDiscardPile().acceptsCard(YELLOW_SKIP));
        assertFalse(board.getDiscardPile().acceptsCard(GREEN_4));
    }

    @Test
    public void testDealCards() {
        board.dealCards();

        assertEquals(7, board.getCurrentPlayer().handSize());
        assertEquals(7, board.getNextPlayer().handSize());
    }

    @Test
    public void testComputeScore() {
        board.getPlayer(0).addToHand(RED_SKIP);
        board.getPlayer(1).addToHand(YELLOW_SKIP);
        board.getPlayer(1).addToHand(WILD_DRAW_4);
        board.getPlayer(2).addToHand(RED_0);
        board.getPlayer(2).addToHand(GREEN_4);

        board.computeScores();
        assertEquals(94, board.getPlayer(3).getScore());
    }
}
