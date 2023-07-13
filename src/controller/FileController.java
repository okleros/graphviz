package src.controller;

import java.awt.image.BufferedImage;
import java.awt.Point;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import src.model.ArestaGenerico;
import src.view.telasGrafos.TelaVisualizacao;

public class FileController 
{
    // Transforma todo o grafo em um vetor de bytes e escreve para um arquivo jpeg
    public static void salvarGrafo(GrafoController controller, String nome) throws Exception 
    {
        byte tag[] = new byte[2]; tag[0] = (byte) 0xFF; tag[1] = (byte) 0xFE; 
        
        ArrayList<Integer> arr = new ArrayList<>();
        
        arr.add(controller.getN()); arr.add(controller.getM());
        
        for (int i = 0; i < controller.getN(); i++) 
        {
            if (controller.verticeExiste(i)) 
            {
                arr.add((int) controller.getDesenho().getVertice(i).getCentro().getX()); 
                arr.add((int) controller.getDesenho().getVertice(i).getCentro().getY());
            
            } else 
            {
                arr.add(-1); arr.add(-1);
            }
        }
        for (int i = 0; i < controller.getN(); i++) 
        {
            if (!controller.verticeExiste(i)) continue;
            
            ArrayList<ArestaGenerico> adj = controller.getAdjList(i);
            
            for (ArestaGenerico e : adj) 
            {
                if (e.getOrigem() < e.getDestino()) 
                {
                    arr.add(e.getDestino()); 
                    arr.add(e.getOrigem()); 
                    arr.add(-1); //Peso
                }
            }
        }
        
        TelaVisualizacao panel = new TelaVisualizacao(controller);
        
        arr.add(arr.size());
        
        BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        panel.print(img.getGraphics());
        
        try 
        {
            ImageIO.write(img, "jpeg", new File(nome));
            
            Path p = FileSystems.getDefault().getPath(nome);
            
            byte [] fileData = Files.readAllBytes(p);
            
            DataOutputStream stream = new DataOutputStream(new FileOutputStream(nome));
            
            stream.write(fileData);
            stream.write(tag);
            
            for (int i:arr)
            {    
                stream.writeInt(i);
            }
            
            stream.close();
        
        } catch (IOException e) 
        {
            e.printStackTrace();
            throw e;
        }
    }

    private static int bytesToInt(byte b1, byte b2, byte b3, byte b4)
    {
        long ans = 0;
        
        ans |= (int) b1;
        ans <<= 8;
        ans |= (int) b2;
        ans <<= 8;
        ans |= (int) b3;
        ans <<= 8; 
        ans |= (int) b4;

        return (int) ans;
    }

    /*  Faz o inverso da função de salvar, lê os bytes de um jpeg qualquer,
        e como o salvamento é feito de forma padronizada, o carregamento só
        funcionará se a imagem de entrada tiver o mesmo formato de bytes que
        a função de salvar gera, ou seja, só podemos abrir grafos que foram
        gerados no nosso software ou com técnica semelhante.    */
    public static void carregarGrafo(GrafoController controller, File img) throws Exception 
    {
        Path p = FileSystems.getDefault().getPath(img.getAbsolutePath());
        try 
        {
            //vetor de bytes da imagem
            byte [] fileData = Files.readAllBytes(p);

            // Calcula quantos bytes da imagem temos que ignorar até chegar no primeiro byte que faz parte da informação do grafo
            int pos_qnt = fileData.length - 4;
            int qnt_int = bytesToInt(fileData[pos_qnt], fileData[pos_qnt + 1], fileData[pos_qnt + 2], fileData[pos_qnt + 3]);
            int ini = pos_qnt - (4 * qnt_int);
            
            //Ler de fato
            DataInputStream stream = new DataInputStream(new FileInputStream(img));
            stream.skipBytes(ini);

            int n = stream.readInt();
            int m = stream.readInt();
            
            controller.clear();
            
            ArrayList<Integer> excluir = new ArrayList<>();
            
            for (int i = 0, x, y; i < n; i++)
            {
                x = stream.readInt();
                y = stream.readInt();
          
                controller.adicionaVertice(new Point(x, y));
                
                if (x == -1) excluir.add(i);
            }

            for (int i = 0, u, v, w; i < m; i++)
            {
                u = stream.readInt();
                v = stream.readInt();
                w = stream.readInt(); // Peso
                
                controller.adicionaAresta(u, v);
            }
            
            for (int e : excluir)
            {
                controller.removeVertice(e);
            }

            stream.close();
            
        }
        catch (Exception e) 
        {
            throw e;
        }
    }

}
