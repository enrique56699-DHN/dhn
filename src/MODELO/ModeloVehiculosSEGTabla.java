/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlVehiculosSEG;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author enrique.orozco
 */
public class ModeloVehiculosSEGTabla extends AbstractTableModel
{
    String[]titulos;
    ArrayList<ModeloVehiculosSEG> mdl = new ArrayList<>();
    ControlVehiculosSEG ctrl;
    int CONSULTA = 0;
    
    public ModeloVehiculosSEGTabla() throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 0;
        titulos = new String[11];
        titulos[0] ="FOLIO";
        titulos[1] ="NUMERO DE UNIDAD";
        titulos[2] ="VEHICULO";
        titulos[3] ="EMPRESA";
        titulos[4] ="ASEGURADORA";
        titulos[5] ="NUMERO DE POLIZA";
        titulos[6] ="FECHA DE ALTA";
        titulos[7] ="VIGENCIA";        
        titulos[8] ="PROPIETARIO";
        titulos[9] ="COSTO";
        titulos[10] ="ARCHIVO";
        
        ctrl = new ControlVehiculosSEG();
        try 
        {
            mdl = (ArrayList<ModeloVehiculosSEG>) ctrl.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE VEHICULOS SEGURO");
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
                    case 0: return mdl.get(rowIndex).getIDVEHICULOSEG();
                    case 1: return mdl.get(rowIndex).getIDVEHICULO();
                    case 2: return mdl.get(rowIndex).getMARCA()+" "+mdl.get(rowIndex).getMODELO();
                    case 3: return mdl.get(rowIndex).getEMPRESA();
                    case 4: return mdl.get(rowIndex).getASEGURADORA();
                    case 5: return mdl.get(rowIndex).getNP();
                    case 6: return mdl.get(rowIndex).getFECHA();
                    case 7: return mdl.get(rowIndex).getVIGENCIA();
                    case 8: return mdl.get(rowIndex).getPROPIETARIO();
                    case 9: return mdl.get(rowIndex).getCOSTO();
                    case 10: return mdl.get(rowIndex).getARCHIVO();

                    default: 
                        return null;
                }                                    
            default: 
                return null;
                
        }              
        
    }
}
