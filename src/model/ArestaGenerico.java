package src.model;

public class ArestaGenerico implements Comparable<ArestaGenerico> 
{
    private int id;
    private int destino;
    private int origem;

    ArestaGenerico(ArestaGenerico other)
    {
        this(other.getId(), other.getOrigem(), other.getDestino());
    }

    ArestaGenerico(int id, int origem, int destino) 
    {
        setId(id);
        setOrigem(origem);
        setDestino(destino);
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public int getDestino() 
    {
        return destino;
    }

    public void setDestino(int destino) 
    {
        this.destino = destino;
    }

    public int getOrigem() 
    {
        return origem;
    }

    public void setOrigem(int origem) 
    {
        this.origem = origem;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) return true;
        
        if ((obj instanceof ArestaGenerico) == false)
            return false;
        
        return id == ((ArestaGenerico) obj).getId();
    }

    @Override
    public int compareTo(ArestaGenerico o) 
    {
        return id < o.getId() ? -1 : ((id == o.getId()) ? 0 : 1);
    }

}