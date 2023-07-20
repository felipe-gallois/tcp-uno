package tcp_uno.game;

public class GameBoard {
    private final int numPlayers;
    private Deck deck;
    private DiscardPile discardPile;
    private GameDirection direction;
    private int currentPlayerIdx;

    public GameBoard(int numPlayers) {
        this.numPlayers = numPlayers;
        this.reset();
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public int getCurrentPlayerIdx() {
        return this.currentPlayerIdx;
    }

    public GameDirection getDirection() {
        return this.direction;
    }

    public void reset() {
        this.deck = new Deck();
        this.deck.shuffle();
        this.discardPile = new DiscardPile(this.deck.draw());
        this.currentPlayerIdx = 0;
        this.direction = GameDirection.CLOCKWISE;
    }

    public void advancePlayer() {
        if (this.direction == GameDirection.CLOCKWISE) {
            this.currentPlayerIdx = (this.currentPlayerIdx + 1) % this.numPlayers;
        } else {
            this.currentPlayerIdx = (this.currentPlayerIdx - 1) % this.numPlayers;
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
        this.discardPile.putCard(card);
    }
}
