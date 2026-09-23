/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloEmpleados;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Enrique Orozco Alcala
 */

public class ControlEmpleados extends ControlAbstracto
{
    private final String TABLA = "EMPLEADOS";
    
    public ControlEmpleados() throws ClassNotFoundException, SQLException 
    {
        this.mane = new Manejador();
    }
        
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloEmpleados> lista = new ArrayList<>();
        sql = "select * from "+TABLA+"";
        rs = mane.consultar(sql);
        ModeloEmpleados mdl;
        while(rs.next())
        {
            mdl = new ModeloEmpleados();
            mdl.setIDEMPLEADO(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setAPELLIDOS(rs.getString(3));
            mdl.setTEL(rs.getString(4));       
            mdl.setSUELDO(rs.getFloat(5));
            mdl.setEMPRESA(rs.getString(6));
            mdl.setBONO(rs.getFloat(7));
            mdl.setBONOP(rs.getFloat(8));
            mdl.setFECHA(rs.getString(9));
            mdl.setESTADO(rs.getString(10));
            mdl.setIDEPUESTO(rs.getInt(11));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception 
    {
        ModeloEmpleados mdl = (ModeloEmpleados)model;
        sql = "INSERT INTO "+TABLA+" (IDEMPLEADO, NOMBRE, APELLIDOS, TEL, SUELDO, EMPRESA, BONO, BONOP, FECHA, ESTADO, IDEPUESTO)"
                + "VALUES (" + 
                mdl.getIDEMPLEADO() + ",'" +
                mdl.getNOMBRE() + "','" +
                mdl.getAPELLIDOS() + "','" +
                mdl.getTEL() + "'," +
                mdl.getSUELDO() + ",'" +
                mdl.getEMPRESA() + "'," +
                mdl.getBONO() + "," +
                mdl.getBONOP() + ",'" +
                mdl.getFECHA() + "','" +
                mdl.getESTADO() + "'," +
                mdl.getIDEPUESTO() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception 
    {
        ModeloEmpleados mdl = (ModeloEmpleados)model;
        sql = "update "+TABLA+" set "
                + "NOMBRE='" + mdl.getNOMBRE()
                + "', APELLIDOS = '" + mdl.getAPELLIDOS()
                + "', TEL = '" + mdl.getTEL()
                + "', SUELDO = " + mdl.getSUELDO()
                + " , EMPRESA = '" + mdl.getEMPRESA()
                + "' , BONO = " + mdl.getBONO()
                + " , BONOP = " + mdl.getBONOP()
                + " , FECHA = '" + mdl.getFECHA()
                + "' , ESTADO = '" + mdl.getESTADO()
                + "' , IDEPUESTO = " + mdl.getIDEPUESTO()
                + " where IDEMPLEADO=" + mdl.getIDEMPLEADO() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception 
    {
        ModeloEmpleados mdl = (ModeloEmpleados)model;
        sql=("delete from "+TABLA+" where IDEMPLEADO =" + mdl.getIDEMPLEADO() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloEmpleados getRegistros(Integer code) throws Exception 
    {
        sql = "SELECT empleados.IDEMPLEADO, empleados.NOMBRE, empleados.APELLIDOS, empleados.TEL, empleados.SUELDO, empleados.EMPRESA, empleados.BONO, empleados.BONOP, empleados.FECHA, empleados.ESTADO, empleados.IDEPUESTO, epuestos.DEPTO, epuestos.PUESTO "
                + "FROM "+TABLA+" "
                + "INNER JOIN epuestos ON empleados.IDEPUESTO = epuestos.IDEPUESTO "
                + "WHERE empleados.IDEMPLEADO = "+code;
        rs = mane.consultar(sql);
        ModeloEmpleados mdl = new ModeloEmpleados();
        while(rs.next())
        {   
            mdl.setIDEMPLEADO(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setAPELLIDOS(rs.getString(3));
            mdl.setTEL(rs.getString(4));       
            mdl.setSUELDO(rs.getFloat(5));
            mdl.setEMPRESA(rs.getString(6));
            mdl.setBONO(rs.getFloat(7));
            mdl.setBONOP(rs.getFloat(8));
            mdl.setFECHA(rs.getString(9));
            mdl.setESTADO(rs.getString(10));
            mdl.setIDEPUESTO(rs.getInt(11));
            mdl.setDPTO(rs.getString(12));
            mdl.setPUESTO(rs.getString(13));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public ModeloEmpleados getRegistrosID(int ID) throws Exception 
    {
        sql = "select * from "+TABLA+" where IDEMPLEADO ="+ID+"";
        rs = mane.consultar(sql);
        ModeloEmpleados mdl = null;
        while(rs.next())
        {
            mdl = new ModeloEmpleados();
            mdl.setIDEMPLEADO(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setAPELLIDOS(rs.getString(3));
            mdl.setTEL(rs.getString(4));       
            mdl.setSUELDO(rs.getFloat(5));
            mdl.setEMPRESA(rs.getString(6));
            mdl.setBONO(rs.getFloat(7));
            mdl.setBONOP(rs.getFloat(8));
            mdl.setFECHA(rs.getString(9));
            mdl.setESTADO(rs.getString(10));
            mdl.setIDEPUESTO(rs.getInt(11));

        }
        mane.limpiarRecursos();
        return mdl;
    }

    public int getRegistros(String NOMBRE) throws Exception
    {
        int ID = 0;
        sql = "select EMPLEADOS.IDEMPLEADO from "+TABLA+" where NOMBRE ='"+NOMBRE+"'";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            ID=rs.getInt(1);
        }
        mane.limpiarRecursos();
        return ID;
    }
    
    public double suma(String TABLA,String CAMPO1,String CAMPO2,String EMPRESA) throws Exception
    {
        double suma = 0;
        //campo1=empleados, campo2 empresa, en caso de ser empresa
        sql = "SELECT SUM("+TABLA+"."+CAMPO1+") FROM "+TABLA+" WHERE "+TABLA+"."+CAMPO2+" = '"+EMPRESA+"'";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getDouble(1);
        }
        mane.limpiarRecursos();
        return suma;
    }
    
    public double aguinaldo(int IDEMPLEADO, String EMPRESA) throws Exception
    {
        double aguinaldo;
        double aguinaldoA = 0;
        float sueldo;
        String fechaingreso;
        int id;
        float salario;
        Date fi,fa;
        
        //VARIABLE CACULO DE AGUINALDO
        Date hoy = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        long dias;
        int DIAS;
        double diaspropo;
        
        String Dia, Mes, Ano;
        String fechaaguinaldo = formateador.format(hoy);
        String []datos=fechaaguinaldo.split("/");

        Dia="08";
        Mes="12";
        Ano="";
        Ano=datos[2];
        
        fechaaguinaldo = Dia+"/"+Mes+"/"+Ano;
        
        if(IDEMPLEADO == 0)        
            sql = "SELECT empleados.IDEMPLEADO, empleados.SUELDO, empleados.FECHA FROM "+TABLA+" "
                    + "WHERE empleados.ESTADO = 'ACTIVO' AND empleados.EMPRESA = '"+EMPRESA+"'";          
        else        
            sql = "SELECT empleados.IDEMPLEADO, empleados.SUELDO, empleados.FECHA FROM "+TABLA+" "
                    + "WHERE empleados.ESTADO = 'ACTIVO' AND empleados.EMPRESA = '"+EMPRESA+"' AND empleados.IDEMPLEADO = "+IDEMPLEADO+" ";
        
        rs = mane.consultar(sql);
        while(rs.next())
        {
            id = rs.getInt(1);
            sueldo  = rs.getFloat(2);
            fechaingreso = rs.getString(3);

            //CALCULAR LOS DIAS ENTRE FECHA DE INGRESO Y FECHA DE AGUINALDO
            fi=formateador.parse(fechaingreso);
            fa=formateador.parse(fechaaguinaldo);
            dias = fa.getTime() - fi.getTime();
            dias = TimeUnit.DAYS.convert(dias, TimeUnit.MILLISECONDS);
            DIAS = (int) dias;
            
            //COMPROBAR DIAS DE AGUINALGO
            if(DIAS > 365)
            {
                salario = sueldo / 7;
                aguinaldo = salario * 15;
            }
            else
            {
                salario = sueldo / 7;
                diaspropo = (DIAS * 15);
                diaspropo = diaspropo/365;
                aguinaldo = salario * diaspropo;                
            }
            
            aguinaldo = Math.round(aguinaldo * 100) / 100d;
            
            aguinaldoA = aguinaldoA + aguinaldo;
        }
        
        aguinaldoA = Math.round(aguinaldoA * 100) / 100d;
        
        mane.limpiarRecursos();
        return aguinaldoA;
    }
    
    public List getRegistros(String ESTADO, String ORG) throws Exception 
    {
        ArrayList<ModeloEmpleados> lista = new ArrayList<>();
        /*sql = "SELECT empleados.NOMBRE FROM "+TABLA+" "
                + "WHERE empleados.ESTADO = '"+ESTADO+"' "
                + "ORDER BY empleados.NOMBRE "+ORG;*/
        sql = "SELECT DISTINCT empleados.NOMBRE "
                + "FROM "+TABLA+" "
                + "INNER JOIN epuestos ON empleados.IDEPUESTO = epuestos.IDEPUESTO "
                + "WHERE empleados.ESTADO = '"+ESTADO+"' "
                + "AND epuestos.DEPTO = 'OPERATIVO' "
                + "OR epuestos.DEPTO = 'PERSONAL SUBCONTRATADO' "
                + "OR epuestos.DEPTO = 'GERENCIA'"
                + "ORDER BY empleados.NOMBRE "+ORG;
        rs = mane.consultar(sql);
        ModeloEmpleados mdl;
        while(rs.next())
        {
            mdl = new ModeloEmpleados();
            mdl.setNOMBRE(rs.getString(1));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public List getRegistrosE(String ESTADO) throws Exception 
    {
        ArrayList<ModeloEmpleados> lista = new ArrayList<>();
        /*sql = "SELECT empleados.IDEMPLEADO, empleados.NOMBRE, empleados.APELLIDOS, empleados.TEL, empleados.SUELDO, empleados.EMPRESA, empleados.BONO, empleados.BONOP, empleados.FECHA "
                + "FROM "+TABLA+" "
                + "INNER JOIN epuestos ON empleados.IDEPUESTO = epuestos.IDEPUESTO "
                + "WHERE ESTADO = '"+ESTADO+"' ";*/
        
        sql = "SELECT empleados.IDEMPLEADO, empleados.NOMBRE, empleados.APELLIDOS, empleados.TEL, empleados.SUELDO, empleados.EMPRESA, empleados.BONO, empleados.BONOP, empleados.FECHA, epuestos.DEPTO, epuestos.PUESTO "
                + "FROM "+TABLA+" "
                + "INNER JOIN epuestos ON empleados.IDEPUESTO = epuestos.IDEPUESTO "
                + "WHERE ESTADO = '"+ESTADO+"' ";
        rs = mane.consultar(sql);
        ModeloEmpleados mdl;
        while(rs.next())
        {
            mdl = new ModeloEmpleados();
            mdl.setIDEMPLEADO(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setAPELLIDOS(rs.getString(3));
            mdl.setTEL(rs.getString(4));       
            mdl.setSUELDO(rs.getFloat(5));
            mdl.setEMPRESA(rs.getString(6));
            mdl.setBONO(rs.getFloat(7));
            mdl.setBONOP(rs.getFloat(8));
            mdl.setFECHA(rs.getString(9));
            mdl.setDPTO(rs.getString(10));
            mdl.setPUESTO(rs.getString(11));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public String getNOMBRE() throws Exception
    {
        String NOMBRE = null;
        sql = "SELECT empleados.NOMBRE "
                + "FROM empleados "
                + "INNER JOIN almacen ON empleados.IDEMPLEADO <> almacen.IDEMPLEADO "
                + "WHERE almacen.ESTADO = 'ABIERTO' AND empleados.ESTADO = 'ACTIVO'";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            NOMBRE = rs.getString(1);
        }
        return NOMBRE;
    }
    
    public List getNOMBRE(String ESTADO) throws Exception 
    {
        ArrayList<ModeloEmpleados> lista = new ArrayList<>();
        sql = "SELECT DISTINCT empleados.NOMBRE "
                + "FROM empleados "
                + "INNER JOIN almacen ON empleados.IDEMPLEADO <> almacen.IDEMPLEADO "
                + "WHERE almacen.ESTADO = 'ABIERTO' AND empleados.ESTADO = 'ACTIVO'";
        rs = mane.consultar(sql);
        ModeloEmpleados mdl;
        while(rs.next())
        {
            mdl = new ModeloEmpleados();
            mdl.setNOMBRE(rs.getString(1));            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public double totalNOMINA(String EMPRESA) throws Exception
    {
        double suma = 0;
        sql = "SELECT SUM(empleados.SUELDO) "
                + "FROM "+TABLA+" "
                + "WHERE empleados.EMPRESA = '"+EMPRESA+"'";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getDouble(1);
        }
        mane.limpiarRecursos();
        return suma;
    }
    
}
