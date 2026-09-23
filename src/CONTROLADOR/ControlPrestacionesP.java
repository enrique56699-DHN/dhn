/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloPrestacionesP;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlPrestacionesP extends ControlAbstracto
{
    private final String TABLA = "PRESTACIONESP";
    
    public ControlPrestacionesP() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {        
        ArrayList<ModeloPrestacionesP> lista = new ArrayList<>();
        sql = "SELECT * FROM "+TABLA+" ";
        rs = mane.consultar(sql);
        //rs = mane.consultar1(sql);
        ModeloPrestacionesP x;
        while(rs.next())
        {
            x = new ModeloPrestacionesP();
            x.setIDPRESTACIONP(rs.getInt(1));
            x.setIDEMPLEADO(rs.getInt(2));
            x.setIDPRESTACION(rs.getInt(3));
            x.setFREG(rs.getString(4));
            x.setMONTO(rs.getFloat(5));
            x.setPERIODO(rs.getInt(6));
            lista.add(x);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloPrestacionesP x = (ModeloPrestacionesP)model;
        sql = "INSERT INTO "+TABLA+" (IDPRESTACIONP, IDEMPLEADO, IDPRESTACION, FREG, MONTO, PERIODO)"
            + "VALUES (" + 
            x.getIDPRESTACIONP() + "," +
            x.getIDEMPLEADO() + "," +
            x.getIDPRESTACION() + ",'" + 
            x.getFREG() + "'," +       
            x.getMONTO() + "," +           
            x.getPERIODO() + " )"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
        }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloPrestacionesP x = (ModeloPrestacionesP)model;
        sql = "update "+TABLA+" set "
                + " IDEMPLEADO = " + x.getIDEMPLEADO()
                + " , IDPRESTACION = " + x.getIDPRESTACION()
                + " , FREG = '" + x.getFREG()
                + "' , MONTO = " + x.getMONTO()
                + " , PERIODO = " + x.getPERIODO()
                + " where IDPRESTACIONP = " + x.getIDPRESTACIONP() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloPrestacionesP x = (ModeloPrestacionesP)model;
        sql=("delete from "+TABLA+" where IDPRESTACIONP =" + x.getIDPRESTACIONP() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }    
    
    public int aguinaldop(int IDEMPLEADO, int PERIODO) throws Exception
    {
        int resultado=0;
        sql = "SELECT COUNT(*) FROM "+TABLA+" WHERE IDEMPLEADO = "+IDEMPLEADO+" AND PERIODO = "+PERIODO+" ";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            resultado=rs.getInt(1);
        }
        return resultado;
    }
    
    public List getRegistrosE() throws Exception {        
        ArrayList<ModeloPrestacionesP> lista = new ArrayList<>();
        sql = "SELECT prestacionesp.IDPRESTACIONP, empleados.NOMBRE, empleados.APELLIDOS, empleados.EMPRESA, prestaciones.CONCEPTO, prestacionesp.FREG, prestacionesp.MONTO, prestaciones.PERIODO, prestaciones.FINI, prestaciones.FFIN FROM "+TABLA+" INNER JOIN empleados ON empleados.IDEMPLEADO = prestacionesp.IDEMPLEADO INNER JOIN prestaciones ON prestacionesp.IDPRESTACION = prestaciones.IDPRESTACION ";
        rs = mane.consultar(sql);
        ModeloPrestacionesP x;
        while(rs.next())
        {
            x = new ModeloPrestacionesP();
            x.setIDPRESTACIONP(rs.getInt(1));
            x.setNOMBRE(rs.getString(2));
            x.setAPELLIDOS(rs.getString(3));
            x.setEMPRESA(rs.getString(4));
            x.setPRESTACION(rs.getString(5));
            x.setFECHA(rs.getString(6));
            x.setMONTO(rs.getFloat(7));
            x.setPERIODO(rs.getInt(8));
            x.setFINI(rs.getString(9));
            x.setFFIN(rs.getString(10));
            lista.add(x);

        }
        mane.limpiarRecursos();
        return lista;
    }
}
