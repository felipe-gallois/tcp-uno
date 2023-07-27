package tcp_uno.game;

import java.util.List;

public class ChallengeDraw4 extends GameAction {

    private final Player challengedPlayer;

    public ChallengeDraw4(Player challenger, GameBoard gameBoard, Player challengedPlayer) {
        super(challenger, gameBoard);
        this.challengedPlayer = challengedPlayer;
    }

    @Override
    public void execute() {
        CardColor topColor = getGameBoard().getCurrentColor();
        List<Card> playerHand = this.challengedPlayer.getHand();

        boolean challengeResult = playerHand.stream().anyMatch(card -> card.getColor() == topColor);

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
