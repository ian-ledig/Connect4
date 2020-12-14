package connect.frame.page;

import connect.controller.ControllerMain;
import connect.util.GameType;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PageMain extends Page {

    public static final String TEXTURE_LOGO = "src/resources/logo.png";

    @Override
    public void draw() throws FileNotFoundException {

        ImageView logo = new ImageView(new Image(new FileInputStream(TEXTURE_LOGO)));
        logo.setLayoutY(-520);
        add(logo);

        ComboBox cbxGameType = new ComboBox<>();
        cbxGameType.setLayoutX(185);
        cbxGameType.setLayoutY(-230);
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
        btnPlay.setLayoutX(185);
        btnPlay.setLayoutY(-190);
        btnPlay.setPrefSize(180, 20);
        btnPlay.setOnAction(ControllerMain::play);
        add(btnPlay);
    }
}
