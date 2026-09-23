/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloMovD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlMovD extends ControlAbstracto
{
    private final String TABLA = "MOVD";
    
    public ControlMovD() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloMovD> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" WHERE MOVIMIENTO = 'NO IDENTIFICADO'";
        //sql = "SELECT idmovd,ref,fac,cotejado FROM "+TABLA+" WHERE movd.COTEJADO = false";
        //sql = "SELECT idmovd,ref,fac,cotejado FROM "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloMovD mdl;
        while(rs.next())
        {
            mdl = new ModeloMovD();
            mdl.setIDMOVD(rs.getInt(1));
            mdl.setMOVIMIENTO(rs.getString(2));
            mdl.setIDMOVIMIENTO(rs.getInt(3));
            mdl.setREF(rs.getString(4));            
            mdl.setFAC(rs.getString(5));
            mdl.setCOTEJADO(rs.getBoolean(6));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloMovD mdl = (ModeloMovD)model;
        sql = "INSERT INTO "+TABLA+" (IDMOVD, MOVIMIENTO, IDMOVIMIENTO, REF, COTEJADO, FAC)"
                + "VALUES (" + 
                mdl.getIDMOVD() + ",'" +
                mdl.getMOVIMIENTO() + "'," +
                mdl.getIDMOVIMIENTO() + ",'" +
                mdl.getREF() + "'," +
                mdl.getCOTEJADO() + ",'" +
                mdl.getFAC() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloMovD mdl = (ModeloMovD)model;
        sql = "update "+TABLA+" set "
                + "MOVIMIENTO = '" + mdl.getMOVIMIENTO()
                + "',IDMOVIMIENTO = " + mdl.getIDMOVIMIENTO()
                + ",REF = '" + mdl.getREF()
                + "',COTEJADO = " + mdl.getCOTEJADO()
                + ",FAC = '" + mdl.getFAC()
                + "' where IDMOVD=" + mdl.getIDMOVD() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloMovD mdl = (ModeloMovD)model;
        sql=("delete from "+TABLA+" where IDMOVD =" + mdl.getIDMOVD() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloMovD getRegistros(int IDMOVD) throws Exception {
        sql = "select * from "+TABLA+" WHERE IDMOVD = "+IDMOVD+" ";
        rs = mane.consultar(sql);
        ModeloMovD mdl = null;
        while(rs.next())
        {
            mdl = new ModeloMovD();
            mdl.setIDMOVD(rs.getInt(1));
            mdl.setMOVIMIENTO(rs.getString(2));
            mdl.setIDMOVIMIENTO(rs.getInt(3));
            mdl.setREF(rs.getString(4));
            mdl.setCOTEJADO(rs.getBoolean(5));
            mdl.setFAC(rs.getString(6));

        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public void updateFAC(int IDMOVD, String FAC) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "FAC = '" + FAC
                + "' where IDMOVD =" + IDMOVD + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public void updateCOT(int IDMOVD, boolean COTEJADO) throws Exception
    {
        sql = "update "+TABLA+" set "
                + " COTEJADO = " + COTEJADO
                + " where IDMOVD = " + IDMOVD + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public List ref() throws SQLException, Exception
    {
        ArrayList<Object> lista = new ArrayList<>();        
        sql=("SELECT ref FROM movd WHERE COTEJADO = false ORDER BY idmovd ASC");
        rs = mane.consultar(sql);
        while(rs.next())
        {
            lista.add(rs.getString(1));
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public int idmovimiento(String conceptoA) throws Exception
    {
        int idmov = 0;
        sql=("SELECT idmovimiento FROM movd WHERE COTEJADO = false AND ref = '"+conceptoA+"' ");
        rs = mane.consultar(sql);
        while(rs.next())
        {
            idmov = rs.getInt(1);
        }        
        return idmov;
        
    }
}
