package sample;

import javafx.scene.paint.Color;

public class RedDisc extends Decorator {
    protected RedDisc(Widget widget) {
        super(widget);
    }
    @Override
    public void drawDisc(){
        widget.drawDisc();
        setDiscColorRed();
    }

    public void setDiscColorRed(){
        widget.get_disc().setFill(Color.RED);
    }
}
