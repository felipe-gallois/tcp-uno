package tcp_uno.components;

import com.raylib.Raylib.*;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.DrawTexture;

public class Background {
    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    private Texture texture;
    public Background() {
    }

    public void display() {
        DrawTexture(texture, 0, 0, RAYWHITE);
    }
}
