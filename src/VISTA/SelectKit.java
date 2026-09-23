
/*
 * RegClientes.java
 *.
 * Created on 11-nov-2013, 18:18:42
 */
package VISTA;

import CONTROLADOR.ControlKits;
import CONTROLADOR.ControlPendientes;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloKits;
import MODELO.ModeloKitsTabla;
import MODELO.ModeloPendientes;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author KIKE FRENCH
 */
public final class SelectKit extends javax.swing.JDialog 
{
    private ModeloKitsTabla mdlT, mdlP;
    private int IDPENDIENTE, IDEMPLEADO, IDCLIENTE;
    private String ACCION, FEST, NARCHIVO ="0.png";
    private Float PRECIO;
   
    /** Creates new form RegClientes 
    * @param parent
     * @param modal
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException*/
    public SelectKit(java.awt.Frame parent, boolean modal) throws ClassNotFoundException, SQLException, Exception{
        super(parent, modal);
        //mdl = new ModeloKitsTabla(0);
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/carrito.png")); 
        setIconImage(icon);
        setLocationRelativeTo(null);
        this.Bar.setFloatable(false);
        F.teclas(this);
        //actualizarImagen();
    }
    
    
    public void mostrar(int IDPENDIENTE, String ACCION, int IDEMPLEADO, String FEST) throws ClassNotFoundException, Exception
    {
        categorias();
        this.IDPENDIENTE = IDPENDIENTE;
        this.IDEMPLEADO = IDEMPLEADO;
        this.txtCATEGORIAS.setSelectedIndex(0);
        nombres();
        this.ACCION = ACCION;
        this.FEST = FEST;
        actualizarImagen();
    }
    
    private void categorias() throws ClassNotFoundException, SQLException
    {
        mdlT = new ModeloKitsTabla(0);
        
        txtCATEGORIAS.removeAllItems();
        int NUM = mdlT.getRowCount();
        //System.out.println("Cantidad de categorias: "+NUM);
        for(int i=0;i<NUM;i++)
        {
            //System.out.println("Este vale: "+mdl.getValueAt(i, 0));
            txtCATEGORIAS.addItem(""+ mdlT.getValueAt(i, 0));
        }      
        
    }
    
    private void nombres() throws ClassNotFoundException, SQLException
    {
        if(txtCATEGORIAS.getSelectedItem()!=null&&txtPAQUETES.getSelectedItem()!=null)
        {
            mdlP = new ModeloKitsTabla(txtCATEGORIAS.getSelectedItem().toString());

            txtPAQUETES.removeAllItems();
            int NUM = mdlP.getRowCount();
            for(int i=0;i<NUM;i++)
            {
                txtPAQUETES.addItem(""+ mdlP.getValueAt(i, 0));
            }
            this.lblKIT.setText(txtCATEGORIAS.getSelectedItem().toString()+" "+txtPAQUETES.getSelectedItem().toString());
        }
        
    }
    
    private void actualizarImagen() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloKits mdl;
        ControlKits ctrl = new ControlKits();
        mdl = ctrl.getRegistro(txtPAQUETES.getSelectedItem().toString(), txtCATEGORIAS.getSelectedItem().toString());
        
        if(!mdl.getFOTO().equals(null)||!mdl.getFOTO().equals(""))
            NARCHIVO = mdl.getFOTO();
        lblIMAGEN.setIcon(F.previsualizar(lblIMAGEN,NARCHIVO,"\\"+"\\"+G.ip+"\\dhn\\KITS\\"));
        PRECIO = mdl.getPRECIO();
    }
    
    private void agregar() throws SQLException, Exception
    {
        int eleccion = JOptionPane.showConfirmDialog(null, "Agregar este KIT:\n"+lblKIT.getText()+"");

        switch(ACCION)
        {
            case "VENTA":                
                ModeloPendientes x;
                x = new ModeloPendientes();    
                IDCLIENTE = IDEMPLEADO;
                x.setIDCLIENTE(IDCLIENTE);
                x.setIDDIRECCION(IDPENDIENTE);
                x.setNPEDIDO("");
                x.setNPEDIDOF("");
                x.setCATEGORIA("VENTA");        
                x.setSUBCATEGORIA("GENERAL");                        
                x.setDESCRIPCION("VENTA E INSTALACION DE:\n * "+txtPAQUETES.getSelectedItem().toString()+" CON PRECIO BASE DE $ "+PRECIO+" EN CASO DE MATERIAL EXTRA SE CONSIDERA EN PRECIO FINAL");     
                x.setCOMENTARIOS("");   
                x.setFREG(FEST);   
                x.setFEST(FEST);
                x.setFFIN(FEST);
                x.setESTADO("ABIERTO");
                IDPENDIENTE = F.Registro("PENDIENTES","IDPENDIENTE","DESC");
                x.setIDPENDIENTE(IDPENDIENTE);
                
                ControlPendientes cp = new ControlPendientes();
                cp.guardarRegistro(x);
                                
                IDEMPLEADO = 0;                
                break;
            default:
                break;
        }
        if (eleccion == 0)        
            F.llenaCarrito(txtPAQUETES.getSelectedItem().toString(), txtCATEGORIAS.getSelectedItem().toString(), IDPENDIENTE, IDEMPLEADO);   
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
        btnEXCEL = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtCATEGORIAS = new javax.swing.JComboBox<>();
        txtPAQUETES = new javax.swing.JComboBox<>();
        lblKIT = new javax.swing.JLabel();
        lblIMAGEN = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar al Carrito");
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Elige tu KIT:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtCATEGORIAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCATEGORIAS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtCATEGORIAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Categorias:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtCATEGORIAS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtCATEGORIASItemStateChanged(evt);
            }
        });
        txtCATEGORIAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCATEGORIASActionPerformed(evt);
            }
        });

        txtPAQUETES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPAQUETES.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtPAQUETES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Paquetes:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtPAQUETES.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtPAQUETESItemStateChanged(evt);
            }
        });
        txtPAQUETES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPAQUETESActionPerformed(evt);
            }
        });

        lblKIT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblKIT.setText("NOMBRE DEL KIT");
        lblKIT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kit:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblIMAGEN.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblIMAGEN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIMAGEN.setText("Imagen");
        lblIMAGEN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 3, true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblIMAGEN, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCATEGORIAS, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtPAQUETES, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblKIT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblIMAGEN, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCATEGORIAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPAQUETES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblKIT)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed
        try {
            agregar();            
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(SelectKit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnADDActionPerformed

    private void btnEXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXCELActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnEXCELActionPerformed

    private void txtCATEGORIASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCATEGORIASActionPerformed
        try {
            nombres();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SelectKit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtCATEGORIASActionPerformed

    private void txtPAQUETESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPAQUETESActionPerformed
        // TODO add your handling code here:
        if(txtCATEGORIAS.getSelectedItem()!=null && txtPAQUETES.getSelectedItem()!=null)        
            this.lblKIT.setText(txtCATEGORIAS.getSelectedItem().toString()+" "+txtPAQUETES.getSelectedItem().toString());       
        else
            this.lblKIT.setText("");
    }//GEN-LAST:event_txtPAQUETESActionPerformed

    private void txtPAQUETESItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtPAQUETESItemStateChanged
        try {
            actualizarImagen();
        } catch (SQLException ex) {
            Logger.getLogger(SelectKit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SelectKit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtPAQUETESItemStateChanged

    private void txtCATEGORIASItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtCATEGORIASItemStateChanged
        try {
            actualizarImagen();
        } catch (SQLException ex) {
            Logger.getLogger(SelectKit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SelectKit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtCATEGORIASItemStateChanged

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
            java.util.logging.Logger.getLogger(SelectKit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                SelectKit dialog = new SelectKit(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            } catch (HeadlessException | ClassNotFoundException | SQLException ex) {
                //Logger.getLogger(ElegirKit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(SelectKit.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnEXCEL;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblIMAGEN;
    private javax.swing.JLabel lblKIT;
    private javax.swing.JComboBox<String> txtCATEGORIAS;
    private javax.swing.JComboBox<String> txtPAQUETES;
    // End of variables declaration//GEN-END:variables
    
}
