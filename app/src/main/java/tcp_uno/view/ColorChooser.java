package tcp_uno.view;

import tcp_uno.components.Button;
import tcp_uno.components.TextButton;
import tcp_uno.game.CardColor;
import tcp_uno.presenter.GamePresenter;

import java.net.CacheRequest;

import static com.raylib.Jaylib.*;
import static com.raylib.Raylib.DrawRectangle;

public class ColorChooser {
    private GamePresenter presenter;
    private CardColor chosen_color;
    private boolean is_done;

    private TextButton red_button;
    private TextButton blue_button;
    private TextButton green_button;
    private TextButton yellow_button;


    ColorChooser(GamePresenter presenter) {
        this.presenter = presenter;

        is_done = false;
        chosen_color = CardColor.RED;
        red_button = new TextButton("Red", 450, 250, 20, RED);
        red_button.setHoverColor(BLACK);
        blue_button = new TextButton("Blue", 550, 250, 20, BLUE);
        blue_button.setHoverColor(BLACK);
        green_button = new TextButton("Green", 450, 300, 20, GREEN);
        green_button.setHoverColor(BLACK);
        yellow_button = new TextButton("Yellow", 550, 300, 20, YELLOW);
        yellow_button.setHoverColor(BLACK);
    }

    public void display() {
        DrawRectangle(420, 230, 250, 100, WHITE);
        red_button.display();
        blue_button.display();
        green_button.display();
        yellow_button.display();
    }

    public void setChosenColor(CardColor chosen_color) {
        this.chosen_color = chosen_color;
        this.is_done = true;
    }

    public boolean isDone() {
        return is_done;
    }

    public CardColor getChosenColor() {
        return chosen_color;
    }

    public void update() {
        if (blue_button.popClicked()) {
            setChosenColor(CardColor.BLUE);
        } else if (red_button.popClicked()) {
            setChosenColor(CardColor.RED);
        } else if (green_button.popClicked()) {
            setChosenColor(CardColor.GREEN);
        } else if (yellow_button.popClicked()) {
            setChosenColor(CardColor.YELLOW);
        }
        blue_button.update();
        red_button.update();
        green_button.update();
        yellow_button.update();
    }
}
