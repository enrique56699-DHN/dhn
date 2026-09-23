/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloDetallesDisp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlDetallesDisp extends ControlAbstracto
{
    private final String TABLA = "DETALLESDISP";
    
    public ControlDetallesDisp() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloDetallesDisp> lista = new ArrayList<>();
        sql = "SELECT * FROM "+TABLA;
        rs = mane.consultar(sql);
        ModeloDetallesDisp x;
        while(rs.next())
        {
            x = new ModeloDetallesDisp();
            x.setIDD(rs.getInt(1));
            x.setTABLA(rs.getString(2));
            x.setCATEGORIA(rs.getString(3));
            x.setDESCRIPCION(rs.getString(4));

            lista.add(x);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public List getRegistros(String Tabla, String CATEGORIA) throws Exception {
        ArrayList<ModeloDetallesDisp> lista = new ArrayList<>();
        sql = "SELECT DESCRIPCION FROM "+TABLA+" WHERE TABLA = '"+Tabla+"' AND CATEGORIA = '"+CATEGORIA+"' ORDER BY DESCRIPCION ASC";
        rs = mane.consultar(sql);
        ModeloDetallesDisp x;
        while(rs.next())
        {
            x = new ModeloDetallesDisp();
            x.setDESCRIPCION(rs.getString(1));

            lista.add(x);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloDetallesDisp x = (ModeloDetallesDisp)model;
        sql = "INSERT INTO "+TABLA+" (IDD, TABLA, CATEGORIA, DESCRIPCION)"
                + "VALUES (" + 
                x.getIDD() + ",'" + 
                x.getTABLA() + "','" +
                x.getCATEGORIA() + "','" +
                x.getDESCRIPCION() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloDetallesDisp pto = (ModeloDetallesDisp)model;
        sql = "update "+TABLA+" set "
                + " TABLA = '" + pto.getTABLA()
                + "' , CATEGORIA = '" + pto.getCATEGORIA()
                + "' , DESCRIPCION = '" + pto.getDESCRIPCION()
                + "' where IDD = " + pto.getIDD() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloDetallesDisp pto = (ModeloDetallesDisp)model;
        sql=("delete from "+TABLA+" where IDD =" + pto.getIDD() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloDetallesDisp getRegistrosID(int ID) throws Exception
    {
        sql = "SELECT * FROM "+TABLA+" WHERE IDD = "+ID+"";
        rs = mane.consultar(sql);
        ModeloDetallesDisp x = null;
        while(rs.next())
        {
            x = new ModeloDetallesDisp();
            x.setIDD(rs.getInt(1));
            x.setTABLA(rs.getString(2));
            x.setCATEGORIA(rs.getString(3));
            x.setDESCRIPCION(rs.getString(4));

        }
        mane.limpiarRecursos();
        return x;
    }
    
    public List getRegistrosIDE(int IDD) throws Exception
    {
        ArrayList<ModeloDetallesDisp> lista = new ArrayList<>();
        sql = "SELECT * FROM "+TABLA+" WHERE IDD = "+IDD+"";
        rs = mane.consultar(sql);
        ModeloDetallesDisp x;
        while(rs.next())
        {
            x = new ModeloDetallesDisp();
            x.setIDD(rs.getInt(1));
            x.setTABLA(rs.getString(2));
            x.setCATEGORIA(rs.getString(3));
            x.setDESCRIPCION(rs.getString(4));

            lista.add(x);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
}
