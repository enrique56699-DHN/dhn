/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlBitacora;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class ModeloBitacoraTabla extends AbstractTableModel
{
    String[]titulos = new String[5];
    ArrayList<ModeloBitacora> lista = new ArrayList<>();
    ControlBitacora x;
    int CONSULTA;
    
    public ModeloBitacoraTabla(String TABLA, int REGISTRO) throws ClassNotFoundException, SQLException
    {
        switch (TABLA)
        {
            case "PENDIENTES":
                CONSULTA = 0;
                titulos[0] ="FECHA";
                titulos[1] ="ACCION";
                titulos[2] ="USUARIO";
                titulos[3] ="ANTES";
                titulos[4] ="DESPUES";
                break;
            case "PAGOS":
                CONSULTA = 1;
                titulos = new String[2];
                titulos[0] ="FECHA";
                titulos[1] ="USUARIO";
                break;
            case "CONTRATOS":
                CONSULTA = 0;
                titulos = new String[5];
                titulos[0] ="FECHA";
                titulos[1] ="ACCION";
                titulos[2] ="USUARIO";
                titulos[3] ="ANTES";
                titulos[4] ="DESPUES";
                break;
            case "COTIZACIONES":
                CONSULTA = 3;
                titulos = new String[4];
                titulos[0] ="FECHA";
                titulos[1] ="ACCION";
                titulos[2] ="USUARIO";
                titulos[3] ="COMENTARIOS";
                break;
            case "VEHICULOSSEG":
                CONSULTA = 0;
                titulos = new String[5];
                titulos[0] ="FECHA";
                titulos[1] ="ACCION";
                titulos[2] ="USUARIO";
                titulos[3] ="MOVIMIENTO";
                titulos[4] ="N. DE SALIDA";
                break;
            case "VEHICULOSSER":
                CONSULTA = 0;
                titulos = new String[5];
                titulos[0] ="FECHA";
                titulos[1] ="ACCION";
                titulos[2] ="USUARIO";
                titulos[3] ="MOVIMIENTO";
                titulos[4] ="N. DE SALIDA";                
                break;
            case "MOTIVOSR":
                CONSULTA = 2;
                titulos = new String[3];
                titulos[0] ="FECHA";
                titulos[1] ="USUARIO";
                titulos[2] ="MOTIVO DE RECHAZO";
                break;
            default:
                break;
        }
        x = new ControlBitacora();
        try 
        {
            lista = (ArrayList<ModeloBitacora>) x.getRegistros(TABLA, REGISTRO);
        }

        catch (Exception ex) 
        {
            System.out.print("ERROR EN LA CREACION DE LA BITACORA "+TABLA+" "+ex);
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
                    case 0: return lista.get(rowIndex).getFECHA();
                    case 1: return lista.get(rowIndex).getACCION();
                    case 2: return lista.get(rowIndex).getUSER();
                    case 3: return lista.get(rowIndex).getCOMENTARIOS();
                    case 4: return lista.get(rowIndex).getREFERENCIA();
                    default: return null;
                }                     
            case 1:
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getFECHA();
                    case 1: return lista.get(rowIndex).getUSER();
                    default: return null;
                }
            case 2:   
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getFECHA();
                    case 1: return lista.get(rowIndex).getUSER();
                    case 2: return lista.get(rowIndex).getREFERENCIA();
                    default: return null;
                }
            case 3:
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getFECHA();
                    case 1: return lista.get(rowIndex).getACCION();
                    case 2: return lista.get(rowIndex).getUSER();
                    case 3: return lista.get(rowIndex).getCOMENTARIOS();
                    default: return null;
                }  
            default:
                return null;
                
        }        
    }
    
}
