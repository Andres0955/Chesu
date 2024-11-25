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
    
    /**
 * Clase para gestionar la creación y escritura de un archivo con la información 
 * de una partida de ajedrez.
 */
    public EscribirArchivo(){
        this.archivo = null;
        this.numMovimiento = 0;
        this.turnoCompleto = false;
        this.turno = 0;
        
    }
    
    /**
     * Constructor por defecto que inicializa el archivo a null y establece los contadores 
     * de movimiento y turno en cero.
     */
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
    
    /**
     * Crea un archivo con la información proporcionada en el arreglo {@code informacion}.
     * Si el archivo ya existe, lanza una excepción.
     * 
     * @param informacion Un arreglo de cadenas que contiene información sobre el evento,
     *                    sitio, fecha, ronda, jugador con blancas, jugador con negras, 
     *                    y nombre del archivo.
     * @throws ArchivoExistenteException si el archivo ya existe.
     */
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
    
    /**
     * Escribe un movimiento en el archivo, incluyendo el tipo de pieza, posición, captura, 
     * enroque, jaque mate y promoción si corresponde.
     * 
     * @param movimiento El objeto {@code Movimiento} que contiene la información del movimiento.
     */
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
        if(movimiento.getEnroqueCorto()){
            dato = " " + obtenerTurno() + "0-0";
        }else if(movimiento.getEnroqueLargo()){
            dato = " " + obtenerTurno() + "0-0-0";
        }else if(pieza.equals("") && captura.equals("x")){
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
    
    /**
     * Obtiene el turno actual del juego en formato de cadena. 
     * Actualiza el contador de movimientos y el estado del turno.
     * 
     * @return El turno actual en formato de cadena.
     */
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
    
    /**
     * Borra el archivo creado.
     * 
     * @return {@code true} si el archivo fue borrado con éxito, {@code false} en caso contrario.
     */
    public boolean borrarArchivo(){
        return archivo.delete();
    }

    /**
     * Verifica si existe un archivo creado.
     * 
     * @return {@code true} si existe un archivo creado, {@code false} en caso contrario.
     */
    public boolean getArchivo(){
        return archivo != null;
    }
}
