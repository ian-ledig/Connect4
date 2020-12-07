package connect.frame.page;

import connect.component.GameTile;
import connect.controller.ControllerConnect;
import connect.util.GameType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PageConnect extends Page {

    public static final String TEXTURE_BOARD = "src/resources/board.jpg";
    public final GameType gameType;

    public PageConnect(GameType gameType){
        this.gameType = gameType;
    }

    @Override
    public void draw() throws FileNotFoundException {
        ImageView board = new ImageView(new Image (new FileInputStream(TEXTURE_BOARD)));
        add(board);

        for (int i = 0; i < 6; i++){
            for(int e = 0; e < 7; e++){
                GameTile tile = new GameTile(113 + e * 92, 73 + i * 91, 38);
                tile.setFill(Color.WHITE);
                tile.setOnMouseClicked(ControllerConnect::addChips);
            }
        }
    }
}
