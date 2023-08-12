package tcp_uno.view;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

import com.raylib.Raylib.Rectangle;
import com.raylib.Raylib.Texture;
import com.raylib.Raylib.Vector2;

public class NumberSlot {
    private static final int MIN_CHARACTER_ID = 0;
    private static final int MAX_CHARACTER_ID = 11;

    private final int textureHeight, textureWidth;
    private final Texture texture;

    private int x, y;
    private int characterId;
    private boolean isEnabled;

    public NumberSlot() {
        textureHeight = 36;
        textureWidth = 36;
        x = 0;
        y = 0;
        characterId = 0;
        isEnabled = false;
        texture = LoadTexture("resources/Numbers.png");
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

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        if (characterId >= MIN_CHARACTER_ID &&
            characterId <= MAX_CHARACTER_ID) {
                this.characterId = characterId;
        } else {
            throw new InvalidCharacterId();
        }
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

    private Rectangle getTextureSrc() {
        return new Rectangle().y(textureHeight * characterId)
                              .width(textureWidth).height(textureHeight);
    }

    public Vector2 getPosition() {
        Vector2 position = new Vector2();
        position.x(x);
        position.y(y);
        return position;
    }

    public void display() {
        DrawTextureRec(texture, getTextureSrc(), getPosition(), RAYWHITE);
    }

}
