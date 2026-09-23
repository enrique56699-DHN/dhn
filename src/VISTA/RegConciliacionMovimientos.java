
/*
 * RegClientes.java
 *.
 * Created on 11-nov-2013, 18:18:42
 */

package VISTA;

import CONTROLADOR.ControlMovD;
import CONTROLADOR.F;
import MODELO.ModeloEntradasTabla;
import MODELO.ModeloMovD;
import MODELO.ModeloPagosTabla;
import MODELO.ModeloSalidasTabla;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author KIKE FRENCH
 */
public final class RegConciliacionMovimientos extends javax.swing.JDialog 
{    
    private int IDMOVD, IDMOVIMIENTO;
    private String ACCION, DESCRIPCION,TIPO;
    
    /** Creates new form RegClientes 
    * @param parent
     * @param modal
     * @throws java.text.ParseException        
     * @throws java.lang.ClassNotFoundException        
     * @throws java.sql.SQLException*/        
    public RegConciliacionMovimientos(java.awt.Frame parent, boolean modal) throws ParseException, ClassNotFoundException, SQLException{
        super(parent, modal);
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/link.png")); 
        setIconImage(icon);
        setLocationRelativeTo(null);
        //actualizarTablas();
        this.Bar.setFloatable(false);
    }
    
    private void actualizarTablas(String TIPO) throws ClassNotFoundException, SQLException
    {
        switch(TIPO)
        {
            case "SALIDAS":
                ModeloSalidasTabla mst;
                mst = new ModeloSalidasTabla();
                tblMOVIMIENTOS.setModel(mst);
                F.dimensionar(tblMOVIMIENTOS,"MODV");
                break;
            case "ENTRADAS":
                ModeloEntradasTabla met;
                met = new ModeloEntradasTabla();
                tblMOVIMIENTOS.setModel(met);
                F.dimensionar(tblMOVIMIENTOS,"MOVD");
                break;
            case "PAGOS":
                ModeloPagosTabla mpt;
                mpt = new ModeloPagosTabla();
                tblMOVIMIENTOS.setModel(mpt);
                F.dimensionar(tblMOVIMIENTOS,"MOVD");
                break;    
            default:
                break;
        }
        
    }
    
    public void mostrar(int IDMOVD, String ACCION, int IDMOVIMIENTO, String REF, String TIPO) throws ClassNotFoundException, SQLException, Exception
    {
        this.ACCION=ACCION;
        this.IDMOVD = IDMOVD;
        this.TIPO = TIPO;
        
        switch(ACCION)
        {
            case "NUEVO":
                txtBUSCAR.requestFocus();
                this.txtREF.setText(REF);
                actualizarTablas(TIPO);
                break;                
            case "EDITAR":                
                break;                
            default:
                    break;
        }
    }
        
    private void seleccion() throws ClassNotFoundException, SQLException, Exception
    {
        int fila = tblMOVIMIENTOS.getSelectedRow();
        IDMOVIMIENTO = (int)tblMOVIMIENTOS.getValueAt(fila, 0);
        this.txtIDMOVIMIENTO.setText(""+IDMOVIMIENTO);
        DESCRIPCION = tblMOVIMIENTOS.getValueAt(fila, 1).toString();
        this.txtBUSCAR.setText(DESCRIPCION);
    }   
        
    private void guardar(String ACCION) throws ClassNotFoundException, SQLException, Exception
    {                
        ModeloMovD mdl = new ModeloMovD();        
        
        mdl.setIDMOVD(IDMOVD);
        
        mdl.setIDMOVIMIENTO(Integer.parseInt(txtIDMOVIMIENTO.getText()));
        mdl.setMOVIMIENTO(TIPO);
        mdl.setREF(txtREF.getText());
        mdl.setCOTEJADO(false);
        mdl.setFAC(txtFAC.getText());

        
        switch(ACCION)
        {
            case "NUEVO":
                try
                { 
                    IDMOVD = F.Registro("MOVD","IDMOVD","DESC");
                    mdl.setIDMOVD(IDMOVD);

                    ControlMovD ctrl = new ControlMovD();
                    ctrl.guardarRegistro(mdl);
                    
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

                    ControlMovD ctrl = new ControlMovD();
                    ctrl.actualizarRegistro(mdl);
                    //Funciones.bitacora("CARRITO",IDCARRITO,ACCION,G.IDUSER,"SE MODIFICO DEL PENDIENTE: "+IDPENDIENTE,"");

                    this.dispose();
                } 
                catch (Exception ex) 
                {

                    JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR ACTUALIZAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);

                }
                break;  
            /*case "LIGAR":
                try
                { 
                    IDMOVD = F.Registro("MOVD","IDMOVD","DESC");
                    mdl.setIDMOVD(IDMOVD);

                    ControlMovD ctrl = new ControlMovD();
                    ctrl.guardarRegistro(mdl);
                    //Funciones.bitacora("CARRITO",IDCARRITO,ACCION,G.IDUSER,"AGREGADO EL DIA: "+FEST,"");

                    this.dispose();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR GUARDAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);

                }
                break;*/

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
        btnCANCEL = new javax.swing.JButton();
        jspMOVIMIENTOS = new javax.swing.JScrollPane();
        tblMOVIMIENTOS = new javax.swing.JTable();
        txtBUSCAR = new javax.swing.JTextField();
        pnlACCIONES = new javax.swing.JPanel();
        txtIDMOVIMIENTO = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtREF = new javax.swing.JTextArea();
        txtFAC = new javax.swing.JTextField();
        chkPENDIENTE = new javax.swing.JCheckBox();
        chkFAC = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ligar con Movimiento");
        setResizable(false);

        Bar.setRollover(true);

        btnADD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disk.png"))); // NOI18N
        btnADD.setText("GUARDAR");
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

        btnCANCEL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCANCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnCANCEL.setText("CANCELAR");
        btnCANCEL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCANCEL.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCANCELActionPerformed(evt);
            }
        });
        Bar.add(btnCANCEL);

        jspMOVIMIENTOS.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movimiento:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jspMOVIMIENTOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tblMOVIMIENTOS.setAutoCreateRowSorter(true);
        tblMOVIMIENTOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblMOVIMIENTOS.setName("tblMOVIMIENTOS"); // NOI18N
        tblMOVIMIENTOS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblMOVIMIENTOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMOVIMIENTOSMousePressed(evt);
            }
        });
        jspMOVIMIENTOS.setViewportView(tblMOVIMIENTOS);

        txtBUSCAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBUSCAR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtBUSCAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBUSCARKeyTyped(evt);
            }
        });

        pnlACCIONES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        pnlACCIONES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtIDMOVIMIENTO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIDMOVIMIENTO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDMOVIMIENTO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. de Movimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtIDMOVIMIENTO.setEnabled(false);

        txtREF.setColumns(20);
        txtREF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtREF.setRows(5);
        txtREF.setTabSize(3);
        txtREF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REF", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtREF.setEnabled(false);
        jScrollPane1.setViewportView(txtREF);

        txtFAC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFAC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFAC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Factura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        chkPENDIENTE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkPENDIENTE.setText("PENDIENTE");
        chkPENDIENTE.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkPENDIENTEItemStateChanged(evt);
            }
        });

        chkFAC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkFAC.setText("NO APLICA");
        chkFAC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkFACItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlACCIONESLayout = new javax.swing.GroupLayout(pnlACCIONES);
        pnlACCIONES.setLayout(pnlACCIONESLayout);
        pnlACCIONESLayout.setHorizontalGroup(
            pnlACCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlACCIONESLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlACCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlACCIONESLayout.createSequentialGroup()
                        .addComponent(txtIDMOVIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFAC, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkFAC, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkPENDIENTE)
                        .addGap(0, 341, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlACCIONESLayout.setVerticalGroup(
            pnlACCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlACCIONESLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlACCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDMOVIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFAC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkFAC)
                    .addComponent(chkPENDIENTE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(pnlACCIONES, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBUSCAR, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspMOVIMIENTOS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspMOVIMIENTOS, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlACCIONES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed
        try {
            guardar(ACCION);            
        } catch (SQLException ex) {
            Logger.getLogger(RegConciliacionMovimientos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegConciliacionMovimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnADDActionPerformed

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        F.MAYUS(evt);
        F.buscar(tblMOVIMIENTOS, txtBUSCAR.getText(),null,0);
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void btnCANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCANCELActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCANCELActionPerformed

    private void tblMOVIMIENTOSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMOVIMIENTOSMousePressed
        if (evt.getClickCount() > 1)
        {
            try {
                seleccion();
            } catch (Exception ex) {
                Logger.getLogger(RegConciliacionMovimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }//GEN-LAST:event_tblMOVIMIENTOSMousePressed

    private void chkPENDIENTEItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkPENDIENTEItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            txtFAC.setText("PENDIENTE");
            txtFAC.setEnabled(false);
            chkFAC.setEnabled(false);
        }
        else
        {
            txtFAC.setText("");
            txtFAC.setEnabled(true);
            chkFAC.setEnabled(true);
        }
    }//GEN-LAST:event_chkPENDIENTEItemStateChanged

    private void chkFACItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkFACItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            txtFAC.setText("NO APLICA");
            txtFAC.setEnabled(false);
            chkPENDIENTE.setEnabled(false);
        }
        else
        {
            txtFAC.setText("");
            txtFAC.setEnabled(true);
            chkPENDIENTE.setEnabled(true);
        }
    }//GEN-LAST:event_chkFACItemStateChanged

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
            java.util.logging.Logger.getLogger(RegConciliacionMovimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    RegConciliacionMovimientos dialog = new RegConciliacionMovimientos(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (ParseException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(RegConciliacionMovimientos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnCANCEL;
    private javax.swing.JCheckBox chkFAC;
    private javax.swing.JCheckBox chkPENDIENTE;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jspMOVIMIENTOS;
    private javax.swing.JPanel pnlACCIONES;
    private javax.swing.JTable tblMOVIMIENTOS;
    private javax.swing.JTextField txtBUSCAR;
    private javax.swing.JTextField txtFAC;
    private javax.swing.JTextField txtIDMOVIMIENTO;
    private javax.swing.JTextArea txtREF;
    // End of variables declaration//GEN-END:variables
    
}
