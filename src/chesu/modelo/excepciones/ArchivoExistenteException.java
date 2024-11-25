package chesu.modelo.excepciones;

/**
 *
 * @author Cesar Acosta
 */
public class ArchivoExistenteException extends Exception{
    public ArchivoExistenteException(){
        super("Ya existe un archivo con el mismo nombre.");
    }
    
}
