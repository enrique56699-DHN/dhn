/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlEDocs;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloEDocsTabla extends AbstractTableModel
{
    String[]titulos;
    private ArrayList<ModeloEDocs> lista = new ArrayList<>();
    private ControlEDocs datos;
    int CONSULTA;

    public ModeloEDocsTabla(int IDEMPLEADO) throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        titulos = new String[5];
        CONSULTA = 0;
        titulos[0] ="#";
        titulos[1] ="NUMERO";
        titulos[2] ="TIPO";
        titulos[3] ="VIGENCIA";
        titulos[4] ="RUTA";
        
        datos = new ControlEDocs();
        try 
        {
            lista = (ArrayList<ModeloEDocs>) datos.getRegistros(IDEMPLEADO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE  LA TABLA EDOCS");
        }
    }
    
    public ModeloEDocsTabla() throws ClassNotFoundException, SQLException
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
        titulos[7] ="FECHA DE INGRESO";
        titulos[8] ="ESTADO";
        
        datos = new ControlEDocs();
        try 
        {
            lista = (ArrayList<ModeloEDocs>) datos.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE  LA TABLA EMPLEADO");
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
                    case 0: return lista.get(rowIndex).getIDEDOC();
                    case 1: return lista.get(rowIndex).getNUMBER();
                    case 2: return lista.get(rowIndex).getDESCRIPCION();
                    case 3: return lista.get(rowIndex).getVIGENCIA();
                    case 4: return lista.get(rowIndex).getDOC();

                    default:
                        return null;
                }
            case 1:
                switch(columnIndex)
                {
                    case 1: return lista.get(rowIndex).getNOMBRE();

                    default:
                        return null;
                }
            default: 
                return null;
        }
        
    }
}
