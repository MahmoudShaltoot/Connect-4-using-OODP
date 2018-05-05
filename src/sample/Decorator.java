package sample;

import javafx.scene.paint.Color;

public abstract class Decorator extends Widget{
    protected Widget widget;
    //protected Color color;
/*
    protected Decorator(Widget widget,Color color){
        this.widget = widget;
        this.color =  color;
    }*/
    protected Decorator(Widget widget) {
        this.widget = widget;
    }
    @Override
    public void  drawDisc(){
        widget.drawDisc();
    }
}
