
package EDDproyecto1;

import java.util.PriorityQueue;
import javax.swing.JOptionPane;


public class Grafo {
    
    private boolean dirigido; // Indica si es dirigido o no. 
    private int maxNodos; // Tamaño máximo de la tabla. 
    private int numVertices; // Número de vértices del grafo. 
    private boolean matrizAdy [ ] [ ]; // Matriz de adyacencias del grafo.
    int aristasvalor[][]; // Matriz que contiene las distancias de vertice a vertice
    private double feromonas[][]; // Matriz con las feromonas de Ciudad a Ciudad
    private Vertice vertices[];
    private int ciudadI; // Vector Inicial / Posicion Inicial de las Hormigas
    private int ciudadF; // Vector Final / Posicion Comida
    private int alfa;
    private int beta;
    private double phi;
    
    // // Se crea el constructor
    
    public Grafo(){
    
        this.dirigido = false; 
        this.maxNodos = 20; 
        this.numVertices = 0;
        this.matrizAdy = new boolean[20][20];
        this.aristasvalor = new int[20][20];
        this.vertices = new Vertice[20];
        this.feromonas = new double[20][20];
        this.ciudadI = ciudadF = -1;
        this.alfa = 1;
        this.beta = 2;
        this.phi = 0.1;
        

    }
    
    // Se crean los Getters and Setters

    public boolean isDirigido() {
        return dirigido;
    }

    public void setDirigido(boolean dirigido) {
        this.dirigido = dirigido;
    }

    public int getMaxNodos() {
        return maxNodos;
    }

    public void setMaxNodos(int maxNodos) {
        this.maxNodos = maxNodos;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public boolean[][] getMatrizAdy() {
        return matrizAdy;
    }

    public void setMatrizAdy(boolean[][] matrizAdy) {
        this.matrizAdy = matrizAdy;
    }

    public int[][] getAristasvalor() {
        return aristasvalor;
    }

    public void setAristasvalor(int[][] aristasvalor) {
        this.aristasvalor = aristasvalor;
    }

    public double[][] getFeromonas() {
        return feromonas;
    }

    public void setFeromonas(double[][] feromonas) {
        this.feromonas = feromonas;
    }

    public int getCiudadI() {
        return ciudadI;
    }

    public void setCiudadI(int ciudadI) {
        this.ciudadI = ciudadI;
    }

    public int getCiudadF() {
        return ciudadF;
    }

    public void setCiudadF(int ciudadF) {
        this.ciudadF = ciudadF;
    }

    public Vertice[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertice[] vertices) {
        this.vertices = vertices;
    }

    public int getAlfa() {
        return alfa;
    }

    public void setAlfa(int alfa) {
        this.alfa = alfa;
    }

    public int getBeta() {
        return beta;
    }

    public void setBeta(int beta) {
        this.beta = beta;
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }
 
    // Metodos y Funciones
    
    // Método para insertar una arista entre dos vértices
    
    public void InsertaArista (int i, int j, int value){        
        matrizAdy [i] [j] = true;       
        aristasvalor[i][j] = value;      
        if (!dirigido){            
            matrizAdy [j] [i] = matrizAdy [i] [j];          
            aristasvalor[j][i] = aristasvalor[i][j];
        } 
    }
    
    // Método para eliminar una arista entre dos vértices
    
    public void EliminarArista (int i, int j){       
        matrizAdy [i] [j] = false;        
        if (!dirigido){            
            matrizAdy [j] [i] = false;
        } 
    }
    
    // Método para insertar un vértice en el grafo
    
    public void InsertaVertice (String dato){        
        if ( maxNodos == numVertices){           
            JOptionPane.showMessageDialog(null,"Error, se supera el número de nodos máximo");
        }    
        else{           
            Vertice nuevo = new Vertice(dato);     
            vertices[numVertices] = nuevo;         
            for (int i = 0; i < numVertices + 1; i++) {              
                for (int j = numVertices; j < numVertices + 1; j++){                  
                    matrizAdy [i] [j] = matrizAdy [j] [i] = false;
                } 
            }
            numVertices++;            
            ciudadF = numVertices-1;          
            if(numVertices >= 1){           
                ciudadI = 0;
            }
        } 
    }
    
    // Método para eliminar un vértice del grafo
    
    public void EliminarVertice(String ciudad){        
        int pos =-1;       
        for (int i = 0; i < numVertices; i++) {            
            if(ciudad.equalsIgnoreCase(vertices[i].getDato())){          
                pos = i;
                break;
            }
        }        
        if(numVertices>0 && pos >-1 && pos<numVertices){   
            for (int i = 0; i <numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if(matrizAdy [i] [j]){
                        if(i == pos || j==pos){
                            matrizAdy [i] [j] = false;
                            aristasvalor[i][j] = 0;
                        }
                    }
                    else{
                        matrizAdy [i] [j] = false;
                        aristasvalor[i][j] = 0;
                    }
                }
            }               
            if(pos != numVertices-1){
                for (int i = pos; i <numVertices; i++) {                    
                    if(i != numVertices-1){                   
                        vertices[i] = vertices[i+1];
                    }
                    for (int j = 0; j < numVertices; j++) {
                        if(i == (numVertices-1)){
                            matrizAdy [i] [j]=false;
                            aristasvalor[i][j] =0;
                            continue;
                        }
                        if(matrizAdy [i+1] [j]){
                            matrizAdy [i] [j]= matrizAdy [i+1] [j];
                            aristasvalor[i][j] = aristasvalor[i+1][j];
                        }
                    }
                }
                for (int i = 0; i <numVertices; i++) {
                    for (int j = pos; j < numVertices; j++) {
                        if(j == (numVertices-1)){
                            matrizAdy [i] [j]=false;
                            aristasvalor[i][j] =0;
                            continue;
                        }
                        if(matrizAdy [i] [j+1]){
                            matrizAdy [i] [j]= matrizAdy [i] [j+1];
                            aristasvalor[i][j] = aristasvalor[i][j+1];
                        }
                    }
                }
            }            
            vertices[numVertices-1] = null;            
            numVertices--;           
            ciudadF=numVertices-1;            
            if(numVertices ==0){            
                ciudadF = ciudadI = -1;
            }
        }       
        else{       
            System.out.println("Error, ciudad ingresada no valida");
        }
    }
    
    // Método para calcular el grado de entrada de un vértice
    
    public int GradoIn(int j) {         
    int gIn = 0;    
    for (int i = 0; i < numVertices; i++){ //recorrido por filas     
        if (matrizAdy [i] [j]){ //manteniendo la posición de la columna en [j]             
            gIn++;
            }
        }   
    return gIn; 
    }
    
    // Método para calcular el grado de salida de un vértice
    
    public int GradoOut(int i) {    
    int gOut = 0;   
    for (int j= 0; j < numVertices; j++){       
        if (matrizAdy [i][j]){           
            gOut++; // manteniendo la posición de la fila en [i]
            }   
        }   
    return gOut;
    }
    
    // Método para calcular la incidencia de un vértice
    
    public int Incidencia (int i) {         
        if (!dirigido){            
            return GradoIn (i);
        } 
        else{             
            return GradoIn(i) + GradoOut(i);
        } 
    }
    
    // Método para calcular el tamaño del grafo
    
    public int Tamano() {        
        int tm = 0;      
        for (int i = 0; i < numVertices; i++){            
            for (int j=0; j < numVertices; j++){               
                if (matrizAdy [i] [j]) tm++;
            }
        } 
        if (dirigido){             
            return tm;
        }         
        else return tm/2; 
    }
    
    // Método para verificar si el grafo es dirigido
    
    public boolean EsDirigido (Grafo g) {        
        boolean dir = true;       
        for (int i = 0; i < numVertices; i++){           
            for (int j = 0; j < numVertices; j++){               
                if (matrizAdy [i] [j] != matrizAdy [j] [i]){                   
                    dir = false;
                }
            }
        }
        
        return dir; 
    }
    
    // Método para imprimir la matriz de adyacencia y los valores de las aristas
    
    public void ImprimirTabla () {        
        System.out.println ("La matriz contiene " + numVertices + " vértices: \n");       
        for (int i = 0; i < numVertices; i++) {           
            for (int j = 0; j < numVertices; j++) {               
                if (matrizAdy [i] [j]){                 
                    System.out.print ("1 ");
                }            
                else{                  
                    System.out.print ("0 ");
                    aristasvalor[i][j] = 0;
                } 
            }           
            System.out.println("\n");
        }        
        System.out.println("El valor de las aristas es: \n");        
        for (int i = 0; i < numVertices; i++) {            
            for (int j = 0; j < numVertices; j++) {               
                if (aristasvalor[i][j] != -1) {                   
                    System.out.println(aristasvalor[i][j]+" ");
                }                
                else{                   
                    System.out.println("0 ");
                }
            }            
            System.out.println("\n");
        }      
    }
    
    // Método para inicializar las feromonas en el grafo
    
    public void IniciarFeromonas(){        
        double cantidad = ((double) 1 / (double) numVertices);        
        if (numVertices != 0) {            
            for (int i = 0; i < numVertices; i++) {                
                for (int j = 0; j < numVertices; j++) {                    
                    if (i != j) {                       
                        feromonas [i] [j] = cantidad;
                    }
                }
            }
        }
    }
    
    // Método para actualizar las feromonas en el grafo, donde p es la posicion y d el destino
    
    public void ActualizarFeromonas(int p, int d){       
        double incremento = ( (double) 1/(double) aristasvalor[p][d] );       
        feromonas[p][d] += incremento;       
        feromonas[d][p] += incremento;       
    }
    
    // Método para realizar la evaporación de las feromonas en el grafo
    
    public void EvaporacionFeromonas(){ 
        for (int i = 0; i < numVertices; i++) {          
            for (int j = 0; j < numVertices; j++) {              
                feromonas[i][j] *= ((double)1-phi); 
            }
        }
    } 
    
    // Método para verificar si el grafo tiene callejones sin salida
    
    public boolean ComprobarGrafo(){
        for (int i = 0; i < numVertices; i++) {     
            if(i!= numVertices-1){       
                if(Incidencia(i) <=1){             
                    return false;
                }
            }
        }    
        return true;
    }
    
    // Método para agregar una ciudad al grafo
    
    public void AgregarCiudad(int numAristas){ //n --> numero de aristas a agregar       
        if (numAristas > vertices.length) {          
            System.out.println("El numero de aristas a insertar es mayor a la cantidad de Vertices en el grafo.");
        }     
        if (numVertices == 0) {       
            String ciudadInsertar = JOptionPane.showInputDialog("Inserte el nombre de la Ciudad a Insertar: "); // Lo inserta el usuario       
            InsertaVertice(ciudadInsertar);
        }     
        else{        
            try{          
                String ciudadInsertar = JOptionPane.showInputDialog("Inserte el nombre de la Ciudad a Insertar: "); // Lo inserta el usuario        
                int posI = numVertices;         
                for (int i = 0; i < numAristas; i++) {         
                    String ciudadEnlazar = JOptionPane.showInputDialog("Inserte el nombre de la Ciudad a la que se quiere enlazar: "); // Lo inserta el usuario            
                    int value = 0; // Lo inserta el usuario               
                    int posJ = -1;                
                    for (int j = 0; j < numVertices; i++) {            
                        if(ciudadEnlazar.equalsIgnoreCase(vertices[j].getDato())){            
                            posJ = j;                       
                            break;
                        }
                    }                
                    if (posJ != -1) {            
                        InsertaVertice(ciudadInsertar);                    
                        InsertaArista(posI, posJ, value);
                    }
                    else{       
                    System.out.println("La ciudad: " + ciudadEnlazar + " no existe.");
                    }                
                } 
            }
            catch(Exception e){            
                JOptionPane.showMessageDialog(null, "Error. Asegurese de insertar un numero valido en las distancias de las aristas");
            }          
        }   
    }
    
    // Método para eliminar una ciudad del grafo
    
    public void EliminarCiudad(String dato){    
        EliminarVertice(dato);
    }
    
    // Método para obtener la posición de un vértice en el grafo
    
    public int ObtenerPosicionVertice(String nombre){        
        int pos = -1;        
        for (int i = 0; i < numVertices; i++) {            
            if(nombre.equalsIgnoreCase(vertices[i].getDato())){          
                pos = i;
                break;
            }
        }        
        return pos;
    }
    
    // Método para implementar el algoritmo de Dijkstra
    
    public int[] dijkistra() {        
    int inicio = ciudadI;
    int[] distancia = new int[numVertices];
    int[] padre = new int[numVertices];
    boolean[] visto = new boolean[numVertices];    
    int [] ciudades = new int[numVertices];    
    for (int i = 0; i < numVertices; i++) {       
        distancia[i] = 1200000000;
        padre[i] = -1;
        visto[i] = false;
        ciudades[i] = -1;
    }   
    distancia[inicio]=0;   
    PriorityQueue<Integer> pila = new PriorityQueue<>(); 
    pila.add(distancia[inicio]);
    while (!pila.isEmpty()){    
        int u = pila.poll();        
        visto[u] = true;  
        for (int i = 0; i < numVertices; i++) {        
            if (aristasvalor[u][i] != 0) {         
                if (distancia[i] > distancia[u] + aristasvalor[u][i]) {                   
                    distancia[i] = distancia[u] + aristasvalor[u][i];
                    padre[i] = u;
                    pila.add(i);
                }
            }
        }
    }  
    int i=0;
    ciudades[i] = ciudadF;   
    i++; 
    ciudades[i] = padre[numVertices-1];        
    while(padre[ciudades[i]] !=-1){  
        ciudades[i+1] = padre[ciudades[i]];    
        i++;
    }
    i=numVertices-1;
    int j=0;
    int [] temp = new int [ciudades.length];
    while(i!=-1){
        temp[j]=ciudades[i];
        j++;
        i--;
    }
    return temp;
    }
}
