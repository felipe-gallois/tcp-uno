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
    TextButton contextButton;
    TextButton challengeButton;
    TextButton skipChallengeButton;

    UNOButton screamUNOButton;
    tcp_uno.components.Card deck_card;

    RoundSummary rs;

    public GameView() {
        background = new Background();
        background.setTexture(LoadTexture("resources/Menu.png"));
        presenter = new GamePresenter();
        presenter.newGame();
        myHandView = new MyHandView();

        contextButton = new TextButton("", 10, 500, 20, WHITE);
        contextButton.setHoverColor(RED);

        challengeButton = new TextButton("Challenge +4", 10, 400, 20, WHITE);
        challengeButton.setHoverColor(RED);

        skipChallengeButton = new TextButton("Don't challenge", 10, 450, 20, WHITE);
        skipChallengeButton.setHoverColor(RED);

        screamUNOButton = new UNOButton();
        screamUNOButton.setX(10);
        screamUNOButton.setY(550);

        rs = null;
    }

    @Override
    public void display() {
        BeginDrawing();

        if (rs != null) {
            rs.display();
        } else {
            background.display();
            background.display();
            displayDeck();
            displayHand();
            if (presenter.canDrawACard()) {
                contextButton.setText("Draw Card");
                contextButton.display();
            } else if (presenter.canSkipTurn()) {
                contextButton.setText("Skip Turn");
                contextButton.display();
            }
            if (presenter.canChallengeDraw4()) {
                challengeButton.display();
                skipChallengeButton.display();
            }

            if (presenter.canSayUno()) {
                screamUNOButton.display();
            }

            BrokenConsoleFont brokenConsoleFont = new BrokenConsoleFont();

            for (int i = 0; i < 4; i++) {
                if (i == presenter.getGame().getGameBoard().getCurrentPlayerIdx())
                    brokenConsoleFont.drawText("Player " + i + " Cards: " + presenter.getGame().getGameBoard().getPlayer(i).handSize(),
                            810, 100 + i * 50, 24, 1, RED
                    );
                else
                    brokenConsoleFont.drawText("Player " + i + " Cards: " + presenter.getGame().getGameBoard().getPlayer(i).handSize(), 810, 100 + i * 50, 24, 1, WHITE);

            }
        }
        EndDrawing();
    }

    public void displayDeck() {
        Card topCard = presenter.getGame().getTopCard();
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

        if (presenter.getGame().isRoundOver()) {
            presenter.updateScores();
            if (rs == null) {
                rs = new RoundSummary(presenter);
            }
        }
        if (rs != null) {
            rs.update();
            if (rs.shouldExit()) {
                return AppState.EXIT;
            }
            if (rs.shouldContinue()) {
                rs = null;
                presenter.nextRound();
            }
        }

        myHandView.setCards(presenter.getHand());
        myHandView.update();
        presenter.update();
        int clicked = myHandView.getCardClicked();
        if (clicked >= 0) {
            presenter.playCard(clicked);
        }

        if (contextButton.popClicked()) {
            if (presenter.canDrawACard()) {
                presenter.drawCard();
            } else if (presenter.canSkipTurn()) {
                presenter.skipTurn();
            }
        }
        if (screamUNOButton.popClicked()) {
            presenter.callUNO();
        }

        if (challengeButton.popClicked() && presenter.canChallengeDraw4()) {
            presenter.challengeDraw4();
        }
        if (skipChallengeButton.popClicked() && presenter.canChallengeDraw4()) {
            presenter.skipChallenge();
        }
        contextButton.update();
        challengeButton.update();
        skipChallengeButton.update();
        screamUNOButton.update();
        return null;
    }
}
