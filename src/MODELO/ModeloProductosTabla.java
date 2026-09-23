/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlProductos;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author enrique.orozco
 */
public class ModeloProductosTabla extends AbstractTableModel
{
    String[]titulos = new String[6];
    ArrayList<ModeloProductos> mdl = new ArrayList<>();
    ControlProductos ctrl;
    int CONSULTA = 0;
    
    public ModeloProductosTabla() throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 0;
        titulos[0] ="ID";
        titulos[1] ="CLAVE SICAR";
        titulos[2] ="DESCRIPCION";
        titulos[3] ="CATEGORIA";
        titulos[4] ="PRECIO PUBLICO";
        titulos[5] ="PRECIO INSTALADOR";
        
        ctrl = new ControlProductos();
        try 
        {
            mdl = (ArrayList<ModeloProductos>) ctrl.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA PRODUCTOS");
        }
    }
    
    public ModeloProductosTabla(String MONEDA) throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        
        CONSULTA = 1;
        titulos = new String[7];
        titulos[0] ="ID";
        titulos[1] ="COSTO";
        titulos[2] ="IVA";
        titulos[3] ="PP";
        titulos[4] ="PI";
        titulos[5] ="CPP";
        titulos[6] ="CPI";
        
        ctrl = new ControlProductos();
        try 
        {
            mdl = (ArrayList<ModeloProductos>) ctrl.getRegistrosMONEDA(MONEDA);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA PRODUCTOS Y MONEDA ");
        }
    }
    
    public ModeloProductosTabla(String TECNOLOGIA, String CAPACIDAD, String VOLTAJE, String PRESTACION, String MARCA) throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 2;
        titulos = new String[2];
        titulos[0] ="ID";
        titulos[1] ="DESCRIPCION";
        
        ctrl = new ControlProductos();
        try 
        {
            mdl = (ArrayList<ModeloProductos>) ctrl.getRegistrosHVAC(TECNOLOGIA, CAPACIDAD, VOLTAJE, PRESTACION, MARCA);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA PRODUCTOS HVAC");
        }
    }
    
    public ModeloProductosTabla(String PRODUCTO, String CATEGORIA) throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 2;
        titulos = new String[2];
        titulos[0] ="ID";
        titulos[1] ="DESCRIPCION";
        
        ctrl = new ControlProductos();
        try 
        {
            mdl = (ArrayList<ModeloProductos>) ctrl.getRegistros(PRODUCTO, CATEGORIA);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA PRODUCTOS HVAC");
        }
    }
    
    public ModeloProductosTabla(String ESTADO, int AUX) throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 3;
        titulos = new String[1];
        titulos[0] ="CATEGORIA";
        
        ctrl = new ControlProductos();
        try 
        {
            mdl = (ArrayList<ModeloProductos>) ctrl.getRegistrosCAT();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LISTA CATEGORIAS DE PRODUCTOS");
        }
    }
    
    public ModeloProductosTabla(String ESTADO,boolean AUX) throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 4;
        titulos = new String[9];
        titulos[0] ="ID";
        titulos[1] ="CLAVE SICAR";
        titulos[2] ="CLAVE SAT";
        titulos[3] ="CODIGO DE BARRAS";
        titulos[4] ="DESCRIPCION";
        titulos[5] ="CATEGORIA";
        titulos[6] ="PRECIO PUBLICO";
        titulos[7] ="PRECIO INSTALADOR";
        titulos[8] ="FOTO";
        
        ctrl = new ControlProductos();
        try 
        {
            mdl = (ArrayList<ModeloProductos>) ctrl.getRegistros(ESTADO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA PRODUCTOS JOINS");
        }
    }
    
    public ModeloProductosTabla(String ESTADO,String CATEGORIA,boolean AUX) throws ClassNotFoundException, SQLException 
    {
        //String status =Estado;
        CONSULTA = 5;
        titulos = new String[6];
        titulos[0] ="ID";
        titulos[1] ="CLAVE SICAR";
        titulos[2] ="CODIGO DE BARRAS";
        titulos[3] ="DESCRIPCION";
        titulos[4] ="PRECIO PUBLICO";
        titulos[5] ="FOTO";
        
        ctrl = new ControlProductos();
        try 
        {
            mdl = (ArrayList<ModeloProductos>) ctrl.getRegistros(ESTADO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA PRODUCTOS JOINS");
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
                    case 0: return mdl.get(rowIndex).getIDPRODUCTO();
                    case 1: return mdl.get(rowIndex).getSICAR();
                    case 2: return mdl.get(rowIndex).getDESCRIPCION();
                    case 3: return mdl.get(rowIndex).getCATEGORIA();
                    case 4: return mdl.get(rowIndex).getPP();
                    case 5: return mdl.get(rowIndex).getPI();
                    default:
                        return null;
                }
            case 1:
                switch(columnIndex)
                {
                    case 0: return mdl.get(rowIndex).getIDPRODUCTO();
                    case 1: return mdl.get(rowIndex).getCOSTO();
                    case 2: return mdl.get(rowIndex).getIVA();
                    case 3: return mdl.get(rowIndex).getPP();
                    case 4: return mdl.get(rowIndex).getPI();
                    case 5: return mdl.get(rowIndex).getCPP();
                    case 6: return mdl.get(rowIndex).getCPI();
                    default:
                        return null;
                }
            case 2:
                switch(columnIndex)
                {
                    case 0: return mdl.get(rowIndex).getIDPRODUCTO();
                    case 1: return mdl.get(rowIndex).getDESCRIPCION();
                    default:
                        return null;
                }
            case 3:
                switch(columnIndex)
                {
                    case 0: return mdl.get(rowIndex).getCATEGORIA();
                    default:
                        return null;
                }
            case 4:
                switch(columnIndex)
                {
                    case 0: return mdl.get(rowIndex).getIDPRODUCTO();
                    case 1: return mdl.get(rowIndex).getSICAR();
                    case 2: return mdl.get(rowIndex).getCLAVESAT();
                    case 3: return mdl.get(rowIndex).getCB();
                    case 4: return mdl.get(rowIndex).getDESCRIPCION();
                    case 5: return mdl.get(rowIndex).getCATEGORIA();
                    case 6: return mdl.get(rowIndex).getPP();
                    case 7: return mdl.get(rowIndex).getPI();
                    case 8: return mdl.get(rowIndex).getFOTO();
                    default:
                        return null;
                }  
            case 5:
                switch(columnIndex)
                {
                    case 0: return mdl.get(rowIndex).getIDPRODUCTO();
                    case 1: return mdl.get(rowIndex).getSICAR();
                    case 2: return mdl.get(rowIndex).getCB();
                    case 3: return mdl.get(rowIndex).getDESCRIPCION();
                    case 4: return mdl.get(rowIndex).getPP();
                    case 5: return mdl.get(rowIndex).getFOTO();
                    default:
                        return null;
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
