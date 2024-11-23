package chesu.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar Acosta
 */
public class EscribirArchivo {
    private boolean archivoCreado;
    private File archivo;
    private int numMovimiento;
    private boolean turnoCompleto;
    private int turno;
    
    public EscribirArchivo(){
        this.archivoCreado = false;
        this.numMovimiento = 0;
        this.turnoCompleto = false;
        this.turno = 0;
        
    }
    
    public void crearArchivo(String[] informacion){
        String nombre = informacion[6];
        File archivo = new File(nombre);
        System.out.println(nombre);
        try {
            if(archivo.createNewFile()) {
                archivoCreado = true;
                this.archivo = archivo;
            }else {
                archivoCreado = false;
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo.");
            e.printStackTrace();
        }
        
        crearMetaDatos(informacion);
    }
    
    private void crearMetaDatos(String[] informacion){
        try(FileWriter escribir = new FileWriter(archivo)){
            
            escribir.write("[Event " + '"' + informacion[0] + '"' + "]\n");
            escribir.write("[Site " + '"' + informacion[1] + '"' + "]\n");
            escribir.write("[Date " + '"' + informacion[2] + '"' + "]\n");
            escribir.write("[Round " + '"' + informacion[3] + '"' + "]\n");
            escribir.write("[White " + '"' + informacion[4] + '"' + "]\n");
            escribir.write("[Black " + '"' + informacion[5] + '"' + "]\n");
            
        }catch (IOException e) { 
            System.out.printf("An exception occurred %s", e.getMessage()); 
        }
    }
    
    public void escribirMovimiento(Movimiento movimiento){
        char[] letras = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        int fila = 8 - movimiento.getFila();
        char columna = letras[movimiento.getColumna()];
        String captura = movimiento.esCaptura() ? "x" : "";
        char pieza = movimiento.getTipo() == 'P' ? '\0' : movimiento.getTipo();
        String dato = " " + obtenerTurno() + pieza + captura  + columna + fila;
        
        try(FileWriter escribir = new FileWriter(archivo, true)){
            escribir.write(dato);
        }catch (IOException e) { 
            System.out.printf("An exception occurred %s", e.getMessage()); 
        }
    }
    
    private String obtenerTurno() {
        // Caso especial: primera llamada
        if (turno == 0 && numMovimiento == 0) {
            numMovimiento = 1; 
            turno++;          
            return " " + numMovimiento + ". "; 
        }

        turno++; 

        if (turno == 2) { 
            turnoCompleto = true;
            turno = 0; 
            numMovimiento++;
            return " ";
        } else {
            turnoCompleto = false;
            if (turno == 1) {
                return numMovimiento + ". ";
            }
            return null;
        }
    }

    public boolean getArchivoCreado(){
        return archivoCreado;
    }
}
