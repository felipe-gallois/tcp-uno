package tcp_uno.view;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

import tcp_uno.AppState;

public class CreditsView implements View {
    ImageButton returnButton;
    Background background;

    public CreditsView() {
        returnButton = new ImageButton();
        returnButton.setTexture(LoadTexture("resources/VOLTAR.png"));
        returnButton.setTextureHeight(64);
        returnButton.setTextureWidth(211);
        returnButton.setEnabled(true);
        returnButton.setX(442);
        returnButton.setY(550);

        background = new Background();
        background.setTexture(LoadTexture("resources/Creditos-bg.png"));
    }

    @Override
    public void display() {
        BeginDrawing();

        ClearBackground(RAYWHITE);

        background.display();
        returnButton.display();

        EndDrawing();
    }

    @Override
    public AppState update() {

        if (returnButton.popClicked()) {
            return AppState.MENU;
        }

        returnButton.update();

        return AppState.CREDITS;
    }
}
