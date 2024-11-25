package chesu.controlador;

import chesu.modelo.*;
import chesu.util.Sonido;
import chesu.vista.JpReproducirPartida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Timer;

/**
 *
 * @author Cesar Acosta
 */
public class ControlReproductor{
    private ControlPrincipal controlPrincipal;
    private JpReproducirPartida jpReproducirPartida;
    private ControlReproductor instancia;
    private Tablero tablero;
    private Sonido sonido;
    private Timer temporizador;
    private File archivo;
    
    public ControlReproductor(ControlPrincipal controlPrincipal, JpReproducirPartida jpReproducirPartida){
        this.instancia = this;
        this.controlPrincipal = controlPrincipal;
        this.jpReproducirPartida = jpReproducirPartida;
        this.sonido = new Sonido();
        this.tablero = new Tablero(instancia);
        this.temporizador = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                avanzar();
                jpReproducirPartida.actualizarPanel();
            }
        });
        this.archivo = new File("");
    }
    
    /**
     * Obtiene el archivo que el usuario selecciono y posteriormente carga la partida.
     * @param archivoSeleccionado el archivo que el usuario selecciono mediante el file chooser.
     */
    public void obtenerArchivo(File archivoSeleccionado){
        this.archivo = archivoSeleccionado;
        cargarPartida();
    }
    
    /**
     * Crea una instancia de la clase LeerArchivo para obtener los movimientos
     * y mandarlos al tablero y al jpPartida para que puedan cargar las piezas 
     * en su posicion predeterminada.
     */
    private void cargarPartida(){
        LeerArchivo lector = new LeerArchivo();
        tablero.setMovimientosPgn(lector.leerYcargarArchivo(archivo));
        jpReproducirPartida.setPosiciones(tablero.getPosiciones());
    }
    
    /**
     * Reproduce un movimiento y recibe la matriz actualizada con ese movimiento
     * para luego mandarla a la vista y el usuario pueda visualizar el movimiento.
     */
    public void avanzar(){
        Piezas[][] posiciones = tablero.reproducirSiguienteMovimiento();
        sonido.reproducir();
        jpReproducirPartida.setPosiciones(posiciones);
    }
    
    /**
     * Obtiene la matriz que contiene un moviento previo y manda esta matriz a la
     * vista para poder ser visualizada.
     */
    public void retroceder(){
        Piezas[][] posiciones = tablero.extraerMatriz();
        jpReproducirPartida.setPosiciones(posiciones);
        jpReproducirPartida.actualizarPanel();
        sonido.reproducir();
        
    }
    
    /**
     * Da la orden al modelo de reiniciar las pilas, los arrayList y la matriz para 
     * una nueva partida.
     */
    public void reiniciar(){
        tablero.reiniciarPartida();
        cargarPartida();
        jpReproducirPartida.actualizarPanel();
    }
    
   
    /**
     * Inicia el temporizador para iniciar la reproducción automatica.
     */ 
    public void iniciarTemporizador(){
        temporizador.start();
    }
    
    /**
     * Detiene la reproducción automatica.
     */
    public void pararTemporizador(){
        temporizador.stop();
    }
    
    public void errorEncontrado(char tipoPieza){
        jpReproducirPartida.errorDeMovimiento(tipoPieza);
    }
    
    /**
     * Envia a la vista la informacion de cada movimiento(enroque, captura, jaque o jaque mate).
     * @param informacion la informacion que se desea mostrar.
     */
    public void obtenerInformacion(String informacion){
        jpReproducirPartida.actualizarInformacion(informacion);
    }

    /**
     * Modifica el contenido del atributo <code>archivo</code>.
     * @param archivo el nuevo valor de archivo.
     */
    public void setArchivo(File archivo){
        this.archivo = archivo;
    }
}
