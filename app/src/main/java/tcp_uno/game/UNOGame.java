package tcp_uno.game;

import java.util.ArrayList;
import java.util.List;

public class UNOGame {
    private static final int NUM_PLAYERS = 4;
    private static final int TARGET_SCORE = 500;

    private final GameBoard gameBoard;
    private final List<Player> players = new ArrayList<>();
    private final List<GameAction> pendingActions = new ArrayList<>();

    public UNOGame() {
        for (int i = 0; i < NUM_PLAYERS; i++) {
            players.add(new Player());
        }
        gameBoard = new GameBoard(players);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void newRound() {
        for (Player player : players) {
            player.addScore(player.handScore());
        }
        gameBoard.reset();
    }

    public boolean gameOver() {
        for (Player player : players) {
            if (player.getScore() >= TARGET_SCORE) {
                return true;
            }
        }
        return false;
    }

    public List<GameAction> getAvailableActions() {
        List<GameAction> actions = new ArrayList<>();

        if (lastActionIsFinal())
            return actions;

        Player currentPlayer = gameBoard.getCurrentPlayer();

        boolean canChallengeDraw4 = checkForChallengeDraw4(actions, currentPlayer);

        if (canChallengeDraw4)
            return actions;

        boolean canPlayACard = checkForPlayCard(actions, currentPlayer);

        if (canPlayACard && currentPlayer.handSize() <= 2) {
            actions.add(new ScreamUNO(currentPlayer, gameBoard));
        }

        if (!gameBoard.currentPlayerDidDraw()) {
            actions.add(new DrawCards(currentPlayer, gameBoard, 1));
        }

        return actions;
    }

    private boolean lastActionIsFinal() {
        if (pendingActions.isEmpty())
            return false;
    
        GameAction lastAction = pendingActions.get(pendingActions.size() - 1);

        // No action can be performed in response to the following action types
        List<Class<? extends GameAction>> finalActions = List.of(
            ScreamUNO.class,
            ChallengeDraw4.class,
            DrawCards.class
        );

        return finalActions.stream().anyMatch(actionClass -> actionClass.isInstance(lastAction));
    }

    private boolean checkForChallengeDraw4(List<GameAction> actions, Player currentPlayer) {
        if (pendingActions.isEmpty())
            return false;

        GameAction lastAction = pendingActions.get(pendingActions.size() - 1);
        if (lastAction instanceof PlayCard) {
            Card card = ((PlayCard) lastAction).getCard();
            if (card.getValue() == CardValue.WILD_DRAW_4) {
                actions.add(new ChallengeDraw4(gameBoard.getNextPlayer(), gameBoard, currentPlayer));
                return true;
            }
        }
        return false;
    }

    private boolean checkForPlayCard(List<GameAction> actions, Player currentPlayer) {
        boolean canPlayACard = false;

        if (gameBoard.currentPlayerDidDraw()) {
            Card drawnCard = currentPlayer.getCard(currentPlayer.handSize() - 1);
            if (gameBoard.canBePlayed(drawnCard)) {
                canPlayACard = true;
                actions.addAll(PlayCard.getOptionsForCard(currentPlayer, gameBoard, drawnCard));
            }
        } else {
            for (Card card : currentPlayer.getHand()) {
                if (gameBoard.canBePlayed(card)) {
                    canPlayACard = true;
                    actions.addAll(PlayCard.getOptionsForCard(currentPlayer, gameBoard, card));
                }
            }
        }

        return canPlayACard;
    }

    public void addAction(GameAction action) {
        this.pendingActions.add(action);
    }

    public void executeActions() {
        for (GameAction action : pendingActions) {
            action.execute();
        }
    }
}
