package tcp_uno.game;

/**
 * The class <code>Card</code> represents any type of card present in an UNO
 * game.
 */
public class Card {
    private CardColor color;
    private CardEffect effect;

    /**
     * Creates a new <code>Card</code> object.
     * 
     * @param color the color of the card
     * @param effect the effect of the card
     * 
     * @exception InvalidCardTypeException if the color and effect combination
     * does not exist in the game
     */
    public Card(CardColor color, CardEffect effect) {
        if (color == CardColor.BLACK) {
            if (effect != CardEffect.WILD && effect != CardEffect.WILD_DRAW_4) {
                throw new InvalidCardTypeException();
            }
        } else {
            if (effect == CardEffect.WILD || effect == CardEffect.WILD_DRAW_4) {
                throw new InvalidCardTypeException();
            }
        }

        this.color = color;
        this.effect = effect;
    }

    /**
     * Gets the color of the card object
     * 
     * @return the color of the card
     */
    public CardColor getColor() {
        return color;
    }

    /**
     * Gets the effect of the card object
     * 
     * @return the effect of the card
     */
    public CardEffect getEffect() {
        return effect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (getColor() != card.getColor()) return false;
        return getEffect() == card.getEffect();
    }

    @Override
    public int hashCode() {
        int result = getColor().hashCode();
        result = 31 * result + getEffect().hashCode();
        return result;
    }

    /**
     * Gets the score associated with the card
     * 
     * @return the card score
     */
    public int getScore() {
        return effect.getScore();
    }

    /**
     * Returns a boolean that answers if a card that is played
     * after this <code>Card</code> object can be of any color 
     * 
     * @return if the following card can be of any color
     */
    public boolean precedeAnyColor() {
        if (color == CardColor.BLACK) {
            return true;
        } else {
            return false;
        }
    }
}
