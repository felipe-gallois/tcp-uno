package tcp_uno.game;

import java.util.ArrayList;


public class Player {
    private final ArrayList<Card> hand;
    private boolean saidUno;
    private int score;

    public Player() {
        hand = new ArrayList<>();
        saidUno = false;
        score = 0;
    }

    public void addToHand(Card card) {
        hand.add(card);
        // When the player draws a card, we need to reset the flag
        // so they can say UNO again later
        saidUno = false;
    }

    public boolean haveCardWithColor(CardColor color) {
        return this.hand.stream().anyMatch(card -> card.getColor() == color);
    }

    public Card getCard(int position) {
        // This may propagate an exception
        return hand.get(position);
    }

    public Card popCard(int position) {
        // This may propagate an exception
        return hand.remove(position);
    }
    
    public boolean popCard(Card card) {
        return hand.remove(card);
    }

    public int handSize() {
        return hand.size();
    }

    public int handScore() {
        int totalScore = 0;

        for (Card c : hand) {
            totalScore += c.getScore();
        }

        return totalScore;
    }

    public void sayUno() {
        saidUno = true;
    }

    public boolean saidUno() {
        return saidUno;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }
}
