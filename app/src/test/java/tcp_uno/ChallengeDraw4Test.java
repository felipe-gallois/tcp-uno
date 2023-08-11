package tcp_uno;

import org.junit.Before;
import org.junit.Test;
import tcp_uno.game.*;

import static org.junit.Assert.*;

public class ChallengeDraw4Test {
    private GameBoard gameBoard;
    private Player player;

    private static final Card YELLOW_4 = new Card(CardColor.YELLOW, CardValue.NUM_4);
    private static final Card YELLOW_5 = new Card(CardColor.YELLOW, CardValue.NUM_5);
    private static final Card GREEN_4 = new Card(CardColor.GREEN, CardValue.NUM_4);
    private static final Card WILD = new Card(CardColor.BLACK, CardValue.WILD);

    @Before
    public void setUp() {
        this.gameBoard = new GameBoard(4);
        this.gameBoard.addToDiscardPile(YELLOW_4);
        this.player = new Player();
    }

    @Test
    public void testChallengeSuccessful() {
        Player challengedPlayer = new Player();
        challengedPlayer.addToHand(YELLOW_5);

        int challengerHandSizeBefore = player.handSize();
        int challengedHandSizeBefore = challengedPlayer.handSize();

        ChallengeDraw4 challengeDraw4 = new ChallengeDraw4(player, gameBoard, challengedPlayer);
        challengeDraw4.execute();

        assertEquals(challengerHandSizeBefore, player.handSize());
        assertEquals(challengedHandSizeBefore + 4, challengedPlayer.handSize());
        assertTrue(gameBoard.wasChallengeSuccessful());
    }

    @Test
    public void testChallengeUnsuccessful() {
        Player challengedPlayer = new Player();
        challengedPlayer.addToHand(GREEN_4);

        int challengerHandSizeBefore = player.handSize();
        int challengedHandSizeBefore = challengedPlayer.handSize();

        ChallengeDraw4 challengeDraw4 = new ChallengeDraw4(player, gameBoard, challengedPlayer);
        challengeDraw4.execute();

        assertEquals(challengerHandSizeBefore + 2, player.handSize());
        assertEquals(challengedHandSizeBefore, challengedPlayer.handSize());
        assertFalse(gameBoard.wasChallengeSuccessful());
    }

    @Test
    public void testChallengeSucceedsWithWildOnTop() {
        Player challengedPlayer = new Player();
        challengedPlayer.addToHand(YELLOW_4);
        gameBoard.addToDiscardPile(WILD, CardColor.YELLOW);

        int challengerHandSizeBefore = player.handSize();
        int challengedHandSizeBefore = challengedPlayer.handSize();

        ChallengeDraw4 challengeDraw4 = new ChallengeDraw4(player, gameBoard, challengedPlayer);
        challengeDraw4.execute();

        assertEquals(challengerHandSizeBefore, player.handSize());
        assertEquals(challengedHandSizeBefore + 4, challengedPlayer.handSize());
        assertTrue(gameBoard.wasChallengeSuccessful());
    }
}
