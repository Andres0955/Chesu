package chesu.controlador;

import chesu.modelo.EscribirArchivo;
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
        escribirArchivo.crearArchivo(informacion);
        confirmarCreacionArchivo(escribirArchivo.getArchivoCreado());
    }
    
    public void confirmarCreacionArchivo(boolean creado){
        jpInformacionPartida.creacionArchivoExitosa(creado);
    }
    
    public void casillaSeleccionada(int x, int y){
        tablero2.casillaSeleccionada(x, y);
        jpTablero.setCasillas(tablero2.getCasillas());
        jpTablero.setPosiciones(tablero2.getPosiciones());
        jpTablero.actualizarPanel();
    }
    
    public void enviarCasillaSeleccionada(int fila, int columna){
        jpTablero.setPosiciones(tablero2.posiblesCasillasDestino(fila, columna));
        
    }
    
}
