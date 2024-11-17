package chesu.controlador;

import chesu.vista.JpInicio;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Cesar Acosta
 */
public class ControlPrincipal {
    private ControlPrincipal instancia;
    private JpInicio jpInicio;
    private JPanel panelActual;
    private JFrame frame;
    
    public ControlPrincipal(JFrame frame){
        this.frame = frame;
        this.instancia = this;
        this.jpInicio = new JpInicio(instancia);
        cambiarPanel("inicio");
    }
    
    public void cambiarPanel(String nuevoPanel){
        switch(nuevoPanel){
            case "inicio":
                panelActual = jpInicio;
                break;
            
        }
        
        frame.add(panelActual);
        
    }
}
