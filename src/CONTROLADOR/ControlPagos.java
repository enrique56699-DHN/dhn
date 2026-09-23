/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.ModeloPagos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oscar E Gonzalez G
 */
public class ControlPagos extends ControlAbstracto
{
    private final String TABLA = "PAGOS";
    
    public ControlPagos() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }
        
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloPagos> pagos = new ArrayList<>();
        /*sql = "SELECT pagos.IDNOTA, clientes.NOMBRE, pagos.IDCONTRATO, pagos.FECHA, pagos.METODO, pagos.MONTO, pagos.COMENTARIOS, pagos.DOC, pagos.IDCAJA "
                + "FROM "+TABLA+" "
                + "INNER JOIN clientes on pagos.IDCLIENTE = clientes.IDCLIENTE ORDER BY pagos.IDNOTA DESC";*/
        sql = "SELECT pagos.IDNOTA, clientes.NOMBRE, pagos.IDCONTRATO, pagos.FECHA, pagos.METODO, pagos.MONTO, pagos.COMENTARIOS, pagos.DOC, pagos.IDCAJA "
                + "FROM "+TABLA+" "
                + "INNER JOIN contratos on pagos.IDCONTRATO = contratos.IDCONTRATO "
                + "INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE "
                + "ORDER BY pagos.IDNOTA DESC";
        rs = mane.consultar(sql);
        ModeloPagos pto;
        while(rs.next())
        {

            pto = new ModeloPagos();
            pto.setIDNOTA(rs.getInt(1));
            pto.setNOMBRE(rs.getString(2));
            pto.setIDCONTRATO(rs.getInt(3));
            pto.setFECHA(rs.getString(4));
            pto.setMETODO(rs.getString(5));
            pto.setMONTO(rs.getFloat(6));
            pto.setCOMENTARIOS(rs.getString(7));
            pto.setDOC(rs.getString(8));
            pto.setIDCAJA(rs.getInt(9));
            
            pagos.add(pto);

        }
        mane.limpiarRecursos();
        return pagos;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloPagos pto = (ModeloPagos)model;
        sql = "INSERT INTO "+TABLA+" (IDNOTA, IDCONTRATO, FECHA, METODO, MONTO, DOC, REFERENCIA, COMENTARIOS, IDCAJA)"
                + "VALUES (" + 
                pto.getIDNOTA() + "," +
                //pto.getIDCLIENTE() + "," +
                pto.getIDCONTRATO() + ",'" +
                pto.getFECHA() + "','" +
                pto.getMETODO() + "'," +
                pto.getMONTO() + ",'" +
                pto.getDOC() + "','" +
                pto.getREFERENCIA() + "','" +
                pto.getCOMENTARIOS() + "'," +
                pto.getIDCAJA() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloPagos pto = (ModeloPagos)model;
        sql = "update "+TABLA+" set "
                //+ "IDCLIENTE=" + pto.getIDCLIENTE()
                + "IDCONTRATO=" + pto.getIDCONTRATO()
                + ",FECHA='" + pto.getFECHA()
                + "',METODO='" + pto.getMETODO()
                + "',MONTO=" + pto.getMONTO()
                + ",DOC='" + pto.getDOC()
                + "',REFERENCIA='" + pto.getREFERENCIA()
                + "',COMENTARIOS='" + pto.getCOMENTARIOS()
                + "',IDCAJA=" + pto.getIDCAJA()
                + " where IDNOTA=" + pto.getIDNOTA() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloPagos pto = (ModeloPagos)model;
        sql=("delete from "+TABLA+" where IDNOTA =" + pto.getIDNOTA() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloPagos getRegistro(Integer code) throws Exception {
        sql = "select * from "+TABLA+" where IDNOTA = "+code;
        rs = mane.consultar(sql);
        ModeloPagos pto=new ModeloPagos();
        while(rs.next())
        {   
            pto.setIDNOTA(rs.getInt(1));
            //pto.setIDCLIENTE(rs.getInt(2));
            pto.setIDCONTRATO(rs.getInt(2));
            pto.setFECHA(rs.getString(3));
            pto.setMETODO(rs.getString(4));
            pto.setMONTO(rs.getFloat(5));
            pto.setDOC(rs.getString(6));
            pto.setREFERENCIA(rs.getString(7));
            pto.setCOMENTARIOS(rs.getString(8));
            pto.setIDCAJA(rs.getInt(9));
        }
        mane.limpiarRecursos();
        return pto;
    }
    
    public ModeloPagos getRegistrosID(int ID) throws Exception {
        sql = "SELECT pagos.IDNOTA, clientes.NOMBRE, pagos.FECHA, pagos.METODO, pagos.MONTO, pagos.COMENTARIOS, pagos.IDCONTRATO, pagos.IDCAJA, pagos.REFERENCIA, pagos.DOC "
                + "FROM "+TABLA+" "
                + "INNER JOIN contratos on pagos.IDCONTRATO = contratos.IDCONTRATO "
                + "INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE "
                + "WHERE pagos.IDNOTA ="+ID+"";
        rs = mane.consultar(sql);
        ModeloPagos pto = null;
        while(rs.next())
        {
            pto = new ModeloPagos();
            pto.setIDNOTA(rs.getInt(1));
            pto.setNOMBRE(rs.getString(2));
            pto.setFECHA(rs.getString(3));
            pto.setMETODO(rs.getString(4));
            pto.setMONTO(rs.getFloat(5));
            pto.setCOMENTARIOS(rs.getString(6));
            pto.setIDCONTRATO(rs.getInt(7));
            pto.setIDCAJA(rs.getInt(8));
            pto.setREFERENCIA(rs.getString(9));
            pto.setDOC(rs.getString(10));
        }
        mane.limpiarRecursos();
        return pto;
    }
    
    public List getRegistros(int IDCONTRATO) throws Exception
    {
        ArrayList<ModeloPagos> m = new ArrayList<>();
        sql = "SELECT pagos.IDNOTA, clientes.NOMBRE, pagos.IDCONTRATO, pagos.FECHA, pagos.MONTO, pagos.DOC, pagos.REFERENCIA, pagos.COMENTARIOS "
                + "FROM "+TABLA+" "
                + "INNER JOIN contratos on pagos.IDCONTRATO = contratos.IDCONTRATO "
                + "INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE "
                + "WHERE pagos.IDCONTRATO ="+IDCONTRATO+"";
        rs = mane.consultar(sql);
        ModeloPagos pto;
        while(rs.next())
        {
            pto = new ModeloPagos();
            pto.setIDNOTA(rs.getInt(1));
            pto.setNOMBRE(rs.getString(2));
            pto.setIDCONTRATO(rs.getInt(3));
            pto.setFECHA(rs.getString(4));
            pto.setMETODO(rs.getString(5));
            pto.setMONTO(rs.getFloat(6));
            pto.setDOC(rs.getString(7));
            pto.setREFERENCIA(rs.getString(8));
            pto.setCOMENTARIOS(rs.getString(9));
            
            //pto.setCONTRATOS((ModeloContratos)ccon.getRegistrosID(IDCONTRATO));
                        
            m.add(pto);
            
        }
        mane.limpiarRecursos();
        return m;
    }
    
    public List getRegistros(String DE, String AL, String METODO) throws Exception
    {
        ArrayList<ModeloPagos> m = new ArrayList<>();
        if (METODO.equals("TODOS"))
        {
            sql = "SELECT pagos.IDNOTA, clientes.NOMBRE, pagos.IDCONTRATO, pagos.FECHA, pagos.METODO, pagos.MONTO, pagos.COMENTARIOS "
                    + "FROM "+TABLA+" "
                    + "INNER JOIN contratos on pagos.IDCONTRATO = contratos.IDCONTRATO "
                    + "INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE "
                    + "WHERE pagos.FECHA BETWEEN '"+ DE +"' AND '"+ AL +"' ";
        }
        else
        {
            sql = "SELECT pagos.IDNOTA, clientes.NOMBRE, pagos.IDCONTRATO, pagos.FECHA, pagos.METODO, pagos.MONTO, pagos.COMENTARIOS "
                    + "FROM "+TABLA+" "
                    + "INNER JOIN contratos on pagos.IDCONTRATO = contratos.IDCONTRATO "
                    + "INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE "
                    + "WHERE pagos.METODO = '"+METODO+"' and pagos.FECHA BETWEEN '"+ DE +"' AND '"+ AL +"' ";
        }
        rs = mane.consultar(sql);
        ModeloPagos pto;

        while(rs.next())
        {            
            pto = new ModeloPagos();
            pto.setIDNOTA(rs.getInt(1));
            pto.setNOMBRE(rs.getString(2));
            pto.setIDCONTRATO(rs.getInt(3));
            pto.setFECHA(rs.getString(4));
            pto.setMETODO(rs.getString(5));
            pto.setMONTO(rs.getFloat(6));
            pto.setCOMENTARIOS(rs.getString(7));
            m.add(pto);
            
        }
        mane.limpiarRecursos();
        return m;
    }

    public List getRegistros(String DE, String AL) throws Exception
    {
        ArrayList<ModeloPagos> m = new ArrayList<>();
        String METODO ="EFECTIVO";
        sql = "SELECT pagos.IDNOTA, clientes.NOMBRE, pagos.IDCONTRATO, pagos.FECHA, pagos.METODO, pagos.MONTO, pagos.COMENTARIOS "
                + "FROM "+TABLA+" "
                + "INNER JOIN contratos on pagos.IDCONTRATO = contratos.IDCONTRATO "
                + "INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE "
                + "WHERE pagos.METODO = '"+METODO+"' and pagos.FECHA BETWEEN '"+ DE +"' AND '"+ AL +"' ";
        rs = mane.consultar(sql);
        ModeloPagos pto;

        while(rs.next())
        {

            pto = new ModeloPagos();
            pto.setIDNOTA(rs.getInt(1));
            pto.setNOMBRE(rs.getString(2));
            pto.setIDCONTRATO(rs.getInt(3));
            pto.setFECHA(rs.getString(4));
            pto.setMETODO(rs.getString(5));
            pto.setMONTO(rs.getFloat(6));
            pto.setCOMENTARIOS(rs.getString(7));
            m.add(pto);
            
        }
        mane.limpiarRecursos();
        return m;
    }
    
    public List getRegistrosIDCONT(int IDCONTRATO) throws Exception
    {
        ArrayList<ModeloPagos> m = new ArrayList<>();
        sql = "select IDNOTA,FECHA,METODO,MONTO,COMENTARIOS,DOC,REFERENCIA "
                + "from "+TABLA+" "
                + "where IDCONTRATO = "+IDCONTRATO+"";
        rs = mane.consultar(sql);
        ModeloPagos pto;
        while(rs.next())
        {
            pto = new ModeloPagos();
            pto.setIDNOTA(rs.getInt(1));
            pto.setFECHA(rs.getString(2));
            pto.setMETODO(rs.getString(3));
            pto.setMONTO(rs.getFloat(4));            
            pto.setCOMENTARIOS(rs.getString(5));
            pto.setDOC(rs.getString(6));
            pto.setREFERENCIA(rs.getString(7));

            m.add(pto);
        }
        mane.limpiarRecursos();
        return m;
    }
    
    public void actualizarPagos(int IDNOTA, String METODO, String COMENTARIOS, float MONTO, String REFERENCIA, String DOC) throws Exception
    {
        sql = "update "+TABLA+" set "
                + " METODO='" + METODO
                + "', REFERENCIA='" + REFERENCIA
                + "', DOC='" + DOC
                + "', COMENTARIOS='" + COMENTARIOS
                + "', MONTO = " + MONTO
                + " where IDNOTA=" + IDNOTA + " ";
        
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public void cancelarPago(int IDNOTA, float MONTO, String COMENTARIOS, String REFERENCIA) throws Exception
    {
        sql = " update "+TABLA+" set"
                //+ " IDCLIENTE=" + 0
                + " IDCONTRATO=" + 0
                + ", MONTO=" + MONTO
                + ", COMENTARIOS='" + COMENTARIOS
                + "', REFERENCIA= '" + REFERENCIA
                + "' where IDNOTA=" + IDNOTA + " ";
        
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
        public List listaCorteD(int IDPAGOI, String METODO, int IDCAJA) throws Exception
    {
        ArrayList<ModeloPagos> m = new ArrayList<>();
        sql = "SELECT pagos.IDNOTA, clientes.NOMBRE, pagos.MONTO "
                + "FROM "+TABLA+" "
                + "INNER JOIN contratos on pagos.IDCONTRATO = contratos.IDCONTRATO "
                + "INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE "
                + "WHERE pagos.METODO = '"+METODO+"' and pagos.IDNOTA > "+IDPAGOI+" AND pagos.IDCAJA = "+IDCAJA+" ";
        rs = mane.consultar(sql);
        ModeloPagos mdl;
        while(rs.next())
        {
            mdl = new ModeloPagos();
            mdl.setIDNOTA(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setMONTO(rs.getFloat(3));
            m.add(mdl);    
        }
        mane.limpiarRecursos();
        return m;
    }
    
    public float suma(float IDPAGOI, float IDPAGOF, String METODO, int IDCAJA) throws Exception
    {
        float suma = 0;
        if("".equals(METODO))
        {
            sql = "SELECT SUM(MONTO) FROM "+TABLA+" WHERE pagos.IDNOTA BETWEEN "+IDPAGOI+" AND "+IDPAGOF;
        }
        else
            sql = "SELECT SUM(MONTO) FROM "+TABLA+" WHERE pagos.IDNOTA BETWEEN "+IDPAGOI+" AND "+IDPAGOF+" AND pagos.METODO = '"+METODO+"' AND pagos.IDCAJA = "+IDCAJA+" ";
        
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getFloat(1);
        }
        mane.limpiarRecursos();
        return suma;
    }    
    
    public List listaCorteD(int IDPAGOI, int IDPAGOF, String METODO, int IDCAJA) throws Exception
    {
        ArrayList<ModeloPagos> m = new ArrayList<>();
        sql = "SELECT pagos.IDNOTA, clientes.NOMBRE, pagos.MONTO "
                + "FROM "+TABLA+" "
                + "INNER JOIN contratos on pagos.IDCONTRATO = contratos.IDCONTRATO "
                + "INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE "
                + "WHERE pagos.IDNOTA BETWEEN "+IDPAGOI+" AND "+IDPAGOF+" AND pagos.METODO = '"+METODO+"' AND pagos.IDCAJA = "+IDCAJA+" ";
        rs = mane.consultar(sql);
        ModeloPagos mdl;
        while(rs.next())
        {
            mdl = new ModeloPagos();
            mdl.setIDNOTA(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setMONTO(rs.getFloat(3));
            m.add(mdl);    
        }
        mane.limpiarRecursos();
        return m;
    }
    
    public List getRegistros(String CP) throws Exception {
        ArrayList<ModeloPagos> pagos = new ArrayList<>();
        /*switch(CP)
            {
                case "NO VERIFICADO":
                    sql = "SELECT pagos.IDNOTA, clientes.NOMBRE, pagos.IDCONTRATO, pagos.FECHA, pagos.METODO, pagos.MONTO, pagos.COMENTARIOS, pagos.IDCAJA FROM pagos INNER JOIN clientes on pagos.IDCLIENTE = clientes.IDCLIENTE WHERE pagos.REFERENCIA = '"+CP+"' ORDER BY pagos.IDNOTA DESC";
                    break;
                case "VERIFICADO SIN ENVIAR":
                    sql = "SELECT pagos.IDNOTA, clientes.NOMBRE, pagos.IDCONTRATO, pagos.FECHA, pagos.METODO, pagos.MONTO, pagos.COMENTARIOS, pagos.IDCAJA FROM pagos INNER JOIN clientes on pagos.IDCLIENTE = clientes.IDCLIENTE WHERE pagos.REFERENCIA = '"+CP+"' ORDER BY pagos.IDNOTA DESC";
                    
                    break;
                default:  
                    sql = "SELECT pagos.IDNOTA, clientes.NOMBRE, pagos.IDCONTRATO, pagos.FECHA, pagos.METODO, pagos.MONTO, pagos.COMENTARIOS, pagos.IDCAJA FROM pagos INNER JOIN clientes on pagos.IDCLIENTE = clientes.IDCLIENTE WHERE pagos.REFERENCIA LIKE 'PENDIENTE' AND pagos.COMENTARIOS LIKE '%"+CP+"%' ORDER BY pagos.IDNOTA DESC";
                    break;
            }*/
        sql = "SELECT pagos.IDNOTA, clientes.NOMBRE, pagos.IDCONTRATO, pagos.FECHA, pagos.METODO, pagos.MONTO, pagos.COMENTARIOS, pagos.DOC, pagos.IDCAJA "
                + "FROM "+TABLA+" "
                + "INNER JOIN contratos on pagos.IDCONTRATO = contratos.IDCONTRATO "
                + "INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE "
                + "WHERE pagos.REFERENCIA LIKE 'PENDIENTE' AND pagos.COMENTARIOS LIKE '%"+CP+"%' "
                + "ORDER BY pagos.IDNOTA DESC";
        rs = mane.consultar(sql);
        ModeloPagos pto;
        while(rs.next())
        {

            pto = new ModeloPagos();
            pto.setIDNOTA(rs.getInt(1));
            pto.setNOMBRE(rs.getString(2));
            pto.setIDCONTRATO(rs.getInt(3));
            pto.setFECHA(rs.getString(4));
            pto.setMETODO(rs.getString(5));
            pto.setMONTO(rs.getFloat(6));
            pto.setCOMENTARIOS(rs.getString(7));
            pto.setDOC(rs.getString(8));
            pto.setIDCAJA(rs.getInt(9));
            
            pagos.add(pto);

        }
        mane.limpiarRecursos();
        return pagos;
    }
    
    public List getRegistros(String REFERENCIA,int AUX) throws Exception {
        ArrayList<ModeloPagos> pagos = new ArrayList<>();        
        sql = "SELECT pagos.IDNOTA, clientes.NOMBRE, pagos.IDCONTRATO, pagos.FECHA, pagos.METODO, pagos.MONTO, pagos.COMENTARIOS, pagos.DOC, pagos.IDCAJA "
                + "FROM "+TABLA+" "
                + "INNER JOIN contratos on pagos.IDCONTRATO = contratos.IDCONTRATO "
                + "INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE "
                + "WHERE pagos.REFERENCIA = '"+REFERENCIA+"' "
                + "ORDER BY pagos.IDNOTA ASC";
        rs = mane.consultar(sql);
        ModeloPagos pto;
        while(rs.next())
        {
            pto = new ModeloPagos();
            pto.setIDNOTA(rs.getInt(1));
            pto.setNOMBRE(rs.getString(2));
            pto.setIDCONTRATO(rs.getInt(3));
            pto.setFECHA(rs.getString(4));
            pto.setMETODO(rs.getString(5));
            pto.setMONTO(rs.getFloat(6));
            pto.setCOMENTARIOS(rs.getString(7));
            pto.setDOC(rs.getString(8));
            pto.setIDCAJA(rs.getInt(9));
            
            pagos.add(pto);
        }

        mane.limpiarRecursos();
        return pagos;
    }
    
    public float pagosPT(String CP) throws Exception
    {
        sql = "SELECT SUM(pagos.MONTO) "
                + "FROM "+TABLA+" "
                + "WHERE pagos.REFERENCIA LIKE 'PENDIENTE' AND pagos.COMENTARIOS LIKE '%"+CP+"%'";
        rs = mane.consultar(sql);
        float saldo=0;
        while(rs.next())
        {
            saldo=rs.getFloat(1);
        }
        mane.limpiarRecursos();
        return saldo;
    }
    
    
    
    public ModeloPagos getRegistrosIDR(int IDNOTA) throws Exception {
        sql = "SELECT pagos.IDNOTA, PAGOS.FECHA, pagos.IDCONTRATO, pagos.METODO, pagos.MONTO, pagos.COMENTARIOS,  clientes.NOMBRE, contratos.FCOR, paquetes.COSTO "
                + "FROM "+TABLA+" "
                + "INNER JOIN contratos on pagos.IDCONTRATO = contratos.IDCONTRATO "
                + "INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE "
                + "INNER JOIN paquetes ON contratos.IDPAQUETE = paquetes.IDPAQUETE "
                + "WHERE pagos.IDNOTA ="+IDNOTA+"";
        rs = mane.consultar(sql);
        ModeloPagos pto = null;
        while(rs.next())
        {
            pto = new ModeloPagos();
            pto.setIDNOTA(rs.getInt(1));
            pto.setFECHA(rs.getString(2));
            pto.setIDCONTRATO(rs.getInt(3));
            pto.setMETODO(rs.getString(4));
            pto.setMONTO(rs.getFloat(5));
            pto.setCOMENTARIOS(rs.getString(6));
            pto.setNOMBRE(rs.getString(7));
            pto.setFCOR(rs.getString(8));           
            pto.setCOSTO(rs.getInt(9));
           
        }
        mane.limpiarRecursos();
        return pto;
    }
    
    public void actualizarReferencia (String REFERENCIA, int IDNOTA) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "REFERENCIA='" + REFERENCIA
                + "' where IDNOTA = " + IDNOTA + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();        
    }
    
    public void actualizarComentarios (String COMENTARIOS, int IDNOTA) throws Exception
    {
        sql = "update "+TABLA+" set "
                + "COMENTARIOS='" + COMENTARIOS
                + "' where IDNOTA = " + IDNOTA + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();        
    }
    
    public ModeloPagos getRegistrosAUT(String AUTORIZACION) throws Exception {
        sql = "SELECT pagos.IDNOTA, PAGOS.FECHA, pagos.IDCONTRATO, pagos.METODO, pagos.MONTO, pagos.COMENTARIOS,  clientes.NOMBRE, contratos.FCOR, paquetes.COSTO, pagos.DOC "
                + "FROM "+TABLA+" "
                + "INNER JOIN contratos on pagos.IDCONTRATO = contratos.IDCONTRATO "
                + "INNER JOIN clientes on contratos.IDCLIENTE = clientes.IDCLIENTE "
                + "INNER JOIN paquetes ON contratos.IDPAQUETE = paquetes.IDPAQUETE "
                + "WHERE pagos.COMENTARIOS LIKE '%"+AUTORIZACION+"%' AND pagos.METODO = 'OXXO' ";
        rs = mane.consultar(sql);
        ModeloPagos pto = null;
        while(rs.next())
        {
            pto = new ModeloPagos();
            pto.setIDNOTA(rs.getInt(1));
            pto.setFECHA(rs.getString(2));
            pto.setIDCONTRATO(rs.getInt(3));
            pto.setMETODO(rs.getString(4));
            pto.setMONTO(rs.getFloat(5));
            pto.setCOMENTARIOS(rs.getString(6));
            pto.setNOMBRE(rs.getString(7));
            pto.setFCOR(rs.getString(8));           
            pto.setCOSTO(rs.getInt(9));
            pto.setDOC(rs.getString(10));
        }
        mane.limpiarRecursos();
        return pto;
    }
    
    public ModeloPagos getRegistrosDIR(int IDNOTA) throws Exception {
        sql = "SELECT contratos.IDCONTRATO, direcciones.CALLE, direcciones.NUMERO, direcciones.COLONIA, direcciones.LOCALIDAD, direcciones.LATITUD, direcciones.LONGITUD, direcciones.REFERENCIA, clientes.NOMBRE "
                + "FROM "+TABLA+" "
                + "INNER JOIN contratos on pagos.IDCONTRATO = contratos.IDCONTRATO "
                + "INNER JOIN direcciones ON contratos.IDDIRECCION = direcciones.IDDIRECCION "
                + "INNER JOIN clientes ON direcciones.IDCLIENTE = clientes.IDCLIENTE "
                + "WHERE IDNOTA = "+IDNOTA;
        rs = mane.consultar(sql);
        ModeloPagos pto = null;
        while(rs.next())
        {
            pto = new ModeloPagos();
            pto.setIDCONTRATO(rs.getInt(1));
            pto.setDIRECCION(rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)
                    +" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7)
                    +" "+rs.getString(8));
            pto.setNOMBRE(rs.getString(9));
        }
        mane.limpiarRecursos();
        return pto;
    }
}
