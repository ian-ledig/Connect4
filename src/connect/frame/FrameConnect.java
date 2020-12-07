package connect.frame;

import connect.frame.page.Page;
import connect.frame.page.PageMain;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FrameConnect extends Application {

    public static final String TITLE = "Connect 4";
    public static final StackPane ROOT = new StackPane();

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE);

        stage.setScene(new Scene(ROOT,900, 600));
        stage.setResizable(false);
        stage.show();

        setShowedPage(new PageMain());
    }

    public static void setShowedPage(Page showedPage) {
        FrameConnect.ROOT.getChildren().clear();
        FrameConnect.ROOT.getChildren().add(showedPage);
    }
}
