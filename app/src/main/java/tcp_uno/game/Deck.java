package tcp_uno.game;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private final Stack<Card> cards = new Stack<>();

    public Deck() {
        for (CardColor color: CardColor.values()) {
            // Black cards do not have numeric and action values
            if (color == CardColor.BLACK) continue;

            cards.push(new Card(color, CardValue.NUM_0));
            cards.push(new Card(color, CardValue.NUM_1));
            cards.push(new Card(color, CardValue.NUM_1));
            cards.push(new Card(color, CardValue.NUM_2));
            cards.push(new Card(color, CardValue.NUM_2));
            cards.push(new Card(color, CardValue.NUM_3));
            cards.push(new Card(color, CardValue.NUM_3));
            cards.push(new Card(color, CardValue.NUM_4));
            cards.push(new Card(color, CardValue.NUM_4));
            cards.push(new Card(color, CardValue.NUM_5));
            cards.push(new Card(color, CardValue.NUM_5));
            cards.push(new Card(color, CardValue.NUM_6));
            cards.push(new Card(color, CardValue.NUM_6));
            cards.push(new Card(color, CardValue.NUM_7));
            cards.push(new Card(color, CardValue.NUM_7));
            cards.push(new Card(color, CardValue.NUM_8));
            cards.push(new Card(color, CardValue.NUM_8));
            cards.push(new Card(color, CardValue.NUM_9));
            cards.push(new Card(color, CardValue.NUM_9));
            cards.push(new Card(color, CardValue.SKIP));
            cards.push(new Card(color, CardValue.SKIP));
            cards.push(new Card(color, CardValue.REVERSE));
            cards.push(new Card(color, CardValue.REVERSE));
            cards.push(new Card(color, CardValue.DRAW_2));
            cards.push(new Card(color, CardValue.DRAW_2));
        }

        for (int i = 0; i < 4; i++) {
            cards.push(new Card(CardColor.BLACK, CardValue.WILD));
            cards.push(new Card(CardColor.BLACK, CardValue.WILD_DRAW_4));
        }
    }

    public Card draw() {
        if (isEmpty())
            throw new EmptyDeckException();
        return cards.pop();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void restock(DiscardPile discardPile) {
        for (Card card: discardPile.removeExcessCards()) {
            cards.push(card);
        }
    }
}
