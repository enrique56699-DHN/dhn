/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor..
 */
package CONTROLADOR;

import DHNv2.G;
import MODELO.ModeloContratos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Oscar E Gonzalez G
 */
public class ControlContratos extends ControlAbstracto
{
    private final String TABLA = "CONTRATOS";
    
    private int DIAS;
    private long dif,difm;
    private Date inicio,fin;
    
    //private SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

    public ControlContratos() throws ClassNotFoundException, SQLException
    {
        this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloContratos> contratos = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        
        ModeloContratos x;
        while(rs.next())
        {
            x = new ModeloContratos();
            x.setIDCONTRATO(rs.getInt(1));
            x.setIDCLIENTE(rs.getInt(2));
            x.setIDDIRECCION(rs.getInt(3));
            x.setIDPAQUETE(rs.getInt(4));
            x.setFREG(rs.getString(5));
            x.setFINS(rs.getString(6));
            x.setCOMENTARIOS(rs.getString(7));
            x.setESTADO(rs.getString(8));
            x.setFCOR(rs.getString(9));
            x.setEQUIPO(rs.getString(10));
            x.setIP(rs.getString(11));
            x.setCONT(rs.getBoolean(12));            
            contratos.add(x);
        }
        //System.out.println("TABLA GENERICA CONTRATOS");
        mane.limpiarRecursos();
        return contratos;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloContratos pto = (ModeloContratos)model;
            sql = "INSERT INTO "+TABLA+" (IDCONTRATO,IDCLIENTE,IDDIRECCION,IDPAQUETE,FREG,FINS,COMENTARIOS,ESTADO,FCOR,EQUIPO,IP,CONT)"
                + "VALUES (" + 
                pto.getIDCONTRATO() + "," + 
                pto.getIDCLIENTE() + "," +
                pto.getIDDIRECCION() + "," +
                pto.getIDPAQUETE() + ",'" +
                pto.getFREG() + "','" +
                pto.getFINS() + "','" +
                pto.getCOMENTARIOS() + "','" +
                pto.getESTADO() + "','" +
                pto.getFCOR() + "','" +    
                pto.getEQUIPO() + "','" +
                pto.getIP() + "'," +    
                pto.getCONT() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloContratos pto = (ModeloContratos)model;
            sql = "update "+TABLA+" set "
                + " IDCLIENTE = " + pto.getIDCLIENTE()
                + " , IDDIRECCION = " + pto.getIDDIRECCION()
                + " , IDPAQUETE = " + pto.getIDPAQUETE()
                + " , FREG = '" + pto.getFREG()
                + "' , FINS = '" + pto.getFINS()    
                + "' , COMENTARIOS = '" + pto.getCOMENTARIOS()
                + "' , ESTADO = '" + pto.getESTADO()
                + "' , FCOR = '" + pto.getFCOR()
                + "' , EQUIPO='" + pto.getEQUIPO()
                + "' , IP='" + pto.getIP()
                + "' , CONT=" + pto.getCONT()
                + " where IDCONTRATO=" + pto.getIDCONTRATO() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloContratos pto = (ModeloContratos)model;
        sql=("delet from "+TABLA+" where IDCONTRATO =" + pto.getIDCONTRATO() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloContratos getRegistro(int code) throws Exception {
        sql = "select * from "+TABLA+" where IDCONTRATO = "+code;
        rs = mane.consultar(sql);
        ModeloContratos x=new ModeloContratos();
        while(rs.next())
        {   
            x = new ModeloContratos();
            x.setIDCONTRATO(rs.getInt(1));
            x.setIDCLIENTE(rs.getInt(2));
            x.setIDDIRECCION(rs.getInt(3));
            x.setIDPAQUETE(rs.getInt(4));
            x.setFREG(rs.getString(5));
            x.setFINS(rs.getString(6));
            x.setCOMENTARIOS(rs.getString(7));
            x.setESTADO(rs.getString(8));
            x.setFCOR(rs.getString(9));
            x.setEQUIPO(rs.getString(10));
            x.setIP(rs.getString(11));
            x.setCONT(rs.getBoolean(12));
        }
        //System.out.println("TABLA GENERICA CONTRATOS X ID");
        mane.limpiarRecursos();
        return x;
    }
    
    public List getRegistros(String Estado) throws Exception {
        ArrayList<ModeloContratos> c = new ArrayList<>();
        //Otra manera de hacerlo es con 2 consultas sql, almacenar las variables de rs, despues devolver en x añadirlo y retornarlo
        sql = "select * from "+TABLA+" where ESTADO ='"+Estado+"'";
        rs = mane.consultar(sql);
        ModeloContratos x;
        while(rs.next())
        {
            x = new ModeloContratos();
            x.setIDCONTRATO(rs.getInt(1));
            x.setIDCLIENTE(rs.getInt(2));
            x.setIDDIRECCION(rs.getInt(3));
            x.setIDPAQUETE(rs.getInt(4));
            x.setFREG(rs.getString(5));
            x.setFINS(rs.getString(6));
            x.setCOMENTARIOS(rs.getString(7));
            x.setESTADO(rs.getString(8));
            x.setFCOR(rs.getString(9));
            x.setEQUIPO(rs.getString(10));
            x.setIP(rs.getString(11));
            x.setCONT(rs.getBoolean(12));

            c.add(x);
        }
        //System.out.println("TABLA GENERICA CONTRATOS CUENTA DIAS");
        mane.limpiarRecursos();
        return c;
    }
    
    public List getRegistros2(String Estado) throws Exception {
        ArrayList<ModeloContratos> c = new ArrayList<>();
        sql = "select * from "+TABLA+" where ESTADO ='"+Estado+"'";
        rs = mane.consultar(sql);
        ModeloContratos x;
        while(rs.next())
        {
            x = new ModeloContratos();
            x.setIDCONTRATO(rs.getInt(1));
            x.setIDCLIENTE(rs.getInt(2));
            x.setIDDIRECCION(rs.getInt(3));
            x.setIDPAQUETE(rs.getInt(4));
            x.setFREG(rs.getString(5));
            x.setFINS(rs.getString(6));
            x.setCOMENTARIOS(rs.getString(7));
            x.setESTADO(rs.getString(8));
            x.setFCOR(rs.getString(9));
            x.setEQUIPO(rs.getString(10));
            x.setIP(rs.getString(11));
            x.setCONT(rs.getBoolean(12));

            c.add(x);
        }
        mane.limpiarRecursos();
        return c;
    }

    public ModeloContratos getRegistrosID(int IDCONTRATO) throws Exception {
        sql = "select * from "+TABLA+" where IDCONTRATO ="+IDCONTRATO+"";
        rs = mane.consultar(sql);
        ModeloContratos x = null;
        while(rs.next())
        {
            
            x = new ModeloContratos();
            x.setIDCONTRATO(rs.getInt(1));
            x.setIDCLIENTE(rs.getInt(2));
            x.setIDDIRECCION(rs.getInt(3));
            x.setIDPAQUETE(rs.getInt(4));
            x.setFREG(rs.getString(5));
            x.setFINS(rs.getString(6));
            x.setCOMENTARIOS(rs.getString(7));
            x.setESTADO(rs.getString(8));
            x.setFCOR(rs.getString(9));
            x.setEQUIPO(rs.getString(10));
            x.setIP(rs.getString(11));
            x.setCONT(rs.getBoolean(12));
            
        }
        mane.limpiarRecursos();
        return x;
    }
    
    public ModeloContratos getRegistrosIDINNER(int IDCONTRATO) throws Exception {
        sql = "SELECT contratos.IDCONTRATO, clientes.NOMBRE, clientes.TEL1, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, direcciones.LOCALIDAD, direcciones.LATITUD, direcciones.LONGITUD, paquetes.COSTO, contratos.FINS, contratos.FCOR, contratos.IP, contratos.CONT, contratos.COMENTARIOS, contratos.EQUIPO, contratos.IDPAQUETE, contratos.IDCLIENTE, contratos.IDDIRECCION "
                + "FROM contratos "
                + "INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE "
                + "INNER JOIN direcciones on contratos.IDDIRECCION = direcciones.IDDIRECCION "
                + "INNER JOIN paquetes on contratos.IDPAQUETE = paquetes.IDPAQUETE "
                + "WHERE contratos.IDCONTRATO ="+IDCONTRATO+"";
        rs = mane.consultar(sql);
        ModeloContratos x = null;
        while(rs.next())
        {
            x = new ModeloContratos();
            x.setIDCONTRATO(rs.getInt(1));
            x.setNOMBRE(rs.getString(2));
            x.setTEL1(rs.getString(3));
            x.setCALLE(rs.getString(4));
            x.setNUMERO(rs.getString(5));
            x.setCOLONIA(rs.getString(6));
            x.setLOCALIDAD(rs.getString(7));
            x.setLATITUD(rs.getString(8));
            x.setLONGITUD(rs.getString(9));
            x.setCOSTO(rs.getInt(10));
            x.setFINS(rs.getString(11));
            x.setFCOR(rs.getString(12));
            x.setIP(rs.getString(13));
            x.setCONT(rs.getBoolean(14));
            x.setCOMENTARIOS(rs.getString(15));
            x.setEQUIPO(rs.getString(16));
            x.setIDPAQUETE(rs.getInt(17));
            x.setIDCLIENTE(rs.getInt(18));
            x.setIDCONTRATO(rs.getInt(19));
        }
        mane.limpiarRecursos();
        return x;
    }
    
    public void actualizarCorte(String dia, int ID) throws Exception
    {

        sql = "update "+TABLA+" set "
                + "FCOR='" + dia
                + "' where IDCONTRATO=" + ID + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
        //System.out.println("EXITO O NO???");
    }
    
    public void actualizarINSTALACION(boolean CONT, int IDCONTRATO) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "CONT=" + CONT
                + " where IDCONTRATO=" + IDCONTRATO + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
        
    public void actualizarEQUIPO(int IDCONTRATO, String EQUIPO, String IP) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "EQUIPO = '" + EQUIPO
                + "', IP = '" + IP
                + "' where IDCONTRATO = " + IDCONTRATO + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public void actualizarEstado (String ESTADO, int ID, String COMENTARIOS) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "ESTADO='" + ESTADO
                + "', COMENTARIOS='" + COMENTARIOS
                + "' where IDCONTRATO=" + ID + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public void actualizarEstado (String ESTADO, int IDCONTRATO) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "ESTADO='" + ESTADO
                + "' where IDCONTRATO = " + IDCONTRATO + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();        
    }
    
    public void activarContrato(int IDCONTRATO, String FINS, String FCOR, String ESTADO) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "FINS = '" + FINS
                + "', FCOR = '" + FCOR
                + "', ESTADO = '" + ESTADO
                + "' where IDCONTRATO =" + IDCONTRATO + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public int costo(int IDCONTRATO) throws Exception
    {
        int costo = 0;
        sql = "SELECT paquetes.COSTO FROM "+TABLA+" "
                + "INNER JOIN paquetes on contratos.IDPAQUETE = paquetes.IDPAQUETE "
                + "WHERE contratos.IDCONTRATO = " + IDCONTRATO + " ";        
        rs = mane.consultar(sql);
        while(rs.next())
        {
            costo = rs.getInt(1);
        }
        mane.limpiarRecursos();
        return costo;
        
    }
    
    public static List getRegistrosID(String ESTADO) throws Exception {
        ArrayList<Integer> cont = new ArrayList<>();
        Manejador obj = new Manejador();
        String sql2 = "select IDCONTRATO from CONTRATOS where ESTADO='" + ESTADO + "'";
        ResultSet rs =obj.consultar(sql2);
        while(rs.next())
        {

            cont.add(rs.getInt(1));
        }

        obj.limpiarRecursos();
        return cont;
    }
    
    public List getRegistrosINNER(String Estado) throws Exception {
        
        ArrayList<ModeloContratos> c = new ArrayList<>();
        //Otra manera de hacerlo es con 2 consultas sql, almacenar las variables de rs, despues devolver en x añadirlo y retornarlo
        sql = "SELECT contratos.IDCONTRATO, clientes.NOMBRE, clientes.TEL1, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, direcciones.LOCALIDAD, direcciones.LATITUD, direcciones.LONGITUD, paquetes.COSTO, contratos.FINS, contratos.FCOR, contratos.IP, contratos.CONT, contratos.COMENTARIOS, contratos.EQUIPO, paquetes.DESCRIPCION FROM "+TABLA+
                " INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE INNER JOIN direcciones on contratos.IDDIRECCION = direcciones.IDDIRECCION INNER JOIN paquetes on contratos.IDPAQUETE = paquetes.IDPAQUETE WHERE contratos.ESTADO ='"+Estado+"'";
        rs = mane.consultar(sql);
        ModeloContratos x;
        while(rs.next())
        {
            x = new ModeloContratos();
            x.setIDCONTRATO(rs.getInt(1));
            x.setNOMBRE(rs.getString(2));
            x.setTEL1(rs.getString(3));
            x.setCALLE(rs.getString(4));
            x.setNUMERO(rs.getString(5));
            x.setCOLONIA(rs.getString(6));
            x.setLOCALIDAD(rs.getString(7));
            x.setLATITUD(rs.getString(8));
            x.setLONGITUD(rs.getString(9));
            x.setCOSTO(rs.getInt(10));
            x.setFINS(rs.getString(11));
            x.setFCOR(rs.getString(12));
            x.setIP(rs.getString(13));
            x.setCONT(rs.getBoolean(14));
            x.setCOMENTARIOS(rs.getString(15));
            x.setEQUIPO(rs.getString(16));
            x.setDESCIPCION(rs.getString(17));

            if ("CANCELADO".equals(Estado))
            {
                DIAS=0;
            }
            else
            {
                //fecha=formateador.format(hoy);  
                inicio=G.formato.parse(x.getFCOR());
                //fin=formateador.parse(fecha);
                //fin=Global.hoy;
                difm = inicio.getTime() - G.hoy().getTime();
                dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);
                DIAS= (int) dif;
            }           
            
            x.setDIAS(DIAS);
            
            //Aal final gregamos todos los resultados al modelo
            c.add(x);
        }
        //System.out.println("TABLA GENERICA CONTRATOS CUENTA DIAS-CON INNER JOIN");
        mane.limpiarRecursos();
        return c;
    }
    
    public ModeloContratos getRegistrosPAGO(int IDCONTRATO) throws Exception {        
        sql = "SELECT contratos.IDCONTRATO, clientes.NOMBRE, paquetes.COSTO, contratos.FCOR FROM "+TABLA+" INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE INNER JOIN paquetes on contratos.IDPAQUETE = paquetes.IDPAQUETE WHERE contratos.IDCONTRATO = "+IDCONTRATO+" ";
        rs = mane.consultar(sql);
        ModeloContratos x = null;
        while(rs.next())
        {
            x = new ModeloContratos();
            x.setIDCONTRATO(rs.getInt(1));
            x.setNOMBRE(rs.getString(2));
            x.setCOSTO(rs.getInt(3));
            x.setFCOR(rs.getString(4));            
            
                //fecha=formateador.format(hoy);  
                inicio=G.formato.parse(x.getFCOR());
                //fin=formateador.parse(fecha);
                difm = inicio.getTime() - G.hoy().getTime();
                dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);
                DIAS= (int) dif;
            
            x.setDIAS(DIAS);

        }
        //System.out.println("TABLA GENERICA CONTRATOS CUENTA DIAS-CON INNER JOIN");
        mane.limpiarRecursos();
        return x;
    }
    
    public ModeloContratos DIRECCION(int IDCONTRATO) throws Exception
    {
        sql = "SELECT direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, direcciones.LOCALIDAD, direcciones.LATITUD, direcciones.LONGITUD FROM direcciones INNER JOIN contratos on direcciones.IDDIRECCION = contratos.IDDIRECCION WHERE contratos.IDCONTRATO = "+IDCONTRATO+" ";
        rs = mane.consultar(sql);
        ModeloContratos x = null;
        while(rs.next())
        {
            x = new ModeloContratos();
            x.setCALLE(rs.getString(1));
            x.setNUMERO(rs.getString(2));
            x.setCOLONIA(rs.getString(3));
            x.setLOCALIDAD(rs.getString(4));
            x.setLATITUD(rs.getString(5));
            x.setLONGITUD(rs.getString(6));

        }
        
        mane.limpiarRecursos();
        return x;
    }
    
    public List getRegistrosIP(int IP, String ESTADO) throws Exception {
        ArrayList<ModeloContratos> contratos = new ArrayList<>();
        sql = "SELECT contratos.IDCONTRATO, clientes.NOMBRE, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, direcciones.LOCALIDAD, direcciones.LATITUD, direcciones.LONGITUD, contratos.EQUIPO, contratos.IP FROM "+TABLA+" INNER JOIN clientes ON clientes.IDCLIENTE = contratos.IDCLIENTE INNER JOIN direcciones ON direcciones.IDDIRECCION = contratos.IDDIRECCION WHERE contratos.IP = "+IP+" AND contratos.ESTADO = '"+ESTADO+"' ";
        rs = mane.consultar(sql);
        
        ModeloContratos x;
        while(rs.next())
        {
            x = new ModeloContratos();
            x.setIDCONTRATO(rs.getInt(1));
            x.setNOMBRE(rs.getString(2));
            x.setCALLE(rs.getString(3));
            x.setNUMERO(rs.getString(4));
            x.setCOLONIA(rs.getString(5));
            x.setLOCALIDAD(rs.getString(6));
            x.setLATITUD(rs.getString(7));
            x.setLONGITUD(rs.getString(8));
            x.setEQUIPO(rs.getString(9));
            x.setIP(rs.getString(10));  
            
            contratos.add(x);
        }
        mane.limpiarRecursos();
        return contratos;
    }
    
    public List getRegistrosAuto(String ESTADO) throws Exception {
        ArrayList<ModeloContratos> contratos = new ArrayList<>();
        sql = "SELECT contratos.IDCONTRATO, contratos.FCOR, contratos.IP FROM "+TABLA+" WHERE contratos.ESTADO = '"+ESTADO+"' ";
        rs = mane.consultar(sql);
        
        ModeloContratos x;
        while(rs.next())
        {
            x = new ModeloContratos();
            x.setIDCONTRATO(rs.getInt(1));            
            x.setFCOR(rs.getString(2));  
            x.setIP(rs.getString(3));
            
            contratos.add(x);
        }
        mane.limpiarRecursos();
        return contratos;
    }        
    
    public String obtenerEstado(int IDCONTRATO) throws Exception
    {
        String ESTADO = null;
        sql = "SELECT contratos.ESTADO FROM "+TABLA+" WHERE contratos.IDCONTRATO = " + IDCONTRATO + " ";
       
        rs = mane.consultar(sql);
        while(rs.next())
        {
            ESTADO = rs.getString(1);
        }
        mane.limpiarRecursos();
        return ESTADO;        
    }
}
