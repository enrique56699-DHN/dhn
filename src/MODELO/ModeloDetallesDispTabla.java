/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlDetallesDisp;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class ModeloDetallesDispTabla extends AbstractTableModel
{
    String[]titulos;
    ArrayList<ModeloDetallesDisp> lista = new ArrayList<>();
    ControlDetallesDisp pto;
    int CONSULTA = 0;
    
    public ModeloDetallesDispTabla() throws ClassNotFoundException, SQLException 
    {
        
        titulos = new String[4];
        CONSULTA = 0;
        titulos[0] ="ID";
        titulos[1] ="TABLA";
        titulos[2] ="CATEGORIA";
        titulos[3] ="DESCRIPCION";        
        
        pto = new ControlDetallesDisp();
        try 
        {
            lista = (ArrayList<ModeloDetallesDisp>) pto.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERRO EN LA CREACION DE TABLA DETALLES DISP");
        }
    }
    
    public ModeloDetallesDispTabla(String TABLA, String CATEGORIA) throws ClassNotFoundException, SQLException 
    {
        
        titulos = new String[1];
        CONSULTA = 1;
        titulos[0] ="DESCRIPCION";        
        
        pto = new ControlDetallesDisp();
        try 
        {
            lista = (ArrayList<ModeloDetallesDisp>) pto.getRegistros(TABLA, CATEGORIA);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERRO EN LA CREACION DE TABLA DETALLES DISP TABLA CATEGORIA");
        }
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
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
                    case 0: return lista.get(rowIndex).getIDD();
                    case 1: return lista.get(rowIndex).getTABLA();
                    case 2: return lista.get(rowIndex).getCATEGORIA();
                    case 3: return lista.get(rowIndex).getDESCRIPCION();                    

                    default: 
                        return null;
                }
            case 1:
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getDESCRIPCION();                    

                    default: 
                        return null;
                }    
            
            default: 
                return null;
        }           
        
    }
}
