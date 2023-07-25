package tcp_uno.game;

public class DrawCards extends GameAction {
    private int amount;

    public DrawCards(Player player, GameBoard gameBoard, int amount) {
        super(player, gameBoard);
        this.amount = amount;
    }

    @Override
    public void execute() {
        getGameBoard().makeDraw(getPlayer(), amount);
    }
}
