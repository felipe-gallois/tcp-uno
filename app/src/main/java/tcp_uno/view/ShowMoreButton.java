package tcp_uno.view;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

import com.raylib.Raylib.Rectangle;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.Vector2;

public class ShowMoreButton {
    private boolean isClicked;
    private boolean isHovered;
    private final int textureHeight = 36, textureWidth = 36;
    private int x, y;
    private boolean isEnabled;
    private final Texture texture = LoadTexture("resources/SHOWMORE.png");

    public ShowMoreButton() {
        isClicked = false;
        isHovered = false;
        x = 0;
        y = 0;
        isEnabled = false;
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

    public int getTextureHeight() {
        return textureHeight;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Texture getTexture() {
        return texture;
    }

    public boolean popClicked() {
        boolean temp = isClicked;
        isClicked = false;
        return temp;
    }

    public boolean getHovered() {
        return isHovered;
    }

    public boolean peakClicked() {
        return isClicked;
    }

    private Rectangle getTextureSrc() {
        if (!isEnabled) {
            return new Rectangle().y(textureHeight * 3).width(textureWidth).height(textureHeight);
        }
        if (peakClicked()) {
            return new Rectangle().y(textureHeight * 2).width(textureWidth).height(textureHeight);
        } else if (getHovered()) {
            return new Rectangle().y(textureHeight).width(textureWidth).height(textureHeight);
        } else {
            return new Rectangle().y(0).width(textureWidth).height(textureHeight);
        }
    }

    public void update() {
        if (isEnabled) {
            Vector2 mousePos = GetMousePosition();
            isHovered = CheckCollisionPointRec(mousePos, getRectangle());
            // isClicked should not be unset until it is popped
            isClicked = isClicked || (isHovered && IsMouseButtonPressed(MOUSE_BUTTON_LEFT));
        }
    }

    public Vector2 getPosition() {
        Vector2 position = new Vector2();
        position.x(x);
        position.y(y);
        return position;
    }

    public Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.x(x);
        rectangle.y(y);
        rectangle.width(textureWidth);
        rectangle.height(textureHeight);
        return rectangle;
    }

    public void display() {
        DrawTextureRec(texture, getTextureSrc(), getPosition(), RAYWHITE);
    }

}

