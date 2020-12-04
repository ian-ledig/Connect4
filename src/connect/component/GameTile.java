package connect.component;

import javafx.scene.shape.Circle;

public class GameTile extends Circle {

    public GameTile(int x, int y, int radius) {
        super(x, y, radius);
    }

    private boolean isEmpty(){
        return getFill() == null;
    }
}
