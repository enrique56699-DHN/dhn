
/*
 * RegClientes.java
 *.
 * Created on 11-nov-2013, 18:18:42
 */
package VISTA;

import CONTROLADOR.ControlCarrito;
import CONTROLADOR.ControlEmpleados;
import CONTROLADOR.ControlOrdenesP;
import CONTROLADOR.ControlPendientes;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloCarrito;
import MODELO.ModeloCarritoTabla;
import MODELO.ModeloEmpleados;
import MODELO.ModeloEmpleadosTabla;
import MODELO.ModeloOrdenesP;
import MODELO.ModeloPendientes;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author KIKE FRENCH
 */
public final class RegOrdenesPD extends javax.swing.JDialog 
{
    private String CLIENTE, TEL, DIRECCION, DESCRIPCIONtmp, COMENTARIOStmp, FEST, SICAR,ESTADO;
    private int IDORDENP, IDPENDIENTE, IDCARRITO, IDEMPLEADO;
    private double TOTAL;
    private String rutaPDF;
        
    /** Creates new form RegClientes 
    * @param parent
     * @param modal
     * @throws java.text.ParseException
     * @throws java.sql.SQLException */
    public RegOrdenesPD(java.awt.Frame parent, boolean modal) throws ParseException, SQLException, Exception {
        super(parent, modal);
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/orden-de-trabajo.png")); 
        setIconImage(icon);
        setLocationRelativeTo(null);
        this.Bar1.setFloatable(false);
        this.Bar3.setFloatable(false);
        this.Bar4.setFloatable(false);
        this.Bar5.setFloatable(false);     
        this.Bar6.setFloatable(false);     
        this.btnTOOLS.setVisible(false);
        F.teclas(this);
        configurarAtajos();
        empleados();
    }
    
    private void configurarAtajos() {
        configurarAtajoBoton(btnADD, KeyEvent.VK_RIGHT, KeyEvent.CTRL_DOWN_MASK, null);
        configurarAtajoBoton(btnGUARDAR, KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK,null);
        configurarAtajoBoton(btnNEXT, KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK,null);
        configurarAtajoBoton(tgbtnSADMIN, KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK, null);
        configurarAtajoBoton(btnIMPRIMIR, KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK, null);
    }
    
    private void configurarAtajoBoton(AbstractButton boton, int keyCode, int modifiers, Runnable accion) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, modifiers);
        String accionNombre = "atajo_" + System.identityHashCode(boton);        
        boton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, accionNombre);
        boton.getActionMap().put(accionNombre, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boton.doClick();                
                // Si enviaste un Runnable (ej. tu lambda activar(true)), se ejecuta aquí
                if (accion != null) {
                    accion.run();
                }
                System.out.println("Atajo activado: " + boton.getText());
            }
        });
    }
    
    private void empleados() throws ClassNotFoundException, SQLException
    {
        ModeloEmpleadosTabla met = new ModeloEmpleadosTabla("ACTIVO","ASC");            
        cmbOFICIAL.removeAllItems();
        cmbAYUDANTE.removeAllItems();
        int NUM = met.getRowCount();
        for(int i=0;i<NUM;i++)
        {
            cmbOFICIAL.addItem(""+ met.getValueAt(i, 1));
            cmbAYUDANTE.addItem(""+ met.getValueAt(i, 1));
        }
        //this.txtDIRECCION.setEditable(false);
    }
        
    private void existe(String ARCHIVO, String RUTA)
    {               
        File f = new File(RUTA);
        if(f.exists())
        {
            switch(ARCHIVO)
            {
                case "PDF":
                    this.btnPDF.setEnabled(true);
                    break;                
                default: 
                    break;
            }
        }       
    }    
    
    private void APOYO() throws SQLException, Exception
    {
        this.TEL = lblTEL.getText();
        
        JInternalFrame ji = validador.getJInternalFrame(VApoyo.class.getName());
        if(ji != null )
            ji.dispose();
        int existe = F.total("ORDENESP", "IDORDENP", IDORDENP);

        if(existe>0)
        {
            Object[] inputFields = {"DESEAS REIMPRIMIR ORDEN DE TRABAJO?"};

            int option = JOptionPane.showConfirmDialog(this, inputFields, "Impresion de Orden de trabajo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (option == JOptionPane.OK_OPTION)                
                ji = new VApoyo("ORDENP",IDORDENP,IDPENDIENTE,CLIENTE,TEL,DIRECCION,txtDESCRIPCION.getText(),txtCOMENTARIOSS.getText(),cmbOFICIAL.getSelectedItem().toString(),cmbAYUDANTE.getSelectedItem().toString());
            else
                this.dispose();             
        }
        else
        {
            Object[] inputFields = {"DESEA CREAR UNA ORDEN NUEVA?"};

            int option = JOptionPane.showConfirmDialog(this, inputFields, "Impresion de Orden de trabajo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (option == JOptionPane.OK_OPTION)
            {
                IDORDENP = F.Registro("ORDENESP", "IDORDENP", "DESC");
                ji = new VApoyo("ORDENPN",IDORDENP,IDPENDIENTE,CLIENTE,TEL,DIRECCION,txtDESCRIPCION.getText(),txtCOMENTARIOSS.getText(),cmbOFICIAL.getSelectedItem().toString(),cmbAYUDANTE.getSelectedItem().toString());
            }
            else
                this.dispose();
        }

        Principal.getCONT().add(ji);
        validador.addJIframe(VApoyo.class.getName(), ji);                   

        this.dispose();
            
        ji.setMaximum(true); 

    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnACTUALIZAR = new javax.swing.JButton();
        btnCERRAR = new javax.swing.JButton();
        pnlORDEN = new javax.swing.JPanel();
        lblTECNICO = new javax.swing.JLabel();
        btnIMPRIMIR = new javax.swing.JButton();
        cmbOFICIAL = new javax.swing.JComboBox<>();
        cmbAYUDANTE = new javax.swing.JComboBox<>();
        pnlSERVICIO = new javax.swing.JPanel();
        lblIDPENDIENTE = new javax.swing.JLabel();
        txtFACTURA = new javax.swing.JTextField();
        txtTICKET = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDESCRIPCION = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtCOMENTARIOSS = new javax.swing.JTextArea();
        lblREG = new javax.swing.JLabel();
        lblFEST = new javax.swing.JLabel();
        pnlCLIENTE = new javax.swing.JPanel();
        lblCLIENTE = new javax.swing.JLabel();
        lblDIRECCION = new javax.swing.JLabel();
        lblTEL = new javax.swing.JLabel();
        Bar1 = new javax.swing.JToolBar();
        tgbtnSADMIN = new javax.swing.JToggleButton();
        btnGUARDAR = new javax.swing.JButton();
        btnNEXT = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();
        btnBITACORA = new javax.swing.JButton();
        btnCOTIZACIONES = new javax.swing.JButton();
        btnTOOLS = new javax.swing.JButton();
        pnlCARRITO = new javax.swing.JPanel();
        jspTABLA = new javax.swing.JScrollPane();
        tblTABLA = new javax.swing.JTable();
        Bar3 = new javax.swing.JToolBar();
        btnADD = new javax.swing.JButton();
        btnDELETE = new javax.swing.JButton();
        btnEDITAR = new javax.swing.JButton();
        btnCAMBIAR = new javax.swing.JButton();
        btnLLENAR = new javax.swing.JButton();
        bntIMPCAR = new javax.swing.JButton();
        bntCOPIAR = new javax.swing.JButton();
        lblTOTAL = new javax.swing.JLabel();
        Bar4 = new javax.swing.JToolBar();
        btnENTREGA1 = new javax.swing.JButton();
        btnDEVOLVER1 = new javax.swing.JButton();
        btnREQUERIDO = new javax.swing.JButton();
        btnDEVOLUCIONT = new javax.swing.JButton();
        btnENTREGAT = new javax.swing.JButton();
        Bar5 = new javax.swing.JToolBar();
        btnADJUNTAR = new javax.swing.JButton();
        btnGARANTIAS = new javax.swing.JButton();
        Bar6 = new javax.swing.JToolBar();
        btnCAP = new javax.swing.JButton();
        btnGARANTIAS1 = new javax.swing.JButton();

        btnACTUALIZAR.setText("jButton1");
        btnACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnACTUALIZARActionPerformed(evt);
            }
        });

        btnCERRAR.setText("Cerrar");
        btnCERRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCERRARActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Historial de Cliente");
        setResizable(false);

        pnlORDEN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Orden", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTECNICO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTECNICO.setText("TECNICO");
        lblTECNICO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tecnico Actual:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        btnIMPRIMIR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnIMPRIMIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer.png"))); // NOI18N
        btnIMPRIMIR.setToolTipText("Imprimir Orden");
        btnIMPRIMIR.setEnabled(false);
        btnIMPRIMIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIMPRIMIRActionPerformed(evt);
            }
        });

        cmbOFICIAL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbOFICIAL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbOFICIAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tecnico Oficial:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        cmbOFICIAL.setEnabled(false);

        cmbAYUDANTE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbAYUDANTE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbAYUDANTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tecnico Ayudante:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        cmbAYUDANTE.setEnabled(false);

        javax.swing.GroupLayout pnlORDENLayout = new javax.swing.GroupLayout(pnlORDEN);
        pnlORDEN.setLayout(pnlORDENLayout);
        pnlORDENLayout.setHorizontalGroup(
            pnlORDENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlORDENLayout.createSequentialGroup()
                .addComponent(btnIMPRIMIR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlORDENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTECNICO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlORDENLayout.createSequentialGroup()
                        .addComponent(cmbOFICIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbAYUDANTE, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlORDENLayout.setVerticalGroup(
            pnlORDENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlORDENLayout.createSequentialGroup()
                .addComponent(lblTECNICO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlORDENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbAYUDANTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIMPRIMIR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbOFICIAL)))
        );

        pnlSERVICIO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Servicio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        pnlSERVICIO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblIDPENDIENTE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblIDPENDIENTE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIDPENDIENTE.setText("#");
        lblIDPENDIENTE.setToolTipText("");
        lblIDPENDIENTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "#:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        txtFACTURA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFACTURA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFACTURA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Factura:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtFACTURA.setEnabled(false);
        txtFACTURA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFACTURAKeyTyped(evt);
            }
        });

        txtTICKET.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTICKET.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTICKET.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ticket:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtTICKET.setEnabled(false);
        txtTICKET.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTICKETKeyTyped(evt);
            }
        });

        txtDESCRIPCION.setColumns(20);
        txtDESCRIPCION.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtDESCRIPCION.setLineWrap(true);
        txtDESCRIPCION.setRows(5);
        txtDESCRIPCION.setWrapStyleWord(true);
        txtDESCRIPCION.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripcion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtDESCRIPCION.setEnabled(false);
        txtDESCRIPCION.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDESCRIPCIONKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(txtDESCRIPCION);

        txtCOMENTARIOSS.setColumns(20);
        txtCOMENTARIOSS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtCOMENTARIOSS.setLineWrap(true);
        txtCOMENTARIOSS.setRows(5);
        txtCOMENTARIOSS.setWrapStyleWord(true);
        txtCOMENTARIOSS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comentarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtCOMENTARIOSS.setEnabled(false);
        txtCOMENTARIOSS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCOMENTARIOSSKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(txtCOMENTARIOSS);

        lblREG.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblREG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblREG.setText("00/00/0000");
        lblREG.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha Registro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblFEST.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFEST.setForeground(new java.awt.Color(0, 0, 204));
        lblFEST.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFEST.setText("00/00/0000");
        lblFEST.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha Estimada", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout pnlSERVICIOLayout = new javax.swing.GroupLayout(pnlSERVICIO);
        pnlSERVICIO.setLayout(pnlSERVICIOLayout);
        pnlSERVICIOLayout.setHorizontalGroup(
            pnlSERVICIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSERVICIOLayout.createSequentialGroup()
                .addComponent(lblIDPENDIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblREG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTICKET, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFACTURA, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFEST, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlSERVICIOLayout.setVerticalGroup(
            pnlSERVICIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSERVICIOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSERVICIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDPENDIENTE)
                    .addComponent(txtFACTURA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTICKET, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblREG)
                    .addComponent(lblFEST))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCLIENTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblCLIENTE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCLIENTE.setText("NOMBRE DE CLIENTE");
        lblCLIENTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblDIRECCION.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDIRECCION.setText("DIRECCION DEL SERVICIO");
        lblDIRECCION.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Direccion:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblTEL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTEL.setText("0000000000");
        lblTEL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Telefono", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout pnlCLIENTELayout = new javax.swing.GroupLayout(pnlCLIENTE);
        pnlCLIENTE.setLayout(pnlCLIENTELayout);
        pnlCLIENTELayout.setHorizontalGroup(
            pnlCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCLIENTELayout.createSequentialGroup()
                .addGroup(pnlCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblDIRECCION, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCLIENTELayout.createSequentialGroup()
                        .addComponent(lblCLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTEL, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlCLIENTELayout.setVerticalGroup(
            pnlCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCLIENTELayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCLIENTE, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTEL, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDIRECCION))
        );

        Bar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Bar1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tgbtnSADMIN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tgbtnSADMIN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pass.png"))); // NOI18N
        tgbtnSADMIN.setToolTipText("PRIVILEGIOS");
        tgbtnSADMIN.setFocusable(false);
        tgbtnSADMIN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tgbtnSADMIN.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tgbtnSADMIN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgbtnSADMINItemStateChanged(evt);
            }
        });
        Bar1.add(tgbtnSADMIN);

        btnGUARDAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disk.png"))); // NOI18N
        btnGUARDAR.setToolTipText("GUARDAR");
        btnGUARDAR.setEnabled(false);
        btnGUARDAR.setFocusable(false);
        btnGUARDAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGUARDAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGUARDARActionPerformed(evt);
            }
        });
        Bar1.add(btnGUARDAR);

        btnNEXT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNEXT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/next_green.png"))); // NOI18N
        btnNEXT.setToolTipText("Reagendar");
        btnNEXT.setFocusable(false);
        btnNEXT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNEXT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNEXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNEXTActionPerformed(evt);
            }
        });
        Bar1.add(btnNEXT);

        btnPDF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cuentas1.png"))); // NOI18N
        btnPDF.setToolTipText("Abrir Venta Generada");
        btnPDF.setEnabled(false);
        btnPDF.setFocusable(false);
        btnPDF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPDF.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });
        Bar1.add(btnPDF);

        btnBITACORA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBITACORA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bitacora.png"))); // NOI18N
        btnBITACORA.setToolTipText("Bitacora de movimeintos");
        btnBITACORA.setFocusable(false);
        btnBITACORA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBITACORA.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBITACORA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBITACORAActionPerformed(evt);
            }
        });
        Bar1.add(btnBITACORA);

        btnCOTIZACIONES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCOTIZACIONES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cotizacion.png"))); // NOI18N
        btnCOTIZACIONES.setToolTipText("Agregar Cotizacion");
        btnCOTIZACIONES.setEnabled(false);
        btnCOTIZACIONES.setFocusable(false);
        btnCOTIZACIONES.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCOTIZACIONES.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCOTIZACIONES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCOTIZACIONESActionPerformed(evt);
            }
        });
        Bar1.add(btnCOTIZACIONES);

        btnTOOLS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTOOLS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/activar.png"))); // NOI18N
        btnTOOLS.setToolTipText("Configurar UBNT");
        btnTOOLS.setFocusable(false);
        btnTOOLS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTOOLS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTOOLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTOOLSActionPerformed(evt);
            }
        });
        Bar1.add(btnTOOLS);

        pnlCARRITO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Materiales y  Consumibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        pnlCARRITO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jspTABLA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jspTABLA.setEnabled(false);
        jspTABLA.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        tblTABLA.setAutoCreateRowSorter(true);
        tblTABLA.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tblTABLA.setEnabled(false);
        tblTABLA.setName("tblTABLA"); // NOI18N
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

        Bar3.setRollover(true);

        btnADD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_add.png"))); // NOI18N
        btnADD.setToolTipText("Agregar al Carrito");
        btnADD.setEnabled(false);
        btnADD.setFocusable(false);
        btnADD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnADD.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDActionPerformed(evt);
            }
        });
        Bar3.add(btnADD);

        btnDELETE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDELETE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_delete.png"))); // NOI18N
        btnDELETE.setToolTipText("Eliminar del carrito");
        btnDELETE.setEnabled(false);
        btnDELETE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDELETE.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDELETEActionPerformed(evt);
            }
        });
        Bar3.add(btnDELETE);

        btnEDITAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit.png"))); // NOI18N
        btnEDITAR.setToolTipText("Editar Articulo");
        btnEDITAR.setEnabled(false);
        btnEDITAR.setFocusable(false);
        btnEDITAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEDITAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEDITAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEDITARActionPerformed(evt);
            }
        });
        Bar3.add(btnEDITAR);

        btnCAMBIAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cambio2.png"))); // NOI18N
        btnCAMBIAR.setToolTipText("Cambio de Servicio");
        btnCAMBIAR.setEnabled(false);
        btnCAMBIAR.setFocusable(false);
        btnCAMBIAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCAMBIAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCAMBIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCAMBIARActionPerformed(evt);
            }
        });
        Bar3.add(btnCAMBIAR);

        btnLLENAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/produtos.png"))); // NOI18N
        btnLLENAR.setToolTipText("Llenar carrito");
        btnLLENAR.setEnabled(false);
        btnLLENAR.setFocusable(false);
        btnLLENAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLLENAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLLENAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLLENARActionPerformed(evt);
            }
        });
        Bar3.add(btnLLENAR);

        bntIMPCAR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntIMPCAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer.png"))); // NOI18N
        bntIMPCAR.setToolTipText("Imprimir");
        bntIMPCAR.setEnabled(false);
        bntIMPCAR.setFocusable(false);
        bntIMPCAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntIMPCAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Bar3.add(bntIMPCAR);

        bntCOPIAR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntCOPIAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clipboard.png"))); // NOI18N
        bntCOPIAR.setToolTipText("Copiar Clave SICAR");
        bntCOPIAR.setEnabled(false);
        bntCOPIAR.setFocusable(false);
        bntCOPIAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntCOPIAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntCOPIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCOPIARActionPerformed(evt);
            }
        });
        Bar3.add(bntCOPIAR);

        lblTOTAL.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTOTAL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTOTAL.setText("0.0");
        lblTOTAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        Bar4.setRollover(true);

        btnENTREGA1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnENTREGA1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/OK-T.png"))); // NOI18N
        btnENTREGA1.setToolTipText("Entregar Todos");
        btnENTREGA1.setEnabled(false);
        btnENTREGA1.setFocusable(false);
        btnENTREGA1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnENTREGA1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnENTREGA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnENTREGA1ActionPerformed(evt);
            }
        });
        Bar4.add(btnENTREGA1);

        btnDEVOLVER1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/-.png"))); // NOI18N
        btnDEVOLVER1.setToolTipText("Devolver Producto");
        btnDEVOLVER1.setEnabled(false);
        btnDEVOLVER1.setFocusable(false);
        btnDEVOLVER1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDEVOLVER1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDEVOLVER1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDEVOLVER1ActionPerformed(evt);
            }
        });
        Bar4.add(btnDEVOLVER1);

        btnREQUERIDO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/out-of-stock.png"))); // NOI18N
        btnREQUERIDO.setToolTipText("Marcar como REQUERIDO");
        btnREQUERIDO.setEnabled(false);
        btnREQUERIDO.setFocusable(false);
        btnREQUERIDO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnREQUERIDO.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnREQUERIDO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnREQUERIDOActionPerformed(evt);
            }
        });
        Bar4.add(btnREQUERIDO);

        btnDEVOLUCIONT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDEVOLUCIONT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/almacen.png"))); // NOI18N
        btnDEVOLUCIONT.setToolTipText("Marcar como DEVUELTO");
        btnDEVOLUCIONT.setEnabled(false);
        btnDEVOLUCIONT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDEVOLUCIONT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDEVOLUCIONT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDEVOLUCIONTActionPerformed(evt);
            }
        });
        Bar4.add(btnDEVOLUCIONT);

        btnENTREGAT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnENTREGAT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/entrega.png"))); // NOI18N
        btnENTREGAT.setToolTipText("Marcar como Entregado");
        btnENTREGAT.setEnabled(false);
        btnENTREGAT.setFocusable(false);
        btnENTREGAT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnENTREGAT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnENTREGAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnENTREGATActionPerformed(evt);
            }
        });
        Bar4.add(btnENTREGAT);

        javax.swing.GroupLayout pnlCARRITOLayout = new javax.swing.GroupLayout(pnlCARRITO);
        pnlCARRITO.setLayout(pnlCARRITOLayout);
        pnlCARRITOLayout.setHorizontalGroup(
            pnlCARRITOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCARRITOLayout.createSequentialGroup()
                .addGroup(pnlCARRITOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCARRITOLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
                    .addComponent(lblTOTAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCARRITOLayout.createSequentialGroup()
                        .addComponent(Bar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Bar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlCARRITOLayout.setVerticalGroup(
            pnlCARRITOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCARRITOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCARRITOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Bar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTOTAL)
                .addContainerGap())
        );

        Bar5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adjuntar Evidencia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        Bar5.setRollover(true);

        btnADJUNTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clip-de-papel.png"))); // NOI18N
        btnADJUNTAR.setToolTipText("Adjuntar Evidencia Fotografica");
        btnADJUNTAR.setEnabled(false);
        btnADJUNTAR.setFocusable(false);
        btnADJUNTAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnADJUNTAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnADJUNTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADJUNTARActionPerformed(evt);
            }
        });
        Bar5.add(btnADJUNTAR);

        btnGARANTIAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnGARANTIAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/garantias.png"))); // NOI18N
        btnGARANTIAS.setToolTipText("Producto en Garantia");
        btnGARANTIAS.setEnabled(false);
        btnGARANTIAS.setFocusable(false);
        btnGARANTIAS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGARANTIAS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGARANTIAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGARANTIASActionPerformed(evt);
            }
        });
        Bar5.add(btnGARANTIAS);

        Bar6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Proveedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        btnCAP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pagoprovedores.png"))); // NOI18N
        btnCAP.setToolTipText("Cargos Y Abonos de Proveedores");
        btnCAP.setEnabled(false);
        btnCAP.setFocusable(false);
        btnCAP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCAP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCAPActionPerformed(evt);
            }
        });
        Bar6.add(btnCAP);

        btnGARANTIAS1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnGARANTIAS1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/garantias.png"))); // NOI18N
        btnGARANTIAS1.setToolTipText("Producto en Garantia");
        btnGARANTIAS1.setEnabled(false);
        btnGARANTIAS1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGARANTIAS1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGARANTIAS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGARANTIAS1ActionPerformed(evt);
            }
        });
        Bar6.add(btnGARANTIAS1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Bar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlCLIENTE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSERVICIO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlORDEN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Bar5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Bar6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCARRITO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlCARRITO, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(pnlCLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSERVICIO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Bar5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Bar6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlORDEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Bar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opciones(String OPCION) throws SQLException, Exception
    {
        switch(OPCION)
        {
            case "DEVUELTO":
                marcar(OPCION);
                break;
            case "ENTREGADO":
                marcar(OPCION);
                break;
            case "BORRAR":
                borrar();
                break;
            case "EDITAR":
                seleccion();
                carritoAccion(OPCION);
                break;
            case "CAMBIAR":
                seleccion();
                cambiar();
                break;
            default: 
                break;
        }
    }
    
    private void cambiar() throws SQLException, Exception
    {        
        String IDPENDIENTEN = JOptionPane.showInputDialog(null,"Numero de Servicio","Mover articulo",1);
        ControlCarrito ctrl = new ControlCarrito();
        ctrl.actualizarSERVICIO(IDCARRITO, Integer.parseInt(IDPENDIENTEN));        
    }
    
    private void borrar()
    {
        if(Principal.PRIVILEGIOS.equals("SADMIN"))
        {
            int eleccion = JOptionPane.showConfirmDialog(null, "Realmente Desea Eliminar el registro?");
            if ( eleccion == 0)
            {
                try 
                {
                    seleccion();
                    ModeloCarrito mdl = new ModeloCarrito();
                    mdl.setIDCARRITO(IDCARRITO);
                    ControlCarrito ctrl = new ControlCarrito();
                    ctrl.eliminarRegistro(mdl);
                    actualizarTabla(IDPENDIENTE);
                } catch (SQLException ex) {
                    Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void mostrar(int IDPENDIENTE,int IDORDENP) throws ParseException, Exception
    {
        ModeloPendientes mdlp;
        ControlPendientes ctrlp = new ControlPendientes();
        if(IDPENDIENTE == 0)        
            mdlp=ctrlp.getRegistroID(IDORDENP);        
        else
            mdlp=ctrlp.getRegistroID(IDPENDIENTE);
        
        this.lblTEL.setText(mdlp.getTEL1());        
        this.CLIENTE = mdlp.getNOMBRE();
        this.lblCLIENTE.setText(CLIENTE);
        this.DIRECCION = mdlp.getCALLE()+" "+mdlp.getNUMERO()+" "+mdlp.getCOLONIA()+" "+mdlp.getLOCALIDAD();
        this.lblDIRECCION.setText(DIRECCION);
        this.IDPENDIENTE = IDPENDIENTE;
        this.IDORDENP = IDORDENP;
        this.lblIDPENDIENTE.setText(""+IDPENDIENTE);
        this.txtTICKET.setText(mdlp.getNPEDIDO());
        this.txtFACTURA.setText(mdlp.getNPEDIDOF());
        //this.lblESTADO.setText(mdlp.getESTADO());
        ESTADO = mdlp.getESTADO();
        this.COMENTARIOStmp = mdlp.getCOMENTARIOS();
        this.txtCOMENTARIOSS.setText(mdlp.getCOMENTARIOS());
        this.DESCRIPCIONtmp = mdlp.getDESCRIPCION();
        this.txtDESCRIPCION.setText(mdlp.getDESCRIPCION());
        this.lblFEST.setText(mdlp.getFEST());
        FEST=mdlp.getFEST();
        this.lblREG.setText(mdlp.getFREG());
        
        if(IDORDENP>0)
        {
            ModeloOrdenesP pto;
            ControlOrdenesP cop = new ControlOrdenesP();
            pto=cop.getRegistrosID(IDORDENP);
            this.IDEMPLEADO = pto.getIDEMPLEADO();
            
            ModeloEmpleados mdle;
            ControlEmpleados ctrle = new ControlEmpleados();
            mdle=ctrle.getRegistrosID(IDEMPLEADO);
            this.lblTECNICO.setText(mdle.getNOMBRE()+" "+mdle.getAPELLIDOS());
            
            //this.btnIMPRIMIR.setEnabled(true);
        }  
        /*this.lblRA.setText(""+F.totalREAGENDADOS("bitacora", "bitacora.TABLA", "PENDIENTES", 
                "bitacora.ACCION", "REAGENDAR", "REGISTRO", IDPENDIENTE));*/
        actualizarTabla(IDPENDIENTE);
        rutaPDF = "\\"+"\\" +G.ip +"\\dhn\\VENTAS\\"+IDPENDIENTE+".pdf";
        existe("PDF",rutaPDF);
        
        this.btnADJUNTAR.setBorder(F.tituloBorde(""+F.total("PDOCS", "IDPENDIENTE", IDPENDIENTE), "Tahoma", 12, Color.BLUE));
        this.btnCOTIZACIONES.setBorder(F.tituloBorde(""+F.total("COTIZACIONES", "IDPENDIENTE", IDPENDIENTE), "Tahoma", 12, Color.BLUE));
        this.btnNEXT.setBorder(F.tituloBorde(""+F.totalREAGENDADOS("bitacora", "bitacora.TABLA", "PENDIENTES", 
                "bitacora.ACCION", "REAGENDAR", "REGISTRO", IDPENDIENTE), "Tahoma", 12, Color.BLUE));
        
        this.pnlSERVICIO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Servicio / "+ESTADO, 
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("Tahoma", 1, 10)));
        
        //activamos o desactivamos el boton de tools
        if(txtTICKET.getText().contains("DHN"))
            if(Principal.PRIVILEGIOS.equals("SADMIN"))
                btnTOOLS.setVisible(true);
    }
    
    private void actualizarTabla(int IDPENDIENTE) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloCarritoTabla mtpn;
        mtpn = new ModeloCarritoTabla (IDPENDIENTE);
        tblTABLA.setModel(mtpn);
        F.dimensionar(tblTABLA,"CARRITOPD");
        total();
    }
    
    private void seleccion() throws ClassNotFoundException, SQLException, Exception
    {        
        int fila = tblTABLA.getSelectedRow();
        IDCARRITO = (int)tblTABLA.getValueAt(fila, 0);
        SICAR = tblTABLA.getValueAt(fila, 2).toString();
    }
    
    private void marcar(String ESTADO) throws ClassNotFoundException, SQLException, Exception
    {
        int eleccion = 5;
        switch(ESTADO)
        {
            case "ENTREGADO":
                eleccion = JOptionPane.showConfirmDialog(null, "SE ENTREGO TODO?");
                break;
            case "DEVUELTO":
                eleccion = JOptionPane.showConfirmDialog(null, "DESEAS DEVOLVER TODO");
                break;
            default: 
                break;
        }       
        if ( eleccion == 0)
        {
            if(IDEMPLEADO != 0)
            {
                for (int i = 0; i<tblTABLA.getRowCount(); i++)
                {      
                    ControlCarrito ctrl;
                    ctrl = new ControlCarrito();
                    IDCARRITO = (int)tblTABLA.getValueAt(i, 0);
                    ctrl.actualizarESTADO(IDCARRITO,ESTADO,G.FECHA(G.hoy(),false), IDEMPLEADO);     
                    //Funciones.bitacora("CARRITO",IDCARRITO,ESTADO,G.IDUSER,"DEVOLVIO DEL SERVICIO NUMERO: "+IDPENDIENTE,"EMPLEADO NO. "+IDEMPLEADO);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "No se cuenta con un empleado a quien asignar", "Error", JOptionPane.ERROR_MESSAGE);
        }        
    }
    
    private void marcar1(String ESTADO) throws ClassNotFoundException, SQLException, Exception
    {
        int eleccion = JOptionPane.showConfirmDialog(null, "MARCAR ESTE PRODUCTO COMO: "+ESTADO+"?");
        if ( eleccion == 0)
        {
            if(IDEMPLEADO != 0)
            {
                //String FECHA = formateador.format(hoy);
                int fila = tblTABLA.getSelectedRow();
                      
                ControlCarrito ctrl;
                ctrl = new ControlCarrito();
                
                IDCARRITO = (int)tblTABLA.getValueAt(fila, 0);
                ctrl.actualizarESTADO(IDCARRITO,ESTADO,G.FECHA(G.hoy(),false), IDEMPLEADO);                   
                
            }
            else
                JOptionPane.showMessageDialog(null, "No se cuenta con un empleado a quien asignar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Funciones.bitacora("CARRITO",IDCARRITO,ESTADO,G.IDUSER,"DEVOLVIO DEL SERVICIO NUMERO: "+IDPENDIENTE,"");
    
    }
    
    public void total() throws ClassNotFoundException, SQLException, Exception
    {
         ControlCarrito ctrl = new ControlCarrito();
         TOTAL = ctrl.total(IDPENDIENTE);
         this.lblTOTAL.setText(""+TOTAL);
    }
    
    private void activar(boolean ACCION)
    {
        this.btnGUARDAR.setEnabled(ACCION);
        if(Principal.PRIVILEGIOS.equals("SADMIN") || (Principal.PRIVILEGIOS.equals("ADMIN")) )
        {
            this.btnDEVOLUCIONT.setEnabled(ACCION);
            this.btnENTREGAT.setEnabled(ACCION);
            this.btnENTREGA1.setEnabled(ACCION);
            this.btnIMPRIMIR.setEnabled(ACCION);
            //this.txtCOMENTARIOS.setEnabled(ACCION);            
            this.btnDEVOLVER1.setEnabled(ACCION);
            this.btnREQUERIDO.setEnabled(ACCION);
            this.btnEDITAR.setEnabled(ACCION);
            this.btnCAMBIAR.setEnabled(ACCION);
            this.btnLLENAR.setEnabled(ACCION);
            this.cmbOFICIAL.setEnabled(ACCION);
            this.cmbAYUDANTE.setEnabled(ACCION);
        }        
        
        this.txtDESCRIPCION.setEnabled(ACCION);        
        this.txtCOMENTARIOSS.setEnabled(ACCION);        
        this.txtFACTURA.setEnabled(ACCION);
        this.txtTICKET.setEnabled(ACCION);
        //this.btnAGENDAR.setEnabled(ACCION);
         
        this.btnADD.setEnabled(ACCION);
        this.btnDELETE.setEnabled(ACCION);
        this.bntIMPCAR.setEnabled(ACCION);
        this.bntCOPIAR.setEnabled(ACCION);
        this.btnCOTIZACIONES.setEnabled(ACCION);
        
        this.jspTABLA.setEnabled(ACCION);
        this.tblTABLA.setEnabled(ACCION);
        
        this.btnADJUNTAR.setEnabled(ACCION);
        this.btnCAP.setEnabled(ACCION);
        /*if(IDORDENP!=0)
        {
            this.txtCOMENTARIOS.setEnabled(ACCION);
            this.spCARRITO.setEnabled(ACCION);
            this.tblCARRITO.setEnabled(ACCION);
            this.btnADD.setEnabled(ACCION);
            this.btnDELETE.setEnabled(ACCION);
            this.bntIMPCAR.setEnabled(ACCION);
        }*/
       
    }
    
    private void guardar() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloPendientes mdlP = new ModeloPendientes();
        
        mdlP.setIDPENDIENTE(IDPENDIENTE);
        mdlP.setNPEDIDO(txtTICKET.getText());
        mdlP.setNPEDIDOF(txtFACTURA.getText());
        mdlP.setCOMENTARIOS(txtCOMENTARIOSS.getText());
        mdlP.setDESCRIPCION(txtDESCRIPCION.getText());
        
        ControlPendientes ctrlP = new ControlPendientes();
        ctrlP.actualizar(mdlP);
        
        if(IDORDENP>0)
        {
            //COMENTARIOS = txtCOMENTARIOS.getText();
            ControlOrdenesP ctrlO = new ControlOrdenesP();
            ctrlO.actualizar(IDORDENP,"");
        }
        //F.bitacora("PENDIENTES",IDCARRITO,"CAMBIAR DE SERVICIO",G.IDUSER,"CAMBIAR PRODUCTO DE NUMERO DE SERVICIO: "+IDPENDIENTE+" AL "+IDPENDIENTEN,"EMPLEADO NO. "+IDEMPLEADO);
        if(!DESCRIPCIONtmp.equals(txtDESCRIPCION.getText()))
            F.bitacora("PENDIENTES",IDPENDIENTE,"EDITAR",G.IDUSER,DESCRIPCIONtmp,txtDESCRIPCION.getText());
        
        if(!COMENTARIOStmp.equals(txtCOMENTARIOSS.getText()))
            F.bitacora("PENDIENTES",IDPENDIENTE,"EDITAR",G.IDUSER,COMENTARIOStmp,txtCOMENTARIOSS.getText());        
        
        AcualizarEstados();
        this.tgbtnSADMIN.doClick();
        //activar(false);
    }
    
    private void agendar(String ACCION) throws ParseException, SQLException, Exception
    {
        Agendar reg;
        reg = new Agendar(null, true);
        reg.mostrar(IDPENDIENTE, IDORDENP, ACCION);
        reg.setVisible(true);
    }
    
    private void AcualizarEstados() throws ParseException, SQLException, Exception
    {
        ActualizaEstados reg;            
        reg = new ActualizaEstados(null,true);
        reg.mostrar(IDPENDIENTE,IDORDENP);
        reg.setVisible(true);
    }
    
    private void carritoAccion(String ACCION) throws SQLException, ParseException, ClassNotFoundException, Exception
    {        
        //if(tgbtnSADMIN.isSelected())
        //{
            RegArticuloCarrito producto;
            producto = new RegArticuloCarrito(null,true);
            
            if(ACCION.equals("EDITAR"))        
                IDPENDIENTE = IDCARRITO;        

            producto.mostrar(IDPENDIENTE, ACCION, IDEMPLEADO, FEST);
            producto.setVisible(true);  
            
            
        //}            
        //actualizarTabla(IDPENDIENTE);
    }
    
    private void devolver(String ACCION) throws ParseException, SQLException, Exception
    {
        //seleccion();
        MarcarUSADOS reg;
        reg = new MarcarUSADOS(null, true);
        reg.mostrar(IDCARRITO, ACCION);
        reg.setVisible(true);
    }
    
    private void prellenar(String ACCION) throws SQLException, ParseException, ClassNotFoundException, Exception
    {
        SelectKit producto;            
        producto = new SelectKit(null,true);
        producto.mostrar(IDPENDIENTE, ACCION, IDEMPLEADO, FEST);
        producto.setVisible(true);  
    }
    
    private void cotizaciones() throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        ViewCotizaciones jd;
        jd = new ViewCotizaciones(null,true);
        jd.mostrar("IDPENDIENTE",Integer.parseInt(lblIDPENDIENTE.getText()));
        jd.setVisible(true);
    }

    private void pdocs(int IDEMPLEADO) throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        ViewPDocs jd;
        jd = new ViewPDocs(null,true);
        jd.mostrar("IDPENDIENTE",Integer.parseInt(lblIDPENDIENTE.getText()), IDEMPLEADO);
        jd.setVisible(true);
    }
    
    private void MOTIVOS(String ACCION) throws ParseException, SQLException, Exception
    {        
        RegMotivosRP reg;
        reg = new RegMotivosRP(null, true);
        reg.mostrar( IDPENDIENTE, ACCION,FEST);
        reg.setVisible(true);
    }
    
    /*private void pregunta() throws SQLException, Exception
    {
        Object[] inputFields = {"¿REAGENDAR SERVICIO?"};
        int option = JOptionPane.showConfirmDialog(this, inputFields, "Reagendar servicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (option == JOptionPane.OK_OPTION)        
            reagendar();        
    }*/
    
    private void cfgubnt(String ACCION) throws ClassNotFoundException, SQLException, Exception
    {
        
        String []datos=txtTICKET.getText().split("-");
        int IDCONTRATO = Integer.parseInt(datos [2]);
        System.out.println(IDCONTRATO);
        
        Tools cfg; 
        cfg = new Tools(null,true);
        cfg.mostrar(IDCONTRATO,ACCION,"");
        cfg.setVisible(true);
    }
    
    private void btnIMPRIMIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIMPRIMIRActionPerformed
        try {
            agendar("IMPORDEN");
            APOYO();
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_btnIMPRIMIRActionPerformed

    private void btnGUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGUARDARActionPerformed
        try {
            guardar();
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGUARDARActionPerformed

    private void tblTABLAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMouseClicked
        /*try {
            // TODO add your handling code here:
            seleccion(); 
       } catch (SQLException ex) {
            Logger.getLogger(VRegCarrito.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRegCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_tblTABLAMouseClicked

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed
        try {
            carritoAccion("NUEVO");
            actualizarTabla(IDPENDIENTE);
        } catch (ParseException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnADDActionPerformed

    private void btnDELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDELETEActionPerformed
        try {      
            if(F.candado())            
                opciones("BORRAR");            
            actualizarTabla(IDPENDIENTE);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnDELETEActionPerformed

    private void tgbtnSADMINItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgbtnSADMINItemStateChanged
        activar(evt.getStateChange() == ItemEvent.SELECTED);        
    }//GEN-LAST:event_tgbtnSADMINItemStateChanged

    private void btnACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnACTUALIZARActionPerformed
        try {
            configurarAtajos();
            actualizarTabla(IDPENDIENTE);
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnACTUALIZARActionPerformed

    private void btnENTREGATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnENTREGATActionPerformed
        try {
            if(F.candado())            
                opciones("ENTREGADO");            
            actualizarTabla(IDPENDIENTE);
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnENTREGATActionPerformed

    private void btnDEVOLUCIONTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDEVOLUCIONTActionPerformed
        try {        
            if(F.candado())            
                opciones("DEVUELTO");            
            actualizarTabla(IDPENDIENTE);
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDEVOLUCIONTActionPerformed

    private void btnNEXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNEXTActionPerformed
        try {
            MOTIVOS("REAGENDAR");
            mostrar(IDPENDIENTE,IDORDENP);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNEXTActionPerformed

    private void btnBITACORAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBITACORAActionPerformed
        try {
            F.viewBitacora("PENDIENTES",lblIDPENDIENTE.getText(), lblCLIENTE.getText(), lblDIRECCION.getText());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBITACORAActionPerformed

    private void tblTABLAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMousePressed
        try {            
            if(F.clickD(evt, tblTABLA))
                seleccion();
            else
                if(F.dosClick(evt, tblTABLA))
                {
                    int columna = tblTABLA.columnAtPoint(evt.getPoint());
                    int fila = tblTABLA.rowAtPoint(evt.getPoint());
                    IDCARRITO = (int)tblTABLA.getValueAt(fila, 0);
                    if(columna == 5)
                        devolver("USADO");
                    else if(columna == 1)
                        System.out.println("EVENTO DE CANTIDAD");                    
                }
            actualizarTabla(IDPENDIENTE);
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_tblTABLAMousePressed

    private void btnENTREGA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnENTREGA1ActionPerformed
        try {
            seleccion();
            marcar1("ENTREGADO");
            actualizarTabla(IDPENDIENTE);
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnENTREGA1ActionPerformed

    private void btnDEVOLVER1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDEVOLVER1ActionPerformed
        try {
            seleccion();
            marcar1("DEVUELTO");
            actualizarTabla(IDPENDIENTE);
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDEVOLVER1ActionPerformed

    private void btnREQUERIDOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnREQUERIDOActionPerformed
        try {
            seleccion();
            marcar1("REQUERIDO");
            actualizarTabla(IDPENDIENTE);
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnREQUERIDOActionPerformed

    private void btnEDITARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEDITARActionPerformed
        try {
            if(F.candado())            
                opciones("EDITAR");            
            actualizarTabla(Integer.parseInt(lblIDPENDIENTE.getText()));
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEDITARActionPerformed

    private void btnCAMBIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCAMBIARActionPerformed
        try {
            if(F.candado())            
                opciones("CAMBIAR");            
            actualizarTabla(IDPENDIENTE);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCAMBIARActionPerformed

    private void btnLLENARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLLENARActionPerformed
        try {
            prellenar("NUEVO");
            actualizarTabla(IDPENDIENTE);
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLLENARActionPerformed

    private void btnCERRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCERRARActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCERRARActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        F.abrirArchivo(rutaPDF);
    }//GEN-LAST:event_btnPDFActionPerformed

    private void bntCOPIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCOPIARActionPerformed
        try {
            seleccion();
            F.clipboard(SICAR);
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntCOPIARActionPerformed

    private void btnCOTIZACIONESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCOTIZACIONESActionPerformed
        try {
            cotizaciones();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCOTIZACIONESActionPerformed

    private void btnADJUNTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADJUNTARActionPerformed
        try {
            pdocs(IDEMPLEADO);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnADJUNTARActionPerformed

    private void txtCOMENTARIOSSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCOMENTARIOSSKeyTyped
        F.MAYUS(evt);
    }//GEN-LAST:event_txtCOMENTARIOSSKeyTyped

    private void txtDESCRIPCIONKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDESCRIPCIONKeyTyped
        F.MAYUS(evt);
    }//GEN-LAST:event_txtDESCRIPCIONKeyTyped

    private void txtTICKETKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTICKETKeyTyped
        F.MAYUS(evt);
    }//GEN-LAST:event_txtTICKETKeyTyped

    private void txtFACTURAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFACTURAKeyTyped
        F.MAYUS(evt);
    }//GEN-LAST:event_txtFACTURAKeyTyped

    private void btnGARANTIASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGARANTIASActionPerformed
        
    }//GEN-LAST:event_btnGARANTIASActionPerformed

    private void btnTOOLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTOOLSActionPerformed
        try {
            cfgubnt("NUEVO");
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTOOLSActionPerformed

    private void btnCAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCAPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCAPActionPerformed

    private void btnGARANTIAS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGARANTIAS1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGARANTIAS1ActionPerformed
  
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
            java.util.logging.Logger.getLogger(RegOrdenesPD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                RegOrdenesPD dialog = new RegOrdenesPD(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            } catch (ParseException ex) {
                Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar1;
    private javax.swing.JToolBar Bar3;
    private javax.swing.JToolBar Bar4;
    private javax.swing.JToolBar Bar5;
    private javax.swing.JToolBar Bar6;
    private javax.swing.JButton bntCOPIAR;
    private javax.swing.JButton bntIMPCAR;
    public static javax.swing.JButton btnACTUALIZAR;
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnADJUNTAR;
    private javax.swing.JButton btnBITACORA;
    private javax.swing.JButton btnCAMBIAR;
    private javax.swing.JButton btnCAP;
    public static javax.swing.JButton btnCERRAR;
    private javax.swing.JButton btnCOTIZACIONES;
    private javax.swing.JButton btnDELETE;
    private javax.swing.JButton btnDEVOLUCIONT;
    private javax.swing.JButton btnDEVOLVER1;
    private javax.swing.JButton btnEDITAR;
    private javax.swing.JButton btnENTREGA1;
    private javax.swing.JButton btnENTREGAT;
    private javax.swing.JButton btnGARANTIAS;
    private javax.swing.JButton btnGARANTIAS1;
    private javax.swing.JButton btnGUARDAR;
    private javax.swing.JButton btnIMPRIMIR;
    private javax.swing.JButton btnLLENAR;
    public static javax.swing.JButton btnNEXT;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnREQUERIDO;
    private javax.swing.JButton btnTOOLS;
    private javax.swing.JComboBox<String> cmbAYUDANTE;
    private javax.swing.JComboBox<String> cmbOFICIAL;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jspTABLA;
    private javax.swing.JLabel lblCLIENTE;
    private javax.swing.JLabel lblDIRECCION;
    private javax.swing.JLabel lblFEST;
    private javax.swing.JLabel lblIDPENDIENTE;
    private javax.swing.JLabel lblREG;
    private javax.swing.JLabel lblTECNICO;
    private javax.swing.JLabel lblTEL;
    private javax.swing.JLabel lblTOTAL;
    private javax.swing.JPanel pnlCARRITO;
    private javax.swing.JPanel pnlCLIENTE;
    private javax.swing.JPanel pnlORDEN;
    private javax.swing.JPanel pnlSERVICIO;
    private javax.swing.JTable tblTABLA;
    private javax.swing.JToggleButton tgbtnSADMIN;
    private javax.swing.JTextArea txtCOMENTARIOSS;
    private javax.swing.JTextArea txtDESCRIPCION;
    private javax.swing.JTextField txtFACTURA;
    private javax.swing.JTextField txtTICKET;
    // End of variables declaration//GEN-END:variables
}
