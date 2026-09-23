/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloPrestamos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlPrestamos extends ControlAbstracto
{
    private final String TABLA = "PRESTAMOS";
    
    public ControlPrestamos() throws ClassNotFoundException, SQLException {
         this.mane = new Manejador();
    }    
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloPrestamos> lista = new ArrayList<>();
        sql = "SELECT prestamos.IDPRESTAMO, empleados.NOMBRE, empleados.APELLIDOS, prestamos.FECHA, prestamos.DESCRIPCION, prestamos.MONTO, prestamos.PERIODO FROM prestamos INNER JOIN empleados ON prestamos.IDEMPLEADO = empleados.IDEMPLEADO ORDER BY IDPRESTAMO DESC";
        rs = mane.consultar(sql);
        ModeloPrestamos mdl;
        while(rs.next())
        {
            mdl = new ModeloPrestamos();
            mdl.setIDPRESTAMO(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setAPELLIDOS(rs.getString(3));
            mdl.setFECHA(rs.getString(4));
            mdl.setDESCRIPCION(rs.getString(5));
            mdl.setMONTO(rs.getFloat(6));
            mdl.setPERIODO(rs.getInt(7));
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloPrestamos mdl = (ModeloPrestamos)model;
        sql = "INSERT INTO "+TABLA+" (IDPRESTAMO, IDEMPLEADO, FECHA, DESCRIPCION, MONTO, PERIODO)"
            + "VALUES (" + 
            mdl.getIDPRESTAMO() + "," +
            mdl.getIDEMPLEADO() + ",'" +
            mdl.getFECHA() + "','" + 
            mdl.getDESCRIPCION() + "'," +   
            mdl.getMONTO() + "," +       
            mdl.getPERIODO() + " )"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloPrestamos mdl = (ModeloPrestamos)model;
        sql = "update "+TABLA+" set "
                + " IDEMPLEADO = " + mdl.getIDEMPLEADO()
                + " , FECHA = '" + mdl.getFECHA()
                + "' , DESCRIPCION = '" + mdl.getDESCRIPCION()
                + "' , MONTO = " + mdl.getMONTO()
                + " , PERIODO = " + mdl.getPERIODO()
                + " where IDPRESTAMO = " + mdl.getIDPRESTAMO() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloPrestamos mdl = (ModeloPrestamos)model;
        sql=("delete from "+TABLA+" where IDPRESTAMO =" + mdl.getIDPRESTAMO() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloPrestamos getRegistrosID(int IDPRESTAMO) throws Exception
    {
        sql = "SELECT prestamos.IDPRESTAMO, empleados.NOMBRE, empleados.APELLIDOS, prestamos.FECHA, prestamos.DESCRIPCION, prestamos.MONTO, prestamos.IDEMPLEADO, prestamos.PERIODO FROM prestamos INNER JOIN empleados ON prestamos.IDEMPLEADO = empleados.IDEMPLEADO WHERE prestamos.IDPRESTAMO = "+IDPRESTAMO+"";
        rs = mane.consultar(sql);
        ModeloPrestamos mdl = null;
        while(rs.next())
        {
            mdl = new ModeloPrestamos();
            mdl.setIDPRESTAMO(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setAPELLIDOS(rs.getString(3));
            mdl.setFECHA(rs.getString(4));
            mdl.setDESCRIPCION(rs.getString(5));
            mdl.setMONTO(rs.getFloat(6));
            mdl.setIDEMPLEADO(rs.getInt(7));
            mdl.setPERIODO(rs.getInt(8));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public List getRegistrosIDE(int IDEMPLEADO, int PERIODO) throws Exception
    {
        ArrayList<ModeloPrestamos> lista = new ArrayList<>();
        sql = "SELECT prestamos.IDPRESTAMO, empleados.NOMBRE, empleados.APELLIDOS, prestamos.FECHA, prestamos.DESCRIPCION, prestamos.MONTO, prestamos.IDEMPLEADO, prestamos.PERIODO FROM prestamos INNER JOIN empleados ON prestamos.IDEMPLEADO = empleados.IDEMPLEADO WHERE prestamos.IDEMPLEADO = "+IDEMPLEADO+" AND prestamos.PERIODO="+PERIODO+" ";
        rs = mane.consultar(sql);
        ModeloPrestamos mdl;
        while(rs.next())
        {
            mdl = new ModeloPrestamos();
            mdl.setIDPRESTAMO(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setAPELLIDOS(rs.getString(3));
            mdl.setFECHA(rs.getString(4));
            mdl.setDESCRIPCION(rs.getString(5));
            mdl.setMONTO(rs.getFloat(6));
            mdl.setIDEMPLEADO(rs.getInt(7));
            mdl.setPERIODO(rs.getInt(8));
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public double suma() throws Exception
    {
        double suma = 0;
        sql = "SELECT SUM(prestamos.MONTO) FROM "+TABLA+" ";
        //sql = "SELECT SUM(prestamos.MONTO) FROM prestamos INNER JOIN empleados ON prestamos.IDEMPLEADO = empleados.IDEMPLEADO WHERE empleados.ESTADO = 'ACTIVO'";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getDouble(1);
        }
        mane.limpiarRecursos();
        return suma;
    }
    
    public double sumaID(int IDEMPLEADO) throws Exception
    {
        double suma = 0;
        sql = "SELECT SUM(prestamos.MONTO) FROM prestamos WHERE prestamos.IDEMPLEADO = "+IDEMPLEADO+" ";
        //sql = "SELECT SUM(prestamos.MONTO) FROM prestamos INNER JOIN empleados ON prestamos.IDEMPLEADO = empleados.IDEMPLEADO WHERE empleados.ESTADO = 'ACTIVO'";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getDouble(1);
        }
        mane.limpiarRecursos();
        return suma;
    }
}
