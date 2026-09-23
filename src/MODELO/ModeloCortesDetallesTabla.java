/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlCortesDetalles;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloCortesDetallesTabla extends AbstractTableModel
{
    String[]titulos = new String[8];
    ArrayList<ModeloCortesDetalles> datos = new ArrayList<>();
    ControlCortesDetalles pto;
    
    public ModeloCortesDetallesTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        titulos[0] ="DETALLE";
        titulos[1] ="IDCORTE";
        titulos[2] ="PAGO INICIAL";
        titulos[3] ="PAGO FINAL";
        titulos[4] ="ENTRADA INICIAL";        
        titulos[5] ="ENTRADA FINAL";
        titulos[6] ="SALIDA INICIAL";
        titulos[7] ="SALIDA FINAL";
        
        pto = new ControlCortesDetalles();
        try 
        {
            datos = (ArrayList<ModeloCortesDetalles>) pto.getRegistros();
            
        }
        
        catch (Exception ex) 
        {
            
        }
    }

    @Override
    public int getRowCount() {
        return datos.size();
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
            case 0: return datos.get(rowIndex).getIDCORTED();
            case 1: return datos.get(rowIndex).getIDCORTE();
            case 2: return datos.get(rowIndex).getIDPAGOI();
            case 3: return datos.get(rowIndex).getIDENTRADAI();
            case 4: return datos.get(rowIndex).getIDSALIDAI();
     
            default: return null;
        }
    }
}
