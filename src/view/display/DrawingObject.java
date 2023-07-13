package src.view.display;

import java.awt.Color;
import java.awt.BasicStroke;

public interface DrawingObject 
{
    public Color getFillColor();

    public Color getStrokeColor();

    public BasicStroke getStrokeStyle();

    public void setAparencia(Color FILL_COLOR, Color STROKE_COLOR, BasicStroke STROKE_STYLE);
}
