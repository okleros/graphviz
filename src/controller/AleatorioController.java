package src.controller;

import java.util.Random;

import java.awt.Point;
import java.awt.geom.Point2D;

import src.view.constantes.Paleta;


public class AleatorioController 
{
    private static int randomInterval(int l, int r)
    {
        Random random = new Random();
        return l + (random.nextInt(r) % (r - l + 1));

    }

    private static Point2D.Double[] randomPoints(int n)
    {
        Point2D.Double answ[] = new Point2D.Double[n];
        
        for (int i = 0; i < n; i++)
            answ[i] = new Point2D.Double(randomInterval(50, Paleta.WINDOW_WIDTH - 50), randomInterval(50, Paleta.WINDOW_HEIGHT - 130)); 
        
        return answ;
    }

    public static void gerarGrafoAleatorio(GrafoController controller)
    {
        controller.clear();

        Random random = new Random();

        //Número de Vértices
        int n = random.nextInt(1, 25);            

        //Adicionar vértices na Tela
        Point2D.Double pontosDosVertices[] = randomPoints(n);
        for (int i = 0; i < n; i++)
            controller.adicionaVertice(new Point((int)pontosDosVertices[i].x, (int)pontosDosVertices[i].y));

        //Número de Arestas        
        double p = Math.random();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < i; j++)
                if (Math.random() <= p)
                    controller.adicionaAresta(i, j);        

    }

    public static void forceDirectedPlacement(GrafoController controller, int X, int Y) 
    {
        int n = controller.getN(), iter = 100;
        
        Point2D.Double p[], answ[] = new Point2D.Double[n];

        int bestCrossings = -1;
        for ( ; iter != 0; iter--)
        {
            p = randomPoints(n);
            fruchtermanReingold(controller, X, Y, p);
            if (bestCrossings == -1 || controller.getDesenho().getNumberOfCrossings() < bestCrossings) 
            {
                bestCrossings = controller.getDesenho().getNumberOfCrossings();
                answ = p;
            }
        }
        
        for (int i = 0; i < n; i++)
        {
            if (controller.getDesenho().getVertice(i) != null)
            {
                controller.getDesenho().getVertice(i).getCentro().move((int)answ[i].x, (int)answ[i].y);
            }
        }
    }

    private static void fruchtermanReingold(GrafoController controller, int X, int Y, Point2D.Double p[]) 
    {
        int n = controller.getN(), iter = 100;
        int adjMat[][] = controller.getAdjMatrix();
        Point2D.Double force[] = new Point2D.Double[n];
        
        double cRep = 0.9;
        double k = cRep * Math.sqrt(X * Y * 1.0 / controller.getN());
        double t = Math.min(X, Y) / 10;
        double delta = t / iter;
        double eps = 1e-6;

        for (int i = 0; i < n; i++)
        {
            force[i] = new Point2D.Double(0.0, 0.0);
        }

        for ( ; iter != 0; iter--)
        {
            for (int i = 0; i < n; i++)
            {
                force[i].x = 0;
                force[i].y = 0;

                for (int j = 0; j < n ; j++)
                {
                    if (j == i) continue;
                    double dist = p[j].distance(p[i]);
                    if (dist < eps) dist = eps;

                    //Calcula vetor unitario no mesmo sentido de p[i]p[j]
                    double unitx = (p[j].x - p[i].x) / dist;
                    double unity = (p[j].y - p[i].y) / dist;

                    if (dist < k)
                    { 
                        //Calcula força de repulsão
                        force[i].x -= unitx * (k * k / dist);
                        force[i].y -= unity * (k * k / dist);
                    }
                    if (adjMat[i][j] != 0)
                    {
                        //Calcula força de atração
                        force[i].x += unitx * (dist * dist / k);
                        force[i].y += unity * (dist * dist / k);
                    }
                }

                //Limita a força para garantir que vértices vão se manter na tela
                double norm = force[i].distance(0, 0);
                
                if (norm > eps) 
                {
                    force[i].x = (force[i].x / norm) * Math.min(norm, t);
                    force[i].y = (force[i].y / norm) * Math.min(norm, t);
                }

            }
            
            //Movimenta os vértices de acordo com as forças
            for (int i = 0; i < n; i++) 
            {
                p[i].x += (force[i].x);
                p[i].x = Math.max(Math.min(p[i].x, X), 0);
                p[i].y += (force[i].y);
                p[i].y = Math.max(Math.min(p[i].y, Y), 0);
            }
            
            t = Math.max(0, t - delta);
            
        }

        for (int i = 0; i < n; i++)
        {
            if (controller.getDesenho().getVertice(i) != null)
            {
                controller.getDesenho().getVertice(i).getCentro().move((int)p[i].x, (int)p[i].y);
            }
        }

    }

}
