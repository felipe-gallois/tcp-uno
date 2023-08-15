package tcp_uno;

import org.junit.Before;
import org.junit.Test;
import tcp_uno.game.*;


import java.util.List;

import static org.junit.Assert.assertTrue;

public class ScreamUNOTest {
    private GameBoard gameBoard;
    private Player player;

    private final Card yellow4 = new Card(CardColor.YELLOW, CardValue.NUM_4);
    private final Card yellow5 = new Card(CardColor.YELLOW, CardValue.NUM_5);

    @Before
    public void setUp() {
        this.player = new Player();
        this.player.addToHand(yellow5);
        this.gameBoard = new GameBoard(List.of(player, new Player(), new Player(), new Player()));
        this.gameBoard.addToDiscardPile(yellow4);
    }

    @Test
    public void testScreamUno() {
        ScreamUNO screamUNO = new ScreamUNO(player, gameBoard);
        screamUNO.execute();
        assertTrue(player.saidUno());
    }
}
