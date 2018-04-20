package sample;

import javafx.scene.shape.Circle;

public abstract class Widget {
    protected static Circle disc;

    public Circle get_disc(){
        return this.disc;
    }

    public abstract void drawDisc();
}
