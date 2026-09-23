/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import java.sql.*;
import java.util.List;

/**
 *
 * @author KIKE FRENCH
 * @param <mdl>
 */
public abstract class ControlAbstracto <mdl> 
{
    public Manejador mane;
    public ResultSet rs;
    public String sql;
    
    public Manejador getManejador() 
    {
        return mane;
    }

    public void setManejador(Manejador mane) 
    {
        this.mane = mane;
    }
    
    public ResultSet getRs() 
    {
        return rs;
    }

    public void setRs(ResultSet rs) 
    {
        this.rs = rs;
    }

    public String getSql() 
    {
        return sql;
    }

    public void setSql(String sql) 
    {
        this.sql = sql;
    }
    
    public abstract List getRegistros()throws Exception;
    public abstract void guardarRegistro(mdl model)throws Exception;
    public abstract void actualizarRegistro(mdl model)throws Exception;
    public abstract void eliminarRegistro(mdl model)throws Exception;


}
