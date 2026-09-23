/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlPrestacionesP;
import DHNv2.G;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Oscar E Gonzalez G
 */
public class ModeloPrestacionesPTabla extends AbstractTableModel
{
    String[]titulos = new String[8];
    ArrayList<ModeloPrestacionesP> x = new ArrayList<>();
    ControlPrestacionesP pto;
    int CONSULTA;
    
    //private Locale locale = new Locale ("en", "US");
    //private NumberFormat totalF = NumberFormat.getInstance (locale);    

    public ModeloPrestacionesPTabla() throws ClassNotFoundException, SQLException {
        CONSULTA=0;
        titulos[0] ="NO";
        titulos[1] ="NOMBRE DEL EMPLEADO";
        titulos[2] ="EMPRESA";
        titulos[3] ="PRESTACION";
        titulos[4] ="FECHA DE ENTREGA";       
        titulos[5] ="MONTO";
        titulos[6] ="PERIODO";        
        titulos[7] ="COMPRENDE FECHAS";
        
        pto = new ControlPrestacionesP();
        try 
        {
            x = (ArrayList<ModeloPrestacionesP>) pto.getRegistrosE();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR AL CREAR LA TABLA BONOSD");
        }
        
    }
    
    @Override
    public int getRowCount() {
        return x.size();
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
                            case 0: return x.get(rowIndex).getIDPRESTACIONP();
                            case 1: return x.get(rowIndex).getNOMBRE() + " "+ x.get(rowIndex).getAPELLIDOS();
                            case 2: return x.get(rowIndex).getEMPRESA();
                            case 3: return x.get(rowIndex).getPRESTACION();
                            case 4: return x.get(rowIndex).getFECHA();
                            //case 5: return "$ "+totalF.format(x.get(rowIndex).getMONTO());
                            case 5: return "$ "+G.numero.format(x.get(rowIndex).getMONTO());
                            case 6: return x.get(rowIndex).getPERIODO();
                            case 7: return x.get(rowIndex).getFINI()+" - "+x.get(rowIndex).getFFIN();                            
                            
                            default: return null;
                        }                
            default:             
                    return null;                         
        }
    
    }
}
