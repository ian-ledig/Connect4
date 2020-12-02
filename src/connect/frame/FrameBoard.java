package connect.frame;

import connect.component.GameGroup;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FrameBoard extends Application {

    public static final String TITLE = "Connect 4";
    public static final String TEXTURE_BOARD = "D:\\documents\\lessons\\a31\\a31-project\\src\\resources";

    private GameGroup grpRoot = new GameGroup();

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE);

        StackPane root = new StackPane();
        stage.setScene(new Scene(root,900, 600));
        stage.setResizable(false);
        stage.show();

        root.getChildren().add(this.grpRoot);

        try {
            draw();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void draw() throws FileNotFoundException {
        ImageView board = new ImageView(new Image(new FileInputStream(T)));
        this.grpRoot.add(board);
    }
}
