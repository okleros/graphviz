package src.view.telasGrafos.algoritmos;

import src.controller.GrafoController;

import src.view.constantes.Paleta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Font;
import java.awt.BorderLayout;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class AbstractTelaAlgoritmoAnimado extends AbstractTelaAlgoritmo {
    Timer timer;

    ArrayList<Alteration> arr;
    int pos;

    AbstractTelaAlgoritmoAnimado(GrafoController controller){
        super(controller);

        this.setLayout(new BorderLayout());
        BarraDeOpcoes barra = new BarraDeOpcoes();
        this.add(barra,BorderLayout.PAGE_END);
        
    }

    abstract public void executa();

    public void animate(){
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(arg0.getSource()==timer)
                    step();
            }
        });

        timer.setDelay(200);
        timer.start(); 
    }

    public void step() { 
        if(arr == null) return;
        if(pos < arr.size()) {
            arr.get(pos++).modifica();
            repaint();
        }
        else if (timer != null && timer.isRunning()){
            timer.stop();
        }
    }

    public void backstep() {
        if(arr == null) return;
        if(pos >= 1){
            arr.get(--pos).retorna();
            repaint();
        }
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if(aFlag == false && timer != null){
            arr = null;
            pos = 0;
            timer.stop();
        }
    }

    private void toogleTimer(){
        if(timer != null){
            if(timer.isRunning())
                timer.stop();
            else
                timer.restart();
        }
    }

    class BarraDeOpcoes extends JPanel {
        JButton last, auto, next;

        BarraDeOpcoes() {
            initComponent();
        }

        void initComponent(){
            last = new JButton("<<");
            this.add(last);

            auto = new JButton("II");
            this.add(auto);

            next = new JButton(">>");
            this.add(next);

            auto.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == auto){
                        toogleTimer();   
                        
                        if(timer != null && timer.isRunning()) auto.setText("II");
                        if(timer != null && !timer.isRunning()) auto.setText(">");
                    }
                }
            });

            
            last.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == last){
                        if(timer != null && timer.isRunning()){
                            toogleTimer();  
                            if(timer != null && timer.isRunning()) auto.setText("||");
                            if(timer != null && !timer.isRunning()) auto.setText(">");
                        }
                        backstep();
                    }
                }
            });

            
            next.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == next){
                        if(timer != null && timer.isRunning()){
                            toogleTimer();   
                            if(timer != null && timer.isRunning()) auto.setText("||");
                            if(timer != null && !timer.isRunning()) auto.setText(">");
                        }
                        
                        step();
                    }
                }
            });

            this.setBackground(Paleta.TRANSPARENTE);
            
            for(Component c : this.getComponents()){
                c.setBackground(Paleta.MENU_BOTAO1_COR_DEFAULT);
                c.setFont(Paleta.MENU_FONT_DEFAULT.deriveFont(Font.BOLD,12));
                c.setForeground(Paleta.MENU_BOTAO_FONT_COR_DEFAULT);
                c.setFocusable(false);
                
                if(c instanceof JButton)
                    ((JButton)c).setBorderPainted(false);
            }

        }
    }

}
