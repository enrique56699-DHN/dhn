/*

 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;

import CONTROLADOR.ControlPendientes;
import CONTROLADOR.F;

import DHNv2.G;
import MODELO.ModeloPendientesTabla;
import java.awt.Component;
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author KIKE FRENCH
 */
public final class VAgendaL extends javax.swing.JInternalFrame {
    
    /** Creates new form VRepPagos
     * @throws java.lang.Exception
        */
    private int IDPENDIENTE;
    private final String ESTADO="ABIERTO";

    private String f1,f2,f3,f4,f5,f8,f9,f10,f11,f12,f02,f03,f04,f05,f09,f010,f011,f012;
     
    //private int cont=0;
    private JTable table;
    private JScrollPane jsp;
    private int index;
    
    
    public VAgendaL() throws Exception{
       
    }
    
    public VAgendaL(String titulo) throws Exception{       
        initComponents();
        this.setTitle(titulo);
        index = 4;
        actualizarTabla(G.FECHA(G.hoy(),false), ESTADO);
        this.Bar.setFloatable(false);   
        //----------SELEC ITEM----------
        //this.tpnlSERVICIOS.setSelectedIndex(4);
        table = tblF8;      
        F.teclas(this);
        F.noENTER(table);
    }
        
    private void agenda() throws ClassNotFoundException, SQLException, Exception
    {
        JInternalFrame ji = validador.getJInternalFrame(VPendientesL.class.getName());
        if(ji != null )
            ji.dispose();        
        try {               
            ji = new VPendientesL("Agenda Global","ABIERTO");
            Principal.getCONT().add(ji);
            validador.addJIframe(VPendientesL.class.getName(), ji);
        } catch (ParseException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ji.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
        ji.setVisible(true);
        ji.moveToFront();
        this.setClosed(true);
        this.dispose();
                
    }
    
    private void fechas(String FLIMITE) throws ParseException 
    {
        Date fechaBase = obtenerFechaBase(FLIMITE);

        // Día actual
        this.f1 = FLIMITE;
        this.f8 = formatearFechaConDia(fechaBase);

        // Días siguientes (+1, +2, +3, +4)
        this.f2 = obtenerFechaOffset(fechaBase, 1);
        this.f9 = obtenerFechaConDia(fechaBase, 1);

        this.f3 = obtenerFechaOffset(fechaBase, 2);
        this.f10 = obtenerFechaConDia(fechaBase, 2);

        this.f4 = obtenerFechaOffset(fechaBase, 3);
        this.f11 = obtenerFechaConDia(fechaBase, 3);

        this.f5 = obtenerFechaOffset(fechaBase, 4);
        this.f12 = obtenerFechaConDia(fechaBase, 4);

        // Días anteriores (-1, -2, -3, -4)
        this.f02 = obtenerFechaOffset(fechaBase, -1);
        this.f09 = obtenerFechaConDia(fechaBase, -1);

        this.f03 = obtenerFechaOffset(fechaBase, -2);
        this.f010 = obtenerFechaConDia(fechaBase, -2);

        this.f04 = obtenerFechaOffset(fechaBase, -3);
        this.f011 = obtenerFechaConDia(fechaBase, -3);

        this.f05 = obtenerFechaOffset(fechaBase, -4);
        this.f012 = obtenerFechaConDia(fechaBase, -4);

    }

    // Métodos auxiliares
    private Date obtenerFechaBase(String FLIMITE) throws ParseException {
        if (!FLIMITE.isEmpty()) {
            return G.formato.parse(FLIMITE);
        }
        return G.hoy();
    }

    private String obtenerFechaOffset(Date fechaBase, int offsetDias) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(fechaBase);
        c.add(Calendar.DATE, offsetDias);
        return G.formato.format(c.getTime());
    }

    private String obtenerFechaConDia(Date fechaBase, int offsetDias) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(fechaBase);
        c.add(Calendar.DATE, offsetDias);

        String dayOfWeek = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)
                           .toUpperCase();
        return F.nomdias(dayOfWeek);
    }

    private String formatearFechaConDia(Date fecha) {
        String dia = obtenerNombreDia(fecha);
        return dia; // Asumiendo que f8 es el nombre del día, no la fecha
    }

    private String obtenerNombreDia(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        String dayOfWeek = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)
                           .toUpperCase();
        return F.nomdias(dayOfWeek);
    }
    
    /*private void fechas(String FLIMITE) throws ParseException
    {
        Date d;
        Calendar c = Calendar.getInstance();
        String dayOfWeek;
        
        this.f1=FLIMITE;
        //-----------bloque de aumento--------
        //d=Global.formato.parse(f1); 
        if(!f1.equals(""))
        {
            d=G.formato.parse(f1); 
        }
        else
            d=G.hoy();
        
        c.setTime(d);
        c.add(Calendar.DATE,+0);
        d = c.getTime();
        f1=""+G.formato.format(d);
        c.setTime(d);
        dayOfWeek = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();
        f8=f1;
        f8 = nomdias(dayOfWeek);
        
        d=G.formato.parse(f1);       
        c.setTime(d);
        c.add(Calendar.DATE, +1);
        d = c.getTime();
        f2=""+G.formato.format(d);        
        c.setTime(d);
        dayOfWeek = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();        
        f9=f2;
        f9 = nomdias(dayOfWeek);
        
        d=G.formato.parse(f2);
        c.setTime(d);
        c.add(Calendar.DATE, +1);
        d = c.getTime();
        f3=""+G.formato.format(d);
        c.setTime(d);
        dayOfWeek = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();
        f10=f3;
        f10 = nomdias(dayOfWeek);
        
        d=G.formato.parse(f3);
        c.setTime(d);
        c.add(Calendar.DATE, +1);
        d = c.getTime();
        f4=""+G.formato.format(d);
        c.setTime(d);
        dayOfWeek = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();        
        f11=f4;
        f11 = nomdias(dayOfWeek);
        
        d=G.formato.parse(f4);
        c.setTime(d);
        c.add(Calendar.DATE, +1);
        d = c.getTime();
        f5=""+G.formato.format(d);
        c.setTime(d);
        dayOfWeek = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();        
        f12=f5;
        f12 = nomdias(dayOfWeek);
        
        //-----------bloque de decremento--------
                
        d=G.formato.parse(f1);       
        c.setTime(d);
        c.add(Calendar.DATE, -1);
        d = c.getTime();
        f02=""+G.formato.format(d);        
        c.setTime(d);
        dayOfWeek = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();        
        f09=f02;
        f09 = nomdias(dayOfWeek);
        
        d=G.formato.parse(f02);
        c.setTime(d);
        c.add(Calendar.DATE, -1);
        d = c.getTime();
        f03=""+G.formato.format(d);
        c.setTime(d);
        dayOfWeek = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();
        f010=f03;
        f010 = nomdias(dayOfWeek);
        
        d=G.formato.parse(f03);
        c.setTime(d);
        c.add(Calendar.DATE, -1);
        d = c.getTime();
        f04=""+G.formato.format(d);
        c.setTime(d);
        dayOfWeek = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();        
        f011=f04;
        f011 = nomdias(dayOfWeek);
        
        d=G.formato.parse(f04);
        c.setTime(d);
        c.add(Calendar.DATE, -1);
        d = c.getTime();
        f05=""+G.formato.format(d);
        c.setTime(d);
        dayOfWeek = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();        
        f012=f05;
        f012 = nomdias(dayOfWeek);
        
    }*/
    
    /*private String nomdias(String dayOfWeek)
    {
        String dia = null;
        switch(dayOfWeek)
        {
            case "MONDAY":
                dia = "LUNES";
                break;
            case "TUESDAY":
                dia = "MARTES";
                break;
            case "WEDNESDAY":
                dia = "MIERCOLES";
                break;
            case "THURSDAY":
                dia = "JUEVES";
                break;
            case "FRIDAY":
                dia = "VIERNES";
                break;
            case "SATURDAY":
                dia = "SABADO";
                break;
            case "SUNDAY":
                dia = "DOMINGO";
                break;
            default: break;
        }
        return dia;
    }*/
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void actualizarTabla(String FLIMITE, String ESTADO) throws ClassNotFoundException, SQLException, Exception
    {        
        fechas(FLIMITE);        
        ModeloPendientesTabla mpt;
        
        //---------DIAS ANTERIORES----------
        
        jspF012.setBorder(javax.swing.BorderFactory.createTitledBorder(null, f05, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        tpnlSERVICIOS.add(f012,jspF012);
        mpt = new ModeloPendientesTabla(f05, ESTADO,0,0);        
        tblF012.setModel(mpt);        
        F.dimensionar(tblF012,"AGENDA");
        
        jspF011.setBorder(javax.swing.BorderFactory.createTitledBorder(null, f04, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        tpnlSERVICIOS.add(f011,jspF011);
        mpt = new ModeloPendientesTabla(f04, ESTADO,0,0);        
        tblF011.setModel(mpt);        
        F.dimensionar(tblF011,"AGENDA");
        
        jspF010.setBorder(javax.swing.BorderFactory.createTitledBorder(null, f03, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        tpnlSERVICIOS.add(f010,jspF010);
        mpt = new ModeloPendientesTabla(f03, ESTADO,0,0);        
        tblF010.setModel(mpt);        
        F.dimensionar(tblF010,"AGENDA");
        
        jspF09.setBorder(javax.swing.BorderFactory.createTitledBorder(null, f02, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        tpnlSERVICIOS.add(f09,jspF09);
        mpt = new ModeloPendientesTabla(f02, ESTADO,0,0);        
        tblF09.setModel(mpt);        
        F.dimensionar(tblF09,"AGENDA");
        
        //---------DIA ACTUAL----------
        
        jspF8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, f1, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        tpnlSERVICIOS.add(f8,jspF8);
        mpt = new ModeloPendientesTabla(f1, ESTADO,0,0);        
        tblF8.setModel(mpt);        
        F.dimensionar(tblF8,"AGENDA");
        
        //---------DIAS SIGUIENTES----------
        
        jspF9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, f2, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        tpnlSERVICIOS.add(f9,jspF9);
        mpt = new ModeloPendientesTabla(f2, ESTADO,0,0);        
        tblF9.setModel(mpt);        
        F.dimensionar(tblF9,"AGENDA");
        
        jspF10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, f3, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        tpnlSERVICIOS.add(f10,jspF10);
        mpt = new ModeloPendientesTabla(f3, ESTADO,0,0);        
        tblF10.setModel(mpt);        
        F.dimensionar(tblF10,"AGENDA");
        
        jspF11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, f4, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        tpnlSERVICIOS.add(f11,jspF11);
        mpt = new ModeloPendientesTabla(f4, ESTADO,0,0);        
        tblF11.setModel(mpt);        
        F.dimensionar(tblF11,"AGENDA");
        
        jspF12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, f5, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        tpnlSERVICIOS.add(f12,jspF12);
        mpt = new ModeloPendientesTabla(f5, ESTADO,0,0);        
        tblF12.setModel(mpt);        
        F.dimensionar(tblF12,"AGENDA");       
       
        //----------SELEC ITEM----------
        this.tpnlSERVICIOS.setSelectedIndex(index);
    }
    //------------------------------------------------------------------------------
    
    
    /*private void actualizarTabla(String FLIMITE, String ESTADO) throws ClassNotFoundException, SQLException, Exception 
    {
    
        fechas(FLIMITE);

        // Configuración centralizada
        actualizarPanelConTabla(jspF012, f05, tblF012, ESTADO);
        actualizarPanelConTabla(jspF011, f04, tblF011, ESTADO);
        actualizarPanelConTabla(jspF010, f03, tblF010, ESTADO);
        actualizarPanelConTabla(jspF09, f02, tblF09, ESTADO);

        // Día actual
        actualizarPanelConTabla(jspF8, f1, tblF8, ESTADO);

        // Días siguientes
        actualizarPanelConTabla(jspF9, f2, tblF9, ESTADO);
        actualizarPanelConTabla(jspF10, f3, tblF10, ESTADO);
        actualizarPanelConTabla(jspF11, f4, tblF11, ESTADO);
        actualizarPanelConTabla(jspF12, f5, tblF12, ESTADO);

        // Restaurar selección
        tpnlSERVICIOS.setSelectedIndex(index);
    }

    
    private void actualizarPanelConTabla(JScrollPane scrollPane, String titulo, 
                                          JTable tabla, String estado) throws ClassNotFoundException, SQLException {
        scrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(
            null, 
            titulo, 
            TitledBorder.DEFAULT_JUSTIFICATION, 
            TitledBorder.DEFAULT_POSITION, 
            new  java.awt.Font("Tahoma", 1, 12)
        ));

        tpnlSERVICIOS.add(scrollPane, scrollPane.getName() != null ? scrollPane.getName() : "");

        ModeloPendientesTabla modelo = new ModeloPendientesTabla(titulo, estado, 0, 0);
        tabla.setModel(modelo);
        F.dimensionar(tabla, "AGENDA");
    }
    
    /*private void navegar(String SIGNO) throws ParseException, SQLException, Exception
    {
        Date d;
        Calendar c = Calendar.getInstance();        
        if("+".equals(SIGNO))
        {
            d = G.formato.parse(this.f1);       
            c.setTime(d);
            c.add(Calendar.DATE, +7);
            d = c.getTime();
            f1=""+G.formato.format(d);
        }
        else
            if("-".equals(SIGNO))
            {
                d = G.formato.parse(this.f1);          
                c.setTime(d);
                c.add(Calendar.DATE, -7);
                d = c.getTime();
                f1=""+G.formato.format(d);
            }
        actualizarTabla(this.f1,"ABIERTO");
        
        //----------SELEC ITEM----------
        //this.tpnlSERVICIOS.setSelectedIndex(4);
    }*/
    
    private void navegar(String SIGNO) throws ParseException, SQLException, Exception 
    {
        int offset = "+".equals(SIGNO) ? 7 : "-".equals(SIGNO) ? -7 : 0;

        if (offset != 0) {
            Date fechaActual = G.formato.parse(this.f1);
            Calendar c = Calendar.getInstance();
            c.setTime(fechaActual);
            c.add(Calendar.DATE, offset);
            this.f1 = G.formato.format(c.getTime());
            actualizarTabla(this.f1, "ABIERTO");
        }
    }
    
    private void seleccion(JTable tabla) throws ClassNotFoundException, SQLException, Exception
    {
        int fila = tabla.getSelectedRow();
        IDPENDIENTE = (int) tabla.getValueAt(fila, 0);
    }
    
    private void abrir(int ID, int IDORDEN) throws ParseException, Exception
    {
        RegOrdenesPD reg;            
        reg = new RegOrdenesPD(null,true);
        reg.mostrar(ID,IDORDEN);
        reg.setVisible(true);            
    }
    
    private void pestana()
    {
        index = this.tpnlSERVICIOS.getSelectedIndex();

        // --- Obtener los nombres ---
        // 2. Obtener el componente en la pestaña 0
        Component comp = tpnlSERVICIOS.getComponentAt(index);
        
        if (comp instanceof JScrollPane) {
            //JScrollPane sp = (JScrollPane) comp;
            jsp = (JScrollPane) comp;
            // 3. Obtener nombre del JScrollPane
            //System.out.println("JScrollPane Name: " + jsp.getName());
            
            // 4. Obtener el JTable dentro del scroll
            //JTable tbl = (JTable) sp.getViewport().getView();
            table = (JTable) jsp.getViewport().getView();
            //System.out.println("JTable Name: " + table.getName());
        }
        /*switch(index)
        {
            case 0:
                table = this.tblF012;
                jsp = this.jspF012;
                break;
            case 1:
                table = this.tblF011;
                jsp = this.jspF011;
                break;
            case 2:
                table = this.tblF10;
                jsp = this.jspF010;
                break;
            case 3:
                table = this.tblF09;
                jsp = this.jspF09;
                break;
            case 4:
                table = this.tblF8;
                jsp = this.jspF8;
                break;
            case 5:
                table = this.tblF9;
                jsp = this.jspF9;                
                break;
            case 6:
                table = this.tblF10;
                jsp = this.jspF10;
                break;
            case 7:
                table = this.tblF11;
                jsp = this.jspF11;
                break;
            case 8:
                table = this.tblF12;
                jsp = this.jspF12;
                break;
            default:
                break;
        }
        */
    }
    
    private void pregunta() throws SQLException, Exception
    {
        Object[] inputFields = {"¿REAGENDAR SERVICIO?"};
        int option = JOptionPane.showConfirmDialog(this, inputFields, "Reagendar servicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (option == JOptionPane.OK_OPTION)        
            reagendar();        
    }
    
    private void reagendar() throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        Border borde = jsp.getBorder();
        TitledBorder titledBorder = (TitledBorder) borde;        
        String FEST = titledBorder.getTitle();
                                
        if(FEST.equals(""))        
            FEST=G.FECHA(G.hoy(),false);
       
        Date DFEST=G.formato.parse(FEST);             
        
        String dayOfWeek;
        String dia;
         
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DFEST);
        //calendar.add(Calendar.DAY_OF_WEEK,i);
        dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();
        //dia = nomdias(dayOfWeek);
        
        switch(dayOfWeek)
        {
            case "MONDAY":
                dia = "LUNES";
                calendar.add(Calendar.DAY_OF_WEEK,1);
                break;
            case "TUESDAY":
                dia = "MARTES";
                calendar.add(Calendar.DAY_OF_WEEK,1);
                break;
            case "WEDNESDAY":
                dia = "MIERCOLES";
                calendar.add(Calendar.DAY_OF_WEEK,1);
                break;
            case "THURSDAY":
                dia = "JUEVES";
                calendar.add(Calendar.DAY_OF_WEEK,1);
                break;
            case "FRIDAY":
                dia = "VIERNES";
                calendar.add(Calendar.DAY_OF_WEEK,3);
                break;
            case "SATURDAY":
                dia = "SABADO";
                calendar.add(Calendar.DAY_OF_WEEK,2);
                break;
            case "SUNDAY":
                dia = "DOMINGO";
                calendar.add(Calendar.DAY_OF_WEEK,1);
                break;
            default:
                break;
        }
                        
        DFEST=calendar.getTime();
        FEST=""+G.formato.format(DFEST);
        //this.lblFEST.setText(FEST);
        
        ControlPendientes ctrlP = new ControlPendientes();        
        ctrlP.actualizarFEST(FEST, IDPENDIENTE);
        
        /*Border borde = jsp.getBorder();
        String fest = (borde instanceof TitledBorder) ? ((TitledBorder) borde).getTitle() : "";

        if (fest == null || fest.trim().isEmpty()) {
            fest = G.FECHA(G.hoy(), false);
        }

        Date dfest = G.formato.parse(fest);

        // Convertir Date a LocalDate
        LocalDate fecha = dfest.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Avanzar al siguiente día hábil
        switch (fecha.getDayOfWeek()) {
            case FRIDAY:
                fecha = fecha.plusDays(3);
                break;
            case SATURDAY:
                fecha = fecha.plusDays(2);
                break;
            default:
                fecha = fecha.plusDays(1);
                break;
        }

        // Convertir de vuelta a Date para formatear con G.formato
        Date nuevaFecha = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
        fest = G.formato.format(nuevaFecha);

        new ControlPendientes().actualizarFEST(fest, IDPENDIENTE);*/

        
    }
    
    private void agendar(String ACCION) throws ParseException, SQLException, Exception
    {
        Agendar reg;
        reg = new Agendar(null, true);
        reg.mostrar(IDPENDIENTE, 0, ACCION);
        reg.setVisible(true);
    }
        
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmpACCIONES = new javax.swing.JPopupMenu();
        jmmAGENDARSIGUIENTE = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmmAGENDAR = new javax.swing.JMenuItem();
        Bar = new javax.swing.JToolBar();
        bntATRAS = new javax.swing.JButton();
        btnACTUALIZAR = new javax.swing.JButton();
        btnDELANTE = new javax.swing.JButton();
        btnCAMBIOH = new javax.swing.JButton();
        tpnlSERVICIOS = new javax.swing.JTabbedPane();
        jspF012 = new javax.swing.JScrollPane();
        tblF012 = new javax.swing.JTable();
        jspF011 = new javax.swing.JScrollPane();
        tblF011 = new javax.swing.JTable();
        jspF010 = new javax.swing.JScrollPane();
        tblF010 = new javax.swing.JTable();
        jspF09 = new javax.swing.JScrollPane();
        tblF09 = new javax.swing.JTable();
        jspF8 = new javax.swing.JScrollPane();
        tblF8 = new javax.swing.JTable();
        jspF9 = new javax.swing.JScrollPane();
        tblF9 = new javax.swing.JTable();
        jspF10 = new javax.swing.JScrollPane();
        tblF10 = new javax.swing.JTable();
        jspF11 = new javax.swing.JScrollPane();
        tblF11 = new javax.swing.JTable();
        jspF12 = new javax.swing.JScrollPane();
        tblF12 = new javax.swing.JTable();
        txtBUSCAR = new javax.swing.JTextField();

        jmmAGENDARSIGUIENTE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmAGENDARSIGUIENTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/next_green.png"))); // NOI18N
        jmmAGENDARSIGUIENTE.setText("Reagendar siguiente dia");
        jmmAGENDARSIGUIENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmAGENDARSIGUIENTEActionPerformed(evt);
            }
        });
        jmpACCIONES.add(jmmAGENDARSIGUIENTE);
        jmpACCIONES.add(jSeparator1);

        jmmAGENDAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmAGENDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reloj.png"))); // NOI18N
        jmmAGENDAR.setText("Agendar fecha especifica");
        jmmAGENDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmAGENDARActionPerformed(evt);
            }
        });
        jmpACCIONES.add(jmmAGENDAR);

        setClosable(true);
        setMaximizable(true);
        setTitle("Vista de Servicios estimados");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agenda.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        Bar.setRollover(true);

        bntATRAS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntATRAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        bntATRAS.setText("ANTERIOR");
        bntATRAS.setToolTipText("Dia anterior");
        bntATRAS.setFocusable(false);
        bntATRAS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntATRAS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntATRAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntATRASActionPerformed(evt);
            }
        });
        Bar.add(bntATRAS);

        btnACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/refresh.png"))); // NOI18N
        btnACTUALIZAR.setText("ACTUALIZAR");
        btnACTUALIZAR.setToolTipText("ACTUALIZAR");
        btnACTUALIZAR.setFocusable(false);
        btnACTUALIZAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnACTUALIZAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnACTUALIZARActionPerformed(evt);
            }
        });
        Bar.add(btnACTUALIZAR);

        btnDELANTE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDELANTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/next.png"))); // NOI18N
        btnDELANTE.setText("SIGUIENTE");
        btnDELANTE.setToolTipText("Dia siguiente");
        btnDELANTE.setFocusable(false);
        btnDELANTE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDELANTE.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDELANTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDELANTEActionPerformed(evt);
            }
        });
        Bar.add(btnDELANTE);

        btnCAMBIOH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCAMBIOH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cambioh.png"))); // NOI18N
        btnCAMBIOH.setText("AGENDA");
        btnCAMBIOH.setToolTipText("Cambir vista");
        btnCAMBIOH.setFocusable(false);
        btnCAMBIOH.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCAMBIOH.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCAMBIOH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCAMBIOHActionPerformed(evt);
            }
        });
        Bar.add(btnCAMBIOH);

        tpnlSERVICIOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Servicios Estimados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        tpnlSERVICIOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tpnlSERVICIOSMouseClicked(evt);
            }
        });

        jspF012.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "F5", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jspF012.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspF012.setMaximumSize(new java.awt.Dimension(185, 185));
        jspF012.setMinimumSize(new java.awt.Dimension(185, 185));
        jspF012.setPreferredSize(new java.awt.Dimension(185, 185));

        tblF012.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblF012.setComponentPopupMenu(jmpACCIONES);
        tblF012.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblF012.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblF012MousePressed(evt);
            }
        });
        tblF012.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblF012KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblF012KeyReleased(evt);
            }
        });
        jspF012.setViewportView(tblF012);

        tpnlSERVICIOS.addTab("tab5", jspF012);

        jspF011.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "F4", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jspF011.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspF011.setMaximumSize(new java.awt.Dimension(185, 185));
        jspF011.setMinimumSize(new java.awt.Dimension(185, 185));
        jspF011.setPreferredSize(new java.awt.Dimension(185, 185));

        tblF011.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblF011.setComponentPopupMenu(jmpACCIONES);
        tblF011.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblF011.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblF011MousePressed(evt);
            }
        });
        tblF011.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblF011KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblF011KeyReleased(evt);
            }
        });
        jspF011.setViewportView(tblF011);

        tpnlSERVICIOS.addTab("tab4", jspF011);

        jspF010.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "F3", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jspF010.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspF010.setMaximumSize(new java.awt.Dimension(185, 185));
        jspF010.setMinimumSize(new java.awt.Dimension(185, 185));
        jspF010.setPreferredSize(new java.awt.Dimension(185, 185));

        tblF010.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblF010.setComponentPopupMenu(jmpACCIONES);
        tblF010.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblF010.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblF010MousePressed(evt);
            }
        });
        tblF010.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblF010KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblF010KeyReleased(evt);
            }
        });
        jspF010.setViewportView(tblF010);

        tpnlSERVICIOS.addTab("tab3", jspF010);

        jspF09.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "F2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jspF09.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspF09.setMaximumSize(new java.awt.Dimension(185, 185));
        jspF09.setMinimumSize(new java.awt.Dimension(185, 185));
        jspF09.setPreferredSize(new java.awt.Dimension(185, 185));

        tblF09.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblF09.setComponentPopupMenu(jmpACCIONES);
        tblF09.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblF09.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblF09MousePressed(evt);
            }
        });
        tblF09.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblF09KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblF09KeyReleased(evt);
            }
        });
        jspF09.setViewportView(tblF09);

        tpnlSERVICIOS.addTab("tab2", jspF09);

        jspF8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "F1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jspF8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspF8.setMaximumSize(new java.awt.Dimension(185, 185));
        jspF8.setMinimumSize(new java.awt.Dimension(185, 185));
        jspF8.setPreferredSize(new java.awt.Dimension(185, 185));

        tblF8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblF8.setComponentPopupMenu(jmpACCIONES);
        tblF8.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblF8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblF8MousePressed(evt);
            }
        });
        tblF8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblF8KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblF8KeyReleased(evt);
            }
        });
        jspF8.setViewportView(tblF8);

        tpnlSERVICIOS.addTab("tab1", jspF8);

        jspF9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "F2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jspF9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspF9.setMaximumSize(new java.awt.Dimension(185, 185));
        jspF9.setMinimumSize(new java.awt.Dimension(185, 185));
        jspF9.setPreferredSize(new java.awt.Dimension(185, 185));

        tblF9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblF9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblF9.setComponentPopupMenu(jmpACCIONES);
        tblF9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblF9.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblF9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblF9MousePressed(evt);
            }
        });
        tblF9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblF9KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblF9KeyReleased(evt);
            }
        });
        jspF9.setViewportView(tblF9);

        tpnlSERVICIOS.addTab("tab2", jspF9);

        jspF10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "F3", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jspF10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspF10.setMaximumSize(new java.awt.Dimension(185, 185));
        jspF10.setMinimumSize(new java.awt.Dimension(185, 185));
        jspF10.setPreferredSize(new java.awt.Dimension(185, 185));

        tblF10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblF10.setComponentPopupMenu(jmpACCIONES);
        tblF10.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblF10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblF10MousePressed(evt);
            }
        });
        tblF10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblF10KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblF10KeyReleased(evt);
            }
        });
        jspF10.setViewportView(tblF10);

        tpnlSERVICIOS.addTab("tab3", jspF10);

        jspF11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "F4", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jspF11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspF11.setMaximumSize(new java.awt.Dimension(185, 185));
        jspF11.setMinimumSize(new java.awt.Dimension(185, 185));
        jspF11.setPreferredSize(new java.awt.Dimension(185, 185));

        tblF11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblF11.setComponentPopupMenu(jmpACCIONES);
        tblF11.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblF11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblF11MousePressed(evt);
            }
        });
        tblF11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblF11KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblF11KeyReleased(evt);
            }
        });
        jspF11.setViewportView(tblF11);

        tpnlSERVICIOS.addTab("tab4", jspF11);

        jspF12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "F5", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jspF12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspF12.setMaximumSize(new java.awt.Dimension(185, 185));
        jspF12.setMinimumSize(new java.awt.Dimension(185, 185));
        jspF12.setPreferredSize(new java.awt.Dimension(185, 185));

        tblF12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblF12.setComponentPopupMenu(jmpACCIONES);
        tblF12.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblF12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblF12MousePressed(evt);
            }
        });
        tblF12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblF12KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblF12KeyReleased(evt);
            }
        });
        jspF12.setViewportView(tblF12);

        tpnlSERVICIOS.addTab("tab5", jspF12);

        txtBUSCAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBUSCAR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtBUSCAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBUSCARKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBUSCARKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            .addComponent(txtBUSCAR)
            .addComponent(tpnlSERVICIOS)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpnlSERVICIOS, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntATRASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntATRASActionPerformed
        try {
            navegar("-");            
        } catch (ParseException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntATRASActionPerformed

    private void btnDELANTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDELANTEActionPerformed
        try {
            navegar("+");
        } catch (ParseException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDELANTEActionPerformed

    private void tblF8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblF8MousePressed
        try {            
            pestana();
            if(F.clickD(evt, table))
                seleccion(table);
            else
                if(F.dosClick(evt, table))
                {
                    seleccion(table);
                    int IDORDENP = F.SERVICIO(IDPENDIENTE);
                    abrir(IDPENDIENTE, IDORDENP);  
                    actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
                }
        } catch (SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblF8MousePressed

    private void btnCAMBIOHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCAMBIOHActionPerformed
        try {
            agenda();
        } catch (SQLException ex) {
            Logger.getLogger(VPendientesL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VPendientesL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCAMBIOHActionPerformed

    private void tblF9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblF9MousePressed
        try {            
            pestana();
            if(F.clickD(evt, table))
                seleccion(table);
            else
                if(F.dosClick(evt, table))
                {
                    seleccion(table);
                    int IDORDENP = F.SERVICIO(IDPENDIENTE);
                    abrir(IDPENDIENTE, IDORDENP);  
                    actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
                }
        } catch (SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblF9MousePressed

    private void tblF10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblF10MousePressed
        try {            
            pestana();
            if(F.clickD(evt, table))
                seleccion(table);
            else
                if(F.dosClick(evt, table))
                {
                    seleccion(table);
                    int IDORDENP = F.SERVICIO(IDPENDIENTE);
                    abrir(IDPENDIENTE, IDORDENP);  
                    actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
                }
        } catch (SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblF10MousePressed

    private void tblF11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblF11MousePressed
        try {            
            pestana();
            if(F.clickD(evt, table))
                seleccion(table);
            else
                if(F.dosClick(evt, table))
                {
                    seleccion(table);
                    int IDORDENP = F.SERVICIO(IDPENDIENTE);
                    abrir(IDPENDIENTE, IDORDENP);  
                    actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
                }
        } catch (SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblF11MousePressed

    private void tblF12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblF12MousePressed
        try {            
            pestana();
            if(F.clickD(evt, table))
                seleccion(table);
            else
                if(F.dosClick(evt, table))
                {
                    seleccion(table);
                    int IDORDENP = F.SERVICIO(IDPENDIENTE);
                    abrir(IDPENDIENTE, IDORDENP);  
                    actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
                }
        } catch (SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblF12MousePressed

    private void btnACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnACTUALIZARActionPerformed
        try {
            // TODO add your handling code here:
            //index = 4;
            actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
        } catch (SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnACTUALIZARActionPerformed

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        pestana();
        F.MAYUS(evt);
        F.buscar(table, txtBUSCAR.getText(),null,0);
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void tpnlSERVICIOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpnlSERVICIOSMouseClicked
        // TODO add your handling code here:
        pestana();
        
    }//GEN-LAST:event_tpnlSERVICIOSMouseClicked

    private void tblF09MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblF09MousePressed
        try {            
            pestana();
            if(F.clickD(evt, table))
                seleccion(table);
            else
                if(F.dosClick(evt, table))
                {
                    seleccion(table);
                    int IDORDENP = F.SERVICIO(IDPENDIENTE);
                    abrir(IDPENDIENTE, IDORDENP);  
                    actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
                }
        } catch (SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblF09MousePressed

    private void tblF010MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblF010MousePressed
        try {            
            pestana();
            if(F.clickD(evt, table))
                seleccion(table);
            else
                if(F.dosClick(evt, table))
                {
                    seleccion(table);
                    int IDORDENP = F.SERVICIO(IDPENDIENTE);
                    abrir(IDPENDIENTE, IDORDENP);  
                    actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
                }
        } catch (SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblF010MousePressed

    private void tblF011MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblF011MousePressed
        try {            
            pestana();
            if(F.clickD(evt, table))
                seleccion(table);
            else
                if(F.dosClick(evt, table))
                {
                    seleccion(table);
                    int IDORDENP = F.SERVICIO(IDPENDIENTE);
                    abrir(IDPENDIENTE, IDORDENP);  
                    actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
                }
        } catch (SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblF011MousePressed

    private void tblF012MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblF012MousePressed
        try {            
            pestana();
            if(F.clickD(evt, table))
                seleccion(table);
            else
                if(F.dosClick(evt, table))
                {
                    seleccion(table);
                    int IDORDENP = F.SERVICIO(IDPENDIENTE);
                    abrir(IDPENDIENTE, IDORDENP);  
                    actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
                }
        } catch (SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblF012MousePressed

    private void jmmAGENDARSIGUIENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmAGENDARSIGUIENTEActionPerformed
        try {
            pestana();
            seleccion(table);
            pregunta();
            //index = 4;
            actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
        } catch (SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmAGENDARSIGUIENTEActionPerformed

    private void jmmAGENDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmAGENDARActionPerformed
        try {
            pestana();
            seleccion(table);
            agendar("AGENDAR");
            //index = 4;
            actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
        } catch (SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmAGENDARActionPerformed

    private void txtBUSCARKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyPressed
        if(F.ENTER(evt))
        {
            pestana();
            if(table.getRowCount() > 0)
            {
                table.requestFocusInWindow();
                SwingUtilities.invokeLater(() -> 
                {                   
                    table.setRowSelectionInterval(0, 0);
                    table.scrollRectToVisible(table.getCellRect(0, 0, true));
                });           
                SwingUtilities.invokeLater(() -> 
                {                   
                    try 
                    {                
                        seleccion(table);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }); 
            }              
        }
    }//GEN-LAST:event_txtBUSCARKeyPressed

    private void tblF012KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF012KeyReleased
        if(F.NAVEGARTABLA(evt))
        {
            pestana();
            try 
            {
                seleccion(table);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
               Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }//GEN-LAST:event_tblF012KeyReleased

    private void tblF011KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF011KeyReleased
        if(F.NAVEGARTABLA(evt))
        {
            pestana();
            try 
            {
                seleccion(table);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
               Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }//GEN-LAST:event_tblF011KeyReleased

    private void tblF010KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF010KeyReleased
        if(F.NAVEGARTABLA(evt))
        {
            pestana();
            try 
            {
                seleccion(table);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
               Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }//GEN-LAST:event_tblF010KeyReleased

    private void tblF09KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF09KeyReleased
        if(F.NAVEGARTABLA(evt))
        {
            pestana();
            try 
            {
                seleccion(table);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
               Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }//GEN-LAST:event_tblF09KeyReleased

    private void tblF8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF8KeyReleased
        if(F.NAVEGARTABLA(evt))
        {
            pestana();
            try 
            {
                seleccion(table);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
               Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }//GEN-LAST:event_tblF8KeyReleased

    private void tblF9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF9KeyReleased
        if(F.NAVEGARTABLA(evt))
        {
            pestana();
            try 
            {
                seleccion(table);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
               Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }//GEN-LAST:event_tblF9KeyReleased

    private void tblF10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF10KeyReleased
        if(F.NAVEGARTABLA(evt))
        {
            pestana();
            try 
            {
                seleccion(table);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
               Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }//GEN-LAST:event_tblF10KeyReleased

    private void tblF11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF11KeyReleased
        if(F.NAVEGARTABLA(evt))
        {
            pestana();
            try 
            {
                seleccion(table);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
               Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }//GEN-LAST:event_tblF11KeyReleased

    private void tblF12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF12KeyReleased
        if(F.NAVEGARTABLA(evt))
        {
            pestana();
            try 
            {
                seleccion(table);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
               Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }//GEN-LAST:event_tblF12KeyReleased

    private void tblF012KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF012KeyPressed
        if(F.ENTER(evt))
            try {
                pestana();
                seleccion(table);
                int IDORDENP = F.SERVICIO(IDPENDIENTE);
                abrir(IDPENDIENTE, IDORDENP);  
                actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
        else
            if(F.SHIFT(evt))
            {
                try {
                    pestana();
                    seleccion(table);
                    Rectangle rect = table.getCellRect(table.getSelectedRow(), 0, true);
                    jmpACCIONES.show(table, rect.x, rect.y + rect.height);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_tblF012KeyPressed

    private void tblF011KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF011KeyPressed
        if(F.ENTER(evt))
            try {
                pestana();
                seleccion(table);
                int IDORDENP = F.SERVICIO(IDPENDIENTE);
                abrir(IDPENDIENTE, IDORDENP);  
                actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
        else
            if(F.SHIFT(evt))
            {
                try {
                    pestana();
                    seleccion(table);
                    Rectangle rect = table.getCellRect(table.getSelectedRow(), 0, true);
                    jmpACCIONES.show(table, rect.x, rect.y + rect.height);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_tblF011KeyPressed

    private void tblF010KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF010KeyPressed
        if(F.ENTER(evt))
            try {
                pestana();
                seleccion(table);
                int IDORDENP = F.SERVICIO(IDPENDIENTE);
                abrir(IDPENDIENTE, IDORDENP);  
                actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
        else
            if(F.SHIFT(evt))
            {
                try {
                    pestana();
                    seleccion(table);
                    Rectangle rect = table.getCellRect(table.getSelectedRow(), 0, true);
                    jmpACCIONES.show(table, rect.x, rect.y + rect.height);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_tblF010KeyPressed

    private void tblF09KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF09KeyPressed
        if(F.ENTER(evt))
            try {
                pestana();
                seleccion(table);
                int IDORDENP = F.SERVICIO(IDPENDIENTE);
                abrir(IDPENDIENTE, IDORDENP);  
                actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
        else
            if(F.SHIFT(evt))
            {
                try {
                    pestana();
                    seleccion(table);
                    Rectangle rect = table.getCellRect(table.getSelectedRow(), 0, true);
                    jmpACCIONES.show(table, rect.x, rect.y + rect.height);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_tblF09KeyPressed

    private void tblF8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF8KeyPressed
        if(F.ENTER(evt))
            try {
                pestana();
                seleccion(table);
                int IDORDENP = F.SERVICIO(IDPENDIENTE);
                abrir(IDPENDIENTE, IDORDENP);  
                actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
        else
            if(F.SHIFT(evt))
            {
                try {
                    pestana();
                    seleccion(table);
                    Rectangle rect = table.getCellRect(table.getSelectedRow(), 0, true);
                    jmpACCIONES.show(table, rect.x, rect.y + rect.height);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_tblF8KeyPressed

    private void tblF9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF9KeyPressed
        if(F.ENTER(evt))
            try {
                pestana();
                seleccion(table);
                int IDORDENP = F.SERVICIO(IDPENDIENTE);
                abrir(IDPENDIENTE, IDORDENP);  
                actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
        else
            if(F.SHIFT(evt))
            {
                try {
                    pestana();
                    seleccion(table);
                    Rectangle rect = table.getCellRect(table.getSelectedRow(), 0, true);
                    jmpACCIONES.show(table, rect.x, rect.y + rect.height);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_tblF9KeyPressed

    private void tblF10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF10KeyPressed
        if(F.ENTER(evt))
            try {
                pestana();
                seleccion(table);
                int IDORDENP = F.SERVICIO(IDPENDIENTE);
                abrir(IDPENDIENTE, IDORDENP);  
                actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
        else
            if(F.SHIFT(evt))
            {
                try {
                    pestana();
                    seleccion(table);
                    Rectangle rect = table.getCellRect(table.getSelectedRow(), 0, true);
                    jmpACCIONES.show(table, rect.x, rect.y + rect.height);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_tblF10KeyPressed

    private void tblF11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF11KeyPressed
        if(F.ENTER(evt))
            try {
                pestana();
                seleccion(table);
                int IDORDENP = F.SERVICIO(IDPENDIENTE);
                abrir(IDPENDIENTE, IDORDENP);  
                actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
        else
            if(F.SHIFT(evt))
            {
                try {
                    pestana();
                    seleccion(table);
                    Rectangle rect = table.getCellRect(table.getSelectedRow(), 0, true);
                    jmpACCIONES.show(table, rect.x, rect.y + rect.height);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_tblF11KeyPressed

    private void tblF12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblF12KeyPressed
        if(F.ENTER(evt))
            try {
                pestana();
                seleccion(table);
                int IDORDENP = F.SERVICIO(IDPENDIENTE);
                abrir(IDPENDIENTE, IDORDENP);  
                actualizarTabla(G.FECHA(G.hoy(),false),ESTADO);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
        }
        else
            if(F.SHIFT(evt))
            {
                try {
                    pestana();
                    seleccion(table);
                    Rectangle rect = table.getCellRect(table.getSelectedRow(), 0, true);
                    jmpACCIONES.show(table, rect.x, rect.y + rect.height);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(VAgendaL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_tblF12KeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton bntATRAS;
    public static javax.swing.JButton btnACTUALIZAR;
    private javax.swing.JButton btnCAMBIOH;
    private javax.swing.JButton btnDELANTE;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem jmmAGENDAR;
    private javax.swing.JMenuItem jmmAGENDARSIGUIENTE;
    private javax.swing.JPopupMenu jmpACCIONES;
    private javax.swing.JScrollPane jspF010;
    private javax.swing.JScrollPane jspF011;
    private javax.swing.JScrollPane jspF012;
    private javax.swing.JScrollPane jspF09;
    private javax.swing.JScrollPane jspF10;
    private javax.swing.JScrollPane jspF11;
    private javax.swing.JScrollPane jspF12;
    private javax.swing.JScrollPane jspF8;
    private javax.swing.JScrollPane jspF9;
    private javax.swing.JTable tblF010;
    private javax.swing.JTable tblF011;
    private javax.swing.JTable tblF012;
    private javax.swing.JTable tblF09;
    private javax.swing.JTable tblF10;
    private javax.swing.JTable tblF11;
    private javax.swing.JTable tblF12;
    private javax.swing.JTable tblF8;
    private javax.swing.JTable tblF9;
    private javax.swing.JTabbedPane tpnlSERVICIOS;
    private javax.swing.JTextField txtBUSCAR;
    // End of variables declaration//GEN-END:variables
}

