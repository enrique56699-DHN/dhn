/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor..
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;

import CONTROLADOR.ControlContratos;
import CONTROLADOR.ControlPagos;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloContratos;
import MODELO.ModeloPagos;
import MODELO.ModeloPagosTabla;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author KIKE FRENCH
 */
public class VPagos extends javax.swing.JInternalFrame {
    
    private String IDRECIBO,FECHA,METODO,F_CORTE,TITULAR,COMENTARIOS,D_CORTE;
    private int IDNOTA,IDCONTRATO;
    private float monto;
    
    private String DIRECCION;

    /** Creates new form VClientes*/
    public VPagos(){                
    }
    
public VPagos(String titulo) throws ClassNotFoundException, SQLException {                
        initComponents();
        this.setTitle(titulo);
        permisos(Principal.PRIVILEGIOS);
        actualizarTablas();
        this.Bar.setFloatable(false);
        F.teclas(this);
        
    }
    
    private void permisos(String PRIVILEGIOS)
    {
        if(PRIVILEGIOS.equals("SADMIN"))
        {
            btnEDITAR.setEnabled(true);
            //btnCANCELAR.setEnabled(true);*/
        }
    }
    
    private void accion(String ACCION) throws Exception
    {
        RegPagos p = new RegPagos(null,true);
        p.mostrar(IDNOTA,ACCION,F_CORTE);
        p.setVisible(true);
    }    
        
    private void seleccion() throws ClassNotFoundException, SQLException, Exception
    {
        int fila = tblPAGOS.getSelectedRow();
        IDRECIBO = tblPAGOS.getValueAt(fila, 0).toString();
        IDNOTA = Integer.parseInt(IDRECIBO);  
        IDCONTRATO = Integer.parseInt(tblPAGOS.getValueAt(fila, 2).toString());
        TITULAR = tblPAGOS.getValueAt(fila, 1).toString();
        
        ModeloContratos mdl;
        ControlContratos ctrl = new ControlContratos();
        mdl=ctrl.getRegistrosPAGO(IDCONTRATO);
        F_CORTE=mdl.getFCOR();
    }
    
    private void direccion() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloContratos mdlC;
        ControlContratos ctrlC = new ControlContratos();
        mdlC = ctrlC.DIRECCION(IDCONTRATO);
        this.DIRECCION = mdlC.getCALLE()+" "+ mdlC.getNUMERO()+" "+mdlC.getCOLONIA()+" "+mdlC.getLOCALIDAD();
        
    }
    
    private void imprimir() throws IOException, ClassNotFoundException, SQLException, Exception
    {
        seleccion();                
        
        ModeloPagos mdl;
        ControlPagos ctrl = new ControlPagos();
        mdl = ctrl.getRegistrosIDR(IDNOTA);
        this.FECHA=mdl.getFECHA();
        this.IDCONTRATO=mdl.getIDCONTRATO();
        this.METODO=mdl.getMETODO();
        this.monto=mdl.getMONTO();
        this.COMENTARIOS=mdl.getCOMENTARIOS();
        this.TITULAR=mdl.getNOMBRE();
        this.D_CORTE= mdl.getFCOR();        
        int COSTO = mdl.getCOSTO();
        //CALCULAR DIAS
        Date inicio,fin;
        long dif,difm;
        
        //fecha=formateador.format(hoy);  
        inicio = G.formato.parse(D_CORTE);
        fin=G.hoy();
        difm = inicio.getTime() - fin.getTime();
        dif = TimeUnit.DAYS.convert(difm, TimeUnit.MILLISECONDS);
        int DIAS= (int) dif;
        
        //calcula adeudo
        int mes, adeudo;
        
        mes=(int)(DIAS/30);
        //System.out.println("ESTE ES EL MES "+mes);
        if(DIAS>0)        
            adeudo=0;        
        else
        {            
            if (mes<1)
            {
                mes=mes-1;
                //System.out.println("ESTE ES EL MES PERO YA MULTIPLICADO "+mes);
            }            
        }
                
        adeudo = COSTO*mes;       
        
        direccion();
        
        ImpPago pca = new ImpPago(IDRECIBO, FECHA, IDCONTRATO, METODO, "$ "+monto, COMENTARIOS, TITULAR,D_CORTE,DIRECCION);
        JButton[] opciones = pca.getButtons();
        JOptionPane.showInternalOptionDialog(this, pca, "Imprimir PAGO",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
        if(pca.getOpcion() != null)
            pca.Imprimir();
    }
    
    private void actualizarTablas() throws ClassNotFoundException, SQLException
    {
        ModeloPagosTabla mtpn;
        mtpn = new ModeloPagosTabla();
        tblPAGOS.setModel(mtpn);
        F.dimensionar(tblPAGOS,"PAGOS");
    }
    
    private void abrir()
    {
        int fila = tblPAGOS.getSelectedRow();
        String NARCHIVO = tblPAGOS.getValueAt(fila, 7).toString();
        String rutaETIQUETA = G.rutaServidor+"VOUCHER\\"+NARCHIVO;
        F.abrirArchivo(rutaETIQUETA);
        
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
        btnActualizar = new javax.swing.JButton();
        btnEXCEL = new javax.swing.JButton();
        btnEDITAR = new javax.swing.JButton();
        btnABRIR = new javax.swing.JButton();
        btnIMPRIMIR = new javax.swing.JButton();
        jspPAGOS = new javax.swing.JScrollPane();
        tblPAGOS = new javax.swing.JTable();
        txtBUSCAR = new javax.swing.JTextField();

        setClosable(true);
        setMaximizable(true);
        setTitle("Pagos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/coins.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        Bar.setRollover(true);

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
        btnEXCEL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEXCEL.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEXCELActionPerformed(evt);
            }
        });
        Bar.add(btnEXCEL);

        btnEDITAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEDITAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_edit.png"))); // NOI18N
        btnEDITAR.setText("EDITAR/BORRAR");
        btnEDITAR.setToolTipText("Editar y Borrar");
        btnEDITAR.setFocusable(false);
        btnEDITAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEDITAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEDITAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEDITARActionPerformed(evt);
            }
        });
        Bar.add(btnEDITAR);

        btnABRIR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnABRIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cuentas1.png"))); // NOI18N
        btnABRIR.setText("ABRIR");
        btnABRIR.setToolTipText("Abrir Documento");
        btnABRIR.setFocusable(false);
        btnABRIR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnABRIR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnABRIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnABRIRActionPerformed(evt);
            }
        });
        Bar.add(btnABRIR);

        btnIMPRIMIR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnIMPRIMIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer.png"))); // NOI18N
        btnIMPRIMIR.setText("IMPRIMIR");
        btnIMPRIMIR.setToolTipText("Reimprimir");
        btnIMPRIMIR.setFocusable(false);
        btnIMPRIMIR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIMPRIMIR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIMPRIMIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIMPRIMIRActionPerformed(evt);
            }
        });
        Bar.add(btnIMPRIMIR);

        jspPAGOS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblPAGOS.setAutoCreateRowSorter(true);
        tblPAGOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblPAGOS.setName("tblPAGOS"); // NOI18N
        tblPAGOS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPAGOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPAGOSMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPAGOSMousePressed(evt);
            }
        });
        jspPAGOS.setViewportView(tblPAGOS);

        txtBUSCAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBUSCAR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtBUSCAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBUSCARKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspPAGOS, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            .addComponent(txtBUSCAR)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspPAGOS, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            actualizarTablas();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tblPAGOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPAGOSMouseClicked
        try {
            seleccion();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VPagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblPAGOSMouseClicked

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        F.MAYUS(evt);
        F.buscar(tblPAGOS, txtBUSCAR.getText(),null,0);
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void btnIMPRIMIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIMPRIMIRActionPerformed
        try 
        {
            imprimir();            
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VPagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIMPRIMIRActionPerformed

    private void btnEXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXCELActionPerformed
        F.exportarXLS(tblPAGOS,"\\REPORTES\\PAGOS");
    }//GEN-LAST:event_btnEXCELActionPerformed

    private void tblPAGOSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPAGOSMousePressed
        if (evt.getClickCount() > 1)
        {
            try {
                seleccion();
                F.viewBitacora("PAGOS",IDRECIBO, TITULAR, DIRECCION);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
            Logger.getLogger(VPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_tblPAGOSMousePressed

    private void btnEDITARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEDITARActionPerformed
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
            
    }//GEN-LAST:event_btnEDITARActionPerformed

    private void btnABRIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnABRIRActionPerformed
        abrir();
    }//GEN-LAST:event_btnABRIRActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnABRIR;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEDITAR;
    private javax.swing.JButton btnEXCEL;
    private javax.swing.JButton btnIMPRIMIR;
    private javax.swing.JScrollPane jspPAGOS;
    private javax.swing.JTable tblPAGOS;
    private javax.swing.JTextField txtBUSCAR;
    // End of variables declaration//GEN-END:variables

}

