package tcp_uno;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tcp_uno.game.Card;
import tcp_uno.game.CardColor;
import tcp_uno.game.CardValue;
import tcp_uno.game.ChallengeDraw4;
import tcp_uno.game.DrawCards;
import tcp_uno.game.GameAction;
import tcp_uno.game.GameBoard;
import tcp_uno.game.PlayCard;
import tcp_uno.game.Player;
import tcp_uno.game.ScreamUNO;
import tcp_uno.game.UNOGame;

public class UNOGameTest {
    private Card WILD = new Card(CardColor.BLACK, CardValue.WILD);
    private Card WILD_DRAW_4 = new Card(CardColor.BLACK, CardValue.WILD_DRAW_4);
    private Card RED_0 = new Card(CardColor.RED, CardValue.NUM_0);
    private Card RED_1 = new Card(CardColor.RED, CardValue.NUM_1);
    private Card YELLOW_0 = new Card(CardColor.YELLOW, CardValue.NUM_0);
    private Card GREEN_SKIP = new Card(CardColor.GREEN, CardValue.SKIP);
    private Card BLUE_6 = new Card(CardColor.BLUE, CardValue.NUM_6);
    private Card RED_REVERSE = new Card(CardColor.RED, CardValue.REVERSE);

    private UNOGame game;
    private GameBoard gameBoard;
    private Player player;

    @Before
    public void setUp() {
        game = new UNOGame();
        gameBoard = game.getGameBoard();
        gameBoard.addToDiscardPile(RED_0);
        player = gameBoard.getCurrentPlayer();
    }

    @Test
    public void testGameCommonScenario() {
        player.addToHand(WILD);
        player.addToHand(RED_1);
        player.addToHand(YELLOW_0);
        player.addToHand(GREEN_SKIP);

        List<GameAction> availableActions = game.getAvailableActions();

        assertEquals(availableActions.size(), 7);
        assertEquals(
                6,
                availableActions
                        .stream()
                        .filter(action -> action instanceof PlayCard)
                        .count());
        assertEquals(
                1,
                availableActions
                        .stream()
                        .filter(action -> action instanceof DrawCards)
                        .count());
    }

    @Test
    public void testPlaySequence() {
        player.addToHand(WILD);
        player.addToHand(RED_1);
        player.addToHand(YELLOW_0);
        
        Player nextPlayer = gameBoard.getNextPlayer();
        nextPlayer.addToHand(BLUE_6);
        nextPlayer.addToHand(GREEN_SKIP);
        nextPlayer.addToHand(RED_REVERSE);

        game.addAction(new PlayCard(player, gameBoard, RED_1));
        game.executeActions();

        assertEquals(nextPlayer, gameBoard.getCurrentPlayer());
    }

    @Test
    public void testGameSayUno() {
        player.addToHand(WILD);
        player.addToHand(RED_1);

        List<GameAction> availableActions = game.getAvailableActions();

        assertEquals(availableActions.size(), 7);
        assertEquals(
                5,
                availableActions
                        .stream()
                        .filter(action -> action instanceof PlayCard)
                        .count());
        assertEquals(
                1,
                availableActions
                        .stream()
                        .filter(action -> action instanceof DrawCards)
                        .count());
        assertEquals(
                1,
                availableActions
                        .stream()
                        .filter(action -> action instanceof ScreamUNO)
                        .count());
    }

    @Test
    public void testPlayerActionsAfterDraw() {
        player.addToHand(WILD);
        player.addToHand(RED_1);
        player.addToHand(GREEN_SKIP);

        GameAction drawCards = new DrawCards(player, gameBoard, 1);
        game.addAction(drawCards);
        game.executeActions();

        List<GameAction> availableActions = game.getAvailableActions();
        
        // A player cannot draw cards if it has already drawn a card in their turn
        assertEquals(
                0,
                availableActions
                        .stream()
                        .filter(action -> action instanceof DrawCards)
                        .count());
        
        // After drawing, a player can only play the drawn card (if it is playable)
        // This means that the number of different cards it can play after drawing a card
        // must be at most 1.
        long numPlayableCards = availableActions
                .stream()
                .filter(action -> action instanceof PlayCard)
                .map(action -> ((PlayCard) action).getCard())
                .distinct()
                .count();
        
        assertTrue(1 >= numPlayableCards);
    }

    @Test
    public void testPlayerCanChallengeDraw4() {
        GameAction playDraw4 = new PlayCard(player, gameBoard, WILD_DRAW_4, CardColor.RED);
        game.addAction(playDraw4);

        List<GameAction> availableActions = game.getAvailableActions();
        
        assertEquals(1, availableActions.size());
        assertTrue(availableActions.get(0) instanceof ChallengeDraw4);
    }

    @Test
    public void testGameOver() {
        assertFalse(game.gameOver());
        player.addScore(500);
        assertTrue(game.gameOver());
    }
}
