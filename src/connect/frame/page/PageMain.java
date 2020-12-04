package connect.frame.page;

import connect.controller.ControllerMain;
import connect.util.GameType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PageMain extends Page {

    public static final String TEXTURE_LOGO = "src/resources/logo.png";

    @Override
    public void draw() throws FileNotFoundException {
        ComboBox cbxGameType = new ComboBox<>();
        cbxGameType.setLayoutY(-20);
        cbxGameType.setPrefSize(180, 20);
        cbxGameType.setItems(
            FXCollections.observableArrayList(
                    Stream.of(GameType.values())
                    .map(GameType::getName)
                    .collect(Collectors.toList())
            )
        );
        cbxGameType.getSelectionModel().select(0);
        add(cbxGameType);

        Button btnPlay = new Button("Play");
        btnPlay.setLayoutY(20);
        btnPlay.setPrefSize(180, 20);

        btnPlay.setOnAction(new ControllerMain());
        add(btnPlay);

        ImageView logo = new ImageView(new Image(new FileInputStream(TEXTURE_LOGO)));
        add(logo);
    }
}
