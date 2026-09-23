/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlAhorros;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloAhorrosTabla extends AbstractTableModel
{
    String[]titulos = new String[5];
    ArrayList<ModeloAhorros> lista = new ArrayList<>();
    ControlAhorros x;
    String Movimiento;
    
    public ModeloAhorrosTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        titulos[0] = "#";
        titulos[1] = "EMPLEADO";
        titulos[2] = "FECHA";
        titulos[3] = "MONTO";
        titulos[4] = "PERIODO";
        //titulos
                
        x = new ControlAhorros();
        try 
        {
            lista = (ArrayList<ModeloAhorros>) x.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA AHORROS");
        }
        
    }
    
    public ModeloAhorrosTabla(int IDEMPLEADO, int PERIODO) throws ClassNotFoundException, SQLException
    {
        Movimiento = "HISTORIAL";
        titulos = new String[4];
        titulos[0] = "#";
        titulos[1] = "FECHA";
        titulos[2] = "MONTO";
        titulos[3] = "PERIODO";
                
        x = new ControlAhorros();
        try 
        {
            lista = (ArrayList<ModeloAhorros>) x.getRegistrosIDE(IDEMPLEADO, PERIODO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA AHORROS");
        }
        
    }
    
    public ModeloAhorrosTabla(String MOVIMIENTO) throws ClassNotFoundException, SQLException
    {
        Movimiento = MOVIMIENTO;
        titulos = new String[4];
        titulos[0] = "#";
        titulos[1] = "FECHA";
        titulos[2] = "MONTO";
        titulos[3] = "PERIODO";
                
        x = new ControlAhorros();
        try 
        {
            lista = (ArrayList<ModeloAhorros>) x.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA AHORROS");
        }
        
    }
    
    public ModeloAhorrosTabla(int IDEMPLEADO, int IDAHORRO, String FECHA, float MONTO, int PERIODO) throws ClassNotFoundException, SQLException
    {
        Movimiento = "AGREGAR";
        titulos = new String[4];
        titulos[0] = "#";
        titulos[1] = "FECHA";
        titulos[2] = "MONTO";
        titulos[3] = "PERIODO";
                
        x = new ControlAhorros();
        try 
        {
            lista = (ArrayList<ModeloAhorros>) x.getRegistrosIDE(IDEMPLEADO, IDAHORRO, FECHA, MONTO, PERIODO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA AHORROS");
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
                case 0: return lista.get(rowIndex).getIDAHORRO();
                case 1: return lista.get(rowIndex).getFECHA();
                case 2: return lista.get(rowIndex).getMONTO();
                case 3: return lista.get(rowIndex).getPERIODO();
                
                default: return null;

            } 
        }
        else
            if("AGREGAR".equals(Movimiento))
            {
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getIDAHORRO();
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
                        case 0: return lista.get(rowIndex).getIDAHORRO();
                        case 1: return lista.get(rowIndex).getFECHA();
                        case 2: return lista.get(rowIndex).getMONTO();
                        case 3: return lista.get(rowIndex).getPERIODO();
                        
                        default: return null;

                    } 
                }
        else
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getIDAHORRO();
                    case 1: return lista.get(rowIndex).getNOMBRE()+" "+lista.get(rowIndex).getAPELLIDOS();
                    case 2: return lista.get(rowIndex).getFECHA();
                    case 3: return lista.get(rowIndex).getMONTO();
                    case 4: return lista.get(rowIndex).getPERIODO();

                    default: return null;

                }       
    
    }

}