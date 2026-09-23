/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import java.util.StringTokenizer;

/**
 *
 * @author enrique.orozco
 */
public class CorregirRuta {
 
    private final String ruta;
    private final String separador; 
    private final String nuevoSeparador;
 
    public CorregirRuta(String laruta, String sep, String nuevoSep)
    {
        ruta=laruta;
        separador=sep;
        nuevoSeparador=nuevoSep;
    }
 
    public String obtenerRutaCorregidaWindows()
    {
         StringTokenizer tokens=new StringTokenizer(ruta, separador);
         //Para guardar la ruta corregida
         String rutaCorregida = new String();
         //Contar los tokens (en este caso las carpetas, contado tambien
         // el nombre del archivo seleccionado).
         int noTokens = tokens.countTokens();
         int i = 1;
         do
         {      //Agregar el nuevo separador
            rutaCorregida += tokens.nextToken()+nuevoSeparador;
            i++;
         }while(i<noTokens);
         //Agregar a la ruta corregida el ultimo token, (nombre del archivo)
         rutaCorregida += tokens.nextToken();       
         //Mostrar la ruta corregida en la consola
         return rutaCorregida;
    }
    
}
