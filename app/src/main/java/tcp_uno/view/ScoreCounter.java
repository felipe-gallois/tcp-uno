package tcp_uno.view;

import tcp_uno.components.BrokenConsoleFont;
import tcp_uno.components.Font;

import static com.raylib.Jaylib.WHITE;

public class ScoreCounter {

    private final int x;
    private final int y;
    private final int value;

    public ScoreCounter(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public void display() {
        Font font = new BrokenConsoleFont();
        font.drawText(String.valueOf(value), x, y, 42, 0, WHITE);
    }
}
