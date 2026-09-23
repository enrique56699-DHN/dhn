/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloCable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author win 10
 */
public class ControlCable extends ControlAbstracto
{

    private final String TABLA = "CABLE";
    
    public ControlCable() throws ClassNotFoundException, SQLException {
         this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloCable> lista = new ArrayList<>();
        sql = "SELECT * FROM "+TABLA;
        rs = mane.consultar(sql);
        ModeloCable mdl;
        while(rs.next())
        {
            mdl = new ModeloCable();
            mdl.setIDCABLE(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setFECHA(rs.getString(3));
            mdl.setENTREGADO(rs.getDouble(4));
            mdl.setUSADO(rs.getDouble(5));
            mdl.setDEVUELTO(rs.getDouble(6));
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloCable mdl = (ModeloCable)model;
        sql = "INSERT INTO "+TABLA+" (IDCABLE, IDEMPLEADO, FECHA, ENTREGADO, USADO, DEVUELTO)"
            + "VALUES (" + 
            mdl.getIDCABLE() + "," +
            mdl.getIDEMPLEADO() + ",'" +
            mdl.getFECHA() + "'," + 
            mdl.getENTREGADO() + "," +
            mdl.getUSADO() + "," +    
            mdl.getDEVUELTO() + " )"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloCable mdl = (ModeloCable)model;
        sql = "update "+TABLA+" set "
                + " IDEMPLEADO = " + mdl.getIDEMPLEADO()
                + " , FECHA = '" + mdl.getFECHA()
                + "' , ENTREGADO = " + mdl.getENTREGADO()
                + " , USADO = " + mdl.getUSADO()
                + " , DEVUELTO = " + mdl.getDEVUELTO()
                + " where IDCABLE = " + mdl.getIDCABLE() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloCable mdl = (ModeloCable)model;
        sql=("delete from "+TABLA+" where IDCABLE =" + mdl.getIDCABLE() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloCable getRegistro(int IDEMPLEADO, String FECHA) throws Exception {
        sql = "SELECT * FROM "+TABLA+" WHERE IDEMPLEADO = "+IDEMPLEADO+" AND FECHA = '"+FECHA+"' ";
        rs = mane.consultar(sql);
        ModeloCable mdl=new ModeloCable();
        while(rs.next())
        {   
            mdl = new ModeloCable();
            mdl.setIDCABLE(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setFECHA(rs.getString(3));
            mdl.setENTREGADO(rs.getDouble(4));
            mdl.setUSADO(rs.getDouble(5));
            mdl.setDEVUELTO(rs.getDouble(6));
        }
        mane.limpiarRecursos();
        return mdl;
    }
}
