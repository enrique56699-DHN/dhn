/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloKits;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlKits extends ControlAbstracto
{
    private final String TABLA = "KITS";

    public ControlKits() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }    

    @Override
    public List getRegistros() throws Exception {
       ArrayList<ModeloKits> lista = new ArrayList<>();
        sql = "SELECT kits.IDKIT, kits.NOMBRE, kits.CATEGORIA, kits.COSTO, kits.PRECIO, kits.FOTO "
                + "FROM "+TABLA+" ORDER BY NOMBRE ASC";
        rs = mane.consultar(sql);
        ModeloKits mdl;
        while(rs.next())
        {
            mdl = new ModeloKits();
            mdl.setIDKIT(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setCATEGORIA(rs.getString(3));
            mdl.setCOSTO(rs.getFloat(4));
            mdl.setPRECIO(rs.getFloat(5));
            mdl.setFOTO(rs.getString(6));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloKits mdl = (ModeloKits)model;
        sql = "INSERT INTO "+TABLA+" (IDKIT, NOMBRE, CATEGORIA, COSTO, PRECIO, FOTO)"
                + "VALUES (" + 
                mdl.getIDKIT() + ",'" +
                mdl.getNOMBRE() + "','" +
                mdl.getCATEGORIA() + "'," +
                mdl.getCOSTO() + "," +
                mdl.getPRECIO() + ",'" +
                mdl.getFOTO() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloKits mdl = (ModeloKits)model;
        sql = "update "+TABLA+" set "
                + "NOMBRE='" + mdl.getNOMBRE()
                + "',CATEGORIA='" + mdl.getCATEGORIA()
                + "',COSTO=" + mdl.getCOSTO()
                + ",PRECIO=" + mdl.getPRECIO()
                + ",FOTO='" + mdl.getFOTO()
                + "' where IDKIT=" + mdl.getIDKIT() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloKits mdl = (ModeloKits)model;
        sql=("delete from "+TABLA+" where IDKIT =" + mdl.getIDKIT() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloKits getRegistrosID(int IDKIT) throws Exception {
        sql = "SELECT kits.IDKIT, kits.NOMBRE, kits.CATEGORIA, kits.COSTO, kits.PRECIO, kits.FOTO "
                + "FROM "+TABLA+" WHERE kits.IDKIT ="+IDKIT+"";
        
        rs = mane.consultar(sql);
        ModeloKits mdl = null;
        while(rs.next())
        {
            mdl = new ModeloKits();
            mdl.setIDKIT(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setCATEGORIA(rs.getString(3));
            mdl.setCOSTO(rs.getFloat(4));
            mdl.setPRECIO(rs.getFloat(5));
            mdl.setFOTO(rs.getString(6));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public ModeloKits getRegistro(String NOMBRE, String CATEGORIA) throws Exception 
    {       
        sql = "SELECT kits.IDKIT, kits.NOMBRE, kits.CATEGORIA, kits.COSTO, kits.PRECIO, kits.FOTO "
                + "FROM kits WHERE kits.NOMBRE = '"+NOMBRE+"' AND kits.CATEGORIA = '"+CATEGORIA+"' ";        
        rs = mane.consultar(sql);
        ModeloKits mdl = null;
        while(rs.next())
        {
            mdl = new ModeloKits();
            mdl.setIDKIT(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setCATEGORIA(rs.getString(3));
            mdl.setCOSTO(rs.getFloat(4));
            mdl.setPRECIO(rs.getFloat(5));
            mdl.setFOTO(rs.getString(6));
            
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public List getCATEGORIAS() throws Exception {
        ArrayList<ModeloKits> lista = new ArrayList<>();
        sql = "SELECT DISTINCT CATEGORIA FROM "+TABLA+" ORDER BY CATEGORIA ASC";
        rs = mane.consultar(sql);
        ModeloKits mdl;
        while(rs.next())
        {
            mdl = new ModeloKits();
            mdl.setCATEGORIA(rs.getString(1));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public List getNOMBRES(String CATEGORIA) throws Exception {
        ArrayList<ModeloKits> lista = new ArrayList<>();
        sql = "SELECT DISTINCT kits.NOMBRE FROM "+TABLA+" WHERE kits.CATEGORIA ='"+CATEGORIA+"' ORDER BY NOMBRE ASC";
        rs = mane.consultar(sql);
        ModeloKits mdl;
        while(rs.next())
        {
            mdl = new ModeloKits();
            mdl.setNOMBRE(rs.getString(1));
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
}
