/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import MODELO.ModeloClientes;

/**
 *
 * @author Oscar E Gonzalez G
 */
public class ControlClientes extends ControlAbstracto
{
    private final String TABLA = "CLIENTES";
    //private final String[] COLUMNAS = {"IDCLIENTE", "NOMBRE", "TEL1", "TEL2", "TEL3", "IDENTIFICACION", "INE", "CFE"};

    public ControlClientes() throws ClassNotFoundException, SQLException
    {
        this.mane = new Manejador();
    }
   
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloClientes> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloClientes mdl;
        while(rs.next())
        {
            mdl = new ModeloClientes();
            mdl.setIDCLIENTE(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            //datos.setAPELLIDOS(rs.getString(3));
            mdl.setTEL1(rs.getString(3));
            mdl.setTEL2(rs.getString(4));
            mdl.setTEL3(rs.getString(5));
            mdl.setIDENTIFICACION(rs.getString(6));
            mdl.setINE(rs.getString(7));
            mdl.setCFE(rs.getString(8));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloClientes mdl = (ModeloClientes)model;
        sql = "INSERT INTO "+TABLA+" (IDCLIENTE, NOMBRE, TEL1, TEL2, TEL3, IDENTIFICACION, INE, CFE)"
                + "VALUES (" + 
                mdl.getIDCLIENTE() + ",'" +
                mdl.getNOMBRE() + "','" +
                //datos.getAPELLIDOS() + "','" +
                mdl.getTEL1() + "','" +
                mdl.getTEL2() + "','" +
                mdl.getTEL3() + "','" +
                mdl.getIDENTIFICACION() + "','" +
                mdl.getINE() + "','" +
                mdl.getCFE() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloClientes mdl = (ModeloClientes)model;
        sql = "update "+TABLA+" set "
                + "NOMBRE='" + mdl.getNOMBRE()
                + "',TEL1='" + mdl.getTEL1()
                + "',TEL2='" + mdl.getTEL2()
                + "',TEL3='" + mdl.getTEL3()
                + "',IDENTIFICACION='" + mdl.getIDENTIFICACION()
                + "',INE='" + mdl.getINE()
                + "',CFE='" + mdl.getCFE()
                + "' where IDCLIENTE=" + mdl.getIDCLIENTE() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloClientes mdl = (ModeloClientes)model;
        sql=("delete from "+TABLA+" where IDCLIENTE =" + mdl.getIDCLIENTE() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloClientes getRegistro(Integer code) throws Exception {
        sql = "select * from "+TABLA+" where IDCLIENTE = "+code;
        rs = mane.consultar(sql);
        ModeloClientes mdl=new ModeloClientes();
        while(rs.next())
        {   
            mdl.setIDCLIENTE(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setTEL1(rs.getString(3));
            mdl.setTEL2(rs.getString(4));
            mdl.setTEL3(rs.getString(5));
            mdl.setIDENTIFICACION(rs.getString(6));
            mdl.setINE(rs.getString(7));
            mdl.setCFE(rs.getString(8));

        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public ModeloClientes getRegistrosID(int ID) throws Exception {
        sql = "select NOMBRE, TEL1, TEL2, TEL3, IDENTIFICACION from "+TABLA+" where IDCLIENTE ="+ID+"";        
        rs = mane.consultar(sql);
        ModeloClientes mdl = null;
        while(rs.next())
        {
            mdl = new ModeloClientes();

            mdl.setNOMBRE(rs.getString(1));
            mdl.setTEL1(rs.getString(2));
            mdl.setTEL2(rs.getString(3));
            mdl.setTEL3(rs.getString(4));
            mdl.setIDENTIFICACION(rs.getString(5));

        }
        mane.limpiarRecursos();
        return mdl;
    }

    public List getRegistrosT() throws Exception {
        ArrayList<ModeloClientes> lista = new ArrayList<>();
        sql = "SELECT clientes.IDCLIENTE, clientes.NOMBRE, clientes.TEL1, clientes.TEL2 FROM "+TABLA+" ORDER BY IDCLIENTE DESC";
        rs = mane.consultar(sql);
        ModeloClientes mdl;
        while(rs.next())
        {
            mdl = new ModeloClientes();
            mdl.setIDCLIENTE(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setTEL1(rs.getString(3));
            mdl.setTEL2(rs.getString(4));
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public List getRegistros(String TIPO, int ID) throws Exception {
        ArrayList<ModeloClientes> lista = new ArrayList<>();
        switch(TIPO)
        {
            case "SERVICIO":
                sql = "SELECT pendientes.IDPENDIENTE, pendientes.ESTADO, ordenesp.IDORDENP "
                        + "FROM pendientes "
                        + "LEFT JOIN ordenesp "
                        + "ON pendientes.IDPENDIENTE = ordenesp.IDPENDIENTE "
                        + "WHERE pendientes.IDCLIENTE = "+ID+" "
                        + "ORDER BY pendientes.IDPENDIENTE DESC";
                break;
            case "INTERNET":
                sql = "SELECT contratos.IDCONTRATO, contratos.ESTADO, ordenes.IDORDEN "
                        + "FROM contratos "
                        + "LEFT JOIN ordenes "
                        + "ON contratos.IDCONTRATO = ordenes.IDORDEN "
                        + "WHERE contratos.IDCLIENTE = "+ID+" "
                        + "ORDER BY contratos.IDCONTRATO DESC";
                break;
            default:
                break;
        }        
        
        rs = mane.consultar(sql);
        ModeloClientes mdl;
        while(rs.next())
        {
            mdl = new ModeloClientes();
            mdl.setID(rs.getInt(1));
            mdl.setESTADO(rs.getString(2));
            mdl.setIDORDEN(rs.getInt(3));
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
}
