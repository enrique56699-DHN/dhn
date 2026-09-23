/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor..
 */
package VISTA;

import CONTROLADOR.ControlEmpleados;
import CONTROLADOR.ControlOrdenesP;
import CONTROLADOR.ControlPendientes;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloOrdenesP;
import MODELO.ModeloPendientes;
import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

/**
 *
 * @author Oscar E Gonzalez G
 */
public final class ImpOrdenP extends javax.swing.JPanel implements Printable
{
    private JOptionPane op;
    private final JViewport vp = new JViewport();
    private Integer opcion;
    //private final ModeloEmpleadosTabla met;
    //private ModeloOrdenesP mo;
    private String FINI, ACCION;
    private int IDEMP,IDPENDIENTE,IDORDENP;
    
    /**
     * Creates new form ImpPago
     * @param ultimo
     * @param IDPENDIENTE
     * @param CLIENTE
     * @param TEL
     * @param DIRECCION
     * @param DESCRIPCION
     * @param COMENTARIOS     
     * @param ACCION
     * @param EMPLEADO1
     * @param EMPLEADO2
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws net.sourceforge.barbecue.BarcodeException
     * @throws java.io.IOException
     * @throws net.sourceforge.barbecue.output.OutputException
     */ 
   public ImpOrdenP(int ultimo,int IDPENDIENTE,String CLIENTE, String TEL, String DIRECCION, String DESCRIPCION, String COMENTARIOS, String ACCION, String EMPLEADO1, String EMPLEADO2) throws ClassNotFoundException, SQLException, BarcodeException, IOException, OutputException, Exception {
        vp.setBackground(Color.WHITE);
        //met = new ModeloEmpleadosTabla("ACTIVO","ASC");            
        initComponents();                          
        
        //this.FINI=""+formato.format(fecha);
        datos(IDPENDIENTE);
        this.IDORDENP = ultimo;
        this.lblFECHA.setText(FINI);
        this.lblDORDEN.setText(""+IDORDENP);
        //this.lblNPENDIENTE.setText(""+NPENDIENTE);
        this.lblCLIENTE.setText(CLIENTE);
        this.lblTEL.setText(TEL);
        this.txtDIRECCION.setText(DIRECCION);
        this.txtDESCRIPCION.setText(DESCRIPCION);
        this.txtCOMENTARIOS.setText(COMENTARIOS);
        this.IDPENDIENTE=IDPENDIENTE;
        this.lblEMPLEADO1.setText(EMPLEADO1);
        this.lblEMPLEADO2.setText(EMPLEADO2);
        codigo(IDPENDIENTE);
        this.ACCION = ACCION;
        //empleados();
        
   }
   
   private void datos(int IDPENDIENTE) throws ClassNotFoundException, SQLException, Exception
   {
       ModeloPendientes mdl;
       ControlPendientes ctrl = new ControlPendientes();
       mdl= ctrl.getRegistroID(IDPENDIENTE);
       FINI = mdl.getFEST();
   }
   
   private void codigo(int IDPENDIENTE) throws BarcodeException, IOException, OutputException
   {
        lblCODIGO.setText("");
        
        Barcode bar = BarcodeFactory.createCode128C(""+IDPENDIENTE);
        //Barcode bar = BarcodeFactory.parseEAN128(""+IDPENDIENTE);
        File f = new File(G.rutaLocal + "\\CODIGO.png");
        BarcodeImageHandler.savePNG(bar, f);
            
            //Thread.sleep(200);
        //Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/prestamos.png"));
        //ImageIcon ico1 = new ImageIcon(getClass().getResource(appPath + "\\CODIGO.png"));
        ImageIcon ico1 = new ImageIcon(G.rutaLocal + "\\CODIGO.png");
        ImageIcon ico2 = new ImageIcon(ico1.getImage().getScaledInstance(150, 40, 
                Image.SCALE_DEFAULT));
        
        lblCODIGO.setIcon(ico2);
   }

    private void guardar(String ACCION) throws Exception
    { 
        ModeloOrdenesP mo;
        ControlEmpleados ce = new ControlEmpleados();
        IDEMP = ce.getRegistros(lblEMPLEADO1.getText());
        ControlOrdenesP co = new ControlOrdenesP();
        mo = new ModeloOrdenesP();
        mo.setIDORDENP(IDORDENP);
        mo.setIDEMPLEADO(IDEMP);
        mo.setIDPENDIENTE(IDPENDIENTE);
        mo.setFINI(FINI);
        mo.setFFIN("");
        mo.setESTADO("ABIERTO");
        mo.setCOMENTARIOS(lblEMPLEADO2.getText());
        
        switch(ACCION)
        {
            case "NUEVO":
                 co.guardarRegistro(mo);
                 break;
            case "EDITAR":
                co.actualizarRegistro(mo);
                break;
            default:
                break;
        }   
        ControlPendientes cp = new ControlPendientes();
        cp.actualizarEstado("ABIERTO",IDPENDIENTE);
    }
    
    /*private void empleados()
    {
        txtEMPLEADO.removeAllItems();
        txtEMPLEADO1.removeAllItems();
        int NUM = met.getRowCount();
        for(int i=0;i<NUM;i++)
        {
            txtEMPLEADO.addItem(""+ met.getValueAt(i, 1));
            txtEMPLEADO1.addItem(""+ met.getValueAt(i, 1));
        }
        //this.txtDIRECCION.setEditable(false);
    }*/
    
    private void getOptionPane()
    {
        if(op != null)
        {
            return;
        }
        Component pdr =this.getParent(); 
        while(pdr.getParent() != null)
        {
            if(pdr instanceof JOptionPane)
            {
                op = (JOptionPane)pdr;
                break;
            }            
            pdr = pdr.getParent();
        }
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        try
        {
            if (pageIndex > 0)             
                    return NO_SUCH_PAGE;            
            Graphics2D g2d=  (Graphics2D)graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            this.printAll(graphics);
            return PAGE_EXISTS;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog( null, "NO HAY OBJETOS A IMPRIMIR", "ERROR DE IMPRESION", JOptionPane.ERROR_MESSAGE);
        }
        return PAGE_EXISTS;
    }
    
    public void Imprimir()
    {        
        F.Imprimir(this);    
    }    
    
    public Integer getOpcion() {
        return opcion;
    }
    
    public JButton[] getButtons()
    {
        return new JButton[]{bntImprimir,bntCancelar};
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bntImprimir = new javax.swing.JButton();
        bntCancelar = new javax.swing.JButton();
        lblLOGO = new javax.swing.JLabel();
        lblFECHA = new javax.swing.JLabel();
        lblDORDEN = new javax.swing.JLabel();
        lblCLIENTE = new javax.swing.JLabel();
        lblTEL = new javax.swing.JLabel();
        jspDIRECCION = new javax.swing.JScrollPane();
        txtDIRECCION = new javax.swing.JTextArea();
        jspDESCRIPCION = new javax.swing.JScrollPane();
        txtDESCRIPCION = new javax.swing.JTextArea();
        jspCOMENTARIOS = new javax.swing.JScrollPane();
        txtCOMENTARIOS = new javax.swing.JTextArea();
        lblCODIGO = new javax.swing.JLabel();
        lblEMPLEADO1 = new javax.swing.JLabel();
        lblEMPLEADO2 = new javax.swing.JLabel();

        bntImprimir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bntImprimir.setText("IMPRIMIR");
        bntImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntImprimirActionPerformed(evt);
            }
        });

        bntCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bntCancelar.setText("CANCELAR");
        bntCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCancelarActionPerformed(evt);
            }
        });

        setMaximumSize(new java.awt.Dimension(200, 300));
        setMinimumSize(new java.awt.Dimension(80, 210));

        lblLOGO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/DHN-SMALL.png"))); // NOI18N
        lblLOGO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblFECHA.setFont(new java.awt.Font("Tahoma", 0, 6)); // NOI18N
        lblFECHA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFECHA.setText("00/00/0000");
        lblFECHA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 6))); // NOI18N

        lblDORDEN.setFont(new java.awt.Font("Tahoma", 1, 5)); // NOI18N
        lblDORDEN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDORDEN.setText("00000");
        lblDORDEN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. Orden", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 6))); // NOI18N

        lblCLIENTE.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        lblCLIENTE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCLIENTE.setText("XXXXXXXXX XXXXXXXXXX ");

        lblTEL.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        lblTEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTEL.setText("1234567890");

        txtDIRECCION.setColumns(20);
        txtDIRECCION.setFont(new java.awt.Font("Tahoma", 0, 6)); // NOI18N
        txtDIRECCION.setLineWrap(true);
        txtDIRECCION.setRows(3);
        txtDIRECCION.setTabSize(7);
        txtDIRECCION.setWrapStyleWord(true);
        txtDIRECCION.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Direccion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 7))); // NOI18N
        txtDIRECCION.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jspDIRECCION.setViewportView(txtDIRECCION);

        txtDESCRIPCION.setColumns(20);
        txtDESCRIPCION.setFont(new java.awt.Font("Tahoma", 0, 6)); // NOI18N
        txtDESCRIPCION.setLineWrap(true);
        txtDESCRIPCION.setRows(3);
        txtDESCRIPCION.setTabSize(7);
        txtDESCRIPCION.setWrapStyleWord(true);
        txtDESCRIPCION.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descipcion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 7))); // NOI18N
        jspDESCRIPCION.setViewportView(txtDESCRIPCION);

        txtCOMENTARIOS.setColumns(20);
        txtCOMENTARIOS.setFont(new java.awt.Font("Tahoma", 0, 6)); // NOI18N
        txtCOMENTARIOS.setLineWrap(true);
        txtCOMENTARIOS.setRows(3);
        txtCOMENTARIOS.setTabSize(7);
        txtCOMENTARIOS.setWrapStyleWord(true);
        txtCOMENTARIOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comentarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 7))); // NOI18N
        jspCOMENTARIOS.setViewportView(txtCOMENTARIOS);

        lblCODIGO.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblCODIGO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCODIGO.setText("CODIGO");

        lblEMPLEADO1.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        lblEMPLEADO1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEMPLEADO1.setText("EMPLEADO1");

        lblEMPLEADO2.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        lblEMPLEADO2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEMPLEADO2.setText("EMPLEADO2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFECHA, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDORDEN, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblCLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblTEL, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblCODIGO, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jspCOMENTARIOS, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jspDESCRIPCION, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblEMPLEADO1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspDIRECCION, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(lblEMPLEADO2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblFECHA)
                        .addGap(6, 6, 6)
                        .addComponent(lblDORDEN)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEMPLEADO1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEMPLEADO2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTEL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jspDIRECCION, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspDESCRIPCION, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspCOMENTARIOS, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCODIGO)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bntImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntImprimirActionPerformed
        getOptionPane();
        opcion = 1;
        op.setValue(1);
        try {
            guardar(ACCION);
            F.copiarPanel(this);
        } catch (Exception ex) {
            Logger.getLogger(ImpOrdenP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntImprimirActionPerformed

    private void bntCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelarActionPerformed
        getOptionPane();
        op.setValue(0);
        F.copiarPanel(this);
    }//GEN-LAST:event_bntCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bntCancelar;
    public javax.swing.JButton bntImprimir;
    public javax.swing.JScrollPane jspCOMENTARIOS;
    public javax.swing.JScrollPane jspDESCRIPCION;
    public javax.swing.JScrollPane jspDIRECCION;
    public javax.swing.JLabel lblCLIENTE;
    public javax.swing.JLabel lblCODIGO;
    public javax.swing.JLabel lblDORDEN;
    public javax.swing.JLabel lblEMPLEADO1;
    public javax.swing.JLabel lblEMPLEADO2;
    public javax.swing.JLabel lblFECHA;
    public javax.swing.JLabel lblLOGO;
    public javax.swing.JLabel lblTEL;
    public javax.swing.JTextArea txtCOMENTARIOS;
    public javax.swing.JTextArea txtDESCRIPCION;
    public javax.swing.JTextArea txtDIRECCION;
    // End of variables declaration//GEN-END:variables
}
