package tcp_uno.view;

import tcp_uno.AppState;

public interface View {
    void display();

    AppState update();
}
