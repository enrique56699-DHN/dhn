/*

 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;

import CONTROLADOR.ControlMovD;
import CONTROLADOR.Excel;
import CONTROLADOR.F;
import static CONTROLADOR.F.abrirArchivo;
import UTILERIAS.RenderCelda;
import DHNv2.G;
import MODELO.ModeloMovD;
import MODELO.ModeloMovDTabla;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author KIKE FRENCH
 */
public final class VConciliar extends javax.swing.JInternalFrame {
    
    /** Creates new form VRepPagos
     * @throws java.lang.Exception
        */

    private String DESCRIPCION;    
    private DefaultTableModel mdlPRINCIPAL;
    private DefaultTableModel mdlCLASIFICADA;
    private DefaultTableModel mdlCOMPARADA;
    private Connection conexion;
    private JTable table;
    private File archivo;
    private int nos;
    
    public VConciliar() throws Exception{
       
    }
    
    public VConciliar(String titulo) throws Exception{
        initComponents();
        this.setTitle(titulo);
        this.Bar.setFloatable(false);           
        // Modelos de tabla
        mdlDefault();
        actualizarTabla();
    }
    
    private void mdlDefault()
    {
        mdlPRINCIPAL = new DefaultTableModel(new Object[]{"Fecha", "Concepto/Referencia", "Cargo", "Abono", "Saldo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        mdlCLASIFICADA = new DefaultTableModel(new Object[]{"Fecha", "Concepto/Referencia", "Cargo", "Abono", "Saldo", "Tipo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        mdlCOMPARADA = new DefaultTableModel(new Object[]{"Fecha", "Concepto/Referencia", "Cargo", "Abono", "Saldo", "Tipo","Encontrado", "Factura", "ID-DETALLES", "CONCILIADO"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }
    
    private void actualizarTablas()
    {
        tblPRINCIPAL.setModel(mdlPRINCIPAL);
        F.dimensionar(tblPRINCIPAL,"CONCILIAR");
        tblCLASIFICADA.setModel(mdlCLASIFICADA);
        F.dimensionar(tblCLASIFICADA,"CONCILIAR");
        tblCOMPARADA.setModel(mdlCOMPARADA);
        F.dimensionar(tblCOMPARADA,"CONCILIAR");
        tblCOMPARADA.getColumnModel().getColumn(6).setCellRenderer(new RenderCelda(true,true,"CONCILIACION"));    
        tblCOMPARADA.getColumnModel().getColumn(5).setCellRenderer(new RenderCelda(true,true,"METODOS"));    
    }
    
    private void seleccion(JTable tabla) throws ClassNotFoundException, SQLException, Exception
    {
        int fila = tabla.getSelectedRow();
        DESCRIPCION = tabla.getValueAt(fila, 1).toString();
    }
    
    private void abrirArchivoTXT() throws SQLException, Exception 
    {
        JFileChooser fc = new JFileChooser();
        FileFilter ff = new FileFilter() {
            @Override
            public boolean accept(File f) {
               if(f.isDirectory()) return true;
                else if(f.getName().endsWith(".txt")) return true;
                    else return false;
            }

            @Override
            public String getDescription() {
                return "Archivos de texto";
            }
        };
        fc.addChoosableFileFilter(ff);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setPreferredSize(new Dimension(1280, 720));
        int resultado = fc.showOpenDialog(this);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            archivo = fc.getSelectedFile();
            this.lblARCHIVO.setText(archivo.getName());
            proceso(archivo);                
        }
    }
    
    private void proceso(File archivo) throws SQLException, Exception
    {
        mdlDefault();
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                // Limpiar tablas existentes
                mdlPRINCIPAL.setRowCount(0);
                mdlCLASIFICADA.setRowCount(0);
                
                String linea;
                boolean primeraLinea = true;
                
                while ((linea = br.readLine()) != null) {
                    if (primeraLinea) {
                        primeraLinea = false;
                        continue; // Saltar encabezado si existe
                    }
                    
                    // Dividir la línea por tabuladores o comas (ajustar según formato)
                    String[] partes = linea.split("\t");
                    if (partes.length < 5) {
                        partes = linea.split(",");
                    }
                    
                    if (partes.length >= 5) {
                        String fecha = partes[0].trim();
                        String concepto = partes[1].trim();
                        String cargo = partes[2].trim();
                        String monto = partes[3].trim();
                        monto = monto.replace(",", "");
                        String saldo = partes[4].trim();
                        
                        // Clasificar transacciones especiales
                        if (concepto.contains("PAGO CUENTA DE TERCERO") || 
                            concepto.contains("SPEI RECIBIDO") || 
                            concepto.contains("SPEI RECIBIDOSTP") || 
                            concepto.contains("CHEQUE") ||   
                            concepto.contains("DEPOSITO POR CORRECCION") ||       
                            concepto.contains("DEPOSITO DE TERCERO") ||
                            concepto.contains("TRASP") ||  
                            concepto.contains("Prestamo") ||      
                            concepto.contains("entre cuentas") ||      
                            concepto.contains("ENTRE CUENTAS") ||          
                            concepto.contains("DEPOSITO EFECTIVO")) {
                            
                            String tipo = "";
                            if (concepto.contains("entre cuentas")) {
                                tipo = "TRASPASO ENTRE CUENTAS PROPIAS";
                                //if(abono!=null)
                                  //  tdep = tdep + Float.parseFloat(abono);
                            } else if (concepto.contains("ENTRE CUENTAS")) {
                                tipo = "TRASPASO ENTRE CUENTAS PROPIAS";
                                //if(abono!=null)
                                  //  tdep = tdep + Float.parseFloat(abono);
                            } else if (concepto.contains("Prestamo")) {
                                tipo = "PRESTAMO";
                                //if(abono!=null)
                                  //  tdep = tdep + Float.parseFloat(abono);
                            } else if (concepto.contains("PAGO CUENTA DE TERCERO")) {
                                tipo = "TRANSFERENCIA";
                                //if(abono!=null)            
                                  //  ttrans = ttrans + Float.parseFloat(abono);
                            } else if (concepto.contains("SPEI RECIBIDOSTP")) {
                                tipo = "TARJETA";
                                //if(abono!=null)
                                  //  ttar = ttar + Float.parseFloat(abono);
                            } else if (concepto.contains("SPEI RECIBIDO")) {
                                tipo = "TRANSFERENCIA";
                                //if(abono!=null)
                                  //  ttrans = ttrans + Float.parseFloat(abono);
                            } else if (concepto.contains("DEPOSITO EFECTIVO")) {
                                tipo = "DEPOSITO";
                                //if(abono!=null)
                                  //  tdep = tdep + Float.parseFloat(abono);
                            } else if (concepto.contains("CHEQUE")) {
                                tipo = "CHEQUE";
                                //if(abono!=null)
                                  //  tdep = tdep + Float.parseFloat(abono);
                            } else if (concepto.contains("DEPOSITO POR CORRECCION")) {
                                tipo = "DEPOSITO";
                                //if(abono!=null)
                                  //  tdep = tdep + Float.parseFloat(abono);
                            } else if (concepto.contains("DEPOSITO DE TERCERO")) {
                                tipo = "TRANSFERENCIA";
                                //if(abono!=null)
                                  //  tdep = tdep + Float.parseFloat(abono);
                            } else if (concepto.contains("TRASP")) {
                                tipo = "TRANSFERENCIA";
                                //if(abono!=null)
                                  //  tdep = tdep + Float.parseFloat(abono);
                            }
                            
                            mdlCLASIFICADA.addRow(new Object[]{fecha, concepto, cargo, monto, saldo, tipo});
                        } else {
                            //String tipo="SALIDA";
                            mdlCLASIFICADA.addRow(new Object[]{fecha, concepto, cargo, monto, saldo, "SALIDA"});
                            mdlPRINCIPAL.addRow(new Object[]{fecha, concepto, cargo, monto, saldo});
                        }
                    }
                }
                                                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + ex.getMessage(), 
                                              "Error", JOptionPane.ERROR_MESSAGE);
                //lblEstatus.setText("Error al leer archivo");
            }
        //}
        conectarBD();
        compararConBD();
        actualizarTablas();
        actualizarTabla();
        saldos();
        //this.jspCOMPARADA.show();
        this.tpnlTRANSACCIONES.setSelectedIndex(2);
    }
    
    /*private void proceso(File archivo) throws SQLException, Exception {
    mdlDefault();
    mdlPRINCIPAL.setRowCount(0);
    mdlCLASIFICADA.setRowCount(0);

    // Definimos las reglas de clasificación en un Map para evitar ifs infinitos
    Map<String, String> reglas = new LinkedHashMap<>(); // LinkedHashMap para mantener orden de prioridad
    reglas.put("entre cuentas", "TRASPASO ENTRE CUENTAS PROPIAS");
    reglas.put("ENTRE CUENTAS", "TRASPASO ENTRE CUENTAS PROPIAS");
    reglas.put("Prestamo", "PRESTAMO");
    reglas.put("PAGO CUENTA DE TERCERO", "TRANSFERENCIA");
    reglas.put("SPEI RECIBIDOSTP", "TARJETA");
    reglas.put("SPEI RECIBIDO", "TRANSFERENCIA");
    reglas.put("DEPOSITO EFECTIVO", "DEPOSITO");
    reglas.put("CHEQUE", "CHEQUE");
    reglas.put("DEPOSITO POR CORRECCION", "DEPOSITO");
    reglas.put("DEPOSITO DE TERCERO", "TRANSFERENCIA");
    reglas.put("TRASP", "TRANSFERENCIA");

    try (BufferedReader br = Files.newBufferedReader(archivo.toPath(), StandardCharsets.UTF_8)) {
        String linea;
        boolean esEncabezado = true;

        while ((linea = br.readLine()) != null) {
            if (esEncabezado) { 
                esEncabezado = false; 
                continue; 
            }

            // Detectar separador (Tab o Coma)
            String[] partes = linea.split("\t");
            if (partes.length < 5) partes = linea.split(",");
            
            if (partes.length >= 5) {
                // Limpieza de datos inmediata
                String fecha    = partes[0].trim();
                String concepto = partes[1].trim();
                String cargo    = partes[2].trim();
                String monto    = partes[3].trim().replace(",", "");
                String saldo    = partes[4].trim();

                // Determinar el tipo usando el Map de reglas
                String tipo = reglas.entrySet().stream()
                        .filter(e -> concepto.contains(e.getKey()))
                        .map(Map.Entry::getValue)
                        .findFirst()
                        .orElse("SALIDA");

                Object[] filaCompleta = {fecha, concepto, cargo, monto, saldo, tipo};

                if (tipo.equals("SALIDA")) {
                    mdlCLASIFICADA.addRow(filaCompleta);
                    mdlPRINCIPAL.addRow(new Object[]{fecha, concepto, cargo, monto, saldo});
                } else {
                    mdlCLASIFICADA.addRow(filaCompleta);
                }
            }
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al procesar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Procesos finales
    conectarBD();
    compararConBD();
    actualizarTablas();
    actualizarTabla();
    saldos();
    this.tpnlTRANSACCIONES.setSelectedIndex(2);
}*/
    
    private void conectarBD() {
        try {                       
            conexion = DriverManager.getConnection(G.url, G.loginDB, G.passwordDB);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar a la BD: " + ex.getMessage(), 
                                          "Error de Conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void compararConBD() throws SQLException, ClassNotFoundException, Exception 
    {
        if (conexion == null) {
            JOptionPane.showMessageDialog(this, "No hay conexión a la base de datos", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Obtener todos los conceptos de la base de datos
            List<String> conceptosBD = new ArrayList<>();
            List<String> movimientos = new ArrayList<>();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ref,fac,idmovd,cotejado FROM movd WHERE movd.COTEJADO = false");            
            
            while (rs.next()) {
                conceptosBD.add(rs.getString("ref").trim());
                movimientos.add(""+rs.getInt("idmovd"));                
            } 
            
            proceso(mdlCLASIFICADA,conceptosBD, movimientos);         
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al comparar con BD: " + ex.getMessage(), 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }        
        if (conexion != null) {
            conexion.close();
        }
    }
    
    private int proceso(DefaultTableModel mdl1, List<String> conceptosBD, List<String> movimientos) throws ClassNotFoundException, Exception
    {
        String fac = null;
        int idmovd = 0;
        int auxmov=0;
        boolean cotejado = false;
        nos=0;
        
        for (int i = 0; i < mdl1.getRowCount(); i++) 
        {
            String fecha = mdl1.getValueAt(i, 0).toString().trim();
            String concepto = mdl1.getValueAt(i, 1).toString().trim();                
            String cargo = mdl1.getValueAt(i, 2).toString().trim();
            String monto = mdl1.getValueAt(i, 3).toString().trim();
            monto = monto.replace(",", "");
            String saldo = mdl1.getValueAt(i, 4).toString().trim();
            String tipo = mdl1.getValueAt(i, 5).toString().trim();
            String encontrado = "No";
            fac = null;
            idmovd = 0;
            cotejado = false;                

            for (String conceptoBD : conceptosBD) 
            {
                if(F.comparar(conceptoBD,concepto)==true)
                {
                    encontrado = "Si"; 
                    int indice = Integer.parseInt(movimientos.get(idmovd));
                    ModeloMovD mdl;
                    ControlMovD ctrl = new ControlMovD();
                    mdl=ctrl.getRegistros(indice);
                    auxmov=mdl.getIDMOVD();
                    fac=mdl.getFAC();
                    cotejado=mdl.getCOTEJADO();   
                    break;
                }
                idmovd++;
            }                      
            if("No".equals(encontrado))
            {
                auxmov = 0;
                nos++;
            }               

            mdlCOMPARADA.addRow(new Object[]{fecha, concepto, cargo, monto, saldo, tipo,encontrado,fac,auxmov, cotejado});
            auxmov=0;

        }
        System.out.println(nos);
        return nos;
    }
    
    private void saldos()
    {
        //DefaultTableModel mdl1 = (DefaultTableModel) tblCOMPARADA.getModel();
        DefaultTableModel mdl1 = mdlCOMPARADA;
        float ttransf = 0;
        float ttarf = 0;
        float tdepf = 0;
        float ttranss = 0;
        float ttars = 0;
        float tdeps = 0;
        
        for (int i = 0; i < mdl1.getRowCount(); i++) 
        {
            String fac="";
            String monto;
            Object a = null;
            Object b =  mdl1.getValueAt(i, 3);
            if(b!=null)
            {
                monto = mdl1.getValueAt(i, 3).toString().trim();
                monto = monto.replace(",", "");
            }
            else
                monto="0";
            
            String tipo = mdl1.getValueAt(i, 5).toString().trim();
            a =  mdl1.getValueAt(i, 7);
            
            if(a!=null)
            {
                fac = mdl1.getValueAt(i, 7).toString().trim();
            }                        
            
            if(!"".equals(fac) && !"NO APLICA".equals(fac))
            {
                switch(tipo)
                {
                    case "TRANSFERENCIA":
                        if(monto == null || monto == "" || monto.trim().isEmpty())
                            ttransf = ttransf + 0;                                                        
                        else                        
                            ttransf = ttransf + Float.parseFloat(monto);
                        break; 
                    case "TARJETA":
                        if(monto != null && monto != "")
                            ttarf = ttarf + Float.parseFloat(monto);
                        break;
                    case "DEPOSITO":
                        if(monto != null && monto != "")
                            tdepf = tdepf + Float.parseFloat(monto);
                        break;                        
                    default:
                        break;
                }

            }
            else
            {
                //System.out.println("Tipo: "+ tipo + " Iteracion: "+i+" Monto: " +monto);
                if(!"NO APLICA".equals(fac))
                {
                    switch(tipo)
                    {
                        case "TRANSFERENCIA":
                            if(monto == null || monto == "" || monto.trim().isEmpty())
                                ttranss = ttranss + 0;                            
                            else                       
                                ttranss = ttranss + Float.parseFloat(monto);                            
                            break;
                        case "TARJETA":
                            if(monto != null && monto != "")
                                ttars = ttars + Float.parseFloat(monto);
                            break;
                        case "DEPOSITO":
                            if(monto != null && monto != "")
                                tdeps = tdeps + Float.parseFloat(monto);
                            break;                           
                        default:
                            break;
                    }
                }
                

            }           
            
        }   
        
        lblTRAF.setText("$ "+G.numero.format(ttransf));
        lblTARF.setText("$ "+G.numero.format(ttarf));
        lblDEPF.setText("$ "+G.numero.format(tdepf));

        lblTRA.setText("$ "+G.numero.format(ttranss));
        lblTAR.setText("$ "+G.numero.format(ttars));
        lblDEP.setText("$ "+G.numero.format(tdeps));
        
    }    
        
    /*private double RcalcularSimilitud(String conceptoBD, String concepto) {
        // Implementación del algoritmo de similitud de Jaccard
        List<String> palabras1 = dividirEnPalabras(conceptoBD);
        List<String> palabras2 = dividirEnPalabras(concepto);
        
        if (palabras1.isEmpty() && palabras2.isEmpty()) return 1.0;
        if (palabras1.isEmpty() || palabras2.isEmpty()) return 0.0;
        
        // Calcular intersección
        List<String> interseccion = new ArrayList<>(palabras1);
        interseccion.retainAll(palabras2);
        
        // Calcular unión
        List<String> union = new ArrayList<>(palabras1);
        union.addAll(palabras2);
        
        // Eliminar duplicados en la unión
        List<String> unionSinDuplicados = new ArrayList<>();
        for (String palabra : union) {
            if (!unionSinDuplicados.contains(palabra)) {
                unionSinDuplicados.add(palabra);
            }
        }
        
        return (double) interseccion.size() / unionSinDuplicados.size();
    }

    private List<String> RdividirEnPalabras(String texto) {
        // Dividir el texto en palabras (simplificado)
        String[] palabras = texto.toLowerCase().split("\\s+");
        List<String> resultado = new ArrayList<>();
        for (String palabra : palabras) {
            
            // Eliminar puntuación alrededor de la palabra
            String limpia = palabra.replaceAll("^[^a-zA-Z0-9áéíóúÁÉÍÓÚñÑ]+|[^a-zA-Z0-9áéíóúÁÉÍÓÚñÑ]+$", "");
            
            
            if (!limpia.isEmpty()) {
                resultado.add(limpia);
            }
        }
        return resultado;
    }*/
    
    private void pestana()
    {
        int pestana = this.tpnlTRANSACCIONES.getSelectedIndex();
        switch(pestana)
        {
            case 0:
                table = tblPRINCIPAL;
                break;
            case 1:
                table = tblCLASIFICADA;
                break;
            case 2:
                table = tblCOMPARADA;
                break;
            case 3:
                table = tblNOIDENT;
                break;
            default:
                break;
        }
    }
    
    private void opMovimientos(String TIPO) throws ParseException, SQLException, Exception
    {
        int eleccion ;//JOptionPane.showConfirmDialog(null,"MENSAJE", "TITULO",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        
        switch(TIPO)
        {
            case "SALIDAS":
                eleccion = JOptionPane.showConfirmDialog(null,"¿Ligar con SALIDA existente? \n Si, liga con SALIDA existente \n No, crea nueva SALIDA", "Acciones de SALIDA",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                switch(eleccion)
                {
                    case 0:
                        RegConciliacionMovimientos c = new RegConciliacionMovimientos(null,true);
                        c.mostrar(0,"NUEVO",0,DESCRIPCION,TIPO);
                        c.setVisible(true);
                        break;
                    case 1:
                        RegSalidas p = new RegSalidas(null,true);
                        p.mostrar(0,"CONCILIACION",DESCRIPCION);
                        p.setVisible(true);
                        break;
                    default:
                        break;
                }
                break;
            case "ENTRADAS":
                eleccion = JOptionPane.showConfirmDialog(null,"¿Ligar con ENTRADA existente? \n Si, liga con ENTRADA existente \n No, crea nueva ENTRADA", "Acciones de ENTRADA",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                switch(eleccion)
                {
                    case 0:
                        RegConciliacionMovimientos c = new RegConciliacionMovimientos(null,true);
                        c.mostrar(0,"NUEVO",0,DESCRIPCION,TIPO);
                        c.setVisible(true);
                        break;
                    case 1:
                        RegEntradas p = new RegEntradas(null,true);
                        p.mostrar(0,"CONCILIACION",DESCRIPCION);
                        p.setVisible(true);
                        break;
                    default:
                        break;
                }
                break;
            case "PAGOS":
                eleccion = JOptionPane.showConfirmDialog(null,"¿Ligar con PAGO existente? \n Si, liga con PAGO existente \n No, Marca pago como NO IDENTIFICADO", "Acciones de PAGO",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                switch(eleccion)
                {
                    case 0:
                        RegConciliacionMovimientos c = new RegConciliacionMovimientos(null,true);
                        c.mostrar(0,"NUEVO",0,DESCRIPCION,TIPO);
                        c.setVisible(true);
                        break;
                    case 1:
                        Object[] inputFields = {"¿Marcar como no identificado?"};

                        int option = JOptionPane.showConfirmDialog(this, inputFields, "Marcar no identificado", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        if (option == JOptionPane.OK_OPTION)
                        {
                            add("NO IDENTIFICADO",0,DESCRIPCION,"");                            
                        }
                        break;
                    default:
                        break;
                }
                break;
                
            default:
                break;
        }
        
    }
        
    private void actualizarTabla() throws ClassNotFoundException, SQLException, Exception
    {        
        ModeloMovDTabla mct;
        mct = new ModeloMovDTabla();
        tblNOIDENT.setModel( mct);
        F.dimensionar(tblNOIDENT,"NO IDENTIFICADO");
    }
    
    private void activar(boolean ACCION)
    {
        this.txtNFACTEFE.setEnabled(ACCION);
        this.txtNFACTTAR.setEnabled(ACCION);
        this.txtNFACTTRA.setEnabled(ACCION);
        this.btnFACTURAR.setEnabled(ACCION);        
    }
    
    private void opciones(String OPCION) throws ClassNotFoundException, SQLException, Exception
    {
        DefaultTableModel mdl1 = mdlCOMPARADA;
        //ControlMovD ctrl;
        for (int i = 0; i < mdl1.getRowCount(); i++) 
        {
            ControlMovD ctrl = new ControlMovD();
            String tipo = mdl1.getValueAt(i, 5).toString().trim();
            String encontrado = mdl1.getValueAt(i, 6).toString().trim();
            Object fac = mdl1.getValueAt(i, 7);
            int idmovd = (int) mdl1.getValueAt(i, 8);
            
            switch(OPCION)
            {
                case "FACTURAR":
                    if("Si".equals(encontrado))
                    {
                        if(fac==null||"".equals(fac))
                        {
                            switch(tipo)
                            {
                                //son update vienen del metodo add
                                case "TRANSFERENCIA":                              
                                    ctrl.updateFAC(idmovd,txtNFACTTRA.getText());
                                    break;
                                case "TARJETA":                            
                                    ctrl.updateFAC(idmovd,txtNFACTTAR.getText());
                                   break;
                                case "DEPOSITO":
                                    ctrl.updateFAC(idmovd,txtNFACTEFE.getText());
                                    break;
                                default:
                                    break;
                            }
                        }
                    } 
                    break;
                case "CONCILIAR":
                        ctrl.updateCOT(idmovd, true);                   
                    break;
                default:
                    break;
            }
            
        }       
                
        txtNFACTTAR.setEnabled(false);
        txtNFACTTRA.setEnabled(false);
        txtNFACTEFE.setEnabled(false);
        
        if("CONCILIAR".equals(OPCION))
        {
            exportarExcel();
            
            String DOCUMENTO = "\\"+"\\"+G.ip+"\\dhn\\REPORTES\\"+G.FECHA_DOC()+archivo.getName();
            File DESTINO = new File(DOCUMENTO);
            FileUtils.copyFile(archivo, DESTINO);
        }
    }
    
    private void add(String TIPO, int IDMOVIMIENTO, String REF, String FAC) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloMovD mdl = new ModeloMovD();
        
        mdl.setMOVIMIENTO(TIPO);
        mdl.setIDMOVIMIENTO(IDMOVIMIENTO);
        mdl.setREF(REF);
        mdl.setFAC(FAC);        
        mdl.setCOTEJADO(false);

        ControlMovD cp = new ControlMovD();
        cp.guardarRegistro(mdl);
    }
    
    private void exportarExcel()
    {
        String rutaExcelY = G.rutaLocal+"\\REPORTES\\CONCILIADO"+G.FECHA_DOC()+".xls";
        String rutaExcel = "\\"+"\\" +G.ip +"\\dhn\\REPORTES\\CONCILIADO"+G.FECHA_DOC()+".xls";
        
        pestana();
        TableColumnModel tcm = table.getColumnModel();
        tcm.removeColumn(tcm.getColumn(9));
        tcm.removeColumn(tcm.getColumn(8));
        tcm.removeColumn(tcm.getColumn(6));
        //tcm.removeColumn(tcm.getColumn(4));            
        Excel exportador = new Excel(table,new File(rutaExcel),"Hoja1");
        boolean exportacionOk=exportador.export();

        if(exportacionOk)
        {
            File ORIGEN = new File(rutaExcel);
            File DESTINO = new File(rutaExcelY);
            try {
                FileUtils.copyFile(ORIGEN, DESTINO);
            } catch (IOException ex) {
                Logger.getLogger(VConciliar.class.getName()).log(Level.SEVERE, null, ex);
            }                
            abrirArchivo(rutaExcelY);
        }
        else        
            JOptionPane.showMessageDialog(this,"ERROR AL EXPORTAR\nSELECCIONE UN REGISTRO DE LA TABLA","VERIFICAR",JOptionPane.ERROR_MESSAGE);
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Bar = new javax.swing.JToolBar();
        btnABRIR = new javax.swing.JButton();
        btnEXCEL = new javax.swing.JButton();
        btnSALIDA = new javax.swing.JButton();
        btnEntrada = new javax.swing.JButton();
        btnPago = new javax.swing.JButton();
        btnLINK = new javax.swing.JButton();
        tpnlTRANSACCIONES = new javax.swing.JTabbedPane();
        jspPRINCIPAL = new javax.swing.JScrollPane();
        tblPRINCIPAL = new javax.swing.JTable();
        jspCLASIFICADA = new javax.swing.JScrollPane();
        tblCLASIFICADA = new javax.swing.JTable();
        jspCOMPARADA = new javax.swing.JScrollPane();
        tblCOMPARADA = new javax.swing.JTable();
        jspNOIDENT = new javax.swing.JScrollPane();
        tblNOIDENT = new javax.swing.JTable();
        txtBUSCAR = new javax.swing.JTextField();
        pnlSALDOS = new javax.swing.JPanel();
        lblTRA = new javax.swing.JLabel();
        lblTRAF = new javax.swing.JLabel();
        lblTARF = new javax.swing.JLabel();
        lblTAR = new javax.swing.JLabel();
        lblDEPF = new javax.swing.JLabel();
        lblDEP = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNFACTEFE = new javax.swing.JTextField();
        txtNFACTTRA = new javax.swing.JTextField();
        txtNFACTTAR = new javax.swing.JTextField();
        btnFACTURAR = new javax.swing.JButton();
        tgbtnSADMIN = new javax.swing.JToggleButton();
        lblARCHIVO = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clasificados y Conciliador de Transacciones Bancarias");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/link.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        Bar.setRollover(true);

        btnABRIR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnABRIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carpeta.png"))); // NOI18N
        btnABRIR.setText("ABRIR");
        btnABRIR.setFocusable(false);
        btnABRIR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnABRIR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnABRIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnABRIRActionPerformed(evt);
            }
        });
        Bar.add(btnABRIR);

        btnEXCEL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel.png"))); // NOI18N
        btnEXCEL.setText("EXPORTAR");
        btnEXCEL.setFocusable(false);
        btnEXCEL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEXCEL.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEXCELActionPerformed(evt);
            }
        });
        Bar.add(btnEXCEL);

        btnSALIDA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSALIDA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/-.png"))); // NOI18N
        btnSALIDA.setText("SALIDA");
        btnSALIDA.setToolTipText("Generar salida");
        btnSALIDA.setFocusable(false);
        btnSALIDA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSALIDA.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSALIDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSALIDAActionPerformed(evt);
            }
        });
        Bar.add(btnSALIDA);

        btnEntrada.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/+.png"))); // NOI18N
        btnEntrada.setText("ENTRADA");
        btnEntrada.setToolTipText("Generar Entrada");
        btnEntrada.setFocusable(false);
        btnEntrada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEntrada.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradaActionPerformed(evt);
            }
        });
        Bar.add(btnEntrada);

        btnPago.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/coins_add.png"))); // NOI18N
        btnPago.setText("PAGOS");
        btnPago.setToolTipText("Ligar con Pago");
        btnPago.setFocusable(false);
        btnPago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPago.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagoActionPerformed(evt);
            }
        });
        Bar.add(btnPago);

        btnLINK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLINK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/link.png"))); // NOI18N
        btnLINK.setText("CIERRE DE MES");
        btnLINK.setFocusable(false);
        btnLINK.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLINK.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLINK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLINKActionPerformed(evt);
            }
        });
        Bar.add(btnLINK);

        tpnlTRANSACCIONES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transacciones Bancarias", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        tpnlTRANSACCIONES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tpnlTRANSACCIONES.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tpnlTRANSACCIONESMouseClicked(evt);
            }
        });

        jspPRINCIPAL.setBorder(null);
        jspPRINCIPAL.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspPRINCIPAL.setMaximumSize(new java.awt.Dimension(185, 185));
        jspPRINCIPAL.setMinimumSize(new java.awt.Dimension(185, 185));
        jspPRINCIPAL.setPreferredSize(new java.awt.Dimension(185, 185));

        tblPRINCIPAL.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblPRINCIPAL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPRINCIPAL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPRINCIPALMousePressed(evt);
            }
        });
        jspPRINCIPAL.setViewportView(tblPRINCIPAL);

        tpnlTRANSACCIONES.addTab("Principales", jspPRINCIPAL);

        jspCLASIFICADA.setBorder(null);
        jspCLASIFICADA.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspCLASIFICADA.setMaximumSize(new java.awt.Dimension(185, 185));
        jspCLASIFICADA.setMinimumSize(new java.awt.Dimension(185, 185));
        jspCLASIFICADA.setPreferredSize(new java.awt.Dimension(185, 185));

        tblCLASIFICADA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblCLASIFICADA.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCLASIFICADA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCLASIFICADAMousePressed(evt);
            }
        });
        jspCLASIFICADA.setViewportView(tblCLASIFICADA);

        tpnlTRANSACCIONES.addTab("Clasificadas", jspCLASIFICADA);

        jspCOMPARADA.setBorder(null);
        jspCOMPARADA.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspCOMPARADA.setMaximumSize(new java.awt.Dimension(185, 185));
        jspCOMPARADA.setMinimumSize(new java.awt.Dimension(185, 185));
        jspCOMPARADA.setPreferredSize(new java.awt.Dimension(185, 185));

        tblCOMPARADA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblCOMPARADA.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCOMPARADA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCOMPARADAMousePressed(evt);
            }
        });
        jspCOMPARADA.setViewportView(tblCOMPARADA);

        tpnlTRANSACCIONES.addTab("Comparadas", jspCOMPARADA);

        jspNOIDENT.setBorder(null);
        jspNOIDENT.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspNOIDENT.setMaximumSize(new java.awt.Dimension(185, 185));
        jspNOIDENT.setMinimumSize(new java.awt.Dimension(185, 185));
        jspNOIDENT.setPreferredSize(new java.awt.Dimension(185, 185));

        tblNOIDENT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblNOIDENT.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblNOIDENT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNOIDENTMousePressed(evt);
            }
        });
        jspNOIDENT.setViewportView(tblNOIDENT);

        tpnlTRANSACCIONES.addTab("No Identificados", jspNOIDENT);

        txtBUSCAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBUSCAR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtBUSCAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBUSCARKeyTyped(evt);
            }
        });

        pnlSALDOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Saldos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTRA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTRA.setForeground(new java.awt.Color(204, 0, 0));
        lblTRA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTRA.setText("00000.00");
        lblTRA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trans", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblTRAF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTRAF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTRAF.setText("00000.00");
        lblTRAF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trans F", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblTARF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTARF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTARF.setText("00000.00");
        lblTARF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tarjeta F", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblTAR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTAR.setForeground(new java.awt.Color(204, 0, 0));
        lblTAR.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTAR.setText("00000.00");
        lblTAR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tarjeta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblDEPF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDEPF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDEPF.setText("00000.00");
        lblDEPF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Deposito F", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblDEP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDEP.setForeground(new java.awt.Color(204, 0, 0));
        lblDEP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDEP.setText("00000.00");
        lblDEP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Deposito", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Facturar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtNFACTEFE.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNFACTEFE.setForeground(new java.awt.Color(0, 51, 204));
        txtNFACTEFE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNFACTEFE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. Fact Deposito", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtNFACTEFE.setEnabled(false);

        txtNFACTTRA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNFACTTRA.setForeground(new java.awt.Color(0, 51, 204));
        txtNFACTTRA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNFACTTRA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. Fact Transferencia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtNFACTTRA.setEnabled(false);

        txtNFACTTAR.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNFACTTAR.setForeground(new java.awt.Color(0, 51, 204));
        txtNFACTTAR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNFACTTAR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. Fact Tarjeta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtNFACTTAR.setEnabled(false);

        btnFACTURAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnFACTURAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/factura.png"))); // NOI18N
        btnFACTURAR.setToolTipText("Facturar en publico general");
        btnFACTURAR.setEnabled(false);
        btnFACTURAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFACTURAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFACTURAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFACTURARActionPerformed(evt);
            }
        });

        tgbtnSADMIN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tgbtnSADMIN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pass.png"))); // NOI18N
        tgbtnSADMIN.setToolTipText("PRIVILEGIOS");
        tgbtnSADMIN.setFocusable(false);
        tgbtnSADMIN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tgbtnSADMIN.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tgbtnSADMIN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgbtnSADMINItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNFACTTRA)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tgbtnSADMIN, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFACTURAR))
                    .addComponent(txtNFACTTAR)
                    .addComponent(txtNFACTEFE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNFACTTRA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNFACTTAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNFACTEFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFACTURAR)
                    .addComponent(tgbtnSADMIN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlSALDOSLayout = new javax.swing.GroupLayout(pnlSALDOS);
        pnlSALDOS.setLayout(pnlSALDOSLayout);
        pnlSALDOSLayout.setHorizontalGroup(
            pnlSALDOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSALDOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSALDOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlSALDOSLayout.createSequentialGroup()
                        .addGroup(pnlSALDOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDEPF, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(lblTARF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTRAF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlSALDOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblTRA, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(lblTAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDEP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlSALDOSLayout.setVerticalGroup(
            pnlSALDOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSALDOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSALDOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTRA)
                    .addComponent(lblTRAF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSALDOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTARF)
                    .addComponent(lblTAR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSALDOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDEPF)
                    .addComponent(lblDEP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblARCHIVO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblARCHIVO.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblARCHIVO.setText("ARCHIVO");
        lblARCHIVO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Archivo Cargado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpnlTRANSACCIONES, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                    .addComponent(txtBUSCAR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblARCHIVO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(pnlSALDOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblARCHIVO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpnlTRANSACCIONES, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                    .addComponent(pnlSALDOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblPRINCIPALMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPRINCIPALMousePressed
        if (evt.getClickCount() > 1)
        {
            try {
                pestana();
                seleccion(table);
                actualizarTablas();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(InfoClientes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(InfoClientes.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_tblPRINCIPALMousePressed

    private void tblCLASIFICADAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCLASIFICADAMousePressed
        if (evt.getClickCount() > 1)
        {
            try {
                pestana();
                seleccion(table);
                actualizarTablas();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(InfoClientes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(InfoClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblCLASIFICADAMousePressed

    private void tblCOMPARADAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCOMPARADAMousePressed
        if (evt.getClickCount() > 1)
        {
            try {
                pestana();
                seleccion(table);
                actualizarTablas();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(InfoClientes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(InfoClientes.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_tblCOMPARADAMousePressed

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        pestana();
        F.MAYUS(evt);
        F.buscar(table, txtBUSCAR.getText(),null,0);
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void tpnlTRANSACCIONESMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpnlTRANSACCIONESMouseClicked
        pestana();        
    }//GEN-LAST:event_tpnlTRANSACCIONESMouseClicked

    private void btnEXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXCELActionPerformed
        exportarExcel();
    }//GEN-LAST:event_btnEXCELActionPerformed

    private void btnABRIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnABRIRActionPerformed
        try {
            abrirArchivoTXT();            
        } catch (SQLException ex) {
            Logger.getLogger(VConciliar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VConciliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnABRIRActionPerformed

    private void btnSALIDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSALIDAActionPerformed
        try {
            pestana();
            seleccion(table);
            opMovimientos("SALIDAS");
            proceso(archivo);
        } catch (SQLException ex) {
            Logger.getLogger(VConciliar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VConciliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSALIDAActionPerformed

    private void tblNOIDENTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNOIDENTMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblNOIDENTMousePressed

    private void tgbtnSADMINItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgbtnSADMINItemStateChanged
        activar(evt.getStateChange() == ItemEvent.SELECTED);        
    }//GEN-LAST:event_tgbtnSADMINItemStateChanged

    private void btnEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradaActionPerformed
        try {
            pestana();
            seleccion(table);
            opMovimientos("ENTRADAS");
            proceso(archivo);
        } catch (SQLException ex) {
            Logger.getLogger(VConciliar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VConciliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEntradaActionPerformed

    private void btnPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagoActionPerformed
        try {
            pestana();
            seleccion(table);
            opMovimientos("PAGOS");
            proceso(archivo);
        } catch (SQLException ex) {
            Logger.getLogger(VConciliar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VConciliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPagoActionPerformed

    private void btnFACTURARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFACTURARActionPerformed
        try {
            if(F.candado())            
                opciones("FACTURAR");            
        } catch (SQLException ex) {
            Logger.getLogger(VConciliar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VConciliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFACTURARActionPerformed

    private void btnLINKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLINKActionPerformed
        try {
            if(F.candado())            
                opciones("CONCILIAR");            
        } catch (SQLException ex) {
            Logger.getLogger(VConciliar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VConciliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLINKActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnABRIR;
    private javax.swing.JButton btnEXCEL;
    private javax.swing.JButton btnEntrada;
    private javax.swing.JButton btnFACTURAR;
    private javax.swing.JButton btnLINK;
    private javax.swing.JButton btnPago;
    private javax.swing.JButton btnSALIDA;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jspCLASIFICADA;
    private javax.swing.JScrollPane jspCOMPARADA;
    private javax.swing.JScrollPane jspNOIDENT;
    private javax.swing.JScrollPane jspPRINCIPAL;
    private javax.swing.JLabel lblARCHIVO;
    private javax.swing.JLabel lblDEP;
    private javax.swing.JLabel lblDEPF;
    private javax.swing.JLabel lblTAR;
    private javax.swing.JLabel lblTARF;
    private javax.swing.JLabel lblTRA;
    private javax.swing.JLabel lblTRAF;
    private javax.swing.JPanel pnlSALDOS;
    private javax.swing.JTable tblCLASIFICADA;
    private javax.swing.JTable tblCOMPARADA;
    private javax.swing.JTable tblNOIDENT;
    private javax.swing.JTable tblPRINCIPAL;
    private javax.swing.JToggleButton tgbtnSADMIN;
    private javax.swing.JTabbedPane tpnlTRANSACCIONES;
    private javax.swing.JTextField txtBUSCAR;
    private javax.swing.JTextField txtNFACTEFE;
    private javax.swing.JTextField txtNFACTTAR;
    private javax.swing.JTextField txtNFACTTRA;
    // End of variables declaration//GEN-END:variables

}

