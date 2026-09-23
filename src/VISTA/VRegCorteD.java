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
import CONTROLADOR.ControlSalidas;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloCortes;
import MODELO.ModeloCortesDetalles;
import MODELO.ModeloEntradas;
import MODELO.ModeloEntradasTabla;
import MODELO.ModeloPagosTabla;
import MODELO.ModeloSalidas;
import MODELO.ModeloSalidasTabla;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author KIKE FRENCH
 */

public final class VRegCorteD extends javax.swing.JInternalFrame {
    
    /** Creates new form VRepPagos
     * @throws java.lang.Exception
        */
    private double monto;
    private double total;
    private String DE,AL;
    private double pagos, entradas, salidas,tarjeta,etarjeta,ttarjeta;
    private float MONTOINI ;
    private String FECHAINI ;
    private int IDPAGOI, IDENTRADAI, IDSALIDAI;
    private int CORTEANTERIOR;
    private float NTOTAL;
    private int NCORTE;
    /*private Locale locale = new Locale ("en", "US");
    private NumberFormat totalF = NumberFormat.getInstance (locale);
    private final int IDCAJA = Integer.parseInt(config.vector.get(16).toString());   */ 
    int b1000 = 0;
        int b500 = 0;
        int b200 = 0;
        int b100 = 0;
        int b50 = 0;
        int b20 = 0;
        int b10 = 0;
        int b5 = 0;
        int b2 = 0;
        int b1 = 0;
        
    private String TABLA, TITULAR, IDRECIBO;
    
    public VRegCorteD(){  
        
    }
    
    public VRegCorteD(String titulo) throws Exception{   
        initComponents();
        this.setTitle(titulo);
        datosINI();
        actualizarTabla();
        totales();        
        lblIDCAJA.setText(""+G.IDCAJA);
        this.Bar.setFloatable(false);          
        
    }
    
    private void contadorsh(int a)
    {
        String GETS;      
                        
        switch(a)
        {
            case 1000:
                GETS = txt1000.getText();
                if(GETS.equals(""))                
                    b1000 = 0;                
                else
                    b1000 = Integer.parseInt(GETS) * 1000;
                lbl1000.setText("$ "+G.numero.format(b1000)+".00");
                break;
            case 500:
                GETS = txt500.getText();
                if(GETS.equals(""))                
                    b500 = 0;                
                else
                    b500 = Integer.parseInt(GETS) * 500;
                lbl500.setText("$ "+G.numero.format(b500)+".00");
                break;
            case 200:
                GETS = txt200.getText();
                if(GETS.equals(""))                
                    b200 = 0;                
                else
                    b200 = Integer.parseInt(GETS) * 200;
                lbl200.setText("$ "+G.numero.format(b200)+".00");
                break;
            case 100:
                GETS = txt100.getText();
                if(GETS.equals(""))                
                    b100 = 0;                
                else
                    b100 = Integer.parseInt(GETS) * 100;
                lbl100.setText("$ "+G.numero.format(b100)+".00");
                break;
            case 50:
                GETS = txt50.getText();
                if(GETS.equals(""))                
                    b50 = 0;                
                else
                    b50 = Integer.parseInt(GETS) * 50;
                lbl50.setText("$ "+G.numero.format(b50)+".00");
                break;
            case 20:
                GETS = txt20.getText();
                if(GETS.equals(""))                
                    b20 = 0;                
                else
                    b20 = Integer.parseInt(GETS) * 20;
                lbl20.setText("$ "+G.numero.format(b20)+".00");
                break;
            case 10:
                GETS = txt10.getText();
                if(GETS.equals(""))               
                    b10 = 0;                
                else
                    b10 = Integer.parseInt(GETS) * 10;
                lbl10.setText("$ "+G.numero.format(b10)+".00");
                break;
            case 5:
                GETS = txt5.getText();
                if(GETS.equals(""))                
                    b5 = 0;                
                else
                    b5 = Integer.parseInt(GETS) * 5;
                lbl5.setText("$ "+G.numero.format(b5)+".00");
                break;
            case 2:
                GETS = txt2.getText();
                if(GETS.equals(""))                
                    b2 = 0;                
                else
                    b2 = Integer.parseInt(GETS) * 2;
                lbl2.setText("$ "+G.numero.format(b2)+".00");
                break;
            case 1:
                GETS = txt1.getText();
                if(GETS.equals(""))                
                    b1 = 0;                
                else
                    b1 = Integer.parseInt(GETS) * 1;
                lbl1.setText("$ "+G.numero.format(b1)+".00");
                break;
            default: break                    ;
        }
        
        int totalB = b1000 + b500 + b200 + b100 + b50 + b20 + b10 + b5 + b2 + b1;
        lblTOTALB.setText("$ "+G.numero.format(totalB)+".00");
        
    }

    private void actualizarTabla() throws ClassNotFoundException, SQLException, Exception
    {
        String METODO = "EFECTIVO";
        ModeloCortesDetalles mdl;
        //int ultimo = ControlCortesDetalles.Registro("DESC");
        ControlCortesDetalles ctrl = new ControlCortesDetalles();
        mdl=ctrl.getRegistrosID(CORTEANTERIOR);
        IDPAGOI = mdl.getIDPAGOF();
        IDENTRADAI = mdl.getIDENTRADAF();
        IDSALIDAI = mdl.getIDSALIDAF();
        
        ModeloPagosTabla mrpt;
        mrpt = new ModeloPagosTabla(IDPAGOI,METODO,G.IDCAJA);
        
        ModeloEntradasTabla mvtp;
        mvtp = new ModeloEntradasTabla(IDENTRADAI,METODO,G.IDCAJA);
        
        ModeloSalidasTabla mvte;
        mvte = new ModeloSalidasTabla(IDSALIDAI, METODO,G.IDCAJA);
        
        METODO = "TARJETA";
        
        ModeloPagosTabla mrptt;
        mrptt = new ModeloPagosTabla(IDPAGOI,METODO,G.IDCAJA);
        
        ModeloEntradasTabla mvtpt;
        mvtpt = new ModeloEntradasTabla(IDENTRADAI,METODO,G.IDCAJA);
        
        jtblENTRADAS.setModel(mvtp);
        jtblSALIDAS.setModel(mvte);
        jtblPAGOS.setModel(mrpt);
        tblTARJETAS.setModel(mrptt);
        tblETARJETAS.setModel(mvtpt);
        
        F.dimensionar(jtblPAGOS,"CORTESD");
        F.dimensionar(jtblENTRADAS,"CORTESD");
        F.dimensionar(jtblSALIDAS,"CORTESD");
        F.dimensionar(tblTARJETAS,"CORTESD");
        F.dimensionar(tblETARJETAS,"CORTESD");
        //totales();
    }
    
    private void totales()
    {
        lblPAGOS.setText("$ 0.00");
        lblENTRADAS.setText("$ 0.00");
        lblSALIDAS.setText("$ 0.00");        
        lblTOTAL.setText("$ 0.00");
        lblETARJETA.setText("$ 0.00");
        lblTARJETA.setText("$ 0.00");
        lblTOTALT.setText("$ 0.00");
        
        entradas=0;
        salidas=0;
        pagos=0; 
        tarjeta=0;
        etarjeta=0;
        ttarjeta=0;

        monto=0;
        total=0;               
        
        if(jtblPAGOS.getRowCount()>0)
        {
            for(int i = 0; i < jtblPAGOS.getRowCount(); i ++)
            {
                monto = Double.parseDouble(jtblPAGOS.getValueAt(i, 2).toString());
                total += monto;
            }
            pagos=total;
            lblPAGOS.setText("$ "+G.numero.format(pagos)+".00");
            
        }
        
        monto=0;
        total=0;
        if(jtblENTRADAS.getRowCount()>0)
        {
            for(int i = 0; i < jtblENTRADAS.getRowCount(); i ++)
            {
                monto = Double.parseDouble(jtblENTRADAS.getValueAt(i, 2).toString());
                total += monto;
            }
            entradas=total;
            lblENTRADAS.setText("$ "+G.numero.format(entradas)+".00");
            
        }
        
        monto=0;
        total=0;
        if(jtblSALIDAS.getRowCount()>0)
        {
            for(int i = 0; i < jtblSALIDAS.getRowCount(); i ++)
            {
                monto = Double.parseDouble(jtblSALIDAS.getValueAt(i, 2).toString());
                total += monto;
            }
            salidas=total;
            lblSALIDAS.setText("$ "+G.numero.format(salidas)+".00");
        }
        
        monto=0;
        total=0;
        if(tblTARJETAS.getRowCount()>0)
        {
            for(int i = 0; i < tblTARJETAS.getRowCount(); i ++)
            {
                monto = Double.parseDouble(tblTARJETAS.getValueAt(i, 2).toString());
                total += monto;
            }
            tarjeta=total;
            lblTARJETA.setText("$ "+G.numero.format(tarjeta)+".00");
        }
        
        monto=0;
        total=0;
        if(tblETARJETAS.getRowCount()>0)
        {
            for(int i = 0; i < tblETARJETAS.getRowCount(); i ++)
            {
                monto = Double.parseDouble(tblETARJETAS.getValueAt(i, 2).toString());
                total += monto;
            }
            etarjeta=total;
            lblETARJETA.setText("$ "+G.numero.format(etarjeta)+".00");
        }
        
        monto=0;
        total=0;
        total=pagos+entradas-salidas;
        ttarjeta = tarjeta + etarjeta;
        
        NTOTAL=(float) (MONTOINI+total);
        lblTOTAL.setText("$ "+G.numero.format(NTOTAL)+".00");
        lblTOTALT.setText("$ "+G.numero.format(ttarjeta)+".00");
        //lblTOTAL.setText("$ "+totalF.format(total)+".00");
    }
    
    private void datosINI() throws ClassNotFoundException, SQLException, Exception
    {
       int ultimo = F.Registro("CORTES","IDCORTE","DESC","IDCAJA",G.IDCAJA);
       CORTEANTERIOR = ultimo-1;
       //System.out.println("CORTEANTERIOR "+CORTEANTERIOR);
       ModeloCortes mdl;       
       ControlCortes ctrl = new ControlCortes();       
       
       mdl=ctrl.getRegistrosID(CORTEANTERIOR);

       MONTOINI = mdl.getMONTOFIN();
       FECHAINI = mdl.getFFIN();
       //NTOTAL=(float) (MONTOINI+total);
       
       lblSALDOINICIAL.setText("$ "+G.numero.format(MONTOINI)+".00");
       //lblTOTAL.setText("$ "+totalF.format(NTOTAL)+".00");
    }
    
   private void guardar() throws ClassNotFoundException, SQLException, Exception
   {
       int IDPAGOF = F.Registro("PAGOS","IDNOTA","DESC");
       int IDENTRADAF = F.Registro("ENTRADAS","IDENTRADA","DESC");
       int IDSALIDAF = F.Registro("SALIDAS","IDSALIDA","DESC");
       
       NCORTE = F.Registro("CORTES","IDCORTE","DESC");
       //NCORTE=ultimo;
       //String FECHAFIN = Global.formateador.format(fecha);
       ModeloCortes mdl = new ModeloCortes();       
       ControlCortes ctrl = new ControlCortes();
       mdl.setIDCORTE(NCORTE);
       mdl.setMONTOINI(MONTOINI);
       mdl.setFINI(FECHAINI);
       mdl.setFFIN(G.FECHA(G.hoy(),true));
       mdl.setMONTOFIN(NTOTAL);
       mdl.setIDCAJA(G.IDCAJA);
       ctrl.guardarRegistro(mdl);
       
       int ultimoD = F.Registro("CORTESD","IDCORTED","DESC");
       ModeloCortesDetalles mdlD = new ModeloCortesDetalles();       
       ControlCortesDetalles ctrlD = new ControlCortesDetalles();
       mdlD.setIDCORTED(ultimoD);
       mdlD.setIDCORTE(NCORTE);
       mdlD.setIDPAGOI(IDPAGOI);
       mdlD.setIDPAGOF(IDPAGOF-1);
       mdlD.setIDENTRADAI(IDENTRADAI);
       mdlD.setIDENTRADAF(IDENTRADAF-1);
       mdlD.setIDSALIDAI(IDSALIDAI);
       mdlD.setIDSALIDAF(IDSALIDAF-1);
       ctrlD.guardarRegistro(mdlD);
       
       DE=FECHAINI;
       AL=G.FECHA(G.hoy(),true);
       
       int ultimoS = F.Registro("SALIDAS","IDSALIDA","DESC");
       ModeloSalidas md = new ModeloSalidas();
       md.setIDSALIDA (ultimoS);
       md.setDESCRIPCION("SALIDA POR CORTE DE CAJA "+G.IDCAJA);
       md.setMONTO(NTOTAL);
       md.setCATEGORIA("CORTE DE CAJA");
       md.setMETODO("EFECTIVO");
       md.setFECHA(AL);
       md.setIDCAJA(G.IDCAJA);
       ControlSalidas cp = new ControlSalidas();
       cp.guardarRegistro(md);
       
       F.bitacora("SALIDAS",ultimoS,"NUEVO",G.IDUSER,"AUTOMATIC GENERATOR","");
   }
   
   private void entrada() throws ClassNotFoundException, SQLException, Exception
   {
        int ultimo = F.Registro("ENTRADAS","IDENTRADA","DESC");
        ModeloEntradas md = new ModeloEntradas();
        md.setIDENTRADA(ultimo);
        md.setDESCRIPCION("ENTRADA POR CORTE DE CAJA 1");
        md.setMONTO(NTOTAL);
        md.setCATEGORIA("CORTE DE CAJA");
        md.setMETODO("EFECTIVO");
        md.setFECHA(G.FECHA(G.hoy(),true));
        md.setIDCAJA(G.IDCAJA);
        
        ControlEntradas cp = new ControlEntradas();
        cp.guardarRegistro(md);
   }
   
   private void impresionlarga() throws ClassNotFoundException
   {
       try {
            ImpCorteDIA pca = new ImpCorteDIA(DE,AL,""+"$ "+G.numero.format(NTOTAL)+".00",jtblPAGOS.getModel(), jtblENTRADAS.getModel(), jtblSALIDAS.getModel(),
                    lblPAGOS.getText(),lblENTRADAS.getText(),lblSALIDAS.getText(),"$ "+G.numero.format(MONTOINI)+".00","$ "+G.numero.format(total)+".00",NCORTE,lblIDCAJA.getText(),
            lblTOTALT.getText());
            JButton[] opciones = pca.getButtons();
            JOptionPane.showInternalOptionDialog(this, pca, "Imprimir Corte de Caja",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
            if(pca.getOpcion() != null)            
                pca.Imprimir();            
        } catch (SQLException ex) {
            Logger.getLogger(ImpCorteDIA.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
    private void pregunta() throws SQLException, Exception
    {
        Object[] inputFields = {"¿QUIERES REALIZAR CORTE?"};

        int option = JOptionPane.showConfirmDialog(this, inputFields, "Realizar Corte de Caja", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (option == JOptionPane.OK_OPTION)
        {
            guardar();
            if(G.IDCAJA==1)            
                entrada();
                                    
            impresionlarga();
            this.dispose();
        }
    }
   
    
    private void seleccion(String TABLA) throws ClassNotFoundException, SQLException
    {
        this.TABLA = TABLA;
        JTable tabla = null;
        
        if(TABLA.equals("PAGOS"))        
            tabla = jtblPAGOS;        
        if(TABLA.equals("ENTRADAS"))        
            tabla = jtblENTRADAS;        
        if(TABLA.equals("SALIDAS"))        
            tabla = jtblPAGOS;
        
        int fila = tabla.getSelectedRow();
        IDRECIBO = tabla.getValueAt(fila, 0).toString();
        TITULAR = tabla.getValueAt(fila, 1).toString();
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Bar = new javax.swing.JToolBar();
        bntCORTELARGO = new javax.swing.JButton();
        btnACTUALIZAR = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jspPAGOS = new javax.swing.JScrollPane();
        jtblPAGOS = new javax.swing.JTable();
        jspENTRADAS = new javax.swing.JScrollPane();
        jtblENTRADAS = new javax.swing.JTable();
        jspSALIDAS = new javax.swing.JScrollPane();
        jtblSALIDAS = new javax.swing.JTable();
        lblPAGOS = new javax.swing.JLabel();
        lblENTRADAS = new javax.swing.JLabel();
        lblSALIDAS = new javax.swing.JLabel();
        pnlACCIONES = new javax.swing.JPanel();
        lblSALDOINICIAL = new javax.swing.JLabel();
        lblIDCAJA = new javax.swing.JLabel();
        lblTOTAL = new javax.swing.JLabel();
        lblTOTALT = new javax.swing.JLabel();
        pnlCONTADOR = new javax.swing.JPanel();
        txt1000 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lbl1000 = new javax.swing.JLabel();
        txt500 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lbl500 = new javax.swing.JLabel();
        txt200 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lbl200 = new javax.swing.JLabel();
        txt100 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lbl100 = new javax.swing.JLabel();
        lbl50 = new javax.swing.JLabel();
        txt50 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lbl20 = new javax.swing.JLabel();
        txt20 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt10 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lbl10 = new javax.swing.JLabel();
        txt5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        txt2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lblTOTALB = new javax.swing.JLabel();
        jspTARJETAS = new javax.swing.JScrollPane();
        tblTARJETAS = new javax.swing.JTable();
        lblTARJETA = new javax.swing.JLabel();
        jspETARJETAS = new javax.swing.JScrollPane();
        tblETARJETAS = new javax.swing.JTable();
        lblETARJETA = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setTitle("Hacer Corte de Caja");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/c_caja.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(970, 650));
        setPreferredSize(new java.awt.Dimension(970, 650));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Elige las opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        Bar.setRollover(true);

        bntCORTELARGO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntCORTELARGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cajaR.png"))); // NOI18N
        bntCORTELARGO.setText("CORTE DE CAJA");
        bntCORTELARGO.setToolTipText("CORTE DETALLADO");
        bntCORTELARGO.setFocusable(false);
        bntCORTELARGO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntCORTELARGO.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntCORTELARGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCORTELARGOActionPerformed(evt);
            }
        });
        Bar.add(bntCORTELARGO);

        btnACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/refresh.png"))); // NOI18N
        btnACTUALIZAR.setText("ACTUALIZAR");
        btnACTUALIZAR.setFocusable(false);
        btnACTUALIZAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnACTUALIZAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnACTUALIZARActionPerformed(evt);
            }
        });
        Bar.add(btnACTUALIZAR);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        jspPAGOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PAGOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jtblPAGOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblPAGOS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtblPAGOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtblPAGOSMousePressed(evt);
            }
        });
        jspPAGOS.setViewportView(jtblPAGOS);

        jspENTRADAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ENTRADAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jtblENTRADAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblENTRADAS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtblENTRADAS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtblENTRADASMousePressed(evt);
            }
        });
        jspENTRADAS.setViewportView(jtblENTRADAS);

        jspSALIDAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SALIDAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jtblSALIDAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblSALIDAS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtblSALIDAS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtblSALIDASMousePressed(evt);
            }
        });
        jspSALIDAS.setViewportView(jtblSALIDAS);

        lblPAGOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPAGOS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPAGOS.setText("PAGOS");
        lblPAGOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PAGOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblENTRADAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblENTRADAS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblENTRADAS.setText("ENTRADAS");
        lblENTRADAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ENTRADAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblSALIDAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSALIDAS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSALIDAS.setText("SALIDAS");
        lblSALIDAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SALIDAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblSALDOINICIAL.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblSALDOINICIAL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSALDOINICIAL.setText("TOTAL");
        lblSALDOINICIAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Saldo Inicial", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lblIDCAJA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblIDCAJA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIDCAJA.setText("Caja No");
        lblIDCAJA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Caja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lblTOTAL.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblTOTAL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTOTAL.setText("TOTAL");
        lblTOTAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lblTOTALT.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblTOTALT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTOTALT.setText("TOTAL TARJETA");
        lblTOTALT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        javax.swing.GroupLayout pnlACCIONESLayout = new javax.swing.GroupLayout(pnlACCIONES);
        pnlACCIONES.setLayout(pnlACCIONESLayout);
        pnlACCIONESLayout.setHorizontalGroup(
            pnlACCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlACCIONESLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSALDOINICIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblIDCAJA, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171)
                .addComponent(lblTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTOTALT, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlACCIONESLayout.setVerticalGroup(
            pnlACCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlACCIONESLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlACCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSALDOINICIAL)
                    .addComponent(lblIDCAJA))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlACCIONESLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlACCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTOTAL)
                    .addComponent(lblTOTALT)))
        );

        pnlCONTADOR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contador:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txt1000.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt1000.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt1000.setText("0");
        txt1000.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1000", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        txt1000.setMaximumSize(new java.awt.Dimension(70, 39));
        txt1000.setMinimumSize(new java.awt.Dimension(70, 39));
        txt1000.setPreferredSize(new java.awt.Dimension(70, 39));
        txt1000.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt1000KeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("=");

        lbl1000.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl1000.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl1000.setText("$ 0.00");

        txt500.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt500.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt500.setText("0");
        txt500.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "500", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        txt500.setMaximumSize(new java.awt.Dimension(70, 39));
        txt500.setMinimumSize(new java.awt.Dimension(70, 39));
        txt500.setPreferredSize(new java.awt.Dimension(70, 39));
        txt500.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt500KeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("=");

        lbl500.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl500.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl500.setText("$ 0.00");

        txt200.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt200.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt200.setText("0");
        txt200.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "200", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        txt200.setMaximumSize(new java.awt.Dimension(70, 39));
        txt200.setMinimumSize(new java.awt.Dimension(70, 39));
        txt200.setPreferredSize(new java.awt.Dimension(70, 39));
        txt200.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt200KeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("=");

        lbl200.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl200.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl200.setText("$ 0.00");

        txt100.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt100.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt100.setText("0");
        txt100.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "100", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        txt100.setMaximumSize(new java.awt.Dimension(70, 39));
        txt100.setMinimumSize(new java.awt.Dimension(70, 39));
        txt100.setPreferredSize(new java.awt.Dimension(70, 39));
        txt100.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt100KeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("=");

        lbl100.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl100.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl100.setText("$ 0.00");

        lbl50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl50.setText("$ 0.00");

        txt50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt50.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt50.setText("0");
        txt50.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "50", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        txt50.setMaximumSize(new java.awt.Dimension(70, 39));
        txt50.setMinimumSize(new java.awt.Dimension(70, 39));
        txt50.setPreferredSize(new java.awt.Dimension(70, 39));
        txt50.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt50KeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("=");

        lbl20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl20.setText("$ 0.00");

        txt20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt20.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt20.setText("0");
        txt20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "20", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        txt20.setMaximumSize(new java.awt.Dimension(70, 39));
        txt20.setMinimumSize(new java.awt.Dimension(70, 39));
        txt20.setPreferredSize(new java.awt.Dimension(70, 39));
        txt20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt20KeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("=");

        txt10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt10.setText("0");
        txt10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "10", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        txt10.setMaximumSize(new java.awt.Dimension(70, 39));
        txt10.setMinimumSize(new java.awt.Dimension(70, 39));
        txt10.setPreferredSize(new java.awt.Dimension(70, 39));
        txt10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt10KeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("=");

        lbl10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl10.setText("$ 0.00");

        txt5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt5.setText("0");
        txt5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "5", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        txt5.setMaximumSize(new java.awt.Dimension(70, 39));
        txt5.setMinimumSize(new java.awt.Dimension(70, 39));
        txt5.setPreferredSize(new java.awt.Dimension(70, 39));
        txt5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt5KeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("=");

        lbl5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl5.setText("$ 0.00");

        txt2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt2.setText("0");
        txt2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        txt2.setMaximumSize(new java.awt.Dimension(70, 39));
        txt2.setMinimumSize(new java.awt.Dimension(70, 39));
        txt2.setPreferredSize(new java.awt.Dimension(70, 39));
        txt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt2KeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("=");

        lbl2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl2.setText("$ 0.00");

        txt1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt1.setText("0");
        txt1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        txt1.setMaximumSize(new java.awt.Dimension(70, 39));
        txt1.setMinimumSize(new java.awt.Dimension(70, 39));
        txt1.setPreferredSize(new java.awt.Dimension(70, 39));
        txt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt1KeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("=");

        lbl1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl1.setText("$ 0.00");

        lblTOTALB.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTOTALB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTOTALB.setText("$ 0.00");
        lblTOTALB.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout pnlCONTADORLayout = new javax.swing.GroupLayout(pnlCONTADOR);
        pnlCONTADOR.setLayout(pnlCONTADORLayout);
        pnlCONTADORLayout.setHorizontalGroup(
            pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCONTADORLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCONTADORLayout.createSequentialGroup()
                        .addGroup(pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt200, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt500, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt1000, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlCONTADORLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlCONTADORLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlCONTADORLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlCONTADORLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlCONTADORLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlCONTADORLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlCONTADORLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlCONTADORLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl200, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlCONTADORLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl1000, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCONTADORLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl500, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblTOTALB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlCONTADORLayout.setVerticalGroup(
            pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCONTADORLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt1000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl1000))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt500, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl500))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt200, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl200))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl100))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl50))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCONTADORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTOTALB)
                .addGap(95, 95, 95))
        );

        jspTARJETAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PAGOS CON TARJETA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        tblTARJETAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTARJETAS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTARJETAS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTARJETASMousePressed(evt);
            }
        });
        jspTARJETAS.setViewportView(tblTARJETAS);

        lblTARJETA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTARJETA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTARJETA.setText("PAGOS CON TARJETA");
        lblTARJETA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PAGOS CON TARJETA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        jspETARJETAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ENTRADAS CON TARJETA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        tblETARJETAS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblETARJETAS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblETARJETAS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblETARJETASMousePressed(evt);
            }
        });
        jspETARJETAS.setViewportView(tblETARJETAS);

        lblETARJETA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblETARJETA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblETARJETA.setText("ENTRADAS CON TARJETA");
        lblETARJETA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ENTRADAS CON TARJETA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblPAGOS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblENTRADAS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblSALIDAS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jspPAGOS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jspENTRADAS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jspSALIDAS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jspTARJETAS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTARJETA, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jspETARJETAS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblETARJETA, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlCONTADOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlACCIONES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlACCIONES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCONTADOR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jspTARJETAS, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTARJETA)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jspETARJETAS, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jspENTRADAS)
                            .addComponent(jspSALIDAS)
                            .addComponent(jspPAGOS))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPAGOS)
                            .addComponent(lblENTRADAS)
                            .addComponent(lblSALIDAS)
                            .addComponent(lblETARJETA))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntCORTELARGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCORTELARGOActionPerformed
        try {
            actualizarTabla();
            pregunta();
        } catch (Exception ex) {
            Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntCORTELARGOActionPerformed

    private void txt1000KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1000KeyReleased
        // TODO add your handling code here:
        contadorsh(1000);
    }//GEN-LAST:event_txt1000KeyReleased

    private void txt500KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt500KeyReleased
        // TODO add your handling code here:}
        contadorsh(500);
    }//GEN-LAST:event_txt500KeyReleased

    private void txt200KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt200KeyReleased
        // TODO add your handling code here:
        contadorsh(200);
    }//GEN-LAST:event_txt200KeyReleased

    private void txt100KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt100KeyReleased
        // TODO add your handling code here:
        contadorsh(100);
    }//GEN-LAST:event_txt100KeyReleased

    private void txt50KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt50KeyReleased
        // TODO add your handling code here:
        contadorsh(50);
    }//GEN-LAST:event_txt50KeyReleased

    private void txt20KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt20KeyReleased
        // TODO add your handling code here:
        contadorsh(20);
    }//GEN-LAST:event_txt20KeyReleased

    private void txt10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt10KeyReleased
        // TODO add your handling code here:
        contadorsh(10);
    }//GEN-LAST:event_txt10KeyReleased

    private void txt5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt5KeyReleased
        // TODO add your handling code here:
        contadorsh(5);
    }//GEN-LAST:event_txt5KeyReleased

    private void txt2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2KeyReleased
        // TODO add your handling code here:
        contadorsh(2);
    }//GEN-LAST:event_txt2KeyReleased

    private void txt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyReleased
        // TODO add your handling code here:
        contadorsh(1);
    }//GEN-LAST:event_txt1KeyReleased

    private void jtblPAGOSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblPAGOSMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() > 1)
        {
            try {
                seleccion("PAGOS");
                F.viewBitacora(TABLA,IDRECIBO, TITULAR, "");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jtblPAGOSMousePressed

    private void jtblENTRADASMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblENTRADASMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() > 1)
        {
            try {
                seleccion("ENTRADAS");
                F.viewBitacora(TABLA,IDRECIBO, TITULAR, "");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jtblENTRADASMousePressed

    private void jtblSALIDASMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblSALIDASMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() > 1)
        {
            try {
                seleccion("SALIDAS");
                F.viewBitacora(TABLA,IDRECIBO, TITULAR, "");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jtblSALIDASMousePressed

    private void btnACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnACTUALIZARActionPerformed
        try {
            // TODO add your handling code here:
            actualizarTabla();
            totales();
        } catch (SQLException ex) {
            Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnACTUALIZARActionPerformed

    private void tblTARJETASMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTARJETASMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() > 1)
        {
            try {
                seleccion("PAGOS");
                F.viewBitacora(TABLA,IDRECIBO, TITULAR, "");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblTARJETASMousePressed

    private void tblETARJETASMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblETARJETASMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() > 1)
        {
            try {
                seleccion("ENTRADAS");
                F.viewBitacora(TABLA,IDRECIBO, TITULAR, "");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblETARJETASMousePressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        try {
            actualizarTabla();
        } catch (SQLException ex) {
            Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRegCorteD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formInternalFrameActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton bntCORTELARGO;
    private javax.swing.JButton btnACTUALIZAR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jspENTRADAS;
    private javax.swing.JScrollPane jspETARJETAS;
    private javax.swing.JScrollPane jspPAGOS;
    private javax.swing.JScrollPane jspSALIDAS;
    private javax.swing.JScrollPane jspTARJETAS;
    private javax.swing.JTable jtblENTRADAS;
    private javax.swing.JTable jtblPAGOS;
    private javax.swing.JTable jtblSALIDAS;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl10;
    private javax.swing.JLabel lbl100;
    private javax.swing.JLabel lbl1000;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl20;
    private javax.swing.JLabel lbl200;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl50;
    private javax.swing.JLabel lbl500;
    private javax.swing.JLabel lblENTRADAS;
    private javax.swing.JLabel lblETARJETA;
    private javax.swing.JLabel lblIDCAJA;
    private javax.swing.JLabel lblPAGOS;
    private javax.swing.JLabel lblSALDOINICIAL;
    private javax.swing.JLabel lblSALIDAS;
    private javax.swing.JLabel lblTARJETA;
    private javax.swing.JLabel lblTOTAL;
    private javax.swing.JLabel lblTOTALB;
    private javax.swing.JLabel lblTOTALT;
    private javax.swing.JPanel pnlACCIONES;
    private javax.swing.JPanel pnlCONTADOR;
    private javax.swing.JTable tblETARJETAS;
    private javax.swing.JTable tblTARJETAS;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt10;
    private javax.swing.JTextField txt100;
    private javax.swing.JTextField txt1000;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt20;
    private javax.swing.JTextField txt200;
    private javax.swing.JTextField txt5;
    private javax.swing.JTextField txt50;
    private javax.swing.JTextField txt500;
    // End of variables declaration//GEN-END:variables

}

