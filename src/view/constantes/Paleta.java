package src.view.constantes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.ImageIcon;

public class Paleta {
    public static Color BACKGROUND_GRAFO_DEFAULT = new Color(0xE1E1E1); //new Color(0xF5F5F5);
    public static Color BACKGROUND_MENU_DEFAULT = new Color(0x353535); 
    public static Color TRANSPARENTE = new Color(0x00000000);
    public static Color MENU_BOTAO_COR_DEFAULT = new Color(0x3B3B3B); 
    public static Color MENU_BOTAO1_COR_DEFAULT = new Color(0x151515); 
    public static Color MENU_BOTAO_FONT_COR_DEFAULT = new Color(0xFFFFFF); 
    public static Font MENU_FONT_DEFAULT = new Font("Serif",Font.PLAIN, 25);
    public static Font MENU_FONT_SMALL = new Font("Serif",Font.PLAIN, 12);

    public static int RIGHT_BUTTON = 3;
    public static int LEFT_BUTTON = 1;

    public static Color STROKE_DEFAULT = new Color(0x515151);
    public static BasicStroke STROKE_WIDTH_DEFAULT = new BasicStroke(2);
    public static Color FILL_DEFAULT = BACKGROUND_GRAFO_DEFAULT;
    
    public static Font LABEL_FONT_DEFAULT = new Font("Serif",Font.PLAIN, 25);
    public static Color LABEL_COLOR_DEFAULT = new Color(0x000000);;

    public static int VERTICE_RAIO_DEFAULT = 25;

    //Constantes para cor para algoritmos
    public static Color ALGORITHM_0 = new Color(0xFFBA91);
    public static Color ALGORITHM_1 = new Color(0x9A76C9);
    public static Color ALGORITHM_2 = new Color(0x6893DE);
    public static Color ALGORITHM_3 = new Color(0x15997A);

    public static BasicStroke DASHED_STROKE = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
    public static BasicStroke WIDE_STROKE = new BasicStroke(3);

    //Tamanho da janela
    public static int WINDOW_HEIGHT = 700;
    public static int WINDOW_WIDTH = 1200;

    public static int WINDOW_GRAPH_AREA_HEIGHT = 590;
    public static int WINDOW_GRAPH_AREA_WIDTH = WINDOW_WIDTH;
    
    public static int WINDOW_MENU_AREA_HEIGHT = WINDOW_HEIGHT - WINDOW_GRAPH_AREA_HEIGHT;
    public static int WINDOW_MENU_AREA_WIDTH = WINDOW_WIDTH;

    public static ImageIcon WINDOW_ICONE = new ImageIcon("assets/icone.png");

    //Funções para carregar fontes tipográficas
    public static void initializeCustomFonts(){
        Font Roboto = loadFont("assets/fonts/Roboto/Roboto-Thin.ttf");
        LABEL_FONT_DEFAULT = Roboto.deriveFont(Font.BOLD,20);
        MENU_FONT_DEFAULT = Roboto.deriveFont(Font.BOLD,23);
        MENU_FONT_SMALL = Roboto.deriveFont(Font.BOLD,12);
    }

    private static Font loadFont(String path){
        try {
            Font nova = Font.createFont(Font.TRUETYPE_FONT,new File(path));    
            
            return nova;
        
        } catch (Exception e) {
            e.printStackTrace();
            
            System.err.println("Não foi possível carregar fontes personalizadas. ");
            
            return null;
        }
    }


}
