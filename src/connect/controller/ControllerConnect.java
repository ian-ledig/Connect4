package connect.controller;

import connect.component.GameGrid;
import connect.component.GameTile;
import connect.frame.FrameConnect;
import connect.frame.page.PageConnect;
import connect.frame.page.PageMain;
import connect.util.GameType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ControllerConnect {

    public static void goToMainMenu(MouseEvent event){
        FrameConnect.setShowedPage(new PageMain());
    }

    public static void addChips(MouseEvent event) {
        if(event.getSource() instanceof GameTile){
            GameTile gameTile = (GameTile) event.getSource();

            if(gameTile.getParent() instanceof PageConnect){
                PageConnect pageConnect = (PageConnect) gameTile.getParent();

                if(!pageConnect.isGameIsEnded()){
                    GameType gameType = pageConnect.getGameType();
                    GameGrid grid = pageConnect.getGrid();
                    int indexColumn = grid.getColumn(gameTile);

                    if(
                            (gameType.equals(GameType.POPOUT) || gameType.equals(GameType.POPOUTINAROW5)) &&
                            !gameTile.getFill().equals(Color.WHITE) &&
                                    grid.getGameTile(indexColumn, GameGrid.ROW_NUMBER - 1).equals(gameTile)
                    ){
                        grid.getTiles()[indexColumn][0].setFill(Color.WHITE);
                        for(int i = GameGrid.ROW_NUMBER - 1; i > 0; i--){
                            grid.getTiles()[indexColumn][i].setFill(grid.getTiles()[indexColumn][i - 1].getFill());
                            pageConnect.checkForWinner(indexColumn, i);
                        }
                        pageConnect.switchPlayer();
                    }
                    else {
                        boolean end = false;
                        int indexTile = GameGrid.ROW_NUMBER - 1;
                        while(!end && indexTile >= 0){
                            GameTile tile = grid.getGameTile(indexColumn, indexTile);
                            if(tile.getFill().equals(Color.WHITE)){
                                end = true;
                                tile.setFill(pageConnect.getCurrentColor());
                                pageConnect.switchPlayer();
                            }
                            else
                                indexTile--;
                        }
                        if(indexTile >= 0)
                            pageConnect.checkForWinner(indexColumn, indexTile);
                    }
                }
            }
        }
    }
}
