/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloBitacora;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlBitacora extends ControlAbstracto
{
    private final String TABLA = "BITACORA";
    
    public ControlBitacora() throws ClassNotFoundException, SQLException
    {
        this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloBitacora> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloBitacora mdl;
        while(rs.next())
        {
            mdl = new ModeloBitacora();
            mdl.setIDBITACORA(rs.getInt(1));
            mdl.setTABLA(rs.getString(2));
            mdl.setREGISTRO(rs.getInt(3));
            mdl.setACCION(rs.getString(4));
            mdl.setIDUSER(rs.getInt(5));
            mdl.setCOMENTARIOS(rs.getString(6));
            mdl.setREFERENCIA(rs.getString(7));
            mdl.setFECHA(rs.getString(8));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloBitacora mdl = (ModeloBitacora)model;
        sql = "INSERT INTO "+TABLA+" (IDBITACORA, TABLA, REGISTRO, ACCION, IDUSER, COMENTARIOS, REFERENCIA, FECHA)"
                + "VALUES (" + 
                mdl.getIDBITACORA() + ",'" +
                mdl.getTABLA() + "'," +
                mdl.getREGISTRO() + ",'" +
                mdl.getACCION() + "'," +
                mdl.getIDUSER() + ",'" +
                mdl.getCOMENTARIOS() + "','" +
                mdl.getREFERENCIA() + "','" +
                mdl.getFECHA() + "' )"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloBitacora mdl = (ModeloBitacora)model;
        sql = "update "+TABLA+" set "
                + "TABLA = '" + mdl.getTABLA()
                + "', REGISTRO = " + mdl.getREGISTRO()
                + " , ACCION = '" + mdl.getACCION()
                + "' , IDUSER = " + mdl.getIDUSER()
                + " , COMENTARIOS = '" + mdl.getCOMENTARIOS()
                + "' , REFERENCIA = '" + mdl.getREFERENCIA()
                + "' , FECHA = '" + mdl.getFECHA()
                + "' where IDBITACORA=" + mdl.getIDBITACORA() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloBitacora mdl = (ModeloBitacora)model;
        sql=("delete from "+TABLA+" where IDBITACORA =" + mdl.getIDBITACORA() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    
    }
    
    public List getRegistros(String TABLA, int REGISTRO) throws Exception {
        ArrayList<ModeloBitacora> lista = new ArrayList<>();
        sql = "SELECT bitacora.FECHA, bitacora.ACCION, usuarios.NOMBRE, bitacora.COMENTARIOS, bitacora.REFERENCIA "
                + "FROM bitacora "
                + "INNER JOIN usuarios ON bitacora.IDUSER = usuarios.IDUSUARIO "
                + "WHERE bitacora.TABLA = '"+TABLA+"' AND bitacora.REGISTRO = "+REGISTRO+" ";
        
        rs = mane.consultar(sql);
        ModeloBitacora mdl;
        while(rs.next())
        {
            mdl = new ModeloBitacora();
            mdl.setFECHA(rs.getString(1));
            mdl.setACCION(rs.getString(2));
            mdl.setUSER(rs.getString(3));
            mdl.setCOMENTARIOS(rs.getString(4));
            mdl.setREFERENCIA(rs.getString(5));            
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
    
}
