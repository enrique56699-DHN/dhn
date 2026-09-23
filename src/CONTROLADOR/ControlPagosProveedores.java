/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloPagosProveedores;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
/*public class ControlPagosProveedores extends ControlAbstracto
{
    private final String TABLA = "PAGOSPROVEEDORES";

    public ControlPagosProveedores() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }
    
    private ModeloPagosProveedores mapearFila(ResultSet rs) throws SQLException {
        ModeloPagosProveedores mdl = new ModeloPagosProveedores();
        mdl.setIDPAGOPROV(rs.getInt("IDPAGOPROV"));
        mdl.setIDPROVEEDOR(rs.getInt("IDPROVEEDOR"));
        mdl.setFECHA(rs.getString("FECHA"));
        mdl.setIDPENDIENTE(rs.getInt("IDPENDIENTE"));
        mdl.setCARGO(rs.getFloat("CARGO"));
        mdl.setABONO(rs.getFloat("ABONO"));
        mdl.setSALDO(rs.getFloat("SALDO"));
        mdl.setDESCRIPCION(rs.getString("DESCRIPCION"));
        mdl.setESTADO(rs.getString("ESTADO"));
        mdl.setNOMBRE(rs.getString("NOMBRE"));
        return mdl;
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloPagosProveedores> lista = new ArrayList<>();
        sql = "select IDUSUARIO, NOMBRE, PASSWORD, PRIVILEGIOS from " + TABLA;
        rs = mane.consultar(sql);
        ModeloPagosProveedores mdl;
        while (rs.next()) {
            mdl = mapearFila(rs);
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloPagosProveedores mdl = (ModeloPagosProveedores) model;
        sql = "INSERT INTO " + TABLA + " (IDUSUARIO, NOMBRE, PASSWORD, PRIVILEGIOS) VALUES (?, ?, ?, ?)";
        mane.EjecutarSeguro(sql, mdl.getIDUSUARIO(), mdl.getNOMBRE(), hash, mdl.getPRIVILEGIOS());
        mane.limpiarRecursos();
    }
}
*/