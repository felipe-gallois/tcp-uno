package tcp_uno.game;

public class SkipTurn extends GameAction {
    public SkipTurn(Player player, GameBoard gameBoard) {
        super(player, gameBoard);
    }

    @Override
    public void execute() {
        getGameBoard().advancePlayer();
    }
}
