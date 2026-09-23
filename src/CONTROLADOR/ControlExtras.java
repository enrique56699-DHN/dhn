/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloExtras;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author win 10
 */
public class ControlExtras extends ControlAbstracto
{
    private final String TABLA = "EXTRAS";
    
    public ControlExtras() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloExtras> lista = new ArrayList<>();
        sql = "SELECT * FROM "+TABLA+" ORDER BY IDEXTRA DESC";
        rs = mane.consultar(sql);
        ModeloExtras x;
        while(rs.next())
        {
            x = new ModeloExtras();
            x.setIDEXTRA(rs.getInt(1));
            x.setFECHA(rs.getString(2));
            x.setDESCRIPCION(rs.getString(3));
            x.setTIPO(rs.getString(4));
            x.setMONTO(rs.getFloat(5));

            lista.add(x);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloExtras x = (ModeloExtras)model;
        sql = "INSERT INTO "+TABLA+" (IDEXTRA, FECHA, DESCRIPCION, TIPO, MONTO)"
                + "VALUES (" + 
                x.getIDEXTRA() + ",'" + 
                x.getFECHA() + "','" +
                x.getDESCRIPCION() + "','" +
                x.getTIPO() + "'," +
                x.getMONTO() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloExtras pto = (ModeloExtras)model;
        sql = "update "+TABLA+" set "
                + " FECHA = '" + pto.getFECHA()
                + "' , DESCRIPCION = '" + pto.getDESCRIPCION()
                + "' , TIPO = '" + pto.getTIPO()
                + "' , MONTO = " + pto.getMONTO()
                + " where IDEXTRA = " + pto.getIDEXTRA() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloExtras pto = (ModeloExtras)model;
        sql=("delete from "+TABLA+" where IDEXTRA =" + pto.getIDEXTRA() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloExtras getRegistrosID(int ID) throws Exception
    {
        sql = "SELECT * FROM "+TABLA+" WHERE IDEXTRA = "+ID+"";
        rs = mane.consultar(sql);
        ModeloExtras x = null;
        while(rs.next())
        {
            x = new ModeloExtras();
            x.setIDEXTRA(rs.getInt(1));
            x.setFECHA(rs.getString(2));
            x.setDESCRIPCION(rs.getString(3));
            x.setTIPO(rs.getString(4));
            x.setMONTO(rs.getFloat(5));
        }
        mane.limpiarRecursos();
        return x;
    }
    
    /*public float monto(String TIPO) throws Exception
    {
        float a = 0;
        sql = "SELECT sum(monto) FROM "+TABLA+" WHERE TIPO = '"+TIPO+"' ";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            a = rs.getFloat(1);            
        }
        mane.cerrarConexion();
        return a;
    }*/
}
