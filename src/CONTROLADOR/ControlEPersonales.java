/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloEPersonales;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author win 10
 */
public class ControlEPersonales extends ControlAbstracto
{
    private final String TABLA = "EPERSONALES";

    public ControlEPersonales() throws ClassNotFoundException, SQLException {
         this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloEPersonales> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloEPersonales mdl;
        while(rs.next())
        {
            mdl = new ModeloEPersonales();
            mdl.setIDEPERSONAL(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setFNACIMIENTO(rs.getString(3));
            mdl.setSEXO(rs.getString(4));
            mdl.setEDOCIVIL(rs.getString(5));
            mdl.setNACIONALIDAD(rs.getString(6));            
            mdl.setCURP(rs.getString(7));
            mdl.setRFC(rs.getString(8));
            mdl.setNSS(rs.getString(9));
            mdl.setCALLE(rs.getString(10));
            mdl.setNUMERO(rs.getString(11));
            mdl.setCOLONIA(rs.getString(12));
            mdl.setLOCALIDAD(rs.getString(13));

            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloEPersonales pto = (ModeloEPersonales)model;
        sql = "INSERT INTO "+TABLA+" (IDEPERSONAL,IDEMPLEADO,FNACIMIENTO,SEXO,EDOCIVIL,NACIONALIDAD, CURP, RFC, NSS, CALLE, NUMERO, COLONIA, LOCALIDAD)"
                + "VALUES (" + 
                pto.getIDEPERSONAL() + "," + 
                pto.getIDEMPLEADO() + ",'" +
                pto.getFNACIMIENTO() + "','" +
                pto.getSEXO() + "','" +
                pto.getEDOCIVIL()+ "','" +
                pto.getNACIONALIDAD() + "','" +
                pto.getCURP() + "','" +
                pto.getRFC() + "','" +
                pto.getNSS() + "','" +
                pto.getCALLE() + "','" +
                pto.getNUMERO() + "','" +
                pto.getCOLONIA() + "','" +                
                pto.getLOCALIDAD() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloEPersonales pto = (ModeloEPersonales)model;
        sql = "update "+TABLA+" set "
                //+ "IDEMPLEADO = " + pto.getIDEMPLEADO()
                + "FNACIMIENTO = '" + pto.getFNACIMIENTO()
                + "' , SEXO = '" + pto.getSEXO()
                + "' , EDOCIVIL = '" + pto.getEDOCIVIL()
                + "' , NACIONALIDAD = '" + pto.getNACIONALIDAD()
                + "' , CURP = '" + pto.getCURP()
                + "' , RFC = '" + pto.getRFC()
                + "' , NSS = '" + pto.getNSS()
                + "' , CALLE = '" + pto.getCALLE()
                + "' , NUMERO = '" + pto.getNUMERO()
                + "' , COLONIA = '" + pto.getCOLONIA()
                + "' , LOCALIDAD = '" + pto.getLOCALIDAD()
                + "' where IDEMPLEADO =" + pto.getIDEMPLEADO() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloEPersonales pto = (ModeloEPersonales)model;
        sql=("delete from "+TABLA+" where IDEMPLEADO =" + pto.getIDEMPLEADO() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloEPersonales getRegistro(int ID) throws Exception {
        sql = "select * from "+TABLA+" where IDEMPLEADO = "+ID;
        rs = mane.consultar(sql);
        ModeloEPersonales mdl = new ModeloEPersonales();
        while(rs.next())
        {   
            mdl.setIDEPERSONAL(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setFNACIMIENTO(rs.getString(3));
            mdl.setSEXO(rs.getString(4));
            mdl.setEDOCIVIL(rs.getString(5));
            mdl.setNACIONALIDAD(rs.getString(6));            
            mdl.setCURP(rs.getString(7));
            mdl.setRFC(rs.getString(8));
            mdl.setNSS(rs.getString(9));
            mdl.setCALLE(rs.getString(10));
            mdl.setNUMERO(rs.getString(11));
            mdl.setCOLONIA(rs.getString(12));
            mdl.setLOCALIDAD(rs.getString(13));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public List getRegistros(int IDEPUESTO, String CAMPO, String ATRIBUTO) throws Exception {
        ArrayList<ModeloEPersonales> lista = new ArrayList<>();
        if(null==CAMPO)
            sql = "select DISTINCT * from "+TABLA+" where IDEPUESTO ="+IDEPUESTO+"";
        else switch (CAMPO) {
            case "LISTA":
                sql = "select DISTINCT * from "+TABLA+" GROUP BY DEPTO";
                break;
            default:
                sql = "select DISTINCT * from "+TABLA+" where "+CAMPO+" = '"+ATRIBUTO+"' GROUP BY PUESTO";
                break;
        }
        
        rs = mane.consultar(sql);
        ModeloEPersonales mdl;  
        while(rs.next())
        {
            mdl = new ModeloEPersonales();
            mdl.setIDEPERSONAL(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setFNACIMIENTO(rs.getString(3));
            mdl.setSEXO(rs.getString(4));
            mdl.setEDOCIVIL(rs.getString(5));
            mdl.setNACIONALIDAD(rs.getString(6));            
            mdl.setCURP(rs.getString(7));
            mdl.setRFC(rs.getString(8));
            mdl.setNSS(rs.getString(9));
            mdl.setCALLE(rs.getString(10));
            mdl.setNUMERO(rs.getString(11));
            mdl.setCOLONIA(rs.getString(12));
            mdl.setLOCALIDAD(rs.getString(13));
            
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }
    
}
