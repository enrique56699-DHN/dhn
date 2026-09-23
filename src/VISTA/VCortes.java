/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;


import CONTROLADOR.ControlCortes;
import CONTROLADOR.ControlCortesDetalles;
import CONTROLADOR.ControlEntradas;
import CONTROLADOR.ControlPagos;
import CONTROLADOR.ControlSalidas;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloCortes;
import MODELO.ModeloCortesDetalles;
import MODELO.ModeloCortesTabla;
import MODELO.ModeloEntradasTabla;
import MODELO.ModeloPagosTabla;
import MODELO.ModeloSalidasTabla;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author KIKE FRENCH
 */
public final class VCortes extends javax.swing.JInternalFrame {

    private int IDCORTE;
    private int IDCAJA;

    /** Creates new form VClientes */
    public VCortes(){
        
    }
    
    public VCortes(String titulo) throws ClassNotFoundException, SQLException { 
        initComponents();
        this.setTitle(titulo);
        actualizarTablas();
        permisos(Principal.PRIVILEGIOS);
        this.Bar.setFloatable(false);
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowActivated(WindowEvent we)
            {
                try {
                    actualizarTablas();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(VCortes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
    private void permisos(String PRIVILEGIOS)
    {
        if(PRIVILEGIOS.equals("SADMIN"))
        {
            btnBORRAR.setEnabled(true);
        }
    }
    
    private void actualizarTablas() throws ClassNotFoundException, SQLException
    {
        ModeloCortesTabla met;
        met = new ModeloCortesTabla();
        tblCORTES.setModel(met);
        F.dimensionar(tblCORTES,"CORTES");
    }

    private void seleccion() throws ClassNotFoundException, SQLException, Exception
    {
        int fila = tblCORTES.getSelectedRow();
        IDCORTE = (int) tblCORTES.getValueAt(fila, 0);
        IDCAJA = (int) tblCORTES.getValueAt(fila, 7);
    }
    
    private void imprimir() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloCortesDetalles mdlD; 
        ControlCortesDetalles cc = new ControlCortesDetalles();
        mdlD=cc.getRegistrosID(IDCORTE);

        int IDPAGOI = mdlD.getIDPAGOI();
        int IDPAGOF = mdlD.getIDPAGOF();
        int IDENTRADAI = mdlD.getIDENTRADAI();
        int IDENTRADAF = mdlD.getIDENTRADAF();
        int IDSALIDAI = mdlD.getIDSALIDAI();
        int IDSALIDAF = mdlD.getIDSALIDAF();
        
        ModeloCortes mc;
        ControlCortes ct = new ControlCortes();
        mc=ct.getRegistrosID(IDCORTE);
        String DE = mc.getFINI();
        String AL = mc.getFFIN();
        float MONTOINI = mc.getMONTOINI();
                
        ControlEntradas ca = new ControlEntradas();
        float TENTRADAS = ca.suma(IDENTRADAI+1, IDENTRADAF, "EFECTIVO", IDCAJA);
        ca = new ControlEntradas();
        float TENTRADAST = ca.suma(IDENTRADAI+1, IDENTRADAF, "TARJETA", IDCAJA);
        
        ControlSalidas cs = new ControlSalidas();
        float TSALIDAS = cs.suma(IDSALIDAI+1, IDSALIDAF, "EFECTIVO", IDCAJA);
        
        ControlPagos cp = new ControlPagos();
        float TPAGOS = cp.suma(IDPAGOI+1, IDPAGOF, "EFECTIVO", IDCAJA);
        cp = new ControlPagos();
        float TPAGOST = cp.suma(IDPAGOI+1, IDPAGOF, "TARJETA", IDCAJA);
        
        float total = TPAGOS+TENTRADAS-TSALIDAS;
        float NTOTAL = MONTOINI+total;
        float ttarjetas = TENTRADAST + TPAGOST;
        
        ModeloPagosTabla mdlpt;
        mdlpt = new ModeloPagosTabla(IDPAGOI+1,IDPAGOF,"EFECTIVO",IDCAJA);
        ModeloEntradasTabla mdlet;
        mdlet = new ModeloEntradasTabla(IDENTRADAI+1,IDENTRADAF , "EFECTIVO",IDCAJA);
        ModeloSalidasTabla mdlst;
        mdlst = new ModeloSalidasTabla(IDSALIDAI+1,IDSALIDAF,"EFECTIVO",IDCAJA);
        try {
 
            ImpCorteDIA pca = new ImpCorteDIA(DE,AL,""+"$ "+G.numero.format(NTOTAL)+".00",mdlpt, mdlet, mdlst,
                    "$ "+G.numero.format(TPAGOS)+".00","$ "+G.numero.format(TENTRADAS)+".00","$ "+G.numero.format(TSALIDAS)+".00","$ "+G.numero.format(MONTOINI)+".00","$ "+G.numero.format(total)+".00",IDCORTE, ""+IDCAJA,
                    "$ "+G.numero.format(ttarjetas)+".00");         
            JButton[] opciones = pca.getButtons();
            JOptionPane.showInternalOptionDialog(this, pca, "Imprimir Corte de Caja",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
            if(pca.getOpcion() != null)            
                pca.Imprimir();            
        } catch (SQLException ex) {
            Logger.getLogger(ImpCorteDIA.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        btnBORRAR = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEXCEL = new javax.swing.JButton();
        btnIMPRIMIR = new javax.swing.JButton();
        jspCORTES = new javax.swing.JScrollPane();
        tblCORTES = new javax.swing.JTable();
        txtBUSCAR = new javax.swing.JTextField();

        setClosable(true);
        setMaximizable(true);
        setTitle("Cortes de Caja");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cajaR.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        Bar.setRollover(true);

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

        jspCORTES.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblCORTES.setAutoCreateRowSorter(true);
        tblCORTES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblCORTES.setName("tblCORTES"); // NOI18N
        tblCORTES.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCORTES.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCORTESMouseClicked(evt);
            }
        });
        jspCORTES.setViewportView(tblCORTES);

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
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            .addComponent(txtBUSCAR)
            .addComponent(jspCORTES)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspCORTES, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBORRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBORRARActionPerformed
        
    }//GEN-LAST:event_btnBORRARActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            actualizarTablas();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VCortes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tblCORTESMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCORTESMouseClicked
        try {
            seleccion();
        } catch (SQLException ex) {
            Logger.getLogger(VCortes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VCortes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblCORTESMouseClicked

    private void btnEXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXCELActionPerformed
        F.exportarXLS(tblCORTES,"\\REPORTES\\CORTES");
    }//GEN-LAST:event_btnEXCELActionPerformed

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        F.MAYUS(evt);
        F.buscar(tblCORTES, txtBUSCAR.getText(),null,0);
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void btnIMPRIMIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIMPRIMIRActionPerformed
        try {
            seleccion();
            imprimir();
        } catch (SQLException ex) {
            Logger.getLogger(VCortes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VCortes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIMPRIMIRActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBORRAR;
    private javax.swing.JButton btnEXCEL;
    private javax.swing.JButton btnIMPRIMIR;
    private javax.swing.JScrollPane jspCORTES;
    private javax.swing.JTable tblCORTES;
    private javax.swing.JTextField txtBUSCAR;
    // End of variables declaration//GEN-END:variables

}

