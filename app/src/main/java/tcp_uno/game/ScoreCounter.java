
package tcp_uno.game;

import java.util.ArrayList;

public class ScoreCounter {
    
    ArrayList<Player> playerList;
    
    public ScoreCounter(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }
    
    public void AddScore(int indexWinner) {
        for (Player player : playerList) {
            playerList.get(indexWinner).addScore(player.handScore());
        }
    }    
}
