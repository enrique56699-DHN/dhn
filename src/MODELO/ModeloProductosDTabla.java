/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlProductosD;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author enrique.orozco
 */
public class ModeloProductosDTabla extends AbstractTableModel
{
    String[]titulos = new String[4];
    ArrayList<ModeloProductosD> mdl = new ArrayList<>();
    ControlProductosD ctrl;
    int CONSULTA = 0;
    
    public ModeloProductosDTabla() throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 0;
        titulos[0] ="CODIGO DE BARRAS";
        titulos[1] ="SICAR";
        titulos[2] ="CATEGORIA";
        titulos[3] ="DESCRIPCION";
        
        ctrl = new ControlProductosD();
        try 
        {
            mdl = (ArrayList<ModeloProductosD>) ctrl.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA KITS DETALLE");
        }
    }
    
    public ModeloProductosDTabla(String ARGUMENTO,String ARG) throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        titulos = new String[5];
        CONSULTA = 1;
        titulos[0] ="CODIGO DE BARRAS";
        titulos[1] ="SICAR";
        titulos[2] ="CATEGORIA";
        titulos[3] ="DESCRIPCION";
        titulos[4] ="IDPRODUCTO";
        
        ctrl = new ControlProductosD();
        try 
        {
            mdl = (ArrayList<ModeloProductosD>) ctrl.getRegistrosID(ARGUMENTO,ARG);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA PRODUCTOSD CON NOMBRE ");
        }
    }
                
    @Override
    public int getRowCount() {
        return mdl.size();
    }

    @Override
    public int getColumnCount() {
        return titulos.length;
    }

    @Override
    public String getColumnName(int column) {
        return titulos[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        switch(CONSULTA)
        {
            case 0:
                switch(columnIndex)
                {
                    case 0: return mdl.get(rowIndex).getCB();
                    case 1: return mdl.get(rowIndex).getSICAR();
                    case 2: return mdl.get(rowIndex).getCATEGORIA();
                    case 3: return mdl.get(rowIndex).getDESCRIPCION();

                    default: return null;
                } 
            case 1:
                switch(columnIndex)
                {
                    case 0: return mdl.get(rowIndex).getCB();
                    case 1: return mdl.get(rowIndex).getSICAR();
                    case 2: return mdl.get(rowIndex).getCATEGORIA();
                    case 3: return mdl.get(rowIndex).getDESCRIPCION();
                    case 4: return mdl.get(rowIndex).getIDPRODUCTO();

                    default: return null;
                } 
            
            default:
                switch(columnIndex)
                {                    
                    default: return null;
                }
        }              
        
    }
}
