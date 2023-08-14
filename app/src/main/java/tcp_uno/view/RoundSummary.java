package tcp_uno.view;

import static com.raylib.Raylib.*;

import tcp_uno.components.ImageButton;
import tcp_uno.components.Background;
import tcp_uno.presenter.GamePresenter;

public class RoundSummary {
    private final ImageButton exitButton;
    private final ImageButton continueButton;

    private final Background background;
    private final ScoreCounter previousScore;
    private final ScoreCounter roundScore;
    private final ScoreCounter totalScore;

    private boolean wantsToExit;

    private boolean wantsToContinue;

    public RoundSummary(GamePresenter presenter) {
        int prevScore = presenter.getPrevScore();
        int currentScore = presenter.getCurrentScore();
        background = new Background();

        previousScore = new ScoreCounter(false);
        previousScore.setX(586);
        previousScore.setY(312);
        previousScore.setValue(prevScore);

        roundScore = new ScoreCounter(true);
        roundScore.setX(586);
        roundScore.setY(368);
        roundScore.setValue(currentScore - prevScore);

        totalScore = new ScoreCounter(false);
        totalScore.setX(586);
        totalScore.setY(424);
        totalScore.setValue(currentScore);

        exitButton = new ImageButton();
        exitButton.setTexture(LoadTexture("resources/EXIT.png"));
        exitButton.setTextureHeight(80);
        exitButton.setTextureWidth(169);
        exitButton.setEnabled(true);
        exitButton.setX(250);
        exitButton.setY(525);

        continueButton = new ImageButton();
        continueButton.setTexture(LoadTexture("resources/CONTINUAR.png"));
        continueButton.setTextureHeight(80);
        continueButton.setTextureWidth(399);
        continueButton.setEnabled(true);
        continueButton.setX(475);
        continueButton.setY(525);

        wantsToExit = false;
        wantsToContinue = false;

        setBackgroundType(presenter.playerWonRound());
    }

    public void setBackgroundType(boolean roundWon) {
        if (roundWon) {
            background.setTexture(LoadTexture("resources/RoundWin-bg.png"));
        } else {
            background.setTexture(LoadTexture("resources/RoundLose-bg.png"));
        }
    }

    public void setPreviousScore(int score) {
        previousScore.setValue(score);
        calculateTotalScore();
    }

    public void setRoundScore(int score) {
        roundScore.setValue(score);
        calculateTotalScore();
    }

    private void calculateTotalScore() {
        totalScore.setValue(previousScore.getScore() + roundScore.getScore());
    }

    public void display() {
        background.display();
        previousScore.display();
        roundScore.display();
        totalScore.display();
        exitButton.display();
        continueButton.display();
    }

    public void update() {
        if (continueButton.popClicked()) {
            wantsToContinue = true;
        }
        if (exitButton.popClicked()) {
            wantsToExit = true;
        }

        continueButton.update();
        exitButton.update();
    }

    public boolean shouldContinue() {
        return wantsToContinue;
    }

    public boolean shouldExit() {
        return wantsToExit;
    }
}
