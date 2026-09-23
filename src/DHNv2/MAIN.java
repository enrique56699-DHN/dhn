/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package DHNv2;

import VISTA.*;
import javax.swing.JFrame;

import org.jvnet.substance.SubstanceLookAndFeel;

/**
 *
 * @author Admin
 */
public class MAIN {

    static config confi = new config();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try
        {
           JFrame.setDefaultLookAndFeelDecorated(true);
           String skin=config.vector.get(0).toString();
           String tema=config.vector.get(2).toString();
           String marca=config.vector.get(4).toString();
           
            if(skin.equals("")==true)           
                JFrame.setDefaultLookAndFeelDecorated(true);           
            else
            {
                SubstanceLookAndFeel.setSkin(skin);
                SubstanceLookAndFeel.setCurrentTheme(tema);
                SubstanceLookAndFeel.setCurrentWatermark(marca);
            }
       }              
       catch(Exception e)
       {
           System.out.println("NO COLOREADO");
       }        
        
        if( new config().comprobar() )
        {                
            G g = new G();
            Splash p = new Splash();
            p.setVisible(true);
            
        }        
        else        
            System.exit(0);        
    }
    
}
