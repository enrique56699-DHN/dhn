
/*
 * RegClientes.java
 *
 * Created on 11-nov-2013, 18:18:42
 */
package VISTA;


import CONTROLADOR.ControlEDetalles;
import CONTROLADOR.ControlEmpleados;
import CONTROLADOR.ControlPrestaciones;
import CONTROLADOR.ControlVacaciones;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloEDetalles;
import MODELO.ModeloEmpleados;
import MODELO.ModeloPrestaciones;
import MODELO.ModeloVacaciones;
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
public final class RegVacaciones extends javax.swing.JDialog 
{

    private int cli0,IDEMPLEADO;
    private String descripcion, diast, empleado, reg, salida, regreso;
    private String ACCION;
    private int idvacacion;
    private int PERIODO;
    private String PERIODOT;
    
    /*private Date fecha = new Date();
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");*/

    /** Creates new form RegClientes
     * @param parent
     * @param modal
     * @throws java.text.ParseException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException */
    
    public RegVacaciones(java.awt.Frame parent, boolean modal) throws ParseException, ClassNotFoundException, SQLException {
        super(parent, modal);
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/vacaciones1.png")); 
        setIconImage(icon);
        this.Bar.setFloatable(false);
        setLocationRelativeTo(null);
        //AÑADE FECHA reg HOY A ABRIR
        dteFREG.setDate(G.hoy());
        G.atributosCalendario(dteFREG);
        G.atributosCalendario(dteFSALIDA);
        G.atributosCalendario(dteFREGRESO);
        reg=G.FECHA(G.hoy(),false);

    }
        
    private void APOYO() throws SQLException, Exception
    {
        JInternalFrame ji = validador.getJInternalFrame(VApoyo.class.getName());
        if(ji != null )
            ji.dispose();
        ji = new VApoyo("VACACION",reg,salida,regreso,descripcion,empleado,diast, PERIODOT);
        Principal.getCONT().add(ji);
        validador.addJIframe(VApoyo.class.getName(), ji);
        RegVacaciones.btnCERRAR.doClick();
        this.dispose();
                
            ji.setMaximum(true);        
    }
    
    private void periodo() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloEDetalles mdlD;
        ControlEDetalles ctrlD = new ControlEDetalles();
        mdlD = ctrlD.periodoVID(IDEMPLEADO);
        PERIODO = mdlD.getPERIODOV();
        
        ModeloPrestaciones mdl;
        ControlPrestaciones ctrl = new ControlPrestaciones();
        mdl = ctrl.Registros("PRIMA VACACIONAL", "DESC");
        int PERIODOS = mdl.getPERIODO();
        PERIODOT = PERIODOS+" - "+mdl.getFINI() +"-"+mdl.getFFIN();
        
        lblPERIODOV.setText(PERIODOT);
        
        if(PERIODO != PERIODOS)
        {
            JOptionPane.showMessageDialog( null, "EL EMPLEADO NO SE ENCUENTRA EN EL PERIODO DE VACACIONES QUE PUEDENS ER PAGADAS", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
            this.dispose();
        }        
    }
        
    public void accion(int IDVACACION, int IDEMPLEADO, String ACCION) throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        this.ACCION=ACCION;
        this.idvacacion = IDVACACION;
        this.IDEMPLEADO = IDEMPLEADO;        
        
        switch(ACCION)
        {
            case "NUEVO":
                ModeloEmpleados mdlE;
                ControlEmpleados ctrlE = new ControlEmpleados();
                mdlE = ctrlE.getRegistrosID(this.IDEMPLEADO);
                empleado=mdlE.getNOMBRE()+" "+mdlE.getAPELLIDOS();
                lblEMPLEADO.setText(empleado);
                this.txtCOMENTARIOS.requestFocus();
                periodo();                
                break;
                
            case "EDITAR":
                ModeloVacaciones pto;
                ControlVacaciones cp = new ControlVacaciones();
                pto=cp.getRegistrosID(IDVACACION);                
                this.txtIDVACACION.setText(""+pto.getIDVACACION());
                this.txtCOMENTARIOS.setText(""+pto.getCOMENTARIOS());   
                this.dteFREG.setDate(G.formato.parse(pto.getFREG()));
                this.dteFSALIDA.setDate(G.formato.parse(pto.getFSALIDA()));
                this.dteFREGRESO.setDate(G.formato.parse(pto.getFREGRESO()));
                this.txtDIAST.setText(""+pto.getDIAST());
                this.IDEMPLEADO = pto.getIDEMPLEADO();
                lblEMPLEADO.setText(empleado);
                this.PERIODO=pto.getPERIODO();
                break;
                
            default: break;
                
        }
        
    }

    private void guardar(String ACCION) throws SQLException, Exception
    {
                
        if(this.txtCOMENTARIOS.getText().equals(""))
        {
                JOptionPane.showMessageDialog( null, "CAMPO NO PUEDE ESTAR VACIO", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
                this.txtCOMENTARIOS.requestFocus();
        }
            else
            {
                ModeloVacaciones md = new ModeloVacaciones();
                descripcion=txtCOMENTARIOS.getText();
                diast=txtDIAST.getText();
                empleado=lblEMPLEADO.getText();
                reg=""+G.formato.format(dteFREG.getDate());
                salida=""+G.formato.format(dteFSALIDA.getDate());
                regreso=""+G.formato.format(dteFREGRESO.getDate());
                
                md.setCOMENTARIOS(descripcion);
                md.setDIAST(Integer.parseInt(diast.trim()));
                md.setIDEMPLEADO(IDEMPLEADO);
                md.setFREG(reg);
                md.setFSALIDA(salida);
                md.setFREGRESO(regreso);
                md.setPERIODO(PERIODO);                
                                
                switch(ACCION)
                {
                    case "NUEVO":
                        try
                        {
                            idvacacion = F.Registro("VACACIONES", "IDVACACION", "DESC");
                            md.setIDVACACION(idvacacion);
                            
                            ControlVacaciones cp = new ControlVacaciones();
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
                            md.setIDVACACION(Integer.parseInt(txtIDVACACION.getText()));
                            
                            ControlVacaciones cp = new ControlVacaciones();
                            cp.actualizarRegistro(md);
                            APOYO();
                            this.dispose();
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR ACTUALIZAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    default: break;
                }                
            }
         APOYO();
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
        txtIDVACACION = new javax.swing.JTextField();
        txtDIAST = new javax.swing.JTextField();
        dteFREG = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCOMENTARIOS = new javax.swing.JTextArea();
        dteFSALIDA = new com.toedter.calendar.JDateChooser();
        dteFREGRESO = new com.toedter.calendar.JDateChooser();
        lblEMPLEADO = new javax.swing.JLabel();
        lblPERIODOV = new javax.swing.JLabel();
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
        setTitle("Registro de Vacaciones");
        setResizable(false);

        txtIDVACACION.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtIDVACACION.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtIDVACACION.setEnabled(false);

        txtDIAST.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDIAST.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDIAST.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dias Tomados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtDIAST.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDIASTKeyTyped(evt);
            }
        });

        dteFREG.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha de Registro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        dteFREG.setToolTipText("Fecha de Registro");
        dteFREG.setDateFormatString("dd/MM/yyyy");
        dteFREG.setEnabled(false);
        dteFREG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dteFREG.setMaxSelectableDate(new java.util.Date(2524633297000L));
        dteFREG.setMaximumSize(new java.awt.Dimension(50, 50));
        dteFREG.setMinSelectableDate(new java.util.Date(1514790097000L));
        dteFREG.setMinimumSize(new java.awt.Dimension(50, 50));
        dteFREG.setPreferredSize(new java.awt.Dimension(50, 50));

        txtCOMENTARIOS.setColumns(20);
        txtCOMENTARIOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCOMENTARIOS.setLineWrap(true);
        txtCOMENTARIOS.setRows(5);
        txtCOMENTARIOS.setWrapStyleWord(true);
        txtCOMENTARIOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comentarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtCOMENTARIOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCOMENTARIOSKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtCOMENTARIOS);

        dteFSALIDA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salida", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        dteFSALIDA.setToolTipText("Fecha de Salida");
        dteFSALIDA.setDateFormatString("dd/MM/yyyy");
        dteFSALIDA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dteFSALIDA.setMaxSelectableDate(new java.util.Date(2524633297000L));
        dteFSALIDA.setMaximumSize(new java.awt.Dimension(50, 50));
        dteFSALIDA.setMinSelectableDate(new java.util.Date(1514790097000L));
        dteFSALIDA.setMinimumSize(new java.awt.Dimension(50, 50));
        dteFSALIDA.setPreferredSize(new java.awt.Dimension(50, 50));

        dteFREGRESO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Regreso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        dteFREGRESO.setToolTipText("Fecha de Regreso");
        dteFREGRESO.setDateFormatString("dd/MM/yyyy");
        dteFREGRESO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dteFREGRESO.setMaxSelectableDate(new java.util.Date(2524633297000L));
        dteFREGRESO.setMaximumSize(new java.awt.Dimension(50, 50));
        dteFREGRESO.setMinSelectableDate(new java.util.Date(1514790097000L));
        dteFREGRESO.setMinimumSize(new java.awt.Dimension(50, 50));
        dteFREGRESO.setPreferredSize(new java.awt.Dimension(50, 50));

        lblEMPLEADO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEMPLEADO.setText("NOMBRE DEL EMPLEADO");
        lblEMPLEADO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Empleado:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblPERIODOV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPERIODOV.setText("1 - 2023 - 2024");
        lblPERIODOV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Periodo:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout jpnCONTENEDORLayout = new javax.swing.GroupLayout(jpnCONTENEDOR);
        jpnCONTENEDOR.setLayout(jpnCONTENEDORLayout);
        jpnCONTENEDORLayout.setHorizontalGroup(
            jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                        .addComponent(txtIDVACACION, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPERIODOV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dteFREG, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                        .addComponent(dteFSALIDA, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dteFREGRESO, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDIAST, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 148, Short.MAX_VALUE))
                    .addComponent(lblEMPLEADO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnCONTENEDORLayout.setVerticalGroup(
            jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCONTENEDORLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dteFREG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIDVACACION)
                    .addComponent(lblPERIODOV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEMPLEADO)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dteFSALIDA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dteFREGRESO, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCONTENEDORLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtDIAST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            // TODO add your handling code here:
            guardar(ACCION);
        } catch (Exception ex) {
            Logger.getLogger(RegVacaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCERRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCERRARActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCERRARActionPerformed

    private void txtCOMENTARIOSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCOMENTARIOSKeyTyped
        F.MAYUS(evt);
    }//GEN-LAST:event_txtCOMENTARIOSKeyTyped

    private void txtDIASTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDIASTKeyTyped
        F.NUM(evt);
    }//GEN-LAST:event_txtDIASTKeyTyped

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
            java.util.logging.Logger.getLogger(RegVacaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    RegVacaciones dialog = new RegVacaciones(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (ParseException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(RegVacaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    public static javax.swing.JButton btnCERRAR;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGuardar;
    private com.toedter.calendar.JDateChooser dteFREG;
    private com.toedter.calendar.JDateChooser dteFREGRESO;
    private com.toedter.calendar.JDateChooser dteFSALIDA;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpnCONTENEDOR;
    private javax.swing.JLabel lblEMPLEADO;
    private javax.swing.JLabel lblPERIODOV;
    private javax.swing.JTextArea txtCOMENTARIOS;
    private javax.swing.JTextField txtDIAST;
    private javax.swing.JTextField txtIDVACACION;
    // End of variables declaration//GEN-END:variables
}
