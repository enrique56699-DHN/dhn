/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlDirecciones;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Oscar E Gonzalez G
 */
public class ModeloDireccionesTabla extends AbstractTableModel
{
    String[]titulos = new String[8];
    ArrayList<ModeloDirecciones> productos = new ArrayList<>();
    ControlDirecciones pto;
    int CONSULTA =0;
    
    public ModeloDireccionesTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        titulos[0] ="#";
        titulos[1] ="CALLE";
        titulos[2] ="NUMERO";
        titulos[3] ="COLONIA";
        titulos[4] ="LOCALIDAD";
        titulos[5] ="LATITUD";
        titulos[6] ="LONGITUD";
        titulos[7] ="REFERENCIA";
        
        pto = new ControlDirecciones();
        try 
        {
            productos = (ArrayList<ModeloDirecciones>) pto.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.print("ERROR EN LA CREACION DE LA TABLA DIRECION "+ex);
        }
    }
    
    public ModeloDireccionesTabla(int IDCLIENTE) throws ClassNotFoundException, SQLException
    {
        titulos = new String[8];
        CONSULTA =1;
        titulos[0] ="#";
        titulos[1] ="CALLE";
        titulos[2] ="NUMERO";
        titulos[3] ="COLONIA";
        titulos[4] ="LOCALIDAD";
        titulos[5] ="LINK MAPS";
        titulos[6] ="VISTA SATELITAL";
        titulos[7] ="STREETVIEW";
        
        pto = new ControlDirecciones();
        try 
        {
            productos = (ArrayList<ModeloDirecciones>) pto.getRegistros(IDCLIENTE);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR AL CARGAR LA TABLA DIRECCIONES POR CLIENTE "+ex);
        }
    }    
    
    @Override
    public int getRowCount() {
        return productos.size();
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
                    case 0: return productos.get(rowIndex).getIDDIRECCION();
                    //case 1: return productos.get(rowIndex).getIDCLIENTE();
                    case 1: return productos.get(rowIndex).getCALLE();
                    case 2: return productos.get(rowIndex).getNUMERO();
                    case 3: return productos.get(rowIndex).getCOLONIA();
                    case 4: return productos.get(rowIndex).getLOCALIDAD();
                    case 5: return productos.get(rowIndex).getLATITUD();
                    case 6: return productos.get(rowIndex).getLONGITUD();
                    case 7: return productos.get(rowIndex).getREFERENCIA();

                    default: return null;
                }
            case 1:
                switch(columnIndex)
                {
                    case 0: return productos.get(rowIndex).getIDDIRECCION();
                    //case 1: return productos.get(rowIndex).getIDCLIENTE();
                    case 1: return productos.get(rowIndex).getCALLE();
                    case 2: return productos.get(rowIndex).getNUMERO();
                    case 3: return productos.get(rowIndex).getCOLONIA();
                    case 4: return productos.get(rowIndex).getLOCALIDAD();
                    case 5: return productos.get(rowIndex).getLATITUD();
                    case 6: return productos.get(rowIndex).getLONGITUD();
                    case 7: return productos.get(rowIndex).getREFERENCIA();

                    default: return null;
                }
            default:
                switch(columnIndex)
                {
                    case 0: return productos.get(rowIndex).getIDDIRECCION();
                    //case 1: return productos.get(rowIndex).getIDCLIENTE();
                    case 1: return productos.get(rowIndex).getCALLE();
                    case 2: return productos.get(rowIndex).getNUMERO();
                    case 3: return productos.get(rowIndex).getCOLONIA();
                    case 4: return productos.get(rowIndex).getLOCALIDAD();
                    case 5: return productos.get(rowIndex).getLATITUD();
                    case 6: return productos.get(rowIndex).getLONGITUD();
                    case 7: return productos.get(rowIndex).getREFERENCIA();

                    default: return null;
                }
        }        
    }
    
}
