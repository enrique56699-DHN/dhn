/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloCortesDetalles;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlCortesDetalles extends ControlAbstracto
{
    private final String TABLA = "CORTESD";
    
    public ControlCortesDetalles() throws ClassNotFoundException, SQLException
    {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloCortesDetalles> lista = new ArrayList<>();
        sql = "select * from "+TABLA+"";
        rs = mane.consultar(sql);
        //rs = mane.consultar1(sql);
        ModeloCortesDetalles datos;
        while(rs.next())
        {
            datos = new ModeloCortesDetalles();
            datos.setIDCORTED(rs.getInt(1));
            datos.setIDCORTE(rs.getInt(2));
            datos.setIDPAGOI(rs.getInt(3));
            datos.setIDPAGOF(rs.getInt(4));
            datos.setIDENTRADAI(rs.getInt(5));
            datos.setIDENTRADAF(rs.getInt(6));
            datos.setIDSALIDAI(rs.getInt(7));
            datos.setIDSALIDAF(rs.getInt(8));
            
            lista.add(datos);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloCortesDetalles datos = (ModeloCortesDetalles)model;
        sql = "INSERT INTO "+TABLA+" (IDCORTED, IDCORTE, IDPAGOI, IDPAGOF, IDENTRADAI, IDENTRADAF, IDSALIDAI, IDSALIDAF)"
                + "VALUES (" + 
                datos.getIDCORTED() + "," +
                datos.getIDCORTE() + "," +
                datos.getIDPAGOI() + "," +
                datos.getIDPAGOF() + "," +
                datos.getIDENTRADAI() + "," +
                datos.getIDENTRADAF() + "," +
                datos.getIDSALIDAI() + "," +
                datos.getIDSALIDAF() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloCortesDetalles datos = (ModeloCortesDetalles)model;
        sql = "update "+TABLA+" set "
                + "IDCORTE=" + datos.getIDCORTE()
                + ", IDPAGOI =" + datos.getIDPAGOI()
                + ", IDPAGOF =" + datos.getIDPAGOF()
                + ", IDENTRADAI =" + datos.getIDENTRADAI()
                + ", IDENTRADAF =" + datos.getIDENTRADAF()
                + ", IDENTRADAI =" + datos.getIDSALIDAI()
                + ", IDSALIDAF =" + datos.getIDSALIDAF()
                + " where IDCORTED =" + datos.getIDCORTED() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloCortesDetalles datos = (ModeloCortesDetalles)model;
        sql=("delete from "+TABLA+" where IDCORTED =" + datos.getIDCORTED() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloCortesDetalles getRegistro(Integer code) throws Exception {
        sql = "select * from "+TABLA+" where IDCORTED = "+code;
        rs = mane.consultar(sql);
        ModeloCortesDetalles datos = new ModeloCortesDetalles();
        while(rs.next())
        {   
            datos.setIDCORTED(rs.getInt(1));
            datos.setIDCORTE(rs.getInt(2));
            datos.setIDPAGOI(rs.getInt(3));
            datos.setIDENTRADAI(rs.getInt(4));
            datos.setIDSALIDAI(rs.getInt(5));

        }
        mane.limpiarRecursos();
        return datos;
    }
    
    public ModeloCortesDetalles getRegistrosID(int IDCORTE) throws Exception {
        sql = "select * from "+TABLA+" where IDCORTE ="+IDCORTE+"";
        rs = mane.consultar(sql);
        ModeloCortesDetalles datos = null;
        while(rs.next())
        {
            datos = new ModeloCortesDetalles();
            datos.setIDCORTED(rs.getInt(1));
            datos.setIDCORTE(rs.getInt(2));
            datos.setIDPAGOI(rs.getInt(3));
            datos.setIDPAGOF(rs.getInt(4));
            datos.setIDENTRADAI(rs.getInt(5));
            datos.setIDENTRADAF(rs.getInt(6));
            datos.setIDSALIDAI(rs.getInt(7));
            datos.setIDSALIDAF(rs.getInt(8));
        }
        mane.limpiarRecursos();
        return datos;
    }
    
    public void guardarRegistroP(int IDPAGO) throws Exception {
        sql = "INSERT INTO "+TABLA+" ( IDPAGO)"
                + "VALUES (" + 
                IDPAGO + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    
}
