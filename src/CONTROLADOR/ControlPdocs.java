/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloPdocs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author win 10
 */
public class ControlPdocs extends ControlAbstracto
{
    private final String TABLA = "PDOCS";
    
    public ControlPdocs() throws ClassNotFoundException, SQLException
    {
        this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloPdocs> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloPdocs mdl;
        while(rs.next())
        {
            mdl = new ModeloPdocs();
            mdl.setIDPDOC(rs.getInt(1));
            mdl.setIDPENDIENTE(rs.getInt(2));
            mdl.setFECHA(rs.getString(3));
            mdl.setDOC(rs.getString(4));            
            mdl.setCOMENTARIOS(rs.getString(5));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloPdocs mdl = (ModeloPdocs)model;
        sql = "INSERT INTO "+TABLA+" (IDPDOC, IDPENDIENTE, FECHA, DOC, COMENTARIOS)"
                + "VALUES (" + 
                mdl.getIDPDOC() + "," +
                mdl.getIDPENDIENTE() + ",'" +
                mdl.getFECHA() + "','" +
                mdl.getDOC() + "','" +
                mdl.getCOMENTARIOS() + "' )"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloPdocs mdl = (ModeloPdocs)model;
        sql = "update "+TABLA+" set "
                + "IDPENDIENTE = " + mdl.getIDPENDIENTE()
                + " , FECHA = '" + mdl.getFECHA()
                + "' , DOC = '" + mdl.getDOC()
                + "' , COMENTARIOS = '" + mdl.getCOMENTARIOS()
                + "' where IDPDOC=" + mdl.getIDPDOC() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloPdocs mdl = (ModeloPdocs)model;
        sql=("delete from "+TABLA+" where IDPDOC =" + mdl.getIDPDOC() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    
    }
    
    public ModeloPdocs getRegistro(String CONSULTA, int ID) throws Exception {
        sql = "SELECT * "
                + "FROM "+TABLA+" WHERE "+CONSULTA+" = "+ID+"";
        /*switch(CONSULTA)
        {
            case "IDPDOC":
                sql = sql+" WHERE "+CONSULTA+" = "+ID+" ";
                break;
            case "IDPENDIENTE":
                sql = sql+" WHERE "+CONSULTA+" = "+ID+" ";
                break;    
            default:
                break;
        }*/
        
        rs = mane.consultar(sql);
        ModeloPdocs mdl = new ModeloPdocs();
        while(rs.next())
        {   
            mdl.setIDPDOC(rs.getInt(1));
            mdl.setIDPENDIENTE(rs.getInt(2));
            mdl.setFECHA(rs.getString(3));
            mdl.setDOC(rs.getString(4));
            mdl.setCOMENTARIOS(rs.getString(5));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public List getRegistros(String CONSULTA, int ID) throws Exception {
        ArrayList<ModeloPdocs> lista = new ArrayList<>();
        sql = "SELECT * "
                + "FROM "+TABLA+" WHERE "+CONSULTA+" = "+ID+"";
        /*switch(consulta)
        {
            case 0:
                sql = sql+" WHERE pdocs.IDPDOC = "+ID+" ";
                break;
            case 1:
                sql = sql+" WHERE pdocs.IDPENDIENTE = "+ID+" ";
                break;    
            default:
                break;
        }*/
        
        rs = mane.consultar(sql);
        ModeloPdocs mdl;
        while(rs.next())
        {
            mdl = new ModeloPdocs();
            mdl.setIDPDOC(rs.getInt(1));
            mdl.setIDPENDIENTE(rs.getInt(2));
            mdl.setFECHA(rs.getString(3));
            mdl.setDOC(rs.getString(4));
            mdl.setCOMENTARIOS(rs.getString(5));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
    
    
}
