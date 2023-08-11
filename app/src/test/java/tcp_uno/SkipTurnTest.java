package tcp_uno;

import org.junit.Before;
import org.junit.Test;
import tcp_uno.game.*;

import static org.junit.Assert.assertEquals;

public class SkipTurnTest {
    private GameBoard gameBoard;
    private Player currentPlayer;
    private Player nextPlayer;

    @Before
    public void setUp() {
        this.gameBoard = new GameBoard(4);
        this.currentPlayer = gameBoard.getCurrentPlayer();
        this.nextPlayer = gameBoard.getNextPlayer();
    }

    @Test
    public void testSkipTurn() {
        SkipTurn skipTurn = new SkipTurn(currentPlayer, gameBoard);
        skipTurn.execute();

        assertEquals(nextPlayer, gameBoard.getCurrentPlayer());
    }
}
