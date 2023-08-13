package tcp_uno.components;

import com.raylib.Raylib;

public interface Font {
    Raylib.Font getFont(boolean bold);

    Raylib.Font getNormalFont();

    Raylib.Font getBoldFont();

    void drawText(String text, int x, int y, int fontSize, int spacing, Raylib.Color color);

    void drawText(String text, int x, int y, int fontSize, int spacing, Raylib.Color color, boolean bold);

    Raylib.Vector2 measureText(String text, int fontSize, int spacing);
}
