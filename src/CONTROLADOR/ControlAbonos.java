/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloAbonos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlAbonos extends ControlAbstracto
{
    private final String TABLA = "ABONOS";
    
    public ControlAbonos() throws ClassNotFoundException, SQLException {
         this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloAbonos> lista = new ArrayList<>();
        sql = "SELECT abonos.IDABONO, empleados.NOMBRE, empleados.APELLIDOS, abonos.FECHA, abonos.DESCRIPCION, abonos.MONTO, abonos.PERIODO  FROM abonos INNER JOIN empleados ON abonos.IDEMPLEADO = empleados.IDEMPLEADO ORDER BY IDABONO DESC";
        rs = mane.consultar(sql);
        ModeloAbonos mdl;
        while(rs.next())
        {
            mdl = new ModeloAbonos();
            mdl.setIDABONO(rs.getInt(1));
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
        ModeloAbonos mdl = (ModeloAbonos)model;
        sql = "INSERT INTO "+TABLA+" (IDABONO, IDEMPLEADO, FECHA, DESCRIPCION, MONTO, PERIODO)"
            + "VALUES (" + 
            mdl.getIDABONO() + "," +
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
        ModeloAbonos mdl = (ModeloAbonos)model;
        sql = "update "+TABLA+" set "
                + " IDEMPLEADO = " + mdl.getIDEMPLEADO()
                + " , FECHA = '" + mdl.getFECHA()
                + "' , DESCRIPCION = '" + mdl.getDESCRIPCION()
                + "' , MONTO = " + mdl.getMONTO()
                + " , PERIODO = " + mdl.getPERIODO()
                + " where IDABONO = " + mdl.getIDABONO() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloAbonos mdl = (ModeloAbonos)model;
        sql=("delete from "+TABLA+" where IDABONO =" + mdl.getIDABONO() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloAbonos getRegistrosID(int ID) throws Exception
    {
        sql = "SELECT abonos.IDABONO, empleados.NOMBRE, empleados.APELLIDOS, abonos.FECHA, abonos.DESCRIPCION, abonos.MONTO, abonos.IDEMPLEADO, abonos.PERIODO FROM abonos INNER JOIN empleados ON abonos.IDEMPLEADO = empleados.IDEMPLEADO WHERE abonos.IDABONO = "+ID+" ORDER BY IDABONO DESC";
        rs = mane.consultar(sql);
        ModeloAbonos mdl = null;
        while(rs.next())
        {
            mdl = new ModeloAbonos();
            mdl.setIDABONO(rs.getInt(1));
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
        ArrayList<ModeloAbonos> lista = new ArrayList<>();
        sql = "SELECT abonos.IDABONO, empleados.NOMBRE, empleados.APELLIDOS, abonos.FECHA, abonos.DESCRIPCION, abonos.MONTO, abonos.IDEMPLEADO, abonos.PERIODO FROM abonos INNER JOIN empleados ON abonos.IDEMPLEADO = empleados.IDEMPLEADO WHERE abonos.IDEMPLEADO = "+IDEMPLEADO+" AND abonos.PERIODO = "+PERIODO+" ";
        rs = mane.consultar(sql);
        ModeloAbonos mdl;
        while(rs.next())
        {
            mdl = new ModeloAbonos();
            mdl.setIDABONO(rs.getInt(1));
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
    
    public List getRegistrosIDE(int IDEMPLEADO,int IDABONO, String FECHA, float MONTO, int PERIODO) throws Exception
    {
        ArrayList<ModeloAbonos> lista = new ArrayList<>();
        sql = "SELECT abonos.IDABONO, empleados.NOMBRE, empleados.APELLIDOS, abonos.FECHA, abonos.DESCRIPCION, abonos.MONTO, abonos.IDEMPLEADO, abonos.PERIODO FROM abonos INNER JOIN empleados ON abonos.IDEMPLEADO = empleados.IDEMPLEADO WHERE abonos.IDEMPLEADO = "+IDEMPLEADO+" and abonos.PERIODO="+PERIODO+" ";
        rs = mane.consultar(sql);
        ModeloAbonos mdl;
        while(rs.next())
        {
            mdl = new ModeloAbonos();
            mdl.setIDABONO(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setAPELLIDOS(rs.getString(3));
            mdl.setFECHA(rs.getString(4));
            mdl.setDESCRIPCION(rs.getString(5));
            mdl.setMONTO(rs.getFloat(6));
            mdl.setIDEMPLEADO(rs.getInt(7));
            mdl.setPERIODO(rs.getInt(8));
            lista.add(mdl);
        }
        mdl = new ModeloAbonos();
        mdl.setIDABONO(IDABONO);
        mdl.setFECHA(FECHA);
        mdl.setMONTO(MONTO);
        mdl.setPERIODO(PERIODO);
        lista.add(mdl);
        mane.limpiarRecursos();
        return lista;
    }
    
    public double suma() throws Exception
    {
        double suma = 0;
        sql = "SELECT SUM(abonos.MONTO) FROM abonos";
        //sql = "SELECT SUM(abonos.MONTO) FROM abonos INNER JOIN empleados ON abonos.IDEMPLEADO = empleados.IDEMPLEADO WHERE empleados.ESTADO = 'ACTIVO'";        
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
        sql = "SELECT SUM(abonos.MONTO) FROM abonos WHERE abonos.IDEMPLEADO = "+IDEMPLEADO+" ";
        //sql = "SELECT SUM(abonos.MONTO) FROM abonos INNER JOIN empleados ON abonos.IDEMPLEADO = empleados.IDEMPLEADO WHERE empleados.ESTADO = 'ACTIVO'";        
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getDouble(1);
        }
        mane.limpiarRecursos();
        return suma;
    }
}
