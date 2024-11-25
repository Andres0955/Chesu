package chesu.modelo;

/**
 * 
 * @author Cesar Acosta
 */
public class Movimiento {
    private char tipo;
    private int[] casillaDestino;
    private int[] casillaOrigen;
    private boolean captura;
    private boolean enroque;
    private boolean esJaqueMate;
    private String ganador;
    private String promocion;
    private boolean enroqueCorto;
    private boolean enroqueLargo;
    
    /**
     * Constructor de la clase Movimineto.
     * @param tipo el tipo de la pieza que se desea mover.
     * @param casillaDestino arreglo con las ubicaciones de destino.
     * @param captura si el movimiento es una captura.
     */
    public Movimiento(char tipo, int[] casillaDestino, boolean captura) {
        this.tipo = tipo;
        this.casillaDestino = casillaDestino;
        this.captura = captura;
    }
    
    public Movimiento(char tipo, int[] casillaOrigen, int[] casillaDestino, boolean captura, boolean jaqueMate, 
            String ganador, String promocion, boolean enroqueCorto, boolean enroqueLargo){
        this.tipo = tipo;
        this.casillaOrigen = casillaOrigen;
        this.casillaDestino = casillaDestino;
        this.captura = captura;
        this.esJaqueMate = jaqueMate;
        this.ganador = ganador;
        this.promocion = promocion;
        this.enroqueCorto = enroqueCorto;
        this.enroqueLargo = enroqueLargo;
    }
    
    /**
     * Obtiene el tipo de la pieza.
     * @return el tipo de la pieza.
     */
    public char getTipo() {
        return tipo;
    }
    
    /**
     * Obtiene la fila destino de la piza.
     * @return la fila destino.
     */
    public int getFila() {
        return casillaDestino[0];
    }
    
    /**
     * Obtiene la columna destino de la pieza.
     * @return la columna destino.
     */
    public int getColumna() {
        return casillaDestino[1];
    }
    
    public int getColumnaOrigen(){
        return casillaOrigen[1];
    }
    
    /**
     * Obtiene el arreglo con las ubicaciones destino.
     * @return las ubicaciones destino.
     */
    public int[] getCasillaDestino(){
        return casillaDestino;
    }

    /**
     * Comprueba si el movimiento es una captura.
     * @return true si es una captura, false en caso contrario.
     */
    public boolean esCaptura() {
        return captura;
    }
    
    /**
     * Comprueba si el movimiento es un enroque.
     * @return true si es un enroque, false si no lo es.
     */
    public boolean esEnroque(){
        return enroque;
    }
    
    /**
 * Devuelve si se ha realizado un enroque corto en el movimiento.
 * 
 * @return {@code true} si el enroque corto se ha realizado, {@code false} en caso contrario.
 */
    public boolean getEnroqueCorto(){
        return enroqueCorto;
    }
    
    /**
 * Devuelve si se ha realizado un enroque largo en el movimiento.
 * 
 * @return {@code true} si el enroque largo se ha realizado, {@code false} en caso contrario.
 */
    public boolean getEnroqueLargo(){
        return enroqueLargo;
    }
    
    /**
 * Verifica si el movimiento ha resultado en un jaque mate.
 * 
 * @return {@code true} si el movimiento ha resultado en un jaque mate, {@code false} en caso contrario.
 */
    public boolean esJaqueMate(){
        return esJaqueMate;
    }
    
    /**
 * Devuelve el nombre del ganador si el juego ha terminado.
 * 
 * @return El nombre del ganador si hay uno, o {@code null} si el juego no ha terminado.
 */
    public String getGanador(){
        return ganador;
    }
    
    /**
 * Devuelve la pieza a la que se ha promocionado en caso de que haya habido una promoción de peón.
 * 
 * @return La pieza a la que se ha promocionado, o {@code null} si no hubo promoción.
 */
    public String getPromocion(){
        return promocion;
    }
    
    /**
 * Establece la pieza a la que se ha promocionado un peón.
 * 
 * @param nuevaPromocion La nueva pieza a la que se ha promocionado.
 */
    public void setPromocion(String nuevaPromocion){
        this.promocion = nuevaPromocion;
    }
}
