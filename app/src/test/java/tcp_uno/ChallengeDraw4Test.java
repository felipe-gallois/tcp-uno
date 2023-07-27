package tcp_uno;

import org.junit.Before;
import org.junit.Test;
import tcp_uno.game.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChallengeDraw4Test {
    private GameBoard gameBoard;
    private Player player;

    private final Card yellow4 = new Card(CardColor.YELLOW, CardValue.NUM_4);
    private final Card yellow5 = new Card(CardColor.YELLOW, CardValue.NUM_5);
    private final Card green4 = new Card(CardColor.GREEN, CardValue.NUM_4);
    private final Card wild = new Card(CardColor.BLACK, CardValue.WILD);

    @Before
    public void setUp() {
        this.gameBoard = new GameBoard(4);
        this.gameBoard.addToDiscardPile(yellow4);
        this.player = new Player();
    }

    @Test
    public void testChallengeSuccessful() {
        Player challengedPlayer = new Player(List.of(yellow5));

        int challengerHandSizeBefore = player.handSize();
        int challengedHandSizeBefore = challengedPlayer.handSize();

        ChallengeDraw4 challengeDraw4 = new ChallengeDraw4(player, gameBoard, challengedPlayer);
        challengeDraw4.execute();

        assertEquals(challengerHandSizeBefore, player.handSize());
        assertEquals(challengedHandSizeBefore + 4, challengedPlayer.handSize());
    }

    @Test
    public void testChallengeUnsuccessful() {
        Player challengedPlayer = new Player(List.of(green4));

        int challengerHandSizeBefore = player.handSize();
        int challengedHandSizeBefore = challengedPlayer.handSize();

        ChallengeDraw4 challengeDraw4 = new ChallengeDraw4(player, gameBoard, challengedPlayer);
        challengeDraw4.execute();

        assertEquals(challengerHandSizeBefore + 2, player.handSize());
        assertEquals(challengedHandSizeBefore, challengedPlayer.handSize());
    }

    @Test
    public void testChallengeSucceedsWithWildOnTop() {
        Player challengedPlayer = new Player(List.of(yellow4));
        gameBoard.addToDiscardPile(wild, CardColor.YELLOW);

        int challengerHandSizeBefore = player.handSize();
        int challengedHandSizeBefore = challengedPlayer.handSize();

        ChallengeDraw4 challengeDraw4 = new ChallengeDraw4(player, gameBoard, challengedPlayer);
        challengeDraw4.execute();

        assertEquals(challengerHandSizeBefore, player.handSize());
        assertEquals(challengedHandSizeBefore + 4, challengedPlayer.handSize());
    }
}
