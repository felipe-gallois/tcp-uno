package tcp_uno.game;

public class Card {
    private final CardColor color;
    private final CardValue value;
    private final int score;

    public Card(CardColor color, CardValue value) {
        if (color == CardColor.BLACK) {
            if (value != CardValue.WILD && value != CardValue.WILD_DRAW_4) {
                throw new InvalidCardTypeException();
            }
        } else {
            if (value == CardValue.WILD || value == CardValue.WILD_DRAW_4) {
                throw new InvalidCardTypeException();
            }
        }

        this.color = color;
        this.value = value;
        this.score = value.getScore();
    }

    public CardColor getColor() {
        return color;
    }

    public CardValue getValue() {
        return value;
    }

    public int getScore() {
        return score;
    }

    public boolean playerSelectColor() {
        return color == CardColor.BLACK;
    }

    public String toString() {
        return color + " " + value;
    }
}
