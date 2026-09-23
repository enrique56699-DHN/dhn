
/*
 * RegClientes.java
 *
 * Created on 11-nov-2013, 18:18:42
 */
package VISTA;

import CONTROLADOR.ControlMovD;
import CONTROLADOR.ControlPagos;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloMovD;
import MODELO.ModeloPagos;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author KIKE FRENCH
 */
public final class RegMovD extends javax.swing.JDialog 
{
     private String ACCION;
    
    /** Creates new form RegClientes
     * @param parent
     * @param modal
     * @throws java.text.ParseException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException */
    public RegMovD(java.awt.Frame parent, boolean modal) throws ParseException, ClassNotFoundException, SQLException {
        super(parent, modal);
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/verificar.png")); 
        setIconImage(icon);
        this.Bar.setFloatable(false);
        setLocationRelativeTo(null);
    }    
        
    public void accion(int IDMOVD, String ACCION, int IDPAGO) throws ParseException, Exception
    {
        this.ACCION=ACCION;
        
        switch(ACCION)
        {   
            case "NUEVO":
                this.txtREF.requestFocus();
                this.txtIDMOVIMIENTO.setText(""+IDPAGO);
                break;                
            case "EDITAR":                
                ModeloMovD mdl;
                ControlMovD ctrl = new ControlMovD();
                mdl = ctrl.getRegistros(IDMOVD);                  
                this.txtIDMOVIMIENTO.setText(""+mdl.getIDMOVIMIENTO());
                this.txtIDMOVIMIENTO.setEnabled(true);
                this.txtREF.setText(""+mdl.getREF());
                this.txtFAC.setText(""+mdl.getFAC());
                this.txtMOVIMIENTO.setSelectedItem(mdl.getMOVIMIENTO());
                this.txtMOVIMIENTO.setEnabled(true);
                this.txtCOTEJADO.setSelected(mdl.getCOTEJADO());
                this.txtCOTEJADO.setEnabled(true);                
                break;                
            default:
                break;                
        }
    }

    private void guardar(String ACCION) throws SQLException, Exception
    {
        
        if(this.txtREF.getText().equals(""))
        {
             JOptionPane.showMessageDialog( null, "CAMPO NO PUEDE ESTAR VACIO", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
             this.txtREF.requestFocus();
        }
        else
        {
            ModeloMovD mdl = new ModeloMovD();

            mdl.setIDMOVIMIENTO(Integer.parseInt(txtIDMOVIMIENTO.getText()));
            mdl.setREF(txtREF.getText());
            mdl.setFAC(txtFAC.getText());
            mdl.setMOVIMIENTO(txtMOVIMIENTO.getSelectedItem().toString());
            mdl.setCOTEJADO(txtCOTEJADO.isSelected());

            switch(ACCION)
            {
                case "NUEVO":
                    try
                    {
                        ControlMovD cp = new ControlMovD();
                        cp.guardarRegistro(mdl);
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR GUARDAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "EDITAR":
                    try
                    {                       
                        ControlMovD cp = new ControlMovD();
                        cp.actualizarRegistro(mdl);                        
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR ACTUALIZAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                default:
                    break;
            }
        }         
    }

    private void intermedio(String ACCION) throws ClassNotFoundException, SQLException, Exception
    {
        String conceptoB = txtREF.getText();
        ControlMovD ctrl = new ControlMovD();
        List lista = ctrl.ref();
        boolean x = false;
        for(int i = 0; i < lista.size(); i++)
        {
            String conceptoA = (String) lista.get(i);         
            if(F.comparar(conceptoA, conceptoB))            
            {                 
                ctrl = new ControlMovD();
                int idmov = ctrl.idmovimiento(conceptoA);
                ModeloPagos mdl;
                ControlPagos ctrlP = new ControlPagos();
                mdl=ctrlP.getRegistrosID(idmov);
                F.abrirArchivo(G.rutaServidor+"VOUCHER\\"+mdl.getDOC());
                int eleccion = JOptionPane.showConfirmDialog( null, "Atencion!! La conciliacion ya existe\n Numero de recibo: "+idmov+"\n Contrato: "
                        +mdl.getIDCONTRATO()+"\n Cliente: "+mdl.getNOMBRE()+"\n Guardar de todos modos??",
                        "VERIFICAR", JOptionPane.YES_NO_OPTION);    
                x = eleccion == 0;                     
                break;
            }
            else            
                x = false;              
        }
        if(x == true)
            guardar(ACCION); //       System.out.println("AQUI VAMOS A GUARDAR")
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
        jpnCONTENEDOR = new javax.swing.JPanel();
        txtFAC = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtREF = new javax.swing.JTextArea();
        Bar = new javax.swing.JToolBar();
        btnGuardar = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtMOVIMIENTO = new javax.swing.JComboBox<>();
        txtIDMOVIMIENTO = new javax.swing.JTextField();
        txtCOTEJADO = new javax.swing.JCheckBox();
        txtBYPASS = new javax.swing.JCheckBox();

        btnCERRAR.setText("jButton1");
        btnCERRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCERRARActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Pagos");
        setResizable(false);

        txtFAC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFAC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFAC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Factura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtFAC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFACKeyTyped(evt);
            }
        });

        txtREF.setColumns(20);
        txtREF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtREF.setLineWrap(true);
        txtREF.setRows(5);
        txtREF.setWrapStyleWord(true);
        txtREF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Referencia Bancaria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtREF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtREFKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtREF);

        javax.swing.GroupLayout jpnCONTENEDORLayout = new javax.swing.GroupLayout(jpnCONTENEDOR);
        jpnCONTENEDOR.setLayout(jpnCONTENEDORLayout);
        jpnCONTENEDORLayout.setHorizontalGroup(
            jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                        .addComponent(txtFAC, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnCONTENEDORLayout.setVerticalGroup(
            jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCONTENEDORLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFAC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Bar.setRollover(true);

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disk.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        Bar.add(btnGuardar);

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

        txtMOVIMIENTO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMOVIMIENTO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ENTRADAS", "PAGOS", "SALIDAS" }));
        txtMOVIMIENTO.setSelectedIndex(1);
        txtMOVIMIENTO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de Movimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtMOVIMIENTO.setEnabled(false);

        txtIDMOVIMIENTO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIDMOVIMIENTO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDMOVIMIENTO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. de Movimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtIDMOVIMIENTO.setEnabled(false);

        txtCOTEJADO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCOTEJADO.setText("Cotejado");
        txtCOTEJADO.setEnabled(false);

        txtBYPASS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBYPASS.setText("ByPass");
        txtBYPASS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtBYPASSItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnCONTENEDOR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMOVIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDMOVIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCOTEJADO)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtBYPASS)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMOVIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIDMOVIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCOTEJADO)
                        .addComponent(txtBYPASS)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnCONTENEDOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            if(!this.txtBYPASS.isSelected())                           
                intermedio(ACCION);                  
            VPagosVeri.btnVERI.doClick();
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(RegMovD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCERRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCERRARActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCERRARActionPerformed

    private void txtBYPASSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtBYPASSItemStateChanged
        this.txtREF.setEnabled(!txtBYPASS.isSelected());
        this.txtFAC.setEnabled(!txtBYPASS.isSelected());
    }//GEN-LAST:event_txtBYPASSItemStateChanged

    private void txtREFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtREFKeyTyped
        F.MAYUS(evt);
    }//GEN-LAST:event_txtREFKeyTyped

    private void txtFACKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFACKeyTyped
        F.NUM(evt);
    }//GEN-LAST:event_txtFACKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(RegMovD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                RegMovD dialog = new RegMovD(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            } catch (ParseException | ClassNotFoundException | SQLException ex) {
                Logger.getLogger(RegMovD.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    public static javax.swing.JButton btnCERRAR;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpnCONTENEDOR;
    private javax.swing.JCheckBox txtBYPASS;
    private javax.swing.JCheckBox txtCOTEJADO;
    private javax.swing.JTextField txtFAC;
    private javax.swing.JTextField txtIDMOVIMIENTO;
    private javax.swing.JComboBox<String> txtMOVIMIENTO;
    private javax.swing.JTextArea txtREF;
    // End of variables declaration//GEN-END:variables
}
