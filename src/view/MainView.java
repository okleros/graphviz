package src.view;

import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.controller.AleatorioController;
import src.controller.FileController;
import src.controller.GrafoController;
import src.view.constantes.Paleta;
import src.view.telasGrafos.AbstractTelaGrafo;
import src.view.telasGrafos.algoritmos.*;
import src.view.telasGrafos.telaEdicao.TelaEdicao;

public class MainView extends JPanel {
    GrafoController controller;
    TelaEdicao telaEdicao;
    ArrayList<AbstractTelaAlgoritmo> algoritmos;
    AbstractTelaGrafo atual;

    MainView(){
        controller = new GrafoController();

        initComponent();
    }

    void initComponent(){
        this.setPreferredSize(new Dimension(Paleta.WINDOW_WIDTH,Paleta.WINDOW_HEIGHT));

        this.telaEdicao = new TelaEdicao(controller);
        this.algoritmos = new ArrayList<AbstractTelaAlgoritmo>();
        this.algoritmos.add(new BreadthFirstSearch(controller));
        this.algoritmos.add(new DepthFirstSearch(controller));
        this.algoritmos.add(new BiconnectedComponents(controller));
        BarraLateral barraLateral = new BarraLateral(this);
        
        this.add(telaEdicao,BorderLayout.CENTER);
        telaEdicao.setPreferredSize(new Dimension(Paleta.WINDOW_GRAPH_AREA_WIDTH,Paleta.WINDOW_GRAPH_AREA_HEIGHT));
        telaEdicao.setVisible(true);

        for(AbstractTelaAlgoritmo telaAlgoritmo:algoritmos){
            this.add(telaAlgoritmo,BorderLayout.CENTER);
            telaAlgoritmo.setPreferredSize(new Dimension(Paleta.WINDOW_GRAPH_AREA_WIDTH,Paleta.WINDOW_GRAPH_AREA_HEIGHT));
            telaAlgoritmo.setVisible(false);
        }

        this.add(barraLateral,BorderLayout.SOUTH);        
        barraLateral.setPreferredSize(new Dimension(Paleta.WINDOW_MENU_AREA_WIDTH,Paleta.WINDOW_MENU_AREA_HEIGHT));

        this.atual = telaEdicao;
    }

    public void mudaTelaGrafo(int id){
        controller.getDesenho().resetVerticesToDefault();
        controller.getDesenho().resetArestasToDefault();

        atual.setVisible(false);
        if(atual == telaEdicao)
            controller.getDesenho().centralizar(telaEdicao.getWidth(), telaEdicao.getHeight());

        if(id == -1){
            atual = telaEdicao;
        }
        else{
            atual = algoritmos.get(id);
        }
        
        atual.setVisible(true);
        
        if(atual instanceof AbstractTelaAlgoritmo)
            ( (AbstractTelaAlgoritmo) atual).executa();
    }

    public void randomizar(){
        AleatorioController.gerarGrafoAleatorio(controller);
        // organizar();
        controller.getDesenho().centralizar(atual.getWidth(), atual.getHeight());
        mudaTelaGrafo(-1);
    }

    public void salvar(){
        try{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int i = fileChooser.showSaveDialog(null);
            if(i != 1){
                File arquivo = fileChooser.getSelectedFile();
                FileController.salvarGrafo(controller,arquivo.getPath());
                atual.repaint();
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"Não foi possível salvar o arquivo.","Erro",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void carregar(){
        try{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int i = fileChooser.showOpenDialog(null);
            if(i == JFileChooser.APPROVE_OPTION){
                File arquivo = fileChooser.getSelectedFile();
                FileController.carregarGrafo(controller,arquivo);
                controller.getDesenho().centralizar(telaEdicao.getWidth(), telaEdicao.getHeight());
                mudaTelaGrafo(-1);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"Não foi possível carregar o arquivo.","Erro",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void organizar(){
        AleatorioController.forceDirectedPlacement(controller,atual.getWidth()-60,atual.getHeight()-60);
        controller.getDesenho().centralizar(atual.getWidth(), atual.getHeight());
        mudaTelaGrafo(-1);
    }

}
