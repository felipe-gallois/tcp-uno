package tcp_uno.components;

import com.raylib.Raylib.Rectangle;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.Vector2;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.DrawTextureRec;
import static com.raylib.Raylib.LoadTexture;

public class UNOButton extends Button {
    private final int textureHeight = 80, textureWidth = 169;
    private int x, y;
    private final Texture texture = LoadTexture("resources/UNO.png");

    public UNOButton() {
        x = 0;
        y = 0;
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


    public Texture getTexture() {
        return texture;
    }

    private Rectangle getTextureSrc() {
        if (!getEnabled()) {
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
