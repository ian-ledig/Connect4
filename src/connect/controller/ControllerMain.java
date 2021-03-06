package connect.controller;

import connect.frame.FrameConnect;
import connect.frame.page.PageConnect;
import connect.frame.page.PageMain;
import connect.util.GameType;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ControllerMain {

    public static void play(ActionEvent actionEvent) {
        Object button = actionEvent.getSource();

        if(button instanceof Button){
            Node page = ((Button) button).getParent();

            if(page instanceof PageMain){
                PageMain pageMain = (PageMain) page;
                Node node = pageMain.getChildren().get(1);

                if(node instanceof ComboBox) {
                    try {
                        FrameConnect.setShowedPage(new PageConnect(GameType.getGameType(((ComboBox<?>) node).getSelectionModel().getSelectedItem().toString()), Integer.parseInt(pageMain.txfScoreToWin.getText())));
                    } catch (IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
