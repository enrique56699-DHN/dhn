/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35.
 */
package VISTA;

import CONTROLADOR.ControlAbonos;
import CONTROLADOR.ControlAhorros;
import CONTROLADOR.ControlDetalles;
import CONTROLADOR.ControlEDetalles;
import CONTROLADOR.ControlEmpleados;
import CONTROLADOR.ControlEntradas;
import CONTROLADOR.ControlNominas;
import CONTROLADOR.ControlPrestaciones;
import CONTROLADOR.ControlPrestacionesP;
import CONTROLADOR.ControlPrestamos;
import CONTROLADOR.ControlSalidas;
import CONTROLADOR.ControlSalidasCaja;
import CONTROLADOR.ControlVacaciones;
import CONTROLADOR.ControlVacacionesTAB;
import CONTROLADOR.F;
import UTILERIAS.RenderCelda;
import DHNv2.G;
import HIK.AcsEventResponse;
import HIK.EventInfo;
import HIK.HikvisionClient;
import HIK.Registro;
import MODELO.ModeloAbonos;
import MODELO.ModeloAbonosTabla;
import MODELO.ModeloAhorros;
import MODELO.ModeloAhorrosTabla;
import MODELO.ModeloDetalles;
import MODELO.ModeloEDetalles;
import MODELO.ModeloEmpleados;
import MODELO.ModeloEntradas;
import MODELO.ModeloNominas;
import MODELO.ModeloPrestaciones;
import MODELO.ModeloPrestacionesP;
import MODELO.ModeloPrestamos;
import MODELO.ModeloPrestamosTabla;
import MODELO.ModeloSalidas;
import MODELO.ModeloSalidasCaja;
import MODELO.ModeloVacaciones;
import MODELO.ModeloVacacionesTAB;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.event.ItemEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.JAXBException;
import org.docx4j.openpackaging.exceptions.Docx4JException;

/**
 *
 * @author KIKE FRENCH
 */

public final class VRegNomina extends javax.swing.JInternalFrame {

    int IDCAJA = G.IDCAJA;
    private String FECHAALTA;
    private Date ALTA;
    private Date BAJA;
    private long dif,difm;
    private int diasTrabajados;

    public String DIA_CORTE,Dia;
    private float SUELDO;
    private int IDEMPLEADO;
    private int IDNOMINA;
    private float BONO;
    private float SALDO;
    private double monto;
    private double total;
    private double prestamos;
    private double abonos;
    private double ahorros;
    private int IDAHORROTH;
    private float MONTOTH;
    private int IDABONOTA;
    private float MONTOTA;
    private double salario_minimo;
    private String DiaD, MesD, AnoD;
    private int SEMANA;
    private double NSUELDO = 0;
    private float DIAS;
    private double EXTRA;
    private double TOTAL;
    private double  AHORRO;
    private double ABONO;
    private int IDSALIDA;
    private double AGUINALDO;
    private int DIAST=0;
    private int ANO;
    private int IDPRESTACIONP;
    private int PERIODOB;
    private int PERIODOV;
    private int PERIODOA;
    private int PERIODOEV;
    private float montoB;
    private double salario_diario;
    private int dias_pendientes;
    private int anos_completos;
    private double tv;
    private double primavaca;
    private int dias_correspondientes;
    private double dias_proporcionales;
    private double dias_prima;
    private String PINI, PFIN;
    private float TOTALBP;

    private DefaultTableModel tableModel;
    private DefaultTableModel tableModel2;
    private List<Registro> registros;
    
    private String DEPTO;                   
    
    private HikvisionClient hikvisionClient;
    int f=0, r=0, rj=0, psgs=0, pcgs=0;
    
    /** Creates new form VClientes

     * @throws java.text.ParseException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException */
    public VRegNomina() throws ParseException, ClassNotFoundException, SQLException {
     
    }
    
    private void imp(String DOCUMENTO) throws IOException, Docx4JException, FileNotFoundException, JAXBException, InterruptedException, Exception
    {
        //String MONTO = "0";
        String SERIE = ""+IDPRESTACIONP;
        //String DURACIONCONT = "";
        switch(DOCUMENTO)
        {
            case "NOMINAS":
                SERIE = ""+IDNOMINA;
                //MONTO = txtTOTAL.getText()+".00";
                //MONTO = txtTOTAL.getText();
                break;
            case "BONO":
                //MONTO = "$ "+G.numero.format(montoB)+".00";
                //MONTO = G.numero.format(montoB);
                TOTAL = montoB;
                break;
            case "AGUINALDO":
                //MONTO = "$ "+G.numero.format(AGUINALDO);
                //MONTO = G.numero.format(AGUINALDO);     
                TOTAL = TOTALBP;
                break;
            default:                
                break;
        }
        
        String rutaExcel = G.rutaServidor+"MACHOTES\\"+DOCUMENTO+".xlsx";
        String rutaWord = G.rutaServidor+"MACHOTES\\"+DOCUMENTO+".docx";
        String rutaWordtmp = G.rutaServidor+"MACHOTES\\"+DOCUMENTO+"tmp.docx";
        String rutaPDF = G.rutaServidor+"DOCS\\"+DOCUMENTO+"-"+SERIE+".pdf";    
                         
        // Definimos un helper para el formato de moneda y evitar repetir código
        Function<Object, String> formatMoney = (n) -> "$ " + G.numero.format(n) + (n instanceof Double || n instanceof Float ? ".00" : "");

        Map<String, String> reporte = new LinkedHashMap<>();
        
        // Helper para insertar celda simple con validación de límites
        BiConsumer<String, int[]> putCelda = (clave, pos) -> {
            int fila = pos[0];
            int col = pos[1];
            if (fila < this.tblTABLA.getRowCount() && col < this.tblTABLA.getColumnCount()) {
                Object valor = this.tblTABLA.getValueAt(fila, col);
                if (valor != null) {
                    reporte.put(clave, valor.toString());
                }
            }
        };

        // Helper para concatenar dos celdas (ej. Fecha + Día)
        BiConsumer<String, int[]> putConcatenatedCeldas = (clave, pos) -> {
            int fila = pos[0];
            if (fila < this.tblTABLA.getRowCount() && 1 < this.tblTABLA.getColumnCount()) {
                Object col0 = this.tblTABLA.getValueAt(fila, 0);
                Object col1 = this.tblTABLA.getValueAt(fila, 1);
                if (col0 != null) {
                    String val1 = Objects.toString(col0, "");
                    String val2 = Objects.toString(col1, "");
                    // Mantiene el espacio intermedio si la primera celda lo requería
                    reporte.put(clave, val1 + (fila == 0 ? " - " : " - ") + val2);
                }
            }
        };

        // Helper para insertar valor seleccionado de JComboBox
        BiConsumer<String, JComboBox<?>> putCombo = (clave, combo) -> {
            if (combo != null && combo.getSelectedItem() != null) {
                reporte.put(clave, combo.getSelectedItem().toString());
            }
        };

        reporte.put("«FOLIO»",SERIE);
        reporte.put("«FECHA»", G.formato.format(dteFECHA.getDate()));
        reporte.put("«EMPRESA»",txtEMPRESA.getText());
        reporte.put("«NOMBRE»",txtNOMBRE.getText());
        reporte.put("«SALARIO»",formatMoney.apply(SUELDO));
        reporte.put("«DIAST»",txtDIAS.getText());
        reporte.put("«NSALARIO»",formatMoney.apply(NSUELDO));
        reporte.put("«BONO»",formatMoney.apply(EXTRA));
        reporte.put("«DESCRIPCIONBONO»",txtCONCEPTO.getText());
        reporte.put("«AHORRO»",formatMoney.apply(AHORRO));
        reporte.put("«ABONO»",formatMoney.apply(ABONO));
        reporte.put("«TOTAL»",formatMoney.apply(TOTAL));
        reporte.put("«VCORRESPONDIENTES»",txtDIASCORRESPONDIENTES.getText());
        reporte.put("«VPREDETERMINADAS»",txtDIASPREDERTERMINADOS.getText());
        reporte.put("«VDTOMADOS»",txtDIASTOMADOS.getText());
        reporte.put("«VDRESTANTES»",txtDIASRESTANTES.getText());
        reporte.put("«VPRIMA»",txtPRIMA.getText());
        reporte.put("«TPRESTAMOS»",txtSPRESTAMOS.getText());
        reporte.put("«TABONOS»",txtSABONOS.getText());
        reporte.put("«TAHORROS»",txtSAHORROS.getText());
        reporte.put("«TSALDO»",txtSALDO.getText());
        reporte.put("«BONOT»", txtBONOT.getText());
        reporte.put("«SEMANA»",String.valueOf(SEMANA));
        reporte.put("«PAGODIAS»",txtMONTOV.getText());
        reporte.put("«TOTALPV»",txtTOTALV.getText());
        reporte.put("«RA»","0");
        reporte.put("FIA","0");
        reporte.put("FAA","0");
        reporte.put("FJA","0");
        reporte.put("TtAA","0");
        reporte.put("CARGOSH","0");
        reporte.put("ABONOSH","0");
        reporte.put("TOTALH","0");
        reporte.put("TBP","0");
        reporte.put("TOTALBP",formatMoney.apply(TOTALBP));
        reporte.put("¬BP¬",txtBONOTP.getText());
        //RETARDOS,FALTAS,ETC
        reporte.put("¬FIS¬",lblF.getText());
        reporte.put("¬RS¬",lblR.getText());
        reporte.put("¬FJS¬",lblFJ.getText());
        reporte.put("¬FAS¬",lblFA.getText());
        
        
        //APLICA A TODOS
        //FECHAS DE LA TABLA
        /*if(this.tblTABLA.getValueAt(0, 0)!=null)
            reporte.put("¬F1¬", this.tblTABLA.getValueAt(0, 0).toString()+" "+this.tblTABLA.getValueAt(0, 1).toString());
        if(this.tblTABLA.getValueAt(1, 0)!=null)
            reporte.put("¬F2¬", this.tblTABLA.getValueAt(1, 0).toString()+this.tblTABLA.getValueAt(1, 1).toString());
        if(this.tblTABLA.getValueAt(2, 0)!=null)
            reporte.put("¬F3¬", this.tblTABLA.getValueAt(2, 0).toString()+this.tblTABLA.getValueAt(2, 1).toString());
        if(this.tblTABLA.getValueAt(3, 0)!=null)
            reporte.put("¬F4¬", this.tblTABLA.getValueAt(3, 0).toString()+this.tblTABLA.getValueAt(3, 1).toString());
        if(this.tblTABLA.getValueAt(4, 0)!=null)
            reporte.put("¬F5¬", this.tblTABLA.getValueAt(4, 0).toString()+this.tblTABLA.getValueAt(4, 1).toString());
        reporte.put("¬F6¬", "");
        //ENTRADAS 1 DE LA TABLA Y PONER ENTRADA 2 EN BLANCO HASTA NO SER ADMINISTRATIVO
        if(this.tblTABLA.getValueAt(0, 2)!=null)
            reporte.put("¬F1E1¬", this.tblTABLA.getValueAt(0, 2).toString());
        reporte.put("¬F1E2¬", "");
        if(this.tblTABLA.getValueAt(1, 2)!=null)
            reporte.put("¬F2E1¬", this.tblTABLA.getValueAt(1, 2).toString());
        reporte.put("¬F2E2¬", "");
        if(this.tblTABLA.getValueAt(2, 2)!=null)
            reporte.put("¬F3E1¬", this.tblTABLA.getValueAt(2, 2).toString());
        reporte.put("¬F3E2¬", "");
        if(this.tblTABLA.getValueAt(3, 2)!=null)
            reporte.put("¬F4E1¬", this.tblTABLA.getValueAt(3, 2).toString());
        reporte.put("¬F4E2¬", "");
        if(this.tblTABLA.getValueAt(4, 2)!=null)
            reporte.put("¬F5E1¬", this.tblTABLA.getValueAt(4, 2).toString());
        reporte.put("¬F5E2¬", "");
        reporte.put("¬F6E1¬", "");
        //SALIDAS 1 DE LA TABLA Y PONER SALIDA 2 EN BLANCO HASTA NO SER ADMINISTRATIVO
        if(this.tblTABLA.getValueAt(0, 4)!=null)
            reporte.put("¬F1S1¬", this.tblTABLA.getValueAt(0, 4).toString());
        reporte.put("¬F1S2¬", "");
        if(this.tblTABLA.getValueAt(1, 4)!=null)
            reporte.put("¬F2S1¬", this.tblTABLA.getValueAt(1, 4).toString());
        reporte.put("¬F2S2¬", "");
        if(this.tblTABLA.getValueAt(2, 4)!=null)
            reporte.put("¬F3S1¬", this.tblTABLA.getValueAt(2, 4).toString());
        reporte.put("¬F3S2¬", "");
        if(this.tblTABLA.getValueAt(3, 4)!=null)
            reporte.put("¬F4S1¬", this.tblTABLA.getValueAt(3, 4).toString());
        reporte.put("¬F4S2¬", "");
        if(this.tblTABLA.getValueAt(4, 4)!=null)
            reporte.put("¬F5S1¬", this.tblTABLA.getValueAt(4, 4).toString());   
        reporte.put("¬F5S2¬", "");
        reporte.put("¬F6S1¬", "");
        //TOMA EL VALOR DE LOS COMBO-BOX
        if(this.txtF1R1.getSelectedItem()!=null)
            reporte.put("¬F1R1¬", txtF1R1.getSelectedItem().toString());
        if(this.txtF2R1.getSelectedItem()!=null)
            reporte.put("¬F2R1¬", txtF2R1.getSelectedItem().toString());
        if(this.txtF3R1.getSelectedItem()!=null)
            reporte.put("¬F3R1¬", txtF3R1.getSelectedItem().toString());
        if(this.txtF4R1.getSelectedItem()!=null)
            reporte.put("¬F4R1¬", txtF4R1.getSelectedItem().toString());
        if(this.txtF5R1.getSelectedItem()!=null)
            reporte.put("¬F5R1¬", txtF5R1.getSelectedItem().toString());
        reporte.put("¬F6R1¬", "");
        
        if(DEPTO.equals("ADMINISTRATIVO"))
        {
            //VALOR DE LA FECHA 6 PARA ADMINISRATIVO
            if(this.tblTABLA.getValueAt(5, 0)!=null)
                reporte.put("¬F6¬", this.tblTABLA.getValueAt(5, 0).toString()+this.tblTABLA.getValueAt(5, 1).toString());
            //VALOR DE LA ENTRADA DE LA FECHA 6 PARA ADMINISTRATIVO
            if(this.tblTABLA.getValueAt(5, 2)!=null)
                reporte.put("¬F6E1¬", this.tblTABLA.getValueAt(5, 2).toString());
            if(this.tblTABLA.getValueAt(0, 5)!=null)
                reporte.put("¬F1E2¬", this.tblTABLA.getValueAt(0, 5).toString());
            if(this.tblTABLA.getValueAt(1, 5)!=null)
                reporte.put("¬F2E2¬", this.tblTABLA.getValueAt(1, 5).toString());
            if(this.tblTABLA.getValueAt(2, 5)!=null)
                reporte.put("¬F3E2¬", this.tblTABLA.getValueAt(2, 5).toString());
            if(this.tblTABLA.getValueAt(3, 5)!=null)
                reporte.put("¬F4E2¬", this.tblTABLA.getValueAt(3, 5).toString());
            if(this.tblTABLA.getValueAt(4, 5)!=null)
                reporte.put("¬F5E2¬", this.tblTABLA.getValueAt(4, 5).toString());
            //VALOR DE LA SALIDA DE LA FECHA 6 PARA ADMINISTRATIVO
            if(this.tblTABLA.getValueAt(5, 7)!=null)
                reporte.put("¬F6S1¬", this.tblTABLA.getValueAt(5, 7).toString());            
            if(this.tblTABLA.getValueAt(0, 7)!=null)
                reporte.put("¬F1S2¬", this.tblTABLA.getValueAt(0, 7).toString());
            if(this.tblTABLA.getValueAt(1, 7)!=null)
                reporte.put("¬F2S2¬", this.tblTABLA.getValueAt(1, 7).toString());
            if(this.tblTABLA.getValueAt(2, 7)!=null)
                reporte.put("¬F3S2¬", this.tblTABLA.getValueAt(2, 7).toString());
            if(this.tblTABLA.getValueAt(3, 7)!=null)
                reporte.put("¬F4S2¬", this.tblTABLA.getValueAt(3, 7).toString());
            if(this.tblTABLA.getValueAt(4, 7)!=null)
                reporte.put("¬F5S2¬", this.tblTABLA.getValueAt(4, 7).toString());      
            //VALOR DE LOS COMBOBOX R2
            if(this.txtF1R2.getSelectedItem()!=null)
                reporte.put("¬F1R2¬", txtF1R2.getSelectedItem().toString());
            if(this.txtF2R2.getSelectedItem()!=null)
                reporte.put("¬F2R2¬", txtF2R2.getSelectedItem().toString());
            if(this.txtF3R2.getSelectedItem()!=null)
                reporte.put("¬F3R2¬", txtF3R2.getSelectedItem().toString());
            if(this.txtF4R2.getSelectedItem()!=null)
                reporte.put("¬F4R2¬", txtF4R2.getSelectedItem().toString());
            if(this.txtF5R2.getSelectedItem()!=null)
                reporte.put("¬F5R2¬", txtF5R2.getSelectedItem().toString());
            if(this.txtF6R1.getSelectedItem()!=null)
                reporte.put("¬F6R1¬", txtF6R1.getSelectedItem().toString());
        }*/
        
        // 2. VALORES POR DEFECTO / APLICA A TODOS (Filas 1 a 5)
        for (int i = 0; i < 5; i++) {
            int numFila = i + 1;

            // Fechas concatenadas (Columna 0 + Columna 1)
            putConcatenatedCeldas.accept("¬F" + numFila + "¬", new int[]{i});

            // Entradas 1 y 2
            putCelda.accept("¬F" + numFila + "E1¬", new int[]{i, 2});
            reporte.put("¬F" + numFila + "E2¬", "");

            // Salidas 1 y 2
            putCelda.accept("¬F" + numFila + "S1¬", new int[]{i, 4});
            reporte.put("¬F" + numFila + "S2¬", "");
        }

        reporte.put("¬F6¬", "");
        reporte.put("¬F6E1¬", "");
        reporte.put("¬F6S1¬", "");

        // Combos R1
        JComboBox<?>[] combosR1 = {txtF1R1, txtF2R1, txtF3R1, txtF4R1, txtF5R1};
        for (int i = 0; i < combosR1.length; i++) {
            putCombo.accept("¬F" + (i + 1) + "R1¬", combosR1[i]);
        }
        reporte.put("¬F6R1¬", "");

        // 3. SOBRESCRIBIR VALORES PARA DEPARTAMENTO ADMINISTRATIVO
        if ("ADMINISTRATIVO".equals(DEPTO)) {
            putCelda.accept("¬F6¬", new int[]{5, 0});
            putCelda.accept("¬F6E1¬", new int[]{5, 2});
            putCelda.accept("¬F6S1¬", new int[]{5, 7});

            for (int i = 0; i < 5; i++) {
                int numFila = i + 1;
                putCelda.accept("¬F" + numFila + "E2¬", new int[]{i, 5});
                putCelda.accept("¬F" + numFila + "S2¬", new int[]{i, 7});
            }

            JComboBox<?>[] combosR2 = {txtF1R2, txtF2R2, txtF3R2, txtF4R2, txtF5R2};
            for (int i = 0; i < combosR2.length; i++) {
                putCombo.accept("¬F" + (i + 1) + "R2¬", combosR2[i]);
            }
            putCombo.accept("¬F6R1¬", txtF6R1);
        }
        
        // Si necesitas recuperar los arrays para un método antiguo:
        String[] titulos = reporte.keySet().toArray(new String[0]);
        String[] datos = reporte.values().toArray(new String[0]);
        
        F.imprimir(titulos,datos, rutaExcel, rutaWord, rutaWordtmp, rutaPDF);                
    }
    
    public VRegNomina(int IDEMPLEADO, String NOMBRE, float SUELDO, String EMPRESA, float BONO, float BONOP, String FECHAALTA) throws ParseException, Exception {
        initComponents();     
        dteFECHA.setDate(G.hoy());
        G.atributosCalendario(dteFECHA);
        //dteSINI.setDate(G.hoy());
        G.atributosCalendario(dteSINI);
        //dteSFIN.setDate(G.hoy());
        G.atributosCalendario(dteSFIN);
        this.Bar.setFloatable(false);
        this.jpVACACIONES.setVisible(false);
        this.txtDIAS.requestFocus();
        
        mostrar(IDEMPLEADO, NOMBRE, SUELDO, EMPRESA, BONO, BONOP, FECHAALTA);
        periodo();
        vacaciones(IDEMPLEADO, PERIODOEV);
        aguinaldo(IDEMPLEADO, EMPRESA);
        detalles();
        //calcularVACACIONES();
        liquidacion();             
        actualizarTabla();        
        allCMB();
        
        hikvisionClient = new HikvisionClient();
        registros = new ArrayList<>();
        iniciarTabla();        
        F.teclas(this);
    }
    
    private void periodo() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloPrestaciones mdl;
        ControlPrestaciones ctrl = new ControlPrestaciones();
        mdl = ctrl.Registros("BONO DE CAJA", "DESC");
        PERIODOB = mdl.getPERIODO();
        
        ctrl = new ControlPrestaciones();
        mdl = ctrl.Registros("PRIMA VACACIONAL", "DESC");
        PERIODOV = mdl.getPERIODO();
        
        ctrl = new ControlPrestaciones();
        mdl = ctrl.Registros("AGUINALDO", "DESC");
        PERIODOA = mdl.getPERIODO();
        PERIODOA = 0;
        
        ModeloEDetalles mdlED;
        ControlEDetalles ctrlED = new ControlEDetalles();
        mdlED = ctrlED.getRegistrosID(IDEMPLEADO);
        PERIODOEV = mdlED.getPERIODOV();
    }
    
    private void vacaciones(int IDEMPLEADO, int PERIODOEV) throws ClassNotFoundException, SQLException, Exception
    {   
        ControlVacaciones cv = new ControlVacaciones();
        DIAST=cv.getDIAST(IDEMPLEADO, PERIODOEV);        
        if(DIAST <=0)        
            this.txtDIASTOMADOS.setText("0");        
        else        
            this.txtDIASTOMADOS.setText(""+DIAST);        
        
        ModeloEDetalles med;
        ControlEDetalles ced = new ControlEDetalles();
        med=ced.getRegistrosID(IDEMPLEADO);
        this.dtePINI.setDate(G.formato.parse(med.getPINI()));
        this.dtePFIN.setDate(G.formato.parse(med.getPFIN()));
        PINI = med.getPINI();
        PFIN = med.getPFIN();
    }
        
    private void detalles() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloDetalles md;
        ControlDetalles cd = new ControlDetalles();
        md=cd.getRegistrosD();
        this.txtDIASPREDERTERMINADOS.setText(""+md.getDIASPRE());
        salario_minimo=md.getSMINIMO();
    }
    
    private void aguinaldo(int IDEMPLEADO, String EMPRESA) throws ClassNotFoundException, SQLException, Exception
    {       
        int resultado;
        ControlPrestacionesP cpp = new ControlPrestacionesP();
        resultado= cpp.aguinaldop(IDEMPLEADO, PERIODOA);
        if(resultado==1)
        {
            this.lblAGUINALDO.setText(""+0);
        }
        else
        {
            ControlEmpleados ce = new ControlEmpleados();
            AGUINALDO = ce.aguinaldo(IDEMPLEADO, EMPRESA);
            this.lblAGUINALDO.setText("$ "+G.numero.format(AGUINALDO));
        }
        
    }
    
    public static boolean existePunto(String cadena)
    {
        boolean resultado=false;
        for(int i=0;i<cadena.length();i++)
        {
            if(cadena.substring(i,i+1).equals("."))
            {
                resultado=true;
                break;
            }
        }
        return resultado;
    }
   
    public void mostrar(int IDEMPLEADO, String NOMBRE, float SUELDO, String EMPRESA, float BONO,float BONOP, String FECHAALTA) throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        this.IDEMPLEADO = IDEMPLEADO;
        this.txtIDEMPLEADO.setText(""+IDEMPLEADO);
        this.txtNOMBRE.setText(NOMBRE);
        this.SUELDO = SUELDO;
        this.txtSUELDO.setText(""+SUELDO);
        this.txtEMPRESA.setText(EMPRESA);
        this.BONO = BONO;
        this.txtBONOT.setText("$ "+G.numero.format(BONO)+".00");
        this.txtBONOTP.setText("$ "+G.numero.format(BONOP)+".00");
        this.FECHAALTA = FECHAALTA;
        this.txtFECHAALTA.setText(FECHAALTA);
        
        //OBTENER EL NUMERO DE SEMANA
        Calendar calendario = Calendar.getInstance();
        this.ANO = calendario.get(Calendar.YEAR);
        this.SEMANA = calendario.get(Calendar.WEEK_OF_YEAR);
        this.txtSEMANA.setText(""+SEMANA);
        salario_diario=SUELDO/7;
        
        ModeloEmpleados mdl;
        ControlEmpleados ctrl = new ControlEmpleados();
        
        mdl=ctrl.getRegistros(IDEMPLEADO);
        this.lblDEPTO.setText(mdl.getDPTO());
        this.DEPTO = mdl.getDPTO();
        
        fechasAsistencias();
    }
    
    private void actualizarTabla() throws ClassNotFoundException, SQLException, Exception
    {
        periodo();
        ModeloPrestamosTabla mpt;
        mpt = new ModeloPrestamosTabla (IDEMPLEADO, PERIODOB);
        jtblPRESTAMOS.setModel(mpt);
        F.dimensionar(jtblPRESTAMOS, "MOVNOMINAS");
        
        ModeloAbonosTabla mat;
        mat = new ModeloAbonosTabla (IDEMPLEADO, PERIODOB);
        jtblABONOS.setModel(mat);
        F.dimensionar(jtblABONOS, "MOVNOMINAS");
        
        ModeloAhorrosTabla mht;
        mht = new ModeloAhorrosTabla (IDEMPLEADO, PERIODOB);
        jtblAHORROS.setModel(mht);
        F.dimensionar(jtblAHORROS, "MOVNOMINAS");
        
        movimientos();        
        /*tblASISTENCIAS.setModel(HIK.downloadEvents(IDEMPLEADO, 
                txtCARGO.getSelectedItem().toString(),
                "2025-12-11 00:00:00","2025-12-12 23:59:59"));*/
    }
    
    private void movimientos()
    {
        monto=0;
        total=0;
        if(jtblPRESTAMOS.getRowCount()>0)
        {
            for(int i = 0; i < jtblPRESTAMOS.getRowCount(); i ++)
            {
                monto = Double.parseDouble(jtblPRESTAMOS.getValueAt(i, 2).toString());
                total += monto;
            }
            prestamos=total;
            txtSPRESTAMOS.setText("$ "+G.numero.format(prestamos)+".00");
            
        }
        else
        {
            txtSPRESTAMOS.setText("$ "+G.numero.format(0)+".00");
        }
        monto=0;
        total=0;
        if(jtblABONOS.getRowCount()>0)
        {
            for(int i = 0; i < jtblABONOS.getRowCount(); i ++)
            {
                monto = Double.parseDouble(jtblABONOS.getValueAt(i, 2).toString());
                total += monto;
            }
            abonos=total;
            txtSABONOS.setText("$ "+G.numero.format(abonos)+".00");            
        }
        else        
            txtSABONOS.setText("$ "+G.numero.format(0)+".00");        
        
        monto=0;
        total=0;
        if(jtblAHORROS.getRowCount()>0)
        {
            for(int i = 0; i < jtblAHORROS.getRowCount(); i ++)
            {
                monto = Double.parseDouble(jtblAHORROS.getValueAt(i, 2).toString());
                total += monto;
            }
            ahorros=total;
            txtSAHORROS.setText("$ "+G.numero.format(ahorros)+".00");
            
        }
        else        
            txtSAHORROS.setText("$ "+G.numero.format(0)+".00");        
        
        monto=0;
        total=0;
        total=ahorros+abonos-prestamos;
        SALDO = (float) total;
        txtSALDO.setText("$ "+G.numero.format(SALDO)+".00");
        
    }
    
    private void calcularNomina() throws SQLException, Exception
    {        
        DIAS = Float.parseFloat(txtDIAS.getText());
                
        if(this.txtDIAS.getText().equals(""))
        {
            JOptionPane.showMessageDialog( null, "DIAS NO PUEDE SER VACIO", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
            
            this.txtDIAS.requestFocus();
        }
        else
        {
            if(DIAS>7)
            {
                JOptionPane.showMessageDialog( null, "Los dias trabajados no puedes ser mayores a 7 \n Los dias extras se pagan en el apartado BONOS o EXTRAS", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
                txtDIAS.requestFocus();
            }
            else
            {
                NSUELDO = (DIAS * SUELDO) / 7;
                
                //NSUELDO = Math.round(NSUELDO * 10) / 10d;
                NSUELDO = Math.ceil(NSUELDO / 5.0) * 5d;
                //F.redondearAlCinco(NSUELDO);
                
                this.txtNSUELDO.setText(""+NSUELDO);
                
                if(this.txtBONO.getText().equals(""))
                {
                    this.txtBONO.setText("0");
                }

                if(this.txtAHORRO.getText().equals(""))
                {
                    this.txtAHORRO.setText("0");
                }
                else
                {
                    if(Float.parseFloat(txtAHORRO.getText())>0)
                    {
                        this.IDAHORROTH = F.Registro("AHORROS", "IDAHORRO", "DESC");
                        //this.FECHATH = ""+Global.formato.format(dte_fecha.getDate());
                        this.MONTOTH = Float.parseFloat(txtAHORRO.getText());
                    }
                }

                if(this.txtABONO.getText().equals(""))
                {
                    this.txtABONO.setText("0");
                }
                else
                {
                    if(Float.parseFloat(txtABONO.getText())>=1)
                    {
                        this.IDABONOTA = F.Registro("ABONOS", "IDABONO", "DESC");
                        //this.FECHATA = ""+Global.formato.format(dte_fecha.getDate());
                        this.MONTOTA = Float.parseFloat(txtABONO.getText());
                    }
                }

                EXTRA = Double.parseDouble(this.txtBONO.getText());
                AHORRO = Double.parseDouble(this.txtAHORRO.getText());
                ABONO = Double.parseDouble(this.txtABONO.getText());
                TOTAL = NSUELDO+EXTRA-AHORRO-ABONO;
                F.redondearAlCinco(TOTAL);
                this.txtTOTAL.setText("$ "+G.numero.format(TOTAL));
                
                ModeloAbonosTabla mat;
                //mat = new ModeloAbonosTabla();
                mat = new ModeloAbonosTabla (IDEMPLEADO,IDABONOTA,G.FECHA(G.hoy(),false),MONTOTA, PERIODOB);
                jtblABONOS.setModel(mat);
                mat.fireTableDataChanged();
                
                ModeloAhorrosTabla mht;
                //mht = new ModeloAhorrosTabla();
                mht = new ModeloAhorrosTabla(IDEMPLEADO,IDAHORROTH,G.FECHA(G.hoy(),false),MONTOTH, PERIODOB);
                jtblAHORROS.setModel(mht);
                mht.fireTableDataChanged();
                
                movimientos();
                liquidacion();
            }
        }  
    }
    
    private void calcularVACACIONES() throws ClassNotFoundException, SQLException, Exception
    {
        
    }
    
    private void liquidacion() throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        //----------------------------CALCULAR DIAS TRASCURRIDOS
        //FECHAACTUAL=formateador.format(hoy);  
        ALTA=G.formato.parse(FECHAALTA);
        BAJA=G.formato.parse(G.FECHA(G.hoy(),false));
        difm = BAJA.getTime() - ALTA.getTime() ;
        dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);
        diasTrabajados= (int) dif;
        
        int dias_ano = 365;
        int dias_trabajados = this.diasTrabajados ++;
        int dias_prima_antiguedad = 12;
        double antiguedad = dias_trabajados / dias_ano;
        anos_completos = (int) antiguedad;
        int prima_antigudad = anos_completos * dias_prima_antiguedad;
        //double salario_diario = SUELDO / 7;
        salario_diario = Math.round(salario_diario * 100) / 100d;
        double salario_min_dup = salario_minimo * 2;
        
        //-----------------------------VERIFICAR EL AGUINALDO        
        //String fecha_proporcional=formateador.format(hoy);
        String fecha_proporcional = G.FECHA(G.hoy(),false);
        String []datos=fecha_proporcional.split("/");
        DiaD="01";
        MesD="01";
        AnoD="";
        AnoD=datos[2];
        fecha_proporcional = DiaD+"/"+MesD+"/"+AnoD;
        Date DIAS_AGUINALDO = G.formato.parse(fecha_proporcional);

        long dias_correspondientes_aguinaldo = (BAJA.getTime() - DIAS_AGUINALDO.getTime());
        dias_correspondientes_aguinaldo = TimeUnit.DAYS.convert(dias_correspondientes_aguinaldo, TimeUnit.MILLISECONDS);
        int dias_correspondientes_aguinaldo_entero = (int) dias_correspondientes_aguinaldo + 1;
        double dias_proporcionales_aguinaldo;
        if(dias_correspondientes_aguinaldo>350)
        {
            dias_proporcionales_aguinaldo = 15;
        }
        else
        {
            dias_proporcionales_aguinaldo = dias_correspondientes_aguinaldo_entero * 15 / 365;
            dias_proporcionales_aguinaldo = Math.round(dias_proporcionales_aguinaldo * 100) / 100d;
        }
        
        //------------------------------------VERIFICAR VACACIONES
        
        //limiteVacaciones();
        if(PERIODOV == PERIODOEV)
        {
            this.btnPAGARVACACIONES.setEnabled(true);
            
            ModeloVacacionesTAB mv;
            ControlVacacionesTAB cv = new ControlVacacionesTAB();        
            mv=cv.getRegistrosID(anos_completos);
            dias_correspondientes = mv.getDIAS();
            txtDIASCORRESPONDIENTES.setText(""+mv.getDIAS());

            int dias_predeterminados = Integer.parseInt(txtDIASPREDERTERMINADOS.getText());
            int dias_tomados = Integer.parseInt(txtDIASTOMADOS.getText());
            dias_pendientes = (dias_correspondientes - dias_predeterminados) - dias_tomados;
            txtDIASRESTANTES.setText(""+dias_pendientes);

            String fecha_proporcional_vacaciones = ""+G.formato.format(dtePINI.getDate());
            Date fpropovac = G.formato.parse(fecha_proporcional_vacaciones);
            long dias_correspondientes_vacaciones = BAJA.getTime() - fpropovac.getTime();
            dias_correspondientes_vacaciones = TimeUnit.DAYS.convert(dias_correspondientes_vacaciones, TimeUnit.MILLISECONDS);
            int dias_correspondientes_vacaciones_entero = (int) dias_correspondientes_vacaciones + 1;

            dias_proporcionales = ((dias_correspondientes_vacaciones_entero * dias_correspondientes)/365) + dias_pendientes ;
            dias_prima = dias_proporcionales * .25;
            primavaca = salario_diario * mv.getDIAS() * .25;
            //---------------------------------
            int primavacaI = (int) primavaca;
            primavaca = primavacaI;
            //---------------------------------
            primavaca = Math.round(primavaca * 100) / 100d;
            txtPRIMA.setText("$ "+G.numero.format(primavaca));
            //txtPRIMA.setText(""+primavaca);
            int res = (int) (dias_pendientes * salario_diario);
            
            this.txtMONTOV.setText("$ "+G.numero.format(res));
        }
            
        //--------------------------------------TOTAL DE LIQUIDACION
        double aguinaldo = salario_diario * dias_proporcionales_aguinaldo;
        aguinaldo = Math.round(aguinaldo * 100) / 100d;
        System.out.println("AGUINALDO "+aguinaldo);

        double vacaciones = salario_diario * dias_proporcionales;
        vacaciones = Math.round(vacaciones * 100) / 100d;
        //vacaciones = vacaciones * -1;
        System.out.println("VACACIONES "+vacaciones);
        
        double prima = salario_diario * dias_prima;
        System.out.println("PRIMA "+prima);
        
        tv = dias_pendientes * salario_diario;
        //--------------------------------
        int tvI = (int) tv;
        tv = tvI;
        //-------------------------------
        tv = tv+primavaca;
        this.txtTOTALV.setText("$ "+G.numero.format(tv));
        prima = (Math.round(prima * 100) / 100d);
        //prima = prima * -1;
        
        double prima_antiguedad = salario_diario * prima_antigudad;
        prima_antiguedad = Math.round(prima_antiguedad * 100) / 100d;        
        
        //double FINIQUITO = aguinaldo + vacaciones + prima + prima_antiguedad;
        double FINIQUITO = aguinaldo + vacaciones + prima;
        FINIQUITO = Math.round(FINIQUITO * 100) / 100d;
        FINIQUITO = FINIQUITO + total;
        txtLIQUIDACION.setText("$ "+G.numero.format(FINIQUITO));
        //txtLIQUIDACION.setText(""+FINIQUITO);        
        //lblAGUINALDO.setText(""+aguinaldo);
    }    
    
    public void verificarNOMINA(int IDEMPLEADO, int SEMANA, int ANO) throws ClassNotFoundException, SQLException, Exception
    {
        ControlNominas cn  = new ControlNominas(); 
       boolean existe = cn.existeNOMINA(IDEMPLEADO, SEMANA, ANO);
        if(existe==true)        
             JOptionPane.showMessageDialog( null, "YA SE HA CREADO UNA NOMINA DE ESTE EMPLEADO", "ERROR", JOptionPane.ERROR_MESSAGE);
        else        
            guardar("NUEVO");   
    }
    
    private void guardar(String ACCION) throws ClassNotFoundException, SQLException, Exception
    {        
        ModeloNominas mn = new ModeloNominas();
        mn.setIDNOMINA(IDNOMINA);
        mn.setIDEMPLEADO(IDEMPLEADO);
        mn.setFECHA(G.FECHA(G.hoy(),false));
        mn.setSEMANA(SEMANA);
        mn.setANO(ANO);
        mn.setDIAS(Float.parseFloat(txtDIAS.getText().trim()));
        mn.setNSUELDO(NSUELDO);
        mn.setBONO(EXTRA);
        mn.setCONCEPTO(txtCONCEPTO.getText());
        mn.setIDAHORRO(IDAHORROTH);
        mn.setIDABONO(IDABONOTA);
        mn.setTOTAL(TOTAL);
        
        switch(ACCION)
        {
            case "NUEVO":
                try
                {
                    IDNOMINA = F.Registro("NOMINAS", "IDNOMINA", "DESC");
                    mn.setIDNOMINA(IDNOMINA);
                    
                    ControlNominas cn = new ControlNominas();
                    cn.guardarRegistro(mn);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR GUARDAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "EDITAR":
                try
                {                
                    ControlNominas cn = new ControlNominas();
                    cn.actualizarRegistro(mn);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR ACTUALIZAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                }
            default:break;
        }
        guardarSALIDA(ACCION);
        guardarAHORRO(ACCION);
        guardarABONO(ACCION);                
        
        imp("NOMINAS");
    }
    
    private void guardarAHORRO(String ACCION) throws ClassNotFoundException, SQLException, Exception
    {
        if(AHORRO >= 1)
        {
            ModeloAhorros x = new ModeloAhorros();
            x.setIDAHORRO(IDAHORROTH);
            x.setIDEMPLEADO(IDEMPLEADO);
            x.setFECHA(G.FECHA(G.hoy(),false));
            x.setMONTO((float) AHORRO);
            x.setPERIODO(PERIODOB);
                       
            switch(ACCION)
            {
                case "NUEVO":
                    try
                    {
                        ControlAhorros cah = new ControlAhorros();
                        cah.guardarRegistro(x);
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR GUARDAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "EDITAR":
                    try
                    {                
                        ControlAhorros cah = new ControlAhorros();
                        cah.actualizarRegistro(x);
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR ACTUALIZAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                    }
                default:break;
            }
        }
        else        
            System.out.println("EL AHORRO ES MENOR A 0");
    }
    
    private void guardarABONO(String ACCION) throws ClassNotFoundException, SQLException, Exception
    {
        if(ABONO >= 1)
        {
            System.out.println("EL -ABONO- ES MAYOR A 0");
            
            ModeloAbonos x = new ModeloAbonos();
            x.setIDABONO(IDABONOTA);
            x.setIDEMPLEADO(IDEMPLEADO);
            x.setFECHA(G.FECHA(G.hoy(),false));
            x.setMONTO((float) ABONO);
            x.setPERIODO(PERIODOB);
            switch(ACCION)
            {
                case "NUEVO":
                    try
                    {
                        ControlAbonos cab = new ControlAbonos();
                        cab.guardarRegistro(x);
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR GUARDAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "EDITAR":
                    try
                    {                
                        ControlAbonos cab = new ControlAbonos();
                        cab.actualizarRegistro(x);
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR ACTUALIZAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                    }
                default:break;
            }
        }
        else        
            System.out.println("EL ABONO ES MENOR A 0");        
    }

    private void guardarSALIDA(String ACCION) throws SQLException, Exception
    {   
        if("DHN".equals(txtEMPRESA.getText()))
        {
            ModeloSalidas x = new ModeloSalidas();
            float montosalida = (float) (NSUELDO + EXTRA);
            x.setIDSALIDA(IDSALIDA);
            x.setDESCRIPCION("NOMINA DE " + txtNOMBRE.getText());
            x.setMONTO(montosalida);
            x.setCATEGORIA("SUELDOS");
            x.setMETODO("EFECTIVO");
            x.setFECHA(G.FECHA(G.hoy(),true));
            x.setIDCAJA(G.IDCAJA);

            switch(ACCION)
            {
                case "NUEVO":
                    try
                    {
                        IDSALIDA = F.Registro("SALIDAS", "IDSALIDA", "DESC");
                        x.setIDSALIDA(IDSALIDA);
                        
                        ControlSalidas cs = new ControlSalidas();
                        cs.guardarRegistro(x);
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR GUARDAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "EDITAR":
                    try
                    {                
                        ControlSalidas cs = new ControlSalidas();
                        cs.actualizarRegistro(x);
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR ACTUALIZAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                    }
                default:break;
            }
        }        
    }
    
    private void pagarAGUINALDO(String DOCUMENTO) throws ClassNotFoundException, SQLException, Exception
    {
        int idprestacion  = ControlPrestaciones.idprestacion("AGUINALDO");
        ModeloPrestacionesP mdlP = new ModeloPrestacionesP();
        IDPRESTACIONP = F.Registro("PRESTACIONESP","IDPRESTACIONP","DESC");
        //System.out.println("prestacion "+IDPRESTACIONP);
        mdlP.setIDPRESTACIONP(IDPRESTACIONP);
        mdlP.setIDEMPLEADO(IDEMPLEADO);
        mdlP.setIDPRESTACION(idprestacion);
        mdlP.setFREG(G.FECHA(G.hoy(),true));
        mdlP.setMONTO((float)AGUINALDO);
        mdlP.setPERIODO(PERIODOB);        
        
        ControlPrestacionesP ctrlP = new ControlPrestacionesP();
        ctrlP.guardarRegistro(mdlP);
        
        guardarSALIDAAGUINALDO();
        
        /*rutaWord = "C:\\DHN\\PRESTACIONES\\AGUINALDOS.docx";
        rutaExcel = "C:\\DHN\\PRESTACIONES\\AGUINALDOS.xlsx";
        rutaWordtmp = "C:\\DHN\\PRESTACIONES\\AGUINALDOStmp.docx";
        rutaPDF = "C:\\DHN\\PRESTACIONES\\AGUINALDOS.pdf";
        String[] titulosA = {"«FOLIO»", 
            "«FECHA»", 
            "«EMPRESA»", 
            "«NOMBRE»",
            "«AGUINALDO»"};
        String[] datos = {""+IDPRESTACIONP, 
            ""+formato.format(dte_fecha.getDate()), 
            txtEMPRESA.getText(), txtNOMBRE.getText(), 
            "$ "+totalF.format(AGUINALDO)};        
        Funciones.imprimir(titulosA,datos, rutaExcel, rutaWord, rutaWordtmp, rutaPDF);*/
        imp(DOCUMENTO);
    }
    
    private void guardarSALIDAAGUINALDO() throws SQLException, Exception
    {   
        IDSALIDA = F.Registro("SALIDAS", "IDSALIDA", "DESC");
        if("DHN".equals(txtEMPRESA.getText()))
        {
            ModeloSalidas x = new ModeloSalidas();
            x.setIDSALIDA(IDSALIDA);
            x.setDESCRIPCION("PAGO DE AGUINALDO DE " + txtNOMBRE.getText());
            x.setMONTO((float) AGUINALDO);
            x.setCATEGORIA("SUELDOS");
            x.setMETODO("EFECTIVO");
            x.setFECHA(G.FECHA(G.hoy(),false));
            x.setIDCAJA(G.IDCAJA);

            ControlSalidas cs = new ControlSalidas();
            cs.guardarRegistro(x);
        }        
    }

    
    
    /*private void pregunta() throws SQLException, Exception
    {
        Object[] inputFields = {"¿LIQUIDAR PRIMA VACACIONAL\n(Incluye el pago de dias restantes)"};

        int option = JOptionPane.showConfirmDialog(this, inputFields, "Pago de Vacaciones", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (option == JOptionPane.OK_OPTION)
        {
            pagarVACACIONES();
        }
    }*/
    
    /*private void preguntaA() throws SQLException, Exception
    {
        Object[] inputFields = {"PAGARAS AGUINALDOS?"};

        int option = JOptionPane.showConfirmDialog(this, inputFields, "Pago de Aguinaldos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (option == JOptionPane.OK_OPTION)
        {
            pagarAGUINALDO();
        }
    }*/
        
    private void opciones(String DOCUMENTO) throws SQLException, Exception
    {
        switch(DOCUMENTO)
            {
                case "NOMINAS":
                    verificarNOMINA(IDEMPLEADO,SEMANA,ANO);
                    break;
                case "AGUINALDO":
                    pagarAGUINALDO(DOCUMENTO);
                    break;
                case "BONO":
                    pagarBONOP(DOCUMENTO);
                    break;
                case "PRIMA":
                    pagarVACACIONES(DOCUMENTO);
                    break;     
                case "BONOP":
                    pagarBONOP(DOCUMENTO);
                    break;
                default: 
                    break;
            }
    }        
    
    private void pagarVACACIONES(String DOCUMENTO) throws ClassNotFoundException, SQLException, Exception
    {
        
        //REGISTRO POR PAGO DE DIAS PENDIENTES O RESTANTES
        int IDVACACION = F.Registro("VACACIONES","IDVACACION","DESC");
        ModeloVacaciones mdlV = new ModeloVacaciones();
        mdlV.setIDVACACION(IDVACACION);
        mdlV.setIDEMPLEADO(IDEMPLEADO);
        mdlV.setFREG(G.FECHA(G.hoy(),false));
        mdlV.setFSALIDA(G.FECHA(G.hoy(),false));
        mdlV.setFREGRESO(G.FECHA(G.hoy(),false));
        mdlV.setDIAST(dias_pendientes);
        mdlV.setCOMENTARIOS("PAGADOS POR DIAS RESTANTES: "+dias_pendientes);
        mdlV.setPERIODO(PERIODOV);
        
        ControlVacaciones ctrlV = new ControlVacaciones();
        ctrlV.guardarRegistro(mdlV);
        
        //REGISTRO A PRESTACIONES PAGADAS
        int idprestacion  = ControlPrestaciones.idprestacion("PRIMA VACACIONAL");
        IDPRESTACIONP = F.Registro("PRESTACIONESP","IDPRESTACIONP","DESC");
        ModeloPrestacionesP mdlPP = new ModeloPrestacionesP();
        mdlPP.setIDPRESTACIONP(IDPRESTACIONP);
        mdlPP.setIDEMPLEADO(IDEMPLEADO);
        mdlPP.setIDPRESTACION(idprestacion);
        mdlPP.setFREG(G.FECHA(G.hoy(),false));
        mdlPP.setMONTO((float) primavaca);
        mdlPP.setPERIODO(PERIODOV);
        
        ControlPrestacionesP ctrlCP = new ControlPrestacionesP();
        ctrlCP.guardarRegistro(mdlPP);
        
        //SALIDA SI ES DE LA EMPRESA DHN}
        IDSALIDA = F.Registro("SALIDAS", "IDSALIDA", "DESC");
        if("DHN".equals(txtEMPRESA.getText()))
        {
            ModeloSalidas x = new ModeloSalidas();
            x.setIDSALIDA(IDSALIDA);
            //CORREGIR LA VARIABLE PRESTACION TV
            x.setDESCRIPCION("PAGO DE VACACIONES DE " + txtNOMBRE.getText()+",  DIAS RESTANTES: "+dias_pendientes+" = "+txtMONTOV.getText()+", PRIMA VACACIONAL: "+txtPRIMA.getText()+" = "+txtTOTALV.getText()+", DEL PRIODO: "+"PERIODOVT");
            x.setMONTO((float) tv);
            x.setCATEGORIA("SUELDOS");
            x.setMETODO("EFECTIVO");
            x.setFECHA(G.FECHA(G.hoy(),false));
            x.setIDCAJA(G.IDCAJA);

            ControlSalidas cs = new ControlSalidas();
            cs.guardarRegistro(x);
        }
        
        //MODIFICA EL EDETALLE
        ModeloEDetalles mdlED = new ModeloEDetalles();
        
        int a;
        String []datos=PINI.split("/");
       
        DiaD=datos[0];
        MesD=datos[1];
        AnoD=datos[2];
        a = Integer.parseInt(AnoD)+1;        
        PINI = DiaD+"/"+MesD+"/"+a;
        
        String []datos0=PFIN.split("/");
        DiaD=datos0[0];
        MesD=datos0[1];
        AnoD=datos0[2];
        a = Integer.parseInt(AnoD)+1;
        PFIN = DiaD+"/"+MesD+"/"+a;
        
        mdlED.setIDEMPLEADO(IDEMPLEADO);
        mdlED.setIDEDETALLE(IDEMPLEADO);
        mdlED.setPINI(PINI);
        mdlED.setPFIN(PFIN);
        mdlED.setVPAGADAS(false);
        mdlED.setAPAGADOS(false);
        mdlED.setPERIODOV(PERIODOEV+1);
        
        ControlEDetalles ctrlED = new ControlEDetalles();
        ctrlED.actualizarRegistro(mdlED);
        
        imp(DOCUMENTO);        
    }  
        
    private void pagarBONOP(String DOCUMENTO) throws ClassNotFoundException, SQLException, Exception
    {
        String EMPRESA = txtEMPRESA.getText();
        String CONCEPTO = "";
        float MONTO = 0;          
        switch(DOCUMENTO)
        {
            case "AGUINALDO":
                CONCEPTO = "AGUINALDO"; 
                MONTO = Float.parseFloat(lblAGUINALDO.getText().replaceAll("[$ ,]", ""));
                break;
            case "BONO":
                CONCEPTO = "BONO DE CAJA"; 
                MONTO = Float.parseFloat(txtBONOT.getText().replaceAll("[$ ,]", ""));
                SALDO = Float.parseFloat(txtSALDO.getText().replaceAll("[$ ,]", ""));
                //MONTO = MONTO + SALDO;
                montoB = MONTO + SALDO;
                break;
            case "BONOP":
                CONCEPTO = "BONO DE PRODUCTIVIDAD";
                MONTO = Float.parseFloat(txtBONOTP.getText().replaceAll("[$ ,]", ""));
                TOTALBP=MONTO;
                break;
            /*case "VACACION":
                CONCEPTO = "PRIMA VACACIONAL";
                MONTO = Float.parseFloat(txtTOTALV.getText().replaceAll("[$ ,]", ""));
                break;*/
            default:
                break;
        }
        //System.out.print("PASO 0 GENERAR SALIDA DE CAJA");
        salidaC(CONCEPTO,EMPRESA,MONTO);
        
        //System.out.print("PASO 1 SI ES DHN GENERA ENTRDA Y SALIDA");
        if(EMPRESA.equals("DHN"))
        {
            entrada(CONCEPTO,MONTO);
            salida(CONCEPTO,MONTO);
        }
        
        periodo();        
        
        IDPRESTACIONP = F.Registro("PRESTACIONESP","IDPRESTACIONP","DESC");        
        
        ModeloPrestaciones mdl;
        ControlPrestaciones ctrl = new ControlPrestaciones();
        mdl = ctrl.Registros(CONCEPTO, "DESC");
        int idprestacion = mdl.getIDPRESTACION();
        
        ModeloPrestacionesP mdlP = new ModeloPrestacionesP();           
        mdlP.setIDPRESTACIONP(IDPRESTACIONP);
        mdlP.setIDEMPLEADO(IDEMPLEADO);
        mdlP.setIDPRESTACION(idprestacion);
        mdlP.setFREG(G.FECHA(G.hoy(),false));
        mdlP.setMONTO(MONTO);
        mdlP.setPERIODO(PERIODOB);
        
        System.out.print("PASO 2 GENERAR PRESTACIONES");        
        ControlPrestacionesP ctrlP = new ControlPrestacionesP();
        ctrlP.guardarRegistro(mdlP);
                
        //System.out.print("PASO 3 IMP PAGO");
        imp(DOCUMENTO);
        
        //System.out.print("PASO 4 GEN SALIDA");
        if(DOCUMENTO.equals("BONO"))
        {
            if(SALDO>0)
                salidaC(CONCEPTO,"CAJA DE AHORRO",SALDO);
            else
                guardarPRESTAMO();
        }
        
        
        //System.out.print("PASO 5 IMP NUEVA CARTA EN CASO DE SER NECESARIO");
        if(DOCUMENTO.equals("BONO")||DOCUMENTO.equals("BONOP"))
            pregunta(DOCUMENTO);
        
    }
    
    private void salidaC(String CONCEPTO,String EMPRESA,float MONTO) throws SQLException, Exception
    {
        int IDSALIDAC;
        IDSALIDAC = F.Registro("SALIDASCAJA","IDSALIDACAJA","DESC");
        ModeloSalidasCaja md = new ModeloSalidasCaja();

        md.setIDSALIDACAJA(IDSALIDAC);
        md.setDESCRIPCION("RETIRO DE APORTACIONES DE CAJA POR "+CONCEPTO+" DEL EMPLEADO N. "+IDEMPLEADO+".- "+txtNOMBRE.getText());
        md.setMONTO(MONTO);
        md.setEMPRESA(EMPRESA);
        md.setFECHA(G.FECHA(G.hoy(),false));
        //System.out.println("GUARDANDO SALIDA "+IDSALIDAC);
        ControlSalidasCaja cp = new ControlSalidasCaja();
        cp.guardarRegistro(md);
        
    }
    
    private void entrada(String CONCEPTO, float MONTO) throws SQLException, Exception
    {        
        int identradas = F.Registro("ENTRADAS","IDENTRADA","DESC");
        ModeloEntradas md = new ModeloEntradas();
        
        md.setIDENTRADA(identradas);
        md.setDESCRIPCION("INGRESO A CAJA POR CONCEPTO: "+CONCEPTO+" DEL EMPLEADO N. "+IDEMPLEADO+".- "+txtNOMBRE.getText());
        md.setMONTO(MONTO);
        md.setCATEGORIA("GENERAL");
        md.setMETODO("EFECTIVO");
        //md.setFECHA(G.formato.format(dte_fecha.getDate()));
        md.setFECHA(G.FECHA(dteFECHA.getDate(),true));
        md.setIDCAJA(IDCAJA);    

        ControlEntradas cp = new ControlEntradas();
        cp.guardarRegistro(md);
    }
    
    private void salida(String CONCEPTO,float MONTO) throws ClassNotFoundException, SQLException, Exception
    {
        int idsalidas = F.Registro("SALIDAS","IDSALIDA","DESC");
        ModeloSalidas x = new ModeloSalidas();
        x.setIDSALIDA(idsalidas);
        x.setDESCRIPCION("PAGO DE "+CONCEPTO+" DEL EMPLEADO N. "+IDEMPLEADO+".- "+txtNOMBRE.getText());
        x.setMONTO(MONTO);
        x.setCATEGORIA("SUELDOS");
        x.setMETODO("EFECTIVO");
        x.setFECHA(G.FECHA(G.hoy(),true));
        x.setIDCAJA(G.IDCAJA);

        ControlSalidas cs = new ControlSalidas();
        cs.guardarRegistro(x);
    }    
    
    private void guardarPRESTAMO() throws SQLException, Exception
    {
        int IDPRESTAMO = F.Registro("PRESTAMOS", "IDPRESTAMO", "DESC");
        ModeloPrestamos md = new ModeloPrestamos();
        
        md.setIDPRESTAMO(IDPRESTAMO);
        md.setDESCRIPCION("RESIDUO DE SALDO DEL PEDIODO N. "+PERIODOB);
        md.setMONTO(montoB);
        md.setIDEMPLEADO(IDEMPLEADO);
        md.setFECHA(G.FECHA(G.hoy(),false));
        md.setPERIODO(PERIODOB+1);
        ControlPrestamos cp = new ControlPrestamos();
        cp.guardarRegistro(md);
    }
    
    private void pregunta(String DOCUMENTO) throws SQLException, Exception
    {
        Object[] inputFields = {"¿DESEAS IMPRIMIR LA NUEVA CARTA DE BONO?\n"};

        int option = JOptionPane.showConfirmDialog(this, inputFields, "Imprimir carta nueva", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (option == JOptionPane.OK_OPTION)   
            if(DOCUMENTO.equals("BONO"))
                imp("CARTABAHO");
            else
                imp("CARTABPROD");                    
    }
    
    private void activar(boolean ACCION)
    {
        
        this.btnPAGARAGUINALDOS.setEnabled(ACCION);
        this.btnPAGARVACACIONES.setEnabled(ACCION);
        this.btnPAGARBONO.setEnabled(ACCION);      
        this.btnFINIQUITO.setEnabled(ACCION);         
        this.btnPAGARBONOP.setEnabled(ACCION);
        
        this.txtF1R1.setEnabled(ACCION);
        this.txtF1R2.setEnabled(ACCION);
        this.txtF2R1.setEnabled(ACCION);
        this.txtF2R2.setEnabled(ACCION);
        this.txtF3R1.setEnabled(ACCION);
        this.txtF3R2.setEnabled(ACCION);
        this.txtF4R1.setEnabled(ACCION);
        this.txtF4R2.setEnabled(ACCION);
        this.txtF5R1.setEnabled(ACCION);
        this.txtF5R2.setEnabled(ACCION);
        this.txtF6R1.setEnabled(ACCION);

    }
    
    private void  pagarFINIQUITO() throws ParseException, SQLException, Exception
    {
        /*RegFiniquito vista = null;            
        vista = new RegFiniquito(null,true);

        int ultimo = ControlFiniquito.Registro("DESC");
        vista.mostrar("NUEVO",ultimo,IDEMPLEADO);
        vista.setVisible(true);*/
    }
    
    private void activaCMB(boolean ACCION, String CARGO)
    {
        this.txtF1R1.setEnabled(ACCION);        
        this.txtF2R1.setEnabled(ACCION);       
        this.txtF3R1.setEnabled(ACCION);        
        this.txtF4R1.setEnabled(ACCION);
        this.txtF5R1.setEnabled(ACCION);
        if(CARGO.equals("ADMINISTRATIVO"))
        {
            this.txtF1R2.setEnabled(ACCION);
            this.txtF2R2.setEnabled(ACCION);
            this.txtF3R2.setEnabled(ACCION);
            this.txtF4R2.setEnabled(ACCION);       
            this.txtF5R2.setEnabled(ACCION);
            this.txtF6R1.setEnabled(ACCION);
        }
    }
    
    private void iniciarTabla()
    {                
        tableModel = new DefaultTableModel(new Object[]{"Fecha/Hora", "Empleado", "Nombre"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel2 = new DefaultTableModel(new Object[]{"Fecha", "Día", "Entrada", "Estado Entrada", "Salida Comida", 
                               "Regreso Comida", "Estado Regreso", "Salida Jornada"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
    }
    
    private void checador()
    {
        if(Principal.PRIVILEGIOS.equals("SADMIN")||Principal.PRIVILEGIOS.equals("ADMIN"))
        {
            if(DEPTO.equals("ADMINISTRATIVO")||DEPTO.equals("OPERATIVO"))
            {
                downloadEvents();
                //clearTable();        
                llenar(tableModel2);
                actualizarTabla(IDEMPLEADO, DEPTO);
                tblTABLA.setModel(tableModel);        

                ordenarAsistencias(tblTABLA, tableModel, DEPTO);     

                if(DEPTO.equals("OPERATIVO"))
                {
                    TableColumnModel tcm = tblTABLA.getColumnModel();
                    tcm.removeColumn(tcm.getColumn(6));
                    tcm.removeColumn(tcm.getColumn(5));
                    tcm.removeColumn(tcm.getColumn(4));
                }

                CMBAsistencias(tblTABLA,DEPTO);
                activaCMB(true,DEPTO);
                contarCMB(DEPTO);
            }
            
        }
        
    }
    
    private void downloadEvents() 
    {    
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String SINI = formato.format(dteSINI.getDate());
        String SFIN = formato.format(dteSFIN.getDate());
        SINI = SINI+" 00:00:00";
        SFIN = SFIN+" 23:59:59";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date startDate = sdf.parse(SINI);
            Date endDate = sdf.parse(SFIN);
            int maxResults = 0;

            // Limpiar tabla
            //clearTable();

            // Obtener eventos
            AcsEventResponse responseini = hikvisionClient.getAccessEvents(startDate, endDate, maxResults);
            int nveces = (int) responseini.getAcsEvent().getTotalMatches()/ responseini.getAcsEvent().getNumOfMatches();
            nveces++;
            int index = 0;

            for(int i=0; i<nveces; i++)
            {
                AcsEventResponse response =  hikvisionClient.getAccessEvents(startDate, endDate, index);
                // Actualizar tabla en EDT
                    if (response != null && response.getAcsEvent() != null) {
                        try {
                            updateTable(response.getAcsEvent().getInfoList(), IDEMPLEADO);
                        } catch (ParseException ex) {
                            
                        }
                    }
                index = index+response.getAcsEvent().getNumOfMatches();
            }    

        } catch (Exception ex) {                      
                ex.printStackTrace();
        }   
    }    
    
    private void updateTable(List<EventInfo> events, int NoE) throws ParseException {
        if (events == null) return;
        
        SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        for (EventInfo event : events) {
            Object[] row = new Object[3];          
            
            if(event.getEmployeeNo()!=null)
            {
                int No = Integer.parseInt(event.getEmployeeNo());               
                if(No==NoE)
                {
                    // Fecha/Hora
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        Date eventDate = sdf.parse(event.getTime().substring(0, 19));
                        row[0] = displayFormat.format(eventDate);
                    } catch (ParseException e) {
                        row[0] = event.getTime();
                    }
                                                
                    // Empleado
                    row[1] = event.getEmployeeNo() != null ? event.getEmployeeNo() : "N/A";

                    // Nombre
                    row[2] = event.getName() != null ? event.getName() : "N/A";

                    tableModel.addRow(row);   
                    tableModel2.addRow(new Object[]{
                        row[0],
                        row[1],
                        row[2]
                    });
                }                
            }            
        }
    }
    
    private void llenar(DefaultTableModel mdl)
    {
        Registro registro;           
        for(int i=0; i<mdl.getRowCount(); i++)
        {            
            registro = new Registro(mdl.getValueAt(i, 1).toString(),
                    mdl.getValueAt(i, 2).toString(),mdl.getValueAt(i, 0).toString());
            registros.add(registro);
        }        
    }
        
    private void actualizarTabla(int NoE, String Horario) {
        tableModel.setRowCount(0);
        tableModel = new DefaultTableModel(new Object[]{"Fecha", "Día", "Entrada", "Estado Entrada", "Salida Comida", 
                               "Regreso Comida", "Estado Regreso", "Salida Jornada"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String numeroEmpleado = ""+NoE;
        String horario = Horario;

        if (numeroEmpleado.isEmpty()) {
            return;
        }

        // Filtrar registros por empleado
        List<Registro> registrosEmpleado = new ArrayList<>();
        for (Registro r : registros) {
            if (r.numEmpleado.equals(numeroEmpleado)) {
                registrosEmpleado.add(r);
            }
        }

        // Agrupar por fecha
        Map<String, List<Registro>> registrosPorFecha = new HashMap<>();
        for (Registro r : registrosEmpleado) {
            String fecha = r.fecha;
            registrosPorFecha.putIfAbsent(fecha, new ArrayList<>());
            registrosPorFecha.get(fecha).add(r);
        }

        // Procesar cada fecha
        for (Map.Entry<String, List<Registro>> entry : registrosPorFecha.entrySet()) {
            String fecha = entry.getKey();
            List<Registro> registrosDia = entry.getValue();
            
            // Ordenar registros por hora
            registrosDia.sort(Comparator.comparing(r -> r.hora));
            
            if (horario.equals("ADMINISTRATIVO")) {
                procesarHorarioAdministrativo(fecha, registrosDia);
            } else {
                procesarHorarioOperativo(fecha, registrosDia);
            }
        }
    }

    private void procesarHorarioAdministrativo(String fecha, List<Registro> registros) {
        String diaSemana = obtenerDiaSemana(fecha);
        
        if (diaSemana.equals("DOMINGO")) {
            return;
        }

        LocalTime entrada = null;
        LocalTime salidaComida = null;
        LocalTime regresoComida = null;
        LocalTime salidaJornada = null;

        for (Registro r : registros) {
            LocalTime hora = r.hora;
            
            if (entrada == null && hora.isBefore(LocalTime.of(14, 0))) {
                entrada = hora;
            }
            else if (hora.isAfter(LocalTime.of(13, 59)) && hora.isBefore(LocalTime.of(14, 31))) {
                salidaComida = hora;
            }
            else if (hora.isAfter(LocalTime.of(15, 59)) && hora.isBefore(LocalTime.of(19, 30))) {
                if (regresoComida == null || hora.isBefore(regresoComida)) {
                    regresoComida = hora;
                }
            }
            else if (hora.isAfter(LocalTime.of(19, 29))) {
                salidaJornada = hora;
            }
        }

        // Evaluar entradas y regresos
        String estadoEntrada = evaluarEntradaAdministrativa(entrada, diaSemana);
        String estadoRegreso = evaluarRegresoComida(regresoComida);

        agregarFilaTabla(fecha, diaSemana, entrada, estadoEntrada, salidaComida, 
                        regresoComida, estadoRegreso, salidaJornada);
    }

    private void procesarHorarioOperativo(String fecha, List<Registro> registros) {
        String diaSemana = obtenerDiaSemana(fecha);
        
        if (diaSemana.equals("SABADO") || diaSemana.equals("DOMINGO")) {
            return;
        }

        LocalTime entrada = null;
        LocalTime salidaJornada = null;

        for (Registro r : registros) {
            LocalTime hora = r.hora;
            
            if (entrada == null) {
                entrada = hora;
            }
            salidaJornada = hora;
        }

        String estadoEntrada = evaluarEntradaOperativa(entrada);
        
        agregarFilaTabla(fecha, diaSemana, entrada, estadoEntrada, null, null, null, salidaJornada);
    }

    private String evaluarEntradaAdministrativa(LocalTime entrada, String diaSemana) {
        if (entrada == null) return "FALTA";
        
        if (diaSemana.equals("SABADO")) {
            if (entrada.isBefore(LocalTime.of(9, 1))) return "PUNTUAL";
            if (entrada.isBefore(LocalTime.of(9, 11))) return "RETARDO";
            return "FALTA";
        } else {
            if (entrada.isBefore(LocalTime.of(9, 1))) return "PUNTUAL";
            if (entrada.isBefore(LocalTime.of(9, 11))) return "RETARDO";
            return "FALTA";
        }
    }

    private String evaluarRegresoComida(LocalTime regreso) {
        if (regreso == null) return "FALTA";
        if (regreso.isBefore(LocalTime.of(16, 11))) return "PUNTUAL";
        return "RETARDO";
    }

    private String evaluarEntradaOperativa(LocalTime entrada) {
        if (entrada == null) return "FALTA";
        if (entrada.isBefore(LocalTime.of(8, 31))) return "PUNTUAL";
        if (entrada.isBefore(LocalTime.of(8, 41))) return "RETARDO";
        return "FALTA";
    }

    private String obtenerDiaSemana(String fecha) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(fecha);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            
            String[] dias = {"DOMINGO", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO"};
            return dias[cal.get(Calendar.DAY_OF_WEEK) - 1];
        } catch (ParseException e) {
            return "ERROR";
        }
    }
    
    private void agregarFilaTabla(String fecha, String dia, LocalTime entrada, String estadoEntrada,
                                 LocalTime salidaComida, LocalTime regresoComida, String estadoRegreso,
                                 LocalTime salidaJornada) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        tableModel.addRow(new Object[]{
            fecha,
            dia,
            entrada != null ? entrada.format(formatter) : "--:--:--",
            estadoEntrada,
            salidaComida != null ? salidaComida.format(formatter) : "--:--:--",
            regresoComida != null ? regresoComida.format(formatter) : "--:--:--",
            estadoRegreso != null ? estadoRegreso : "--",
            salidaJornada != null ? salidaJornada.format(formatter) : "--:--:--"
        });        
    }
    
    private void ordenarAsistencias(JTable tabla, DefaultTableModel mdl, String CARGO)
    {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(mdl);
        tabla.setRowSorter(sorter);
        // 3. Crear una lista de SortKey para la columna 0 (ascendente)
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        // 4. Asignar las claves de ordenación al sorter
        sorter.setSortKeys(sortKeys);
        //Renderizar tabla por colores
        tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderCelda(true,true,"ASISTENCIAS") );   
        if(CARGO.equals("ADMINISTRATIVO"))
            tabla.getColumnModel().getColumn(6).setCellRenderer(new RenderCelda(true,true,"ASISTENCIAS") );
    }
    
    private void CMBAsistencias(JTable tabla,String CARGO)
    {
        String aux = null;
        for(int i=0; i<tabla.getRowCount(); i++)
        {
            if(tabla.getValueAt(i, 1)!=null)
            {
                aux = tabla.getValueAt(i, 1).toString();
                switch(aux)
                {
                    case "LUNES":
                        this.txtF1R1.setSelectedItem(tabla.getValueAt(i, 3));                        
                        break;
                    case "MARTES":
                        this.txtF2R1.setSelectedItem(tabla.getValueAt(i, 3));
                        break;
                    case "MIERCOLES":
                        this.txtF3R1.setSelectedItem(tabla.getValueAt(i, 3));
                        break;
                    case "JUEVES":
                        this.txtF4R1.setSelectedItem(tabla.getValueAt(i, 3));
                        break;
                    case "VIERNES":
                        this.txtF5R1.setSelectedItem(tabla.getValueAt(i, 3));
                        break;    
                    default:
                        break;
                }
            }            
        }
        
        switch(CARGO)
        {
            case "ADMINISTRATIVO":   
                for(int i=0; i<tabla.getRowCount(); i++)
                {
                    if(tabla.getValueAt(i, 1)!=null)
                    {
                        aux = tabla.getValueAt(i, 1).toString();
                        switch(aux)
                        {
                            case "LUNES":
                                this.txtF1R2.setSelectedItem(tabla.getValueAt(i, 6));
                                break;
                            case "MARTES":
                                this.txtF2R2.setSelectedItem(tabla.getValueAt(i, 6));
                                break;
                            case "MIERCOLES":
                                this.txtF3R2.setSelectedItem(tabla.getValueAt(i, 6));
                                break;
                            case "JUEVES":
                                this.txtF4R2.setSelectedItem(tabla.getValueAt(i, 6));
                                break;
                            case "VIERNES":
                                this.txtF5R1.setSelectedItem(tabla.getValueAt(i, 6));
                                break;    
                            case "SABADO":
                                this.txtF6R1.setSelectedItem(tabla.getValueAt(i, 6));
                                break;        
                            default:
                                break;
                        }
                    }
                }       
                break;
            case "OPERATIVO":
                this.txtF1R2.setVisible(false);
                this.txtF2R2.setVisible(false);
                this.txtF3R2.setVisible(false);
                this.txtF4R2.setVisible(false);
                this.txtF5R2.setVisible(false);
                break;
            default:
                break;
        }   
        
    }
    
    private void contarCMB(String CARGO)
    {   
        if(DEPTO.equals("ADMINISTRATIVO")||DEPTO.equals("OPERATIVO"))
        {
            f=0; r=0; rj=0; psgs=0; pcgs=0;
        
            onetoone(txtF1R1.getSelectedItem().toString());
            onetoone(txtF2R1.getSelectedItem().toString());
            onetoone(txtF3R1.getSelectedItem().toString());
            onetoone(txtF4R1.getSelectedItem().toString());
            onetoone(txtF5R1.getSelectedItem().toString());

            if(CARGO.equals("ADMINISTRATIVO"))
            {
                onetoone(txtF1R1.getSelectedItem().toString());
                onetoone(txtF2R2.getSelectedItem().toString());
                onetoone(txtF3R2.getSelectedItem().toString());
                onetoone(txtF4R2.getSelectedItem().toString());
                onetoone(txtF5R2.getSelectedItem().toString());        
                onetoone(txtF6R1.getSelectedItem().toString());
            }
            this.lblF.setText(""+f);
            this.lblR.setText(""+r);
            this.lblRJ.setText(""+rj);
            this.lblPC.setText(""+pcgs);
            this.lblPS.setText(""+psgs);

            int nfadmin=0;
            if(r>3)        
                f = f + (r/3);        
                //f++;
            if(f>3)
                nfadmin = nfadmin + (f/3);
            this.lblFA.setText(""+nfadmin);
        }
        
    }
    
    private void onetoone(String item)
    {
        switch(item)
        {
            case "FALTA":
                f++;
                break;
            case "RETARDO":
                r++;
                break;
            case "RETARDO JUSTIFICADO":
                rj++;
                break;
            case "FALTA JUSTIFICADA":
                psgs++;
                break;
            case "PERMISO SGS":
                psgs++;
                break;
            case "PERMISO CGS":
                pcgs++;
                break;    
            default:
                break;
        }
    }
    
    private void defaulCMB(JComboBox CMB)
    {        
        CMB.removeAllItems();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("PUNTUAL");        
        model.addElement("RETARDO");        
        model.addElement("RETARDO JUSTIFICADO");
        model.addElement("FALTA");
        model.addElement("FALTA JUSTIFICADA");   
        model.addElement("PERMISO CGS");        
        model.addElement("PERMISO SGS");        
        CMB.setModel(model);
        CMB.setSelectedIndex(3);
    }
    
    private void allCMB()
    {
        defaulCMB(txtF1R1);
        defaulCMB(txtF1R2);
        defaulCMB(txtF2R1);
        defaulCMB(txtF2R2);
        defaulCMB(txtF3R1);
        defaulCMB(txtF3R2);
        defaulCMB(txtF4R1);
        defaulCMB(txtF4R2);
        defaulCMB(txtF5R1);
        defaulCMB(txtF5R2);
        defaulCMB(txtF6R1);
        
    }
    
    private void fechasAsistencias() throws ParseException
    {
        String []datos2 = G.FECHA(G.hoy(),false).split("/");

        int anio=Integer.parseInt(datos2[2]);
        
        LocalDate lunes = LocalDate.of(anio, 1, 1) // Fecha inicial
            .with(TemporalAdjusters.firstDayOfYear()) // Primer día del año
            .with(ChronoField.ALIGNED_WEEK_OF_YEAR, this.SEMANA) // Semana deseada
            .with(DayOfWeek.MONDAY); // Asegurarse de que sea el lunes

        LocalDate domingo = lunes.plusDays(6); // Sumar 6 días para obtener el domingo

        String Lunes = lunes.toString();//replaceAll("-", "/");
        String Domingo = domingo.toString();//.replaceAll("-", "/");
        
        String []dar = Lunes.split("-");     
        int d = Integer.parseInt(dar[2]);
        int m = Integer.parseInt(dar[1]);
        int a = Integer.parseInt(dar[0]);       
        dteSINI.setDate(G.formato.parse(d+"/"+m+"/"+a));
        
        String []dar1 = Domingo.split("-");     
        d = Integer.parseInt(dar1[2]);
        m = Integer.parseInt(dar1[1]);
        a = Integer.parseInt(dar1[0]);
        dteSFIN.setDate(G.formato.parse(d+"/"+m+"/"+a));

    }
    
    private void imprimir() throws ClassNotFoundException, SQLException
    {
        float TOTALILLO = (float)BONO + SALDO;
        ImpResumenBono pca = new ImpResumenBono(""+G.formato.format(dteFECHA.getDate()),txtIDEMPLEADO.getText(),txtNOMBRE.getText(),
                txtSAHORROS.getText(),"- "+txtSPRESTAMOS.getText(), txtSABONOS.getText(), txtSALDO.getText(),txtBONOT.getText(),"$ "+G.numero.format(TOTALILLO));
        JButton[] opciones = pca.getButtons();
        JOptionPane.showInternalOptionDialog(this, pca, "Bono acomulado",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
        if(pca.getOpcion() != null)
            pca.Imprimir();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMETODO = new javax.swing.JTextField();
        jpnCONTENEDOR = new javax.swing.JPanel();
        txtNOMBRE = new javax.swing.JTextField();
        txtDIAS = new javax.swing.JTextField();
        txtIDEMPLEADO = new javax.swing.JTextField();
        txtSUELDO = new javax.swing.JTextField();
        Bar = new javax.swing.JToolBar();
        btnCALCULAR = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        tgbtnSADMIN = new javax.swing.JToggleButton();
        btnVACACIONES = new javax.swing.JToggleButton();
        dteFECHA = new com.toedter.calendar.JDateChooser();
        txtEMPRESA = new javax.swing.JTextField();
        txtBONO = new javax.swing.JTextField();
        txtCONCEPTO = new javax.swing.JTextField();
        txtAHORRO = new javax.swing.JTextField();
        txtABONO = new javax.swing.JTextField();
        txtTOTAL = new javax.swing.JTextField();
        jpVACACIONES = new javax.swing.JPanel();
        txtDIASCORRESPONDIENTES = new javax.swing.JTextField();
        txtDIASTOMADOS = new javax.swing.JTextField();
        txtDIASPREDERTERMINADOS = new javax.swing.JTextField();
        txtPRIMA = new javax.swing.JTextField();
        txtDIASRESTANTES = new javax.swing.JTextField();
        btnPAGARVACACIONES = new javax.swing.JButton();
        txtMONTOV = new javax.swing.JTextField();
        txtTOTALV = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        dtePFIN = new com.toedter.calendar.JDateChooser();
        dtePINI = new com.toedter.calendar.JDateChooser();
        txtNSUELDO = new javax.swing.JTextField();
        txtFECHAALTA = new javax.swing.JTextField();
        txtSEMANA = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        lblFECHAS = new javax.swing.JPanel();
        txtF1R1 = new javax.swing.JComboBox<>();
        txtF1R2 = new javax.swing.JComboBox<>();
        txtF2R1 = new javax.swing.JComboBox<>();
        txtF2R2 = new javax.swing.JComboBox<>();
        txtF3R1 = new javax.swing.JComboBox<>();
        txtF3R2 = new javax.swing.JComboBox<>();
        txtF4R1 = new javax.swing.JComboBox<>();
        txtF4R2 = new javax.swing.JComboBox<>();
        txtF5R1 = new javax.swing.JComboBox<>();
        txtF5R2 = new javax.swing.JComboBox<>();
        txtF6R1 = new javax.swing.JComboBox<>();
        jspTABLA = new javax.swing.JScrollPane();
        tblTABLA = new javax.swing.JTable();
        dteSINI = new com.toedter.calendar.JDateChooser();
        dteSFIN = new com.toedter.calendar.JDateChooser();
        pnlRESUMEN = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lblF = new javax.swing.JLabel();
        lblFJ = new javax.swing.JLabel();
        lblRJ = new javax.swing.JLabel();
        lblR = new javax.swing.JLabel();
        lblPS = new javax.swing.JLabel();
        lblPC = new javax.swing.JLabel();
        lblFA = new javax.swing.JLabel();
        lblDEPTO = new javax.swing.JLabel();
        txtTOTAL1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jspPRESTAMOS = new javax.swing.JScrollPane();
        jtblPRESTAMOS = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtSALDO = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jspABONOS = new javax.swing.JScrollPane();
        jtblABONOS = new javax.swing.JTable();
        jspAHORROS = new javax.swing.JScrollPane();
        jtblAHORROS = new javax.swing.JTable();
        txtSPRESTAMOS = new javax.swing.JTextField();
        txtSABONOS = new javax.swing.JTextField();
        txtSAHORROS = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnPAGARAGUINALDOS = new javax.swing.JButton();
        lblAGUINALDO = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnPAGARBONO = new javax.swing.JButton();
        txtBONOT = new javax.swing.JTextField();
        txtBONOTP = new javax.swing.JTextField();
        btnPAGARBONOP = new javax.swing.JButton();
        jpEXTRAS = new javax.swing.JPanel();
        txtLIQUIDACION = new javax.swing.JTextField();
        txtMAXIMO = new javax.swing.JTextField();
        btnFINIQUITO = new javax.swing.JButton();

        txtMETODO.setText("jTextField3");

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setClosable(true);
        setMaximizable(true);
        setTitle("Registro de Nomina");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nomina.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1100, 750));

        jpnCONTENEDOR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Nomina", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtNOMBRE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNOMBRE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Empleado:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtNOMBRE.setEnabled(false);

        txtDIAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDIAS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDIAS.setText("7");
        txtDIAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dias Trabajados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtDIAS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDIASKeyTyped(evt);
            }
        });

        txtIDEMPLEADO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIDEMPLEADO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDEMPLEADO.setToolTipText("Numero de Empleado");
        txtIDEMPLEADO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtIDEMPLEADO.setEnabled(false);

        txtSUELDO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSUELDO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSUELDO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sueldo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtSUELDO.setEnabled(false);

        btnCALCULAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCALCULAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calcular.png"))); // NOI18N
        btnCALCULAR.setText("CALCULAR");
        btnCALCULAR.setFocusable(false);
        btnCALCULAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCALCULAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCALCULAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCALCULARActionPerformed(evt);
            }
        });
        Bar.add(btnCALCULAR);

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disk.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setEnabled(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        Bar.add(btnGuardar);

        tgbtnSADMIN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tgbtnSADMIN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pass.png"))); // NOI18N
        tgbtnSADMIN.setText("DESBLOQUEO");
        tgbtnSADMIN.setToolTipText("PRIVILEGIOS");
        tgbtnSADMIN.setFocusable(false);
        tgbtnSADMIN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tgbtnSADMIN.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tgbtnSADMIN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgbtnSADMINItemStateChanged(evt);
            }
        });
        Bar.add(tgbtnSADMIN);

        btnVACACIONES.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVACACIONES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vacaciones1.png"))); // NOI18N
        btnVACACIONES.setText("VACACIONES");
        btnVACACIONES.setToolTipText("Ver Detalles de Vacaciones");
        btnVACACIONES.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVACACIONES.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVACACIONES.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnVACACIONESItemStateChanged(evt);
            }
        });
        Bar.add(btnVACACIONES);

        dteFECHA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        dteFECHA.setToolTipText("Fecha de Registro");
        dteFECHA.setDateFormatString("dd/MM/yyyy");
        dteFECHA.setEnabled(false);
        dteFECHA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dteFECHA.setMaxSelectableDate(new java.util.Date(2524633297000L));
        dteFECHA.setMaximumSize(new java.awt.Dimension(50, 50));
        dteFECHA.setMinSelectableDate(new java.util.Date(1514790097000L));
        dteFECHA.setMinimumSize(new java.awt.Dimension(50, 50));
        dteFECHA.setPreferredSize(new java.awt.Dimension(50, 50));

        txtEMPRESA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtEMPRESA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Empresa:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtEMPRESA.setEnabled(false);

        txtBONO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBONO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBONO.setText("0");
        txtBONO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bono o Extra:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtBONO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBONOKeyTyped(evt);
            }
        });

        txtCONCEPTO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCONCEPTO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Concepto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        txtAHORRO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAHORRO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtAHORRO.setText("0");
        txtAHORRO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ahorro:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtAHORRO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAHORROKeyTyped(evt);
            }
        });

        txtABONO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtABONO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtABONO.setText("0");
        txtABONO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Abonos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtABONO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtABONOKeyTyped(evt);
            }
        });

        txtTOTAL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTOTAL.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTOTAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtTOTAL.setEnabled(false);

        jpVACACIONES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vacaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtDIASCORRESPONDIENTES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDIASCORRESPONDIENTES.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDIASCORRESPONDIENTES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Correspondientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtDIASCORRESPONDIENTES.setEnabled(false);

        txtDIASTOMADOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDIASTOMADOS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDIASTOMADOS.setText("0");
        txtDIASTOMADOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tomados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtDIASTOMADOS.setEnabled(false);

        txtDIASPREDERTERMINADOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDIASPREDERTERMINADOS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDIASPREDERTERMINADOS.setText("10");
        txtDIASPREDERTERMINADOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Predeterminados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtDIASPREDERTERMINADOS.setEnabled(false);

        txtPRIMA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPRIMA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPRIMA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prima Vacacional", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtPRIMA.setEnabled(false);

        txtDIASRESTANTES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDIASRESTANTES.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDIASRESTANTES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Restantes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtDIASRESTANTES.setEnabled(false);

        btnPAGARVACACIONES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pagarv.png"))); // NOI18N
        btnPAGARVACACIONES.setToolTipText("Pagar Vacaciones");
        btnPAGARVACACIONES.setEnabled(false);
        btnPAGARVACACIONES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPAGARVACACIONESActionPerformed(evt);
            }
        });

        txtMONTOV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMONTOV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMONTOV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "$ Restante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtMONTOV.setEnabled(false);

        txtTOTALV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTOTALV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTOTALV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total con Prima:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtTOTALV.setEnabled(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Periodo Para disposicion:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        jPanel3.setToolTipText("");
        jPanel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        dtePFIN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hasta:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        dtePFIN.setToolTipText("Hasta:");
        dtePFIN.setDateFormatString("dd/MM/yyyy");
        dtePFIN.setEnabled(false);
        dtePFIN.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        dtePFIN.setMaxSelectableDate(new java.util.Date(2524633297000L));
        dtePFIN.setMaximumSize(new java.awt.Dimension(50, 50));
        dtePFIN.setMinSelectableDate(new java.util.Date(1514790097000L));
        dtePFIN.setMinimumSize(new java.awt.Dimension(50, 50));
        dtePFIN.setPreferredSize(new java.awt.Dimension(50, 50));

        dtePINI.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Desde:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        dtePINI.setToolTipText("Desde:");
        dtePINI.setDateFormatString("dd/MM/yyyy");
        dtePINI.setEnabled(false);
        dtePINI.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        dtePINI.setMaxSelectableDate(new java.util.Date(2524633297000L));
        dtePINI.setMaximumSize(new java.awt.Dimension(50, 50));
        dtePINI.setMinSelectableDate(new java.util.Date(1514790097000L));
        dtePINI.setMinimumSize(new java.awt.Dimension(50, 50));
        dtePINI.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(dtePINI, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dtePFIN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtePINI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtePFIN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpVACACIONESLayout = new javax.swing.GroupLayout(jpVACACIONES);
        jpVACACIONES.setLayout(jpVACACIONESLayout);
        jpVACACIONESLayout.setHorizontalGroup(
            jpVACACIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVACACIONESLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpVACACIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVACACIONESLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpVACACIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDIASCORRESPONDIENTES, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(txtDIASPREDERTERMINADOS))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPRIMA, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpVACACIONESLayout.createSequentialGroup()
                        .addComponent(txtDIASTOMADOS, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDIASRESTANTES, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMONTOV, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTOTALV, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPAGARVACACIONES)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpVACACIONESLayout.setVerticalGroup(
            jpVACACIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpVACACIONESLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addGroup(jpVACACIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpVACACIONESLayout.createSequentialGroup()
                        .addGroup(jpVACACIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDIASCORRESPONDIENTES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPRIMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDIASPREDERTERMINADOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpVACACIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDIASTOMADOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpVACACIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDIASRESTANTES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMONTOV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTOTALV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPAGARVACACIONES))
                .addContainerGap())
        );

        txtNSUELDO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNSUELDO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNSUELDO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sueldo Calculado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtNSUELDO.setEnabled(false);

        txtFECHAALTA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFECHAALTA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFECHAALTA.setToolTipText("Fecha de Alta");
        txtFECHAALTA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha de Alta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtFECHAALTA.setEnabled(false);

        txtSEMANA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSEMANA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSEMANA.setToolTipText("Numero de semana");
        txtSEMANA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Semana", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtSEMANA.setEnabled(false);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tiempo y Asistencia por Semana", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblFECHAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Asistencias:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtF1R1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        txtF1R1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puntual", "Retardo", "Retardo Justificado", "Falta Injustificada", "Fata Justificada", "Permiso SGS", "Permiso CGS" }));
        txtF1R1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtF1R1.setEnabled(false);
        txtF1R1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtF1R1PropertyChange(evt);
            }
        });

        txtF1R2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        txtF1R2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puntual", "Retardo", "Retardo Justificado", "Falta Injustificada", "Fata Justificada", "Permiso SGS", "Permiso CGS" }));
        txtF1R2.setEnabled(false);
        txtF1R2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtF1R2PropertyChange(evt);
            }
        });

        txtF2R1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        txtF2R1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puntual", "Retardo", "Retardo Justificado", "Falta Injustificada", "Fata Justificada", "Permiso SGS", "Permiso CGS" }));
        txtF2R1.setEnabled(false);
        txtF2R1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtF2R1PropertyChange(evt);
            }
        });

        txtF2R2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        txtF2R2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puntual", "Retardo", "Retardo Justificado", "Falta Injustificada", "Fata Justificada", "Permiso SGS", "Permiso CGS" }));
        txtF2R2.setEnabled(false);
        txtF2R2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtF2R2PropertyChange(evt);
            }
        });

        txtF3R1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        txtF3R1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puntual", "Retardo", "Retardo Justificado", "Falta Injustificada", "Fata Justificada", "Permiso SGS", "Permiso CGS" }));
        txtF3R1.setEnabled(false);
        txtF3R1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtF3R1PropertyChange(evt);
            }
        });

        txtF3R2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        txtF3R2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puntual", "Retardo", "Retardo Justificado", "Falta Injustificada", "Fata Justificada", "Permiso SGS", "Permiso CGS" }));
        txtF3R2.setEnabled(false);
        txtF3R2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtF3R2PropertyChange(evt);
            }
        });

        txtF4R1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        txtF4R1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puntual", "Retardo", "Retardo Justificado", "Falta Injustificada", "Fata Justificada", "Permiso SGS", "Permiso CGS" }));
        txtF4R1.setEnabled(false);
        txtF4R1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtF4R1PropertyChange(evt);
            }
        });

        txtF4R2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        txtF4R2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puntual", "Retardo", "Retardo Justificado", "Falta Injustificada", "Fata Justificada", "Permiso SGS", "Permiso CGS" }));
        txtF4R2.setEnabled(false);
        txtF4R2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtF4R2PropertyChange(evt);
            }
        });

        txtF5R1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        txtF5R1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puntual", "Retardo", "Retardo Justificado", "Falta Injustificada", "Fata Justificada", "Permiso SGS", "Permiso CGS" }));
        txtF5R1.setEnabled(false);
        txtF5R1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtF5R1PropertyChange(evt);
            }
        });

        txtF5R2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        txtF5R2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puntual", "Retardo", "Retardo Justificado", "Falta Injustificada", "Fata Justificada", "Permiso SGS", "Permiso CGS" }));
        txtF5R2.setEnabled(false);
        txtF5R2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtF5R2PropertyChange(evt);
            }
        });

        txtF6R1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        txtF6R1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puntual", "Retardo", "Retardo Justificado", "Falta Injustificada", "Fata Justificada", "Permiso SGS", "Permiso CGS" }));
        txtF6R1.setEnabled(false);
        txtF6R1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtF6R1PropertyChange(evt);
            }
        });

        tblTABLA.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tblTABLA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblTABLA.setRowHeight(7);
        tblTABLA.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspTABLA.setViewportView(tblTABLA);

        dteSINI.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Desde:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        dteSINI.setForeground(new java.awt.Color(153, 0, 0));
        dteSINI.setToolTipText("Desde:");
        dteSINI.setDateFormatString("dd/MM/yyyy");
        dteSINI.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        dteSINI.setMaxSelectableDate(new java.util.Date(2524633297000L));
        dteSINI.setMaximumSize(new java.awt.Dimension(50, 50));
        dteSINI.setMinSelectableDate(new java.util.Date(1514790097000L));
        dteSINI.setMinimumSize(new java.awt.Dimension(50, 50));
        dteSINI.setPreferredSize(new java.awt.Dimension(50, 50));

        dteSFIN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hasta:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        dteSFIN.setForeground(new java.awt.Color(153, 0, 0));
        dteSFIN.setToolTipText("Hasta:");
        dteSFIN.setDateFormatString("dd/MM/yyyy");
        dteSFIN.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        dteSFIN.setMaxSelectableDate(new java.util.Date(2524633297000L));
        dteSFIN.setMaximumSize(new java.awt.Dimension(50, 50));
        dteSFIN.setMinSelectableDate(new java.util.Date(1514790097000L));
        dteSFIN.setMinimumSize(new java.awt.Dimension(50, 50));
        dteSFIN.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout lblFECHASLayout = new javax.swing.GroupLayout(lblFECHAS);
        lblFECHAS.setLayout(lblFECHASLayout);
        lblFECHASLayout.setHorizontalGroup(
            lblFECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblFECHASLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(lblFECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblFECHASLayout.createSequentialGroup()
                        .addComponent(dteSINI, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dteSFIN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lblFECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jspTABLA)
                        .addGroup(lblFECHASLayout.createSequentialGroup()
                            .addGroup(lblFECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtF1R1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtF1R2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(lblFECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtF2R2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtF2R1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(lblFECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtF3R1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtF3R2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(lblFECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtF4R2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtF4R1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(lblFECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtF5R1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtF5R2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtF6R1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        lblFECHASLayout.setVerticalGroup(
            lblFECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblFECHASLayout.createSequentialGroup()
                .addGroup(lblFECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dteSINI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dteSFIN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTABLA, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lblFECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblFECHASLayout.createSequentialGroup()
                        .addGroup(lblFECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtF5R1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtF6R1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtF5R2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lblFECHASLayout.createSequentialGroup()
                        .addGroup(lblFECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtF1R1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtF2R1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lblFECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtF1R2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtF2R2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(lblFECHASLayout.createSequentialGroup()
                        .addComponent(txtF3R1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtF3R2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lblFECHASLayout.createSequentialGroup()
                        .addComponent(txtF4R1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtF4R2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pnlRESUMEN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resumen", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Anual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Semanal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblF.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblF.setForeground(new java.awt.Color(0, 0, 204));
        lblF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblF.setText("0");
        lblF.setToolTipText("Faltas semanales");
        lblF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Faltas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10), new java.awt.Color(0, 0, 204))); // NOI18N
        lblF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblFJ.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblFJ.setForeground(new java.awt.Color(0, 0, 153));
        lblFJ.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFJ.setText("0");
        lblFJ.setToolTipText("Faltas semanales Justificadas");
        lblFJ.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Faltas J", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10), new java.awt.Color(0, 0, 102))); // NOI18N
        lblFJ.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblRJ.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblRJ.setForeground(new java.awt.Color(0, 0, 102));
        lblRJ.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRJ.setText("0");
        lblRJ.setToolTipText("Retardos semanales Justificados");
        lblRJ.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Retardos J", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10), new java.awt.Color(0, 0, 102))); // NOI18N
        lblRJ.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblR.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblR.setForeground(new java.awt.Color(0, 0, 204));
        lblR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblR.setText("0");
        lblR.setToolTipText("Retardos semanales");
        lblR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Retardos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10), new java.awt.Color(0, 0, 204))); // NOI18N
        lblR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblPS.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblPS.setForeground(new java.awt.Color(0, 0, 204));
        lblPS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPS.setText("0");
        lblPS.setToolTipText("Permiso Sin Goce de Sueldo");
        lblPS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Permisos S", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10), new java.awt.Color(0, 0, 204))); // NOI18N
        lblPS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblPC.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblPC.setForeground(new java.awt.Color(0, 0, 102));
        lblPC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPC.setText("0");
        lblPC.setToolTipText("Permisos semanales Con Goce de Sueldo");
        lblPC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Permisos C", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10), new java.awt.Color(0, 0, 102))); // NOI18N
        lblPC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblFA.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblFA.setForeground(new java.awt.Color(0, 102, 0));
        lblFA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFA.setText("0");
        lblFA.setToolTipText("Faltas Administrativas Semanales");
        lblFA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Faltas Administrativas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10), new java.awt.Color(0, 102, 51))); // NOI18N
        lblFA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFJ, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRJ, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPS, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFA, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblR)
                    .addComponent(lblPS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRJ)
                    .addComponent(lblPC)
                    .addComponent(lblFA)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(lblF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFJ))
        );

        javax.swing.GroupLayout pnlRESUMENLayout = new javax.swing.GroupLayout(pnlRESUMEN);
        pnlRESUMEN.setLayout(pnlRESUMENLayout);
        pnlRESUMENLayout.setHorizontalGroup(
            pnlRESUMENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRESUMENLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlRESUMENLayout.setVerticalGroup(
            pnlRESUMENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRESUMEN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblFECHAS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFECHAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRESUMEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblDEPTO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDEPTO.setText("ADMINISTRATIVO");
        lblDEPTO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Departamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        txtTOTAL1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTOTAL1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTOTAL1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descuentos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtTOTAL1.setEnabled(false);

        javax.swing.GroupLayout jpnCONTENEDORLayout = new javax.swing.GroupLayout(jpnCONTENEDOR);
        jpnCONTENEDOR.setLayout(jpnCONTENEDORLayout);
        jpnCONTENEDORLayout.setHorizontalGroup(
            jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpVACACIONES, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                .addComponent(txtDIAS, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(txtSUELDO, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNSUELDO, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                .addComponent(txtFECHAALTA, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSEMANA, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIDEMPLEADO, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDEPTO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dteFECHA, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                .addComponent(txtNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEMPRESA))
            .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTOTAL1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                        .addComponent(txtABONO, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAHORRO, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                        .addComponent(txtTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                        .addComponent(txtBONO, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCONCEPTO))))
        );
        jpnCONTENEDORLayout.setVerticalGroup(
            jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCONTENEDORLayout.createSequentialGroup()
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dteFECHA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFECHAALTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSEMANA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDEMPLEADO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblDEPTO)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEMPRESA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSUELDO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDIAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNSUELDO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAHORRO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtABONO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCONCEPTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBONO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTOTAL1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpVACACIONES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Historial de Movimientos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jspPRESTAMOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prestamos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jtblPRESTAMOS.setAutoCreateRowSorter(true);
        jtblPRESTAMOS.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jtblPRESTAMOS.setName("jtblPRESTAMOS"); // NOI18N
        jtblPRESTAMOS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspPRESTAMOS.setViewportView(jtblPRESTAMOS);

        txtSALDO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSALDO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSALDO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SALDO:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        txtSALDO.setEnabled(false);
        txtSALDO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSALDOActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer.png"))); // NOI18N
        jButton1.setToolTipText("Resumen de Bono de Ahorro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSALDO, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 35, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSALDO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        jspABONOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Abonos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jtblABONOS.setAutoCreateRowSorter(true);
        jtblABONOS.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jtblABONOS.setName("jtblPRESTAMOS"); // NOI18N
        jtblABONOS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspABONOS.setViewportView(jtblABONOS);

        jspAHORROS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ahorro:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jtblAHORROS.setAutoCreateRowSorter(true);
        jtblAHORROS.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jtblAHORROS.setName("jtblPRESTAMOS"); // NOI18N
        jtblAHORROS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspAHORROS.setViewportView(jtblAHORROS);

        txtSPRESTAMOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSPRESTAMOS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSPRESTAMOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prestamos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtSPRESTAMOS.setEnabled(false);

        txtSABONOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSABONOS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSABONOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Abonos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtSABONOS.setEnabled(false);

        txtSAHORROS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSAHORROS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSAHORROS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ahorro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtSAHORROS.setEnabled(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aguinaldos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnPAGARAGUINALDOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aguinaldo-pyme.png"))); // NOI18N
        btnPAGARAGUINALDOS.setToolTipText("Pagar Aguinaldos");
        btnPAGARAGUINALDOS.setEnabled(false);
        btnPAGARAGUINALDOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPAGARAGUINALDOSActionPerformed(evt);
            }
        });

        lblAGUINALDO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAGUINALDO.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAGUINALDO.setText("AGUINALDOS");
        lblAGUINALDO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Correspondiente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        lblAGUINALDO.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAGUINALDO, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPAGARAGUINALDOS)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPAGARAGUINALDOS)
                    .addComponent(lblAGUINALDO)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bonos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        btnPAGARBONO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPAGARBONO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bonoc.png"))); // NOI18N
        btnPAGARBONO.setToolTipText("Pagar Bono");
        btnPAGARBONO.setEnabled(false);
        btnPAGARBONO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPAGARBONOActionPerformed(evt);
            }
        });

        txtBONOT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBONOT.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBONOT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bono de Caja:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBONOT.setEnabled(false);

        txtBONOTP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBONOTP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBONOTP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bono de Produtividad:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBONOTP.setEnabled(false);

        btnPAGARBONOP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bonop.png"))); // NOI18N
        btnPAGARBONOP.setToolTipText("Pagar Bono");
        btnPAGARBONOP.setEnabled(false);
        btnPAGARBONOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPAGARBONOPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(txtBONOT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPAGARBONO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBONOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPAGARBONOP)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPAGARBONOP)
                    .addComponent(txtBONOTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPAGARBONO)
                    .addComponent(txtBONOT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jpEXTRAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Extra:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jpEXTRAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtLIQUIDACION.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLIQUIDACION.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLIQUIDACION.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Liquidacion:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtLIQUIDACION.setEnabled(false);

        txtMAXIMO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMAXIMO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMAXIMO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prestamo Maximo:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtMAXIMO.setEnabled(false);

        btnFINIQUITO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnFINIQUITO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/finiquito.png"))); // NOI18N
        btnFINIQUITO.setToolTipText("Pagar Finiquito");
        btnFINIQUITO.setEnabled(false);
        btnFINIQUITO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFINIQUITOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpEXTRASLayout = new javax.swing.GroupLayout(jpEXTRAS);
        jpEXTRAS.setLayout(jpEXTRASLayout);
        jpEXTRASLayout.setHorizontalGroup(
            jpEXTRASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEXTRASLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFINIQUITO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLIQUIDACION)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMAXIMO, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpEXTRASLayout.setVerticalGroup(
            jpEXTRASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEXTRASLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpEXTRASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFINIQUITO, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEXTRASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLIQUIDACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMAXIMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSPRESTAMOS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSABONOS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSAHORROS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jspPRESTAMOS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jspABONOS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspAHORROS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpEXTRAS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jpEXTRAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspPRESTAMOS)
                    .addComponent(jspABONOS)
                    .addComponent(jspAHORROS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSPRESTAMOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSABONOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSAHORROS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnCONTENEDOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnCONTENEDOR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //try {
            //imp("NOMINAS");
            try {
                if(F.candado())
                    opciones("NOMINAS");
                this.dispose();
            } catch (SQLException ex) {
            Logger.getLogger(VRegNomina.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
            Logger.getLogger(VRegNomina.class.getName()).log(Level.SEVERE, null, ex);
            }
        /*} catch (Docx4JException ex) {
            Logger.getLogger(VRegNomina.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException | InterruptedException ex) {
            Logger.getLogger(VRegNomina.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRegNomina.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCALCULARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCALCULARActionPerformed
        try {            
            checador();            
            calcularNomina();
            this.btnGuardar.setEnabled(true);
        } catch (Exception ex) {
            Logger.getLogger(VRegNomina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCALCULARActionPerformed

    private void btnPAGARAGUINALDOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPAGARAGUINALDOSActionPerformed
        try {
            if(F.candado())              
                pagarBONOP("AGUINALDO");
        } catch (Exception ex) {
            Logger.getLogger(VRegNomina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPAGARAGUINALDOSActionPerformed

    private void btnPAGARVACACIONESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPAGARVACACIONESActionPerformed
        try {
            if(F.candado())            
                opciones("PRIMA");            
        } catch (Exception ex) {
            Logger.getLogger(VRegNomina.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnPAGARVACACIONESActionPerformed

    private void btnPAGARBONOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPAGARBONOActionPerformed
        try {
            if(F.candado())            
                opciones("BONO");            
        } catch (Exception ex) {
            Logger.getLogger(VRegNomina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPAGARBONOActionPerformed

    private void btnFINIQUITOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFINIQUITOActionPerformed
        try {
            pagarFINIQUITO();
        } catch (SQLException ex) {
            Logger.getLogger(VRegNomina.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRegNomina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFINIQUITOActionPerformed

    private void tgbtnSADMINItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgbtnSADMINItemStateChanged
        activar(evt.getStateChange() == ItemEvent.SELECTED);
    }//GEN-LAST:event_tgbtnSADMINItemStateChanged

    private void btnVACACIONESItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnVACACIONESItemStateChanged
        this.jpVACACIONES.setVisible(evt.getStateChange() == ItemEvent.SELECTED);
            
    }//GEN-LAST:event_btnVACACIONESItemStateChanged

    private void btnPAGARBONOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPAGARBONOPActionPerformed
        try {
            if(F.candado())
                opciones("BONOP");            
        } catch (Exception ex) {
            Logger.getLogger(VRegNomina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPAGARBONOPActionPerformed

    private void txtF1R1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtF1R1PropertyChange
       contarCMB(DEPTO);
    }//GEN-LAST:event_txtF1R1PropertyChange

    private void txtF1R2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtF1R2PropertyChange
        contarCMB(DEPTO);
    }//GEN-LAST:event_txtF1R2PropertyChange

    private void txtF2R1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtF2R1PropertyChange
        contarCMB(DEPTO);
    }//GEN-LAST:event_txtF2R1PropertyChange

    private void txtF2R2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtF2R2PropertyChange
        contarCMB(DEPTO);
    }//GEN-LAST:event_txtF2R2PropertyChange

    private void txtF3R1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtF3R1PropertyChange
        contarCMB(DEPTO);
    }//GEN-LAST:event_txtF3R1PropertyChange

    private void txtF3R2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtF3R2PropertyChange
        contarCMB(DEPTO);
    }//GEN-LAST:event_txtF3R2PropertyChange

    private void txtF4R1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtF4R1PropertyChange
        contarCMB(DEPTO);
    }//GEN-LAST:event_txtF4R1PropertyChange

    private void txtF4R2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtF4R2PropertyChange
        contarCMB(DEPTO);
    }//GEN-LAST:event_txtF4R2PropertyChange

    private void txtF5R1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtF5R1PropertyChange
       contarCMB(DEPTO);
    }//GEN-LAST:event_txtF5R1PropertyChange

    private void txtF5R2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtF5R2PropertyChange
        contarCMB(DEPTO);
    }//GEN-LAST:event_txtF5R2PropertyChange

    private void txtF6R1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtF6R1PropertyChange
       contarCMB(DEPTO);
    }//GEN-LAST:event_txtF6R1PropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            imprimir();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VRegNomina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSALDOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSALDOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSALDOActionPerformed

    private void txtAHORROKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAHORROKeyTyped
        F.NUM(evt, txtAHORRO.getText().trim());
    }//GEN-LAST:event_txtAHORROKeyTyped

    private void txtABONOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtABONOKeyTyped
        F.NUM(evt, txtABONO.getText().trim());
    }//GEN-LAST:event_txtABONOKeyTyped

    private void txtBONOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBONOKeyTyped
        F.NUM(evt, txtBONO.getText().trim());
    }//GEN-LAST:event_txtBONOKeyTyped

    private void txtDIASKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDIASKeyTyped
        F.NUM(evt, txtDIAS.getText().trim());
    }//GEN-LAST:event_txtDIASKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnCALCULAR;
    private javax.swing.JButton btnFINIQUITO;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnPAGARAGUINALDOS;
    private javax.swing.JButton btnPAGARBONO;
    private javax.swing.JButton btnPAGARBONOP;
    private javax.swing.JButton btnPAGARVACACIONES;
    private javax.swing.JToggleButton btnVACACIONES;
    private com.toedter.calendar.JDateChooser dteFECHA;
    private com.toedter.calendar.JDateChooser dtePFIN;
    private com.toedter.calendar.JDateChooser dtePINI;
    private com.toedter.calendar.JDateChooser dteSFIN;
    private com.toedter.calendar.JDateChooser dteSINI;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jpEXTRAS;
    private javax.swing.JPanel jpVACACIONES;
    private javax.swing.JPanel jpnCONTENEDOR;
    private javax.swing.JScrollPane jspABONOS;
    private javax.swing.JScrollPane jspAHORROS;
    private javax.swing.JScrollPane jspPRESTAMOS;
    private javax.swing.JScrollPane jspTABLA;
    private javax.swing.JTable jtblABONOS;
    private javax.swing.JTable jtblAHORROS;
    private javax.swing.JTable jtblPRESTAMOS;
    private javax.swing.JLabel lblAGUINALDO;
    private javax.swing.JLabel lblDEPTO;
    private javax.swing.JLabel lblF;
    private javax.swing.JLabel lblFA;
    private javax.swing.JPanel lblFECHAS;
    private javax.swing.JLabel lblFJ;
    private javax.swing.JLabel lblPC;
    private javax.swing.JLabel lblPS;
    private javax.swing.JLabel lblR;
    private javax.swing.JLabel lblRJ;
    private javax.swing.JPanel pnlRESUMEN;
    private javax.swing.JTable tblTABLA;
    private javax.swing.JToggleButton tgbtnSADMIN;
    private javax.swing.JTextField txtABONO;
    private javax.swing.JTextField txtAHORRO;
    private javax.swing.JTextField txtBONO;
    private javax.swing.JTextField txtBONOT;
    private javax.swing.JTextField txtBONOTP;
    private javax.swing.JTextField txtCONCEPTO;
    private javax.swing.JTextField txtDIAS;
    private javax.swing.JTextField txtDIASCORRESPONDIENTES;
    private javax.swing.JTextField txtDIASPREDERTERMINADOS;
    private javax.swing.JTextField txtDIASRESTANTES;
    private javax.swing.JTextField txtDIASTOMADOS;
    private javax.swing.JTextField txtEMPRESA;
    private javax.swing.JComboBox<String> txtF1R1;
    private javax.swing.JComboBox<String> txtF1R2;
    private javax.swing.JComboBox<String> txtF2R1;
    private javax.swing.JComboBox<String> txtF2R2;
    private javax.swing.JComboBox<String> txtF3R1;
    private javax.swing.JComboBox<String> txtF3R2;
    private javax.swing.JComboBox<String> txtF4R1;
    private javax.swing.JComboBox<String> txtF4R2;
    private javax.swing.JComboBox<String> txtF5R1;
    private javax.swing.JComboBox<String> txtF5R2;
    private javax.swing.JComboBox<String> txtF6R1;
    private javax.swing.JTextField txtFECHAALTA;
    private javax.swing.JTextField txtIDEMPLEADO;
    private javax.swing.JTextField txtLIQUIDACION;
    private javax.swing.JTextField txtMAXIMO;
    private javax.swing.JTextField txtMETODO;
    private javax.swing.JTextField txtMONTOV;
    private javax.swing.JTextField txtNOMBRE;
    private javax.swing.JTextField txtNSUELDO;
    private javax.swing.JTextField txtPRIMA;
    private javax.swing.JTextField txtSABONOS;
    private javax.swing.JTextField txtSAHORROS;
    private javax.swing.JTextField txtSALDO;
    private javax.swing.JTextField txtSEMANA;
    private javax.swing.JTextField txtSPRESTAMOS;
    private javax.swing.JTextField txtSUELDO;
    private javax.swing.JTextField txtTOTAL;
    private javax.swing.JTextField txtTOTAL1;
    private javax.swing.JTextField txtTOTALV;
    // End of variables declaration//GEN-END:variables
}