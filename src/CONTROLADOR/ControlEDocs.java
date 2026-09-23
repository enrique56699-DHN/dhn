/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloEDocs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ControlEDocs extends ControlAbstracto
{
    private final String TABLA = "EDOCS";

    public ControlEDocs() throws ClassNotFoundException, SQLException {
         this.mane = new Manejador();
    }
    
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloEDocs> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ";
        rs = mane.consultar(sql);
        ModeloEDocs mdl;
        while(rs.next())
        {
            mdl = new ModeloEDocs();
            mdl.setIDEDOC(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setNUMBER(rs.getString(3));
            mdl.setDESCRIPCION(rs.getString(4));
            mdl.setVIGENCIA(rs.getString(5));
            mdl.setDOC(rs.getString(6));

            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloEDocs pto = (ModeloEDocs)model;
        sql = "INSERT INTO "+TABLA+" (IDEDOC,IDEMPLEADO,NUMBER,DESCRIPCION,VIGENCIA,DOC)"
                + "VALUES (" + 
                pto.getIDEDOC() + "," + 
                pto.getIDEMPLEADO() + ",'" +
                pto.getNUMBER() + "','" +
                pto.getDESCRIPCION() + "','" +                
                pto.getVIGENCIA() + "','" +
                pto.getDOC() + "')"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloEDocs pto = (ModeloEDocs)model;
        sql = "update "+TABLA+" set "
                + "IDEMPLEADO=" + pto.getIDEMPLEADO()
                + ",NUMBER='" + pto.getNUMBER()
                + "',DESCRIPCION='" + pto.getDESCRIPCION()
                + "',VIGENCIA='" + pto.getVIGENCIA()
                + "',DOC='" + pto.getDOC()
                + "' where IDEDOC=" + pto.getIDEDOC() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloEDocs pto = (ModeloEDocs)model;
        sql=("delete from "+TABLA+" where IDEDOC =" + pto.getIDEDOC() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloEDocs getRegistro(Integer code) throws Exception {
        sql = "select * from "+TABLA+" where IDEDOC = "+code;
        rs = mane.consultar(sql);
        ModeloEDocs mdl = new ModeloEDocs();
        while(rs.next())
        {   
            mdl.setIDEDOC(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setNUMBER(rs.getString(3));
            mdl.setDESCRIPCION(rs.getString(4));
            mdl.setVIGENCIA(rs.getString(5));
            mdl.setDOC(rs.getString(6));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public List getRegistros(int IDEMPLEADO) throws Exception {
        ArrayList<ModeloEDocs> antenas = new ArrayList<>();
        sql = "select * from "+TABLA+" where IDEMPLEADO ="+IDEMPLEADO+"";
        rs = mane.consultar(sql);
        ModeloEDocs mdl;  
        while(rs.next())
        {
            mdl = new ModeloEDocs();
            mdl.setIDEDOC(rs.getInt(1));
            mdl.setIDEMPLEADO(rs.getInt(2));
            mdl.setNUMBER(rs.getString(3));
            mdl.setDESCRIPCION(rs.getString(4));
            mdl.setVIGENCIA(rs.getString(5));
            mdl.setDOC(rs.getString(6));
            
            antenas.add(mdl);
        }
        mane.limpiarRecursos();
        return antenas;
    }
    
    
}
