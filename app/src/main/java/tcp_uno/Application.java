package tcp_uno;

import tcp_uno.view.View;

import static com.raylib.Raylib.*;

public class Application {
    public static final String WINDOW_TITLE = "Uno Game";
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 450;
    private AppState appState;
    private View activeView;

    Application() {
        appState = AppState.MENU;
//        activeView = new MenuView();
    }

    private static void closeWindow() {
        CloseWindow();
    }

    private static void initWindow() {
        InitWindow(WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_TITLE);
        SetTargetFPS(60);
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    public void run() {
        initWindow();

        while (shouldKeepRunning()) {
            AppState newAppState = activeView.update();
            activeView.display();
            if (newAppState != appState) {
                onStateChange(newAppState);
            }
        }

        closeWindow();
    }

    private boolean shouldKeepRunning() {
        return !WindowShouldClose()
                && appState != AppState.EXIT;
    }

    public void onStateChange(AppState newState) {
        appState = newState;
        activeView = appState.getView();
    }
}
