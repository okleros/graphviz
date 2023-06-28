package src.view.telasGrafos.telaEdicao;

import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import src.controller.GrafoController;
import src.view.display.ArestaDisplay;
import src.view.telasGrafos.AbstractTelaGrafo;
import src.view.telasGrafos.telaEdicao.listeners.Apagar;
import src.view.telasGrafos.telaEdicao.listeners.CriarArestas;
import src.view.telasGrafos.telaEdicao.listeners.CriarVertices;
import src.view.constantes.Paleta;

public class TelaEdicao extends AbstractTelaGrafo {
    ArrayList<ArestaDisplay> arestasTemporarias;

    public TelaEdicao(AbstractTelaGrafo T){
        this(T.getController());
    }

    public TelaEdicao(GrafoController controller){
        super(controller);
        arestasTemporarias = new ArrayList<ArestaDisplay>();
    }

    @Override
    protected void initComponent() {
        Apagar listApagar = new Apagar(this);
        this.addMouseListener(listApagar);

        CriarVertices listVertices = new CriarVertices(this);
        this.addMouseListener(listVertices);
        this.addMouseMotionListener(listVertices);
        
        CriarArestas listArestas = new CriarArestas(this);
        this.addMouseListener(listArestas);
        this.addMouseMotionListener(listArestas);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(!isVisible()) return;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);         

        for(ArestaDisplay e:arestasTemporarias)
            e.draw(g2);

        getDesenho().draw(g2);

        g2.setFont(Paleta.MENU_FONT_DEFAULT);
        g2.drawString("Editor", 20, 20);
    }

    public void adicionaArestaTemporaria(ArestaDisplay e){
        arestasTemporarias.add(e);
    }

    public void removeArestaTemporaria(ArestaDisplay e){
        arestasTemporarias.remove(e);
    }

}
