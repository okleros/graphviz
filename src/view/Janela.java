package src.view;

import javax.swing.JFrame;
import src.view.constantes.Paleta;

public class Janela extends JFrame
{
    public Janela()
    {
        this.setTitle("Vizualizador de Grafos");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(Paleta.WINDOW_WIDTH, Paleta.WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);

        this.setIconImage(Paleta.WINDOW_ICONE.getImage());
        Paleta.initializeCustomFonts();
        
        this.add(new MainView());
    }

    public static void main(String[] args) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new Janela().setVisible(true);
            }
        });    
    }

}