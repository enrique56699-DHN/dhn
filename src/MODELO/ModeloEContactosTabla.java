/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlEContactos;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloEContactosTabla extends AbstractTableModel
{
    String[]titulos;
    private ArrayList<ModeloEContactos> lista = new ArrayList<>();
    private ControlEContactos ctrl;
    int CONSULTA;

    public ModeloEContactosTabla(int IDEMPLEADO) throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        titulos = new String[4];
        CONSULTA = 0;
        titulos[0] ="#";
        titulos[1] ="NOMBRE";
        titulos[2] ="TELEFONO";
        titulos[3] ="EMAIL";
        
        ctrl = new ControlEContactos();
        try 
        {
            lista = (ArrayList<ModeloEContactos>) ctrl.getRegistros(IDEMPLEADO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE  LA TABLA ECONTACTOS");
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
                    case 0: return lista.get(rowIndex).getIDECONTACTO();
                    case 1: return lista.get(rowIndex).getNOMBRE();
                    case 2: return lista.get(rowIndex).getTEL();
                    case 3: return lista.get(rowIndex).getEMAIL();
                    default:
                        return null;
                }
            case 1:
                switch(columnIndex)
                {                    
                    default:
                        return null;
                }
            default: 
                return null;
        }
        
    }
}
