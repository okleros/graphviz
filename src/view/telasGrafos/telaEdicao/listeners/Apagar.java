package src.view.telasGrafos.telaEdicao.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import src.view.display.ArestaDisplay;
import src.view.display.VerticeDisplay;
import src.view.telasGrafos.telaEdicao.TelaEdicao;

public class Apagar extends MouseAdapter {
    TelaEdicao tela;

    public Apagar(TelaEdicao tela){
        this.tela = tela;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getClickCount() == 2 && e.getButton() == 3){ //Botão Direito do Mouse
            VerticeDisplay verticeSelec = tela.getController().getDesenho().verticeContendo(e.getPoint());
            if (verticeSelec != null){
                tela.getController().removeVertice(verticeSelec.getId());
            }
            else {
                ArestaDisplay arestaSelec = tela.getController().getDesenho().arestaContendo(e.getPoint());
                if (arestaSelec != null)
                    tela.getController().removeAresta(arestaSelec.getId());
            }
            tela.repaint();
        }
    }

}
