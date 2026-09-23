/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlPdocs;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author win 10
 */
public class ModeloPdocsTabla extends AbstractTableModel
{
    String[]titulos;
    ArrayList<ModeloPdocs> lista = new ArrayList<>();
    ControlPdocs ctrl;
    String CONSULTA;
    
    public ModeloPdocsTabla(String CONSULTA, int ID) throws ClassNotFoundException, SQLException
    {
        this.CONSULTA = CONSULTA;      
        switch(CONSULTA)
        {
            case "IDPENDIENTE":
                titulos = new String[4];
                titulos[0] ="#";
                titulos[1] ="FECHA";
                titulos[2] ="COMENTARIOS";
                titulos[3] ="DOCUMENTO";
                break;
            default:
                break;
        }

        ctrl = new ControlPdocs();
        try 
        {
            lista = (ArrayList<ModeloPdocs>) ctrl.getRegistros(CONSULTA, ID);
        }
        catch (Exception ex) 
        {
            System.out.print("ERROR EN LA CREACION DE LA PDOCS  "+ex);
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
            case "IDPENDIENTE":
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getIDPDOC();
                    case 1: return lista.get(rowIndex).getFECHA();
                    case 2: return lista.get(rowIndex).getCOMENTARIOS();
                    case 3: return lista.get(rowIndex).getDOC();
                    default: return null;
                }            
            default:
                return null;                
        }        
    }
    
}

