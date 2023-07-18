package tcp_uno.game;

public class Card {
    private CardColor color;
    private CardValue value;

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
    }

    public CardColor getColor() {
        return color;
    }

    public CardValue getValue() {
        return value;
    }
}
