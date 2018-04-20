package sample;

import javafx.scene.paint.Color;

public class ColorDecorator extends Decorator{

    public ColorDecorator(Widget widget , Color color) {
        super(widget,color);
    }

    @Override
    public void  drawDisc(){
        widget.drawDisc();
        disc.setFill(color);
    }
}
