/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlUsuarios;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloUsuariosTabla extends AbstractTableModel
{
    String[]titulos = new String[4];
    private ArrayList<ModeloUsuarios> lista = new ArrayList<>();
    private ControlUsuarios datos;

    public ModeloUsuariosTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        titulos[0] ="#";
        titulos[1] ="NOMBRE";
        titulos[2] ="PASSWORD";
        titulos[3] ="PRIVILEGIOS";
        
        datos = new ControlUsuarios();
        try 
        {
            lista = (ArrayList<ModeloUsuarios>) datos.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA USUARIOS");
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
        switch(columnIndex)
        {
            case 0: return lista.get(rowIndex).getIDUSUARIO();
            case 1: return lista.get(rowIndex).getNOMBRE();
            case 2: return lista.get(rowIndex).getPASSWORD();
            case 3: return lista.get(rowIndex).getPRIVILEGIOS();
    
            default: return null;
        }
    }
}
