/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;

import CONTROLADOR.ControlDetalles;
import CONTROLADOR.ControlProductos;
import CONTROLADOR.F;
import MODELO.ModeloDetalles;
import MODELO.ModeloPrestaciones;
import MODELO.ModeloProductosTabla;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KIKE FRENCH
 */
public final class VDetalles extends javax.swing.JInternalFrame 
{    
    private int IDDETALLE;  
    private float IVA;
    private double TC;
    
    /** Creates new form VClientes
      */
    public VDetalles() { 
        
    }
    
    public VDetalles(String titulo) throws ClassNotFoundException, SQLException, Exception { 
        initComponents();
        this.setTitle(titulo);
        permisos(Principal.PRIVILEGIOS);
        this.Bar.setFloatable(false);
        mostrar();  
        
    }
    private void permisos(String PRIVILEGIOS)
    {
        if(PRIVILEGIOS.equals("SADMIN"))
        {
            this.btnGuardar.setEnabled(true);
            this.btnEDITAR.setEnabled(true);
        }
    }
            
    private void guardar() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloDetalles mdl = new ModeloDetalles();        
        mdl.setIDDETALLE(IDDETALLE);
        mdl.setCONCEPTO(txtCONCEPTO.getText());
        mdl.setDIASPRE(Integer.parseInt(txtDIASPRE.getText()));
        mdl.setSMINIMO(Float.parseFloat(txtSMINIMO.getText()));
        mdl.setTC(Float.parseFloat(txtTC.getText()));
        mdl.setIVA(Float.parseFloat(txtIVA.getText()));
        mdl.setGI(Integer.parseInt(txtGI.getText()));
        mdl.setGP(Integer.parseInt(txtGP.getText()));
        
        ControlDetalles ctrl = new ControlDetalles();
        ctrl.actualizarRegistro(mdl);
        
        this.btnEDITAR.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        
        editar(false);
        
        mostrar();
        actualizaTC();
    }
    
    public void actualizaTC() throws Exception
    {
        int IDPRODUCTO;
        double PP, PI, GP, GI, porcentGP, porcentGI, costo;
        boolean iva, CPI, CPP;
        
        ModeloProductosTabla mcont;
        mcont = new ModeloProductosTabla("USD"); 
        
        GP = Integer.parseInt(txtGP.getText());
        GP = GP / 100;
        GI = Integer.parseInt(txtGI.getText());
        GI = GI / 100;
        
        porcentGP = GP+1;                
        porcentGI = GI+1;
        for(int c = 0; c<mcont.getRowCount(); c++)
        {
            IDPRODUCTO=(int)mcont.getValueAt(c, 0);
            costo = (double) mcont.getValueAt(c, 1);
            iva = (boolean) mcont.getValueAt(c, 2);
            PP = (double) mcont.getValueAt(c, 3);
            PI = (double) mcont.getValueAt(c, 4);
            CPP = (boolean) mcont.getValueAt(c, 5);
            CPI = (boolean) mcont.getValueAt(c, 6);
            if(iva==true)            
                IVA=Float.parseFloat(txtIVA.getText());            
            else
                IVA=1;
            
            if(CPP==false)
            {
                PP = costo * IVA  * TC * porcentGP;
                PP = F.redondearAlCinco(PP);
            }
            
            if(CPI==false)
            {
                PI = costo * IVA  * TC * porcentGI;
                PI = F.redondearAlCinco(PI);
            }
            
            ControlProductos ctrlP = new ControlProductos();
            ctrlP.actualizarPrecio(PP, PI, IDPRODUCTO);
        }
        
    }        
    
    private void editar(boolean ACCION)
    {
        this.txtDIASPRE.setEnabled(ACCION);
        this.txtCONCEPTO.setEnabled(ACCION);
        this.txtSMINIMO.setEnabled(ACCION);
        this.btnPAGUINALDO.setEnabled(ACCION);
        this.btnPBONO.setEnabled(ACCION);
        this.btnPBONOP.setEnabled(ACCION);
        this.btnPRIMAV.setEnabled(ACCION);
        this.txtIVA.setEnabled(ACCION);
        this.txtTC.setEnabled(ACCION);
        this.txtGP.setEnabled(ACCION);
        this.txtGI.setEnabled(ACCION);
    }
    
    private void mostrar() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloDetalles mdl;
        ControlDetalles ctrl = new ControlDetalles();
        mdl = ctrl.getRegistrosD();
        
        this.IDDETALLE = mdl.getIDDETALLE();
        this.txtCONCEPTO.setText(mdl.getCONCEPTO());
        this.txtDIASPRE.setText(""+mdl.getDIASPRE());
        this.txtSMINIMO.setText(""+mdl.getSMINIMO());       

        ModeloPrestaciones mdlp;
        mdlp = F.selectP("AGUINALDO", "DESC");
        this.txtAGUINALDO.setText(mdlp.getPERIODO()+" - "+mdlp.getFINI()+ " -- "+mdlp.getFFIN());

        mdlp = F.selectP("BONO DE CAJA", "DESC");
        this.txtBONO.setText(mdlp.getPERIODO()+" - "+mdlp.getFINI()+ " -- "+mdlp.getFFIN());
        
        mdlp = F.selectP("BONO DE PRODUCTIVIDAD", "DESC");
        this.txtBONOP.setText(mdlp.getPERIODO()+" - "+mdlp.getFINI()+ " -- "+mdlp.getFFIN());
        
        mdlp = F.selectP("PRIMA VACACIONAL", "DESC");
        this.txtPRIMAV.setText(mdlp.getPERIODO()+" - "+mdlp.getFINI()+ " -- "+mdlp.getFFIN());
        
        
        TCIVA();
    }
    
    
    
    private void TCIVA() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloDetalles mdl;
        ControlDetalles ctrlC = new ControlDetalles();
        mdl = ctrlC.getTCIVA();
        this.TC=mdl.getTC();
        TC = Math.round(TC * 100d) / 100d;
        this.IVA=mdl.getIVA();
        this.txtGP.setText(""+mdl.getGP());
        this.txtGI.setText(""+mdl.getGI());
        this.txtIVA.setText(""+IVA);
        this.txtTC.setText(""+TC);
    }
    
    private void selectPRESTACION(String CONCEPTO) throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        SelectPRESTACION vista = null;            
        vista = new SelectPRESTACION(null,true);            
        vista.mostrar(CONCEPTO);
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

        btnREFRESCAR = new javax.swing.JButton();
        Bar = new javax.swing.JToolBar();
        btnEDITAR = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCAJAADD = new javax.swing.JButton();
        btnCAJADELETE = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtDIASPRE = new javax.swing.JTextField();
        txtSMINIMO = new javax.swing.JTextField();
        txtCONCEPTO = new javax.swing.JTextField();
        btnPAGUINALDO = new javax.swing.JButton();
        btnPBONO = new javax.swing.JButton();
        txtAGUINALDO = new javax.swing.JLabel();
        txtBONO = new javax.swing.JLabel();
        txtIVA = new javax.swing.JTextField();
        txtTC = new javax.swing.JTextField();
        txtGP = new javax.swing.JTextField();
        txtGI = new javax.swing.JTextField();
        btnPBONOP = new javax.swing.JButton();
        txtBONOP = new javax.swing.JLabel();
        txtPRIMAV = new javax.swing.JLabel();
        btnPRIMAV = new javax.swing.JButton();

        btnREFRESCAR.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnREFRESCAR.setText("REFRESCAR");
        btnREFRESCAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnREFRESCARActionPerformed(evt);
            }
        });

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Detalles");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/detalles.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        Bar.setRollover(true);

        btnEDITAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEDITAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_edit.png"))); // NOI18N
        btnEDITAR.setText("EDITAR");
        btnEDITAR.setEnabled(false);
        btnEDITAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEDITAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEDITAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEDITARActionPerformed(evt);
            }
        });
        Bar.add(btnEDITAR);

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disk.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setEnabled(false);
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        Bar.add(btnGuardar);

        btnCAJAADD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCAJAADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disk.png"))); // NOI18N
        btnCAJAADD.setText("AGREGAR CAJA");
        btnCAJAADD.setEnabled(false);
        btnCAJAADD.setFocusable(false);
        btnCAJAADD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCAJAADD.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCAJAADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCAJAADDActionPerformed(evt);
            }
        });
        Bar.add(btnCAJAADD);

        btnCAJADELETE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCAJADELETE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disk.png"))); // NOI18N
        btnCAJADELETE.setText("ELIMINAR CAJA");
        btnCAJADELETE.setEnabled(false);
        btnCAJADELETE.setFocusable(false);
        btnCAJADELETE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCAJADELETE.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCAJADELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCAJADELETEActionPerformed(evt);
            }
        });
        Bar.add(btnCAJADELETE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Extras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        txtDIASPRE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDIASPRE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDIASPRE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dias Predefinidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        txtDIASPRE.setEnabled(false);

        txtSMINIMO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSMINIMO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSMINIMO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salario Minimo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        txtSMINIMO.setEnabled(false);

        txtCONCEPTO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCONCEPTO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Concepto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        txtCONCEPTO.setEnabled(false);

        btnPAGUINALDO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cursor.png"))); // NOI18N
        btnPAGUINALDO.setToolTipText("Selecciona Periodo de Aguinaldo");
        btnPAGUINALDO.setEnabled(false);
        btnPAGUINALDO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPAGUINALDOActionPerformed(evt);
            }
        });

        btnPBONO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cursor.png"))); // NOI18N
        btnPBONO.setToolTipText("Selecciona Periodo de Bono");
        btnPBONO.setEnabled(false);
        btnPBONO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPBONOActionPerformed(evt);
            }
        });

        txtAGUINALDO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAGUINALDO.setText("AGUINALDO");
        txtAGUINALDO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion de Aguinaldo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        txtBONO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBONO.setText("BONO");
        txtBONO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion de Bono de Caja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        txtIVA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIVA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIVA.setText("1.16");
        txtIVA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IVA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        txtIVA.setEnabled(false);

        txtTC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTC.setText("0.0");
        txtTC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TC.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        txtTC.setEnabled(false);

        txtGP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtGP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGP.setText("0.0");
        txtGP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GP.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        txtGP.setEnabled(false);

        txtGI.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtGI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGI.setText("0.0");
        txtGI.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GI.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        txtGI.setEnabled(false);

        btnPBONOP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cursor.png"))); // NOI18N
        btnPBONOP.setToolTipText("Selecciona Periodo de Bono");
        btnPBONOP.setEnabled(false);
        btnPBONOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPBONOPActionPerformed(evt);
            }
        });

        txtBONOP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBONOP.setText("BONO DE PRODUCTIVIDAD");
        txtBONOP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion de Bono de Productividad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        txtPRIMAV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPRIMAV.setText("PRIMA VACACIONAL");
        txtPRIMAV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion de Prima Vacacional", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        btnPRIMAV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cursor.png"))); // NOI18N
        btnPRIMAV.setToolTipText("Selecciona Periodo de Bono");
        btnPRIMAV.setEnabled(false);
        btnPRIMAV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPRIMAVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSMINIMO, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTC, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGI, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDIASPRE, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCONCEPTO, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAGUINALDO, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBONO, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPBONO)
                            .addComponent(btnPAGUINALDO)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBONOP, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPBONOP))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPRIMAV, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPRIMAV)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDIASPRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCONCEPTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSMINIMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtAGUINALDO)
                    .addComponent(btnPAGUINALDO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPBONO)
                    .addComponent(txtBONO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPBONOP)
                    .addComponent(txtBONOP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPRIMAV)
                    .addComponent(txtPRIMAV))
                .addContainerGap(193, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            // TODO add your handling code here:
            guardar();
        } catch (SQLException ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEDITARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEDITARActionPerformed
        editar(true);        
    }//GEN-LAST:event_btnEDITARActionPerformed

    private void btnCAJAADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCAJAADDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCAJAADDActionPerformed

    private void btnCAJADELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCAJADELETEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCAJADELETEActionPerformed

    private void btnPAGUINALDOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPAGUINALDOActionPerformed
        try {
            // TODO add your handling code here:
            selectPRESTACION("AGUINALDO");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPAGUINALDOActionPerformed

    private void btnPBONOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPBONOActionPerformed
        try {
            // TODO add your handling code here:
            selectPRESTACION("BONO DE CAJA");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPBONOActionPerformed

    private void btnREFRESCARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnREFRESCARActionPerformed
        try {
            // TODO add your handling code here:
            mostrar();
        } catch (SQLException ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnREFRESCARActionPerformed

    private void btnPBONOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPBONOPActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            selectPRESTACION("BONO DE PRODUCTIVIDAD");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPBONOPActionPerformed

    private void btnPRIMAVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPRIMAVActionPerformed
        try {
            selectPRESTACION("PRIMA VACACIONAL");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VDetalles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPRIMAVActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnCAJAADD;
    private javax.swing.JButton btnCAJADELETE;
    private javax.swing.JButton btnEDITAR;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnPAGUINALDO;
    private javax.swing.JButton btnPBONO;
    private javax.swing.JButton btnPBONOP;
    private javax.swing.JButton btnPRIMAV;
    public static javax.swing.JButton btnREFRESCAR;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtAGUINALDO;
    private javax.swing.JLabel txtBONO;
    private javax.swing.JLabel txtBONOP;
    private javax.swing.JTextField txtCONCEPTO;
    private javax.swing.JTextField txtDIASPRE;
    private javax.swing.JTextField txtGI;
    private javax.swing.JTextField txtGP;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JLabel txtPRIMAV;
    private javax.swing.JTextField txtSMINIMO;
    private javax.swing.JTextField txtTC;
    // End of variables declaration//GEN-END:variables

}

