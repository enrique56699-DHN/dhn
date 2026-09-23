/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlKitsD;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author enrique.orozco
 */
public class ModeloKitsDTabla extends AbstractTableModel
{
    String[]titulos = new String[4];
    ArrayList<ModeloKitsD> mdl = new ArrayList<>();
    ControlKitsD ctrl;
    int CONSULTA = 0;
    
    public ModeloKitsDTabla() throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 0;
        titulos[0] ="IDKITD";
        titulos[1] ="IDKIT";
        titulos[2] ="IDPRODUCTO";
        titulos[3] ="CANTIDAD";
        
        ctrl = new ControlKitsD();
        try 
        {
            mdl = (ArrayList<ModeloKitsD>) ctrl.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA KITS DETALLE");
        }
    }
    
    public ModeloKitsDTabla(int IDKIT) throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        titulos = new String[6];
        CONSULTA = 1;
        titulos[0] ="IDKITD";
        titulos[1] ="ESTADO";
        titulos[2] ="CANTIDAD";
        titulos[3] ="DESCRIPCION";
        titulos[4] ="COSTO";
        titulos[5] ="FOTO";
        
        ctrl = new ControlKitsD();
        try 
        {
            mdl = (ArrayList<ModeloKitsD>) ctrl.getRegistrosID(IDKIT);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA KITS DETALLE CON NOMBRE");
        }
    }
    
    public ModeloKitsDTabla(int IDKIT,String AUX) throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        titulos = new String[2];
        CONSULTA = 2;
        titulos[0] ="IDPRODUCTO";
        titulos[1] ="CANTIDAD";
        
        ctrl = new ControlKitsD();
        try 
        {
            mdl = (ArrayList<ModeloKitsD>) ctrl.getRegistros(IDKIT);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA KITS DETALLE PARA LLENAR CARRITO");
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
                    case 0: return mdl.get(rowIndex).getIDKITD();
                    case 1: return mdl.get(rowIndex).getIDKIT();
                    case 2: return mdl.get(rowIndex).getIDPRODUCTO();
                    case 3: return mdl.get(rowIndex).getCANT();

                    default: return null;
                } 
            case 1:
                switch(columnIndex)
                {
                    case 0: return mdl.get(rowIndex).getIDKITD();
                    case 1: return mdl.get(rowIndex).getESTADO();
                    case 2: return mdl.get(rowIndex).getCANT();
                    case 3: return mdl.get(rowIndex).getDESCRIPCION();
                    case 4: return mdl.get(rowIndex).getCOSTO();
                    case 5: return mdl.get(rowIndex).getFOTO();

                    default: return null;
                } 
            case 2:
                switch(columnIndex)
                {
                    case 0: return mdl.get(rowIndex).getIDPRODUCTO();
                    case 1: return mdl.get(rowIndex).getCANT();

                    default: return null;
                } 
            default:
                switch(columnIndex)
                {                    
                    default:
                        return null;
                }
        }              
        
    }
}
