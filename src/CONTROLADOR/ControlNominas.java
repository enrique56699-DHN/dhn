/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloNominas;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlNominas extends ControlAbstracto
{
    private final String TABLA = "NOMINAS";
    
    public ControlNominas() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloNominas> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloNominas mdl;
        while(rs.next())
        {
            mdl = new ModeloNominas();
            mdl.setIDNOMINA(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setFECHA(rs.getString(3));
            mdl.setSEMANA(rs.getInt(4));
            mdl.setANO(rs.getInt(5));
            mdl.setDIAS(rs.getInt(6));
            mdl.setNSUELDO(rs.getDouble(7));
            mdl.setBONO(rs.getDouble(8));
            mdl.setCONCEPTO(rs.getString(9));
            mdl.setIDAHORRO(rs.getInt(10));
            mdl.setIDABONO(rs.getInt(11));
            mdl.setTOTAL(rs.getDouble(12));            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
            ModeloNominas mdl = (ModeloNominas)model;
            sql = "INSERT INTO "+TABLA+" (IDNOMINA,IDEMPLEADO,FECHA,SEMANA,ANO,DIAS,NSUELDO,BONO,CONCEPTO,IDAHORRO,IDABONO,TOTAL)"
                + "VALUES (" + 
                mdl.getIDNOMINA() + "," + 
                mdl.getIDEMPLEADO() + ",'" +
                mdl.getFECHA() + "'," +
                mdl.getSEMANA() + "," +
                mdl.getANO() + "," +    
                mdl.getDIAS() + "," +
                mdl.getNSUELDO() + "," +
                mdl.getBONO() + ",'" +
                mdl.getCONCEPTO() + "'," +
                mdl.getIDAHORRO() + "," +
                mdl.getIDABONO() + "," +    
                mdl.getTOTAL() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloNominas mdl = (ModeloNominas)model;
            sql = "update "+TABLA+" set "
                + " IDEMPLEADO = " + mdl.getIDEMPLEADO()
                + " , FECHA = '" + mdl.getFECHA()
                + "' , SEMANA = " + mdl.getSEMANA()
                + " , ANO = " + mdl.getANO()    
                + " , DIAS = " + mdl.getDIAS()    
                + " , NSUELDO = " + mdl.getNSUELDO()
                + " , BONO = " + mdl.getBONO()
                + " , CONCEPTO = '" + mdl.getCONCEPTO()
                + "' , IDAHORRO = " + mdl.getIDAHORRO()
                + " , IDABONO = " + mdl.getIDABONO()
                + " , TOTAL = " + mdl.getTOTAL()    
                + " where IDNOMINA = " + mdl.getIDNOMINA() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloNominas mdl = (ModeloNominas)model;
        sql=("delet from "+TABLA+" where IDNOMINA =" + mdl.getIDNOMINA() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
   
    public List getRegistrosINNER() throws Exception {        
        ArrayList<ModeloNominas> lista = new ArrayList<>();
        sql = "SELECT nominas.IDNOMINA, empleados.NOMBRE, empleados.APELLIDOS, empleados.EMPRESA, nominas.FECHA, nominas.SEMANA, nominas.ANO, empleados.SUELDO, nominas.DIAS, nominas.NSUELDO, nominas.BONO, nominas.CONCEPTO, ahorros.MONTO, abonos.MONTO, nominas.TOTAL FROM nominas INNER JOIN empleados ON nominas.IDEMPLEADO = empleados.IDEMPLEADO LEFT JOIN ahorros ON nominas.IDAHORRO = ahorros.IDAHORRO LEFT JOIN abonos ON nominas.IDABONO = abonos.IDABONO ORDER BY nominas.IDNOMINA DESC";
        rs = mane.consultar(sql);
        ModeloNominas mdl;
        while(rs.next())
        {
            mdl = new ModeloNominas();
            mdl.setIDNOMINA(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setAPELLIDOS(rs.getString(3));
            mdl.setEMPRESA(rs.getString(4));
            mdl.setFECHA(rs.getString(5));
            mdl.setSEMANA(rs.getInt(6));
            mdl.setANO(rs.getInt(7));
            mdl.setSUELDO(rs.getDouble(8));
            mdl.setDIAS(rs.getInt(9));
            mdl.setNSUELDO(rs.getDouble(10));
            mdl.setBONO(rs.getDouble(11));
            mdl.setCONCEPTO(rs.getString(12));
            mdl.setAHORRO(rs.getInt(13));
            mdl.setABONO(rs.getInt(14));
            mdl.setTOTAL(rs.getDouble(15)); 
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public boolean existeNOMINA(int IDEMPLEADO, int SEMANA, int ANO) throws Exception
    {
        boolean existe;
        sql = "SELECT nominas.IDNOMINA FROM "+TABLA+" WHERE nominas.IDEMPLEADO = "+IDEMPLEADO+" AND nominas.SEMANA = "+SEMANA+" AND nominas.ANO = "+ANO+" ";
        rs = mane.consultar(sql);
        existe = rs.next();
        
        /*if(rs.next())
        {
            existe = true;
        }
        else
        {
            existe = false;
        }*/
        
        return existe;
    }
}
