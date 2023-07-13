package src.view.telasGrafos.algoritmos;

import src.view.display.DrawingObject;

import java.awt.Color;
import java.awt.BasicStroke;

public class Alteration 
{
    private DrawingObject target;
    private Color FILL_COLOR, STROKE_COLOR;
    private BasicStroke STROKE_STYLE;
    private Color OLD_FILL_COLOR, OLD_STROKE_COLOR;
    private BasicStroke OLD_STROKE_STYLE;

    Alteration(DrawingObject target, Color fillColor, Color strokeColor, BasicStroke strokeStyle)
    {
        this.target = target;
        FILL_COLOR = fillColor;
        STROKE_COLOR = strokeColor;
        STROKE_STYLE = strokeStyle;
    }

    public void modifica()
    {
        OLD_FILL_COLOR = target.getFillColor();
        OLD_STROKE_COLOR = target.getStrokeColor();
        OLD_STROKE_STYLE = target.getStrokeStyle();
        target.setAparencia(FILL_COLOR, STROKE_COLOR, STROKE_STYLE);
    }

    public void retorna()
    {
        target.setAparencia(OLD_FILL_COLOR, OLD_STROKE_COLOR, OLD_STROKE_STYLE);
    }

}
