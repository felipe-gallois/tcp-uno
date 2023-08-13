package tcp_uno.game;

import java.util.List;

public class PlayCard extends GameAction {
    private final Card card;
    private final CardColor nextColor;

    public PlayCard(Player player, GameBoard gameBoard, Card card) {
        super(player, gameBoard);
        this.card = card;
        this.nextColor = null;
    }

    public PlayCard(Player player, GameBoard gameBoard, Card card, CardColor nextColor) {
        super(player, gameBoard);
        this.card = card;
        this.nextColor = nextColor;
    }

    public Card getCard() {
        return card;
    }

    public static List<PlayCard> getOptionsForCard(Player player, GameBoard gameBoard, Card card) {
        if (card.playerSelectColor())
            return List.of(
                    new PlayCard(player, gameBoard, card, CardColor.BLUE),
                    new PlayCard(player, gameBoard, card, CardColor.YELLOW),
                    new PlayCard(player, gameBoard, card, CardColor.RED),
                    new PlayCard(player, gameBoard, card, CardColor.GREEN)
            );
        else
            return List.of(
                    new PlayCard(player, gameBoard, card)
            );
    }

    @Override
    public void execute() {
        GameBoard gameBoard = getGameBoard();

        Player player = this.getPlayer();

        // A player can only play a card that is in their hand
        if (player.popCard(card)) {
            gameBoard.addToDiscardPile(card, nextColor);
        } else {
            return;
        }

        performCardEffect();

        // We must wait the card effect to resolve before advancing to the next player because
        // cards may change the game direction
        gameBoard.advancePlayer();
    }

    private void performCardEffect() {
        GameBoard gameBoard = getGameBoard();

        switch (card.getValue()) {
            case REVERSE:
                gameBoard.reverse();
                break;
            case SKIP:
                gameBoard.advancePlayer();
                break;
            case DRAW_2:
                gameBoard.advancePlayer();
                gameBoard.makeDraw(gameBoard.getCurrentPlayer(), 2);
                break;
            case WILD_DRAW_4:
                if (!gameBoard.wasChallengeSuccessful()) {
                    gameBoard.advancePlayer();
                    gameBoard.makeDraw(gameBoard.getCurrentPlayer(), 4);
                } else {
                    gameBoard.setChallengeSuccessful(false);
                }
                break;
            default:
                break;
        }
    }

    public String toString() {
        if (nextColor != null)
            return "PlayCard(" + card + ", " + nextColor + ")";
        else
            return "PlayCard(" + card + ")";
    }
}
