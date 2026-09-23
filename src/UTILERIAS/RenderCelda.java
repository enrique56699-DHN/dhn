/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTILERIAS;

import DHNv2.G;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author win 10
 */
public class RenderCelda extends JTextArea implements TableCellRenderer
{
    private boolean tooltip;
    private boolean adjust;
    private String DEFINICION;
    
    public RenderCelda(boolean tooltip, boolean adjust,String DEFINICION)
    {
        this.tooltip=tooltip;
        this.adjust=adjust;
        this.DEFINICION=DEFINICION;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
    {
        if(value!=null)
            this.setText(value.toString());
        this.setFont(table.getFont());
        
        if(tooltip==true)
            this.setToolTipText((MultiLineTooltips.splitToolTip(this.getText())));
        if(adjust==true)
        {            
            this.setWrapStyleWord(true);
            this.setLineWrap(true);            
            this.setBorder(table.getBorder());
            this.setBackground(table.getBackground());            
        }
        
        switch(DEFINICION)
        {
            case "AGENDA":
                Object x = table.getValueAt(row, column);
                if(x != null)
                {
                    String a = x.toString();
                    if(a.equals(G.FECHA(G.hoy(),false)))
                    {
                        this.setBackground(Color.GREEN);
                        this.setForeground(Color.BLACK);
                    }
                    else
                        this.setBackground(new Color(0,0,0,0));
                }
                else
                    this.setBackground(new Color(0,0,0,0));
                break;
            case "ASISTENCIAS":
                switch(table.getValueAt(row, column).toString())
                {
                    case "FALTA":
                        this.setBackground(Color.PINK);
                        this.setForeground(Color.BLACK);                        
                        break;
                    case "RETARDO":
                        this.setBackground(Color.YELLOW);
                        this.setForeground(Color.BLACK);                        
                        break;    
                    default:
                        break;
                }
                break;        
            case "PENDIENTES":
                Integer a= (Integer) table.getValueAt(row, column);
                if(a>14)
                {
                    this.setBackground(Color.red);
                    this.setForeground(Color.WHITE);
                }
                else
                {
                    if(a>6)
                    {
                        this.setBackground(Color.YELLOW);
                        this.setForeground(Color.BLACK);
                    }
                    else
                    {
                        this.setBackground(new Color(0,0,0,0));
                        this.setForeground(Color.BLACK);
                    }
                }
                break;
            case "CARRITO":                
                switch(table.getValueAt(row, column).toString())
                {
                    case "USADO":
                        this.setBackground(Color.BLUE);
                        this.setForeground(Color.WHITE);
                        break;
                    case "ENTREGADO":
                        this.setBackground(Color.GREEN);
                        this.setForeground(Color.BLACK);
                        break;
                    case "DEVUELTO":
                        this.setBackground(Color.PINK);
                        this.setForeground(Color.WHITE);
                        break;
                    case "REQUERIDO":
                        this.setBackground(new Color(0,0,0,0));
                        this.setForeground(Color.BLACK);
                        break;
                    default: 
                        break;
                }
                break;
            case "CONTRATOS":
                if((Integer) table.getValueAt(row, column)<0)
                {
                    this.setBackground(Color.RED);
                    this.setForeground(Color.WHITE);

                }
                else
                {
                    this.setBackground(new Color(0,0,0,0));
                    //this.setBackground(new Color(0,0,0,5));
                    this.setForeground(Color.BLACK);
                }
                break;
            case "PAQUETES":      
                if(table.getValueAt(row, column).toString().contains("PREPAGO"))
                {
                    this.setBackground(Color.MAGENTA);
                    this.setForeground(Color.WHITE);
                }
                else
                    if(table.getValueAt(row, column).toString().contains("DOMICILIADO"))
                    {
                        this.setBackground(Color.CYAN);
                        this.setForeground(Color.BLACK);
                    }
                    else
                    {
                        this.setBackground(new Color(0,0,0,0));
                        this.setForeground(Color.BLACK);
                    }
                break;     
            case "CONCILIACION":            
                switch(table.getValueAt(row, column).toString())
                {                    
                    case "No":
                        this.setBackground(Color.MAGENTA);
                        this.setForeground(Color.WHITE);
                        break;
                    case "Si":
                        this.setBackground(new Color(0,0,0,0));
                        this.setForeground(Color.BLACK);
                        break;
                    default: 
                        break;
                }
                break;
            case "COTIZACIONESSER":
                switch(table.getValueAt(row, column).toString())
                {
                    case "ENVIADA":
                        this.setBackground(Color.BLUE);
                        this.setForeground(Color.BLACK);
                        break;
                    case "APROBADA":
                        this.setBackground(Color.GREEN);
                        this.setForeground(Color.BLACK);
                        break;
                    case "RECHAZADA":
                        this.setBackground(Color.PINK);
                        this.setForeground(Color.WHITE);
                        break;
                    case "FINALIZADA":
                        this.setBackground(Color.ORANGE);
                        this.setForeground(Color.BLACK);
                        break;
                    default: 
                        break;
                }
                break;
            case "KITSR":
                if(table.getValueAt(row, column)!=null)
                {
                    switch(table.getValueAt(row, column).toString())
                    {
                        case "":
                            this.setBackground(Color.YELLOW);
                            this.setForeground(Color.WHITE);                        
                            break;
                        case "INACTIVO":
                            this.setBackground(Color.RED);
                            this.setForeground(Color.WHITE);                        
                            break;    
                        case "ACTIVO":
                            this.setBackground(Color.BLUE);
                            this.setForeground(Color.WHITE);                        
                            break;    
                        default:    
                            this.setBackground(new Color(0,0,0,0));
                            this.setForeground(Color.BLACK);
                            break;
                    }
                }                
                break;    
            case "METODOS":
                switch(table.getValueAt(row, column).toString())
                {
                    case "TRANSFERENCIA":
                        this.setBackground(Color.PINK);
                        this.setForeground(Color.BLACK);                        
                        break;
                    case "TARJETA":
                        this.setBackground(Color.YELLOW);
                        this.setForeground(Color.BLACK);
                        break;
                    case "OXXO":
                        this.setBackground(Color.ORANGE);
                        this.setForeground(Color.BLACK);
                        break;
                    case "EFECTIVO":
                        this.setBackground(Color.GREEN);
                        this.setForeground(Color.BLACK);
                        break;    
                    case "DEPOSITO":
                        this.setBackground(Color.GREEN);
                        this.setForeground(Color.BLACK);
                        break;
                    case "PRESTAMO":
                        this.setBackground(Color.BLUE);
                        this.setForeground(Color.WHITE);
                        break;    
                    case "TRASPASO ENTRE CUENTAS PROPIAS":
                        this.setBackground(Color.ORANGE);
                        this.setForeground(Color.BLACK);
                        break;             
                    default:    
                        this.setBackground(new Color(0,0,0,0));
                        this.setForeground(Color.BLACK);
                        break;
                }    
                break; 
            case "REGPAGOS":
                switch(table.getValueAt(row, column).toString())
                {
                    case "NO VERIFICADO":
                        this.setBackground(Color.BLACK);
                        this.setForeground(Color.WHITE);                        
                        break;
                    case "VERIFICADO SIN ENVIAR":
                        this.setBackground(Color.GREEN);
                        this.setForeground(Color.BLACK);                        
                        break;    
                    case "RECHAZADO":
                        this.setBackground(Color.RED);
                        this.setForeground(Color.WHITE);                        
                        break;    
                    default:    
                        this.setBackground(new Color(0,0,0,0));
                        this.setForeground(Color.BLACK);
                        break;
                }
                break;    
            case "PAGO DOMICILIADO":
                switch(table.getValueAt(row, column).toString())
                {
                    case "PAGO DOMICILIADO":
                        this.setBackground(Color.CYAN);
                        this.setForeground(Color.BLACK);            
                        break;
                    default:    
                        this.setBackground(new Color(0,0,0,0));
                        this.setForeground(Color.BLACK);
                        break;
                }
                break;        
            case "VALES":
                switch(table.getValueAt(row, column).toString())
                {
                    case "CARGO":
                        this.setBackground(Color.PINK);
                        this.setForeground(Color.BLACK);                        
                        break;
                    case "ABONO":
                        this.setBackground(Color.GREEN);
                        this.setForeground(Color.BLACK);                        
                        break;    
                    default:
                        break;
                }
                break;            
            default:
                break;
        }
        return this;        
    }
    
}
