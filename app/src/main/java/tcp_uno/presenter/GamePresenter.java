package tcp_uno.presenter;

import tcp_uno.game.UNOGame;
import tcp_uno.view.GameView;

public class GamePresenter {

    private final GameView view;
    private UNOGame game;
//    private List<Bot> bots;

    public GamePresenter(GameView view) {
        this.view = view;
    }

    public void newGame() {
        game = new UNOGame();
        game.startGame();
    }

    public void nextRound() {
    }

    public UNOGame getGame() {
        return game;
    }

    public void playCard(int card) {
    }

    public void challangeDraw4() {
    }

    public void drawCard() {
    }

    public void runBot() {
    }

    public void callUNO() {
    }
}
