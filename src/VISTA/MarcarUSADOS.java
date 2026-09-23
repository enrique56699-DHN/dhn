
/*
 * RegClientes.java
 *
 * Created on 11-nov-2013, 18:18:42
 */
package VISTA;


import CONTROLADOR.ControlCarrito;
import DHNv2.G;
import MODELO.ModeloCarrito;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author KIKE FRENCH
 */
public final class MarcarUSADOS extends javax.swing.JDialog 
{
    private String ACCION;
    private int IDCARRITO, IDPENDIENTE, IDPRODUCTO, IDEMPLEADO;
    private double TOTAL, CANTIDAD;     
        
    /** Creates new form RegClientes
     * @param parent
     * @param modal
     * @throws java.text.ParseException */
    public MarcarUSADOS(java.awt.Frame parent, boolean modal) throws ParseException {
        super(parent, modal);
        initComponents();
        //this.FECHA = formateador.format(hoy);
        this.Bar1.setFloatable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/devolver.png")); 
        setIconImage(icon);
        setLocationRelativeTo(null);
        this.lblFECHAE.setVisible(false);
    }
    
    
    public void mostrar(int IDCARRITO, String ACCION) throws ClassNotFoundException, SQLException, Exception
    {
        this.IDCARRITO = IDCARRITO;
        //this.ACCION = ACCION;
        ModeloCarrito mdl;
        ControlCarrito ctrl = new ControlCarrito();
        mdl = ctrl.getRegistros(IDCARRITO);
        this.lblDESCRIPCION.setText(mdl.getDESCRIPCION());
        this.txtCANTIDAD.setText(""+mdl.getCANTIDAD());
        this.txtCANTIDAD.requestFocus();
        this.IDPENDIENTE = mdl.getIDPENDIENTE();
        this.IDPRODUCTO = mdl.getIDPRODUCTO();
        this.CANTIDAD = mdl.getCANTIDAD();
        this.TOTAL = mdl.getTOTAL();
        this.IDEMPLEADO = mdl.getIDEMPLEADO();
        
        if(ACCION.equals("EDITAR"))
        {
            this.lblFECHAE.setText(mdl.getFECHA());
            this.lblFECHAE.setVisible(true);
            
        }
        
    }

    private void guardar() throws ClassNotFoundException, SQLException, Exception
    { 
        int i = 0;
        double USADOS = Double.parseDouble(txtCANTIDAD.getText());
        if(CANTIDAD == USADOS)
        {
            i = 0;
        }
        if(CANTIDAD > USADOS)
        {            
            i = 1;
        }
        if(CANTIDAD < USADOS)
        {
           i = 2;
        }
        ModeloCarrito mdl;
        ControlCarrito ctrl = new ControlCarrito();
        switch(i)
        {
            case 0:
                /*Object[] inputFields = {"Marcar USADO los articulos seleccionados "};

                int option = JOptionPane.showConfirmDialog(this, inputFields, "USARON", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.OK_OPTION)
                {*/
                    
                    mdl = new ModeloCarrito();
                    mdl.setIDCARRITO(IDCARRITO);
                    mdl.setIDPENDIENTE(IDPENDIENTE);
                    mdl.setIDPRODUCTO(IDPRODUCTO);
                    mdl.setCANTIDAD(CANTIDAD);
                    mdl.setTOTAL(TOTAL);
                    mdl.setIDEMPLEADO(IDEMPLEADO);
                    mdl.setESTADO("USADO");
                    mdl.setFECHA(G.FECHA(G.hoy(),false));
                    
                    ctrl.actualizarRegistro(mdl);
                    this.dispose();
                //}
                break;
            case 1:
                Object[] inputFields = {"USARON MENOS ARTICULOS DE LOS ENTREGADO?\n SE AGREGARAN Y MODIFICARAN LAS CANTIDADES"};

                int option = JOptionPane.showConfirmDialog(this, inputFields, "USARON", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.OK_OPTION)
                {
                    double NCANT = CANTIDAD - USADOS;
                    double NTOTAL = (TOTAL / CANTIDAD) * USADOS;
                    //ModeloCarrito mdl;
                    mdl = new ModeloCarrito();
                    mdl.setIDCARRITO(IDCARRITO);
                    mdl.setIDPENDIENTE(IDPENDIENTE);
                    mdl.setIDPRODUCTO(IDPRODUCTO);
                    mdl.setCANTIDAD(USADOS);
                    mdl.setTOTAL(NTOTAL);
                    mdl.setIDEMPLEADO(IDEMPLEADO);
                    mdl.setESTADO("USADO");
                    mdl.setFECHA(G.FECHA(G.hoy(),false));
                    //ControlCarrito ctrl = new ControlCarrito();
                    ctrl.actualizarRegistro(mdl);
                    
                    NTOTAL = (TOTAL / CANTIDAD) * NCANT;
                    mdl = new ModeloCarrito();
                    mdl.setIDPENDIENTE(IDPENDIENTE);
                    mdl.setIDPRODUCTO(IDPRODUCTO);
                    mdl.setCANTIDAD(NCANT);
                    mdl.setTOTAL(NTOTAL);
                    mdl.setIDEMPLEADO(IDEMPLEADO);
                    mdl.setESTADO("ENTREGADO");
                    mdl.setFECHA(G.FECHA(G.hoy(),false));
                    ctrl = new ControlCarrito();
                    ctrl.guardarRegistro(mdl);
                
                    this.dispose();
                }
                else
                    this.dispose();                
                break;
            case 2:
                JOptionPane.showMessageDialog( null, "NO PUEDES USAR MAS ARTICULOS QUE LOS ENTREGADOS", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                this.txtCANTIDAD.requestFocus();
                /*double NCANT = CANTIDAD - USADOS;
                double NTOTAL = (TOTAL / CANTIDAD) * NCANT;
                ModeloCarrito mdl;
                mdl = new ModeloCarrito();
                mdl.setIDCARRITO(IDCARRITO);
                mdl.setIDPENDIENTE(IDPENDIENTE);
                mdl.setIDPRODUCTO(IDPRODUCTO);
                mdl.setCANTIDAD(USADOS);
                mdl.setTOTAL(0);
                mdl.setIDEMPLEADO(IDEMPLEADO);
                mdl.setESTADO("DEVUELTO");
                ControlCarrito ctrl = new ControlCarrito();
                ctrl.actualizarRegistro(mdl);
                
                mdl = new ModeloCarrito();
                mdl.setIDPENDIENTE(IDPENDIENTE);
                mdl.setIDPRODUCTO(IDPRODUCTO);
                mdl.setCANTIDAD(NCANT);
                mdl.setTOTAL(NTOTAL);
                mdl.setIDEMPLEADO(IDEMPLEADO);
                mdl.setESTADO("ENTREGADO");
                ctrl = new ControlCarrito();
                ctrl.guardarRegistro(mdl);
                this.dispose();*/
                break;
            default: break;
        }
        //Funciones.bitacora("ALMACEN",IDPENDIENTE,ACCION,Splash.IDUSER,COMENTARIOS,DESCRIPCION);
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCERRAR = new javax.swing.JButton();
        Bar1 = new javax.swing.JToolBar();
        btnGUARDAR = new javax.swing.JButton();
        btnCANCEL = new javax.swing.JButton();
        lblDESCRIPCION = new javax.swing.JLabel();
        txtCANTIDAD = new javax.swing.JTextField();
        lblFECHAE = new javax.swing.JLabel();

        btnCERRAR.setText("jButton1");
        btnCERRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCERRARActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Marcar como USADOS");
        setResizable(false);

        Bar1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnGUARDAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disk.png"))); // NOI18N
        btnGUARDAR.setText("GUARDAR");
        btnGUARDAR.setToolTipText("GUARDAR");
        btnGUARDAR.setFocusable(false);
        btnGUARDAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGUARDAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGUARDARActionPerformed(evt);
            }
        });
        Bar1.add(btnGUARDAR);

        btnCANCEL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCANCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnCANCEL.setText("CERRAR");
        btnCANCEL.setToolTipText("CERRAR");
        btnCANCEL.setFocusable(false);
        btnCANCEL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCANCEL.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCANCELActionPerformed(evt);
            }
        });
        Bar1.add(btnCANCEL);

        lblDESCRIPCION.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDESCRIPCION.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripcion del producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        txtCANTIDAD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCANTIDAD.setToolTipText("Productos USADOS en el servicio");
        txtCANTIDAD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos a USADOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtCANTIDAD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCANTIDADKeyPressed(evt);
            }
        });

        lblFECHAE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFECHAE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFECHAE.setText("00/00/0000");
        lblFECHAE.setToolTipText("");
        lblFECHAE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha Entrega:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Bar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDESCRIPCION, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCANTIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 365, Short.MAX_VALUE)
                                .addComponent(lblFECHAE, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDESCRIPCION, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCANTIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFECHAE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(Bar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnCERRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCERRARActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCERRARActionPerformed

    private void btnGUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGUARDARActionPerformed
        try {
            guardar();
        } catch (SQLException ex) {
            Logger.getLogger(MarcarUSADOS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MarcarUSADOS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGUARDARActionPerformed

    private void btnCANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCANCELActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCANCELActionPerformed

    private void txtCANTIDADKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCANTIDADKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            try {
                guardar();
            } catch (SQLException ex) {
                Logger.getLogger(MarcarUSADOS.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(MarcarUSADOS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtCANTIDADKeyPressed

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
            java.util.logging.Logger.getLogger(MarcarUSADOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    MarcarUSADOS dialog = new MarcarUSADOS(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(MarcarUSADOS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar1;
    private javax.swing.JButton btnCANCEL;
    public static javax.swing.JButton btnCERRAR;
    private javax.swing.JButton btnGUARDAR;
    private javax.swing.JLabel lblDESCRIPCION;
    private javax.swing.JLabel lblFECHAE;
    private javax.swing.JTextField txtCANTIDAD;
    // End of variables declaration//GEN-END:variables
}
