package src.view.display;

import java.awt.*;
import java.awt.geom.*;

import src.view.constantes.Paleta;

public class VerticeDisplay implements DrawingObject 
{
    private int id;
    private Point centro;

    //Modificar essas variáveis
    int RAIO;
    Color FILL_COLOR;
    Color TEXT_COLOR;
    Color STROKE_COLOR;
    BasicStroke STROKE_WIDTH;

    public VerticeDisplay(Point centro) 
    {
        this(centro, -1);
    }

    public VerticeDisplay(Point centro, int id) 
    {
        setCentro(centro);
        setId(id);
        setAparencia(Paleta.FILL_DEFAULT, Paleta.STROKE_DEFAULT, Paleta.STROKE_WIDTH_DEFAULT);
        RAIO = Paleta.VERTICE_RAIO_DEFAULT;
        TEXT_COLOR = Paleta.LABEL_COLOR_DEFAULT;
    }

    public void draw(Graphics2D g2) 
    {
        //Desenha parte interna
        g2.setColor(FILL_COLOR);
        g2.fill(new Ellipse2D.Double(centro.x-RAIO, centro.y-RAIO,2*RAIO,2*RAIO));        

        //Desenha borda
        g2.setColor(STROKE_COLOR);
        g2.setStroke(STROKE_WIDTH);
        g2.draw(new Ellipse2D.Double(centro.x-RAIO, centro.y-RAIO,2*RAIO,2*RAIO));        
        
        //Desenha rotulo centralizado
        String rotulo = Integer.toString(id);
        
        g2.setColor(TEXT_COLOR);
        g2.setFont(Paleta.LABEL_FONT_DEFAULT);
        
        FontMetrics fm = g2.getFontMetrics();
        double textWidth = fm.getStringBounds(rotulo, g2).getWidth(), textHeight = fm.getMaxAscent();
        
        g2.drawString(rotulo, (int)( centro.x-textWidth/2), (int) (centro.y+textHeight/2));
    }

    public Point getCentro() 
    {
        return centro;
    }
    
    public void setCentro(Point centro) 
    {
        this.centro = centro;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    @Override
    public void setAparencia(Color FILL_COLOR, Color STROKE_COLOR, BasicStroke STROKE_WIDTH) 
    {
        this.FILL_COLOR = FILL_COLOR;
        this.STROKE_COLOR = STROKE_COLOR;
        this.STROKE_WIDTH = STROKE_WIDTH;
    }

    //Funções auxiliares
    public boolean contains(Point p)
    {
        return (centro.distance(p) <= RAIO);
    }

    @Override
    public Color getFillColor() 
    {
        return FILL_COLOR;
    }

    @Override
    public Color getStrokeColor() 
    {
        return STROKE_COLOR;
    }

    @Override
    public BasicStroke getStrokeStyle() 
    {
        return STROKE_WIDTH;
    }

}
