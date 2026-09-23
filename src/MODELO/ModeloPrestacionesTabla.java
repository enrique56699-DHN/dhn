/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlPrestaciones;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloPrestacionesTabla extends AbstractTableModel
{

    String[]titulos = new String[6];
    ArrayList<ModeloPrestaciones> lista = new ArrayList<>();
    ControlPrestaciones x;
    int CONSULTA;
    
    //private Locale locale = new Locale ("en", "US");
    //private NumberFormat totalF = NumberFormat.getInstance (locale);    

    public ModeloPrestacionesTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        CONSULTA=0;
        titulos[0] = "#";
        titulos[1] = "CONCEPTO";
        titulos[2] = "PERIODO";
        titulos[3] = "FINI";
        titulos[4] = "FFIN";
        titulos[5] = "ESTADO";
                
        x = new ControlPrestaciones();
        try 
        {
            lista = (ArrayList<ModeloPrestaciones>) x.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA PRESTACIONES");
        }
        
    }
    
    public ModeloPrestacionesTabla(String CONCEPTO) throws ClassNotFoundException, SQLException
    {
        CONSULTA=1;
        titulos = new String[5];
        titulos[0] = "#";
        titulos[1] = "PERIODO";
        titulos[2] = "FINI";
        titulos[3] = "FFIN";
        titulos[4] = "ESTADO";
                
        x = new ControlPrestaciones();
        try 
        {
            lista = (ArrayList<ModeloPrestaciones>) x.getRegistros(CONCEPTO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA PRESTACIONES POR CONCEPTO");
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
                    case 0: return lista.get(rowIndex).getIDPRESTACION();
                    case 1: return lista.get(rowIndex).getCONCEPTO();
                    case 2: return lista.get(rowIndex).getPERIODO();
                    case 3: return lista.get(rowIndex).getFINI();
                    case 4: return lista.get(rowIndex).getFFIN();
                    case 5: return lista.get(rowIndex).getESTADO();
                    default: return null;

                }
            case 1:
                switch(columnIndex)
                    {
                        case 0: return lista.get(rowIndex).getIDPRESTACION();
                        case 1: return lista.get(rowIndex).getPERIODO();
                        case 2: return lista.get(rowIndex).getFINI();
                        case 3: return lista.get(rowIndex).getFFIN();
                        case 4: return lista.get(rowIndex).getESTADO();
                        default: return null;

                    }
            /*case 2:
                switch(columnIndex)
                    {
                        case 0: return lista.get(rowIndex).getIDENTRADA();
                        case 1: return lista.get(rowIndex).getDESCRIPCION();
                        case 2: return "$ "+totalF.format(lista.get(rowIndex).getMONTO())+".00";
                        case 3: return lista.get(rowIndex).getCATEGORIA();
                        case 4: return lista.get(rowIndex).getFECHA();
                        case 5: return lista.get(rowIndex).getMETODO();
                        case 6: return lista.get(rowIndex).getIDCAJA();
                        default: return null;

                    }*/
            default: 
                switch(columnIndex)
                    {
                        case 0: return lista.get(rowIndex).getIDPRESTACION();
                        case 1: return lista.get(rowIndex).getCONCEPTO();
                        case 2: return lista.get(rowIndex).getPERIODO();
                        case 3: return lista.get(rowIndex).getFINI();
                        case 4: return lista.get(rowIndex).getFFIN();
                        case 5: return lista.get(rowIndex).getESTADO();
                        default: return null;

                    }
        }        
        
    }
    
}
