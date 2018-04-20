package sample;

import javafx.scene.paint.Color;

public abstract class Decorator extends Widget{
    protected Widget widget;
    protected Color color;

    protected Decorator(Widget widget,Color color){
        this.widget = widget;
        this.color =  color;
    }

    @Override
    public void  drawDisc(){
        widget.drawDisc();
    }
}
