package sample;

import javafx.scene.paint.Color;

public class RedDisc extends Decorator {
    public RedDisc(Widget widget) {
        super(widget);
    }
    @Override
    public void drawDisc(){
        widget.drawDisc();
        setDiscColorRed();
    }

    private void setDiscColorRed(){
        widget.get_disc().setFill(Color.RED);
    }
}
