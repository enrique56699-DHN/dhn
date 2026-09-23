/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloCotizaciones;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author win 10
 */
public class ControlCotizaciones extends ControlAbstracto
{
private final String TABLA = "COTIZACIONES";

    public ControlCotizaciones() throws ClassNotFoundException, SQLException
    {
        this.mane = new Manejador();
    }
   
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloCotizaciones> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloCotizaciones mdl;
        while(rs.next())
        {
            mdl = new ModeloCotizaciones();
            mdl.setIDCOTIZACION(rs.getInt(1));
            mdl.setIDPENDIENTE(rs.getInt(2));
            mdl.setFECHA(rs.getString(3));
            mdl.setCATEGORIA(rs.getString(4));
            mdl.setCOMENTARIOS(rs.getString(5));
            mdl.setDOC(rs.getString(6));
            mdl.setESTADO(rs.getString(7));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloCotizaciones mdl = (ModeloCotizaciones)model;
        sql = "INSERT INTO "+TABLA+" (IDCOTIZACION, IDPENDIENTE, FECHA, CATEGORIA, COMENTARIOS, DOC, ESTADO)"
                + "VALUES (" + 
                mdl.getIDCOTIZACION() + "," +
                mdl.getIDPENDIENTE() + ",'" +
                mdl.getFECHA() + "','" +
                mdl.getCATEGORIA() + "','" +
                mdl.getCOMENTARIOS() + "','" +
                mdl.getDOC() + "','" +
                mdl.getESTADO() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloCotizaciones mdl = (ModeloCotizaciones)model;
        sql = "update "+TABLA+" set "
                + "IDPENDIENTE=" + mdl.getIDPENDIENTE()
                + ",FECHA='" + mdl.getFECHA()
                + "',CATEGORIA='" + mdl.getCATEGORIA()
                + "',COMENTARIOS='" + mdl.getCOMENTARIOS()
                + "',DOC='" + mdl.getDOC()
                + "',ESTADO='" + mdl.getESTADO()
                + "' where IDCOTIZACION=" + mdl.getIDCOTIZACION() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloCotizaciones mdl = (ModeloCotizaciones)model;
        sql=("delete from "+TABLA+" where IDCOTIZACION =" + mdl.getIDCOTIZACION() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloCotizaciones getRegistro(String CONSULTA, int ID, String ESTADO) throws Exception {
        sql = "SELECT cotizaciones.IDCOTIZACION, cotizaciones.IDPENDIENTE, cotizaciones.FECHA, cotizaciones.CATEGORIA, cotizaciones.COMENTARIOS, "
                + "cotizaciones.DOC, cotizaciones.ESTADO, clientes.NOMBRE, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, "
                + "direcciones.LOCALIDAD, clientes.TEL1, clientes.TEL2 "
                + "FROM "+TABLA+" "
                + "INNER JOIN pendientes ON pendientes.IDPENDIENTE = cotizaciones.IDPENDIENTE "
                + "INNER JOIN clientes ON pendientes.IDCLIENTE = clientes.IDCLIENTE "
                + "INNER JOIN direcciones ON pendientes.IDDIRECCION = direcciones.IDDIRECCION";
        switch(CONSULTA)
        {
            case "IDCOTIZACION":
                sql = sql+" WHERE cotizaciones."+CONSULTA+" = "+ID+" ";
                break;
            case "IDPENDIENTE":
                sql = sql+" WHERE cotizaciones."+CONSULTA+" = "+ID+" ";
                break;      
            default:
                break;
        }
        if(!ESTADO.equals(""))
        {
            sql = sql+" WHERE cotizaciones."+CONSULTA+" = '"+ESTADO+"' ";
        }
        
        rs = mane.consultar(sql);
        ModeloCotizaciones mdl = new ModeloCotizaciones();
        while(rs.next())
        {   
            mdl.setIDCOTIZACION(rs.getInt(1));
            mdl.setIDPENDIENTE(rs.getInt(2));
            mdl.setFECHA(rs.getString(3));
            mdl.setCATEGORIA(rs.getString(4));
            mdl.setCOMENTARIOS(rs.getString(5));
            mdl.setDOC(rs.getString(6));
            mdl.setESTADO(rs.getString(7));
            mdl.setNOMBRE(rs.getString(8));
            mdl.setCALLE(rs.getString(9));
            mdl.setNUMERO(rs.getString(10));
            mdl.setCOLONIA(rs.getString(11));
            mdl.setLOCALIDAD(rs.getString(12));
            mdl.setTEL(rs.getString(13));
            mdl.setTEL2(rs.getString(14));

        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public List getRegistros(String CONSULTA, int ID, String ESTADO) throws Exception {
        ArrayList<ModeloCotizaciones> lista = new ArrayList<>();
        sql = "SELECT cotizaciones.IDCOTIZACION, cotizaciones.IDPENDIENTE, cotizaciones.FECHA, cotizaciones.CATEGORIA, cotizaciones.COMENTARIOS, "
                + "cotizaciones.DOC, cotizaciones.ESTADO, clientes.NOMBRE, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, "
                + "direcciones.LOCALIDAD, clientes.TEL1, clientes.TEL2 "
                + "FROM "+TABLA+" "
                + "INNER JOIN pendientes ON pendientes.IDPENDIENTE = cotizaciones.IDPENDIENTE "
                + "INNER JOIN clientes ON pendientes.IDCLIENTE = clientes.IDCLIENTE "
                + "INNER JOIN direcciones ON pendientes.IDDIRECCION = direcciones.IDDIRECCION";
        switch(CONSULTA)
        {
            case "IDCOTIZACION":
                sql = sql+" WHERE cotizaciones."+CONSULTA+" = "+ID+" ";
                break;
            case "IDPENDIENTE":
                sql = sql+" WHERE cotizaciones."+CONSULTA+" = "+ID+" ";
                break;                
            default:
                break;
        }
        if(!ESTADO.equals(""))
        {
            sql = sql+" WHERE cotizaciones.ESTADO = '"+ESTADO+"' ";
        }            
        
        rs = mane.consultar(sql);
        ModeloCotizaciones mdl;
        while(rs.next())
        {
            mdl = new ModeloCotizaciones();
            mdl.setIDCOTIZACION(rs.getInt(1));
            mdl.setIDPENDIENTE(rs.getInt(2));
            mdl.setFECHA(rs.getString(3));
            mdl.setCATEGORIA(rs.getString(4));
            mdl.setCOMENTARIOS(rs.getString(5));
            mdl.setDOC(rs.getString(6));
            mdl.setESTADO(rs.getString(7));
            mdl.setNOMBRE(rs.getString(8));
            mdl.setCALLE(rs.getString(9));
            mdl.setNUMERO(rs.getString(10));
            mdl.setCOLONIA(rs.getString(11));
            mdl.setLOCALIDAD(rs.getString(12));
            mdl.setTEL(rs.getString(13));
            mdl.setTEL2(rs.getString(14));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }    
}
