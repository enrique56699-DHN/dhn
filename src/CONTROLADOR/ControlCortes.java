/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloCortes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlCortes extends ControlAbstracto
{
    private final String TABLA = "CORTES";
    
    public ControlCortes() throws ClassNotFoundException, SQLException
    {
        this.mane = new Manejador();
    }        
   
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloCortes> lista = new ArrayList<>();
        sql = "SELECT cortes.IDCORTE, cortes.MONTOINI, cortes.FINI, cortes.FFIN, cortes.MONTOFIN, cortesd.IDPAGOI, cortesd.IDPAGOF, cortesd.IDENTRADAI, cortesd.IDENTRADAF, cortesd.IDSALIDAI, cortesd.IDSALIDAF, cortes.IDCAJA FROM cortes INNER JOIN cortesd ON cortesd.IDCORTE = cortes.IDCORTE ORDER BY IDCORTE DESC";
        rs = mane.consultar(sql);
        //rs = mane.consultar1(sql);
        ModeloCortes mdl;
        while(rs.next())
        {
            mdl = new ModeloCortes();
            mdl.setIDCORTE(rs.getInt(1));
            mdl.setMONTOINI(rs.getFloat(2));
            mdl.setFINI(rs.getString(3));
            mdl.setFFIN(rs.getString(4));
            mdl.setMONTOFIN(rs.getFloat(5));
            mdl.setIDPAGOI(rs.getInt(6));
            mdl.setIDPAGOF(rs.getInt(7));
            mdl.setIDENTRADAI(rs.getInt(8));
            mdl.setIDENTRADAF(rs.getInt(9));
            mdl.setIDSALIDAI(rs.getInt(10));
            mdl.setIDSALIDAF(rs.getInt(11));
            mdl.setIDCAJA(rs.getInt(12));
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloCortes datos = (ModeloCortes)model;
        sql = "INSERT INTO "+TABLA+" (IDCORTE, MONTOINI, FINI, FFIN, MONTOFIN, IDCAJA)"
                + "VALUES (" + 
                datos.getIDCORTE() + "," +
                datos.getMONTOINI() + ",'" +
                datos.getFINI() + "','" +
                datos.getFFIN() + "'," +
                datos.getMONTOFIN() + "," +
                datos.getIDCAJA() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloCortes pto = (ModeloCortes)model;
        sql = "update "+TABLA+" set "
                + "FINI = '" + pto.getFINI()
                + "MONTOINI = " + pto.getMONTOINI()
                + ", FFIN = '" + pto.getFFIN()
                + "', MONTOFIN = " + pto.getMONTOFIN()
                + ", IDCAJA = " + pto.getIDCAJA()
                + " where IDCORTE=" + pto.getIDCORTE() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloCortes datos = (ModeloCortes)model;
        sql=("delete from "+TABLA+" where IDCORTE =" + datos.getIDCORTE() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloCortes getRegistro(Integer code) throws Exception {
        sql = "select * from "+TABLA+" where IDCORTE = "+code;
        rs = mane.consultar(sql);
        ModeloCortes datos = new ModeloCortes();
        while(rs.next())
        {   
            datos.setIDCORTE(rs.getInt(1));
            datos.setMONTOINI(2);
            datos.setFINI(rs.getString(3));
            datos.setFFIN(rs.getString(4));
            datos.setMONTOFIN(rs.getFloat(5));

        }
        mane.limpiarRecursos();
        return datos;
    }
    
    public ModeloCortes getRegistrosID(int ID) throws Exception {
        sql = "select * from "+TABLA+" where IDCORTE ="+ID+" ";
        rs = mane.consultar(sql);
        ModeloCortes datos = null;
        while(rs.next())
        {
            datos = new ModeloCortes();
            datos.setIDCORTE(rs.getInt(1));
            datos.setMONTOINI(rs.getFloat(2));
            datos.setFINI(rs.getString(3));
            datos.setFFIN(rs.getString(4));
            datos.setMONTOFIN(rs.getFloat(5));
            datos.setIDCAJA(rs.getInt(6));
        }
        mane.limpiarRecursos();
        return datos;
    }
    
    public int CorteCNuevo(int ID) throws Exception
    {
        int a = 0;
        sql = "select FFIN from "+TABLA+" where IDCORTE ="+ID+"";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            String ffin =rs.getString(1);
            if(ffin.equals(""))
            {
                a=0;
            }
            else
            {
                a=1;
            }
        }
        mane.limpiarRecursos();
        return a;
    }
    
    public float monto() throws Exception
    {
        float a = 0;
        sql = "SELECT montofin FROM "+TABLA+" WHERE idcaja = 1 ORDER BY idcorte DESC LIMIT 1";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            a = rs.getFloat(1);            
        }
        mane.limpiarRecursos();
        return a;
    }
}
