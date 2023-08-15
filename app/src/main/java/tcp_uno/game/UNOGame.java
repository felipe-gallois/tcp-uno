package tcp_uno.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UNOGame {
    private static final int NUM_PLAYERS = 4;
    private static final int TARGET_SCORE = 500;

    private final GameBoard gameBoard;
    private final List<Player> players = new ArrayList<>();
    private final Stack<GameAction> pendingActions = new Stack<>();

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
        gameBoard.computeScores();
        gameBoard.reset();
        gameBoard.dealCards();
    }

    public boolean isRoundOver() {
        for (int i = 0; i < NUM_PLAYERS; i++) {
            if (gameBoard.getPlayer(i).handSize() == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean gameOver() {
        for (Player player : players) {
            if (player.getScore() >= TARGET_SCORE) {
                return true;
            }
        }
        return false;
    }

    public List<GameAction> getAvailableActions(int playerIdx) {
        Player player = players.get(playerIdx);
        List<GameAction> actions = new ArrayList<>();

        // Some actions cannot be responded by any player
        if (lastActionCannotBeResponded())
            return actions;


        if (player == gameBoard.getCurrentPlayer()) {
            // If the next player can challenge a Draw 4, the current player cannot do anything at this moment
            if (checkForChallengeDraw4())
                return actions;

            boolean canPlayACard = checkForPlayCard(actions, player);

            if (canPlayACard && player.handSize() <= 2 && !player.saidUno()) {
                actions.add(new ScreamUNO(player, gameBoard));
            }
            if (gameBoard.currentPlayerDidDraw()) {
                actions.add(new SkipTurn(player, gameBoard));
            } else {
                actions.add(new DrawCards(player, gameBoard, 1));
            }
            return actions;
        } else if (player == gameBoard.getNextPlayer()) {
            if (checkForChallengeDraw4()) {
                actions.add(new ChallengeDraw4(player, gameBoard, gameBoard.getCurrentPlayer()));
            }
            return actions;
        }

        return actions;
    }

    private boolean lastActionCannotBeResponded() {
        if (pendingActions.isEmpty())
            return false;

        GameAction lastAction = pendingActions.get(pendingActions.size() - 1);

        // No action can be performed in response to the following action types
        List<Class<? extends GameAction>> finalActions = List.of(
                ScreamUNO.class,
                ChallengeDraw4.class,
                DrawCards.class,
                SkipTurn.class
        );

        return finalActions.stream().anyMatch(actionClass -> actionClass.isInstance(lastAction));
    }

    private boolean checkForChallengeDraw4() {
        if (pendingActions.isEmpty())
            return false;

        GameAction lastAction = pendingActions.get(pendingActions.size() - 1);
        if (lastAction instanceof PlayCard) {
            Card card = ((PlayCard) lastAction).getCard();
            return card.getValue() == CardValue.WILD_DRAW_4;
        }
        return false;
    }

    private boolean checkForPlayCard(List<GameAction> actions, Player currentPlayer) {
        boolean canPlayACard = false;

        if (gameBoard.currentPlayerDidDraw()) {
            Card drawnCard = currentPlayer.getCard(currentPlayer.handSize() - 1);
            if (gameBoard.getDiscardPile().acceptsCard(drawnCard)) {
                canPlayACard = true;
                actions.addAll(PlayCard.getOptionsForCard(currentPlayer, gameBoard, drawnCard));
            }
        } else {
            for (Card card : currentPlayer.getHand()) {
                if (gameBoard.getDiscardPile().acceptsCard(card)) {
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
        while (!pendingActions.isEmpty()) {
            pendingActions.pop().execute();
        }
    }

    public Card getTopCard() {
        for (GameAction action : pendingActions) {
            if (action instanceof PlayCard) {
                return ((PlayCard) action).getCard();
            }
        }
        return gameBoard.getDiscardPile().top();
    }
}
