/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloVacacionesTAB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlVacacionesTAB extends ControlAbstracto
{
    private final String TABLA = "VACACIONESTAB";
    
    public ControlVacacionesTAB() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloVacacionesTAB> lista = new ArrayList<>();
        sql = "select * from "+TABLA+" ORDER BY IDVACACIONTAB ASC";
        rs = mane.consultar(sql);
        //rs = mane.consultar1(sql);
        ModeloVacacionesTAB x;
        while(rs.next())
        {
            x = new ModeloVacacionesTAB();
            x.setIDVACACIONTAB(rs.getInt(1));
            x.setDIAS(rs.getInt(2));
            lista.add(x);

        }
        mane.limpiarRecursos();
        return lista;
    }
    
    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloVacacionesTAB x = (ModeloVacacionesTAB)model;
        sql = "INSERT INTO "+TABLA+" (IDVACACIONTAB, DIAS)"
            + "VALUES (" + 
            x.getIDVACACIONTAB() + "," +    
            x.getDIAS() + " )"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloVacacionesTAB x = (ModeloVacacionesTAB)model;
        sql = "update "+TABLA+" set "
                + " DIAS = " + x.getDIAS()
                + " where IDVACACIONTAB = " + x.getIDVACACIONTAB() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloVacacionesTAB x = (ModeloVacacionesTAB)model;
        sql=("delete from "+TABLA+" where IDVACACIONTAB =" + x.getIDVACACIONTAB() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }    
    
    public ModeloVacacionesTAB getRegistrosID(int ID) throws Exception
    {
        sql = "select * from "+TABLA+" where IDVACACIONTAB= "+ID+"";
        rs = mane.consultar(sql);
        
        ModeloVacacionesTAB x = null;
        while(rs.next())
        {
            x = new ModeloVacacionesTAB();
            x.setIDVACACIONTAB(rs.getInt(1));
            x.setDIAS(rs.getInt(2));

        }
        mane.limpiarRecursos();
        return x;
    }
    
    
}
