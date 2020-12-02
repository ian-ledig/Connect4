package connect.component;

import javafx.scene.Group;
import javafx.scene.Node;

public class GameGroup extends Group {

    public void add(Node node) {
        getChildren().add(node);
    }

    public void remove(Node node) {
        getChildren().remove(node);
    }
}
