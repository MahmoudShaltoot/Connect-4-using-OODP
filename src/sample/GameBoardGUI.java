package sample;

import javafx.animation.TranslateTransition;

import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;


public class GameBoardGUI {

    static private boolean PLAYER1TURN = true;

    private final int DISC_SIZE = 100;
    private final int ROWS = 6, COLUMNS = 7;

    private static final int margin_left = 50, margin_top = 50;
    private static final int innerSpaceX = 30, innerSpaceY = 30;

    javafx.scene.control.Label player1Label = new javafx.scene.control.Label("player 1");
    javafx.scene.control.Label player2Label = new javafx.scene.control.Label("player 2");

    private Game playerVSplayer ;
    private Game playerVSRandomComputer ;
    private Game playerVsAIomputer ;

    private TranslateTransition animation;

    Pane disc_root = new Pane();

    protected Shape makeGridBoard(){
        //Rectangle width and height should be bigger than normal because of margin between tiles
        Shape rectangleShape = new Rectangle((COLUMNS) * DISC_SIZE + ( margin_left + innerSpaceX*COLUMNS),
                (ROWS) * DISC_SIZE + ( margin_top + innerSpaceY*ROWS));

        for(int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                Widget circleCell = new Disc(DISC_SIZE);
                circleCell.drawDisc();

                circleCell.get_disc().setTranslateX((x * DISC_SIZE) + margin_left + (x * innerSpaceX));
                circleCell.get_disc().setTranslateY((y * DISC_SIZE) + margin_top + (y * innerSpaceY));

                rectangleShape = Shape.subtract(rectangleShape, circleCell.get_disc());
            }
        }
        //Set Rectangle Board Color
        rectangleShape.setFill(Color.BLUE);
        makeLabels();
        return rectangleShape;
    }

    private void initGameMode(){
        if(Game.getGameMode().equals("playerVSplayer"))
            playerVSplayer = new PlayerVSPlayer();
        else if (Game.getGameMode().equals("playerVSrandomComputer"))
            playerVSRandomComputer = new PlayerVSRandomComputer();
        else if(Game.getGameMode().equals("playerVSAIcomputer"))
            playerVsAIomputer = new PlayerVsAIcomputer();
    }

    public List<Rectangle> columns_hover() {
        List<Rectangle> list = new ArrayList<>();
        initGameMode();
        for (int x = 0; x < COLUMNS; x++) {
            Rectangle rect = new Rectangle((DISC_SIZE + innerSpaceX) , (ROWS * DISC_SIZE) + margin_top + innerSpaceY*ROWS);
            rect.setTranslateX((x * DISC_SIZE) + margin_left + (x * innerSpaceX) - (innerSpaceX / 2));

            rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.3)));
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            final int column = x ;
            //region PLAYER VS PLAYER
            if(Game.getGameMode().equals("playerVSplayer")){
                rect.setOnMouseClicked(e -> {
                        if(!ConnectFour.gameOver) {
                            if (PLAYER1TURN && !ConnectFour.gameOver) {
                                playerVSplayer.player1.play("player 1", column);
                                } else {
                                playerVSplayer.player2.play("player 2", column);
                            }
                            if(ConnectFour.validGame){
                            final int rowIndex = ConnectFour.getRow();
                            placeDisc(rowIndex, column);
                            if(ConnectFour.gameOver)
                                    makeGameOverSound();
                                changePlayerTurnLabels();
                            }
                        } else {
                            makeGameOverSound();
                            System.out.println("Game Over");
                        }
                });
            }
            //endregion

            //region Random & AI computer
            if(!Game.getGameMode().equals("playerVSplayer")){
                rect.setOnMouseClicked(e -> {
                if(!ConnectFour.gameOver) {
                    if (PLAYER1TURN && !ConnectFour.gameOver) {
                        if(Game.getGameMode().equals("playerVSrandomComputer"))
                            playerVSRandomComputer.player1.play("player 1", column);
                        else
                            playerVsAIomputer.player1.play("player 1", column);

                        if (ConnectFour.validGame) {
                            final int rowIndex = ConnectFour.getRow();
                            placeDisc(rowIndex, column);
                            changePlayerTurnLabels();
                            if(ConnectFour.gameOver)
                                makeGameOverSound();
                        }
                        animation.setOnFinished(event -> {
                            if (!ConnectFour.gameOver) {
                                try {
                                    if(Game.getGameMode().equals("playerVSrandomComputer"))
                                        playerVSRandomComputer.player2.play();
                                    else
                                        playerVsAIomputer.player2.play();
                                } catch (CloneNotSupportedException e1) {
                                    e1.printStackTrace();
                                }
                                final int rowIndex = ConnectFour.getRow();
                                final int columnIndex = ConnectFour.getColumn();
                                placeDisc(rowIndex, columnIndex);
                                changePlayerTurnLabels();
                                if(ConnectFour.gameOver)
                                    makeGameOverSound();
                            }
                        });
                    }
                }
                else {
                    makeGameOverSound();
                    System.out.println("Game Over");
                    }
                });
            }
            //endregion

            list.add(rect);
        }
        return list;
    }

    private void placeDisc(int row, int column){

        Widget widget;
        if(PLAYER1TURN)
             widget = new RedDisc(new Disc(DISC_SIZE));
        else
             widget = new BlackDisc(new Disc(DISC_SIZE));

        widget.drawDisc();

        PLAYER1TURN = !PLAYER1TURN;
        disc_root.getChildren().add(widget.get_disc());

        widget.get_disc().setTranslateX(column * (DISC_SIZE) + margin_left + (column * innerSpaceX));

        final int original_height =((row) * DISC_SIZE) + margin_top + ((row) * innerSpaceY);
        animation = new TranslateTransition(Duration.seconds(1), widget.get_disc());
        animation.setToY(original_height);

        animation.play();
    }

    private void makeLabels(){
        player1Label.setLayoutX(10);
        player1Label.setLayoutY(10);
        player1Label.setTextFill(Color.WHITE);

        player2Label.setLayoutX(10);
        player2Label.setLayoutY(10);
        player2Label.setTextFill(Color.WHITE);

        //PlayerOneTurn
        player2Label.setVisible(false);
    }

    private void changePlayerTurnLabels(){
        if(player1Label.isVisible()){
            player1Label.setVisible(false);
            player2Label.setVisible(true);
        }else{
            player1Label.setVisible(true);
            player2Label.setVisible(false);
        }
    }

    private void makeGameOverSound(){
        AudioClip gameOverSound = new AudioClip(this.getClass().getResource("gameOver.wav").toString());
        gameOverSound.play();
    }
}
