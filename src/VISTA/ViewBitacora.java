/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package VISTA;

import CONTROLADOR.ControlCotizaciones;
import CONTROLADOR.ControlPagos;
import CONTROLADOR.F;
import UTILERIAS.RenderCelda;
import MODELO.ModeloBitacoraTabla;
import MODELO.ModeloCotizaciones;
import MODELO.ModeloPagos;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.sql.SQLException;
import javax.swing.JTable;
/**
 *
 * @author Enrique Orozco Alcala
 */
public class ViewBitacora extends javax.swing.JDialog {

    /**
     * Creates new form Tools
     * @param parent
     * @param modal
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    
    public ViewBitacora(java.awt.Frame parent, boolean modal) throws ClassNotFoundException, SQLException {
        super(parent, modal);
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/bitacora.png")); 
        setIconImage(icon);
        setLocationRelativeTo(null);        
        this.lblNCONTRATO.setVisible(false);
        F.teclas(this);
    }
    
    public void mostrar(String TABLA, String SERVICIO, String NOMBRE, String DOMICILIO) throws SQLException, Exception
    {
        this.lblCLIENTE.setText(NOMBRE);
        this.lblDOMICILIO.setText(DOMICILIO);
        this.lblNSERVICIO.setText(SERVICIO);
        int REGISTRO = Integer.parseInt(SERVICIO);
        switch(TABLA)
        {            
            /*case "PAGOS":
                lblDOMICILIO.setVisible(false);
                break;*/
            case "COTIZACIONES":
                datos(TABLA,REGISTRO);
                break; 
            case "PAGOS":
                this.lblNCONTRATO.setVisible(true);
                datos(TABLA,REGISTRO);
                break;    
            case "MOTIVOSR":
                this.lblNCONTRATO.setVisible(true);
                datos("PAGOS",REGISTRO);
                break;
            default:
                break;
        }                
        actualizarTablas(TABLA,REGISTRO);
    }
    
    private void datos(String TABLA, int REGISTRO) throws ClassNotFoundException, SQLException, Exception
    {
        switch(TABLA)
        {
            case "COTIZACIONES":
                ModeloCotizaciones mdlC;
                ControlCotizaciones ctrlC = new ControlCotizaciones();
                mdlC = ctrlC.getRegistro("IDCOTIZACION", REGISTRO, "");
                this.lblCLIENTE.setText(mdlC.getNOMBRE());
                this.lblDOMICILIO.setText(mdlC.getCALLE()+" "+mdlC.getNUMERO()+" "+mdlC.getCOLONIA()+" "+mdlC.getLOCALIDAD());
                break;
            case "PAGOS":
                ModeloPagos mdl;
                ControlPagos ctrl = new ControlPagos();
                mdl = ctrl.getRegistrosDIR(REGISTRO);
                this.lblCLIENTE.setText(mdl.getNOMBRE());
                this.lblNCONTRATO.setText(mdl.getIDCONTRATO()+"");
                this.lblDOMICILIO.setText(mdl.getDIRECCION());
                break;
            default:
                break;
        }
    }
    
    private void actualizarTablas(String TABLA, int REGISTRO) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloBitacoraTabla mtc;
        mtc = new ModeloBitacoraTabla(TABLA, REGISTRO);
        tblTABLA.setModel(mtc);        
        mtc.fireTableDataChanged();
        dimensionar(tblTABLA,TABLA);
    }
    
    private void dimensionar(JTable tabla, String TABLA)
    {
        switch(TABLA)
        {
            case "PENDIENTES":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.getColumnModel().getColumn(4).setCellRenderer(new RenderCelda(true,true,"") );                    
                tabla.setRowHeight(75);   

                tabla.setAutoCreateRowSorter(true);
                break;
            case "COTIZACIONES":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(600);
                //tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderCelda(true,true,"") );
                //tabla.getColumnModel().getColumn(4).setCellRenderer(new RenderCelda(true,true,"") );                
                tabla.setRowHeight(75);   

                tabla.setAutoCreateRowSorter(true);
                break;
            case "PAGOS":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(700);
                tabla.getColumnModel().getColumn(1).setCellRenderer(new RenderCelda(true,true,"") );
                tabla.setRowHeight(75);   
                tabla.setAutoCreateRowSorter(true);
                break;
            case "MOTIVOSR":
                tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(800);
                break;
            default: 
                tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(2).setPreferredWidth(30);
                tabla.getColumnModel().getColumn(3).setPreferredWidth(300);
                tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
                break;
        }
                
    } 
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpCONFIG = new javax.swing.JPanel();
        lblCLIENTE = new javax.swing.JLabel();
        lblNSERVICIO = new javax.swing.JLabel();
        lblDOMICILIO = new javax.swing.JLabel();
        lblNCONTRATO = new javax.swing.JLabel();
        jspTABLA = new javax.swing.JScrollPane();
        tblTABLA = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bitacora");

        jpCONFIG.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Servicio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblCLIENTE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCLIENTE.setText("NOMBRE DEL CLIENTE");
        lblCLIENTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NOMBRE DEL CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblNSERVICIO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNSERVICIO.setText("000000");
        lblNSERVICIO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "#", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblDOMICILIO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDOMICILIO.setText("DOMICILIO");
        lblDOMICILIO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DOMICILIO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblNCONTRATO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNCONTRATO.setText("000000");
        lblNCONTRATO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contrato", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        javax.swing.GroupLayout jpCONFIGLayout = new javax.swing.GroupLayout(jpCONFIG);
        jpCONFIG.setLayout(jpCONFIGLayout);
        jpCONFIGLayout.setHorizontalGroup(
            jpCONFIGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCONFIGLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCONFIGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNSERVICIO)
                    .addComponent(lblNCONTRATO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCONFIGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDOMICILIO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCLIENTE, javax.swing.GroupLayout.DEFAULT_SIZE, 1204, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpCONFIGLayout.setVerticalGroup(
            jpCONFIGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCONFIGLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCONFIGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNSERVICIO)
                    .addComponent(lblCLIENTE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCONFIGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDOMICILIO)
                    .addComponent(lblNCONTRATO))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jspTABLA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bitacora de Movimientos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        tblTABLA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTABLA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTABLA.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspTABLA.setViewportView(tblTABLA);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 1290, Short.MAX_VALUE)
            .addComponent(jpCONFIG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpCONFIG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ViewBitacora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                ViewBitacora dialog = new ViewBitacora(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            } catch (ClassNotFoundException | SQLException ex) {
                java.util.logging.Logger.getLogger(ViewBitacora.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jpCONFIG;
    private javax.swing.JScrollPane jspTABLA;
    private javax.swing.JLabel lblCLIENTE;
    private javax.swing.JLabel lblDOMICILIO;
    private javax.swing.JLabel lblNCONTRATO;
    private javax.swing.JLabel lblNSERVICIO;
    private javax.swing.JTable tblTABLA;
    // End of variables declaration//GEN-END:variables
}
