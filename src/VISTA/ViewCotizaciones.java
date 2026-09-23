/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package VISTA;

import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloCotizacionesTabla;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 *
 * @author Enrique Orozco Alcala
 */
public class ViewCotizaciones extends javax.swing.JDialog {

    private int IDCOTIZACION;
    private int ID;
    private String CONSULTA;
    private String NARCHIVO;
     
    /**
     * Creates new form Tools
     * @param parent
     * @param modal
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    
    public ViewCotizaciones(java.awt.Frame parent, boolean modal) throws ClassNotFoundException, SQLException {
        super(parent, modal);
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/cotizacion.png")); 
        setIconImage(icon);
        setLocationRelativeTo(null);       
        this.Bar.setFloatable(false);
        permisos(Principal.PRIVILEGIOS);
    }
        
    private void permisos(String PRIVILEGIOS)
    {
        if(PRIVILEGIOS.equals("ADMIN")||PRIVILEGIOS.equals("SADMIN"))
        {
            this.btnEDITAR.setEnabled(true);
            if(PRIVILEGIOS.equals("SADMIN"))
                this.btnBORRAR.setEnabled(true);
        }
    }
    
    public void mostrar(String CONSULTA, int ID) throws SQLException, Exception
    {
        this.CONSULTA = CONSULTA;
        this.ID = ID;
        actualizarTablas(CONSULTA,ID);        
    }
    
    private void actualizarTablas(String CONSULTA, int ID) throws ClassNotFoundException, SQLException, Exception
    {        
        ModeloCotizacionesTabla mdlt;
        mdlt = new ModeloCotizacionesTabla(CONSULTA, ID, "");
        tblTABLA.setModel(mdlt);        
        mdlt.fireTableDataChanged();
        F.dimensionar(tblTABLA,"COTIZACIONESSER");
    }
    
    private void seleccion(JTable tabla) throws ClassNotFoundException, SQLException
    {                
        int fila = tabla.getSelectedRow();
        IDCOTIZACION = (int) tabla.getValueAt(fila, 0);     
        NARCHIVO = (String) tabla.getValueAt(fila, 3);
    }
        
    private void accion(String ACCION) throws ClassNotFoundException, SQLException, Exception
    {
        try
        {
            if(ACCION.equals("BORRAR"))
            {
                seleccion(tblTABLA);
                int eleccion = JOptionPane.showConfirmDialog(null, "Realmente Desea Eliminar el registro?");
                if ( eleccion == 0)
                {
                    try
                    {
                        try
                        {                            
                            F.eliminarRegistro(IDCOTIZACION, "COTIZACIONES", "IDCOTIZACION");
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR ELIMINAR LOS DATOS DE LA TABLA", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch (HeadlessException ex)
                    {
                        JOptionPane.showMessageDialog( null, "PARA PODER ELIMINAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            else
            {
                try
                {
                    if(ACCION.equals("NUEVO"))    
                    {
                    
                        RegCotizaciones jd = new RegCotizaciones(null,true);
                        jd.mostrar("IDPENDIENTE",ID,ACCION);
                        jd.setVisible(true);
                    }                        
                    else
                    {
                        RegCotizaciones jd = new RegCotizaciones(null,true);
                        jd.mostrar("IDCOTIZACION",IDCOTIZACION,ACCION);
                        jd.setVisible(true);
                    }

                }
                catch (ParseException ex)
                {
                    JOptionPane.showMessageDialog( null, "PARA PODER EDITAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
                }
                actualizarTablas(CONSULTA, ID);
            }
        }
        catch (ParseException ex)
        {
            JOptionPane.showMessageDialog( null, "PARA PODER EDITAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
        }
                
    }
    
    
    private void abrir()
    {
        if(NARCHIVO.equals(""))        
            JOptionPane.showMessageDialog( null, "No hay un documento cargado", "VERIFICAR", JOptionPane.WARNING_MESSAGE);        
        else        
            F.abrirArchivo(G.rutaServidor+"COTIZACIONES\\"+NARCHIVO);        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspTABLA = new javax.swing.JScrollPane();
        tblTABLA = new javax.swing.JTable();
        Bar = new javax.swing.JToolBar();
        btnNUEVO = new javax.swing.JButton();
        btnEDITAR = new javax.swing.JButton();
        btnBORRAR = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnABRIRDOC = new javax.swing.JButton();
        txtBUSCAR = new javax.swing.JTextField();
        pnlIMAGEN = new javax.swing.JPanel();
        lblIMAGEN = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cotizaciones por Servicio");

        jspTABLA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cotizaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        tblTABLA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTABLA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTABLA.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTABLA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTABLAMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTABLAMousePressed(evt);
            }
        });
        jspTABLA.setViewportView(tblTABLA);

        Bar.setRollover(true);

        btnNUEVO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNUEVO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_add.png"))); // NOI18N
        btnNUEVO.setText("NUEVO");
        btnNUEVO.setFocusable(false);
        btnNUEVO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNUEVO.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNUEVO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNUEVOActionPerformed(evt);
            }
        });
        Bar.add(btnNUEVO);

        btnEDITAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEDITAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_edit.png"))); // NOI18N
        btnEDITAR.setText("EDITAR");
        btnEDITAR.setEnabled(false);
        btnEDITAR.setFocusable(false);
        btnEDITAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEDITAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEDITAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEDITARActionPerformed(evt);
            }
        });
        Bar.add(btnEDITAR);

        btnBORRAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBORRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_delete.png"))); // NOI18N
        btnBORRAR.setText("BORRAR");
        btnBORRAR.setEnabled(false);
        btnBORRAR.setFocusable(false);
        btnBORRAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBORRAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBORRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBORRARActionPerformed(evt);
            }
        });
        Bar.add(btnBORRAR);

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnCancel.setText("CANCELAR");
        btnCancel.setFocusable(false);
        btnCancel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        Bar.add(btnCancel);

        btnABRIRDOC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnABRIRDOC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cuentas1.png"))); // NOI18N
        btnABRIRDOC.setText("ABRIR");
        btnABRIRDOC.setToolTipText("Abrir Documento");
        btnABRIRDOC.setFocusable(false);
        btnABRIRDOC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnABRIRDOC.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnABRIRDOC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnABRIRDOCActionPerformed(evt);
            }
        });
        Bar.add(btnABRIRDOC);

        txtBUSCAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBUSCAR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtBUSCAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBUSCARKeyTyped(evt);
            }
        });

        pnlIMAGEN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Previa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblIMAGEN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblIMAGEN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIMAGEN.setText("Imagen no disponible");

        javax.swing.GroupLayout pnlIMAGENLayout = new javax.swing.GroupLayout(pnlIMAGEN);
        pnlIMAGEN.setLayout(pnlIMAGENLayout);
        pnlIMAGENLayout.setHorizontalGroup(
            pnlIMAGENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIMAGEN, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );
        pnlIMAGENLayout.setVerticalGroup(
            pnlIMAGENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIMAGEN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                    .addComponent(txtBUSCAR)
                    .addComponent(jspTABLA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlIMAGEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtBUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnlIMAGEN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        F.MAYUS(evt);
        F.buscar(tblTABLA, txtBUSCAR.getText(),null,0);
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void btnNUEVOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNUEVOActionPerformed
        try {
            accion("NUEVO");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ViewCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNUEVOActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnABRIRDOCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnABRIRDOCActionPerformed
        try {
            seleccion(tblTABLA);
            abrir();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ViewCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnABRIRDOCActionPerformed

    private void btnEDITARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEDITARActionPerformed
        try {
            seleccion(tblTABLA);
            accion("EDITAR");
        } catch (SQLException ex) {
            Logger.getLogger(ViewCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ViewCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEDITARActionPerformed

    private void btnBORRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBORRARActionPerformed
        try {
            accion("BORRAR");
        } catch (SQLException ex) {
            Logger.getLogger(ViewCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ViewCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBORRARActionPerformed

    private void tblTABLAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMousePressed
        try 
        {
            if(F.clickD(evt, tblTABLA))
                seleccion(tblTABLA);
            else
                if(F.dosClick(evt, tblTABLA))
                {
                    seleccion(tblTABLA);
                    abrir();
                }
            } 
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ViewCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTABLAMousePressed

    private void tblTABLAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMouseClicked
        try {
            seleccion(tblTABLA);           
            lblIMAGEN.setIcon(F.previsualizar(lblIMAGEN,NARCHIVO,G.rutaServidor+"COTIZACIONES\\"));
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ViewCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTABLAMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCotizaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                ViewCotizaciones dialog = new ViewCotizaciones(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            } catch (ClassNotFoundException | SQLException ex) {
                java.util.logging.Logger.getLogger(ViewCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnABRIRDOC;
    private javax.swing.JButton btnBORRAR;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEDITAR;
    private javax.swing.JButton btnNUEVO;
    private javax.swing.JScrollPane jspTABLA;
    private javax.swing.JLabel lblIMAGEN;
    private javax.swing.JPanel pnlIMAGEN;
    private javax.swing.JTable tblTABLA;
    private javax.swing.JTextField txtBUSCAR;
    // End of variables declaration//GEN-END:variables
}
