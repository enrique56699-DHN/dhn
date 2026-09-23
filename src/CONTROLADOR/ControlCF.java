/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloCF;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author win 10
 */
public class ControlCF extends ControlAbstracto
{
    private final String TABLA = "CF";
    
    public ControlCF() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloCF> lista = new ArrayList<>();
        sql = "SELECT * FROM "+TABLA;
        rs = mane.consultar(sql);
        ModeloCF x;
        while(rs.next())
        {
            x = new ModeloCF();
            x.setIDCF(rs.getInt(1));
            x.setFECHA(rs.getString(2));
            x.setTIPO(rs.getString(3));
            x.setMONTO(rs.getFloat(4));

            lista.add(x);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloCF x = (ModeloCF)model;
        sql = "INSERT INTO "+TABLA+" (IDCF, FECHA,TIPO,MONTO)"
                + "VALUES (" + 
                x.getIDCF() + ",'" + 
                x.getFECHA() + "','" +
                x.getTIPO() + "'," +
                x.getMONTO() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloCF pto = (ModeloCF)model;
        sql = "update "+TABLA+" set "
                + " FECHA = '" + pto.getFECHA()
                + "' , TIPO = '" + pto.getTIPO()
                + "' , MONTO = " + pto.getMONTO()
                + " where IDCF = " + pto.getIDCF() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloCF pto = (ModeloCF)model;
        sql=("delete from "+TABLA+" where IDCF =" + pto.getIDCF() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloCF getRegistrosID(int ID) throws Exception
    {
        sql = "SELECT * FROM "+TABLA+" WHERE IDCF = "+ID+"";
        rs = mane.consultar(sql);
        ModeloCF x = null;
        while(rs.next())
        {
            x = new ModeloCF();
            x.setIDCF(rs.getInt(1));
            x.setFECHA(rs.getString(2));
            x.setTIPO(rs.getString(3));
            x.setMONTO(rs.getFloat(4));
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
