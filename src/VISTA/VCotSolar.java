/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package VISTA;

import CONTROLADOR.ControlDetalles;
import DHNv2.G;
import MODELO.ModeloDetalles;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */

public class VCotSolar extends javax.swing.JInternalFrame 
{
    
    /**
     * Creates new form VCotSolar
     */
    public VCotSolar() {
        initComponents();
    }
    
    
    public VCotSolar(String titulo) throws SQLException, Exception {
        initComponents();
        this.setTitle(titulo);
        TC();
    }
    
    private void TC() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloDetalles mdlD;
        ControlDetalles ctrlD = new ControlDetalles();
        mdlD=ctrlD.getRegistrosD();
        txtTC.setText(""+mdlD.getTC());
        txtIVA.setText(""+mdlD.getIVA());
    }
    
    private void calcula()
    {
        //variables usadas
        double BD1 = 0;
        double BD2 = 0;
        double BD3 = 0;
        double BD4 = 0;
        double BD5 = 0;
        double BD6 = 0;
        
        int B1 = 0;
        int B2 = 0;
        int B3 = 0;
        int B4 = 0;
        int B5 = 0;
        int B6 = 0;
        
        double BP1 = 0;
        double BP2 = 0;
        double BP3 = 0;
        double BP4 = 0;
        double BP5 = 0;
        double BP6 = 0;
        
        //Consumos diarios bimestrales
        if(txtBM1.getText() != null&&!txtBM1.getText().equals(""))
        {
            B1 = Integer.parseInt(txtBM1.getText());
            BD1 = Math.round((Double.parseDouble(txtBM1.getText()) / 61) * 100) / 100d;
            lblCD1.setText(""+BD1);            
        }
        if(txtBM2.getText() != null&&!txtBM2.getText().equals(""))
        {
            B2 = Integer.parseInt(txtBM2.getText());
            BD2 = Math.round((Double.parseDouble(txtBM2.getText()) / 61) * 100) / 100d;
            lblCD2.setText(""+BD2);            
        }
        if(txtBM3.getText() != null&&!txtBM3.getText().equals(""))
        {
            B3 = Integer.parseInt(txtBM3.getText());
            BD3 = Math.round((Double.parseDouble(txtBM3.getText()) / 61) * 100) / 100d;
            lblCD3.setText(""+BD3);            
        }
        if(txtBM4.getText() != null&&!txtBM4.getText().equals(""))
        {
            B4 = Integer.parseInt(txtBM4.getText());
            BD4 = Math.round((Double.parseDouble(txtBM4.getText()) / 61) * 100) / 100d;
            lblCD4.setText(""+BD4);            
        }
        if(txtBM5.getText() != null&&!txtBM5.getText().equals(""))
        {
            B5 = Integer.parseInt(txtBM5.getText());
            BD5 = Math.round((Double.parseDouble(txtBM5.getText()) / 61) * 100) / 100d;
            lblCD5.setText(""+BD5);            
        }
        if(txtBM6.getText() != null&&!txtBM6.getText().equals(""))
        {
            B6 = Integer.parseInt(txtBM6.getText());
            BD6 = Math.round((Double.parseDouble(txtBM6.getText()) / 61) * 100) / 100d;
            lblCD6.setText(""+BD6);            
        }
                
        //Promedios de consumos
        double promb = (B1+B2+B3+B4+B5+B6) / 6;
        promb = Math.round(promb * 100) / 100d;
        lblPROMANUAL.setText(""+ promb);
        double promd = (BD1+BD2+BD3+BD4+BD5+BD6) / 6;
        promd = Math.round(promd * 100) / 100d;
        lblPROMDIARIO.setText("" + promd );
        
        if(txtCAPACIDAD.getText() != null && !txtCAPACIDAD.getText().equals("") &&
                txtDESCUENTO.getText() != null && !txtDESCUENTO.getText().equals("") &&
                txtHRS.getText() != null && !txtHRS.getText().equals(""))
        {
            double perdida = Integer.parseInt(txtCAPACIDAD.getText()) - ((Integer.parseInt(txtDESCUENTO.getText()) * 
                    Integer.parseInt(txtCAPACIDAD.getText())) / 100);
            perdida = Math.round(perdida * 100) / 100d;
            int vu = Integer.parseInt(txtCAPACIDAD.getText()) - ((15*Integer.parseInt(txtCAPACIDAD.getText())) / 100);
            lblVIDAUTIL.setText(""+vu);
            double prodpanel = (perdida * Double.parseDouble(txtHRS.getText()))/1000;
            prodpanel = Math.round(prodpanel * 100) / 100d;
            lblPP.setText(""+prodpanel);

            lblCPP.setText("" + (int) Math.ceil(promd/prodpanel));
            lblCINVERSOR.setText(""+(int)Math.ceil(((Double.parseDouble(txtCAPACIDAD.getText())*Double.parseDouble(lblCPP.getText()))/1000)));
        }
        
        //Consumos diarios bimestrales
        if(txtBMC1.getText() != null&&!txtBMC1.getText().equals(""))
        {
            BP1 = Math.round((Double.parseDouble(txtBMC1.getText())) * 100) / 100d;          
        }
        if(txtBMC2.getText() != null&&!txtBMC2.getText().equals(""))
        {
            BP2 = Math.round((Double.parseDouble(txtBMC2.getText())) * 100) / 100d;          
        }
        if(txtBMC3.getText() != null&&!txtBMC3.getText().equals(""))
        {
            BP3 = Math.round((Double.parseDouble(txtBMC3.getText())) * 100) / 100d;         
        }
        if(txtBMC4.getText() != null&&!txtBMC4.getText().equals(""))
        {
            BP4 = Math.round((Double.parseDouble(txtBMC4.getText())) * 100) / 100d;        
        }
        if(txtBMC5.getText() != null&&!txtBMC5.getText().equals(""))
        {
            BP5 = Math.round((Double.parseDouble(txtBMC5.getText())) * 100) / 100d;      
        }
        if(txtBMC6.getText() != null&&!txtBMC6.getText().equals(""))
        {
            BP6 = Math.round((Double.parseDouble(txtBMC6.getText())) * 100) / 100d;        
        }
        
        double promp = BP1+BP2+BP3+BP4+BP5+BP6;
        promp = Math.round(promp * 100) / 100d;
        lblPROMPAGOA.setText(""+promp);
        lblPROMPAGOM.setText(""+Math.round((promp/6) * 100) / 100d);   
        
        if(txtCOSTOPANELES.getText() != null&&!txtCOSTOPANELES.getText().equals(""))
        {
            lblCOSTOT.setText(""+Math.round((Double.parseDouble(txtCOSTOPANELES.getText())*Integer.parseInt(lblCPP.getText())) * 100) / 100d);        
        }
        
        if(txtCOSTOINVERSOR.getText() != null&&!txtCOSTOINVERSOR.getText().equals(""))
        {
            lblTOTALUSD.setText(""+Math.round((Double.parseDouble(lblCOSTOT.getText()) + 
                    Double.parseDouble(txtCOSTOINVERSOR.getText())) * 100) / 100d);        
        }
        
        if(txtCOSTOMO.getText() != null&&!txtCOSTOMO.getText().equals(""))
        {
            lblTOTALMO.setText(""+Math.round((Double.parseDouble(txtCOSTOMO.getText())*Integer.parseInt(lblCPP.getText())) * 100) / 100d);        
        }
        
        double ganancia = 1+(Double.parseDouble(txtGANANCIA.getText())/100);
        
        double TOTAL = Math.round((((Double.parseDouble(lblTOTALUSD.getText())) * 
                Double.parseDouble(txtTC.getText()))*
                ganancia)+
                Double.parseDouble(lblTOTALMO.getText()) + 
                Double.parseDouble(txtCAMBIO220.getText())) * 100 / 100d;
        //double TOTAL = (((Double.parseDouble(lblCOSTOT.getText()) + Double.parseDouble(lblTOTALUSD.getText())) * Double.parseDouble(txtTC.getText())) * Double.parseDouble(txtGANANCIA.getText())) +Double.parseDouble(lblTOTALMO.getText());

        txtTOTAL.setText("$ "+ G.numero.format(TOTAL));
        
        //double IVA = Math.round((TOTAL * Double.parseDouble(txtIVA.getText())) * 100) / 100d;
        double IVA  = (Double.parseDouble(txtIVA.getText())-1);
        double IVAT = IVA*TOTAL;
        lblIVA.setText("$ "+G.numero.format(IVAT));
        
        double GTOTAL = IVAT + TOTAL;
        
        if(txtTOTAL.getText() != null&&!txtTOTAL.getText().equals(""))
        {
            txtGTOTAL.setText("$ "+G.numero.format(GTOTAL)); 
        }
        
        if(txtGTOTAL.getText() != null&&!txtGTOTAL.getText().equals(""))
        {
            lblRETORNO.setText(""+Math.round((GTOTAL/Double.parseDouble(lblPROMPAGOA.getText())) * 100) / 100d);
        }
        
    }
    
    private void inversores()
    {
        
    }
    
    private void paneles()
    {
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCLIENTE = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtGANANCIA = new javax.swing.JTextField();
        txtINVERSOR = new javax.swing.JComboBox<>();
        txtCOSTOINVERSOR = new javax.swing.JTextField();
        txtPANELES = new javax.swing.JComboBox<>();
        txtCOSTOPANELES = new javax.swing.JTextField();
        txtCOSTOMO = new javax.swing.JTextField();
        txtTC = new javax.swing.JTextField();
        txtIVA = new javax.swing.JTextField();
        lblCOSTOT = new javax.swing.JLabel();
        txtTOTAL = new javax.swing.JTextField();
        txtGTOTAL = new javax.swing.JTextField();
        lblTOTALMO = new javax.swing.JLabel();
        lblTOTALUSD = new javax.swing.JLabel();
        lblIVA = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtCAMBIO220 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        pnlRECIBO = new javax.swing.JPanel();
        txtBM1 = new javax.swing.JTextField();
        txtBM2 = new javax.swing.JTextField();
        txtBM3 = new javax.swing.JTextField();
        txtBM4 = new javax.swing.JTextField();
        txtBM5 = new javax.swing.JTextField();
        txtBM6 = new javax.swing.JTextField();
        lblCD1 = new javax.swing.JLabel();
        lblCD2 = new javax.swing.JLabel();
        lblCD3 = new javax.swing.JLabel();
        lblCD4 = new javax.swing.JLabel();
        lblCD5 = new javax.swing.JLabel();
        lblCD6 = new javax.swing.JLabel();
        lblPROMANUAL = new javax.swing.JLabel();
        lblPROMDIARIO = new javax.swing.JLabel();
        txtBMC1 = new javax.swing.JTextField();
        txtBMC2 = new javax.swing.JTextField();
        txtBMC3 = new javax.swing.JTextField();
        txtBMC4 = new javax.swing.JTextField();
        txtBMC5 = new javax.swing.JTextField();
        txtBMC6 = new javax.swing.JTextField();
        lblPROMPAGOM = new javax.swing.JLabel();
        txtRPU = new javax.swing.JTextField();
        lblPROMPAGOA = new javax.swing.JLabel();
        lblRETORNO = new javax.swing.JLabel();
        pnlPANELES = new javax.swing.JPanel();
        txtCAPACIDAD = new javax.swing.JTextField();
        txtDESCUENTO = new javax.swing.JTextField();
        lblVIDAUTIL = new javax.swing.JLabel();
        txtHRS = new javax.swing.JTextField();
        lblPP = new javax.swing.JLabel();
        lblCPP = new javax.swing.JLabel();
        lblCINVERSOR = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setTitle("Cotizador Sistemas Votovoltaicos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/panel-solar.png"))); // NOI18N

        pnlCLIENTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("NOMBRE DEL CLIENTE");
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout pnlCLIENTELayout = new javax.swing.GroupLayout(pnlCLIENTE);
        pnlCLIENTE.setLayout(pnlCLIENTELayout);
        pnlCLIENTELayout.setHorizontalGroup(
            pnlCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCLIENTELayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCLIENTELayout.setVerticalGroup(
            pnlCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCLIENTELayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Costos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtGANANCIA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtGANANCIA.setForeground(new java.awt.Color(0, 153, 153));
        txtGANANCIA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGANANCIA.setText("25");
        txtGANANCIA.setToolTipText("Ganancia en %");
        txtGANANCIA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ganancia en %", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtGANANCIA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
        });

        txtINVERSOR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtINVERSOR.setForeground(new java.awt.Color(0, 153, 0));
        txtINVERSOR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtINVERSOR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inversor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtCOSTOINVERSOR.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCOSTOINVERSOR.setForeground(new java.awt.Color(51, 153, 0));
        txtCOSTOINVERSOR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCOSTOINVERSOR.setText("0.00");
        txtCOSTOINVERSOR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Costo Inversor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtCOSTOINVERSOR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
        });

        txtPANELES.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPANELES.setForeground(new java.awt.Color(255, 51, 0));
        txtPANELES.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtPANELES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Paneles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtCOSTOPANELES.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCOSTOPANELES.setForeground(new java.awt.Color(255, 51, 0));
        txtCOSTOPANELES.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCOSTOPANELES.setText("0.00");
        txtCOSTOPANELES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Costo Paneles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtCOSTOPANELES.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
        });

        txtCOSTOMO.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCOSTOMO.setForeground(new java.awt.Color(204, 204, 0));
        txtCOSTOMO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCOSTOMO.setText("2500");
        txtCOSTOMO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Costo Mano de Obra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtCOSTOMO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
        });

        txtTC.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTC.setForeground(new java.awt.Color(0, 153, 153));
        txtTC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTC.setText("0.0");
        txtTC.setToolTipText("Tipo de cambio");
        txtTC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "T.C.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtTC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
        });

        txtIVA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtIVA.setForeground(new java.awt.Color(0, 153, 153));
        txtIVA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIVA.setText("0.0");
        txtIVA.setToolTipText("IVA");
        txtIVA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IVA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtIVA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
        });

        lblCOSTOT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCOSTOT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCOSTOT.setText("0.00");
        lblCOSTOT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Costo total de Paneles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtTOTAL.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTOTAL.setForeground(new java.awt.Color(0, 102, 102));
        txtTOTAL.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTOTAL.setText("0.00");
        txtTOTAL.setToolTipText("");
        txtTOTAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total MXN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtGTOTAL.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtGTOTAL.setForeground(new java.awt.Color(255, 51, 51));
        txtGTOTAL.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGTOTAL.setText("0.00");
        txtGTOTAL.setToolTipText("");
        txtGTOTAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gran Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTOTALMO.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTOTALMO.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTOTALMO.setText("0.00");
        lblTOTALMO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Costo de MO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTOTALUSD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTOTALUSD.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTOTALUSD.setText("0.00");
        lblTOTALUSD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Equipos USD", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblIVA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblIVA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIVA.setText("0.00");
        lblIVA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IVA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cuadernoS.png"))); // NOI18N
        jButton1.setText("COTIZAR");
        jButton1.setToolTipText("");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        txtCAMBIO220.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCAMBIO220.setForeground(new java.awt.Color(51, 153, 0));
        txtCAMBIO220.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCAMBIO220.setText("1500");
        txtCAMBIO220.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cambio a 220", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtCAMBIO220.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtGTOTAL, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIVA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTOTAL, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTOTALMO, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCOSTOMO, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTOTALUSD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCOSTOINVERSOR, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtINVERSOR, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCOSTOT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCOSTOPANELES, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPANELES, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtGANANCIA, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtTC, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton1))
                    .addComponent(txtCAMBIO220))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGANANCIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPANELES, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCOSTOPANELES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCOSTOT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtINVERSOR, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCOSTOINVERSOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTOTALUSD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCOSTOMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTOTALMO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCAMBIO220, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIVA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Generales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        pnlRECIBO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Recibo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtBM1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBM1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBM1.setText("0");
        txtBM1.setToolTipText("");
        txtBM1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1 BIMESTRE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBM1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        txtBM2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBM2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBM2.setText("0");
        txtBM2.setToolTipText("");
        txtBM2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "2 BIMESTRE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBM2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        txtBM3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBM3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBM3.setText("0");
        txtBM3.setToolTipText("");
        txtBM3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "3 BIMESTRE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBM3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        txtBM4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBM4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBM4.setText("0");
        txtBM4.setToolTipText("");
        txtBM4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "5 BIMESTRE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBM4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        txtBM5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBM5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBM5.setText("0");
        txtBM5.setToolTipText("");
        txtBM5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "4 BIMESTRE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBM5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        txtBM6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBM6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBM6.setText("0");
        txtBM6.setToolTipText("");
        txtBM6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "6 BIMESTRE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBM6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        lblCD1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCD1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCD1.setText("0.0");
        lblCD1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consumo Diario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblCD2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCD2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCD2.setText("0.0");
        lblCD2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consumo Diario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblCD3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCD3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCD3.setText("0.0");
        lblCD3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consumo Diario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblCD4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCD4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCD4.setText("0.0");
        lblCD4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consumo Diario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblCD5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCD5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCD5.setText("0.0");
        lblCD5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consumo Diario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblCD6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCD6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCD6.setText("0.0");
        lblCD6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consumo Diario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblPROMANUAL.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPROMANUAL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPROMANUAL.setText("0");
        lblPROMANUAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Promedio Anual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblPROMDIARIO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPROMDIARIO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPROMDIARIO.setText("0.0");
        lblPROMDIARIO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Promedio Diario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtBMC1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBMC1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBMC1.setText("0");
        txtBMC1.setToolTipText("");
        txtBMC1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1 BIMESTRE PAGO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBMC1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        txtBMC2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBMC2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBMC2.setText("0");
        txtBMC2.setToolTipText("");
        txtBMC2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "2 BIMESTRE PAGO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBMC2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        txtBMC3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBMC3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBMC3.setText("0");
        txtBMC3.setToolTipText("");
        txtBMC3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "3 BIMESTRE PAGO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBMC3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        txtBMC4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBMC4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBMC4.setText("0");
        txtBMC4.setToolTipText("");
        txtBMC4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "4 BIMESTRE PAGO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBMC4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        txtBMC5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBMC5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBMC5.setText("0");
        txtBMC5.setToolTipText("");
        txtBMC5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "5 BIMESTRE PAGO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBMC5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        txtBMC6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBMC6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBMC6.setText("0");
        txtBMC6.setToolTipText("");
        txtBMC6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "6 BIMESTRE PAGO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBMC6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        lblPROMPAGOM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPROMPAGOM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPROMPAGOM.setText("0.0");
        lblPROMPAGOM.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Promedio Pago Mensual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtRPU.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtRPU.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RPU", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblPROMPAGOA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPROMPAGOA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPROMPAGOA.setText("0.0");
        lblPROMPAGOA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Promedio Pago Anual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblRETORNO.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblRETORNO.setForeground(new java.awt.Color(153, 153, 0));
        lblRETORNO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRETORNO.setText("0");
        lblRETORNO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Retorno de Inversion en Años", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        javax.swing.GroupLayout pnlRECIBOLayout = new javax.swing.GroupLayout(pnlRECIBO);
        pnlRECIBO.setLayout(pnlRECIBOLayout);
        pnlRECIBOLayout.setHorizontalGroup(
            pnlRECIBOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRECIBOLayout.createSequentialGroup()
                .addGroup(pnlRECIBOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPROMANUAL, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBM6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBM4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBM5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBM3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBM2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBM1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlRECIBOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblCD6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(lblCD5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCD3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCD2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCD1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCD4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPROMDIARIO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRECIBOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBMC6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBMC5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBMC4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBMC3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBMC2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBMC1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPROMPAGOM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPROMPAGOA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(txtRPU)
            .addComponent(lblRETORNO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlRECIBOLayout.setVerticalGroup(
            pnlRECIBOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRECIBOLayout.createSequentialGroup()
                .addComponent(txtRPU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRECIBOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRECIBOLayout.createSequentialGroup()
                        .addGroup(pnlRECIBOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCD1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRECIBOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCD2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRECIBOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCD3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRECIBOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBM5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCD4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRECIBOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCD5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRECIBOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBM6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCD6)))
                    .addGroup(pnlRECIBOLayout.createSequentialGroup()
                        .addComponent(txtBMC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBMC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBMC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBMC4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBMC5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBMC6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRECIBOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPROMANUAL)
                    .addComponent(lblPROMDIARIO)
                    .addComponent(lblPROMPAGOM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPROMPAGOA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRETORNO)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlPANELES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Modulos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtCAPACIDAD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCAPACIDAD.setForeground(new java.awt.Color(0, 51, 153));
        txtCAPACIDAD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCAPACIDAD.setText("700");
        txtCAPACIDAD.setToolTipText("Tamaño de Modulos");
        txtCAPACIDAD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capacidad Modulos W", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtCAPACIDAD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        txtDESCUENTO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDESCUENTO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDESCUENTO.setText("15");
        txtDESCUENTO.setToolTipText("Descuento de Produccion %");
        txtDESCUENTO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descuento de Produccion %", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtDESCUENTO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        lblVIDAUTIL.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblVIDAUTIL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVIDAUTIL.setText("0");
        lblVIDAUTIL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vida Util 15 años", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtHRS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtHRS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHRS.setText("5.5");
        txtHRS.setToolTipText("Horas Producidad en la region");
        txtHRS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Horas Producidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtHRS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CALC(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        lblPP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPP.setText("KWH");
        lblPP.setToolTipText("Produccion por panel");
        lblPP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produccion x Panel KWH", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblCPP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCPP.setForeground(new java.awt.Color(153, 0, 0));
        lblCPP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCPP.setText("0");
        lblCPP.setToolTipText("Cantidad de Paneles Propuestos");
        lblCPP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cant de Paneles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblCINVERSOR.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCINVERSOR.setForeground(new java.awt.Color(0, 153, 51));
        lblCINVERSOR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCINVERSOR.setText("0");
        lblCINVERSOR.setToolTipText("Capacidad de Inversor");
        lblCINVERSOR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capacidad de Inversor en KW", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        javax.swing.GroupLayout pnlPANELESLayout = new javax.swing.GroupLayout(pnlPANELES);
        pnlPANELES.setLayout(pnlPANELESLayout);
        pnlPANELESLayout.setHorizontalGroup(
            pnlPANELESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCINVERSOR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlPANELESLayout.createSequentialGroup()
                .addGroup(pnlPANELESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCAPACIDAD, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(txtDESCUENTO)
                    .addComponent(txtHRS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPANELESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCPP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblVIDAUTIL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPP, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)))
        );
        pnlPANELESLayout.setVerticalGroup(
            pnlPANELESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPANELESLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPANELESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCAPACIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCPP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPANELESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDESCUENTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVIDAUTIL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPANELESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHRS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCINVERSOR)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRECIBO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPANELES, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRECIBO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPANELES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlCLIENTE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NUM(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NUM
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_NUM

    private void CALC(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CALC
        // TODO add your handling code here:
        calcula();
    }//GEN-LAST:event_CALC


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCD1;
    private javax.swing.JLabel lblCD2;
    private javax.swing.JLabel lblCD3;
    private javax.swing.JLabel lblCD4;
    private javax.swing.JLabel lblCD5;
    private javax.swing.JLabel lblCD6;
    private javax.swing.JLabel lblCINVERSOR;
    private javax.swing.JLabel lblCOSTOT;
    private javax.swing.JLabel lblCPP;
    private javax.swing.JLabel lblIVA;
    private javax.swing.JLabel lblPP;
    private javax.swing.JLabel lblPROMANUAL;
    private javax.swing.JLabel lblPROMDIARIO;
    private javax.swing.JLabel lblPROMPAGOA;
    private javax.swing.JLabel lblPROMPAGOM;
    private javax.swing.JLabel lblRETORNO;
    private javax.swing.JLabel lblTOTALMO;
    private javax.swing.JLabel lblTOTALUSD;
    private javax.swing.JLabel lblVIDAUTIL;
    private javax.swing.JPanel pnlCLIENTE;
    private javax.swing.JPanel pnlPANELES;
    private javax.swing.JPanel pnlRECIBO;
    private javax.swing.JTextField txtBM1;
    private javax.swing.JTextField txtBM2;
    private javax.swing.JTextField txtBM3;
    private javax.swing.JTextField txtBM4;
    private javax.swing.JTextField txtBM5;
    private javax.swing.JTextField txtBM6;
    private javax.swing.JTextField txtBMC1;
    private javax.swing.JTextField txtBMC2;
    private javax.swing.JTextField txtBMC3;
    private javax.swing.JTextField txtBMC4;
    private javax.swing.JTextField txtBMC5;
    private javax.swing.JTextField txtBMC6;
    private javax.swing.JTextField txtCAMBIO220;
    private javax.swing.JTextField txtCAPACIDAD;
    private javax.swing.JTextField txtCOSTOINVERSOR;
    private javax.swing.JTextField txtCOSTOMO;
    private javax.swing.JTextField txtCOSTOPANELES;
    private javax.swing.JTextField txtDESCUENTO;
    private javax.swing.JTextField txtGANANCIA;
    private javax.swing.JTextField txtGTOTAL;
    private javax.swing.JTextField txtHRS;
    private javax.swing.JComboBox<String> txtINVERSOR;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JComboBox<String> txtPANELES;
    private javax.swing.JTextField txtRPU;
    private javax.swing.JTextField txtTC;
    private javax.swing.JTextField txtTOTAL;
    // End of variables declaration//GEN-END:variables
}
