/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloOrdenesP;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlOrdenesP extends ControlAbstracto
{
    private final String TABLA = "ORDENESP";
    
    public ControlOrdenesP() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloOrdenesP> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloOrdenesP mdl;
        while(rs.next())
        {
            mdl = new ModeloOrdenesP();
            mdl.setIDORDENP(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setIDPENDIENTE(rs.getInt(3));
            mdl.setFINI(rs.getString(4));
            mdl.setFFIN(rs.getString(5));
            mdl.setESTADO(rs.getString(6));
            mdl.setCOMENTARIOS(rs.getString(7));
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloOrdenesP mdl = (ModeloOrdenesP)model;
            sql = "INSERT INTO "+TABLA+" (IDORDENP,IDEMPLEADO,IDPENDIENTE,FINI,FFIN,ESTADO,COMENTARIOS)"
                + "VALUES (" + 
                mdl.getIDORDENP() + "," + 
                mdl.getIDEMPLEADO() + "," +
                mdl.getIDPENDIENTE() + ",'" +
                mdl.getFINI() + "','" +
                mdl.getFFIN() + "','" +
                mdl.getESTADO() + "','" +
                mdl.getCOMENTARIOS() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloOrdenesP mdl = (ModeloOrdenesP)model;
            sql = "update "+TABLA+" set "
                + " IDEMPLEADO=" + mdl.getIDEMPLEADO()
                + ", IDPENDIENTE=" + mdl.getIDPENDIENTE()
                + ", FINI='" + mdl.getFINI()
                + "', FFIN='" + mdl.getFFIN()
                + "', ESTADO='" + mdl.getESTADO()
                + "', COMENTARIOS='" + mdl.getCOMENTARIOS()    
                + "' where IDORDENP=" + mdl.getIDORDENP() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloOrdenesP mdl = (ModeloOrdenesP)model;
        sql=("delet from "+TABLA+" where IDORDENP =" + mdl.getIDORDENP() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloOrdenesP getRegistro(Integer code) throws Exception {
        sql = "select * from "+TABLA+" where IDORDENP = "+code;
        rs = mane.consultar(sql);
        ModeloOrdenesP mdl = new ModeloOrdenesP();
        while(rs.next())
        {   
            mdl = new ModeloOrdenesP();
            mdl.setIDORDENP(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setIDPENDIENTE(rs.getInt(3));
            mdl.setFINI(rs.getString(4));
            mdl.setFFIN(rs.getString(5));
            mdl.setESTADO(rs.getString(6));
            mdl.setCOMENTARIOS(rs.getString(7));
        }
        mane.limpiarRecursos();
        return mdl;
    }

    public ModeloOrdenesP getRegistrosID(int ID) throws Exception {
        sql = "select * from "+TABLA+" where IDORDENP ="+ID+"";
        rs = mane.consultar(sql);
        ModeloOrdenesP mdl = null;
        while(rs.next())
        {
            mdl = new ModeloOrdenesP();
            mdl.setIDORDENP(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setIDPENDIENTE(rs.getInt(3));
            mdl.setFINI(rs.getString(4));
            mdl.setFFIN(rs.getString(5));
            mdl.setESTADO(rs.getString(6));
            mdl.setCOMENTARIOS(rs.getString(7));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public ModeloOrdenesP getRegistrosIDCONT(int IDPENDIENTE) throws Exception {
        sql = "select * from "+TABLA+" where IDPENDIENTE ="+IDPENDIENTE+"";
        rs = mane.consultar(sql);
        ModeloOrdenesP mdl = null;
        while(rs.next())
        {
            mdl = new ModeloOrdenesP();
            mdl.setIDORDENP(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setIDPENDIENTE(rs.getInt(3));
            mdl.setFINI(rs.getString(4));
            mdl.setFFIN(rs.getString(5));
            mdl.setESTADO(rs.getString(6));
            mdl.setCOMENTARIOS(rs.getString(7));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public ArrayList<ModeloOrdenesP> getRegistrosINNER(String ESTADO) throws Exception {
        //sql = "select * from ORDENESP where ESTADO ='"+ESTADO+"'";
       sql = "SELECT ordenesp.IDORDENP, pendientes.IDPENDIENTE, empleados.NOMBRE, clientes.NOMBRE, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, direcciones.LOCALIDAD, direcciones.LATITUD, direcciones.LONGITUD, ordenesp.FINI, ordenesp.FFIN, ordenesp.COMENTARIOS, pendientes.DESCRIPCION FROM ordenesp INNER JOIN pendientes ON ordenesp.IDPENDIENTE = pendientes.IDPENDIENTE INNER JOIN empleados ON ordenesp.IDEMPLEADO = empleados.IDEMPLEADO INNER JOIN clientes on pendientes.IDCLIENTE = clientes.IDCLIENTE INNER JOIN direcciones on pendientes.IDDIRECCION = direcciones.IDDIRECCION WHERE ordenesp.ESTADO = '"+ESTADO+"' ORDER BY ordenesp.IDORDENP DESC";
        ArrayList<ModeloOrdenesP> lista = new ArrayList<>();
        rs = mane.consultar(sql);
        ModeloOrdenesP mdl;
        while(rs.next())
        {
            mdl = new ModeloOrdenesP();
            mdl.setIDORDENP(rs.getInt(1));
            mdl.setIDPENDIENTE(rs.getInt(2));
            mdl.setEMPLEADO(rs.getString(3));
            mdl.setCLIENTE(rs.getString(4));
            mdl.setCALLE(rs.getString(5));
            mdl.setNUMERO(rs.getString(6));
            mdl.setCOLONIA(rs.getString(7));
            mdl.setLOCALIDAD(rs.getString(8));
            mdl.setLATITUD(rs.getString(9));
            mdl.setLONGITUD(rs.getString(10));
            mdl.setFINI(rs.getString(11));
            mdl.setFFIN(rs.getString(12));
            mdl.setCOMENTARIOS(rs.getString(13));
            mdl.setDESCRIPCION(rs.getString(14));
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public void terminarOrden(int IDORDENP, String FFIN, String ESTADO) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "FFIN = '" + FFIN
                + "', ESTADO = '" + ESTADO
                + "' where IDORDENP =" + IDORDENP + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public static List getRegistrosIDCONT(String ESTADO) throws Exception {
        ArrayList<Integer> cont = new ArrayList<>();
        Manejador obj = new Manejador();
        String sql2 = "select IDPENDIENTE from ORDENESP where ESTADO='" + ESTADO + "'";
        ResultSet rs =obj.consultar(sql2);
        while(rs.next())
        {

            cont.add(rs.getInt(1));
        }
        obj.limpiarRecursos();
        return cont;
    }
    
    public int idorden(int IDPENDIENTE) throws Exception
    {
        int idpendiente = 0;
        sql = "SELECT ordenesp.IDORDENP FROM "+TABLA+" WHERE ordenesp.IDPENDIENTE = " + IDPENDIENTE + " ";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            idpendiente = rs.getInt(1);
        }
        mane.limpiarRecursos();
        return idpendiente;
    }
    
    public void guardarRegistroIF(Object model) throws Exception {
        ModeloOrdenesP mdl = (ModeloOrdenesP)model;
            sql = "INSERT INTO "+TABLA+" (IDORDENP,IDEMPLEADO,IDPENDIENTE,FINI,FFIN,ESTADO,COMENTARIOS) SELECT (" +
                mdl.getIDORDENP() + "," + 
                mdl.getIDEMPLEADO() + "," +
                mdl.getIDPENDIENTE() + ",'" +
                mdl.getFINI() + "','" +
                mdl.getFFIN() + "','" +
                mdl.getESTADO() + "','" +
                mdl.getCOMENTARIOS() + "') WHERE NOT EXISTS (SELECT * FROM ordenesp WHERE IDPENDIENTE = "+mdl.getIDPENDIENTE()+") "; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public void actualizar(int IDORDENP, String COMENTARIOS) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "COMENTARIOS = '" + COMENTARIOS
                + "' where IDORDENP =" + IDORDENP + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public void actualizarEstado(String ESTADO,int IDORDENP) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "ESTADO = '" + ESTADO
                + "' where IDORDENP =" + IDORDENP + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public void actualizarFFIN(String FFIN, int IDORDENP) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "FFIN = '" + FFIN
                + "' where IDORDENP =" + IDORDENP + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public void actualizarFINI(String FINI, int IDORDENP) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "FINI = '" + FINI
                + "' where IDORDENP =" + IDORDENP + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
}
