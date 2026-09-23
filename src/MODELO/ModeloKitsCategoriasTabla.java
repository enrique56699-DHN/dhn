
package MODELO;

import CONTROLADOR.ControlKitsCategorias;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author enrique.orozco
 */
public class ModeloKitsCategoriasTabla extends AbstractTableModel
{
    String[]titulos = new String[1];
    ArrayList<ModeloKitsCategorias> mdl = new ArrayList<>();
    ControlKitsCategorias ctrl;
    int CONSULTA = 0;
    
    public ModeloKitsCategoriasTabla() throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 0;
        titulos[0] ="CATEGORIA";
        
        ctrl = new ControlKitsCategorias();
        try 
        {
            mdl = (ArrayList<ModeloKitsCategorias>) ctrl.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA KITS CATEGORIAS");
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
                    case 0: return mdl.get(rowIndex).getCATEGORIA();

                    default: return null;
                }             
            default:
                switch(columnIndex)
                {                    
                    default: return null;
                }
        }              
        
    }
}
