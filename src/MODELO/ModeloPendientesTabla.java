/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.ControlPendientes;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloPendientesTabla extends AbstractTableModel
{
    String[]titulos = new String[12];
    ArrayList<ModeloPendientes> lista = new ArrayList<>();
    ControlPendientes x;
    int CONSULTA = 0;
    
    public ModeloPendientesTabla() throws ClassNotFoundException, SQLException
    {
        CONSULTA = 0;
        titulos[0] ="#";
        titulos[1] ="NOMBRE DE CLIENTE";
        titulos[2] ="TELEFONO";
        titulos[3] ="DIRECCION";
        titulos[4] ="SUBCATEGORIA";
        titulos[5] ="DESCRIPCION";
        titulos[6] ="COMENTARIOS";
        titulos[7] ="FECHA DE REGISTRO";
        titulos[8] ="FECHA ESTIMADA";
        titulos[9] ="DIAS TRANSCURRIDOS";
        titulos[10] ="TICKET-1";
        titulos[11] ="TICKET/FACTURA";   
                
        x = new ControlPendientes();
        try 
        {
            lista = (ArrayList<ModeloPendientes>) x.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA PENDIENTES");
        }
        
    }
    
    public ModeloPendientesTabla(String CATEGORIA,String ESTADO) throws ClassNotFoundException, SQLException
    {
        CONSULTA = 0;
        titulos = new String[8];
        titulos[0] ="#";
        titulos[1] ="NOMBRE DE CLIENTE";
        titulos[2] ="TELEFONO";
        titulos[3] ="DIRECCION";
        titulos[4] ="DESCRIPCION";
        titulos[5] ="COMENTARIOS";
        titulos[6] ="CATEGORIAS";
        titulos[7] ="DIAS";
                
        x = new ControlPendientes();
        try 
        {
            lista = (ArrayList<ModeloPendientes>) x.getRegistros(CATEGORIA,ESTADO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA PENDIENTES POR CATEGORIA + estado");
        }
        
    }
        
    public ModeloPendientesTabla(String ESTADO) throws ClassNotFoundException, SQLException
    {
        titulos = new String[8];
        CONSULTA = 3;
        titulos[0] ="#";
        titulos[1] ="NOMBRE DE CLIENTE";
        titulos[2] ="TELEFONO";
        titulos[3] ="CATEGORIA";
        titulos[4] ="DIRECCION";
        titulos[5] ="DESCRIPCION";
        titulos[6] ="COMENTARIOS";
        titulos[7] ="DIAS";
                
        x = new ControlPendientes();
        try 
        {
            lista = (ArrayList<ModeloPendientes>) x.getRegistrosINNER2(ESTADO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA PENDIENTES POR CATEGORIA");
        }
        
    }
    
    public ModeloPendientesTabla(String FESTIMADA, String ESTADO, int SERVICIO, int X) throws ClassNotFoundException, SQLException
    {
        titulos = new String[7];
        CONSULTA = 4;
        titulos[0] ="#";
        titulos[1] ="NOMBRE DE CLIENTE";
        titulos[2] ="TELEFONO";
        titulos[3] ="DIRECCION";
        titulos[4] ="DESCRIPCION";
        titulos[5] ="EMPLEADO";
        titulos[6] ="FECHA DE ORDEN";
                
        x = new ControlPendientes();
        try 
        {
            lista = (ArrayList<ModeloPendientes>) x.getRegistrosINNER3(FESTIMADA,ESTADO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA PENDIENTES AGENDAL");
        }
        
    }
    
    public ModeloPendientesTabla(String FESTIMADA, String ESTADO, int SERVICIO) throws ClassNotFoundException, SQLException
    {
        titulos = new String[3];
        CONSULTA = 2;
        titulos[0] ="#";
        titulos[1] ="DESCRIPCION";
        titulos[2] ="FECHA DE ORDEN";
                
        x = new ControlPendientes();
        try 
        {
            lista = (ArrayList<ModeloPendientes>) x.getRegistrosProg(FESTIMADA,ESTADO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA PENDIENTES POR CATEGORIA");
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
                        case 0: return lista.get(rowIndex).getIDPENDIENTE();
                        case 1: return lista.get(rowIndex).getNOMBRE();
                        case 2: return lista.get(rowIndex).getTEL1();
                        case 3: return lista.get(rowIndex).getCALLE()+" "+lista.get(rowIndex).getNUMERO()+" "+
                                lista.get(rowIndex).getCOLONIA()+" "+lista.get(rowIndex).getLOCALIDAD();
                        case 4: return lista.get(rowIndex).getDESCRIPCION();
                        case 5: return lista.get(rowIndex).getCOMENTARIOS();
                        case 6: return lista.get(rowIndex).getCATEGORIA();
                        case 7: return lista.get(rowIndex).getDIAS();

                        default: return null;
                    }
            case 1:
                switch(columnIndex)
                    {    
                        case 0: return lista.get(rowIndex).getIDPENDIENTE();
                        case 1: return lista.get(rowIndex).getNOMBRE();
                        case 2: return lista.get(rowIndex).getTEL1();
                        case 3: return lista.get(rowIndex).getCALLE()+" "+lista.get(rowIndex).getNUMERO()+" "+
                                lista.get(rowIndex).getCOLONIA()+" "+lista.get(rowIndex).getLOCALIDAD();
                        case 4: return lista.get(rowIndex).getSUBCATEGORIA();
                        case 5: return lista.get(rowIndex).getDESCRIPCION();
                        case 6: return lista.get(rowIndex).getCOMENTARIOS();
                        case 7: return lista.get(rowIndex).getFREG();
                        case 8: return lista.get(rowIndex).getFEST();
                        case 9: return lista.get(rowIndex).getDIAS();
                        case 10: return lista.get(rowIndex).getNPEDIDO();
                        case 11: return lista.get(rowIndex).getNPEDIDOF();

                        default: return null;
                    }
            case 2:
                switch(columnIndex)
                    {    
                        case 0: return lista.get(rowIndex).getIDPENDIENTE();
                        case 1: return lista.get(rowIndex).getDESCRIPCION();
                        case 2: return lista.get(rowIndex).getFINIORDEN();

                        default: return null;
                    }
            case 3:
                switch(columnIndex)
                    {    
                        case 0: return lista.get(rowIndex).getIDPENDIENTE();
                        case 1: return lista.get(rowIndex).getNOMBRE();
                        case 2: return lista.get(rowIndex).getTEL1();
                        case 3: return lista.get(rowIndex).getCATEGORIA();
                        case 4: return lista.get(rowIndex).getCALLE()+" "+lista.get(rowIndex).getNUMERO()+" "+
                                lista.get(rowIndex).getCOLONIA()+" "+lista.get(rowIndex).getLOCALIDAD();
                        case 5: return lista.get(rowIndex).getDESCRIPCION();
                        case 6: return lista.get(rowIndex).getCOMENTARIOS();
                        case 7: return lista.get(rowIndex).getDIAS();

                        default: return null;
                    }
            case 4:
                switch(columnIndex)
                    {    
                        case 0: return lista.get(rowIndex).getIDPENDIENTE();
                        case 1: return lista.get(rowIndex).getNOMBRE();
                        case 2: return lista.get(rowIndex).getTEL1();
                        case 3: return lista.get(rowIndex).getCALLE()+" "+lista.get(rowIndex).getNUMERO()+" "+
                                lista.get(rowIndex).getCOLONIA()+" "+lista.get(rowIndex).getLOCALIDAD();
                        case 4: return lista.get(rowIndex).getDESCRIPCION();
                        case 5: return lista.get(rowIndex).getEMPLEADO();//+ " "+lista.get(rowIndex).getAPELLIDOS();
                        case 6: return lista.get(rowIndex).getFEST();
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
