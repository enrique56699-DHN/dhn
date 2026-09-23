/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloProductos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlProductos extends ControlAbstracto
{
    private final String TABLA = "PRODUCTOS";
    
    public ControlProductos() throws ClassNotFoundException, SQLException
    {
        this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloProductos> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        //String ESTADO = "ACTIVO";
        /*sql = "SELECT productos.IDPRODUCTO, productos.SICAR, productosd.CLAVESAT,productosd.CB, productos.CATEGORIA, productos.DESCRIPCION, productos.COSTO, productos.MONEDA, productos.IVA, productos.PP, productos.PI, productosd.FOTO "
                + "FROM "+TABLA+" "
                + "LEFT JOIN productosd "
                + "ON productos.IDPRODUCTO = productosd.IDPRODUCTO WHERE productosd.ESTADO = '"+ESTADO+"' ";*/
        rs = mane.consultar(sql);
        ModeloProductos mdl;
        while(rs.next())
        {
            mdl = new ModeloProductos();
            mdl.setIDPRODUCTO(rs.getInt(1));
            mdl.setSICAR(rs.getString(2));
            //mdl.setCLAVESAT(rs.getString(3));
            //mdl.setCB(rs.getString(2));
            mdl.setCATEGORIA(rs.getString(3));
            mdl.setDESCRIPCION(rs.getString(4));
            mdl.setCOSTO(rs.getDouble(5));
            mdl.setMONEDA(rs.getString(6));
            mdl.setIVA(rs.getBoolean(7));
            mdl.setPP(rs.getDouble(8));
            mdl.setPI(rs.getDouble(9));
            mdl.setKIT(rs.getString(10));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloProductos datos = (ModeloProductos)model;
        sql = "INSERT INTO "+TABLA+" (IDPRODUCTO, SICAR, CATEGORIA, DESCRIPCION, COSTO, MONEDA, IVA, PP, PI, KIT)"
                + "VALUES (" + 
                datos.getIDPRODUCTO() + ",'" +
                datos.getSICAR() + "','" +
                datos.getCATEGORIA() + "','" +
                datos.getDESCRIPCION() + "'," +
                datos.getCOSTO() + ",'" +
                datos.getMONEDA() + "'," +
                datos.getIVA() + "," +
                datos.getPP() + "," +
                datos.getPI() + ",'" +
                datos.getKIT() + "' )"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloProductos datos = (ModeloProductos)model;
        sql = "update "+TABLA+" set "
                + "SICAR='" + datos.getSICAR()
                + "',CATEGORIA='" + datos.getCATEGORIA()
                + "',DESCRIPCION='" + datos.getDESCRIPCION()
                + "',COSTO=" + datos.getCOSTO()
                + ",MONEDA='" + datos.getMONEDA()
                + "',IVA=" + datos.getIVA()
                + ",PP=" + datos.getPP()
                + ",PI=" + datos.getPI()
                + ",KIT='" + datos.getKIT()
                + "' where IDPRODUCTO = " + datos.getIDPRODUCTO() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloProductos datos = (ModeloProductos)model;
        sql=("delete from "+TABLA+" where IDPRODUCTO =" + datos.getIDPRODUCTO() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloProductos getRegistrosID(int IDPRODUCTO) throws Exception {
        sql = "select * from "+TABLA+" WHERE IDPRODUCTO = "+IDPRODUCTO+" ";
        rs = mane.consultar(sql);
        ModeloProductos mdl = null;
        while(rs.next())
        {
            mdl = new ModeloProductos();
            mdl.setIDPRODUCTO(rs.getInt(1));
            mdl.setSICAR(rs.getString(2));
            mdl.setCATEGORIA(rs.getString(3));
            mdl.setDESCRIPCION(rs.getString(4));
            mdl.setCOSTO(rs.getDouble(5));
            mdl.setMONEDA(rs.getString(6));
            mdl.setIVA(rs.getBoolean(7));
            mdl.setPP(rs.getDouble(8));
            mdl.setPI(rs.getDouble(9));
            mdl.setKIT(rs.getString(10));
        }
        mane.limpiarRecursos();
        return mdl;
    }    
    
    public List getRegistrosMONEDA(String MONEDA) throws Exception 
    {        
        ArrayList<ModeloProductos> c = new ArrayList<>();
        sql = "SELECT productos.IDPRODUCTO, productos.COSTO, productos.IVA, productos.PP, productos.PI, productosd.CPP, productosd.CPI FROM "
                + ""+TABLA+" "
                + "INNER JOIN productosd "
                + "ON productos.IDPRODUCTO = productosd.IDPRODUCTO "
                + "WHERE productos.MONEDA = '"+MONEDA+"' ";
        rs = mane.consultar(sql);
        ModeloProductos mdl;
        while(rs.next())
        {
            mdl = new ModeloProductos();
            mdl.setIDPRODUCTO(rs.getInt(1));
            mdl.setCOSTO(rs.getDouble(2));
            mdl.setIVA(rs.getBoolean(3));
            mdl.setPP(rs.getDouble(4));
            mdl.setPI(rs.getDouble(5));
            mdl.setCPP(rs.getBoolean(6));
            mdl.setCPI(rs.getBoolean(7));
            c.add(mdl);
        }
        mane.limpiarRecursos();
        return c;
    }
    
    public void actualizarPrecio(double PP, double PI, int IDPRODUCTO) throws Exception
    {
        sql = "UPDATE productos SET "
                + "PP = "+PP
                +", PI = "+PI
                +" where IDPRODUCTO=" + IDPRODUCTO + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();        
    }
    
   public ModeloProductos getRegistrosID(String KIT) throws Exception {
        sql = "select * from "+TABLA+" WHERE KIT = '"+KIT+"' ";
        rs = mane.consultar(sql);
        ModeloProductos mdl = null;
        while(rs.next())
        {
            mdl = new ModeloProductos();
            mdl.setIDPRODUCTO(rs.getInt(1));
            mdl.setSICAR(rs.getString(2));
            mdl.setCATEGORIA(rs.getString(3));
            mdl.setDESCRIPCION(rs.getString(4));
            mdl.setCOSTO(rs.getDouble(5));
            mdl.setMONEDA(rs.getString(6));
            mdl.setIVA(rs.getBoolean(7));
            mdl.setPP(rs.getDouble(8));
            mdl.setPI(rs.getDouble(9));
            mdl.setKIT(rs.getString(10));
            
        }
        mane.limpiarRecursos();
        return mdl;
    }   
   
   public List getRegistrosHVAC(String TECNOLOGIA, String CAPACIDAD, String VOLTAJE, String PRESTACION, String MARCA) throws Exception {
        ArrayList<ModeloProductos> lista = new ArrayList<>();
        sql = "select productos.IDPRODUCTO, productos.DESCRIPCION, productos.PP from "+TABLA+" WHERE productos.CATEGORIA = 'AIRE ACONDICIONADO' AND productos.DESCRIPCION LIKE '%"+VOLTAJE+"%' AND productos.DESCRIPCION LIKE '%"+MARCA+"%' AND productos.DESCRIPCION LIKE '%"+PRESTACION+"%' AND productos.DESCRIPCION LIKE '%"+TECNOLOGIA+"%'AND productos.DESCRIPCION LIKE '%"+CAPACIDAD+"%'";
        rs = mane.consultar(sql);
        ModeloProductos mdl;
        while(rs.next())
        {
            mdl = new ModeloProductos();
            mdl.setIDPRODUCTO(rs.getInt(1));
            mdl.setDESCRIPCION(rs.getString(2));
            mdl.setPP(rs.getDouble(3));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
   
   public List getRegistros(String PRODUCTO, String CATEGORIA) throws Exception {
        ArrayList<ModeloProductos> lista = new ArrayList<>();
        sql = "select productos.IDPRODUCTO, productos.DESCRIPCION, productos.PP from "+TABLA+" WHERE productos.CATEGORIA = '"+CATEGORIA+"' ";
        rs = mane.consultar(sql);
        ModeloProductos mdl;
        while(rs.next())
        {
            mdl = new ModeloProductos();
            mdl.setIDPRODUCTO(rs.getInt(1));
            mdl.setDESCRIPCION(rs.getString(2));
            mdl.setPP(rs.getDouble(3));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
   
   public List getRegistrosCAT() throws Exception {
        ArrayList<ModeloProductos> lista = new ArrayList<>();
            sql = "SELECT DISTINCT categoria FROM "+TABLA+" ORDER BY categoria ASC";
        rs = mane.consultar(sql);
        ModeloProductos mdl;
        while(rs.next())
        {
            mdl = new ModeloProductos();

            mdl.setCATEGORIA(rs.getString(1));
           
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
   
   public List getRegistros(String ESTADO) throws Exception {
        ArrayList<ModeloProductos> lista = new ArrayList<>();           
        sql = "SELECT productos.IDPRODUCTO, productos.SICAR, productosd.CLAVESAT,productosd.CB, productos.CATEGORIA, productos.DESCRIPCION, productos.COSTO, productos.MONEDA, productos.IVA, productos.PP, productos.PI, productosd.FOTO "
                + "FROM "+TABLA+" "
                + "LEFT JOIN productosd "
                + "ON productos.IDPRODUCTO = productosd.IDPRODUCTO ";
        if(!ESTADO.equals("TODOS"))        
            sql = sql + "WHERE productosd.ESTADO = '"+ESTADO+"'";
                                   
        sql=sql + " ORDER BY IDPRODUCTO ASC"; 
        rs = mane.consultar(sql);
        ModeloProductos mdl;
        while(rs.next())
        {
            mdl = new ModeloProductos();
            mdl.setIDPRODUCTO(rs.getInt(1));
            mdl.setSICAR(rs.getString(2));
            mdl.setCLAVESAT(rs.getString(3));
            mdl.setCB(rs.getString(4));
            mdl.setCATEGORIA(rs.getString(5));
            mdl.setDESCRIPCION(rs.getString(6));
            mdl.setCOSTO(rs.getDouble(7));
            mdl.setMONEDA(rs.getString(8));
            mdl.setIVA(rs.getBoolean(9));
            mdl.setPP(rs.getDouble(10));
            mdl.setPI(rs.getDouble(11));
            mdl.setFOTO(rs.getString(12));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
   
   public ModeloProductos getRegistrosIDD(int IDPRODUCTO) throws Exception {
        //sql = "select * from "+TABLA+" WHERE IDPRODUCTO = "+IDPRODUCTO+" ";
        sql = "SELECT productos.IDPRODUCTO, productos.SICAR, productosd.CLAVESAT,productosd.CB, productos.CATEGORIA, productos.DESCRIPCION, productos.COSTO, productos.MONEDA, productos.IVA, productos.PP, productos.PI, productosd.ESTADO, productosd.FOTO, productosd.MARCA,  productosd.PROVEEDOR, productosd.SKU, productosd.CPP, productosd.CPI "
                + "FROM "+TABLA+" "
                + "LEFT JOIN productosd "
                + "ON productos.IDPRODUCTO = productosd.IDPRODUCTO "
                +"WHERE productos.IDPRODUCTO = "+IDPRODUCTO+" ";      
        rs = mane.consultar(sql);
        ModeloProductos mdl = null;
        while(rs.next())
        {
            mdl = new ModeloProductos();
            /*mdl.setIDPRODUCTO(rs.getInt(1));
            mdl.setSICAR(rs.getString(2));
            mdl.setCATEGORIA(rs.getString(3));
            mdl.setDESCRIPCION(rs.getString(4));
            mdl.setCOSTO(rs.getDouble(5));
            mdl.setMONEDA(rs.getString(6));
            mdl.setIVA(rs.getBoolean(7));
            mdl.setPP(rs.getDouble(8));
            mdl.setPI(rs.getDouble(9));
            mdl.setKIT(rs.getString(10));*/
            mdl.setIDPRODUCTO(rs.getInt(1));
            mdl.setSICAR(rs.getString(2));
            mdl.setCLAVESAT(rs.getString(3));
            mdl.setCB(rs.getString(4));
            mdl.setCATEGORIA(rs.getString(5));
            mdl.setDESCRIPCION(rs.getString(6));
            mdl.setCOSTO(rs.getDouble(7));
            mdl.setMONEDA(rs.getString(8));
            mdl.setIVA(rs.getBoolean(9));
            mdl.setPP(rs.getDouble(10));
            mdl.setPI(rs.getDouble(11));
            mdl.setESTADO(rs.getString(12));
            mdl.setFOTO(rs.getString(13));
            mdl.setMARCA(rs.getString(14));
            mdl.setPROVEEDOR(rs.getString(15));
            mdl.setSKU(rs.getString(16));
            mdl.setCPP(rs.getBoolean(17));
            mdl.setCPI(rs.getBoolean(18));
        }
        mane.limpiarRecursos();
        return mdl;
    } 
}
