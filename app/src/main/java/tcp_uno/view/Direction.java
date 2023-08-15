package tcp_uno.view;

import com.raylib.Raylib;
import com.raylib.Raylib.Texture;
import tcp_uno.game.GameDirection;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.DrawTextureRec;

public class Direction {
    private final int textureWidth = 128;
    private final int textureHeight = 63;

    private final Texture texture;
    private GameDirection direction;


    public Direction(GameDirection direction) {
        texture = Raylib.LoadTexture("resources/DIRECTION.png");
        this.direction = direction;
    }

    public void setDirection(GameDirection direction) {
        this.direction = direction;
    }

    private Raylib.Vector2 getVector(int x, int y) {
        Raylib.Vector2 position = new Raylib.Vector2();
        position.x(x);
        position.y(y);
        return position;
    }

    private Raylib.Rectangle getRectangle(int x, int y) {
        Raylib.Rectangle rectangle = new Raylib.Rectangle();
        rectangle.x(x);
        rectangle.y(y);
        rectangle.width(textureWidth);
        rectangle.height(textureHeight);
        return rectangle;
    }

    public void display() {
        //Valores de teste, alterar quando for implementar no campo
        int posUpArrowX = 100;
        int posUpArrowY = 50;
        int distDownArrow = 200;
        if (direction == GameDirection.CLOCKWISE) {
            DrawTextureRec(texture, getRectangle(textureWidth, textureHeight * 2), getVector(posUpArrowX, posUpArrowY), RAYWHITE);
            DrawTextureRec(texture, getRectangle(textureWidth, textureHeight), getVector(posUpArrowX, posUpArrowY + distDownArrow), RAYWHITE);
        } else {
            DrawTextureRec(texture, getRectangle(textureWidth, textureHeight), getVector(posUpArrowX, posUpArrowY), RAYWHITE);
            DrawTextureRec(texture, getRectangle(textureWidth, textureHeight * 2), getVector(posUpArrowX, posUpArrowY + distDownArrow), RAYWHITE);
        }
    }
}
