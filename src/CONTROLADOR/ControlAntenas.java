/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.ModeloAntenas;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author enrique.orozco
 */
public class ControlAntenas extends ControlAbstracto
{
    private final String TABLA = "ANTENAS";
    
    public ControlAntenas() throws ClassNotFoundException, SQLException
    {
        this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloAntenas> antenas = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloAntenas pto;
        while(rs.next())
        {
            pto = new ModeloAntenas();
            pto.setIDANTENA(rs.getInt(1));
            pto.setMODELO(rs.getString(2));
            pto.setNOMBRE(rs.getString(3));
            pto.setMARCA(rs.getString(4));
            pto.setIP(rs.getString(5));
            pto.setSITIO(rs.getString(6));
            pto.setIPPADRE(rs.getString(7));

            antenas.add(pto);
        }
        mane.limpiarRecursos();
        return antenas;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloAntenas pto = (ModeloAntenas)model;
        sql = "INSERT INTO "+TABLA+" (IDANTENA,MODELO,NOMBRE,MARCA,IP,SITIO,IPPADRE)"
                + "VALUES (" + 
                pto.getIDANTENA() + ",'" + 
                pto.getMODELO() + "','" +
                pto.getNOMBRE() + "','" +
                pto.getMARCA() + "','" +                
                pto.getIP() + "','" +
                pto.getSITIO() + "','" +
                pto.getIPPADRE() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloAntenas pto = (ModeloAntenas)model;
        sql = "update "+TABLA+" set "
                + "MODELO='" + pto.getMODELO()
                + "',NOMBRE='" + pto.getNOMBRE()
                + "',MARCA='" + pto.getMARCA()
                + "',IP='" + pto.getIP()
                + "',SITIO='" + pto.getSITIO()
                + "',IPPADRE='" + pto.getIPPADRE()
                + "' where IDANTENA=" + pto.getIDANTENA() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloAntenas pto = (ModeloAntenas)model;
        sql=("delete from "+TABLA+" where IDANTENA =" + pto.getIDANTENA() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloAntenas getRegistro(Integer code) throws Exception {
        sql = "select * from "+TABLA+" where IDANTENA = "+code;
        rs = mane.consultar(sql);
        ModeloAntenas pto=new ModeloAntenas();
        while(rs.next())
        {   
            pto.setIDANTENA(rs.getInt(1));
            pto.setMODELO(rs.getString(2));
            pto.setNOMBRE(rs.getString(3));
            pto.setMARCA(rs.getString(4));
            pto.setIP(rs.getString(5));
            pto.setSITIO(rs.getString(6));
            pto.setIPPADRE(rs.getString(7));
        }
        mane.limpiarRecursos();
        return pto;
    }
    
    public List getRegistros(int IDANTENA) throws Exception {
        ArrayList<ModeloAntenas> antenas = new ArrayList<>();
        sql = "select * from "+TABLA+" where IDANTENA = "+IDANTENA+" ";
        rs = mane.consultar(sql);
        ModeloAntenas pto;  
        while(rs.next())
        {
            pto = new ModeloAntenas();
            pto.setIDANTENA(rs.getInt(1));
            pto.setMODELO(rs.getString(2));
            pto.setNOMBRE(rs.getString(3));
            pto.setMARCA(rs.getString(4));
            pto.setIP(rs.getString(5));
            pto.setSITIO(rs.getString(6));
            pto.setIPPADRE(rs.getString(7));
            
            antenas.add(pto);
        }
        mane.limpiarRecursos();
        return antenas;
    }
    
    /*public void CambiarAntena(ModeloAntenas pto) throws Exception{
        sql = "update "+TABLA+" set "
                + "NOMBRE='" + pto.getNOMBRE()
                + "',MARCA='" + pto.getMARCA()
                + "',MAC='" + pto.getMAC()
                + "',MARCA='" + pto.getMARCA()
                + "',IP='" + pto.getIP()
                + "',TIPO='" + pto.getTIPO()
                + "',ESTADO='" + pto.getESTADO()
                + "',OBSERVACIONES='" + pto.getOBSERVACIONES()
                + "' where IDANTENA=" + pto.getIDANTENA() + " ";
        mane.Ejecutar(sql);
        mane.cerrarConexion();
    }*/
    
    /*public static String comprobar(String sn) throws Exception 
    {
        String SN="";
               Manejador obj = new Manejador();
               String sql;
               ResultSet rs;
               sql=("SELECT MAC FROM ANTENAS WHERE MAC ='"+sn+"'");
               rs= obj.consultar(sql);
               if(rs.next())
                {
                    SN=rs.getString(1);
                }
               obj.cerrarConexion();
        return SN;   
    }*/
    
    /*public ModeloAntenas getRegistrosID(int IDANTENA) throws Exception {
        sql = "select * from "+TABLA+" where IDANTENA ="+IDANTENA+"";
        rs = mane.consultar(sql);
        ModeloAntenas pto = null;
        while(rs.next())
        {
            pto = new ModeloAntenas();
            pto.setIDANTENA(rs.getInt(1));
            pto.setNOMBRE(rs.getString(2));
            pto.setMARCA(rs.getString(3));
            pto.setMAC(rs.getString(4));
            pto.setIP(rs.getString(5));
            pto.setTIPO(rs.getString(6));
            pto.setESTADO(rs.getString(7));
            pto.setOBSERVACIONES(rs.getString(8));
            pto.setFRECUENCIA(rs.getInt(9));
        }
        mane.cerrarConexion();
        return pto;
    }
    
    public List getRegistrosTipo(String TIPO1, String TIPO2) throws Exception {
        ArrayList<ModeloAntenas> antenas = new ArrayList<>();
        sql = "select NOMBRE,FRECUENCIA from "+TABLA+" where TIPO = '"+TIPO1+"' OR TIPO = '"+TIPO2+"'";
        rs = mane.consultar(sql);
        ModeloAntenas pto;  
        while(rs.next())
        {
            pto = new ModeloAntenas();
            pto.setNOMBRE(rs.getString(1));
            pto.setFRECUENCIA(rs.getInt(2));
            
            antenas.add(pto);
        }
        mane.cerrarConexion();
        return antenas;
    }
    
    public void actualizarAntena(int IDANTENA, String IPANTENA) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "IP = '" + IPANTENA
                + "' where IDANTENA =" + IDANTENA + " ";
        mane.Ejecutar(sql);
        mane.cerrarConexion();
    }*/
}
