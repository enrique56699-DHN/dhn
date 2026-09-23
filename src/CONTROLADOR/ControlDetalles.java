/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloDetalles;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlDetalles extends ControlAbstracto
{
    private final String TABLA = "DETALLES";
    
    public ControlDetalles() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }
   
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloDetalles> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloDetalles x;
        while(rs.next())
        {
            x = new ModeloDetalles();
            x.setIDDETALLE(rs.getInt(1));
            x.setDIASPRE(rs.getInt(2));
            x.setCONCEPTO(rs.getString(3));
            x.setSMINIMO(rs.getFloat(4));
            x.setTC(rs.getFloat(5));
            x.setIVA(rs.getFloat(6));
            x.setGP(rs.getInt(7));
            x.setGI(rs.getInt(8));
            
            lista.add(x);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloDetalles x = (ModeloDetalles)model;
        sql = "INSERT INTO "+TABLA+" (IDDETALLE, DIASPRE, CONCEPTO, SMINIMO, TC, IVA, GP, GI)"
                + "VALUES (" + 
                x.getIDDETALLE() + "," + 
                x.getDIASPRE() + ",'" +
                x.getCONCEPTO() + "' ," +
                x.getSMINIMO() + " ," +
                x.getTC() + " ," +
                x.getIVA() + " ," +
                x.getGP() + " ," +
                x.getGI() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloDetalles pto = (ModeloDetalles)model;
        sql = "update "+TABLA+" set "
                + " DIASPRE = " + pto.getDIASPRE()
                + " , CONCEPTO = '" + pto.getCONCEPTO()
                + "' , SMINIMO = " + pto.getSMINIMO()
                + " , TC = " + pto.getTC()
                + " , IVA = " + pto.getIVA()
                + " , GP = " + pto.getGP()
                + " , GI = " + pto.getGI()
                + " where IDDETALLE = " + pto.getIDDETALLE() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloDetalles pto = (ModeloDetalles)model;
        sql=("delete from DETALLES where IDDETALLE =" + pto.getIDDETALLE() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloDetalles getRegistrosD() throws Exception {
        sql = "select * from "+TABLA+"";
        rs = mane.consultar(sql);
        ModeloDetalles x = null;
        while(rs.next())
        {
            x = new ModeloDetalles();
            x.setIDDETALLE(rs.getInt(1));
            x.setDIASPRE(rs.getInt(2));
            x.setCONCEPTO(rs.getString(3));
            x.setSMINIMO(rs.getFloat(4));
            x.setTC(rs.getFloat(5));
            x.setIVA(rs.getFloat(6));
            x.setGP(rs.getInt(7));
            x.setGI(rs.getInt(8));
            
        }
        mane.limpiarRecursos();
        return x;
    }
    
    public ModeloDetalles getTCIVA() throws Exception {
        sql = "select TC, IVA, GP, GI from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloDetalles x = null;
        while(rs.next())
        {
            x = new ModeloDetalles();
            x.setTC(rs.getFloat(1));
            x.setIVA(rs.getFloat(2));
            x.setGP(rs.getInt(3));
            x.setGI(rs.getInt(4));
            
        }
        mane.limpiarRecursos();
        return x;
    }
    
    public void actualizarTC(float tc) throws Exception 
    {        
        sql = "update "+TABLA+" set TC = " +tc+" ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
}
