package tcp_uno.view;

import tcp_uno.AppState;
import tcp_uno.game.Card;
import tcp_uno.presenter.GamePresenter;

import static com.raylib.Jaylib.*;
import static com.raylib.Raylib.*;
import static java.awt.Color.white;

public class GameView implements View {

    Background background;
    GamePresenter presenter;
    MyHandView myHandView;

    TextButton drawCardButton;
    TextButton screamUNOButton;

    tcp_uno.view.Card deck_card;

    public GameView() {
        background = new Background();
        background.setTexture(LoadTexture("resources/Menu.png"));
        presenter = new GamePresenter(this);
        presenter.newGame();
        myHandView = new MyHandView();

        drawCardButton = new TextButton("Draw Card", 10, 500, 20, WHITE);
        drawCardButton.setHoverColor(RED);
        screamUNOButton = new TextButton("Scream UNO", 10, 550, 20, WHITE);
        screamUNOButton.setHoverColor(RED);


    }

    @Override
    public void display() {
        BeginDrawing();
        background.display();
        displayDeck();
        displayHand();
        drawCardButton.display();
        screamUNOButton.display();

        DrawText("Current Player: " + presenter.getGame().getGameBoard().getCurrentPlayerIdx(), 510, 600, 20, WHITE);
        DrawText("Direction: " + presenter.getGame().getGameBoard().getDirection(), 510, 650, 20, WHITE);

        for (int i = 0; i < 4; i++) {
            DrawText("Player " + i + " Score: " + presenter.getGame().getGameBoard().getPlayerHand(i).size(), 810, 100 + i * 50, 20, WHITE);
        }

        EndDrawing();
    }

    public void displayDeck() {

        Card topCard = presenter.getGame().getGameBoard().getTopCard();
        deck_card = new tcp_uno.view.Card(topCard, 100, false);
        deck_card.setX(500);
        deck_card.setY(300);
        DrawText("Top Card: " + topCard.toString(), 10, 10, 20, WHITE);
        deck_card.display();

    }

    public void displayHand() {
        myHandView.display();
    }

    @Override
    public AppState update() {

        myHandView.update();
        presenter.update();
        myHandView.setCards(presenter.getGame().getGameBoard().getPlayerHand(0));
        int clicked = myHandView.getCardClicked();
        if (clicked >= 0) {
            presenter.playCard(clicked);
        }
        if (drawCardButton.popClicked()) {
            presenter.drawCard();
        }
        if (screamUNOButton.popClicked()) {
            presenter.callUNO();
        }
        drawCardButton.update();
        screamUNOButton.update();
        return null;
    }
}
