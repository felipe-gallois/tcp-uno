package tcp_uno.components;

import com.raylib.Raylib;

public interface Font {
    public Raylib.Font getFont(boolean bold);
    public Raylib.Font getNormalFont();
    public Raylib.Font getBoldFont();

    public void drawText(String text, int x, int y, int fontSize, int spacing, Raylib.Color color);
    public void drawText(String text, int x, int y, int fontSize, int spacing, Raylib.Color color, boolean bold);

    public Raylib.Vector2 measureText(String text, int fontSize, int spacing);
}
