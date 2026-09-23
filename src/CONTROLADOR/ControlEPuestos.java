/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloEPuestos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author win 10
 */
public class ControlEPuestos extends ControlAbstracto
{
    private final String TABLA = "EPUESTOS";

    public ControlEPuestos() throws ClassNotFoundException, SQLException {
         this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloEPuestos> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloEPuestos mdl;
        while(rs.next())
        {
            mdl = new ModeloEPuestos();
            mdl.setIDEPUESTO(rs.getInt(1));
            mdl.setDEPTO(rs.getString(2));
            mdl.setPUESTO(rs.getString(3));
            mdl.setDOC(rs.getString(4));
            mdl.setSUELDO(rs.getFloat(5));

            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloEPuestos pto = (ModeloEPuestos)model;
        sql = "INSERT INTO "+TABLA+" (IDEPUESTO,DEPTO,PUESTO,DOC,SUELDO)"
                + "VALUES (" + 
                pto.getIDEPUESTO() + ",'" + 
                pto.getDEPTO() + "','" +
                pto.getPUESTO() + "','" +
                pto.getDOC() + "'," +                
                pto.getSUELDO() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloEPuestos pto = (ModeloEPuestos)model;
        sql = "update "+TABLA+" set "
                + "DEPTO = '" + pto.getDEPTO()
                + "' , PUESTO = '" + pto.getPUESTO()
                + "' , DOC = '" + pto.getDOC()
                + "' , SUELDO = " + pto.getSUELDO()
                + " where IDEPUESTO=" + pto.getIDEPUESTO() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloEPuestos pto = (ModeloEPuestos)model;
        sql=("delete from "+TABLA+" where IDEPUESTO =" + pto.getIDEPUESTO() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloEPuestos getRegistro(int ID) throws Exception {
        sql = "select * from "+TABLA+" where IDEPUESTO = "+ID;
        rs = mane.consultar(sql);
        ModeloEPuestos mdl = new ModeloEPuestos();
        while(rs.next())
        {   
            mdl.setIDEPUESTO(rs.getInt(1));
            mdl.setDEPTO(rs.getString(2));
            mdl.setPUESTO(rs.getString(3));
            mdl.setDOC(rs.getString(4));
            mdl.setSUELDO(rs.getFloat(5));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public List getRegistros(int IDEPUESTO, String CAMPO, String ATRIBUTO) throws Exception {
        ArrayList<ModeloEPuestos> lista = new ArrayList<>();
        if(CAMPO==null)
            sql = "select DISTINCT * from "+TABLA+" where IDEPUESTO ="+IDEPUESTO+"";
        else if(CAMPO.equals("LISTA"))
            sql = "select DISTINCT * from "+TABLA+" GROUP BY DEPTO";
        else
            sql = "select DISTINCT * from "+TABLA+" where "+CAMPO+" = '"+ATRIBUTO+"' GROUP BY PUESTO";
        
        rs = mane.consultar(sql);
        ModeloEPuestos mdl;  
        while(rs.next())
        {
            mdl = new ModeloEPuestos();
            mdl.setIDEPUESTO(rs.getInt(1));
            mdl.setDEPTO(rs.getString(2));
            mdl.setPUESTO(rs.getString(3));
            mdl.setDOC(rs.getString(4));
            mdl.setSUELDO(rs.getFloat(5));
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public int IDEPUESTO(String DEPARTAMENTO, String CARGO) throws Exception
    {
        int IDEPUESTO = 0;        
        sql = "SELECT IDEPUESTO FROM "+TABLA+" "
                + "WHERE DEPTO = '" + DEPARTAMENTO + "' AND PUESTO = '"+CARGO+"'";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            IDEPUESTO = rs.getInt(1);
        }
        mane.limpiarRecursos();
        
        return IDEPUESTO;
    }
}
