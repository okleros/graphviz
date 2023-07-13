package src.view.telasGrafos.algoritmos;

import src.controller.GrafoController;
import src.view.constantes.Paleta;
import src.view.telasGrafos.AbstractTelaGrafo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public abstract class AbstractTelaAlgoritmo extends AbstractTelaGrafo 
{
    String info;

    AbstractTelaAlgoritmo(AbstractTelaGrafo T)
    {
        this(T.getController());
    }

    AbstractTelaAlgoritmo(GrafoController controller)
    {
        super(controller);
    }

    abstract public void executa();

    @Override
    protected void paintComponent(Graphics g) 
    {
        if (!isVisible()) return;
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);         

        getDesenho().draw(g2);

        g2.setFont(Paleta.MENU_FONT_DEFAULT);
        g2.drawString(info, 20, 20);
    }

}
