package tcp_uno.game;

public class ChallengeDraw4 extends GameAction {

    private final Player challengedPlayer;

    public ChallengeDraw4(Player challenger, GameBoard gameBoard, Player challengedPlayer) {
        super(challenger, gameBoard);
        this.challengedPlayer = challengedPlayer;
    }

    @Override
    public void execute() {
        GameBoard gameboard = getGameBoard();
        CardColor topColor = gameboard.getDiscardPile().getCurrentColor();
        boolean challengeResult = this.challengedPlayer.getHand().stream().anyMatch(card -> card.getColor() == topColor);
        gameboard.setChallengeSuccessful(challengeResult);

        if (challengeResult) {
            challengeSuccessful();
        } else {
            challengeFailed();
        }
    }

    private void challengeSuccessful() {
        GameBoard gameBoard = getGameBoard();
        gameBoard.setChallengeSuccessful(true);
        gameBoard.makeDraw(challengedPlayer, 4);
    }

    private void challengeFailed() {
        GameBoard gameBoard = getGameBoard();
        gameBoard.setChallengeSuccessful(false);
        gameBoard.makeDraw(getPlayer(), 2);
    }
}
