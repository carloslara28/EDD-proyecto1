
package EDDproyecto1;

import java.io.*;

public class Manejoarchivo {    
    public Manejoarchivo(){    
    }

    public Grafo CargarGrafo(String direccion){ // Direccion del archivo a leer    
        String [] texto;
        String []linea;  
        boolean [][] boleanotemp = new boolean [20][20];
        int [][] intertemp= new int [20][20]; 
        Vertice [] vertice= new Vertice[20];            
        int contador =0;
        Grafo grafo = new Grafo(); 
        try{   
            FileReader fr = new FileReader(direccion);
            BufferedReader br = new BufferedReader(fr);
            String temp = "";
            String bfRead;
            while((bfRead = br.readLine()) != null){
                // Ciclo mientra bfRead tiene datos   
                temp += bfRead; // Guardado el texto del archivo
                temp +="\n";
            } 
            texto = temp.split("\n");
            for (int i = 0; i < texto.length; i++) {    
                if(texto[i].equalsIgnoreCase("Datos Normales")){    
                    grafo.setMaxNodos(Integer.parseInt(texto[i+1]));
                    grafo.setCiudadI(Integer.parseInt(texto[i+2]));
                    grafo.setCiudadF(Integer.parseInt(texto[i+3]));
                    grafo.setDirigido(Boolean.parseBoolean(texto[i+4]));
                    grafo.setNumVertices(Integer.parseInt(texto[i+5]));
                    grafo.setAlfa(Integer.parseInt(texto[i+6]));
                    grafo.setBeta(Integer.parseInt(texto[i+7]));
                    grafo.setPhi(Double.parseDouble(texto[i+8])); 
                    i +=8;
                    continue;
                }                
                else if(texto[i].equalsIgnoreCase("Ciudades")|| texto[i].equalsIgnoreCase("Fin Ciudades")){                
                    if(texto[i].equalsIgnoreCase("Fin Ciudades")){                   
                        grafo.setVertices(vertice);                        
                        contador =0;
                        continue;
                    }                    
                    i++;                    
                    Vertice tempvertice = new Vertice(texto[i]);                   
                    tempvertice.setX(Integer.parseInt(texto[i+1])); 
                    tempvertice.setY(Integer.parseInt(texto[i+2]));
                    tempvertice.setD(Integer.parseInt(texto[i+3]));                    
                    int [] n3 = new int[2];                    
                    n3[0] = Integer.parseInt(texto[i+4]);
                    n3[1] = Integer.parseInt(texto[i+5]);                                        
                    tempvertice.setXy(n3);                   
                    i +=5;                    
                    vertice[contador] = tempvertice;
                    contador++;
                }                
                else if(texto[i].equalsIgnoreCase("Dirrecciones")|| texto[i].equalsIgnoreCase("Fin Dirrecciones")){                    
                    if(texto[i].equalsIgnoreCase("Fin Dirrecciones")){                
                        grafo.setMatrizAdy(boleanotemp);                        
                        contador =0;
                        continue;
                    }                   
                    i++;                
                    linea = texto[i].split(",");                   
                    for (int j = 0; j < grafo.getNumVertices(); j++) {                       
                        if(linea[j].equalsIgnoreCase("1")){                       
                            boleanotemp[contador][j] =true;
                        }                       
                        else{                        
                            boleanotemp[contador][j] =false;
                        }
                    }                    
                    contador++;                   
                    continue;
                }                
                else if(texto[i].equalsIgnoreCase("Aristas")|| texto[i].equalsIgnoreCase("Fin Arista")){                    
                    if(texto[i].equalsIgnoreCase("Fin Arista")){                
                        grafo.setAristasvalor(intertemp);                        
                        contador =0;
                        continue;
                    }                    
                    i++;               
                    linea = texto[i].split(",");                   
                    for (int j = 0; j < grafo.getNumVertices(); j++) {                        
                        intertemp[contador][j] = Integer.parseInt(linea[j]);
                    }                    
                    contador++;                   
                    continue;
                }
            }
        }
        catch(Exception e){        
            System.err.println("No se encontro archivo. " + e);            
            return null;
        }        
        grafo.IniciarFeromonas();        
        return grafo;
    }
    
    public void GuardarGrafo(String nombre_txt, Grafo grafo){    
        String ruta = "C:\\Users\\Carlos Manuel\\OneDrive\\Documentos\\"+nombre_txt+".txt";        
        try{            
            File archivo = new File(ruta);                
            System.out.println("Se ha creado el archivo.");                
            PrintWriter pw = new PrintWriter(ruta);               
            pw.append("Datos Normales\n");                
            pw.append(grafo.getMaxNodos()+"\n");
            pw.append(grafo.getCiudadI()+"\n");
            pw.append(grafo.getCiudadF()+"\n");
            pw.append(grafo.isDirigido()+"\n");
            pw.append(grafo.getNumVertices()+"\n");
            pw.append(grafo.getAlfa()+"\n");
            pw.append(grafo.getBeta()+"\n");
            pw.append(grafo.getPhi()+"\n");                
            for (int i = 0; i < grafo.getVertices().length; i++) {                
                if(grafo.getVertices()[i] ==null){                
                    break;
                }                
                pw.append("Ciudades\n");                    
                pw.append(grafo.getVertices()[i].getDato()+"\n");
                pw.append(grafo.getVertices()[i].getX()+"\n");
                pw.append(grafo.getVertices()[i].getY()+"\n");
                pw.append(grafo.getVertices()[i].getD()+"\n");               
                pw.append(grafo.getVertices()[i].getXy()[0]+"\n");
                pw.append(grafo.getVertices()[i].getXy()[1]+"\n");
            }                
            pw.append("Fin Ciudades\n");               
            pw.append("Dirrecciones\n");                
            for (int i = 0; i < grafo.getNumVertices(); i++) {            
                for (int j = 0; j < grafo.getNumVertices(); j++) {                       
                    if(grafo.getMatrizAdy()[i][j]){                        
                        pw.append("1,");
                    }                        
                    else{                        
                        pw.append("0,");
                    }
                }                    
                pw.append("\n");                    
                if(i != grafo.getNumVertices()-1){                   
                    pw.append("Dirrecciones\n");
                }
            }                
            pw.append("Fin Dirrecciones\n");                
            pw.append("Aristas\n");                
            for (int i = 0; i < grafo.getNumVertices(); i++) {            
                for (int j = 0; j < grafo.getNumVertices(); j++) {                        
                    if(grafo.getMatrizAdy()[i][j]){                         
                        pw.append(grafo.getAristasvalor()[i][j]+",");
                    }                        
                    else{                        
                        pw.append("0,");
                    }
                }                    
                pw.append("\n");                    
                if(i != grafo.getNumVertices()-1){                   
                    pw.append("Aristas\n");
                }
            }                
            pw.append("Fin Arista\n");                
            pw.close();            
        }
        catch(IOException e){        
            System.err.println("No se ha podido crear el archivo. " + e);
        }
        
    }
  
    public int EncontrarMaximoDelGrafo(String direccion){
        try{           
            FileReader fr = new FileReader(direccion);          
            BufferedReader br = new BufferedReader(fr);          
            String temp = "";         
            String bfRead;         
            int contador = 0;         
            while((bfRead = br.readLine()) != null){
                // Ciclo mientra bfRead tiene datos              
                temp = bfRead;             
                if(contador ==1){             
                temp.replaceAll("\n", "");
                break;
                }
                contador++;
            }
            return Integer.parseInt(temp);    
        }
        catch(Exception e){
        }
        return -1;
    }
}
