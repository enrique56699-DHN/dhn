/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloVehiculos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlVehiculos extends ControlAbstracto
{
    private final String TABLA = "VEHICULOS";

    public ControlVehiculos() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    } 

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloVehiculos> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloVehiculos mdl;
        while(rs.next())
        {
            mdl = new ModeloVehiculos();
            mdl.setIDVEHICULO(rs.getInt(1));
            mdl.setMARCA(rs.getString(2));
            mdl.setMODELO(rs.getString(3));
            mdl.setNS(rs.getString(4));
            mdl.setNMOTOR(rs.getString(5));
            mdl.setPLACA(rs.getString(6));
            mdl.setEMPRESA(rs.getString(7));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloVehiculos mdl = (ModeloVehiculos)model;
        sql = "INSERT INTO "+TABLA+" (IDVEHICULO, MARCA, MODELO, NS, NMOTOR, PLACA, EMPRESA)"
                + "VALUES (" + 
                mdl.getIDVEHICULO() + ",'" +
                mdl.getMARCA() + "','" +
                mdl.getMODELO() + "','" +
                mdl.getNS() + "','" +
                mdl.getNMOTOR() + "','" +
                mdl.getPLACA() + "','" +
                mdl.getEMPRESA() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloVehiculos mdl = (ModeloVehiculos)model;
        sql = "update "+TABLA+" set "
                + "MARCA = '" + mdl.getMARCA()
                + "',MODELO = '" + mdl.getMODELO()
                + "',NS = '" + mdl.getNS()
                + "',NMOTOR = '" + mdl.getNMOTOR()
                + "',PLACA = '" + mdl.getPLACA()
                + "',EMPRESA = '" + mdl.getEMPRESA()
                + "' where IDVEHICULO=" + mdl.getIDVEHICULO() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloVehiculos mdl = (ModeloVehiculos)model;
        sql=("delete from "+TABLA+" where IDVEHICULO =" + mdl.getIDVEHICULO() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloVehiculos getRegistros(int IDVEHICULO) throws Exception {
        sql = "select * from "+TABLA+" WHERE IDVEHICULO = "+IDVEHICULO+" ";
        rs = mane.consultar(sql);
        ModeloVehiculos mdl = null;
        while(rs.next())
        {
            mdl = new ModeloVehiculos();
            mdl.setIDVEHICULO(rs.getInt(1));
            mdl.setMARCA(rs.getString(2));
            mdl.setMODELO(rs.getString(3));
            mdl.setNS(rs.getString(4));
            mdl.setNMOTOR(rs.getString(5));
            mdl.setPLACA(rs.getString(6));
            mdl.setEMPRESA(rs.getString(7));

        }
        mane.limpiarRecursos();
        return mdl;
    }
    
}
