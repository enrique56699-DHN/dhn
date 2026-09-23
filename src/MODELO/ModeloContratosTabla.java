/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlContratos;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Oscar E Gonzalez G
 */
public class ModeloContratosTabla extends AbstractTableModel
{
    String[]titulos = new String[10];
    ArrayList<ModeloContratos> lista = new ArrayList<>();
    ControlContratos datos;
    public String DIA_CORTE;
    private int CONSULTA;
    //String NOMBRE;
    
    public ModeloContratosTabla() throws ClassNotFoundException, SQLException
    {
        //String status =Estado;
        titulos[0] ="#";
        titulos[1] ="CLIENTE";
        titulos[2] ="TELEFONO";
        titulos[3] ="DIRECCION";
        titulos[4] ="PAQUETE";
        titulos[5] ="INSTALACION";
        titulos[6] ="DIA DE CORTE";
        titulos[7] ="COMENTARIOS";
        titulos[8] ="DIAS POR VENCER";
        titulos[9] ="IP";
                
        datos = new ControlContratos();
        try 
        {
            lista = (ArrayList<ModeloContratos>) datos.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA CONTRATOS");
        }
        
    }
        
    public ModeloContratosTabla(String ESTADO) throws ClassNotFoundException, SQLException, Exception
    {
        CONSULTA = 0;
        titulos = new String[11];
        titulos[0] ="#";
        titulos[1] ="CLIENTE";
        titulos[2] ="TELEFONO";
        titulos[3] ="DIRECCION";
        titulos[4] ="COSTO";
        titulos[5] ="PAQUETE";
        titulos[6] ="INSTALACION";
        titulos[7] ="DIA DE CORTE";
        titulos[8] ="COMENTARIOS";
        titulos[9] ="DIAS POR VENCER";
        titulos[10] ="IP";
                
        datos = new ControlContratos();
            
        try 
        {
            //PRUEBA DE EJECUCION SIN CONTEO ES COLOCAR EL NUMERO 2
            lista = (ArrayList<ModeloContratos>) datos.getRegistrosINNER(ESTADO);
        }
        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA CONTRATOS POR ESTADO");
        }            
        
    }
    
    public ModeloContratosTabla(int IP, String ESTADO) throws ClassNotFoundException, SQLException, Exception
    {
        CONSULTA = 1;
        titulos = new String[5];
        titulos[0] ="#";
        titulos[1] ="CLIENTE";
        titulos[2] ="DIRECCION";
        titulos[3] ="EQUIPO";
        titulos[4] ="IP";
                
        datos = new ControlContratos();
        
        try 
        {
            //PRUEBA DE EJECUCION SIN CONTEO ES COLOCAR EL NUMERO 2
            lista = (ArrayList<ModeloContratos>) datos.getRegistrosIP(IP, ESTADO);
        }

        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA CONTRATOS POR IP");
        }       
        
    }
    
    public ModeloContratosTabla(String ESTADO, boolean AUX) throws ClassNotFoundException, SQLException, Exception
    {
        CONSULTA = 2;
        titulos = new String[5];
        titulos[0] ="CONT";
        titulos[1] ="FCOR";
        titulos[2] ="IP";
                
        datos = new ControlContratos();
        
        try 
        {
            //PRUEBA DE EJECUCION SIN CONTEO ES COLOCAR EL NUMERO 2
            lista = (ArrayList<ModeloContratos>) datos.getRegistrosAuto(ESTADO);
        }

        catch (Exception ex) 
        {
            System.out.println("ERROR EN LA CREACION DE LA TABLA CONTRATOS PARA MOVER");
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
                    case 0: return lista.get(rowIndex).getIDCONTRATO();
                    case 1: return lista.get(rowIndex).getNOMBRE();
                    case 2: return lista.get(rowIndex).getTEL1();
                    case 3: return lista.get(rowIndex).getCALLE()+" "+lista.get(rowIndex).getNUMERO()+" "+
                            lista.get(rowIndex).getCOLONIA()+" "+lista.get(rowIndex).getLOCALIDAD();
                    case 4: return lista.get(rowIndex).getCOSTO();
                    case 5: return lista.get(rowIndex).getDESCIPCION();
                    case 6: return lista.get(rowIndex).getFINS();
                    case 7: return lista.get(rowIndex).getFCOR();
                    case 8: return lista.get(rowIndex).getCOMENTARIOS();
                    case 9: return lista.get(rowIndex).getDIAS();
                    case 10: return lista.get(rowIndex).getIP();
                    default: return null;
                }
            case 1:
                switch(columnIndex)
                {           
                    case 0: return lista.get(rowIndex).getIDCONTRATO();
                    case 1: return lista.get(rowIndex).getNOMBRE();
                    case 2: return lista.get(rowIndex).getCALLE()+" "+lista.get(rowIndex).getNUMERO()+" "+
                            lista.get(rowIndex).getCOLONIA()+" "+lista.get(rowIndex).getLOCALIDAD()+" "+
                            lista.get(rowIndex).getLATITUD()+" "+lista.get(rowIndex).getLONGITUD();
                    case 3: return lista.get(rowIndex).getEQUIPO();
                    case 4: return lista.get(rowIndex).getIP();
                    default: return null;
                }
            case 2:
                switch(columnIndex)
                {           
                    case 0: return lista.get(rowIndex).getIDCONTRATO();
                    case 1: return lista.get(rowIndex).getFCOR();
                    case 2: return lista.get(rowIndex).getIP();
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
