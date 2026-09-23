/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlVehiculos;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author enrique.orozco
 */
public class ModeloVehiculosTabla extends AbstractTableModel
{
    String[]titulos = new String[7];
    ArrayList<ModeloVehiculos> mdl = new ArrayList<>();
    ControlVehiculos ctrl;
    int CONSULTA = 0;
    
    public ModeloVehiculosTabla() throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 0;
        titulos[0] ="UNIDAD";
        titulos[1] ="MARCA";
        titulos[2] ="MODELO Y AÑO";
        titulos[3] ="NS";
        titulos[4] ="NMOTOR";
        titulos[5] ="PLACA";
        titulos[6] ="EMPRESA";
        
        ctrl = new ControlVehiculos();
        try 
        {
            mdl = (ArrayList<ModeloVehiculos>) ctrl.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA VEHICULOS");
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
                    case 0: return mdl.get(rowIndex).getIDVEHICULO();
                    case 1: return mdl.get(rowIndex).getMARCA();
                    case 2: return mdl.get(rowIndex).getMODELO();
                    case 3: return mdl.get(rowIndex).getNS();
                    case 4: return mdl.get(rowIndex).getNMOTOR();
                    case 5: return mdl.get(rowIndex).getPLACA();
                    case 6: return mdl.get(rowIndex).getEMPRESA();

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
