package src.view.telasGrafos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import src.controller.GrafoController;

public class TelaVisualizacao extends AbstractTelaGrafo 
{ 
    int offsetX = 100, offsetY = 100;
    int mnx = -1, mxx = -1, mny = -1, mxy = -1;
    
    public TelaVisualizacao(AbstractTelaGrafo T)
    {
        this(T.getController());
    }

    public TelaVisualizacao(GrafoController controller)
    {
        super(controller);
        initComponent(controller);
    }

    void initComponent(GrafoController controller) 
    {
        for (int i = 0; i < controller.getN(); i++)
        {
            if (controller.verticeExiste(i))
            {
                Point p = controller.getDesenho().getVertice(i).getCentro();
                
                if (p.getX() < mnx || mnx == -1)
                    mnx = ((int) p.getX());
                
                if (p.getX() > mxx || mxx == -1)
                    mxx = ((int) p.getX());
                
                if (p.getY() < mny || mny == -1)
                    mny = ((int) p.getY());
                
                if (p.getY() > mxy || mxy == -1)
                    mxy = ((int) p.getY());
            }
        }
        
        this.setSize(mxx - mnx + offsetX, mxy - mny + offsetY);
        
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.translate(-mnx + offsetX / 2,-mny + offsetY / 2);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);         
        
        getDesenho().draw(g2);

    }

}
