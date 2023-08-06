package tcp_uno;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tcp_uno.game.*;


public class PlayCardTest {
    private GameBoard gameboard;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private final Card blue9 = new Card(CardColor.BLUE, CardValue.NUM_9);
    private final Card blue6 = new Card(CardColor.BLUE, CardValue.NUM_6);
    private final Card redSkip = new Card(CardColor.RED, CardValue.SKIP);
    private final Card yellowReverse = new Card(CardColor.YELLOW, CardValue.REVERSE);
    private final Card greenDraw2 = new Card(CardColor.GREEN, CardValue.DRAW_2);
    private final Card wildDraw4 = new Card(CardColor.BLACK, CardValue.WILD_DRAW_4);

    
    @Before
    public void setUp() {
        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        player4 = new Player();

        gameboard = new GameBoard(List.of(player1, player2, player3, player4));
        player1.addToHand(blue9);
        player1.addToHand(blue6);
        player1.addToHand(redSkip);
        player1.addToHand(yellowReverse);
        player1.addToHand(greenDraw2);
        player1.addToHand(wildDraw4);
    }

    @Test
    public void testPlayNumericCard() {
        int numCardsPlayer1 = player1.handSize();
    
        PlayCard playCard = new PlayCard(player1, gameboard, blue9);
        playCard.execute();

        assertEquals(numCardsPlayer1 - 1, player1.handSize());
        assertEquals(0, player2.handSize());
        assertEquals(0, player3.handSize());
        assertEquals(0, player4.handSize());
        assertEquals(player2, gameboard.getCurrentPlayer());
        assertEquals(blue9, gameboard.getTopCard());
    }

    @Test
    public void testPlaySkipCard() {
        int numCardsPlayer1 = player1.handSize();
    
        PlayCard playCard = new PlayCard(player1, gameboard, redSkip);
        playCard.execute();

        assertEquals(numCardsPlayer1 - 1, player1.handSize());
        assertEquals(0, player2.handSize());
        assertEquals(0, player3.handSize());
        assertEquals(0, player4.handSize());
        assertEquals(player3, gameboard.getCurrentPlayer());
        assertEquals(redSkip, gameboard.getTopCard());
    }

    @Test
    public void testPlayReverseCard() {
        int numCardsPlayer1 = player1.handSize();
    
        PlayCard playCard = new PlayCard(player1, gameboard, yellowReverse);
        playCard.execute();

        assertEquals(numCardsPlayer1 - 1, player1.handSize());
        assertEquals(0, player2.handSize());
        assertEquals(0, player3.handSize());
        assertEquals(0, player4.handSize());
        assertEquals(player4, gameboard.getCurrentPlayer());
        assertEquals(yellowReverse, gameboard.getTopCard());
    }

    @Test
    public void testPlayDraw2() {
        int numCardsPlayer1 = player1.handSize();
    
        PlayCard playCard = new PlayCard(player1, gameboard, greenDraw2);
        playCard.execute();

        assertEquals(numCardsPlayer1 - 1, player1.handSize());
        assertEquals(2, player2.handSize());
        assertEquals(0, player3.handSize());
        assertEquals(0, player4.handSize());
        assertEquals(player3, gameboard.getCurrentPlayer());
        assertEquals(greenDraw2, gameboard.getTopCard());
    }

    @Test
    public void testPlayWildDraw4() {
        int numCardsPlayer1 = player1.handSize();
    
        PlayCard playCard = new PlayCard(player1, gameboard, wildDraw4, CardColor.YELLOW);
        playCard.execute();

        assertEquals(numCardsPlayer1 - 1, player1.handSize());
        assertEquals(4, player2.handSize());
        assertEquals(0, player3.handSize());
        assertEquals(0, player4.handSize());
        assertEquals(player3, gameboard.getCurrentPlayer());
        assertEquals(wildDraw4, gameboard.getTopCard());
        assertEquals(CardColor.YELLOW, gameboard.getCurrentColor());
    }

    @Test
    public void testPlayWildDraw4AfterSuccessfulChallenge() {
        int numCardsPlayer1 = player1.handSize();
        gameboard.setChallengeSuccessful(true);

        PlayCard playCard = new PlayCard(player1, gameboard, wildDraw4, CardColor.YELLOW);
        playCard.execute();

        assertEquals(numCardsPlayer1 - 1, player1.handSize());
        assertEquals(0, player2.handSize());
        assertEquals(0, player3.handSize());
        assertEquals(0, player4.handSize());
        assertEquals(player2, gameboard.getCurrentPlayer());
        assertEquals(wildDraw4, gameboard.getTopCard());
        assertEquals(CardColor.YELLOW, gameboard.getCurrentColor());
        assertFalse(gameboard.wasChallengeSuccessfull());
    }
}
