/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlVacaciones;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloVacacionesTabla extends AbstractTableModel
{
    String[]titulos = new String[8];
    ArrayList<ModeloVacaciones> lista = new ArrayList<>();
    ControlVacaciones x;

    public ModeloVacacionesTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        titulos[0] = "#";
        titulos[1] = "EMPLEADO";
        titulos[2] = "FECHA DE REGISTRO";
        titulos[3] = "FECHA DE SALIDA";
        titulos[4] = "FECHA DE REGRESO";
        titulos[5] = "DIAS TOMADOS";
        titulos[6] = "COMENTARIOS";
        titulos[7] = "PERIODO";
                
        x = new ControlVacaciones();
        try 
        {
            lista = (ArrayList<ModeloVacaciones>) x.getRegistrosINNERP();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA VACACIONES");
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
            case 0: return lista.get(rowIndex).getIDVACACION();
            //case 1: return lista.get(rowIndex).getIDEMPLEADO();
            case 1: return lista.get(rowIndex).getNOMBRE()+" "+lista.get(rowIndex).getAPELLIDOS();
            case 2: return lista.get(rowIndex).getFREG();
            case 3: return lista.get(rowIndex).getFSALIDA();
            case 4: return lista.get(rowIndex).getFREGRESO();
            case 5: return lista.get(rowIndex).getDIAST();
            case 6: return lista.get(rowIndex).getCOMENTARIOS();
            case 7: return lista.get(rowIndex).getPERIODO();
            default: return null;

        }
    }
    
}
