package tcp_uno;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import tcp_uno.game.Card;
import tcp_uno.game.CardColor;
import tcp_uno.game.CardValue;
import tcp_uno.game.InvalidCardTypeException;

public class CardTest {
    private static final CardColor VALID_CARD_COLOR = CardColor.BLUE;
    private static final CardValue VALID_CARD_VALUE = CardValue.NUM_7;
    private static final CardColor VALID_CARD_COLOR_2 = CardColor.BLACK;
    private static final CardValue VALID_CARD_VALUE_2 = CardValue.WILD_DRAW_4;
    private static final CardColor INVALID_CARD_COLOR = CardColor.BLACK;
    private static final CardValue INVALID_CARD_VALUE = CardValue.NUM_0;

    private static Card validCard;
    private static Card validCard2;

    @BeforeClass
    public static void testCreateValidCardIsSuccessful() {
        validCard = new Card(VALID_CARD_COLOR, VALID_CARD_VALUE);
        validCard2 = new Card(VALID_CARD_COLOR_2, VALID_CARD_VALUE_2);
    }

    @Test(expected = InvalidCardTypeException.class)
    public void testCreateInvalidCardThrowsException() {
        new Card(INVALID_CARD_COLOR, INVALID_CARD_VALUE);
    }

    @Test
    public void testCannotChooseColorAfterColoredCard() {
        assertFalse(validCard.playerSelectColor());
    }

    @Test
    public void testMustChooseColorAfterBlackCard() {
        assertTrue(validCard2.playerSelectColor());
    }
}
