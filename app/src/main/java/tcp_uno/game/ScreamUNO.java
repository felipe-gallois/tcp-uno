package tcp_uno.game;

public class ScreamUNO extends GameAction {

    public ScreamUNO(Player player, GameBoard gameBoard) {
        super(player, gameBoard);
    }

    @Override
    public void execute() {
        getPlayer().sayUno();
    }

    public String toString() {
        return "ScreamUNO";
    }
}
