package connect.frame.page;

import connect.component.GameTile;
import connect.controller.ControllerConnect;
import connect.util.Game;
import connect.util.GameType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PageConnect extends Page {

    public static final String TEXTURE_BOARD = "src/resources/board.jpg";
    public static final int MAX_COLUMN = 7;
    public static final int MAX_ROW = 6;

    public final GameType gameType;
    public final Game game;

    public static GameTile[][] gameTiles = new GameTile[7][6];
    public static int currentPlayer = 0;

    public PageConnect(GameType gameType){
        this.gameType = gameType;
        this.game = new Game();
    }

    @Override
    public void draw() throws FileNotFoundException {
        ImageView board = new ImageView(new Image (new FileInputStream(TEXTURE_BOARD)));
        add(board);

        for (int i = 0; i < MAX_COLUMN; i++){
            for(int e = 0; e < MAX_ROW; e++){
                GameTile tile = new GameTile(113 + i * 92, 73 + e * 91, 38);
                tile.setFill(Color.WHITE);
                tile.setOnMouseClicked(ControllerConnect::addChips);
                gameTiles[i][e] = tile;
                add(tile);
            }
        }
    }

    public static void checkForWinner(int column, int row){
        if(PageConnect.isWin(column, row))
            System.out.println("WIN !");
    }

    public static boolean isWin(int column, int row){
        boolean result = false;

        if(
                getNeighborChipsHorizontally(column, row, column) == 4 ||
                getNeighborChipsVertically(column, row, row) == 4 ||
                getNeighborChipsLeftDiagonal(column, row, column, row) == 4 ||
                getNeighborChipsRightDiagonal(column, row, column, row) == 4
        )
            result = true;

        return result;
    }

    public static int getNeighborChipsHorizontally(int column, int row, int oldColumn){
        int correctChip = 1;

        final int previousColumn = column - 1;
        final int nextColumn = column + 1;

        if(column > 0 && previousColumn != oldColumn && gameTiles[previousColumn][row].getFill().equals(gameTiles[column][row].getFill()))
            correctChip += getNeighborChipsHorizontally(previousColumn, row, column);

        else if(column < MAX_COLUMN - 1 && nextColumn != oldColumn && gameTiles[nextColumn][row].getFill().equals(gameTiles[column][row].getFill()))
            correctChip += getNeighborChipsHorizontally(nextColumn, row, column);

        return correctChip;
    }

    public static int getNeighborChipsVertically(int column, int row, int oldRow){
        int correctChip = 1;

        final int previousRow = row - 1;
        final int nextRow = row + 1;

        if(row > 0 && previousRow != oldRow && gameTiles[column][previousRow].getFill().equals(gameTiles[column][row].getFill()))
            correctChip += getNeighborChipsVertically(column, previousRow, row);

        else if(row < MAX_ROW - 1 && nextRow != oldRow && gameTiles[column][nextRow].getFill().equals(gameTiles[column][row].getFill()))
            correctChip += getNeighborChipsVertically(column, nextRow, row);

        return correctChip;
    }

    public static int getNeighborChipsLeftDiagonal(int column, int row, int oldColumn, int oldRow){
        int correctChip = 1;

        final int previousColumn = column - 1;
        final int nextColumn = column + 1;
        final int previousRow = row - 1;
        final int nextRow = row + 1;

        if(column > 0 && row > 0 && previousColumn != oldColumn && previousRow != oldRow && gameTiles[previousColumn][previousRow].getFill().equals(gameTiles[column][row].getFill()))
            correctChip += getNeighborChipsLeftDiagonal(previousColumn, previousRow, column, row);

        else if(column < MAX_COLUMN - 1 && row < MAX_ROW - 1 && nextColumn != oldColumn && nextRow != oldRow && gameTiles[nextColumn][nextRow].getFill().equals(gameTiles[column][row].getFill()))
            correctChip += getNeighborChipsLeftDiagonal(nextColumn, nextRow, column, row);

        return correctChip;
    }

    public static int getNeighborChipsRightDiagonal(int column, int row, int oldColumn, int oldRow){
        int correctChip = 1;

        final int previousColumn = column - 1;
        final int nextColumn = column + 1;
        final int previousRow = row - 1;
        final int nextRow = row + 1;

        if(column > 0 && row < MAX_ROW - 1 && previousColumn != oldColumn && nextRow != oldRow && gameTiles[previousColumn][nextRow].getFill().equals(gameTiles[column][row].getFill()))
            correctChip += getNeighborChipsRightDiagonal(previousColumn, nextRow, column, row);

        else if(column < MAX_COLUMN - 1 && row > 0 && nextColumn != oldColumn && previousRow != oldRow && gameTiles[nextColumn][previousRow].getFill().equals(gameTiles[column][row].getFill()))
            correctChip += getNeighborChipsRightDiagonal(nextColumn, previousRow, column, row);

        return correctChip;
    }

    public static Color getCurrentColor(){
        return currentPlayer == 0 ? Color.RED : Color.YELLOW;
    }

    public static void switchPlayer(){
        currentPlayer = currentPlayer == 0 ? 1 : 0;
    }
}
