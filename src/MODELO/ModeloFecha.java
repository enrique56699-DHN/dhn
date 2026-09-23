/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author KIKE FRENCH
 */
public class ModeloFecha 
{
    private final SimpleDateFormat formato = new SimpleDateFormat("dd'/'MM'/'yyyy");
    private Date fechaString;
    
    public String getFechaFormateada(Date fecha)
    {
        return formato.format((Date)fecha);
    }
    
    public Date stringToDate(String text)
    {        
        try 
        {
            fechaString=(Date)formato.parseObject(text);
        } 
        catch (ParseException ex) 
        {
            
        }
        return fechaString;
    }
  
}
