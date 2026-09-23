
/*
 * RegClientes.java
 *
 * Created on 11-nov-2013, 18:18:42
 */
package VISTA;


import CONTROLADOR.ControlContratos;
import CONTROLADOR.ControlOrdenesP;
import CONTROLADOR.ControlPendientes;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloContratos;
import MODELO.ModeloPendientes;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author KIKE FRENCH
 */
public final class ActualizaEstados extends javax.swing.JDialog 
{
    private String ESTADO, ESTADOO, TICKET, FACTURA;
    private int IDPENDIENTE, IDORDEN;
    
    /** Creates new form RegClientes
     * @param parent
     * @param modal
     * @throws java.text.ParseException */
    public ActualizaEstados(java.awt.Frame parent, boolean modal) throws ParseException {
        super(parent, modal);
        initComponents();
        this.Bar1.setFloatable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/refresh1.png")); 
        setIconImage(icon);
        setLocationRelativeTo(null);
        F.teclas(this);
    }
    
    public void mostrar(int IDPENDIENTE, int IDORDEN) throws ClassNotFoundException, SQLException, Exception
    {
        this.IDPENDIENTE = IDPENDIENTE;
        this.IDORDEN = IDORDEN;
        ModeloPendientes mdlP;
        ControlPendientes ctrlP = new ControlPendientes();
        mdlP = ctrlP.getRegistroID(IDPENDIENTE);
        TICKET = mdlP.getNPEDIDO();
        FACTURA  = mdlP.getNPEDIDOF();
        this.txtSERVICIO.setSelectedItem(mdlP.getESTADO());     
        
        desicion();
    }

    private void guardar() throws ClassNotFoundException, SQLException, Exception
    {
        desicion();
        
        ControlPendientes ctrlP = new ControlPendientes();
        ctrlP.actualizarEstado(ESTADO, IDPENDIENTE);
        
        String FFIN = G.FECHA(G.hoy(),false);
        if(!"ABIERTO".equals(ESTADO))
        {
            if(!ESTADO.equals("FINALIZADO"))
            {
                ControlPendientes ctrlp1 = new ControlPendientes();
               
                ctrlp1.actualizarFFIN(FFIN, IDPENDIENTE);

                ControlOrdenesP ctrlOPF = new ControlOrdenesP();
                ctrlOPF.actualizarFFIN(FFIN, IDORDEN);
            }
            else  
                if(TICKET.equals("") && FACTURA.equals(""))                
                    JOptionPane.showMessageDialog( null, "NO SE PUEDE FINALIZAR SIN UN TICKET/FACTURA", "ATENCION", JOptionPane.INFORMATION_MESSAGE);
                else
                {
                    ControlPendientes ctrlp1 = new ControlPendientes();
                    FFIN = G.FECHA(G.hoy(),false);
                    ctrlp1.actualizarFFIN(FFIN, IDPENDIENTE);

                    ControlOrdenesP ctrlOPF = new ControlOrdenesP();
                    ctrlOPF.actualizarFFIN(FFIN, IDORDEN);
                    
                    //FORMATO DE FECHAS, LO SEPARAMOS APRA PODER FORMATEAR EN CADA TECNOLOGIA
                    String D1;
                    int OPN, CONT;
                    String []datos=TICKET.split("-");        

                    D1="";
                    OPN=0;
                    CONT=0;

                    D1=datos[0];
                    OPN=Integer.parseInt(datos[1]);
                    CONT=Integer.parseInt(datos[2]);
                    
                    int eleccion;
                    ControlContratos ctrlC;
                            
                    if(D1.equals("DHN"))
                    {
                        switch(OPN)
                        {
                            case 0:
                                eleccion = JOptionPane.showConfirmDialog(null, "SE CAMBIO EL MODEM  ?");
                                if(eleccion == 1)
                                {
                                    RegOrdenesPD.btnNEXT.doClick();
                                }
                                break;
                            case 1:
                                eleccion = JOptionPane.showConfirmDialog(null, "SERVICIO REPARADO?");
                                if(eleccion == 1)
                                {
                                    RegOrdenesPD.btnNEXT.doClick();
                                }
                                break;
                            case 2:
                                eleccion = JOptionPane.showConfirmDialog(null, "SE RETIRO EL EQUIPO DEL SITIO?");
                                if(eleccion == 0)
                                {
                                    ctrlC = new ControlContratos();
                                    ctrlC.actualizarEstado("CANCELADO", CONT, "EQUIPO RETIRADO EL DIA "+G.FECHA(G.hoy(),false));
                                }
                                break;
                            case 3:
                                eleccion = JOptionPane.showConfirmDialog(null, "SE INSTALO EL EQUIPO?");
                                switch(eleccion)
                                {
                                    case 0:
                                        ctrlC = new ControlContratos();
                                        ctrlC.actualizarEstado("ACTIVO", CONT, "");
                                        
                                        ctrlC = new ControlContratos();
                                        ctrlC.activarContrato(CONT, FFIN, FFIN, "ACTIVO");
                                        
                                        abrir(CONT);
                                        
                                        this.dispose();
                                        break;
                                    case 1:
                                        RegOrdenesPD.btnNEXT.doClick();
                                        break;
                                    case 2:
                                        ctrlC = new ControlContratos();
                                        ctrlC.actualizarEstado("CANCELADO", CONT, "CANCELADO - NO INSTALADO");
                                        break;
                                    default:
                                        break;
                                }                                 
                                break;
                            default:
                                break;
                        }                        
                    }
                }                
        }
        if(IDORDEN>0)
        {
            ControlOrdenesP ctrlOP = new ControlOrdenesP();
            ctrlOP.actualizarEstado(ESTADOO, IDORDEN);
            
        }
        else
            JOptionPane.showMessageDialog( null, "SERVICIO SIN ORDEN DE TRABAJO, SE CERRAR EL SERVICIO PERO JAMAS SE GENERO UNA ORDEN DE TRABAJO", "ATENCION", JOptionPane.INFORMATION_MESSAGE);
                               
    }
    
    private void desicion()
    {
        ESTADO = txtSERVICIO.getSelectedItem().toString();
        switch(ESTADO)
        {
            case "ABIERTO":                
                ESTADOO="ABIERTO";                
                break;
            case "SIN TICKET":
                ESTADOO="CERRADO";
                break;
            case "FINALIZADO":
                ESTADOO="CERRADO";
                break;
            case "CANCELADO":
                ESTADOO="CERRADO";
                break;
            default: 
                break;
        }
    }
    
    private void abrir(int IDCONTRATO) throws ParseException, Exception
    {        
        JInternalFrame ji = validador.getJInternalFrame(VRegPagos.class.getName());
        if(ji != null )
            ji.dispose();                   
        ModeloContratos mdl;
        ControlContratos ctrl = new ControlContratos();
        mdl = ctrl.getRegistrosPAGO(IDCONTRATO);                        
        ji = new VRegPagos(IDCONTRATO,"NUEVO",mdl.getDIAS(),mdl.getNOMBRE(),""+mdl.getCOSTO(),"",false);
        Principal.getCONT().add(ji);
        validador.addJIframe(VRegPagos.class.getName(), ji);
                
        ji.setMaximum(true);
        
        ji.setVisible(true);
        ji.moveToFront();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSERVICIO = new javax.swing.JComboBox<>();
        Bar1 = new javax.swing.JToolBar();
        btnGUARDAR = new javax.swing.JButton();
        btnCANCEL = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Actualizar estado");
        setResizable(false);

        txtSERVICIO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSERVICIO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ABIERTO", "SIN TICKET", "FINALIZADO", "CANCELADO", "-COTIZACION-" }));
        txtSERVICIO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado de SERVICIO:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        Bar1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnGUARDAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disk.png"))); // NOI18N
        btnGUARDAR.setText("GUARDAR");
        btnGUARDAR.setToolTipText("GUARDAR");
        btnGUARDAR.setFocusable(false);
        btnGUARDAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGUARDAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGUARDARActionPerformed(evt);
            }
        });
        Bar1.add(btnGUARDAR);

        btnCANCEL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCANCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnCANCEL.setText("CERRAR");
        btnCANCEL.setToolTipText("CERRAR");
        btnCANCEL.setFocusable(false);
        btnCANCEL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCANCEL.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCANCELActionPerformed(evt);
            }
        });
        Bar1.add(btnCANCEL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
            .addComponent(txtSERVICIO, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtSERVICIO, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Bar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnGUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGUARDARActionPerformed
        try {
            guardar();
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(ActualizaEstados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ActualizaEstados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGUARDARActionPerformed

    private void btnCANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCANCELActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCANCELActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizaEstados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    ActualizaEstados dialog = new ActualizaEstados(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(ActualizaEstados.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar1;
    private javax.swing.JButton btnCANCEL;
    private javax.swing.JButton btnGUARDAR;
    private javax.swing.JComboBox<String> txtSERVICIO;
    // End of variables declaration//GEN-END:variables
}
