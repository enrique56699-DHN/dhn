/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlClientes;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco
 */
public class ModeloClientesTabla extends AbstractTableModel
{

    String[]titulos = new String[4];
    private ArrayList<ModeloClientes> cli = new ArrayList<>();
    private ControlClientes cl;
    private int CONSULTA;

    public ModeloClientesTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        CONSULTA = 0;
        titulos[0] ="#";
        titulos[1] ="NOMBRE DEL CLIENTE";
        titulos[2] ="TEL1";
        titulos[3] ="TEL2";

        
        cl = new ControlClientes();
        try 
        {
            cli = (ArrayList<ModeloClientes>) cl.getRegistrosT();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR AL CREAR TABLA CLIENTES");
        }
    }
    
    public ModeloClientesTabla(String TIPO, int ID) throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        CONSULTA = 1;
        titulos = new String[3];
        titulos[0] ="#";
        titulos[1] ="ESTADO";
        titulos[2] ="#ORDEN";
        
        cl = new ControlClientes();
        try 
        {
            cli = (ArrayList<ModeloClientes>) cl.getRegistros(TIPO, ID);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR AL CREAR TABLA CLIENTES/SERVICIOS");
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
                    case 0: return cli.get(rowIndex).getIDCLIENTE();
                    case 1: return cli.get(rowIndex).getNOMBRE();
                    case 2: return cli.get(rowIndex).getTEL1();
                    case 3: return cli.get(rowIndex).getTEL2();

                    default: return null;
                }
            case 1:
                switch(columnIndex)
                {
                    case 0: return cli.get(rowIndex).getID();
                    case 1: return cli.get(rowIndex).getESTADO();
                    case 2: return cli.get(rowIndex).getIDORDEN();

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
