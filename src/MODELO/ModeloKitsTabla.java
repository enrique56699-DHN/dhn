/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlKits;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author enrique.orozco
 */
public class ModeloKitsTabla extends AbstractTableModel
{
    String[]titulos = new String[6];
    ArrayList<ModeloKits> mdl = new ArrayList<>();
    ControlKits ctrl;
    int CONSULTA = 0;
    
    public ModeloKitsTabla() throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 0;
        titulos[0] ="ID";
        titulos[1] ="DESCRIPCION";
        titulos[2] ="CATEGORIA";
        titulos[3] ="COSTO";
        titulos[4] ="PRECIO";
        titulos[5] ="FOTO";
        
        ctrl = new ControlKits();
        try 
        {
            mdl = (ArrayList<ModeloKits>) ctrl.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA KITS");
        }
    }
    
    public ModeloKitsTabla(int AUX) throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 1;
        titulos[0] ="CATEGORIA";
        
        ctrl = new ControlKits();
        try 
        {
            mdl = (ArrayList<ModeloKits>) ctrl.getCATEGORIAS();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA KITS SOLO CATEGORIA");
        }
    }
    
    public ModeloKitsTabla(String CATEGORIA) throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 2;
        titulos[0] ="NOMBRES";
        
        ctrl = new ControlKits();
        try 
        {
            mdl = (ArrayList<ModeloKits>) ctrl.getNOMBRES(CATEGORIA);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA KITS SOLO NOMBRE DE KITS POR CATEGORIA");
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
                    case 0: return mdl.get(rowIndex).getIDKIT();
                    case 1: return mdl.get(rowIndex).getNOMBRE();
                    case 2: return mdl.get(rowIndex).getCATEGORIA();
                    case 3: return mdl.get(rowIndex).getCOSTO();
                    case 4: return mdl.get(rowIndex).getPRECIO();
                    case 5: return mdl.get(rowIndex).getFOTO();

                    default: return null;
                }   
            case 1:
                switch(columnIndex)
                {
                    case 0: return mdl.get(rowIndex).getCATEGORIA();

                    default: return null;
                }   
            case 2:
                switch(columnIndex)
                {
                    case 0: return mdl.get(rowIndex).getNOMBRE();

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
