/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloAhorros;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlAhorros extends ControlAbstracto
{
    private final String TABLA = "AHORROS";
    
    public ControlAhorros() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloAhorros> lista = new ArrayList<>();
        sql = "SELECT ahorros.IDAHORRO, empleados.NOMBRE, empleados.APELLIDOS, ahorros.FECHA, ahorros.MONTO, ahorros.PERIODO FROM ahorros INNER JOIN empleados ON ahorros.IDEMPLEADO = empleados.IDEMPLEADO";
        rs = mane.consultar(sql);
        ModeloAhorros x;
        while(rs.next())
        {
            x = new ModeloAhorros();
            x.setIDAHORRO(rs.getInt(1));
            x.setNOMBRE(rs.getString(2));
            x.setAPELLIDOS(rs.getString(3));
            x.setFECHA(rs.getString(4));
            x.setMONTO(rs.getFloat(5));
            x.setPERIODO(rs.getInt(6));

            lista.add(x);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloAhorros x = (ModeloAhorros)model;
        sql = "INSERT INTO "+TABLA+" (IDAHORRO, IDEMPLEADO, FECHA, MONTO, PERIODO)"
                + "VALUES (" + 
                x.getIDAHORRO() + ",'" + 
                x.getIDEMPLEADO() + "','" +
                x.getFECHA() + "'," +
                x.getMONTO() + "," +
                x.getPERIODO() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloAhorros pto = (ModeloAhorros)model;
        sql = "update "+TABLA+" set "
                + " IDEMPLEADO = " + pto.getIDEMPLEADO()
                + " , FECHA = '" + pto.getFECHA()
                + "' , MONTO = " + pto.getMONTO()
                + " , PERIODO = " + pto.getPERIODO()
                + " where IDAHORRO = " + pto.getIDAHORRO() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloAhorros pto = (ModeloAhorros)model;
        sql=("delete from "+TABLA+" where IDAHORRO =" + pto.getIDAHORRO() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloAhorros getRegistrosID(int ID) throws Exception
    {
        sql = "SELECT ahorros.IDAHORRO, empleados.NOMBRE, empleados.APELLIDOS, ahorros.FECHA, ahorros.MONTO, ahorros.IDEMPLEADO, ahorros.PERIODO FROM ahorros INNER JOIN empleados ON ahorros.IDEMPLEADO = empleados.IDEMPLEADO WHERE ahorros.IDAHORRO = "+ID+"";
        rs = mane.consultar(sql);
        ModeloAhorros x = null;
        while(rs.next())
        {
            x = new ModeloAhorros();
            x.setIDAHORRO(rs.getInt(1));
            x.setNOMBRE(rs.getString(2));
            x.setAPELLIDOS(rs.getString(3));
            x.setFECHA(rs.getString(4));
            x.setMONTO(rs.getFloat(5));
            x.setIDEMPLEADO(rs.getInt(6));
            x.setPERIODO(rs.getInt(7));
        }
        mane.limpiarRecursos();
        return x;
    }
    
    public List getRegistrosIDE(int IDEMPLEADO, int PERIODO) throws Exception
    {
        ArrayList<ModeloAhorros> lista = new ArrayList<>();
        sql = "SELECT ahorros.IDAHORRO, empleados.NOMBRE, empleados.APELLIDOS, ahorros.FECHA, ahorros.MONTO, ahorros.IDEMPLEADO, ahorros.PERIODO FROM ahorros INNER JOIN empleados ON ahorros.IDEMPLEADO = empleados.IDEMPLEADO WHERE ahorros.IDEMPLEADO = "+IDEMPLEADO+" AND ahorros.PERIODO="+PERIODO+" ";
        rs = mane.consultar(sql);
        ModeloAhorros x;
        while(rs.next())
        {
            x = new ModeloAhorros();
            x.setIDAHORRO(rs.getInt(1));
            x.setNOMBRE(rs.getString(2));
            x.setAPELLIDOS(rs.getString(3));
            x.setFECHA(rs.getString(4));
            x.setMONTO(rs.getFloat(5));
            x.setIDEMPLEADO(rs.getInt(6));
            x.setPERIODO(rs.getInt(7));
            lista.add(x);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public List getRegistrosIDE(int IDEMPLEADO, int IDAHORRO, String FECHA, float MONTO, int  PERIODO) throws Exception
    {
        ArrayList<ModeloAhorros> lista = new ArrayList<>();
        sql = "SELECT ahorros.IDAHORRO, empleados.NOMBRE, empleados.APELLIDOS, ahorros.FECHA, ahorros.MONTO, ahorros.IDEMPLEADO, ahorros.PERIODO FROM ahorros INNER JOIN empleados ON ahorros.IDEMPLEADO = empleados.IDEMPLEADO WHERE ahorros.IDEMPLEADO = "+IDEMPLEADO+" and ahorros.PERIODO ="+PERIODO+" ";
        rs = mane.consultar(sql);
        ModeloAhorros x;
        while(rs.next())
        {
            x = new ModeloAhorros();
            x.setIDAHORRO(rs.getInt(1));
            x.setNOMBRE(rs.getString(2));
            x.setAPELLIDOS(rs.getString(3));
            x.setFECHA(rs.getString(4));
            x.setMONTO(rs.getFloat(5));
            x.setIDEMPLEADO(rs.getInt(6));
            x.setPERIODO(rs.getInt(7));
            lista.add(x);
        }
        x = new ModeloAhorros();
        x.setIDAHORRO(IDAHORRO);
        x.setFECHA(FECHA);
        x.setMONTO(MONTO);
        x.setPERIODO(PERIODO);
        
        lista.add(x);
        mane.limpiarRecursos();
        return lista;
    }
    
    public double suma() throws Exception
    {
        double suma = 0;
        sql = "SELECT SUM(ahorros.MONTO) FROM "+TABLA+"";
        //sql = "SELECT SUM(monto) FROM ahorros INNER JOIN empleados ON ahorros.IDEMPLEADO = empleados.IDEMPLEADO WHERE empleados.ESTADO = 'ACTIVO'";
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
        sql = "SELECT SUM(ahorros.MONTO) FROM ahorros WHERE ahorros.IDEMPLEADO = "+IDEMPLEADO+" ";
        //sql = "SELECT SUM(monto) FROM ahorros INNER JOIN empleados ON ahorros.IDEMPLEADO = empleados.IDEMPLEADO WHERE empleados.ESTADO = 'ACTIVO'";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getDouble(1);
        }
        mane.limpiarRecursos();
        return suma;
    }
}
