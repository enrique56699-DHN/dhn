/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlCF;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author win 10
 */
public class ModeloCFTabla extends AbstractTableModel
{
    String[]titulos = new String[4];
    ArrayList<ModeloCF> lista = new ArrayList<>();
    ControlCF x;
    int CONSULTA;

    public ModeloCFTabla() throws ClassNotFoundException, SQLException 
    {
        CONSULTA = 0;
        titulos = new String[4];
        titulos[0] ="#";
        titulos[1] ="FECHA";
        titulos[2] ="TIPO";
        titulos[3] ="MONTO";

        x = new ControlCF();
        try 
        {
            lista = (ArrayList<ModeloCF>) x.getRegistros();
        }
        catch (Exception ex) 
        {
            System.out.print("ERROR EN LA CREACION DE LA CF "+ex);
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
                    case 0: return lista.get(rowIndex).getIDCF();
                    case 1: return lista.get(rowIndex).getFECHA();
                    case 2: return lista.get(rowIndex).getTIPO();
                    case 3: return lista.get(rowIndex).getMONTO();
                    default: 
                        return null;
                }                     
            
            default:
                return null;
                
        }        
    }
    
}

