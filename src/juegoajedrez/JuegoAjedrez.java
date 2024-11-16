package juegoajedrez;

import juegoajedrez.controlador.Control;
import javax.swing.JFrame;

/**
 *
 * @author Cesar Acosta
 */
public class JuegoAjedrez {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ajedrez");
        Control control = new Control(frame);
        frame.setVisible(true);
        frame.pack();
    }
    
}
