/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloProveedores;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author win 10
 */
public class ControlProveedores extends ControlAbstracto
{
    private final String TABLA = "PROVEEDORES";
    
    public ControlProveedores() throws ClassNotFoundException, SQLException
    {
        this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloProveedores> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloProveedores mdl;
        while(rs.next())
        {
            mdl = new ModeloProveedores();
            mdl.setIDPROVEEDOR(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setTEL1(rs.getString(3));
            mdl.setTEL2(rs.getString(4));
            mdl.setREFERENCIA(rs.getString(5));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloProveedores mdl = (ModeloProveedores)model;
        sql = "INSERT INTO "+TABLA+" (IDPROVEEDOR, NOMBRE, TEL1, TEL2, REFERENCIA)"
                + "VALUES (" + 
                mdl.getIDPROVEEDOR() + ",'" +
                mdl.getNOMBRE() + "','" +
                mdl.getTEL1() + "','" +
                mdl.getTEL2() + "','" +
                mdl.getREFERENCIA() + "' )"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloProveedores mdl = (ModeloProveedores)model;
        sql = "update "+TABLA+" set "
                + "NOMBRE = '" + mdl.getNOMBRE()
                + "' , TEL1 = '" + mdl.getTEL1()
                + "' , TEL2 = '" + mdl.getTEL2()
                + "' , REFERENCIA = '" + mdl.getREFERENCIA()
                + "' where IDPROVEEDOR=" + mdl.getIDPROVEEDOR() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloProveedores mdl = (ModeloProveedores)model;
        sql=("delete from "+TABLA+" where IDPROVEEDOR =" + mdl.getIDPROVEEDOR() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    
    }
    
    public ModeloProveedores getRegistro(String CONSULTA, int ID) throws Exception {
        sql = "SELECT * "
                + "FROM "+TABLA+" ";
        switch(CONSULTA)
        {
            case "IDPROVEEDOR":
                sql = sql + " WHERE "+CONSULTA+" = "+ID+"";
                break;
            case "TODOS":
                //sql = sql+" WHERE pdocs.IDPENDIENTE = "+ID+" ";
                break;    
            default:
                break;
        }
        
        rs = mane.consultar(sql);
        ModeloProveedores mdl = new ModeloProveedores();
        while(rs.next())
        {   
            mdl.setIDPROVEEDOR(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setTEL1(rs.getString(3));
            mdl.setTEL2(rs.getString(4));
            mdl.setREFERENCIA(rs.getString(5));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public List getRegistros(String CONSULTA, int ID) throws Exception {
        ArrayList<ModeloProveedores> lista = new ArrayList<>();
        sql = "SELECT * "
                + "FROM "+TABLA+"";
        switch(CONSULTA)
        {
            case "IDPROVEEDOR":
                sql = sql + " WHERE "+CONSULTA+" = "+ID+"";
                break;
            case "TODOS":
                //sql = sql+" WHERE pdocs.IDPENDIENTE = "+ID+" ";
                break;    
            default:
                break;
        }
        
        rs = mane.consultar(sql);
        ModeloProveedores mdl;
        while(rs.next())
        {
            mdl = new ModeloProveedores();
            mdl.setIDPROVEEDOR(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setTEL1(rs.getString(3));
            mdl.setTEL2(rs.getString(4));
            mdl.setREFERENCIA(rs.getString(5));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
    
    
}
