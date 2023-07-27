package tcp_uno.game;

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

    @Override
    public void execute() {
        GameBoard gameBoard = getGameBoard();

        PutCard putCardAction = new PutCard(getPlayer(), gameBoard, card, nextColor);
        putCardAction.execute();

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
                gameBoard.advancePlayer();
                gameBoard.makeDraw(gameBoard.getCurrentPlayer(), 4);
                break;
            default:
                break;
        }
    }    
}
