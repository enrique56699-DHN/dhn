/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloSalidasCaja;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlSalidasCaja extends ControlAbstracto
{
    private final String TABLA = "SALIDASCAJA";
    
    public ControlSalidasCaja() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }    
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloSalidasCaja> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ORDER BY IDSALIDACAJA DESC";
        rs = mane.consultar(sql);
        ModeloSalidasCaja x;
        while(rs.next())
        {
            x = new ModeloSalidasCaja();
            x.setIDSALIDACAJA(rs.getInt(1));
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
        ModeloSalidasCaja x = (ModeloSalidasCaja)model;
            sql = "INSERT INTO "+TABLA+" (IDSALIDACAJA, DESCRIPCION, MONTO, EMPRESA, FECHA)"
                + "VALUES (" + 
                x.getIDSALIDACAJA() + ",'" +    
                x.getDESCRIPCION() + "'," +
                x.getMONTO() + ",'" +
                x.getEMPRESA() + "','" +  
                x.getFECHA() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloSalidasCaja x = (ModeloSalidasCaja)model;
        sql = "update "+TABLA+" set "
                + " DESCRIPCION = '" + x.getDESCRIPCION()
                + "',MONTO=" + x.getMONTO()
                + ", EMPRESA='" + x.getEMPRESA()
                + "', FECHA='" + x.getFECHA()
                + "' where IDSALIDACAJA=" + x.getIDSALIDACAJA() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloSalidasCaja x = (ModeloSalidasCaja)model;
        sql=("delete from "+TABLA+" where IDSALIDACAJA =" + x.getIDSALIDACAJA() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloSalidasCaja getRegistro(Integer code) throws Exception
    {
        sql = "select * from "+TABLA+" where IDSALIDACAJA= "+code;
        rs = mane.consultar(sql);
        ModeloSalidasCaja x=new ModeloSalidasCaja();
        while(rs.next())
        {
            x.setIDSALIDACAJA(rs.getInt(1));
            x.setDESCRIPCION(rs.getString(2));
            x.setMONTO(rs.getFloat(3));
            x.setEMPRESA(rs.getString(4));
            x.setFECHA(rs.getString(5));
        }
        mane.limpiarRecursos();
        return x;
    }
    
    public ModeloSalidasCaja getRegistrosID(int IDSALIDACAJA) throws Exception
    {
        sql = "select * from "+TABLA+" where IDSALIDACAJA= "+IDSALIDACAJA+"";
        rs = mane.consultar(sql);
        ModeloSalidasCaja x = null;
        while(rs.next())
        {
            x = new ModeloSalidasCaja();
            x.setIDSALIDACAJA(rs.getInt(1));
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
        sql = "SELECT SUM(salidascaja.MONTO) FROM salidascaja";
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
        sql = "SELECT SUM(salidascaja.MONTO) FROM salidascaja WHERE salidascaja.EMPRESA = '"+EMPRESA+"'";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getDouble(1);
        }
        mane.limpiarRecursos();
        return suma;
    }
}
