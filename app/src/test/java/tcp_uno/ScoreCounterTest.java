package tcp_uno;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import tcp_uno.game.Card;
import tcp_uno.game.CardColor;
import tcp_uno.game.CardValue;
import tcp_uno.game.Player;
import tcp_uno.game.ScoreCounter;

public class ScoreCounterTest {
    
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    
    private ArrayList<Player> playerList;
    
    private final Card blue9 = new Card(CardColor.BLUE, CardValue.NUM_9);
    private final Card blue6 = new Card(CardColor.BLUE, CardValue.NUM_6);
    private final Card redSkip = new Card(CardColor.RED, CardValue.SKIP);
    private final Card yellowReverse = new Card(CardColor.YELLOW, CardValue.REVERSE);
    private final Card greenDraw2 = new Card(CardColor.GREEN, CardValue.DRAW_2);
    private final Card wildDraw4 = new Card(CardColor.BLACK, CardValue.WILD_DRAW_4);
    private final Card green0 = new Card(CardColor.GREEN, CardValue.NUM_0);
    
    public ScoreCounterTest() {
    }
    
    @Before
    public void setUpClass() {
        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        player4 = new Player();
        
        playerList = new ArrayList<>(4);
        
        //9 + 0 = 9
        player2.addToHand(blue9);
        player2.addToHand(green0);
        //50 + 20 = 70
        player3.addToHand(wildDraw4);
        player3.addToHand(redSkip);
        //6 + 20 = 26
        player4.addToHand(blue6);
        player4.addToHand(yellowReverse);
        
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
    }

    @Test
    public void testAddScore() {
        int scorePlayer1 = player1.getScore();
        int scorePlayer2 = player2.handScore();
        int scorePlayer3 = player3.handScore();
        int scorePlayer4 = player4.handScore();
        ScoreCounter score = new ScoreCounter(playerList);
        score.AddScore(0);
        int newScorePlayer1 = player1.getScore();
        
        
        assertEquals(scorePlayer1 + 
                     scorePlayer2 +
                     scorePlayer3 +
                     scorePlayer4, newScorePlayer1);
        
        
    }
    
}
