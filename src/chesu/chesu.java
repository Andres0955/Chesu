package chesu;

import chesu.controlador.ControlPrincipal;
import javax.swing.JFrame;

/**
 *
 * @author Cesar Acosta
 */
public class chesu {

    public static void main(String[] args) {
        JFrame frame = new JFrame("CHESU");
        ControlPrincipal control = new ControlPrincipal(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
    
}
