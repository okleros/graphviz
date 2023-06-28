package src.view.telasGrafos.algoritmos;

import java.util.ArrayDeque;
import java.util.ArrayList;

import src.controller.GrafoController;
import src.view.constantes.Paleta;
import src.model.ArestaGenerico;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DepthFirstSearch extends AbstractTelaAlgoritmoAnimado {
    
    public DepthFirstSearch(GrafoController controller){
        super(controller);
        info = "Depth First Search";
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

    void resposta(int u) {
        this.removeMouseListener(this.getMouseListeners()[0]);
        info = "Depth First Search";
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
        int[] visited = new int[getController().getN()];                
        boolean[] isOnStack = new boolean[getController().getN()];                

        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        visited[source] = -1; q.push(source);

        auxiliar(source, visited, isOnStack, -1);
    }

    void auxiliar(int u, int[] visited, boolean[] isOnStack, int p){
        visited[u] = p;
        isOnStack[u] = true;

        if(visited[u] != -1)
            arr.add(new Alteration(getDesenho().getAresta(visited[u]),Paleta.ALGORITHM_3,Paleta.ALGORITHM_3,Paleta.STROKE_WIDTH_DEFAULT));    
        
        arr.add(new Alteration(getDesenho().getVertice(u),Paleta.ALGORITHM_3,Paleta.ALGORITHM_3,Paleta.WIDE_STROKE));

        ArrayList<ArestaGenerico> adj = getController().getAdjList(u);
        
        for(ArestaGenerico e : adj){
            if(visited[e.getDestino()] == 0){
                auxiliar(e.getDestino(), visited, isOnStack, e.getId());
            }
        
            else if (e.getId() != visited[u] && isOnStack[e.getDestino()]){
                arr.add(new Alteration(getDesenho().getAresta(e.getId()),Paleta.FILL_DEFAULT,Paleta.STROKE_DEFAULT,Paleta.DASHED_STROKE));    
            }
        }
        
        arr.add(new Alteration(getDesenho().getVertice(u),Paleta.ALGORITHM_1,Paleta.ALGORITHM_1,Paleta.STROKE_WIDTH_DEFAULT));
        if(visited[u] != -1)
            arr.add(new Alteration(getDesenho().getAresta(visited[u]),Paleta.ALGORITHM_1,Paleta.ALGORITHM_1,Paleta.STROKE_WIDTH_DEFAULT));    
        
        isOnStack[u] = false;

    }

}
