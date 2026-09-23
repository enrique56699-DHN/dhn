/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlBonosD;
import DHNv2.G;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Oscar E Gonzalez G
 */
public class ModeloBonosDTabla extends AbstractTableModel
{
    String[]titulos = new String[7];
    ArrayList<ModeloBonosD> x = new ArrayList<>();
    ControlBonosD pto;
    int CONSULTA;
    
    //private Locale locale = new Locale ("en", "US");
    //private NumberFormat totalF = NumberFormat.getInstance (locale);    


    public ModeloBonosDTabla() throws ClassNotFoundException, SQLException {
        CONSULTA=0;
        titulos[0] ="NO";
        titulos[1] ="NOMBRE DEL EMPLEADO";
        titulos[2] ="EMPRESA";
        titulos[3] ="PRESTACION";
        titulos[4] ="PERIODO";
        titulos[5] ="MONTO";
        titulos[6] ="FECHA";
        
        pto = new ControlBonosD();
        try 
        {
            x = (ArrayList<ModeloBonosD>) pto.getRegistros();
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
                            case 0: return x.get(rowIndex).getIDBONOD();
                            case 1: return x.get(rowIndex).getNOMBRE() + " "+ x.get(rowIndex).getAPELLIDOS();
                            case 2: return x.get(rowIndex).getEMPRESA();
                            case 3: return x.get(rowIndex).getPRESTACION();
                            case 4: return x.get(rowIndex).getPERIODO();
                            //case 5: return "$ "+totalF.format(x.get(rowIndex).getMONTO())+".00";
                            case 5: return "$ "+G.numero.format(x.get(rowIndex).getMONTO())+".00";
                            case 6: return x.get(rowIndex).getFECHA();
                            
                            default: return null;
                        }                
            default:             
                    return null;                         
        }
    
    }
}
