package tcp_uno.game;

public class ChallengeDraw4 extends GameAction {

    private final Player challengedPlayer;

    public ChallengeDraw4(Player challenger, GameBoard gameBoard, Player challengedPlayer) {
        super(challenger, gameBoard);
        this.challengedPlayer = challengedPlayer;
    }

    @Override
    public void execute() {
        CardColor topColor = getGameBoard().getCurrentColor();

        boolean challengeResult = this.challengedPlayer.haveCardWithColor(topColor);

        if (challengeResult) {
            challengeSuccessful();
        } else {
            challengeFailed();
        }
    }

    private void challengeSuccessful() {
        GameBoard gameBoard = getGameBoard();
        gameBoard.makeDraw(challengedPlayer, 4);
    }

    private void challengeFailed() {
        GameBoard gameBoard = getGameBoard();
        gameBoard.makeDraw(getPlayer(), 2);
    }
}
