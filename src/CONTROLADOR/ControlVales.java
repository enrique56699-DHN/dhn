/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloVales;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author win 10
 */
public class ControlVales extends ControlAbstracto
{
    private final String TABLA = "VALES";
    
    public ControlVales() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloVales> lista = new ArrayList<>();
        sql = "SELECT * FROM "+TABLA+" ORDER BY IDVALE DESC";
        rs = mane.consultar(sql);
        ModeloVales mdl;
        while(rs.next())
        {
            mdl = new ModeloVales();
            mdl.setIDVALE(rs.getInt(1));
            mdl.setFECHA(rs.getString(2));
            mdl.setDESCRIPCION(rs.getString(3));
            mdl.setTIPO(rs.getString(4));
            mdl.setMONTO(rs.getFloat(5));
            mdl.setESTADO(rs.getBoolean(6));

            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloVales mdl = (ModeloVales)model;
        sql = "INSERT INTO "+TABLA+" (IDVALE, FECHA, DESCRIPCION,TIPO,MONTO,ESTADO)"
                + "VALUES (" + 
                mdl.getIDVALE() + ",'" + 
                mdl.getFECHA() + "','" +
                mdl.getDESCRIPCION() + "','" +
                mdl.getTIPO() + "'," +
                mdl.getMONTO() + "," +
                mdl.getESTADO() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloVales pto = (ModeloVales)model;
        sql = "update "+TABLA+" set "
                + " FECHA = '" + pto.getFECHA()
                + "' , DESCRIPCION = '" + pto.getDESCRIPCION()
                + "' , TIPO = '" + pto.getTIPO()
                + "' , MONTO = " + pto.getMONTO()
                + " , ESTADO = " + pto.getESTADO()
                + " where IDVALE = " + pto.getIDVALE() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloVales pto = (ModeloVales)model;
        sql=("delete from "+TABLA+" where IDVALE =" + pto.getIDVALE() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloVales getRegistrosID(int ID) throws Exception
    {
        sql = "SELECT * FROM "+TABLA+" WHERE IDVALE = "+ID+"";
        rs = mane.consultar(sql);
        ModeloVales mdl = null;
        while(rs.next())
        {
            mdl = new ModeloVales();
            mdl.setIDVALE(rs.getInt(1));
            mdl.setFECHA(rs.getString(2));
            mdl.setDESCRIPCION(rs.getString(3));
            mdl.setTIPO(rs.getString(4));
            mdl.setMONTO(rs.getFloat(5));
            mdl.setESTADO(rs.getBoolean(6));

        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public List getRegistros(int ID, String TIPO, boolean ESTADO) throws Exception {
        ArrayList<ModeloVales> lista = new ArrayList<>();
        sql = "SELECT * FROM "+TABLA+"";
        if(!TIPO.equals("TODOS"))
            sql = sql+" WHERE vales.TIPO = '"+TIPO+"' AND vales.ESTADO =  "+ESTADO+" ";
        
        sql = sql +" ORDER BY IDVALE DESC";
        rs = mane.consultar(sql);
        ModeloVales mdl;
        while(rs.next())
        {
            mdl = new ModeloVales();
            mdl.setIDVALE(rs.getInt(1));
            mdl.setFECHA(rs.getString(2));
            mdl.setDESCRIPCION(rs.getString(3));
            mdl.setTIPO(rs.getString(4));
            mdl.setMONTO(rs.getFloat(5));
            mdl.setESTADO(rs.getBoolean(6));
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public float monto(String TIPO, boolean ESTADO) throws Exception
    {
        float a = 0;
        sql = "SELECT sum(monto) FROM "+TABLA+" WHERE TIPO = '"+TIPO+"' AND ESTADO = "+ESTADO+" ";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            a = rs.getFloat(1);            
        }
        mane.limpiarRecursos();
        return a;
    }
    
    
}
