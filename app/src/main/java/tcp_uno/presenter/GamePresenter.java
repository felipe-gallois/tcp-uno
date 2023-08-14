package tcp_uno.presenter;

import tcp_uno.game.*;
import tcp_uno.view.GameView;

import java.util.ArrayList;
import java.util.List;

public class GamePresenter {

    private final static int HUMAN_PLAYER_INDEX = 0;
    private UNOGame game;
    private List<Bot> bots;

    long lastTime = System.currentTimeMillis();

    public void newGame() {
        game = new UNOGame();
        bots = new ArrayList<Bot>();
        for (int i = 1; i < 4; i++) {
            bots.add(new Bot());
        }
        game.newRound();
    }

    public void nextRound() {
        game.newRound();
    }

    public UNOGame getGame() {
        return game;
    }

    private boolean canPlayCard(int card) {
        Card c = game.getGameBoard().getPlayer(HUMAN_PLAYER_INDEX).getCard(card);
        List<GameAction> actions = game.getAvailableActions(HUMAN_PLAYER_INDEX);
        return actions.stream().anyMatch(action -> (action instanceof PlayCard) && ((PlayCard) action).getCard() == c);
    }

    public void playCard(int card) {
        if (!this.canPlayCard(card))
            return;

        Card c = game.getGameBoard().getPlayer(HUMAN_PLAYER_INDEX).getCard(card);
        if (c.playerSelectColor())
            game.addAction(new PlayCard(game.getGameBoard().getCurrentPlayer(), game.getGameBoard(), c, CardColor.RED));
        else
            game.addAction(new PlayCard(game.getGameBoard().getCurrentPlayer(), game.getGameBoard(), c));

        if (!canChallengeDraw4()) {
            game.executeActions();
        }
        lastTime = System.currentTimeMillis();
    }

    public boolean canChallengeDraw4() {
        List<GameAction> actions = game.getAvailableActions(HUMAN_PLAYER_INDEX);
        return actions.stream().anyMatch(action -> action instanceof ChallengeDraw4);
    }

    public void challengeDraw4() {
        game.addAction(new ChallengeDraw4(game.getGameBoard().getNextPlayer(), game.getGameBoard(), game.getGameBoard().getCurrentPlayer()));
        game.executeActions();
    }

    public void skipChallenge() {
        game.executeActions();
    }

    public boolean canDrawACard() {
        List<GameAction> actions = game.getAvailableActions(HUMAN_PLAYER_INDEX);
        return actions.stream().anyMatch(action -> action instanceof DrawCards);
    }

    public void drawCard() {
        game.addAction(new DrawCards(game.getGameBoard().getCurrentPlayer(), game.getGameBoard(), 1));
        game.executeActions();
        lastTime = System.currentTimeMillis();
    }

    public boolean canSkipTurn() {
        List<GameAction> actions = game.getAvailableActions(HUMAN_PLAYER_INDEX);
        return actions.stream().anyMatch(action -> action instanceof SkipTurn);
    }

    public void skipTurn() {
        game.addAction(new SkipTurn(game.getGameBoard().getCurrentPlayer(), game.getGameBoard()));
        game.executeActions();
        lastTime = System.currentTimeMillis();
    }

    public boolean canSayUno() {
        List<GameAction> actions = game.getAvailableActions(HUMAN_PLAYER_INDEX);
        return actions.stream().anyMatch(action -> action instanceof ScreamUNO);
    }

    public void callUNO() {
        game.addAction(new ScreamUNO(game.getGameBoard().getCurrentPlayer(), game.getGameBoard()));
        game.executeActions();
    }

    public void runBot() {
        if (game.getGameBoard().getCurrentPlayerIdx() == HUMAN_PLAYER_INDEX)
            return;

        GameAction action = botSelectAction(game.getGameBoard().getCurrentPlayerIdx());
        if (action == null) return;

        game.addAction(action);
        System.out.println("Game available actions: " + game.getAvailableActions());
        if (!game.nextPlayerCanRespond()) {
            game.executeActions();
            return;
        }

        if (game.getGameBoard().getNextPlayerIdx() != HUMAN_PLAYER_INDEX) {
            GameAction response = botSelectAction(game.getGameBoard().getNextPlayerIdx()-1);
            game.addAction(response);
            game.executeActions();
        }
    }

    private GameAction botSelectAction(int playerIdx) {
        List<GameAction> availableActions = game.getAvailableActions(playerIdx);
        return bots.get(playerIdx - 1).selectAction(availableActions);
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime > 1500) {
            lastTime = currentTime;
            if (game.getGameBoard().getCurrentPlayerIdx() != HUMAN_PLAYER_INDEX) {
                runBot();
            }
        }
    }
}
