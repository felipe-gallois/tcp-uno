package tcp_uno.game;

public class PutCard extends GameAction {
    private final Card card;
    private final CardColor nextColor;

    public PutCard(Player player, GameBoard gameboard, Card card) {
        super(player, gameboard);
        this.card = card;
        this.nextColor = null;

        if (card.playerSelectColor()) {
            throw new RequiresColorChoiceException();
        }
    }

    public PutCard(Player player, GameBoard gameboard, Card card, CardColor nextColor) {
        super(player, gameboard);
        this.card = card;
        this.nextColor = nextColor;
    }

    public void execute() {
        GameBoard gameboard = this.getGameBoard();
        Player player = this.getPlayer();

        if (player.popCard(card)) {
            if (card.playerSelectColor()) {
                gameboard.addToDiscardPile(card, nextColor);
            } else {
                gameboard.addToDiscardPile(card);
            }
        }
    }
}
