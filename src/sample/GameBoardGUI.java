package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class GameBoardGUI {

    static boolean PLAYER1TURN = true;

    private  Color PLAYER1COLOR = Color.RED;
    private  Color PLAYER2COLOR = Color.BLACK;

    private final int DISC_SIZE = 100;
    private final int ROWS = 6, COLUMNS = 7;

    protected static final int margin_left = 50, margin_top = 50;
    protected static final int innerSpaceX = 30, innerSpaceY = 30;

    Game playerVSplayer = new PlayerVSPlayer();

    Game playerVSRandomComputer = new PlayerVSRandomComputer();

    Game playerVsAIomputer = new PlayerVsAIcomputer();

    TranslateTransition animation;
    private Widget[][] grid = new Disc[ROWS][COLUMNS];

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
        rectangleShape.setFill(Color.BLUE);
        return rectangleShape;
    }

    public List<Rectangle> columns_hover() {
        List<Rectangle> list = new ArrayList<>();

        for (int x = 0; x < COLUMNS; x++) {
            Rectangle rect = new Rectangle((DISC_SIZE + innerSpaceX) , (ROWS * DISC_SIZE) + margin_top + innerSpaceY*ROWS);
            rect.setTranslateX((x * DISC_SIZE) + margin_left + (x * innerSpaceX) - (innerSpaceX / 2));

            rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.3)));
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            final int column = x;
            //region PLAYER VS PLAYER
/*
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
                            }
                        }
                        else
                        {
                            System.out.println("Game Over");
                        }
            });
*/
//endregion

            //region RandomPlayer
            /*rect.setOnMouseClicked(e -> {

                if(!ConnectFour.gameOver) {

                    if (PLAYER1TURN && !ConnectFour.gameOver) {
                        playerVSRandomComputer.player1.play("player 1", column);
                        if (ConnectFour.validGame) {
                            final int rowIndex = ConnectFour.getRow();
                            placeDisc(rowIndex, column);
                        }
                        animation.setOnFinished(event -> {
                            if (!ConnectFour.gameOver) {
                                try {
                                    playerVSRandomComputer.player2.play();
                                } catch (CloneNotSupportedException e1) {
                                    e1.printStackTrace();
                                }
                                final int rowIndex = ConnectFour.getRow();
                                final int columnIndex = ConnectFour.getColumn();
                                placeDisc(rowIndex, columnIndex);
                            }
                        });
                    }
                }
                else
                    System.out.println("Game Over");
            });
            */
            //endregion

            rect.setOnMouseClicked(e -> {
                if(!ConnectFour.gameOver) {

                    if (PLAYER1TURN && !ConnectFour.gameOver) {
                        playerVsAIomputer.player1.play("player 1", column);
                        if (ConnectFour.validGame) {
                            final int rowIndex = ConnectFour.getRow();
                            placeDisc(rowIndex, column);
                        }
                        animation.setOnFinished(event -> {
                            if (!ConnectFour.gameOver) {
                                try {
                                    playerVsAIomputer.player2.play();
                                } catch (CloneNotSupportedException e1) {
                                    e1.printStackTrace();
                                }
                                final int rowIndex = ConnectFour.getRow();
                                final int columnIndex = ConnectFour.getColumn();
                                placeDisc(rowIndex, columnIndex);
                            }
                        });
                    }
                }
                else
                    System.out.println("Game Over");
            });
            list.add(rect);
        }
        return list;
    }

    private void placeDisc(int row, int column){

        Widget widget = new Disc(DISC_SIZE);
        widget = new ColorDecorator(widget,PLAYER1TURN ? PLAYER1COLOR : PLAYER2COLOR);

        widget.drawDisc();

        PLAYER1TURN = !PLAYER1TURN;
        disc_root.getChildren().add(widget.get_disc());

        widget.get_disc().setTranslateX(column * (DISC_SIZE) + margin_left + (column * innerSpaceX));

        final int original_height =((row) * DISC_SIZE) + margin_top + ((row) * innerSpaceY);
        animation = new TranslateTransition(Duration.seconds(1), widget.get_disc());
        animation.setToY(original_height);

        animation.play();
    }
}
