/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloVacaciones;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlVacaciones extends ControlAbstracto
{
    private final String TABLA = "VACACIONES";
    
    public ControlVacaciones() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloVacaciones> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        
        ModeloVacaciones x;
        while(rs.next())
        {
            x = new ModeloVacaciones();
            x.setIDVACACION(rs.getInt(1));
            x.setIDEMPLEADO(rs.getInt(2));
            x.setFREG(rs.getString(3));
            x.setFSALIDA(rs.getString(4));
            x.setFREGRESO(rs.getString(5));
            x.setDIAST(rs.getInt(6));
            x.setCOMENTARIOS(rs.getString(7));
            x.setPERIODO(rs.getInt(8));
            
            lista.add(x);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloVacaciones pto = (ModeloVacaciones)model;
            sql = "INSERT INTO "+TABLA+" (IDVACACION, IDEMPLEADO, FREG, FSALIDA, FREGRESO, DIAST, COMENTARIOS, PERIODO)"
                + "VALUES (" + 
                pto.getIDVACACION() + "," + 
                pto.getIDEMPLEADO() + ",'" +
                pto.getFREG() + "','" +    
                pto.getFSALIDA() + "','" +
                pto.getFREGRESO() + "'," +
                pto.getDIAST() + ",'" +
                pto.getCOMENTARIOS() + "'," +    
                pto.getPERIODO() + ") "; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloVacaciones pto = (ModeloVacaciones)model;
            sql = "update "+TABLA+" set "
                + " IDEMPLEADO = " + pto.getIDEMPLEADO()
                + " , FREG = '" + pto.getFREG()
                + "' , FSALIDA = '" + pto.getFSALIDA()
                + "' , FREGRESO = '" + pto.getFREGRESO()    
                + "' , DIAST = " + pto.getDIAST()
                + " , COMENTARIOS = '" + pto.getCOMENTARIOS()
                + " , PERIODO = '" + pto.getPERIODO()    
                + " where IDVACACION = " + pto.getIDVACACION() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloVacaciones pto = (ModeloVacaciones)model;
        sql=("delet from "+TABLA+" where IDVACACION =" + pto.getIDVACACION() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public List getRegistrosINNERP() throws Exception {
        ArrayList<ModeloVacaciones> lista = new ArrayList<>();
        sql = "SELECT vacaciones.IDVACACION, empleados.NOMBRE, empleados.APELLIDOS, vacaciones.FREG, vacaciones.FSALIDA, vacaciones.FREGRESO, vacaciones.DIAST, vacaciones.COMENTARIOS, vacaciones.PERIODO FROM vacaciones INNER JOIN empleados ON vacaciones.IDEMPLEADO = empleados.IDEMPLEADO";
        rs = mane.consultar(sql);
        
        ModeloVacaciones x;
        while(rs.next())
        {
            x = new ModeloVacaciones();
            x.setIDVACACION(rs.getInt(1));
            x.setNOMBRE(rs.getString(2));
            x.setAPELLIDOS(rs.getString(3));
            x.setFREG(rs.getString(4));
            x.setFSALIDA(rs.getString(5));
            x.setFREGRESO(rs.getString(6));
            x.setDIAST(rs.getInt(7));
            x.setCOMENTARIOS(rs.getString(8));
            x.setPERIODO(rs.getInt(9));
            lista.add(x);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public ModeloVacaciones getRegistrosID(int IDVACACION) throws Exception {
        sql = "select * from "+TABLA+" WHERE IDVACACION = "+IDVACACION+"";
        rs = mane.consultar(sql);        
        ModeloVacaciones x = null;
        while(rs.next())
        {
            x = new ModeloVacaciones();
            x.setIDVACACION(rs.getInt(1));
            x.setIDEMPLEADO(rs.getInt(2));
            x.setFREG(rs.getString(3));
            x.setFSALIDA(rs.getString(4));
            x.setFREGRESO(rs.getString(5));
            x.setDIAST(rs.getInt(6));
            x.setPERIODO(rs.getInt(7));
        }
        mane.limpiarRecursos();
        return x;
    }
    
    public int getDIAST(int IDEMPLEADO, int PERIODO) throws Exception {
        sql = "SELECT SUM(vacaciones.DIAST) FROM vacaciones WHERE vacaciones.IDEMPLEADO = "+IDEMPLEADO+" and vacaciones.PERIODO = "+PERIODO+"";
        rs = mane.consultar(sql);        
        int DIAST=0;
        while(rs.next())
        {
            DIAST = rs.getInt(1);
        }
        mane.limpiarRecursos();
        String dt = ""+DIAST;
        
        if(dt == null)
        {
            DIAST = 0;
        }
        
        return DIAST;
    }
    
    public ModeloVacaciones getRegistrosIDEP(int IDEMPLEADO) throws Exception {
        sql = "select * from VACACIONES WHERE IDEMPLEADO = "+IDEMPLEADO+"";
        rs = mane.consultar(sql);        
        ModeloVacaciones x = null;
        while(rs.next())
        {
            x = new ModeloVacaciones();
            x.setIDVACACION(rs.getInt(1));
            x.setIDEMPLEADO(rs.getInt(2));
            x.setFREG(rs.getString(3));
            x.setFSALIDA(rs.getString(4));
            x.setFREGRESO(rs.getString(5));
            x.setDIAST(rs.getInt(6));
            x.setPERIODO(rs.getInt(7));
        }
        mane.limpiarRecursos();
        return x;
    }
}
