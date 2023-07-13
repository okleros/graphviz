package src.view.telasGrafos;

import java.awt.Dimension;

import javax.swing.JPanel;

import src.controller.GrafoController;
import src.view.constantes.Paleta;
import src.view.display.GrafoDisplay;
    
public abstract class AbstractTelaGrafo extends JPanel{
    protected GrafoController controller;

    public AbstractTelaGrafo(GrafoController controller) 
    {
        this.controller = controller;
        initComponent();
    }

    protected void initComponent() 
    {
        this.setPreferredSize(new Dimension(500, 500));
        this.setFont(Paleta.LABEL_FONT_DEFAULT);
        this.setVisible(true);
    }

    public GrafoController getController() 
    {
        return controller;
    }

    public GrafoDisplay getDesenho() 
    {
        return controller.getDesenho();
    }

}
