/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlNominas;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloNominasTabla extends AbstractTableModel
{
    String[]titulos = new String[14];
    private ArrayList<ModeloNominas> cli = new ArrayList<>();
    private ControlNominas cl;
    private int CONSULTA;

    public ModeloNominasTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        CONSULTA = 0;
        titulos[0] ="#";
        titulos[1] ="EMPLEADO";
        titulos[2] ="EMPRESA";
        titulos[3] ="FECHA";
        titulos[4] ="SEMANA";
        titulos[5] ="AÑO";
        titulos[6] ="SUELDO";
        titulos[7] ="DIAS TRABAJADOS";
        titulos[8] ="SUELDO CALCULADO";
        titulos[9] ="BONO O EXTRA";
        titulos[10] ="CONCEPTO";
        titulos[11] ="AHORRO";
        titulos[12] ="ABONOS";
        titulos[13] ="TOTAL";
        
        cl = new ControlNominas();
        try 
        {
            cli = (ArrayList<ModeloNominas>) cl.getRegistrosINNER();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR AL CREAR TABLA NOMINAS");
        }
    }
    
    public ModeloNominasTabla(int IDNOMINA) throws ClassNotFoundException, SQLException
    {
        CONSULTA = 1;
        titulos = new String[13];
        titulos[0] ="#";
        titulos[1] ="EMPLEADO";
        titulos[2] ="EMPRESA";
        titulos[3] ="FECHA";
        titulos[4] ="SEMANA";
        titulos[5] ="AÑO";
        titulos[6] ="SUELDO";
        titulos[7] ="DIAS TRABAJADOS";
        titulos[8] ="SUELDO CALCULADO";
        titulos[9] ="BONO O EXTRA";
        titulos[10] ="AHORRO";
        titulos[11] ="ABONOS";
        titulos[12] ="TOTAL";
        
        cl = new ControlNominas();
        try 
        {
            cli = (ArrayList<ModeloNominas>) cl.getRegistrosINNER();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR AL CREAR TABLA NOMINAS CON INNER");
        }
    }
    
    @Override
    public int getRowCount() {
        return cli.size();
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
                    case 0: return cli.get(rowIndex).getIDNOMINA();
                    case 1: return cli.get(rowIndex).getNOMBRE()+" "+cli.get(rowIndex).getAPELLIDOS();
                    case 2: return cli.get(rowIndex).getEMPRESA();
                    case 3: return cli.get(rowIndex).getFECHA();
                    case 4: return cli.get(rowIndex).getSEMANA();
                    case 5: return cli.get(rowIndex).getANO();
                    case 6: return cli.get(rowIndex).getSUELDO();
                    case 7: return cli.get(rowIndex).getDIAS();
                    case 8: return cli.get(rowIndex).getNSUELDO();
                    case 9: return cli.get(rowIndex).getBONO();
                    case 10: return cli.get(rowIndex).getCONCEPTO();
                    case 11: return cli.get(rowIndex).getAHORRO();
                    case 12: return cli.get(rowIndex).getABONO();
                    case 13: return cli.get(rowIndex).getTOTAL();

                    default: return null;
                }
            case 1:
                switch(columnIndex)
                {
                    case 0: return cli.get(rowIndex).getIDNOMINA();
                    case 1: return cli.get(rowIndex).getNOMBRE()+" "+cli.get(rowIndex).getAPELLIDOS();
                    case 2: return cli.get(rowIndex).getEMPRESA();
                    case 3: return cli.get(rowIndex).getFECHA();
                    case 4: return cli.get(rowIndex).getSEMANA();
                    case 5: return cli.get(rowIndex).getANO();
                    case 6: return cli.get(rowIndex).getSUELDO();
                    case 7: return cli.get(rowIndex).getDIAS();
                    case 8: return cli.get(rowIndex).getNSUELDO();
                    case 9: return cli.get(rowIndex).getBONO();
                    case 10: return cli.get(rowIndex).getAHORRO();
                    case 11: return cli.get(rowIndex).getABONO();
                    case 12: return cli.get(rowIndex).getTOTAL();

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
