package tcp_uno;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import tcp_uno.game.ChallengeDraw4;
import tcp_uno.game.DrawCards;
import tcp_uno.game.GameAction;
import tcp_uno.game.PlayCard;
import tcp_uno.game.ScreamUNO;
import tcp_uno.game.Bot;

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
        actionsList.add(new DrawCards(null, null, 0));
        actionsList.add(new PlayCard(null, null, null, null));
        actionsList.add(new PlayCard(null, null, null, null));
        actionsList.add(new PlayCard(null, null, null, null));
        actionsList.add(new ChallengeDraw4(null, null, null));
        actionsList.add(new ScreamUNO(null, null));

        assertEquals(actionsList.get(actionsList.size() - 1),
                     bot.selectAction(actionsList));

        actionsList.remove(actionsList.size() - 1);
        assertTrue(bot.selectAction(actionsList) instanceof ChallengeDraw4 ||
                   bot.selectAction(actionsList) instanceof PlayCard);

        for (int i = 0; i < 3; i++) {
            actionsList.remove(actionsList.size() - 1);
            assertTrue(bot.selectAction(actionsList) instanceof PlayCard);
        }

        actionsList.remove(actionsList.size() - 1);
        assertEquals(actionsList.get(actionsList.size() - 1),
                     bot.selectAction(actionsList));

        actionsList.remove(actionsList.size() - 1);
        assertNull(bot.selectAction(actionsList));
    }
}
