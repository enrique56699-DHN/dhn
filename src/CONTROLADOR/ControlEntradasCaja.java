/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloEntradasCaja;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlEntradasCaja extends ControlAbstracto
{
    private final String TABLA = "ENTRADASCAJA";
    
    public ControlEntradasCaja() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloEntradasCaja> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ORDER BY IDENTRADACAJA DESC";
        rs = mane.consultar(sql);
        //rs = mane.consultar1(sql);
        ModeloEntradasCaja x;
        while(rs.next())
        {
            x = new ModeloEntradasCaja();
            x.setIDENTRADACAJA(rs.getInt(1));
            x.setDESCRIPCION(rs.getString(2));
            x.setMONTO(rs.getFloat(3));
            x.setEMPRESA(rs.getString(4));
            x.setFECHA(rs.getString(5));
            lista.add(x);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloEntradasCaja x = (ModeloEntradasCaja)model;
        sql = "INSERT INTO "+TABLA+" (IDENTRADACAJA, DESCRIPCION, MONTO, EMPRESA, FECHA)"
            + "VALUES (" + 
            x.getIDENTRADACAJA() + ",'" +
            x.getDESCRIPCION() + "'," +
            x.getMONTO() + ",'" + 
            x.getEMPRESA() + "','" +  
            x.getFECHA() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloEntradasCaja x = (ModeloEntradasCaja)model;
        sql = "update "+TABLA+" set "
                + " DESCRIPCION = '" + x.getDESCRIPCION()
                + "',MONTO=" + x.getMONTO()
                + ", EMPRESA='" + x.getEMPRESA()
                + "', FECHA='" + x.getFECHA()
                + "' where IDENTRADACAJA = " + x.getIDENTRADACAJA() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloEntradasCaja x = (ModeloEntradasCaja)model;
        sql=("delete from "+TABLA+" where IDENTRADACAJA =" + x.getIDENTRADACAJA() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloEntradasCaja getRegistro(Integer code) throws Exception
    {
        sql = "select * from "+TABLA+" where IDENTRADACAJA = "+code;
        rs = mane.consultar(sql);
        ModeloEntradasCaja x=new ModeloEntradasCaja();
        while(rs.next())
        {
            x = new ModeloEntradasCaja();
            x.setIDENTRADACAJA(rs.getInt(1));
            x.setDESCRIPCION(rs.getString(2));
            x.setMONTO(rs.getFloat(3));
            x.setEMPRESA(rs.getString(4));
            x.setFECHA(rs.getString(5));

        }
        mane.limpiarRecursos();
        return x;
    }
    
    public ModeloEntradasCaja getRegistrosID(int IDENTRADACAJA) throws Exception
    {
        sql = "select * from "+TABLA+" where IDENTRADACAJA= "+IDENTRADACAJA+"";
        rs = mane.consultar(sql);
        ModeloEntradasCaja x = null;
        while(rs.next())
        {
            x = new ModeloEntradasCaja();
            x.setIDENTRADACAJA(rs.getInt(1));
            x.setDESCRIPCION(rs.getString(2));
            x.setMONTO(rs.getFloat(3));
            x.setEMPRESA(rs.getString(4));
            x.setFECHA(rs.getString(5));
        }
        mane.limpiarRecursos();
        return x;
    }
    
    public double suma() throws Exception
    {
        double suma = 0;
        sql = "SELECT SUM(entradascaja.MONTO) FROM entradascaja";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getDouble(1);
        }
        mane.limpiarRecursos();
        return suma;
    }
    
    public double suma(String EMPRESA) throws Exception
    {
        double suma = 0;
        sql = "SELECT SUM(entradascaja.MONTO) FROM entradascaja WHERE entradascaja.EMPRESA = '"+EMPRESA+"'";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getDouble(1);
        }
        mane.limpiarRecursos();
        return suma;
    }
}
