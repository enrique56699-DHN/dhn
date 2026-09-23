/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloPrivilegios;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlPrivilegios extends ControlAbstracto
{
    private final String TABLA = "PRIVILEGIOS";

    public ControlPrivilegios() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloPrivilegios> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloPrivilegios mdl;
        while(rs.next())
        {
            mdl = new ModeloPrivilegios();
            mdl.setIDPRIVILEGIO(rs.getInt(1));
            mdl.setGRUPO(rs.getString(2));
            mdl.setITEM(rs.getString(3));   
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloPrivilegios mdl = (ModeloPrivilegios)model;
        sql = "INSERT INTO "+TABLA+" (IDPRIVILEGIO, GRUPO, ITEM)"
                + "VALUES (" + 
                mdl.getIDPRIVILEGIO() + ",'" +
                mdl.getGRUPO() + "','" +
                mdl.getITEM() + "' )"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloPrivilegios mdl = (ModeloPrivilegios)model;
        sql = "update "+TABLA+" set "
                + "GRUPO = '" + mdl.getGRUPO()
                + "', ITEM = '" + mdl.getITEM()
                + "' where IDPRIVILEGIO = " + mdl.getIDPRIVILEGIO() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloPrivilegios mdl = (ModeloPrivilegios)model;
        sql=("delete from "+TABLA+" where IDPRIVILEGIO =" + mdl.getIDPRIVILEGIO() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    public ModeloPrivilegios getRegistros(int IDPRIVILEGIO, String GRUPO) throws Exception 
     {
        if(IDPRIVILEGIO == 0)
        {
            sql = "select * from "+TABLA+" WHERE GRUPO = '"+GRUPO+"' ";
        }
        else
            sql = "select * from "+TABLA+" WHERE IDPRIVILEGIO = "+IDPRIVILEGIO+" ";

        rs = mane.consultar(sql);
        ModeloPrivilegios mdl = null;
        while(rs.next())
        {
            mdl = new ModeloPrivilegios();
            mdl.setIDPRIVILEGIO(rs.getInt(1));
            mdl.setGRUPO(rs.getString(2));
            mdl.setITEM(rs.getString(3));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public List getRegistros(String GRUPO) throws Exception {
        ArrayList lista = new ArrayList<>();
        sql = "select ITEM from "+TABLA+" WHERE GRUPO = '"+GRUPO+"' ";
        rs = mane.consultar(sql);
        String item;
        while(rs.next())
        { 
            item = rs.getString(1);
            
            lista.add(item);

        }
        mane.limpiarRecursos();
        return lista;
    }
}
