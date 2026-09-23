/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloSalidas;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlSalidas extends ControlAbstracto
{
    private final String TABLA = "SALIDAS";
    
    public ControlSalidas() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloSalidas> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ORDER BY IDSALIDA DESC";
        rs = mane.consultar(sql);
        //rs = mane.consultar1(sql);
        ModeloSalidas mdl;
        while(rs.next())
        {
            mdl = new ModeloSalidas();
            mdl.setIDSALIDA(rs.getInt(1));
            mdl.setDESCRIPCION(rs.getString(2));
            mdl.setMONTO(rs.getFloat(3));
            mdl.setCATEGORIA(rs.getString(4));
            mdl.setFECHA(rs.getString(5));
            mdl.setMETODO(rs.getString(6));
            mdl.setIDCAJA(rs.getInt(7));
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloSalidas x = (ModeloSalidas)model;
            sql = "INSERT INTO "+TABLA+" (IDSALIDA, DESCRIPCION, MONTO, CATEGORIA, FECHA, METODO,IDCAJA)"
                + "VALUES (" + 
                x.getIDSALIDA() + ",'" +    
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
        ModeloSalidas x = (ModeloSalidas)model;
        sql = "update "+TABLA+" set "
                + " DESCRIPCION = '" + x.getDESCRIPCION()
                + "',MONTO=" + x.getMONTO()
                + ", CATEGORIA='" + x.getCATEGORIA()
                + "', FECHA='" + x.getFECHA()
                + "', METODO='" + x.getMETODO()
                + "', IDCAJA = " + x.getIDCAJA()
                + " where IDSALIDA=" + x.getIDSALIDA() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloSalidas x = (ModeloSalidas)model;
        sql=("delete from "+TABLA+" where IDSALIDA =" + x.getIDSALIDA() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloSalidas getRegistro(Integer code) throws Exception
    {
        sql = "select * from "+TABLA+" where IDSALIDA= "+code;
        rs = mane.consultar(sql);
        ModeloSalidas x=new ModeloSalidas();
        while(rs.next())
        {
            x.setIDSALIDA(rs.getInt(1));
            x.setDESCRIPCION(rs.getString(2));
            x.setMONTO(rs.getFloat(3));
            x.setCATEGORIA(rs.getString(4));
            x.setFECHA(rs.getString(5));
            x.setMETODO(rs.getString(6));
        }
        mane.limpiarRecursos();
        return x;
    }
    
    public ModeloSalidas getRegistrosID(int ID) throws Exception
    {
        sql = "select * from "+TABLA+" where IDSALIDA= "+ID+"";
        rs = mane.consultar(sql);
        ModeloSalidas x = null;
        while(rs.next())
        {
            x = new ModeloSalidas();
            x.setIDSALIDA(rs.getInt(1));
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
    
    public List getRegistros(String DE, String AL) throws Exception
    {
        ArrayList<ModeloSalidas> m = new ArrayList<>();
        sql = "select * from "+TABLA+" where FECHA between '"+ DE +"' AND '"+ AL +"' ";
        rs = mane.consultar(sql);
        ModeloSalidas x;

        while(rs.next())
        {
            x = new ModeloSalidas();
            x.setIDSALIDA(rs.getInt(1));
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
        ArrayList<ModeloSalidas> m = new ArrayList<>();
        sql = "select * from "+TABLA+" where FECHA between '"+ DE +"' AND '"+ AL +"' and METODO = '" + METODO + "' ";
        rs = mane.consultar(sql);
        ModeloSalidas x;

        while(rs.next())
        {
            x = new ModeloSalidas();
            x.setIDSALIDA(rs.getInt(1));
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
        ArrayList<ModeloSalidas> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" where IDSALIDA = '"+METODO+"' ";
        rs = mane.consultar(sql);
        //rs = mane.consultar1(sql);
        ModeloSalidas x;
        while(rs.next())
        {
            x = new ModeloSalidas();
            x.setIDSALIDA(rs.getInt(1));
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
    
    public List listaCorteD(int IDSALIDAI, String METODO, int IDCAJA) throws Exception
    {
        ArrayList<ModeloSalidas> m = new ArrayList<>();
        sql = "SELECT salidas.IDSALIDA, salidas.DESCRIPCION, salidas.MONTO FROM salidas WHERE salidas.METODO = '"+METODO+"' and salidas.IDSALIDA > "+IDSALIDAI+" and salidas.IDCAJA ="+IDCAJA+"";
        rs = mane.consultar(sql);
        ModeloSalidas mdl;

        while(rs.next())
        {

            mdl = new ModeloSalidas();
            mdl.setIDSALIDA(rs.getInt(1));
            mdl.setDESCRIPCION(rs.getString(2));
            mdl.setMONTO(rs.getFloat(3));
            m.add(mdl);
            
        }
        mane.limpiarRecursos();
        return m;
    }
    
    public float suma(float IDSALIDAI, float IDSALIDAF, String METODO, int IDCAJA) throws Exception
    {
        float suma = 0;
        if("".equals(METODO))
        {
            sql = "SELECT SUM(MONTO) FROM salidas WHERE salidas.IDSALIDA BETWEEN "+IDSALIDAI+" AND "+IDSALIDAF;
        }
        else
            sql = "SELECT SUM(MONTO) FROM salidas WHERE salidas.IDSALIDA BETWEEN "+IDSALIDAI+" AND "+IDSALIDAF+" AND salidas.METODO = '"+METODO+"' AND salidas.IDCAJA ="+IDCAJA+" ";
        
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getFloat(1);
        }
        mane.limpiarRecursos();
        return suma;
    }
    
    public List listaCorteD(int IDSALIDAI, int IDSALIDAF, String METODO, int IDCAJA) throws Exception
    {
        ArrayList<ModeloSalidas> m = new ArrayList<>();
        sql = "SELECT salidas.IDSALIDA, salidas.DESCRIPCION, salidas.MONTO FROM salidas WHERE salidas.IDSALIDA BETWEEN "+IDSALIDAI+" AND "+IDSALIDAF+" AND salidas.METODO = '"+METODO+"' and salidas.IDCAJA ="+IDCAJA+"";
        rs = mane.consultar(sql);
        ModeloSalidas mdl;

        while(rs.next())
        {

            mdl = new ModeloSalidas();
            mdl.setIDSALIDA(rs.getInt(1));
            mdl.setDESCRIPCION(rs.getString(2));
            mdl.setMONTO(rs.getFloat(3));
            m.add(mdl);
            
        }
        mane.limpiarRecursos();
        return m;
    }
    
    public float TU(String SOCIO, String MES, String ANO) throws Exception
    {
        float suma = 0;
        sql = "SELECT SUM(MONTO) FROM salidas WHERE CATEGORIA = 'TOMA DE UTILIDADES' AND DESCRIPCION LIKE '%"+SOCIO+"%' AND DESCRIPCION LIKE '%"+MES+"%' AND DESCRIPCION LIKE '%"+ANO+"%' ";
        
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getFloat(1);
        }
        mane.limpiarRecursos();
        return suma;
    }
}
