/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor..
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;

import CONTROLADOR.ControlContratos;
import CONTROLADOR.ControlPagos;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloContratos;
import MODELO.ModeloPagos;
import MODELO.ModeloPagosTabla;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author KIKE FRENCH
 */
public class VPagosVeri extends javax.swing.JInternalFrame
{    
    private String IDRECIBO,FECHA,METODO,F_CORTE,TITULAR,COMENTARIOS,D_CORTE;
    private int IDNOTA,IDCONTRATO;
    private float monto;
    private String IPc;    
    private String DIRECCION;

    /** Creates new form VClientes*/
    public VPagosVeri(){                
    }

    public VPagosVeri(String titulo, String ESTADO) throws ClassNotFoundException, SQLException {                
        initComponents();
        this.setTitle(titulo);
        actualizarTablas(ESTADO,0);
        this.lblCANTIDAD.setText(""+tblTABLA.getRowCount());
        permisos(Principal.PRIVILEGIOS);
        this.Bar.setFloatable(false);        
        F.teclas(this);
        F.noENTER(tblTABLA);
        this.txtBUSCAR.requestFocus();
        this.txtREFERENCIA.setSelectedItem(ESTADO);
    }    
        
    private void permisos(String PRIVILEGIOS)
    {
        if(PRIVILEGIOS.equals("SADMIN")||PRIVILEGIOS.equals("ADMIN"))
        {
            btnEDITAR.setEnabled(true);
            //btnCANCELAR.setEnabled(true);*/
            btnVERIFICAR.setEnabled(true);
            btnRECHAZAR.setEnabled(true);
        }
        if(txtREFERENCIA.getSelectedItem().toString().equals("RECHAZADO"))        
            btnEDITAR.setEnabled(true);
    }
    
    private void accion(String ACCION) throws Exception
    {
        RegPagos p = new RegPagos(null,true);
        p.mostrar(IDNOTA,ACCION,F_CORTE);
        p.setVisible(true);
    }    
        
    private void seleccion() throws ClassNotFoundException, SQLException, Exception
    {
        if(tblTABLA.getRowCount() > 0)
        {
            int fila = tblTABLA.getSelectedRow();
            IDRECIBO = tblTABLA.getValueAt(fila, 0).toString();
            IDNOTA = Integer.parseInt(IDRECIBO);  
            IDCONTRATO = Integer.parseInt(tblTABLA.getValueAt(fila, 2).toString());

            ModeloContratos mdl;
            ControlContratos ctrl = new ControlContratos();
            mdl=ctrl.getRegistrosPAGO(IDCONTRATO);
            F_CORTE=mdl.getFCOR();
        }        
    }
    
    private void direccion() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloContratos mdlC;
        ControlContratos ctrlC = new ControlContratos();
        mdlC = ctrlC.DIRECCION(IDCONTRATO);
        this.DIRECCION = mdlC.getCALLE()+" "+ mdlC.getNUMERO()+" "+mdlC.getCOLONIA()+" "+mdlC.getLOCALIDAD();  
    }
    
    private void imprimir() throws IOException, ClassNotFoundException, SQLException, Exception
    {
        seleccion();                
        
        ModeloPagos mdl;
        ControlPagos ctrl = new ControlPagos();
        mdl = ctrl.getRegistrosIDR(IDNOTA);
        this.FECHA=mdl.getFECHA();
        this.IDCONTRATO=mdl.getIDCONTRATO();
        this.METODO=mdl.getMETODO();
        this.monto=mdl.getMONTO();
        this.COMENTARIOS=mdl.getCOMENTARIOS();
        this.TITULAR=mdl.getNOMBRE();
        this.D_CORTE= mdl.getFCOR();        
        int COSTO = mdl.getCOSTO();
        //CALCULAR DIAS
        Date inicio,fin;
        long dif,difm;
        
        inicio = G.formato.parse(D_CORTE);
        fin=G.hoy();
        difm = inicio.getTime() - fin.getTime();
        dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);
        int DIAS= (int) dif;
        
        //calcula adeudo
        int mes, adeudo;        
        mes =(int)(DIAS/30);

        if(DIAS>0)        
            adeudo=0;        
        else       
            if (mes<1)            
                mes=mes-1;    
        
        adeudo=COSTO*mes;               
        direccion();
        
        ImpPago pca = new ImpPago(IDRECIBO, FECHA, IDCONTRATO, METODO, "$ "+monto, COMENTARIOS, TITULAR,D_CORTE,DIRECCION);
        JButton[] opciones = pca.getButtons();
        JOptionPane.showInternalOptionDialog(this, pca, "Imprimir PAGO",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
        if(pca.getOpcion() != null)
            pca.Imprimir();        
    }
    
    private void pregunta() throws ClassNotFoundException, SQLException, Exception
    {
        seleccion(); 
        int eleccion = JOptionPane.showConfirmDialog(null, "Se envio el comprobate?");
        if (eleccion ==0)
        {
            ControlPagos ctrl = new ControlPagos();
            ctrl.actualizarReferencia("VERIFICADO ENVIADO", IDNOTA);
        }
    }
    
    private void actualizarTablas(String REFERENCIA, int AUX) throws ClassNotFoundException, SQLException
    {
        ModeloPagosTabla mtpn;
        mtpn = new ModeloPagosTabla(REFERENCIA,AUX);
        tblTABLA.setModel(mtpn);
        F.dimensionar(tblTABLA,"PAGOSVERI");
        this.lblCANTIDAD.setText(""+tblTABLA.getRowCount());
    }
    
    public void verificar(String REFERENCIA) throws ClassNotFoundException, SQLException, Exception
    {
        ControlPagos ctrl = new ControlPagos();
        ctrl.actualizarReferencia(REFERENCIA, IDNOTA);
        if(!REFERENCIA.equals("RECHAZADO"))
        {
            corte_nuevo(F.TIPOpaquete(IDCONTRATO));
            F.automatizacion("VERIFICA", IPc, IDCONTRATO, D_CORTE);
        }                
    }
    
    private void corte_nuevo(int PAQUETE) throws Exception
    {
        int x1;
        //int adeudo;
        Date N_CORTE;
        ModeloContratos mdl;
        ControlContratos ctrl = new ControlContratos();
        mdl = ctrl.getRegistrosIDINNER(IDCONTRATO);
        IPc = mdl.getIP();
        //boolean CONTRATO = mdl.getCONT();
        String DIA_CORTE = mdl.getFCOR();
        int COSTO = mdl.getCOSTO();
        
        ModeloPagos mdlP;
        ControlPagos ctrlP = new ControlPagos();
        mdlP = ctrlP.getRegistrosID(IDNOTA);
        float MONTOF = mdlP.getMONTO();
        int MONTO = (int) MONTOF;      
                
        //CALCULA LOS DIAS DE RETRASO
        Date inicio;
        if(PAQUETE==0)        
            inicio=G.formato.parse(DIA_CORTE);        
        else
            inicio=G.hoy();        
        
        Date fin=G.hoy();
        long difm = inicio.getTime() - fin.getTime();
        long dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);
        int DIAS= (int) dif;
        
        x1=MONTO/COSTO;
        N_CORTE=G.formato.parse(DIA_CORTE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(N_CORTE);
        calendar.add(Calendar.MONTH,x1);
        Date fmes=calendar.getTime();

        D_CORTE=""+G.formato.format(fmes);

        ctrl = new ControlContratos();
        ctrl.actualizarCorte(D_CORTE, IDCONTRATO);
        String REFERENCIA = txtREFERENCIA.getSelectedItem().toString();
        actualizarTablas(REFERENCIA,0);
       
        
        /*
        // 1. Centralizar controladores y validación inicial
        ControlContratos ctrl = new ControlContratos();
        ControlPagos ctrlP = new ControlPagos();
        
        ModeloContratos mdl = ctrl.getRegistrosIDINNER(IDCONTRATO);
        ModeloPagos mdlP = ctrlP.getRegistrosID(IDNOTA);

        // Validación de nulidad para evitar NullPointerException
        if (mdl == null || mdlP == null) return;

        // 2. Extracción de datos
        IPc = mdl.getIP();
        String diaCorteStr = mdl.getFCOR();
        int costo = mdl.getCOSTO();
        int MONTO = (int) mdlP.getMONTO();

        // 3. Lógica de días de retraso
        // Usamos una fecha base según el paquete
        Date fechaInicioRetraso = (PAQUETE == 0) ? G.formato.parse(diaCorteStr) : G.hoy();
        Date hoy = G.hoy();

        long difMilis = fechaInicioRetraso.getTime() - hoy.getTime();
        int diasRetraso = (int) TimeUnit.DAYS.convert(difMilis, TimeUnit.MILLISECONDS);

        // 4. Cálculo de extensión de meses (x1)
        // Validación para evitar división por cero si el costo es 0
        int mesesASumar = (costo > 0) ? (MONTO / costo) : 0;

        // 5. Cálculo de la nueva fecha de corte
        // Siempre usamos la fecha del contrato como base para sumar los meses
        Date fechaCorteActual = G.formato.parse(diaCorteStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaCorteActual);
        calendar.add(Calendar.MONTH, mesesASumar);

        D_CORTE = G.formato.format(calendar.getTime());

        // 6. Actualización y cierre
        ctrl.actualizarCorte(D_CORTE, IDCONTRATO);
        
        // Obtenemos la referencia de forma segura
        Object selectedRef = txtREFERENCIA.getSelectedItem();
        String referencia = (selectedRef != null) ? selectedRef.toString() : "";
        
        actualizarTablas(referencia, 0);*/
    }
    
    private void editar() throws SQLException, Exception
    {
        seleccion();
        accion("EDITAR");            
    }
    
    public void opciones(String ACCION) throws SQLException, Exception
    {
        switch(ACCION)
        {
            case "VERIFICAR":
                verificar("VERIFICADO SIN ENVIAR");
                break;
            case "EDITAR":
                editar();
                break;
            case "RECHAZAR":    
                MOTIVOS("RECHAZADO");
                verificar("RECHAZADO");
                break;    
            case "MOVIMIENTO":
                try
                    {
                       RegMovD p = new RegMovD(null,true);
                       p.accion( 0, "NUEVO",IDNOTA);
                       p.setVisible(true);
                    }
                catch (ParseException ex)
                {
                    JOptionPane.showMessageDialog( null, "1234567", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default: 
                break;
        }
    }
    
    private void abrir()
    {
        int fila = tblTABLA.getSelectedRow();
        String NARCHIVO = tblTABLA.getValueAt(fila, 7).toString();
        String rutaETIQUETA = G.rutaServidor+"VOUCHER\\"+NARCHIVO;
        F.abrirArchivo(rutaETIQUETA);
    }
    
    private void MOTIVOS(String ACCION) throws ParseException, SQLException, Exception
    {        
        RegMotivosR reg;
        reg = new RegMotivosR(null, true);
        reg.mostrar( IDNOTA, ACCION);
        reg.setVisible(true);
    }
    
    private void historialPagos(int IDCONTRATO) throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        ViewHistorialPagos reg;
        reg = new ViewHistorialPagos(null, true);
        reg.mostrar(IDCONTRATO);
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

        btnVERI = new javax.swing.JButton();
        pmACCIONES = new javax.swing.JPopupMenu();
        jmmREGPAGOS = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmmMOTIVOSR = new javax.swing.JMenuItem();
        Bar = new javax.swing.JToolBar();
        btnACTUALIZAR = new javax.swing.JButton();
        btnEXCEL = new javax.swing.JButton();
        btnEDITAR = new javax.swing.JButton();
        btnVERIFICAR = new javax.swing.JButton();
        btnIMPRIMIR = new javax.swing.JButton();
        btnRECHAZAR = new javax.swing.JButton();
        jspTABLA = new javax.swing.JScrollPane();
        tblTABLA = new javax.swing.JTable();
        txtBUSCAR = new javax.swing.JTextField();
        txtREFERENCIA = new javax.swing.JComboBox<>();
        lblCANTIDAD = new javax.swing.JLabel();

        btnVERI.setText("jButton1");
        btnVERI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVERIActionPerformed(evt);
            }
        });

        jmmREGPAGOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmREGPAGOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/coins.png"))); // NOI18N
        jmmREGPAGOS.setText("Abrir Historial de Pagos");
        jmmREGPAGOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmREGPAGOSActionPerformed(evt);
            }
        });
        pmACCIONES.add(jmmREGPAGOS);
        pmACCIONES.add(jSeparator1);

        jmmMOTIVOSR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmMOTIVOSR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/rechazar.png"))); // NOI18N
        jmmMOTIVOSR.setText("Motivos de Rechazo");
        jmmMOTIVOSR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmMOTIVOSRActionPerformed(evt);
            }
        });
        pmACCIONES.add(jmmMOTIVOSR);

        setClosable(true);
        setMaximizable(true);
        setTitle("Verificaion de Pagos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/verificar.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        Bar.setRollover(true);

        btnACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_refresh.png"))); // NOI18N
        btnACTUALIZAR.setText("ACTUALIZAR");
        btnACTUALIZAR.setFocusable(false);
        btnACTUALIZAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnACTUALIZAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnACTUALIZARActionPerformed(evt);
            }
        });
        Bar.add(btnACTUALIZAR);

        btnEXCEL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel.png"))); // NOI18N
        btnEXCEL.setText("EXPORTAR");
        btnEXCEL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEXCEL.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEXCELActionPerformed(evt);
            }
        });
        Bar.add(btnEXCEL);

        btnEDITAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEDITAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_edit.png"))); // NOI18N
        btnEDITAR.setText("EDITAR/BORRAR");
        btnEDITAR.setToolTipText("Editar y Borrar");
        btnEDITAR.setEnabled(false);
        btnEDITAR.setFocusable(false);
        btnEDITAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEDITAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEDITAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEDITARActionPerformed(evt);
            }
        });
        Bar.add(btnEDITAR);

        btnVERIFICAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVERIFICAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/verificar.png"))); // NOI18N
        btnVERIFICAR.setText("VERIFICAR");
        btnVERIFICAR.setToolTipText("Verificar Pago");
        btnVERIFICAR.setEnabled(false);
        btnVERIFICAR.setFocusable(false);
        btnVERIFICAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVERIFICAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVERIFICAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVERIFICARActionPerformed(evt);
            }
        });
        Bar.add(btnVERIFICAR);

        btnIMPRIMIR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnIMPRIMIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer.png"))); // NOI18N
        btnIMPRIMIR.setText("IMPRIMIR");
        btnIMPRIMIR.setToolTipText("Reimprimir");
        btnIMPRIMIR.setEnabled(false);
        btnIMPRIMIR.setFocusable(false);
        btnIMPRIMIR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIMPRIMIR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIMPRIMIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIMPRIMIRActionPerformed(evt);
            }
        });
        Bar.add(btnIMPRIMIR);

        btnRECHAZAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRECHAZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/rechazar.png"))); // NOI18N
        btnRECHAZAR.setText("RECHAZAR");
        btnRECHAZAR.setToolTipText("Rechazar Pago");
        btnRECHAZAR.setEnabled(false);
        btnRECHAZAR.setFocusable(false);
        btnRECHAZAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRECHAZAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRECHAZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRECHAZARActionPerformed(evt);
            }
        });
        Bar.add(btnRECHAZAR);

        jspTABLA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblTABLA.setAutoCreateRowSorter(true);
        tblTABLA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTABLA.setComponentPopupMenu(pmACCIONES);
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
        tblTABLA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblTABLAKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblTABLAKeyReleased(evt);
            }
        });
        jspTABLA.setViewportView(tblTABLA);

        txtBUSCAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBUSCAR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtBUSCAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBUSCARKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBUSCARKeyTyped(evt);
            }
        });

        txtREFERENCIA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtREFERENCIA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO VERIFICADO", "VERIFICADO SIN ENVIAR", "RECHAZADO" }));
        txtREFERENCIA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtREFERENCIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtREFERENCIAActionPerformed(evt);
            }
        });

        lblCANTIDAD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCANTIDAD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCANTIDAD.setText("0000");
        lblCANTIDAD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cantidad:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtBUSCAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCANTIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtREFERENCIA, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBUSCAR)
                    .addComponent(txtREFERENCIA, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCANTIDAD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnACTUALIZARActionPerformed
        try {
            actualizarTablas(txtREFERENCIA.getSelectedItem().toString(),0);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnACTUALIZARActionPerformed

    private void tblTABLAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMouseClicked
        try {
            seleccion();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTABLAMouseClicked

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        F.MAYUS(evt);
        F.buscar(tblTABLA, txtBUSCAR.getText(),null,0);        
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void btnEXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXCELActionPerformed
        F.exportarXLS(tblTABLA,"\\REPORTES\\PAGOSVERIFICAR");
    }//GEN-LAST:event_btnEXCELActionPerformed

    private void tblTABLAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMousePressed
        try 
        {
            if(F.clickD(evt, tblTABLA))
                seleccion();
            else
                if(F.dosClick(evt, tblTABLA))
                {
                    seleccion();
                    abrir();
                }
            } 
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTABLAMousePressed

    private void btnEDITARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEDITARActionPerformed
        try {
            String REFERENCIA = txtREFERENCIA.getSelectedItem().toString();
            if(REFERENCIA.equals("RECHAZADO"))
            {
                editar();
                actualizarTablas(REFERENCIA,0);
            }
            else
                if(F.candado())
                    opciones("EDITAR");
            actualizarTablas(REFERENCIA,0);
        } catch (Exception ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEDITARActionPerformed

    private void txtREFERENCIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtREFERENCIAActionPerformed
        try {
            String REFERENCIA = txtREFERENCIA.getSelectedItem().toString();
            if(REFERENCIA.equals("NO VERIFICADO"))
                this.btnIMPRIMIR.setEnabled(false);
            else
                this.btnIMPRIMIR.setEnabled(true);

            permisos(Principal.PRIVILEGIOS);            
            actualizarTablas(REFERENCIA,0);
            txtBUSCAR.requestFocus();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_txtREFERENCIAActionPerformed

    private void btnIMPRIMIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIMPRIMIRActionPerformed
        try {
            imprimir();
            pregunta();
            VPagosVeri.btnACTUALIZAR.doClick();
        } catch (SQLException ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }//GEN-LAST:event_btnIMPRIMIRActionPerformed

    private void btnVERIFICARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVERIFICARActionPerformed
        try {
            if(F.candado())
                opciones("MOVIMIENTO");            
            actualizarTablas(txtREFERENCIA.getSelectedItem().toString(),0);
        } catch (SQLException ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVERIFICARActionPerformed

    private void btnRECHAZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRECHAZARActionPerformed
        try {
            if(F.candado())
                opciones("RECHAZAR");
            actualizarTablas(txtREFERENCIA.getSelectedItem().toString(),0);
        } catch (Exception ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRECHAZARActionPerformed

    private void btnVERIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVERIActionPerformed
        try {
            //if(F.candado())
                opciones("VERIFICAR");
            //actualizarTablas(REFERENCIA,0);
            VPagosVeri.btnACTUALIZAR.doClick();
        } catch (Exception ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVERIActionPerformed

    private void jmmMOTIVOSRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmMOTIVOSRActionPerformed
        try {
            F.viewBitacora("MOTIVOSR", ""+IDNOTA,"","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmMOTIVOSRActionPerformed

    private void jmmREGPAGOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmREGPAGOSActionPerformed
        try {
            historialPagos(IDCONTRATO);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmREGPAGOSActionPerformed

    private void txtBUSCARKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyPressed
        if(F.ENTER(evt))
        {
            if(tblTABLA.getRowCount() > 0)
            {
                tblTABLA.requestFocusInWindow();
                SwingUtilities.invokeLater(() -> 
                {                   
                    tblTABLA.setRowSelectionInterval(0, 0);
                    tblTABLA.scrollRectToVisible(tblTABLA.getCellRect(0, 0, true));
                });           
                SwingUtilities.invokeLater(() -> 
                {                   
                    try 
                    {                
                        seleccion();
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });    
            }
        }
    }//GEN-LAST:event_txtBUSCARKeyPressed

    private void tblTABLAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTABLAKeyReleased
        if(F.NAVEGARTABLA(evt))
        {
            try 
            {
                seleccion();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(VPagosVeri.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblTABLAKeyReleased

    private void tblTABLAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTABLAKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTABLAKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    public static javax.swing.JButton btnACTUALIZAR;
    private javax.swing.JButton btnEDITAR;
    private javax.swing.JButton btnEXCEL;
    private javax.swing.JButton btnIMPRIMIR;
    private javax.swing.JButton btnRECHAZAR;
    public static javax.swing.JButton btnVERI;
    private javax.swing.JButton btnVERIFICAR;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem jmmMOTIVOSR;
    private javax.swing.JMenuItem jmmREGPAGOS;
    private javax.swing.JScrollPane jspTABLA;
    private javax.swing.JLabel lblCANTIDAD;
    private javax.swing.JPopupMenu pmACCIONES;
    private javax.swing.JTable tblTABLA;
    private javax.swing.JTextField txtBUSCAR;
    private javax.swing.JComboBox<String> txtREFERENCIA;
    // End of variables declaration//GEN-END:variables

}

