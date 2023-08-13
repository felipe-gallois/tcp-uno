package tcp_uno.view;

import tcp_uno.game.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.raylib.Jaylib.RED;
import static com.raylib.Jaylib.WHITE;

public class MyHandView {
    final int HAND_SIZE = 15;

    private List<Card> cards;
    private List<TextButton> buttons;

    private Function<Card, Boolean> cardClicked;

    MyHandView() {
        int posY = 50;
        buttons = new ArrayList<TextButton>();
        for (int i = 0; i < HAND_SIZE; i++) {
            TextButton btn = new TextButton("", 50, posY, 14, WHITE);
            btn.setShowWhenDisabled(false);
            btn.setEnabled(false);
            btn.setHoverColor(RED);
            buttons.add(btn);
            posY += 50;
        }
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
        for (int i = 0; i < cards.size(); i++) {
            buttons.get(i).setText(cards.get(i).toString());
            buttons.get(i).setEnabled(true);
        }
        for (int i = cards.size(); i < HAND_SIZE; i++) {
            buttons.get(i).setEnabled(false);
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
        for (TextButton button : buttons) {
            button.display();
        }
    }

    public void update() {
        for (TextButton button : buttons) {
            button.update();
        }
    }

}
