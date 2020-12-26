package connect.component;

import connect.controller.ControllerConnect;
import javafx.scene.paint.Color;

public class GameGrid {
    public static final int COLUMN_NUMBER = 7;
    public static final int ROW_NUMBER = 6;

    private GameTile[][] tiles;

    public GameGrid(){
        tiles = new GameTile[COLUMN_NUMBER][ROW_NUMBER];

        for (int i = 0; i < COLUMN_NUMBER; i++){
            for(int e = 0; e < ROW_NUMBER; e++){
                GameTile tile = new GameTile(113 + i * 92, 73 + e * 91, 38);
                tile.setFill(Color.WHITE);
                tile.setOnMouseClicked(ControllerConnect::addChips);
                setTile(i, e, tile);
            }
        }
    }

    public void setTile(int column, int row, GameTile newTile){
        tiles[column][row] = newTile;
    }

    public GameTile getGameTile(int column, int row){
        return tiles[column][row];
    }

    public GameTile[][] getTiles() {
        return tiles;
    }

    public int getColumn(GameTile tile){
        boolean end = false;
        int indexColumn = 0;
        int indexRow = 0;
        while(!end && indexRow <= 41){
            if(tiles[indexColumn][indexRow].equals(tile)){
                end = true;
            }
            else if(indexRow == 5){
                indexRow = 0;
                indexColumn++;
            }
            else
                indexRow++;
        }
        return indexColumn;
    }
}