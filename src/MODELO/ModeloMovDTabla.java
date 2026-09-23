/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlMovD;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class ModeloMovDTabla extends AbstractTableModel
{
    String[]titulos = new String[6];
    private ArrayList<ModeloMovD> lista = new ArrayList<>();
    private ControlMovD ctrl;
    private int CONSULTA;

    public ModeloMovDTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        CONSULTA = 0;
        titulos[0] ="#";
        titulos[1] ="MOVIMIENTO";
        titulos[2] ="IDMOVIMIENTO";
        titulos[3] ="REFERENCIA";
        titulos[4] ="FACTURA";
        titulos[5] ="COTEJADO";
        
        ctrl = new ControlMovD();
        try 
        {
            lista = (ArrayList<ModeloMovD>) ctrl.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR AL CREAR TABLA MOVIMIENTOS-DETALLES");
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
                    case 0: return lista.get(rowIndex).getIDMOVD();
                    case 1: return lista.get(rowIndex).getMOVIMIENTO();
                    case 2: return lista.get(rowIndex).getIDMOVIMIENTO();
                    case 3: return lista.get(rowIndex).getREF();                    
                    case 4: return lista.get(rowIndex).getFAC();
                    case 5: return lista.get(rowIndex).getCOTEJADO();
                    
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
