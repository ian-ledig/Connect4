package connect.frame.page;

import connect.component.GameGrid;
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

    private final Game game = new Game();
    private final GameType gameType;
    private final int pointsToWin;

    private boolean gameIsEnded = false;
    private Label lblPoints;
    private GameGrid grid;
    private int currentPlayer = 0;

    public PageConnect(GameType gameType, int pointsToWin){
        this.gameType = gameType;
        this.pointsToWin = pointsToWin;
    }

    @Override
    public void draw() throws FileNotFoundException {
        grid = new GameGrid();

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

        for (int i = 0; i < GameGrid.COLUMN_NUMBER; i++) {
            for (int e = 0; e < GameGrid.ROW_NUMBER; e++) {
                add(grid.getGameTile(i, e));
            }
        }
    }

    public void checkForWinner(int column, int row){
        if(isWin(column, row)){
            if(grid.getGameTile(column, row).getFill().equals(Color.RED))
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
                for (int i = 0; i < 7; i++) {
                    for (int e = 0; e < 6; e++) {
                        grid.getGameTile(i, e).setFill(Color.WHITE);
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
                !grid.getTiles()[column][row].getFill().equals(Color.WHITE) && (
                getNeighborChipsHorizontally(column, row, column) == 4 ||
                getNeighborChipsVertically(column, row, row) == 4 ||
                getNeighborChipsLeftDiagonal(column, row, column, row) == 4 ||
                getNeighborChipsRightDiagonal(column, row, column, row) == 4
        ))
            result = true;

        return result;
    }

    public int getNeighborChipsHorizontally(int column, int row, int oldColumn){
        int correctChip = 1;

        final int previousColumn = column - 1;
        final int nextColumn = column + 1;

        if(column > 0 && previousColumn != oldColumn && grid.getGameTile(previousColumn, row).getFill().equals(grid.getGameTile(column, row).getFill()))
            correctChip += getNeighborChipsHorizontally(previousColumn, row, column);

        if(column < GameGrid.COLUMN_NUMBER - 1 && nextColumn != oldColumn && grid.getGameTile(nextColumn, row).getFill().equals(grid.getGameTile(column, row).getFill()))
            correctChip += getNeighborChipsHorizontally(nextColumn, row, column);

        return correctChip;
    }

    public int getNeighborChipsVertically(int column, int row, int oldRow){
        int correctChip = 1;

        final int previousRow = row - 1;
        final int nextRow = row + 1;

        if(row > 0 && previousRow != oldRow && grid.getGameTile(column, previousRow).getFill().equals(grid.getGameTile(column, row).getFill()))
            correctChip += getNeighborChipsVertically(column, previousRow, row);

        if(row < GameGrid.ROW_NUMBER - 1 && nextRow != oldRow && grid.getGameTile(column, nextRow).getFill().equals(grid.getGameTile(column, row).getFill()))
            correctChip += getNeighborChipsVertically(column, nextRow, row);

        return correctChip;
    }

    public int getNeighborChipsLeftDiagonal(int column, int row, int oldColumn, int oldRow){
        int correctChip = 1;

        final int previousColumn = column - 1;
        final int nextColumn = column + 1;
        final int previousRow = row - 1;
        final int nextRow = row + 1;

        if(column > 0 && row > 0 && previousColumn != oldColumn && previousRow != oldRow && grid.getGameTile(previousColumn, previousRow).getFill().equals(grid.getGameTile(column, row).getFill()))
            correctChip += getNeighborChipsLeftDiagonal(previousColumn, previousRow, column, row);

        if(column < GameGrid.COLUMN_NUMBER - 1 && row < GameGrid.ROW_NUMBER - 1 && nextColumn != oldColumn && nextRow != oldRow && grid.getGameTile(nextColumn, nextRow).getFill().equals(grid.getGameTile(column, row).getFill()))
            correctChip += getNeighborChipsLeftDiagonal(nextColumn, nextRow, column, row);

        return correctChip;
    }

    public int getNeighborChipsRightDiagonal(int column, int row, int oldColumn, int oldRow){
        int correctChip = 1;

        final int previousColumn = column - 1;
        final int nextColumn = column + 1;
        final int previousRow = row - 1;
        final int nextRow = row + 1;

        if(column > 0 && row < GameGrid.ROW_NUMBER - 1 && previousColumn != oldColumn && nextRow != oldRow && grid.getGameTile(previousColumn, nextRow).getFill().equals(grid.getGameTile(column, row).getFill()))
            correctChip += getNeighborChipsRightDiagonal(previousColumn, nextRow, column, row);

        if(column < GameGrid.COLUMN_NUMBER - 1 && row > 0 && nextColumn != oldColumn && previousRow != oldRow && grid.getGameTile(nextColumn, previousRow).getFill().equals(grid.getGameTile(column, row).getFill()))
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

    public GameGrid getGrid() {
        return grid;
    }

    public GameType getGameType() {
        return gameType;
    }
}
