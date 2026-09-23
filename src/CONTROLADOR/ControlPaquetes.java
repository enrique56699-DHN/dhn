/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.ModeloPaquetes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oscar E Gonzalez G
 */
public class ControlPaquetes extends ControlAbstracto
{
    
    private final String TABLA = "PAQUETES";
    
    public ControlPaquetes() throws ClassNotFoundException, SQLException
    {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloPaquetes> paq = new ArrayList<>();
        sql = "select * from "+TABLA+"";
        rs = mane.consultar(sql);
        ModeloPaquetes pto;
        while(rs.next())
        {
            pto = new ModeloPaquetes();
            pto.setIDPAQUETE(rs.getInt(1));
            pto.setCOSTO(rs.getInt(2));
            pto.setDESCRIPCION(rs.getString(3));
            
            paq.add(pto);
        }
        mane.limpiarRecursos();
        return paq;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloPaquetes pto = (ModeloPaquetes)model;
        sql = "INSERT INTO "+TABLA+" (IDPAQUETE,COSTO,DESCRIPCION)"
                + "VALUES (" + 
                pto.getIDPAQUETE() + "," + 
                pto.getCOSTO() + ",'" +
                pto.getDESCRIPCION() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloPaquetes pto = (ModeloPaquetes)model;
        sql = "update "+TABLA+" set "
                + "COSTO=" + pto.getCOSTO()
                + ",DESCRIPCION='" + pto.getDESCRIPCION()
                + "' where IDPAQUETE=" + pto.getIDPAQUETE() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloPaquetes pto = (ModeloPaquetes)model;
        sql=("delete from "+TABLA+" where IDPAQUETE =" + pto.getIDPAQUETE() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloPaquetes getRegistrosID(int ID) throws Exception
     {
        sql = "select * from "+TABLA+" where IDPAQUETE ="+ID+"";
        rs = mane.consultar(sql);
        ModeloPaquetes pto = null;
        while(rs.next())
        {
            pto = new ModeloPaquetes();
            pto.setIDPAQUETE(rs.getInt(1));
            pto.setCOSTO(rs.getInt(2));
            pto.setDESCRIPCION(rs.getString(3));

        }
        mane.limpiarRecursos();
        return pto;
     }
    
    public List getRegistros(int ID) throws Exception {
        ArrayList<ModeloPaquetes> paq = new ArrayList<>();
        sql = "select * from "+TABLA+" where IDPAQUETE ="+ID+"";
        rs = mane.consultar(sql);
        ModeloPaquetes pto;
        while(rs.next())
        {
            pto = new ModeloPaquetes();
            pto.setIDPAQUETE(rs.getInt(1));
            pto.setCOSTO(rs.getInt(2));
            pto.setDESCRIPCION(rs.getString(3));
            
            paq.add(pto);
        }
        mane.limpiarRecursos();
        return paq;
    }    
    
}
