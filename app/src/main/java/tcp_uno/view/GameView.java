package tcp_uno.view;

import tcp_uno.AppState;
import tcp_uno.game.Card;
import tcp_uno.presenter.GamePresenter;

import static com.raylib.Jaylib.BLACK;
import static com.raylib.Jaylib.WHITE;
import static com.raylib.Raylib.*;
import static java.awt.Color.white;

public class GameView implements View {

    Background background;
    GamePresenter presenter;
    MyHandView myHandView;


    public GameView() {
        background = new Background();
        background.setTexture(LoadTexture("resources/Menu.png"));
        presenter = new GamePresenter(this);
        presenter.newGame();
        myHandView = new MyHandView();

    }

    @Override
    public void display() {
        BeginDrawing();
        background.display();
        displayDeck();
        displayHand();
        EndDrawing();
    }

    public void displayDeck() {

        Card topCard = presenter.getGame().getGameBoard().getTopCard();
        DrawText("Top Card: " + topCard.toString(), 10, 10, 20, WHITE);

    }

    public void displayHand() {
        myHandView.display(presenter.getGame().getGameBoard().getPlayerHand(0));
    }

    @Override
    public AppState update() {

        myHandView.update();
        return null;
    }
}
