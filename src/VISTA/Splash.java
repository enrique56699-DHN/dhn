package VISTA;

import CONTROLADOR.ControlCutservices;
import CONTROLADOR.ControlPrivilegios;
import CONTROLADOR.ControlUsuarios;
import CONTROLADOR.F;
import DHNv2.G;
import DHNv2.config;
import MODELO.ModeloCutservices;
import MODELO.ModeloUsuarios;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Splash extends javax.swing.JFrame implements ActionListener
{
    private Timer timer;
    private int i=0;
    private String NOMBRE, PASSWORD, PRIVILEGIOS, PASSWORDe;
    private File ficheroServer = new File(G.rutaServidor+"version.txt");
    
    String item = null;
    Principal main = null;
    
    private String versionactual = config.vector.get(17).toString();
    //private final float versionfinal = Float.parseFloat(vector1.get(1).toString());*/   
        
    //CREAR BASE DE DATOS
    /*private void crearBase()
    {
        lbMensaje.setText("Creando Base de Datos....");
        System.out.println("La base ha sido creada "+getDirectorio());
        File FOLDERFACTURAS = new File (getDirectorio()+G.separador+"FACTURAS");
        File FOLDERECIBOS = new File (getDirectorio()+G.separador+"RECIBOS");
        File FOLDERREPORTES = new File (getDirectorio()+G.separador+"REPORTES");
        File FOLDERRESPALDO = new File (getDirectorio()+G.separador+"RESPALDOS DB");
        File FOLDERDOCUMENTOS = new File (getDirectorio()+G.separador+"DOCUMENTOS");
        File FOLDERAHORROS = new File (getDirectorio()+G.separador+"AHORROS");
        File FOLDERABONOS = new File (getDirectorio()+G.separador+"ABONOS");
        File FOLDERPRESTAMOS = new File (getDirectorio()+G.separador+"PRESTAMOS");
        File FOLDERNOMINAS = new File (getDirectorio()+G.separador+"NOMINAS");
        File FOLDERENTRADAS = new File (getDirectorio()+G.separador+"ENTRADAS");
        File FOLDERSALIDAS = new File (getDirectorio()+G.separador+"SALIDAS");

        FOLDERFACTURAS.mkdirs();
        FOLDERECIBOS.mkdirs();
        FOLDERREPORTES.mkdirs();
        FOLDERRESPALDO.mkdirs();
        FOLDERDOCUMENTOS.mkdirs();
        FOLDERAHORROS.mkdirs();
        FOLDERABONOS.mkdirs();
        FOLDERPRESTAMOS.mkdirs();
        FOLDERNOMINAS.mkdirs();
        FOLDERENTRADAS.mkdirs();
        FOLDERSALIDAS.mkdirs();
    
    }*/
    
    //TOMAR DIRECTORIO DE LA BASE DE DATOS
    /*private String getDirectorio()
    {
        if(so.equals("Linux"))
        {
            String carL="C:"+G.separador+".DHN";
            //String carL=appPath;
            return carL;
        }
        else
        {        
            String carW="C:"+G.separador+"DHN";
            //String carW=appPath;
            return carW;
        }
    
    }*/
    
        
    public Splash()
    {
        initComponents();
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                new config().cerrarApp();
                System.exit(0);  
            }
        });
        setLocationRelativeTo(null);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/Icono.png")); 
        setIconImage(icon);
       try
            {
                
        timer = new Timer(10,new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent o)
            {
                //contador
                i+=10;
                //al progresbar se le pasa como parametro el contador
                pBar.setValue(i);
                
                while (i==70)
                {
                    lbMensaje.setText("Actualizando Registros..........");
                    try 
                    {
                        //System.out.println(G.NTP());
                        //System.out.println(G.FECHA(G.hoy(), true));
                        if(F.cut_services("CUTSERVICES", G.FECHA(G.hoy(),false)) == 0)
                        {
                            F.automatizacion("ACTIVO",null,0,null);
                            ModeloCutservices mdl = new ModeloCutservices();
                            mdl.setFECHA(G.FECHA(G.hoy(),false));
                            
                            ControlCutservices ctrl = new ControlCutservices();                                                        
                            ctrl.guardarRegistro(mdl);
                        }
                        G.hoy();
                        chckUPD();
                        lbMensaje.setText("Excluyendo ACTUALIZACION..........");
                    } catch (Exception ex) 
                    {
                        Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    i+=1;
                    
                }
            
            try {
                lbMensaje.setText("Terminando Configuracion..........Version actual: "+versionactual);
                //cuando llega a 100 pone un stop
                cek();
            } catch (Exception ex) {
                Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        });
        
        timer.start();
        }
        catch(Exception e)
            {
                System.out.println("ERROR EN SPLASH "+e);
            }
    }
    
    private void chckUPD() throws Exception
    {
        lbMensaje.setText("Revisando actualizaciones de sistema");
        leer();
    }
    
    public void leer() throws Exception
    {
        String linea = "0";        
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader( new FileReader( ficheroServer ) );            
            while(bufferedReader.ready()){
                linea = bufferedReader.readLine();            
        }
        }catch (IOException e) {
            System.err.println( e.getMessage() );
        }
        
        //versionnueva = linea;
        if(!linea.equals("0"))
        {
            if( !versionactual.equals(linea))
            {

                JOptionPane.showMessageDialog(this, "La version se actualizara de "+versionactual+" a la "+linea);
                try
                {
                    Runtime obj = Runtime.getRuntime();
                    //obj.exec("C:\\DHN\\DHNUPD.exe");
                    obj.exec(G.rutaLocal+"\\DHNUPD.exe");
                }
                catch(IOException e)
                    {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                this.dispose();

                cek();
                new config().cerrarApp();
                System.exit(0); 
           }
            else
                lbMensaje.setText("No hay versiones nuevas");
        }
        else
            JOptionPane.showMessageDialog( null, "ERROR DE CONEXION CON EL SERVIDOR DE ACTUALIZACION", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
        
                
    }
    
    //metodo para hacer que haga un stop antes de llegar a 100
    private void cek() throws Exception
    {  
        if(pBar.getPercentComplete()==1.0){
            timer.stop();            
        }        
    }
    
    /*private void iniciar() throws Exception
    {        
        try
        {
            //int IDUSER;
            NOMBRE=txtUSER.getText();            
            PASSWORD=String.copyValueOf(txtPASS.getPassword());

            ModeloUsuarios mdl;
            ControlUsuarios ctrl = new ControlUsuarios();

            mdl = ctrl.getRegistros(0, NOMBRE);
            mdl.getIDUSUARIO();
            G.IDUSER = mdl.getIDUSUARIO();
            PASSWORDe = mdl.getPASSWORD();
            PRIVILEGIOS = mdl.getPRIVILEGIOS();
        }
        catch(Exception e)
                {
                    JOptionPane.showMessageDialog( null, "ERROR USUARIO NO ENCONTRADO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                }
            if(PASSWORD.equals(PASSWORDe)||PASSWORD.equals("."))
            {
                ControlPrivilegios ctrlP = new ControlPrivilegios();
                ArrayList lista;
                lista = (ArrayList) ctrlP.getRegistros(PRIVILEGIOS);
                
                main = new Principal(PRIVILEGIOS);      
                
                for(int in=0; in<lista.size(); in++)
                {
                    item = lista.get(in).toString();
                    switch(item)
                    {
                        case "CLIENTES":                            
                            Principal.jmmClientes.setEnabled(true);
                            break;
                        case "EMPLEADOS":
                            Principal.jmEMPLEADOS.setEnabled(true);
                            break;                       
                        case "CAMBIOCAJA":
                            Principal.jmmCAJA.setEnabled(true);
                            break;
                        case "USUARIOS":
                            Principal.jmUSUARIOS.setEnabled(true);
                            break;
                        case "CAJAAHORRO":
                            Principal.jmCAJA.setEnabled(true);
                            break;
                        case "CORTES":
                            Principal.jmmCORTEDIA.setEnabled(true);
                            break;
                        case "FINANZAS":
                            Principal.jmmREPFINANZAS.setEnabled(true);
                            break;
                        case "MOVIMIENTOS":
                            Principal.jmMOVIMIENTOS.setEnabled(true);
                            break;
                        case "PRODUCTOS":
                            Principal.jmmProductos.setEnabled(true);
                            break;
                        case "ELECTRONICS":
                            Principal.jmELECTRONICS.setEnabled(true);
                            break;
                        case "INTERNET":
                            Principal.jmInternet.setEnabled(true);
                            break;
                        case "TOOLS":
                            Principal.jmmUBNT.setEnabled(true);
                            break;
                        case "BACKUP":
                            Principal.jmmRespaldar.setEnabled(true);
                            break;
                        case "PROVEEDORES":
                            Principal.jmPROVEEDORES.setEnabled(true);
                            break;
                        case "UTILIDADES":
                            Principal.jmmUTILIDADES.setEnabled(true);
                            break;
                        case "VEHICULOS":
                            Principal.jmmVEHICULOS.setEnabled(true);
                            break;
                        case "CAMBIO_IP":
                            Principal.jmmIP.setEnabled(true);
                            break;
                        case "COTSOLAR":
                            Principal.jmmSOLAR.setEnabled(true);
                            break;  
                        case "CONCILIAR":
                            Principal.jmmCONCILIAR.setEnabled(true);
                            break;    
                        case "MONITOREO":
                            Principal.jmmMONITOREO.setEnabled(true);
                            break;
                        case "IMPORTAR":
                            Principal.jmmIMPORTAR.setEnabled(true);
                            break;    
                        case "RESTORE":
                            Principal.jmmRestaurar.setEnabled(true);
                            break;
                        case "VALES":
                            Principal.jmmVALES.setEnabled(true);
                            break;
                        case "CF":
                            Principal.jmmCF.setEnabled(true);
                            break;
                        case "GLOBAL":
                            Principal.jmmGLOBAL.setEnabled(true);
                            break;
                        case "CORTEG":
                            Principal.jmmCORTEG.setEnabled(true);
                            break;   
                        case "EXTRAS":
                            Principal.jmmEXTRAS.setEnabled(true);
                            break;       
                        default:
                            break;
                    }
                }            
                
                main.setVisible(true);
                this.dispose();
            }
            else
                JOptionPane.showMessageDialog( null, "ERROR PASSWORD NO VALIDO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
    }*/
    
    private void iniciar() throws Exception
    {
        // NOTA DE SEGURIDAD: este metodo ya NO compara PASSWORD contra el valor
        // guardado directamente, y ya NO existe password maestro/comodin (".").
        // Toda la validacion pasa por ControlUsuarios.autenticar(), que usa BCrypt
        // y migra automaticamente cuentas viejas en texto plano al primer login
        // correcto. Ver CONTROLADOR/ControlUsuarios.java y CONTROLADOR/PasswordUtil.java.
        NOMBRE = txtUSER.getText();
        PASSWORD = String.copyValueOf(txtPASS.getPassword());

        ModeloUsuarios mdl = null;
        try
        {
            ControlUsuarios ctrl = new ControlUsuarios();
            mdl = ctrl.autenticar(NOMBRE, PASSWORD);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERROR CONSULTANDO USUARIO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (mdl == null)
        {
            JOptionPane.showMessageDialog(null, "USUARIO O PASSWORD INCORRECTOS", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        G.IDUSER = mdl.getIDUSUARIO();
        PRIVILEGIOS = mdl.getPRIVILEGIOS();

        {
                ControlPrivilegios ctrlP = new ControlPrivilegios();
                ArrayList lista;
                lista = (ArrayList) ctrlP.getRegistros(PRIVILEGIOS);
                
                main = new Principal(PRIVILEGIOS);      
                
                for(int in=0; in<lista.size(); in++)
                {
                    item = lista.get(in).toString();
                    switch(item)
                    {
                        case "CLIENTES":                            
                            Principal.jmmClientes.setEnabled(true);
                            break;
                        case "EMPLEADOS":
                            Principal.jmEMPLEADOS.setEnabled(true);
                            break;                       
                        case "CAMBIOCAJA":
                            Principal.jmmCAJA.setEnabled(true);
                            break;
                        case "USUARIOS":
                            Principal.jmUSUARIOS.setEnabled(true);
                            break;
                        case "CAJAAHORRO":
                            Principal.jmCAJA.setEnabled(true);
                            break;
                        case "CORTES":
                            Principal.jmmCORTEDIA.setEnabled(true);
                            break;
                        case "FINANZAS":
                            Principal.jmmREPFINANZAS.setEnabled(true);
                            break;
                        case "MOVIMIENTOS":
                            Principal.jmMOVIMIENTOS.setEnabled(true);
                            break;
                        case "PRODUCTOS":
                            Principal.jmmProductos.setEnabled(true);
                            break;
                        case "ELECTRONICS":
                            Principal.jmELECTRONICS.setEnabled(true);
                            break;
                        case "INTERNET":
                            Principal.jmInternet.setEnabled(true);
                            break;
                        case "TOOLS":
                            Principal.jmmUBNT.setEnabled(true);
                            break;
                        case "BACKUP":
                            Principal.jmmRespaldar.setEnabled(true);
                            break;
                        case "PROVEEDORES":
                            Principal.jmPROVEEDORES.setEnabled(true);
                            break;
                        case "UTILIDADES":
                            Principal.jmmUTILIDADES.setEnabled(true);
                            break;
                        case "VEHICULOS":
                            Principal.jmmVEHICULOS.setEnabled(true);
                            break;
                        case "CAMBIO_IP":
                            Principal.jmmIP.setEnabled(true);
                            break;
                        case "COTSOLAR":
                            Principal.jmmSOLAR.setEnabled(true);
                            break;  
                        case "CONCILIAR":
                            Principal.jmmCONCILIAR.setEnabled(true);
                            break;    
                        case "MONITOREO":
                            Principal.jmmMONITOREO.setEnabled(true);
                            break;
                        case "IMPORTAR":
                            Principal.jmmIMPORTAR.setEnabled(true);
                            break;    
                        case "RESTORE":
                            Principal.jmmRestaurar.setEnabled(true);
                            break;
                        case "VALES":
                            Principal.jmmVALES.setEnabled(true);
                            break;
                        case "CF":
                            Principal.jmmCF.setEnabled(true);
                            break;
                        case "GLOBAL":
                            Principal.jmmGLOBAL.setEnabled(true);
                            break;
                        case "CORTEG":
                            Principal.jmmCORTEG.setEnabled(true);
                            break;   
                        case "EXTRAS":
                            Principal.jmmEXTRAS.setEnabled(true);
                            break;       
                        default:
                            break;
                    }
                }            
                
                main.setVisible(true);
                this.dispose();
            }
    }
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pBar = new javax.swing.JProgressBar();
        lbMensaje = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnlLogin = new javax.swing.JPanel();
        btnACEPTAR = new javax.swing.JButton();
        txtPASS = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        txtUSER = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DHN v2");
        setForeground(java.awt.Color.white);
        setMinimumSize(new java.awt.Dimension(450, 450));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(450, 450));
        jPanel1.setLayout(null);

        pBar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pBar.setForeground(new java.awt.Color(255, 0, 0));
        pBar.setDoubleBuffered(true);
        pBar.setStringPainted(true);
        jPanel1.add(pBar);
        pBar.setBounds(0, 390, 670, 30);

        lbMensaje.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lbMensaje.setForeground(new java.awt.Color(153, 0, 0));
        lbMensaje.setText("Cargando..");
        jPanel1.add(lbMensaje);
        lbMensaje.setBounds(0, 370, 450, 15);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/DHN1.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 0, 440, 370);

        pnlLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnACEPTAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnACEPTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/accept.png"))); // NOI18N
        btnACEPTAR.setText("ACEPTAR");
        btnACEPTAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnACEPTAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnACEPTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnACEPTARActionPerformed(evt);
            }
        });
        pnlLogin.add(btnACEPTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 200, -1));

        txtPASS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPASS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPASS.setToolTipText("Password");
        txtPASS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contraseña:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtPASS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPASSKeyPressed(evt);
            }
        });
        pnlLogin.add(txtPASS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 200, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/login_m.png"))); // NOI18N
        pnlLogin.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        txtUSER.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtUSER.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUSER.setToolTipText("Usuario");
        txtUSER.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuario:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        pnlLogin.add(txtUSER, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 200, -1));

        jPanel1.add(pnlLogin);
        pnlLogin.setBounds(450, 0, 220, 390);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 680, 420);

        setSize(new java.awt.Dimension(669, 422));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnACEPTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnACEPTARActionPerformed
        try {
            // TODO add your handling code here:
            iniciar();
        } catch (Exception ex) {
            Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnACEPTARActionPerformed

    private void txtPASSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPASSKeyPressed
        if(F.ENTER(evt))
            try {
                iniciar();
        } catch (Exception ex) {
            Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtPASSKeyPressed
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            Splash a;
            @Override
            public void run() {
                a = new Splash();
                a.setVisible(true);
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnACEPTAR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbMensaje;
    private javax.swing.JProgressBar pBar;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPasswordField txtPASS;
    private javax.swing.JTextField txtUSER;
    // End of variables declaration//GEN-END:variables
}
