/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloBitacora;
import MODELO.ModeloCarrito;
import MODELO.ModeloContratosTabla;
import MODELO.ModeloKits;
import MODELO.ModeloKitsDTabla;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Funciones 
{
    private static final SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
    
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
    
    public boolean copiar(String origen, String destino){
        File archivoOrigen;
        File archivoDestino;
        FileInputStream in = null;
        FileOutputStream out = null;
        boolean b;
        try{
            archivoOrigen = new File(origen);
            archivoDestino = new File(destino);
          
            /**
             * Validamos que el archivo de origen exista. En caso de que no
             * exista saldremos del método
             */
            if(b = archivoOrigen.exists()) {
                /**
                 * Validamos que el archivo de origen se pueda leer
                 */
                if(b = archivoOrigen.canRead()) {
                    /**
                     * Creamos el lector y el escritor
                     */
                    in = new FileInputStream(archivoOrigen);
                    out = new FileOutputStream(archivoDestino);

                    /**
                     * Mientras se lee de un lado por otro lado se escribe
                     */
                    int c;
                    while( (c = in.read() ) != -1) {
                        out.write(c);
                    }
                }
            }
        } catch(IOException ex){
            ex.printStackTrace(System.out);
            b = false;
        } finally {
            try{
                if(in != null) {
                    in.close();
                }
                if(out != null) {
                    out.close();
                }
            } catch(IOException ex) {
                ex.printStackTrace(System.out);
                b = false;
            }
        }
        return b;
    }
    
    public static Integer eliminarRegistro(int ID, String TABLA, String ATRIBUTO) throws ClassNotFoundException, SQLException, Exception
    {
        Manejador obj = new Manejador();
        String sql;
        
        if(TABLA.equals("productosd"))
        {
            sql=("DELETE FROM "+TABLA+" WHERE CB = " + ATRIBUTO + " ");
        }
        else
            sql=("DELETE FROM "+TABLA+" WHERE "+ATRIBUTO+" = " + ID + " ");
        
        obj.Ejecutar(sql);
        obj.limpiarRecursos();
        return 1;
    }
    
    public static int total(String MONEDA) throws ClassNotFoundException, SQLException, Exception 
    {
        int total;
        Manejador obj = new Manejador();
        String sql;
        ResultSet rs;
        sql=("select COUNT(*) from PRODUCTOS where MONEDA='" + MONEDA + "'");
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
                System.out.println("DE SEGURO YA NO TENEMOS NADA EN ACTIVOS");
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
                System.out.println("DE SEGURO YA NO TENEMOS NADA EN ACTIVOS");
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
                System.out.println("ERROR EN EL CONTEO: "+e);
            } catch (Exception ex) {
            Logger.getLogger(ControlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 0;
    }
    
    /* UPDATE pendientes SET ESTADO = REPLACE(ESTADO, 'OK-FINALIZADO', 'FINALIZADO') WHERE ESTADO LIKE '%_%'; */
    
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
        Date hoy = new Date();
        String FECHA = formateador.format(hoy);
        
        ModeloBitacora mdl =  new ModeloBitacora();
        
        mdl.setTABLA(TABLA);
        mdl.setREGISTRO(IDREGISTRO);
        mdl.setACCION(ACCION);        
        mdl.setIDUSER(IDUSER);
        mdl.setCOMENTARIOS(COMENTARIOS);
        mdl.setREFERENCIA(REFERENCIA);
        mdl.setFECHA(FECHA);
                
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
                System.out.println("DE SEGURO YA NO TENEMOS NADA EN ACTIVOS");
            } catch (Exception ex) {
            Logger.getLogger(ControlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 0;
    }
    
    public static int llenaCarrito(String NOMBRE, String CATEGORIA, int IDPENDIENTE, int IDEMPLEADO) throws ClassNotFoundException, SQLException, Exception
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date hoy = new Date();
        String FECHA = formato.format(hoy);
        
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
            mdlC.setFECHA(FECHA);
            
            ctrlC = new ControlCarrito();
            ctrlC.guardarRegistro(mdlC);
        }
        return 1;
    }
    
    public static boolean automatizacion(String ESTADO, String IPc, int IDCONTc, String FCORc) throws SQLException, Exception
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date hoy = new Date();
        String FECHA = formato.format(hoy);
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
            fin=formato.parse(FECHA);
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
                                ctrl.actualizarEstado("SUSPENDIDO", IDCONTc, "SUSPENDIDO EL DIA: "+FECHA);
                                
                                comando = "/ip dhcp-server lease set [find address="+IPc+"] block-access=yes";
                                ejecutar(session,comando);
                                System.out.println("EJECUTA COMANDO EN MIKROTIK:"+IDCONTc);
                                
                            }

                        }
        }
        
        if(IPc == null)
        {
            for(int i=0; i<mdl.getRowCount(); i++)
            {
                //mdl.getValueAt(i, 1);
                System.out.println("---------------------------------"+mdl.getValueAt(i, 1));
                //FECHA=formateador.format(hoy);  
                FCOR = mdl.getValueAt(i, 1).toString();
                IDCONT = (int) mdl.getValueAt(i, 0);
                IP = mdl.getValueAt(i, 2).toString();
                inicio=formato.parse(FCOR);
                fin=formato.parse(FECHA);
                difm = inicio.getTime() - fin.getTime();
                dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);
                DIAS= (int) dif;

                if(!IP.equals("0"))
                {
                    if(DIAS<=-30)
                    {
                        ctrl = new ControlContratos();
                        ctrl.actualizarEstado("RETIRAR", IDCONT, "ENVIO A RETIRAR EL DIA: "+FECHA);
                        System.out.println("MANDA A RETIRAR A:"+IDCONT);
                        //if(!IP.equals("0"))
                        //{
                            //COMANDOS MIKROTIK
                            comando = "/ip dhcp-server lease set [find address="+IP+"] block-access=yes";
                            ejecutar(session,comando);
                            System.out.println("EJECUTA COMANDO EN MIKROTIK: "+IDCONT);
                        //}

                    }
                    else
                    {
                        if(DIAS<-5&&DIAS>=-30)
                        {
                            if(!ESTADO.equals("SUSPENDIDO"))
                            {
                                ctrl = new ControlContratos();
                                ctrl.actualizarEstado("SUSPENDIDO", IDCONT, "SUSPENDIDO EL DIA: "+FECHA);
                                System.out.println("MANDA A SUSPENDIDO A:"+IDCONT);
                                //if(!IP.equals("0"))
                                //{
                                    //COMANDOS MIKROTIK
                                    comando = "/ip dhcp-server lease set [find address="+IP+"] block-access=yes";
                                    ejecutar(session,comando);
                                    System.out.println("EJECUTA COMANDO EN MIKROTIK:"+IDCONT);
                                //}
                            }

                        }
                    }
                }


            }
        }
        
        
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
    
    public static int ejecutar(Session session, String comando) throws JSchException, IOException
    {     
        int x=0;
        ChannelExec ssh = (ChannelExec)session.openChannel("exec");
 
        InputStream inn = ssh.getInputStream();

        ssh.setCommand(comando);
        ssh.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inn));
        String linea = null;
        String s;

        while ((s = linea = reader.readLine()) != null) 
        {
            //System.out.println(" : " + linea);

            s = linea;
            //info();

        }
                
                
        ssh.disconnect();
        //session.disconnect();       
        return x;
    }
}
