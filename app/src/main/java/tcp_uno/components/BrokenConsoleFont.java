package tcp_uno.components;

import com.raylib.Raylib;

public class BrokenConsoleFont implements Font {

    private static final String REGULAR_FONT_PATH = "resources/fonts/Broken Console Regular.ttf";
    private static final String BOLD_FONT_PATH = "resources/fonts/Broken Console Bold.ttf";

    private static Raylib.Font loadedNormalFont = null;
    private static Raylib.Font loadedBoldFont = null;

    @Override
    public Raylib.Font getFont(boolean bold) {
        if (bold) {
            return getBoldFont();
        } else {
            return getNormalFont();
        }
    }

    @Override
    public Raylib.Font getNormalFont() {
        if (loadedNormalFont == null) {
            loadedNormalFont = Raylib.LoadFont(REGULAR_FONT_PATH);
        }

        return loadedNormalFont;
    }

    @Override
    public Raylib.Font getBoldFont() {
        if (loadedBoldFont == null) {
            loadedBoldFont = Raylib.LoadFont(BOLD_FONT_PATH);
        }

        return loadedBoldFont;
    }


    @Override
    public void drawText(String text, int x, int y, int fontSize, int spacing, Raylib.Color color) {
        Raylib.DrawTextEx(getFont(false), text, new Raylib.Vector2().x(x).y(y), fontSize, spacing, color);
    }

    @Override
    public void drawText(String text, int x, int y, int fontSize, int spacing, Raylib.Color color, boolean bold) {
        Raylib.DrawTextEx(getFont(bold), text, new Raylib.Vector2().x(x).y(y), fontSize, spacing, color);
    }

    @Override
    public Raylib.Vector2 measureText(String text, int fontSize, int spacing) {
        return Raylib.MeasureTextEx(getFont(false), text, fontSize, spacing);
    }
}
