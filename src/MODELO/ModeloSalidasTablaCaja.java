/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlSalidasCaja;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloSalidasTablaCaja extends AbstractTableModel
{
    String[]titulos = new String[5];
    ArrayList<ModeloSalidasCaja> lista = new ArrayList<>();
    ControlSalidasCaja x;


    public ModeloSalidasTablaCaja() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        titulos[0] ="#";
        titulos[1] ="DESCRIPCION";
        titulos[2] ="MONTO";
        titulos[3] ="EMPRESA";
        titulos[4] ="FECHA";
                
        x = new ControlSalidasCaja();
        try 
        {
            lista = (ArrayList<ModeloSalidasCaja>) x.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA SALIDAS CAJA");
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
            case 0: return lista.get(rowIndex).getIDSALIDACAJA();
            case 1: return lista.get(rowIndex).getDESCRIPCION();
            case 2: return lista.get(rowIndex).getMONTO();
            case 3: return lista.get(rowIndex).getEMPRESA();
            case 4: return lista.get(rowIndex).getFECHA();

            default: return null;

        }
            
        
    }
    
}
