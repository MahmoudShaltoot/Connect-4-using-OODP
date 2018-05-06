package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Disc extends Widget{
    private int disc_size;

    public Disc(int disc_size){
        this.disc_size = disc_size;
    }

    @Override
    public void drawDisc() {
        disc = new Circle(disc_size / 2);
        disc.setCenterX(disc_size/2);
        disc.setCenterY(disc_size/2);
    }
}
