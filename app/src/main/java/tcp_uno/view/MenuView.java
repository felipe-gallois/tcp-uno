package tcp_uno.view;

import tcp_uno.AppState;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

public class MenuView implements View {
    Button playButton;
    Button creditsButton;
    Button exitButton;
    Background background;

    public MenuView() {
        playButton = new Button();
        playButton.setTexture(LoadTexture("resources/PLAY.png"));
        playButton.setTextureHeight(82);
        playButton.setTextureWidth(214);
        playButton.setEnabled(true);
        playButton.setX(442);
        playButton.setY(293);

        creditsButton = new Button();
        creditsButton.setTexture(LoadTexture("resources/CREDITOS.png"));
        creditsButton.setTextureHeight(85);
        creditsButton.setTextureWidth(361);
        creditsButton.setEnabled(true);
        creditsButton.setX(381);
        creditsButton.setY(369);

        exitButton = new Button();
        exitButton.setTexture(LoadTexture("resources/EXIT.png"));
        exitButton.setTextureHeight(80);
        exitButton.setTextureWidth(169);
        exitButton.setEnabled(true);
        exitButton.setX(453);
        exitButton.setY(456);


        background = new Background();
        background.setTexture(LoadTexture("resources/Menu.png"));
    }

    @Override
    public void display() {
        BeginDrawing();

        ClearBackground(RAYWHITE);

        background.display();
        playButton.display();
        creditsButton.display();
        exitButton.display();

        EndDrawing();

    }

    @Override
    public AppState update() {

        if (playButton.popClicked()) {
            return AppState.PLAYING;
        }
        if (creditsButton.popClicked()) {
            return AppState.CREDITS;
        }
        if (exitButton.popClicked()) {
            return AppState.EXIT;
        }

        playButton.update();
        creditsButton.update();
        exitButton.update();

        return AppState.MENU;
    }
}
