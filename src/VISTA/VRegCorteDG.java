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
import CONTROLADOR.ControlCortesGlobal;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloCortesGlobal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author KIKE FRENCH
 */

public final class VRegCorteDG extends javax.swing.JInternalFrame {
    
    private int b1000 = 0;
    private int b500 = 0;
    private int b200 = 0;
    private int b100 = 0;
    private int b50 = 0;
    private int b20 = 0;
    private int b10 = 0;
    private int b5 = 0;
    private int b2 = 0;
    private int b1 = 0;
    private float EDH,DHN,CARGOSV,ABONOSV,CARGOSCF,ABONOSCF,MONTOG,MONTOR,VALES,CF,CARGOSEX,ABONOSEX,EXTRAS;
    private int IDCORTEG;
        
    /** Creates new form VRepPagos
        */
    
    public VRegCorteDG(){        
    }
    
    public VRegCorteDG(String titulo) throws Exception{   
        initComponents();
        this.setTitle(titulo);
        saldos(true);
        this.txtEDH.requestFocus();
        this.Bar.setFloatable(false);        
    }
    
    private void saldos(boolean sql) throws ClassNotFoundException, SQLException, Exception
    {
        if(sql)
        {
            ControlCortes ctrlC = new ControlCortes();
            DHN = ctrlC.monto();            
            
            CARGOSV = F.monto("VALES","TIPO", "CARGO",null,false);
            ABONOSV = F.monto("VALES", "TIPO", "ABONO",null,false);

            CARGOSCF = F.monto("CF", "TIPO", "CARGO",null,false);
            ABONOSCF = F.monto("CF", "TIPO", "ABONO",null,false);
            
            CARGOSEX = F.monto("EXTRAS", "TIPO", "CARGO",null,false);
            ABONOSEX = F.monto("EXTRAS", "TIPO", "ABONO",null,false);            
        }
        
        this.lblDHN.setText("$ "+G.numero.format(DHN));        
        
        VALES = CARGOSV - ABONOSV;        
        this.lblVALES.setText("$ "+G.numero.format(VALES));        
                
        CF = ABONOSCF - CARGOSCF;
        this.lblCF.setText("$ "+G.numero.format(CF));
        
        EXTRAS = ABONOSEX - CARGOSEX;
        this.lblEXTRAS.setText("$ "+G.numero.format(EXTRAS));
        
        if(txtEDH.getText().trim() == null || txtEDH.getText().trim()=="")
            EDH=0;
        else
            EDH=Float.parseFloat(txtEDH.getText());            
        
        MONTOG = DHN+EDH;
        this.lblMONTOG.setText("$ "+G.numero.format(MONTOG));
        
        MONTOR = MONTOG-CF-VALES;
        this.lblMONTOR.setText("$ "+G.numero.format(MONTOR));
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
            default:
                break;
        }
        
        int totalB = b1000 + b500 + b200 + b100 + b50 + b20 + b10 + b5 + b2 + b1;
        lblTOTALB.setText("$ "+G.numero.format(totalB)+".00");        
    }
   
    private void impresionlarga() throws ClassNotFoundException, SQLException
    {
        ImpCorteDIAG pca = new ImpCorteDIAG(G.FECHA(G.hoy(), true),""+IDCORTEG,
                txtEDH.getText(), lblDHN.getText(), lblVALES.getText(),lblCF.getText(),
                lblMONTOG.getText(),lblMONTOR.getText());
        JButton[] opciones = pca.getButtons();
        JOptionPane.showInternalOptionDialog(this, pca, "Imprimir Corte de Caja GLOBAL",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
        if(pca.getOpcion() != null)        
            pca.Imprimir();        
   }
   
    private void pregunta() throws SQLException, Exception
    {
        Object[] inputFields = {"¿QUIERES REALIZAR CORTE GLOBAL?"};
        int option = JOptionPane.showConfirmDialog(this, inputFields, "Realizar Corte de Caja GLOBAL", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (option == JOptionPane.OK_OPTION)
        {
            guardar();                                   
            impresionlarga();
            //this.dispose();
            preguntaCF();
        }
    }
    
    private void preguntaCF() throws SQLException, Exception
    {
        Object[] inputFields = {"¿HACER ABONO A CF?"};
        int option = JOptionPane.showConfirmDialog(this, inputFields, "Realizar Corte de Caja GLOBAL", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (option == JOptionPane.OK_OPTION)
        {
            RegCF p = new RegCF(null,true);
            p.mostrar(0,"NUEVO","ABONO");
            p.setVisible(true);
            //guardar();                                   
            //impresionlarga();
            //this.dispose();
            
        }
        saldos(true);
    }
   
    private void guardar() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloCortesGlobal mdl = new ModeloCortesGlobal(); 
        
        IDCORTEG = F.Registro("CORTESG","IDCORTEG","DESC");
        
        mdl.setDHN(DHN);
        mdl.setEDH(EDH);
        mdl.setVALES(VALES);
        mdl.setCF(CF);
        mdl.setFECHA(G.FECHA(G.hoy(), true));
        mdl.setMONTOG(MONTOG);
        mdl.setMONTOR(MONTOR);
        
        ControlCortesGlobal ctrl = new ControlCortesGlobal();
        ctrl.guardarRegistro(mdl);
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
        pnlDATOS = new javax.swing.JPanel();
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
        jPanel4 = new javax.swing.JPanel();
        txtEDH = new javax.swing.JTextField();
        lblDHN = new javax.swing.JLabel();
        lblMONTOG = new javax.swing.JLabel();
        lblVALES = new javax.swing.JLabel();
        lblCF = new javax.swing.JLabel();
        lblMONTOR = new javax.swing.JLabel();
        pnlEXTRAS = new javax.swing.JPanel();
        lblEXTRAS = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Hacer Corte de Caja GLOBAL");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dinero-global.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(970, 650));
        setPreferredSize(new java.awt.Dimension(970, 650));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Elige las opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        Bar.setRollover(true);

        bntCORTELARGO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntCORTELARGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dinero-global.png"))); // NOI18N
        bntCORTELARGO.setText("CORTE GLOBAL");
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
            .addGap(0, 762, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        pnlDATOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtEDH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtEDH.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEDH.setText("0.0");
        txtEDH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EDH", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtEDH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEDHKeyReleased(evt);
            }
        });

        lblDHN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDHN.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDHN.setText("0.0");
        lblDHN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DHN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblMONTOG.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblMONTOG.setForeground(new java.awt.Color(0, 51, 102));
        lblMONTOG.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMONTOG.setText("0.0");
        lblMONTOG.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Monto Global", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblVALES.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblVALES.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVALES.setText("0.0");
        lblVALES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblCF.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCF.setText("0.0");
        lblCF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CF", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblMONTOR.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMONTOR.setForeground(new java.awt.Color(0, 0, 102));
        lblMONTOR.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMONTOR.setText("0.0");
        lblMONTOR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Monto Real", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDHN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtEDH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
            .addComponent(lblMONTOG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblVALES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblCF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMONTOR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDHN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMONTOG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVALES)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMONTOR)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlEXTRAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lblEXTRAS.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblEXTRAS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEXTRAS.setText("0.0");
        lblEXTRAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Extras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout pnlEXTRASLayout = new javax.swing.GroupLayout(pnlEXTRAS);
        pnlEXTRAS.setLayout(pnlEXTRASLayout);
        pnlEXTRASLayout.setHorizontalGroup(
            pnlEXTRASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEXTRAS, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
        );
        pnlEXTRASLayout.setVerticalGroup(
            pnlEXTRASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEXTRASLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEXTRAS)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDATOSLayout = new javax.swing.GroupLayout(pnlDATOS);
        pnlDATOS.setLayout(pnlDATOSLayout);
        pnlDATOSLayout.setHorizontalGroup(
            pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDATOSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCONTADOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEXTRAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
        );
        pnlDATOSLayout.setVerticalGroup(
            pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDATOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlEXTRAS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCONTADOR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlDATOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDATOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            pregunta();
        } catch (Exception ex) {
            Logger.getLogger(VRegCorteDG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntCORTELARGOActionPerformed

    private void btnACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnACTUALIZARActionPerformed
        try {
            saldos(true);
        } catch (SQLException ex) {
            Logger.getLogger(VRegCorteDG.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRegCorteDG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnACTUALIZARActionPerformed

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

    private void txtEDHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEDHKeyReleased
        try {
            saldos(false);
        } catch (SQLException ex) {
            Logger.getLogger(VRegCorteDG.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRegCorteDG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtEDHKeyReleased

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
    private javax.swing.JPanel jPanel4;
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
    private javax.swing.JLabel lblCF;
    private javax.swing.JLabel lblDHN;
    private javax.swing.JLabel lblEXTRAS;
    private javax.swing.JLabel lblMONTOG;
    private javax.swing.JLabel lblMONTOR;
    private javax.swing.JLabel lblTOTALB;
    private javax.swing.JLabel lblVALES;
    private javax.swing.JPanel pnlCONTADOR;
    private javax.swing.JPanel pnlDATOS;
    private javax.swing.JPanel pnlEXTRAS;
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
    private javax.swing.JTextField txtEDH;
    // End of variables declaration//GEN-END:variables

}

