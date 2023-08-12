package tcp_uno.view;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

public class ImageButton extends Button {
    int textureHeight, textureWidth;
    int x, y;
    Texture texture;

    public ImageButton() {
        textureHeight = 0;
        textureWidth = 0;
        x = 0;
        y = 0;
        texture = null;
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

    public void setTextureHeight(int textureHeight) {
        this.textureHeight = textureHeight;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public void setTextureWidth(int textureWidth) {
        this.textureWidth = textureWidth;
    }



    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
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
