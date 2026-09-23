/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import UTILERIAS.RenderCelda;
import DHNv2.G;

import MODELO.ModeloBitacora;
import MODELO.ModeloCarrito;
import MODELO.ModeloContratos;
import MODELO.ModeloContratosTabla;
import MODELO.ModeloKits;
import MODELO.ModeloKitsDTabla;
import MODELO.ModeloPaquetes;
import MODELO.ModeloPendientes;
import MODELO.ModeloPrestaciones;
import static VISTA.Principal.lblCE;
import static VISTA.Principal.lblCP;
import static VISTA.Principal.lblCPE;
import static VISTA.Principal.lblPR;
import static VISTA.Principal.lblPXE;
import static VISTA.Principal.lblPXV;
import static VISTA.Principal.lblSD;
import VISTA.RegDetallesDisp;
import VISTA.ViewBitacora;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jcraft.jsch.*;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.itext.extension.font.ITextFontRegistry;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import java.io.OutputStream;

import java.io.File;
import javax.xml.bind.JAXBException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.util.Units;
//import org.sejda.imageio.plugins.webp.WebPImageReader;
/**
 *
 * @author Admin
 */
public class F 
{
    //private static final SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss"); 
    
    public static Integer Registro(String TABLA, String CAMPO, String ORG) throws ClassNotFoundException, SQLException, Exception 
    {
        int x;     
        Manejador obj = new Manejador();
        String sql;
        ResultSet rs;
        sql=("SELECT "+CAMPO+" FROM "+TABLA+" ORDER BY "+CAMPO+" "+ORG);
        try {
            rs= obj.consultar(sql);
            if(rs.next())
               {
                   x=rs.getInt(1)+1;
                   return x;
               }
        } catch (Exception ex) {
            Logger.getLogger(ControlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 1;
    }
    
    public static Integer Registro(String TABLA, String CAMPO, String ORG, String CAMPOCONDICION, int ID) throws ClassNotFoundException, SQLException, Exception 
    {
        int x;     
        Manejador obj = new Manejador();
        String sql;
        ResultSet rs;
        sql=("SELECT "+CAMPO+" FROM "+TABLA+" WHERE "+CAMPOCONDICION+" = "+ID+" ORDER BY "+CAMPO+" "+ORG);
        //sql=("SELECT IDCORTE FROM CORTES WHERE IDCAJA="+IDCAJA+" ORDER BY IDCORTE "+ORG);
        try {
            rs= obj.consultar(sql);
            if(rs.next())
               {
                   x=rs.getInt(1)+1;
                   return x;
               }
        } catch (Exception ex) {
            Logger.getLogger(ControlCortes.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 1;   
    }
    
    public static Integer eliminarRegistro(int ID, String TABLA, String ATRIBUTO) throws ClassNotFoundException, SQLException, Exception
    {
        Manejador obj = new Manejador();
        String sql;
        
        if(TABLA.equals("productosd"))
        {
            sql=("DELETE FROM "+TABLA+" WHERE CB = '" + ATRIBUTO + "' ");
        }
        else
            sql=("DELETE FROM "+TABLA+" WHERE "+ATRIBUTO+" = " + ID + " ");
        
        obj.Ejecutar(sql);
        obj.limpiarRecursos();
        return 1;
    }
    
    public static int total(String TABLA, String CAMPO1, String campo1) throws ClassNotFoundException, SQLException, Exception 
    {
        int total;
        Manejador obj = new Manejador();
        String sql;
        ResultSet rs;
        sql=("select COUNT(*) from "+TABLA+" where "+CAMPO1+" = '" + campo1 + "' ");
        try
        {
            rs= obj.consultar(sql);
            if(rs.next())
            {
                total=rs.getInt(1);
                return total;
            }
            obj.limpiarRecursos();
        }
        catch(SQLException | ClassNotFoundException e)
            {
            } catch (Exception ex) {
            Logger.getLogger(ControlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 0;
    }
    
    public static int total(String sql) throws ClassNotFoundException, SQLException, Exception 
    {
        int total;
        Manejador obj = new Manejador();
        //String sql;
        ResultSet rs;
        //sql=("select COUNT(*) from "+TABLA+" where "+CAMPO1+" = '" + campo1 + "' ");
        try
        {
            rs= obj.consultar(sql);
            if(rs.next())
            {
                total=rs.getInt(1);
                return total;
            }
            obj.limpiarRecursos();
        }
        catch(SQLException | ClassNotFoundException e)
            {
            } catch (Exception ex) {
            Logger.getLogger(ControlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 0;
    }
        
    public static int SERVICIO(int IDPENDIENTE) throws ClassNotFoundException, SQLException, Exception 
    {
        int total;
        Manejador obj = new Manejador();
        String sql;
        ResultSet rs;
        sql=("select IDORDENP from ORDENESP where IDPENDIENTE=" + IDPENDIENTE + "");
        try
        {
            rs= obj.consultar(sql);
            if(rs.next())
            {
                total=rs.getInt(1);
                return total;
            }
            obj.limpiarRecursos();
        }
        catch(SQLException | ClassNotFoundException e)
            {
            } catch (Exception ex) {
            Logger.getLogger(ControlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 0;
    }
    
    public static int total(String TABLA, String CAMPO, int IDORDEN) throws ClassNotFoundException, SQLException, Exception 
    {
        int total;
        Manejador obj = new Manejador();
        String sql;
        ResultSet rs;
        sql=("select COUNT(*) from "+TABLA+" where "+CAMPO+" = " + IDORDEN + " ");
        try
        {
            rs= obj.consultar(sql);
            if(rs.next())
            {
                total=rs.getInt(1);
                return total;
            }
            obj.limpiarRecursos();
        }
        catch(SQLException | ClassNotFoundException e)
            {
            } catch (Exception ex) {
            Logger.getLogger(ControlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 0;
    }
    
    public static int totalAlmacen(String TABLA, String CAMPO, int ID, String ESTADO) throws ClassNotFoundException, SQLException, Exception 
    {
        int total;
        Manejador obj = new Manejador();
        String sql;
        ResultSet rs;
        sql=("SELECT COUNT(*) FROM "+TABLA+" WHERE "+CAMPO+" = "+ID+" AND ESTADO = '"+ESTADO+"' ");
        try
        {
            rs= obj.consultar(sql);
            if(rs.next())
            {
                total=rs.getInt(1);
                return total;
            }
            obj.limpiarRecursos();
        }
        catch(SQLException | ClassNotFoundException e)
            {
            } catch (Exception ex) {
            Logger.getLogger(ControlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 0;
    }
    
    public static int totalREAGENDADOS(String TABLA, String CAMPO1, String CAMPO01, String CAMPO2, String CAMPO02, String CAMPO3, int CAMPO03) throws ClassNotFoundException, SQLException, Exception 
    {
        int total;
        Manejador obj = new Manejador();
        String sql;
        ResultSet rs;
        sql=("SELECT COUNT(*) FROM "
                +TABLA
                +" WHERE "+CAMPO1+" = '"+CAMPO01+"' "
                + "AND "+CAMPO2+" = '"+CAMPO02+"' "
                + "AND "+CAMPO3+" = "+CAMPO03+" ");
        try
        {
            rs= obj.consultar(sql);
            if(rs.next())
            {
                total=rs.getInt(1);
                return total;
            }
            obj.limpiarRecursos();
        }
        catch(SQLException | ClassNotFoundException e)
            {
            } catch (Exception ex) {
            Logger.getLogger(ControlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 0;
    }
        
    public static Integer IDRegistro(String CAMPO, String TABLA, String CAMPOCONDICION, int CONDICION, String ORG) throws ClassNotFoundException, SQLException, Exception 
    {
        int x;
     
               Manejador obj = new Manejador();
               String sql;
               ResultSet rs;
               sql=("SELECT "+CAMPO+" FROM "+TABLA+" WHERE "+CAMPOCONDICION+" = "+CONDICION+" ORDER BY "+CAMPO+" "+ORG);
        try {
            rs= obj.consultar(sql);
            if(rs.next())
               {
                   x=rs.getInt(1);
                   return x;
               }
        } catch (Exception ex) {
            Logger.getLogger(ControlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 1;
    }
    
    public static void bitacora(String TABLA,int IDREGISTRO, String ACCION, int IDUSER, String COMENTARIOS, String REFERENCIA) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloBitacora mdl =  new ModeloBitacora();
        
        mdl.setTABLA(TABLA);
        mdl.setREGISTRO(IDREGISTRO);
        mdl.setACCION(ACCION);        
        mdl.setIDUSER(IDUSER);
        mdl.setCOMENTARIOS(COMENTARIOS);
        mdl.setREFERENCIA(REFERENCIA);
        mdl.setFECHA(G.FECHA(G.hoy(),true));
                
        ControlBitacora ctrl = new ControlBitacora();
        ctrl.guardarRegistro(mdl);
        
    }
    
    public static int totalT(String TABLA) throws ClassNotFoundException, SQLException, Exception 
    {
        int total;
        Manejador obj = new Manejador();
        String sql;
        ResultSet rs;
        sql=("select COUNT(*) from "+TABLA+" ");
        try
        {
            rs= obj.consultar(sql);
            if(rs.next())
            {
                total=rs.getInt(1);
                return total;
            }
            obj.limpiarRecursos();
        }
        catch(SQLException | ClassNotFoundException e)
            {

            } catch (Exception ex) {
            Logger.getLogger(ControlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 0;
    }
    
    public static int llenaCarrito(String NOMBRE, String CATEGORIA, int IDPENDIENTE, int IDEMPLEADO) throws ClassNotFoundException, SQLException, Exception
    {
        //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //Date hoy = new Date();
        //String FECHA = formato.format(hoy);
        
        ModeloKits mdlK;
        ControlKits ctrlK = new ControlKits();        
        mdlK = ctrlK.getRegistro(NOMBRE, CATEGORIA);
        int IDKIT = mdlK.getIDKIT();
        
        ModeloCarrito mdlC;
        ControlCarrito ctrlC;
        ModeloKitsDTabla mdlKDT = new ModeloKitsDTabla(IDKIT,"AUX");
        
        for(int i=0; i<mdlKDT.getRowCount(); i++)
        {
            int IDPRODUCTO = (int) mdlKDT.getValueAt(i, 0);
            int CANTIDAD = (int) mdlKDT.getValueAt(i, 1);
                        
            mdlC = new  ModeloCarrito();
            mdlC.setIDPENDIENTE(IDPENDIENTE);
            mdlC.setIDPRODUCTO(IDPRODUCTO);
            mdlC.setCANTIDAD(CANTIDAD);
            mdlC.setTOTAL(0);
            mdlC.setIDEMPLEADO(IDEMPLEADO);
            mdlC.setESTADO("REQUERIDO");
            mdlC.setFECHA(G.FECHA(G.hoy(),false));
            
            ctrlC = new ControlCarrito();
            ctrlC.guardarRegistro(mdlC);
        }
        return 1;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static boolean automatizacion(String ESTADO, String IPc, int IDCONTc, String FCORc) throws SQLException, Exception
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formato1 = new SimpleDateFormat("dd-MM-yyyy");
        Date hoy = new Date();
        //String FECHA = formato.format(hoy);
        String FECHA1 = formato1.format(hoy);
        int DIAS;
        long dif,difm;
        Date inicio,fin;
        int IDCONT;
        String FCOR;
        String IP;
        String userCCR = "admin";
        String passwordCCR = "012837DHN.*,";
        int port = 22;
        String keyFilePath = null;
        String keyPassword = null;
        
        boolean AUX = true;
        ModeloContratosTabla mdl;
        mdl = new ModeloContratosTabla(ESTADO,AUX);
        ControlContratos ctrl;
        
        String comando = null;
        Session session ;
        session = createSession(userCCR, "10.0.0.1", port, keyFilePath, keyPassword,passwordCCR);
                
        if(ESTADO.equals("VERIFICA"))
        {
            inicio=formato.parse(FCORc);
            fin=formato.parse(G.FECHA(G.hoy(),false));
            difm = inicio.getTime() - fin.getTime();
            dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);
            DIAS= (int) dif;
            
            if(DIAS>0)
            {
                ctrl = new ControlContratos();
                ctrl.actualizarEstado("ACTIVO", IDCONTc, "");
                //COMANDOS MIKROTIK
                comando = "/ip dhcp-server lease set [find address="+IPc+"] block-access=no";
                ejecutar(session,comando);
            }
            else
                if(DIAS<-5&&DIAS>=-30)
                {
                    if(!ESTADO.equals("SUSPENDIDO"))
                    {
                        ctrl = new ControlContratos();
                        ctrl.actualizarEstado("SUSPENDIDO", IDCONTc, "SUSPENDIDO EL DIA: "+G.FECHA(G.hoy(),true));

                        comando = "/ip dhcp-server lease set [find address="+IPc+"] block-access=yes";
                        ejecutar(session,comando);
                    }

                }
        }
        
        if(ESTADO.equals("ONEDAY"))
        {
            ctrl = new ControlContratos();
            ctrl.actualizarEstado("ACTIVO", IDCONTc);

            comando = "/ip dhcp-server lease set [find address="+IPc+"] block-access=no";
            ejecutar(session,comando);
            //System.out.println("EJECUTA COMANDO EN MIKROTIK:"+IDCONTc);
        }
        
        if(ESTADO.equals("AUTORIZAR"))
        {
            ctrl = new ControlContratos();
            ctrl.actualizarEstado("AUTORIZADO", IDCONTc);

            comando = "/ip dhcp-server lease set [find address="+IPc+"] block-access=no";
            ejecutar(session,comando);
        }
        
        if(IPc == null)
        {
            //BufferedWriter bw = new BufferedWriter(new FileWriter("\\"+"\\"+ip+"\\dhn\\LOGS\\"+FECHA1+".txt"));
            FileWriter nuevo = new FileWriter(G.rutaServidor+"LOGS\\"+FECHA1+".txt");
            PrintWriter pw = new PrintWriter(nuevo);
            
            for(int i=0; i<mdl.getRowCount(); i++)
            {
                //
                //FECHA=formateador.format(hoy);  
                FCOR = mdl.getValueAt(i, 1).toString();
                IDCONT = (int) mdl.getValueAt(i, 0);
                IP = mdl.getValueAt(i, 2).toString();
                inicio=formato.parse(FCOR);
                fin=formato.parse(G.FECHA(G.hoy(),false));
                difm = inicio.getTime() - fin.getTime();
                dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);
                DIAS= (int) dif;

                if(!IP.equals("0"))
                {
                    if(DIAS<=-30&&!ESTADO.equals("AUTORIZADO"))
                    {
                        ctrl = new ControlContratos();
                        ctrl.actualizarEstado("RETIRAR", IDCONT, "ENVIO A RETIRAR EL DIA: "+G.FECHA(G.hoy(),false));
                        
                        comando = "/ip dhcp-server lease set [find address="+IP+"] block-access=yes";
                        ejecutar(session,comando);
                        
                        String st = "RETIRAR-"+IDCONT+"-"+IP;
                        pw.println(st);
                        regOrden(IDCONT,G.FECHA(G.hoy(),false));
                    }
                    else
                    {
                        if(DIAS<-5&&DIAS>=-30&&!ESTADO.equals("AUTORIZADO"))
                        {
                            if(!ESTADO.equals("SUSPENDIDO"))
                            {
                                ctrl = new ControlContratos();
                                ctrl.actualizarEstado("SUSPENDIDO", IDCONT, "SUSPENDIDO EL DIA: "+G.FECHA(G.hoy(),false));
                                
                                comando = "/ip dhcp-server lease set [find address="+IP+"] block-access=yes";
                                ejecutar(session,comando);
                                
                                String st = "SUSPENDIDO-"+IDCONT+"-"+IP;
                                pw.println(st);
                            }

                        }
                    }
                    /*if(ESTADO.equals("ACTIVO"))
                    {
                        comando = "/ip dhcp-server lease set [find address="+IP+"] block-access=no";
                        ejecutar(session,comando);
                        System.out.println("EJECUTA COMANDO ACTIVO EN MIKROTIK:"+IDCONT);
                    }
                    if(ESTADO.equals("AUTORIZADO"))
                    {
                        comando = "/ip dhcp-server lease set [find address="+IP+"] block-access=no";
                        ejecutar(session,comando);
                        System.out.println("EJECUTA COMANDO ACTIVO EN MIKROTIK:"+IDCONT);
                    }*/
                }
            }
            nuevo.close();
        }        
        
            if (session != null && session.isConnected()) session.disconnect();
        
        return AUX;
    }
    
    public static Session createSession(String user, String host, int port, String keyFilePath, String keyPassword, String password) 
    {
        
        try {
            JSch jsch = new JSch();

            if (keyFilePath != null) {
                if (keyPassword != null) {
                    jsch.addIdentity(keyFilePath, keyPassword);
                } else {
                    jsch.addIdentity(keyFilePath);
                }
            }

            Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            Session session = jsch.getSession(user, host, port);
                        
            session.setPassword(password);
            
            session.setConfig(config);
            session.connect();

            return session;
        } catch (JSchException e) {
            System.out.println(e);
            return null;
        }
    
    }
    
    public static int ejecutar(Session session, String comando) throws JSchException, IOException {
        ChannelExec channel = null;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(comando);

            try (InputStream in = channel.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

                channel.connect(5000); // Timeout de conexión de canal

                String linea;
                while ((linea = reader.readLine()) != null) {
                    // Mantenemos la salida limpia
                    System.out.println("RouterOS Reply: " + linea);
                }
            }

            while (!channel.isClosed()) {
                try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }

            return channel.getExitStatus();
        } finally {
            if (channel != null) {
                channel.disconnect();
                //JOptionPane.showMessageDialog(null, "ERROR, Problema con conexion SSH", "VERIFICAR",JOptionPane.ERROR_MESSAGE);
            
            }
        }
    }
    
    public static void regOrden(int idCont, String FECHA) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloContratos mdlC;
        ControlContratos ctrlC = new ControlContratos();
        mdlC = ctrlC.getRegistrosIDINNER(idCont);
        
        ModeloPaquetes mdlP;
        ControlPaquetes ctrlP = new ControlPaquetes();
        mdlP = ctrlP.getRegistrosID(mdlC.getIDPAQUETE());
        
        if(!mdlP.getDESCRIPCION().contains("PREPAGO"))
        {
            int IDPENDIENTE = Registro("PENDIENTES","IDPENDIENTE","DESC");
                    
            ModeloPendientes x = new ModeloPendientes();

            x.setIDPENDIENTE(IDPENDIENTE);
            x.setIDCLIENTE(mdlC.getIDCLIENTE());
            x.setIDDIRECCION(mdlC.getIDDIRECCION());
            x.setNPEDIDO("DHN-2-"+idCont);
            x.setNPEDIDOF("");
            x.setCATEGORIA("INTERNET");        
            x.setSUBCATEGORIA("GENERAL");        
            x.setDESCRIPCION("RETIRO DE EQUIPO");     
            x.setCOMENTARIOS("");   
            x.setFREG(FECHA);
            x.setFEST(FECHA);
            x.setFFIN(FECHA);

            x.setESTADO("ABIERTO");

            ControlPendientes ctrlPe = new ControlPendientes();
            ctrlPe.guardarRegistro(x);
        }                    
    }
    
    public static int cut_services(String TABLA,String FECHA) throws ClassNotFoundException, SQLException, Exception 
    {
        int total;
        Manejador obj = new Manejador();
        String sql;
        ResultSet rs;
        sql=("select COUNT(*) from "+TABLA+" WHERE FECHA ='"+FECHA+"'");
        try
        {
            rs= obj.consultar(sql);
            if(rs.next())
            {
                total=rs.getInt(1);
                return total;
            }
            obj.limpiarRecursos();
        }
        catch(SQLException | ClassNotFoundException e)
            {
                
            } catch (Exception ex) {
            Logger.getLogger(ControlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 0;
    }
    
    public static void sendMessage(/*String phone, String apiKey, String message, String imageUrl*/) 
    {
        String phone = "5213921031128";  // Número sin + ni espacios
        String apiKey = "4811776";
        String message = "Hola";
        String imageUrl = "/imagenes/contrato.png";
        
        try 
        {
            /*String urlString = "https://api.callmebot.com/whatsapp.php?phone=" + phone
                    + "&apikey=" + apiKey
                    + "&text=" + URLEncoder.encode(message, "UTF-8")
                    + "&img=" + URLEncoder.encode(imageUrl, "UTF-8");*/
            String urlString = "https://api.callmebot.com/whatsapp.php?phone=" 
                    + phone
                    + "&text=" + message
                    + "&apikey=" + apiKey
                    /*+ "&img=" + URLEncoder.encode(imageUrl, "UTF-8")*/;

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Mensaje enviado con éxito.");
            } else {
                System.out.println("Error al enviar el mensaje.");
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public static void openweb(String URL,String TIPO) throws IOException, URISyntaxException
    {
        //java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
        switch(TIPO)
        {
            case "IP":
                Desktop.getDesktop().browse(new URI("https://"+URL));
                break;
            default:
                Desktop.getDesktop().browse(new URI(URL));
                break;
        }
        
    }
    
    public static void bandwidth(String user, String password,String IP, int IDPAQUETE) throws ClassNotFoundException, SQLException, Exception
    {
        //String appPath = System.getProperties().getProperty("user.dir");
        
        ModeloPaquetes mdl;
        ControlPaquetes ctrl = new ControlPaquetes();
        mdl = ctrl.getRegistrosID(IDPAQUETE);
        //System.out.println("PAQUETE: "+mdl.getDESCRIPCION());
        
        String []datos = mdl.getDESCRIPCION().split(" ");
        int velocidad = Integer.parseInt(datos[0]);
        int IN = velocidad * 1024;
        int OUT = IN * 2;
        //System.out.println("VELOCIDAD: "+velocidad);
        
        //String user = "manager";
        //String password = "012837DHN.*,";
        String host = IP;
        int port = 22;
        String keyFilePath = null;
        String keyPassword = null;
        Session session;
        String remoteA = "/tmp/";
        String local = G.rutaLocal+"\\";
        String local0 = G.rutaLocal+"\\CFG\\";
        String fileName = "system.cfg";
        BufferedReader br = null;
        String texto = null;
        FileWriter nuevo = new FileWriter(local0+fileName);
        PrintWriter pw = new PrintWriter(nuevo);            
        
       //CREA SESION SCP
        session = createSession(user, host, port, keyFilePath, keyPassword,password);
        //DESCARGA EL ARCHIVO system.cfg DESDE LA ANTENA A LA PC
        copyRemoteToLocal(session, remoteA, local, fileName);
        
        //Crear un objeto BufferedReader al que se le pasa 
        //   un objeto FileReader con el nombre del fichero
        br = new BufferedReader(new FileReader(fileName));
        //Leer la primera línea, guardando en un String
        texto = br.readLine();
        //Repetir mientras no se llegue al final del fichero
        while(texto != null)
        {
            //System.out.println("TEXTO: "+texto);
            //int tam = texto.length();
            String []datosT = texto.split("[.=]");
            String d0,d1,d2,d3,dcompleto;
            //System.out.println("TAM: "+tam);
                        
            d0 = datosT[0];
            if(d0!=null)
            {
                if(d0.equals("tshaper"))
                {
                    d1 = datosT[1];
                    if(d1!=null)
                    {
                        if(d1.equals("1")||d1.equals("2"))
                        {
                            d2 = datosT[2];
                            if(d2!=null)
                            {
                                if(d2.equals("output"))
                                {
                                    d3 = datosT[3];
                                    if(d3!=null)
                                    {
                                        dcompleto = d0+"."+d1+"."+d2+"."+d3;
                                        //System.out.println(dcompleto);
                                        switch(dcompleto)
                                        {
                                            case "tshaper.1.output.rate":
                                                texto="tshaper.1.output.rate="+IN;
                                                break;
                                            case"tshaper.2.output.rate":
                                                texto="tshaper.2.output.rate="+OUT;
                                                break;                        
                                            case "tshaper.2.output.burst":
                                                texto="tshaper.2.output.burst="+IN;
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }                               
                            }
                        }                        
                    }
                }                                    
            }
                          
            pw.println(texto);
            //Leer la siguiente línea
            texto = br.readLine();
        }
       
        //Cerrar el buffered
        //if(br != null)
        nuevo.close();
        br.close();        
        
        //crearNuevo();
        copyLocalToRemote(session, local0, remoteA, fileName);
        guarda_cambios(session,password);
        reiniciar(session);                
        
    }
    
    public static void copyRemoteToLocal(Session session, String from, String to, String fileName)
    {        
        try
        {
        from = from + File.separator + fileName;
        String prefix = null;

        if (new File(to).isDirectory()) {
            prefix = to + File.separator;
        }

        // exec 'scp -f rfile' remotely
        String command = "scp -f " + from;
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);

        // get I/O streams for remote scp
        OutputStream out = channel.getOutputStream();
        InputStream in = channel.getInputStream();

        channel.connect();

        byte[] buf = new byte[1024];

        // send '\0'
        buf[0] = 0;
        out.write(buf, 0, 1);
        out.flush();

        while (true) {
            int c = checkAck(in);
            if (c != 'C') {
                break;
            }

            // read '0644 '
            in.read(buf, 0, 5);

            long filesize = 0L;
            while (true) {
                if (in.read(buf, 0, 1) < 0) {
                    // error
                    break;
                }
                if (buf[0] == ' ') break;
                filesize = filesize * 10L + (long) (buf[0] - '0');
            }

            String file = null;
            for (int i = 0; ; i++) {
                in.read(buf, i, 1);
                if (buf[i] == (byte) 0x0a) {
                    file = new String(buf, 0, i);
                    break;
                }
            }

            // send '\0'
            buf[0] = 0;
            out.write(buf, 0, 1);
            out.flush();

            // read a content of lfile
            FileOutputStream fos = new FileOutputStream(prefix == null ? to : prefix + file);
            int foo;
            while (true) {
                if (buf.length < filesize) foo = buf.length;
                else foo = (int) filesize;
                foo = in.read(buf, 0, foo);
                if (foo < 0) {
                    // error
                    break;
                }
                fos.write(buf, 0, foo);
                filesize -= foo;
                if (filesize == 0L) break;
            }

            if (checkAck(in) != 0) {
                System.exit(0);
            }

            // send '\0'
            buf[0] = 0;
            out.write(buf, 0, 1);
            out.flush();
            
            fos.close();
            /*try {
                if (fos != null) fos.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }*/
        }

        channel.disconnect();
        
        }
        catch(IOException | JSchException IO)
        {
            JOptionPane.showMessageDialog(null, "ERROR, VERIFICA ANTENA CONECTADA\n MOTIVO: " + IO.getMessage(), "VERIFICAR",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(F.class.getName()).log(Level.SEVERE, null, IO);
        }

    }

    public static int checkAck(InputStream in) throws IOException 
    {
        int b = in.read();        
        if (b == 0) return b;
        if (b == -1) return b;

        if (b == 1 || b == 2) {
            StringBuilder sb = new StringBuilder();
            int c;
            do {
                c = in.read();
                sb.append((char) c);
            }
            while (c != '\n');
            if (b == 1) { // error
                System.out.print(sb.toString());
            }
            if (b == 2) { // fatal error
                System.out.print(sb.toString());
            }
        }
        return b;
    }
    
    public static void copyLocalToRemote(Session session, String from, String to, String fileName) throws JSchException, IOException 
    {
       
        boolean ptimestamp = true;

        //from = from + File.separator + fileName;
        from = from + fileName;

        // exec 'scp -t rfile' remotely
        String command = "scp " + (ptimestamp ? "-p" : "") + " -t " + to + "system.cfg";
        //String command = "scp " + from +" "+ to + fileName;
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);

        // get I/O streams for remote scp
        OutputStream out = channel.getOutputStream();
        InputStream in = channel.getInputStream();

        channel.connect();

        if (checkAck(in) != 0) {
            System.exit(0);
        }

        File _lfile = new File(from);

        if (ptimestamp) {
            command = "T" + (_lfile.lastModified() / 1000) + " 0";
            // The access time should be sent here,
            // but it is not accessible with JavaAPI ;-<
            command += (" " + (_lfile.lastModified() / 1000) + " 0\n");
            out.write(command.getBytes());
            out.flush();
            if (checkAck(in) != 0) {
                System.exit(0);
            }
        }

        // send "C0644 filesize filename", where filename should not include '/'
        long filesize = _lfile.length();
        command = "C0644 " + filesize + " ";
        if (from.lastIndexOf('/') > 0) {
            command += from.substring(from.lastIndexOf('/') + 1);
        } else {
            command += from;
        }

        command += "\n";
        out.write(command.getBytes());
        out.flush();

        if (checkAck(in) != 0) {
            System.exit(0);
        }

        // send a content of lfile
        FileInputStream fis = new FileInputStream(from);
        byte[] buf = new byte[1024];
        while (true) {
            int len = fis.read(buf, 0, buf.length);
            if (len <= 0) break;
            out.write(buf, 0, len); //out.flush();
        }

        // send '\0'
        buf[0] = 0;
        out.write(buf, 0, 1);
        out.flush();

        if (checkAck(in) != 0) {
            System.exit(0);
        }
        out.close();

        try {
            if (fis != null) fis.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        channel.disconnect();
        //session.disconnect();
    }
    
    public static void guarda_cambios(Session session, String pass) throws JSchException, IOException
    {
        String sudo_pass = pass;
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        ((ChannelExec) channel).setCommand( "cfgmtd -f /tmp/system.cfg -w");

        InputStream in = channel.getInputStream();
        OutputStream out = channel.getOutputStream();
        ((ChannelExec) channel).setErrStream(System.err);

        channel.connect();
        out.write((sudo_pass + "\n").getBytes());
        out.flush();

        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0)
                    break;
                System.out.print(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                System.out.println("exit-status: " + channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ee) {
                System.out.println(ee);
            }
        }
        channel.disconnect();
    }
    
    public static void reiniciar(Session session) throws JSchException, IOException
    {        
        String command = "reboot";
        
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);
        channel.connect();
        
        channel.disconnect();
    }
    
    public static void backup()
    {
        /*String bd = "dhn";
        String loginDB = config.vector.get(14).toString();
        String passwordDB = config.vector.get(15).toString();
        String ip = config.vector.get(12).toString();
        String puerto = config.vector.get(13).toString();*/
        SimpleDateFormat dateformat=new SimpleDateFormat("ddMMyyyy");
        Date calendario=new Date();
        String mysqlDumpPath = "\\\\"+G.ip+"\\xampp\\mysql\\bin\\mysqldump.exe"; // Ruta de mysqldump
        String backupFilePath = "\\\\"+G.ip+"\\DHN\\RESPALDOS DB\\"+G.db + dateformat.format(calendario.getTime()) + ".sql"; // Ruta de salida
        
        try
        {
            ProcessBuilder pb = new ProcessBuilder(
            mysqlDumpPath,
            "-h", G.ip,
            "-P", G.puerto,
            "-u", G.loginDB,
            "-p" + G.passwordDB,
            "--add-drop-database",
            "--databases", G.db);
            Process process = pb.start();
            InputStream is = process.getInputStream();
            FileOutputStream fos = new FileOutputStream(new File(backupFilePath));
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) > 0) {
                fos.write(buffer, 0, bytesRead);
            }
            fos.close();
            is.close();

            JOptionPane.showMessageDialog(null, "Auto Respaldo correcto", "EXITO",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "ERROR, NO SE GENERO EL ARCHIVO DE RESPALDO\nPOR EL SIGUIENTE MOTIVO: " + e.getMessage(), "VERIFICAR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*public static Map<String, String> leerExcel(String rutaExcel) throws FileNotFoundException, IOException 
    {
        Map<String, String> datos = new HashMap<>();        
        
        try (FileInputStream file = new FileInputStream(new File(rutaExcel));
             Workbook workbook = WorkbookFactory.create(file)) 
        {
            
            Sheet sheet = workbook.getSheetAt(0); // Primera hoja
            Iterator<Row> rowIterator = sheet.iterator();
            
            if (rowIterator.hasNext()) {
                Row headerRow = rowIterator.next();
                Row dataRow = rowIterator.next();
                
                for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                    String clave = headerRow.getCell(i).getStringCellValue();
                    String valor = dataRow.getCell(i).toString();
                    datos.put(clave, valor);
                }
            }
        } 
        catch (Exception e) 
        {
            
        }
        //System.out.println(datos);
        return datos;
    }
    
    public static void reemplazarMarcadores(String rutaWord, Map<String, String> datos,String rutaWordtmp) throws IOException, Docx4JException
    {
        try (FileInputStream wordFile = new FileInputStream(rutaWord); XWPFDocument document = new XWPFDocument(wordFile)) {
            // Reemplazar los marcadores en el documento de Word
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                reemplazarTexto(paragraph, datos);               
            }
            //reemplazar en tablas
            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            reemplazarTexto(paragraph, datos);
                            //System.out.println(paragraph);
                        }
                    }
                }
            }

            //reemplazar en encabezados y píes de pagina
            for (XWPFHeader header : document.getHeaderList()) {
                for (XWPFParagraph paragraph : header.getParagraphs()) {
                    reemplazarTexto(paragraph, datos);
                }
            }
            
            for (XWPFFooter footer : document.getFooterList()) {
                for (XWPFParagraph paragraph : footer.getParagraphs()) {
                    reemplazarTexto(paragraph, datos);
                }
            }
            // Guardar el documento modificado
            FileOutputStream out = new FileOutputStream(rutaWordtmp);
            document.write(out);            
            out.close();
        }
    }
    
    private static void reemplazarTexto(XWPFParagraph paragraph, Map<String, String> datos)
    {
        for (XWPFRun run : paragraph.getRuns()) {
            String texto = run.getText(0);
            if (texto != null) {
                for (Map.Entry<String, String> entry : datos.entrySet()) {
                    texto = texto.replace(entry.getKey(), entry.getValue());
                    //System.out.println(":"+texto);
                }
                run.setText(texto, 0);
            }
        }
    }*/
    
    public static Map<String, String> leerExcel(String rutaExcel) throws IOException {
        Map<String, String> datos = new HashMap<>();
        try (FileInputStream file = new FileInputStream(new File(rutaExcel));
             Workbook workbook = WorkbookFactory.create(file)) {
            
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            Row dataRow = sheet.getRow(1);

            if (headerRow != null && dataRow != null) {
                for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                    Cell cellClave = headerRow.getCell(i);
                    Cell cellValor = dataRow.getCell(i);
                    if (cellClave != null && cellValor != null) {
                        datos.put(cellClave.getStringCellValue(), cellValor.toString());
                    }
                }
            }
        }
        return datos;
    }

    public static void reemplazarMarcadores(String rutaWord, Map<String, String> datos, String rutaWordtmp) throws Exception {
        try (FileInputStream fis = new FileInputStream(rutaWord);
             XWPFDocument document = new XWPFDocument(fis)) {

            // Procesar el cuerpo del documento
            procesarCuerpo(document, datos);

            // Procesar Encabezados y Pies
            for (XWPFHeader header : document.getHeaderList()) procesarContenedor(header.getParagraphs(), datos);
            for (XWPFFooter footer : document.getFooterList()) procesarContenedor(footer.getParagraphs(), datos);

            try (FileOutputStream out = new FileOutputStream(rutaWordtmp)) {
                document.write(out);
            }
        }
    }

    private static void procesarCuerpo(XWPFDocument doc, Map<String, String> datos) throws Exception {
        procesarContenedor(doc.getParagraphs(), datos);
        for (XWPFTable table : doc.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    procesarContenedor(cell.getParagraphs(), datos);
                }
            }
        }
    }

    private static void procesarContenedor(List<XWPFParagraph> párrafos, Map<String, String> datos) throws Exception {
        for (XWPFParagraph p : párrafos) {
            reemplazarTextoProtegiendoFormato(p, datos);
        }
    }

    /*private static void reemplazarTextoProtegiendoFormato(XWPFParagraph paragraph, Map<String, String> datos) throws Exception {
        String textoParrafo = paragraph.getText();
        if (textoParrafo == null || textoParrafo.isEmpty()) return;

        boolean huboCambio = false;
        for (Map.Entry<String, String> entry : datos.entrySet()) {
            if (textoParrafo.contains(entry.getKey())) {
                textoParrafo = textoParrafo.replace(entry.getKey(), entry.getValue());
                huboCambio = true;
            }
        }

        if (huboCambio) {
            // TRUCO PARA LA FRAGMENTACIÓN Y LAS IMÁGENES:
            // En lugar de borrar todo, intentamos mantener el primer Run para conservar la sangría
            // y solo borramos el contenido de los demás.
            List<XWPFRun> runs = paragraph.getRuns();
            if (runs.size() > 0) {
                // Si el valor es una ruta de imagen (ejemplo: .jpg o .png)
                if (textoParrafo.toLowerCase().endsWith(".jpg") || textoParrafo.toLowerCase().endsWith(".png")) {
                    insertarImagenEnRun(paragraph, textoParrafo);
                } else {
                    runs.get(0).setText(textoParrafo, 0);
                    for (int i = runs.size() - 1; i > 0; i--) {
                        paragraph.removeRun(i);
                    }
                }
            }
        }
    }*/
    
    private static void reemplazarTextoProtegiendoFormato(XWPFParagraph paragraph, Map<String, String> datos) throws Exception {
    String textoParrafo = paragraph.getText();
    if (textoParrafo == null || textoParrafo.isEmpty()) return;

    boolean huboCambio = false;
    for (Map.Entry<String, String> entry : datos.entrySet()) {
        if (textoParrafo.contains(entry.getKey())) {
            // Reemplazo simple que mantiene caracteres especiales
            textoParrafo = textoParrafo.replace(entry.getKey(), entry.getValue());
            huboCambio = true;
        }
    }

    if (huboCambio) {
        List<XWPFRun> runs = paragraph.getRuns();
        if (!runs.isEmpty()) {
            // Verificamos si es imagen
            if (textoParrafo.toLowerCase().endsWith(".jpg") || textoParrafo.toLowerCase().endsWith(".png")) {
                insertarImagenEnRun(paragraph, textoParrafo);
            } else {
                // Seteamos el texto completo en el primer run
                XWPFRun firstRun = runs.get(0);
                // El 0 indica que reemplaza el texto existente en ese run
                firstRun.setText(textoParrafo, 0); 

                // Eliminamos los demás runs para que no se duplique el texto
                for (int i = runs.size() - 1; i > 0; i--) {
                    paragraph.removeRun(i);
                }
            }
        }
    }
    }

    private static void insertarImagenEnRun(XWPFParagraph p, String rutaImagen) throws Exception {
        // Limpiamos el párrafo
        for (int i = p.getRuns().size() - 1; i >= 0; i--) p.removeRun(i);
        
        XWPFRun run = p.createRun();
        File img = new File(rutaImagen);
        if (img.exists()) {
            try (FileInputStream is = new FileInputStream(img)) {
                // Ajusta el ancho y alto según necesites
                run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, img.getName(), Units.toEMU(200), Units.toEMU(200));
            }
        }
    }
    
    /*public static void convertirToPDF(String rutaWordtmp, String rutaPDF) throws IOException, InterruptedException, Docx4JException
    {   
        FileInputStream docxWord = new FileInputStream (rutaWordtmp);
        XWPFDocument document = new XWPFDocument(docxWord);        
        
        try {
            OutputStream out = new FileOutputStream(rutaPDF);
            PdfOptions options = PdfOptions.create();
            document.createNumbering(); // Fuerza la inicialización del motor de numeración
            PdfConverter.getInstance().convert(document , out, options);
            
 
        } catch (XWPFConverterException e) {

            System.out.println("Error en la conversión");
            e.printStackTrace();
        } catch (IOException e) {

            System.out.println("Error creando el fichero PDF");
            e.printStackTrace();
        }

    }*/
    
    public static void convertirToPDF(String rutaWordtmp, String rutaPDF) 
    {
        try (InputStream is = new FileInputStream(new File(rutaWordtmp));
             XWPFDocument document = new XWPFDocument(is);
             OutputStream out = new FileOutputStream(new File(rutaPDF))) {

            // 1. Configuración avanzada de opciones
            PdfOptions options = PdfOptions.create();

            // 2. Crucial: Manejo de fuentes para respetar métricas de sangría
            options.fontEncoding("Identity-H");
            
            // 3.- Forzar a que busque fuentes locales
            options.fontProvider(ITextFontRegistry.getRegistry());

            // 4. Forzar la carga de estilos y numeración (mejora sangrías de listas)
            if (document.getStyle() == null) {
              System.out.println("Aviso: El documento no tiene estilos definidos, se usarán genéricos.");
              //aplicarEstiloPorDefecto(document);
            }
            //aplicarEstiloPorDefecto(document);
            
            // 5. Inicialización SEGURA de numeración
            // Solo se crea si el documento carece completamente de la estructura
            if (document.getNumbering() == null) {
                document.createNumbering();
            }
            
            // 6. Ejecución de la conversión
            PdfConverter.getInstance().convert(document, out, options);

            System.out.println("Conversión finalizada con éxito.");

        } catch (Throwable e) {
            System.err.println("Error crítico en la conversión:");
            System.err.println("Mensaje: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
        
    
    public static void abrirArchivo(String archivo)
    {
        try 
        {
            File objetofile = new File (archivo);
            if(objetofile.exists())            
                Desktop.getDesktop().open(objetofile);                        
        }
        catch (IOException ex) 
        {

            //System.out.println(ex);
            System.out.println("Archivo no localizable en sistema de archivos..."+ex);
        }
        
    } 
    
    public static void abrirApp(String ruta) throws IOException
    {
        Runtime obj = Runtime.getRuntime();
        obj.exec(ruta);
        
    }
    
    public static void imprimir(String[] titulos,String[] datos,String rutaExcel, String rutaWord, String rutaWordtmp, String rutaPDF) throws IOException, Docx4JException, FileNotFoundException, JAXBException, InterruptedException, Exception
    {
        crearExcel(titulos, datos, rutaExcel);        
        Map<String, String> data = leerExcel(rutaExcel);
        reemplazarMarcadores(rutaWord, data,rutaWordtmp);
        convertirToPDF(rutaWordtmp, rutaPDF);
        abrirArchivo(rutaPDF);
    }
    
    public static void crearExcel(String[] titulos,String[] datos,String rutaExcel)
    {
        File archivo = new File(rutaExcel);

        // Usamos try-with-resources para asegurar que el Workbook y el Stream se cierren solos
        try (Workbook workbook = new XSSFWorkbook(); 
             FileOutputStream salida = new FileOutputStream(archivo)) {

            Sheet pagina = workbook.createSheet("Hoja1");

            // Estilo del encabezado
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            if(titulos!=null)
            {
                // Fila 0: Encabezados
                Row filaEncabezado = pagina.createRow(0);
                for (int i = 0; i < titulos.length; i++) {
                    Cell celda = filaEncabezado.createCell(i);
                    celda.setCellStyle(style);
                    celda.setCellValue(titulos[i]);
                }
            }
            

            // Fila 1: Datos
            Row filaDatos;
            //if(titulos!=null)            
                filaDatos = pagina.createRow(1);            
            //else
                //filaDatos = pagina.createRow(0);
            
            for (int i = 0; i < datos.length; i++) {
                Cell celda = filaDatos.createCell(i);
                celda.setCellValue(datos[i]);
            }

            // Ajustar el ancho de las columnas automáticamente (Opcional pero recomendado)
            if(titulos!=null)
            {
                for (int i = 0; i < titulos.length; i++)
                {
                    pagina.autoSizeColumn(i);
                }
            }            

            workbook.write(salida);
            System.out.println("Archivo Excel creado exitosamente en: " + rutaExcel);

        } catch (IOException e) {
            System.err.println("Error al generar el archivo Excel: " + e.getMessage());
        }
    }        
    
    /*public static String getMAC(String ip) throws IOException,Exception
    {   
            
        
        String cmd = "arp -A "+ip;
        Runtime r = Runtime.getRuntime();
        r = Runtime.getRuntime();
        Process pro = r.exec(cmd);

        BufferedReader br = new BufferedReader (new InputStreamReader (pro.getInputStream()));
        String aux=br.readLine();
        String aux2 = null;
        String arreglo = null;
        String aux3= null;


        int cont =0;
        while (aux != null) 
        {                        
            if (aux.contains(ip)) {
                //aux2+=aux.trim();
                aux2=aux.trim();
                aux3=aux2.replace(ip, "");
                arreglo=(String) aux3.subSequence(0, 27);

                return arreglo.toUpperCase().trim();
            }
            aux = br.readLine();

            cont++;
                    //return aux.toUpperCase();
        }
        br.close();
            
        return null;
    }*/
    
    public static float totales(String TABLA, String TABLA2, String CAMPO1, String CAMPO2, String SEMANA, int semana, String ANO, int ano) throws ClassNotFoundException, SQLException, Exception
    {
        float total=0;
        Manejador obj = new Manejador();
        String sql;
        ResultSet rs;
        sql=("select SUM("+TABLA+"."+CAMPO2+") from "+TABLA+" INNER JOIN "+TABLA2+" ON "+TABLA+"."+CAMPO1+" = "+TABLA2+"."+CAMPO1+" "
                + "where "+TABLA2+"."+SEMANA+" = "+semana+" AND "+TABLA2+"."+ANO+" = "+ano+" ");
        try
        {
            rs= obj.consultar(sql);
            if(rs.next())
            {
                total=rs.getInt(1);
                return total;
            }
            obj.limpiarRecursos();
        }
        catch(SQLException | ClassNotFoundException e)
            {
                System.out.println("DE SEGURO YA NO TENEMOS NADA EN ACTIVOS");
            } catch (Exception ex) {
            Logger.getLogger(ControlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return total;
    }
    
    public static int TIPOpaquete(int IDCONTRATO) throws Exception
    {       
        int x;
     
               Manejador obj = new Manejador();
               String sql;
               ResultSet rs;
               sql=("SELECT COUNT(*) FROM paquetes WHERE IDPAQUETE = "+IDCONTRATO+" AND DESCRIPCION LIKE '%PREPAGO%'");
        try {
            rs= obj.consultar(sql);
            if(rs.next())
               {
                   x = rs.getInt(1);
                   return x;
               }
        } catch (Exception ex) {
            Logger.getLogger(ControlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 0;
    }
    
    public static void clipboard(String ITEM)
    {
        StringSelection selection = new StringSelection(ITEM);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }    
       
    public static boolean buscarPalabra(String TEXTO, String PALABRA)
    {
        return TEXTO.contains(PALABRA);
    }
    
    public static boolean pingIP(String ip,int N) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            String command = os.contains("win") ? "ping -n "+N+" " + ip : "ping -c "+N+" " + ip;
            
            Process process = Runtime.getRuntime().exec(command);
            int returnVal = process.waitFor();
            
            return (returnVal == 0);
        } catch (IOException | InterruptedException ex) {
            return false;
        }
    }
    
    /*public static String pingMS(String ip, int N)
    {
        String finalResponseTime = null;
        try 
        {
            String command;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                command = "ping -n "+N+" " + ip;
            } else {
                command = "ping -c "+N+" " + ip;
            }

            Process process = Runtime.getRuntime().exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            boolean reachable = false;
            String responseTime = "N/A";

            while ((line = input.readLine()) != null) {
                if (line.contains("TTL=") || line.contains("ttl=")) {
                    reachable = true;
                    // Extraer tiempo de respuesta (formato puede variar según SO)
                    if (line.contains("time=") || line.contains("tiempo=")) {
                        int timeIndex = line.indexOf("time=");
                        if (timeIndex == -1) timeIndex = line.indexOf("tiempo=");
                        if (timeIndex != -1) {
                            String timePart = line.substring(timeIndex);
                            responseTime = timePart.split("=")[1].split(" ")[0];
                        }
                    }
                }
            }

            int exitValue = process.waitFor();
            final String status = (exitValue == 0 || reachable) ? "Activo" : "Inactivo";
            finalResponseTime = reachable ? responseTime : "Sin respuesta";

            // Esperar antes del siguiente ping (ej. 5 segundos)
            Thread.sleep(5000);
        } 
        catch (IOException | InterruptedException e) 
        {

        }
    return finalResponseTime;
    }    
    */
    
    public static String pingMS(String ip, int N) {
    try {
        String command;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            command = "ping -n " + N + " " + ip;
        } else {
            command = "ping -c " + N + " " + ip;
        }

        Process process = Runtime.getRuntime().exec(command);
        
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            
            String line;
            boolean reachable = false;
            String responseTime = "N/A";

            while ((line = input.readLine()) != null) {
                // Detectar si el host responde
                if (line.contains("TTL=") || line.contains("ttl=") ||
                    line.contains("bytes=") || line.contains("time=")) {
                    reachable = true;
                }
                
                // Extraer tiempo de respuesta
                String timeStr = extractResponseTime(line);
                if (timeStr != null) {
                    responseTime = timeStr;
                }
            }

            int exitValue = process.waitFor();
            String status = (exitValue == 0 || reachable) ? "Activo" : "Inactivo";
            
            return reachable ? responseTime + " ms" : "Sin respuesta";
            
        } // BufferedReader se cierra automáticamente aquí
        
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        return "Error: " + e.getMessage();
    }
}

// Método auxiliar para extraer el tiempo de forma más robusta
    private static String extractResponseTime(String line) {
        // Patrones comunes en diferentes sistemas
        String[] patterns = {"time=", "tiempo=", "time<", "tiempo<"};

        for (String pattern : patterns) {
            int idx = line.indexOf(pattern);
            if (idx != -1) {
                String rest = line.substring(idx + pattern.length());
                // Extraer número (hasta espacio, ms, o <)
                String[] parts = rest.split("[\\s<ms]+");
                if (parts.length > 0) {
                    try {
                        Double.parseDouble(parts[0]); // Validar que es número
                        return parts[0];
                    } catch (NumberFormatException e) {
                        // No es un número válido
                    }
                }
            }
        }
        return null;
    }
    
    public static long semanas(Date inicio, Date fin)
    {
        //long s;
        //Fechas de ejemplo (pueden ser tus campos JDateChooser o similar)
        //Date fechaInicio = new Date(122, 0, 1);  // 1 de enero de 2022
        //Date fechaFin = new Date(122, 11, 31); // 31 de diciembre de 2022

        // Convertir a Instant
        Instant instantInicio = inicio.toInstant();
        Instant instantFin = fin.toInstant();

        // Calcular el número de semanas
        long numeroSemanas = ChronoUnit.DAYS.between(instantInicio, instantFin);
        numeroSemanas = numeroSemanas / 7;
        System.out.println("Semanas: "+numeroSemanas);
        return numeroSemanas+1;
    }
        
    public static boolean existe(int IDEMPLEADO, String FECHA) throws Exception
    {       
        boolean existe = false;     
           Manejador obj = new Manejador();
           String sql;
           ResultSet rs;
           sql = "SELECT IDCABLE FROM cable WHERE IDEMPLEADO = "+IDEMPLEADO+" AND FECHA = '"+FECHA+"' ";
        try {
            rs= obj.consultar(sql);
            if(rs.next())
               {
                   return true;
               }
        } catch (Exception ex) {
            Logger.getLogger(ControlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return existe;
    }
    
    public static String buscar (JTable table,String dato, String TIPO, int col) 
    {
        final TableRowSorter<TableModel> sorter =new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        //int col = 5;
        String nombre = dato;
        if (nombre.length() == 0)        
           sorter.setRowFilter(null);        
        else
        {
            try
            {
                if(col>0)
                {
                    RowFilter<TableModel, Object> filtro1 = RowFilter.regexFilter("(?i)" + TIPO, col); // Columna 5
                    RowFilter<TableModel, Object> filtro2 = RowFilter.regexFilter("(?i)" + nombre); // Columna 1

                    List<RowFilter<TableModel, Object>> filtros = new ArrayList<>();
                    filtros.add(filtro1);
                    filtros.add(filtro2);
                    sorter.setRowFilter(RowFilter.andFilter(filtros));
                }
                else
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + nombre));
                /*switch(col)
                {
                    case 5:
                        RowFilter<TableModel, Object> filtro1 = RowFilter.regexFilter("(?i)" + TIPO, col); // Columna 5
                        RowFilter<TableModel, Object> filtro2 = RowFilter.regexFilter("(?i)" + nombre); // Columna 1

                        List<RowFilter<TableModel, Object>> filtros = new ArrayList<>();
                        filtros.add(filtro1);
                        filtros.add(filtro2);
                        sorter.setRowFilter(RowFilter.andFilter(filtros));
                        break;
                    default:
                        System.out.println("asdasd");
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + nombre));
                        break;
                }*/
                //if(TIPO.equals("")||TIPO == null)
                    //sorter.setRowFilter(RowFilter.regexFilter("(?i)" + nombre));
                /*else
                {
                    RowFilter<TableModel, Object> filtro1 = RowFilter.regexFilter("(?i)" + TIPO, 5); // Columna 5
                    RowFilter<TableModel, Object> filtro2 = RowFilter.regexFilter("(?i)" + nombre); // Columna 1
                    
                    List<RowFilter<TableModel, Object>> filtros = new ArrayList<>();
                    filtros.add(filtro1);
                    filtros.add(filtro2);
                    sorter.setRowFilter(RowFilter.andFilter(filtros));
                }*/
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog( null, "NO EXISTEN DATOS QUE COINCIDAN\n"
                        + "CON LAS PALABRAS ESCRITAS", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        return dato;
    }
    
    public static String selectarchivo(String destino,String DOC) throws IOException
    {
        SimpleDateFormat formato1 = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date hoy = new Date();
        String FECHA1 = formato1.format(hoy);
        String NARCHIVO = "";
        
        File ORIGEN = null;
        String DESTINO = null;
                
        JFileChooser fc = new JFileChooser();
        //Image icon = new ImageIcon(getClass().getResource("/imagenes/Icono.png")); 
        fc.addChoosableFileFilter(new ImageFilter());
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileView(new ImageFileView());
        fc.setAccessory(new ImagePreview(fc));
        fc.setPreferredSize(new Dimension(1280, 960));
        int estado = fc.showOpenDialog(null);
                
        if(estado == JFileChooser.APPROVE_OPTION)
        {
            ORIGEN = fc.getSelectedFile();
            String ARCHIVO = fc.getSelectedFile().getName();
            String EXTENSION = FilenameUtils.getExtension(ARCHIVO);
            NARCHIVO = FECHA1+"_"+DOC+"."+EXTENSION;
            DESTINO = "\\\\"+G.ip+"\\dhn\\"+destino+"\\"+NARCHIVO;
            File DOCUMENTO = new File (DESTINO);
            FileUtils.copyFile(ORIGEN, DOCUMENTO);          
            
        }
        return NARCHIVO;
    }
    
    public static void dimensionar(JTable tabla, String ATRIBUTO)
    {
        switch(ATRIBUTO)
        {
            case "AGENDA":
                tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(550);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(250);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(4).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(6).setCellRenderer(new RenderCelda(false,false,"AGENDA") );
                tabla.setRowHeight(55);
                break;
            case "ABONOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
                break;
            case "AHORROS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(200);
                break;
            case "ALMACEN":
                tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(450);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
                tabla.getColumnModel().getColumn(2).setCellRenderer(new RenderCelda(true,true,"") );        
                tabla.setRowHeight(40);
                break;
            case "ANTENAS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(400);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(40);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(40);
                break;
            case "ARTICULOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(1000);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(10);
                tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderCelda(true,true,"") );       
                tabla.setRowHeight(40);
                break;
            case "BONOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(400);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
                break;
            case "CLIENTES":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(900);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(50);                
                break;            
            case "CARRITOPD":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(40);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(400);        
                tabla.getColumnModel().getColumn(2).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(5).setCellRenderer(new RenderCelda(true,true,"CARRITO") );
                tabla.setRowHeight(30);
                break;
            case "CF":
                tabla.getColumnModel().getColumn(2).setCellRenderer(new RenderCelda(true,true,"VALES") );
                break;
            case "CONCILIAR":
                tabla.getColumnModel().getColumn(3).setPreferredWidth(75);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(75);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(800);
                tabla.getColumnModel().getColumn(0).setPreferredWidth(75);
                tabla.getColumnModel().getColumn(1).setCellRenderer(new RenderCelda(true,true,""));    
                tabla.setRowHeight(30);
                break;
            case "CORTES":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(20);
                break;
            case "CONTRATOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(250);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(40);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(250);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(7).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(8).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(9).setPreferredWidth(10);
                tabla.getColumnModel().getColumn(10).setPreferredWidth(10);
                tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(5).setCellRenderer(new RenderCelda(true,true,"PAQUETES") );
                tabla.getColumnModel().getColumn(9).setCellRenderer(new RenderCelda(true,true,"CONTRATOS") );        
                break;
            case "CONTRATOSIP":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(400);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(400);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(200);
                break;
            case "CORTESD":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(8);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(140);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                break;
            case "CORTESG":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(180);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                break;
            case "COTIZACIONES":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(400);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(7).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(8).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(4).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(5).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.setRowHeight(40);
                break;
            case "COTIZACIONESSER":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(4).setCellRenderer(new RenderCelda(false,false,"COTIZACIONESSER") );
                tabla.setRowHeight(40);
                break;
            case "CP":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(400);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(400);
                tabla.getColumnModel().getColumn(7).setPreferredWidth(50);
                break;
            case "DIRECCIONES":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(30);
                break;
            case "ECONTACTOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(5);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(250);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(10);
                //tabla.getColumnModel().getColumn(1).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.setRowHeight(15);
                break;
            case "EMPLEADOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(70);
                tabla.getColumnModel().getColumn(7).setPreferredWidth(70);
                tabla.getColumnModel().getColumn(8).setPreferredWidth(70);
                break;
            case "EPUESTOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
                break;    
            case "ENTRADAS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(600);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(1).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(5).setCellRenderer(new RenderCelda(true,true,"METODOS") );
                break;
            case "ENTRADASC":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(500);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
                break;
            case "EXTRAS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(700);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(2).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderCelda(true,true,"VALES") );                
                break;
            case "INFO":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                break;
            case "KITSR":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(1250);
                tabla.getColumnModel().getColumn(1).setCellRenderer(new RenderCelda(true,true,"KITSR") );
                tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderCelda(true,true,"") );                
                tabla.setRowHeight(30);
                break;
            case "MOVD":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(500);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
                break;
            case "MOVNOMINAS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(10);
                break;
            case "NO IDENTIFICADO":
                tabla.getColumnModel().getColumn(3).setPreferredWidth(600);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(75);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(75);
                tabla.getColumnModel().getColumn(0).setPreferredWidth(75);
                tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderCelda(true,true,""));    
                tabla.setRowHeight(30);
                break;    
            case "NOMINAS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(7).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(8).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(9).setPreferredWidth(150);
                tabla.getColumnModel().getColumn(10).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(11).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(12).setPreferredWidth(20);
                break;
            case "PAGOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(700);
                tabla.getColumnModel().getColumn(7).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(6).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(4).setCellRenderer(new RenderCelda(true,true,"METODOS") );
                tabla.setRowHeight(40);
                break;
            case "PAGOSVERI":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(600);
                tabla.getColumnModel().getColumn(7).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(6).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(4).setCellRenderer(new RenderCelda(true,true,"METODOS") );
                tabla.setRowHeight(40);
                break;
            case "PAQUETES":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
                break;
            case "PDOCS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(500);
                break;
            case "PENDIENTES":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(6).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(5).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(4).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(7).setCellRenderer(new RenderCelda(true,true,"PENDIENTES") );        
                break;
            case "PRESTACIONESP":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(400);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(7).setPreferredWidth(70);
                break;
            case "PRESTAMOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(200);        
                break;
            case "PRODUCTOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(00);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(700);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(7).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(8).setPreferredWidth(00);
                tabla.getColumnModel().getColumn(4).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.setRowHeight(40);
                break;
            case "PRODUCTOSD":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(1100);
                break;
            case "PRODUCTOSL":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(740);
                break;
            case "PROVEEDORES":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
                break;
            case "REGEMPLEADOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(10);
                break;
            case "REGPAGOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(400);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(4).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(6).setCellRenderer(new RenderCelda(true,true,"REGPAGOS") );
                tabla.getColumnModel().getColumn(2).setCellRenderer(new RenderCelda(true,true,"METODOS") );
                tabla.getColumnModel().getColumn(5).setCellRenderer(new RenderCelda(true,true,"PAGO DOMICILIADO") );
                break;
            case "REPORTES":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                break;
            case "SALIDAS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(600);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(1).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(5).setCellRenderer(new RenderCelda(true,true,"METODOS") );        
                break;
            case "SALIDASCAJA":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(500);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
                break;
            case "USUARIOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(900);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
                break;
            case "VACACIONES":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(400);
                break;
            case "VEHICULOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(500);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(600);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(200);
                break;
            case "VALES":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(700);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(2).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderCelda(true,true,"VALES") );
                break;
            case "VSEGUROS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(10);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
                break;
            case "VSERVICIOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(10);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(5).setPreferredWidth(400);
                tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(5).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.setRowHeight(40);
                break;
            case "KITS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(1100);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
                //tabla.getColumnModel().getColumn(2).setCellRenderer(new RenderCelda(true,true,"") );
                //tabla.setRowHeight(40);
                break;
            case "PAGOSH":
                TableColumnModel tcm = tabla.getTableHeader().getColumnModel();
                tcm.removeColumn(tabla.getColumnModel().getColumn(5));
                tcm.removeColumn(tabla.getColumnModel().getColumn(4));
                tcm.removeColumn(tabla.getColumnModel().getColumn(0));
                tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(30);     
                tabla.getTableHeader().setVisible(false);
                break;
            default:
                break;
        }
        tabla.setAutoCreateRowSorter(true);
    }
    
    public static void resizer(JPanel panel,JScrollPane jsp, JTable tabla,JTextArea area) throws ClassNotFoundException, SQLException
    {
        panel.revalidate();
        if(jsp!=null)
        {
            int anchoT = 190; //este asi lo pongo
            int altoT = tabla.getRowCount() * 11; //ponemos segun el alto de la fuente
            tabla.setPreferredSize(new Dimension(anchoT,altoT));
            //jsp.setPreferredSize(new Dimension(anchoT+3, altoT+3)); //le aumentamos los bordes
            jsp.setViewportView(tabla);
            jsp.setSize(tabla.getPreferredScrollableViewportSize());
            //jsp.setAutoscrolls(false);
            panel.setSize(new Dimension(anchoT+5,altoT+100));
            panel.setPreferredSize(new Dimension(anchoT+5,altoT+100));
            panel.setMaximumSize(new Dimension(anchoT+5,altoT+100));
            panel.setMinimumSize(new Dimension(anchoT+5,altoT+100));
        }
        if(area!=null)
        {
            // Obtenemos el ancho actual del JTextArea
            int ancho = area.getWidth();
            if (ancho <= 0) return; // Evitamos errores si el área no es visible aún

            FontMetrics fm = area.getFontMetrics(area.getFont());
            String texto = area.getText();

            // Calculamos el alto de una línea ($altoLinea$)
            int altoLinea = fm.getHeight();

            // Calculamos cuántos caracteres caben en el ancho disponible
            int caracteresPorLinea = ancho / fm.charWidth('A');

            // Estimamos el número de líneas necesarias
            int lineasEstimadas = (int) Math.ceil((double) texto.length() / caracteresPorLinea);

            // Calculamos el alto total ($altoTotal = lineas \times altoLinea$)
            int altoTotal = Math.max(altoLinea, lineasEstimadas * altoLinea);

            // Ajustamos el tamaño del componente
            area.setPreferredSize(new Dimension(ancho, altoTotal));
            area.revalidate(); // Actualiza el panel para reflejar el cambio
        }
        
        panel.repaint(); //repintamos el panel, suponiendo que toma las reglas de gridlayoul
        
        //int anchoS = jsp.getPreferredSize().width;                   //ancho scroll pane
        //int anchoS = 190;
        //int alturaS = jsp.getViewport().getViewSize().height;        //altura viewport        
        
        /*if(h1<alturaS)
            jsp.setSize(new Dimension(w, h1+27));
        else
            jsp.setSize(tabla.getPreferredScrollableViewportSize());*/
        /*int anchoS = 0;
        for (int i = 0; i < tabla.getColumnCount(); i++) 
        {
            anchoS += tabla.getColumnModel().getColumn(i).getPreferredWidth();
        }*/
        //int alturaS = tabla.getRowHeight() * tabla.getRowCount() + tabla.getTableHeader().getPreferredSize().height;
        //jsp.setPreferredSize(new Dimension(anchoS, alturaS));
        
        
        
        //int b=a*20;
        //int b = tabla.getRowHeight() * tabla.getRowCount();
        
        /*int b = 20 * tabla.getRowCount();
        System.out.println("b: "+b);
        
        tabla.setPreferredSize(new Dimension(190,b));
        tabla.setMaximumSize(new Dimension(190,b));
        tabla.setMinimumSize(new Dimension(190,b));
        tabla.setSize(190, b);
        
        //int alturaTS = tabla.getPreferredScrollableViewportSize().height; //altura tabla
        int alturaTS = b+50;
        
        System.out.println("alturaTS: "+alturaTS);
        
        jsp.setPreferredSize(new Dimension(190,alturaTS));
        jsp.setMaximumSize(new Dimension(190,alturaTS));
        jsp.setMinimumSize(new Dimension(190,alturaTS));
        jsp.setSize(190, alturaTS);
        jsp.setAutoscrolls(false);
        
        int alturaN = b+35;       
        System.out.println("alturaN: "+alturaN);
        
        panel.setSize(new Dimension(anchoS,alturaN));
        panel.setPreferredSize(new Dimension(anchoS,alturaN));
        panel.setMaximumSize(new Dimension(anchoS,alturaN));
        panel.setMinimumSize(new Dimension(anchoS,alturaN));
        
        panel.repaint();*/

        //this.tblTABLA.setModel(model);*/
        
        /*int anchoT = 190;
        int altoT = tabla.getRowHeight() * tabla.getRowCount();
        tabla.setPreferredSize(new Dimension(anchoT,altoT));
        
        tabla.setPreferredSize(new Dimension(anchoT,altoT));
        tabla.setMaximumSize(new Dimension(anchoT,altoT));
        tabla.setMinimumSize(new Dimension(anchoT,altoT));
        tabla.setSize(anchoT, altoT);
        
        int anchoS = 0;
        for (int i = 0; i < tabla.getColumnCount(); i++) 
        {
            anchoS += tabla.getColumnModel().getColumn(i).getPreferredWidth();
        }
        int alturaS = tabla.getRowHeight() * tabla.getRowCount() + tabla.getTableHeader().getPreferredSize().height;
        jsp = new JScrollPane(tabla);
        jsp.setPreferredSize(new Dimension(anchoS, alturaS));
        
        panel = new JPanel(new BorderLayout());
        panel.add(jsp, BorderLayout.CENTER);

        // Opcional: Ajustar el tamaño del JPanel al contenido
        panel.setPreferredSize(jsp.getPreferredSize());
        panel.repaint();*/
    }
    
    public static boolean candado()
    {
        boolean x = false;
        JPasswordField pwd = new JPasswordField(10);
        pwd.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Intoduce el PIN de 4 Digitos", 
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("Tahoma", 1, 10)));
        pwd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);        
        int action = JOptionPane.showConfirmDialog(null, pwd,"PIN DE DESBLOQUEO",JOptionPane.OK_CANCEL_OPTION);
        if(action == 0)
        {            
            if(String.copyValueOf(pwd.getPassword()).equals("4527"))            
                x = true;                            
            else
                JOptionPane.showMessageDialog(null, "PIN incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return x;
    }
    
    public static boolean comparar(String conceptoBD, String concepto)
    {
        boolean x = false;        
        String a = conceptoBD.toUpperCase().replaceAll("\\s", "").trim();
        String b = concepto.toUpperCase().replaceAll("\\s", "").trim();     
        int menor = Math.min(a.length(), b.length());        
        if(a.substring(0, menor).equals(b.substring(0, menor)))
            x = true;        
        return x;
    }
    
    public static void noENTER(JTable tabla)
    {
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        tabla.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "none");
    }
    
    public static boolean ENTER(KeyEvent evt)
    {
        boolean x = false;
        if (evt.getKeyCode()==KeyEvent.VK_ENTER)
            x = true;
        return x;
    }
    
    public static boolean SHIFT(KeyEvent evt)
    {
        boolean x = false;
        if (evt.getKeyCode()==KeyEvent.VK_SHIFT)
            x = true;
        return x;
    }
    
    public static KeyEvent MAYUS(KeyEvent evt)
    {
        Character c = evt.getKeyChar();
        if(Character.isLetter(c))         
           evt.setKeyChar(Character.toUpperCase(c));
        return evt;
    }
    
    /*public static KeyEvent NUM(KeyEvent evt, String str)
    {
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '.') 
                    evt.consume(); // Ignorar si no es número ni punto              
        if (c == '.' && str.contains(".")) 
                    evt.consume(); // Ignorar si ya hay un punto
        return evt;        
    }*/
    
    public static KeyEvent NUM(KeyEvent evt, String str)
    {
        char c = evt.getKeyChar();

        // Permitir dígitos, punto y signo negativo
        if (!Character.isDigit(c) && c != '.' && c != '-') 
            evt.consume(); // Ignorar si no es número, punto ni negativo

        // Validar el punto: solo si no existe ya
        if (c == '.' && str.contains(".")) 
            evt.consume(); // Ignorar si ya hay un punto

        // Validar el signo negativo: solo al inicio y una sola vez
        if (c == '-') {
            // Si el string no está vacío o ya contiene un negativo
            if (str.length() > 0 || str.contains("-")) 
                evt.consume(); // Ignorar si no está al inicio o ya existe
        }

        return evt;        
    }
    
    public static KeyEvent NUM(KeyEvent evt)
    {
        Character c = evt.getKeyChar();              
        if((c<'0' || c>'9')) evt.consume();
        return evt;
    }
    
    public static boolean clickD(MouseEvent evt, JTable tabla)
    {
        boolean x = false;
        if (SwingUtilities.isRightMouseButton(evt))
        {
            Point p = evt.getPoint();
            int row = tabla.rowAtPoint(p);
            // Si el click fue sobre una fila válida
            if (row >= 0 && row < tabla.getRowCount()) {
                // Seleccionar la fila
                tabla.setRowSelectionInterval(row, row);
                // Asegurar que la tabla gane el foco (FocusGained)
                tabla.requestFocusInWindow();
                x = true;
            }            
        }        
        return x;
    }
    
    public static boolean dosClick(MouseEvent evt, JTable tabla)
    {
        boolean x = false;
        if (evt.getClickCount() > 1)
            x = true; 
        return x;
    }
        
    public static void Imprimir(JPanel pnl)
    {
        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat pf = new PageFormat();
        Paper p = new Paper();
        p.setImageableArea(0,0,600,800);
        pf.setPaper(p);
        job.setPrintable((Printable) pnl,pf);
        try { 
            job.print(); 
        }catch (PrinterException e) { System.out.println("Error de impresión: " + e); }            
    }
    
    public static void getOptionPane(JOptionPane op, JPanel pnl)
    {
        //JOptionPane op;
        if(op != null)        
            return;        
        Component pdr = pnl.getParent(); 
        while(pdr.getParent() != null)
        {
            if(pdr instanceof JOptionPane)
            {
                op = (JOptionPane)pdr;
                break;
            }            
            pdr = pdr.getParent();
        }
    }
    
    public static float monto(String TABLA, String CAMPO1, String CAMPO1S, String CAMPO2, boolean CAMPO2B ) throws Exception
    {
        Manejador mane = new Manejador();
        String sql;
        ResultSet rs;
        
        float a = 0;
        
        if(CAMPO2 == null)             
            sql = "SELECT sum(monto) FROM "+TABLA+" WHERE "+CAMPO1+" = '"+CAMPO1S+"' ";               
        else        
            sql = "SELECT sum(monto) FROM "+TABLA+" WHERE "+CAMPO1+" = '"+CAMPO1S+"' AND "+CAMPO2+" = "+CAMPO2B+" ";
            
        rs = mane.consultar(sql);
        
        while(rs.next())
        {
            a = rs.getFloat(1);            
        }
        
        mane.limpiarRecursos();
        return a;
    }
    
    public static boolean existe(String TABLA, String CAMPO1, String CAMPO2, String CAMPO3, String CAMPO4, String CAMPO5,int CAMPOI1, int CAMPOI2, int CAMPOI3,int CAMPOI4, float CAMPOF1, int CAMPOF2) throws Exception
    {
        Manejador mane = new Manejador();
        String sql = null;
        ResultSet rs;
        
        boolean existe;
        switch(TABLA)
        {
            case "PAGOS":
                sql = "SELECT "+CAMPOI1+" FROM "+TABLA+" WHERE "+CAMPO2+" LIKE '%"+CAMPO3+"%' AND "+CAMPO4+" = '"+CAMPO5+"' ";
                break;
            case "NOMINAS":
                //sql = "SELECT nominas.IDNOMINA FROM "+TABLA+" WHERE nominas.IDEMPLEADO = "+IDEMPLEADO+" AND nominas.SEMANA = "+SEMANA+" AND nominas.ANO = "+ANO+" ";
                sql = "SELECT "+CAMPOI1+" FROM "+TABLA+" WHERE "+CAMPO2+" = "+CAMPOI2+" AND "+CAMPO3+" = "+CAMPOI3+" AND "+CAMPO4+" = "+CAMPOI4+" ";
                break;
            case "PAGOSTRANS":
                sql = "SELECT "+CAMPO1+" FROM "+CAMPO5+" WHERE "+CAMPO2+" like '%"+CAMPO3+"%' AND "+CAMPO4+" = "+CAMPOI1+" ";
                break;
            default:
                break;
        }
        rs = mane.consultar(sql);
        existe = rs.next();
        
        return existe;
    }
    
    public static void exportarXLS(JTable tabla,String Ruta)
    {
        String rutaExcel = G.rutaLocal+Ruta+".xls";
        int eleccion = JOptionPane.showConfirmDialog(null, "DESEA EXPORTAR DATOS A EXCEL");
        if (eleccion ==0)
        {
            Excel exportador=new Excel(tabla,new File(rutaExcel),"Hoja1");
            boolean exportacionOk=exportador.export();
            if(exportacionOk)            
            {
                JOptionPane.showMessageDialog(null,"LA EXPORTACION SE REALIZO CORRECTAMENTE,\nARCHIVO GUARDADO EN LA UNIDAD C:","EXPORTANDO DATOS",JOptionPane.INFORMATION_MESSAGE);
                abrirArchivo(rutaExcel);
            }
            else            
                JOptionPane.showMessageDialog(null,"ERROR AL EXPORTAR\nSELECCIONE UN REGISTRO DE LA TABLA","VERIFICAR",JOptionPane.ERROR_MESSAGE);            
        }
    }
    
    public static void viewBitacora(String TABLA,String SERVICIO, String NOMBRE, String DOMICILIO) throws ClassNotFoundException, SQLException, Exception
    {
        ViewBitacora datos;
        datos = new ViewBitacora(null,true);
        datos.mostrar(TABLA,SERVICIO, NOMBRE, DOMICILIO);
        datos.setVisible(true);
    }
    
    public static void actualizarRegistro(String TABLA, 
            String CAMPO1, int CAMPO1I, String CAMPO1S, 
            String COND1, int COND1I, String COND1S) throws ClassNotFoundException, SQLException, Exception
    {
        Manejador mane = new Manejador();
        String sql;
        sql = "update "+TABLA+" set "
                +CAMPO1+"= '"+CAMPO1S+"' "
                + "where "+COND1+" = " +COND1I+ " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    /*public static ImageIcon previsualizar(JLabel lbl, String NARCHIVO, String ruta) throws IOException
    {                
        int w = 350;
        int h = 150;
        if(lbl!=null)
        {
            lbl.setText("");
            w =lbl.getWidth();
            h = lbl.getHeight();            
        }

        //String ruta = "\\"+"\\"+G.ip+"\\dhn\\PDOCS\\"+NARCHIVO;
        ruta = ruta + NARCHIVO;
        
        ImageIcon ico = null;
        //ImageIcon tmpIcon = new ImageIcon(ruta);
        
        String []datos = NARCHIVO.split("\\.");            
        String extension = datos[1];
        switch(extension)
        {
            case "pdf":
                System.setProperty("fontbox.fontcache", "null");
                //System.setProperty("pdfbox.fontcache", System.getProperty("java.io.tmpdir") + "/.pdfbox.cache");
                File pdfFile = new File(ruta);
                try {
                    // 1. Cargar el documento PDF
                    // Para PDFBox 3.x use Loader.loadPDF()
                    // Para PDFBox 2.x use PDDocument.load(pdfFile)
                    try (PDDocument document = Loader.loadPDF(pdfFile)) 
                    {

                        // 2. Instanciar PDFRenderer
                        PDFRenderer pdfRenderer = new PDFRenderer(document);

                        // 3. Renderizar la primera página (índice 0) a una imagen
                        // DPI bajo (96-150) para previsualización rápida
                        BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 96, ImageType.RGB);

                        // 4. Mostrar la imagen en el JLabel
                        //label.setIcon(new ImageIcon(bim));
                        ico = new ImageIcon(bim);
                    }
                }  catch (IOException e) {
                    if (e.getMessage().contains("PostScriptTable")) {
                        // Loguear el error pero continuar
                        System.err.println("Error en fuente, pero continuando: " + e.getMessage());
                    } else {
                        throw e; // Lanzar si es otro error
                    }
                    //System.err.println("Error en fuente, pero continuando: " + e.getMessage());
                }
                break;
            default:
                ico = new ImageIcon(ruta);
                break;
        }
            
            if(ico != null)
            {
                try {
                    // 1. Cargar la imagen original
                    //iconOriginal = new ImageIcon(ruta);

                    // 2. Obtener las dimensiones del JLabel para escalar
                    //int anchoLabel = 200;
                    //int altoLabel = 200;

                    // 3. Escalar la imagen usando SCALE_SMOOTH para mejorar la calidad
                    Image imgEscalada = ico.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);

                    // 4. Crear un nuevo ImageIcon con la imagen escalada
                    ImageIcon iconEscalado = new ImageIcon(imgEscalada);

                    // 5. Asignar al JLabel
                    //lbl.setIcon(iconEscalado);
                    ico = iconEscalado;
                } catch (Exception e) {
                    System.out.println("Error al cargar la imagen: " + e.getMessage());
                }     
            }
        return ico;        
    }   */ 
    
    /*public static ImageIcon previsualizar(JLabel lbl, String NARCHIVO, String ruta) throws IOException
    {                
        int w = 350;
        int h = 150;
        if(lbl!=null)
        {
            lbl.setText("");
            w =lbl.getWidth();
            h = lbl.getHeight();            
        }
        
        ruta = ruta + NARCHIVO;
        
        ImageIcon ico = null;
        
        String []datos = NARCHIVO.split("\\.");            
        String extension = datos[1].toLowerCase(); // Convertir a minúsculas para comparación uniforme
        
        switch(extension)
        {
            case "pdf":
                System.setProperty("fontbox.fontcache", "null");
                File pdfFile = new File(ruta);
                try {
                    try (PDDocument document = Loader.loadPDF(pdfFile)) 
                    {
                        PDFRenderer pdfRenderer = new PDFRenderer(document);
                        BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 96, ImageType.RGB);
                        ico = new ImageIcon(bim);
                    }
                }  catch (IOException e) {
                    if (e.getMessage().contains("PostScriptTable")) {
                        System.err.println("Error en fuente, pero continuando: " + e.getMessage());
                    } else {
                        throw e;
                    }
                }
                break;
                
            case "webp":
                // Usar ImageIO para leer WebP (requiere biblioteca adicional)
                try {
                    File webpFile = new File(ruta);
                    BufferedImage webpImage = ImageIO.read(webpFile);
                    if (webpImage != null) {
                        ico = new ImageIcon(webpImage);
                    } else {
                        // Fallback: intentar cargar como imagen normal
                        ico = new ImageIcon(ruta);
                    }
                } catch (IOException e) {
                    System.err.println("Error al cargar WebP: " + e.getMessage());
                    // Intentar cargar como imagen normal como fallback
                    ico = new ImageIcon(ruta);
                }
                break;

                
            default:
                ico = new ImageIcon(ruta);
                break;
        }
        
        if(ico != null)
        {
            try {
                Image imgEscalada = ico.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                ImageIcon iconEscalado = new ImageIcon(imgEscalada);
                ico = iconEscalado;
            } catch (Exception e) {
                System.out.println("Error al cargar la imagen: " + e.getMessage());
            }     
        }
        
        return ico;        
    }*/
    
    public static ImageIcon previsualizar(JLabel lbl, String NARCHIVO, String ruta) throws IOException
    {   
        ImageIcon ico = null;
        if(NARCHIVO.equals(null)||NARCHIVO.equals("")||NARCHIVO.equals("Arrasta y Suelta"))
            NARCHIVO="0.png";
        
            int w = 350;
            int h = 150;
            if(lbl!=null)
            {
                lbl.setText("");
                w = lbl.getWidth();
                h = lbl.getHeight();            
            }
            //System.out.println(NARCHIVO);
            ruta = ruta + NARCHIVO;

            String []datos = NARCHIVO.split("\\.");            
            String extension = datos[1].toLowerCase();

            switch(extension)
            {
                case "pdf":
                    System.setProperty("fontbox.fontcache", "null");
                    File pdfFile = new File(ruta);
                    try {
                        try (PDDocument document = Loader.loadPDF(pdfFile)) 
                        {
                            PDFRenderer pdfRenderer = new PDFRenderer(document);
                            BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 96, ImageType.RGB);
                            ico = new ImageIcon(bim);
                        }
                    }  catch (IOException e) {
                        if (e.getMessage().contains("PostScriptTable")) {
                            System.err.println("Error en fuente, pero continuando: " + e.getMessage());
                        } else {
                            throw e;
                        }
                    }
                    break;

                case "webp":
                    try {
                        File webpFile = new File(ruta);
                        BufferedImage webpImage = ImageIO.read(webpFile);
                        if (webpImage != null) {
                            ico = new ImageIcon(webpImage);
                        } else {
                            // Si ImageIO falla, intentar con Toolkit (puede funcionar en algunos casos)
                            ico = new ImageIcon(Toolkit.getDefaultToolkit().createImage(ruta));
                        }
                    } catch (IOException e) {
                        System.err.println("Error al cargar WebP: " + e.getMessage());
                        // Último recurso
                        ico = new ImageIcon(Toolkit.getDefaultToolkit().createImage(ruta));
                    }
                    break;

                default:
                    ico = new ImageIcon(ruta);
                    break;
            }

            // Resto de tu código para escalar...        
            if(ico != null)
            {
                try {
                    Image imgEscalada = ico.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                    ImageIcon iconEscalado = new ImageIcon(imgEscalada);
                    ico = iconEscalado;
                } catch (Exception e) {
                    System.out.println("Error al cargar la imagen: " + e.getMessage());
                }     
            }
        
        return ico;
    }
    
    
    
    
    public static TitledBorder tituloBorde(String TITULO, String FUENTE, int TFUENTE, Color COLOR)
    {
        // 1. Definir la fuente Tahoma, tamaño 10
        Font fuenteTitulo = new Font(FUENTE, Font.BOLD, TFUENTE);

        // 2. Crear un borde de línea simple (opcional, para que se vea mejor)
        Border bordeLinea = BorderFactory.createLineBorder(Color.GRAY);

        // 3. Crear el borde titulado con la fuente definida
        TitledBorder bordeTitulo = BorderFactory.createTitledBorder(
            bordeLinea, 
            TITULO, 
            TitledBorder.LEFT, 
            TitledBorder.TOP, 
            fuenteTitulo, 
            COLOR // Color del texto del título
        );
        
        return bordeTitulo;
    }
    
    public static void actualizarEstado(String TABLA, String ESTADO, boolean ESTADOB, int ID, String CAMPO1, String CONDICION) throws Exception
    {
        Manejador obj = new Manejador();
        String sql;
        sql = "update "+TABLA+" set "
                +CAMPO1+" = " + ESTADOB
                + " where "+CONDICION+" = " + ID + " ";
        
        obj.Ejecutar(sql);
        obj.limpiarRecursos();
    }
    
    public static void addDD(String ACCION, String TABLA,String CATEGORIA) throws ClassNotFoundException, SQLException, Exception
    {
        RegDetallesDisp vista;            
        vista = new RegDetallesDisp(null,true);
        vista.mostrar(0,ACCION, TABLA, CATEGORIA);
        vista.setVisible(true);
    }
    
    public static void copiarPanel(JPanel panel) {
        // 1. Crear la imagen
        BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        
        // 2. Pintar el panel en la imagen
        panel.paint(g2d);
        g2d.dispose();

        // 3. Crear el objeto Transferable
        ImageSelection imgSel = new ImageSelection(img);
        
        // 4. Copiar al portapapeles
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(imgSel, null);
    }

    // Clase auxiliar para transferir la imagen
    public static class ImageSelection implements Transferable {
        private final Image image;
        public ImageSelection(Image image) { this.image = image; }
        
        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[] { DataFlavor.imageFlavor };
        }
        
        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return DataFlavor.imageFlavor.equals(flavor);
        }
        
        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (!DataFlavor.imageFlavor.equals(flavor)) throw new UnsupportedFlavorException(flavor);
            return image;
        }
    }
    
    public static double contarCable(String EMPRESA,String INICIO, String FIN) throws ClassNotFoundException, SQLException, Exception 
    {
        double x = 0;     
        Manejador obj = new Manejador();
        String sql = null;
        ResultSet rs;
        switch(EMPRESA)
        {
            case "DHN":
                sql=("SELECT SUM(carrito.CANTIDAD) "
                        + "FROM ordenesp "
                        + "INNER JOIN pendientes ON ordenesp.IDPENDIENTE = pendientes.IDPENDIENTE "
                        + "INNER JOIN carrito ON pendientes.IDPENDIENTE = carrito.IDPENDIENTE "
                        + "INNER JOIN productos ON carrito.IDPRODUCTO = productos.IDPRODUCTO "
                        + "WHERE pendientes.CATEGORIA = 'INTERNET' "
                        + "AND pendientes.ESTADO = 'FINALIZADO' "
                        + "AND carrito.ESTADO = 'USADO' "
                        + "AND productos.IDPRODUCTO = 78 "
                        + "AND STR_TO_DATE(ordenesp.FFIN, '%d/%m/%Y') "
                        + "BETWEEN '"+INICIO+"' AND '"+FIN+"' ORDER BY IDORDENP DESC");
                break;
            case "EDH":
                sql=("SELECT SUM(carrito.CANTIDAD) "
                        + "FROM ordenesp "
                        + "INNER JOIN pendientes ON ordenesp.IDPENDIENTE = pendientes.IDPENDIENTE "
                        + "INNER JOIN carrito ON pendientes.IDPENDIENTE = carrito.IDPENDIENTE "
                        + "INNER JOIN productos ON carrito.IDPRODUCTO = productos.IDPRODUCTO "
                        + "WHERE pendientes.CATEGORIA != 'INTERNET' "
                        + "AND pendientes.ESTADO = 'FINALIZADO' "
                        + "AND carrito.ESTADO = 'USADO' "
                        + "AND productos.IDPRODUCTO = 78 "
                        + "AND STR_TO_DATE(ordenesp.FFIN, '%d/%m/%Y') "
                        + "BETWEEN '"+INICIO+"' AND '"+FIN+"' ORDER BY IDORDENP DESC");
                break;
            default:
                break;
        }        
        try {
            rs= obj.consultar(sql);
            if(rs.next())
               {
                   x=rs.getInt(1);
                   return x;
               }
        } catch (Exception ex) {
            Logger.getLogger(ControlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return x;
    }
    
    public static void pegarImagen(JLabel label)
    {
                // --- LÓGICA PARA PEGAR Y GUARDAR ---
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents = clipboard.getContents(null);

            if (contents != null && contents.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                // 1. Obtener imagen del portapapeles
                Image image = (Image) contents.getTransferData(DataFlavor.imageFlavor);
                
                // 2. Mostrar en JLabel
                label.setIcon(new ImageIcon(image));
                label.setText(""); // Quitar texto

                // 3. Convertir a BufferedImage para guardar
                BufferedImage bi = new BufferedImage(
                    image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
                bi.getGraphics().drawImage(image, 0, 0, null);

                // 4. Crear archivo
                File outputfile = new File("imagen_pegada.png");
                ImageIO.write(bi, "png", outputfile);
                System.out.println("Imagen guardada en: " + outputfile.getAbsolutePath());
                
            } else {
                System.out.println("No hay imagen en el portapapeles");
            }
        } catch (HeadlessException | UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void DragAndDrop(JLabel label, String destino, String DOC)
    {
        label.setBorder(BorderFactory.createDashedBorder(Color.BLUE, 5, 5));
        final SimpleDateFormat formato1 = new SimpleDateFormat("ddMMyyyyHHmmss");
        final Date hoy = new Date();
        final String FECHA1 = formato1.format(hoy);

        // Habilitar el arrastrar y soltar
        label.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
            }

            @Override
            public boolean importData(TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }

                try {
                    Transferable transferable = support.getTransferable();
                    List<File> files = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);

                    for (File file : files) {
                        String path = file.getAbsolutePath().toLowerCase();

                        if (path.endsWith(".jpg") || path.endsWith(".jpeg") || 
                            path.endsWith(".png") || path.endsWith(".pdf") || 
                            path.endsWith(".webp") ||     
                            path.endsWith(".tiff") || path.endsWith(".tif")) {

                            System.out.println("Archivo aceptado: " + file.getAbsolutePath());             

                            File ORIGEN = file.getAbsoluteFile();  // Declarada dentro del método
                            String ARCHIVO = file.getName();
                            String EXTENSION = FilenameUtils.getExtension(ARCHIVO);
                            String NARCHIVO = FECHA1 + "_" + DOC + "." + EXTENSION;
                            String DESTINO = "\\\\" + G.ip + "\\dhn\\" + destino + "\\" + NARCHIVO;
                            File DOCUMENTO = new File(DESTINO);
                            FileUtils.copyFile(ORIGEN, DOCUMENTO);                                                           

                            label.setText(NARCHIVO);
                        } else {
                            JOptionPane.showMessageDialog(null, "Solo se permiten imágenes y archivos PDF.");
                        }
                    }

                    return true;
                } catch (HeadlessException | UnsupportedFlavorException | IOException e) {
                    e.printStackTrace(); // Mejor para depuración
                    return false;
                }
            }
        });   

    }
    
    public static ModeloPrestaciones selectP(String CONCEPTO, String ORG) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloPrestaciones mdl;        
        ControlPrestaciones ctrl = new ControlPrestaciones();
        mdl=ctrl.Registros(CONCEPTO, ORG);
        
        return mdl;
    }
    
    // Método para redondear a la DECENA más alta cercana
    public static double redondearDecena(double numero) {
        return (double) (Math.ceil(numero / 10.0) * 10);
    }
    
    // Método para redondear al MÚLTIPLO DE 5 más alto cercano
    public static double redondearAlCinco(double numero) {
        return (double) (Math.ceil(numero / 5.0) * 5);
    }
        
    
    /*public static Object selectTabla(JTable TABLA, int col)
    {
        Object valor = null;
        TABLA.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
        {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Verificar si la selección ya terminó (evita que se ejecute dos veces)
                if (!e.getValueIsAdjusting()) {

                    // Obtener la fila seleccionada
                    int filaSeleccionada = TABLA.getSelectedRow();

                    // Asegurarse de que realmente hay una fila seleccionada
                    if (filaSeleccionada != -1) {
                        // Obtener datos de la fila (ejemplo: columna 0)
                        Object valorColumna0 = TABLA.getValueAt(filaSeleccionada, col);

                        System.out.println("Fila seleccionada: " + filaSeleccionada);
                        System.out.println("Valor: " + valorColumna0);
                        
                        
                        // LÓGICA DE TU EVENTO AQUÍ
                        
                    }
                }
            }
        });
       
        return valor;        
    }
    
    // 1. Defines el método pasándole la acción que quieres que ocurra
    public static void selectFila(JTable TABLA, int col, Consumer<Object> retorno) 
    {
        TABLA.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = TABLA.getSelectedRow();
                if (filaSeleccionada != -1) {
                    Object valor = TABLA.getValueAt(filaSeleccionada, col);

                    // 2. Ejecutas la acción pasándole el valor obtenido
                    System.out.println(valor);
                    retorno.accept(valor); 
                }
            }
        });
    }
    */
    
    public static boolean navegaTablaAvanzado(JTable table, JLabel label, int columnaIndex) {
        try {
            // Validar parámetros
            if (table == null || label == null) {
                return false;
            }
            
            // Agregar listener para selección de fila
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            Object valor = table.getValueAt(selectedRow, columnaIndex);
                            label.setText(valor != null ? valor.toString() : "");
                        }
                    }
                }
            });
            
            // Agregar KeyListener para navegación avanzada
            table.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    int row = table.getSelectedRow();
                    int col = table.getSelectedColumn();
                    
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            if (row > 0) {
                                table.setRowSelectionInterval(row - 1, row - 1);
                                e.consume();
                            }
                            break;
                            
                        case KeyEvent.VK_DOWN:
                            if (row < table.getRowCount() - 1) {
                                table.setRowSelectionInterval(row + 1, row + 1);
                                e.consume();
                            }
                            break;
                            
                        case KeyEvent.VK_HOME:
                            if (table.getRowCount() > 0) {
                                table.setRowSelectionInterval(0, 0);
                                e.consume();
                            }
                            break;
                            
                        case KeyEvent.VK_END:
                            if (table.getRowCount() > 0) {
                                table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
                                e.consume();
                            }
                            break;
                            
                        case KeyEvent.VK_PAGE_UP:
                            int pageUpRow = Math.max(0, row - 10);
                            table.setRowSelectionInterval(pageUpRow, pageUpRow);
                            e.consume();
                            break;
                            
                        case KeyEvent.VK_PAGE_DOWN:
                            int pageDownRow = Math.min(table.getRowCount() - 1, row + 10);
                            table.setRowSelectionInterval(pageDownRow, pageDownRow);
                            e.consume();
                            break;
                    }
                }
            });
            
            // Agregar MouseListener para selección con clic
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = table.rowAtPoint(evt.getPoint());
                    if (row >= 0) {
                        table.setRowSelectionInterval(row, row);
                    }
                }
            });
            
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al agregar navegación por teclado: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Versión simplificada que solo agrega navegación básica por teclado
     * @param table
     * @param label
     * @param columnaIndex
     * @return 
     */
    public static boolean navegaTablaSimple(JTable table, JLabel label, int columnaIndex) {
        try {
            if (table == null || label == null) {
                return false;
            }
            
            // Selección de fila con actualización del label
            table.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Object valor = table.getValueAt(selectedRow, columnaIndex);
                        label.setText(valor != null ? valor.toString() : "");
                    }
                }
            });
            
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al agregar navegación simple: " + e.getMessage());
            return false;
        }
    }
    
    
    public static boolean NAVEGARTABLA(KeyEvent evt)
    {
        boolean x = false;
        if (evt.getKeyCode()==KeyEvent.VK_UP||evt.getKeyCode()==KeyEvent.VK_DOWN)
            x = true;
        return x;
    }
    
    // Recibimos un JFrame (o Window) en lugar de un Object genérico
    /*public static void teclas(JDialog ventana) {
        // Obtenemos el contentPane del JFrame como JComponent
        JComponent panel = (JComponent) ventana.getContentPane();

        // Como configurarTecla ahora es static, podemos llamarlo sin problema
        // Pasamos 'ventana' al lambda para poder cerrarla
        configurarTecla(panel, KeyEvent.VK_ESCAPE, 0, "CERRAR", () -> cerrar(ventana));
    }

    // Convertido a static para poder usarse desde el método teclas()
    public static void configurarTecla(JComponent panel, int keyCode, int modifiers, String accionNombre, Runnable accion) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, modifiers);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, accionNombre);
        panel.getActionMap().put(accionNombre, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accion.run();
            }
        });
    }

    // 'dispose()' pertenece a JFrame (o JDialog), no a JComponent
    public static void cerrar(JDialog ventana) {
        ventana.dispose();
    }*/
    
    
    // Método genérico que recibe cualquier componente y una acción
    public static void configurarTecla(JComponent component, int keyCode, int modifiers, 
                                     String accionNombre, Runnable accion) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, modifiers);
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, accionNombre);
        component.getActionMap().put(accionNombre, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accion.run();
            }
        });
    }
    
    // Método para cualquier objeto con dispose()
    public static void teclas(Object ventana) {
        if (ventana instanceof JDialog) {
            teclas((JDialog) ventana);
        } else if (ventana instanceof JFrame) {
            teclas((JFrame) ventana);
        } else if (ventana instanceof JInternalFrame) {
            teclas((JInternalFrame) ventana);
        } else if (ventana instanceof JPanel) {
            teclas((JPanel) ventana);
        } else {
            throw new IllegalArgumentException("Tipo de ventana no soportado");
        }
    }
    
    // Sobrecarga para JDialog
    public static void teclas(JDialog ventana) {
        accionVentana((JComponent) ventana.getContentPane(), 
            () -> ventana.dispose());
    }
    
    // Sobrecarga para JFrame
    public static void teclas(JFrame ventana) {
        accionVentana((JComponent) ventana.getContentPane(), 
            () -> ventana.dispose());
    }
    
    // Sobrecarga para JInternalFrame
    public static void teclas(JInternalFrame ventana) {
        accionVentana((JComponent) ventana.getContentPane(), 
            () -> ventana.dispose());
    }
    
    // Sobrecarga para JPanel
    public static void teclas(JPanel panel) {
        accionVentana(panel, () -> {
            Window window = SwingUtilities.getWindowAncestor(panel);
            if (window != null) {
                if (window instanceof JDialog) {
                    ((JDialog) window).dispose();
                } else if (window instanceof JFrame) {
                    ((JFrame) window).dispose();
                }
            }
        });
    }
    
    private static void accionVentana(JComponent panel, Runnable cerrar) {
        configurarTecla(panel, KeyEvent.VK_ESCAPE, 0, "CERRAR", cerrar);
    }
    
    //CREAR VARIABLE DE TIEMPO
    public static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    public static void notificaciones() throws SQLException, Exception
    {
        String sql;
        //PAGOS
        lblPXV.setText(""+total("PAGOS","pagos.REFERENCIA", "NO VERIFICADO"));
        lblPXE.setText(""+total("PAGOS","pagos.REFERENCIA", "VERIFICADO SIN ENVIAR"));
        lblPR.setText(""+total("PAGOS","pagos.REFERENCIA", "RECHAZADO"));  
        //COTIZACIONES
        lblCPE.setText(""+total("COTIZACIONES","cotizaciones.ESTADO", "PENDIENTE DE ENVIO"));        
        lblCE.setText(""+total("COTIZACIONES","cotizaciones.ESTADO", "ENVIADA"));   
        sql ="SELECT COUNT(*) FROM pendientes LEFT JOIN cotizaciones ON cotizaciones.IDPENDIENTE = pendientes.IDPENDIENTE WHERE pendientes.ESTADO = '-COTIZACION-'";
        int todos = total(sql);
        sql ="SELECT COUNT(*) FROM pendientes CROSS JOIN cotizaciones ON cotizaciones.IDPENDIENTE = pendientes.IDPENDIENTE WHERE pendientes.ESTADO = '-COTIZACION-'";
        int resultado = todos - total(sql);
        lblCP.setText(""+resultado);
        //SERVICIOS
        sql = "SELECT COUNT(*) "
                + "FROM pendientes "
                + "INNER JOIN clientes ON pendientes.IDCLIENTE = clientes.IDCLIENTE "
                + "INNER JOIN direcciones on pendientes.IDDIRECCION = direcciones.IDDIRECCION "
                + "LEFT JOIN ordenesp ON pendientes.IDPENDIENTE = ordenesp.IDPENDIENTE "
                + "INNER JOIN empleados ON empleados.IDEMPLEADO = ordenesp.IDEMPLEADO "
                + "WHERE pendientes.ESTADO = 'ABIERTO' AND pendientes.FEST ='"+G.FECHA(G.hoy(), false)+"'";
        lblSD.setText(""+total(sql));
        //REPORTE DE INTERNET
        
    }
    
    public static void ejecutor()
    {
         Runnable tarea = () -> {
             try {
                 notificaciones();
             } catch (Exception ex) {
                 Logger.getLogger(F.class.getName()).log(Level.SEVERE, null, ex);
             }
         };

        // Retardo inicial de 0 segundos, y se repite cada 100 segundos
        scheduler.scheduleAtFixedRate(tarea, 0, 100, TimeUnit.SECONDS);
    }
    
    
    private static final Map<String, String> DIAS_SEMANA = new HashMap<>();
    
    static 
    {
        DIAS_SEMANA.put("MONDAY", "LUNES");
        DIAS_SEMANA.put("TUESDAY", "MARTES");
        DIAS_SEMANA.put("WEDNESDAY", "MIERCOLES");
        DIAS_SEMANA.put("THURSDAY", "JUEVES");
        DIAS_SEMANA.put("FRIDAY", "VIERNES");
        DIAS_SEMANA.put("SATURDAY", "SABADO");
        DIAS_SEMANA.put("SUNDAY", "DOMINGO");
    }

    public static String nomdias(String dayOfWeek) 
    {
        return DIAS_SEMANA.get(dayOfWeek);
    }
}
