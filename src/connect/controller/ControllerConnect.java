package connect.controller;

import connect.component.GameGrid;
import connect.component.GameTile;
import connect.frame.FrameConnect;
import connect.frame.page.PageConnect;
import connect.frame.page.PageMain;
import connect.gamerule.GameRule;
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
                    GameGrid grid = pageConnect.getGrid();
                    int indexColumn = grid.getColumn(gameTile);

                    if(
                            pageConnect.getGameRule().isRemoveChip() && pageConnect.isCanRemoveChip() && !gameTile.getFill().equals(Color.WHITE) &&
                                    grid.getGameTile(indexColumn, GameGrid.ROW_NUMBER - 1).equals(gameTile)
                    ){
                        boolean isSameColor = gameTile.getFill().equals(pageConnect.getCurrentColor());
                        if(isSameColor){
                            boolean isWin = false;

                            if(pageConnect.getGameRule().isFillGrid()){
                                if(isSameColor && pageConnect.isWin(indexColumn, GameGrid.ROW_NUMBER - 1))
                                    pageConnect.checkForWinner(gameTile);
                                else{
                                    pageConnect.switchPlayer();
                                    pageConnect.setCanRemoveChip(false);
                                }
                            }

                            for(int i = GameGrid.ROW_NUMBER - 1; i > 0; i--){
                                grid.getTiles()[indexColumn][i].setFill(grid.getTiles()[indexColumn][i - 1].getFill());
                                if(!pageConnect.getGameRule().isFillGrid()){
                                    if(!isWin && pageConnect.isWin(indexColumn, i)){
                                        isWin = true;
                                        pageConnect.checkForWinner(gameTile);
                                    }
                                }
                            }
                            grid.getTiles()[indexColumn][0].setFill(Color.WHITE);
                            pageConnect.switchPlayer();
                        }
                        else{
                            boolean thereIsCurrentPlayerColor = false;
                            for(int i = 0; i < grid.getColumnNumber(); i++){
                                if(grid.getTiles()[i][GameGrid.ROW_NUMBER - 1].getFill().equals(pageConnect.getCurrentColor()))
                                    thereIsCurrentPlayerColor = true;
                            }

                            if(!thereIsCurrentPlayerColor)
                                pageConnect.switchPlayer();
                        }
                    }
                    else if(!pageConnect.getGameRule().isFillGrid() || !pageConnect.isCanRemoveChip()){
                        boolean end = false;
                        int indexTile = GameGrid.ROW_NUMBER - 1;
                        GameTile tile = null;
                        while(!end && indexTile >= 0){
                            tile = grid.getGameTile(indexColumn, indexTile);
                            if(tile.getFill().equals(Color.WHITE)){
                                end = true;
                                tile.setFill(pageConnect.getCurrentColor());
                                pageConnect.switchPlayer();
                            }
                            else
                                indexTile--;
                        }
                        if(!pageConnect.getGameRule().isFillGrid() && indexTile >= 0 && pageConnect.isWin(indexColumn, indexTile))
                            pageConnect.checkForWinner(tile);
                        pageConnect.setCanRemoveChip(true);
                    }
                }
            }
        }
    }
}
