package src.view.telasGrafos.telaEdicao.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import src.view.display.VerticeDisplay;
import src.view.telasGrafos.telaEdicao.TelaEdicao;

public class CriarVertices extends MouseAdapter {
    TelaEdicao tela;
    VerticeDisplay dragTarget;

    public CriarVertices(TelaEdicao tela){
        this.tela = tela;
        dragTarget = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Criar ou Arrastar Vértices
        if(e.getClickCount() == 1 && e.getButton() == 1){ 
            VerticeDisplay selecionado = tela.getController().getDesenho().verticeContendo(e.getPoint());
            if(selecionado != null){
                dragTarget = selecionado;
            } else {
                tela.getController().adicionaVertice(e.getPoint());
            }  
            
            tela.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(dragTarget != null){
            dragTarget = null;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(dragTarget != null){
            if(tela.contains(e.getPoint()))
                dragTarget.setCentro(e.getPoint());                    
            
            tela.repaint();
        }
    }
}