package chesu.vista;

import chesu.controlador.ControlJugar;
import chesu.controlador.ControlPrincipal;
import chesu.modelo.Casilla;
import chesu.modelo.Piezas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Cesar Acosta
 */
public class JpTablero extends javax.swing.JPanel{
    private static final int TAMANO_CASILLA = 75; 
    private static final int MARGENX = 120;
    private static final int MARGENY = 50;
    private static final int DIMENSION_TABLERO = 8; 
    private ControlPrincipal controlPrincipal;
    private ControlJugar controlJugar;
    private Color[] colorTablero;
    private ImageIcon fondo;
    private Map<String, Point> coordenadas;
    private Casilla[][] casillas;
    private Piezas[][] posiciones;
    private boolean tableroActivado;
    
    public JpTablero(ControlPrincipal controlPrincipal){
        this.controlPrincipal = controlPrincipal;
        this.colorTablero = new Color[2];
        this.fondo = new ImageIcon(getClass().getResource("/recursos/imagenes/fondos/fondo.png"));
        this.coordenadas = new HashMap<>();
        this.casillas = new Casilla[8][8];
        this.posiciones = new Piezas[8][8];
        this.tableroActivado = true;
        
        initComponents();
        elegirColores(0);
        rastrearUbicacion();
    }
    
    /**
     * Dibuja el fondo del panel junto con el tablero y las piezas.
     * @param g el contexto grafico el cual es usado para pintar.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(fondo != null){
            g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        
        dibujarTablero(g);
        dibujarPiezas(g);
        
    }

    /**
     * Calcula la posicion de cada recuadro del tablero y lo dibuja en el panel.
     * @param g, el contexto grafico usado para pintar.
     */
    public void dibujarTablero(Graphics g) {
        char[] letras = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        for (int fila = 0; fila < DIMENSION_TABLERO; fila++) {
            for (int col = 0; col < DIMENSION_TABLERO; col++){
                
                Casilla casilla = casillas[fila][col];
                g.drawImage(casilla.getImagen(), casilla.getPosX(), casilla.getPosY(), casilla.getAncho(), casilla.getAlto(), this);
            }
        }
        
        //Dibuja los numeros en el lado derecho e izquierdo del tablero.
        g.setColor(Color.white);
        g.setFont(new Font("Alergian", Font.ITALIC, 15));
        for (int fila = 0; fila < DIMENSION_TABLERO; fila++) {
            int y = MARGENY + fila * TAMANO_CASILLA + TAMANO_CASILLA / 2;
            g.drawString(String.valueOf(DIMENSION_TABLERO - fila), MARGENX / 2 + 35, y);
            g.drawString(String.valueOf(DIMENSION_TABLERO - fila), MARGENX + DIMENSION_TABLERO * TAMANO_CASILLA + 10, y);
        }
        
        // Dibuja las letras en la parte superior e inferior del tablero.
        for (int columna = 0; columna < DIMENSION_TABLERO; columna++) {
            char letra = letras[columna];
            int x = MARGENX + columna * TAMANO_CASILLA + TAMANO_CASILLA / 2;
            g.drawString(String.valueOf(letra), x, MARGENY / 2);
            g.drawString(String.valueOf(letra), x, MARGENY + DIMENSION_TABLERO * TAMANO_CASILLA + 20);
        }

    }
    
    /**
     * Dibuja cada una de las piezas del tablero de ajedrez usando el hashMap de las posiciones en pixeles.
     * @param g, el contexto grafico usado para pintar.
     */
    private void dibujarPiezas(Graphics g){
        if (posiciones == null) {
        System.err.println("El arreglo de posiciones no ha sido inicializado.");
        return;
        }
        
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Piezas pieza = posiciones[fila][columna];
                if (pieza != null) {
                     Point coordenadaActual = coordenadas.get(fila+""+columna);
                     int posX = coordenadaActual.x;
                     int posY = coordenadaActual.y;

                    g.drawImage(pieza.getImagen(), posX, posY, pieza.getAncho(), pieza.getAlto(), null);
                }
            }
        }
    }
    
    /**
     * Elije los colores para el tablero.
     * @param eleccion determina cuales seran los colores para el tablero.
     */
    private void elegirColores(int eleccion){
        if(eleccion == 0){
            colorTablero[0] = Color.white;
            colorTablero[1] = Color.gray;
        }else{
            colorTablero[0] = new Color(234, 195, 120);
            colorTablero[1] = new Color(55, 32, 24);
        }
    }
    
    /**
 * Método que rastrea la ubicación del clic del ratón en el tablero y 
 * responde en consecuencia. Si el tablero está activado, detecta la 
 * posición del clic y realiza la acción correspondiente a la casilla seleccionada.
 */
    private void rastrearUbicacion(){
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(!tableroActivado){
                    return;
                }
                int x = e.getX();
                int y = e.getY();
                
                controlJugar.casillaSeleccionada(x, y);
                mostrarTurno(controlJugar.obtenerTurno());
                String mensajeError = controlJugar.obtenerMensajeError();
                
                if(mensajeError != null){
                    mostrarMensajeError(mensajeError);
                }
                if(posiciones != null && controlJugar.esFinJuego()){
                    finJuegoDetectado();
                    tableroActivado = false;
                }                
            }
        });
    }
    
    /**
 * Verifica si hay una promoción disponible en el tablero. Si la hay y el tablero está activado,
 * muestra un diálogo para que el usuario elija el tipo de pieza a la que desea promover.
 */
    public void verificarPromocion(){
        if(controlJugar.hayPromocion() != null && tableroActivado){
            char tipoNuevaPieza = mostrarEleccionPieza();
            controlJugar.actualizarTipoNuevaPieza(tipoNuevaPieza);
            controlJugar.modificarPieza(tipoNuevaPieza);
        }
    }
    
    /**
 * Muestra un mensaje indicando de quién es el turno actual.
 * 
 * @param turno {@code true} si es el turno de las piezas blancas, {@code false} si es el turno de las piezas negras.
 */
    private void mostrarTurno(boolean turno){
        String mensaje = turno ? "It's White's Turn" : "It's Black's Turn";
        txtInformacion.setText(mensaje);
    }
    
    /**
 * Muestra un mensaje de error en un cuadro de diálogo.
 * 
 * @param mensaje El mensaje de error a mostrar.
 */
    private void mostrarMensajeError(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
 * Muestra un mensaje indicando el final del juego y el ganador.
 */
    private void finJuegoDetectado(){
        String ganador = controlJugar.getGanador();
        JOptionPane.showMessageDialog(this, ganador + " has won the game.", "End of Game", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
 * Muestra un cuadro de diálogo que permite al jugador elegir el tipo de pieza
 * al que desea promover un peón. Devuelve el tipo de pieza elegido.
 * 
 * @return El tipo de pieza elegida: 'Q' (Reina), 'R' (Torre), 'B' (Alfil), 'N' (Caballo).
 */
    private char mostrarEleccionPieza(){
        ImageIcon[] botones;
        ImageIcon reinaBlanca = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/reinaBlanco.png"));
        ImageIcon torreBlanca = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/torreBlanco.png"));
        ImageIcon alfilBlanca = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/alfilBlanco.png"));
        ImageIcon caballoBlanca = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/caballoBlanco.png"));
        ImageIcon reinaNegra = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/reinaNegro.png"));
        ImageIcon torreNegra = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/torreNegro.png"));
        ImageIcon alfilNegra = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/alfilNegro.png"));
        ImageIcon caballoNegra = new ImageIcon(getClass().getResource("/recursos/imagenes/botones/caballoNegro.png"));


        if(!controlJugar.verificarTurno()){
            botones = new ImageIcon[] {reinaBlanca, torreBlanca, alfilBlanca, caballoBlanca};
        }else{
            botones = new ImageIcon[] {reinaNegra, torreNegra, alfilNegra, caballoNegra};
        }

        int resultado = JOptionPane.showOptionDialog(this, "Choose your new piece", "Choose", 0, JOptionPane.INFORMATION_MESSAGE, null, botones, 0);

        char tipoPieza = switch(resultado){
            case 0 -> 'Q';
            case 1 -> 'R';
            case 2 -> 'B';
            case 3 -> 'N';
            default -> '\0'; 
        };
        return tipoPieza;
    }

    /**
 * Actualiza el panel repintando los componentes.
 */
    public void actualizarPanel(){
        repaint();
    }
    
    /**
 * Establece las casillas del tablero.
 * 
 * @param casillas Matriz bidimensional de objetos {@code Casilla} que representan las casillas del tablero.
 */
    public void setCasillas(Casilla[][] casillas){
        this.casillas = casillas;
    }
    
    /**
 * Establece las posiciones de las piezas en el tablero.
 * 
 * @param posiciones Matriz bidimensional de objetos {@code Piezas} que representan la posición de cada pieza en el tablero.
 */
    public void setPosiciones(Piezas[][] posiciones){
        this.posiciones = posiciones;
    }
    
    /**
 * Establece las coordenadas de las casillas en el tablero.
 * 
 * @param coordenadas Mapa que asocia una cadena (identificador de casilla) con un objeto {@code Point} 
 *                    que representa la posición de la casilla en el panel.
 */
    public void setCoordenadas(Map<String, Point> coordenadas){
        this.coordenadas = coordenadas;
    }
    
    /**
 * Establece el controlador del juego.
 * 
 * @param controlJugar Objeto {@code ControlJugar} que gestiona la lógica del juego.
 */
    public void setControlJugar(ControlJugar controlJugar){
        this.controlJugar = controlJugar;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtInformacion = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtInformacion.setBackground(new java.awt.Color(0, 0, 0));
        txtInformacion.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        txtInformacion.setForeground(new java.awt.Color(255, 255, 255));
        txtInformacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtInformacion.setText("Game Details");
        txtInformacion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtInformacion.setEnabled(false);
        txtInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInformacionActionPerformed(evt);
            }
        });
        jPanel1.add(txtInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 140, 100));

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnAtras.png"))); // NOI18N
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.setFocusPainted(false);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 150, 50));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, 190, 700));
    }// </editor-fold>//GEN-END:initComponents

    private void txtInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInformacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInformacionActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        int resultado = JOptionPane.showConfirmDialog(this, "Do you want to save the game?",
                "Save game?", JOptionPane.YES_NO_CANCEL_OPTION);
        
        switch(resultado){
            case 0:
                JOptionPane.showMessageDialog(this,"Game save successfully.", "File saved", JOptionPane.INFORMATION_MESSAGE);
                controlPrincipal.cambiarPanel("inicio");
                break;
            case 1:
                if(controlJugar.borrarArchivo()){
                    controlPrincipal.cambiarPanel("inicio");
                }else{
                    JOptionPane.showMessageDialog(this,"An error occurred while deleting the file.",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 2:
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtInformacion;
    // End of variables declaration//GEN-END:variables
}
