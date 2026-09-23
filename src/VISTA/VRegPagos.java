/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35.
 */
package VISTA;

import CONTROLADOR.ControlContratos;
import CONTROLADOR.ControlPagos;
import CONTROLADOR.ControlSalidas;
import CONTROLADOR.Excel;
import CONTROLADOR.F;
import static CONTROLADOR.F.abrirArchivo;
import DHNv2.G;
import MODELO.ModeloContratos;
import MODELO.ModeloPagos;
import MODELO.ModeloPagosTabla;
import MODELO.ModeloSalidas;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.SQLException;
import static DHNv2.G.formato;
import java.awt.Color;
import java.awt.event.ItemEvent;

/**
 *
 * @author KIKE FRENCH
 */
public final class VRegPagos extends javax.swing.JInternalFrame {

    private String monto, IPc;
    private String DIA_CORTE;

    //private int IDCLIENTE;
    private int IDCONTRATO;
    private int IDPAQUETE;
    private int IDNOTA;
    private String METODO, METODOPAGO;
    private String D_CORTE;
    private Date N_CORTE;
    private String ACCION, ESTADO;
    private String DOC = "", REFERENCIA = "";   
       
    private int adeudo;
    private float montoP;
    private int cont;
    private int mes;
    private int COSTO, idsalidas;
    private String CP,COMENTARIOS, COMENTARIOSP;
    private String DIRECCION;
    private boolean CONTRATO=false, DOMICILIADO;    
    
    /** Creates new form VClientes

     * @throws java.text.ParseException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException */
    public VRegPagos() throws ParseException, ClassNotFoundException, SQLException {
        initComponents();
    }
    
    public VRegPagos(int IDCONTRATO, String ACCION, int DIAS, String NOMBRE, String COSTO, String DIRECCION, boolean DOMICILIADO) throws ParseException, Exception {        
        initComponents();
        dteFECHA.setDate(G.hoy());
        G.atributosCalendario(dteFECHA);
        G.atributosCalendario(dteFDOC);
        this.Bar.setFloatable(false);
        this.Bar1.setFloatable(false);
        
        pagar(IDCONTRATO,DIAS,NOMBRE,COSTO);
        this.IDCONTRATO = IDCONTRATO;
        this.pnlDATOS.setVisible(false);
        mostrar("NUEVO", DOMICILIADO);
        this.DOMICILIADO = DOMICILIADO;
        actualizarTablas(IDCONTRATO);
        this.txtMONTO.requestFocus();
        this.DIRECCION = DIRECCION;
        
        pack();
        this.jpsCOMENTARIOS.setVisible(false);
        this.txtCOMENTARIOS.setVisible(false);
        atencion();        
        F.teclas(this);
    }
    private void atencion() throws ClassNotFoundException, SQLException, Exception
    {
        String valorBuscado = "NO VERIFICADO";
        boolean encontrado = false;

        // Obtiene la cantidad de filas y columnas de la tabla
        int filas = tblPAGOS.getRowCount();
        int columnas = tblPAGOS.getColumnCount();

        // Recorre las filas
        for (int i = 0; i < filas; i++) {
            // Recorre las columnas de la fila actual
            for (int j = 0; j < columnas; j++) {
                // Obtiene el valor de la celda y lo compara
                Object valorCelda = tblPAGOS.getValueAt(i, j);

                if (valorCelda != null && valorCelda.toString().equals(valorBuscado)) {
                    encontrado = true;
                    JOptionPane.showMessageDialog(null, "EL CLIENTE CUENTA CON UN PAGO NO VERIFICADO","ATENCION",JOptionPane.WARNING_MESSAGE);
                    if(!ESTADO.equals("AUTORIZADO"))
                    {
                        ControlContratos ctrl = new ControlContratos();
                        ctrl.actualizarEstado("AUTORIZADO", IDCONTRATO);
                    }                       
                    break; // Detiene el ciclo si solo necesitas saber si existe
                }
            }
            if (encontrado) {
                break; // Detiene la búsqueda principal
            }
        }
    }
    private void imprimir() throws IOException
    {
        ImpPago pca = new ImpPago(""+IDNOTA,""+G.FECHA(G.hoy(),false),Integer.parseInt(txtIDCONTRATO.getText()),METODO,monto,txtCOMENTARIOS.getText(),txtNOMBRE.getText(),D_CORTE, DIRECCION);
        JButton[] opciones = pca.getButtons();
        JOptionPane.showInternalOptionDialog(this, pca, "Imprimir RECIBO",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
        if(pca.getOpcion() != null)  
            pca.Imprimir();            
    }
    
    public void mostrar(String ACCION,boolean DOMICILIADO)
    {
        this.ACCION=ACCION;
        txtMONTO.requestFocus();
        if(DOMICILIADO)
        {
            txtMETODO.setSelectedItem("TRANSFERENCIA");
            txtMETODO.setEnabled(false);
            cmbORIGEN.setSelectedItem("SANTANDER");
            cmbORIGEN.setEnabled(false);
            cmbDESTINO.setSelectedItem("BBVA");
            cmbDESTINO.setEnabled(false);
            dteFDOC.setVisible(false);
            txtREF.setVisible(false);
            btnADJUNTAR.setVisible(false);
            lblARCHIVO.setText("PAGO DOMICILIADO");
            //lblARCHIVO.setVisible(false);       
            pnlDATOS.setVisible(true);
            txtCP.setVisible(false);
        }
        
    }
    
    public void mostrar(int IDPAGO, String ACCION) throws ParseException, Exception
    {
        this.ACCION = ACCION;
        ModeloPagos pto;
        ControlPagos cp = new ControlPagos();
        pto=(ModeloPagos) cp.getRegistro(IDPAGO);
        this.txtIDCONTRATO.setText(""+pto.getIDCONTRATO());
        this.txtRECIBO.setText(""+pto.getIDNOTA());
        this.dteFECHA.setDate(G.formato.parse(pto.getFECHA()));
        this.txtMONTO.setText(""+pto.getMONTO());
        this.txtCOMENTARIOS.setText(""+pto.getCOMENTARIOS());
        
    }
    
    private void pagar(int IDCONTRATO, int DIAS, String NOMBRE,String COSTO) throws Exception
    {
        ModeloContratos pto;
        ControlContratos cc = new ControlContratos();
        pto=cc.getRegistrosID(IDCONTRATO);
        chkCONT.setSelected(pto.getCONT());
        this.IDPAQUETE = pto.getIDPAQUETE();
        this.IDCONTRATO=IDCONTRATO;
        if (!chkCONT.isSelected())
        {  
            chkCONT.setEnabled(true);
            cont=-1000;            
        }
        else        
            if(chkCONT.isSelected())
                cont=0;
                            
        
        DIA_CORTE=pto.getFCOR();
        txtIDCONTRATO.setText(""+pto.getIDCONTRATO());
        txtMONTO.setText(COSTO);

        txtNOMBRE.setText(NOMBRE);
        lblD_CORTE.setText(DIA_CORTE);
        mes=(int)(DIAS/30);
        //System.out.println("ESTE ES EL MES "+mes);
        if(DIAS>0)        
            adeudo=0;        
        else   
            if (mes<1)            
                mes=mes-1;
        
        this.COSTO = Integer.parseInt(COSTO);
                
        adeudo=this.COSTO*mes;
        lblTOTAL.setText("$ "+G.numero.format(adeudo+cont)+".00");
        IPc = pto.getIP();
        ESTADO = pto.getESTADO();
    }
    
    private void metodoPago()
    {          
        METODO = txtMETODO.getSelectedItem().toString();
        
        switch(txtMETODO.getSelectedItem().toString())
        {
            case "OXXO":
                pnlDATOS.setVisible(true);
                dteFDOC.setDate(G.hoy());
                txtCP.setSelectedItem("LOCAL");
                //txtCP.setVisible(false);
                txtCP.setEnabled(false);
                cmbORIGEN.setSelectedItem("OXXO");
                cmbORIGEN.setEnabled(false);
                cmbDESTINO.setSelectedItem("SPIN");
                cmbDESTINO.setEnabled(false);      
                txtREF.setVisible(true);
                //lblTERMINACION.setVisible(true);
                btnMETODOS.setEnabled(false);
                terminacion();
                break;
            case "TRANSFERENCIA":
                pnlDATOS.setVisible(true);
                dteFDOC.setDate(G.hoy());
                txtCP.setSelectedItem("LOCAL");
                //txtCP.setVisible(false);
                txtCP.setEnabled(false);
                cmbORIGEN.setEnabled(true);
                cmbDESTINO.setEnabled(true);
                txtREF.setVisible(false);
                //lblTERMINACION.setVisible(true);
                btnMETODOS.setEnabled(false);
                terminacion();
                break;
            case "EFECTIVO":
                pnlDATOS.setVisible(false);
                //txtCP.setVisible(true);                
                this.txtCP.setEnabled(true);
                btnMETODOS.setEnabled(false);
                break;
            case "TARJETA":
                pnlDATOS.setVisible(false);
                txtCP.setSelectedItem("LOCAL");
                //txtCP.setVisible(false);
                txtCP.setEnabled(false);
                txtREF.setVisible(false);
                //lblTERMINACION.setVisible(true);
                btnMETODOS.setEnabled(false);
                terminacion();
                break;
            case "DIFERIDO":
                pnlDATOS.setVisible(false);
                txtCP.setVisible(false);
                btnMETODOS.setEnabled(true);
                break;                
            default:
                break;
        }        
    }
    
    private void terminacion()
    {
        String CUENTA = cmbDESTINO.getSelectedItem().toString();
        if(CUENTA.equals("BBVA"))
            this.cmbDESTINO.setBorder(F.tituloBorde("Destino 0689 o 6897:", "Tahoma", 10, Color.BLUE));
            //this.lblTERMINACION.setText("0689 o 6897");
        else
            this.cmbDESTINO.setBorder(F.tituloBorde("Destino 8549:", "Tahoma", 10, Color.MAGENTA));
    }
    
    //CALCULA EL SIGUIENTE MES, LA X ES LA CANTIDAD DE MESES QUE SE VAN A SUMAR
    private void corte_nuevo(int PAQUETE) throws Exception
    {        
        /*Date fmes=null;
        int x;
        ControlContratos ctrl = new ControlContratos();
        if (CONTRATO==true)
        {
            adeudo=adeudo+Integer.parseInt(txtMONTO.getText());
            x = (Integer.parseInt(txtMONTO.getText()) - 1000 ) / COSTO;
        }
        else
        {
            adeudo=adeudo+Integer.parseInt(txtMONTO.getText());
            x = Integer.parseInt(txtMONTO.getText())/COSTO;
        }
        
        if(PAQUETE==0)        
            N_CORTE=formato.parse(lblD_CORTE.getText());           
        else
            N_CORTE=G.hoy();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(N_CORTE);
        calendar.add(Calendar.MONTH,x);
        fmes=calendar.getTime();
        D_CORTE=""+formato.format(fmes);        
        
        ctrl.actualizarCorte(D_CORTE, IDCONTRATO);
        actualizarTablas(IDCONTRATO);
        lblTOTAL.setText("$ "+G.numero.format(adeudo+cont)+".00");
        txtMONTO.setEnabled(false);*/
        
        ControlContratos ctrl = new ControlContratos();
    
        // 1. Parsear el monto una sola vez
        int montox = Integer.parseInt(txtMONTO.getText());

        // 2. Lógica común de adeudo
        adeudo += montox;

        // 3. Cálculo de 'x' simplificado con operador ternario
        int x = (CONTRATO) ? (montox - 1000) / COSTO : montox / COSTO;

        // 4. Determinar fecha de inicio del corte
        N_CORTE = (PAQUETE == 0) ? formato.parse(lblD_CORTE.getText()) : G.hoy();

        // 5. Cálculo de fecha futura
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(N_CORTE);
        calendar.add(Calendar.MONTH, x);

        D_CORTE = formato.format(calendar.getTime());

        // 6. Actualizaciones finales
        ctrl.actualizarCorte(D_CORTE, IDCONTRATO);
        actualizarTablas(IDCONTRATO);

        lblTOTAL.setText("$ " + G.numero.format(adeudo + cont) + ".00");
        txtMONTO.setEnabled(false);

    }    
    
    private void actualizarTablas(int id) throws ClassNotFoundException, SQLException
    {
        ModeloPagosTabla mtpn;
        mtpn = new ModeloPagosTabla (id);
        tblPAGOS.setModel(mtpn);
        F.dimensionar(tblPAGOS,"REGPAGOS");
    }
    
    private void guardar(String ACCION) throws SQLException, Exception
    {
        METODO = txtMETODO.getSelectedItem().toString();
        try 
        {
            //metodoPago();
            COMENTARIOS = txtCOMENTARIOS.getText();           
            switch(METODO)
            {
                case "OXXO":
                    //DOC = lblARCHIVO.getText();
                    if(!lblARCHIVO.getText().equals("ARCHIVO"))
                        DOC = lblARCHIVO.getText();
                    else
                        DOC = lblARCHIVO2.getText();
                    REFERENCIA = "NO VERIFICADO";
                    COMENTARIOS = "FECHA: " + G.formato.format(dteFDOC.getDate())
                           + " AUTORIZACION: " + txtREF.getText()
                           + "\n"+txtCOMENTARIOS.getText();
                    break;
                case "TRANSFERENCIA":
                    //DOC = lblARCHIVO.getText();
                    if(!lblARCHIVO.getText().equals("ARCHIVO"))
                        DOC = lblARCHIVO.getText();
                    else
                        DOC = lblARCHIVO2.getText();
                    if(DOMICILIADO)
                        REFERENCIA = "VERIFICADO SIN ENVIAR";
                    else
                        REFERENCIA = "NO VERIFICADO";
                    COMENTARIOS = "FECHA: " + G.formato.format(dteFDOC.getDate())
                           //+ " AUTORIZACION: " + txtREF.getText()
                           + " ORIGEN: " + cmbORIGEN.getSelectedItem().toString()
                           + " DESTINO: " + cmbDESTINO.getSelectedItem().toString()
                           + "\n"+txtCOMENTARIOS.getText();
                    break;
                default: 
                    break;
            } 
           
            ModeloPagos mdl = new ModeloPagos();
            
            mdl.setIDCONTRATO(IDCONTRATO);
            mdl.setFECHA(G.FECHA(G.hoy(),false));
            mdl.setMETODO(METODO);
            montoP = Float.parseFloat(txtMONTO.getText().trim());
            mdl.setMONTO(montoP);
            monto = "$ "+G.numero.format(Float.parseFloat(txtMONTO.getText().trim()))+".00";
                             
            mdl.setDOC(DOC);
            mdl.setREFERENCIA(REFERENCIA);           
            mdl.setIDCAJA(G.IDCAJA);
           
            CP = txtCP.getSelectedItem().toString();
            switch(CP)
            {
                case "JUAN":
                        gensalida();
                        mdl.setREFERENCIA("PENDIENTE");
                        COMENTARIOS = "PAGO REALIZADO EN CP: "+CP+", CORRESPONDE LA SALIDA # "+idsalidas;
                        mdl.setCOMENTARIOS(COMENTARIOS);
                        this.txtCOMENTARIOS.setText(COMENTARIOS);
                    break;
                case "VICTOR":
                    gensalida();
                    mdl.setREFERENCIA("PENDIENTE");
                    COMENTARIOS = "PAGO REALIZADO EN CP: "+CP+", CORRESPONDE LA SALIDA # "+idsalidas;
                    mdl.setCOMENTARIOS(COMENTARIOS);
                    this.txtCOMENTARIOS.setText(COMENTARIOS);
                    break;
                default:
                    break;
            }                    
                       
            IDNOTA = F.Registro("PAGOS","IDNOTA","DESC");
            mdl.setIDNOTA(IDNOTA);
            txtRECIBO.setText(""+IDNOTA);    
            mdl.setCOMENTARIOS(COMENTARIOS);

            if(!DOC.equals("ARCHIVO"))
            {
                ControlPagos cp = new ControlPagos();
                cp.guardarRegistro(mdl);

                funciones(false);

                switch(METODO)
                {
                    case "OXXO":
                        //Funciones.automatizacion("VERIFICA", IPc, IDCONTRATO, D_CORTE);
                        /*ModeloContratos mdlc;
                        ControlContratos ctrl = new ControlContratos();
                        mdlc = ctrl.getRegistrosID(IDCONTRATO);
                        if(mdlc.getESTADO().equals("ACTIVO"))*/
                        oneday(0);
                        break;
                    case "TRANSFERENCIA":
                        //Funciones.automatizacion("VERIFICA", IPc, IDCONTRATO, D_CORTE);
                        if(DOMICILIADO==false)
                            oneday(0);
                        else
                            {
                                corte_nuevo(F.TIPOpaquete(IDPAQUETE));
                                F.automatizacion("VERIFICA", IPc, IDCONTRATO, D_CORTE);
                            }
                        break;
                    case "EFECTIVO":
                        corte_nuevo(F.TIPOpaquete(IDPAQUETE));
                        F.automatizacion("VERIFICA", IPc, IDCONTRATO, D_CORTE);
                        imprimir();
                        break;
                    case "TARJETA":                        
                        corte_nuevo(F.TIPOpaquete(IDPAQUETE));
                        F.automatizacion("VERIFICA", IPc, IDCONTRATO, D_CORTE);
                        imprimir();
                        break;
                    default:
                        break;
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Falta archivo ADJUNTO","ERROR",JOptionPane.ERROR_MESSAGE);                     
           
        } catch (HeadlessException | NumberFormatException ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        } 

        if (cont == -1000 && chkCONT.isSelected())
        {
            try {
                
                ControlContratos cc = new ControlContratos();
                cc.actualizarINSTALACION(this.chkCONT.isSelected(),IDCONTRATO);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else        
            System.out.println("PUES YA PASO ALGO AQUI");        
        
        F.bitacora("PAGOS",IDNOTA,ACCION,G.IDUSER,COMENTARIOS,"");
    }
  
    private void gensalida() throws SQLException, Exception
    {                            
        idsalidas = F.Registro("SALIDAS","IDSALIDA","DESC");

        ModeloSalidas mdls = new ModeloSalidas();
        mdls.setIDSALIDA (idsalidas);
        mdls.setDESCRIPCION("PRESTAMO A CP: "+CP+", POR PAGO DE CONTRATO: "+IDCONTRATO);
        mdls.setMONTO(montoP);
        mdls.setCATEGORIA("PRESTAMO");
        mdls.setMETODO(METODO);
        mdls.setFECHA(G.FECHA(G.hoy(),true));
        mdls.setIDCAJA(G.IDCAJA);

        ControlSalidas cs = new ControlSalidas();
        cs.guardarRegistro(mdls);           
    }
    
    private void funciones(boolean accion)
    {
       bntIMPRIMIR.setEnabled(!accion);
       btnGuardar.setEnabled(accion);
       txtCP.setEnabled(accion);
       //txtCUENTA.setEnabled(accion);
       txtMONTO.setEnabled(accion);
       txtCOMENTARIOS.setEnabled(accion);
       pnlDATOS.setVisible(accion);
       chkCONT.setEnabled(accion);
    }   
        
    private void seleccion() throws ClassNotFoundException, SQLException
    {
        int fila = tblPAGOS.getSelectedRow();
        IDNOTA = (int) tblPAGOS.getValueAt(fila, 0);    
        METODOPAGO = tblPAGOS.getValueAt(fila, 2).toString();  
        COMENTARIOSP = tblPAGOS.getValueAt(fila, 4).toString();  
    }
    
    private void oneday(int TIEMPO) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloContratos pto;
        ControlContratos cc = new ControlContratos();
        pto=cc.getRegistrosID(IDCONTRATO);
        IPc=pto.getIP();
        switch(TIEMPO)
        {
            case 0:
                F.automatizacion("AUTORIZAR", IPc, IDCONTRATO, "");
                //Funciones.bitacora("AUTORIZAR",IDCONTRATO,"AUTORIZAR",G.IDUSER,"REACTIVACION AUTORIZADA: "+G.FECHA(G.hoy(),true),"");
                JOptionPane.showMessageDialog( null, "El Contrato: "+IDCONTRATO+" Se reactivo de manera momentanea\n para activacion por periodo se requiere el registro de pago de mensualidad", "AUTORIZADO", JOptionPane.INFORMATION_MESSAGE);                
                break;
            case 1:
                F.automatizacion("ONEDAY", IPc, IDCONTRATO, "");
                F.bitacora("ONEDAY",IDCONTRATO,"ONEDAY",G.IDUSER,"REACTIVACION POR UN DIA: "+G.FECHA(G.hoy(),true),"");
                JOptionPane.showMessageDialog( null, "El Contrato: "+IDCONTRATO+" Se reactivo de manera momentanea\n para activacion por periodo se requiere el registro de pago de mensualidad", "ACTIVO", JOptionPane.INFORMATION_MESSAGE);
                
            default:
                break;
        }
        
    }
    
    private void accion(String ACCION) throws Exception
    {
        try
        {
            if(ACCION.equals("BORRAR"))
            {
                seleccion();
                int eleccion = JOptionPane.showConfirmDialog(null, "Realmente Desea Eliminar el registro?");
                if ( eleccion == 0)
                {
                    try
                    {
                        try
                        {                            
                            F.eliminarRegistro(IDNOTA, "PAGOS", "IDNOTA");
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR ELIMINAR LOS DATOS DE LA TABLA", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch (HeadlessException ex)
                    {
                        JOptionPane.showMessageDialog( null, "PARA PODER ELIMINAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            else
            {
                try
                {
                    if(ACCION.equals("NUEVO"))                    
                        IDNOTA = 0;                    
                    
                    RegPagos p = new RegPagos(null,true);
                    p.mostrar(IDNOTA,ACCION,lblD_CORTE.getText());
                    p.setVisible(true);

                }
                catch (ParseException ex)
                {
                    JOptionPane.showMessageDialog( null, "PARA PODER EDITAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
                }
                actualizarTablas(IDCONTRATO);
            }
        }
        catch (ParseException ex)
        {
            JOptionPane.showMessageDialog( null, "PARA PODER EDITAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
        }
    }  
        
    private void abrir()
    {
        int fila = tblPAGOS.getSelectedRow();
        String NARCHIVO = tblPAGOS.getValueAt(fila, 5).toString();
        if(NARCHIVO.equals(""))        
            JOptionPane.showMessageDialog( null, "No hay un documento cargado", "VERIFICAR", JOptionPane.WARNING_MESSAGE);        
        else        
            F.abrirArchivo(G.rutaServidor+"VOUCHER\\"+NARCHIVO);        
    }
    
    private void extras(String TIPO, String CATEGORIA) throws ParseException, SQLException, Exception
    {
        switch(TIPO)
        {
            case "ENTRADA":
                RegEntradas p = new RegEntradas(null,true);
                p.mostrar(0,CATEGORIA,"PAGO DE "+CATEGORIA+" DE "+txtNOMBRE.getText());
                p.setVisible(true);
                break;
            case "SALIDA":
                RegSalidas s = new RegSalidas(null,true);
                s.mostrar(0,CATEGORIA,"CONDONACION CONT "+txtIDCONTRATO.getText()+" CLIENTE "+txtNOMBRE.getText()+" DEL PAGO "+IDNOTA+"-"+METODOPAGO);
                s.setVisible(true);
                break;
            case "PAGO":
                break;
            case "ANUALIDAD":
                
                break;
            default:
                break;
        }       
    }
    
    private void activar(boolean ACCION)
    {
        this.btnANUALIDAD.setEnabled(ACCION);
        this.btnDESCUENTO.setEnabled(ACCION);
        this.btnREUBICACION.setEnabled(ACCION);
        this.btnMANTENIENTO.setEnabled(ACCION);
        this.btnVENTA.setEnabled(ACCION);
        this.bntFLUJOTV.setEnabled(ACCION);
    }
    
    private void METODOS() throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        RegDosMetodos vista;            
        vista = new RegDosMetodos(null,true);
        vista.accion(0,IDCONTRATO,"NUEVO");
        vista.setVisible(true);
    }
            
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnACTUALIZAR = new javax.swing.JButton();
        jpnCONTENEDOR = new javax.swing.JPanel();
        txtNOMBRE = new javax.swing.JTextField();
        txtRECIBO = new javax.swing.JTextField();
        jpsCOMENTARIOS = new javax.swing.JScrollPane();
        txtCOMENTARIOS = new javax.swing.JTextPane();
        txtIDCONTRATO = new javax.swing.JTextField();
        chkCONT = new javax.swing.JCheckBox();
        Bar = new javax.swing.JToolBar();
        btnGuardar = new javax.swing.JButton();
        btnCERRAR = new javax.swing.JButton();
        bntIMPRIMIR = new javax.swing.JButton();
        dteFECHA = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        txtCP = new javax.swing.JComboBox<>();
        txtMETODO = new javax.swing.JComboBox<>();
        btnMETODOS = new javax.swing.JButton();
        btnONEDAY = new javax.swing.JButton();
        pnlDATOS = new javax.swing.JPanel();
        dteFDOC = new com.toedter.calendar.JDateChooser();
        cmbORIGEN = new javax.swing.JComboBox<>();
        cmbDESTINO = new javax.swing.JComboBox<>();
        btnADJUNTAR = new javax.swing.JButton();
        lblARCHIVO = new javax.swing.JLabel();
        txtREF = new javax.swing.JTextField();
        lblARCHIVO2 = new javax.swing.JLabel();
        txtMONTO = new javax.swing.JTextField();
        Bar1 = new javax.swing.JToolBar();
        btnANUALIDAD = new javax.swing.JButton();
        btnDESCUENTO = new javax.swing.JButton();
        btnREUBICACION = new javax.swing.JButton();
        btnMANTENIENTO = new javax.swing.JButton();
        btnVENTA = new javax.swing.JButton();
        bntFLUJOTV = new javax.swing.JButton();
        tgbtnSADMIN = new javax.swing.JToggleButton();
        tgbtnCOMENTARIOS = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        lblD_CORTE = new javax.swing.JLabel();
        jspPAGOS = new javax.swing.JScrollPane();
        tblPAGOS = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        lblTOTAL = new javax.swing.JLabel();
        bntIMPCLI = new javax.swing.JButton();
        btnABRIRDOC = new javax.swing.JButton();
        btnEXCEL = new javax.swing.JButton();

        btnACTUALIZAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/refresh1.png"))); // NOI18N
        btnACTUALIZAR.setText("ACTUALZIAR");
        btnACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnACTUALIZARActionPerformed(evt);
            }
        });

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setClosable(true);
        setTitle("Registro de Pagos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/coins_add.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1100, 750));

        jpnCONTENEDOR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtNOMBRE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNOMBRE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre del Cliente:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtNOMBRE.setEnabled(false);

        txtRECIBO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtRECIBO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRECIBO.setToolTipText("Numero de RECIBO");
        txtRECIBO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. de Recibo:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtRECIBO.setEnabled(false);

        txtCOMENTARIOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comentarios:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtCOMENTARIOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCOMENTARIOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCOMENTARIOSKeyTyped(evt);
            }
        });
        jpsCOMENTARIOS.setViewportView(txtCOMENTARIOS);

        txtIDCONTRATO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIDCONTRATO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDCONTRATO.setToolTipText("Numero de CONTRATO");
        txtIDCONTRATO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. de Contrato:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtIDCONTRATO.setEnabled(false);

        chkCONT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkCONT.setText("Contrato");
        chkCONT.setEnabled(false);
        chkCONT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkCONTMouseClicked(evt);
            }
        });

        Bar.setRollover(true);

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disk.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        Bar.add(btnGuardar);

        btnCERRAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCERRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnCERRAR.setText("CERRAR");
        btnCERRAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCERRAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCERRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCERRARActionPerformed(evt);
            }
        });
        Bar.add(btnCERRAR);

        bntIMPRIMIR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntIMPRIMIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer.png"))); // NOI18N
        bntIMPRIMIR.setText("IMPRIMIR");
        bntIMPRIMIR.setEnabled(false);
        bntIMPRIMIR.setFocusable(false);
        bntIMPRIMIR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntIMPRIMIR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntIMPRIMIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntIMPRIMIRActionPerformed(evt);
            }
        });
        Bar.add(bntIMPRIMIR);

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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles de Pago:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        txtCP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCP.setMaximumRowCount(10);
        txtCP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LOCAL", "JUAN", "VICTOR" }));
        txtCP.setToolTipText("Centros de Pago");
        txtCP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Centro de Pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        txtMETODO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMETODO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EFECTIVO", "TRANSFERENCIA", "TARJETA", "OXXO" }));
        txtMETODO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Metodo de Pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtMETODO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMETODOActionPerformed(evt);
            }
        });

        btnMETODOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnMETODOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/metodospagos.png"))); // NOI18N
        btnMETODOS.setToolTipText("Pago en 2 Metodos");
        btnMETODOS.setEnabled(false);
        btnMETODOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMETODOSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMETODO, 0, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCP, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMETODOS)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMETODOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMETODO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        btnONEDAY.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnONEDAY.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reloj-de-arena.png"))); // NOI18N
        btnONEDAY.setToolTipText("Reactiva servicio de manera momentanea");
        btnONEDAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnONEDAYActionPerformed(evt);
            }
        });

        pnlDATOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        dteFDOC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        dteFDOC.setToolTipText("Fecha de comprobante");
        dteFDOC.setDateFormatString("dd/MM/yyyy");
        dteFDOC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dteFDOC.setMaxSelectableDate(new java.util.Date(2524633297000L));
        dteFDOC.setMaximumSize(new java.awt.Dimension(50, 50));
        dteFDOC.setMinSelectableDate(new java.util.Date(1514790097000L));
        dteFDOC.setMinimumSize(new java.awt.Dimension(50, 50));
        dteFDOC.setPreferredSize(new java.awt.Dimension(50, 50));

        cmbORIGEN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbORIGEN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BAJIO", "BANAMEX", "BANCO AZTECA", "BANCOPPEL", "BANORTE", "BBVA", "HSBC", "INBURSA", "MERCADOPAGO", "SANTANDER", "SCOTIANK", "SPIN", "OXXO", "OTRO" }));
        cmbORIGEN.setSelectedIndex(5);
        cmbORIGEN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Origen:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 10))); // NOI18N

        cmbDESTINO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbDESTINO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BBVA", "SPIN" }));
        cmbDESTINO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Destino 0689 o 6897:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10), java.awt.Color.blue)); // NOI18N
        cmbDESTINO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDESTINOItemStateChanged(evt);
            }
        });

        btnADJUNTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clip-de-papel.png"))); // NOI18N
        btnADJUNTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADJUNTARActionPerformed(evt);
            }
        });

        lblARCHIVO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblARCHIVO.setText("ARCHIVO");

        txtREF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtREF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtREF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Autorizacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblARCHIVO2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblARCHIVO2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblARCHIVO2.setText("Arrasta y Suelta el Comprobante ");

        javax.swing.GroupLayout pnlDATOSLayout = new javax.swing.GroupLayout(pnlDATOS);
        pnlDATOS.setLayout(pnlDATOSLayout);
        pnlDATOSLayout.setHorizontalGroup(
            pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDATOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDATOSLayout.createSequentialGroup()
                        .addComponent(lblARCHIVO2, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlDATOSLayout.createSequentialGroup()
                        .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dteFDOC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbORIGEN, 0, 190, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbDESTINO, 0, 190, Short.MAX_VALUE)
                            .addComponent(txtREF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnADJUNTAR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblARCHIVO)
                        .addGap(255, 255, 255))))
        );
        pnlDATOSLayout.setVerticalGroup(
            pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDATOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dteFDOC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtREF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbORIGEN, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnADJUNTAR)
                        .addComponent(lblARCHIVO)
                        .addComponent(cmbDESTINO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblARCHIVO2, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
        );

        txtMONTO.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtMONTO.setForeground(new java.awt.Color(0, 0, 153));
        txtMONTO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMONTO.setText("000.00");
        txtMONTO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Monto:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        txtMONTO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMONTOKeyTyped(evt);
            }
        });

        Bar1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Otros Pagos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        btnANUALIDAD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnANUALIDAD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1.png"))); // NOI18N
        btnANUALIDAD.setToolTipText("Pago de anualidad");
        btnANUALIDAD.setEnabled(false);
        btnANUALIDAD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnANUALIDAD.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnANUALIDAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnANUALIDADActionPerformed(evt);
            }
        });
        Bar1.add(btnANUALIDAD);

        btnDESCUENTO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDESCUENTO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/descuento.png"))); // NOI18N
        btnDESCUENTO.setToolTipText("Realizar condonacion");
        btnDESCUENTO.setEnabled(false);
        btnDESCUENTO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDESCUENTO.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDESCUENTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDESCUENTOActionPerformed(evt);
            }
        });
        Bar1.add(btnDESCUENTO);

        btnREUBICACION.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnREUBICACION.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ubicacion-1.png"))); // NOI18N
        btnREUBICACION.setToolTipText("Reubicacion");
        btnREUBICACION.setEnabled(false);
        btnREUBICACION.setFocusable(false);
        btnREUBICACION.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnREUBICACION.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnREUBICACION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnREUBICACIONActionPerformed(evt);
            }
        });
        Bar1.add(btnREUBICACION);

        btnMANTENIENTO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMANTENIENTO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/herramienta.png"))); // NOI18N
        btnMANTENIENTO.setToolTipText("Mantenimiento Reparacion");
        btnMANTENIENTO.setEnabled(false);
        btnMANTENIENTO.setFocusable(false);
        btnMANTENIENTO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMANTENIENTO.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMANTENIENTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMANTENIENTOActionPerformed(evt);
            }
        });
        Bar1.add(btnMANTENIENTO);

        btnVENTA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVENTA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/compra.png"))); // NOI18N
        btnVENTA.setToolTipText("Compra de equipo");
        btnVENTA.setEnabled(false);
        btnVENTA.setFocusable(false);
        btnVENTA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVENTA.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVENTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVENTAActionPerformed(evt);
            }
        });
        Bar1.add(btnVENTA);

        bntFLUJOTV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntFLUJOTV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FLUJO.png"))); // NOI18N
        bntFLUJOTV.setToolTipText("Pago de Magistv o Flujotv");
        bntFLUJOTV.setEnabled(false);
        bntFLUJOTV.setFocusable(false);
        bntFLUJOTV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntFLUJOTV.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntFLUJOTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntFLUJOTVActionPerformed(evt);
            }
        });
        Bar1.add(bntFLUJOTV);

        tgbtnSADMIN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tgbtnSADMIN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pass.png"))); // NOI18N
        tgbtnSADMIN.setToolTipText("Habiliar Funciones");
        tgbtnSADMIN.setFocusable(false);
        tgbtnSADMIN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tgbtnSADMIN.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tgbtnSADMIN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgbtnSADMINItemStateChanged(evt);
            }
        });

        tgbtnCOMENTARIOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tgbtnCOMENTARIOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comentarios.png"))); // NOI18N
        tgbtnCOMENTARIOS.setToolTipText("Introducir comentarios");
        tgbtnCOMENTARIOS.setFocusable(false);
        tgbtnCOMENTARIOS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tgbtnCOMENTARIOS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tgbtnCOMENTARIOS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgbtnCOMENTARIOSItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jpnCONTENEDORLayout = new javax.swing.GroupLayout(jpnCONTENEDOR);
        jpnCONTENEDOR.setLayout(jpnCONTENEDORLayout);
        jpnCONTENEDORLayout.setHorizontalGroup(
            jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNOMBRE)
                    .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                        .addComponent(txtRECIBO, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDCONTRATO, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkCONT)
                        .addGap(39, 39, 39)
                        .addComponent(dteFECHA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jpsCOMENTARIOS)
                    .addComponent(pnlDATOS, javax.swing.GroupLayout.PREFERRED_SIZE, 530, Short.MAX_VALUE)
                    .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(txtMONTO))
                    .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                        .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Bar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                        .addComponent(btnONEDAY)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tgbtnSADMIN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tgbtnCOMENTARIOS)))
                .addContainerGap())
        );
        jpnCONTENEDORLayout.setVerticalGroup(
            jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCONTENEDORLayout.createSequentialGroup()
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dteFECHA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRECIBO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDCONTRATO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkCONT))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDATOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpsCOMENTARIOS, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tgbtnSADMIN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnONEDAY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tgbtnCOMENTARIOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMONTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Adeudos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblD_CORTE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblD_CORTE.setForeground(new java.awt.Color(204, 0, 0));
        lblD_CORTE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblD_CORTE.setText("DIA DE CORTE:");
        lblD_CORTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dia de Corte:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jspPAGOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro de pagos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        tblPAGOS.setAutoCreateRowSorter(true);
        tblPAGOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblPAGOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPAGOS.setName("tblPAGOS"); // NOI18N
        tblPAGOS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPAGOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPAGOSMousePressed(evt);
            }
        });
        jspPAGOS.setViewportView(tblPAGOS);

        lblTOTAL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTOTAL.setForeground(new java.awt.Color(204, 0, 0));
        lblTOTAL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTOTAL.setText("ADEUDO:");
        lblTOTAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SALDO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        bntIMPCLI.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntIMPCLI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer.png"))); // NOI18N
        bntIMPCLI.setText("IMPRIMIR");
        bntIMPCLI.setToolTipText("Imprimir Historial");
        bntIMPCLI.setFocusable(false);
        bntIMPCLI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntIMPCLI.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntIMPCLI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntIMPCLIActionPerformed(evt);
            }
        });

        btnABRIRDOC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnABRIRDOC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cuentas1.png"))); // NOI18N
        btnABRIRDOC.setText("ABRIR");
        btnABRIRDOC.setToolTipText("Abrir Documento");
        btnABRIRDOC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnABRIRDOC.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnABRIRDOC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnABRIRDOCActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEXCEL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnABRIRDOC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntIMPCLI)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnABRIRDOC, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(lblTOTAL))
                        .addComponent(btnEXCEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(bntIMPCLI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblD_CORTE, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspPAGOS, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblD_CORTE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspPAGOS)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnCONTENEDOR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            //METODO = txtMETODO.getSelectedItem().toString();
            boolean x = false;
            switch(txtMETODO.getSelectedItem().toString())
            {
                case "OXXO":
                    if(txtREF.getText().trim().equals("")||txtREF.getText()==null)
                    JOptionPane.showMessageDialog( null, "CAMPO NO PUEDE ESTAR VACIO", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
                    else
                    {
                        if(F.existe("PAGOS", "IDNOTA","COMENTARIOS",txtREF.getText(),"METODO",txtMETODO.getSelectedItem().toString(),0,0,0,0,0,0))
                        {
                            ModeloPagos mdl;
                            ControlPagos ctrl = new ControlPagos();
                            mdl=ctrl.getRegistrosAUT(txtREF.getText());
                            F.abrirArchivo(G.rutaServidor+"VOUCHER\\"+mdl.getDOC());
                            int eleccion = JOptionPane.showConfirmDialog( null, "Atencion!! Ya existe pago con este numero de autorizacion\n Numero de recibo: "+mdl.getIDNOTA()+"\n Contrato: "
                                    +mdl.getIDCONTRATO()+"\n Cliente: "+mdl.getNOMBRE()+"\n Guardar de todos modos??",
                                    "VERIFICAR", JOptionPane.YES_NO_OPTION);
                            x = eleccion == 0;
                            break;
                        }
                        else 
                            x = true;
                    }
                    break;
                case "TRANSFERENCIA":
                    if(DOMICILIADO == false)
                        if(F.existe("PAGOSTRANS", "IDNOTA","PAGOS.COMENTARIOS","FECHA: " + G.formato.format(dteFDOC.getDate())
                               + " ORIGEN: " + cmbORIGEN.getSelectedItem().toString()
                               + " DESTINO: " + cmbDESTINO.getSelectedItem().toString()
                               + "\n"+txtCOMENTARIOS.getText(),
                                "pagos.IDCONTRATO","PAGOS",
                                IDCONTRATO,0,0,0,0,0))
                            {
                                ModeloPagos mdl;
                                ControlPagos ctrl = new ControlPagos();
                                mdl=ctrl.getRegistrosAUT(txtREF.getText());
                                F.abrirArchivo(G.rutaServidor+"VOUCHER\\"+mdl.getDOC());
                                int eleccion = JOptionPane.showConfirmDialog( null, "Atencion!! Ya existe pago con este numero de autorizacion\n Numero de recibo: "+mdl.getIDNOTA()+"\n Contrato: "
                                        +mdl.getIDCONTRATO()+"\n Cliente: "+mdl.getNOMBRE()+"\n Guardar de todos modos??",
                                        "VERIFICAR", JOptionPane.YES_NO_OPTION);
                                x = eleccion == 0;   
                                System.out.println(x);
                                break;
                            }
                            else 
                                x = true;
                    else
                        x = true;
                    break;
                default:
                    x = true;
                    break;                        
            }
            /*if(METODO.equals("OXXO"))
            {
                if(txtREF.getText().trim().equals("")||txtREF.getText()==null)
                    JOptionPane.showMessageDialog( null, "CAMPO NO PUEDE ESTAR VACIO", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
                else
                {
                    if(F.existe("PAGOS", "IDNOTA","COMENTARIOS",txtREF.getText(),"METODO",METODO,0,0,0,0,0,0))
                    {
                        ModeloPagos mdl;
                        ControlPagos ctrl = new ControlPagos();
                        mdl=ctrl.getRegistrosAUT(txtREF.getText());
                        F.abrirArchivo("\\"+"\\"+G.ip+"\\dhn\\VOUCHER\\"+mdl.getDOC());
                        int eleccion = JOptionPane.showConfirmDialog( null, "Atencion!! Ya existe pago con este numero de autorizacion\n Numero de recibo: "+mdl.getIDNOTA()+"\n Contrato: "
                                +mdl.getIDCONTRATO()+"\n Cliente: "+mdl.getNOMBRE()+"\n Guardar de todos modos??",
                                "VERIFICAR", JOptionPane.YES_NO_OPTION);
                        x = eleccion == 0;              
                    }
                    else 
                        x =true;
                }                
            }            
            else            
                x = true;*/
            if(x==true)
            {
                System.out.println(x);
                guardar(ACCION);         
                this.btnGuardar.setEnabled(false);
                VContratosL.btnActualizar.doClick();
                this.dispose();
            }
            else
                System.out.println(x+"NO JALO");
                            
        } catch (Exception ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCERRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCERRARActionPerformed
        /*try
        {
            VContratosL.btnActualizar.doClick();            
        }
        catch (Exception ex) 
        {
            System.out.println(ex);            
        }*/
        
        this.dispose();        
    }//GEN-LAST:event_btnCERRARActionPerformed

    private void chkCONTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkCONTMouseClicked
        if(this.chkCONT.isSelected())
        {
            txtMONTO.setEnabled(true);
            int adeudoCCONT = Integer.parseInt(txtMONTO.getText())+1000;
            CONTRATO = true;
            txtMONTO.setText(""+adeudoCCONT);
            txtMONTO.requestFocus();
        }
        else
        {
            txtMONTO.setEnabled(false);
            txtMONTO.setText("");
            txtMONTO.requestFocus();
        }
    }//GEN-LAST:event_chkCONTMouseClicked

    private void bntIMPCLIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntIMPCLIActionPerformed
        try {
            ImpPagoClientes pca = new ImpPagoClientes(""+G.FECHA(G.hoy(),false),txtIDCONTRATO.getText(),txtNOMBRE.getText(),tblPAGOS.getModel(),DIA_CORTE,lblTOTAL.getText());
            JButton[] opciones = pca.getButtons();
            JOptionPane.showInternalOptionDialog(this, pca, "Imprimir RECIBO",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
            if(pca.getOpcion() != null)            
                pca.Imprimir();            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntIMPCLIActionPerformed

    private void tblPAGOSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPAGOSMousePressed
        if (evt.getClickCount() > 1)
        {
            if(Principal.PRIVILEGIOS.equals("SADMIN"))
            {
                try 
                {
                    seleccion();
                    accion("EDITAR");
                } 
                catch (Exception ex) 
                {
                    Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
                JOptionPane.showMessageDialog( null, "NO CUENTAS CON PRIVILEGIOS SUFICIENTES", "VERIFICAR", JOptionPane.WARNING_MESSAGE);    
        }
    }//GEN-LAST:event_tblPAGOSMousePressed

    private void btnACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnACTUALIZARActionPerformed
        try {
            actualizarTablas(IDCONTRATO);
            lblD_CORTE.setText(RegPagos.D_CORTE);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnACTUALIZARActionPerformed

    private void bntIMPRIMIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntIMPRIMIRActionPerformed
        try {
            imprimir();
        } catch (IOException ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntIMPRIMIRActionPerformed

    private void btnONEDAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnONEDAYActionPerformed
        int eleccion = JOptionPane.showConfirmDialog(null, "Activar Servicio de manera momentanea?");
        if ( eleccion == 0)
        {
            try
            {
                try
                {                            
                    oneday(1);
                    this.dispose();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog( null, "Error en la reactivacion momentanea de servicio", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (HeadlessException ex)
            {
                JOptionPane.showMessageDialog( null, "Verifica los datos para poder continuar", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
            }
        }        
    }//GEN-LAST:event_btnONEDAYActionPerformed

    private void btnADJUNTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADJUNTARActionPerformed
        try {
            lblARCHIVO.setText(F.selectarchivo("VOUCHER",IDCONTRATO+"-"+txtMETODO.getSelectedItem().toString()));
        } catch (IOException ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnADJUNTARActionPerformed

    private void txtMETODOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMETODOActionPerformed
        metodoPago();
        F.DragAndDrop(this.lblARCHIVO2,"VOUCHER",IDCONTRATO+"-"+txtMETODO.getSelectedItem().toString());
    }//GEN-LAST:event_txtMETODOActionPerformed

    private void btnABRIRDOCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnABRIRDOCActionPerformed
        abrir();
    }//GEN-LAST:event_btnABRIRDOCActionPerformed

    private void btnEXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXCELActionPerformed
        String rutaExcel = G.rutaServidor+"REPORTES\\HISTORIAL-CONT-" +IDCONTRATO+ ".xls";
        int eleccion = JOptionPane.showConfirmDialog(null, "DESEA EXPORTAR DATOS A EXCEL");
        if (eleccion ==0)
        {
            Excel exportador = new Excel(tblPAGOS,new File(rutaExcel),"Hoja1");
            boolean exportacionOk=exportador.export();
            if(exportacionOk)
                abrirArchivo(rutaExcel);            
            else            
                JOptionPane.showMessageDialog(this,"ERROR AL EXPORTAR\nSELECCIONE UN REGISTRO DE LA TABLA","VERIFICAR",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEXCELActionPerformed

    private void btnANUALIDADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnANUALIDADActionPerformed
        
    }//GEN-LAST:event_btnANUALIDADActionPerformed

    private void btnDESCUENTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDESCUENTOActionPerformed
        try {
            seleccion();  
            if(F.buscarPalabra(COMENTARIOSP, "-PAGO CONDONADO SALIDA ")==false)
                extras("SALIDA","CONDONACION");            
            else
                JOptionPane.showMessageDialog(null, "Pago condonado anteriormente","Revisar",JOptionPane.ERROR_MESSAGE);           
            actualizarTablas(IDCONTRATO);    
        } catch (SQLException ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDESCUENTOActionPerformed

    private void bntFLUJOTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntFLUJOTVActionPerformed
        try {
            extras("ENTRADA","MAGISTV");
        } catch (SQLException ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntFLUJOTVActionPerformed

    private void btnREUBICACIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnREUBICACIONActionPerformed
        try {
            extras("ENTRADA","REUBICACION");
        } catch (SQLException ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnREUBICACIONActionPerformed

    private void btnMANTENIENTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMANTENIENTOActionPerformed
        try {
            extras("ENTRADA","MANTENIMIENTO");
        } catch (SQLException ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMANTENIENTOActionPerformed

    private void btnVENTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVENTAActionPerformed
        
    }//GEN-LAST:event_btnVENTAActionPerformed

    private void tgbtnSADMINItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgbtnSADMINItemStateChanged
        activar(evt.getStateChange() == ItemEvent.SELECTED);
    }//GEN-LAST:event_tgbtnSADMINItemStateChanged

    private void cmbDESTINOItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDESTINOItemStateChanged
        terminacion();
    }//GEN-LAST:event_cmbDESTINOItemStateChanged

    private void txtCOMENTARIOSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCOMENTARIOSKeyTyped
        F.MAYUS(evt);
    }//GEN-LAST:event_txtCOMENTARIOSKeyTyped

    private void txtMONTOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMONTOKeyTyped
        F.NUM(evt);
    }//GEN-LAST:event_txtMONTOKeyTyped

    private void tgbtnCOMENTARIOSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgbtnCOMENTARIOSItemStateChanged
        /*if(evt.getStateChange() == ItemEvent.SELECTED)
        {
            this.jpsCOMENTARIOS.setVisible(true);
            this.txtCOMENTARIOS.setVisible(true);
        }            
        else
        {
            this.jpsCOMENTARIOS.setVisible(false);
            this.txtCOMENTARIOS.setVisible(false);
        }
        this.validate();*/
        boolean x = evt.getStateChange() == ItemEvent.SELECTED;
        this.jpsCOMENTARIOS.setVisible(x);
        this.txtCOMENTARIOS.setVisible(x);
        this.validate();
    }//GEN-LAST:event_tgbtnCOMENTARIOSItemStateChanged

    private void btnMETODOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMETODOSActionPerformed
        try {
            METODOS();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRegPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMETODOSActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JToolBar Bar1;
    private javax.swing.JButton bntFLUJOTV;
    private javax.swing.JButton bntIMPCLI;
    private javax.swing.JButton bntIMPRIMIR;
    private javax.swing.JButton btnABRIRDOC;
    public static javax.swing.JButton btnACTUALIZAR;
    private javax.swing.JButton btnADJUNTAR;
    private javax.swing.JButton btnANUALIDAD;
    private javax.swing.JButton btnCERRAR;
    private javax.swing.JButton btnDESCUENTO;
    private javax.swing.JButton btnEXCEL;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMANTENIENTO;
    private javax.swing.JButton btnMETODOS;
    private javax.swing.JButton btnONEDAY;
    private javax.swing.JButton btnREUBICACION;
    private javax.swing.JButton btnVENTA;
    private javax.swing.JCheckBox chkCONT;
    private javax.swing.JComboBox<String> cmbDESTINO;
    private javax.swing.JComboBox<String> cmbORIGEN;
    private com.toedter.calendar.JDateChooser dteFDOC;
    private com.toedter.calendar.JDateChooser dteFECHA;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jpnCONTENEDOR;
    private javax.swing.JScrollPane jpsCOMENTARIOS;
    private javax.swing.JScrollPane jspPAGOS;
    private javax.swing.JLabel lblARCHIVO;
    private javax.swing.JLabel lblARCHIVO2;
    private javax.swing.JLabel lblD_CORTE;
    private javax.swing.JLabel lblTOTAL;
    private javax.swing.JPanel pnlDATOS;
    private javax.swing.JTable tblPAGOS;
    private javax.swing.JToggleButton tgbtnCOMENTARIOS;
    private javax.swing.JToggleButton tgbtnSADMIN;
    private javax.swing.JTextPane txtCOMENTARIOS;
    private javax.swing.JComboBox<String> txtCP;
    private javax.swing.JTextField txtIDCONTRATO;
    private javax.swing.JComboBox<String> txtMETODO;
    private javax.swing.JTextField txtMONTO;
    private javax.swing.JTextField txtNOMBRE;
    private javax.swing.JTextField txtRECIBO;
    private javax.swing.JTextField txtREF;
    // End of variables declaration//GEN-END:variables
   }

