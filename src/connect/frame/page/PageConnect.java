package connect.frame.page;

import connect.component.GameTile;
import connect.controller.ControllerConnect;
import connect.util.Game;
import connect.util.GameType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PageConnect extends Page {

    public static final String TEXTURE_BOARD = "src/resources/board.jpg";
    public static final int MAX_COLUMN = 7;
    public static final int MAX_ROW = 6;

    private final Game game = new Game();
    private final GameType gameType;
    private final int pointsToWin;

    private boolean gameIsEnded = false;
    private Label lblPoints;
    private GameTile[][] gameTiles;
    private int currentPlayer = 0;

    public PageConnect(GameType gameType, int pointsToWin){
        this.gameType = gameType;
        this.pointsToWin = pointsToWin;
    }

    @Override
    public void draw() throws FileNotFoundException {
        gameTiles = new GameTile[7][6];

        ImageView imvBoard = new ImageView(new Image (new FileInputStream(TEXTURE_BOARD)));
        add(imvBoard);

        lblPoints = new Label("Red : 0\nYellow : 0");
        lblPoints.setLayoutX(-80);
        lblPoints.setLayoutY(50);
        add(lblPoints);

        Button btnQuit = new Button("Main menu");
        btnQuit.setLayoutX(-80);
        btnQuit.setLayoutY(120);
        btnQuit.setOnMouseClicked(ControllerConnect::goToMainMenu);
        add(btnQuit);

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

    public void checkForWinner(int column, int row){
        if(isWin(column, row)){
            if(gameTiles[column][row].getFill().equals(Color.RED))
                game.incrementRed();
            else
                game.incrementYellow();

            int redPoints = game.getPoints()[0];
            int yellowPoints = game.getPoints()[1];

            lblPoints.setText("Red : " + redPoints +"\nYellow : " + yellowPoints);

            if(redPoints == pointsToWin){
                displayWinner(0);
            }
            else if(yellowPoints == pointsToWin){
                displayWinner(1);
            }
            else {
                for (int i = 0; i < gameTiles.length; i++) {
                    for (int e = 0; e < gameTiles[i].length; e++) {
                        gameTiles[i][e].setFill(Color.WHITE);
                    }
                }
            }
        }
    }

    public void displayWinner(int player){
        gameIsEnded = true;
        lblPoints.setText(lblPoints.getText() + "\n" + ((player == 0) ? "Red" : "Yellow") + " player wins !");
    }

    public boolean isWin(int column, int row){
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

    public int getNeighborChipsHorizontally(int column, int row, int oldColumn){
        int correctChip = 1;

        final int previousColumn = column - 1;
        final int nextColumn = column + 1;

        if(column > 0 && previousColumn != oldColumn && gameTiles[previousColumn][row].getFill().equals(gameTiles[column][row].getFill()))
            correctChip += getNeighborChipsHorizontally(previousColumn, row, column);

        else if(column < MAX_COLUMN - 1 && nextColumn != oldColumn && gameTiles[nextColumn][row].getFill().equals(gameTiles[column][row].getFill()))
            correctChip += getNeighborChipsHorizontally(nextColumn, row, column);

        return correctChip;
    }

    public int getNeighborChipsVertically(int column, int row, int oldRow){
        int correctChip = 1;

        final int previousRow = row - 1;
        final int nextRow = row + 1;

        if(row > 0 && previousRow != oldRow && gameTiles[column][previousRow].getFill().equals(gameTiles[column][row].getFill()))
            correctChip += getNeighborChipsVertically(column, previousRow, row);

        else if(row < MAX_ROW - 1 && nextRow != oldRow && gameTiles[column][nextRow].getFill().equals(gameTiles[column][row].getFill()))
            correctChip += getNeighborChipsVertically(column, nextRow, row);

        return correctChip;
    }

    public int getNeighborChipsLeftDiagonal(int column, int row, int oldColumn, int oldRow){
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

    public int getNeighborChipsRightDiagonal(int column, int row, int oldColumn, int oldRow){
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

    public Color getCurrentColor(){
        return currentPlayer == 0 ? Color.RED : Color.YELLOW;
    }

    public void switchPlayer(){
        currentPlayer = currentPlayer == 0 ? 1 : 0;
    }

    public boolean isGameIsEnded() {
        return gameIsEnded;
    }

    public GameTile[][] getGameTiles() {
        return gameTiles;
    }
}
