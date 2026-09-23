/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloBonosD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlBonosD extends ControlAbstracto
{
    private final String TABLA = "BONOSD";
    
    public ControlBonosD() throws ClassNotFoundException, SQLException {
         this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloBonosD> lista = new ArrayList<>();
        sql = "SELECT bonosd.IDBONOD, empleados.NOMBRE, empleados.APELLIDOS, bonosd.EMPRESA, prestaciones.CONCEPTO, prestaciones.PERIODO, bonosd.MONTO, bonosd.FECHA FROM bonosd INNER JOIN empleados ON bonosd.IDEMPLEADO = empleados.IDEMPLEADO INNER JOIN prestaciones ON bonosd.IDPRESTACION = prestaciones.IDPRESTACION";
        rs = mane.consultar(sql);
        ModeloBonosD mdl;
        while(rs.next())
        {
            mdl = new ModeloBonosD();
            mdl.setIDBONOD(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setAPELLIDOS(rs.getString(3));
            mdl.setEMPRESA(rs.getString(4));
            mdl.setPRESTACION(rs.getString(5));
            mdl.setPERIODO(rs.getInt(6));
            mdl.setMONTO(rs.getFloat(7));
            mdl.setFECHA(rs.getString(8));
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloBonosD mdl = (ModeloBonosD)model;
        sql = "INSERT INTO "+TABLA+" (IDEMPLEADO, IDPRESTACION, EMPRESA, MONTO, FECHA)"
            + "VALUES (" + 
            mdl.getIDEMPLEADO() + "," +
            mdl.getIDPRESTACION() + ",'" +
            mdl.getEMPRESA() + "'," + 
            mdl.getMONTO() + ",'" +
            mdl.getFECHA() + "' )"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        /*ModeloBonosD mdl = (ModeloBonosD)model;
        sql = "update "+TABLA+" set "
                + " IDEMPLEADO = " + mdl.getIDEMPLEADO()
                + " , FECHA = '" + mdl.getFECHA()
                + "' , DESCRIPCION = '" + mdl.getDESCRIPCION()
                + "' , MONTO = " + mdl.getMONTO()
                + " , PERIODO = " + mdl.getPERIODO()
                + " where IDABONO = " + mdl.getIDABONO() + " ";
        mane.Ejecutar(sql);
        mane.cerrarConexion();*/
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloBonosD mdl = (ModeloBonosD)model;
        sql=("delete from "+TABLA+" where IDBONOD =" + mdl.getIDBONOD() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
   
    public double suma(String EMPRESA) throws Exception
    {
        double suma = 0;
        //sql = "SELECT SUM(MONTO) FROM bonosd WHERE EMPRESA = '"+EMPRESA+"' AND CONCEPTO = '"+CONCEPTO+"' ";
        sql = "SELECT SUM(MONTO) FROM bonosd WHERE EMPRESA = '"+EMPRESA+"'";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getDouble(1);
        }
        mane.limpiarRecursos();
        return suma;
    }
        
}
