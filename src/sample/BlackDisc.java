package sample;

import javafx.scene.paint.Color;

public class BlackDisc extends Decorator {
    protected BlackDisc(Widget widget) {
        super(widget);
    }
    @Override
    public void drawDisc(){
        widget.drawDisc();
        setDiscColorRed();
    }

    public void setDiscColorRed(){
        widget.get_disc().setFill(Color.BLACK);
    }
}
