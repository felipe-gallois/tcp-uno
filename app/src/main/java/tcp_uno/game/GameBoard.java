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
        this.discardPile.putCard(card);
    }

    public void addToDiscardPile(Card card, CardColor nextColor) {
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
