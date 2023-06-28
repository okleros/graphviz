package src.view.display;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import java.awt.geom.Line2D;
import java.awt.Point;
import java.awt.Graphics2D;

import src.view.constantes.Paleta;

public class GrafoDisplay {
    protected TreeMap<Integer, VerticeDisplay> vertices;
    protected TreeMap<Integer, ArestaDisplay> arestas;
    
    public GrafoDisplay() {
        vertices = new TreeMap<Integer, VerticeDisplay>();
        arestas = new TreeMap<Integer, ArestaDisplay>();
    }
    
    public void draw(Graphics2D g) {
        for(Map.Entry<Integer, ArestaDisplay> entry:arestas.entrySet()){
                entry.getValue().draw(g);
        }

        for(Map.Entry<Integer, VerticeDisplay> entry : vertices.entrySet()){
            entry.getValue().draw(g);
        }
    }

    public void adicionaVertice(int id, Point centro) {
        vertices.put(id, new VerticeDisplay(centro, id));
    }

    public void removeVertice(int id) {
        vertices.remove(id);
    }

    public void adicionaAresta(int u, int v, int id) {
        arestas.put(id, new ArestaDisplay(vertices.get(u), vertices.get(v), id));
    }

    public void removeAresta(int id) {
        arestas.remove(id);
    }

    public VerticeDisplay getVertice(int id){
        return vertices.get(id);
    }

    public ArestaDisplay getAresta(int id){
        return arestas.get(id);
    }

    public VerticeDisplay verticeContendo(Point p) {
        for(Map.Entry<Integer, VerticeDisplay> entry : vertices.entrySet())
            if(entry.getValue().contains(p))
                return entry.getValue();
        
        return null;
    }

    public ArestaDisplay arestaContendo(Point p) {
        for(Map.Entry<Integer, ArestaDisplay> entry : arestas.entrySet())
            if(entry.getValue().contains(p))
                return entry.getValue();
        
        return null;
    }

    public void resetArestasToDefault() {
        for(Map.Entry<Integer, ArestaDisplay> entry:arestas.entrySet())
            entry.getValue().setAparencia(Paleta.FILL_DEFAULT, Paleta.STROKE_DEFAULT, Paleta.STROKE_WIDTH_DEFAULT);
    }

    public void resetVerticesToDefault() {
        for(Map.Entry<Integer, VerticeDisplay> entry:vertices.entrySet())
            entry.getValue().setAparencia(Paleta.FILL_DEFAULT, Paleta.STROKE_DEFAULT, Paleta.STROKE_WIDTH_DEFAULT);;
    }

    public void centralizar(int width, int height){
        int minX = -1, minY = -1, maxX = -1, maxY = -1;

        for(Map.Entry<Integer,VerticeDisplay> entry:vertices.entrySet()){
            Point p = entry.getValue().getCentro();
            if (p.x < minX || minX == -1) minX = p.x;
            if (p.x > maxX || maxX == -1) maxX = p.x;
            if (p.y < minY || minY == -1) minY = p.y;
            if (p.y > maxY || maxY == -1) maxY = p.y;
        }
    
        for(Map.Entry<Integer, VerticeDisplay> entry:vertices.entrySet()){
            entry.getValue().getCentro().translate(-minX + (width - (maxX - minX)) / 2, -minY + (height - (maxY - minY)) / 2);
        }
    }

    public int getNumberOfCrossings(){
        int answ = 0;
        Collection<ArestaDisplay> edges = arestas.values();

        for(ArestaDisplay e1:edges){
            for(ArestaDisplay e2:edges){
                if(e2 == e1) break;
                answ += (Line2D.linesIntersect(e1.getU().getCentro().x, e1.getU().getCentro().y, e1.getV().getCentro().x, e1.getV().getCentro().y, e2.getU().getCentro().x, e2.getU().getCentro().y, e2.getV().getCentro().x, e2.getV().getCentro().y) ? 1 : 0);
            }
        }

        return answ;
    }

}
