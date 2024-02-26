
package EDDproyecto1;

import Interfaz.Interfaz;
import java.awt.EventQueue;
import javax.swing.JFrame;
import Interfaz.*;


public class EDDproyecto1 extends JFrame {

    public static Grafo grafo = new Grafo();
    public static Hormiga[] hormigas = new Hormiga[50];
    
        public EDDproyecto1() {

            initUI();
        }

        private void initUI() {

        add(new Surface());

        setTitle("Proyecto I");
        setSize(700, 700);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        
        
    public static void main(String[] args) {
        
        Interfaz interfaz = new Interfaz();
        interfaz.setVisible(true);
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                EDDproyecto1 ex = new EDDproyecto1();
                ex.setVisible(false);
            }
        });
        
    }
}
