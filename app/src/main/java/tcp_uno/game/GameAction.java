package tcp_uno.game;

public abstract class GameAction {
    private final Player player;
    private final GameBoard gameBoard;

    public GameAction(Player player, GameBoard gameBoard) {
        this.player = player;
        this.gameBoard = gameBoard;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract void execute();
}
