package tcp_uno.view;

import tcp_uno.AppState;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

public class MenuView implements View {
    Button playButton;

    public MenuView() {
        playButton = new Button();
        playButton.setTexture(LoadTexture("resources/ButtonTemplate.png"));
        playButton.setTextureHeight(64);
        playButton.setTextureWidth(128);
        playButton.setEnabled(true);
        playButton.setX(GetScreenWidth() / 2 - playButton.getTextureWidth() / 2);
        playButton.setY(GetScreenHeight() / 2 - playButton.getTextureHeight() / 2);
    }

    @Override
    public void display() {
        BeginDrawing();

        ClearBackground(RAYWHITE);

        playButton.draw();

        EndDrawing();

    }

    @Override
    public AppState update() {

        if (playButton.popClicked()) {
            // Do something...
        }

        playButton.update();

        return AppState.MENU;
    }
}
