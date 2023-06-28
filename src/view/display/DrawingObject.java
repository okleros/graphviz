package src.view.display;

import java.awt.Color;
import java.awt.BasicStroke;

public interface DrawingObject {
/*
    public void setFillColor(Color FILL_COLOR);

    public void setTextColor(Color TEXT_COLOR);

    public void setStrokeColor(Color STROKE_COLOR);

    public void setStrokeStyle(BasicStroke STROKE_STYLE);
*/
    public Color getFillColor();

    public Color getStrokeColor();

    public BasicStroke getStrokeStyle();

    public void setAparencia(Color FILL_COLOR, Color STROKE_COLOR, BasicStroke STROKE_STYLE);
}
