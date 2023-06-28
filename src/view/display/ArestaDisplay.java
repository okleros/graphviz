package src.view.display;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import src.view.constantes.Paleta;

import java.awt.Point;
import java.awt.BasicStroke;

public class ArestaDisplay implements DrawingObject {
    private VerticeDisplay u, v;
    private int id;

    //Modificar essas variáveis
    Color TEXT_COLOR;
    Color STROKE_COLOR;
    BasicStroke STROKE_WIDTH;    

    public ArestaDisplay(VerticeDisplay u, VerticeDisplay v){
        this(u, v, -1);
    }

    public ArestaDisplay(VerticeDisplay u, VerticeDisplay v, int id){
        this.u = u;
        this.v = v;
        
        setId(id);
        
        setAparencia(Paleta.FILL_DEFAULT, Paleta.STROKE_DEFAULT, Paleta.STROKE_WIDTH_DEFAULT);
    }

    public void draw(Graphics2D g2){
        g2.setColor(STROKE_COLOR);
        g2.setStroke(STROKE_WIDTH);
        g2.draw(new Line2D.Double(u.getCentro(),v.getCentro()));
    }

    public void setV(VerticeDisplay v) {
        this.v = v;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public VerticeDisplay getU() {
        return u;
    }

    public VerticeDisplay getV() {
        return v;
    }

    public boolean contains(Point p){

        if(((   p.getX() >= v.getCentro().getX()   &&
                p.getX() <= u.getCentro().getX())  ||
               (p.getX() >= u.getCentro().getX()   &&
                p.getX() <= v.getCentro().getX())) &&
              ((p.getY() >= v.getCentro().getY()   &&
                p.getY() <= u.getCentro().getY())  ||
               (p.getY() >= u.getCentro().getY()   &&
                p.getY() <= v.getCentro().getY())))
            
            return new Line2D.Double(u.getCentro(), v.getCentro()).ptLineDist(p) <= 20;
        return false;
    }

    @Override
    public void setAparencia(Color FILL_COLOR, Color STROKE_COLOR, BasicStroke STROKE_WIDTH) {
        this.STROKE_COLOR = STROKE_COLOR;
        this.STROKE_WIDTH = STROKE_WIDTH;
    }

    @Override
    public Color getFillColor() {
        return Paleta.FILL_DEFAULT;
    }

    @Override
    public Color getStrokeColor() {
        return STROKE_COLOR;
    }

    @Override
    public BasicStroke getStrokeStyle() {
        return STROKE_WIDTH;
    }

}