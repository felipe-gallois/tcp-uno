package tcp_uno.game;

import java.util.*;

public class Bot {
    private static final Random randomizer = new Random();
    private static final float challengeChance = 0.05f;

    public Optional<GameAction> selectAction(List<GameAction> possibleActions) {
        GameAction screamUNOAction = findScreamUNO(possibleActions);
        if (screamUNOAction != null) return Optional.of(screamUNOAction);

        GameAction challengeDraw4Action = findChallengeDraw4(possibleActions);
        if (challengeDraw4Action != null) {
            float randomFloat = randomizer.nextFloat();
            if (randomFloat < challengeChance) return Optional.of(challengeDraw4Action);
        }

        ArrayList<GameAction> playableCards = getPlayableCards(possibleActions);
        if (!playableCards.isEmpty()) {
            Collections.shuffle(playableCards);
            return Optional.ofNullable(playableCards.get(0));
        }

        GameAction drawCardsAction = findDrawCards(possibleActions);
        if (drawCardsAction != null) return Optional.of(drawCardsAction);

        GameAction skipTurnAction = findSkipTurn(possibleActions);
        if (skipTurnAction != null) return Optional.of(skipTurnAction);

        return Optional.empty();
    }

    private GameAction findScreamUNO(List<GameAction> possibleActions) {
        for (GameAction action : possibleActions) {
            if (action instanceof ScreamUNO) return action;
        }

        return null;
    }

    private GameAction findChallengeDraw4(List<GameAction> possibleActions) {
        for (GameAction action : possibleActions) {
            if (action instanceof ChallengeDraw4) return action;
        }

        return null;
    }

    private ArrayList<GameAction> getPlayableCards(List<GameAction> possibleActions) {
        ArrayList<GameAction> candidateCards = new ArrayList<GameAction>();
        for (GameAction action : possibleActions) {
            if (action instanceof PlayCard) {
                candidateCards.add(action);
            }
        }

        return candidateCards;
    }

    private GameAction findDrawCards(List<GameAction> possibleActions) {
        for (GameAction action : possibleActions) {
            if (action instanceof DrawCards) return action;
        }

        return null;
    }

    private GameAction findSkipTurn(List<GameAction> possibleActions) {
        for (GameAction action : possibleActions) {
            if (action instanceof SkipTurn) return action;
        }

        return null;
    }
}
