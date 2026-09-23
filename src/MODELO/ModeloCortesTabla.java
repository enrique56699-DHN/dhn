/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlCortes;
import DHNv2.G;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloCortesTabla extends AbstractTableModel
{
    String[]titulos = new String[8];
    ArrayList<ModeloCortes> lmdl = new ArrayList<>();
    ControlCortes ctrl;
    //private Locale locale = new Locale ("en", "US");
    //private NumberFormat totalF = NumberFormat.getInstance (locale);
    
    
    public ModeloCortesTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        titulos[0] ="NO. DE CORTE";
        titulos[1] ="MONTO INICIAL";
        titulos[2] ="RANGO DE FECHAS";
        titulos[3] ="MONTO FINAL";
        titulos[4] ="RANGO DE PAGOS";
        titulos[5] ="RANGO DE ENTRADAS";
        titulos[6] ="RANGO DE SALIDAS";
        titulos[7] ="CAJA";
        
        
        ctrl = new ControlCortes();
        try 
        {
            lmdl = (ArrayList<ModeloCortes>) ctrl.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CARGA DE TABLA CORTES");
        }
    }
        
    @Override
    public int getRowCount() {
        return lmdl.size();
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
            case 0: return lmdl.get(rowIndex).getIDCORTE();
            //case 1: return "$ "+totalF.format(lmdl.get(rowIndex).getMONTOINI())+".00";
            case 1: return "$ "+G.numero.format(lmdl.get(rowIndex).getMONTOINI())+".00";
            case 2: return lmdl.get(rowIndex).getFINI() +" - "+ lmdl.get(rowIndex).getFFIN();
            //case 3: return "$ "+totalF.format(lmdl.get(rowIndex).getMONTOFIN())+".00";
            case 3: return "$ "+G.numero.format(lmdl.get(rowIndex).getMONTOFIN())+".00";
            case 4: return lmdl.get(rowIndex).getIDPAGOI() +" - "+ lmdl.get(rowIndex).getIDPAGOF();
            case 5: return lmdl.get(rowIndex).getIDENTRADAI() +" - "+ lmdl.get(rowIndex).getIDENTRADAF();
            case 6: return lmdl.get(rowIndex).getIDSALIDAI() +" - "+ lmdl.get(rowIndex).getIDSALIDAF();
            case 7: return lmdl.get(rowIndex).getIDCAJA();                        
            default: return null;
        }
    }
}
