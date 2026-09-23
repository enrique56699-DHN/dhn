/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloKitsCategorias;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlKitsCategorias extends ControlAbstracto
{
    private final String TABLA = "KITSCATEGORIAS";

    public ControlKitsCategorias() throws ClassNotFoundException, SQLException 
    {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloKitsCategorias> lista = new ArrayList<>();
        sql = "SELECT CATEGORIA FROM "+TABLA+" ORDER BY CATEGORIA ASC";
        rs = mane.consultar(sql);
        ModeloKitsCategorias mdl;
        while(rs.next())
        {
            mdl = new ModeloKitsCategorias();
            mdl.setCATEGORIA(rs.getString(1));
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloKitsCategorias mdl = (ModeloKitsCategorias)model;
        sql = "INSERT INTO "+TABLA+" (CATEGORIA)"
            + "VALUES ('" + 
            mdl.getCATEGORIA() + "' )"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloKitsCategorias mdl = (ModeloKitsCategorias)model;
        sql = "update "+TABLA+" set "
                + " CATEGORIA = '" + mdl.getCATEGORIA()
                + "' where CATEGORIA = '" + mdl.getCATEGORIAANT() + "' ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloKitsCategorias mdl = (ModeloKitsCategorias)model;
        sql=("delete from "+TABLA+" where CATEGORIA ='" + mdl.getCATEGORIA() + "'");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();            
        }
     
}
