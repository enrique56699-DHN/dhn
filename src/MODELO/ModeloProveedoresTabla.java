/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlProveedores;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author win 10
 */
public class ModeloProveedoresTabla extends AbstractTableModel
{
    String[]titulos;
    ArrayList<ModeloProveedores> lista = new ArrayList<>();
    ControlProveedores ctrl;
    String CONSULTA;
    
    public ModeloProveedoresTabla(String CONSULTA, int ID) throws ClassNotFoundException, SQLException
    {
        this.CONSULTA = CONSULTA;      
        switch(CONSULTA)
        {
            case "IDPROVEEDOR":
                titulos = new String[5];
                titulos[0] ="#";
                titulos[1] ="NOMBRE";
                titulos[2] ="TEL1";
                titulos[3] ="TEL2";
                titulos[4] ="REFERENCIA";
                break;
            case "TODOS":
                titulos = new String[5];
                titulos[0] ="#";
                titulos[1] ="NOMBRE";
                titulos[2] ="TEL1";
                titulos[3] ="TEL2";
                titulos[4] ="REFERENCIA";
                break;
            default:
                break;
        }

        ctrl = new ControlProveedores();
        try 
        {
            lista = (ArrayList<ModeloProveedores>) ctrl.getRegistros(CONSULTA, ID);
        }
        catch (Exception ex) 
        {
            System.out.print("ERROR EN LA CREACION DE LA PDOCS  "+ex);
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
            case "IDPENDIENTE":
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getIDPROVEEDOR();
                    case 1: return lista.get(rowIndex).getNOMBRE();
                    case 2: return lista.get(rowIndex).getTEL1();
                    case 3: return lista.get(rowIndex).getTEL2();
                    case 4: return lista.get(rowIndex).getREFERENCIA();
                    default: return null;
                }
            case "TODOS":
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getIDPROVEEDOR();
                    case 1: return lista.get(rowIndex).getNOMBRE();
                    case 2: return lista.get(rowIndex).getTEL1();
                    case 3: return lista.get(rowIndex).getTEL2();
                    case 4: return lista.get(rowIndex).getREFERENCIA();
                    default: return null;
                }    
            default:
                return null;                
        }        
    }
    
}

