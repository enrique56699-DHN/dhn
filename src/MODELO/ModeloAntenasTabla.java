/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlAntenas;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author enrique.orozco
 */
public class ModeloAntenasTabla extends AbstractTableModel
{
    String[]titulos;
    ArrayList<ModeloAntenas> antenas = new ArrayList<>();
    ControlAntenas pto;
    int CONSULTA = 0;
    
    public ModeloAntenasTabla(int IDANTENA) throws ClassNotFoundException, SQLException 
    {
        
        titulos = new String[7];
        CONSULTA = 0;
        titulos[0] ="ID";
        titulos[1] ="MODELO";
        titulos[2] ="NOMBRE";
        titulos[3] ="MARCA";        
        titulos[4] ="IP";
        titulos[5] ="SITIO";
        titulos[6] ="IPPADRE";
        
        pto = new ControlAntenas();
        try 
        {
            antenas = (ArrayList<ModeloAntenas>) pto.getRegistros(IDANTENA);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERRO EN LA CREACION DE TABLA ANTENAS POR ESTADO");
        }
    }
    
    /*public ModeloAntenasTabla() throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        
        titulos = new String[2];
        CONSULTA = 1;
        
        titulos[0] ="NOMBRE";
        titulos[1] ="FRECUENCIA";
        
        pto = new ControlAntenas();
        try 
        {
            antenas = (ArrayList<ModeloAntenas>) pto.getRegistrosTipo("SECTORIAL","OMNI");
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA ANTENAS POR FRECUENCIAS");
        }
    }*/
    
    public ModeloAntenasTabla() throws ClassNotFoundException, SQLException 
    {
        
        titulos = new String[7];
        CONSULTA = 0;
        titulos[0] ="ID";
        titulos[1] ="MODELO";
        titulos[2] ="NOMBRE";
        titulos[3] ="MARCA";        
        titulos[4] ="IP";
        titulos[5] ="SITIO";
        titulos[6] ="IPPADRE";
        //titulos[6] ="FRECUENCIA";
        
        pto = new ControlAntenas();
        try 
        {
            antenas = (ArrayList<ModeloAntenas>) pto.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA ANTENAS POR ESTADO");
        }
    }
   
    @Override
    public int getRowCount() {
        return antenas.size();
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
                    case 0: return antenas.get(rowIndex).getIDANTENA();
                    case 1: return antenas.get(rowIndex).getMODELO();
                    case 2: return antenas.get(rowIndex).getNOMBRE();
                    case 3: return antenas.get(rowIndex).getMARCA();                    
                    case 4: return antenas.get(rowIndex).getIP();
                    case 5: return antenas.get(rowIndex).getSITIO();
                    case 6: return antenas.get(rowIndex).getIPPADRE();

                    default: 
                        return null;
                }
            
            default: 
                return null;
        }           
        
    }
}
