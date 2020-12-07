package connect.controller;

import connect.frame.FrameConnect;
import connect.frame.page.PageConnect;
import connect.frame.page.PageMain;
import connect.util.GameType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ControllerMain implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        Object button = actionEvent.getSource();

        if(button instanceof Button){
            Node page = ((Button) button).getParent();

            if(page instanceof PageMain){
                Node node = ((PageMain) page).getChildren().get(0);

                if(node instanceof ComboBox)
                    FrameConnect.setShowedPage(new PageConnect(GameType.getGameType(((ComboBox<?>) node).getSelectionModel().getSelectedItem().toString())));
            }
        }
    }
}
