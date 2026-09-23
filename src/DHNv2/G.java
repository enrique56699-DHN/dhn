/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DHNv2;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

/**
 *
 * @author Admin
 */
public class G 
{
    //VARIABLES DE BASE DE DATOS
    public static String db = "dhn";
    //public static String db = config.vector.get(11).toString();
    public static String loginDB = config.vector.get(14).toString();
    public static String passwordDB = config.vector.get(15).toString();
    public static String ip = config.vector.get(12).toString(); 
    public static String puerto = config.vector.get(13).toString();
    public static String url = "jdbc:mysql://"+G.ip+":"+G.puerto +"/"+ G.db+"?useUnicode=true&characterEncoding=UTF-8";
    public static String driver = "com.mysql.jdbc.Driver";
    
    //VARIABLES DE ARCHIVO CONFIG
    public static int IDUSER;
    public static final int IDCAJA = Integer.parseInt(config.vector.get(16).toString());
    
    //VARIABLES ABSTRACTAS    
    public static final SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
    public static Locale locale = new Locale ("es", "MX");
    public static NumberFormat numero = NumberFormat.getInstance (locale);
    public static String rutaLocal = System.getProperties().getProperty("user.dir");
    public static String rutaServidor =  "\\\\" +ip+"\\dhn\\";
    public static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public static String home = System.getProperty("user.home");
    public static String separador = System.getProperty("file.separator");
    
    //TAREAS PROGRAMAS
    /*public static void hoy()
    {        
        Runnable tarea = () -> 
        {
            //hoy = new Date();
        };
        scheduler.scheduleAtFixedRate(tarea, 0, 1, TimeUnit.HOURS);
    }*/
    
    //PARA DETENER TAREA
    public static void detener()
    {
        scheduler.shutdown();
    }
    
    public static String FECHA(Date hoy, boolean hora)
    {
        String FECHA;        
        if(hora==true)
        {            
            //formateador.setTimeZone(TimeZone.getTimeZone("America/Mazatlan"));//horario -1 hora America/Mexico_City 0
            //formateador.setTimeZone(TimeZone.getTimeZone("America/Mazatlan"));
            formateador.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
            FECHA = formateador.format(hoy);
        }
        else
            FECHA = formato.format(hoy);
        
        return FECHA;
    }
    
    public static Date hoy()
    {
        // Creamos un calendario configurado explícitamente con la zona horaria de México
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"));
        return calendar.getTime();
        //Date hoy = new Date();
        //return hoy;
    }
    
    public static String FECHA_DOC()
    {
        String HOY;
        String []datos1 = G.FECHA(G.hoy(),false).split("/");
        String Dia=datos1[0];
        String Mes=datos1[1];
        String Ano=datos1[2];
        HOY=Dia+Mes+Ano;
        return HOY;
    }
    
    public static Date Fant(JDateChooser dte_fecha) 
    {
        final Date[] fecha = { dte_fecha.getDate() };  // contenedor mutable

        dte_fecha.addPropertyChangeListener("date", evt -> {
            fecha[0] = dte_fecha.getDate();
            System.out.println("Nueva fecha detectada: " + fecha[0]);
        });

        return fecha[0];  // devuelve la fecha inicial
    }
    
    public static void atributosCalendario(JDateChooser calendario)
    {
        calendario.getJCalendar().setWeekOfYearVisible(false);
        calendario.getJCalendar().setMaxDayCharacters(1);
        calendario.getJCalendar().setForeground(Color.BLACK);
        calendario.getJCalendar().setSundayForeground(Color.RED);
        calendario.getJCalendar().setWeekdayForeground(Color.BLUE);
        calendario.getDateEditor().setEnabled(false);        
    }
    
    public static String NTP()
    {
        //formateador.setTimeZone(TimeZone.getTimeZone("America/Mazatlan"));//horario -1 hora America/Mexico_City 0
        String NTP = formateador.format(hoy());
        
        try (NTPUDPClient client = new NTPUDPClient()) 
        {
            client.open();
            InetAddress hostAddr = InetAddress.getByName("time.windows.com");
            TimeInfo info = client.getTime(hostAddr);
            /*info.computeDetails(); // compute offset/delay if not already done
            Long offsetValue = info.getOffset();
            Long delayValue = info.getDelay();
            String delay = (delayValue == null) ? "N/A" : delayValue.toString();
            String offset = (offsetValue == null) ? "N/A" : offsetValue.toString();
            
            System.out.println(" Roundtrip delay(ms)=" + delay
            + ", clock offset(ms)=" + offset); // offset in ms
            System.out.println(" info "+info); // offset in ms*/
            long returnTime = info.getReturnTime();
            Date ntpDate = new Date(returnTime);
            //formateador.format(ntpDate);
            System.out.println(" info "+formateador.format(ntpDate)); // offset in ms
            return formateador.format(ntpDate);
        } catch (SocketException ex) {
            Logger.getLogger(G.class.getName()).log(Level.SEVERE, null, ex);
            /*formateador.setTimeZone(TimeZone.getTimeZone("America/Mazatlan"));//horario -1 hora America/Mexico_City 0
            return formateador.format(hoy());*/
        } catch (UnknownHostException ex) {
            Logger.getLogger(G.class.getName()).log(Level.SEVERE, null, ex);
            /*formateador.setTimeZone(TimeZone.getTimeZone("America/Mazatlan"));//horario -1 hora America/Mexico_City 0
            return formateador.format(hoy());*/
        } catch (IOException ex) {
            Logger.getLogger(G.class.getName()).log(Level.SEVERE, null, ex);
            /*formateador.setTimeZone(TimeZone.getTimeZone("America/Mazatlan"));//horario -1 hora America/Mexico_City 0
            return formateador.format(hoy());*/
        }       
        
        
        return NTP;
    }
}
