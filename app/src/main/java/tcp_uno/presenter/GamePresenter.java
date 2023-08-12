package tcp_uno.presenter;

import tcp_uno.game.*;
import tcp_uno.view.GameView;

import java.util.ArrayList;
import java.util.List;

public class GamePresenter {

    private final GameView view;
    private UNOGame game;
    private List<Bot> bots;

    long lastTime = System.currentTimeMillis();

    public GamePresenter(GameView view) {
        this.view = view;
    }

    public void newGame() {
        game = new UNOGame();
        bots = new ArrayList<Bot>();
        for (int i = 1; i < 4; i++) {
            bots.add(new Bot());
        }
        game.newRound();

    }

    public void nextRound() {
    }

    public UNOGame getGame() {
        return game;
    }

    public void playCard(int card) {
        boolean isCurrentPlayer = game.getGameBoard().getCurrentPlayerIdx() == 0;
        if (!isCurrentPlayer) {
            return;
        }
        Card c =game.getGameBoard().getPlayerHand(0).get(card);
        boolean canBePlayed = game.getGameBoard().canBePlayed(c);

        if (!canBePlayed) {
            return;
        }

        if (c.getValue() == CardValue.WILD_DRAW_4 || c.getValue() == CardValue.WILD)
            game.addAction(new PlayCard(game.getGameBoard().getCurrentPlayer(), game.getGameBoard(), c, CardColor.RED));
        else
            game.addAction(new PlayCard(game.getGameBoard().getCurrentPlayer(), game.getGameBoard(), c));
        game.executeActions();
        lastTime = System.currentTimeMillis();
    }

    public void challangeDraw4() {
    }

    public void drawCard() {
        boolean isCurrentPlayer = game.getGameBoard().getCurrentPlayerIdx() == 0;
        if (!isCurrentPlayer) {
            return;
        }
        if (game.getGameBoard().currentPlayerDidDraw()) {
            game.addAction(new SkipTurn(game.getGameBoard().getCurrentPlayer(), game.getGameBoard()));
            return;
        }
        game.addAction(new DrawCards(game.getGameBoard().getCurrentPlayer(), game.getGameBoard(), 1));
        game.executeActions();
        lastTime = System.currentTimeMillis();

        if (game.getAvailableActions(game.getGameBoard().getCurrentPlayer()).isEmpty()) {
            game.addAction(new SkipTurn(game.getGameBoard().getCurrentPlayer(), game.getGameBoard()));
            game.executeActions();
        }
    }

    public void runBot() {
        if (game.getGameBoard().getCurrentPlayerIdx() != 0) {
            //bots.get(game.getGameBoard().getCurrentPlayerIdx() - 1).selectAction(game.getGameBoard());
            GameAction action =
            bots.get(game.getGameBoard().getCurrentPlayerIdx() - 1).selectAction(game.getAvailableActions(game.getGameBoard().getCurrentPlayer()));
            game.addAction(action);
            game.executeActions();
        }
    }

    public void callUNO() {
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime > 1500) {
            lastTime = currentTime;
            if (game.getGameBoard().getCurrentPlayerIdx() != 0) {
                runBot();
            }
        }
    }

}
