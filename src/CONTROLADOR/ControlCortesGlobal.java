/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloCortesGlobal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlCortesGlobal extends ControlAbstracto
{
    private final String TABLA = "CORTESG";
    
    public ControlCortesGlobal() throws ClassNotFoundException, SQLException
    {
        this.mane = new Manejador();
    }        
   
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloCortesGlobal> lista = new ArrayList<>();
        sql="SELECT * FROM "+TABLA;
        //sql = "SELECT cortes.IDCORTE, cortes.MONTOINI, cortes.FINI, cortes.FFIN, cortes.MONTOFIN, cortesd.IDPAGOI, cortesd.IDPAGOF, cortesd.IDENTRADAI, cortesd.IDENTRADAF, cortesd.IDSALIDAI, cortesd.IDSALIDAF, cortes.IDCAJA FROM cortes INNER JOIN cortesd ON cortesd.IDCORTE = cortes.IDCORTE ORDER BY IDCORTE DESC";
        rs = mane.consultar(sql);
        ModeloCortesGlobal mdl;
        while(rs.next())
        {
            mdl = new ModeloCortesGlobal();
            mdl.setIDCORTEG(rs.getInt(1));
            mdl.setDHN(rs.getFloat(2));
            mdl.setEDH(rs.getFloat(3));
            mdl.setVALES(rs.getFloat(4));
            mdl.setCF(rs.getFloat(5));
            mdl.setFECHA(rs.getString(6));
            mdl.setMONTOG(rs.getFloat(7));
            mdl.setMONTOR(rs.getFloat(8));

            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloCortesGlobal datos = (ModeloCortesGlobal)model;
        sql = "INSERT INTO "+TABLA+" (IDCORTEG, DHN, EDH, VALES, CF, FECHA, MONTOG, MONTOR)"
                + "VALUES (" + 
                datos.getIDCORTEG() + "," +
                datos.getDHN() + "," +
                datos.getEDH() + "," +
                datos.getVALES() + "," +
                datos.getCF() + ",'" +
                datos.getFECHA() + "'," +
                datos.getMONTOG() + "," +
                datos.getMONTOR() + ")"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloCortesGlobal pto = (ModeloCortesGlobal)model;
        sql = "update "+TABLA+" set "
                + "DHN = '" + pto.getDHN()
                + "EDH = " + pto.getEDH()
                + ", VALES = " + pto.getVALES()
                + ", CF = " + pto.getCF()
                + ", FECHA = '" + pto.getFECHA()
                + "', MONTOG = " + pto.getMONTOG()
                + ", MONTOR = " + pto.getMONTOR()
                + " where IDCORTEG =" + pto.getIDCORTEG() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloCortesGlobal datos = (ModeloCortesGlobal)model;
        sql=("delete from "+TABLA+" where IDCORTEG =" + datos.getIDCORTEG() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    public ModeloCortesGlobal getRegistroID(Integer code) throws Exception {
        sql = "select * from "+TABLA+" where IDCORTEG = "+code;
        rs = mane.consultar(sql);
        ModeloCortesGlobal mdl = new ModeloCortesGlobal();
        while(rs.next())
        {   
            mdl.setIDCORTEG(rs.getInt(1));
            mdl.setDHN(rs.getFloat(2));
            mdl.setEDH(rs.getFloat(3));
            mdl.setVALES(rs.getFloat(4));
            mdl.setCF(rs.getFloat(5));
            mdl.setFECHA(rs.getString(6));
            mdl.setMONTOG(rs.getFloat(7));
            mdl.setMONTOR(rs.getFloat(8));

        }
        mane.limpiarRecursos();
        return mdl;
    }
    
}
