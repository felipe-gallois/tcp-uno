package tcp_uno.components;

import com.raylib.Raylib;
import tcp_uno.game.CardColor;
import tcp_uno.game.CardValue;

import static com.raylib.Jaylib.WHITE;

public class Card {

    public static final float ASPECT_RATIO = 722f / 538f;

    public static final Raylib.Texture cardsTexture = Raylib.LoadTexture("resources/spritesheet_uno_large.png");
    private static final int LEFT_MARGIN = 206;
    private static final int CARD_WIDTH = 538;
    private static final int BETWEEN_CARD_H_MARGIN = 45;
    private static final int TOP_MARGIN = 133;
    private static final int CARD_HEIGHT = 755;
    private static final int BETWEEN_CARD_V_MARGIN = 62;
    final int CARD_PER_ROW = 14;
    private final int cardHeight;
    private final int cardWidth;
    private int x, y;
    private final CardColor color;
    private final CardValue value;
    private final boolean isHidden;

    public Card(tcp_uno.game.Card card, int cardSize, boolean isHidden) {
        this.color = card.getColor();
        this.value = card.getValue();
        this.cardHeight = cardSize;
        this.cardWidth = (int) (cardSize / ASPECT_RATIO);
        this.isHidden = isHidden;
    }

    public void display() {
        if (!isHidden) {
            int colorOffset = calculateColorOffset();
            int valueOffset = calculateValueOffset();
            int offset = colorOffset + valueOffset;
            int row = offset / CARD_PER_ROW;
            int column = offset % CARD_PER_ROW;
            int sourceX = LEFT_MARGIN + column * CARD_WIDTH + BETWEEN_CARD_H_MARGIN * column;
            int sourceY = TOP_MARGIN + row * CARD_HEIGHT + BETWEEN_CARD_V_MARGIN * row;
            Raylib.Rectangle srcRec = new Raylib.Rectangle().x(sourceX).y(sourceY).width(CARD_WIDTH).height(CARD_HEIGHT);
            Raylib.Rectangle destRec = new Raylib.Rectangle().x(x).y(y).width(cardWidth).height(cardHeight);
            Raylib.DrawTexturePro(cardsTexture, srcRec, destRec, new Raylib.Vector2(), 0, WHITE);
        } else {
        }
    }

    private int calculateValueOffset() {
        switch (value) {
            case SKIP:
                return 0;
            case REVERSE:
                return 1;
            case DRAW_2:
                return 2;
            case WILD:
                return 13;
            case WILD_DRAW_4:
                return CARD_PER_ROW + 13;
            default:
                return value.getScore() + 3;
        }
    }

    private int calculateColorOffset() {
        switch (color) {
            case RED:
                return 0;
            case BLUE:
                return CARD_PER_ROW;
            case GREEN:
                return 2 * CARD_PER_ROW;
            case YELLOW:
                return 3 * CARD_PER_ROW;
            default:
                return 0;
        }
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
