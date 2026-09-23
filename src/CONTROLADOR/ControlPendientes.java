/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import DHNv2.G;
import MODELO.ModeloPendientes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlPendientes extends ControlAbstracto
{
    private final String TABLA = "PENDIENTES";
    
    private int DIAS;
    private long dif,difm;
    private Date fin;
    
    public ControlPendientes() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloPendientes> lista = new ArrayList<>();
        sql = "select * from "+TABLA+"";
        rs = mane.consultar(sql);
        ModeloPendientes mdl;
        while(rs.next())
        {
            mdl = new ModeloPendientes();
            mdl.setIDPENDIENTE(rs.getInt(1));
            mdl.setIDCLIENTE(rs.getInt(2));
            mdl.setIDDIRECCION(rs.getInt(3));
            mdl.setNPEDIDO(rs.getString(4));
            mdl.setNPEDIDOF(rs.getString(5));
            mdl.setCATEGORIA(rs.getString(6));
            mdl.setSUBCATEGORIA(rs.getString(7));
            mdl.setDESCRIPCION(rs.getString(8));
            mdl.setCOMENTARIOS(rs.getString(9));
            mdl.setFREG(rs.getString(10));
            mdl.setFEST(rs.getString(11));
            mdl.setFFIN(rs.getString(12));            
            mdl.setESTADO(rs.getString(13));

            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloPendientes mdl = (ModeloPendientes)model;
            sql = "INSERT INTO "+TABLA+" (IDPENDIENTE,IDCLIENTE,IDDIRECCION,NPEDIDO,NPEDIDOF,CATEGORIA,SUBCATEGORIA,DESCRIPCION,COMENTARIOS,FREG,FEST,FFIN,ESTADO)"
                + "VALUES (" + 
                mdl.getIDPENDIENTE() + "," + 
                mdl.getIDCLIENTE() + "," +
                mdl.getIDDIRECCION() + ",'" +
                mdl.getNPEDIDO() + "','" +
                mdl.getNPEDIDOF() + "','" +    
                mdl.getCATEGORIA() + "','" +
                mdl.getSUBCATEGORIA() + "','" +
                mdl.getDESCRIPCION() + "','" +
                mdl.getCOMENTARIOS() + "','" +    
                mdl.getFREG() + "','" +
                mdl.getFEST() + "','" +
                mdl.getFFIN() + "','" +
                mdl.getESTADO() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloPendientes mdl = (ModeloPendientes)model;
            sql = "update "+TABLA+" set "
                + "IDCLIENTE=" + mdl.getIDCLIENTE()
                + ",IDDIRECCION=" + mdl.getIDDIRECCION()
                + ",NPEDIDO='" + mdl.getNPEDIDO()
                + "',NPEDIDOF='" + mdl.getNPEDIDOF()    
                + "',CATEGORIA='" + mdl.getCATEGORIA()
                + "',SUBCATEGORIA='" + mdl.getSUBCATEGORIA()
                + "',DESCRIPCION='" + mdl.getDESCRIPCION()
                + "',COMENTARIOS='" + mdl.getCOMENTARIOS()    
                + "',FREG='" + mdl.getFREG()
                + "',FEST='" + mdl.getFEST()    
                + "',FFIN='" + mdl.getFFIN()
                + "',ESTADO='" + mdl.getESTADO()
                + "' where IDPENDIENTE=" + mdl.getIDPENDIENTE() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloPendientes mdl = (ModeloPendientes)model;
        sql=("delet from "+TABLA+" where IDPENDIENTE =" + mdl.getIDPENDIENTE() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloPendientes getRegistro(Integer code) throws Exception {
        sql = "select * from "+TABLA+" where IDPENDIENTE = "+code;
        rs = mane.consultar(sql);
        ModeloPendientes mdl = new ModeloPendientes();
        while(rs.next())
        {   
            mdl = new ModeloPendientes();
            mdl.setIDPENDIENTE(rs.getInt(1));
            mdl.setIDCLIENTE(rs.getInt(2));
            mdl.setIDDIRECCION(rs.getInt(3));
            mdl.setNPEDIDO(rs.getString(4));
            mdl.setNPEDIDOF(rs.getString(5));
            mdl.setCATEGORIA(rs.getString(6));
            mdl.setSUBCATEGORIA(rs.getString(7));
            mdl.setDESCRIPCION(rs.getString(8));
            mdl.setCOMENTARIOS(rs.getString(9));
            mdl.setFREG(rs.getString(10));
            mdl.setFEST(rs.getString(11));
            mdl.setFFIN(rs.getString(12));            
            mdl.setESTADO(rs.getString(13));
        }
        mane.limpiarRecursos();
       return mdl;
    }
    
    public List getRegistros1(String CATEGORIA, String ESTADO) throws Exception {
        ArrayList<ModeloPendientes> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" where CATEGORIA='"+CATEGORIA+"' and ESTADO='"+ESTADO+"'";
        
        rs = mane.consultar(sql);
        ModeloPendientes mdl;
        while(rs.next())
        {
            mdl = new ModeloPendientes();
            mdl.setIDPENDIENTE(rs.getInt(1));
            mdl.setIDCLIENTE(rs.getInt(2));
            mdl.setIDDIRECCION(rs.getInt(3));
            mdl.setNPEDIDO(rs.getString(4));
            mdl.setNPEDIDOF(rs.getString(5));
            mdl.setCATEGORIA(rs.getString(6));
            mdl.setSUBCATEGORIA(rs.getString(7));
            mdl.setDESCRIPCION(rs.getString(8));
            mdl.setCOMENTARIOS(rs.getString(9));
            mdl.setFREG(rs.getString(10));
            mdl.setFEST(rs.getString(11));
            mdl.setFFIN(rs.getString(12));            
            mdl.setESTADO(rs.getString(13));
           
            //fecha=formateador.format(hoy);

            fin=G.formato.parse(mdl.getFREG());
            //inicio=formateador.parse(fecha);

            difm = G.hoy().getTime() - fin.getTime();

            dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);

            DIAS= (int) dif;

            mdl.setDIAS(DIAS);
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public List getRegistros1(String ESTADO) throws Exception {
        ArrayList<ModeloPendientes> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" where ESTADO='"+ESTADO+"'";
        
        rs = mane.consultar(sql);
        ModeloPendientes mdl;
        while(rs.next())
        {
            mdl = new ModeloPendientes();
            mdl.setIDPENDIENTE(rs.getInt(1));
            mdl.setIDCLIENTE(rs.getInt(2));
            mdl.setIDDIRECCION(rs.getInt(3));
            mdl.setNPEDIDO(rs.getString(4));
            mdl.setNPEDIDOF(rs.getString(5));
            mdl.setCATEGORIA(rs.getString(6));
            mdl.setSUBCATEGORIA(rs.getString(7));
            mdl.setDESCRIPCION(rs.getString(8));
            mdl.setCOMENTARIOS(rs.getString(9));
            mdl.setFREG(rs.getString(10));
            mdl.setFEST(rs.getString(11));
            mdl.setFFIN(rs.getString(12));            
            mdl.setESTADO(rs.getString(13));
            //fecha=formateador.format(hoy);

            fin=G.formato.parse(mdl.getFREG());
            //inicio=formateador.parse(fecha);

            difm = G.hoy().getTime() - fin.getTime();

            dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);

            DIAS= (int) dif;

            mdl.setDIAS(DIAS);
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }

    public ModeloPendientes getRegistrosID(int ID) throws Exception {
        sql = "select * from "+TABLA+" where IDPENDIENTE ="+ID+"";
        rs = mane.consultar(sql);
        ModeloPendientes mdl = null;
        while(rs.next())
        {
            mdl = new ModeloPendientes();
            mdl.setIDPENDIENTE(rs.getInt(1));
            mdl.setIDCLIENTE(rs.getInt(2));
            mdl.setIDDIRECCION(rs.getInt(3));
            mdl.setNPEDIDO(rs.getString(4));
            mdl.setNPEDIDOF(rs.getString(5));
            mdl.setCATEGORIA(rs.getString(6));
            mdl.setSUBCATEGORIA(rs.getString(7));
            mdl.setDESCRIPCION(rs.getString(8));
            mdl.setCOMENTARIOS(rs.getString(9));
            mdl.setFREG(rs.getString(10));
            mdl.setFEST(rs.getString(11));
            mdl.setFFIN(rs.getString(12));            
            mdl.setESTADO(rs.getString(13));
            
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public void actualizarEstado(String ESTADO, int ID) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "ESTADO='" + ESTADO
                + "' where IDPENDIENTE=" + ID + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public void actualizarTF(String TF, int IDPENDIENTE) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "NPEDIDOF='" + TF
                + "' where IDPENDIENTE=" + IDPENDIENTE + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public void actualizarFFIN(String FFIN, int IDPENDIENTE) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "FFIN='" + FFIN
                + "' where IDPENDIENTE=" + IDPENDIENTE + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public List getRegistrosINNER(String CATEGORIA, String ESTADO) throws Exception {
        ArrayList<ModeloPendientes> lista = new ArrayList<>();
        sql = "SELECT pendientes.IDPENDIENTE, clientes.NOMBRE, clientes.TEL1, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, direcciones.LOCALIDAD, direcciones.LATITUD, direcciones.LONGITUD, pendientes.CATEGORIA, pendientes.SUBCATEGORIA, pendientes.DESCRIPCION, pendientes.COMENTARIOS, pendientes.FREG, pendientes.FEST, pendientes.FFIN, pendientes.NPEDIDO, pendientes.NPEDIDOF FROM pendientes INNER JOIN clientes on pendientes.IDCLIENTE = clientes.IDCLIENTE INNER JOIN direcciones on pendientes.IDDIRECCION = direcciones.IDDIRECCION WHERE pendientes.CATEGORIA='"+CATEGORIA+"' and pendientes.ESTADO='"+ESTADO+"'";
        
        rs = mane.consultar(sql);
        ModeloPendientes mdl;
        while(rs.next())
        {
            mdl = new ModeloPendientes();
            mdl.setIDPENDIENTE(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setTEL1(rs.getString(3));
            mdl.setCALLE(rs.getString(4));
            mdl.setNUMERO(rs.getString(5));
            mdl.setCOLONIA(rs.getString(6));
            mdl.setLOCALIDAD(rs.getString(7));
            mdl.setLATITUD(rs.getString(8));
            mdl.setLONGITUD(rs.getString(9));
            mdl.setCATEGORIA(rs.getString(10));
            mdl.setSUBCATEGORIA(rs.getString(11));
            mdl.setDESCRIPCION(rs.getString(12));
            mdl.setCOMENTARIOS(rs.getString(13));
            mdl.setFREG(rs.getString(14));
            mdl.setFEST(rs.getString(15));
            mdl.setFFIN(rs.getString(16));
            mdl.setNPEDIDO(rs.getString(17));
            mdl.setNPEDIDOF(rs.getString(18));            
            
            if ("CANCELADO".equals(ESTADO))
            {
                DIAS=0;
            }
            else
            {
                //fecha=formateador.format(hoy);

                fin=G.formato.parse(mdl.getFREG());
                //inicio=formateador.parse(fecha);

                difm = G.hoy().getTime() - fin.getTime();

                dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);

                DIAS= (int) dif;
            }
            mdl.setDIAS(DIAS);
            
            //Aal final gregamos todos los resultados al modelo
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public List getRegistrosINNER(String ESTADO) throws Exception {
        ArrayList<ModeloPendientes> lista = new ArrayList<>();
        sql = "SELECT pendientes.IDPENDIENTE, clientes.NOMBRE, clientes.TEL1, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, direcciones.LOCALIDAD, direcciones.LATITUD, direcciones.LONGITUD, pendientes.CATEGORIA, pendientes.SUBCATEGORIA, pendientes.DESCRIPCION, pendientes.COMENTARIOS, pendientes.FREG, pendientes.FEST, pendientes.FFIN, pendientes.NPEDIDO, pendientes.NPEDIDOF FROM pendientes INNER JOIN clientes on pendientes.IDCLIENTE = clientes.IDCLIENTE INNER JOIN direcciones on pendientes.IDDIRECCION = direcciones.IDDIRECCION WHERE pendientes.ESTADO='"+ESTADO+"'";
        
        rs = mane.consultar(sql);
        ModeloPendientes mdl;
        while(rs.next())
        {
            mdl = new ModeloPendientes();
            mdl.setIDPENDIENTE(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setTEL1(rs.getString(3));
            mdl.setCALLE(rs.getString(4));
            mdl.setNUMERO(rs.getString(5));
            mdl.setCOLONIA(rs.getString(6));
            mdl.setLOCALIDAD(rs.getString(7));
            mdl.setLATITUD(rs.getString(8));
            mdl.setLONGITUD(rs.getString(9));
            mdl.setCATEGORIA(rs.getString(10));
            mdl.setSUBCATEGORIA(rs.getString(11));
            mdl.setDESCRIPCION(rs.getString(12));
            mdl.setCOMENTARIOS(rs.getString(13));
            mdl.setFREG(rs.getString(14));
            mdl.setFEST(rs.getString(15));
            mdl.setFFIN(rs.getString(16));
            mdl.setNPEDIDO(rs.getString(17));
            mdl.setNPEDIDOF(rs.getString(18));
            
                        

            if ("CANCELADO".equals(ESTADO)||"OK-FINALIZADO".equals(ESTADO))
            {
                DIAS=0;
            }
            else
            {
                //fecha=formateador.format(hoy);

                fin=G.formato.parse(mdl.getFREG());
                //inicio=formateador.parse(fecha);

                difm = G.hoy().getTime() - fin.getTime();

                dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);

                DIAS= (int) dif;
            }
            
            

            mdl.setDIAS(DIAS);
            
            //Aal final gregamos todos los resultados al modelo
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public void terminado(int IDPENDIENTE, String FFIN, String TICKET, String TICKETFAC, String ESTADO, String COMENTARIOS) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "FFIN='" + FFIN
                + "', NPEDIDO ='" + TICKET
                + "', NPEDIDOF ='" + TICKETFAC
                + "', ESTADO ='" + ESTADO
                + "', COMENTARIOS ='" + COMENTARIOS
                + "' where IDPENDIENTE=" + IDPENDIENTE + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloPendientes getRegistroID(int IDPENDIENTE) throws Exception {
        sql = "SELECT pendientes.IDPENDIENTE, clientes.NOMBRE, clientes.TEL1, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, direcciones.LOCALIDAD, direcciones.LATITUD, direcciones.LONGITUD, pendientes.CATEGORIA, pendientes.SUBCATEGORIA, pendientes.DESCRIPCION, pendientes.COMENTARIOS, pendientes.FREG, pendientes.FEST, pendientes.FFIN, pendientes.NPEDIDO, pendientes.NPEDIDOF, pendientes.ESTADO FROM pendientes INNER JOIN clientes on pendientes.IDCLIENTE = clientes.IDCLIENTE INNER JOIN direcciones on pendientes.IDDIRECCION = direcciones.IDDIRECCION WHERE pendientes.IDPENDIENTE="+IDPENDIENTE+" ";
        rs = mane.consultar(sql);
        ModeloPendientes x=null;
        while(rs.next())
        {   
            x = new ModeloPendientes();
            x.setIDPENDIENTE(rs.getInt(1));
            x.setNOMBRE(rs.getString(2));
            x.setTEL1(rs.getString(3));
            x.setCALLE(rs.getString(4));
            x.setNUMERO(rs.getString(5));
            x.setCOLONIA(rs.getString(6));
            x.setLOCALIDAD(rs.getString(7));
            x.setLATITUD(rs.getString(8));
            x.setLONGITUD(rs.getString(9));
            x.setCATEGORIA(rs.getString(10));
            x.setSUBCATEGORIA(rs.getString(11));
            x.setDESCRIPCION(rs.getString(12));
            x.setCOMENTARIOS(rs.getString(13));
            x.setFREG(rs.getString(14));
            x.setFEST(rs.getString(15));
            x.setFFIN(rs.getString(16));
            x.setNPEDIDO(rs.getString(17));
            x.setNPEDIDOF(rs.getString(18));
            x.setESTADO(rs.getString(19));

        }
        mane.limpiarRecursos();
       return x;
    }
    
    public ModeloPendientes getRegistroIDO(int IDORDENP) throws Exception {
        sql = "SELECT pendientes.IDPENDIENTE, clientes.NOMBRE, clientes.TEL1, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, direcciones.LOCALIDAD, direcciones.LATITUD, direcciones.LONGITUD, pendientes.CATEGORIA, pendientes.SUBCATEGORIA, pendientes.DESCRIPCION, pendientes.COMENTARIOS, pendientes.FREG, pendientes.FEST, pendientes.FFIN, pendientes.NPEDIDO, pendientes.NPEDIDOF, pendientes.ESTADO FROM pendientes INNER JOIN clientes on pendientes.IDCLIENTE = clientes.IDCLIENTE INNER JOIN direcciones on pendientes.IDDIRECCION = direcciones.IDDIRECCION INNER JOIN ordenesp ON pendientes.IDPENDIENTE = ordenesp.IDPENDIENTE WHERE ordenesp.IDORDENP ="+IDORDENP+" ";
        rs = mane.consultar(sql);
        ModeloPendientes x=null;
        while(rs.next())
        {   
            x = new ModeloPendientes();
            x.setIDPENDIENTE(rs.getInt(1));
            x.setNOMBRE(rs.getString(2));
            x.setTEL1(rs.getString(3));
            x.setCALLE(rs.getString(4));
            x.setNUMERO(rs.getString(5));
            x.setCOLONIA(rs.getString(6));
            x.setLOCALIDAD(rs.getString(7));
            x.setLATITUD(rs.getString(8));
            x.setLONGITUD(rs.getString(9));
            x.setCATEGORIA(rs.getString(10));
            x.setSUBCATEGORIA(rs.getString(11));
            x.setDESCRIPCION(rs.getString(12));
            x.setCOMENTARIOS(rs.getString(13));
            x.setFREG(rs.getString(14));
            x.setFEST(rs.getString(15));
            x.setFFIN(rs.getString(16));
            x.setNPEDIDO(rs.getString(17));
            x.setNPEDIDOF(rs.getString(18));
            x.setESTADO(rs.getString(19));

        }
        mane.limpiarRecursos();
       return x;
    }
    
    public void actualizar(Object model) throws Exception {
        ModeloPendientes mdl = (ModeloPendientes)model;
            sql = "update "+TABLA+" set "
                + "NPEDIDO='" + mdl.getNPEDIDO()
                + "',NPEDIDOF='" + mdl.getNPEDIDOF()    
                + "',DESCRIPCION='" + mdl.getDESCRIPCION()
                + "',COMENTARIOS='" + mdl.getCOMENTARIOS()    
                + "' where IDPENDIENTE=" + mdl.getIDPENDIENTE() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public List getRegistrosINNER2(String ESTADO) throws Exception {
        ArrayList<ModeloPendientes> lista = new ArrayList<>();
        sql = "SELECT pendientes.IDPENDIENTE, clientes.NOMBRE, clientes.TEL1, pendientes.CATEGORIA, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, direcciones.LOCALIDAD, direcciones.LATITUD, direcciones.LONGITUD, pendientes.DESCRIPCION, pendientes.COMENTARIOS, pendientes.FREG FROM pendientes INNER JOIN clientes on pendientes.IDCLIENTE = clientes.IDCLIENTE INNER JOIN direcciones on pendientes.IDDIRECCION = direcciones.IDDIRECCION WHERE pendientes.ESTADO='"+ESTADO+"'";
        
        rs = mane.consultar(sql);
        ModeloPendientes mdl;
        while(rs.next())
        {
            mdl = new ModeloPendientes();
            mdl.setIDPENDIENTE(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setTEL1(rs.getString(3));
            mdl.setCATEGORIA(rs.getString(4));
            mdl.setCALLE(rs.getString(5));
            mdl.setNUMERO(rs.getString(6));
            mdl.setCOLONIA(rs.getString(7));
            mdl.setLOCALIDAD(rs.getString(8));
            mdl.setLATITUD(rs.getString(9));
            mdl.setLONGITUD(rs.getString(10));

            mdl.setDESCRIPCION(rs.getString(11));
            mdl.setCOMENTARIOS(rs.getString(12));
            mdl.setFREG(rs.getString(13));

            if ("CANCELADO".equals(ESTADO)||"OK-FINALIZADO".equals(ESTADO))
            {
                DIAS=0;
            }
            else
            {
                //fecha=formateador.format(hoy);

                fin=G.formato.parse(mdl.getFREG());
                //inicio=formateador.parse(fecha);

                difm = G.hoy().getTime() - fin.getTime();

                dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);

                DIAS= (int) dif;
            }
            
            mdl.setDIAS(DIAS);
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public List getRegistrosProg(String FESTIMADA, String ESTADO) throws Exception {
        ArrayList<ModeloPendientes> lista = new ArrayList<>();
        sql = "SELECT pendientes.IDPENDIENTE, pendientes.DESCRIPCION, ordenesp.FINI FROM pendientes LEFT JOIN ordenesp ON pendientes.IDPENDIENTE = ordenesp.IDPENDIENTE WHERE pendientes.ESTADO = '"+ESTADO+"' AND pendientes.FEST ='"+FESTIMADA+"' ";
        rs = mane.consultar(sql);
        ModeloPendientes mdl;
        while(rs.next())
        {
            mdl = new ModeloPendientes();
            mdl.setIDPENDIENTE(rs.getInt(1));
            mdl.setDESCRIPCION(rs.getString(2));
            mdl.setFINIORDEN(rs.getString(3));
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public void actualizarFEST(String FEST, int IDPENDIENTE) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "FEST = '" + FEST
                + "' where IDPENDIENTE =" + IDPENDIENTE + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public List getRegistrosINNER3(String FESTIMADA, String ESTADO) throws Exception {
        ArrayList<ModeloPendientes> lista = new ArrayList<>();
        sql = "SELECT pendientes.IDPENDIENTE, clientes.NOMBRE, clientes.TEL1, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, direcciones.LOCALIDAD, direcciones.LATITUD, direcciones.LONGITUD, pendientes.DESCRIPCION, empleados.NOMBRE, ordenesp.FINI "
                + "FROM pendientes "
                + "INNER JOIN clientes ON pendientes.IDCLIENTE = clientes.IDCLIENTE "
                + "INNER JOIN direcciones on pendientes.IDDIRECCION = direcciones.IDDIRECCION "
                + "LEFT JOIN ordenesp ON pendientes.IDPENDIENTE = ordenesp.IDPENDIENTE "
                + "LEFT JOIN empleados ON empleados.IDEMPLEADO = ordenesp.IDEMPLEADO "
                + "WHERE pendientes.ESTADO = '"+ESTADO+"' AND pendientes.FEST ='"+FESTIMADA+"'";
        
        rs = mane.consultar(sql);
        ModeloPendientes mdl;
        while(rs.next())
        {
            mdl = new ModeloPendientes();
            mdl.setIDPENDIENTE(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setTEL1(rs.getString(3));
            mdl.setCALLE(rs.getString(4));
            mdl.setNUMERO(rs.getString(5));
            mdl.setCOLONIA(rs.getString(6));
            mdl.setLOCALIDAD(rs.getString(7));
            mdl.setLATITUD(rs.getString(8));
            mdl.setLONGITUD(rs.getString(9));
            mdl.setDESCRIPCION(rs.getString(10));
            mdl.setEMPLEADO(rs.getString(11));
            //mdl.setAPELLIDOS(rs.getString(12));
            mdl.setFEST(rs.getString(12));
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public List getRegistros(String CATEGORIA, String ESTADO) throws Exception {
        ArrayList<ModeloPendientes> lista = new ArrayList<>();
        if(CATEGORIA.equals("INTERNET"))
            sql = "SELECT pendientes.IDPENDIENTE, clientes.NOMBRE, clientes.TEL1, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, direcciones.LOCALIDAD, direcciones.LATITUD, direcciones.LONGITUD, pendientes.DESCRIPCION, pendientes.COMENTARIOS, pendientes.FREG, pendientes.CATEGORIA FROM pendientes INNER JOIN clientes on pendientes.IDCLIENTE = clientes.IDCLIENTE INNER JOIN direcciones on pendientes.IDDIRECCION = direcciones.IDDIRECCION WHERE pendientes.ESTADO='"+ESTADO+"' AND pendientes.CATEGORIA = '"+CATEGORIA+"'";
        else
            sql = "SELECT pendientes.IDPENDIENTE, clientes.NOMBRE, clientes.TEL1, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, direcciones.LOCALIDAD, direcciones.LATITUD, direcciones.LONGITUD, pendientes.DESCRIPCION, pendientes.COMENTARIOS, pendientes.FREG, pendientes.CATEGORIA FROM pendientes INNER JOIN clientes on pendientes.IDCLIENTE = clientes.IDCLIENTE INNER JOIN direcciones on pendientes.IDDIRECCION = direcciones.IDDIRECCION WHERE pendientes.ESTADO='"+ESTADO+"' AND pendientes.CATEGORIA != 'INTERNET' ORDER BY pendientes.CATEGORIA ASC";
        
        rs = mane.consultar(sql);
        ModeloPendientes mdl;
        while(rs.next())
        {
            mdl = new ModeloPendientes();
            mdl.setIDPENDIENTE(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setTEL1(rs.getString(3));
            mdl.setCALLE(rs.getString(4));
            mdl.setNUMERO(rs.getString(5));
            mdl.setCOLONIA(rs.getString(6));
            mdl.setLOCALIDAD(rs.getString(7));
            mdl.setLATITUD(rs.getString(8));
            mdl.setLONGITUD(rs.getString(9));

            mdl.setDESCRIPCION(rs.getString(10));
            mdl.setCOMENTARIOS(rs.getString(11));
            mdl.setFREG(rs.getString(12));

            if ("CANCELADO".equals(ESTADO)||"OK-FINALIZADO".equals(ESTADO))
            {
                DIAS=0;
            }
            else
            {
                //fecha=formateador.format(hoy);

                fin=G.formato.parse(mdl.getFREG());
                //inicio=formateador.parse(fecha);

                difm = G.hoy().getTime() - fin.getTime();

                dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);

                DIAS= (int) dif;
            }
            mdl.setCATEGORIA(rs.getString(13));
            mdl.setDIAS(DIAS);
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
}
