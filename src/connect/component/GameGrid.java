package connect.component;

import connect.controller.ControllerConnect;
import javafx.scene.paint.Color;

public class GameGrid {
    public static final int ROW_NUMBER = 6;

    private final int columnNumber;

    private GameTile[][] tiles;

    public GameGrid(int columnNumber, boolean isFill){
        this.columnNumber = columnNumber;

        // Usefull to fill the grid with chips
        int redChipCount = columnNumber * ROW_NUMBER / 2;
        int yellowChipCount = columnNumber * ROW_NUMBER / 2;

        tiles = new GameTile[this.columnNumber][ROW_NUMBER];

        for (int i = 0; i < this.columnNumber; i++){
            for(int e = 0; e < ROW_NUMBER; e++){
                GameTile tile = new GameTile(113 + i * 92, 73 + e * 91, 38);

                Color tileColor = Color.WHITE;
                if(isFill) {

                    if ((int)(Math.random() * 2) == 1 && redChipCount != 0) {
                        redChipCount--;
                        tileColor = Color.RED;
                    } else if (yellowChipCount != 0) {
                        yellowChipCount--;
                        tileColor = Color.YELLOW;
                    }
                    else
                        tileColor = Color.RED;
                }
                tile.setFill(tileColor);

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

    public boolean isFull(){
        boolean result = true;

        int indexColumn = 0;
        while(indexColumn < tiles.length && result) {
            int indexRow = 0;
            while (indexRow < tiles[indexColumn].length && result) {
                if (tiles[indexColumn][indexRow].getFill().equals(Color.WHITE))
                    result = false;
                else
                    indexRow++;
            }
            indexColumn++;
        }

        return result;
    }

    public void resetGrid(){
        for (int i = this.columnNumber == 7 ? 0 : 1; i < (this.columnNumber == 7 ? 7 : 8); i++)
            for (int e = 0; e < ROW_NUMBER; e++)
                tiles[i][e].setFill(Color.WHITE);
    }

    public int getColumnNumber() {
        return columnNumber;
    }
}
