package tcp_uno.components;

import tcp_uno.game.Card;


import com.raylib.Raylib;

import static com.raylib.Jaylib.RED;
import static com.raylib.Jaylib.WHITE;

public class CardButton extends Button {
    private final Card card;
    private final tcp_uno.components.Card cardView;
    private int size;
    private int x, y;

    public CardButton(Card card, int size, int x, int y) {
        this.card = card;
        this.cardView = new tcp_uno.components.Card(card, size, false);
        this.cardView.setX(x);
        this.cardView.setY(y);

        this.x = x;
        this.y = y;

        this.size = size;
    }


    @Override
    public Raylib.Rectangle getRectangle() {
        return new Raylib.Rectangle().x(x).y(y).width(size/cardView.ASPECT_RATIO).height(size);
    }

    @Override
    public void display() {
        if (isEnabled()) {
            cardView.display();
        }
        if (getHovered()) {
            Raylib.DrawRectangleLinesEx(getRectangle(), 2, RED);
        }
    }
}
