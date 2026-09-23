/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlVales;
import DHNv2.G;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author win 10
 */
public class ModeloValesTabla extends AbstractTableModel
{
    String[]titulos = new String[5];
    ArrayList<ModeloVales> lista = new ArrayList<>();
    ControlVales x;
    int CONSULTA;

    public ModeloValesTabla(int ID, String TIPO, boolean ESTADO) throws ClassNotFoundException, SQLException 
    {
        CONSULTA = 0;
        titulos = new String[5];
        titulos[0] ="#";
        titulos[1] ="FECHA";
        titulos[2] ="DESCRIPCION";
        titulos[3] ="TIPO";
        titulos[4] ="MONTO";

        x = new ControlVales();
        try 
        {
            lista = (ArrayList<ModeloVales>) x.getRegistros(ID, TIPO, ESTADO);
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
                    case 0: return lista.get(rowIndex).getIDVALE();
                    case 1: return lista.get(rowIndex).getFECHA();
                    case 2: return lista.get(rowIndex).getDESCRIPCION();
                    case 3: return lista.get(rowIndex).getTIPO();
                    case 4: return "$ "+G.numero.format(lista.get(rowIndex).getMONTO());
                    default: 
                        return null;
                }                     
            
            default:
                return null;
                
        }        
    }
    
}

