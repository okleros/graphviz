package src.view.telasGrafos.algoritmos;

import src.view.constantes.Paleta;
import src.controller.GrafoController;
import src.model.ArestaGenerico;

import java.util.ArrayList;
import java.util.Stack;

public class BiconnectedComponents extends AbstractTelaAlgoritmo
{
    int n, tempo, counter;
    int low[], d[], parent[], cc[];
    boolean isOnStack[];
    Stack<Integer> st;

    public BiconnectedComponents(GrafoController controller)
    {
        super(controller);
        info = "Biconnected components";
    }

    public void executa()
    {
        n = getController().getN();
        tempo = 0;
        cc = new int[n + 1];
        low = new int[n + 1];
        d = new int[n + 1];
        isOnStack = new boolean[n + 1];
        parent = new int[n + 1];
        st = new Stack<Integer>();

        for (int i = 0; i < n; i++)
        {
            if (getController().verticeExiste(i) == false) continue;
            if (d[i] == 0)
            {
                parent[i] = -1;
                calcula(i);
            }
        }
        
        parent = new int[n + 1];
        for (int i = 0; i < n; i++)
        {
            if (getController().verticeExiste(i) == false) continue;
            if (parent[i] == 0)
            {
                parent[i] = -1;
                colorir(i, false);
            }
        }

        this.repaint();
    }

    public void calcula(int u)
    {
        d[u] = ++tempo;
        low[u] = d[u];
        isOnStack[u] = true;
        st.push(u); 
        
        ArrayList<ArestaGenerico> adj = getController().getAdjList(u);
        for (ArestaGenerico e : adj)
        {
            if (d[e.getDestino()] == 0)
            {
                parent[e.getDestino()] = e.getId();
                calcula(e.getDestino());
                low[u] = Math.min(low[u], low[e.getDestino()]);
            }
            else if (isOnStack[e.getDestino()] && e.getId() != parent[u])
            {
                low[u] = Math.min(low[u], low[e.getDestino()]);
            }
        }
        
        if (low[u] == d[u])
        {
            counter++;
            while(!st.isEmpty() && low[st.peek()] >= d[u])
            {
                cc[st.peek()] = counter;
                isOnStack[st.peek()] = false;
                st.pop();
            }
        }

    }

    public void colorir(int u, boolean cor)
    {
        parent[u] = 1;
        
        if (cor)
            getController().getDesenho().getVertice(u).setAparencia(Paleta.ALGORITHM_0, Paleta.BACKGROUND_GRAFO_DEFAULT, Paleta.STROKE_WIDTH_DEFAULT);
        else
            getController().getDesenho().getVertice(u).setAparencia(Paleta.ALGORITHM_1, Paleta.BACKGROUND_GRAFO_DEFAULT, Paleta.STROKE_WIDTH_DEFAULT);

        ArrayList<ArestaGenerico> adj = getController().getAdjList(u);
        for (ArestaGenerico e : adj) 
        {
            if (cc[u] == cc[e.getDestino()])
            {
                if (cor)
                    getController().getDesenho().getAresta(e.getId()).setAparencia(Paleta.ALGORITHM_0, Paleta.ALGORITHM_0, Paleta.STROKE_WIDTH_DEFAULT);
                
                else
                    getController().getDesenho().getAresta(e.getId()).setAparencia(Paleta.ALGORITHM_1, Paleta.ALGORITHM_1, Paleta.STROKE_WIDTH_DEFAULT);
            }
            
            else 
            {
                getController().getDesenho().getAresta(e.getId()).setAparencia(Paleta.STROKE_DEFAULT, Paleta.STROKE_DEFAULT, Paleta.DASHED_STROKE);
            }
            
            if (parent[e.getDestino()] == 0)
            {
                colorir(e.getDestino(), (cc[u] == cc[e.getDestino()] ? cor : !cor));
            }
        }
    }

}
