package tcp_uno;

import static com.raylib.Jaylib.LIGHTGRAY;
import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

public class Application {
    public void run() {
        InitWindow(800, 450, "Demo");
        SetTargetFPS(60);
        while (!WindowShouldClose()) {
            BeginDrawing();
            ClearBackground(RAYWHITE);
            DrawText("Hello World", 190, 200, 20, LIGHTGRAY);
            EndDrawing();
        }
        CloseWindow();
    }
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }
}
