package chesu.modelo;

import chesu.modelo.excepciones.ArchivoExistenteException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Cesar Acosta
 */
public class EscribirArchivo {
    private File archivo;
    private int numMovimiento;
    private boolean turnoCompleto;
    private int turno;
    
    public EscribirArchivo(){
        this.archivo = null;
        this.numMovimiento = 0;
        this.turnoCompleto = false;
        this.turno = 0;
        
    }
    
    public void crearArchivo(String[] informacion) throws ArchivoExistenteException{
        String nombre = informacion[6];
        File archivo = new File(nombre);

        try {
            if(archivo.createNewFile()) {
                this.archivo = archivo;
            }else {
                throw new ArchivoExistenteException();
            }
        } catch (IOException e) {
            System.err.println("Error al intentar crear el archivo: " + e.getMessage());
            return;
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
        char columnaOrigen = letras[movimiento.getColumnaOrigen()];
        String captura = movimiento.esCaptura() ? "x" : "";
        String jaqueMate = movimiento.esJaqueMate() ? "#" : "";
        String pieza = movimiento.getTipo() == 'P' ? "" : Character.toString(movimiento.getTipo());
        String ganador = " " + movimiento.getGanador();
        String promocion = movimiento.getPromocion();
        
        String dato; 
        if(pieza.equals("") && captura.equals("x")){
            dato = " " + obtenerTurno() + columnaOrigen + captura  + columna + fila + promocion + jaqueMate;
        }else{
            dato = " " + obtenerTurno() + pieza + captura  + columna + fila + promocion + jaqueMate;

        }
        
        try(FileWriter escribir = new FileWriter(archivo, true)){
            if(jaqueMate.equals("#")){
                escribir.write(dato);
                escribir.write(ganador);
            }else{
                escribir.write(dato);
            }
            
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
    
    public boolean borrarArchivo(){
        return archivo.delete();
    }

    public boolean getArchivo(){
        return archivo != null;
    }
}
