/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.ModeloDirecciones;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oscar E Gonzalez G
 */
public class ControlDirecciones extends ControlAbstracto
{
    private final String TABLA = "DIRECCIONES";
    
    public ControlDirecciones() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloDirecciones> Lista = new ArrayList<>();
        sql = "select * from "+TABLA+"";
        rs = mane.consultar(sql);
        ModeloDirecciones mdl;
        while(rs.next())
        {
            mdl = new ModeloDirecciones();
            mdl.setIDDIRECCION(rs.getInt(1));
            mdl.setIDCLIENTE(rs.getInt(2));
            mdl.setCALLE(rs.getString(3));
            mdl.setNUMERO(rs.getString(4));
            mdl.setCOLONIA(rs.getString(5));
            mdl.setLOCALIDAD(rs.getString(6));
            mdl.setLATITUD(rs.getString(7));
            mdl.setLONGITUD(rs.getString(8));
            mdl.setREFERENCIA(rs.getString(9));
            
            Lista.add(mdl);
        }
        mane.limpiarRecursos();
        return Lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloDirecciones mdl = (ModeloDirecciones)model;
        sql = "INSERT INTO "+TABLA+" (IDDIRECCION,IDCLIENTE,CALLE,NUMERO,COLONIA,LOCALIDAD,LATITUD,LONGITUD,REFERENCIA)"
                + "VALUES (" + 
                mdl.getIDDIRECCION() + "," + 
                mdl.getIDCLIENTE() + ",'" +
                mdl.getCALLE() + "','" +
                mdl.getNUMERO() + "','" +
                mdl.getCOLONIA() + "','" +
                mdl.getLOCALIDAD() + "','" +
                mdl.getLATITUD() + "','" +
                mdl.getLONGITUD() + "','" +
                mdl.getREFERENCIA() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloDirecciones mdl = (ModeloDirecciones)model;
        sql = "update "+TABLA+" set "
                + "IDCLIENTE=" + mdl.getIDCLIENTE()
                + ",CALLE='" + mdl.getCALLE()
                + "',NUMERO='" + mdl.getNUMERO()
                + "',COLONIA='" + mdl.getCOLONIA()
                + "',LOCALIDAD='" + mdl.getLOCALIDAD()
                + "',LATITUD='" + mdl.getLATITUD()
                + "',LONGITUD='" + mdl.getLONGITUD()
                + "',REFERENCIA='" + mdl.getREFERENCIA()
                + "' where IDDIRECCION=" + mdl.getIDDIRECCION() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloDirecciones mdl = (ModeloDirecciones)model;
        sql=("delete from "+TABLA+" where IDDIRECCION =" + mdl.getIDDIRECCION() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public List getRegistros(int IDCLIENTE) throws Exception {
        ArrayList<ModeloDirecciones> Lista = new ArrayList<>();
        sql = "SELECT * FROM direcciones WHERE IDCLIENTE="+IDCLIENTE+"";
        rs = mane.consultar(sql);
        ModeloDirecciones mdl;
        while(rs.next())
        {
            mdl = new ModeloDirecciones();
            mdl.setIDDIRECCION(rs.getInt(1));
            mdl.setIDCLIENTE(rs.getInt(2));
            mdl.setCALLE(rs.getString(3));
            mdl.setNUMERO(rs.getString(4));
            mdl.setCOLONIA(rs.getString(5));
            mdl.setLOCALIDAD(rs.getString(6));
            mdl.setLATITUD(rs.getString(7));
            mdl.setLONGITUD(rs.getString(8));
            mdl.setREFERENCIA(rs.getString(9));
            
            Lista.add(mdl);
        }
        mane.limpiarRecursos();
        
        return Lista;
    }
    
    public ModeloDirecciones getRegistrosIDa(int ID) throws Exception {
        sql = "select * from "+TABLA+" where IDDIRECCION =" + ID + "";
        rs = mane.consultar(sql);
        ModeloDirecciones mdl = null;
        while(rs.next())
        {
            mdl = new ModeloDirecciones();
            mdl.setIDDIRECCION(rs.getInt(1));
            mdl.setIDCLIENTE(rs.getInt(2));
            mdl.setCALLE(rs.getString(3));
            mdl.setNUMERO(rs.getString(4));
            mdl.setCOLONIA(rs.getString(5));
            mdl.setLOCALIDAD(rs.getString(6));
            mdl.setLATITUD(rs.getString(7));
            mdl.setLONGITUD(rs.getString(8));
            mdl.setREFERENCIA(rs.getString(9));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public ModeloDirecciones getRegistrosIDC(int IDCLIENTE) throws Exception {
        sql = "select * from "+TABLA+" where IDCLIENTE =" + IDCLIENTE + "";
        rs = mane.consultar(sql);
        ModeloDirecciones mdl = null;
        while(rs.next())
        {
            mdl = new ModeloDirecciones();
            mdl.setIDDIRECCION(rs.getInt(1));
            mdl.setIDCLIENTE(rs.getInt(2));
            mdl.setCALLE(rs.getString(3));
            mdl.setNUMERO(rs.getString(4));
            mdl.setCOLONIA(rs.getString(5));
            mdl.setLOCALIDAD(rs.getString(6));
            mdl.setLATITUD(rs.getString(7));
            mdl.setLONGITUD(rs.getString(8));
            mdl.setREFERENCIA(rs.getString(9));
        }
        mane.limpiarRecursos();
        return mdl;
    }
}
