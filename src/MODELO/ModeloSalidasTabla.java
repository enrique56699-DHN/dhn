/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlSalidas;
import DHNv2.G;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloSalidasTabla extends AbstractTableModel
{
    String[]titulos = new String[7];
    ArrayList<ModeloSalidas> lista = new ArrayList<>();
    ControlSalidas x;
    int CONSULTA =0;
        
    public ModeloSalidasTabla() throws ClassNotFoundException, SQLException
    {
        CONSULTA = 2;
        titulos[0] = "No. SALIDA";
        titulos[1] = "DESCRIPCION";
        titulos[2] = "MONTO";
        titulos[3] = "CATEGORIA";
        titulos[4] = "FECHA";
        titulos[5] = "METODO";
        titulos[6] = "CAJA";
                
        x = new ControlSalidas();
        try 
        {
            lista = (ArrayList<ModeloSalidas>) x.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA SALIDAS");
        }
        
    }
    
    public ModeloSalidasTabla(String METODO) throws ClassNotFoundException, SQLException
    {
        CONSULTA=0;
        titulos = new String[5];
        titulos[0] ="No. SALIDA";
        titulos[1] ="DESCRIPCION";
        titulos[2] ="MONTO";
        titulos[3] ="CATEGORIA";
        titulos[4] ="FECHA";
                
        x = new ControlSalidas();
        try 
        {
            lista = (ArrayList<ModeloSalidas>) x.getRegistros(METODO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA SALIDAS POR METODO");
        }
        
    }
    
    public ModeloSalidasTabla(String DE, String AL)throws ClassNotFoundException, SQLException
    {
        CONSULTA=1;
        titulos = new String[3];
        
        titulos[0] ="#";
        titulos[1] ="DESCRIPCION";
        titulos[2] ="MONTO";
        
        x = new ControlSalidas();
        try 
        {
            lista = (ArrayList<ModeloSalidas>) x.getRegistros(DE,AL);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA SALIDAS");
        }
    }
    
    public ModeloSalidasTabla(String DE, String AL, String METODO)throws ClassNotFoundException, SQLException
    {
        CONSULTA=1;
        titulos = new String[3];
        
        titulos[0] ="#";
        titulos[1] ="DESCRIPCION";
        titulos[2] ="MONTO";
        
        x = new ControlSalidas();
        try 
        {
            lista = (ArrayList<ModeloSalidas>) x.getRegistros(DE,AL,METODO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA SALIDAS");
        }
    }
    
    public ModeloSalidasTabla(int IDSALIDAI, String METODO, int IDCAJA)throws ClassNotFoundException, SQLException
    {
        
        titulos = new String[3];
        CONSULTA =1;
        /*titulos[0] ="#";
        titulos[1] ="DESCRIPCION";
        titulos[2] ="MONTO";*/
        
        x = new ControlSalidas();
        try 
        {
            lista = (ArrayList<ModeloSalidas>) x.listaCorteD(IDSALIDAI,METODO,IDCAJA);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA SALIDAS");
        }
    }
    
    public ModeloSalidasTabla(int IDSALIDAI, int IDSALIDAF, String METODO, int IDCAJA)throws ClassNotFoundException, SQLException
    {
        titulos = new String[3];
        CONSULTA =1;
        /*titulos[0] ="#";
        titulos[1] ="DESCRIPCION";
        titulos[2] ="MONTO";*/
        
        x = new ControlSalidas();
        try 
        {
            lista = (ArrayList<ModeloSalidas>) x.listaCorteD(IDSALIDAI, IDSALIDAF, METODO,IDCAJA);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA SALIDAS");
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
                    case 0: return lista.get(rowIndex).getIDSALIDA();
                    case 1: return lista.get(rowIndex).getDESCRIPCION();
                    case 2: return lista.get(rowIndex).getMONTO();
                    case 3: return lista.get(rowIndex).getCATEGORIA();
                    case 4: return lista.get(rowIndex).getFECHA();
                    default: return null;

                }
           case 1:
               switch(columnIndex)
                    {
                        case 0: return lista.get(rowIndex).getIDSALIDA();
                        case 1: return lista.get(rowIndex).getDESCRIPCION();
                        case 2: return lista.get(rowIndex).getMONTO();
                        default: return null;

                    }
           case 2:
               switch(columnIndex)
                    {
                        case 0: return lista.get(rowIndex).getIDSALIDA();
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
