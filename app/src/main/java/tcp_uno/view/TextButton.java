package tcp_uno.view;

import com.raylib.Raylib;

import static com.raylib.Raylib.MeasureTextEx;

public class TextButton extends Button {

    private String text;
    private int x, y;
    private int fontSize;
    private Raylib.Color defaultColor;
    private Raylib.Color hoverColor;
    private Raylib.Color clickedColor;

    public boolean isShowWhenDisabled() {
        return showWhenDisabled;
    }

    public void setShowWhenDisabled(boolean showWhenDisabled) {
        this.showWhenDisabled = showWhenDisabled;
    }

    private boolean showWhenDisabled  = true;


    public TextButton(String text, int x, int y, int fontSize, Raylib.Color color) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
        this.defaultColor = color;
        this.hoverColor = color;
        this.clickedColor = color;

    }


    @Override
    public Raylib.Rectangle getRectangle() {
        Raylib.Vector2 size = MeasureTextEx(Raylib.GetFontDefault(), text, fontSize, 0);
        return new Raylib.Rectangle().x(x).y(y).width(size.x()).height(size.y());
    }

    @Override
    public void display() {
        Raylib.Vector2 size = MeasureTextEx(Raylib.GetFontDefault(), text, fontSize, 0);
        Raylib.Color color = defaultColor;
        if (getHovered()) {
            color = hoverColor;
        }
        if (peakClicked()) {
            color = clickedColor;
        }
        if (!getEnabled() && !showWhenDisabled) {
            return;
        }
        Raylib.DrawText(text, x, y, fontSize, color);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public Raylib.Color getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(Raylib.Color defaultColor) {
        this.defaultColor = defaultColor;
    }

    public Raylib.Color getHoverColor() {
        return hoverColor;
    }

    public void setHoverColor(Raylib.Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public Raylib.Color getClickedColor() {
        return clickedColor;
    }

    public void setClickedColor(Raylib.Color clickedColor) {
        this.clickedColor = clickedColor;
    }

}
