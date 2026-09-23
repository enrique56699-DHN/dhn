/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlAbonos;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloAbonosTabla extends AbstractTableModel
{
    String[]titulos = new String[6];
    ArrayList<ModeloAbonos> lista = new ArrayList<>();
    ControlAbonos x;
    String Movimiento;

    public ModeloAbonosTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        titulos[0] = "#";
        titulos[1] = "EMPLEADO";
        titulos[2] = "FECHA";
        titulos[3] = "DESCRIPCION";
        titulos[4] = "MONTO";
        titulos[5] = "PERIODO";
                
        x = new ControlAbonos();
        try 
        {
            lista = (ArrayList<ModeloAbonos>) x.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA ABONOS");
        }
        
    }
    
    public ModeloAbonosTabla(int IDEMPLEADO, int PERIODO) throws ClassNotFoundException, SQLException
    {
        Movimiento = "HISTORIAL";
        titulos = new String[4];
        titulos[0] = "#";
        titulos[1] = "FECHA";
        titulos[2] = "MONTO";
        titulos[3] = "PERIODO";
                
        x = new ControlAbonos();
        try 
        {
            lista = (ArrayList<ModeloAbonos>) x.getRegistrosIDE(IDEMPLEADO, PERIODO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA ABONOS");
        }
        
    }
    
    public ModeloAbonosTabla(int IDEMPLEADO,int IDABONO, String FECHA, float MONTO, int PERIODO) throws ClassNotFoundException, SQLException
    {
        Movimiento = "AGREGAR";
        titulos = new String[4];
        titulos[0] = "#";
        titulos[1] = "FECHA";
        titulos[2] = "MONTO";
        titulos[3] = "PERIODO";
                
        x = new ControlAbonos();
        try 
        {
            lista = (ArrayList<ModeloAbonos>) x.getRegistrosIDE(IDEMPLEADO, IDABONO, FECHA, MONTO, PERIODO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA ABONOS");
        }
        
    }
    
    public ModeloAbonosTabla(String MOVIMIENTO) throws ClassNotFoundException, SQLException
    {
        Movimiento = MOVIMIENTO;
        titulos = new String[4];
        titulos[0] = "#";
        titulos[1] = "FECHA";
        titulos[2] = "MONTO";
        titulos[3] = "PERIODO";
                
        x = new ControlAbonos();
        try 
        {
            lista = (ArrayList<ModeloAbonos>) x.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA ABONOS");
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
                case 0: return lista.get(rowIndex).getIDABONO();
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
                    case 0: return lista.get(rowIndex).getIDABONO();
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
                        case 0: return lista.get(rowIndex).getIDABONO();
                        case 1: return lista.get(rowIndex).getFECHA();
                        case 2: return lista.get(rowIndex).getMONTO();
                        case 3: return lista.get(rowIndex).getPERIODO();

                        default: return null;

                    } 
                }
        else
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getIDABONO();
                    case 1: return lista.get(rowIndex).getNOMBRE()+" "+lista.get(rowIndex).getAPELLIDOS();
                    case 2: return lista.get(rowIndex).getFECHA();
                    case 3: return lista.get(rowIndex).getDESCRIPCION();
                    case 4: return lista.get(rowIndex).getMONTO();
                    case 5: return lista.get(rowIndex).getPERIODO();

                    default: return null;

                }       
    
    }
    
}