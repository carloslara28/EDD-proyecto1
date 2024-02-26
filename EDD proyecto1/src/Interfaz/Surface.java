
package Interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import static EDDproyecto1.EDDproyecto1.grafo;

public class Surface extends JPanel {
    
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        int w = this.getWidth();
        int h = this.getHeight();
       
        // Itera sobre todas las aristas del grafo
        
        for (int i = 0; i < grafo.getAristasvalor().length; i++) {
            
            for (int j = 0; j < grafo.getAristasvalor().length; j++) {
                // Si la arista tiene un valor distinto de  0, dibuja la línea y el valor de la arista
                
                if (grafo.getAristasvalor()[i][j] != 0) {
                    
                    int x1 = grafo.getVertices()[i].getX();
                    
                    int y1 = grafo.getVertices()[i].getY();
                    
                    int x2 = grafo.getVertices()[j].getX();
                    
                    int y2 = grafo.getVertices()[j].getY();
                    
                    
                    g2d.setColor(Color.BLACK);
                    g2d.drawLine(x1, y1, x2, y2);
                    
                    g2d.setPaint(Color.BLACK);
                    g2d.drawString(Integer.toString(grafo.getAristasvalor()[i][j]), (x1+x2)/2, (y1+y2)/2);
                    
                   
                }
                
            }
  
        }
        
        // Itera sobre todos los vértices del grafo para dibujarlos
        
        for (int i = 0; i < grafo.getNumVertices(); i++) {
            
            int x = grafo.getVertices()[i].getX();
            
            int y = grafo.getVertices()[i].getY();
            
            int d = 40;
            
            // Cambia el color del círculo según su posición
             
            if (i == 0) {
                
                g2d.setColor(Color.green);
                g2d.drawOval(x - (d/2) % w, y - (d/2) % h, d, d);
                g2d.fillOval(x - (d/2) % w, y - (d/2) % h, d, d);
                g2d.setColor(Color.black);
                g2d.drawString(grafo.getVertices()[i].getDato(), x , y);
                
            }
            else if (i == grafo.getNumVertices() - 1) {
                
                g2d.setColor(Color.red);
                g2d.drawOval(x - (d/2) % w, y - (d/2) % h, d, d);
                g2d.fillOval(x - (d/2) % w, y - (d/2) % h, d, d);
                g2d.setColor(Color.black);
                g2d.drawString(grafo.getVertices()[i].getDato(), x , y );
                
            }
            else{
            
                g2d.setColor(Color.gray);
                g2d.drawOval(x - (d/2) % w, y - (d/2) % h, d, d);
                g2d.fillOval(x - (d/2) % w, y - (d/2) % h, d, d);
                g2d.setColor(Color.black);
                g2d.drawString(grafo.getVertices()[i].getDato(), x , y );
            }
            
        }
        
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}


