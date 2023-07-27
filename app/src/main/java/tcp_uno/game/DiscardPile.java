package tcp_uno.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DiscardPile {
    private final Stack<Card> pile = new Stack<>();
    private CardColor currentColor;

    public DiscardPile(Card card) {
        putCard(card);
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

    public List<Card> removeExcessCards() {
        Card topCard = this.pile.pop();
        List<Card> cards = this.flush();
        this.putCard(topCard);
        return cards;
    }

    public CardColor getCurrentColor() {
        return this.currentColor;
    }

    public void setCurrentColor(CardColor color) {
        this.currentColor = color;
    }

    public Card top() {
        return this.pile.peek();
    }

    public boolean acceptsCard(Card card) {
        Card topCard = this.top();

        boolean colorOk = card.getColor() == currentColor || card.getColor() == CardColor.BLACK;
        boolean valueOk = card.getValue() == topCard.getValue();

        return colorOk || valueOk;
    }
}
