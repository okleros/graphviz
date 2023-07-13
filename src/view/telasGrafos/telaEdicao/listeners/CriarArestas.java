package src.view.telasGrafos.telaEdicao.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import src.view.display.ArestaDisplay;
import src.view.display.VerticeDisplay;
import src.view.telasGrafos.telaEdicao.TelaEdicao;

import src.view.constantes.Paleta;

public class CriarArestas extends MouseAdapter 
{
    TelaEdicao tela;
    VerticeDisplay verticeOrigem;
    ArestaDisplay arestaTemporaria;
    boolean criandoAresta;

    public CriarArestas(TelaEdicao tela)
    {
        this.tela = tela;
        criandoAresta = false;
        verticeOrigem = null;
        arestaTemporaria = null;
    }
    
    @Override
    public void mousePressed(MouseEvent e) 
    {
        // Quando clica com o botão direito do mouse, pega o vértice que está na posição atual do mouse para ser o vértice inicial da aresta que está sendo criada
        if (e.getButton() == Paleta.RIGHT_BUTTON && !criandoAresta)
        {
            VerticeDisplay selecionado = tela.getController().getDesenho().verticeContendo(e.getPoint());
            if (selecionado != null)
            {
                verticeOrigem = selecionado;
                arestaTemporaria = new ArestaDisplay(verticeOrigem, new VerticeDisplay(e.getPoint()));
                tela.adicionaArestaTemporaria(arestaTemporaria);
                criandoAresta = true;
            }
            tela.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        // E quando solta o botão do mouse, pega o vértice que está na posição atual do mouse para ser o ponto final da aresta que está sendo criada
        if (criandoAresta) 
        {
            criandoAresta = false;
            VerticeDisplay selecionado = tela.getController().getDesenho().verticeContendo(e.getPoint());
            tela.removeArestaTemporaria(arestaTemporaria);
            
            if (selecionado != null && verticeOrigem!=selecionado)
                tela.getController().adicionaAresta(verticeOrigem.getId(),selecionado.getId());
            tela.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        if (criandoAresta)
        {
            arestaTemporaria.setV(new VerticeDisplay(e.getPoint()));
            tela.repaint();
        }
    }
}