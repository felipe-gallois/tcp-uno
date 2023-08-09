package tcp_uno;

import tcp_uno.view.MenuView;
import tcp_uno.view.View;

public enum AppState {
    MENU,
    PLAYING,
    CREDITS,
    EXIT;

    View getView() {
        switch (this) {
            case MENU:
                return new MenuView();
            case PLAYING:
//                return new GameView();
            case CREDITS:
//                return new CreditsView();
            default:
                return null;
        }
    }
}
