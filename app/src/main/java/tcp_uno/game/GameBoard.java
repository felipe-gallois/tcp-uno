package tcp_uno.game;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private final int numPlayers;
    private Deck deck;
    private DiscardPile discardPile;
    private GameDirection direction;
    private int currentPlayerIdx;
    private List<Player> players;

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

    public Player getCurrentPlayer() {
        return this.players.get(this.currentPlayerIdx);
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

    public void advancePlayer() {
        if (this.direction == GameDirection.CLOCKWISE) {
            this.currentPlayerIdx++;
            if (this.currentPlayerIdx >= numPlayers) {
                this.currentPlayerIdx = 0;
            }
        } else {
            this.currentPlayerIdx--;
            if (this.currentPlayerIdx < 0) {
                this.currentPlayerIdx = numPlayers - 1;
            }
        }
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
}
