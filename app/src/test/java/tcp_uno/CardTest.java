package tcp_uno;

import org.junit.Test;

import tcp_uno.game.Card;
import tcp_uno.game.CardColor;
import tcp_uno.game.CardEffect;
import tcp_uno.game.InvalidCardTypeException;

public class CardTest {
    private static final CardColor VALID_CARD_COLOR = CardColor.BLUE;
    private static final CardEffect VALID_CARD_EFFECT = CardEffect.NUM_7;
    private static final CardColor INVALID_CARD_COLOR = CardColor.BLACK;
    private static final CardEffect INVALID_CARD_EFFECT = CardEffect.NUM_0;

    @Test
    public void testCreatingValidCardIsSuccessful() {
        new Card(VALID_CARD_COLOR, VALID_CARD_EFFECT);
    }

    @Test(expected = InvalidCardTypeException.class)
    public void testCreatingInvalidCardThrowsException() {
        new Card(INVALID_CARD_COLOR, INVALID_CARD_EFFECT);
    }
}
