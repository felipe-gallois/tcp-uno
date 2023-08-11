package tcp_uno.view;

import com.raylib.Raylib;

import static com.raylib.Raylib.*;
import static com.raylib.Raylib.MOUSE_BUTTON_LEFT;

public abstract class Button {
    private boolean isClicked;
    private boolean isHovered;
    private boolean isEnabled;

    Button() {
        isClicked = false;
        isHovered = false;
        isEnabled = true;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
    public boolean popClicked() {
        boolean temp = isClicked;
        isClicked = false;
        return temp;
    }

    public boolean getHovered() {
        return isHovered;
    }

    public boolean peakClicked() {
        return isClicked;
    }
    public abstract Rectangle getRectangle();
    public abstract void display();
    public void update() {
        if (isEnabled) {
            Raylib.Vector2 mousePos = GetMousePosition();
            isHovered = CheckCollisionPointRec(mousePos, getRectangle());
            // isClicked should not be unset until it is popped
            isClicked = isClicked || (isHovered && IsMouseButtonPressed(MOUSE_BUTTON_LEFT));
        }
    }
}
