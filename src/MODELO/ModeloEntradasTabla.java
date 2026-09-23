/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlEntradas;
import DHNv2.G;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloEntradasTabla extends AbstractTableModel
{

    String[]titulos = new String[7];
    ArrayList<ModeloEntradas> lista = new ArrayList<>();
    ControlEntradas x;
    int CONSULTA;

    public ModeloEntradasTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        CONSULTA=2;
        titulos[0] = "#";
        titulos[1] = "DESCRIPCION";
        titulos[2] = "MONTO";
        titulos[3] = "CATEGORIA";
        titulos[4] = "FECHA";
        titulos[5] = "METODO";
        titulos[6] = "CAJA";
                
        x = new ControlEntradas();
        try 
        {
            lista = (ArrayList<ModeloEntradas>) x.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA ENTRADAS");
        }
        
    }
    
    public ModeloEntradasTabla(String METODO) throws ClassNotFoundException, SQLException
    {
        CONSULTA=0;
        titulos = new String[5];
        titulos[0] = "#";
        titulos[1] = "DESCRIPCION";
        titulos[2] = "MONTO";
        titulos[3] = "CATEGORIA";
        titulos[4] = "FECHA";
                
        x = new ControlEntradas();
        try 
        {
            lista = (ArrayList<ModeloEntradas>) x.getRegistros(METODO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA ENTRADAS POR METODO");
        }
        
    }
    
    public ModeloEntradasTabla(int DE, int AL, String METODO)throws ClassNotFoundException, SQLException
    {
        CONSULTA=1;
        titulos = new String[3];
        
        titulos[0] ="#";
        titulos[1] ="DESCRIPCION";
        titulos[2] ="MONTO";

        
        x = new ControlEntradas();
        try 
        {
            lista = (ArrayList<ModeloEntradas>) x.getRegistros(DE,AL,METODO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA ENTRADAS DE AL CON METODO NUEVO");
        }
    }
    
    public ModeloEntradasTabla(String DE, String AL, String METODO)throws ClassNotFoundException, SQLException
    {
        
        CONSULTA=1;
        titulos = new String[3];
        
        titulos[0] ="#";
        titulos[1] ="DESCRIPCION";
        titulos[2] ="MONTO";

        
        x = new ControlEntradas();
        try 
        {
            lista = (ArrayList<ModeloEntradas>) x.getRegistros(DE,AL,METODO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA ENTRADAS POR FECHAS Y METODO");
        }
    }
    
    public ModeloEntradasTabla(int IDENTRADAI, String METODO, int IDCAJA)throws ClassNotFoundException, SQLException
    {
        titulos = new String[3];
        CONSULTA =1;
        /*titulos[0] ="#";
        titulos[1] ="DESCRIPCION";
        titulos[2] ="MONTO";*/

        
        x = new ControlEntradas();
        try 
        {
            lista = (ArrayList<ModeloEntradas>) x.listaCorteD(IDENTRADAI,METODO, IDCAJA);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA ENTRADAS");
        }
    }
    
    public ModeloEntradasTabla(int IDENTRADAI, int IDENTRADAF, String METODO, int IDCAJA)throws ClassNotFoundException, SQLException
    {

        titulos = new String[3];
        CONSULTA =1;
        /*titulos[0] ="#";
        titulos[1] ="DESCRIPCION";
        titulos[2] ="MONTO";*/

        
        x = new ControlEntradas();
        try 
        {
            lista = (ArrayList<ModeloEntradas>) x.listaCorteD(IDENTRADAI,IDENTRADAF,METODO, IDCAJA);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA ENTRADAS");
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
                    case 0: return lista.get(rowIndex).getIDENTRADA();
                    case 1: return lista.get(rowIndex).getDESCRIPCION();
                    case 2: return lista.get(rowIndex).getMONTO();
                    case 3: return lista.get(rowIndex).getCATEGORIA();
                    case 4: return lista.get(rowIndex).getFECHA();
                    default: return null;

                }
            case 1:
                switch(columnIndex)
                    {
                        case 0: return lista.get(rowIndex).getIDENTRADA();
                        case 1: return lista.get(rowIndex).getDESCRIPCION();
                        case 2: return lista.get(rowIndex).getMONTO();
                        default: return null;

                    }
            case 2:
                switch(columnIndex)
                    {
                        case 0: return lista.get(rowIndex).getIDENTRADA();
                        case 1: return lista.get(rowIndex).getDESCRIPCION();
                        case 2: return "$ "+G.numero.format(lista.get(rowIndex).getMONTO())+".00";
                        case 3: return lista.get(rowIndex).getCATEGORIA();
                        case 4: return lista.get(rowIndex).getFECHA();
                        case 5: return lista.get(rowIndex).getMETODO();
                        case 6: return lista.get(rowIndex).getIDCAJA();
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
