package tcp_uno.game;

/**
 * <code>CardEffect</code> is an enumeration that stores all the possible values
 * for <code>Card</code> effects
 */
public enum CardEffect {
    NUM_0(0),
    NUM_1(1),
    NUM_2(2),
    NUM_3(3),
    NUM_4(4),
    NUM_5(5),
    NUM_6(6),
    NUM_7(7),
    NUM_8(8),
    NUM_9(9),
    REVERSE(20),
    SKIP(20),
    DRAW_2(20),
    WILD(50),
    WILD_DRAW_4(50);

    private final int score;

    private CardEffect(int score) {
        this.score = score;
    }

    /**
     * Gets the score associated with the given effect
     * 
     * @return the score
     */
    public int getScore() {
        return score;
    }
}
