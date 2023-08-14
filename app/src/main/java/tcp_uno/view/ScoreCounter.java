package tcp_uno.view;

import tcp_uno.components.BrokenConsoleFont;
import tcp_uno.components.Font;

import static com.raylib.Jaylib.WHITE;

public class ScoreCounter {
    private static final int MAX_SLOTS = 8;

    private final boolean hasSign;

    private int x,y;

    private int value;

    public ScoreCounter(boolean hasSign) {
        this.hasSign = hasSign;
    }

    public void setValue(int value) {
            this.value = value;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getScore() {
        return value;
    }

    public void display() {
        Font font = new BrokenConsoleFont();
        font.drawText(String.valueOf(value), x, y, 14, 0, WHITE);
    }
}
