/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloPrestaciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ControlPrestaciones extends ControlAbstracto
{
    private final String TABLA = "PRESTACIONES";
    
    public ControlPrestaciones() throws ClassNotFoundException, SQLException {
         this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloPrestaciones> lista = new ArrayList<>();
        sql = "SELECT * FROM "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloPrestaciones mdl;
        while(rs.next())
        {
            mdl = new ModeloPrestaciones();
            mdl.setIDPRESTACION(rs.getInt(1));
            mdl.setCONCEPTO(rs.getString(2));
            mdl.setPERIODO(rs.getInt(3));
            mdl.setFINI(rs.getString(4));
            mdl.setFFIN(rs.getString(5));
            mdl.setESTADO(rs.getString(6));
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloPrestaciones mdl = (ModeloPrestaciones)model;
        sql = "INSERT INTO "+TABLA+" (IDPRESTACION, CONCEPTO, PERIODO, FINI, FFIN, ESTADO)"
            + "VALUES (" + 
            mdl.getIDPRESTACION() + ",'" +
            mdl.getCONCEPTO() + "'," +
            mdl.getPERIODO() + ",'" + 
            mdl.getFINI() + "','" +   
            mdl.getFFIN() + "','" +       
            mdl.getESTADO() + "' )"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloPrestaciones mdl = (ModeloPrestaciones)model;
        sql = "update "+TABLA+" set "
                + " CONCEPTO = '" + mdl.getCONCEPTO()
                + "' , PERIODO = " + mdl.getPERIODO()
                + " , FINI = '" + mdl.getFINI()
                + "' , FFIN = '" + mdl.getFFIN()
                + "' , ESTADO = '" + mdl.getESTADO()
                + "' where IDPRESTACION = " + mdl.getIDPRESTACION() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloPrestaciones mdl = (ModeloPrestaciones)model;
        sql=("delete from "+TABLA+" where IDPRESTACION =" + mdl.getIDPRESTACION() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public List getRegistros(String CONCEPTO) throws Exception {
        ArrayList<ModeloPrestaciones> lista = new ArrayList<>();
        sql = "SELECT prestaciones.IDPRESTACION, prestaciones.PERIODO, prestaciones.FINI, prestaciones.FFIN, prestaciones.ESTADO FROM prestaciones WHERE prestaciones.CONCEPTO = '"+CONCEPTO+"'";
        rs = mane.consultar(sql);
        ModeloPrestaciones mdl;
        while(rs.next())
        {
            mdl = new ModeloPrestaciones();
            mdl.setIDPRESTACION(rs.getInt(1));
            mdl.setPERIODO(rs.getInt(2));
            mdl.setFINI(rs.getString(3));
            mdl.setFFIN(rs.getString(4));
            mdl.setESTADO(rs.getString(5));
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public static Integer Registro(String CONCEPTO, String ORG) throws ClassNotFoundException, SQLException, Exception 
    {
        int IDNUEVO;
     
               Manejador obj = new Manejador();
               String sql;
               ResultSet rs;
               sql=("SELECT prestaciones.PERIODO FROM prestaciones WHERE prestaciones.CONCEPTO = '"+CONCEPTO+"' ORDER BY prestaciones.PERIODO "+ORG);
        try {
            rs= obj.consultar(sql);
            if(rs.next())
               {
                   IDNUEVO=rs.getInt(1)+1;
                   return IDNUEVO;
               }
        } catch (Exception ex) {
            Logger.getLogger(ControlPrestaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 1;   
    }
    
    public void actualizarSELECCION(int IDPRESTACION, String ESTADO, String CONCEPTO) throws Exception
    {
        if(IDPRESTACION ==0)
        {
            sql = "update "+TABLA+" set "
                + " ESTADO='" + ESTADO
                + "' where CONCEPTO = '" + CONCEPTO + "' ";
        }
        else
            sql = "update "+TABLA+" set "
                + " ESTADO='" + ESTADO
                + "' where CONCEPTO = '" + CONCEPTO + "' AND IDPRESTACION = "+IDPRESTACION+" ";            
         
        
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloPrestaciones getRegistrosID(int IDPRESTACION) throws Exception {
        sql = "select * from "+TABLA+" where IDPRESTACION ="+IDPRESTACION+"";
        rs = mane.consultar(sql);
        ModeloPrestaciones mdl = null;
        while(rs.next())
        {
            mdl = new ModeloPrestaciones();
            mdl.setIDPRESTACION(rs.getInt(1));
            mdl.setCONCEPTO(rs.getString(2));
            mdl.setPERIODO(rs.getInt(3));
            mdl.setFINI(rs.getString(4));
            mdl.setFFIN(rs.getString(5));
            mdl.setESTADO(rs.getString(6));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public ModeloPrestaciones Registros(String CONCEPTO, String ORG) throws Exception {
        sql = "SELECT prestaciones.PERIODO, prestaciones.FINI, prestaciones.FFIN, prestaciones.IDPRESTACION FROM prestaciones WHERE prestaciones.CONCEPTO ='"+CONCEPTO+"' AND prestaciones.ESTADO ='SELECCIONADO' ";
        
        rs = mane.consultar(sql);
        ModeloPrestaciones mdl = null;
        while(rs.next())
        {
            mdl = new ModeloPrestaciones();
            mdl.setPERIODO(rs.getInt(1));
            mdl.setFINI(rs.getString(2));
            mdl.setFFIN(rs.getString(3));
            mdl.setIDPRESTACION(rs.getInt(4));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public static Integer idprestacion(String CONCEPTO) throws ClassNotFoundException, SQLException, Exception 
    {
        int IDNUEVO;
     
               Manejador obj = new Manejador();
               String sql;
               ResultSet rs;
               sql=("SELECT prestaciones.IDPRESTACION FROM prestaciones WHERE prestaciones.CONCEPTO = '"+CONCEPTO+"' AND prestaciones.ESTADO = 'SELECCIONADO' ");
        try {
            rs= obj.consultar(sql);
            if(rs.next())
               {
                   IDNUEVO=rs.getInt(1);
                   return IDNUEVO;
               }
        } catch (Exception ex) {
            Logger.getLogger(ControlPrestaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.limpiarRecursos();
        return 1;   
    }
    
    public ModeloPrestaciones VacacionPeriodo(String CONCEPTO, int PERIODO) throws Exception {
        sql = "SELECT prestaciones.FINI, prestaciones.FFIN FROM prestaciones WHERE prestaciones.CONCEPTO ='"+CONCEPTO+"' AND prestaciones.PERIODO ="+PERIODO+" ";
     
        rs = mane.consultar(sql);
        ModeloPrestaciones mdl = null;
        while(rs.next())
        {
            mdl = new ModeloPrestaciones();
            mdl.setFINI(rs.getString(1));
            mdl.setFFIN(rs.getString(2));
        }
        mane.limpiarRecursos();
        return mdl;
    }
}
