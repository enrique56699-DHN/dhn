
/*
 * RegClientes.java
 *
 * Created on 11-nov-2013, 18:18:42
 */
package VISTA;

import CONTROLADOR.ControlAbonos;
import CONTROLADOR.ControlEmpleados;
import CONTROLADOR.ControlPrestaciones;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloEmpleados;
import MODELO.ModeloAbonos;
import MODELO.ModeloPrestaciones;
import javax.swing.JOptionPane;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;

/**
 *
 * @author KIKE FRENCH
 */
public final class RegAbonos extends javax.swing.JDialog 
{
    private int IDEMPLEADO,idabono;
    private String  descripcion, monto, empleado;
    private String ACCION;
    private int PERIODO;
    
    /** Creates new form RegClientes
     * @param parent
     * @param modal
     * @throws java.text.ParseException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException */
    public RegAbonos(java.awt.Frame parent, boolean modal) throws ParseException, ClassNotFoundException, SQLException {
        super(parent, modal);
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/prestamos.png")); 
        setIconImage(icon);
        this.Bar.setFloatable(false);
        setLocationRelativeTo(null);
        dte_fecha.setDate(G.hoy());
        G.atributosCalendario(dte_fecha);
    }
    
    private void APOYO() throws SQLException, Exception
    {
        JInternalFrame ji = validador.getJInternalFrame(VApoyo.class.getName());
        if(ji != null )
            ji.dispose();
        ji = new VApoyo("ABONO",G.formato.format(dte_fecha.getDate()),""+idabono,"$ "+monto,descripcion,empleado);
        Principal.getCONT().add(ji);
        validador.addJIframe(VApoyo.class.getName(), ji);
        RegAbonos.btnCERRAR.doClick();
        this.dispose();
               
            ji.setMaximum(true);        
    }
    
    private void periodo() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloPrestaciones mdl;
        ControlPrestaciones ctrl = new ControlPrestaciones();
        mdl = ctrl.Registros("BONO DE CAJA", "DESC");
        PERIODO = mdl.getPERIODO();
    }
        
    public void accion(int IDABONO,int IDEMPLEADO, String ACCION) throws ParseException, Exception
    {
        this.ACCION=ACCION;
        this.idabono = IDABONO;
        this.IDEMPLEADO = IDEMPLEADO;
        
        switch(ACCION)
        {
            case "NUEVO":
                ModeloEmpleados mdlE;
                ControlEmpleados ctrlE = new ControlEmpleados();
                mdlE = ctrlE.getRegistrosID(this.IDEMPLEADO);
                empleado=mdlE.getNOMBRE()+" "+mdlE.getAPELLIDOS();
                lblEMPLEADO.setText(empleado);
                this.txtDESCRIPCION.requestFocus();
                break;
            case "EDITAR":
                ModeloAbonos pto;
                ControlAbonos ca = new ControlAbonos();
                pto = ca.getRegistrosID(IDABONO);
                
                this.txtIDABONO.setText(""+pto.getIDABONO());
                this.txtDESCRIPCION.setText(""+pto.getDESCRIPCION());
                this.txtMONTO.setText(""+pto.getMONTO());
                this.dte_fecha.setDate(G.formato.parse(pto.getFECHA()));
                this.IDEMPLEADO = pto.getIDEMPLEADO();
                lblEMPLEADO.setText(empleado);
                
                break;
                default:
                    break;                
        }
    }

    private void guardar(String ACCION) throws SQLException, Exception
    {
        periodo();        
        
        if(this.txtDESCRIPCION.getText().equals(""))
        {
            JOptionPane.showMessageDialog( null, "CAMPO NO PUEDE ESTAR VACIO", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
            this.txtDESCRIPCION.requestFocus();
        }
        else
        {
            ModeloAbonos md = new ModeloAbonos();

            descripcion=txtDESCRIPCION.getText();
            monto=txtMONTO.getText();
            empleado=lblEMPLEADO.getText();
            
            if(txtIDABONO.getText()!=null && !txtIDABONO.getText().equals(""))
                md.setIDABONO(Integer.parseInt(txtIDABONO.getText()));
            
            md.setDESCRIPCION(descripcion);
            md.setMONTO(Float.parseFloat(monto.trim()));
            md.setIDEMPLEADO(IDEMPLEADO);
            md.setFECHA(G.formato.format(dte_fecha.getDate()));
            md.setPERIODO(PERIODO);

            switch(ACCION)
            {
                case "NUEVO":
                    try
                    {
                        idabono = F.Registro("ABONOS", "IDABONO", "DESC");
                        md.setIDABONO(idabono);

                        ControlAbonos cp = new ControlAbonos();
                        cp.guardarRegistro(md);

                        APOYO();
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
                        ControlAbonos cp = new ControlAbonos();
                        cp.actualizarRegistro(md);
                        APOYO();
                        this.dispose();
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
        txtIDABONO = new javax.swing.JTextField();
        txtMONTO = new javax.swing.JTextField();
        dte_fecha = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDESCRIPCION = new javax.swing.JTextArea();
        lblEMPLEADO = new javax.swing.JLabel();
        Bar = new javax.swing.JToolBar();
        btnGuardar = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        btnCERRAR.setText("jButton1");
        btnCERRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCERRARActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Abonos");
        setResizable(false);

        txtIDABONO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIDABONO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. de Abono", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtIDABONO.setEnabled(false);

        txtMONTO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMONTO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Monto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtMONTO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMONTOKeyTyped(evt);
            }
        });

        dte_fecha.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        dte_fecha.setToolTipText("Fecha de Registro");
        dte_fecha.setDateFormatString("dd/MM/yyyy");
        dte_fecha.setEnabled(false);
        dte_fecha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dte_fecha.setMaxSelectableDate(new java.util.Date(2524633297000L));
        dte_fecha.setMaximumSize(new java.awt.Dimension(50, 50));
        dte_fecha.setMinSelectableDate(new java.util.Date(1514790097000L));
        dte_fecha.setMinimumSize(new java.awt.Dimension(50, 50));
        dte_fecha.setPreferredSize(new java.awt.Dimension(50, 50));

        txtDESCRIPCION.setColumns(20);
        txtDESCRIPCION.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDESCRIPCION.setLineWrap(true);
        txtDESCRIPCION.setRows(5);
        txtDESCRIPCION.setWrapStyleWord(true);
        txtDESCRIPCION.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripcion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtDESCRIPCION.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDESCRIPCIONKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtDESCRIPCION);

        lblEMPLEADO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEMPLEADO.setText("NOMBRE DEL EMPLEADO");
        lblEMPLEADO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Empleado:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout jpnCONTENEDORLayout = new javax.swing.GroupLayout(jpnCONTENEDOR);
        jpnCONTENEDOR.setLayout(jpnCONTENEDORLayout);
        jpnCONTENEDORLayout.setHorizontalGroup(
            jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                        .addComponent(txtIDABONO, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 320, Short.MAX_VALUE)
                        .addComponent(dte_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                        .addComponent(txtMONTO, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblEMPLEADO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnCONTENEDORLayout.setVerticalGroup(
            jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCONTENEDORLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dte_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIDABONO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEMPLEADO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMONTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnCONTENEDOR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnCONTENEDOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            guardar(ACCION);
        } catch (Exception ex) {
            Logger.getLogger(RegAbonos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCERRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCERRARActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCERRARActionPerformed

    private void txtDESCRIPCIONKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDESCRIPCIONKeyTyped
        F.MAYUS(evt);
    }//GEN-LAST:event_txtDESCRIPCIONKeyTyped

    private void txtMONTOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMONTOKeyTyped
        F.NUM(evt);
    }//GEN-LAST:event_txtMONTOKeyTyped

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
            java.util.logging.Logger.getLogger(RegAbonos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    RegAbonos dialog = new RegAbonos(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (ParseException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(RegAbonos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    public static javax.swing.JButton btnCERRAR;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGuardar;
    private com.toedter.calendar.JDateChooser dte_fecha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpnCONTENEDOR;
    private javax.swing.JLabel lblEMPLEADO;
    private javax.swing.JTextArea txtDESCRIPCION;
    private javax.swing.JTextField txtIDABONO;
    private javax.swing.JTextField txtMONTO;
    // End of variables declaration//GEN-END:variables
}
