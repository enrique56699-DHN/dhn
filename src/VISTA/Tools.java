/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package VISTA;

import CONTROLADOR.ControlContratos;
import CONTROLADOR.ControlPaquetes;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloContratos;
import MODELO.ModeloPaquetes;
import com.jcraft.jsch.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.io.*;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Enrique Orozco Alcala
 */
public class Tools extends javax.swing.JDialog {

    //private String appPath = System.getProperties().getProperty("user.dir");
    private String remoteA = "/tmp/";
    private String local = G.rutaLocal+"\\";
    //VARIABLES ARCHIVO DE CONFIG
    private String fileName = "system.cfg";
    private BufferedReader br = null;
    private String texto=null;

    private String [] dato = new String [2];
    
    private String user = null;
    private String host = null;
    private String password = null;
    
    private String userCCR = null;
    private String hostCCR = null;
    private String passwordCCR = null;
    
    private int port = 22;
    
    private String keyFilePath = null;
    private String keyPassword = null;

    private Session session = null;
    private InetAddress in ;
    private boolean check = false;
    
    private int IDCONTRATO, IDPAQUETE;
   
    private ModeloContratos mc;
    
    //Variable para archivo a desconectar
    //private File lista = null;
    //private BufferedWriter blista= null;

    private String IP;

    /**
     * Creates new form Tools
     * @param parent
     * @param modal
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    
    
    public Tools(java.awt.Frame parent, boolean modal) throws ClassNotFoundException, SQLException {
        super(parent, modal);   
        initComponents();
        extras();   
        F.teclas(this);
    }
    
    private void extras(){
        view(false);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/activar.png")); 
        setIconImage(icon);
        setLocationRelativeTo(null);
    }
    
    private void proceso(String str, String ventana)
    {
        switch(ventana)
        {
            case "eth0":
                txtSSH.append(System.getProperty("line.separator"));
                txtSSH.append(str);
                txtSSH.update(txtSSH.getGraphics());
                int ultimalineaSSH = txtSSH.getDocument().getLength();
                txtSSH.setCaretPosition(ultimalineaSSH);
                break;
            case "proceso":
                txtPROCESO.append(System.getProperty("line.separator"));
                txtPROCESO.append(str);
                txtPROCESO.update(txtPROCESO.getGraphics());
                int ultimalinea = txtPROCESO.getDocument().getLength();
                txtPROCESO.setCaretPosition(ultimalinea);
                break;
            case "ath0":
                txtAVANZADOS.append(System.getProperty("line.separator"));
                txtAVANZADOS.append(str);
                txtAVANZADOS.update(txtAVANZADOS.getGraphics());
                int ultimalineaAVA = txtAVANZADOS.getDocument().getLength();
                txtAVANZADOS.setCaretPosition(ultimalineaAVA);
                break;
            default: break;
        }
       
    }
    
    private void habilitado(String AC)
    {
        boolean accion = false;
        switch(AC)
        {
            case "LIMPIAR":
                accion = true;                
                this.lblEstatusC.setText("ANTENA");
                this.lblBlockC.setText("ESTATUS");
                this.lblMB.setText("MEGAS");
                this.lblNAT.setText("ACTIVO");
                this.lblNOMBREHOST.setText("NOMBRE DE LA ANTENA");
                this.lblVELOCIDAD.setText("");

                this.lblEstatusC.setOpaque(true);
                this.lblBlockC.setOpaque(true);
                this.lblMB.setOpaque(true);
                this.lblNAT.setOpaque(true);
                this.lblNOMBREHOST.setOpaque(true);
                this.lblVELOCIDAD.setOpaque(true);
                
                this.lblEstatusC.setForeground(Color.black);
                this.lblBlockC.setForeground(Color.black);
                this.lblMB.setForeground(Color.black);
                this.lblNAT.setForeground(Color.black);
                this.lblNOMBREHOST.setForeground(Color.black);       
                this.btnConectarH.setEnabled(!accion);
                this.btnDesconectarH.setEnabled(!accion);
                this.btnConectar.setEnabled(!accion);
                this.btnDesconectar.setEnabled(!accion);
                
                this.pnlAVANZADOS.setVisible(false);
                this.pnlPROPIEDADES.setVisible(false);
                pack();
                
                this.txtPROCESO.setText("");
                this.txtSSH.setText("");
                break;
            case "BLOQUEAR":
                accion = false;
                break;
            default: break;
        }
        this.txtIP.setEnabled(accion);
        this.txtIP.requestFocus();
        this.txtUser.setEnabled(accion);
        this.txtPass.setEnabled(accion);
        this.btnComprobar.setEnabled(accion);
        this.txtUserCCR.setEnabled(accion);
        this.txtPassCCR.setEnabled(accion);
        
        this.btnLIMPIAR.setEnabled(!accion);
        this.btnREINICIAR.setEnabled(!accion);
        this.btnETHPORT.setEnabled(!accion);
        this.btnBANDWIDTH.setEnabled(!accion);
        this.pnlPROPIEDADES.setVisible(!accion);
        
    }

    private void ping(String accion) throws UnknownHostException, IOException, JSchException
    {
        txtPropiedades.setText(null);
        host=txtIP.getText();
        
        //evento(accion);          
        //Funciones.startPing(host);
        
        /*in = InetAddress.getByName(host);
         
            if (in.isReachable(2000)) 
            {
                evento(accion);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "VERIFICAR DIRECCION IP", "DHN", JOptionPane.INFORMATION_MESSAGE);
            }
        */
        int cont = 0;
        while(cont < 4)
        {
            boolean reachable = F.pingIP(host,1);
            String finalResponseTime = F.pingMS(host,1);
            proceso(reachable ? "Activo" : "Inactivo","proceso");
            proceso(finalResponseTime,"proceso");
            if(reachable==true)
            {
                //System.out.println("si respondio");
                evento(accion);
                cont = 4;
            }
        }
        /*for(int i=0; i<6; i++)
        {
            boolean reachable = F.pingIP(host,1);
            String finalResponseTime = F.pingMS(host,1);
            proceso(reachable ? "Activo" : "Inactivo","proceso");
            proceso(finalResponseTime,"proceso");
            if(reachable==false)
            {
                
            }
        }*/
        
        
        
    }
    
    private void evento()throws IOException, JSchException
    {
        //TOMA LOS VALORES DE LAS CAJAS DE TEXTO
        user = txtUser.getText();
        password = txtPass.getText();
        host = txtIP.getText();
        
        //datos = new ArrayList<>();
        //CREA SESION SCP
        session = F.createSession(user, host, port, keyFilePath, keyPassword,password);
        //DESCARGA EL ARCHIVO system.cfg DESDE LA ANTENA A LA PC
        F.copyRemoteToLocal(session, remoteA, local, fileName);
        
        //try {
       //Crear un objeto BufferedReader al que se le pasa 
       //   un objeto FileReader con el nombre del fichero
       br = new BufferedReader(new FileReader(fileName));
       //Leer la primera línea, guardando en un String
       texto = br.readLine();
       //Repetir mientras no se llegue al final del fichero
       while(texto != null)
       {
           //SEPARA CADA PROPIEDAD Y VALOR HASTA LLEGAR AL NOMBRE
           dato=texto.split("=");

           if(dato[0].equals("resolv.host.1.name"))
           {
                this.lblNOMBREHOST.setText(dato[1]);
           }
           
           
           //COMPRUEBA ESTADO ACTIVO O SUPENDIDO
            if(dato[0].equals("iptables.sys.masq.1.status"))
            {
                switch (dato[1]) 
                {
                    case "disabled":
                       this.btnConectar.setEnabled(true);
                       this.btnConectar.requestFocus();
                       this.btnDesconectar.setEnabled(false);
                       this.btnREINICIAR.setEnabled(false);
                       this.lblNAT.setOpaque(true);
                       //this.lblNAT.setBackground(Color.red);
                       this.lblNAT.setForeground(Color.red);
                       this.lblNAT.setText(" ! ");
                       break;
                    case "enabled":
                        this.btnConectar.setEnabled(false);
                        this.btnDesconectar.setEnabled(true);
                        this.btnDesconectar.requestFocus();
                        this.btnREINICIAR.setEnabled(true);
                        this.lblNAT.setOpaque(true);
                        //this.lblNAT.setBackground(Color.green);
                        this.lblNAT.setForeground(Color.BLUE);
                        break;
                    default:
                        this.lblMB.setOpaque(true);
                        this.lblMB.setText(" ! ");
                        this.lblMB.setForeground(Color.red);
                        break;
                }
            }            
           
            if(dato[0].equals("tshaper.1.output.rate"))
            {  
                if(dato[1]!=null)
                {
                    int mb = Integer.parseInt(dato[1]);
                    mb = mb/1024;
                    this.lblMB.setOpaque(true);
                    this.lblMB.setText(mb+" Mb");
                    this.lblMB.setForeground(Color.blue);
                }
                else
                {
                    this.lblMB.setOpaque(true);
                    this.lblMB.setText("NA");
                    this.lblMB.setForeground(Color.red);
                }
                
                /*switch (dato[1]) {
                    //case "tshaper.1.output.rate=5120":
                    case "5120":
                        this.lblMB.setOpaque(true);
                        this.lblMB.setText("5 Mb");
                        this.lblMB.setForeground(Color.blue);
                        break;
                    //case "tshaper.1.output.rate=7168":
                    case "7168":
                        this.lblMB.setOpaque(true);
                        this.lblMB.setText("7 Mb");
                        this.lblMB.setForeground(Color.blue);
                        break;
                    default:
                        this.lblMB.setOpaque(true);
                        this.lblMB.setText("NA");
                        this.lblMB.setForeground(Color.red);
                        break;
                }*/
            }              
               
           //Hacer lo que sea con la línea leída
           txtPropiedades.append(texto);
           txtPropiedades.append(System.getProperty("line.separator")); // Esto para el salto de línea
           //datos.add(texto+"\n");
           //Leer la siguiente línea
           texto = br.readLine();
       }

            if(br != null)
                br.close();

    }
    
    private void eventosS(String accion) throws IOException, JSchException
    {
        String comando = null;
        
        //TOMA LOS VALORES DE LAS CAJAS DE TEXTO        
        userCCR = txtUserCCR.getText();
        passwordCCR = txtPassCCR.getText();
        hostCCR = txtIP.getText();
        //datos = new ArrayList<>();
        //CREA SESION SCP    
        session = F.createSession(userCCR, "10.0.0.1", port, keyFilePath, keyPassword,passwordCCR);
        
        switch(accion)
        {
            case "COMPROBAR":
                proceso("COMPROBANDO...BLOQUEO CCR","proceso");
                comando = ":put [/ip dhcp-server lease get [find address="+hostCCR+"] blocked]";
                ejecutar(session,comando,"proceso");     
                proceso("COMPROBANDO...ASIGNACION EN IP","proceso");
                comando = ":put [/ip dhcp-server lease get [find address="+hostCCR+"] status]";
                ejecutar(session,comando,"proceso");
                break;
            case "CONECTAR":                
                proceso("CONECTANDO...","proceso");
                comando = "/ip dhcp-server lease set [find address="+hostCCR+"] block-access=no";
                ejecutar(session,comando,"proceso");
                break;
            case "DESCONECTAR":
                proceso("DESCONECTANDO...","proceso");
                comando = "/ip dhcp-server lease set [find address="+hostCCR+"] block-access=yes";
                ejecutar(session,comando,"proceso");
                break;
            default:
                break;
            
        }
        
    }
    
    public void info(String linea)
    {     
        switch(linea)
        {
            case "true":     
                lblBlockC.setText("SUSPENDIDO");
                lblBlockC.setOpaque(true);
                lblBlockC.setForeground(Color.red);
                btnConectarH.setEnabled(true);
                btnDesconectarH.setEnabled(false);
                btnConectarH.requestFocus();
                break;
            case "false":
                lblBlockC.setText("ACTIVO");
                lblBlockC.setOpaque(true);
                //lblBlockC.setBackground(Color.green);
                lblBlockC.setForeground(Color.BLUE);
                btnConectarH.setEnabled(false);
                btnDesconectarH.setEnabled(true);
                btnDesconectarH.requestFocus();
                break;                
            case "bound":
                lblEstatusC.setText("CONECTADA");
                lblEstatusC.setOpaque(true);
                //lblEstatusC.setBackground(Color.green);
                lblEstatusC.setForeground(Color.BLUE);
                break;
            case "waiting":
                lblEstatusC.setText("DESCONECTADA");
                lblEstatusC.setOpaque(true);
                //lblEstatusC.setBackground(Color.red);
                lblEstatusC.setForeground(Color.red);
                break;
            case "Link detected: yes":
                lblSTATUSPORT.setText("ACTIVO");
                lblSTATUSPORT.setOpaque(true);
                lblSTATUSPORT.setForeground(Color.BLUE);
                break;
            case "Link detected: no":
                lblSTATUSPORT.setText("CAIDO");
                lblSTATUSPORT.setOpaque(true);
                lblSTATUSPORT.setForeground(Color.RED);
                break;
            case "Speed: 10Mb/s":
                lblSPEED.setText("Speed: 10Mb/s");
                lblSPEED.setOpaque(true);
                lblSPEED.setForeground(Color.RED);
                break;
            case "Speed: 100Mb/s":
                lblSPEED.setText("Speed: 100Mb/s");
                lblSPEED.setOpaque(true);
                lblSPEED.setForeground(Color.BLUE);
                break;
            case "Speed: 1000Mb/s":
                lblSPEED.setText("Speed: 1000Mb/s");
                lblSPEED.setOpaque(true);
                lblSPEED.setForeground(Color.BLUE);
                break;
            default: 
                //proceso("NO EXISTEN COINCIDENCIAS","proceso");
                //System.out.println(linea+"NO EXISTEN COINCIDENCIAS");
                break;
        }
    }
    
    private void ejecutar(Session session, String comando, String ventana) throws JSchException, IOException
    {      
        ChannelExec ssh = (ChannelExec)session.openChannel("exec");
 
        InputStream inn = ssh.getInputStream();

        ssh.setCommand(comando);
        ssh.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inn));
        String linea;
        
        
        while ((linea = reader.readLine()) != null) 
        {
            //System.out.println(" : " + linea);            
            proceso(linea,ventana);
            info(linea);
            //System.out.println(linea);
            //proceso(linea,ventana);
        }               
                
        ssh.disconnect();
        //session.disconnect();       
        
    }
    
    private void evento(String accion) throws IOException, JSchException
    {
       //TOMA LOS VALORES DE LAS CAJAS DE TEXTO
       user = txtUser.getText();
       password = txtPass.getText();
       host = txtIP.getText();
       //datos = new ArrayList<>();
       //CREA SESION SCP
       session = F.createSession(user, host, port, keyFilePath, keyPassword,password);
       //DESCARGA EL ARCHIVO system.cfg DESDE LA ANTENA A LA PC
       F.copyRemoteToLocal(session, remoteA, local, fileName);
        
       //Crear un objeto BufferedReader al que se le pasa 
       //   un objeto FileReader con el nombre del fichero
       br = new BufferedReader(new FileReader(fileName));
       //Leer la primera línea, guardando en un String
       texto = br.readLine();

       //Repetir mientras no se llegue al final del fichero
       while(texto != null)
       {
           if("activar".equals(accion))
           {
               if("iptables.sys.masq.1.status=disabled".equals(texto))
                   {
                       check = true;
                       texto="iptables.sys.masq.1.status=enabled";
                   }

               if("iptables.sys.masq.status=disabled".equals(texto))
                   {
                       check = true;
                       texto="iptables.sys.masq.status=enabled";
                   }
           }
           else
           {
               if("desactivar".equals(accion))
               {
                   if("iptables.sys.masq.1.status=enabled".equals(texto))
                   {
                       check = true;
                       texto="iptables.sys.masq.1.status=disabled";
                   }
                   
                   if("iptables.sys.masq.status=enabled".equals(texto))
                   {
                       check = true;
                       texto="iptables.sys.masq.status=disabled";
                   }
               }

            }


           //Hacer lo que sea con la línea leída
           txtPropiedades.append(texto);
           txtPropiedades.append(System.getProperty("line.separator")); // Esto para el salto de línea
           //datos.add(texto+"\n");
           //Leer la siguiente línea
           texto = br.readLine();
        }
       
        //Cerrar el buffered
        if(br != null)
        br.close();
           
        if(check==true)
        {
            System.out.println("CHECK SI...");
            //CREAR ARCHIVO NUEVO DE CONFIGURACION
            proceso("Creando archivo...","proceso");
            crearNuevo();

            //ABRE UNA NUEVA SESION
            //session = createSession(user, host, port, keyFilePath, keyPassword,password);
            //COPIA EL NUEVO ARCHIVO A LA ANTENA
            proceso("Copiando archivo nuevo","proceso");
            F.copyLocalToRemote(session, local, remoteA, fileName);

            //CREA UNA NUEVA SESSION
            //session = createSession(user, host, port, keyFilePath, keyPassword,password);
            proceso("Guardando cambios...","proceso");
            F.guarda_cambios(session,password);


            //CREA UNA NUEVA SESSION
            //session = createSession(user, host, port, keyFilePath, keyPassword,password);
            proceso("Reiniciando...","proceso");
            F.reiniciar(session);
        }
        else
        {
            System.out.println("CHECK NO...");
            //channel.disconnect();
            session.disconnect();
        }

        //LIMPIAR TEXTAREA
        txtPropiedades.setText(null);
        
        //DESACTIVAR BOTONES
        this.btnDesconectar.setEnabled(false);
        this.btnConectar.setEnabled(false);
        this.btnComprobar.setEnabled(true);
    }
    
    private void config() throws IOException, JSchException
    {
        host = txtIP.getText();
        
        int IN = Integer.parseInt(lblVELOCIDAD.getText()) * 1024;
        int OUT = IN * 2;
        //FORMATO DE FECHAS, LO SEPARAMOS APRA PODER FORMATEAR EN CADA TECNOLOGIA
        String Dia, Mes, Ano, Ceros;
        //String fecha=formateador.format(hoy);
        String []datos=G.FECHA(G.hoy(),false).split("/");

        Dia="";
        Mes="";
        Ano="";
        Ceros="0000";

        Dia=datos[0];
        Mes=datos[1];
        Ano=datos[2];
       
       //fileName = TECNOLOGIA+".cfg";
       if (rbtnAC.isSelected())
           {
               br = new BufferedReader(new FileReader("AC.cfg"));
               texto = br.readLine();
               while(texto != null)
               {
                   //---------------NOMBRE DE LA ANTENA------------------
                   if("resolv.host.1.name=NOMBRE".equals(texto))
                       {
                           texto="resolv.host.1.name="+txtNOMBRE.getText();
                       }
                   //----------------FRECUENCIA---------------------------
                   if("radio.1.scan_list.channels=5000".equals(texto))
                       {
                           texto="radio.1.scan_list.channels="+txtFRECUENCIA.getText();
                       }
                   if("radio.1.freq=5000".equals(texto))
                       {
                           texto="radio.1.freq="+txtFRECUENCIA.getText();
                       }
                   if("radio.1.center.1.freq=5000".equals(texto))
                       {
                           texto="radio.1.center.1.freq="+txtFRECUENCIA.getText();
                       }
                   //----------------MEGAS---------------------------------
                   if("tshaper.1.output.rate=5120".equals(texto))
                       {
                           texto="tshaper.1.output.rate="+IN;
                       }
                   if("tshaper.2.output.rate=10240".equals(texto))
                       {
                           texto="tshaper.2.output.rate="+OUT;
                       }
                   if("tshaper.2.output.burst=5120".equals(texto))
                       {
                           texto="tshaper.2.output.burst="+IN;
                       }
                   //-----------------FECHA CON FORMATO----------------------
                   if("system.date.timestamp=202305130000".equals(texto))
                       {
                           texto="system.date.timestamp="+Ano+Mes+Dia+Ceros;
                       }
                   
                   txtPropiedades.append(texto);
                   txtPropiedades.append(System.getProperty("line.separator")); // Esto para el salto de línea
                   //datos.add(texto+"\n");
                   //Leer la siguiente línea
                   texto = br.readLine();
                }
           }
       else
       {
           br = new BufferedReader(new FileReader("M5.cfg"));
               texto = br.readLine();
               while(texto != null)
               {
                   //---------------NOMBRE DE LA ANTENA------------------
                   if("resolv.host.1.name=NOMBRE".equals(texto))
                       {
                           texto="resolv.host.1.name="+txtNOMBRE.getText();
                       }
                   //----------------FRECUENCIA---------------------------
                   if("wireless.1.scan_list.channels=5000".equals(texto))
                       {
                           texto="wireless.1.scan_list.channels="+txtFRECUENCIA.getText();
                       }
                   //----------------MEGAS---------------------------------
                   if("tshaper.1.output.rate=5120".equals(texto))
                       {
                           texto="tshaper.1.output.rate="+IN;
                       }
                   if("tshaper.2.output.rate=10240".equals(texto))
                       {
                           texto="tshaper.2.output.rate="+OUT;
                       }
                   if("tshaper.2.output.burst=5120".equals(texto))
                       {
                           texto="tshaper.2.output.burst="+IN;
                       }
                   //-----------------FECHA CON FORMATO----------------------
                   if("system.date.timestamp=FECHA".equals(texto))
                       {
                           texto="system.date.timestamp="+Mes+Dia+Ceros+Ano;
                       }
                   
                   txtPropiedades.append(texto);
                   txtPropiedades.append(System.getProperty("line.separator")); // Esto para el salto de línea
                   //datos.add(texto+"\n");
                   //Leer la siguiente línea
                   texto = br.readLine();
                }
       }

       //Repetir mientras no se llegue al final del fichero
       
       
        //Cerrar el buffered
        if(br != null)
        br.close();

            proceso("Creando archivo...","proceso");
            crearNuevo();            
            //CREAMOS SESION PARA PODER SUBIR ARCHIVO
            //CREA SESION SCP
            session = F.createSession(user, host, port, keyFilePath, keyPassword, password);
            proceso("Copiando archivo nuevo","proceso");
            //copyLocalToRemote(session, local, remoteA, fileName);
            F.copyLocalToRemote(session, local, remoteA, fileName);
            proceso("Guardando cambios...","proceso");
            F.guarda_cambios(session,password);

            proceso("Reiniciando...","proceso");
            F.reiniciar(session);

        txtPropiedades.setText(null);
        
    }
    
    private void crearNuevo() throws IOException
    {
        //Creamos archivo de texto system.cfg
        File system = new File(fileName);
        BufferedWriter nuevo = new  BufferedWriter(new FileWriter(system));
        
        //Leemos contenido y lo escribimos en el nuevo archivo
        nuevo.write(txtPropiedades.getText()); //put in textfile
        //nuevo.write(datos.toString());
        nuevo.close();
    }
    
    public void mostrar(int IDCONTRATO, String ACCION, String IP) throws ClassNotFoundException, SQLException, Exception
    {
        this.IDCONTRATO=IDCONTRATO;
        ModeloContratos mdlC = null;
        if(IDCONTRATO>0)
        {           
            ControlContratos ctrlC = new ControlContratos();
            mdlC = ctrlC.getRegistrosIDINNER(this.IDCONTRATO);
            IDPAQUETE = mdlC.getIDPAQUETE();
            this.IP = mdlC.getIP();   
        }            
        
        switch(ACCION)
        {
            case "NUEVO":
                user = "ubnt";
                password = "ubnt";
                this.txtNOMBRE.setEnabled(true);
                this.txtUser.setEnabled(false);
                this.txtPass.setEnabled(false);
                this.txtFRECUENCIA.setEnabled(true);
                this.rbtnAC.setEnabled(true);
                this.rbtnAC.setSelected(true);
                this.rbtnM5.setEnabled(true);
                this.btnComprobar.setVisible(false);
                this.btnREINICIAR.setVisible(false);
                this.pnlPROPIEDADES.setVisible(false);
                this.pnlAVANZADOS.setVisible(false);
                this.btnENVIAR.setEnabled(true);                
                this.txtNOMBRE.setText(""+this.IDCONTRATO+"-"+mdlC.getCALLE().replaceAll(" ", "-")+"-"+mdlC.getNUMERO());
                this.txtNOMBRE.setOpaque(true);
                this.txtNOMBRE.setEnabled(false);                
                velocidad();                
                pack();                
                extras();
                break;
                
            case "DIAGNOSTICAR":                
                this.txtNOMBRE.setVisible(false);
                this.rbtnAC.setVisible(false);
                this.rbtnM5.setVisible(false);
                //this.lblVELOCIDAD.setVisible(false);
                this.btnENVIAR.setVisible(false);
                this.txtIP.setText(this.IP);
                this.txtIP.setEnabled(false);
                this.pnlAVANZADOS.setVisible(false);
                //proceso(Funciones.startPing(host),"proceso");
                velocidad();
                pack();
                extras();
                checkHostWithRetry(this.IP);
                
                //btnAVANZADOS.doClick();                
                break;
            case "MONITOR":
                txtIP.setText(IP);
                this.btnENVIAR.setEnabled(false);
                break;
            default: 
                break;
        }
    }   
    
    private void velocidad() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloPaquetes mdl;
        ControlPaquetes ctrl = new ControlPaquetes();        
        mdl = ctrl.getRegistrosID(IDPAQUETE);
        String []datos = mdl.getDESCRIPCION().split(" ");
        int velocidad = Integer.parseInt(datos[0]);
        this.lblVELOCIDAD.setText(""+velocidad);        
    }
    
    /*private void este(String host) throws Exception
    {

        int cont = 0;
        while(cont <= 4)
        {
            boolean reachable = F.pingIP(host,1);
            String finalResponseTime = F.pingMS(host,1);
            proceso(reachable ? "Activo" : "Inactivo","proceso");
            proceso(finalResponseTime,"proceso");
            if(reachable==true)
            {   
                comprobar();
                cont = 4;
            }
            cont++;
        }
    }*/
    
    private void checkHostWithRetry(String host) 
    {
        final int MAX_ATTEMPTS = 3;
        final int WAIT_BETWEEN_ATTEMPTS = 1000; // 2 segundos

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            try {
                // Un solo ping que devuelve resultado y tiempo
                String pingResult = F.pingMS(host, 1);
                boolean isReachable = !pingResult.contains("Sin respuesta") && 
                                     !pingResult.contains("Error");

                // Registrar estado
                proceso(isReachable ? "Activo" : "Inactivo", "proceso");
                proceso(pingResult, "proceso");

                if (isReachable) {
                    comprobar();
                    return; // Salir exitosamente
                }

                // Esperar antes del siguiente intento (excepto en el último)
                if (attempt < MAX_ATTEMPTS) {
                    Thread.sleep(WAIT_BETWEEN_ATTEMPTS);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                proceso("Interrupción detectada", "error");
                return;
            } catch (Exception e) {
                proceso("Error: " + e.getMessage(), "error");
                // Continuar con siguiente intento
            }
        }

        proceso("No se pudo conectar después de " + MAX_ATTEMPTS + " intentos", "error");
    }
    
    private void comprobar() throws Exception
    {
        host=txtIP.getText();
        /*String cmd = "ping "+host+" -t";
        Runtime.getRuntime().exec(cmd);
        Runtime r = Runtime.getRuntime();
        Process p = r.exec("cmd.exe /C /wait" + cmd);*/
        
        //InputStream is = p.getInputStream();
        //InputStreamReader isr = new InputStreamReader(is);
        //BufferedReader br = new BufferedReader(isr);
        
        //proceso(cmd,"proceso");
        
        //SI FUNCIONA        
        //proceso(Funciones.startPing(host),"proceso");
        try {
            //in = InetAddress.getByName(host);
            eventosS("COMPROBAR");
            habilitado("BLOQUEAR");
            evento();
            eth0();
            pack();
        }
        catch (IOException | JSchException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void view(Boolean ACCION)
    {
        this.pnlDATOS.setVisible(ACCION);
        this.pnlACTIONCCR.setVisible(ACCION);
        this.pnlACTIONUBNT.setVisible(ACCION);
        pack();
    }
    
    private void eth0() throws JSchException, IOException
    {
        //TOMA LOS VALORES DE LAS CAJAS DE TEXTO
        user = txtUser.getText();
        password = txtPass.getText();
        host = txtIP.getText();
        
        //datos = new ArrayList<>();
        //CREA SESION SCP
        session = F.createSession(user, host, port, keyFilePath, keyPassword,password);
        
        proceso("VERIFICANDO PUERTO...","eth0");
        String comando = "ethtool eth0";        
        ejecutar(session,comando,"eth0");
        buscar("Link detected: yes");
        buscar("Link detected: no");
        buscar("Speed: 10Mb/s");
        buscar("Speed: 100Mb/s");
        buscar("Speed: 1000Mb/s");
        session.disconnect();
    }
    
    private void resetPort() throws JSchException, IOException
    {
        //TOMA LOS VALORES DE LAS CAJAS DE TEXTO
        user = txtUser.getText();
        password = txtPass.getText();
        host = txtIP.getText();
        
        //datos = new ArrayList<>();
        //CREA SESION SCP
        session = F.createSession(user, host, port, keyFilePath, keyPassword,password);
        proceso("VERIFICANDO PUERTO...","eth0");
        String comando = "ifconfig eth0 down && ifconfig eth0 up";        
        ejecutar(session,comando,"eth0");
        eth0();
    }
    
    private void avanzados() throws JSchException, IOException
    {
        this.pnlAVANZADOS.setVisible(true);
        //TOMA LOS VALORES DE LAS CAJAS DE TEXTO
        user = txtUser.getText();
        password = txtPass.getText();
        host = txtIP.getText();
        
        //datos = new ArrayList<>();
        //CREA SESION SCP
        session = F.createSession(user, host, port, keyFilePath, keyPassword,password);
        proceso("DETALLES AVANZADOS...","ath0");
        String comando = "ubntbox mca-status";        
        ejecutar(session,comando,"ath0");
        proceso("------------------------------------","ath0");
        comando = "iwconfig ath0";        
        ejecutar(session,comando,"ath0");
        proceso("------------------------------------","ath0");
        comando = "ifconfig ath0";        
        ejecutar(session,comando,"ath0");
        session.disconnect();
        pack();
    }
    
    private void buscar(String textoABuscar)
    {
        String contenido = txtSSH.getText();
        if (contenido.contains(textoABuscar)) {
            //resultadoLabel.setText("Texto encontrado: " + textoABuscar);
            // Opcional: resaltar el texto encontrado en el JTextArea
            txtSSH.setSelectionStart(contenido.indexOf(textoABuscar));
            txtSSH.setSelectionEnd(contenido.indexOf(textoABuscar) + textoABuscar.length());
            txtSSH.requestFocus();
            info(textoABuscar);
        } else {
            //resultadoLabel.setText("Texto no encontrado: " + textoABuscar);
        }
    }
    
    private void plantilla()
    {
        String[] datos = {""+IDCONTRATO, txtNS.getText(), "User2837.", "Home", "DHN_"+IDCONTRATO+"_2.4", txtNS.getText(), ""+IDCONTRATO, "", ""};       
        String rutaExcel = "\\"+"\\" +G.ip +"\\dhn\\ROUTERS\\"+IDCONTRATO+".xlsx"; 
        F.crearExcel(null, datos, rutaExcel);
        JOptionPane.showMessageDialog(null, "ARCHIVO DE CONFIGURACION CREADO", "LISTO",JOptionPane.INFORMATION_MESSAGE);            
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        txtPropiedades = new javax.swing.JTextArea();
        TECNOLOGIA = new javax.swing.ButtonGroup();
        PAQUETES = new javax.swing.ButtonGroup();
        txtFRECUENCIA = new javax.swing.JTextField();
        pnlDATOS = new javax.swing.JPanel();
        txtUser = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        txtUserCCR = new javax.swing.JTextField();
        txtPassCCR = new javax.swing.JTextField();
        pnlACTIONCCR = new javax.swing.JPanel();
        btnConectarH = new javax.swing.JButton();
        btnDesconectarH = new javax.swing.JButton();
        pnlACTIONUBNT = new javax.swing.JPanel();
        btnConectar = new javax.swing.JButton();
        btnDesconectar = new javax.swing.JButton();
        pnlAVANZADOS = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAVANZADOS = new javax.swing.JTextArea();
        jpACTIVACION = new javax.swing.JPanel();
        txtIP = new javax.swing.JTextField();
        btnComprobar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPROCESO = new javax.swing.JTextArea();
        btnLIMPIAR = new javax.swing.JButton();
        btnWINBOX = new javax.swing.JButton();
        btnDSICOVERY = new javax.swing.JButton();
        btnREINICIAR = new javax.swing.JButton();
        btnVIEW = new javax.swing.JToggleButton();
        txtNOMBRE = new javax.swing.JTextField();
        rbtnAC = new javax.swing.JRadioButton();
        rbtnM5 = new javax.swing.JRadioButton();
        lblVELOCIDAD = new javax.swing.JLabel();
        btnENVIAR = new javax.swing.JButton();
        btnWEB = new javax.swing.JButton();
        btnEXCEL = new javax.swing.JButton();
        txtNS = new javax.swing.JTextField();
        btnRUIJIECLOUD = new javax.swing.JButton();
        pnlPROPIEDADES = new javax.swing.JPanel();
        lblBlockC = new javax.swing.JLabel();
        lblNOMBREHOST = new javax.swing.JLabel();
        lblEstatusC = new javax.swing.JLabel();
        lblMB = new javax.swing.JLabel();
        lblNAT = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSSH = new javax.swing.JTextArea();
        lblSPEED = new javax.swing.JLabel();
        lblSTATUSPORT = new javax.swing.JLabel();
        btnETHPORT = new javax.swing.JButton();
        btnBANDWIDTH = new javax.swing.JButton();
        btnAVANZADOS = new javax.swing.JButton();

        txtPropiedades.setColumns(20);
        txtPropiedades.setRows(5);
        jScrollPane2.setViewportView(txtPropiedades);

        txtFRECUENCIA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFRECUENCIA.setBorder(javax.swing.BorderFactory.createTitledBorder("Frecuencia"));
        txtFRECUENCIA.setEnabled(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Herramientas UBNT");

        pnlDATOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Usuarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtUser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUser.setText("manager");
        txtUser.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuario Antena", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        txtPass.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPass.setText("012837DHN.*,");
        txtPass.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Password Antena", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        txtUserCCR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUserCCR.setText("admin");
        txtUserCCR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuario CCR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        txtPassCCR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPassCCR.setText("012837DHN.*,");
        txtPassCCR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Password CCR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout pnlDATOSLayout = new javax.swing.GroupLayout(pnlDATOS);
        pnlDATOS.setLayout(pnlDATOSLayout);
        pnlDATOSLayout.setHorizontalGroup(
            pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtPass)
            .addComponent(txtUserCCR)
            .addComponent(txtUser)
            .addComponent(txtPassCCR)
        );
        pnlDATOSLayout.setVerticalGroup(
            pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDATOSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUserCCR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassCCR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlACTIONCCR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones CCR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        btnConectarH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnConectarH.setText("Conectar");
        btnConectarH.setEnabled(false);
        btnConectarH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarHActionPerformed(evt);
            }
        });
        btnConectarH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnConectarHKeyPressed(evt);
            }
        });

        btnDesconectarH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDesconectarH.setText("Desconectar");
        btnDesconectarH.setEnabled(false);
        btnDesconectarH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesconectarHActionPerformed(evt);
            }
        });
        btnDesconectarH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDesconectarHKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnlACTIONCCRLayout = new javax.swing.GroupLayout(pnlACTIONCCR);
        pnlACTIONCCR.setLayout(pnlACTIONCCRLayout);
        pnlACTIONCCRLayout.setHorizontalGroup(
            pnlACTIONCCRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlACTIONCCRLayout.createSequentialGroup()
                .addGroup(pnlACTIONCCRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnConectarH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDesconectarH, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlACTIONCCRLayout.setVerticalGroup(
            pnlACTIONCCRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlACTIONCCRLayout.createSequentialGroup()
                .addComponent(btnConectarH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDesconectarH))
        );

        pnlACTIONUBNT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones Antena", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        btnConectar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnConectar.setText("Conectar");
        btnConectar.setEnabled(false);
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });
        btnConectar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnConectarKeyPressed(evt);
            }
        });

        btnDesconectar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDesconectar.setText("Desconectar");
        btnDesconectar.setEnabled(false);
        btnDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesconectarActionPerformed(evt);
            }
        });
        btnDesconectar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDesconectarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnlACTIONUBNTLayout = new javax.swing.GroupLayout(pnlACTIONUBNT);
        pnlACTIONUBNT.setLayout(pnlACTIONUBNTLayout);
        pnlACTIONUBNTLayout.setHorizontalGroup(
            pnlACTIONUBNTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlACTIONUBNTLayout.createSequentialGroup()
                .addGroup(pnlACTIONUBNTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDesconectar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConectar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlACTIONUBNTLayout.setVerticalGroup(
            pnlACTIONUBNTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlACTIONUBNTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnConectar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDesconectar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlAVANZADOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles Avanzados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtAVANZADOS.setColumns(20);
        txtAVANZADOS.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtAVANZADOS.setRows(40);
        txtAVANZADOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos avanzados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jScrollPane4.setViewportView(txtAVANZADOS);

        javax.swing.GroupLayout pnlAVANZADOSLayout = new javax.swing.GroupLayout(pnlAVANZADOS);
        pnlAVANZADOS.setLayout(pnlAVANZADOSLayout);
        pnlAVANZADOSLayout.setHorizontalGroup(
            pnlAVANZADOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlAVANZADOSLayout.setVerticalGroup(
            pnlAVANZADOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );

        jpACTIVACION.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Antena", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtIP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIP.setText("192.168.1.");
        txtIP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IP:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIPKeyPressed(evt);
            }
        });

        btnComprobar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnComprobar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/diagnosticar.png"))); // NOI18N
        btnComprobar.setText("Diagnosticar");
        btnComprobar.setToolTipText("Diagnosticar");
        btnComprobar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnComprobar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnComprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprobarActionPerformed(evt);
            }
        });

        txtPROCESO.setColumns(20);
        txtPROCESO.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtPROCESO.setRows(20);
        txtPROCESO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proceso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jScrollPane1.setViewportView(txtPROCESO);

        btnLIMPIAR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLIMPIAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clean.png"))); // NOI18N
        btnLIMPIAR.setToolTipText("Limpia y reactiva cajas");
        btnLIMPIAR.setEnabled(false);
        btnLIMPIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLIMPIARActionPerformed(evt);
            }
        });

        btnWINBOX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/winbox.png"))); // NOI18N
        btnWINBOX.setToolTipText("Abrir Winbox");
        btnWINBOX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWINBOXActionPerformed(evt);
            }
        });

        btnDSICOVERY.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ubnt.png"))); // NOI18N
        btnDSICOVERY.setToolTipText("Abrir Ubnt Discovery");
        btnDSICOVERY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDSICOVERYActionPerformed(evt);
            }
        });

        btnREINICIAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnREINICIAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/standby.png"))); // NOI18N
        btnREINICIAR.setText("Reiniciar");
        btnREINICIAR.setEnabled(false);
        btnREINICIAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnREINICIAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnREINICIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnREINICIARActionPerformed(evt);
            }
        });

        btnVIEW.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.png"))); // NOI18N
        btnVIEW.setToolTipText("Cambiar Usuarios");
        btnVIEW.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnVIEWItemStateChanged(evt);
            }
        });

        txtNOMBRE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNOMBRE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        TECNOLOGIA.add(rbtnAC);
        rbtnAC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtnAC.setSelected(true);
        rbtnAC.setText("AC");

        TECNOLOGIA.add(rbtnM5);
        rbtnM5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtnM5.setText("M5");

        lblVELOCIDAD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblVELOCIDAD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVELOCIDAD.setText("5");
        lblVELOCIDAD.setBorder(javax.swing.BorderFactory.createTitledBorder("Velocidad"));

        btnENVIAR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnENVIAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/send-azul.png"))); // NOI18N
        btnENVIAR.setToolTipText("Enviar configuracion al dispositivo");
        btnENVIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnENVIARActionPerformed(evt);
            }
        });

        btnWEB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/web.png"))); // NOI18N
        btnWEB.setToolTipText("Abrir en web");
        btnWEB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWEBActionPerformed(evt);
            }
        });

        btnEXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel.png"))); // NOI18N
        btnEXCEL.setToolTipText("Generar Plantilla");
        btnEXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEXCELActionPerformed(evt);
            }
        });

        txtNS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NS Router", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtNS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNSKeyTyped(evt);
            }
        });

        btnRUIJIECLOUD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ruijie reyee s.png"))); // NOI18N
        btnRUIJIECLOUD.setToolTipText("Abrir Ruijie Cloud");
        btnRUIJIECLOUD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRUIJIECLOUDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpACTIVACIONLayout = new javax.swing.GroupLayout(jpACTIVACION);
        jpACTIVACION.setLayout(jpACTIVACIONLayout);
        jpACTIVACIONLayout.setHorizontalGroup(
            jpACTIVACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtIP)
            .addComponent(txtNOMBRE)
            .addGroup(jpACTIVACIONLayout.createSequentialGroup()
                .addComponent(rbtnAC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnM5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVELOCIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnENVIAR))
            .addGroup(jpACTIVACIONLayout.createSequentialGroup()
                .addGroup(jpACTIVACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpACTIVACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpACTIVACIONLayout.createSequentialGroup()
                            .addComponent(btnComprobar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnREINICIAR)))
                    .addGroup(jpACTIVACIONLayout.createSequentialGroup()
                        .addGroup(jpACTIVACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNS, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpACTIVACIONLayout.createSequentialGroup()
                                .addComponent(btnVIEW)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnWEB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDSICOVERY)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnWINBOX)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEXCEL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpACTIVACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRUIJIECLOUD)
                            .addComponent(btnLIMPIAR))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpACTIVACIONLayout.setVerticalGroup(
            jpACTIVACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpACTIVACIONLayout.createSequentialGroup()
                .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpACTIVACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpACTIVACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtnAC)
                        .addComponent(rbtnM5)
                        .addComponent(lblVELOCIDAD))
                    .addComponent(btnENVIAR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpACTIVACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnComprobar)
                    .addComponent(btnREINICIAR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpACTIVACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnWEB)
                    .addComponent(btnVIEW)
                    .addComponent(btnDSICOVERY)
                    .addComponent(btnWINBOX)
                    .addComponent(btnLIMPIAR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpACTIVACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEXCEL)
                    .addComponent(btnRUIJIECLOUD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );

        pnlPROPIEDADES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Propiedades", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblBlockC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBlockC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBlockC.setText("ESTATUS");
        lblBlockC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estatus", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblNOMBREHOST.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNOMBREHOST.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNOMBREHOST.setText("NOMBRE DE LA ANTENA");

        lblEstatusC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEstatusC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstatusC.setText("ANTENA");
        lblEstatusC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Antena", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblMB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMB.setText("MEGAS");
        lblMB.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Megas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblNAT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNAT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNAT.setText("ACTIVO");
        lblNAT.setToolTipText("Nateo de antena");
        lblNAT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NAT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        txtSSH.setColumns(25);
        txtSSH.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtSSH.setRows(25);
        txtSSH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado del puerto eth0", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jScrollPane3.setViewportView(txtSSH);

        lblSPEED.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSPEED.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSPEED.setText("VELOCIDAD DEL PUERTO");
        lblSPEED.setToolTipText("SPEED PORT");
        lblSPEED.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Velocidad del puerto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblSTATUSPORT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSTATUSPORT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSTATUSPORT.setText("ESTADO DEL PUERTO");
        lblSTATUSPORT.setToolTipText("ESTADO DEL PUERTO");
        lblSTATUSPORT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado del puerto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        btnETHPORT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ethernet.png"))); // NOI18N
        btnETHPORT.setToolTipText("Reset PORT Ethernet");
        btnETHPORT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnETHPORTActionPerformed(evt);
            }
        });

        btnBANDWIDTH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bandwidth.png"))); // NOI18N
        btnBANDWIDTH.setToolTipText("Corregir ancho de banda");
        btnBANDWIDTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBANDWIDTHActionPerformed(evt);
            }
        });

        btnAVANZADOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/opciones-avanzadas.png"))); // NOI18N
        btnAVANZADOS.setToolTipText("Detalles Avanzados");
        btnAVANZADOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAVANZADOSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPROPIEDADESLayout = new javax.swing.GroupLayout(pnlPROPIEDADES);
        pnlPROPIEDADES.setLayout(pnlPROPIEDADESLayout);
        pnlPROPIEDADESLayout.setHorizontalGroup(
            pnlPROPIEDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPROPIEDADESLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPROPIEDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNOMBREHOST, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlPROPIEDADESLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPROPIEDADESLayout.createSequentialGroup()
                        .addGroup(pnlPROPIEDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlPROPIEDADESLayout.createSequentialGroup()
                                .addComponent(btnAVANZADOS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnETHPORT)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(lblSPEED, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBlockC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPROPIEDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPROPIEDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblNAT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblEstatusC, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                            .addComponent(lblSTATUSPORT, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBANDWIDTH, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        pnlPROPIEDADESLayout.setVerticalGroup(
            pnlPROPIEDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPROPIEDADESLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNOMBREHOST, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPROPIEDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBlockC)
                    .addComponent(lblEstatusC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPROPIEDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMB)
                    .addComponent(lblNAT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPROPIEDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSPEED)
                    .addComponent(lblSTATUSPORT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPROPIEDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPROPIEDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnETHPORT)
                        .addComponent(btnBANDWIDTH))
                    .addComponent(btnAVANZADOS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlACTIONCCR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDATOS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlACTIONUBNT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpACTIVACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPROPIEDADES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAVANZADOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpACTIVACION, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDATOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(pnlACTIONCCR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(pnlACTIONUBNT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlPROPIEDADES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAVANZADOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesconectarActionPerformed
        try {
            // TODO add your handling code here:
            ping("desactivar");
        } catch (IOException | JSchException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDesconectarActionPerformed

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        try {
            // TODO add your handling code here:
            ping("activar");
        } catch (IOException | JSchException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConectarActionPerformed

    private void btnComprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprobarActionPerformed
        try {
            //comprobar();
            checkHostWithRetry(IP);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnComprobarActionPerformed

    private void btnREINICIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnREINICIARActionPerformed
        try {
            // TODO add your handling code here:
            //TOMA LOS VALORES DE LAS CAJAS DE TEXTO
            user = txtUser.getText();
            password = txtPass.getText();
            host = txtIP.getText();
            //datos = new ArrayList<>();
            //CREA SESION SCP
            session = F.createSession(user, host, port, keyFilePath, keyPassword,password);
            F.reiniciar(session);
            habilitado("LIMPIAR");
        } catch (JSchException | IOException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnREINICIARActionPerformed

    private void btnENVIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnENVIARActionPerformed
        try {
            
            config();
        } catch (IOException | JSchException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtNOMBRE.setEnabled(true);
        this.txtUser.setEnabled(true);
        this.txtPass.setEnabled(true);
        this.txtFRECUENCIA.setEnabled(false);
        this.rbtnAC.setEnabled(false);
        this.rbtnM5.setEnabled(false);
       
        this.btnENVIAR.setEnabled(false);
        this.txtNOMBRE.setText("");
        this.txtFRECUENCIA.setText("");
        this.txtIP.setText("");
        this.rbtnAC.setSelected(true);
        this.txtIP.requestFocus();
    }//GEN-LAST:event_btnENVIARActionPerformed

    private void txtIPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIPKeyPressed
        try {
            if(F.ENTER(evt))            
                    comprobar();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtIPKeyPressed

    private void btnDesconectarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDesconectarKeyPressed
        try {
            if(F.ENTER(evt))
                ping("desactivar");
            this.txtIP.requestFocus();
        } catch (IOException | JSchException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDesconectarKeyPressed

    private void btnConectarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnConectarKeyPressed
        try {
            if(F.ENTER(evt))
                ping("activar");
            this.txtIP.requestFocus();
        } catch (IOException | JSchException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConectarKeyPressed

    private void btnDesconectarHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesconectarHActionPerformed
        try {
            // TODO add your handling code here:
            eventosS("DESCONECTAR");
            habilitado("LIMPIAR");
            comprobar();
            user = txtUser.getText();
            password = txtPass.getText();
            host = txtIP.getText();
            //CREA SESION SCP
            session = F.createSession(user, host, port, keyFilePath, keyPassword,password);
            F.reiniciar(session);
        } catch (IOException | JSchException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDesconectarHActionPerformed

    private void btnDesconectarHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDesconectarHKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDesconectarHKeyPressed

    private void btnConectarHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarHActionPerformed
        try {
            eventosS("CONECTAR");
            habilitado("LIMPIAR");
            comprobar();          
        } catch (IOException | JSchException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConectarHActionPerformed

    private void btnConectarHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnConectarHKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConectarHKeyPressed

    private void btnLIMPIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLIMPIARActionPerformed
        // TODO add your handling code here:
        habilitado("LIMPIAR");
    }//GEN-LAST:event_btnLIMPIARActionPerformed

    private void btnDSICOVERYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDSICOVERYActionPerformed
        try{
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        File objetofile = new File ("C:\\dhn\\Apps\\discovery.jar");
        String classpath = objetofile.getAbsolutePath();
        
        ProcessBuilder builder = new ProcessBuilder(javaBin, "-jar", classpath);
        builder.start();
        } catch (IOException ex) {
            /*JOptionPane.showMessageDialog(this, 
                "Error al ejecutar el JAR: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);*/
        }
    }//GEN-LAST:event_btnDSICOVERYActionPerformed

    private void btnWINBOXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWINBOXActionPerformed
        try {
            F.abrirApp("C:\\dhn\\Apps\\winbox64.exe");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnWINBOXActionPerformed

    private void btnVIEWItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnVIEWItemStateChanged
        view(evt.getStateChange() == ItemEvent.SELECTED);
        
    }//GEN-LAST:event_btnVIEWItemStateChanged

    private void btnWEBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWEBActionPerformed
        try {
            if(txtIP.getText()!=null)
            {
                F.openweb(txtIP.getText(),"IP");
                F.clipboard(txtPass.getText());
            }
            else
                JOptionPane.showMessageDialog(null, "VERIFICAR DIRECCION IP", "DHN", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException | URISyntaxException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnWEBActionPerformed

    private void btnETHPORTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnETHPORTActionPerformed
        try {
            // TODO add your handling code here:
            resetPort();
        } catch (JSchException | IOException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnETHPORTActionPerformed

    private void btnBANDWIDTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBANDWIDTHActionPerformed
        try {
            proceso("Corrigiendo ancho de banda.........","eth0");
            F.bandwidth(txtUser.getText(), txtPass.getText(), IP, IDPAQUETE);
            proceso("REINICIANDO DISPOSITIVO!!!.........","eth0");
            habilitado("LIMPIAR");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBANDWIDTHActionPerformed

    private void btnAVANZADOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAVANZADOSActionPerformed
        try {
            avanzados();
        } catch (JSchException | IOException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAVANZADOSActionPerformed

    private void btnEXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXCELActionPerformed
        plantilla();
    }//GEN-LAST:event_btnEXCELActionPerformed

    private void btnRUIJIECLOUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRUIJIECLOUDActionPerformed
        try {
            F.openweb("cloud-la.ruijienetworks.com/macc5/adminIntl/#/monitor_devicesV2_menu","IP");
        } catch (IOException | URISyntaxException ex) {
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRUIJIECLOUDActionPerformed

    private void txtNSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNSKeyTyped
        F.MAYUS(evt);
    }//GEN-LAST:event_txtNSKeyTyped

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
            java.util.logging.Logger.getLogger(Tools.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                Tools dialog = new Tools(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            } catch (ClassNotFoundException | SQLException ex) {
                java.util.logging.Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup PAQUETES;
    private javax.swing.ButtonGroup TECNOLOGIA;
    private javax.swing.JButton btnAVANZADOS;
    private javax.swing.JButton btnBANDWIDTH;
    private javax.swing.JButton btnComprobar;
    private javax.swing.JButton btnConectar;
    private javax.swing.JButton btnConectarH;
    private javax.swing.JButton btnDSICOVERY;
    private javax.swing.JButton btnDesconectar;
    private javax.swing.JButton btnDesconectarH;
    private javax.swing.JButton btnENVIAR;
    private javax.swing.JButton btnETHPORT;
    private javax.swing.JButton btnEXCEL;
    private javax.swing.JButton btnLIMPIAR;
    private javax.swing.JButton btnREINICIAR;
    private javax.swing.JButton btnRUIJIECLOUD;
    private javax.swing.JToggleButton btnVIEW;
    private javax.swing.JButton btnWEB;
    private javax.swing.JButton btnWINBOX;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jpACTIVACION;
    private javax.swing.JLabel lblBlockC;
    private javax.swing.JLabel lblEstatusC;
    private javax.swing.JLabel lblMB;
    private javax.swing.JLabel lblNAT;
    private javax.swing.JLabel lblNOMBREHOST;
    private javax.swing.JLabel lblSPEED;
    private javax.swing.JLabel lblSTATUSPORT;
    private javax.swing.JLabel lblVELOCIDAD;
    private javax.swing.JPanel pnlACTIONCCR;
    private javax.swing.JPanel pnlACTIONUBNT;
    private javax.swing.JPanel pnlAVANZADOS;
    private javax.swing.JPanel pnlDATOS;
    private javax.swing.JPanel pnlPROPIEDADES;
    private javax.swing.JRadioButton rbtnAC;
    private javax.swing.JRadioButton rbtnM5;
    private javax.swing.JTextArea txtAVANZADOS;
    private javax.swing.JTextField txtFRECUENCIA;
    private javax.swing.JTextField txtIP;
    private javax.swing.JTextField txtNOMBRE;
    private javax.swing.JTextField txtNS;
    private javax.swing.JTextArea txtPROCESO;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtPassCCR;
    private javax.swing.JTextArea txtPropiedades;
    private javax.swing.JTextArea txtSSH;
    private javax.swing.JTextField txtUser;
    private javax.swing.JTextField txtUserCCR;
    // End of variables declaration//GEN-END:variables
}
