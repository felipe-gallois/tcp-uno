package tcp_uno.presenter;

import tcp_uno.game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GamePresenter {

    private final static int HUMAN_PLAYER_INDEX = 0;
    private UNOGame game;
    private GameBoard gameBoard;
    private Bot bot;

    private int prevScore;


    public void newGame() {
        game = new UNOGame();
        gameBoard = game.getGameBoard();
        bot = new Bot();
        game.newRound();
    }

    public void nextRound() {
        prevScore = gameBoard.getPlayer(HUMAN_PLAYER_INDEX).getScore();
        game.newRound();
    }

    public UNOGame getGame() {
        return game;
    }

    private boolean canPlayCard(int card) {
        Card c = gameBoard.getPlayer(HUMAN_PLAYER_INDEX).getCard(card);
        List<GameAction> actions = game.getAvailableActions(HUMAN_PLAYER_INDEX);
        return actions.stream().anyMatch(action -> (action instanceof PlayCard) && ((PlayCard) action).getCard() == c);
    }

    public void playCard(int card) {
        if (!this.canPlayCard(card))
            return;

        Card c = gameBoard.getPlayer(HUMAN_PLAYER_INDEX).getCard(card);
        if (c.playerSelectColor())
            game.addAction(new PlayCard(gameBoard.getCurrentPlayer(), gameBoard, c, CardColor.RED));
        else
            game.addAction(new PlayCard(gameBoard.getCurrentPlayer(), gameBoard, c));

        if (!canChallengeDraw4()) {
            game.executeActions();
        }
    }

    public List<Card> getHand() {
        return gameBoard.getPlayer(HUMAN_PLAYER_INDEX).getHand();
    }

    public boolean canChallengeDraw4() {
        List<GameAction> actions = game.getAvailableActions(HUMAN_PLAYER_INDEX);
        return actions.stream().anyMatch(action -> action instanceof ChallengeDraw4);
    }

    public void challengeDraw4() {
        game.addAction(new ChallengeDraw4(gameBoard.getNextPlayer(), gameBoard, gameBoard.getCurrentPlayer()));
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
        game.addAction(new DrawCards(gameBoard.getCurrentPlayer(), gameBoard, 1));
        game.executeActions();
    }

    public boolean canSkipTurn() {
        List<GameAction> actions = game.getAvailableActions(HUMAN_PLAYER_INDEX);
        return actions.stream().anyMatch(action -> action instanceof SkipTurn);
    }

    public void skipTurn() {
        game.addAction(new SkipTurn(gameBoard.getCurrentPlayer(), gameBoard));
        game.executeActions();
    }

    public boolean canSayUno() {
        List<GameAction> actions = game.getAvailableActions(HUMAN_PLAYER_INDEX);
        return actions.stream().anyMatch(action -> action instanceof ScreamUNO);
    }

    public void callUNO() {
        game.addAction(new ScreamUNO(gameBoard.getCurrentPlayer(), gameBoard));
        game.executeActions();
    }

    public void runBot() {
        if (gameBoard.getCurrentPlayerIdx() == HUMAN_PLAYER_INDEX)
            return;

        Optional<GameAction> action = botSelectAction(gameBoard.getCurrentPlayerIdx());
        if (action.isEmpty()) return;

        game.addAction(action.get());
        if (!game.nextPlayerCanRespond()) {
            game.executeActions();
            return;
        }

        if (gameBoard.getNextPlayerIdx() != HUMAN_PLAYER_INDEX) {
            Optional<GameAction> response = botSelectAction(gameBoard.getNextPlayerIdx()-1);
            response.ifPresent(gameAction -> game.addAction(gameAction));
            game.executeActions();
        }
    }

    private Optional<GameAction> botSelectAction(int playerIdx) {
        List<GameAction> availableActions = game.getAvailableActions(playerIdx);
        return bot.selectAction(availableActions);
    }

    public void update() {
        if (gameBoard.getCurrentPlayerIdx() != HUMAN_PLAYER_INDEX) {
            runBot();
        }
    }

    public int getCurrentScore() {
        return gameBoard.getPlayer(HUMAN_PLAYER_INDEX).getScore();
    }

    public int getPrevScore() {
        return prevScore;
    }

    public boolean playerWonRound() {
        return gameBoard.getPlayer(HUMAN_PLAYER_INDEX).handSize() == 0;
    }
}
