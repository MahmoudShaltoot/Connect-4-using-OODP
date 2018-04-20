package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class GameBoardGUI {

    boolean PLAYER1TURN = true;

    private  Color PLAYER1COLOR = Color.RED;
    private  Color PLAYER2COLOR = Color.BLACK;

    private final int DISC_SIZE = 100;
    private final int ROWS = 6, COLUMNS = 7;

    protected static final int margin_left = 50, margin_top = 50;
    protected static final int innerSpaceX = 30, innerSpaceY = 30;
    //Widget widget = new ColorDecorator(new Disc(100),PLAYER1TURN ? Color.RED : Color.BLACK);
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
            rect.setOnMouseClicked(e -> placeDisc(5,column));  // call function Play

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
        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), widget.get_disc());
        animation.setToY(original_height);

        animation.play();
    }
}
