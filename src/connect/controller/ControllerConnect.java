package connect.controller;

import connect.component.GameTile;
import connect.frame.FrameConnect;
import connect.frame.page.PageConnect;
import connect.frame.page.PageMain;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ControllerConnect {

    public static void goToMainMenu(MouseEvent event){
        FrameConnect.setShowedPage(new PageMain());
    }

    public static void addChips(MouseEvent event) {
        if(event.getSource() instanceof GameTile){
            if(!PageConnect.gameIsEnded){
                GameTile gameTile = (GameTile) event.getSource();

                boolean end = false;
                int indexColumn = 0;
                int indexRow = 0;
                while(!end && indexRow <= 41){
                    if(PageConnect.gameTiles[indexColumn][indexRow].equals(gameTile)){
                        end = true;
                    }
                    else if(indexRow == 5 || indexRow == 11 || indexRow == 17 || indexRow == 23 || indexRow == 29 || indexRow == 35 || indexRow == 41){
                        indexRow = 0;
                        indexColumn++;
                    }
                    else
                        indexRow++;
                }

                end = false;
                int indexTile = 5;
                while(!end && indexTile >= 0){
                    GameTile tile = PageConnect.gameTiles[indexColumn][indexTile];
                    if(tile.getFill().equals(Color.WHITE)){
                        end = true;
                        tile.setFill(PageConnect.getCurrentColor());
                        PageConnect.switchPlayer();
                        System.out.println("Setting " + indexColumn + ", " + indexTile);
                    }
                    else
                        indexTile--;
                }
                PageConnect.checkForWinner(indexColumn, indexTile);
            }
        }
    }
}
