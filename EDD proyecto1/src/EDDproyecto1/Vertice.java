
package EDDproyecto1;

import java.util.Random;


public class Vertice {
    
    private String dato;
    private int x;
    private int y;
    private int d;
    private int[] xy;
    
    
    // Se hace el Constructor
    
    public Vertice(String dato){
        
        Random ran = new Random();
        
        this.dato = dato;
        this.x = ran.nextInt(500);
        this.y = ran.nextInt(500);
        this.d = 30;
        this.xy = new int[2];
        
    }

    // Se hacen los Getters and Setters 
    
    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int[] getXy() {
        return xy;
    }

    public void setXy(int[] xy) {
        this.xy = xy;
    }
    
    // Funciones y Metodos
    
    // Metodo para obtener la ubicacion 
    
    public int[] Ubicacion(){
    
        this.xy[0] = this.x;
        this.xy[1] = this.y;
        
        return xy;
    }
    
}
