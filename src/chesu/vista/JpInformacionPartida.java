package chesu.vista;

import chesu.controlador.*;
import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
/**
 *
 * @author Cesar Acosta
 */
public class JpInformacionPartida extends javax.swing.JPanel {
    private ControlPrincipal controlPrincipal;
    private ControlJugar controlJugar;
    
    public JpInformacionPartida(ControlPrincipal controlPrincipal) {
        this.controlPrincipal = controlPrincipal;
        initComponents();
        this.jpFondo.setBackground(new Color(0,0,0,150));
        
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
        
        return informacionDelJuego;
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
        txtJugadorNegro = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnContinuar = new javax.swing.JButton();
        txtFecha = new com.toedter.calendar.JDateChooser();
        lblNombreArchivo = new javax.swing.JLabel();
        txtNombreArchivo = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEvento.setText("Event:");

        lblLugar.setText("Site:");

        lblFecha.setText("Date:");

        lblRound.setText("Round:");

        txtRound.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRoundKeyTyped(evt);
            }
        });

        lblJugadorBlanco.setText("White player name:");

        lblJugadorNegro.setText("Black player name:");

        btnCancelar.setText("Cancel");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnContinuar.setText("Next");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        lblNombreArchivo.setText("Save as:");

        javax.swing.GroupLayout jpFondoLayout = new javax.swing.GroupLayout(jpFondo);
        jpFondo.setLayout(jpFondoLayout);
        jpFondoLayout.setHorizontalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoLayout.createSequentialGroup()
                .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFondoLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnContinuar))
                    .addGroup(jpFondoLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLugar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEvento)
                            .addComponent(txtLugar)
                            .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)))
                    .addGroup(jpFondoLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpFondoLayout.createSequentialGroup()
                                .addComponent(lblNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreArchivo))
                            .addGroup(jpFondoLayout.createSequentialGroup()
                                .addComponent(lblJugadorNegro, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtJugadorNegro, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpFondoLayout.createSequentialGroup()
                                .addComponent(lblJugadorBlanco, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtJugadorBlanco))
                            .addGroup(jpFondoLayout.createSequentialGroup()
                                .addComponent(lblRound)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRound, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                    .addComponent(btnCancelar)
                    .addComponent(btnContinuar))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        add(jpFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, 520));
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        controlJugar.obtenerInformacionDelJuego(llenarInformacion());
        controlJugar.cargarPartida();
        controlPrincipal.cambiarPanel("tablero");
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
