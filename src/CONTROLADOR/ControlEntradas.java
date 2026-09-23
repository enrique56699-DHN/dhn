/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloEntradas;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlEntradas extends ControlAbstracto
{
    private final String TABLA = "ENTRADAS";
    
    public ControlEntradas() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }  

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloEntradas> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ORDER BY IDENTRADA DESC";
        rs = mane.consultar(sql);
        //rs = mane.consultar1(sql);
        ModeloEntradas x;
        while(rs.next())
        {
            x = new ModeloEntradas();
            x.setIDENTRADA(rs.getInt(1));
            x.setDESCRIPCION(rs.getString(2));
            x.setMONTO(rs.getFloat(3));
            x.setCATEGORIA(rs.getString(4));
            x.setFECHA(rs.getString(5));
            x.setMETODO(rs.getString(6));
            x.setIDCAJA(rs.getInt(7));
            lista.add(x);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloEntradas x = (ModeloEntradas)model;
        sql = "INSERT INTO "+TABLA+" (IDENTRADA, DESCRIPCION, MONTO, CATEGORIA, FECHA, METODO,IDCAJA)"
            + "VALUES (" + 
            x.getIDENTRADA() + ",'" +
            x.getDESCRIPCION() + "'," +
            x.getMONTO() + ",'" + 
            x.getCATEGORIA() + "','" +
            x.getFECHA() + "','" +
            x.getMETODO() + "'," +    
            x.getIDCAJA() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloEntradas x = (ModeloEntradas)model;
        sql = "update "+TABLA+" set "
                + " DESCRIPCION = '" + x.getDESCRIPCION()
                + "',MONTO=" + x.getMONTO()
                + ", CATEGORIA='" + x.getCATEGORIA()
                + "', FECHA='" + x.getFECHA()
                + "', METODO='" + x.getMETODO()
                + "', IDCAJA = " + x.getIDCAJA()
                + " where IDENTRADA=" + x.getIDENTRADA() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloEntradas x = (ModeloEntradas)model;
        sql=("delete from "+TABLA+" where IDENTRADA =" + x.getIDENTRADA() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloEntradas getRegistro(Integer code) throws Exception
    {
        sql = "select * from "+TABLA+" where IDENTRADA = "+code;
        rs = mane.consultar(sql);
        ModeloEntradas x=new ModeloEntradas();
        while(rs.next())
        {
            x = new ModeloEntradas();
            x.setIDENTRADA(rs.getInt(1));
            x.setDESCRIPCION(rs.getString(2));
            x.setMONTO(rs.getFloat(3));
            x.setCATEGORIA(rs.getString(4));
            x.setFECHA(rs.getString(5));
            x.setMETODO(rs.getString(6));

        }
        mane.limpiarRecursos();
        return x;
    }
    
    public ModeloEntradas getRegistrosID(int ID) throws Exception
    {
        sql = "select * from "+TABLA+" where IDENTRADA= "+ID+"";
        rs = mane.consultar(sql);
        ModeloEntradas x = null;
        while(rs.next())
        {
            x = new ModeloEntradas();
            x.setIDENTRADA(rs.getInt(1));
            x.setDESCRIPCION(rs.getString(2));
            x.setMONTO(rs.getFloat(3));
            x.setCATEGORIA(rs.getString(4));
            x.setFECHA(rs.getString(5));
            x.setMETODO(rs.getString(6));
            x.setIDCAJA(rs.getInt(7));
        }
        mane.limpiarRecursos();
        return x;
    }
    
    public List getRegistros(int DE, int AL, String METODO) throws Exception
    {
        ArrayList<ModeloEntradas> m = new ArrayList<>();
        if ("TODOS".equals(METODO))
        {
            sql = "select * from "+TABLA+" where IDENTRADA between "+ DE +" AND "+ AL +" ";
        }
        else
            sql = "select * from "+TABLA+" where entradas.METODO = '"+METODO+"' AND entradas.IDENTRADA between "+ DE +" AND "+ AL +" ";
        
        rs = mane.consultar(sql);
        ModeloEntradas x;

        while(rs.next())
        {
            x = new ModeloEntradas();
            x.setIDENTRADA(rs.getInt(1));
            x.setDESCRIPCION(rs.getString(2));
            x.setMONTO(rs.getFloat(3));
            x.setCATEGORIA(rs.getString(4));
            x.setFECHA(rs.getString(5));
            x.setMETODO(rs.getString(6));
            m.add(x);
            
        }
        mane.limpiarRecursos();
        return m;
    }
    
    public List getRegistros(String DE, String AL, String METODO) throws Exception
    {
        ArrayList<ModeloEntradas> m = new ArrayList<>();
        sql = "select * from "+TABLA+" where FECHA between '"+ DE +"' AND '"+ AL +"' and METODO = '" + METODO + "' ";
        rs = mane.consultar(sql);
        ModeloEntradas x;

        while(rs.next())
        {
            x = new ModeloEntradas();
            x.setIDENTRADA(rs.getInt(1));
            x.setDESCRIPCION(rs.getString(2));
            x.setMONTO(rs.getFloat(3));
            x.setCATEGORIA(rs.getString(4));
            x.setFECHA(rs.getString(5));
            x.setMETODO(rs.getString(6));
            m.add(x);
            
        }
        mane.limpiarRecursos();
        return m;
    }
    
    public List getRegistros(String METODO) throws Exception {
        ArrayList<ModeloEntradas> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" where METODO = '"+METODO+"' ";
        rs = mane.consultar(sql);
        //rs = mane.consultar1(sql);
        ModeloEntradas x;
        while(rs.next())
        {
            x = new ModeloEntradas();
            x.setIDENTRADA(rs.getInt(1));
            x.setDESCRIPCION(rs.getString(2));
            x.setMONTO(rs.getFloat(3));
            x.setCATEGORIA(rs.getString(4));
            x.setFECHA(rs.getString(5));
            x.setMETODO(rs.getString(6));
            lista.add(x);

        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public List listaCorteD(int IDENTRADAI, String METODO, int IDCAJA) throws Exception
    {
        ArrayList<ModeloEntradas> m = new ArrayList<>();
        sql = "SELECT entradas.IDENTRADA, entradas.DESCRIPCION, entradas.MONTO FROM "+TABLA+" WHERE entradas.METODO = '"+METODO+"' and entradas.IDENTRADA > "+IDENTRADAI+" and entradas.IDCAJA ="+IDCAJA+"";
        rs = mane.consultar(sql);
        ModeloEntradas mdl;

        while(rs.next())
        {

            mdl = new ModeloEntradas();
            mdl.setIDENTRADA(rs.getInt(1));
            mdl.setDESCRIPCION(rs.getString(2));
            mdl.setMONTO(rs.getFloat(3));
            m.add(mdl);
            
        }
        mane.limpiarRecursos();
        return m;
    }
    

    public float suma(float IDENTRADAI, float IDENTRADAF, String METODO, int IDCAJA) throws Exception
    {
        float suma = 0;
        if("".equals(METODO))
        {
            sql = "SELECT SUM(MONTO) FROM "+TABLA+" WHERE entradas.IDENTRADA BETWEEN "+IDENTRADAI+" AND "+IDENTRADAF;
        }
        else
            sql = "SELECT SUM(MONTO) FROM "+TABLA+" WHERE entradas.IDENTRADA BETWEEN "+IDENTRADAI+" AND "+IDENTRADAF+" AND entradas.METODO = '"+METODO+"' AND entradas.IDCAJA ="+IDCAJA+" ";
        
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getFloat(1);
        }
        mane.limpiarRecursos();
        return suma;
    }
    
    public List listaCorteD(int IDENTRADAI, int IDENTRADAF, String METODO, int IDCAJA) throws Exception
    {
        ArrayList<ModeloEntradas> m = new ArrayList<>();
        sql = "SELECT entradas.IDENTRADA, entradas.DESCRIPCION, entradas.MONTO FROM "+TABLA+" WHERE entradas.IDENTRADA BETWEEN "+IDENTRADAI+" AND "+IDENTRADAF+" AND entradas.METODO = '"+METODO+"' and entradas.IDCAJA ="+IDCAJA+" ";
        rs = mane.consultar(sql);
        ModeloEntradas mdl;

        while(rs.next())
        {

            mdl = new ModeloEntradas();
            mdl.setIDENTRADA(rs.getInt(1));
            mdl.setDESCRIPCION(rs.getString(2));
            mdl.setMONTO(rs.getFloat(3));
            m.add(mdl);
            
        }
        mane.limpiarRecursos();
        return m;
    }
}
