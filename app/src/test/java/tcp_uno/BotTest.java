package tcp_uno;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;

import tcp_uno.game.ChallengeDraw4;
import tcp_uno.game.DrawCards;
import tcp_uno.game.GameAction;
import tcp_uno.game.PlayCard;
import tcp_uno.game.ScreamUNO;
import tcp_uno.game.SkipTurn;
import tcp_uno.game.Bot;

import static org.junit.Assert.*;

public class BotTest {
    private static Bot bot;
    private static ArrayList<GameAction> actionsList;

    @BeforeClass
    public static void setup() {
        bot = new Bot();
        actionsList = new ArrayList<GameAction>();
    }

    @Test
    public void testBotChoosesRightActions() {
        actionsList.add(new SkipTurn(null, null));
        actionsList.add(new DrawCards(null, null, 0));
        actionsList.add(new PlayCard(null, null, null, null));
        actionsList.add(new PlayCard(null, null, null, null));
        actionsList.add(new PlayCard(null, null, null, null));
        actionsList.add(new ChallengeDraw4(null, null, null));
        actionsList.add(new ScreamUNO(null, null));

        Optional<GameAction> action = bot.selectAction(actionsList);
        assertTrue(action.isPresent());
        assertEquals(actionsList.get(actionsList.size() - 1), action.get());

        actionsList.remove(actionsList.size() - 1);
        action = bot.selectAction(actionsList);
        assertTrue(action.isPresent());
        assertTrue(action.get() instanceof ChallengeDraw4 ||
                   action.get() instanceof PlayCard);

        for (int i = 0; i < 3; i++) {
            actionsList.remove(actionsList.size() - 1);
            action = bot.selectAction(actionsList);
            assertTrue(action.isPresent());
            assertTrue(action.get() instanceof PlayCard);
        }

        actionsList.remove(actionsList.size() - 1);
        action = bot.selectAction(actionsList);
        assertTrue(action.isPresent());
        assertEquals(actionsList.get(actionsList.size() - 1), action.get());

        actionsList.remove(actionsList.size() - 1);
        action = bot.selectAction(actionsList);
        assertTrue(action.isPresent());
        assertTrue(action.get() instanceof SkipTurn);
    }

    @Test
    public void testEmptyActionsListReturnsNull() {
        actionsList.clear();
        Optional<GameAction> action = bot.selectAction(actionsList);
        assertTrue(action.isEmpty());
    }
}
