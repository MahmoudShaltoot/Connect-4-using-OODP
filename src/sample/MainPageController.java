package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class MainPageController {
    @FXML
    RadioButton playerVSplayer;
    @FXML
    RadioButton playerVSaicomputer;
    @FXML
    RadioButton playerVSrandomcomputer;

    static Stage primaryStage;

    public static boolean selected = false;
    public void handleSubmitButtonAction(ActionEvent event) {

        System.out.println("Inside StartButton");

        if(playerVSplayer.isSelected()) {
            Game.setGameMode("playerVSplayer");

        }
        else if(playerVSrandomcomputer.isSelected()) {
            Game.setGameMode("playerVSrandomComputer");
        }
        else if(playerVSaicomputer.isSelected()) {
            Game.setGameMode("playerVSAIcomputer");
        }
        Connect4GUI connect4Game = new Connect4GUI();
        connect4Game.start(primaryStage);
    }
}
