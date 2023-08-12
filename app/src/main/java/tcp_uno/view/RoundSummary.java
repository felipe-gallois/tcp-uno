package tcp_uno.view;

import static com.raylib.Raylib.*;

public class RoundSummary {
    private final Button exitButton;
    private final Button continueButton;

    private Background background;
    private ScoreCounter previousScore, roundScore, totalScore;

    private boolean wantsToExit;
    private boolean wantsToContinue;

    public RoundSummary() {
        background = new Background();

        previousScore = new ScoreCounter(3, false);
        previousScore.setX(586);
        previousScore.setY(312);

        roundScore = new ScoreCounter(4, true);
        roundScore.setX(550);
        roundScore.setY(368);

        totalScore = new ScoreCounter(3, false);
        totalScore.setX(586);
        totalScore.setY(424);

        exitButton = new Button();
        exitButton.setTexture(LoadTexture("resources/EXIT.png"));
        exitButton.setTextureHeight(80);
        exitButton.setTextureWidth(169);
        exitButton.setEnabled(true);
        exitButton.setX(250);
        exitButton.setY(525);

        continueButton = new Button();
        continueButton.setTexture(LoadTexture("resources/CONTINUAR.png"));
        continueButton.setTextureHeight(80);
        continueButton.setTextureWidth(399);
        continueButton.setEnabled(true);
        continueButton.setX(475);
        continueButton.setY(525);

        wantsToExit = false;
        wantsToContinue = false;
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
