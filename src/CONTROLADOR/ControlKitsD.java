/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloKitsD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlKitsD extends ControlAbstracto
{
    private final String TABLA = "KITSD";

    public ControlKitsD() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }    

    @Override
    public List getRegistros() throws Exception {
       ArrayList<ModeloKitsD> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloKitsD mdl;
        while(rs.next())
        {
            mdl = new ModeloKitsD();
            mdl.setIDKITD(rs.getInt(1));
            mdl.setIDKIT(rs.getInt(2));
            mdl.setIDPRODUCTO(rs.getInt(3));
            mdl.setCANT(rs.getInt(4));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloKitsD mdl = (ModeloKitsD)model;
        sql = "INSERT INTO "+TABLA+" (IDKITD, IDKIT, IDPRODUCTO, CANTIDAD)"
                + "VALUES (" + 
                mdl.getIDKITD() + "," +
                mdl.getIDKIT() + "," +
                mdl.getIDPRODUCTO() + "," +
                mdl.getCANT() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloKitsD mdl = (ModeloKitsD)model;
        sql = "update "+TABLA+" set "
                + "IDKIT = " + mdl.getIDKIT()
                + ",IDPRODUCTO = " + mdl.getIDPRODUCTO()
                + ",CANTIDAD = " + mdl.getCANT()
                + " where IDKITD=" + mdl.getIDKITD() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloKitsD mdl = (ModeloKitsD)model;
        sql=("delete from "+TABLA+" where IDKITD =" + mdl.getIDKITD() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public List getRegistrosID(int IDKIT) throws Exception {
        ArrayList<ModeloKitsD> lista = new ArrayList<>();
        sql = "SELECT kitsd.IDKITD, kitsd.CANTIDAD, productos.DESCRIPCION, productos.COSTO, productosd.FOTO, productosd.ESTADO "
                + "FROM kitsd "
                + "LEFT JOIN productos "
                + "ON productos.IDPRODUCTO = kitsd.IDPRODUCTO "
                + "LEFT JOIN productosd "
                + "ON productos.IDPRODUCTO = productosd.IDPRODUCTO "
                + "WHERE IDKIT = "+IDKIT+" ";        
        rs = mane.consultar(sql);
        ModeloKitsD mdl;
        while(rs.next())
        {
            mdl = new ModeloKitsD();
            mdl.setIDKITD(rs.getInt(1));
            mdl.setCANT(rs.getInt(2));
            mdl.setDESCRIPCION(rs.getString(3));
            mdl.setCOSTO(rs.getFloat(4));
            mdl.setFOTO(rs.getString(5));
            mdl.setESTADO(rs.getString(6));
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public ModeloKitsD getRegistroID(int IDKITD) throws Exception 
    {       
        sql = "SELECT kitsd.IDKITD, kitsd.IDKIT, kitsd.IDPRODUCTO, kitsd.CANTIDAD, kits.NOMBRE, productos.DESCRIPCION FROM kitsd INNER JOIN kits ON kits.IDKIT = kitsd.IDKIT INNER JOIN productos ON productos.IDPRODUCTO = kitsd.IDPRODUCTO WHERE kitsd.IDKITD = "+IDKITD+" ";        
        rs = mane.consultar(sql);
        ModeloKitsD mdl = null;
        while(rs.next())
        {
            mdl = new ModeloKitsD();
            mdl.setIDKITD(rs.getInt(1));
            mdl.setIDKIT(rs.getInt(2));
            mdl.setIDPRODUCTO(rs.getInt(3));
            mdl.setCANT(rs.getInt(4));
            mdl.setNOMBRE(rs.getString(5));
            mdl.setDESCRIPCION(rs.getString(6));
            
        }
        mane.limpiarRecursos();
        return mdl;
    }
   
    public ModeloKitsD getRegistroIDK(int IDKIT) throws Exception 
    {
        sql = "SELECT *FROM kitsd WHERE IDKIT = "+IDKIT+" ";        
        rs = mane.consultar(sql);
        ModeloKitsD mdl = null;
        while(rs.next())
        {
            mdl = new ModeloKitsD();
            mdl.setIDKITD(rs.getInt(1));
            mdl.setIDKIT(rs.getInt(2));
            mdl.setIDPRODUCTO(rs.getInt(3));
            mdl.setCANT(rs.getInt(4));
            
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public List getRegistros(int IDKIT) throws Exception 
    {
        ArrayList<ModeloKitsD> lista = new ArrayList<>();
        sql = "select IDPRODUCTO, CANTIDAD from "+TABLA+" WHERE IDKIT = "+IDKIT+" ";
        rs = mane.consultar(sql);
        ModeloKitsD mdl;
        while(rs.next())
        {
            mdl = new ModeloKitsD();
            mdl.setIDPRODUCTO(rs.getInt(1));
            mdl.setCANT(rs.getInt(2));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
}
