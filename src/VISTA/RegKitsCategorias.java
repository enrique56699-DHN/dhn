
/*
 * RegClientes.java
 *.
 * Created on 11-nov-2013, 18:18:42
 */
package VISTA;

import CONTROLADOR.ControlKitsCategorias;
import MODELO.ModeloKitsCategorias;
import MODELO.ModeloKitsCategoriasTabla;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 *
 * @author KIKE FRENCH
 */
public final class RegKitsCategorias extends javax.swing.JDialog 
{

    private String CATEGORIA, CATEGORIAANT;

    
    /** Creates new form RegClientes 
    * @param parent
     * @param modal
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException*/
    public RegKitsCategorias(java.awt.Frame parent, boolean modal) throws ClassNotFoundException, SQLException{
        super(parent, modal);
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/carrito.png")); 
        setIconImage(icon);
        setLocationRelativeTo(null);
        actualizarTablas();
        this.Bar.setFloatable(false);
    }
    
    private void actualizarTablas() throws ClassNotFoundException, SQLException
    {
        ModeloKitsCategoriasTabla met;
        met = new ModeloKitsCategoriasTabla();
        tblKITSCATEGORIAS.setModel(met);
    }
    
        
    private void seleccion() throws ClassNotFoundException, SQLException, Exception
    {
        int fila = tblKITSCATEGORIAS.getSelectedRow();
        CATEGORIA = tblKITSCATEGORIAS.getValueAt(fila, 0).toString();      
        CATEGORIAANT = CATEGORIA;
    }  
    
    private void guardar(String ACCION) throws ClassNotFoundException, SQLException, Exception
    {
        //seleccion();
        ModeloKitsCategorias mdl = new ModeloKitsCategorias();        
        
        switch(ACCION)
        {
            case "NUEVO":
                try
                {                 
                    JTextField pwd = new JTextField();
                    pwd.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre de la categoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
                    //pwd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);        
                    int action = JOptionPane.showConfirmDialog(null, pwd,"Registra nueva categoria",JOptionPane.OK_CANCEL_OPTION);
                    if(action == 0)
                    {
                        this.CATEGORIA=pwd.getText();
                        mdl.setCATEGORIA(CATEGORIA);
                        ControlKitsCategorias ctrl = new ControlKitsCategorias();
                        ctrl.guardarRegistro(mdl);
                    }
                    

                    this.dispose();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR GUARDAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);

                }
                break;
            case "EDITAR":
                try
                {
                    seleccion();
                    JTextField pwd = new JTextField();
                    pwd.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre de la categoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
                    //pwd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);        
                    int action = JOptionPane.showConfirmDialog(null, pwd,"Edita la categoria",JOptionPane.OK_CANCEL_OPTION);
                    if(action == 0)
                    {
                        this.CATEGORIA=pwd.getText();
                        mdl.setCATEGORIA(CATEGORIA);
                        mdl.setCATEGORIAANT(CATEGORIAANT);
                        ControlKitsCategorias ctrl = new ControlKitsCategorias();
                        ctrl.actualizarRegistro(mdl);
                    }
                    
                    

                    this.dispose();
                } 
                catch (Exception ex) 
                {

                    JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR ACTUALIZAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);

                }
                break;
            case "BORRAR":
                int eleccion = JOptionPane.showConfirmDialog(null, "Realmente Desea Eliminar el registro?");
                if ( eleccion == 0)
                {
                    try 
                    {
                        seleccion();
                        mdl = new ModeloKitsCategorias();        
                        mdl.setCATEGORIA(CATEGORIA);
                        ControlKitsCategorias ctrl = new ControlKitsCategorias();
                        ctrl.eliminarRegistro(mdl);
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            default:
                break;
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Bar = new javax.swing.JToolBar();
        btnADD = new javax.swing.JButton();
        btnEDITAR = new javax.swing.JButton();
        btnELIMINAR = new javax.swing.JButton();
        btnEXCEL = new javax.swing.JButton();
        jsPKITSCATEGORIAS = new javax.swing.JScrollPane();
        tblKITSCATEGORIAS = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Categorias de Productos");
        setResizable(false);

        Bar.setRollover(true);

        btnADD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/+.png"))); // NOI18N
        btnADD.setText("AÑADIR");
        btnADD.setToolTipText("");
        btnADD.setFocusable(false);
        btnADD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnADD.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDActionPerformed(evt);
            }
        });
        Bar.add(btnADD);

        btnEDITAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEDITAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit.png"))); // NOI18N
        btnEDITAR.setText("EDITAR");
        btnEDITAR.setFocusable(false);
        btnEDITAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEDITAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEDITAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEDITARActionPerformed(evt);
            }
        });
        Bar.add(btnEDITAR);

        btnELIMINAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnELIMINAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/-.png"))); // NOI18N
        btnELIMINAR.setText("ELIMINAR");
        btnELIMINAR.setFocusable(false);
        btnELIMINAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnELIMINAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnELIMINAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnELIMINARActionPerformed(evt);
            }
        });
        Bar.add(btnELIMINAR);

        btnEXCEL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnEXCEL.setText("CANCELAR");
        btnEXCEL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEXCEL.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEXCELActionPerformed(evt);
            }
        });
        Bar.add(btnEXCEL);

        jsPKITSCATEGORIAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Categorias Registradas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jsPKITSCATEGORIAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tblKITSCATEGORIAS.setAutoCreateRowSorter(true);
        tblKITSCATEGORIAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblKITSCATEGORIAS.setName("tblKITSCATEGORIAS"); // NOI18N
        tblKITSCATEGORIAS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblKITSCATEGORIAS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKITSCATEGORIASMousePressed(evt);
            }
        });
        jsPKITSCATEGORIAS.setViewportView(tblKITSCATEGORIAS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
            .addComponent(jsPKITSCATEGORIAS, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jsPKITSCATEGORIAS, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed
        try {
            guardar("NUEVO");
            actualizarTablas();
        } catch (SQLException ex) {
            Logger.getLogger(RegKitsCategorias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegKitsCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnADDActionPerformed

    private void btnEXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXCELActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnEXCELActionPerformed

    private void tblKITSCATEGORIASMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKITSCATEGORIASMousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblKITSCATEGORIASMousePressed

    private void btnEDITARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEDITARActionPerformed
        try {
            // TODO add your handling code here:
            guardar("EDITAR");
            actualizarTablas();
        } catch (SQLException ex) {
            Logger.getLogger(RegKitsCategorias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegKitsCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEDITARActionPerformed

    private void btnELIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnELIMINARActionPerformed
        try {
            // TODO add your handling code here:
            guardar("BORRAR");
            actualizarTablas();
        } catch (SQLException ex) {
            Logger.getLogger(RegKitsCategorias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegKitsCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnELIMINARActionPerformed

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
            java.util.logging.Logger.getLogger(RegKitsCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    RegKitsCategorias dialog = new RegKitsCategorias(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (HeadlessException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(RegKitsCategorias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnEDITAR;
    private javax.swing.JButton btnELIMINAR;
    private javax.swing.JButton btnEXCEL;
    private javax.swing.JScrollPane jsPKITSCATEGORIAS;
    private javax.swing.JTable tblKITSCATEGORIAS;
    // End of variables declaration//GEN-END:variables
    
}
