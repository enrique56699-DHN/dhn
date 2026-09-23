/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlVehiculosSER;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author enrique.orozco
 */
public class ModeloVehiculosSERTabla extends AbstractTableModel
{
    String[]titulos;
    ArrayList<ModeloVehiculosSER> mdl = new ArrayList<>();
    ControlVehiculosSER ctrl;
    int CONSULTA = 0;
    
    public ModeloVehiculosSERTabla() throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 0;
        titulos = new String[9];
        titulos[0] ="FOLIO";
        titulos[1] ="NUMERO DE UNIDAD";
        titulos[2] ="VEHICULO";
        titulos[3] ="EMPRESA";
        titulos[4] ="TIPO DE SERVICIO";
        titulos[5] ="COMENTARIOS";
        titulos[6] ="FECHA";
        titulos[7] ="KM";        
        titulos[8] ="COSTO";

        ctrl = new ControlVehiculosSER();
        try 
        {
            mdl = (ArrayList<ModeloVehiculosSER>) ctrl.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE VEHICULOS SERVICIO");
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
                    case 0: return mdl.get(rowIndex).getIDVEHICULOSER();
                    case 1: return mdl.get(rowIndex).getIDVEHICULO();
                    case 2: return mdl.get(rowIndex).getMARCA()+" "+mdl.get(rowIndex).getMODELO();
                    case 3: return mdl.get(rowIndex).getEMPRESA();
                    case 4: return mdl.get(rowIndex).getTIPO();
                    case 5: return mdl.get(rowIndex).getCOMENTARIOS();
                    case 6: return mdl.get(rowIndex).getFECHA();
                    case 7: return mdl.get(rowIndex).getKM();
                    case 8: return mdl.get(rowIndex).getCOSTO();

                    default: 
                        return null;
                }                                    
            default: 
                return null;
                
        }              
        
    }
}
