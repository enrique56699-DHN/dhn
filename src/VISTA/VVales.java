/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;


import CONTROLADOR.ControlVales;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloVales;
import MODELO.ModeloValesTabla;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author KIKE FRENCH
 */
public final class VVales extends javax.swing.JInternalFrame {

    private int IDVALE;
    private String FECHA, DESCRIPCION, MONTO,TIPO;
    private boolean ESTADO;
    
    /** Creates new form VClientes*/
    public VVales(){
         
    }
    
    public VVales(String titulo) throws ClassNotFoundException, SQLException, Exception {            
        initComponents();
        this.setTitle(titulo);
        permisos(Principal.PRIVILEGIOS);
        actualizarTablas();
        this.Bar.setFloatable(false);
    }
    
    private void permisos(String PRIVILEGIOS)
    {
        if(PRIVILEGIOS.equals("SADMIN"))
        {
            btnEDITAR.setEnabled(true);
            btnBORRAR.setEnabled(true);
        }
    }
    
    private void actualizarTablas() throws ClassNotFoundException, SQLException, Exception
    {
        switch(txtESTADO.getSelectedItem().toString())
        {
            case "PENDIENTES":
                TIPO = "CARGO";
                ESTADO = false;
                break;
            case "APLICADOS":
                TIPO = "CARGO";
                ESTADO = true;
                break;
            case "CONTRAVALES":
                TIPO = "ABONO";
                ESTADO = true;
                break;
            case "TODOS":
                TIPO = "TODOS";
                ESTADO = false;
                break;
            default:
                break;
        }        
        
        ModeloValesTabla met;
        met = new ModeloValesTabla(0,TIPO,ESTADO);
        tblTABLA.setModel(met);
        F.dimensionar(tblTABLA,"VALES");
        
        if(!"TODOS".equals(TIPO))
            this.lblTOTAL.setText(G.numero.format(F.monto("VALES","TIPO", TIPO,"ESTADO",ESTADO)));
        else
            this.lblTOTAL.setText("00000");
    }

    private void accion(String ACCION,String TIPO) throws ClassNotFoundException, SQLException, Exception
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
                            F.eliminarRegistro(IDVALE, "VALES", "IDVALE");
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
                        IDVALE = 0;                    
                    RegVales p = new RegVales(null,true);
                    p.mostrar(IDVALE,ACCION,TIPO);
                    p.setVisible(true);

                }
                catch (ParseException ex)
                {
                    JOptionPane.showMessageDialog( null, "PARA PODER EDITAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
                }
                actualizarTablas();
            }
        }
        catch (ParseException ex)
        {
            JOptionPane.showMessageDialog( null, "PARA PODER EDITAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    private void seleccion() throws ClassNotFoundException, SQLException, Exception
    {
        int fila = tblTABLA.getSelectedRow();
        IDVALE = (int) tblTABLA.getValueAt(fila, 0);
        FECHA = tblTABLA.getValueAt(fila, 1).toString();
        DESCRIPCION = tblTABLA.getValueAt(fila, 2).toString();
        TIPO = tblTABLA.getValueAt(fila, 3).toString();
        MONTO = tblTABLA.getValueAt(fila, 4).toString().replaceAll("[$ ,]", "");        
        
    }
    
    private void imprimir() throws IOException, ClassNotFoundException, SQLException, Exception
    {
        seleccion();
        ImpVALES pca = new ImpVALES(FECHA,""+IDVALE,MONTO,DESCRIPCION,this.TIPO);
        JButton[] opciones = pca.getButtons();
        JOptionPane.showInternalOptionDialog(this, pca, "Imprimir VALES",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
        if(pca.getOpcion() != null)           
            pca.Imprimir();
    }
    
    private void contravale() throws IOException, ClassNotFoundException, SQLException, Exception
    {        
        ModeloVales mdl = new ModeloVales();
        
        mdl.setFECHA(G.FECHA(G.hoy(), true));
        mdl.setDESCRIPCION("CONTRAVALE DEL MOVIMIENTO: "+IDVALE);
        mdl.setTIPO("ABONO");
        mdl.setMONTO(Float.parseFloat(MONTO));
        mdl.setESTADO(true);
        
        ControlVales ctrl = new ControlVales();        
        ctrl.guardarRegistro(mdl);       
        
        F.actualizarEstado("VALES", "", true, IDVALE, "ESTADO", "IDVALE");
    }
    
    private void salida() throws ParseException, Exception
    {
        RegSalidas p = new RegSalidas(null,true);
        p.mostrar(0,"VALE",DESCRIPCION);
        p.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popmACCION = new javax.swing.JPopupMenu();
        jmmCONTRAVALE = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmmSALIDADHN = new javax.swing.JMenuItem();
        Bar = new javax.swing.JToolBar();
        btnCARGO = new javax.swing.JButton();
        btnEDITAR = new javax.swing.JButton();
        btnBORRAR = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEXCEL = new javax.swing.JButton();
        btnIMPRIMIR = new javax.swing.JButton();
        btnSALIDA = new javax.swing.JButton();
        btnCONTRAVALE = new javax.swing.JButton();
        jspTABLA = new javax.swing.JScrollPane();
        tblTABLA = new javax.swing.JTable();
        txtBUSCAR = new javax.swing.JTextField();
        txtESTADO = new javax.swing.JComboBox<>();
        lblTOTAL = new javax.swing.JLabel();

        jmmCONTRAVALE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCONTRAVALE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/contravale.png"))); // NOI18N
        jmmCONTRAVALE.setText("Generar Contravale");
        popmACCION.add(jmmCONTRAVALE);
        popmACCION.add(jSeparator1);

        jmmSALIDADHN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmSALIDADHN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/-.png"))); // NOI18N
        jmmSALIDADHN.setText("Generar Salida DHN");
        jmmSALIDADHN.setToolTipText("Generar salida en DHN y a la vez, contravale correspondiente");
        popmACCION.add(jmmSALIDADHN);

        setClosable(true);
        setMaximizable(true);
        setTitle("Vales");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vale.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        Bar.setRollover(true);

        btnCARGO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCARGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cargo.png"))); // NOI18N
        btnCARGO.setText("VALE");
        btnCARGO.setFocusable(false);
        btnCARGO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCARGO.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCARGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCARGOActionPerformed(evt);
            }
        });
        Bar.add(btnCARGO);

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

        btnBORRAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBORRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_delete.png"))); // NOI18N
        btnBORRAR.setText("BORRAR");
        btnBORRAR.setEnabled(false);
        btnBORRAR.setFocusable(false);
        btnBORRAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBORRAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBORRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBORRARActionPerformed(evt);
            }
        });
        Bar.add(btnBORRAR);

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
        btnIMPRIMIR.setFocusable(false);
        btnIMPRIMIR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIMPRIMIR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIMPRIMIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIMPRIMIRActionPerformed(evt);
            }
        });
        Bar.add(btnIMPRIMIR);

        btnSALIDA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSALIDA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/-.png"))); // NOI18N
        btnSALIDA.setText("SALIDA DHN");
        btnSALIDA.setToolTipText("Crea contravale y salida por monto personalizado en efectivo solo DHN");
        btnSALIDA.setFocusable(false);
        btnSALIDA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSALIDA.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSALIDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSALIDAActionPerformed(evt);
            }
        });
        Bar.add(btnSALIDA);

        btnCONTRAVALE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCONTRAVALE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/contravale.png"))); // NOI18N
        btnCONTRAVALE.setText("CONTRAVALE");
        btnCONTRAVALE.setToolTipText("Reingreo integro del monto");
        btnCONTRAVALE.setFocusable(false);
        btnCONTRAVALE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCONTRAVALE.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCONTRAVALE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCONTRAVALEActionPerformed(evt);
            }
        });
        Bar.add(btnCONTRAVALE);

        jspTABLA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblTABLA.setAutoCreateRowSorter(true);
        tblTABLA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTABLA.setComponentPopupMenu(popmACCION);
        tblTABLA.setName("tblTABLA"); // NOI18N
        tblTABLA.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTABLA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTABLAMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTABLAMousePressed(evt);
            }
        });
        jspTABLA.setViewportView(tblTABLA);

        txtBUSCAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBUSCAR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtBUSCAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBUSCARKeyTyped(evt);
            }
        });

        txtESTADO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtESTADO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDIENTES", "APLICADOS", "CONTRAVALES", "TODOS" }));
        txtESTADO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtESTADO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtESTADOActionPerformed(evt);
            }
        });

        lblTOTAL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTOTAL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTOTAL.setText("00000");
        lblTOTAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            .addComponent(jspTABLA)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtBUSCAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtESTADO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtESTADO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTOTAL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCARGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCARGOActionPerformed
        try {
            accion("NUEVO","CARGO");
        } catch (SQLException ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCARGOActionPerformed

    private void btnEDITARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEDITARActionPerformed
        try {
            seleccion();
            accion("EDITAR",TIPO);
        } catch (SQLException ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEDITARActionPerformed

    private void btnBORRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBORRARActionPerformed
        try {
            seleccion();
            accion("BORRAR",TIPO);
        } catch (SQLException ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBORRARActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            actualizarTablas();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tblTABLAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMouseClicked
        try {
            seleccion();
        } catch (SQLException ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTABLAMouseClicked

    private void btnEXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXCELActionPerformed
        F.exportarXLS(tblTABLA,"\\REPORTES\\VALES");
    }//GEN-LAST:event_btnEXCELActionPerformed

    private void btnIMPRIMIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIMPRIMIRActionPerformed
        try {
            imprimir();
            //this.dispose();
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIMPRIMIRActionPerformed

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        F.MAYUS(evt);
        F.buscar(tblTABLA, txtBUSCAR.getText(),null,0);
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void btnSALIDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSALIDAActionPerformed
        try {
            if(F.candado())
            {
                salida();
                seleccion();
                contravale();
                actualizarTablas();
            }            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSALIDAActionPerformed

    private void btnCONTRAVALEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCONTRAVALEActionPerformed
        try {
            if(F.candado())
            {
                seleccion();
                contravale();
                actualizarTablas();
            } 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCONTRAVALEActionPerformed

    private void txtESTADOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtESTADOActionPerformed
        try {
            actualizarTablas();
            txtBUSCAR.requestFocus();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VPendientesL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtESTADOActionPerformed

    private void tblTABLAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMousePressed
        try 
        {
            if(F.clickD(evt, tblTABLA))
                seleccion();
            else
                if(F.dosClick(evt, tblTABLA))
                {
                    seleccion();
                    F.clipboard(DESCRIPCION);
                }
            } 
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VVales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTABLAMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBORRAR;
    private javax.swing.JButton btnCARGO;
    private javax.swing.JButton btnCONTRAVALE;
    private javax.swing.JButton btnEDITAR;
    private javax.swing.JButton btnEXCEL;
    private javax.swing.JButton btnIMPRIMIR;
    private javax.swing.JButton btnSALIDA;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem jmmCONTRAVALE;
    private javax.swing.JMenuItem jmmSALIDADHN;
    private javax.swing.JScrollPane jspTABLA;
    private javax.swing.JLabel lblTOTAL;
    private javax.swing.JPopupMenu popmACCION;
    private javax.swing.JTable tblTABLA;
    private javax.swing.JTextField txtBUSCAR;
    private javax.swing.JComboBox<String> txtESTADO;
    // End of variables declaration//GEN-END:variables

}

