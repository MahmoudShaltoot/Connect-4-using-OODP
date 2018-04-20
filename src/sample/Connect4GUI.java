package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Connect4GUI extends Application {

    public Parent createContent(){
        //Root object of the game GUI
        Pane root = new Pane();

        GameBoardGUI gameBoardGUI = new GameBoardGUI();

        root.getChildren().add(gameBoardGUI.disc_root);

        //Make The Rectangle board
        Shape gridShape = gameBoardGUI.makeGridBoard();

        root.getChildren().add(gridShape);

        root.getChildren().addAll(gameBoardGUI.columns_hover());

        return root;
    }


    @Override
    public void start(Stage primaryStage) {
        //Setting the title of the Stage.
        primaryStage.setTitle("Connect 4");
        //Setting the Scene of the Stage.
        primaryStage.setScene(new Scene(createContent()));
        //Display Connect4 Screen
        primaryStage.show();
    }

    public void StartGame(String[] args){
        launch(args);
    }
}
