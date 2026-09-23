/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.ControlPagos;
import DHNv2.G;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Oscar E Gonzalez G
 */
public class ModeloPagosTabla extends AbstractTableModel
{
    String[]titulos = new String[9];
    ArrayList<ModeloPagos> x = new ArrayList<>();
    ControlPagos pto;
    int CONSULTA;

    public ModeloPagosTabla() throws ClassNotFoundException, SQLException 
    {
        CONSULTA=4;
        titulos[0] ="NO. RECIBO";
        titulos[1] ="NOMBRE DE CLIENTE";
        titulos[2] ="CONTRATO";
        titulos[3] ="FECHA";
        titulos[4] ="METODO";
        titulos[5] ="MONTO";
        titulos[6] ="COMENTARIOS";
        titulos[7] ="DOCUMENTO";
        titulos[8] = "CAJA";
        
        pto = new ControlPagos();
        try 
        {
            x = (ArrayList<ModeloPagos>) pto.getRegistros();
        }
        
        catch (Exception ex) 
        {
            System.out.println("TABLA SIN FILTROS");
        }
        
    }
    
    public ModeloPagosTabla(String CP) throws ClassNotFoundException, SQLException {
        CONSULTA=4;
        titulos[0] ="NO. RECIBO";
        titulos[1] ="NOMBRE DE CLIENTE";
        titulos[2] ="CONTRATO";
        titulos[3] ="FECHA";
        titulos[4] ="METODO";
        titulos[5] ="MONTO";
        titulos[6] ="COMENTARIOS";
        titulos[7] ="DOCUMENTO";
        titulos[8] = "CAJA";
        
        pto = new ControlPagos();
        try 
        {            
            x = (ArrayList<ModeloPagos>) pto.getRegistros(CP);            
        }
        
        catch (Exception ex) 
        {
            System.out.println("TABLA SIN FILTROS, PERO CON CP");
        }
        
    }
    
    public ModeloPagosTabla(String REFERENCIA,int AUX) throws ClassNotFoundException, SQLException 
    {
        CONSULTA=5;
        titulos = new String[9];
        titulos[0] ="NO. RECIBO";
        titulos[1] ="NOMBRE DE CLIENTE";
        titulos[2] ="CONTRATO";
        titulos[3] ="FECHA";
        titulos[4] ="METODO";
        titulos[5] ="MONTO";
        titulos[6] ="COMENTARIOS";
        titulos[7] ="DOCUMENTO";
        titulos[8] = "CAJA";
        
        pto = new ControlPagos();
        try 
        {            
            x = (ArrayList<ModeloPagos>) pto.getRegistros(REFERENCIA,AUX);            
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERROR AL CREAR TABLA PARA VERIFICAR");
        }
        
    }
    
    public ModeloPagosTabla(int idcontrato) throws ClassNotFoundException, SQLException {
        //TOMA EL VALOR DE IDCOTNRATO PARA REALIZAR EL IF HASTA ABAJO DONDE SE LLENA LA TABLA
        CONSULTA=0;
        titulos = new String[7];
        titulos[0] ="NO. RECIBO";
        titulos[1] ="FECHA";
        titulos[2] ="METODO";
        titulos[3] ="MONTO";
        titulos[4] ="COMENTARIOS";
        titulos[5] ="DOCUMENTO";
        titulos[6] ="PROCESO";
        
        pto = new ControlPagos();
        
        try 
        {
            x = (ArrayList<ModeloPagos>) pto.getRegistrosIDCONT(idcontrato);
        }
        
        catch (Exception ex) 
        {
            System.out.println("TABLA CON NUMERO DE CONTRATO");
        }
        
    }

    public ModeloPagosTabla(String DE, String AL, String METODO) throws ClassNotFoundException, SQLException {
        CONSULTA=1;
        titulos = new String[5];
        
        titulos[0] ="NO. RECIBO";
        titulos[1] ="CONTRATO";
        titulos[2] ="NOMBRE DE CLIENTE";
        titulos[3] ="MONTO";
        titulos[4] = "METODO";
        
        pto = new ControlPagos();
        try 
        {
            x = (ArrayList<ModeloPagos>) pto.getRegistros(DE,AL,METODO);
        }
        
        catch (Exception ex) 
        {
            System.out.println("ERRORsssssss");
        }
    }
    
    public ModeloPagosTabla(String DE, String AL) throws ClassNotFoundException, SQLException {
        CONSULTA=2;
        titulos = new String[3];
        
        titulos[0] ="#";
        titulos[1] ="NOMBRE DE CLIENTE";
        titulos[2] ="MONTO";
        
        pto = new ControlPagos();
        try 
        {
            x = (ArrayList<ModeloPagos>) pto.getRegistros(DE,AL);
        }
        
        catch (Exception ex) 
        {
            //System.out.println("ERROR");
        }
    }
    
    public ModeloPagosTabla(int IDPAGOI, String METODO, int IDCAJA) throws ClassNotFoundException, SQLException 
    {
        CONSULTA=3;
        titulos = new String[3];
              
        pto = new ControlPagos();
        try 
        {
            x = (ArrayList<ModeloPagos>) pto.listaCorteD(IDPAGOI, METODO, IDCAJA);
        }
        
        catch (Exception ex) 
        {
            System.out.println("TABLA PAGOS PARA CORTE");
        }
    }
    
    public ModeloPagosTabla(int IDPAGOI,int IDPAGOF, String METODO, int IDCAJA) throws ClassNotFoundException, SQLException 
    {
        CONSULTA=3;
        titulos = new String[3];
      
        /*titulos[0] ="#";
        titulos[1] ="NOMBRE DE CLIENTE";
        titulos[2] ="MONTO";*/
        
        pto = new ControlPagos();
        try 
        {
            x = (ArrayList<ModeloPagos>) pto.listaCorteD(IDPAGOI, IDPAGOF, METODO, IDCAJA);
        }        
        catch (Exception ex) 
        {
            System.out.println("TABLA PAGOS PARA CORTE");
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
                    case 0: return x.get(rowIndex).getIDNOTA();
                    case 1: return x.get(rowIndex).getFECHA();
                    case 2: return x.get(rowIndex).getMETODO();
                    case 3: return x.get(rowIndex).getMONTO();
                    case 4: return x.get(rowIndex).getCOMENTARIOS();
                    case 5: return x.get(rowIndex).getDOC();
                    case 6: return x.get(rowIndex).getREFERENCIA();

                    default:
                        return null;
                }
            case 1:
                switch(columnIndex)
                {
                    //Si la clase no tiene ningun filtro se crea una tabla con 9 titulos y se toma todos los datos
                    case 0: return x.get(rowIndex).getIDNOTA();
                    case 1: return x.get(rowIndex).getIDCONTRATO();
                    case 2: return x.get(rowIndex).getNOMBRE();
                    case 3: return x.get(rowIndex).getMONTO();
                    case 4: return x.get(rowIndex).getMETODO();
                    
                    default: return null;
                }
            case 2:
                switch(columnIndex)
                    {
                        //Si la clase no tiene ningun filtro se crea una tabla con 9 titulos y se toma todos los datos
                        case 0: return x.get(rowIndex).getIDNOTA();
                        case 1: return x.get(rowIndex).getNOMBRE();
                        case 2: return x.get(rowIndex).getMONTO();

                        default: return null;
                    }
            case 3:
                switch(columnIndex)
                        {
                            //Si la clase no tiene ningun filtro se crea una tabla con 9 titulos y se toma todos los datos
                            case 0: return x.get(rowIndex).getIDNOTA();
                            case 1: return x.get(rowIndex).getNOMBRE();
                            case 2: return x.get(rowIndex).getMONTO();

                            default: return null;
                        }
            case 4:
                switch(columnIndex)
                {
                    //Si la clase no tiene ningun filtro se crea una tabla con 9 titulos y se toma todos los datos
                    case 0: return x.get(rowIndex).getIDNOTA();
                    case 1: return x.get(rowIndex).getNOMBRE();
                    case 2: return x.get(rowIndex).getIDCONTRATO();
                    case 3: return x.get(rowIndex).getFECHA();
                    case 4: return x.get(rowIndex).getMETODO();
                    //case 5: return "$ "+totalF.format(x.get(rowIndex).getMONTO())+".00";
                    case 5: return "$ "+G.numero.format(x.get(rowIndex).getMONTO())+".00";
                    case 6: return x.get(rowIndex).getCOMENTARIOS();
                    case 7: return x.get(rowIndex).getDOC();
                    case 8: return x.get(rowIndex).getIDCAJA();

                    default: return null;
                }
            case 5:
                switch(columnIndex)
                {
                    //Si la clase no tiene ningun filtro se crea una tabla con 9 titulos y se toma todos los datos
                    case 0: return x.get(rowIndex).getIDNOTA();
                    case 1: return x.get(rowIndex).getNOMBRE();
                    case 2: return x.get(rowIndex).getIDCONTRATO();
                    case 3: return x.get(rowIndex).getFECHA();
                    case 4: return x.get(rowIndex).getMETODO();
                    //case 5: return "$ "+totalF.format(x.get(rowIndex).getMONTO())+".00";
                    case 5: return "$ "+G.numero.format(x.get(rowIndex).getMONTO())+".00";
                    case 6: return x.get(rowIndex).getCOMENTARIOS();
                    case 7: return x.get(rowIndex).getDOC();
                    case 8: return x.get(rowIndex).getIDCAJA();

                    default: return null;
                }
            default:
                switch(columnIndex)
                {
                    //Si la clase no tiene ningun filtro se crea una tabla con 9 titulos y se toma todos los datos
                    case 0: return x.get(rowIndex).getIDNOTA();
                    case 1: return x.get(rowIndex).getNOMBRE();
                    case 2: return x.get(rowIndex).getIDCONTRATO();
                    case 3: return x.get(rowIndex).getFECHA();
                    case 4: return x.get(rowIndex).getMETODO();
                    //case 5: return "$ "+totalF.format(x.get(rowIndex).getMONTO())+".00";
                    case 5: return x.get(rowIndex).getMONTO();
                    case 6: return x.get(rowIndex).getCOMENTARIOS();
                    case 7: return x.get(rowIndex).getIDCAJA();

                    default: return null;
                }
                
        }
    
    }
}
