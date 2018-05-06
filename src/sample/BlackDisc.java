package sample;

import javafx.scene.paint.Color;

public class BlackDisc extends Decorator {
    public BlackDisc(Widget widget) {
        super(widget);
    }
    @Override
    public void drawDisc(){
        widget.drawDisc();
        setDiscColorRed();
    }

    private void setDiscColorRed(){
        widget.get_disc().setFill(Color.BLACK);
    }
}
