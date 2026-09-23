/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlCarrito;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Oscar E Gonzalez G
 */
public class ModeloCarritoTabla extends AbstractTableModel
{
    String[]titulos = new String[5];
    ArrayList<ModeloCarrito> x = new ArrayList<>();
    ControlCarrito pto;
    int CONSULTA;
    
    //private Locale locale = new Locale ("en", "US");
    //private NumberFormat totalF = NumberFormat.getInstance (locale);    


    public ModeloCarritoTabla() throws ClassNotFoundException, SQLException {
        CONSULTA=0;
        titulos[0] ="#";
        titulos[1] ="IDPENDIENTE";
        titulos[2] ="IDPRODUCTO";
        titulos[3] ="CANTIDAD";
        titulos[4] ="TOTAL";
        
        pto = new ControlCarrito();
        try 
        {
            x = (ArrayList<ModeloCarrito>) pto.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA TABLA CARRITO");
        }
        
    }
    
    public ModeloCarritoTabla(int IDPENDIENTE) throws ClassNotFoundException, SQLException {
        //TOMA EL VALOR DE IDCOTNRATO PARA REALIZAR EL IF HASTA ABAJO DONDE SE LLENA LA TABLA
        CONSULTA=1;
        titulos = new String[6];
        
        titulos[0] ="ID-CAR";
        titulos[1] ="CANT";
        titulos[2] ="SICAR";
        titulos[3] ="DESCRIPCION";
        titulos[4] ="TOTAL";
        titulos[5] ="ESTADO";
        //titulos[6] ="EMPLEADO";
        
        pto = new ControlCarrito();
        
        try 
        {
            x = (ArrayList<ModeloCarrito>) pto.getRegistrosORDEN(IDPENDIENTE);
        }
        
        catch (Exception ex) 
        {
            System.out.println("TABLA CARRITO CON NUMERO DE SERVICIO");
        }
        
    }
    
    public ModeloCarritoTabla(String ESTADO, String FECHA, int IDEMPLEADO) throws ClassNotFoundException, SQLException {
        CONSULTA=2;
        titulos = new String[4];
        
        titulos[0] ="ID-CAR";
        titulos[1] ="CANT";
        titulos[2] ="DESCRIPCION";
        titulos[3] ="SERVICIO";
        
        pto = new ControlCarrito();
        
        try 
        {
            x = (ArrayList<ModeloCarrito>) pto.getRegistros(ESTADO, FECHA, IDEMPLEADO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE TABLA CARRITO ALMACEN");
        }
        
    }

    @Override
    public int getRowCount() {
        return x.size();
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
                    case 0: return x.get(rowIndex).getIDCARRITO();
                    case 1: return x.get(rowIndex).getIDPENDIENTE();
                    case 2: return x.get(rowIndex).getIDPRODUCTO();
                    case 3: return x.get(rowIndex).getCANTIDAD();
                    case 4: return x.get(rowIndex).getTOTAL();

                    default: return null;
                }
            case 1:
                switch(columnIndex)
                    {    
                        case 0: return x.get(rowIndex).getIDCARRITO();
                        case 1: return x.get(rowIndex).getCANTIDAD();
                        case 2: return x.get(rowIndex).getSICAR();
                        case 3: return x.get(rowIndex).getDESCRIPCION();
                        case 4: return x.get(rowIndex).getTOTAL();      
                        case 5: return x.get(rowIndex).getESTADO();   
                        //case 6: return x.get(rowIndex).getIDEMPLEADO();

                        default: return null;
                    }
            case 2:
                switch(columnIndex)
                    {    
                        case 0: return x.get(rowIndex).getIDCARRITO();
                        case 1: return x.get(rowIndex).getCANTIDAD();
                        case 2: return x.get(rowIndex).getDESCRIPCION();     
                        case 3: return x.get(rowIndex).getIDPENDIENTE();  

                        default: return null;
                    }
            default:
                return null;              
                
        }
    
    }
}
