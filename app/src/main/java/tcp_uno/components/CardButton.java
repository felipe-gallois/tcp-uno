package tcp_uno.components;

import com.raylib.Raylib;
import tcp_uno.game.Card;

import static com.raylib.Jaylib.RED;

public class CardButton extends Button {
    private final Card card;
    private final tcp_uno.components.Card cardView;
    private final int size;
    private final int x;
    private final int y;

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
        return new Raylib.Rectangle().x(x).y(y).width(size / tcp_uno.components.Card.ASPECT_RATIO).height(size);
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
