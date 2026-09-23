/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlEPuestos;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */

public class ModeloEPuestosTabla extends AbstractTableModel
{
    String[]titulos;
    private ArrayList<ModeloEPuestos> lista = new ArrayList<>();
    private ControlEPuestos datos;
    int CONSULTA;

    public ModeloEPuestosTabla(int IDEPUESTO, String CAMPO, String ATRIBUTO) throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        titulos = new String[5];
        CONSULTA = 0;
        titulos[0] ="#";
        titulos[1] ="DEPTO";
        titulos[2] ="PUESTO";
        titulos[3] ="SUELDO";
        titulos[4] ="DOC";
        
        datos = new ControlEPuestos();
        try 
        {
            lista = (ArrayList<ModeloEPuestos>) datos.getRegistros(IDEPUESTO, CAMPO, ATRIBUTO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE  LA TABLA EPUESTOS");
        }
    }
    
    public ModeloEPuestosTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        titulos = new String[5];
        CONSULTA = 0;
        titulos[0] ="#";
        titulos[1] ="DEPTO";
        titulos[2] ="PUESTO";
        titulos[3] ="SUELDO";
        titulos[4] ="DOC";
        
        datos = new ControlEPuestos();
        try 
        {
            lista = (ArrayList<ModeloEPuestos>) datos.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE  LA TABLA PUESTO");
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
                    case 0: return lista.get(rowIndex).getIDEPUESTO();
                    case 1: return lista.get(rowIndex).getDEPTO();
                    case 2: return lista.get(rowIndex).getPUESTO();
                    case 3: return lista.get(rowIndex).getSUELDO();
                    case 4: return lista.get(rowIndex).getDOC();

                    default:
                        return null;
                }
            case 1:
                switch(columnIndex)
                {
                    case 1: return lista.get(rowIndex).getIDEPUESTO();
                    
                    default:
                        return null;
                }
            default: 
                return null;
        }
        
    }
}
