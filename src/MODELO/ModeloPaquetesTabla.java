/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlPaquetes;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Oscar E Gonzalez G
 */
public class ModeloPaquetesTabla extends AbstractTableModel
{
    String[]titulos = new String[3];
    ArrayList<ModeloPaquetes> productos = new ArrayList<>();
    ControlPaquetes pto;
    
    public ModeloPaquetesTabla() throws ClassNotFoundException, SQLException
    {
        titulos[0] ="ID";
        titulos[1] ="COSTO";
        titulos[2] ="DESCRIPCION";
        
        pto = new ControlPaquetes();
        try
        {
            
            productos = (ArrayList<ModeloPaquetes>) pto.getRegistros();
        }
        catch(Exception ex)
        {
            
        }
    }

    public ModeloPaquetesTabla(int ID) throws ClassNotFoundException, SQLException 
    {
        titulos[0] ="ID";
        titulos[1] ="COSTO";
        titulos[2] ="DESCRIPCION";
        
        pto = new ControlPaquetes();
        try
        {
            
            productos = (ArrayList<ModeloPaquetes>) pto.getRegistros(ID);
        }
        catch(Exception ex)
        {
            
        }
    }
      
    @Override
    public int getRowCount() {
        return productos.size(); 
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
        switch(columnIndex)
        {
            case 0: return productos.get(rowIndex).getIDPAQUETE();            //case 0: return a;
            case 1: return productos.get(rowIndex).getCOSTO();
            case 2: return productos.get(rowIndex).getDESCRIPCION();
     
            default: return null;
        }
    }
    
}
