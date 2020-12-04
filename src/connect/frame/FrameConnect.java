package connect.frame;

import connect.component.GameGroup;
import connect.frame.page.PageConnect;
import connect.frame.page.PageMain;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FrameConnect extends Application {

    public static final String TITLE = "Connect 4";

    public static GameGroup showedPage;

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE);

        StackPane root = new StackPane();
        stage.setScene(new Scene(root,900, 600));
        stage.setResizable(false);
        stage.show();

        showedPage = new PageMain();
        root.getChildren().add(this.showedPage);
    }
}
