/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35.
 */
package VISTA;

import CONTROLADOR.ControlContratos;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloContratos;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author KIKE FRENCH
 */
public final class VRegReportes extends javax.swing.JInternalFrame {
   
    private int IDCONTRATO;
    private int IDCLIENTE;
    private int IDREPORTE;
    private String ACCION;
    private String NARCHIVO;
    
    /** Creates new form VClientes

     * @throws java.text.ParseException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException */
    public VRegReportes() throws ParseException, ClassNotFoundException, SQLException {
        initComponents();
    }
    
    public VRegReportes(int ID, String ACCION) throws ParseException, ClassNotFoundException, SQLException, Exception {
        initComponents();
        this.Bar.setFloatable(false);
        actualizarTablas(IDCONTRATO);
        mostrar(ID, ACCION);
        pack();
    }
    
    public void mostrar(int ID, String ACCION) throws ParseException, Exception
    {
        this.ACCION = ACCION;
        
        switch(ACCION)
        {
            case "NUEVO":
                this.IDCONTRATO = ID;
                this.lblESTADO.setText("ABIERTO");
                this.lblFECHA.setText(G.FECHA(G.hoy(), false));
                break;
            case "EDITAR":
                this.IDREPORTE = ID;
                this.lblIDREPORTE.setText(""+ID);
                
                break;
            default:
                break;
        }
        ModeloContratos mdl;
        ControlContratos ctrl = new ControlContratos();
        mdl = ctrl.getRegistrosIDINNER(IDCONTRATO);
        
        this.lblCLIENTE.setText(mdl.getNOMBRE());
        this.lblDIRECCION.setText(mdl.getCALLE()+" "+mdl.getNUMERO()+" "+mdl.getCOLONIA()+" "+mdl.getLOCALIDAD() );
        this.IDCLIENTE = mdl.getIDCLIENTE();   
        this.lblIDCONTRATO.setText(""+IDCONTRATO);
        
    }
    
    private void actualizarTablas(int id) throws ClassNotFoundException, SQLException
    {
        /*ModeloReportesTabla mtpn;
        mtpn = new ModeloReportesTabla (id);
        tblTABLA.setModel(mtpn);
        F.dimensionar(tblTABLA,"REPORTES");*/
    }
            
    private void seleccion(JTable tabla) throws ClassNotFoundException, SQLException
    {
        int fila = tabla.getSelectedRow();
        IDREPORTE = (int) tabla.getValueAt(fila, 0);    
        IDCONTRATO = (int) tabla.getValueAt(fila, 1);    
    }
    
    private void accion(String ACCION) throws Exception
    {/*
        try
        {
            if(ACCION.equals("BORRAR"))
            {
                seleccion();
                int eleccion = JOptionPane.showConfirmDialog(null, "Realmente Desea Eliminar el registro?");
                if ( eleccion == 0)
                {
                    try
                    {
                        try
                        {                            
                            F.eliminarRegistro(IDNOTA, "PAGOS", "IDNOTA");
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
                        IDNOTA = 0;                    
                    
                    RegPagos p = new RegPagos(null,true);
                    p.mostrar(IDNOTA,ACCION,lblD_CORTE.getText());
                    p.setVisible(true);

                }
                catch (ParseException ex)
                {
                    JOptionPane.showMessageDialog( null, "PARA PODER EDITAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
                }
                actualizarTablas(IDCONTRATO);
            }
        }
        catch (ParseException ex)
        {
            JOptionPane.showMessageDialog( null, "PARA PODER EDITAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
        }*/
    }  
        
    private void abrir()
    {
        int fila = tblTABLA.getSelectedRow();
        NARCHIVO = tblTABLA.getValueAt(fila, 5).toString();
        if(NARCHIVO.equals(""))        
            JOptionPane.showMessageDialog( null, "No hay un documento cargado", "VERIFICAR", JOptionPane.WARNING_MESSAGE);        
        else        
            F.abrirArchivo("\\"+"\\"+G.ip+"\\dhn\\VOUCHER\\"+NARCHIVO);        
    }
    
    private void editarCliente(String ACCION) throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        RegClientes reg;            
        reg = new RegClientes(null,true);
        reg.mostrar(IDCLIENTE,ACCION);
        reg.setVisible(true);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlIMAGEN = new javax.swing.JPanel();
        lblIMAGEN = new javax.swing.JLabel();
        pnlCONTENEDOR = new javax.swing.JPanel();
        pnlCONTRATO = new javax.swing.JPanel();
        lblIDCONTRATO = new javax.swing.JLabel();
        lblESTADO = new javax.swing.JLabel();
        lblCLIENTE = new javax.swing.JLabel();
        lblDIRECCION = new javax.swing.JLabel();
        lblDIRECCION1 = new javax.swing.JLabel();
        btnEDITCLIENTE = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblIDREPORTE = new javax.swing.JLabel();
        cmbTIPO = new javax.swing.JComboBox<>();
        lblFECHA = new javax.swing.JLabel();
        btnASISTENTE = new javax.swing.JButton();
        jspDESCRIPCION = new javax.swing.JScrollPane();
        txtDESCRIPCION = new javax.swing.JTextArea();
        jspTABLA = new javax.swing.JScrollPane();
        tblTABLA = new javax.swing.JTable();
        jspDESCRIPCION1 = new javax.swing.JScrollPane();
        txtSOLUCION = new javax.swing.JTextArea();
        Bar = new javax.swing.JToolBar();
        btnGuardar = new javax.swing.JButton();
        btnCERRAR = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setClosable(true);
        setTitle("Registro de Reportes");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporte1.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1100, 750));

        pnlIMAGEN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Previa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblIMAGEN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblIMAGEN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIMAGEN.setText("Imagen no disponible");

        javax.swing.GroupLayout pnlIMAGENLayout = new javax.swing.GroupLayout(pnlIMAGEN);
        pnlIMAGEN.setLayout(pnlIMAGENLayout);
        pnlIMAGENLayout.setHorizontalGroup(
            pnlIMAGENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIMAGEN, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
        );
        pnlIMAGENLayout.setVerticalGroup(
            pnlIMAGENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIMAGEN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlCONTRATO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Contrato", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblIDCONTRATO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblIDCONTRATO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIDCONTRATO.setText("0");
        lblIDCONTRATO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "#Contrato", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        lblIDCONTRATO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblESTADO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblESTADO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblESTADO.setText("ABIERTO");
        lblESTADO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        lblESTADO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblCLIENTE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCLIENTE.setText("NOMBRE COMPLETO");
        lblCLIENTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        lblCLIENTE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblDIRECCION.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDIRECCION.setText("DIRECCION");
        lblDIRECCION.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Direccion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        lblDIRECCION.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblDIRECCION1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDIRECCION1.setText("NO DISPONIBLE");
        lblDIRECCION1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Link Maps", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        lblDIRECCION1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnEDITCLIENTE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEDITCLIENTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente_edit.png"))); // NOI18N
        btnEDITCLIENTE.setToolTipText("Editar datos de cliente");
        btnEDITCLIENTE.setFocusable(false);
        btnEDITCLIENTE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEDITCLIENTE.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEDITCLIENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEDITCLIENTEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCONTRATOLayout = new javax.swing.GroupLayout(pnlCONTRATO);
        pnlCONTRATO.setLayout(pnlCONTRATOLayout);
        pnlCONTRATOLayout.setHorizontalGroup(
            pnlCONTRATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCONTRATOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCONTRATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCONTRATOLayout.createSequentialGroup()
                        .addComponent(lblIDCONTRATO, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblESTADO, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblDIRECCION, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCLIENTE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDIRECCION1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCONTRATOLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEDITCLIENTE)))
                .addContainerGap())
        );
        pnlCONTRATOLayout.setVerticalGroup(
            pnlCONTRATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCONTRATOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCONTRATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDCONTRATO)
                    .addComponent(lblESTADO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCLIENTE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDIRECCION)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDIRECCION1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEDITCLIENTE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reporte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblIDREPORTE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblIDREPORTE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIDREPORTE.setText("0");
        lblIDREPORTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "#Reporte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        lblIDREPORTE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cmbTIPO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbTIPO.setMaximumRowCount(5);
        cmbTIPO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "INTERMITENTE O INESTABLE", "AUSENTE O NULO", "LENTO", "OTRO" }));
        cmbTIPO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de Problema", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblFECHA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFECHA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFECHA.setText("00/00/0000");
        lblFECHA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        lblFECHA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnASISTENTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cheklist.png"))); // NOI18N
        btnASISTENTE.setToolTipText("Iniciar Asistente");

        txtDESCRIPCION.setColumns(20);
        txtDESCRIPCION.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDESCRIPCION.setLineWrap(true);
        txtDESCRIPCION.setRows(3);
        txtDESCRIPCION.setWrapStyleWord(true);
        txtDESCRIPCION.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripcion Breve", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jspDESCRIPCION.setViewportView(txtDESCRIPCION);

        jspTABLA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados de Prueba", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jspTABLA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tblTABLA.setAutoCreateRowSorter(true);
        tblTABLA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTABLA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
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

        txtSOLUCION.setColumns(20);
        txtSOLUCION.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSOLUCION.setLineWrap(true);
        txtSOLUCION.setRows(3);
        txtSOLUCION.setWrapStyleWord(true);
        txtSOLUCION.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Solucion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jspDESCRIPCION1.setViewportView(txtSOLUCION);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblIDREPORTE, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFECHA, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbTIPO, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnASISTENTE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jspDESCRIPCION)
                    .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                    .addComponent(jspDESCRIPCION1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDREPORTE)
                    .addComponent(lblFECHA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTIPO, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnASISTENTE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspDESCRIPCION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTABLA, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspDESCRIPCION1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        Bar.setRollover(true);

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disk.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        Bar.add(btnGuardar);

        btnCERRAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCERRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnCERRAR.setText("CERRAR");
        btnCERRAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCERRAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCERRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCERRARActionPerformed(evt);
            }
        });
        Bar.add(btnCERRAR);

        javax.swing.GroupLayout pnlCONTENEDORLayout = new javax.swing.GroupLayout(pnlCONTENEDOR);
        pnlCONTENEDOR.setLayout(pnlCONTENEDORLayout);
        pnlCONTENEDORLayout.setHorizontalGroup(
            pnlCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCONTRATO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCONTENEDORLayout.setVerticalGroup(
            pnlCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCONTENEDORLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCONTRATO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCONTENEDOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlIMAGEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCONTENEDOR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlIMAGEN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCERRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCERRARActionPerformed
        this.dispose();        
    }//GEN-LAST:event_btnCERRARActionPerformed

    private void tblTABLAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMousePressed
        try {
        if(F.clickD(evt, tblTABLA))
                seleccion(tblTABLA);
            else
                if(F.dosClick(evt, tblTABLA))
                {
                    seleccion(tblTABLA);
                    abrir();
                }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VRegReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTABLAMousePressed

    private void btnEDITCLIENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEDITCLIENTEActionPerformed
        try {
            editarCliente("EDITAR");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VRegReportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VRegReportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRegReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEDITCLIENTEActionPerformed

    private void tblTABLAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMouseClicked
        try {
            seleccion(tblTABLA);
            lblIMAGEN.setIcon(F.previsualizar(lblIMAGEN,NARCHIVO,"\\"+"\\"+G.ip+"\\dhn\\PDOCS\\"));
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(VRegReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTABLAMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnASISTENTE;
    private javax.swing.JButton btnCERRAR;
    private javax.swing.JButton btnEDITCLIENTE;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbTIPO;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jspDESCRIPCION;
    private javax.swing.JScrollPane jspDESCRIPCION1;
    private javax.swing.JScrollPane jspTABLA;
    private javax.swing.JLabel lblCLIENTE;
    private javax.swing.JLabel lblDIRECCION;
    private javax.swing.JLabel lblDIRECCION1;
    private javax.swing.JLabel lblESTADO;
    private javax.swing.JLabel lblFECHA;
    private javax.swing.JLabel lblIDCONTRATO;
    private javax.swing.JLabel lblIDREPORTE;
    private javax.swing.JLabel lblIMAGEN;
    private javax.swing.JPanel pnlCONTENEDOR;
    private javax.swing.JPanel pnlCONTRATO;
    private javax.swing.JPanel pnlIMAGEN;
    private javax.swing.JTable tblTABLA;
    private javax.swing.JTextArea txtDESCRIPCION;
    private javax.swing.JTextArea txtSOLUCION;
    // End of variables declaration//GEN-END:variables
   }

