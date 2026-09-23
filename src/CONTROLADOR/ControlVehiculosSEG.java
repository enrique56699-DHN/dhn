/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloVehiculosSEG;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlVehiculosSEG extends ControlAbstracto
{
    private final String TABLA = "VEHICULOSSEG";

    public ControlVehiculosSEG() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloVehiculosSEG> lista = new ArrayList<>();
        //sql = "select * from "+TABLA+" ";        
        sql = "SELECT vehiculosseg.IDVEHICULOSEG, vehiculosseg.IDVEHICULO, vehiculos.MARCA, vehiculos.MODELO, vehiculosseg.ASEGURADORA, vehiculosseg.NP, vehiculosseg.FECHA, vehiculosseg.VIGENCIA, vehiculosseg.ARCHIVO, vehiculosseg.PROPIETARIO, vehiculosseg.COSTO, vehiculosseg.EMPRESA FROM "+TABLA+" INNER JOIN vehiculos ON vehiculosseg.IDVEHICULO = vehiculos.IDVEHICULO";
        rs = mane.consultar(sql);
        ModeloVehiculosSEG mdl;
        while(rs.next())
        {
            mdl = new ModeloVehiculosSEG();
            mdl.setIDVEHICULOSEG(rs.getInt(1));
            mdl.setIDVEHICULO(rs.getInt(2));
            mdl.setMARCA(rs.getString(3));
            mdl.setMODELO(rs.getString(4));
            mdl.setASEGURADORA(rs.getString(5));
            mdl.setNP(rs.getString(6));
            mdl.setFECHA(rs.getString(7));
            mdl.setVIGENCIA(rs.getString(8));
            mdl.setARCHIVO(rs.getString(9));
            mdl.setPROPIETARIO(rs.getString(10));
            mdl.setCOSTO(rs.getFloat(11));
            mdl.setEMPRESA(rs.getString(12));
                    
            //mdl.setNOMBRE(rs.getString(6));
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloVehiculosSEG mdl = (ModeloVehiculosSEG)model;
        sql = "INSERT INTO "+TABLA+" (IDVEHICULOSEG, IDVEHICULO, ASEGURADORA, NP, FECHA, VIGENCIA, ARCHIVO, PROPIETARIO, COSTO,EMPRESA)"
                + "VALUES (" + 
                mdl.getIDVEHICULOSEG() + "," +
                mdl.getIDVEHICULO() + ",'" +
                mdl.getASEGURADORA() + "','" +
                mdl.getNP() + "','" +
                mdl.getFECHA() + "'," +
                mdl.getVIGENCIA() + "','" +
                mdl.getARCHIVO() + "','" +
                mdl.getPROPIETARIO() + "'," +
                mdl.getCOSTO() + ",'" +
                mdl.getEMPRESA() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloVehiculosSEG mdl = (ModeloVehiculosSEG)model;
        sql = "update "+TABLA+" set "
                + "IDVEHICULO = " + mdl.getIDVEHICULO()
                + ",ASEGURADORA = '" + mdl.getASEGURADORA()
                + "',NP = '" + mdl.getNP()
                + "',FECHA = '" + mdl.getFECHA()
                + "',VIGENCIA = " + mdl.getVIGENCIA()
                + ",ARCHIVO = '" + mdl.getARCHIVO()
                + "',PROPIETARIO = '" + mdl.getPROPIETARIO()
                + "',COSTO = " + mdl.getCOSTO()
                + ",EMPRESA = '" + mdl.getEMPRESA()
                + "' where IDVEHICULOSEG=" + mdl.getIDVEHICULOSEG() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloVehiculosSEG mdl = (ModeloVehiculosSEG)model;
        sql=("delete from "+TABLA+" where IDVEHICULOSEG =" + mdl.getIDVEHICULOSEG() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloVehiculosSEG getRegistros(int IDVEHICULOSEG) throws Exception {
        sql = "select * from "+TABLA+" WHERE IDVEHICULOSEG = "+IDVEHICULOSEG+" ";
        rs = mane.consultar(sql);
        ModeloVehiculosSEG mdl = null;
        while(rs.next())
        {
            mdl = new ModeloVehiculosSEG();
            mdl.setIDVEHICULOSEG(rs.getInt(1));
            mdl.setIDVEHICULO(rs.getInt(2));
            mdl.setASEGURADORA(rs.getString(3));
            mdl.setNP(rs.getString(4));
            mdl.setFECHA(rs.getString(5));
            mdl.setVIGENCIA(rs.getString(6));
            mdl.setARCHIVO(rs.getString(7));
            mdl.setPROPIETARIO(rs.getString(8));
            mdl.setCOSTO(rs.getFloat(9));
            mdl.setEMPRESA(rs.getString(10));

        }
        mane.limpiarRecursos();
        return mdl;
    }
    
}
