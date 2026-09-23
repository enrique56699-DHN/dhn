/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Principal.java
 *
 * Created on 11-nov-2013, 17:49:24
 */
package VISTA;

import CONTROLADOR.F;
import DHNv2.G;
import DHNv2.config;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author KIKE FRENCH
 */
public final class Principal extends javax.swing.JFrame 
{
    String msjAbierta="La ventana ya esta abierta...";
    private String versionactual = config.vector.get(17).toString();
    //private String FTC,TC,FINI,FFIN;
    //private SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    //private Date hoy= new Date();  
    //ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    public static String PRIVILEGIOS = null;
    private Monitoreo mon = new Monitoreo();
    private String ESTADO;

    /** Creates new form Principal
     * @param PRIVILEGIOS
     * @throws java.lang.Exception */
    public Principal(String PRIVILEGIOS) throws Exception {
        //this.repCam = new VRepoCamaras();
        initComponents();
        //this.leer.setVisible(false);
        //form();
        this.PRIVILEGIOS = PRIVILEGIOS;
        //this.setTitle("DHN v"+versionactual+" Fecha: "+FTC+" T.C. "+TC+" DB: "+G.db+" Caja: "+G.IDCAJA);
        this.setTitle("DHN v"+versionactual+" DB: "+G.db+" Caja: "+G.IDCAJA);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/Icono.png")); 
        setIconImage(icon);
        
        //ABRIR MAXIMIZADA LA VENTANA
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //Damos opcion al boton de cerrar de no hacer nada
        setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
        
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Definir el tamaño de la ventana como un porcentaje de la pantalla (por ejemplo, 80%)
        //int width = (int) (screenSize.width * 0.8);
        //int height = (int) (screenSize.height * 0.8);
        //this.setSize(width, height);
 
        //metodo para agregar accion al boton de cerrado
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                int eleccion = JOptionPane.showConfirmDialog(null, "¿Realmente desea salir? \n Se perdera toda la informacion que no ha sido guardada");
                if ( eleccion == 0) 
                {
                    G.detener();
                    JOptionPane.showMessageDialog(null, "Creando Autorespaldo", "Espera...",JOptionPane.INFORMATION_MESSAGE);
                    F.backup();
                    new config().cerrarApp();
                    System.exit(0);
                }  
            }
        });
        //ejecutor();
        F.ejecutor();
    }
    
    public Principal() throws Exception 
    {
        initComponents();
    }
     
    /*public void form()
    {
        FFIN=formato.format(hoy);   
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hoy);
        calendar.add(Calendar.DATE,-5);
        hoy=calendar.getTime();
        
        FINI=formato.format(hoy);
        
    }*/
       
    /*private void TC() throws Exception
    {
        Response response = (Response) readSeries(FINI,FFIN);
        if(response!=null)
        {
            Serie serie=response.getBmx().getSeries().get(0);
            System.out.println("Serie: "+serie.getTitulo());
            for(DataSerie data:serie.getDatos()){
                    //Se omiten las observaciones sin dato (N/E)
                    if(data.getDato().equals("N/E")) continue;
                    FTC=data.getFecha();
                    TC=data.getDato();
                    System.out.println("Fecha: "+data.getFecha());
                    System.out.println("Dato: "+data.getDato());
            }

            double tc = Double.parseDouble(TC);
            tc = (Math.round(tc * 100) / 100d) + .8;

            ModeloDetalles mdlD;
            ControlDetalles ctrlD = new ControlDetalles();

            mdlD=ctrlD.getRegistrosD();
            float tcA = mdlD.getTC();
            float ftc = (float) tc;
            //float ftc = 21;
            if(tcA != ftc)
            {
                ControlDetalles ctrl = new ControlDetalles();
                ctrl.actualizarTC(ftc);

                System.out.println("DIVISAS DIFERENTES");
                float GP = mdlD.getGP();
                float GI = mdlD.getGI();
                float IVA = mdlD.getIVA();
                //float KIT =  mdlD.getKIT();
                actualizaPTC(GP,GI,IVA,ftc);
            }
        }                   
        
    }     */  
    
    /*private void actualizaPTC(float GP, float GI, float IVA, float ftc) throws Exception
    {
        ModeloProductosTabla mcont;
        String MONEDA ="USD";
        //int iteraciones = Funciones.total(MONEDA);
        //LLENAMOS LA TABLA CREADA PARA SACAR LOS DIAS REQUERIDOS
        mcont = new ModeloProductosTabla(MONEDA);        
        
        GP = GP / 100;
        GI = GI / 100;
        
        float porcentGP = GP+1;                
        float porcentGI = GI+1;
        for(int c = 0; c<mcont.getRowCount(); c++)
        {
            
            //System.out.println("DIAS PRIMERO..."+DIAS);
            int IDPRODUCTO = (int)mcont.getValueAt(c, 0);
            //System.out.println("DIAS DESPUES..."+DIAS);
            double costo = (double) mcont.getValueAt(c, 1);
            boolean iva = (boolean) mcont.getValueAt(c, 2);
            if(iva!=true)
            {
                IVA=1;
            }               
            
            double PP = costo * IVA  * ftc * porcentGP;
            PP = Math.round(PP * 100) / 100d;
            PP = Math.ceil(PP);
            
            double PI = costo * IVA  * ftc * porcentGI;
            PI = Math.round(PI * 100) / 100d;
            PI = Math.ceil(PI);
            
            ControlProductos ctrlP = new ControlProductos();
            ctrlP.actualizarPrecio(PP, PI, IDPRODUCTO);
            //System.out.println("actualiza producto: "+IDPRODUCTO);
        }
        
    }   */     
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspCONT = new javax.swing.JScrollPane();
        CONT = new javax.swing.JDesktopPane();
        pnlNOTIFICACIONES = new javax.swing.JPanel();
        lblLPXV = new javax.swing.JLabel();
        lblPXV = new javax.swing.JLabel();
        lblLPE = new javax.swing.JLabel();
        lblPXE = new javax.swing.JLabel();
        lblLPR = new javax.swing.JLabel();
        lblPR = new javax.swing.JLabel();
        lblLCP = new javax.swing.JLabel();
        lblCP = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblCPE = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblCE = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblSD = new javax.swing.JLabel();
        jMENU = new javax.swing.JMenuBar();
        jmArchivo = new javax.swing.JMenu();
        jmUSUARIOS = new javax.swing.JMenu();
        jmmUSUARIOS = new javax.swing.JMenuItem();
        jSeparator22 = new javax.swing.JPopupMenu.Separator();
        jmmGRUPOS = new javax.swing.JMenuItem();
        jSeparator23 = new javax.swing.JPopupMenu.Separator();
        jmmCerrarSesion = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jmmCAJA = new javax.swing.JMenuItem();
        jmRegistro = new javax.swing.JMenu();
        jmmClientes = new javax.swing.JMenuItem();
        jmPROVEEDORES = new javax.swing.JMenu();
        jmmPROVEEDORES = new javax.swing.JMenuItem();
        jSeparator31 = new javax.swing.JPopupMenu.Separator();
        jmmPAGOPROVEEDORES = new javax.swing.JMenuItem();
        jmmPROGRAMARPAGO = new javax.swing.JMenuItem();
        jmEMPLEADOS = new javax.swing.JMenu();
        jmmEMPLEADOS = new javax.swing.JMenuItem();
        jmmCARGO = new javax.swing.JMenuItem();
        jmmUNIFORMES = new javax.swing.JMenuItem();
        jmmHERRAMIENTAS = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jmmPRESTAMOS = new javax.swing.JMenuItem();
        jmmAHORROS = new javax.swing.JMenuItem();
        jmmAbonos = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jmmNOMINAS = new javax.swing.JMenuItem();
        jmPRESTACIONES = new javax.swing.JMenu();
        jmmPRESTACIONES = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jmmVACACIONES = new javax.swing.JMenuItem();
        JMMBONOSD = new javax.swing.JMenuItem();
        jmmVEHICULOS = new javax.swing.JMenu();
        jmVEHICULOS = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jmVEHICULOSSER = new javax.swing.JMenuItem();
        jmVEHOCULOSSEG = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JPopupMenu.Separator();
        jmCAJA = new javax.swing.JMenu();
        jmmENTRADASCAJA = new javax.swing.JMenuItem();
        jmmSALIDASCAJA = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jmELECTRONICS = new javax.swing.JMenu();
        jmmAGENDA = new javax.swing.JMenuItem();
        jmmGARANTIAS = new javax.swing.JMenuItem();
        jmmCOTIZACIONES = new javax.swing.JMenuItem();
        jSeparator30 = new javax.swing.JPopupMenu.Separator();
        leerQR = new javax.swing.JMenuItem();
        jSeparator21 = new javax.swing.JPopupMenu.Separator();
        jmmProductos = new javax.swing.JMenuItem();
        jmCB = new javax.swing.JMenuItem();
        jmmPRODUCTOSD = new javax.swing.JMenuItem();
        jmmCARRITOPRE = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        jmmALMACEN = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jmInternet = new javax.swing.JMenu();
        jmmContratos = new javax.swing.JMenuItem();
        jmmREPORTES = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jmmANTENAS = new javax.swing.JMenuItem();
        jSeparator20 = new javax.swing.JPopupMenu.Separator();
        jmmPaquetes = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jmmPagos = new javax.swing.JMenuItem();
        jmmPagosVeri = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmmCPagos = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jmMOVIMIENTOS = new javax.swing.JMenu();
        jmmENTRADAS = new javax.swing.JMenuItem();
        jmmSALIDAS = new javax.swing.JMenuItem();
        jSeparator19 = new javax.swing.JPopupMenu.Separator();
        jmmCORTES = new javax.swing.JMenuItem();
        jSeparator26 = new javax.swing.JPopupMenu.Separator();
        jmmEXTRAS = new javax.swing.JMenuItem();
        jSeparator29 = new javax.swing.JPopupMenu.Separator();
        jmmVALES = new javax.swing.JMenuItem();
        jmmCF = new javax.swing.JMenuItem();
        jSeparator27 = new javax.swing.JPopupMenu.Separator();
        jmmGLOBAL = new javax.swing.JMenuItem();
        jmInformes = new javax.swing.JMenu();
        jmmUTILIDADES = new javax.swing.JMenuItem();
        jmmREPFINANZAS = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmmCONCILIAR = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        jmmCORTEDIA = new javax.swing.JMenuItem();
        jSeparator28 = new javax.swing.JPopupMenu.Separator();
        jmmCORTEG = new javax.swing.JMenuItem();
        jmHerramientas = new javax.swing.JMenu();
        jmmCALCULADORA = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        jmmIMPORTAR = new javax.swing.JMenuItem();
        jmmRespaldar = new javax.swing.JMenuItem();
        jmmRestaurar = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jmmSOLAR = new javax.swing.JMenuItem();
        jmmIP = new javax.swing.JMenuItem();
        jmmAPARIENCIA = new javax.swing.JMenuItem();
        jSeparator25 = new javax.swing.JPopupMenu.Separator();
        jmmUBNT = new javax.swing.JMenuItem();
        jmmMONITOREO = new javax.swing.JMenuItem();
        jmDETALLES = new javax.swing.JMenu();
        jmmDETALLES = new javax.swing.JMenuItem();
        jmACERCA = new javax.swing.JMenu();
        jmInformacion = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jmCUENTAEDH = new javax.swing.JMenuItem();
        jmIMPCALSOL = new javax.swing.JMenuItem();
        jmIMPHVAC = new javax.swing.JMenuItem();
        jSeparator24 = new javax.swing.JPopupMenu.Separator();
        jmCUENTADHN = new javax.swing.JMenuItem();
        jmPROBLEMAS = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DHN v2");
        setMinimumSize(new java.awt.Dimension(800, 600));

        jspCONT.setAutoscrolls(true);
        jspCONT.setPreferredSize(new java.awt.Dimension(673, 475));

        CONT.setAutoscrolls(true);
        CONT.setMinimumSize(new java.awt.Dimension(680, 480));
        jspCONT.setViewportView(CONT);

        pnlNOTIFICACIONES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notificaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10), new java.awt.Color(153, 0, 0))); // NOI18N

        lblLPXV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblLPXV.setText("Pagos Por Verificar:");
        lblLPXV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLPXV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLPXVMouseClicked(evt);
            }
        });

        lblPXV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPXV.setText("0000");

        lblLPE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblLPE.setText("Pagos Por Enviar:");
        lblLPE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLPE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLPEMouseClicked(evt);
            }
        });

        lblPXE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPXE.setText("0000");

        lblLPR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblLPR.setText("Pagos Rechazados:");
        lblLPR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLPR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLPRMouseClicked(evt);
            }
        });

        lblPR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPR.setText("0000");

        lblLCP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblLCP.setText("Cotizaciones Por Relizar:");
        lblLCP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLCP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLCPMouseClicked(evt);
            }
        });

        lblCP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCP.setText("0000");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Cotizaciones Por Enviar:");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        lblCPE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCPE.setText("0000");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Cotizaciones Enviadas:");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        lblCE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCE.setText("0000");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Servicios del Dia:");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        lblSD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSD.setText("0000");

        javax.swing.GroupLayout pnlNOTIFICACIONESLayout = new javax.swing.GroupLayout(pnlNOTIFICACIONES);
        pnlNOTIFICACIONES.setLayout(pnlNOTIFICACIONESLayout);
        pnlNOTIFICACIONESLayout.setHorizontalGroup(
            pnlNOTIFICACIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNOTIFICACIONESLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLPXV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPXV)
                .addGap(18, 18, 18)
                .addComponent(lblLPE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPXE)
                .addGap(18, 18, 18)
                .addComponent(lblLPR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPR)
                .addGap(18, 18, 18)
                .addComponent(lblLCP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCP)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCPE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSD)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNOTIFICACIONESLayout.setVerticalGroup(
            pnlNOTIFICACIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNOTIFICACIONESLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlNOTIFICACIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNOTIFICACIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLPE)
                        .addComponent(lblPXE)
                        .addComponent(lblLPR)
                        .addComponent(lblPR)
                        .addComponent(lblLCP)
                        .addComponent(lblCP)
                        .addComponent(jLabel5)
                        .addComponent(lblCPE)
                        .addComponent(jLabel6)
                        .addComponent(lblCE)
                        .addComponent(jLabel7)
                        .addComponent(lblSD))
                    .addGroup(pnlNOTIFICACIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLPXV)
                        .addComponent(lblPXV))))
        );

        jmArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/folder.png"))); // NOI18N
        jmArchivo.setText("ARCHIVO");
        jmArchivo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jmUSUARIOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/group.png"))); // NOI18N
        jmUSUARIOS.setText("Usuarios");
        jmUSUARIOS.setEnabled(false);
        jmUSUARIOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jmmUSUARIOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmUSUARIOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/group_add.png"))); // NOI18N
        jmmUSUARIOS.setText("Usuarios");
        jmmUSUARIOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmUSUARIOSActionPerformed(evt);
            }
        });
        jmUSUARIOS.add(jmmUSUARIOS);
        jmUSUARIOS.add(jSeparator22);

        jmmGRUPOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmGRUPOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/password.png"))); // NOI18N
        jmmGRUPOS.setText("Grupos y Privilegios");
        jmUSUARIOS.add(jmmGRUPOS);

        jmArchivo.add(jmUSUARIOS);
        jmArchivo.add(jSeparator23);

        jmmCerrarSesion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmmCerrarSesion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disconnect.png"))); // NOI18N
        jmmCerrarSesion.setText("Cerrar Sesion");
        jmmCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmCerrarSesionActionPerformed(evt);
            }
        });
        jmArchivo.add(jmmCerrarSesion);
        jmArchivo.add(jSeparator6);

        jmmCAJA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCAJA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/register-box.png"))); // NOI18N
        jmmCAJA.setText("Cambio de Caja");
        jmmCAJA.setEnabled(false);
        jmArchivo.add(jmmCAJA);

        jMENU.add(jmArchivo);

        jmRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/registrop.png"))); // NOI18N
        jmRegistro.setText("REGISTRO");
        jmRegistro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jmmClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jmmClientes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente.png"))); // NOI18N
        jmmClientes.setText("Clientes");
        jmmClientes.setEnabled(false);
        jmmClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmClientesActionPerformed(evt);
            }
        });
        jmRegistro.add(jmmClientes);

        jmPROVEEDORES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/proveedor.png"))); // NOI18N
        jmPROVEEDORES.setText("Proveedores");
        jmPROVEEDORES.setEnabled(false);
        jmPROVEEDORES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jmmPROVEEDORES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmPROVEEDORES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/proveedor.png"))); // NOI18N
        jmmPROVEEDORES.setText("Proveedores");
        jmmPROVEEDORES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmPROVEEDORESActionPerformed(evt);
            }
        });
        jmPROVEEDORES.add(jmmPROVEEDORES);
        jmPROVEEDORES.add(jSeparator31);

        jmmPAGOPROVEEDORES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmPAGOPROVEEDORES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pagoprovedores.png"))); // NOI18N
        jmmPAGOPROVEEDORES.setText("Cuentas, Cargos y Abonos");
        jmmPAGOPROVEEDORES.setToolTipText("Lista de Cuentas por Proveedor");
        jmPROVEEDORES.add(jmmPAGOPROVEEDORES);

        jmmPROGRAMARPAGO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmPROGRAMARPAGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reloj.png"))); // NOI18N
        jmmPROGRAMARPAGO.setText("Pagos Programados");
        jmmPROGRAMARPAGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmPROGRAMARPAGOActionPerformed(evt);
            }
        });
        jmPROVEEDORES.add(jmmPROGRAMARPAGO);

        jmRegistro.add(jmPROVEEDORES);

        jmEMPLEADOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tecnico.png"))); // NOI18N
        jmEMPLEADOS.setText("Empleados");
        jmEMPLEADOS.setEnabled(false);
        jmEMPLEADOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jmmEMPLEADOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmmEMPLEADOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmEMPLEADOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tecnico.png"))); // NOI18N
        jmmEMPLEADOS.setText("Empleados");
        jmmEMPLEADOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmEMPLEADOSActionPerformed(evt);
            }
        });
        jmEMPLEADOS.add(jmmEMPLEADOS);

        jmmCARGO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCARGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cargos.png"))); // NOI18N
        jmmCARGO.setText("Puestos de Trabajo");
        jmmCARGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmCARGOActionPerformed(evt);
            }
        });
        jmEMPLEADOS.add(jmmCARGO);

        jmmUNIFORMES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmUNIFORMES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/uniforme.png"))); // NOI18N
        jmmUNIFORMES.setText("Uniformes y Equipo de Seguridad");
        jmEMPLEADOS.add(jmmUNIFORMES);

        jmmHERRAMIENTAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmHERRAMIENTAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/herramienta.png"))); // NOI18N
        jmmHERRAMIENTAS.setText("Herramientas");
        jmEMPLEADOS.add(jmmHERRAMIENTAS);
        jmEMPLEADOS.add(jSeparator8);

        jmmPRESTAMOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmPRESTAMOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/prestamo.png"))); // NOI18N
        jmmPRESTAMOS.setText("Prestamos");
        jmmPRESTAMOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmPRESTAMOSActionPerformed(evt);
            }
        });
        jmEMPLEADOS.add(jmmPRESTAMOS);

        jmmAHORROS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmAHORROS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ahorros.png"))); // NOI18N
        jmmAHORROS.setText("Ahorros");
        jmmAHORROS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmAHORROSActionPerformed(evt);
            }
        });
        jmEMPLEADOS.add(jmmAHORROS);

        jmmAbonos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmAbonos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/prestamos.png"))); // NOI18N
        jmmAbonos.setText("Abonos");
        jmmAbonos.setToolTipText("Abonos");
        jmmAbonos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmAbonosActionPerformed(evt);
            }
        });
        jmEMPLEADOS.add(jmmAbonos);
        jmEMPLEADOS.add(jSeparator15);

        jmmNOMINAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmNOMINAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nomina.png"))); // NOI18N
        jmmNOMINAS.setText("Nominas");
        jmmNOMINAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmNOMINASActionPerformed(evt);
            }
        });
        jmEMPLEADOS.add(jmmNOMINAS);

        jmPRESTACIONES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/prestaciones.png"))); // NOI18N
        jmPRESTACIONES.setText("Prestaciones");
        jmPRESTACIONES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jmmPRESTACIONES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmPRESTACIONES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/prestaciones.png"))); // NOI18N
        jmmPRESTACIONES.setText("Prestaciones Pagadas");
        jmmPRESTACIONES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmPRESTACIONESActionPerformed(evt);
            }
        });
        jmPRESTACIONES.add(jmmPRESTACIONES);
        jmPRESTACIONES.add(jSeparator7);

        jmmVACACIONES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmVACACIONES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vacaciones1.png"))); // NOI18N
        jmmVACACIONES.setText("Permisos de Vacaciones");
        jmmVACACIONES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmVACACIONESActionPerformed(evt);
            }
        });
        jmPRESTACIONES.add(jmmVACACIONES);

        JMMBONOSD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JMMBONOSD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bonos.png"))); // NOI18N
        JMMBONOSD.setText("Bonos");
        JMMBONOSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMMBONOSDActionPerformed(evt);
            }
        });
        jmPRESTACIONES.add(JMMBONOSD);

        jmEMPLEADOS.add(jmPRESTACIONES);

        jmRegistro.add(jmEMPLEADOS);

        jmmVEHICULOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vehiculo.png"))); // NOI18N
        jmmVEHICULOS.setText("Vehiculos");
        jmmVEHICULOS.setEnabled(false);
        jmmVEHICULOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jmVEHICULOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmVEHICULOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vehiculo.png"))); // NOI18N
        jmVEHICULOS.setText("Vehiculos");
        jmVEHICULOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmVEHICULOSActionPerformed(evt);
            }
        });
        jmmVEHICULOS.add(jmVEHICULOS);
        jmmVEHICULOS.add(jSeparator11);

        jmVEHICULOSSER.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmVEHICULOSSER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vehiculo_gastos.png"))); // NOI18N
        jmVEHICULOSSER.setText("Servicios y Reparaciones");
        jmVEHICULOSSER.setToolTipText("");
        jmVEHICULOSSER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmVEHICULOSSERActionPerformed(evt);
            }
        });
        jmmVEHICULOS.add(jmVEHICULOSSER);

        jmVEHOCULOSSEG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmVEHOCULOSSEG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/seguros.png"))); // NOI18N
        jmVEHOCULOSSEG.setText("Seguros");
        jmVEHOCULOSSEG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmVEHOCULOSSEGActionPerformed(evt);
            }
        });
        jmmVEHICULOS.add(jmVEHOCULOSSEG);

        jmRegistro.add(jmmVEHICULOS);
        jmRegistro.add(jSeparator18);

        jmCAJA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ahorro.png"))); // NOI18N
        jmCAJA.setText("Caja de Ahorro");
        jmCAJA.setEnabled(false);
        jmCAJA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jmmENTRADASCAJA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmENTRADASCAJA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/+.png"))); // NOI18N
        jmmENTRADASCAJA.setText("Entradas de CAJA");
        jmmENTRADASCAJA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmENTRADASCAJAActionPerformed(evt);
            }
        });
        jmCAJA.add(jmmENTRADASCAJA);

        jmmSALIDASCAJA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmSALIDASCAJA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/-.png"))); // NOI18N
        jmmSALIDASCAJA.setText("Salidas de CAJA");
        jmmSALIDASCAJA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmSALIDASCAJAActionPerformed(evt);
            }
        });
        jmCAJA.add(jmmSALIDASCAJA);

        jmRegistro.add(jmCAJA);
        jmRegistro.add(jSeparator5);

        jmELECTRONICS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edh.png"))); // NOI18N
        jmELECTRONICS.setText("Electronics");
        jmELECTRONICS.setEnabled(false);
        jmELECTRONICS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jmmAGENDA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jmmAGENDA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmAGENDA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agenda.png"))); // NOI18N
        jmmAGENDA.setText("Agenda");
        jmmAGENDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmAGENDAActionPerformed(evt);
            }
        });
        jmELECTRONICS.add(jmmAGENDA);

        jmmGARANTIAS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmmGARANTIAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmGARANTIAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/garantias.png"))); // NOI18N
        jmmGARANTIAS.setText("Garantias");
        jmELECTRONICS.add(jmmGARANTIAS);

        jmmCOTIZACIONES.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmmCOTIZACIONES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCOTIZACIONES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cotizacion.png"))); // NOI18N
        jmmCOTIZACIONES.setText("Cotizaciones");
        jmmCOTIZACIONES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmCOTIZACIONESActionPerformed(evt);
            }
        });
        jmELECTRONICS.add(jmmCOTIZACIONES);
        jmELECTRONICS.add(jSeparator30);

        leerQR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        leerQR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        leerQR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/QR.png"))); // NOI18N
        leerQR.setText("Leer Codigo");
        leerQR.setToolTipText("Leer Codigo de Servicios");
        leerQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerQRActionPerformed(evt);
            }
        });
        jmELECTRONICS.add(leerQR);
        jmELECTRONICS.add(jSeparator21);

        jmmProductos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jmmProductos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/produtos.png"))); // NOI18N
        jmmProductos.setText("Productos y Servicios");
        jmmProductos.setEnabled(false);
        jmmProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmProductosActionPerformed(evt);
            }
        });
        jmELECTRONICS.add(jmmProductos);

        jmCB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cb.png"))); // NOI18N
        jmCB.setText("Leer CB equivalente");
        jmCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCBActionPerformed(evt);
            }
        });
        jmELECTRONICS.add(jmCB);

        jmmPRODUCTOSD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmPRODUCTOSD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/productosd.png"))); // NOI18N
        jmmPRODUCTOSD.setText("Codigos de Barras Equivalentes");
        jmmPRODUCTOSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmPRODUCTOSDActionPerformed(evt);
            }
        });
        jmELECTRONICS.add(jmmPRODUCTOSD);

        jmmCARRITOPRE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jmmCARRITOPRE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCARRITOPRE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/paquete.png"))); // NOI18N
        jmmCARRITOPRE.setText("Kits");
        jmmCARRITOPRE.setToolTipText("");
        jmmCARRITOPRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmCARRITOPREActionPerformed(evt);
            }
        });
        jmELECTRONICS.add(jmmCARRITOPRE);
        jmELECTRONICS.add(jSeparator16);

        jmmALMACEN.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmmALMACEN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmALMACEN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/almacen.png"))); // NOI18N
        jmmALMACEN.setText("Almacen");
        jmmALMACEN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmALMACENActionPerformed(evt);
            }
        });
        jmELECTRONICS.add(jmmALMACEN);

        jmRegistro.add(jmELECTRONICS);
        jmRegistro.add(jSeparator10);

        jmInternet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/DHN-SMALL-32.png"))); // NOI18N
        jmInternet.setText("Internet");
        jmInternet.setEnabled(false);
        jmInternet.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jmmContratos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jmmContratos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmContratos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/contrato.png"))); // NOI18N
        jmmContratos.setText("Contratos");
        jmmContratos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmContratosActionPerformed(evt);
            }
        });
        jmInternet.add(jmmContratos);

        jmmREPORTES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmREPORTES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporte1.png"))); // NOI18N
        jmmREPORTES.setText("Reportes");
        jmInternet.add(jmmREPORTES);
        jmInternet.add(jSeparator9);

        jmmANTENAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmANTENAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/WISP.png"))); // NOI18N
        jmmANTENAS.setText("Enlaces BH y Sectoriales");
        jmmANTENAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmANTENASActionPerformed(evt);
            }
        });
        jmInternet.add(jmmANTENAS);
        jmInternet.add(jSeparator20);

        jmmPaquetes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmPaquetes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/paq.png"))); // NOI18N
        jmmPaquetes.setText("Paquetes");
        jmmPaquetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmPaquetesActionPerformed(evt);
            }
        });
        jmInternet.add(jmmPaquetes);
        jmInternet.add(jSeparator13);

        jmmPagos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmPagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/coins_add.png"))); // NOI18N
        jmmPagos.setText("Pagos");
        jmmPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmPagosActionPerformed(evt);
            }
        });
        jmInternet.add(jmmPagos);

        jmmPagosVeri.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jmmPagosVeri.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmPagosVeri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/verificar.png"))); // NOI18N
        jmmPagosVeri.setText("Verificar Pagos");
        jmmPagosVeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmPagosVeriActionPerformed(evt);
            }
        });
        jmInternet.add(jmmPagosVeri);
        jmInternet.add(jSeparator2);

        jmmCPagos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCPagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cp.png"))); // NOI18N
        jmmCPagos.setText("Centros de Pago");
        jmmCPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmCPagosActionPerformed(evt);
            }
        });
        jmInternet.add(jmmCPagos);

        jmRegistro.add(jmInternet);
        jmRegistro.add(jSeparator4);

        jmMOVIMIENTOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/entradas-salidas-2.png"))); // NOI18N
        jmMOVIMIENTOS.setText("Movimientos");
        jmMOVIMIENTOS.setToolTipText("Entradas/Salidas");
        jmMOVIMIENTOS.setEnabled(false);
        jmMOVIMIENTOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jmmENTRADAS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        jmmENTRADAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmENTRADAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/+.png"))); // NOI18N
        jmmENTRADAS.setText("Entradas");
        jmmENTRADAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmENTRADASActionPerformed(evt);
            }
        });
        jmMOVIMIENTOS.add(jmmENTRADAS);

        jmmSALIDAS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        jmmSALIDAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmSALIDAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/-.png"))); // NOI18N
        jmmSALIDAS.setText("Salidas");
        jmmSALIDAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmSALIDASActionPerformed(evt);
            }
        });
        jmMOVIMIENTOS.add(jmmSALIDAS);
        jmMOVIMIENTOS.add(jSeparator19);

        jmmCORTES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCORTES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cajaR.png"))); // NOI18N
        jmmCORTES.setText("Cortes de Caja");
        jmmCORTES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmCORTESActionPerformed(evt);
            }
        });
        jmMOVIMIENTOS.add(jmmCORTES);
        jmMOVIMIENTOS.add(jSeparator26);

        jmmEXTRAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmEXTRAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/extra.png"))); // NOI18N
        jmmEXTRAS.setText("Extras");
        jmmEXTRAS.setEnabled(false);
        jmmEXTRAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmEXTRASActionPerformed(evt);
            }
        });
        jmMOVIMIENTOS.add(jmmEXTRAS);
        jmMOVIMIENTOS.add(jSeparator29);

        jmmVALES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmVALES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vale.png"))); // NOI18N
        jmmVALES.setText("Vales");
        jmmVALES.setEnabled(false);
        jmmVALES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmVALESActionPerformed(evt);
            }
        });
        jmMOVIMIENTOS.add(jmmVALES);

        jmmCF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja-fuerte.png"))); // NOI18N
        jmmCF.setText("CF");
        jmmCF.setEnabled(false);
        jmmCF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmCFActionPerformed(evt);
            }
        });
        jmMOVIMIENTOS.add(jmmCF);
        jmMOVIMIENTOS.add(jSeparator27);

        jmmGLOBAL.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmGLOBAL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dinero-global.png"))); // NOI18N
        jmmGLOBAL.setText("Cortes Globales");
        jmmGLOBAL.setEnabled(false);
        jmmGLOBAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmGLOBALActionPerformed(evt);
            }
        });
        jmMOVIMIENTOS.add(jmmGLOBAL);

        jmRegistro.add(jmMOVIMIENTOS);

        jMENU.add(jmRegistro);

        jmInformes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/report.png"))); // NOI18N
        jmInformes.setText("INFORMES");
        jmInformes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jmmUTILIDADES.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmmUTILIDADES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmUTILIDADES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/utilidades.png"))); // NOI18N
        jmmUTILIDADES.setText("Reporte de Utilidades");
        jmmUTILIDADES.setEnabled(false);
        jmmUTILIDADES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmUTILIDADESActionPerformed(evt);
            }
        });
        jmInformes.add(jmmUTILIDADES);

        jmmREPFINANZAS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmmREPFINANZAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmREPFINANZAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/finanzas.png"))); // NOI18N
        jmmREPFINANZAS.setText("Reporte de Finanzas");
        jmmREPFINANZAS.setEnabled(false);
        jmmREPFINANZAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmREPFINANZASActionPerformed(evt);
            }
        });
        jmInformes.add(jmmREPFINANZAS);
        jmInformes.add(jSeparator1);

        jmmCONCILIAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCONCILIAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/link.png"))); // NOI18N
        jmmCONCILIAR.setText("Conciliar Pagos DHN");
        jmmCONCILIAR.setEnabled(false);
        jmmCONCILIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmCONCILIARActionPerformed(evt);
            }
        });
        jmInformes.add(jmmCONCILIAR);
        jmInformes.add(jSeparator17);

        jmmCORTEDIA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCORTEDIA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cajaR.png"))); // NOI18N
        jmmCORTEDIA.setText("Corte de CAJA");
        jmmCORTEDIA.setEnabled(false);
        jmmCORTEDIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmCORTEDIAActionPerformed(evt);
            }
        });
        jmInformes.add(jmmCORTEDIA);
        jmInformes.add(jSeparator28);

        jmmCORTEG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCORTEG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dinero-global.png"))); // NOI18N
        jmmCORTEG.setText("Corte Global");
        jmmCORTEG.setEnabled(false);
        jmmCORTEG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmCORTEGActionPerformed(evt);
            }
        });
        jmInformes.add(jmmCORTEG);

        jMENU.add(jmInformes);

        jmHerramientas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cog.png"))); // NOI18N
        jmHerramientas.setText("HERRAMIENTAS");
        jmHerramientas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jmmCALCULADORA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCALCULADORA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calculator.png"))); // NOI18N
        jmmCALCULADORA.setText("Calculadora");
        jmmCALCULADORA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmCALCULADORAActionPerformed(evt);
            }
        });
        jmHerramientas.add(jmmCALCULADORA);
        jmHerramientas.add(jSeparator14);

        jmmIMPORTAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmIMPORTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/importar_S.png"))); // NOI18N
        jmmIMPORTAR.setText("Importar datos");
        jmmIMPORTAR.setEnabled(false);
        jmmIMPORTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmIMPORTARActionPerformed(evt);
            }
        });
        jmHerramientas.add(jmmIMPORTAR);

        jmmRespaldar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmmRespaldar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmRespaldar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/database_save.png"))); // NOI18N
        jmmRespaldar.setText("Respaldar Base de Datos");
        jmmRespaldar.setEnabled(false);
        jmmRespaldar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmRespaldarActionPerformed(evt);
            }
        });
        jmHerramientas.add(jmmRespaldar);

        jmmRestaurar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmmRestaurar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmRestaurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/database_refresh.png"))); // NOI18N
        jmmRestaurar.setText("Restaurar Base de Datos");
        jmmRestaurar.setEnabled(false);
        jmmRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmRestaurarActionPerformed(evt);
            }
        });
        jmHerramientas.add(jmmRestaurar);
        jmHerramientas.add(jSeparator3);

        jmmSOLAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmSOLAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/panel-solar.png"))); // NOI18N
        jmmSOLAR.setText("Cotizacion Paneles Solares");
        jmmSOLAR.setEnabled(false);
        jmmSOLAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmSOLARActionPerformed(evt);
            }
        });
        jmHerramientas.add(jmmSOLAR);

        jmmIP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmIP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/IP.png"))); // NOI18N
        jmmIP.setText("Cambio IP en contratos");
        jmmIP.setEnabled(false);
        jmmIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmIPActionPerformed(evt);
            }
        });
        jmHerramientas.add(jmmIP);

        jmmAPARIENCIA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmAPARIENCIA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/apariencia.png"))); // NOI18N
        jmmAPARIENCIA.setText("Apariencia");
        jmmAPARIENCIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmAPARIENCIAActionPerformed(evt);
            }
        });
        jmHerramientas.add(jmmAPARIENCIA);
        jmHerramientas.add(jSeparator25);

        jmmUBNT.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmmUBNT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmUBNT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/activar.png"))); // NOI18N
        jmmUBNT.setText("Herramientas UBNT");
        jmmUBNT.setEnabled(false);
        jmmUBNT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmUBNTActionPerformed(evt);
            }
        });
        jmHerramientas.add(jmmUBNT);

        jmmMONITOREO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        jmmMONITOREO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmMONITOREO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/monitoreo.png"))); // NOI18N
        jmmMONITOREO.setText("Monitoreo de Enlaces");
        jmmMONITOREO.setEnabled(false);
        jmmMONITOREO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmMONITOREOActionPerformed(evt);
            }
        });
        jmHerramientas.add(jmmMONITOREO);

        jMENU.add(jmHerramientas);

        jmDETALLES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/details.png"))); // NOI18N
        jmDETALLES.setText("DETALLES");
        jmDETALLES.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jmmDETALLES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmDETALLES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/details.png"))); // NOI18N
        jmmDETALLES.setText("Detalles");
        jmmDETALLES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmDETALLESActionPerformed(evt);
            }
        });
        jmDETALLES.add(jmmDETALLES);

        jMENU.add(jmDETALLES);

        jmACERCA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/help.png"))); // NOI18N
        jmACERCA.setText("ACERCA DE ");
        jmACERCA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jmACERCA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmACERCAActionPerformed(evt);
            }
        });

        jmInformacion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmInformacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/information.png"))); // NOI18N
        jmInformacion.setText("Informacion");
        jmInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmInformacionActionPerformed(evt);
            }
        });
        jmACERCA.add(jmInformacion);
        jmACERCA.add(jSeparator12);

        jmCUENTAEDH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmCUENTAEDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edh.png"))); // NOI18N
        jmCUENTAEDH.setText("Ver cuentas EDH");
        jmCUENTAEDH.setPreferredSize(new java.awt.Dimension(162, 38));
        jmCUENTAEDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCUENTAEDHActionPerformed(evt);
            }
        });
        jmACERCA.add(jmCUENTAEDH);

        jmIMPCALSOL.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmIMPCALSOL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calsol.png"))); // NOI18N
        jmIMPCALSOL.setText("Basica Calentador");
        jmIMPCALSOL.setToolTipText("Abrir documento de Instalcion Basica Calentaxdores Solares");
        jmIMPCALSOL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmIMPCALSOLActionPerformed(evt);
            }
        });
        jmACERCA.add(jmIMPCALSOL);

        jmIMPHVAC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmIMPHVAC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/hvac.png"))); // NOI18N
        jmIMPHVAC.setText("Basica HVAC");
        jmIMPHVAC.setToolTipText("Abrir documento de Instalcion Basica HVAC");
        jmIMPHVAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmIMPHVACActionPerformed(evt);
            }
        });
        jmACERCA.add(jmIMPHVAC);
        jmACERCA.add(jSeparator24);

        jmCUENTADHN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmCUENTADHN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/DHN-SMALL-32.png"))); // NOI18N
        jmCUENTADHN.setText("Ver cuentas DHN");
        jmCUENTADHN.setPreferredSize(new java.awt.Dimension(162, 38));
        jmCUENTADHN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCUENTADHNActionPerformed(evt);
            }
        });
        jmACERCA.add(jmCUENTADHN);

        jmPROBLEMAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmPROBLEMAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aprender.png"))); // NOI18N
        jmPROBLEMAS.setText("Resolucion de Problemas");
        jmPROBLEMAS.setPreferredSize(new java.awt.Dimension(162, 38));
        jmPROBLEMAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmPROBLEMASActionPerformed(evt);
            }
        });
        jmACERCA.add(jmPROBLEMAS);

        jMENU.add(jmACERCA);

        setJMenuBar(jMENU);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspCONT, javax.swing.GroupLayout.DEFAULT_SIZE, 1309, Short.MAX_VALUE)
            .addComponent(pnlNOTIFICACIONES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jspCONT, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlNOTIFICACIONES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /*public boolean estacerrado(Object obj)
    {
        JInternalFrame[] activos = CONT.getAllFrames();
        boolean cerrado = true;
        for (JInternalFrame activo : activos) {
            if (activo.getTitle().trim() == null ? obj.toString().trim() == null : activo.getTitle().trim().equals(obj.toString().trim())) {
                //System.out.println("Titulo extraido: "+activo.getTitle().trim()+"\nTitulo comparado: "+obj.toString().trim());                
                cerrado = false;
                //System.out.println(cerrado);
                break;
            }            
        }
        
        return cerrado;
    }*/
    
    public JInternalFrame verificaFrame(Object obj)
    {
        JInternalFrame[] activos = CONT.getAllFrames();
        JInternalFrame x = null;
        for (JInternalFrame activo : activos) {
            if (activo.getTitle().trim() == null ? obj.toString().trim() == null : activo.getTitle().trim().equals(obj.toString().trim())) {               
                x = activo;
                break;
            }            
        }        
        return x;
    }
        
    private void logout()
    {        
        Splash p = new Splash();
        p.setVisible(true);            
        
        this.dispose();

    }
    
    public static JDesktopPane getCONT(){
        return CONT;
}
   
    private void IF(String x) throws Exception
    {        
        JInternalFrame xclase = null;
        String titulo = null;
        switch(x)
        {
            case "AGENDA":       
                titulo = "Agenda de Servicios"; 
                xclase = new VAgendaL(titulo);
                break;
            case "ABONOS":
                titulo = "Registro de Abonos";   
                xclase = new VAbonos(titulo);
                break;   
            case "AHORROS":
                titulo = "Registro de Ahorros";   
                xclase = new VAhorros(titulo);
                break;    
            case "ALMACEN":
                titulo = "Entrega de Materiales";
                xclase = new VAlmacen(titulo);
                break;
            case "ANTENAS":
                titulo = "Antenas BH y Sectoriales";
                xclase = new VAntenasL(titulo);
                break;    
            case "APARIENCIA":
                titulo = "Cambio de apariencia";      
                xclase = new VApariencia(titulo);
                break;            
            case "BACKUP":
                titulo = "Respaldo de DB";      
                xclase = new VBackup(titulo);
                break; 
            case "BONOS":
                titulo = "Bonos Entregados";   
                xclase = new VBonos(titulo);
                break;      
            case "CF":
                titulo = "Registro de Movimientos en CF";
                xclase = new VCF(titulo);
                break;           
            case "CLIENTES":
                titulo = "Registro de Clientes";
                xclase = new VClientes(titulo);
                break;
            case "CPAGOS":
                titulo = "Centros de Pagos";       
                xclase = new VCPagos(titulo);
                break;   
            case "CONCILIAR":
                titulo = "Clasificador de Transacciones Bancarias";  
                xclase = new VConciliar(titulo);
                break;     
            case "CONTRATOS":
                titulo = "Contratos de Internet";               
                xclase = new VContratosL(titulo);
                break;    
            case "CONTRATOSIP":
                titulo = "Registro de IP ACTIVOS"; 
                xclase = new VContratosIP(titulo);
                break;      
            case "CORTES":
                titulo = "Cortes de Caja";     
                xclase = new VCortes(titulo);
                break;       
            case "CORTESD":
                titulo = "Registro de Corte de Caja";  
                xclase = new VRegCorteD(titulo);
                break;         
            case "CORTESDG":
                titulo = "Registro de Corte de Caja Global";     
                xclase = new VRegCorteDG(titulo);
                break;       
            case "CORTESG":
                titulo = "Cortes Globales";       
                xclase = new VCortesG(titulo);
                break;  
            case "COTIZACIONES":
                titulo = "Cotizaciones";    
                xclase = new VCotizaciones(titulo,ESTADO);
                break;      
            case "COTSOL":
                titulo = "Cotizacion de Paneles Solares";   
                xclase = new VCotSolar(titulo);
                break;      
            case "DETALLES":
                titulo = "Detalles";   
                xclase = new VDetalles(titulo);
                break;       
            case "EMPLEADOS":
                titulo = "Registro de Empleados";  
                xclase = new VEmpleados(titulo);
                break; 
            case "EPUESTOS":
                titulo = "Registro de Puestos";  
                xclase = new VEPuestos(titulo);
                break;     
            case "ENTRADAS":
                titulo = "Registro de Entradas";        
                xclase = new VEntradas(titulo);
                break;   
            case "ENTRADASC":
                titulo = "Entradas a Caja de Ahorro";     
                xclase = new VEntradasCaja(titulo);
                break;      
            case "EXTRAS":
                titulo = "Entradas Extras";    
                xclase = new VExtras(titulo);
                break;       
            case "FINANZAS":
                titulo = "Reporte de Finanzas";      
                xclase = new VRepFinanzas(titulo);
                break;       
            case "IMPORTAR":
                titulo = "Importar Tablas";   
                xclase = new VImport(titulo);
                break;   
            case "KITS":
                titulo = "Formado de Kits";     
                xclase = new VKits(titulo);
                break;      
            case "NOMINAS":
                titulo = "Vista de Nominas";  
                xclase = new VNominas(titulo);
                break;    
            case "PAGOS":
                titulo = "Pagos de Internet";  
                xclase = new VPagos(titulo);
                break;
            case "PAGOSVERI":
                titulo = "Verificacion de Pagos"; 
                xclase = new VPagosVeri(titulo,ESTADO);
                break;       
            case "PAQUETES":
                titulo = "Registro de Paquetes de Internet"; 
                xclase = new VPaquetes(titulo);
                break;    
            case "PENDIENTES":
                titulo = "Agenda Global"; 
                xclase = new VPendientesL(titulo,ESTADO);
                break; 
            case "PRESTACIONESP":
                titulo = "Prestaciones Pagadas";   
                xclase = new VPrestacionesP(titulo );
                break;      
            case "PRESTAMOS":
                titulo = "Registro de Prestamos"; 
                xclase = new VPrestamos(titulo);
                break;   
            case "PRODUCTOS":
                titulo = "Registro de Productos";  
                xclase = new VProductos(titulo);
                break;     
            case "PRODUCTOSD":
                titulo = "Detalles de Productos";   
                xclase = new VProductosD(titulo);
                break;      
            case "PROVEEDORES":
                titulo = "Registro de Proveedores"; 
                xclase = new VProveedores(titulo);
                break;          
            case "SALIDAS":
                titulo = "Salidas de Caja";
                xclase = new VSalidas(titulo);
                break;   
            case "SALIDASC":
                titulo = "Salidas de Caja de Ahorro";
                xclase = new VSalidasCaja(titulo);
                break;    
            case "USUARIOS":
                titulo = "Registro de Usuarios";
                xclase = new VUsuarios(titulo);
                break;   
            case "UTILIDADES":
                titulo = "Reporte de Utilidades";
                xclase = new VRepUtilidades(titulo);
                break;     
            case "VACACIONES":
                titulo = "Vacaciones";      
                xclase = new VVacaciones(titulo);
                break;      
            case "VALES":
                titulo = "Registro de Vales de Caja";
                xclase = new VVales(titulo);
                break;      
            case "VEHICULOS":
                titulo = "Registro de vehiculos"; 
                xclase = new VVehiculos(titulo);
                break;   
            case "VEHICULOSSEG":
                titulo = "Seguros vehiculares";   
                xclase = new VVehiculosSEG(titulo);
                break;      
            case "VEHICULOSSER":
                titulo = "Servicios vehiculares";  
                xclase = new VVehiculosSER(titulo);
                break;     
            default:
                break;
        } 

        if(verificaFrame(titulo)==null)
        {
            CONT.add(xclase);
            xclase.setMaximum(true);
            xclase.show();            
        }
        else         
            verificaFrame(titulo).moveToFront();       

    }
    
    private void JD(String x) throws ClassNotFoundException, SQLException, ParseException
    {
        JDialog clase = null;
        switch(x)
        {
            case "INFO":   
                clase = new VInformacion(null,true);
                break;
            case "TOOLS":
                clase = new Tools(null,true);
                break;
            case "QR":
                clase = new LeerQR(null,true);
                break;
            case "CB":
                clase = new LeerCB(null,true);
                break;
            default:
                break;
        }
        clase.setVisible(true);
        /*if(estacerrado(clase))        
            clase.setVisible(true);        
        else        
            JOptionPane.showMessageDialog(this,msjAbierta);    */    
    }
    
    /*private void notificaciones() throws SQLException, Exception
    {
        lblPXV.setText(""+F.total("PAGOS","pagos.REFERENCIA", "NO VERIFICADO"));
    }
    
    private void ejecutor()
    {
         Runnable tarea = () -> {
             try {
                 notificaciones();
             } catch (Exception ex) {
                 Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
             }
         };

        // Retardo inicial de 0 segundos, y se repite cada 10 segundos
        scheduler.scheduleAtFixedRate(tarea, 0, 10, TimeUnit.SECONDS);
    }*/

    private void jmmRespaldarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmRespaldarActionPerformed
        try {
            IF("BACKUP");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmRespaldarActionPerformed

    private void jmmRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmRestaurarActionPerformed
        // TODO add your handling code here:}
        /*if(estacerrado(rst))
        {
            rst = new VRestore();
            CONT.add(rst);
            rst.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(this,msjAbierta);
        }*/
    }//GEN-LAST:event_jmmRestaurarActionPerformed

    private void jmmAPARIENCIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmAPARIENCIAActionPerformed
        try {
            IF("APARIENCIA");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmAPARIENCIAActionPerformed

    private void jmmCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmCerrarSesionActionPerformed
        logout();
    }//GEN-LAST:event_jmmCerrarSesionActionPerformed

    private void jmmANTENASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmANTENASActionPerformed
        try {
            IF("ANTENAS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmANTENASActionPerformed

    private void jmInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmInformacionActionPerformed
        try {
            JD("INFO");
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmInformacionActionPerformed

    private void jmmContratosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmContratosActionPerformed
        try {
            IF("CONTRATOS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmContratosActionPerformed

    private void jmmPaquetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmPaquetesActionPerformed
        try {
            IF("PAQUETES");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmPaquetesActionPerformed

    private void jmmClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmClientesActionPerformed
        try {            
            IF("CLIENTES");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmClientesActionPerformed

    private void jmmPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmPagosActionPerformed
        try {
            IF("PAGOS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmPagosActionPerformed

    private void jmmUBNTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmUBNTActionPerformed
        try {
            JD("TOOLS");
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmUBNTActionPerformed

    private void jmmSALIDASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmSALIDASActionPerformed
        try {
            IF("SALIDAS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmSALIDASActionPerformed

    private void jmmENTRADASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmENTRADASActionPerformed
        try {
            IF("ENTRADAS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmENTRADASActionPerformed

    private void jmmEMPLEADOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmEMPLEADOSActionPerformed
        try {
            IF("EMPLEADOS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmEMPLEADOSActionPerformed

    private void jmmAGENDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmAGENDAActionPerformed
        try {
            IF("AGENDA");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmAGENDAActionPerformed

    private void jmmCORTEDIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmCORTEDIAActionPerformed
        try {
            IF("CORTESD");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jmmCORTEDIAActionPerformed

    private void jmACERCAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmACERCAActionPerformed
        // TODO add your handling code here:
        /*if(estacerrado(impC))
        {
            impC = new VImpCuenta(null,true);
            impC.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(this,msjAbierta);
            //info.moveToFront();
        }*/
    }//GEN-LAST:event_jmACERCAActionPerformed

    private void jmCUENTADHNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCUENTADHNActionPerformed
        F.abrirArchivo("\\"+"\\" +G.ip +"\\dhn\\MACHOTES\\cuentas-dhn.png");
    }//GEN-LAST:event_jmCUENTADHNActionPerformed

    private void jmmCALCULADORAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmCALCULADORAActionPerformed
        try {
            F.abrirApp("C:\\WINDOWS\\system32\\CALC.EXE");
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmCALCULADORAActionPerformed

    private void jmmPRESTAMOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmPRESTAMOSActionPerformed
        try {
            IF("PRESTAMOS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmPRESTAMOSActionPerformed

    private void jmmAHORROSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmAHORROSActionPerformed
        try {
            IF("AHORROS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmAHORROSActionPerformed

    private void jmmAbonosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmAbonosActionPerformed
        try {
            IF("ABONOS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmAbonosActionPerformed

    private void jmmDETALLESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmDETALLESActionPerformed
        try {
            IF("DETALLES");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmDETALLESActionPerformed

    private void jmmNOMINASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmNOMINASActionPerformed
        try {
            IF("NOMINAS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmNOMINASActionPerformed

    private void jmmREPFINANZASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmREPFINANZASActionPerformed
        try {
            IF("FINANZAS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmREPFINANZASActionPerformed

    private void jmmPROVEEDORESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmPROVEEDORESActionPerformed
        try {
            IF("PROVEEDORES");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmPROVEEDORESActionPerformed

    private void jmmPROGRAMARPAGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmPROGRAMARPAGOActionPerformed
        // TODO add your handling code here:
        /*if(estacerrado(papro))
        {
            try {
                papro = new VPagosProgramados();
            } catch (ClassNotFoundException | SQLException ex) {a
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            CONT.add(papro);
            try {
                papro.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            papro.show();
        }
        else
        {
            //JOptionPane.showMessageDialog(this,msjAbierta);
            papro.moveToFront();
        }*/
    }//GEN-LAST:event_jmmPROGRAMARPAGOActionPerformed

    private void jmmSALIDASCAJAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmSALIDASCAJAActionPerformed
        try {
            IF("SALIDASC");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmSALIDASCAJAActionPerformed

    private void jmmENTRADASCAJAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmENTRADASCAJAActionPerformed
        try {
            IF("ENTRADASC");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmENTRADASCAJAActionPerformed

    private void jmmCORTESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmCORTESActionPerformed
        try {
            IF("CORTES");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmCORTESActionPerformed

    private void jmmProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmProductosActionPerformed
        try {
            IF("PRODUCTOS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmProductosActionPerformed

    private void jmmUSUARIOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmUSUARIOSActionPerformed
        try {
            IF("USUARIOS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmUSUARIOSActionPerformed

    private void leerQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerQRActionPerformed
        try {
            JD("QR");
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_leerQRActionPerformed

    private void jmmALMACENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmALMACENActionPerformed
        try {
            IF("ALMACEN");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmALMACENActionPerformed

    private void jmmUTILIDADESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmUTILIDADESActionPerformed
        try {
            IF("UTILIDADES");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmUTILIDADESActionPerformed

    private void jmmCPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmCPagosActionPerformed
        try {
            IF("CPAGOS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmCPagosActionPerformed

    private void jmmVACACIONESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmVACACIONESActionPerformed
        try {
            IF("VACACIONES");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmVACACIONESActionPerformed

    private void JMMBONOSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMMBONOSDActionPerformed
        try {
            IF("BONOS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMMBONOSDActionPerformed

    private void jmmPRESTACIONESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmPRESTACIONESActionPerformed
        try {
            IF("PRESTACIONESP");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmPRESTACIONESActionPerformed

    private void jmmCARRITOPREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmCARRITOPREActionPerformed
        try {
            IF("KITS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmCARRITOPREActionPerformed

    private void jmmIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmIPActionPerformed
        try {                
            IF("CONTRATOSIP");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmIPActionPerformed

    private void jmmPRODUCTOSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmPRODUCTOSDActionPerformed
        try {
            IF("PRODUCTOSD");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmPRODUCTOSDActionPerformed

    private void jmCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCBActionPerformed
        try {
            JD("CB");
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmCBActionPerformed

    private void jmmPagosVeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmPagosVeriActionPerformed
        try {
            ESTADO = "NO VERIFICADO";
            IF("PAGOSVERI");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmPagosVeriActionPerformed

    private void jmVEHICULOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmVEHICULOSActionPerformed
        try {
            IF("VEHICULOS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmVEHICULOSActionPerformed

    private void jmIMPCALSOLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmIMPCALSOLActionPerformed
        F.abrirArchivo("\\"+"\\" +G.ip +"\\dhn\\MACHOTES\\CALSOLapoyo.pdf");
    }//GEN-LAST:event_jmIMPCALSOLActionPerformed

    private void jmIMPHVACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmIMPHVACActionPerformed
        F.abrirArchivo("\\"+"\\" +G.ip +"\\dhn\\MACHOTES\\HVACapoyo.pdf");
    }//GEN-LAST:event_jmIMPHVACActionPerformed

    private void jmCUENTAEDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCUENTAEDHActionPerformed
        F.abrirArchivo("\\"+"\\" +G.ip +"\\dhn\\MACHOTES\\cuentas-edh.png");
    }//GEN-LAST:event_jmCUENTAEDHActionPerformed

    private void jmPROBLEMASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmPROBLEMASActionPerformed
        F.abrirArchivo("\\"+"\\" +G.ip +"\\dhn\\MACHOTES\\problemas.jpg");
    }//GEN-LAST:event_jmPROBLEMASActionPerformed

    private void jmmSOLARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmSOLARActionPerformed
        try {
            IF("COTSOL");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmSOLARActionPerformed

    private void jmVEHICULOSSERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmVEHICULOSSERActionPerformed
        try {
            IF("VEHICULOSSER");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmVEHICULOSSERActionPerformed

    private void jmVEHOCULOSSEGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmVEHOCULOSSEGActionPerformed
        try {
            IF("VEHICULOSSEG");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmVEHOCULOSSEGActionPerformed

    private void jmmCONCILIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmCONCILIARActionPerformed
        try {
            IF("CONCILIAR");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmCONCILIARActionPerformed

    private void jmmMONITOREOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmMONITOREOActionPerformed
        try {
            if(!mon.isVisible())
            {
                mon = new Monitoreo(PRIVILEGIOS);

                mon.setLocationRelativeTo(null);
                mon.setVisible(true);
            }
            else 
            {
                mon.toFront(); // Opcional: enfoca la ventana si ya está visible
                mon.requestFocusInWindow(); // Opcional: pide el foco para la ventana
            }
            
 
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmMONITOREOActionPerformed

    private void jmmIMPORTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmIMPORTARActionPerformed
        try {        
            IF("IMPORTAR");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmIMPORTARActionPerformed

    private void jmmVALESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmVALESActionPerformed
        try {
            IF("VALES");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmVALESActionPerformed

    private void jmmCFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmCFActionPerformed
        try {
            IF("CF");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmCFActionPerformed

    private void jmmCORTEGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmCORTEGActionPerformed
        try {
            IF("CORTESDG");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmCORTEGActionPerformed

    private void jmmGLOBALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmGLOBALActionPerformed
        try {
            IF("CORTESG");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmGLOBALActionPerformed

    private void jmmEXTRASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmEXTRASActionPerformed
        try {
            IF("EXTRAS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmEXTRASActionPerformed

    private void jmmCOTIZACIONESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmCOTIZACIONESActionPerformed
        try {
            ESTADO = "PENDIENTE DE ENVIO";
            IF("COTIZACIONES");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmCOTIZACIONESActionPerformed

    private void jmmCARGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmCARGOActionPerformed
        try {
            IF("EPUESTOS");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmCARGOActionPerformed

    private void lblLPXVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLPXVMouseClicked
        try {
            ESTADO = "NO VERIFICADO";
            IF("PAGOSVERI");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblLPXVMouseClicked

    private void lblLPRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLPRMouseClicked
        try {
            ESTADO = "RECHAZADO";
            IF("PAGOSVERI");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblLPRMouseClicked

    private void lblLPEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLPEMouseClicked
        try {
            ESTADO = "VERIFICADO SIN ENVIAR";
            IF("PAGOSVERI");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblLPEMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        try {
            IF("AGENDA");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void lblLCPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLCPMouseClicked
        try {
            ESTADO = "-COTIZACION-";
            IF("PENDIENTES");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblLCPMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        try {
            ESTADO = "PENDIENTE DE ENVIO";
            IF("COTIZACIONES");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        try {
            ESTADO = "ENVIADA";
            IF("COTIZACIONES");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                //new Principal().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane CONT;
    private javax.swing.JMenuItem JMMBONOSD;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMENU;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator18;
    private javax.swing.JPopupMenu.Separator jSeparator19;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator20;
    private javax.swing.JPopupMenu.Separator jSeparator21;
    private javax.swing.JPopupMenu.Separator jSeparator22;
    private javax.swing.JPopupMenu.Separator jSeparator23;
    private javax.swing.JPopupMenu.Separator jSeparator24;
    private javax.swing.JPopupMenu.Separator jSeparator25;
    private javax.swing.JPopupMenu.Separator jSeparator26;
    private javax.swing.JPopupMenu.Separator jSeparator27;
    private javax.swing.JPopupMenu.Separator jSeparator28;
    private javax.swing.JPopupMenu.Separator jSeparator29;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator30;
    private javax.swing.JPopupMenu.Separator jSeparator31;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JMenu jmACERCA;
    private javax.swing.JMenu jmArchivo;
    public static javax.swing.JMenu jmCAJA;
    private javax.swing.JMenuItem jmCB;
    private javax.swing.JMenuItem jmCUENTADHN;
    private javax.swing.JMenuItem jmCUENTAEDH;
    private javax.swing.JMenu jmDETALLES;
    public static javax.swing.JMenu jmELECTRONICS;
    public static javax.swing.JMenu jmEMPLEADOS;
    private javax.swing.JMenu jmHerramientas;
    private javax.swing.JMenuItem jmIMPCALSOL;
    private javax.swing.JMenuItem jmIMPHVAC;
    private javax.swing.JMenuItem jmInformacion;
    private javax.swing.JMenu jmInformes;
    public static javax.swing.JMenu jmInternet;
    public static javax.swing.JMenu jmMOVIMIENTOS;
    private javax.swing.JMenu jmPRESTACIONES;
    private javax.swing.JMenuItem jmPROBLEMAS;
    public static javax.swing.JMenu jmPROVEEDORES;
    private javax.swing.JMenu jmRegistro;
    public static javax.swing.JMenu jmUSUARIOS;
    private javax.swing.JMenuItem jmVEHICULOS;
    private javax.swing.JMenuItem jmVEHICULOSSER;
    private javax.swing.JMenuItem jmVEHOCULOSSEG;
    private javax.swing.JMenuItem jmmAGENDA;
    private javax.swing.JMenuItem jmmAHORROS;
    private javax.swing.JMenuItem jmmALMACEN;
    private javax.swing.JMenuItem jmmANTENAS;
    private javax.swing.JMenuItem jmmAPARIENCIA;
    private javax.swing.JMenuItem jmmAbonos;
    public static javax.swing.JMenuItem jmmCAJA;
    private javax.swing.JMenuItem jmmCALCULADORA;
    private javax.swing.JMenuItem jmmCARGO;
    private javax.swing.JMenuItem jmmCARRITOPRE;
    public static javax.swing.JMenuItem jmmCF;
    public static javax.swing.JMenuItem jmmCONCILIAR;
    public static javax.swing.JMenuItem jmmCORTEDIA;
    public static javax.swing.JMenuItem jmmCORTEG;
    private javax.swing.JMenuItem jmmCORTES;
    private javax.swing.JMenuItem jmmCOTIZACIONES;
    private javax.swing.JMenuItem jmmCPagos;
    private javax.swing.JMenuItem jmmCerrarSesion;
    public static javax.swing.JMenuItem jmmClientes;
    private javax.swing.JMenuItem jmmContratos;
    private javax.swing.JMenuItem jmmDETALLES;
    private javax.swing.JMenuItem jmmEMPLEADOS;
    private javax.swing.JMenuItem jmmENTRADAS;
    private javax.swing.JMenuItem jmmENTRADASCAJA;
    public static javax.swing.JMenuItem jmmEXTRAS;
    private javax.swing.JMenuItem jmmGARANTIAS;
    public static javax.swing.JMenuItem jmmGLOBAL;
    private javax.swing.JMenuItem jmmGRUPOS;
    private javax.swing.JMenuItem jmmHERRAMIENTAS;
    public static javax.swing.JMenuItem jmmIMPORTAR;
    public static javax.swing.JMenuItem jmmIP;
    public static javax.swing.JMenuItem jmmMONITOREO;
    private javax.swing.JMenuItem jmmNOMINAS;
    private javax.swing.JMenuItem jmmPAGOPROVEEDORES;
    private javax.swing.JMenuItem jmmPRESTACIONES;
    private javax.swing.JMenuItem jmmPRESTAMOS;
    private javax.swing.JMenuItem jmmPRODUCTOSD;
    private javax.swing.JMenuItem jmmPROGRAMARPAGO;
    private javax.swing.JMenuItem jmmPROVEEDORES;
    private javax.swing.JMenuItem jmmPagos;
    private javax.swing.JMenuItem jmmPagosVeri;
    private javax.swing.JMenuItem jmmPaquetes;
    public static javax.swing.JMenuItem jmmProductos;
    public static javax.swing.JMenuItem jmmREPFINANZAS;
    private javax.swing.JMenuItem jmmREPORTES;
    public static javax.swing.JMenuItem jmmRespaldar;
    public static javax.swing.JMenuItem jmmRestaurar;
    private javax.swing.JMenuItem jmmSALIDAS;
    private javax.swing.JMenuItem jmmSALIDASCAJA;
    public static javax.swing.JMenuItem jmmSOLAR;
    public static javax.swing.JMenuItem jmmUBNT;
    private javax.swing.JMenuItem jmmUNIFORMES;
    private javax.swing.JMenuItem jmmUSUARIOS;
    public static javax.swing.JMenuItem jmmUTILIDADES;
    private javax.swing.JMenuItem jmmVACACIONES;
    public static javax.swing.JMenuItem jmmVALES;
    public static javax.swing.JMenu jmmVEHICULOS;
    public static javax.swing.JScrollPane jspCONT;
    public static javax.swing.JLabel lblCE;
    public static javax.swing.JLabel lblCP;
    public static javax.swing.JLabel lblCPE;
    private javax.swing.JLabel lblLCP;
    private javax.swing.JLabel lblLPE;
    private javax.swing.JLabel lblLPR;
    private javax.swing.JLabel lblLPXV;
    public static javax.swing.JLabel lblPR;
    public static javax.swing.JLabel lblPXE;
    public static javax.swing.JLabel lblPXV;
    public static javax.swing.JLabel lblSD;
    private javax.swing.JMenuItem leerQR;
    public static javax.swing.JPanel pnlNOTIFICACIONES;
    // End of variables declaration//GEN-END:variables
}

class validador {
 
    public static java.util.HashMap<String, javax.swing.JInternalFrame> jIframes = new java.util.HashMap<>();

    public static void addJIframe(String key, javax.swing.JInternalFrame jiframe) {
        jIframes.put(key, jiframe);
    }

    public static javax.swing.JInternalFrame getJInternalFrame(String key) {
        return jIframes.get(key);
    }
}