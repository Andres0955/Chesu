package chesu.vista;

import chesu.controlador.*;
import chesu.modelo.Piezas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Cesar Acosta
 */
public class JpReproducirPartida extends javax.swing.JPanel {
    private ControlReproductor controlReproductor;
    private ControlPrincipal controlPrincipal;
    private static final int TAMANO_CASILLA = 75; 
    private static final int MARGENX = 120;
    private static final int MARGENY = 50;
    private static final int DIMENSION_TABLERO = 8; 
    private Piezas[][] posiciones;
    private Map<String, Point> coordenadas;
    private Color[] colorTablero;
    private ImageIcon fondo;
    
    public JpReproducirPartida(ControlPrincipal controlPrincipal) {
        
        this.controlPrincipal = controlPrincipal;
        this.posiciones = new Piezas[8][8];
        this.coordenadas = new HashMap<>();
        this.colorTablero = new Color[2];
        this.fondo = new ImageIcon(getClass().getResource("/recursos/imagenes/fondos/fondo.png"));
        
        initComponents();
        inicializarMapaPosiciones();
        elegirColores(0);
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
     * Calcula las posiciones en pixeles donde se ubicaran las piezas y las guarda en un hashMap.
     */
    private void inicializarMapaPosiciones() {
        for (int fila = 0; fila < DIMENSION_TABLERO; fila++) {
            for (int columna = 0; columna < DIMENSION_TABLERO; columna++) {
                
                int x = MARGENX + columna * TAMANO_CASILLA + 5;
                int y = MARGENY + fila * TAMANO_CASILLA + 10;

                
                String id = fila +""+ columna;
                coordenadas.put(id, new Point(x, y));
            }
        }
    }
    
     public void mostrarErrorMovimientos(){
        JOptionPane.showMessageDialog(this, "An error was found in the file, please check the file.", 
                "Error in file", JOptionPane.ERROR_MESSAGE);
        btnReproducir.setEnabled(false);
        btnAvanzar.setEnabled(false);
        btnRetroceder.setEnabled(false);
        btnReiniciar.setEnabled(false);
    }
    
    public void mostrarErrorArchivoVacio(){
        JOptionPane.showMessageDialog(this, "No more moves were found, please check the file.", 
                "Error in file", JOptionPane.ERROR_MESSAGE);
        btnReproducir.setEnabled(false);
        btnAvanzar.setEnabled(false);
        btnRetroceder.setEnabled(false);
        btnReiniciar.setEnabled(false);
    }

    /**
     * Calcula la posicion de cada recuadro del tablero y lo dibuja en el panel.
     * @param g, el contexto grafico usado para pintar.
     */
    public void dibujarTablero(Graphics g) {
        char[] letras = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        for (int fila = 0; fila < DIMENSION_TABLERO; fila++) {
            for (int col = 0; col < DIMENSION_TABLERO; col++) {
                
                int x = MARGENX + col * TAMANO_CASILLA;
                int y = MARGENY + fila * TAMANO_CASILLA;

                if ((fila + col) % 2 == 0) {
                    g.setColor(colorTablero[0]);
                } else {
                    g.setColor(colorTablero[1]);
                }
                g.fillRect(x, y, TAMANO_CASILLA, TAMANO_CASILLA);
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
        
        for (int fila = 0; fila < DIMENSION_TABLERO; fila++) {
            for (int columna = 0; columna < DIMENSION_TABLERO; columna++) {
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
     * Actualiza el panel cuando es llamado.
     */
    public void actualizarPanel(){
        repaint();
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
     * Actualiza la informacion de cada movimiento importante en la partida.
     * @param informacion dato que se mostrara en la interfaz.
     */
    public void actualizarInformacion(String informacion){
        int resultado = -1000;
        txtInformacion.setText(informacion);
        if(informacion.equals("White Wins!") || informacion.equals("Black wins") || informacion.equals("Mutual Agreement")){
           resultado = JOptionPane.showConfirmDialog(this, "Desea reproducir nuevamente la partida?",
                   "Fin de la partida", JOptionPane.YES_NO_OPTION);
        }
        
        if(resultado == JOptionPane.YES_OPTION){
            controlReproductor.reiniciar();
        }else if(resultado == JOptionPane.NO_OPTION){
            controlPrincipal.cambiarPanel("inicio");
            controlReproductor.reiniciar();
            controlReproductor.setArchivo(null);
        }
    }
     
    /**
     * Establece o modifica el atributo <code>posiciones</code>.
     * @param posiciones el nuevo valor para el atributo <code>posiciones</code>.
     */
    public void setPosiciones(Piezas[][] posiciones){
        this.posiciones = posiciones;
        
    }
    
    public void setControlReproductor(ControlReproductor controlReproductor){
        this.controlReproductor = controlReproductor;
    }
    
    public void errorDeMovimiento(char tipoPieza){
        String pieza = switch(tipoPieza){
            case 'K' -> "Rey";
            case 'P' -> "Peon";
            case 'Q' -> "Reina";
            case 'R' -> "Torre";
            case 'B' -> "Alfil";
            case 'N' -> "Caballo";
            default -> "Pieza no identificada";
        };
        
        JOptionPane.showMessageDialog(this, "Movimiento inválido para la pieza " + pieza,
                "Movimiento Invalido", JOptionPane.ERROR_MESSAGE);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnReproducir = new javax.swing.JToggleButton();
        btnAvanzar = new javax.swing.JButton();
        btnRetroceder = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        btnTema = new javax.swing.JToggleButton();
        txtInformacion = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnReproducir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnReproducir.png"))); // NOI18N
        btnReproducir.setBorderPainted(false);
        btnReproducir.setContentAreaFilled(false);
        btnReproducir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReproducir.setFocusPainted(false);
        btnReproducir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnPausar.png"))); // NOI18N
        btnReproducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReproducirActionPerformed(evt);
            }
        });
        jPanel1.add(btnReproducir, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 50, 50));

        btnAvanzar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnAvanzar.png"))); // NOI18N
        btnAvanzar.setBorderPainted(false);
        btnAvanzar.setContentAreaFilled(false);
        btnAvanzar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAvanzar.setFocusPainted(false);
        btnAvanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvanzarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAvanzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 50, 50));

        btnRetroceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnRetroceder.png"))); // NOI18N
        btnRetroceder.setBorderPainted(false);
        btnRetroceder.setContentAreaFilled(false);
        btnRetroceder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRetroceder.setFocusPainted(false);
        btnRetroceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetrocederActionPerformed(evt);
            }
        });
        jPanel1.add(btnRetroceder, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 50, 50));

        btnReiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnReiniciar.png"))); // NOI18N
        btnReiniciar.setBorderPainted(false);
        btnReiniciar.setContentAreaFilled(false);
        btnReiniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReiniciar.setFocusPainted(false);
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });
        jPanel1.add(btnReiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 50, 50));

        btnTema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnModoOscuro.png"))); // NOI18N
        btnTema.setSelected(true);
        btnTema.setBorderPainted(false);
        btnTema.setContentAreaFilled(false);
        btnTema.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTema.setFocusPainted(false);
        btnTema.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnModoClaro.png"))); // NOI18N
        btnTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTemaActionPerformed(evt);
            }
        });
        jPanel1.add(btnTema, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 40, 40));

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
        jPanel1.add(txtInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 140, 150));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnAtras.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 150, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 806, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAvanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvanzarActionPerformed
        controlReproductor.avanzar();
        actualizarPanel();
    }//GEN-LAST:event_btnAvanzarActionPerformed

    private void btnRetrocederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetrocederActionPerformed
        controlReproductor.retroceder();
        actualizarPanel();
    }//GEN-LAST:event_btnRetrocederActionPerformed

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        controlReproductor.reiniciar();
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemaActionPerformed
        if(btnTema.isSelected()){
            elegirColores(0);
        }else{
            elegirColores(1);
        }

        actualizarPanel();

    }//GEN-LAST:event_btnTemaActionPerformed

    private void txtInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInformacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInformacionActionPerformed

    private void btnReproducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReproducirActionPerformed
        if(btnReproducir.isSelected()){
            controlReproductor.iniciarTemporizador();
        }else{
            controlReproductor.pararTemporizador();
        }
    }//GEN-LAST:event_btnReproducirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        controlReproductor.reiniciar();
        controlPrincipal.cambiarPanel("cargarPartida");
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvanzar;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JToggleButton btnReproducir;
    private javax.swing.JButton btnRetroceder;
    private javax.swing.JToggleButton btnTema;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtInformacion;
    // End of variables declaration//GEN-END:variables
}
