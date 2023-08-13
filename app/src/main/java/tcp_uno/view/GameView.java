package tcp_uno.view;

import tcp_uno.AppState;
import tcp_uno.components.Background;
import tcp_uno.components.BrokenConsoleFont;
import tcp_uno.components.TextButton;
import tcp_uno.components.UNOButton;
import tcp_uno.game.Card;
import tcp_uno.presenter.GamePresenter;

import static com.raylib.Jaylib.*;

public class GameView implements View {

    Background background;
    GamePresenter presenter;
    MyHandView myHandView;
    TextButton drawCardButton;
    UNOButton screamUNOButton;
    tcp_uno.components.Card deck_card;

    public GameView() {
        background = new Background();
        background.setTexture(LoadTexture("resources/Menu.png"));
        presenter = new GamePresenter(this);
        presenter.newGame();
        myHandView = new MyHandView();

        drawCardButton = new TextButton("Draw Card", 10, 500, 20, WHITE);
        drawCardButton.setHoverColor(RED);

        screamUNOButton = new UNOButton();
        screamUNOButton.setX(10);
        screamUNOButton.setY(550);


    }

    @Override
    public void display() {
        BeginDrawing();
        background.display();
        displayDeck();
        displayHand();
        drawCardButton.display();
        screamUNOButton.display();

        //DrawText("Current Player: " + presenter.getGame().getGameBoard().getCurrentPlayerIdx(), 510, 600, 20, WHITE);
        //DrawText("Direction: " + presenter.getGame().getGameBoard().getDirection(), 510, 650, 20, WHITE);

        BrokenConsoleFont brokenConsoleFont = new BrokenConsoleFont();

        for (int i = 0; i < 4; i++) {
            if (i == presenter.getGame().getGameBoard().getCurrentPlayerIdx())
                brokenConsoleFont.drawText("Player " + i + " Score: " + presenter.getGame().getGameBoard().getPlayerHand(i).size(),
                        810, 100 + i * 50, 24, 1, RED
                );
            else
                brokenConsoleFont.drawText("Player " + i + " Score: " + presenter.getGame().getGameBoard().getPlayerHand(i).size(), 810, 100 + i * 50, 24, 1, WHITE);
        }

        EndDrawing();
    }

    public void displayDeck() {

        Card topCard = presenter.getGame().getGameBoard().getTopCard();
        deck_card = new tcp_uno.components.Card(topCard, 120, false);
        deck_card.setX(500);
        deck_card.setY(300);
//        DrawText("Top Card: " + topCard.toString(), 10, 10, 20, WHITE);
        deck_card.display();

    }

    public void displayHand() {
        myHandView.display();
    }

    @Override
    public AppState update() {

        myHandView.setCards(presenter.getGame().getGameBoard().getPlayerHand(0));

        myHandView.update();
        presenter.update();
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
