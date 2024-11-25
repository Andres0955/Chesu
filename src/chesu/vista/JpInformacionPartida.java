package chesu.vista;

import chesu.controlador.*;
import java.awt.Color;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *
 * @author Cesar Acosta
 */
public class JpInformacionPartida extends javax.swing.JPanel {
    private ControlPrincipal controlPrincipal;
    private ControlJugar controlJugar;
    private ImageIcon fondo;
    
    public JpInformacionPartida(ControlPrincipal controlPrincipal) {
        this.controlPrincipal = controlPrincipal;
        this.fondo = new ImageIcon(getClass().getResource("/recursos/imagenes/fondos/fondo.png"));
        initComponents();
        this.jpFondo.setBackground(new Color(0,0,0,150));
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), null);
    }
    
    private String[] llenarInformacion(){
        String[] informacionDelJuego = new String[7];
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        String nombreArchivo = "./src/recursos/partidas/" + txtNombreArchivo.getText() + ".txt";
        
        informacionDelJuego[0] = txtEvento.getText();
        informacionDelJuego[1] = txtLugar.getText();
        informacionDelJuego[2] = fecha.format(txtFecha.getDate());
        informacionDelJuego[3] = txtRound.getText();
        informacionDelJuego[4] = txtJugadorBlanco.getText();
        informacionDelJuego[5] = txtJugadorNegro.getText();
        informacionDelJuego[6] = nombreArchivo;
        
        String[] jugadores = {txtJugadorBlanco.getText(), txtJugadorNegro.getText()};
        controlJugar.setNombresJugadores(jugadores);
        
        return informacionDelJuego;
    }
    
    private boolean verificarInformacionCompleta() {
        if(txtEvento.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "The event field is empty.", "ERROR:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(txtLugar.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "The site field is empty.", "ERROR:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(txtFecha.getDate() == null){
            JOptionPane.showMessageDialog(this, "The date is not selected.", "ERROR:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(txtRound.getText().isEmpty()){
           JOptionPane.showMessageDialog(this, "The round field is empty.", "ERROR:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(txtJugadorBlanco.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "The white player name field is empty.", "ERROR:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(txtJugadorNegro.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "The black player name field is empty.", "ERROR:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(txtNombreArchivo.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "The save as field is empty.", "ERROR:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public void creacionArchivoExitosa(boolean creado){
        if(creado){
            JOptionPane.showMessageDialog(this, "File created successfully.", "File created:",
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Error creating file, it already exists.", "File created:", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void setControlJugar(ControlJugar controlJugar){
        this.controlJugar = controlJugar;
    }
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpFondo = new javax.swing.JPanel();
        lblEvento = new javax.swing.JLabel();
        txtEvento = new javax.swing.JTextField();
        lblLugar = new javax.swing.JLabel();
        txtLugar = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        lblRound = new javax.swing.JLabel();
        txtRound = new javax.swing.JTextField();
        lblJugadorBlanco = new javax.swing.JLabel();
        txtJugadorBlanco = new javax.swing.JTextField();
        lblJugadorNegro = new javax.swing.JLabel();
        btnContinuar = new javax.swing.JButton();
        txtJugadorNegro = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        lblNombreArchivo = new javax.swing.JLabel();
        txtNombreArchivo = new javax.swing.JTextField();
        txtFecha = new com.toedter.calendar.JDateChooser();

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEvento.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        lblEvento.setForeground(new java.awt.Color(250, 250, 250));
        lblEvento.setText("Event:");

        lblLugar.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        lblLugar.setForeground(new java.awt.Color(250, 250, 250));
        lblLugar.setText("Site:");

        lblFecha.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(250, 250, 250));
        lblFecha.setText("Date:");

        lblRound.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        lblRound.setForeground(new java.awt.Color(250, 250, 250));
        lblRound.setText("Round:");

        txtRound.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRoundKeyTyped(evt);
            }
        });

        lblJugadorBlanco.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        lblJugadorBlanco.setForeground(new java.awt.Color(250, 250, 250));
        lblJugadorBlanco.setText("White player name:");

        txtJugadorBlanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJugadorBlancoActionPerformed(evt);
            }
        });

        lblJugadorNegro.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        lblJugadorNegro.setForeground(new java.awt.Color(250, 250, 250));
        lblJugadorNegro.setText("Black player name:");

        btnContinuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnContinue.png"))); // NOI18N
        btnContinuar.setBorderPainted(false);
        btnContinuar.setContentAreaFilled(false);
        btnContinuar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnContinuar.setFocusPainted(false);
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });
        btnContinuar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnContinuarKeyPressed(evt);
            }
        });

        txtJugadorNegro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJugadorNegroActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnAtras.png"))); // NOI18N
        btnCancelar.setBorderPainted(false);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblNombreArchivo.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        lblNombreArchivo.setForeground(new java.awt.Color(250, 250, 250));
        lblNombreArchivo.setText("Save as:");

        javax.swing.GroupLayout jpFondoLayout = new javax.swing.GroupLayout(jpFondo);
        jpFondo.setLayout(jpFondoLayout);
        jpFondoLayout.setHorizontalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoLayout.createSequentialGroup()
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFondoLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpFondoLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLugar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addComponent(txtLugar)
                            .addComponent(txtEvento)))
                    .addGroup(jpFondoLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpFondoLayout.createSequentialGroup()
                                .addComponent(lblNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreArchivo))
                            .addGroup(jpFondoLayout.createSequentialGroup()
                                .addComponent(lblJugadorNegro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtJugadorNegro))
                            .addGroup(jpFondoLayout.createSequentialGroup()
                                .addComponent(lblJugadorBlanco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtJugadorBlanco, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpFondoLayout.createSequentialGroup()
                                .addComponent(lblRound)
                                .addGap(18, 18, 18)
                                .addComponent(txtRound)))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jpFondoLayout.setVerticalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRound, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRound, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJugadorBlanco, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJugadorBlanco, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJugadorNegro, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJugadorNegro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        add(jpFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, 520));
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
       
    }//GEN-LAST:event_formKeyPressed

    private void btnContinuarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnContinuarKeyPressed

    }//GEN-LAST:event_btnContinuarKeyPressed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        if(verificarInformacionCompleta()){
            controlJugar.obtenerInformacionDelJuego(llenarInformacion());
            controlJugar.cargarPartida();
            controlPrincipal.cambiarPanel("tablero");
        }

    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        controlPrincipal.cambiarPanel("inicio");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtRoundKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRoundKeyTyped
        char c = evt.getKeyChar();
        if(c < '0' || c > '9'){
            evt.consume();
        }
    }//GEN-LAST:event_txtRoundKeyTyped

    private void txtJugadorBlancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJugadorBlancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJugadorBlancoActionPerformed

    private void txtJugadorNegroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJugadorNegroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJugadorNegroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JPanel jpFondo;
    private javax.swing.JLabel lblEvento;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblJugadorBlanco;
    private javax.swing.JLabel lblJugadorNegro;
    private javax.swing.JLabel lblLugar;
    private javax.swing.JLabel lblNombreArchivo;
    private javax.swing.JLabel lblRound;
    private javax.swing.JTextField txtEvento;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtJugadorBlanco;
    private javax.swing.JTextField txtJugadorNegro;
    private javax.swing.JTextField txtLugar;
    private javax.swing.JTextField txtNombreArchivo;
    private javax.swing.JTextField txtRound;
    // End of variables declaration//GEN-END:variables
}
