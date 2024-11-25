package chesu.controlador;

import chesu.modelo.excepciones.ArchivoExistenteException;
import chesu.modelo.EscribirArchivo;
import chesu.modelo.Piezas;
import chesu.modelo.Tablero2;
import chesu.vista.JpInformacionPartida;
import chesu.vista.JpTablero;

/**
 *
 * @author Cesar Acosta
 */
public class ControlJugar {
    private JpInformacionPartida jpInformacionPartida;
    private JpTablero jpTablero;
    private EscribirArchivo escribirArchivo;
    private Tablero2 tablero2;
    
    public ControlJugar(JpInformacionPartida jpInformacionPartida, JpTablero jpTablero, EscribirArchivo escribirArchivo){
        this.jpInformacionPartida = jpInformacionPartida;
        this.jpTablero = jpTablero;
        this.escribirArchivo = escribirArchivo;
        this.tablero2 = new Tablero2(escribirArchivo);
        
    }    
    
    public void cargarPartida(){
        jpTablero.setCasillas(tablero2.crearCasillas());
        jpTablero.setCoordenadas(tablero2.getCoordenadas());
        jpTablero.setPosiciones(tablero2.getPosiciones());
    }
    
    public void obtenerInformacionDelJuego(String[] informacion){
        try {
            escribirArchivo.crearArchivo(informacion);
            confirmarCreacionArchivo(escribirArchivo.getArchivo());
        } catch (ArchivoExistenteException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public void confirmarCreacionArchivo(boolean creado){
        jpInformacionPartida.creacionArchivoExitosa(creado);
    }
    
    public void casillaSeleccionada(int x, int y){
        if(tablero2.casillaSeleccionada(x, y)){
            jpTablero.verificarPromocion();
            tablero2.escribirMovimiento();
        }
        jpTablero.setCasillas(tablero2.getCasillas());
        jpTablero.setPosiciones(tablero2.getPosiciones());
        jpTablero.actualizarPanel();
    }
    
    public void enviarCasillaSeleccionada(int fila, int columna){
        jpTablero.setPosiciones(tablero2.posiblesCasillasDestino(fila, columna));
        
    }
    
    public boolean obtenerTurno(){
        return tablero2.getEsTurnoBlancas();
    }
    
    public String obtenerMensajeError(){
        String mensajeError = tablero2.getMensajeError();
        tablero2.restaurarMensajeError();
        return mensajeError;
    }
    
    public boolean borrarArchivo(){
        return escribirArchivo.borrarArchivo();
    }
    
    public boolean esFinJuego(){
        return tablero2.verificarFinJuego();
    }
    
    public void setNombresJugadores(String[] jugadores){
        tablero2.setNombreJugadores(jugadores);
    }
    
    public String getGanador(){
        return tablero2.getGanador();
    }
    
    public Piezas hayPromocion(){
        return tablero2.getEsPromocion();
    }

    public void modificarPieza(char tipoPieza) {
        tablero2.cambiarPieza(tipoPieza);
        jpTablero.actualizarPanel();
    }
    
    public void actualizarTipoNuevaPieza(char tipoPieza){
        tablero2.setTipoNuevaPieza(tipoPieza);
    }
    
    public boolean verificarTurno(){
        return tablero2.getEsTurnoBlancas();
    }
    
}
