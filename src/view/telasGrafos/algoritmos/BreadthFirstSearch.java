package src.view.telasGrafos.algoritmos;

import java.util.ArrayDeque;
import java.util.ArrayList;

import src.controller.GrafoController;
import src.view.constantes.Paleta;
import src.model.ArestaGenerico;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BreadthFirstSearch extends AbstractTelaAlgoritmoAnimado {

    public BreadthFirstSearch(GrafoController controller){
        super(controller);
        info = "Breadth First Search";
    }

    @Override
    public void executa() {
        info = "Select a vertex";
        repaint();
        
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e) {
                //Criar ou Arrastar Vértices
                if(e.getClickCount() == 1 && e.getButton() == 1){ 
                    if(getController().getDesenho().verticeContendo(e.getPoint()) != null){
                        resposta(getController().getDesenho().verticeContendo(e.getPoint()).getId());
                    }
                }
            }
        });
        
    }

    void resposta(int u){
        this.removeMouseListener(this.getMouseListeners()[0]);
        info = "Breadth First Search";
        repaint();
        executa(u);
    }
    
    public void executa(int u) {
        if(getController().getN() == 0) return;

        arr = new ArrayList<>();
        pos = 0;

        precomputa(u);

        animate();
    }

    void precomputa(int source){
        //int[] dist = new int[getController().getN()];                
        int[] visited = new int[getController().getN()];                
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        visited[source] = -1; q.push(source);

        while(q.isEmpty() == false){
            int u = q.poll();

            if(visited[u] != -1)
                arr.add(new Alteration(getController().getDesenho().getAresta(visited[u]), Paleta.ALGORITHM_0, Paleta.ALGORITHM_0, Paleta.STROKE_WIDTH_DEFAULT));
            arr.add(new Alteration(getController().getDesenho().getVertice(u), Paleta.ALGORITHM_0, Paleta.STROKE_DEFAULT, Paleta.WIDE_STROKE));

            ArrayList<ArestaGenerico> adj = getController().getAdjList(u);
            for(ArestaGenerico e:adj){
                if(visited[e.getDestino()] == 0){
                    visited[e.getDestino()] = e.getId();
                    q.addLast(e.getDestino());                    

                    arr.add(new Alteration(getController().getDesenho().getAresta(e.getId()), Paleta.ALGORITHM_2, Paleta.ALGORITHM_2, Paleta.STROKE_WIDTH_DEFAULT));
                    arr.add(new Alteration(getController().getDesenho().getVertice(e.getDestino()), Paleta.ALGORITHM_2, Paleta.ALGORITHM_2, Paleta.STROKE_WIDTH_DEFAULT));

                }
            }

            arr.add(new Alteration(getController().getDesenho().getVertice(u), Paleta.ALGORITHM_0, Paleta.ALGORITHM_0, Paleta.STROKE_WIDTH_DEFAULT));
        }

    }


}
