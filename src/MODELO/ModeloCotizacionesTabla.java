/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlCotizaciones;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author win 10
 */
public class ModeloCotizacionesTabla extends AbstractTableModel
{
    String[]titulos;
    ArrayList<ModeloCotizaciones> lista = new ArrayList<>();
    ControlCotizaciones ctrl;
    String CONSULTA;
    
    public ModeloCotizacionesTabla(String CONSULTA, int ID, String ESTADO) throws ClassNotFoundException, SQLException
    {
        this.CONSULTA = CONSULTA;      
        switch(CONSULTA)
        {
            case "GLOBAL":
                titulos = new String[9];
                titulos[0] ="ID";
                titulos[1] ="SERVICIO";
                titulos[2] ="FECHA";
                titulos[3] ="CATEGORIA";                
                titulos[4] ="CLIENTE";
                titulos[5] ="DOMICILIO";
                titulos[6] ="TELEFONO";
                titulos[7] ="TELEFONO2";
                titulos[8] ="DOCUMENTO";
                break;            
            case "IDPENDIENTE":
                titulos = new String[5];
                titulos[0] ="ID";
                titulos[1] ="FECHA";
                titulos[2] ="CATEGORIA";
                titulos[3] ="DOCUMENTO";
                titulos[4] ="ESTADO";
                break;
            default:
                break;
        }
        ctrl = new ControlCotizaciones();
        try 
        {
            lista = (ArrayList<ModeloCotizaciones>) ctrl.getRegistros(CONSULTA, ID,ESTADO);
        }
        catch (Exception ex) 
        {
            System.out.print("ERROR EN LA CREACION DE LA COTIZACIONES  "+ex);
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
            case "GLOBAL":
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getIDCOTIZACION();
                    case 1: return lista.get(rowIndex).getIDPENDIENTE();
                    case 2: return lista.get(rowIndex).getFECHA();
                    case 3: return lista.get(rowIndex).getCATEGORIA();                    
                    case 4: return lista.get(rowIndex).getNOMBRE();
                    case 5: return lista.get(rowIndex).getCALLE()+" "+lista.get(rowIndex).getNUMERO()
                            +" "+lista.get(rowIndex).getCOLONIA()+" "+lista.get(rowIndex).getLOCALIDAD();
                    case 6: return lista.get(rowIndex).getTEL();
                    case 7: return lista.get(rowIndex).getTEL2();
                    case 8: return lista.get(rowIndex).getDOC();
                    default:
                        return null;
                }             
            case "IDPENDIENTE":
                switch(columnIndex)
                {
                    case 0: return lista.get(rowIndex).getIDCOTIZACION();
                    case 1: return lista.get(rowIndex).getFECHA();
                    case 2: return lista.get(rowIndex).getCATEGORIA();
                    case 3: return lista.get(rowIndex).getDOC();
                    case 4: return lista.get(rowIndex).getESTADO();
                    default:
                        return null;
                }   
            default:
                switch(columnIndex)
                {                    
                    default:
                        return null;
                }
                //return null;                
        }        
    }
    
}

