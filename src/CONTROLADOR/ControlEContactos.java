/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloEContactos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */

public class ControlEContactos extends ControlAbstracto
{
    private final String TABLA = "ECONTACTOS";
    
    public ControlEContactos() throws ClassNotFoundException, SQLException 
    {
        this.mane = new Manejador();
    }
        
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloEContactos> lista = new ArrayList<>();
        sql = "select * from "+TABLA+"";
        rs = mane.consultar(sql);
        ModeloEContactos mdl;
        while(rs.next())
        {
            mdl = new ModeloEContactos();
            mdl.setIDECONTACTO(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setNOMBRE(rs.getString(3));
            mdl.setTEL(rs.getString(4));       
            mdl.setEMAIL(rs.getString(5));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception 
    {
        ModeloEContactos mdl = (ModeloEContactos)model;
        sql = "INSERT INTO "+TABLA+" (IDECONTACTO, IDEMPLEADO, NOMBRE, TEL, EMAIL)"
                + "VALUES (" + 
                mdl.getIDECONTACTO() + "," +
                mdl.getIDEMPLEADO() + ",'" +
                mdl.getNOMBRE() + "','" +
                mdl.getTEL() + "','" +
                mdl.getEMAIL() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception 
    {
        ModeloEContactos mdl = (ModeloEContactos)model;
        sql = "update "+TABLA+" set "
                + "IDEMPLEADO =" + mdl.getIDEMPLEADO()
                + ", NOMBRE = '" + mdl.getNOMBRE()
                + "', TEL = '" + mdl.getTEL()
                + "', EMAIL = '" + mdl.getEMAIL()
                + "' where IDECONTATO=" + mdl.getIDEMPLEADO() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception 
    {
        ModeloEContactos mdl = (ModeloEContactos)model;
        sql=("delete from "+TABLA+" where IDECONTACTO =" + mdl.getIDECONTACTO() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
        
    public ModeloEContactos getRegistrosID(int ID) throws Exception 
    {
        sql = "select * from "+TABLA+" where IDEMPLEADO ="+ID+"";
        rs = mane.consultar(sql);
        ModeloEContactos mdl = null;
        while(rs.next())
        {
            mdl = new ModeloEContactos();
            mdl.setIDECONTACTO(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setNOMBRE(rs.getString(3));
            mdl.setTEL(rs.getString(4));       
            mdl.setEMAIL(rs.getString(5));

        }
        mane.limpiarRecursos();
        return mdl;
    }

    public List getRegistros(int ID) throws Exception {
        ArrayList<ModeloEContactos> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" "
                + "WHERE IDEMPLEADO = "+ID+" ";
        rs = mane.consultar(sql);
        ModeloEContactos mdl;
        while(rs.next())
        {
            mdl = new ModeloEContactos();
            mdl.setIDECONTACTO(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setNOMBRE(rs.getString(3));
            mdl.setTEL(rs.getString(4));       
            mdl.setEMAIL(rs.getString(5));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
}
