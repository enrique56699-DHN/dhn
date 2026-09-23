/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloEDetalles;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlEDetalles extends ControlAbstracto
{
    private final String TABLA = "EDETALLES";
    
    public ControlEDetalles() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloEDetalles> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        
        ModeloEDetalles mdl;
        while(rs.next())
        {
            mdl = new ModeloEDetalles();
            mdl.setIDEDETALLE(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setPINI(rs.getString(3));
            mdl.setPFIN(rs.getString(4));
            mdl.setVPAGADAS(rs.getBoolean(5));
            mdl.setAPAGADOS(rs.getBoolean(6));
            mdl.setPERIODOV(rs.getInt(7));
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloEDetalles mdl = (ModeloEDetalles)model;
            sql = "INSERT INTO "+TABLA+" (IDEDETALLE,IDEMPLEADO,PINI,PFIN,VPAGADAS,APAGADOS, PERIODOV)"
                + "VALUES (" + 
                mdl.getIDEDETALLE() + "," + 
                mdl.getIDEMPLEADO() + ",'" +
                mdl.getPINI() + "','" +
                mdl.getPFIN() + "'," +
                mdl.getVPAGADAS() + "," +
                mdl.getAPAGADOS() + "," +    
                mdl.getPERIODOV() + ") "; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloEDetalles mdl = (ModeloEDetalles)model;
            sql = "update "+TABLA+" set "
                + " IDEMPLEADO = " + mdl.getIDEMPLEADO()
                + " , PINI = '" + mdl.getPINI()
                + "' , PFIN = '" + mdl.getPFIN()   
                + "' , VPAGADAS = " + mdl.getVPAGADAS()
                + " , APAGADOS = " + mdl.getAPAGADOS()
                + " , PERIODOV = " + mdl.getPERIODOV()    
                + " where IDEDETALLE = " + mdl.getIDEDETALLE() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloEDetalles mdl = (ModeloEDetalles)model;
        sql=("delet from "+TABLA+" where IDEDETALLE =" + mdl.getIDEDETALLE() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
 
    public void pagar(int IDEMPLEADO, boolean APAGADOS) throws Exception
    {
        sql = "update "+TABLA+" set "
                + " APAGADOS = " + APAGADOS
                + " where IDEMPLEADO =" + IDEMPLEADO + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
       
    public ModeloEDetalles getRegistrosID(int IDEMPLEADO) throws Exception {
        sql = "select * from "+TABLA+" WHERE IDEMPLEADO = "+IDEMPLEADO+"";
        rs = mane.consultar(sql);        
        ModeloEDetalles mdl = null;
        while(rs.next())
        {
            mdl = new ModeloEDetalles();
            mdl.setIDEDETALLE(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setPINI(rs.getString(3));
            mdl.setPFIN(rs.getString(4));
            mdl.setVPAGADAS(rs.getBoolean(5));
            mdl.setAPAGADOS(rs.getBoolean(6));
            mdl.setPERIODOV(rs.getInt(7));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public ModeloEDetalles periodoVID(int IDEMPLEADO) throws Exception {
        sql = "select periodov from "+TABLA+" WHERE IDEMPLEADO = "+IDEMPLEADO+"";
        rs = mane.consultar(sql);        
        ModeloEDetalles mdl = null;
        while(rs.next())
        {
            mdl = new ModeloEDetalles();
            mdl.setPERIODOV(rs.getInt(1));            
        }
        mane.limpiarRecursos();
        return mdl;
    }
}
