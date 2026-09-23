/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloProductosD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlProductosD extends ControlAbstracto
{
    private final String TABLA = "PRODUCTOSD";

    public ControlProductosD() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }    

    @Override
    public List getRegistros() throws Exception {
       ArrayList<ModeloProductosD> lista = new ArrayList<>();
        sql = "select productosd.CB, productos.SICAR, productos.CATEGORIA, productos.DESCRIPCION "
                + "from "+TABLA+" "
                + "INNER JOIN productos "
                + "ON productosd.IDPRODUCTO = productos.IDPRODUCTO";
        rs = mane.consultar(sql);
        ModeloProductosD mdl;
        while(rs.next())
        {
            mdl = new ModeloProductosD();
            mdl.setCB(rs.getString(1));
            mdl.setSICAR(rs.getString(2));
            mdl.setCATEGORIA(rs.getString(3));
            mdl.setDESCRIPCION(rs.getString(4));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloProductosD mdl = (ModeloProductosD)model;
        sql = "INSERT INTO "+TABLA+" (CB, IDPRODUCTO)"
                + "VALUES ('" + 
                mdl.getCB() + "'," +
                mdl.getIDPRODUCTO() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
    ModeloProductosD mdl = (ModeloProductosD)model;
            sql = "update "+TABLA+" set "
                + "IDPRODUCTO = " + mdl.getIDPRODUCTO()
                + " where CB='" + mdl.getCB() + "' ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloProductosD mdl = (ModeloProductosD)model;
        sql=("delete from "+TABLA+" where CB =" + mdl.getCB() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public List getRegistrosID(String ARGUMENTO,String ARG) throws Exception {
        ArrayList<ModeloProductosD> lista = new ArrayList<>();
        if(ARGUMENTO.equals("CB"))
            sql = "select productosd.CB, productos.SICAR, productos.CATEGORIA, productos.DESCRIPCION, productosd.IDPRODUCTO, productosd.MARCA,  productosd.PROVEEDOR, productosd.SKU, productosd.CPP, productosd.CPI  "
                    + "from "+TABLA+" "
                    + "INNER JOIN productos "
                    + "ON produtosd.IDPRODUCTO = productos.IDPRODUCTO "
                    + "WHERE productosCB = "+ARG+" ";
        else
            sql = "select productosd.CB, productos.SICAR, productos.CATEGORIA, productos.DESCRIPCION, productosd.IDPRODUCTO, productosd.MARCA,  productosd.PROVEEDOR, productosd.SKU, productosd.CPP, productosd.CPI  "
                    + "from "+TABLA+" "
                    + "INNER JOIN productos "
                    + "ON produtosd.IDPRODUCTO = productos.IDPRODUCTO ";
        rs = mane.consultar(sql);
        ModeloProductosD mdl;
        while(rs.next())
        {
            mdl = new ModeloProductosD();
            mdl.setCB(rs.getString(1));
            mdl.setSICAR(rs.getString(2));
            mdl.setCATEGORIA(rs.getString(3));
            mdl.setDESCRIPCION(rs.getString(4));
            mdl.setIDPRODUCTO(rs.getInt(5));
            mdl.setMARCA(rs.getString(6));
            mdl.setPROVEEDOR(rs.getString(7));
            mdl.setSKU(rs.getString(8));
            mdl.setCPP(rs.getBoolean(9));
            mdl.setCPI(rs.getBoolean(10));
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public ModeloProductosD getRegistroID(String ARGUMENTO,String ARG) throws Exception 
    {   if(ARGUMENTO.equals("CB"))
            sql = "select productosd.CB, productos.SICAR, productos.CATEGORIA, productos.DESCRIPCION, productosd.IDPRODUCTO, productosd.MARCA,  productosd.PROVEEDOR, productosd.SKU, productosd.CPP, productosd.CPI "
                    + "from "+TABLA+" "
                    + "INNER JOIN productos "
                    + "ON produtosd.IDPRODUCTO = productos.IDPRODUCTO "
                    + "WHERE productosCB = "+ARG+" ";
        else
            sql = "select productosd.CB, productos.SICAR, productos.CATEGORIA, productos.DESCRIPCION, productosd.IDPRODUCTO, productosd.MARCA,  productosd.PROVEEDOR, productosd.SKU, productosd.CPP, productosd.CPI "
                    + "from "+TABLA+" "
                    + "INNER JOIN productos "
                    + "ON produtosd.IDPRODUCTO = productos.IDPRODUCTO ";
        rs = mane.consultar(sql);
        ModeloProductosD mdl = null;
        while(rs.next())
        {
            mdl = new ModeloProductosD();
            mdl.setCB(rs.getString(1));
            mdl.setSICAR(rs.getString(2));
            mdl.setCATEGORIA(rs.getString(3));
            mdl.setDESCRIPCION(rs.getString(4));
            mdl.setIDPRODUCTO(rs.getInt(5));
            //mdl.setPP(rs.getFloat(6));
            //mdl.setPI(rs.getFloat(7));
            mdl.setMARCA(rs.getString(6));
            mdl.setPROVEEDOR(rs.getString(7));
            mdl.setSKU(rs.getString(8));
            mdl.setCPP(rs.getBoolean(9));
            mdl.setCPI(rs.getBoolean(10));
            
        }
        mane.limpiarRecursos();
        return mdl;
    }
   
    public void guardarRegistroD(Object model) throws Exception {
        ModeloProductosD mdl = (ModeloProductosD)model;
        sql = "INSERT INTO "+TABLA+" (CB, IDPRODUCTO, CLAVESAT, INVENTARIO, ESTADO, FOTO, MARCA, PROVEEDOR, SKU, CPP, CPI)"
                + "VALUES ('" + 
                mdl.getCB() + "'," +
                mdl.getIDPRODUCTO() + ",'" +
                mdl.getCLAVESAT() + "'," +
                mdl.getINVENTARIO() + ",'" +
                mdl.getESTADO() + "','" +
                mdl.getFOTO() + "','" +
                mdl.getMARCA() + "','" +
                mdl.getPROVEEDOR() + "','" +
                mdl.getSKU() + "'," +
                mdl.getCPP() + "," +
                mdl.getCPI() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public void actualizarRegistroD(Object model) throws Exception {
    ModeloProductosD mdl = (ModeloProductosD)model;
            sql = "update "+TABLA+" set "
                + "CB = '" + mdl.getCB()
                + "', CLAVESAT = '" + mdl.getCLAVESAT()
                + "', INVENTARIO = " + mdl.getINVENTARIO()
                + ", ESTADO = '" + mdl.getESTADO()
                + "', FOTO = '" + mdl.getFOTO()    
                + "', MARCA = '" + mdl.getMARCA()    
                + "', PROVEEDOR = '" + mdl.getPROVEEDOR()    
                + "', SKU = '" + mdl.getSKU()    
                + "', CPP = " + mdl.getCPP()    
                + ", CPI = " + mdl.getCPI()        
                + " where IDPRODUCTO = " + mdl.getIDPRODUCTO() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
}
