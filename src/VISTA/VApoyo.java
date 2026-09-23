/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor..
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

/**
 *
 * @author KIKE FRENCH
 */
public class VApoyo extends javax.swing.JInternalFrame {

    private String CLIENTE, TEL, DIRECCION, DESCRIPCION, COMENTARIOS, CATEGORIA;
    private int IDORDENP, IDPENDIENTE;
    
    private Timer timer;
    private final int delay = 5000;
    private int cont=0;
    private String DE, ID, MONTO, EMPLEADO, reg, salida,regreso, diast,periodo,EMPLEADO1,EMPLEADO2;
    private JButton[] opciones;
    
    /** Creates new form VClientes
     * @param VENTANA
     * @param IDORDENP
     * @param IDPENDIENTE
     * @param CLIENTE
     * @param TEL
     * @param DIRECCION
     * @param DESCRIPCION     
     * @param COMENTARIOS
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException */
    
    public VApoyo(String VENTANA, int IDORDENP,int IDPENDIENTE,String CLIENTE,String TEL,String DIRECCION,String DESCRIPCION,String COMENTARIOS) throws ClassNotFoundException, SQLException, Exception {                
        initComponents();
        this.IDORDENP = IDORDENP;
        this.IDPENDIENTE = IDPENDIENTE;
        this.CLIENTE = CLIENTE;
        this.TEL = TEL;
        this.DIRECCION = DIRECCION;
        this.DESCRIPCION = DESCRIPCION;
        this.COMENTARIOS = COMENTARIOS;
        correr(VENTANA);
    }
    
    public VApoyo(String VENTANA, int IDORDENP,int IDPENDIENTE,String CLIENTE,String TEL,String DIRECCION,String DESCRIPCION,String COMENTARIOS,String EMPLEADO1, String EMPLEADO2) throws ClassNotFoundException, SQLException, Exception {                
        initComponents();
        this.IDORDENP = IDORDENP;
        this.IDPENDIENTE = IDPENDIENTE;
        this.CLIENTE = CLIENTE;
        this.TEL = TEL;
        this.DIRECCION = DIRECCION;
        this.DESCRIPCION = DESCRIPCION;
        this.COMENTARIOS = COMENTARIOS;
        this.EMPLEADO1 = EMPLEADO1;
        this.EMPLEADO2 = EMPLEADO2;
        correr(VENTANA);
    }
    
    public VApoyo() throws ClassNotFoundException, SQLException, Exception {                
        //initComponents();
    }
    
    public VApoyo(String VENTANA, String FECHAR, String FECHARS, String FECHARR, String DESCRIPCION, String EMPLEADO, String DIAS, String PERIODO)//7
    {
        initComponents();
        this.reg = FECHAR;
        this.salida = FECHARS;
        this.regreso = FECHARR;
        this.DESCRIPCION = DESCRIPCION; 
        this.EMPLEADO = EMPLEADO;
        this.diast = DIAS;
        this.periodo = PERIODO;
        correr(VENTANA);
    }

    public VApoyo(String VENTANA, String DE, String ID, String MONTO, String DESCRIPCION, String EMPLEADO)//6
    {
        initComponents();
        this.DE = DE;
        this.ID = ID;
        this.MONTO = MONTO;
        this.DESCRIPCION = DESCRIPCION;
        this.EMPLEADO = EMPLEADO;
        this.CATEGORIA = EMPLEADO;
        correr(VENTANA);
    }
    
    public VApoyo(String VENTANA, String DE, String ID, String MONTO, String EMPLEADO) {
        initComponents();
        this.DE = DE;
        this.ID = ID;
        this.MONTO = MONTO;
        this.EMPLEADO = EMPLEADO;        
        correr(VENTANA);
    }
    
    
    
    private void correr(String VENTANA){
        SwingUtilities.invokeLater(() -> {
            ActionListener action = null;
            if (cont == 0)
            {
                try {
                    imprimir(VENTANA);                
                    timer = new Timer(delay, action);
                    timer.setInitialDelay(0);
                    timer.start();
                } catch (SQLException ex) {
                    Logger.getLogger(VApoyo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(VApoyo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else            
                timer.stop();
            
            cont++;
        });
    }
    
    private void imprimir(String VENTANA) throws ClassNotFoundException, SQLException, Exception
    {   
        //JButton[] opciones;
        switch(VENTANA)
        {            
            case "ORDENP":
                ImpOrdenP pca = new ImpOrdenP(IDORDENP,IDPENDIENTE,CLIENTE,TEL,DIRECCION,DESCRIPCION,COMENTARIOS,"EDITAR",EMPLEADO1,EMPLEADO2);                  
                opciones = pca.getButtons();       
                JOptionPane.showInternalOptionDialog(this, pca, "Imprimir Orden de Trabajo PENDIENTES",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
                if(pca.getOpcion() != null)
                    pca.Imprimir();
                break;
            case "ORDENPN":
                ImpOrdenP pcaN = new ImpOrdenP(IDORDENP,IDPENDIENTE,CLIENTE,TEL,DIRECCION,DESCRIPCION,COMENTARIOS,"NUEVO",EMPLEADO1,EMPLEADO2);        
                opciones = pcaN.getButtons();       
                JOptionPane.showInternalOptionDialog(this, pcaN, "Imprimir Orden de Trabajo PENDIENTES",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
                if(pcaN.getOpcion() != null)
                    pcaN.Imprimir();
                break;
            case "PRESTAMO":
                ImpPRESTAMO pco = new ImpPRESTAMO(DE,ID,MONTO,DESCRIPCION,EMPLEADO);
                opciones = pco.getButtons();
                JOptionPane.showInternalOptionDialog(this, pco, "Imprimir PRESTAMOS",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
                if(pco.getOpcion() != null)
                    pco.Imprimir();
                break;
            case "ABONO":                    
                ImpABONO pcab = new ImpABONO(DE,ID,MONTO,DESCRIPCION,EMPLEADO);
                opciones = pcab.getButtons();
                JOptionPane.showInternalOptionDialog(this, pcab, "Imprimir PRESTAMO",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
                if(pcab.getOpcion() != null)
                    pcab.Imprimir();
                break;
            case "AHORRO":
                ImpAHORRO pcar = new ImpAHORRO(DE,ID,MONTO,EMPLEADO);
                opciones = pcar.getButtons();
                JOptionPane.showInternalOptionDialog(this, pcar, "Imprimir AHORRO",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
                if(pcar.getOpcion() != null)
                    pcar.Imprimir();
                break;
            case "ENTRADA":
                ImpENTRADA pcaE = new ImpENTRADA(DE,ID,MONTO,DESCRIPCION,CATEGORIA);
                opciones = pcaE.getButtons();
                JOptionPane.showInternalOptionDialog(this, pcaE, "Imprimir ENTRADAS",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
                if(pcaE.getOpcion() != null)
                    pcaE.Imprimir();
                break;
            case "SALIDA":
                ImpSALIDA pcaS = new ImpSALIDA(DE,ID,MONTO,DESCRIPCION,CATEGORIA);
                opciones = pcaS.getButtons();
                JOptionPane.showInternalOptionDialog(this, pcaS, "Imprimir ENTRADAS",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
                if(pcaS.getOpcion() != null)
                    pcaS.Imprimir();
                break;
            case "VACACION":
                ImpVACACIONES pcaV = new ImpVACACIONES(reg,salida,regreso,DESCRIPCION,EMPLEADO,diast, periodo);
                opciones = pcaV.getButtons();
                JOptionPane.showInternalOptionDialog(this, pcaV, "Imprimir VACACIONES",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
                if(pcaV.getOpcion() != null)            
                    pcaV.Imprimir();                  
                break;
            case "VALES":
                ImpVALES pcaVa = new ImpVALES(DE,ID,MONTO,DESCRIPCION,CATEGORIA);
                opciones = pcaVa.getButtons();
                JOptionPane.showInternalOptionDialog(this, pcaVa, "Imprimir VALES",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
                if(pcaVa.getOpcion() != null)            
                    pcaVa.Imprimir();
                break;
            case "EXTRAS":
                ImpEXTRAS pcaEX = new ImpEXTRAS(DE,ID,MONTO,DESCRIPCION,CATEGORIA);
                opciones = pcaEX.getButtons();
                JOptionPane.showInternalOptionDialog(this, pcaEX, "Imprimir EXTRAS",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
                if(pcaEX.getOpcion() != null)            
                    pcaEX.Imprimir();
                break;    
            case "CF":
                ImpCF pcaCF = new ImpCF(DE,ID,MONTO,CATEGORIA);
                opciones = pcaCF.getButtons();
                JOptionPane.showInternalOptionDialog(this, pcaCF, "Imprimir CF",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
                if(pcaCF.getOpcion() != null)          
                    pcaCF.Imprimir();
                break;
                default:
                    JOptionPane.showMessageDialog( null, "NO SE GENERO LA IMPRESION", "ATENCION", JOptionPane.INFORMATION_MESSAGE);          
                    break;
            }        
        this.dispose();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCANCELAR = new javax.swing.JButton();
        btnIMPRIMIR = new javax.swing.JButton();

        btnCANCELAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCANCELAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnCANCELAR.setText("CANCELAR");
        btnCANCELAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCANCELAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnIMPRIMIR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnIMPRIMIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer.png"))); // NOI18N
        btnIMPRIMIR.setText("IMPRIMIR");
        btnIMPRIMIR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIMPRIMIR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIMPRIMIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIMPRIMIRActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Impresiones");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 403, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIMPRIMIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIMPRIMIRActionPerformed
        try {
            ImpOrdenP pca = new ImpOrdenP(IDORDENP,IDPENDIENTE,CLIENTE,TEL,DIRECCION,DESCRIPCION,COMENTARIOS,"EDITAR",EMPLEADO1,EMPLEADO2);
            opciones = pca.getButtons();
            JOptionPane.showInternalOptionDialog(this, pca, "Imprimir ENTRADAS",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
            if(pca.getOpcion() != null)            
                pca.Imprimir();            
        } catch (SQLException | IOException | ClassNotFoundException | BarcodeException | OutputException ex) {
            Logger.getLogger(VApoyo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VApoyo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIMPRIMIRActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCANCELAR;
    private javax.swing.JButton btnIMPRIMIR;
    // End of variables declaration//GEN-END:variables

}

