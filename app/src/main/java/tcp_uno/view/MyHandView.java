package tcp_uno.view;

import tcp_uno.game.Card;

import java.util.List;

import static com.raylib.Jaylib.RED;
import static com.raylib.Jaylib.WHITE;

public class MyHandView {
    final int HAND_SIZE = 10;
    private List<TextButton> buttons;

    MyHandView() {
        int posY = 50;
        for (int i = 0; i < 10; i++) {
            TextButton btn = new TextButton("", 50, posY, 14, WHITE);
            btn.setShowWhenDisabled(false);
            btn.setEnabled(false);
            btn.setHoverColor(RED);
            buttons.add(btn);
        }
    }
    public void display(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) == null) {
                buttons.get(i).setEnabled(false);
                buttons.get(i).setText("");
            } else {
                buttons.get(i).setText(cards.get(i).toString());
                buttons.get(i).setEnabled(true);
            }
        }
    }

    public void update() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).update();
        }
    }
}
