package tcp_uno.view;

import java.util.ArrayList;

public class ScoreCounter {
    private static final int MAX_SLOTS = 8;

    private final int slotsNumber;
    private final ArrayList<NumberSlot> slots;
    private final boolean hasSign;

    private int value;

    public ScoreCounter(int slotsNumber, boolean hasSign) {
        this.slotsNumber = slotsNumber;
        if (this.slotsNumber > MAX_SLOTS) {
            throw new ScoreCounterTooBig();
        }
        if ((hasSign && this.slotsNumber < 2) ||
            (!hasSign && this.slotsNumber < 1)) {
            throw new ScoreCounterTooSmall();
        }

        slots = new ArrayList<NumberSlot>();
        for (int i = 0; i < slotsNumber; i++) {
            slots.add(new NumberSlot());
        }

        this.hasSign = hasSign;
    }

    public void setValue(int value) {
        int availableSlots = hasSign ? slotsNumber - 1 : slotsNumber;
        int maxValue = (int) Math.pow(10, availableSlots) - 1;
        if (value < maxValue) {
            this.value = value;
        } else {
            this.value = maxValue;
        }

        updateSlots();
    }

    public void setX(int x) {
        int offset = 0;
        for (NumberSlot slot : slots) {
            slot.setX(x + offset);
            offset += slot.getTextureWidth();
        }
    }

    public void setY(int y) {
        for (NumberSlot slot : slots) {
            slot.setY(y);
        }
    }

    private void updateSlots() {
        if (hasSign) {
            if (value >= 0) {
                slots.get(0).setCharacterId(10);
            } else {
                slots.get(0).setCharacterId(11);
            }
        }

        int startPos = hasSign ? 1 : 0;
        int score = value;
        for (int i = slotsNumber - 1; i >= startPos; i--) {
            slots.get(i).setCharacterId(score % 10);
            score /= 10;
        }
    }

    public int getScore() {
        return value;
    }

    public void display() {
        for (NumberSlot slot : slots) {
            slot.display();
        }
    }
}
