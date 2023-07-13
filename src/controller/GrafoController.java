package src.controller;

import java.util.ArrayList;
import java.awt.Point;

import src.view.display.GrafoDisplay;
import src.model.ArestaGenerico;
import src.model.GrafoGenerico;

public class GrafoController 
{
    private GrafoGenerico G;
    private GrafoDisplay desenho;

    public GrafoController() 
    {
        G = new GrafoGenerico();
        desenho = new GrafoDisplay();
    }

    public void clear() 
    {
        G = new GrafoGenerico();
        desenho = new GrafoDisplay();
    }

    public void adicionaVertice(Point p) 
    {
        int id = G.adicionaVertice();
        desenho.adicionaVertice(id, p);
    }

    public void removeVertice(int u) 
    {
        ArrayList<ArestaGenerico> adj = getAdjList(u);
        
        for (ArestaGenerico e : adj)
            removeAresta(e.getId());
        
        desenho.removeVertice(u);
        G.removeVertice(u);
    }

    public void adicionaAresta(int u, int v) 
    {
        int id = G.adicionaAresta(u, v);
        desenho.adicionaAresta(u, v, id);
    }

    public void removeAresta(int id) 
    {
        desenho.removeAresta(id);
        G.removeAresta(id);
    }

    public ArrayList<ArestaGenerico> getAdjList(int u) 
    {
        return G.getAdjList(u);
    }

    public int[][] getAdjMatrix() 
    {
        return G.getAdjMatrix();
    }

    public GrafoDisplay getDesenho() 
    {
        return desenho;
    }

    public int getN() 
    {
        return G.getN();
    }

    public int getM()
    {
        int ans = 0;
        
        for (int i = 0; i < G.getN(); i++) 
        {
            if (G.verticeExiste(i))
                ans += G.getAdjList(i).size();
        }
        
        return ans / 2;
    }

    public boolean verticeExiste(int id)
    {
        return G.verticeExiste(id);
    }

    public void debug()
    {
        G.debug();
    }

}