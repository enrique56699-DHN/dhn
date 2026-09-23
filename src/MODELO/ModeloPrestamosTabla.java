/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlPrestamos;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloPrestamosTabla extends AbstractTableModel
{
    String[]titulos = new String[6];
    ArrayList<ModeloPrestamos> lista = new ArrayList<>();
    ControlPrestamos x;
    String Movimiento;
    
    public ModeloPrestamosTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        titulos[0] = "#";
        titulos[1] = "EMPLEADO";
        titulos[2] = "FECHA";
        titulos[3] = "DESCRIPCION";
        titulos[4] = "MONTO";
        titulos[5] = "PERIODO";
                
        x = new ControlPrestamos();
        try 
        {
            lista = (ArrayList<ModeloPrestamos>) x.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA PRESTAMOS");
        }
        
    }
    
    public ModeloPrestamosTabla(int IDEMPLEADO, int PERIODO) throws ClassNotFoundException, SQLException
    {
        Movimiento = "HISTORIAL";
        titulos = new String[4];
        titulos[0] = "#";
        titulos[1] = "FECHA";
        titulos[2] = "MONTO";
        titulos[3] = "PERIODO";
                
        x = new ControlPrestamos();
        try 
        {
            
            lista = (ArrayList<ModeloPrestamos>) x.getRegistrosIDE(IDEMPLEADO, PERIODO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA PRESTAMOS");
        }
        
    }
    
    public ModeloPrestamosTabla(String MOVIMIENTO) throws ClassNotFoundException, SQLException
    {
        Movimiento = MOVIMIENTO;
        titulos = new String[3];
        titulos[0] = "#";
        titulos[1] = "FECHA";
        titulos[2] = "MONTO";
        titulos[3] = "PERIODO";
                
        x = new ControlPrestamos();
        try 
        {
            
            lista = (ArrayList<ModeloPrestamos>) x.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA PRESTAMOS");
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
        if("HISTORIAL".equals(Movimiento))
        {
            switch(columnIndex)
            {
                case 0: return lista.get(rowIndex).getIDPRESTAMO();
                case 1: return lista.get(rowIndex).getFECHA();
                case 2: return lista.get(rowIndex).getMONTO();
                case 3: return lista.get(rowIndex).getPERIODO();

                default: return null;

            } 
        }
        else
            if("REPORTE".equals(Movimiento))
            {
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getIDPRESTAMO();
                    case 1: return lista.get(rowIndex).getFECHA();
                    case 2: return lista.get(rowIndex).getMONTO();
                    case 3: return lista.get(rowIndex).getPERIODO();
                    
                    default: return null;

                } 
            }
        else
            switch(columnIndex)
            {
                case 0: return lista.get(rowIndex).getIDPRESTAMO();
                case 1: return lista.get(rowIndex).getNOMBRE()+" "+lista.get(rowIndex).getAPELLIDOS();
                case 2: return lista.get(rowIndex).getFECHA();
                case 3: return lista.get(rowIndex).getDESCRIPCION();
                case 4: return lista.get(rowIndex).getMONTO();
                case 5: return lista.get(rowIndex).getPERIODO();
                
                default: return null;

            }       
    
    }

}