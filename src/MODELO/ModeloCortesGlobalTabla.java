/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlCortesGlobal;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author win 10
 */
public class ModeloCortesGlobalTabla extends AbstractTableModel
{
    String[]titulos = new String[8];
    ArrayList<ModeloCortesGlobal> lista = new ArrayList<>();
    ControlCortesGlobal x;
    int CONSULTA;

    public ModeloCortesGlobalTabla() throws ClassNotFoundException, SQLException 
    {
        CONSULTA = 0;
        titulos = new String[8];
        titulos[0] ="#";
        titulos[1] ="DHN";
        titulos[2] ="EDH";
        titulos[3] ="VALES";
        titulos[4] ="CF";
        titulos[5] ="FECHA";
        titulos[6] ="MONTOG";
        titulos[7] ="MONTOR";

        x = new ControlCortesGlobal();
        try 
        {
            lista = (ArrayList<ModeloCortesGlobal>) x.getRegistros();
        }
        catch (Exception ex) 
        {
            System.out.print("ERROR EN LA CREACION DE LA VALES "+ex);
        }
    }
    
    
    /*public ModeloValesTabla(String TABLA, int REGISTRO) throws ClassNotFoundException, SQLException
    {
        switch (TABLA)
        {
            case "CARGOS":
                CONSULTA = 0;
                titulos[0] ="FECHA";
                titulos[1] ="ACCION";
                titulos[2] ="USUARIO";
                titulos[3] ="ANTES";
                titulos[4] ="DESPUES";

                x = new ControlVales();
                try 
                {
                    lista = (ArrayList<ModeloVales>) x.getRegistros(TABLA, REGISTRO);
                }

                catch (Exception ex) 
                {
                    System.out.print("ERROR EN LA CREACION DE LA BITACORA "+TABLA+" "+ex);
                }
                break;
            case "ABONOS":
                CONSULTA = 1;
                titulos = new String[2];
                titulos[0] ="FECHA";
                titulos[1] ="USUARIO";

                x = new ControlVales();
                try 
                {
                    lista = (ArrayList<ModeloVales>) x.getRegistros(TABLA, REGISTRO);
                }

                catch (Exception ex) 
                {
                    System.out.print("ERROR EN LA CREACION DE LA BITACORA "+TABLA+" "+ex);
                }
                break;
            case "TODO":
                CONSULTA = 0;
                titulos = new String[5];
                titulos[0] ="FECHA";
                titulos[1] ="ACCION";
                titulos[2] ="USUARIO";
                titulos[3] ="ANTES";
                titulos[4] ="DESPUES";

                x = new ControlVales();
                try 
                {
                    lista = (ArrayList<ModeloVales>) x.getRegistros(TABLA, REGISTRO);
                }

                catch (Exception ex) 
                {
                    System.out.print("ERROR EN LA CREACION DE LA BITACORA "+TABLA+" "+ex);
                }
                break;
            case "VEHICULOSSEG":
                CONSULTA = 0;
                titulos = new String[5];
                titulos[0] ="FECHA";
                titulos[1] ="ACCION";
                titulos[2] ="USUARIO";
                titulos[3] ="MOVIMIENTO";
                titulos[4] ="N. DE SALIDA";

                x = new ControlVales();
                try 
                {
                    lista = (ArrayList<ModeloVales>) x.getRegistros(TABLA, REGISTRO);
                }

                catch (Exception ex) 
                {
                    System.out.print("ERROR EN LA CREACION DE LA BITACORA "+TABLA+" "+ex);
                }
                break;
            case "VEHICULOSSER":
                CONSULTA = 0;
                titulos = new String[5];
                titulos[0] ="FECHA";
                titulos[1] ="ACCION";
                titulos[2] ="USUARIO";
                titulos[3] ="MOVIMIENTO";
                titulos[4] ="N. DE SALIDA";

                x = new ControlVales();
                try 
                {
                    lista = (ArrayList<ModeloVales>) x.getRegistros(TABLA, REGISTRO);
                }

                catch (Exception ex) 
                {
                    System.out.print("ERROR EN LA CREACION DE LA BITACORA "+TABLA+" "+ex);
                }
                break;
            default: break;
        }
        
    }*/

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
                    case 0: return lista.get(rowIndex).getIDCORTEG();
                    case 1: return lista.get(rowIndex).getDHN();
                    case 2: return lista.get(rowIndex).getEDH();
                    case 3: return lista.get(rowIndex).getVALES();
                    case 4: return lista.get(rowIndex).getCF();
                    case 5: return lista.get(rowIndex).getFECHA();
                    case 6: return lista.get(rowIndex).getMONTOG();
                    case 7: return lista.get(rowIndex).getMONTOR();
                    default: 
                        return null;
                }                     
            
            default:
                return null;
                
        }        
    }
    
}

