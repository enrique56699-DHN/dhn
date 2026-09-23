
/*
 * RegClientes.java
 *
 * Created on 11-nov-2013, 18:18:42
 */
package VISTA;

import CONTROLADOR.F;
import DHNv2.G;
import com.toedter.calendar.JDateChooser;
import javax.swing.JOptionPane;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author KIKE FRENCH
 */
public final class RegDosMetodos extends javax.swing.JDialog 
{
    private String ACCION;
    private String METODO;
    
    /** Creates new form RegClientes
     * @param parent
     * @param modal
     * @throws java.text.ParseException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException */
    public RegDosMetodos(java.awt.Frame parent, boolean modal) throws ParseException, ClassNotFoundException, SQLException {
        super(parent, modal);
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/prestamos.png")); 
        setIconImage(icon);
        this.Bar.setFloatable(false);
        setLocationRelativeTo(null);
        dteFDOC.setDate(G.hoy());
        G.atributosCalendario(dteFDOC);
        dteFDOC1.setDate(G.hoy());
        G.atributosCalendario(dteFDOC1);
        metodoPago(0);
        metodoPago(1);
    }
    
    public void accion(int IDPAGO,int IDCONTRATO, String ACCION) throws ParseException, Exception
    {
        /*this.ACCION =  ACCION;
        this.IDCONTRATO = IDCONTRATO;
        
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
        }*/
    }
    
    /*private void APOYO() throws SQLException, Exception
    {
        JInternalFrame ji = validador.getJInternalFrame(VApoyo.class.getName());
        if(ji != null )
            ji.dispose();
        ji = new VApoyo("ABONO",G.formato.format(dte_fecha.getDate()),""+idabono,"$ "+monto,descripcion,empleado);
        Principal.getCONT().add(ji);
        validador.addJIframe(VApoyo.class.getName(), ji);
        RegDosMetodos.btnCERRAR.doClick();
        this.dispose();
               
            ji.setMaximum(true);        
    }*/
    
    private void metodoPago(int campo) {
    String metodo = txtMETODO.getSelectedItem().toString();
    
    // Seleccionamos qué grupo de componentes usar
    if (campo == 0) {
        procesarLogicaPago(metodo, campo, dteFDOC, cmbORIGEN, cmbDESTINO, txtREF, btnADJUNTAR, lblARCHIVO);
    } else {
        procesarLogicaPago(metodo, campo, dteFDOC1, cmbORIGEN1, cmbDESTINO1, txtREF1, btnADJUNTAR1, lblARCHIVO1);
    }
}

// Método auxiliar para evitar duplicar la lógica de los SWITCH
private void procesarLogicaPago(String metodo, int campo, JDateChooser dte, JComboBox orig, JComboBox dest, JTextField ref, JButton btn, JLabel lbl) {
    
    // Reset por defecto (Opcional, ayuda a limpiar estados previos)
    dte.setVisible(true);
    orig.setVisible(true);
    dest.setVisible(true);
    btn.setVisible(true);
    lbl.setVisible(true);

    switch (metodo) {
        case "OXXO":
            dte.setDate(G.hoy());
            orig.setSelectedItem("OXXO");
            orig.setEnabled(false);
            dest.setSelectedItem("SPIN");
            dest.setEnabled(false);
            ref.setVisible(true);
            terminacion(campo);
            break;

        case "TRANSFERENCIA":
            dte.setDate(G.hoy());
            orig.setEnabled(true);
            dest.setEnabled(true);
            ref.setVisible(false);
            terminacion(campo);
            break;

        case "EFECTIVO":
        case "TARJETA":
            dte.setVisible(false);
            ref.setVisible(false);
            orig.setVisible(false);
            dest.setVisible(false);
            btn.setVisible(false);
            lbl.setVisible(false);
            if (metodo.equals("TARJETA")) terminacion(campo);
            break;
    }
}
    
    private void terminacion(int campo)
    {
        String CUENTA = cmbDESTINO.getSelectedItem().toString();
        if(CUENTA.equals("BBVA"))
            this.cmbDESTINO.setBorder(F.tituloBorde("Destino 0689 o 6897:", "Tahoma", 10, Color.BLUE));
            //this.lblTERMINACION.setText("0689 o 6897");
        else
            this.cmbDESTINO.setBorder(F.tituloBorde("Destino 8549:", "Tahoma", 10, Color.MAGENTA));
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
        Bar = new javax.swing.JToolBar();
        btnGUARDAR = new javax.swing.JButton();
        btnCANCELAR = new javax.swing.JButton();
        pnlDATOS = new javax.swing.JPanel();
        dteFDOC = new com.toedter.calendar.JDateChooser();
        cmbORIGEN = new javax.swing.JComboBox<>();
        cmbDESTINO = new javax.swing.JComboBox<>();
        btnADJUNTAR = new javax.swing.JButton();
        lblARCHIVO = new javax.swing.JLabel();
        txtREF = new javax.swing.JTextField();
        txtMETODO = new javax.swing.JComboBox<>();
        jpsCOMENTARIOS = new javax.swing.JScrollPane();
        txtCOMENTARIOS = new javax.swing.JTextPane();
        pnlDATOS1 = new javax.swing.JPanel();
        dteFDOC1 = new com.toedter.calendar.JDateChooser();
        cmbORIGEN1 = new javax.swing.JComboBox<>();
        cmbDESTINO1 = new javax.swing.JComboBox<>();
        btnADJUNTAR1 = new javax.swing.JButton();
        lblARCHIVO1 = new javax.swing.JLabel();
        txtREF1 = new javax.swing.JTextField();
        txtMETODO1 = new javax.swing.JComboBox<>();
        jpsCOMENTARIOS1 = new javax.swing.JScrollPane();
        txtCOMENTARIOS1 = new javax.swing.JTextPane();

        btnCERRAR.setText("jButton1");
        btnCERRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCERRARActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Pago en 2 metodos");
        setResizable(false);

        Bar.setRollover(true);

        btnGUARDAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disk.png"))); // NOI18N
        btnGUARDAR.setText("GUARDAR");
        btnGUARDAR.setFocusable(false);
        btnGUARDAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGUARDAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGUARDARActionPerformed(evt);
            }
        });
        Bar.add(btnGUARDAR);

        btnCANCELAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCANCELAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnCANCELAR.setText("CANCELAR");
        btnCANCELAR.setFocusable(false);
        btnCANCELAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCANCELAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCANCELAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCANCELARActionPerformed(evt);
            }
        });
        Bar.add(btnCANCELAR);

        pnlDATOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pagos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        dteFDOC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        dteFDOC.setToolTipText("Fecha de comprobante");
        dteFDOC.setDateFormatString("dd/MM/yyyy");
        dteFDOC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dteFDOC.setMaxSelectableDate(new java.util.Date(2524633297000L));
        dteFDOC.setMaximumSize(new java.awt.Dimension(50, 50));
        dteFDOC.setMinSelectableDate(new java.util.Date(1514790097000L));
        dteFDOC.setMinimumSize(new java.awt.Dimension(50, 50));
        dteFDOC.setPreferredSize(new java.awt.Dimension(50, 50));

        cmbORIGEN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbORIGEN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BAJIO", "BANAMEX", "BANCO AZTECA", "BANCOPPEL", "BANORTE", "BBVA", "HSBC", "INBURSA", "MERCADOPAGO", "SANTANDER", "SCOTIANK", "SPIN", "OXXO", "OTRO" }));
        cmbORIGEN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Origen:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10))); // NOI18N

        cmbDESTINO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbDESTINO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BBVA", "SPIN" }));
        cmbDESTINO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Destino 0689 o 6897:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10), java.awt.Color.blue)); // NOI18N
        cmbDESTINO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDESTINOItemStateChanged(evt);
            }
        });

        btnADJUNTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clip-de-papel.png"))); // NOI18N
        btnADJUNTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADJUNTARActionPerformed(evt);
            }
        });

        lblARCHIVO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblARCHIVO.setText("ARCHIVO");

        txtREF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtREF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtREF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Autorizacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtMETODO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMETODO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EFECTIVO", "TRANSFERENCIA", "TARJETA", "OXXO" }));
        txtMETODO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Metodo de Pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtMETODO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMETODOActionPerformed(evt);
            }
        });

        txtCOMENTARIOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comentarios:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtCOMENTARIOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCOMENTARIOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCOMENTARIOSKeyTyped(evt);
            }
        });
        jpsCOMENTARIOS.setViewportView(txtCOMENTARIOS);

        javax.swing.GroupLayout pnlDATOSLayout = new javax.swing.GroupLayout(pnlDATOS);
        pnlDATOS.setLayout(pnlDATOSLayout);
        pnlDATOSLayout.setHorizontalGroup(
            pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDATOSLayout.createSequentialGroup()
                .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDATOSLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpsCOMENTARIOS))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDATOSLayout.createSequentialGroup()
                        .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDATOSLayout.createSequentialGroup()
                                .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlDATOSLayout.createSequentialGroup()
                                        .addComponent(txtMETODO, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(dteFDOC, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(pnlDATOSLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cmbORIGEN, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbDESTINO, 0, 190, Short.MAX_VALUE)
                            .addComponent(txtREF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnADJUNTAR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblARCHIVO)))
                .addContainerGap())
        );
        pnlDATOSLayout.setVerticalGroup(
            pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDATOSLayout.createSequentialGroup()
                .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblARCHIVO)
                    .addGroup(pnlDATOSLayout.createSequentialGroup()
                        .addComponent(txtMETODO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dteFDOC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtREF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnADJUNTAR)
                            .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbORIGEN, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbDESTINO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpsCOMENTARIOS, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDATOS1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Complemento de Pago:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        dteFDOC1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        dteFDOC1.setToolTipText("Fecha de comprobante");
        dteFDOC1.setDateFormatString("dd/MM/yyyy");
        dteFDOC1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dteFDOC1.setMaxSelectableDate(new java.util.Date(2524633297000L));
        dteFDOC1.setMaximumSize(new java.awt.Dimension(50, 50));
        dteFDOC1.setMinSelectableDate(new java.util.Date(1514790097000L));
        dteFDOC1.setMinimumSize(new java.awt.Dimension(50, 50));
        dteFDOC1.setPreferredSize(new java.awt.Dimension(50, 50));

        cmbORIGEN1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbORIGEN1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BAJIO", "BANAMEX", "BANCO AZTECA", "BANCOPPEL", "BANORTE", "BBVA", "HSBC", "INBURSA", "MERCADOPAGO", "SANTANDER", "SCOTIANK", "SPIN", "OXXO", "OTRO" }));
        cmbORIGEN1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Origen:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10))); // NOI18N

        cmbDESTINO1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbDESTINO1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BBVA", "SPIN" }));
        cmbDESTINO1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Destino 0689 o 6897:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10), java.awt.Color.blue)); // NOI18N
        cmbDESTINO1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDESTINO1ItemStateChanged(evt);
            }
        });

        btnADJUNTAR1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clip-de-papel.png"))); // NOI18N
        btnADJUNTAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADJUNTAR1ActionPerformed(evt);
            }
        });

        lblARCHIVO1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblARCHIVO1.setText("ARCHIVO");

        txtREF1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtREF1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtREF1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Autorizacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtMETODO1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMETODO1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EFECTIVO", "TRANSFERENCIA", "TARJETA", "OXXO" }));
        txtMETODO1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Metodo de Pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtMETODO1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMETODO1ActionPerformed(evt);
            }
        });

        txtCOMENTARIOS1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comentarios:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtCOMENTARIOS1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCOMENTARIOS1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCOMENTARIOS1KeyTyped(evt);
            }
        });
        jpsCOMENTARIOS1.setViewportView(txtCOMENTARIOS1);

        javax.swing.GroupLayout pnlDATOS1Layout = new javax.swing.GroupLayout(pnlDATOS1);
        pnlDATOS1.setLayout(pnlDATOS1Layout);
        pnlDATOS1Layout.setHorizontalGroup(
            pnlDATOS1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDATOS1Layout.createSequentialGroup()
                .addGroup(pnlDATOS1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDATOS1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpsCOMENTARIOS1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDATOS1Layout.createSequentialGroup()
                        .addGroup(pnlDATOS1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMETODO1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dteFDOC1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDATOS1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cmbORIGEN1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlDATOS1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbDESTINO1, 0, 190, Short.MAX_VALUE)
                            .addComponent(txtREF1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnADJUNTAR1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblARCHIVO1)))
                .addContainerGap())
        );
        pnlDATOS1Layout.setVerticalGroup(
            pnlDATOS1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDATOS1Layout.createSequentialGroup()
                .addGroup(pnlDATOS1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblARCHIVO1)
                    .addGroup(pnlDATOS1Layout.createSequentialGroup()
                        .addComponent(txtMETODO1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDATOS1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dteFDOC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtREF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDATOS1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnADJUNTAR1)
                            .addGroup(pnlDATOS1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbORIGEN1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbDESTINO1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpsCOMENTARIOS1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Bar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDATOS1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(pnlDATOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlDATOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDATOS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnCANCELARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCANCELARActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCANCELARActionPerformed

    private void btnGUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGUARDARActionPerformed
        /*try {
            guardar(ACCION);
        } catch (Exception ex) {
            Logger.getLogger(RegDosMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }//GEN-LAST:event_btnGUARDARActionPerformed

    private void btnCERRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCERRARActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCERRARActionPerformed

    private void txtMETODOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMETODOActionPerformed
        metodoPago(0);
    }//GEN-LAST:event_txtMETODOActionPerformed

    private void cmbDESTINOItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDESTINOItemStateChanged
        terminacion(0);
    }//GEN-LAST:event_cmbDESTINOItemStateChanged

    private void btnADJUNTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADJUNTARActionPerformed
        /*try {
            lblARCHIVO.setText(F.selectarchivo("VOUCHER",IDCONTRATO+"-"+txtMETODO.getSelectedItem().toString()));
        } catch (IOException ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_btnADJUNTARActionPerformed

    private void txtCOMENTARIOSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCOMENTARIOSKeyTyped
        F.MAYUS(evt);
    }//GEN-LAST:event_txtCOMENTARIOSKeyTyped

    private void cmbDESTINO1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDESTINO1ItemStateChanged
        terminacion(1);
    }//GEN-LAST:event_cmbDESTINO1ItemStateChanged

    private void btnADJUNTAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADJUNTAR1ActionPerformed
        /*try {
            lblARCHIVO1.setText(F.selectarchivo("VOUCHER",IDCONTRATO+"-"+txtMETODO.getSelectedItem().toString()));
        } catch (IOException ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_btnADJUNTAR1ActionPerformed

    private void txtMETODO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMETODO1ActionPerformed
        metodoPago(1);
    }//GEN-LAST:event_txtMETODO1ActionPerformed

    private void txtCOMENTARIOS1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCOMENTARIOS1KeyTyped
        F.MAYUS(evt);
    }//GEN-LAST:event_txtCOMENTARIOS1KeyTyped

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
            java.util.logging.Logger.getLogger(RegDosMetodos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    RegDosMetodos dialog = new RegDosMetodos(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (ParseException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(RegDosMetodos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnADJUNTAR;
    private javax.swing.JButton btnADJUNTAR1;
    private javax.swing.JButton btnCANCELAR;
    public static javax.swing.JButton btnCERRAR;
    private javax.swing.JButton btnGUARDAR;
    private javax.swing.JComboBox<String> cmbDESTINO;
    private javax.swing.JComboBox<String> cmbDESTINO1;
    private javax.swing.JComboBox<String> cmbORIGEN;
    private javax.swing.JComboBox<String> cmbORIGEN1;
    private com.toedter.calendar.JDateChooser dteFDOC;
    private com.toedter.calendar.JDateChooser dteFDOC1;
    private javax.swing.JScrollPane jpsCOMENTARIOS;
    private javax.swing.JScrollPane jpsCOMENTARIOS1;
    private javax.swing.JLabel lblARCHIVO;
    private javax.swing.JLabel lblARCHIVO1;
    private javax.swing.JPanel pnlDATOS;
    private javax.swing.JPanel pnlDATOS1;
    private javax.swing.JTextPane txtCOMENTARIOS;
    private javax.swing.JTextPane txtCOMENTARIOS1;
    private javax.swing.JComboBox<String> txtMETODO;
    private javax.swing.JComboBox<String> txtMETODO1;
    private javax.swing.JTextField txtREF;
    private javax.swing.JTextField txtREF1;
    // End of variables declaration//GEN-END:variables
}
