package tcp_uno;

import org.junit.Before;
import org.junit.Test;
import tcp_uno.game.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SkipTurnTest {
    private GameBoard gameBoard;
    private Player currentPlayer;
    private Player nextPlayer;

    @Before
    public void setUp() {
        this.currentPlayer = new Player();
        this.nextPlayer = new Player();
        this.gameBoard = new GameBoard(List.of(currentPlayer, nextPlayer, new Player(), new Player()));
    }

    @Test
    public void testSkipTurn() {
        SkipTurn skipTurn = new SkipTurn(currentPlayer, gameBoard);
        skipTurn.execute();

        assertEquals(nextPlayer, gameBoard.getCurrentPlayer());
    }
}
