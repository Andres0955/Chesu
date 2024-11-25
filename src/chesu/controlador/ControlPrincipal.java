package chesu.controlador;


import chesu.modelo.EscribirArchivo;
import chesu.vista.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Cesar Acosta
 */
public class ControlPrincipal {
    private ControlPrincipal instancia;
    private JpInicio jpInicio;
    private JpCargarPartida jpCargarPartida;
    private JpReproducirPartida jpReproducirPartida;
    private JPanel panelActual;
    private JFrame frame;
    private ControlReproductor controlReproductor;
    private ControlJugar controlJugar;
    private JpInformacionPartida jpInformacionPartida;
    private JpTablero jpTablero;
    private EscribirArchivo escribirArchivo;
    
    public ControlPrincipal(JFrame frame){
        this.frame = frame;
        this.instancia = this;
        this.jpInicio = new JpInicio(instancia);
        cambiarPanel("inicio");
    }
    
    public void cambiarPanel(String nuevoPanel){
        if(panelActual != null){
            frame.remove(panelActual);
        }
        
        switch(nuevoPanel){
            case "inicio":
                panelActual = jpInicio;
                break;
            case "cargarPartida":
                panelActual = jpCargarPartida;
                break;
            case "reproducirPartida":
                panelActual = jpReproducirPartida;
                break;
            case "infoPartida":
                panelActual = jpInformacionPartida;
                break;
            case "tablero":
                panelActual = jpTablero;
                break;
        }
        
        frame.add(panelActual);
        frame.revalidate();
        frame.repaint();
    }
    public void eleccionDeModo(int eleccion){
        if(eleccion == 0){
            this.jpReproducirPartida = new JpReproducirPartida(instancia);
            this.controlReproductor = new ControlReproductor(instancia, jpReproducirPartida);
            jpReproducirPartida.setControlReproductor(controlReproductor);
            this.jpCargarPartida = new JpCargarPartida(instancia, controlReproductor);
        }else{
            this.escribirArchivo = new EscribirArchivo();
            this.jpInformacionPartida = new JpInformacionPartida(instancia);
            this.jpTablero = new JpTablero(this);
            this.controlJugar = new ControlJugar(jpInformacionPartida, jpTablero, escribirArchivo);
            jpInformacionPartida.setControlJugar(controlJugar);
            jpTablero.setControlJugar(controlJugar);
            
        }
    }
}
