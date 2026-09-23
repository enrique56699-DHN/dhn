/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import MODELO.ModeloCutservices;

/**
 *
 * @author Oscar E Gonzalez G
 */
public class ControlCutservices extends ControlAbstracto
{
    private final String TABLA = "CUTSERVICES";

    public ControlCutservices() throws ClassNotFoundException, SQLException
    {
        this.mane = new Manejador();
    }
   
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloCutservices> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloCutservices mdl;
        while(rs.next())
        {
            mdl = new ModeloCutservices();
            mdl.setFECHA(rs.getString(1));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloCutservices mdl = (ModeloCutservices)model;
        sql = "INSERT INTO "+TABLA+" (FECHA)"
                + "VALUES ('" + 
                mdl.getFECHA() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloCutservices mdl = (ModeloCutservices)model;
        sql = "update "+TABLA+" set "
                + "FECHA='" + mdl.getFECHA()
                + "' where FECHA = '" + mdl.getFECHA() + "' ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloCutservices mdl = (ModeloCutservices)model;
        sql=("delete from "+TABLA+" where FECHA =" + mdl.getFECHA() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
   
}
