/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;


import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloNominasTabla;
import java.io.File;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KIKE FRENCH
 */
public final class VNominas extends javax.swing.JInternalFrame {
    
    private int IDNOMINA;
    private String EMPLEADO, EMPRESA, FECHA,SUELDO,DIAS,SUELDOCAL,BONO,CONCEPTO,AHORRO,ABONO,TOTAL;
    private int SEMANA, ANO;
    private String RUTA;
    private String rutaWord = "\\"+"\\" +G.ip +"\\dhn\\MACHOTES\\NOMINAS.docx";
    private String rutaExcel = "\\"+"\\" +G.ip +"\\dhn\\MACHOTES\\NOMINAS.xlsx";
    private String rutaWordtmp = "\\"+"\\" +G.ip +"\\dhn\\MACHOTES\\NOMINAStmp.docx";
    private String rutaPDF = "\\"+"\\" +G.ip +"\\dhn\\MACHOTES\\NOMINAS.pdf";
    private String[] titulos = {"«FOLIO»", 
        "«FECHA»", 
        "«EMPRESA»", 
        "«NOMBRE»", 
        "«SALARIO»", 
        "«DIAST»", 
        "«NSALARIO»", 
        "«BONO»", 
        "«DESCRIPCIONBONO»", 
        "«AHORRO»", 
        "«ABONO»", 
        "«TOTAL»", 
        "«VCORRESPONDIENTES»", 
        "«VPREDETERMINADAS»", 
        "«VDTOMADOS»", 
        "«VDRESTANTES»", 
        "«VPRIMA»", 
        "«TPRESTAMOS»", 
        "«TABONOS»", 
        "«TAHORROS»", 
        "«TSALDO»",
        "«BONOT»",
        "«SEMANA»"};
        
    
    /** Creates new form VClientes */
    public VNominas() {  
        }
    
    public VNominas(String titulo) throws ClassNotFoundException, SQLException, Exception {  
        initComponents();
        this.setTitle(titulo);
        permisos(Principal.PRIVILEGIOS);
        actualizarTablas();
        this.Bar.setFloatable(false);
        datos();
        totales();
    }
    
    private void permisos(String PRIVILEGIOS)
    {
        if(PRIVILEGIOS.equals("SADMIN"))
        {
            btnIMPRIMIR.setEnabled(true);
        }
    }
    
    private void datos()
    {
        //OBTENER EL NUMERO DE SEMANA
        Calendar calendario = Calendar.getInstance();
        this.ANO = calendario.get(Calendar.YEAR);
        this.txtANO.setText(""+ANO);
        this.SEMANA = calendario.get(Calendar.WEEK_OF_YEAR);
        this.txtSEMANA.setText(""+SEMANA);
    }    
    private void actualizarTablas() throws ClassNotFoundException, SQLException
    {
        ModeloNominasTabla met;
        met = new ModeloNominasTabla();
        tblNOMINAS.setModel(met);
        F.dimensionar(tblNOMINAS,"NOMINAS");
    }
    
    private void totales() throws SQLException, Exception
    {
        if(txtSEMANA.getText()!=null && txtANO.getText()!=null)
        {
            float tahorros = F.totales("AHORROS", "NOMINAS", "IDAHORRO", "MONTO","SEMANA",Integer.parseInt(txtSEMANA.getText()),"ANO",Integer.parseInt(txtANO.getText()));
            float tabonos = F.totales("ABONOS", "NOMINAS", "IDABONO", "MONTO","SEMANA",Integer.parseInt(txtSEMANA.getText()),"ANO",Integer.parseInt(txtANO.getText()));
            this.lblTAHORROS.setText(""+tahorros);
            this.lblTABONOS.setText(""+tabonos);
            float tcaja = tahorros + tabonos;
            this.lblTCAJA.setText(""+tcaja);
        }
        
    }

    private void seleccion() throws ClassNotFoundException, SQLException, Exception
    {
        int fila = tblNOMINAS.getSelectedRow();
        IDNOMINA = (int) tblNOMINAS.getValueAt(fila, 0);
        EMPLEADO = tblNOMINAS.getValueAt(fila, 1).toString();
        EMPRESA = tblNOMINAS.getValueAt(fila, 2).toString();
        FECHA = tblNOMINAS.getValueAt(fila, 3).toString();
        SEMANA = (int) tblNOMINAS.getValueAt(fila, 4);
        ANO = (int) tblNOMINAS.getValueAt(fila, 5);
        SUELDO = tblNOMINAS.getValueAt(fila, 6).toString();
        DIAS = tblNOMINAS.getValueAt(fila, 7).toString();
        SUELDOCAL = tblNOMINAS.getValueAt(fila, 8).toString();
        BONO = tblNOMINAS.getValueAt(fila, 9).toString();
        CONCEPTO = tblNOMINAS.getValueAt(fila, 10).toString();
        AHORRO = tblNOMINAS.getValueAt(fila, 11).toString();
        ABONO = tblNOMINAS.getValueAt(fila, 12).toString();
        TOTAL = tblNOMINAS.getValueAt(fila, 13).toString();
        RUTA = "\\"+"\\" +G.ip +"\\dhn\\DOCS\\NOMINAS-"+IDNOMINA+".pdf";    
        //existe(RUTA);
        btnIMPRIMIR.setEnabled(existe(RUTA));
    }
    
    private boolean existe(String RUTA)
    {      
        File f = new File(RUTA);        
        return f.exists();
    }
    
    private void nomina() throws SQLException, Exception
    {        
        /*JInternalFrame ji = validador.getJInternalFrame(VRegNomina.class.getName());
        if(ji == null || ji.isClosed())
        {
            ji = new VRegNomina(IDNOMINA,PRIVILEGIOS);
            Principal.getCONT().add(ji);
            validador.addJIframe(VRegNomina.class.getName(), ji);            
        }
        ji.setMaximum(true);

        ji.setVisible(true);
        ji.moveToFront();*/
             
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
        btnEDITAR = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEXCEL = new javax.swing.JButton();
        btnIMPRIMIR = new javax.swing.JButton();
        jspNOMINAS = new javax.swing.JScrollPane();
        tblNOMINAS = new javax.swing.JTable();
        txtBUSCAR = new javax.swing.JTextField();
        lblTCAJA = new javax.swing.JLabel();
        lblTABONOS = new javax.swing.JLabel();
        lblTAHORROS = new javax.swing.JLabel();
        txtANO = new javax.swing.JTextField();
        txtSEMANA = new javax.swing.JTextField();

        setClosable(true);
        setMaximizable(true);
        setTitle("Nominas");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nomina.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        Bar.setRollover(true);

        btnEDITAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEDITAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_edit.png"))); // NOI18N
        btnEDITAR.setText("EDITAR");
        btnEDITAR.setEnabled(false);
        btnEDITAR.setFocusable(false);
        btnEDITAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEDITAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEDITAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEDITARActionPerformed(evt);
            }
        });
        Bar.add(btnEDITAR);

        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_refresh.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setFocusable(false);
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        Bar.add(btnActualizar);

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

        btnIMPRIMIR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnIMPRIMIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer.png"))); // NOI18N
        btnIMPRIMIR.setText("IMPRIMIR");
        btnIMPRIMIR.setEnabled(false);
        btnIMPRIMIR.setFocusable(false);
        btnIMPRIMIR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIMPRIMIR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIMPRIMIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIMPRIMIRActionPerformed(evt);
            }
        });
        Bar.add(btnIMPRIMIR);

        jspNOMINAS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblNOMINAS.setAutoCreateRowSorter(true);
        tblNOMINAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblNOMINAS.setName("tblNOMINAS"); // NOI18N
        tblNOMINAS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblNOMINAS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNOMINASMouseClicked(evt);
            }
        });
        jspNOMINAS.setViewportView(tblNOMINAS);

        txtBUSCAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBUSCAR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtBUSCAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBUSCARKeyTyped(evt);
            }
        });

        lblTCAJA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTCAJA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTCAJA.setText("0000.00");
        lblTCAJA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total para caja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblTABONOS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTABONOS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTABONOS.setText("0000.00");
        lblTABONOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Abonos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblTAHORROS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTAHORROS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTAHORROS.setText("0000.00");
        lblTAHORROS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Ahorros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        txtANO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtANO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtANO.setText("2025");
        txtANO.setToolTipText("");
        txtANO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Año", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        txtSEMANA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSEMANA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSEMANA.setToolTipText("");
        txtSEMANA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Semana", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtSEMANA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSEMANAKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            .addComponent(jspNOMINAS)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtBUSCAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSEMANA, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtANO, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTAHORROS, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTABONOS, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTCAJA, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTCAJA)
                        .addComponent(lblTABONOS)
                        .addComponent(lblTAHORROS)
                        .addComponent(txtANO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSEMANA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspNOMINAS, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            // TODO add your handling code here:
            /*mct = new ModeloClientesTabla();
            JTblClientes.setModel(mct);*/
            actualizarTablas();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VNominas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXCELActionPerformed
        F.exportarXLS(tblNOMINAS,"\\REPORTES\\NOMINAS");
    }//GEN-LAST:event_btnEXCELActionPerformed

    private void btnIMPRIMIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIMPRIMIRActionPerformed
        F.abrirArchivo(RUTA);
    }//GEN-LAST:event_btnIMPRIMIRActionPerformed

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        F.MAYUS(evt);
        F.buscar(tblNOMINAS, txtBUSCAR.getText(),null,0);
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void btnEDITARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEDITARActionPerformed
        try {
            seleccion();
            nomina();
            actualizarTablas();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VAbonos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VNominas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEDITARActionPerformed

    private void txtSEMANAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSEMANAKeyReleased
        try {
            // TODO add your handling code here:
            totales();
        } catch (Exception ex) {
            Logger.getLogger(VNominas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtSEMANAKeyReleased

    private void tblNOMINASMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNOMINASMouseClicked
        try {
            // TODO add your handling code here:
            seleccion();
        } catch (SQLException ex) {
            Logger.getLogger(VNominas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VNominas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblNOMINASMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEDITAR;
    private javax.swing.JButton btnEXCEL;
    private javax.swing.JButton btnIMPRIMIR;
    private javax.swing.JScrollPane jspNOMINAS;
    private javax.swing.JLabel lblTABONOS;
    private javax.swing.JLabel lblTAHORROS;
    private javax.swing.JLabel lblTCAJA;
    private javax.swing.JTable tblNOMINAS;
    private javax.swing.JTextField txtANO;
    private javax.swing.JTextField txtBUSCAR;
    private javax.swing.JTextField txtSEMANA;
    // End of variables declaration//GEN-END:variables

}

