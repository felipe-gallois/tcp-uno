package tcp_uno.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameBoard {
    private final static int INITIAL_CARDS_PER_PLAYER = 7;

    private Deck deck;
    private DiscardPile discardPile;
    private GameDirection direction;
    private int currentPlayerIdx;
    private final List<Player> players;
    private boolean currentPlayerDidDraw = false;
    private boolean challengeSuccessful = false;

    public GameBoard(List<Player> players) {
        this.players = players;
        this.reset();
    }

    public void computeScores() {
        Optional<Player> optWinner = players.stream().filter(player -> player.handSize() == 0).findFirst();
        if (optWinner.isEmpty()) return;

        Player winner = optWinner.get();
        for (Player player : players) {
            winner.addScore(player.handScore());
        }
    }

    public int getCurrentPlayerIdx() {
        return this.currentPlayerIdx;
    }

    public int getNextPlayerIdx() {
        int currentIdx = this.currentPlayerIdx;
        if (this.direction == GameDirection.CLOCKWISE) {
            currentIdx++;
            if (currentIdx >= players.size()) {
                currentIdx = 0;
            }
        } else {
            currentIdx--;
            if (currentIdx < 0) {
                currentIdx = players.size() - 1;
            }
        }
        return currentIdx;
    }

    public Player getCurrentPlayer() {
        return this.players.get(this.currentPlayerIdx);
    }

    public Player getNextPlayer() {
        return this.players.get(this.getNextPlayerIdx());
    }

    public GameDirection getDirection() {
        return this.direction;
    }

    public void reset() {
        for (Player player : players) {
            player.clearHand();
        }
        this.deck = new Deck();
        this.deck.shuffle();

        // Ensures the initial card is not an action card, so we don't have to deal with
        // effects before any card is played
        this.discardPile = new DiscardPile(this.deck.draw());
        while (CardValue.ACTION_VALUES.contains(this.discardPile.top().getValue())) {
            this.discardPile.putCard(this.deck.draw());
        }
        this.deck.restock(this.discardPile);

        this.currentPlayerIdx = 0;
        this.direction = GameDirection.CLOCKWISE;
    }

    public void dealCards() {
        for (int i = 0; i < INITIAL_CARDS_PER_PLAYER; i++) {
            for (Player player : players) {
                makeDrawOne(player);
            }
        }
    }

    public void advancePlayer() {
        this.currentPlayerIdx = this.getNextPlayerIdx();
        currentPlayerDidDraw = false;
    }

    public void reverse() {
        if (this.direction == GameDirection.CLOCKWISE) {
            this.direction = GameDirection.COUNTER_CLOCKWISE;
        } else {
            this.direction = GameDirection.CLOCKWISE;
        }
    }

    public void makeDraw(Player player, int amount) {
        if (player == this.getCurrentPlayer()) {
            currentPlayerDidDraw = true;
        }

        for (int i = 0; i < amount; i++) {
            this.makeDrawOne(player);
        }
    }

    private void makeDrawOne(Player player) {
        if (this.deck.isEmpty()) {
            this.deck.restock(this.discardPile);
        }

        player.addToHand(deck.draw());
    }

    public DiscardPile getDiscardPile() {
        return this.discardPile;
    }

    public boolean currentPlayerDidDraw() {
        return currentPlayerDidDraw;
    }

    public boolean wasChallengeSuccessful() {
        return challengeSuccessful;
    }

    public void setChallengeSuccessful(boolean result) {
        challengeSuccessful = result;
    }

    public Player getPlayer(int playerIdx) {
        return this.players.get(playerIdx);
    }
}
