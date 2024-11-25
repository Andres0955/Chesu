package chesu.vista;

import chesu.controlador.*;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Cesar Acosta
 */
public class JpCargarPartida extends javax.swing.JPanel {
    private ControlPrincipal controlPrincipal;
    private ControlReproductor controlReproductor;
    private ImageIcon fondo;
    private File archivoSeleccionado;
    
    public JpCargarPartida(ControlPrincipal controlPrincipal, ControlReproductor controlReproductor) {
        this.controlPrincipal = controlPrincipal;
        this.controlReproductor = controlReproductor;
        this.fondo = new ImageIcon(getClass().getResource("/recursos/imagenes/fondos/fondo.png"));
        initComponents();
        this.jPanelMenu.setBackground(new Color(0,0,0,150));
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    private File crearFileChooser(){
        JFileChooser fileChooser = new JFileChooser("./src/recursos/partidas");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto", "txt"));
        int resultado = fileChooser.showOpenDialog(this);
        
        if(resultado == JFileChooser.APPROVE_OPTION){
            archivoSeleccionado = fileChooser.getSelectedFile();
            return archivoSeleccionado;
        }else{
            errorAlCargarArchivo();
            return null;
        }
    }
    
    /**
     * Muestra un cuadro de dialogo para informar al usuario que no puede continuar sin cargar un archivo.
     */
    public void errorAlCargarArchivo(){
        JOptionPane.showMessageDialog(this, "No se ha cargado ningun archivo, intentelo nuevamente.",
                "Error al cargar Archivo.", JOptionPane.ERROR_MESSAGE);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMenu = new javax.swing.JPanel();
        btnSeleccionarArchivo = new javax.swing.JButton();
        btnStart = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        lblNombrePrograma = new javax.swing.JLabel();

        btnSeleccionarArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnSubir.png"))); // NOI18N
        btnSeleccionarArchivo.setBorderPainted(false);
        btnSeleccionarArchivo.setContentAreaFilled(false);
        btnSeleccionarArchivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSeleccionarArchivo.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        btnSeleccionarArchivo.setFocusPainted(false);
        btnSeleccionarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarArchivoActionPerformed(evt);
            }
        });

        btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnContinue.png"))); // NOI18N
        btnStart.setBorderPainted(false);
        btnStart.setContentAreaFilled(false);
        btnStart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStart.setFocusPainted(false);
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnSeleccionarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btnSeleccionarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        lblNombrePrograma.setFont(new java.awt.Font("Algerian", 1, 120)); // NOI18N
        lblNombrePrograma.setForeground(new java.awt.Color(255, 255, 255));
        lblNombrePrograma.setText("Chesu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombrePrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(495, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(lblNombrePrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarArchivoActionPerformed
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JpInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        File archivo = crearFileChooser();
        controlReproductor.obtenerArchivo(archivo);
        JOptionPane.showMessageDialog(this, "El archivo " + archivo.getName() + " se cargó correctamente.",
            "Carga exitosa", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnSeleccionarArchivoActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if(archivoSeleccionado != null){
            controlPrincipal.cambiarPanel("reproducirPartida");
        }else{
            errorAlCargarArchivo();
        }
        
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        controlPrincipal.cambiarPanel("inicio");
    }//GEN-LAST:event_btnRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnSeleccionarArchivo;
    private javax.swing.JButton btnStart;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JLabel lblNombrePrograma;
    // End of variables declaration//GEN-END:variables
}
