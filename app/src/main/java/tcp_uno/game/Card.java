package tcp_uno.game;

public class Card {
    private CardColor color;
    private CardValue value;
    private int score;

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
        if (color == CardColor.BLACK) {
            return true;
        } else {
            return false;
        }
    }
}
