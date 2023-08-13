package tcp_uno.game;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private final static int INITIAL_CARDS_PER_PLAYER = 7;

    private final int numPlayers;
    private Deck deck;
    private DiscardPile discardPile;
    private GameDirection direction;
    private int currentPlayerIdx;
    private final List<Player> players;
    private boolean currentPlayerDidDraw = false;
    private boolean challengeSuccessful = false;

    public GameBoard(int numPlayers) {
        this.players = new ArrayList<>(numPlayers);
        this.numPlayers = numPlayers;

        for (int i = 0; i < numPlayers; i++) {
            this.players.add(new Player());
        }

        this.reset();
    }

    public GameBoard(List<Player> players) {
        this.players = players;
        this.numPlayers = players.size();
        this.reset();
    }

    public int getCurrentPlayerIdx() {
        return this.currentPlayerIdx;
    }

    private int getNextPlayerIdx() {
        int currentIdx = this.currentPlayerIdx;
        if (this.direction == GameDirection.CLOCKWISE) {
            currentIdx++;
            if (currentIdx >= numPlayers) {
                currentIdx = 0;
            }
        } else {
            currentIdx--;
            if (currentIdx < 0) {
                currentIdx = numPlayers - 1;
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
        this.discardPile = new DiscardPile(this.deck.draw());
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

    public void addToDiscardPile(Card card) {
        addToDiscardPile(card, card.getColor());
    }

    public void addToDiscardPile(Card card, CardColor nextColor) {
        if (card.playerSelectColor() && (nextColor == null || nextColor == CardColor.BLACK))
            throw new RequiresColorChoiceException();

        // If the player cannot choose the next color, we should ignore the given color and use
        // the card color instead
        if (!card.playerSelectColor())
            nextColor = card.getColor();

        this.discardPile.putCard(card);
        this.discardPile.setCurrentColor(nextColor);
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

    public Card getTopCard() {
        return this.discardPile.top();
    }

    public CardColor getCurrentColor() {
        return this.discardPile.getCurrentColor();
    }

    public boolean canBePlayed(Card card) {
        return this.discardPile.acceptsCard(card);
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

    public List<Card> getPlayerHand(int playerIdx) {
        return this.players.get(playerIdx).getHand();
    }
}
