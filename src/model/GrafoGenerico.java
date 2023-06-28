package src.model;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class GrafoGenerico {
    private ArrayList<TreeSet<ArestaGenerico>> adj;
    private TreeMap<Integer,ArestaGenerico> arestas;
    private PriorityQueue<Integer> idsLivres;
    private int proximoIdAresta;

    public GrafoGenerico() {
        adj = new ArrayList<TreeSet<ArestaGenerico>>();
        idsLivres = new PriorityQueue<Integer>();
        arestas = new TreeMap<Integer,ArestaGenerico>();
        proximoIdAresta = 0;
    }

    /**
     * 
     * @return Um inteiro correspondente ao id do vértice criado.
     */
    public int adicionaVertice() {
        int novo;
        
        if(idsLivres.isEmpty()){
            novo = adj.size();
            adj.add(new TreeSet<ArestaGenerico>());
        
        } else {
            novo = idsLivres.poll();
            adj.set(novo, new TreeSet<ArestaGenerico>());
        }
        
        return novo;
    }

    /**
     * 
     * @param u Identificador do vértice a ser removido.
     * @return true caso a remoção seja bem sucedida, false caso o contrário.
     */
    public boolean removeVertice(int u) {
        if(verticeExiste(u) == false) 
            return false;
        
        for(ArestaGenerico e1 : adj.get(u)){
            ArrayList<ArestaGenerico> removidos = new ArrayList<ArestaGenerico>();
         
            for(ArestaGenerico e2 : adj.get(e1.getDestino()))
                if (e2.getDestino() == u)
                    removidos.add(e2);
            
            adj.get(e1.getDestino()).removeAll(removidos);
        }
        
        adj.set(u, null);
        
        idsLivres.add(u);
        
        return true;
    }

    /**
     * 
     * @param u Extremidade da aresta.
     * @param v Extremidade da aresta.
     * @return Retorna False se a aresta (u,v) já exista no grafo ou os vértice u ou v não existirem. Caso o contrário retorna True.
     */
    public Integer adicionaAresta(int u, int v) {
        proximoIdAresta++;
        
        adj.get(u).add(new ArestaGenerico(proximoIdAresta, u, v));
        adj.get(v).add(new ArestaGenerico(proximoIdAresta, v, u));
        
        arestas.put(proximoIdAresta,new ArestaGenerico(proximoIdAresta,u,v));
        
        return proximoIdAresta;
    }

    /**
     * @param u Extremidade da aresta.
     * @param v Extremidade da aresta.
     * @return True caso seja possível remover a aresta (u,v) do grafo, False caso o contrário.
     */
    public void removeAresta(int id) {
        ArestaGenerico removida = arestas.get(id);
        
        adj.get(removida.getOrigem()).remove(removida);
        adj.get(removida.getDestino()).remove(removida);
    }

    /**
     * @param u Inteiro.
     * @return Retorna True se existir algum vértice com identificador u no grafo, retorna False caso o contrário.
     */
    public boolean verticeExiste(int u) {
        return (u < adj.size() && adj.get(u) != null);
    }

    /**
     * @param u
     */
    public ArrayList<ArestaGenerico> getAdjList(int u) {
        ArrayList<ArestaGenerico> copia = new ArrayList<ArestaGenerico>();
        
        for(ArestaGenerico e : adj.get(u))
            copia.add(new ArestaGenerico(e));
        
        return copia;
    }

    /**
     * 
     * @return
     */
    public int[][] getAdjMatrix(){
        int adjMat[][] = new int[getN()][getN()];
        
        for(int u = 0; u < getN(); u++)
            if(verticeExiste(u))
                for(ArestaGenerico e : adj.get(u))
                    adjMat[e.getOrigem()][e.getDestino()] = 1;
        
        return adjMat;
    }

    /**
     * 
     */
    public int getN(){
        return adj.size();
    }

    public void debug(){
        for(int i = 0; i < adj.size(); i++){
            System.out.print(i + ": ");
            for(ArestaGenerico e : adj.get(i)){
                System.out.print("(" + e.getId() + " " + e.getOrigem() + " " + e.getDestino() + "), ");
            }
            
            System.out.println();
        }
    }

}