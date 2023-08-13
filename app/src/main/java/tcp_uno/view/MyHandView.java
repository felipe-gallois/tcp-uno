package tcp_uno.view;

import tcp_uno.components.Button;
import tcp_uno.components.CardButton;
import tcp_uno.game.Card;

import java.util.ArrayList;
import java.util.List;

import static tcp_uno.Application.WINDOW_HEIGHT;
import static tcp_uno.Application.WINDOW_WIDTH;

public class MyHandView {
    final int CARD_HEIGHT = 120;

    final int MARGIN_BOTTOM = 50;
    final int MARGIN_LEFT = 10;

    final int POS_Y = WINDOW_HEIGHT - CARD_HEIGHT - MARGIN_BOTTOM;

    private List<Card> cards;
    private List<CardButton> buttons;

    MyHandView() {
        int posY = 50;
        buttons = new ArrayList<>();
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
        buttons.clear();
        int count = cards.size();
        int width = (int) (count * (CARD_HEIGHT / tcp_uno.components.Card.ASPECT_RATIO)) + (count - 1) * MARGIN_LEFT;
        int initialX = (WINDOW_WIDTH - width) / 2;

        int posX = initialX;
        for (Card c : cards) {
            buttons.add(new CardButton(c, CARD_HEIGHT, posX, POS_Y));
            posX += (int) (CARD_HEIGHT / tcp_uno.components.Card.ASPECT_RATIO + MARGIN_LEFT);
        }
    }

    public int getCardClicked() {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).popClicked()) {
                return i;
            }
        }
        return -1;
    }

    public void display() {
        for (Button button : buttons) {
            button.display();
        }
    }

    public void update() {
        for (Button button : buttons) {
            button.update();
        }
    }

}
