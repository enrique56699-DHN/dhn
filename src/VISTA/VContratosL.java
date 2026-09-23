/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;
//
import CONTROLADOR.ControlContratos;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloContratos;
import MODELO.ModeloContratosTabla;
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author KIKE FRENCH
 */
public final class VContratosL extends javax.swing.JInternalFrame {


    private int DIAS;
    private String DIRECCION,  NOMBRE,COSTO, DESCRIPCION,IP, PAQUETE;
    private int IDCONTRATO;
    private String rutaPDF;
    private String rutaETIQUETA;  
    
    /** Creates new form VClientes*/
    public VContratosL(){
        
    }
    
    public VContratosL(String titulo) throws ParseException, ClassNotFoundException, SQLException, Exception {        
        initComponents();
        this.setTitle(titulo);
        actualizarTablas("ACTIVO");
        permisos(Principal.PRIVILEGIOS);
        contar();
        this.lblCANTIDAD.setText(""+tblTABLA.getRowCount());
        tblTABLA.requestFocus();
        this.Bar.setFloatable(false);
        F.teclas(this);
        F.noENTER(tblTABLA);
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
                case "ETIQUETA":
                    this.btnETIQUETA.setEnabled(true);
                    break;
                default: 
                    break;
            }
        }
        else
        {
            switch(ARCHIVO)
            {
                case "PDF":
                    this.btnPDF.setEnabled(false);
                    break;
                case "ETIQUETA":
                    this.btnETIQUETA.setEnabled(false);
                    break;
                default: 
                    break;
            }
        }
    }
    
    private void permisos(String PRIVILEGIOS)
    {
        if(PRIVILEGIOS.equals("SADMIN")||PRIVILEGIOS.equals("ADMIN"))
        {
            btnEDITAR.setEnabled(true);
            btnPAGAR.setEnabled(true);
        }
    }
    
    private void contar()
    {
        int regulares=0;
        int vencidos=0;
        for (int i = 0; i < tblTABLA.getRowCount(); i++)
        {
            if((int)tblTABLA.getValueAt(i, 9)>0)
                regulares++;
            else
                if((int)tblTABLA.getValueAt(i, 9)<=0)
                    vencidos++;
        }

        lblREGULARES.setText(""+regulares);
        lblVENCIDOS.setText(""+vencidos);
    }

    private void editar() throws Exception
    {        
        RegContratos p = new RegContratos(null,true);
        p.mostrar(IDCONTRATO,cliente(IDCONTRATO),"EDITAR");
        p.setVisible(true);        
    }
    
    private int cliente(int IDCONTRATO) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloContratos mdl;
        ControlContratos ctrl = new ControlContratos();
        mdl = ctrl.getRegistrosID(IDCONTRATO);
        int IDCLIENTE = mdl.getIDCLIENTE();
        return IDCLIENTE;
    }
    
    private void editarCliente(String ACCION) throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        RegClientes reg;            
        reg = new RegClientes(null,true);
        reg.mostrar(cliente(IDCONTRATO),ACCION);
        reg.setVisible(true);
    }
    
    private void actualizarTablas(String ESTADO) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloContratosTabla mdlT;
        mdlT = new ModeloContratosTabla(ESTADO);
        tblTABLA.setModel(mdlT);        
        mdlT.fireTableDataChanged();
        F.dimensionar(tblTABLA,"CONTRATOS");
    }
    
    private void seleccion() throws ClassNotFoundException, SQLException
    {                
        if(tblTABLA.getRowCount() > 0)
        {
            int fila = tblTABLA.getSelectedRow();
            IDCONTRATO = (int) tblTABLA.getValueAt(fila, 0);     
            NOMBRE = tblTABLA.getValueAt(fila, 1).toString();
            COSTO = tblTABLA.getValueAt(fila, 4).toString();        
            PAQUETE = tblTABLA.getValueAt(fila, 5).toString();       
            DIAS = (int)tblTABLA.getValueAt(fila, 9);
            DIRECCION = (String) tblTABLA.getValueAt(fila, 3);       
            IP = tblTABLA.getValueAt(fila, 10).toString();
            //rutaPDF = "\\\\" +G.ip +"\\dhn\\CONTRATOS\\CONTRATOS-"+IDCONTRATO+".pdf";    
            //rutaETIQUETA = "\\\\" +G.ip +"\\dhn\\ETIQUETAS\\"+IDCONTRATO+".png";
            rutaPDF = G.rutaServidor+"CONTRATOS\\CONTRATOS-"+IDCONTRATO+".pdf";  
            rutaETIQUETA = G.rutaServidor+"ETIQUETAS\\"+IDCONTRATO+".png";
            existe("PDF",rutaPDF);
            existe("ETIQUETA",rutaETIQUETA);
        }       
        
    }
    
    private void pagar() throws ClassNotFoundException, SQLException
    {
        JInternalFrame ji = validador.getJInternalFrame(VRegPagos.class.getName());        
        if(ji != null )
            ji.dispose();
        try {
                ji = new VRegPagos(IDCONTRATO,"NUEVO",DIAS,NOMBRE,COSTO,DIRECCION,F.buscarPalabra(PAQUETE, "DOMICILIADO"));
                Principal.getCONT().add(ji);
                validador.addJIframe(VRegPagos.class.getName(), ji);
            } catch (ParseException ex) {
                Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
            }
        try {
            ji.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
        ji.setVisible(true);
        ji.moveToFront();

    }
    
    private void reportes() throws ClassNotFoundException, SQLException, Exception
    {
        JInternalFrame ji = validador.getJInternalFrame(VRegReportes.class.getName());        
        if(ji != null )
            ji.dispose();
        try {
                ji = new VRegReportes(IDCONTRATO,"NUEVO");
                Principal.getCONT().add(ji);
                validador.addJIframe(VRegReportes.class.getName(), ji);
            } catch (ParseException | ClassNotFoundException | SQLException ex) {
                Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
            }
        try {
            ji.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
        ji.setVisible(true);
        ji.moveToFront();

    }
        
    private void cfgubnt(String ACCION) throws ClassNotFoundException, SQLException, Exception
    {
        Tools cfg; 
        cfg = new Tools(null,true);
        cfg.mostrar(IDCONTRATO,ACCION,"");
        cfg.setVisible(true);
    }
    
    private void servicio(int opn) throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        switch(opn)
        {
            case 0:
                DESCRIPCION = "CAMBIO DE MODEM/ROUTER";                
                break;
            case 1:
                DESCRIPCION = "VISITA DOMICILIARIA PARA REVISION Y REPARACION DE SEÑAL DE INTERNET";
                break;
            case 2:
                DESCRIPCION = "RETIRO DE EQUIPO";
                break;
            case 3:
                DESCRIPCION = "REUBICACION DE EQUIPO POR SOLICITUD DEL CLIENTE";
                break;
            default:
                break;
        }
        
        RegPendientes datos;
        datos = new RegPendientes(null,true/*, PRIVILEGIOS*/);
        datos.mostrar(0,IDCONTRATO,"NUEVO", "INTERNET", DESCRIPCION,opn);
        datos.setVisible(true);
    }
        
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmpACCIONES = new javax.swing.JPopupMenu();
        jmmREPORTES = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmSERVICIO = new javax.swing.JMenu();
        jmmCAMBIOMODEM = new javax.swing.JMenuItem();
        jmmVISITA = new javax.swing.JMenuItem();
        jmmRETIRAR = new javax.swing.JMenuItem();
        jmmREUBICACION = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmTOOLS = new javax.swing.JMenu();
        jmmDIAGNOSTICAR = new javax.swing.JMenuItem();
        jmmWEB = new javax.swing.JMenuItem();
        jmpCFGUBNT = new javax.swing.JMenuItem();
        Bar = new javax.swing.JToolBar();
        btnEDITAR = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEXCEL = new javax.swing.JButton();
        btnPAGAR = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();
        btnETIQUETA = new javax.swing.JButton();
        btnEDITCLIENTE = new javax.swing.JButton();
        txtBUSCAR = new javax.swing.JTextField();
        jspTABLA = new javax.swing.JScrollPane();
        tblTABLA = new javax.swing.JTable();
        txtESTADO = new javax.swing.JComboBox<>();
        lblCANTIDAD = new javax.swing.JLabel();
        lblREGULARES = new javax.swing.JLabel();
        lblVENCIDOS = new javax.swing.JLabel();
        chkDOMICILIADO = new javax.swing.JCheckBox();

        jmpACCIONES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jmmREPORTES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmREPORTES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporte1.png"))); // NOI18N
        jmmREPORTES.setText("Nuevo Reporte");
        jmmREPORTES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmREPORTESActionPerformed(evt);
            }
        });
        jmpACCIONES.add(jmmREPORTES);
        jmpACCIONES.add(jSeparator2);

        jmSERVICIO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agenda.png"))); // NOI18N
        jmSERVICIO.setText("Servicio");
        jmSERVICIO.setToolTipText("Asignar servicio");
        jmSERVICIO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jmmCAMBIOMODEM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCAMBIOMODEM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ruijie.png"))); // NOI18N
        jmmCAMBIOMODEM.setText("Cambio de router");
        jmmCAMBIOMODEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmCAMBIOMODEMActionPerformed(evt);
            }
        });
        jmSERVICIO.add(jmmCAMBIOMODEM);

        jmmVISITA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmVISITA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/visitor.png"))); // NOI18N
        jmmVISITA.setText("Visita domiciliaria");
        jmmVISITA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmVISITAActionPerformed(evt);
            }
        });
        jmSERVICIO.add(jmmVISITA);

        jmmRETIRAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmRETIRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/retirar.png"))); // NOI18N
        jmmRETIRAR.setText("Retiro de equipo");
        jmmRETIRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmRETIRARActionPerformed(evt);
            }
        });
        jmSERVICIO.add(jmmRETIRAR);

        jmmREUBICACION.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmREUBICACION.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ubicacion-1.png"))); // NOI18N
        jmmREUBICACION.setText("Reubicacion de Equipo");
        jmmREUBICACION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmREUBICACIONActionPerformed(evt);
            }
        });
        jmSERVICIO.add(jmmREUBICACION);

        jmpACCIONES.add(jmSERVICIO);
        jmpACCIONES.add(jSeparator1);

        jmTOOLS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/activar.png"))); // NOI18N
        jmTOOLS.setText("Herramientas");

        jmmDIAGNOSTICAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmDIAGNOSTICAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/diagnosticar.png"))); // NOI18N
        jmmDIAGNOSTICAR.setText("Diagnosticar");
        jmmDIAGNOSTICAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmDIAGNOSTICARActionPerformed(evt);
            }
        });
        jmTOOLS.add(jmmDIAGNOSTICAR);

        jmmWEB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmWEB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/web.png"))); // NOI18N
        jmmWEB.setText("Abrir WEB");
        jmmWEB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmWEBActionPerformed(evt);
            }
        });
        jmTOOLS.add(jmmWEB);

        jmpCFGUBNT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmpCFGUBNT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cfgubnt.png"))); // NOI18N
        jmpCFGUBNT.setText("Configurar UBNT");
        jmpCFGUBNT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmpCFGUBNTActionPerformed(evt);
            }
        });
        jmTOOLS.add(jmpCFGUBNT);

        jmpACCIONES.add(jmTOOLS);

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setMaximizable(true);
        setTitle("Contratos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/contrato.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1100, 750));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        Bar.setRollover(true);

        btnEDITAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEDITAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_edit.png"))); // NOI18N
        btnEDITAR.setText("EDITAR");
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

        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_refresh.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setFocusable(false);
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        Bar.add(btnActualizar);

        btnEXCEL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel.png"))); // NOI18N
        btnEXCEL.setText("EXPORTAR");
        btnEXCEL.setFocusable(false);
        btnEXCEL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEXCEL.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEXCELActionPerformed(evt);
            }
        });
        Bar.add(btnEXCEL);

        btnPAGAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPAGAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/coins_add.png"))); // NOI18N
        btnPAGAR.setText("PAGAR");
        btnPAGAR.setEnabled(false);
        btnPAGAR.setFocusable(false);
        btnPAGAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPAGAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPAGAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPAGARActionPerformed(evt);
            }
        });
        Bar.add(btnPAGAR);

        btnPDF.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cuentas1.png"))); // NOI18N
        btnPDF.setText("ABRIR");
        btnPDF.setToolTipText("Abrir Contrato");
        btnPDF.setFocusable(false);
        btnPDF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPDF.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });
        Bar.add(btnPDF);

        btnETIQUETA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnETIQUETA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/QR.png"))); // NOI18N
        btnETIQUETA.setText("ETIQUETA");
        btnETIQUETA.setToolTipText("Abrir Etiqueta");
        btnETIQUETA.setFocusable(false);
        btnETIQUETA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnETIQUETA.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnETIQUETA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnETIQUETAActionPerformed(evt);
            }
        });
        Bar.add(btnETIQUETA);

        btnEDITCLIENTE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEDITCLIENTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente_edit.png"))); // NOI18N
        btnEDITCLIENTE.setText("CLIENTE");
        btnEDITCLIENTE.setToolTipText("Editar datos de cliente");
        btnEDITCLIENTE.setFocusable(false);
        btnEDITCLIENTE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEDITCLIENTE.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEDITCLIENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEDITCLIENTEActionPerformed(evt);
            }
        });
        Bar.add(btnEDITCLIENTE);

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

        jspTABLA.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        tblTABLA.setAutoCreateRowSorter(true);
        tblTABLA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTABLA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTABLA.setComponentPopupMenu(jmpACCIONES);
        tblTABLA.setName("tblTABLA"); // NOI18N
        tblTABLA.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTABLA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTABLAMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                eventoEdit(evt);
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

        txtESTADO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtESTADO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "SUSPENDIDO", "CANCELADO", "PENDIENTE", "RETIRAR", "AUTORIZADO" }));
        txtESTADO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtESTADO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtESTADOActionPerformed(evt);
            }
        });

        lblCANTIDAD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCANTIDAD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCANTIDAD.setText("0000");
        lblCANTIDAD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cantidad:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblREGULARES.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblREGULARES.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblREGULARES.setText("0000");
        lblREGULARES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Regulares:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblVENCIDOS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblVENCIDOS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVENCIDOS.setText("0000");
        lblVENCIDOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vencidos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        chkDOMICILIADO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkDOMICILIADO.setText("Domiciliado");
        chkDOMICILIADO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkDOMICILIADOItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtBUSCAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkDOMICILIADO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVENCIDOS, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblREGULARES, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCANTIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtESTADO, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jspTABLA, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(Bar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1094, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtESTADO, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCANTIDAD)
                            .addComponent(lblREGULARES)
                            .addComponent(lblVENCIDOS))
                        .addComponent(txtBUSCAR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkDOMICILIADO, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEDITARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEDITARActionPerformed
        try {
            editar();
            contar();
        } catch (Exception ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEDITARActionPerformed

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        F.MAYUS(evt);
        if(chkDOMICILIADO.isSelected())
            F.buscar(tblTABLA, txtBUSCAR.getText(),"DOMICILIADO",5);
        else
            F.buscar(tblTABLA, txtBUSCAR.getText(),"",0);
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            actualizarTablas(txtESTADO.getSelectedItem().toString());
            //contar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXCELActionPerformed
        F.exportarXLS(tblTABLA,"\\REPORTES\\CONTRATOS");
    }//GEN-LAST:event_btnEXCELActionPerformed

    private void eventoEdit(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eventoEdit
        try 
        {
            if(F.clickD(evt, tblTABLA))
                seleccion();
            else
                if(F.dosClick(evt, tblTABLA))
                {
                    seleccion();
                    pagar();
                }
            } 
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_eventoEdit

    private void btnPAGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPAGARActionPerformed
        try {
            pagar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnPAGARActionPerformed

    private void jmpCFGUBNTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmpCFGUBNTActionPerformed
        try {
            cfgubnt("NUEVO");
        } catch (SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmpCFGUBNTActionPerformed

    private void txtESTADOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtESTADOActionPerformed
        try {      
            actualizarTablas(txtESTADO.getSelectedItem().toString());
            txtBUSCAR.requestFocus();
            //contar();
            this.lblCANTIDAD.setText(""+tblTABLA.getRowCount());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VPendientesL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtESTADOActionPerformed

    private void jmmCAMBIOMODEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmCAMBIOMODEMActionPerformed
        try {
            servicio(0);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmCAMBIOMODEMActionPerformed

    private void jmmVISITAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmVISITAActionPerformed
        try {
            servicio(1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmVISITAActionPerformed

    private void jmmRETIRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmRETIRARActionPerformed
        try {
            servicio(2);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmRETIRARActionPerformed

    private void jmmWEBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmWEBActionPerformed
        try {
            F.openweb(IP,"IP");
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmWEBActionPerformed

    private void jmmDIAGNOSTICARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmDIAGNOSTICARActionPerformed
        try {
            cfgubnt("DIAGNOSTICAR");
        } catch (SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmDIAGNOSTICARActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed

        F.abrirArchivo(rutaPDF);
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnETIQUETAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnETIQUETAActionPerformed
        F.abrirArchivo(rutaETIQUETA);
    }//GEN-LAST:event_btnETIQUETAActionPerformed

    private void btnEDITCLIENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEDITCLIENTEActionPerformed
        try {
            editarCliente("EDITAR");
            actualizarTablas(txtESTADO.getSelectedItem().toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEDITCLIENTEActionPerformed

    private void tblTABLAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMouseClicked
        try {
            seleccion();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTABLAMouseClicked

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        try {
            actualizarTablas(txtESTADO.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formInternalFrameActivated

    private void jmmREPORTESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmREPORTESActionPerformed
        try {
            reportes();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmREPORTESActionPerformed

    private void jmmREUBICACIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmREUBICACIONActionPerformed
        try {
            servicio(3);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmREUBICACIONActionPerformed

    private void chkDOMICILIADOItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkDOMICILIADOItemStateChanged
        if(chkDOMICILIADO.isSelected())
            F.buscar(tblTABLA, "A","DOMICILIADO",5);
        else
            F.buscar(tblTABLA, txtBUSCAR.getText(),"",0);        
    }//GEN-LAST:event_chkDOMICILIADOItemStateChanged

    private void tblTABLAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTABLAKeyReleased
        if(F.NAVEGARTABLA(evt))
        {
            try 
            {
                seleccion();
                //lblIMAGEN.setIcon(F.previsualizar(lblIMAGEN,NARCHIVO,"\\"+"\\"+G.ip+"\\dhn\\PRODUCTOS\\"));
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblTABLAKeyReleased

    private void tblTABLAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTABLAKeyPressed
        if(F.ENTER(evt))
            try {
                pagar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
        else
            if(F.SHIFT(evt))
            {
                try {
                    seleccion();
                    Rectangle rect = tblTABLA.getCellRect(tblTABLA.getSelectedRow(), 0, true);
                    jmpACCIONES.show(tblTABLA, rect.x, rect.y + rect.height);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_tblTABLAKeyPressed

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
                        Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }); 
            }              
        }
    }//GEN-LAST:event_txtBUSCARKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    public static javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEDITAR;
    private javax.swing.JButton btnEDITCLIENTE;
    private javax.swing.JButton btnETIQUETA;
    private javax.swing.JButton btnEXCEL;
    private javax.swing.JButton btnPAGAR;
    private javax.swing.JButton btnPDF;
    private javax.swing.JCheckBox chkDOMICILIADO;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenu jmSERVICIO;
    private javax.swing.JMenu jmTOOLS;
    private javax.swing.JMenuItem jmmCAMBIOMODEM;
    private javax.swing.JMenuItem jmmDIAGNOSTICAR;
    private javax.swing.JMenuItem jmmREPORTES;
    private javax.swing.JMenuItem jmmRETIRAR;
    private javax.swing.JMenuItem jmmREUBICACION;
    private javax.swing.JMenuItem jmmVISITA;
    private javax.swing.JMenuItem jmmWEB;
    private javax.swing.JPopupMenu jmpACCIONES;
    private javax.swing.JMenuItem jmpCFGUBNT;
    private javax.swing.JScrollPane jspTABLA;
    private javax.swing.JLabel lblCANTIDAD;
    private javax.swing.JLabel lblREGULARES;
    private javax.swing.JLabel lblVENCIDOS;
    private javax.swing.JTable tblTABLA;
    private javax.swing.JTextField txtBUSCAR;
    private javax.swing.JComboBox<String> txtESTADO;
    // End of variables declaration//GEN-END:variables
}

