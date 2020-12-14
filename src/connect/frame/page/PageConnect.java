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

    public static GameTile[][] gameTiles = new GameTile[7][6];
    public static int currentPlayer = 0;

    public PageConnect(GameType gameType){
        this.gameType = gameType;
    }

    @Override
    public void draw() throws FileNotFoundException {
        ImageView board = new ImageView(new Image (new FileInputStream(TEXTURE_BOARD)));
        add(board);

        for (int i = 0; i < 7; i++){
            for(int e = 0; e < 6; e++){
                GameTile tile = new GameTile(113 + i * 92, 73 + e * 91, 38);
                tile.setFill(Color.WHITE);
                tile.setOnMouseClicked(ControllerConnect::addChips);
                gameTiles[i][e] = tile;
                add(tile);
            }
        }
    }
}
