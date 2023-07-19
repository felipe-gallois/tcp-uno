package tcp_uno.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DiscardPile {
    private final Stack<Card> pile = new Stack<>();
    private CardColor currentColor;

    public DiscardPile(Card card) {
        this.pile.push(card);
        this.currentColor = card.getColor();
    }

    public void putCard(Card card) {
        this.pile.push(card);
        this.currentColor = card.getColor();
    }

    public List<Card> flush() {
        ArrayList<Card> cards = new ArrayList<>();
        while (!this.pile.empty()) {
            cards.add(this.pile.pop());
        }
        this.currentColor = null;
        return cards;
    }

    public List<Card> retrieve() {
        Card topCard = this.pile.pop();
        List<Card> cards = this.flush();
        this.putCard(topCard);
        return cards;
    }

    public void setCurrentColor(CardColor color) {
        this.currentColor = color;
    }

    public Card top() {
        return this.pile.peek();
    }

    public boolean canPlayCard(Card card) {
        Card topCard = this.top();

        boolean colorOk = card.getColor() == currentColor || card.getColor() == CardColor.BLACK;
        boolean valueOk = card.getValue() == topCard.getValue();

        return colorOk || valueOk;
    }
}
