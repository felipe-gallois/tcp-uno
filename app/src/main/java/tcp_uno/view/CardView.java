package tcp_uno.view;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.raylib.Raylib.Color;
import tcp_uno.game.CardColor;
import tcp_uno.game.CardValue;


public class CardView {
    
    private int cardHeight;
    private int cardWidth; 
    private int x, y;
    private CardColor color;
    private CardValue value;
    private boolean isHidden;

    //CardView(cor, valor, tamanho)
    //tamanho é a altura da carta
    public CardView(CardColor color, CardValue value, int cardSize, boolean isHidden) {
        this.color = color;
        this.value = value;
        this.cardHeight = cardSize;
        this.cardWidth = cardSize/2;
        this.isHidden = isHidden;
    }
    
    private Color convertColor(CardColor color) {
        switch (color) {
            case BLACK:
                return Jaylib.BLACK;
            case BLUE:
                return Jaylib.BLUE;
            case GREEN:
                return Jaylib.GREEN;
            case RED:
                return Jaylib.RED;
            case YELLOW:
                return Jaylib.YELLOW;
        }
        return null;
    }
    
    private String cardText(CardValue value) {
        switch (value) {
            case NUM_0:
                return " 0";
            case NUM_1:
                return " 1";
            case NUM_2:
                return " 2";
            case NUM_3:
                return " 3";
            case NUM_4:
                return " 4";
            case NUM_5:
                return " 5";
            case NUM_6:
                return " 6";
            case NUM_7:
                return " 7";
            case NUM_8:
                return " 8";
            case NUM_9:
                return " 9";
            case DRAW_2:
                return "+2";
            case REVERSE:
                return "rev";
            case SKIP:
                return " x";
            case WILD:
                return "wild";
            case WILD_DRAW_4:
                return "+4";
        }
        return null;
    }

    public void display() {
        int fontSize = cardWidth/2;
        if (!isHidden) {
            Raylib.DrawRectangle(x, y, cardWidth, cardHeight, convertColor(color));
            Raylib.DrawCircle(x+cardWidth/2, y+cardHeight/2, cardWidth/2, Jaylib.WHITE);
            //Para o texto ficar centralizado, a altura é metade da altura da carta menos metade do tamanho da fonte
            Raylib.DrawText(cardText(value), x+fontSize/3, y+(cardHeight/2)-fontSize/2, fontSize, Jaylib.BLACK);
        } else {
            Raylib.DrawRectangle(x, y, cardWidth, cardHeight, Jaylib.GRAY);
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
