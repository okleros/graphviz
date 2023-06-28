package src.view.telasGrafos.algoritmos;

import src.view.display.DrawingObject;

import java.awt.Color;
import java.awt.BasicStroke;

public class Alteration {
    private DrawingObject target;
    private Color FILL_COLOR, STROKE_COLOR;
    private BasicStroke STROKE_STYLE;
    private Color OLD_FILL_COLOR, OLD_STROKE_COLOR;
    private BasicStroke OLD_STROKE_STYLE;

    Alteration(DrawingObject target, Color fILColor, Color stroColor, BasicStroke stroStyle){
        this.target = target;
        FILL_COLOR = fILColor;
        STROKE_COLOR = stroColor;
        STROKE_STYLE = stroStyle;
    }

    public void modifica(){
        OLD_FILL_COLOR = target.getFillColor();
        OLD_STROKE_COLOR = target.getStrokeColor();
        OLD_STROKE_STYLE = target.getStrokeStyle();
        target.setAparencia(FILL_COLOR, STROKE_COLOR, STROKE_STYLE);
    }

    public void retorna(){
        target.setAparencia(OLD_FILL_COLOR, OLD_STROKE_COLOR, OLD_STROKE_STYLE);
    }

}
