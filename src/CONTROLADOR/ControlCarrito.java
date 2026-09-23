/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloCarrito;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlCarrito extends ControlAbstracto
{
    private final String TABLA = "CARRITO";
    
    public ControlCarrito() throws ClassNotFoundException, SQLException
    {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloCarrito> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloCarrito mdl;
        while(rs.next())
        {
            mdl = new ModeloCarrito();
            mdl.setIDCARRITO(rs.getInt(1));
            mdl.setIDPENDIENTE(rs.getInt(2));
            mdl.setIDPRODUCTO(rs.getInt(3));
            mdl.setCANTIDAD(rs.getInt(4));
            mdl.setTOTAL(rs.getDouble(5));
            mdl.setIDEMPLEADO(rs.getInt(6));
            mdl.setESTADO(rs.getString(7));
            mdl.setFECHA(rs.getString(8));
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloCarrito datos = (ModeloCarrito)model;
        sql = "INSERT INTO "+TABLA+" (IDCARRITO, IDPENDIENTE, IDPRODUCTO, CANTIDAD, TOTAL, IDEMPLEADO, ESTADO, FECHA)"
                + "VALUES (" + 
                datos.getIDCARRITO() + "," +
                datos.getIDPENDIENTE() + "," +
                datos.getIDPRODUCTO() + "," +
                datos.getCANTIDAD() + "," +
                datos.getTOTAL() + "," +
                datos.getIDEMPLEADO() + ",'" +
                datos.getESTADO() + "','" +
                datos.getFECHA() + "' )"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloCarrito mdl = (ModeloCarrito)model;
        sql = "update "+TABLA+" set "
                + "IDPENDIENTE = " + mdl.getIDPENDIENTE()
                + " ,IDPRODUCTO = " + mdl.getIDPRODUCTO()
                + " ,CANTIDAD = " + mdl.getCANTIDAD()
                + " ,TOTAL = " + mdl.getTOTAL()
                + " , IDEMPLEADO = " + mdl.getIDEMPLEADO()
                + " , ESTADO = '" + mdl.getESTADO()
                + "' , FECHA = '" + mdl.getFECHA()
                + "' where IDCARRITO=" + mdl.getIDCARRITO() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloCarrito mdl = (ModeloCarrito)model;
        sql=("delete from CARRITO where IDCARRITO =" + mdl.getIDCARRITO() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    
    }
    
    public List getRegistrosORDEN(int IDPENDIENTE) throws Exception {
        ArrayList<ModeloCarrito> lista = new ArrayList<>();
        sql = "SELECT carrito.IDCARRITO, carrito.CANTIDAD, productos.SICAR, productos.DESCRIPCION, carrito.TOTAL, carrito.ESTADO, carrito.IDEMPLEADO FROM carrito INNER JOIN productos ON carrito.IDPRODUCTO = productos.IDPRODUCTO WHERE carrito.IDPENDIENTE = "+IDPENDIENTE+" ";
        rs = mane.consultar(sql);
        ModeloCarrito mdl;
        while(rs.next())
        {
            mdl = new ModeloCarrito();
            mdl.setIDCARRITO(rs.getInt(1));
            mdl.setCANTIDAD(rs.getInt(2));
            mdl.setSICAR(rs.getString(3));
            mdl.setDESCRIPCION(rs.getString(4));
            mdl.setTOTAL(rs.getDouble(5));
            mdl.setESTADO(rs.getString(6));
            mdl.setIDEMPLEADO(rs.getInt(7));
            //mdl.setFECHA(rs.getString(8));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public float total(float IDPENDIENTE) throws Exception
    {
        float suma = 0;
        /*if("".equals(METODO))
        {
            sql = "SELECT SUM(MONTO) FROM pagos WHERE pagos.IDNOTA BETWEEN "+IDPAGOI+" AND "+IDPAGOF;
        }
        else
            sql = "SELECT SUM(MONTO) FROM pagos WHERE pagos.IDNOTA BETWEEN "+IDPAGOI+" AND "+IDPAGOF+" AND pagos.METODO = '"+METODO+"' AND pagos.IDCAJA = "+IDCAJA+" ";
        */
        sql = "SELECT SUM(TOTAL) FROM carrito WHERE carrito.IDPENDIENTE ="+IDPENDIENTE+" AND ESTADO = 'USADO'";
        rs = mane.consultar(sql);
        while(rs.next())
        {
            suma=rs.getFloat(1);
        }
        
        mane.limpiarRecursos();
        return suma;
    }
    
    public void actualizarESTADO(int IDCARRITO, String ESTADO, String FECHA, int IDEMPLEADO) throws Exception
    {
        sql=("UPDATE "+TABLA+" SET estado = '"+ESTADO+"', fecha = '"+FECHA+"', idempleado = "+IDEMPLEADO+" where IDCARRITO = " + IDCARRITO + " ");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public void actualizarSERVICIO(int IDCARRITO, int IDPENDIENTE) throws Exception
    {
        sql=("UPDATE "+TABLA+" SET IDPENDIENTE = "+IDPENDIENTE+" where IDCARRITO = " + IDCARRITO + " ");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloCarrito getRegistrosID(int IDPENDIENTE) throws Exception {
        sql = "select * from "+TABLA+" WHERE IDPENDIENTE = "+IDPENDIENTE+" ";
        rs = mane.consultar(sql);
        ModeloCarrito mdl = null;
        while(rs.next())
        {
            mdl = new ModeloCarrito();
            mdl.setIDCARRITO(rs.getInt(1));
            mdl.setIDPENDIENTE(rs.getInt(2));
            mdl.setIDPRODUCTO(rs.getInt(3));
            mdl.setCANTIDAD(rs.getInt(4));
            mdl.setTOTAL(rs.getDouble(5));
            mdl.setIDEMPLEADO(rs.getInt(6));
            mdl.setESTADO(rs.getString(7));
            mdl.setFECHA(rs.getString(8));
            
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public List getRegistros(String ESTADO, String FECHA, int IDEMPLEADO) throws Exception {
        ArrayList<ModeloCarrito> lista = new ArrayList<>();
        if(FECHA.equals(""))
        {
            sql = "SELECT carrito.IDCARRITO, carrito.CANTIDAD, productos.DESCRIPCION, carrito.IDPENDIENTE FROM "+TABLA+" INNER JOIN productos ON carrito.IDPRODUCTO = productos.IDPRODUCTO WHERE carrito.ESTADO = '"+ESTADO+"' AND carrito.IDEMPLEADO = "+IDEMPLEADO+" ";
            if(IDEMPLEADO==0)
            {
                sql = "SELECT carrito.IDCARRITO, carrito.CANTIDAD, productos.DESCRIPCION, carrito.IDPENDIENTE FROM "+TABLA+" INNER JOIN productos ON carrito.IDPRODUCTO = productos.IDPRODUCTO WHERE carrito.ESTADO = '"+ESTADO+"' ";
            }
        }
        else
        {
            sql = "SELECT carrito.IDCARRITO, carrito.CANTIDAD, productos.DESCRIPCION, carrito.IDPENDIENTE FROM "+TABLA+" INNER JOIN productos ON carrito.IDPRODUCTO = productos.IDPRODUCTO WHERE carrito.ESTADO = '"+ESTADO+"' AND carrito.IDEMPLEADO = "+IDEMPLEADO+"  AND carrito.FECHA ='"+FECHA+"' ";
            if(IDEMPLEADO==0)
            {
                sql = "SELECT carrito.IDCARRITO, carrito.CANTIDAD, productos.DESCRIPCION, carrito.IDPENDIENTE FROM "+TABLA+" INNER JOIN productos ON carrito.IDPRODUCTO = productos.IDPRODUCTO WHERE carrito.ESTADO = '"+ESTADO+"' AND carrito.FECHA ='"+FECHA+"' ";
            }
        }            
        
        rs = mane.consultar(sql);
        ModeloCarrito mdl;
        while(rs.next())
        {
            mdl = new ModeloCarrito();
            mdl.setIDCARRITO(rs.getInt(1));
            mdl.setCANTIDAD(rs.getInt(2));
            mdl.setDESCRIPCION(rs.getString(3));
            mdl.setIDPENDIENTE(rs.getInt(4));
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    public ModeloCarrito getRegistros(int IDCARRITO) throws Exception {
        sql = "SELECT carrito.IDCARRITO, carrito.IDPENDIENTE, carrito.IDPRODUCTO, carrito.CANTIDAD, carrito.TOTAL, carrito.IDEMPLEADO, carrito.ESTADO, productos.DESCRIPCION FROM carrito INNER JOIN productos ON carrito.IDPRODUCTO = productos.IDPRODUCTO WHERE carrito.IDCARRITO = "+IDCARRITO+" ";
        rs = mane.consultar(sql);
        ModeloCarrito mdl = null;
        while(rs.next())
        {
            mdl = new ModeloCarrito();
            mdl.setIDCARRITO(rs.getInt(1));
            mdl.setIDPENDIENTE(rs.getInt(2));
            mdl.setIDPRODUCTO(rs.getInt(3));
            mdl.setCANTIDAD(rs.getInt(4));
            mdl.setTOTAL(rs.getDouble(5));
            mdl.setIDEMPLEADO(rs.getInt(6));
            mdl.setESTADO(rs.getString(7));
            mdl.setDESCRIPCION(rs.getString(8));
            
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
   
}
