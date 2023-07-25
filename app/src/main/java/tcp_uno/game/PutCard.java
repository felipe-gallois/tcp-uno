package tcp_uno.game;

public class PutCard extends GameAction {
    private Card card;

    public PutCard(Player player, GameBoard gameboard, Card card) {
        super(player, gameboard);
        this.card = card;
    }

    public void execute() {
        GameBoard gameboard = this.getGameBoard();
        Player player = this.getPlayer();

        if (player.popCard(card)) {
            gameboard.addToDiscardPile(card);
        }
    }
}
