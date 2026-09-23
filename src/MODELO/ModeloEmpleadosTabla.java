/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlEmpleados;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloEmpleadosTabla extends AbstractTableModel
{
    String[]titulos = new String[11];
    private ArrayList<ModeloEmpleados> lista = new ArrayList<>();
    private ControlEmpleados datos;
    int CONSULTA;

    public ModeloEmpleadosTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        CONSULTA = 0;
        titulos[0] ="#";
        titulos[1] ="NOMBRE";
        titulos[2] ="APELLIDOS";
        titulos[3] ="TEL";
        titulos[4] ="SUELDO";
        titulos[5] ="EMPRESA";
        titulos[6] ="BONO POR CAJA DE AHORRO";
        titulos[7] ="BONO DE PRODUCTIVIDAD";
        titulos[8] ="FECHA DE INGRESO";
        titulos[9] ="ESTADO";
        titulos[10] ="IDEPUESTO";
        
        datos = new ControlEmpleados();
        try 
        {
            lista = (ArrayList<ModeloEmpleados>) datos.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE  LA TABLA EMPLEADO");
        }
    }
    
    public ModeloEmpleadosTabla(String ESTADO) throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        CONSULTA = 2;
        titulos = new String[11];
        titulos[0] ="#";
        titulos[1] ="NOMBRE";
        titulos[2] ="APELLIDOS";
        titulos[3] ="TEL";
        titulos[4] ="SUELDO";
        titulos[5] ="EMPRESA";
        titulos[6] ="BONO POR CAJA DE AHORRO";
        titulos[7] ="BONO DE PRODUCTIVIDAD";
        titulos[8] ="FECHA DE INGRESO";
        titulos[9] ="DEPARTAMENTO";
        titulos[10] ="PUESTO";
        
        datos = new ControlEmpleados();
        try 
        {
            lista = (ArrayList<ModeloEmpleados>) datos.getRegistrosE(ESTADO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE  LA TABLA EMPLEADO");
        }
    }
    
    public ModeloEmpleadosTabla(String ESTADO, String ORG) throws ClassNotFoundException, SQLException
    {
        CONSULTA = 1;
        titulos = new String[1];
        titulos[0] ="NOMBRE";        
        datos = new ControlEmpleados();
        try 
        {
            if(ESTADO.equals("ALMACEN"))           
                lista = (ArrayList<ModeloEmpleados>) datos.getNOMBRE(ESTADO);            
            else
                lista = (ArrayList<ModeloEmpleados>) datos.getRegistros(ESTADO, ORG);
        }        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE  LA TABLA EMPLEADO ACTIVOS ASC");
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
                    case 0: return lista.get(rowIndex).getIDEMPLEADO();
                    case 1: return lista.get(rowIndex).getNOMBRE();
                    case 2: return lista.get(rowIndex).getAPELLIDOS();
                    case 3: return lista.get(rowIndex).getTEL();
                    case 4: return lista.get(rowIndex).getSUELDO();
                    case 5: return lista.get(rowIndex).getEMPRESA();
                    case 6: return lista.get(rowIndex).getBONO();
                    case 7: return lista.get(rowIndex).getBONOP();
                    case 8: return lista.get(rowIndex).getFECHA();
                    case 9: return lista.get(rowIndex).getDPTO();
                    case 10: return lista.get(rowIndex).getPUESTO();

                    default: return null;
                }
            case 1:
                switch(columnIndex)
                {
                    case 1: return lista.get(rowIndex).getNOMBRE();
                    default: return null;
                }
            case 2:
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getIDEMPLEADO();
                    case 1: return lista.get(rowIndex).getNOMBRE();
                    case 2: return lista.get(rowIndex).getAPELLIDOS();
                    case 3: return lista.get(rowIndex).getTEL();
                    case 4: return lista.get(rowIndex).getSUELDO();
                    case 5: return lista.get(rowIndex).getEMPRESA();
                    case 6: return lista.get(rowIndex).getBONO();
                    case 7: return lista.get(rowIndex).getBONOP();
                    case 8: return lista.get(rowIndex).getFECHA();
                    case 9: return lista.get(rowIndex).getDPTO();
                    case 10: return lista.get(rowIndex).getPUESTO();
                    default:
                        return null;
                }
            default:
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getIDEMPLEADO();
                    case 1: return lista.get(rowIndex).getNOMBRE();
                    case 2: return lista.get(rowIndex).getAPELLIDOS();
                    case 3: return lista.get(rowIndex).getTEL();
                    case 4: return lista.get(rowIndex).getSUELDO();
                    case 5: return lista.get(rowIndex).getEMPRESA();
                    case 6: return lista.get(rowIndex).getBONO();
                    case 7: return lista.get(rowIndex).getBONOP();
                    case 8: return lista.get(rowIndex).getFECHA();
                    case 9: return lista.get(rowIndex).getESTADO();
                    case 10: return lista.get(rowIndex).getIDEPUESTO();

                    default:
                        return null;
                }
        }
        
    }
}
