package tcp_uno;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import tcp_uno.game.Card;
import tcp_uno.game.CardColor;
import tcp_uno.game.CardValue;
import tcp_uno.game.InvalidCardTypeException;

public class CardTest {
    private static final CardColor VALID_CARD_COLOR = CardColor.BLUE;
    private static final CardValue VALID_CARD_VALUE = CardValue.NUM_7;

    private static final CardColor INVALID_CARD_COLOR = CardColor.BLACK;
    private static final CardValue INVALID_CARD_VALUE = CardValue.NUM_0;

    private static Card validCard;

    @BeforeClass
    public static void createValidCard() {
        validCard = new Card(VALID_CARD_COLOR, VALID_CARD_VALUE);
    }

    @Test(expected = InvalidCardTypeException.class)
    public void createInvalidCard() {
        new Card(INVALID_CARD_COLOR, INVALID_CARD_VALUE);
    }

    @Test
    public void checkColorGetter() {
        assertEquals(VALID_CARD_COLOR, validCard.getColor());
    }

    @Test
    public void checkValueGetter() {
        assertEquals(VALID_CARD_VALUE, validCard.getValue());
    }
}
