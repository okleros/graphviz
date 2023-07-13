package src.view;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxUI;

import src.view.constantes.Paleta;

public class BarraLateral extends JPanel 
{
    ArrayList<JButton> botoes;
    JComboBox<String> algoritmo;
    MainView pai;
    JButton editor;

    BarraLateral(MainView pai)
    {
        this.pai = pai;

        this.setBackground(Paleta.BACKGROUND_MENU_DEFAULT);
        this.setFont(Paleta.LABEL_FONT_DEFAULT);
        this.setPreferredSize(new Dimension(Paleta.WINDOW_MENU_AREA_WIDTH, Paleta.WINDOW_MENU_AREA_HEIGHT));
        this.setLayout(null);

        this.criarItems();

    }
    
    public static class ComboBoxBorderless extends BasicComboBoxUI 
    {
        @Override
        protected void installDefaults() 
        {
            super.installDefaults();
            LookAndFeel.uninstallBorder(comboBox); 
        }

        @Override
        protected JButton createArrowButton() 
        {
            final JButton button = new JButton("+");
            button.setFont(Paleta.MENU_FONT_DEFAULT);
            button.setName("ComboBox.arrowButton");
            return button;
        }

        @Override
        public void configureArrowButton() 
        {
            super.configureArrowButton();
            arrowButton.setBackground(Paleta.MENU_BOTAO1_COR_DEFAULT);
            arrowButton.setForeground(Color.white);
            arrowButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        }

        @Override
        public Dimension getMinimumSize(final JComponent c) 
        {
            final Dimension mindim = super.getMinimumSize(c);
            final Insets buttonInsets = arrowButton.getInsets();
            return new Dimension(mindim.width + buttonInsets.left + buttonInsets.right, mindim.height + buttonInsets.top + buttonInsets.bottom);
        }

    }

    // Cria todos os botões e menus da barra lateral
    public void criarItems()
    {
        int larguraBotao = 125, alturaBotao = 45;
        int offsetY = 8, offsetX = 27;
        int hskip = 10;
        
        UIManager.put("ComboBox.borderPaintsFocus", Boolean.FALSE);
        
        int x = 0;
        
        botoes = new ArrayList<JButton>();
        
        editor = new JButton("Editor");
        editor.setPreferredSize(new Dimension(larguraBotao, alturaBotao));
        editor.setBounds(x + offsetX, offsetY, larguraBotao, alturaBotao);

        editor.setBorderPainted(false);
        editor.setFocusable(false);
        
        this.add(editor);
        
        editor.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
            {
                pai.mudaTelaGrafo(-1);
			}
        });

        x += larguraBotao + hskip;

        String opcoesAlgoritmo[] = {"BFS", "DFS", "Biconnected components"};
        
        algoritmo = new JComboBox<String>(opcoesAlgoritmo);
        
        algoritmo.setUI(new ComboBoxBorderless());
        algoritmo.setSelectedIndex(0);
        algoritmo.setFocusable(false);
        algoritmo.setBounds(x + offsetX, offsetY, 2 * larguraBotao, alturaBotao);
        
        this.add(algoritmo);
        
        x += 2 * larguraBotao + hskip;

        for (int i = 0; i < 5; i++)
        {
            JButton novo = new JButton("");
            novo.setPreferredSize(new Dimension(larguraBotao,alturaBotao));
            
            if (i == 1 || i == 4)
                novo.setBounds(x + offsetX, offsetY, 4 * larguraBotao / 3, alturaBotao);
            
            else
                novo.setBounds(x + offsetX, offsetY, larguraBotao, alturaBotao);
            
            botoes.add(novo);
            novo.setBorderPainted(false);
            
            this.add(novo);
            
            novo.setFocusable(false);
            
            if (i == 1)
                x += 4 * larguraBotao / 3 + hskip;
            else
                x += larguraBotao + hskip;
        }

        botoes.get(0).setText("Run");
        botoes.get(0).addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
            {
                if (e.getSource() == botoes.get(0))
                    pai.mudaTelaGrafo(algoritmo.getSelectedIndex());
			}
        });

        botoes.get(1).setText("Randomize");
        botoes.get(1).addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
            {
                if (e.getSource() == botoes.get(1))
                    pai.randomizar();
			}
        });

        botoes.get(2).setText("Save");
        botoes.get(2).addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
            {
                if (e.getSource() == botoes.get(2))
                    pai.salvar();
			}
        });

        botoes.get(3).setText("Load");
        botoes.get(3).addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
            {
                if (e.getSource() == botoes.get(3))
                    pai.carregar();
			}
        });

        botoes.get(4).setText("Organize");
        botoes.get(4).addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
            {
                if (e.getSource() == botoes.get(4))
                    pai.organizar();
			}
        });

        for (Component c : this.getComponents())
        {
            c.setFont(Paleta.MENU_FONT_DEFAULT);
            c.setBackground(Paleta.MENU_BOTAO_COR_DEFAULT);
            c.setForeground(Paleta.MENU_BOTAO_FONT_COR_DEFAULT);
        }
    }

}
