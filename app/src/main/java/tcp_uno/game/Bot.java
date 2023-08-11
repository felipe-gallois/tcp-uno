package tcp_uno.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Bot {
    private static final Random randomizer = new Random();
    private static final float challengeChance = 0.05f;

    public GameAction selectAction(ArrayList<GameAction> possibleActions) {
        GameAction screamUNOAction = findScreamUNO(possibleActions);
        if (screamUNOAction != null) return screamUNOAction;

        GameAction challengeDraw4Action = findChallengeDraw4(possibleActions);
        if (challengeDraw4Action != null) {
            float randomFloat = randomizer.nextFloat();
            if (randomFloat < challengeChance) return challengeDraw4Action;
        }

        ArrayList<GameAction> playableCards = getPlayableCards(possibleActions);
        if (playableCards.isEmpty() == false) {
            Collections.shuffle(playableCards);
            return playableCards.get(0);
        }

        GameAction drawCardsAction = findDrawCards(possibleActions);
        if (drawCardsAction != null) return drawCardsAction;

        return null;
    }

    private GameAction findScreamUNO(ArrayList<GameAction> possibleActions) {
        for (GameAction action : possibleActions) {
            if (action instanceof ScreamUNO) return action;
        }

        return null;
    }

    private GameAction findChallengeDraw4(ArrayList<GameAction> possibleActions) {
        for (GameAction action : possibleActions) {
            if (action instanceof ChallengeDraw4) return action;
        }

        return null;
    }

    private ArrayList<GameAction> getPlayableCards(ArrayList<GameAction> possibleActions) {
        ArrayList<GameAction> candidateCards = new ArrayList<GameAction>(); 
        for (GameAction action : possibleActions) {
            if (action instanceof PlayCard) {
                candidateCards.add(action);
            }
        }

        return candidateCards;
    }

    private GameAction findDrawCards(ArrayList<GameAction> possibleActions) {
        for (GameAction action : possibleActions) {
            if (action instanceof DrawCards) return action;
        }

        return null;
    }
}
