
package tcp_uno.view;

import static com.raylib.Jaylib.RAYWHITE;
import com.raylib.Raylib;
import static com.raylib.Raylib.DrawTextureRec;
import com.raylib.Raylib.Texture;
import tcp_uno.game.GameDirection;

public class Direction {
    public final int textureWidth = 128;
    public final int textureHeight = 63;
    
    //Valores de teste, alterar quando for implementar no campo
    private final int posUpArrowX = 100;
    private final int posUpArrowY = 50;
    private final int distDownArrow = 200;
    
    public Texture texture;
    
    private GameDirection direction;
    
    public Direction(GameDirection direction) {
        this.direction = direction;
        
        texture = Raylib.LoadTexture("resources/DIRECTION.png");
                
    }
    
    public void changeDirection() {
        if (direction == GameDirection.CLOCKWISE) {
            direction = GameDirection.COUNTER_CLOCKWISE;
        } else {
            direction = GameDirection.CLOCKWISE;
        }
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
        if (direction == GameDirection.CLOCKWISE) {
            DrawTextureRec(texture, getRectangle(textureWidth, textureHeight*2), getVector(posUpArrowX, posUpArrowY), RAYWHITE);
            DrawTextureRec(texture, getRectangle(textureWidth, textureHeight), getVector(posUpArrowX, posUpArrowY+distDownArrow), RAYWHITE);
        } else {
            DrawTextureRec(texture, getRectangle(textureWidth, textureHeight), getVector(posUpArrowX, posUpArrowY), RAYWHITE);
            DrawTextureRec(texture, getRectangle(textureWidth, textureHeight*2), getVector(posUpArrowX, posUpArrowY+distDownArrow), RAYWHITE);
        }
    }
}
