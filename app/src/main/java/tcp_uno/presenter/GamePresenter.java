package tcp_uno.presenter;

import tcp_uno.game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GamePresenter {

    private final static int HUMAN_PLAYER_INDEX = 0;
    private UNOGame game;
    private GameBoard gameBoard;
    private List<Bot> bots;

    private int prevScore;

    private long lastTime = System.currentTimeMillis();

    public void newGame() {
        game = new UNOGame();
        gameBoard = game.getGameBoard();
        bots = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            bots.add(new Bot());
        }
        game.newRound();
    }
    
    public void updateScores() {
        prevScore = gameBoard.getPlayer(HUMAN_PLAYER_INDEX).getScore();
        gameBoard.computeScores();
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

    public List<Card> getHand() {
        return game.getGameBoard().getPlayer(HUMAN_PLAYER_INDEX).getHand();
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

        Optional<GameAction> action = botSelectAction(game.getGameBoard().getCurrentPlayerIdx());
        if (action.isEmpty()) return;

        game.addAction(action.get());
        if (!game.nextPlayerCanRespond()) {
            game.executeActions();
            return;
        }

        if (game.getGameBoard().getNextPlayerIdx() != HUMAN_PLAYER_INDEX) {
            Optional<GameAction> response = botSelectAction(game.getGameBoard().getNextPlayerIdx()-1);
            response.ifPresent(gameAction -> game.addAction(gameAction));
            game.executeActions();
        }
    }

    private Optional<GameAction> botSelectAction(int playerIdx) {
        List<GameAction> availableActions = game.getAvailableActions(playerIdx);
        return bots.get(playerIdx - 1).selectAction(availableActions);
    }

    public void update() {
        if (roundEnded()) {
            this.prevScore = game.getGameBoard().getPlayer(HUMAN_PLAYER_INDEX).getScore();
            game.newRound();
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime > 1500) {
            lastTime = currentTime;
            if (game.getGameBoard().getCurrentPlayerIdx() != HUMAN_PLAYER_INDEX) {
                runBot();
            }
        }
    }

    public boolean gameEnded() {
        return game.gameOver();
    }

    public boolean roundEnded() {
        return game.isRoundOver();
    }

    public int getHandScore() {
        return game.getGameBoard().getPlayer(HUMAN_PLAYER_INDEX).handScore();
    }

    public int getPrevScore() {
        return game.getGameBoard().getPlayer(HUMAN_PLAYER_INDEX).getScore();
    }

    public int getTotalScore() {
        Player hp =  game.getGameBoard().getPlayer(HUMAN_PLAYER_INDEX);
        return hp.getScore() + hp.handScore();
    }

    public void cheat() {
        game.getGameBoard().getPlayer(HUMAN_PLAYER_INDEX).getHand().clear();
    }

    public boolean playerWonRound() {
        return game.getGameBoard().getPlayer(HUMAN_PLAYER_INDEX).handSize() == 0;
    }
}
