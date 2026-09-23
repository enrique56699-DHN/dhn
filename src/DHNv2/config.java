
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DHNv2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author KIKE FRENCH
 */
public class config 
{
    //NO PODEMOS USAR VARIABLES G. POR QUE ESTAS SE INICIAN DESPUES
    private String appPath = System.getProperties().getProperty("user.dir");
    private File fichero = new File( this.appPath + "\\DHN.tmp");    
    //tiempo en que se actualiza el fichero TMP
    private int segundos = 20;
    private String default_configuracion;    
    private String archivo_de_configuracion=this.appPath+"\\config.dat";//System.getProperty("user.dir") + "\\config.dat";
    private File archivo = null;
    private FileReader fr = null;
    private BufferedReader br = null;
    private FileWriter ficheroconfig = null;
    private PrintWriter pw = null;
    public static Vector vector = new Vector(17,2);
    
    
    public config() 
    {      
        this.default_configuracion = "/org.jvnet.substance.skin.BusinessBlackSteelSkin/Negocios Negro/org.jvnet.substance.theme.SubstanceSteelBlueTheme/Azul/org.jvnet.substance.watermark.SubstanceCopperplateEngravingWatermark/Metal/Espanol/Usuario/admin/pass/111/dhn/192.168.1.245/3306/terminal/norecuerdo/1/2.030";
        try 
        {
            Config();
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(config.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Config() throws FileNotFoundException, IOException
    {
        String config;     
        archivo = new File(archivo_de_configuracion);
        if(archivo.exists())
        {          
            config = Leer_configuracion();
        }
        else
        {
           Guardar_Configuracion(default_configuracion);                
           config = Leer_configuracion();
        } 
        token(config);
    }
    
    private String  Leer_configuracion() throws FileNotFoundException, IOException
    {
        String tmp_c="";
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
        String linea;
     
        while ((linea = br.readLine()) != null) 
        {
          tmp_c = tmp_c + linea;
        } 
     
        fr.close();
        return tmp_c;
    }
    
    private void token(String s)
    {    
        StringTokenizer tokens = new StringTokenizer(s,"/");     
        while(tokens.hasMoreTokens())
        {                
            vector.addElement(tokens.nextToken());            
        }     
    }
    
    public boolean Guardar_Configuracion(String t){   
    boolean ok = false;
        try 
        {
            ficheroconfig = new FileWriter(archivo_de_configuracion);
            pw = new PrintWriter(ficheroconfig);
            pw.println(t);
            ok = true;     
            ficheroconfig.close();
        } 
        catch (IOException ex) 
        {               
            Logger.getLogger(config.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
  } 
    
    
    /**
     * Comprueba que archivo TMP exista, sino lo crea e inicia valores
     * @return 
     */
    public boolean comprobar()
    {           
        if ( fichero.exists() )
        {           
            long tiempo = leer();//
            long res = restarTiempo( tiempo );           
            if( res < segundos )
            {              
                JOptionPane.showMessageDialog(null,"Error: La aplicacion ya esta en ejecución.");
                return false;
            }
            else
            {        
                programar_tarea();
                return true;
            }
        }
        else// no existe fichero
        {
            crearTMP();   
            programar_tarea();
            return true;
        }            
    }
    
    /**
     * Lee el archivo TMP y retorna su valor 
     * @return LONG cantidad de milisegundos 
     */
    public long leer()
    {
        String linea = "0";        
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader( new FileReader( fichero ) );            
            while(bufferedReader.ready()){
                linea = bufferedReader.readLine();            
        }
        }catch (IOException e) {
            System.err.println( e.getMessage() );
        }
        return Long.parseLong(linea);
    }
    
    /**
     * Programa un proceso que se repite cada cierto tiempo
     */
    public void programar_tarea()
    {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate( 
            new Runnable() 
            {
                @Override
                public void run() {                   
                    crearTMP(); 
                }
            }, 100, segundos * 100 , TimeUnit.MILLISECONDS ); //comienza dentro de 1 segundo y luego se repite cada N segundos            
    }
    
    /**
     * Crea un archivo TMP con un unico valor, el tiempo en milisegundos
     */
    public void crearTMP()
    {
        Date fecha=new Date();        
        try {            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter( fichero ))) {
                writer.write(  String.valueOf( fecha.getTime() ) );
            }
        } catch (IOException e) {
            System.err.println( e.getMessage() );
        }        
    }
 
    /**
     * Resta el tiempo expresado en milisegundos
     * @param tiempoActual el tiempo actual del sistema expresado en milisegundos
     * @return tiempo el resultado expresado en segundos
     */
    public long restarTiempo( long tiempoActual )
    {
        Date date =new Date();        
        long tiempoTMP = date.getTime();        
        long tiempo = tiempoTMP - tiempoActual;        
        tiempo = tiempo /100;        
        return tiempo;
    }
    
    /**
     * Elimina el fichero TMP si es que existe
     */
    public void cerrarApp()
    {   
        if ( fichero.exists() ) { fichero.delete(); }
        System.exit(0);
    }        
}
