/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloVehiculosSER;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlVehiculosSER extends ControlAbstracto
{
    private final String TABLA = "VEHICULOSSER";

    public ControlVehiculosSER() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloVehiculosSER> lista = new ArrayList<>();
        sql = "SELECT vehiculosser.IDVEHICULOSER, vehiculosser.IDVEHICULO, vehiculos.MARCA, vehiculos.MODELO, vehiculosser.EMPRESA, vehiculosser.TIPO, vehiculosser.COMENTARIOS, vehiculosser.FECHA, vehiculosser.KM, vehiculosser.COSTO "
                + "FROM "+TABLA+" INNER JOIN vehiculos ON vehiculosser.IDVEHICULO = vehiculos.IDVEHICULO ";
        rs = mane.consultar(sql);
        ModeloVehiculosSER mdl;
        while(rs.next())
        {
            mdl = new ModeloVehiculosSER();
            mdl.setIDVEHICULOSER(rs.getInt(1));
            mdl.setIDVEHICULO(rs.getInt(2));
            mdl.setMARCA(rs.getString(3));
            mdl.setMODELO(rs.getString(4));
            mdl.setEMPRESA(rs.getString(5));
            mdl.setTIPO(rs.getString(6));
            mdl.setCOMENTARIOS(rs.getString(7));
            mdl.setFECHA(rs.getString(8));
            mdl.setKM(rs.getInt(9));            
            mdl.setCOSTO(rs.getFloat(10));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloVehiculosSER mdl = (ModeloVehiculosSER)model;
        sql = "INSERT INTO "+TABLA+" (IDVEHICULOSER, IDVEHICULO, TIPO, COMENTARIOS, FECHA, KM, EMPRESA, COSTO)"
                + "VALUES (" + 
                mdl.getIDVEHICULOSER() + "," +
                mdl.getIDVEHICULO() + ",'" +
                mdl.getTIPO() + "','" +
                mdl.getCOMENTARIOS() + "','" +
                mdl.getFECHA() + "'," +
                mdl.getKM() + ",'" +
                mdl.getEMPRESA() + "'," +
                mdl.getCOSTO() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloVehiculosSER mdl = (ModeloVehiculosSER)model;
        sql = "update "+TABLA+" set "
                + "IDVEHICULO = " + mdl.getIDVEHICULO()
                + ",TIPO = '" + mdl.getTIPO()
                + "',COMENTARIOS = '" + mdl.getCOMENTARIOS()
                + "',FECHA = '" + mdl.getFECHA()
                + "',KM = " + mdl.getKM()
                + ",EMPRESA = '" + mdl.getEMPRESA()
                + "',COSTO = " + mdl.getCOSTO()
                + " where IDVEHICULOSER=" + mdl.getIDVEHICULOSER() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloVehiculosSER mdl = (ModeloVehiculosSER)model;
        sql=("delete from "+TABLA+" where IDVEHICULOSER =" + mdl.getIDVEHICULOSER() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloVehiculosSER getRegistros(int IDVEHICULOSER) throws Exception {
        sql = "select * from "+TABLA+" WHERE IDVEHICULOSER =" +IDVEHICULOSER+" ";
        rs = mane.consultar(sql);
        ModeloVehiculosSER mdl = null;
        while(rs.next())
        {
            mdl = new ModeloVehiculosSER();
            mdl.setIDVEHICULOSER(rs.getInt(1));
            mdl.setIDVEHICULO(rs.getInt(2));
            mdl.setTIPO(rs.getString(3));
            mdl.setCOMENTARIOS(rs.getString(4));
            mdl.setFECHA(rs.getString(5));
            mdl.setKM(rs.getInt(6));
            mdl.setEMPRESA(rs.getString(7));
            mdl.setCOSTO(rs.getFloat(8));

        }
        mane.limpiarRecursos();
        return mdl;
    }
}
