package tcp_uno.game;

import java.util.List;

public enum CardValue {
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

    public final static List<CardValue> ACTION_VALUES = List.of(SKIP, REVERSE, DRAW_2, WILD, WILD_DRAW_4);

    CardValue(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
