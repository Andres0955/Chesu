
package chesu.modelo;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Cesar Acosta
 */
public class Casilla{
    private int ancho, alto;
     private int posX, posY;
    private int fila, columna;
    private Image imagen;
    private boolean ocupado;
    
    public Casilla(int posX, int posY, int fila, int columna, String ruta, boolean ocupado) {
        this.ancho = 75;
        this.alto = 75;
        this.posX = posX;
        this.posY = posY;
        this.fila = fila;
        this.columna = columna;
        this.imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
        this.ocupado = ocupado;
    }
    
    /**
     * Obtiene el ancho de la casilla.
     * @return el ancho de la casilla.
     */
    public int getAncho(){
        return ancho;
    }
    
    /**
     * obtiene el alto de la casilla.
     * @return el alto de la casilla.
     */
    public int getAlto(){
        return alto;
    }
    
    /**
     * obtiene la fila en la que se encuentra la casilla.
     * @return la fila actual de la casilla.
     */
    public int getFila(){
        return fila;
    }
    
    /**
     * Obtiene la columna en la que se encuentra la casilla.
     * @return la columna actual de la casilla.
     */
    public int getColumna(){
        return columna;
    }
    
    /**
     * Obtiene la imagen que tiene selecionada de la pieza.
     * @return la imagen de la pieza.
     */
    public Image getImagen(){
        return imagen;
    }
    
    /**
     * Obtiene la posicion X en pixeles de la casilla.
     * @return la posicion de la casilla.
     */
    public int getPosX(){
        return posX;
    }
    
    /**
     * Obtiene la posicion Y en pixeles de la casilla.
     * @return la posicion de la casilla.
     */
    public int getPosY(){
        return posY;
    }
    
    public boolean getOcupado(){
        return ocupado;
    }
    
    /**
     * Cambia el estado de la casilla.
     * @param estado estado al cual es quiere cambiar.
     */
    public void setOcupado(boolean estado){
        this.ocupado = estado;
    }
    
    /**
     * Modifica o actualiza la imagen de la casilla.
     * @param ruta ruta en la que se encuentra la imagen.
     */
    public void setImagen(String ruta){
        this.imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
    }

   
    
}
