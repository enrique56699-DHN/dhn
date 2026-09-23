/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor..
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;

import CONTROLADOR.ControlSalidas;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloEntradasTabla;
import MODELO.ModeloPagosTabla;
import MODELO.ModeloSalidasTabla;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KIKE FRENCH
 */
public class VRepUtilidades extends javax.swing.JInternalFrame {
    private float monto;   
    
    int Ano = 0, Mes = 0; 
    float tp,te,ts;
    
    /** Creates new form VClientes */
    public VRepUtilidades(){        
    }

    public VRepUtilidades(String titulo) throws ClassNotFoundException, SQLException {                
        initComponents();
        this.setTitle(titulo);
        permisos(Principal.PRIVILEGIOS);
        fechas();
        this.pnlCATS.setVisible(false);
        this.pnlCATE.setVisible(false);           
        F.teclas(this);
    }
    
    
    private void permisos(String PRIVILEGIOS)
    {
        if(PRIVILEGIOS.equals("SADMIN"))
        {
            /*btnEDITAR.setEnabled(true);
            btnCANCELAR.setEnabled(true);*/
        }
    }
    
    private void fechas()
    {
        //fecha=formateador.format(hoy);
        //int Ano1;
        //int Mes1;
        String []datos2 = G.FECHA(G.hoy(),false).split("/");
        int Mes1=Integer.parseInt(datos2[1]);
        int Ano1=Integer.parseInt(datos2[2]);
        
        switch(Mes1)
        {
            case 1:
                this.txtMES.setSelectedItem("ENERO");
                this.txtANO.setSelectedItem(Ano1);
                break;
            case 2:
                this.txtMES.setSelectedItem("FEBRERO");
                this.txtANO.setSelectedItem(Ano1);
                break;
            case 3:
                this.txtMES.setSelectedItem("MARZO");
                this.txtANO.setSelectedItem(Ano1);
                break;
            case 4:
                this.txtMES.setSelectedItem("ABRIL");
                this.txtANO.setSelectedItem(Ano1);
                break;
            case 5:
                this.txtMES.setSelectedItem("MAYO");
                this.txtANO.setSelectedItem(Ano1);
                break;
            case 6:
                this.txtMES.setSelectedItem("JUNIO");
                this.txtANO.setSelectedItem(Ano1);
                break;
            case 7:
                this.txtMES.setSelectedItem("JULIO");
                this.txtANO.setSelectedItem(Ano1);
                break;
            case 8:
                this.txtMES.setSelectedItem("AGOSTO");
                this.txtANO.setSelectedItem(Ano1);
                break;
            case 9:
                this.txtMES.setSelectedItem("SEPTIEMBRE");
                this.txtANO.setSelectedItem(Ano1);
                break;
            case 10:
                this.txtMES.setSelectedItem("OCTUBRE");
                this.txtANO.setSelectedItem(Ano1);
                break;
            case 11:
                this.txtMES.setSelectedItem("NOVIEMBRE");
                this.txtANO.setSelectedItem(Ano1);
                break;
            case 12:
                this.txtMES.setSelectedItem("DICIEMBRE");
                this.txtANO.setSelectedItem(Ano1);
                break;
            default:
                break;
        }

    }
    
    private int mes()
    {        
        String ms = this.txtMES.getSelectedItem().toString();
        switch(ms)
        {
            case "ENERO":
                Mes = 1;
                break;
            case "FEBRERO":
                Mes = 2;
                break;
            case "MARZO":
                Mes = 3;
                break;
            case "ABRIL":
                Mes = 4;
               break;
            case "MAYO":
                Mes = 5;
                break;
            case "JUNIO":
                Mes = 6;
                break;
            case "JULIO":
                Mes = 7;
                break;
            case "AGOSTO":
                Mes = 8;
                break;
            case "SEPTIEMBRE":
                Mes = 9;
                break;
            case "OCTUBRE":
                Mes = 10;
                break;
            case "NOVIEMBRE":
                Mes = 11;
                break;
            case "DICIEMBRE":
                Mes = 12;
                break;
            default:
                break;
                
        }
        
        return Mes;
    }
    
    private int Ano()
    {
        Ano = Integer.parseInt(txtANO.getSelectedItem().toString());
        return Ano;
    }
    
    private void rpagos(int Mes, int Ano) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloPagosTabla mtpn;
        mtpn = new ModeloPagosTabla();
                
        monto = 0;
        int Ano1;
        int Mes1;
        //int i = 0, a=0;
        String met;
        float tot=0,efe=0,oxx=0,tra=0,tar=0;
        
        int A = F.totalT("pagos")-1;
        
        for(int i=0; i<=A; i++)
        {
            String F = mtpn.getValueAt(i, 3).toString();
            String []datos2 = F.split("/");
            Mes1=Integer.parseInt(datos2[1]);
            Ano1=Integer.parseInt(datos2[2]);
            
            //VARIABLE DE METODO
            met = mtpn.getValueAt(i, 4).toString();
            
            //VARIABLE DE MONTO
            String d1 = mtpn.getValueAt(i, 5).toString();
            String d2 = d1.replaceAll("[$ ,]", "");
            monto = Float.parseFloat(d2);
            
            if(Mes==Mes1 && Ano == Ano1)
            {
                switch(met)
                {
                    case "EFECTIVO":
                        efe =  efe + monto;
                        tot = tot + monto;
                        break;
                    case "TRANSFERENCIA":
                        tra =  tra + monto;
                        tot = tot + monto;
                        break;
                    case "OXXO":
                        oxx =  oxx + monto;
                        tot = tot + monto;
                        break;
                    case "TARJETA":
                        tar =  tar + monto;
                        tot = tot + monto;
                        break;
                    default:
                        break;
                }
            }                
            
        }
        
        this.lblEFECTIVO.setText("$ "+G.numero.format(efe));
        this.lblTRANSFERENCIA.setText("$ "+G.numero.format(tra));
        this.lblOXXO.setText("$ "+G.numero.format(oxx));
        this.lblTARJETA.setText("$ "+G.numero.format(tar));
        this.lblTOTAL.setText("$ "+G.numero.format(tot));
        tp=tot;        
    }
    
    private void rentradas(int Mes, int Ano) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloEntradasTabla mtpn;
        mtpn = new ModeloEntradasTabla();
        
        monto = 0;
        int Ano1;
        int Mes1;
        String met;
        String cat;
        float tot=0,efe=0,oxx=0,tra=0,tar=0, che=0 ;
        float gene=0, magi=0, pago=0, pres=0;
        
        int A = F.totalT("entradas")-1;
        
        for(int i=0; i<=A; i++)
        {
            String F = mtpn.getValueAt(i, 4).toString();            
            String []datos2 = F.split("[/ ,]");
            Mes1=Integer.parseInt(datos2[1]);
            Ano1=Integer.parseInt(datos2[2]);
            
            //VARIABLE DE METODO
            met = mtpn.getValueAt(i, 5).toString();
            cat = mtpn.getValueAt(i, 3).toString();
            
             //VARIABLE DE MONTO
            String d1 = mtpn.getValueAt(i, 2).toString();
            String d2 = d1.replaceAll("[$ ,]", "");
            monto = Float.parseFloat(d2);
            
            if(Mes==Mes1 && Ano==Ano1)
            {
                if(!cat.equals("CORTE DE CAJA"))
                {
                    if(!cat.equals("AGENCIA"))
                    {
                        if(!cat.equals("TRASPASO ENTRE CUENTAS"))
                        {
                            switch(cat)
                            {
                                case "GENERAL":
                                    gene = gene + monto;
                                    break;
                                case "MAGISTV":
                                    magi = magi + monto;
                                    break;
                                case "PAGO":
                                    pago = pago + monto;
                                    break;
                                case "PRESTAMO":
                                    pres = pres + monto;
                                    break;
                                default:
                                    break;                                
                            }
                            
                            switch(met)
                            {
                                case "EFECTIVO":
                                    efe =  efe + monto;
                                    tot = tot + monto;
                                    break;
                                case "TRANSFERENCIA":
                                    tra =  tra + monto;
                                    tot = tot + monto;
                                    break;
                                case "OXXO":
                                    oxx =  oxx + monto;
                                    tot = tot + monto;
                                    break;
                                case "TARJETA":
                                    tar =  tar + monto;
                                    tot = tot + monto;
                                    break;
                                case "CHEQUE":
                                    che =  che + monto;
                                    tot = tot + monto;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }                   
                }                
            }
        }
        
        this.lblEFECTIVOE.setText("$ "+G.numero.format(efe));
        this.lblTRANSFERENCIAE.setText("$ "+G.numero.format(tra));
        this.lblOXXOE.setText("$ "+G.numero.format(oxx));
        this.lblTARJETAE.setText("$ "+G.numero.format(tar));
        this.lblCHEQUEE.setText("$ "+G.numero.format(che));
        this.lblTOTALE.setText("$ "+G.numero.format(tot));
        te=tot;
        this.lblGENERAL.setText("$ "+G.numero.format(gene));
        this.lblMAGISTV.setText("$ "+G.numero.format(magi));
        this.lblPAGO.setText("$ "+G.numero.format(pago));
        this.lblPRESTAMO.setText("$ "+G.numero.format(pres));
    }
    
    private void rsalidas(int Mes, int Ano) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloSalidasTabla mtpn;
        mtpn = new ModeloSalidasTabla();
        
        monto = 0;
        int Ano1;
        int Mes1;
        String met;
        String cat;
        float tot=0,efe=0,oxx=0,tra=0,tar=0, che=0 ;
        float cond=0, cons=0, elec=0, gaso=0, gast=0, herr=0, hono=0, impu=0, mant=0, pago=0, pape=0, prov=0, pres=0, limp=0, rent=0, subc=0, suel=0;
        
        int A = F.totalT("salidas")-1;
        
        for(int i=0; i<=A; i++)
        {
            //VARIABLE DE FECHA
            String F = mtpn.getValueAt(i, 4).toString();
            String []datos2 = F.split("[/ ,]");
            Mes1=Integer.parseInt(datos2[1]);
            Ano1=Integer.parseInt(datos2[2]);
            
            //VARIABLE DE METODO
            met = mtpn.getValueAt(i, 5).toString();
            cat = mtpn.getValueAt(i, 3).toString();
            
             //VARIABLE DE MONTO
            String d1 = mtpn.getValueAt(i, 2).toString();
            String d2 = d1.replaceAll("[$ ,]", "");
           
            monto = Float.parseFloat(d2);
            
            //if(Mes==Mes1&&Ano==Ano1)
            if(Mes==Mes1&&Ano==Ano1)
            {
                if(!cat.equals("CORTE DE CAJA"))
                {
                    if(!cat.equals("TOMA DE UTILIDADES"))
                    {
                        if(!cat.equals("VIATICOS"))
                        {
                            if(!cat.equals("TRASPASO ENTRE CUENTAS"))
                            {
                                switch(cat)
                                {
                                    case "CONDONACION":
                                        cond = cond + monto;
                                        break;
                                    case "CONSUMIBLES":
                                        cons = cons + monto;
                                        break;
                                    case "ELECTRICIDAD":
                                        elec = elec + monto;
                                        break;
                                    case "GASOLINA":
                                        gaso = gaso + monto;
                                        break;
                                    case "GASTOS VARIOS":
                                        gast = gast + monto;
                                        break;
                                    case "HERRAMIENTAS":
                                        herr = herr + monto;
                                        break;
                                    case "HONORARIOS":
                                        hono = hono + monto;
                                        break;
                                    case "IMPUESTOS":
                                        impu = impu + monto;
                                        break;
                                    case "MANTENIMIENTO DE VEHICULOS":
                                        mant = mant + monto;
                                        break;
                                    case "PAGO":
                                        pago = pago + monto;
                                        break;
                                    case "PAGO PROVEEDORES":
                                        prov = prov + monto;
                                        break;
                                    case "PAPELERIA":
                                        pape = pape + monto;
                                        break;
                                    case "PERSONAL SUBCONTRATADO":
                                        subc = subc + monto;
                                        break;
                                    case "PRESTAMO":
                                        pres = pres + monto;
                                        break;
                                    case "LIMPIEZA":
                                        limp = limp + monto;
                                        break;
                                    case "RENTA":
                                        rent = rent + monto;
                                        break;
                                    case "SUELDOS":
                                        suel = suel + monto;
                                        break;

                                    default: break;
                                }

                                switch(met)
                                {
                                    case "EFECTIVO":
                                        efe =  efe + monto;
                                        tot = tot + monto;
                                        break;
                                    case "TRANSFERENCIA":
                                        tra =  tra + monto;
                                        tot = tot + monto;
                                        break;
                                    case "OXXO":
                                        oxx =  oxx + monto;
                                        tot = tot + monto;
                                        break;
                                    case "TARJETA":
                                        tar =  tar + monto;
                                        tot = tot + monto;
                                        break;
                                    case "CHEQUE":
                                        che =  che + monto;
                                        tot = tot + monto;
                                        break;
                                    default: break;
                                }
                            }                            
                        }                        
                    }                    
                }
            }            
        }
        
        this.lblEFECTIVOS.setText("$ "+G.numero.format(efe));
        this.lblTRANSFERENCIAS.setText("$ "+G.numero.format(tra));
        this.lblOXXOS.setText("$ "+G.numero.format(oxx));
        this.lblTARJETAS.setText("$ "+G.numero.format(tar));
        this.lblCHEQUES.setText("$ "+G.numero.format(che));
        this.lblTOTALS.setText("$ "+G.numero.format(tot));
        ts=tot;
        
        this.lblCONDONACIONS.setText("$ "+G.numero.format(cond));
        this.lblCONSUMIBLES.setText("$ "+G.numero.format(cons));
        this.lblELECTRICIDADS.setText("$ "+G.numero.format(elec));
        this.lblGASOLINAS.setText("$ "+G.numero.format(gaso));
        this.lblGASTOSVARIOS.setText("$ "+G.numero.format(gast));
        this.lblHERRAMIENTAS.setText("$ "+G.numero.format(herr));
        this.lblHONORARIOS.setText("$ "+G.numero.format(hono));
        this.lblIMPUESTOS.setText("$ "+G.numero.format(impu));
        this.lblMANTENIMIENTOS.setText("$ "+G.numero.format(mant));
        this.lblPAGOS.setText("$ "+G.numero.format(pago));
        this.lblPAPELERIA.setText("$ "+G.numero.format(pape));
        this.lblPROVEEDORES.setText("$ "+G.numero.format(prov));
        this.lblPRESTAMOS.setText("$ "+G.numero.format(pres));
        this.lblPLIMPIEZA.setText("$ "+G.numero.format(limp));
        this.lblSUBCONTRATADOS.setText("$ "+G.numero.format(subc));
        this.lblRENTA.setText("$ "+G.numero.format(rent));
        this.lblSUELDOS.setText("$ "+G.numero.format(suel));
    }
    
    private void totales() throws ClassNotFoundException, SQLException, Exception
    {
        this.lblTP.setText("$ "+G.numero.format(tp));
        this.lblTE.setText("$ "+G.numero.format(te));
        this.lblTS.setText("$ "+G.numero.format(ts));
        float stot = tp + te - ts;
        this.lblTT.setText("$ "+G.numero.format(stot));
        float ENRIQUE = (float) (stot * 0.1);
        this.lblENRIQUE.setText("$ "+G.numero.format(ENRIQUE));
        float MONI = (float) (stot * 0.2);
        this.lblMONI.setText("$ "+G.numero.format(MONI));
        float OSCAR = (float) (stot * 0.7);
        this.lblOSCAR.setText("$ "+G.numero.format(OSCAR));
        
        ControlSalidas ctrlE = new ControlSalidas();
        float TUsE = ctrlE.TU("ENRIQUE", txtMES.getSelectedItem().toString(), txtANO.getSelectedItem().toString());    
        lblENRIQUEUT.setText("$ "+G.numero.format(TUsE));
        TUsE=ENRIQUE-TUsE;
        lblENRIQUEUR.setText("$ "+G.numero.format(TUsE));
        
        ControlSalidas ctrlM = new ControlSalidas();
        float TUsM = ctrlM.TU("MONI", txtMES.getSelectedItem().toString(), txtANO.getSelectedItem().toString());
        lblMONIUT.setText("$ "+G.numero.format(TUsM));
        TUsM=MONI-TUsM;
        lblMONIUR.setText("$ "+G.numero.format(TUsM));
         
        ControlSalidas ctrl1 = new ControlSalidas();
        float TUsO = ctrl1.TU("OSCAR", txtMES.getSelectedItem().toString(), txtANO.getSelectedItem().toString());
        lblOSCARUT.setText("$ "+G.numero.format(TUsO));
        TUsO=OSCAR-TUsO;
        lblOSCARUR.setText("$ "+G.numero.format(TUsO));
    }
    
    private void UD(boolean ac)
    {
        
            this.jpnlDE.setVisible(ac);
            this.jpnlDM.setVisible(ac);
            this.jpnlDO.setVisible(ac);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtANO = new javax.swing.JComboBox<>();
        txtMES = new javax.swing.JComboBox<>();
        btnCONSULTA = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        pnlPAGOS = new javax.swing.JPanel();
        lblEFECTIVO = new javax.swing.JLabel();
        lblTRANSFERENCIA = new javax.swing.JLabel();
        lblOXXO = new javax.swing.JLabel();
        lblTARJETA = new javax.swing.JLabel();
        lblTOTAL = new javax.swing.JLabel();
        pblENTRADAS = new javax.swing.JPanel();
        lblEFECTIVOE = new javax.swing.JLabel();
        lblTRANSFERENCIAE = new javax.swing.JLabel();
        lblOXXOE = new javax.swing.JLabel();
        lblTARJETAE = new javax.swing.JLabel();
        lblTOTALE = new javax.swing.JLabel();
        pnlCATE = new javax.swing.JPanel();
        lblPAGO = new javax.swing.JLabel();
        lblGENERAL = new javax.swing.JLabel();
        lblPRESTAMO = new javax.swing.JLabel();
        lblMAGISTV = new javax.swing.JLabel();
        lblCHEQUEE = new javax.swing.JLabel();
        tbtnDE = new javax.swing.JToggleButton();
        pblSALIDAS = new javax.swing.JPanel();
        lblEFECTIVOS = new javax.swing.JLabel();
        lblTRANSFERENCIAS = new javax.swing.JLabel();
        lblOXXOS = new javax.swing.JLabel();
        lblTARJETAS = new javax.swing.JLabel();
        pnlCATS = new javax.swing.JPanel();
        lblELECTRICIDADS = new javax.swing.JLabel();
        lblCONDONACIONS = new javax.swing.JLabel();
        lblGASOLINAS = new javax.swing.JLabel();
        lblCONSUMIBLES = new javax.swing.JLabel();
        lblGASTOSVARIOS = new javax.swing.JLabel();
        lblHONORARIOS = new javax.swing.JLabel();
        lblIMPUESTOS = new javax.swing.JLabel();
        lblMANTENIMIENTOS = new javax.swing.JLabel();
        lblHERRAMIENTAS = new javax.swing.JLabel();
        lblPAGOS = new javax.swing.JLabel();
        lblSUELDOS = new javax.swing.JLabel();
        lblSUBCONTRATADOS = new javax.swing.JLabel();
        lblPAPELERIA = new javax.swing.JLabel();
        lblRENTA = new javax.swing.JLabel();
        lblPLIMPIEZA = new javax.swing.JLabel();
        lblPRESTAMOS = new javax.swing.JLabel();
        lblPROVEEDORES = new javax.swing.JLabel();
        lblCHEQUES = new javax.swing.JLabel();
        lblTOTALS = new javax.swing.JLabel();
        tbtnDS = new javax.swing.JToggleButton();
        pblUTILIDADES = new javax.swing.JPanel();
        lblTE = new javax.swing.JLabel();
        lblTS = new javax.swing.JLabel();
        lblTT = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblOSCAR = new javax.swing.JLabel();
        lblENRIQUE = new javax.swing.JLabel();
        lblMONI = new javax.swing.JLabel();
        jpnlDE = new javax.swing.JPanel();
        lblENRIQUEUT = new javax.swing.JLabel();
        lblENRIQUEUR = new javax.swing.JLabel();
        jpnlDM = new javax.swing.JPanel();
        lblMONIUT = new javax.swing.JLabel();
        lblMONIUR = new javax.swing.JLabel();
        jpnlDO = new javax.swing.JPanel();
        lblOSCARUT = new javax.swing.JLabel();
        lblOSCARUR = new javax.swing.JLabel();
        lblTP = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setTitle("Utilidades");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/utilidades.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtANO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtANO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        txtANO.setSelectedIndex(3);
        txtANO.setToolTipText("Año a consultar");
        txtANO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Año:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtMES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMES.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
        txtMES.setToolTipText("Mes a consultar");
        txtMES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mes:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        btnCONSULTA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/consulta.png"))); // NOI18N
        btnCONSULTA.setToolTipText("");
        btnCONSULTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCONSULTAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMES, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtANO, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCONSULTA)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtANO, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMES, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCONSULTA))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlPAGOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pagos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lblEFECTIVO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEFECTIVO.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEFECTIVO.setText("$ 0.00");
        lblEFECTIVO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EFECTIVO:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTRANSFERENCIA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTRANSFERENCIA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTRANSFERENCIA.setText("$ 0.00");
        lblTRANSFERENCIA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TRANSFERENCIA:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblOXXO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblOXXO.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOXXO.setText("$ 0.00");
        lblOXXO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OXXO:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTARJETA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTARJETA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTARJETA.setText("$ 0.00");
        lblTARJETA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TARJETA:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTOTAL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTOTAL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTOTAL.setText("$ 0.00");
        lblTOTAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        javax.swing.GroupLayout pnlPAGOSLayout = new javax.swing.GroupLayout(pnlPAGOS);
        pnlPAGOS.setLayout(pnlPAGOSLayout);
        pnlPAGOSLayout.setHorizontalGroup(
            pnlPAGOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPAGOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPAGOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEFECTIVO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTRANSFERENCIA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(lblOXXO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTARJETA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTOTAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPAGOSLayout.setVerticalGroup(
            pnlPAGOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPAGOSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEFECTIVO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTRANSFERENCIA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOXXO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTARJETA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTOTAL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pblENTRADAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entradas:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lblEFECTIVOE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEFECTIVOE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEFECTIVOE.setText("$ 0.00");
        lblEFECTIVOE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EFECTIVO:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTRANSFERENCIAE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTRANSFERENCIAE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTRANSFERENCIAE.setText("$ 0.00");
        lblTRANSFERENCIAE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TRANSFERENCIA:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblOXXOE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblOXXOE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOXXOE.setText("$ 0.00");
        lblOXXOE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OXXO:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTARJETAE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTARJETAE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTARJETAE.setText("$ 0.00");
        lblTARJETAE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TARJETA:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTOTALE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTOTALE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTOTALE.setText("$ 0.00");
        lblTOTALE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        pnlCATE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Categorias:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblPAGO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPAGO.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPAGO.setText("$ 0.00");
        lblPAGO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PAGO:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblGENERAL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGENERAL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGENERAL.setText("$ 0.00");
        lblGENERAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GENERAL:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblPRESTAMO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPRESTAMO.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPRESTAMO.setText("$ 0.00");
        lblPRESTAMO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRESTAMO:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblMAGISTV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMAGISTV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMAGISTV.setText("$ 0.00");
        lblMAGISTV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MAGISTV:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        javax.swing.GroupLayout pnlCATELayout = new javax.swing.GroupLayout(pnlCATE);
        pnlCATE.setLayout(pnlCATELayout);
        pnlCATELayout.setHorizontalGroup(
            pnlCATELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCATELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCATELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPRESTAMO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPAGO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMAGISTV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGENERAL, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCATELayout.setVerticalGroup(
            pnlCATELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCATELayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGENERAL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMAGISTV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPAGO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPRESTAMO)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCHEQUEE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCHEQUEE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCHEQUEE.setText("$ 0.00");
        lblCHEQUEE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CHEQUE:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        tbtnDE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbtnDE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/details.png"))); // NOI18N
        tbtnDE.setToolTipText("Mostrar Detalles");
        tbtnDE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnDEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pblENTRADASLayout = new javax.swing.GroupLayout(pblENTRADAS);
        pblENTRADAS.setLayout(pblENTRADASLayout);
        pblENTRADASLayout.setHorizontalGroup(
            pblENTRADASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblENTRADASLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pblENTRADASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pblENTRADASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblTARJETAE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblOXXOE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTRANSFERENCIAE, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addComponent(lblEFECTIVOE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblCHEQUEE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pblENTRADASLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblTOTALE, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tbtnDE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCATE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pblENTRADASLayout.setVerticalGroup(
            pblENTRADASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblENTRADASLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pblENTRADASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCATE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pblENTRADASLayout.createSequentialGroup()
                        .addComponent(lblEFECTIVOE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTRANSFERENCIAE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOXXOE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTARJETAE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCHEQUEE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTOTALE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbtnDE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pblSALIDAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salidas:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lblEFECTIVOS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEFECTIVOS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEFECTIVOS.setText("$ 0.00");
        lblEFECTIVOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EFECTIVO:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTRANSFERENCIAS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTRANSFERENCIAS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTRANSFERENCIAS.setText("$ 0.00");
        lblTRANSFERENCIAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TRANSFERENCIA:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblOXXOS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblOXXOS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOXXOS.setText("$ 0.00");
        lblOXXOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OXXO:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTARJETAS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTARJETAS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTARJETAS.setText("$ 0.00");
        lblTARJETAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TARJETA:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        pnlCATS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Categorias:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblELECTRICIDADS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblELECTRICIDADS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblELECTRICIDADS.setText("$ 0.00");
        lblELECTRICIDADS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ELECTRICIDAD:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblCONDONACIONS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCONDONACIONS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCONDONACIONS.setText("$ 0.00");
        lblCONDONACIONS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CONDONACION:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblGASOLINAS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGASOLINAS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGASOLINAS.setText("$ 0.00");
        lblGASOLINAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GASOLINA:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblCONSUMIBLES.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCONSUMIBLES.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCONSUMIBLES.setText("$ 0.00");
        lblCONSUMIBLES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CONSUMIBLES.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblGASTOSVARIOS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGASTOSVARIOS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGASTOSVARIOS.setText("$ 0.00");
        lblGASTOSVARIOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GASTOS VARIOS:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblHONORARIOS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblHONORARIOS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHONORARIOS.setText("$ 0.00");
        lblHONORARIOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HONORARIOS:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblIMPUESTOS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblIMPUESTOS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIMPUESTOS.setText("$ 0.00");
        lblIMPUESTOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IMPUESTOS:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblMANTENIMIENTOS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMANTENIMIENTOS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMANTENIMIENTOS.setText("$ 0.00");
        lblMANTENIMIENTOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MANTENIMIENTOS:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblHERRAMIENTAS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblHERRAMIENTAS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHERRAMIENTAS.setText("$ 0.00");
        lblHERRAMIENTAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HERRAMIENTAS:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblPAGOS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPAGOS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPAGOS.setText("$ 0.00");
        lblPAGOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PAGOS:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblSUELDOS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSUELDOS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSUELDOS.setText("$ 0.00");
        lblSUELDOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SUELDOS:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblSUBCONTRATADOS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSUBCONTRATADOS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSUBCONTRATADOS.setText("$ 0.00");
        lblSUBCONTRATADOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SUBCONTRATADOS:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblPAPELERIA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPAPELERIA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPAPELERIA.setText("$ 0.00");
        lblPAPELERIA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PAPELERIA:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblRENTA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRENTA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRENTA.setText("$ 0.00");
        lblRENTA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RENTA:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblPLIMPIEZA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPLIMPIEZA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPLIMPIEZA.setText("$ 0.00");
        lblPLIMPIEZA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PROD. LIMPIEZA:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblPRESTAMOS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPRESTAMOS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPRESTAMOS.setText("$ 0.00");
        lblPRESTAMOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRESTAMOS:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblPROVEEDORES.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPROVEEDORES.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPROVEEDORES.setText("$ 0.00");
        lblPROVEEDORES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PAGOS A PROVEEDORES:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        javax.swing.GroupLayout pnlCATSLayout = new javax.swing.GroupLayout(pnlCATS);
        pnlCATS.setLayout(pnlCATSLayout);
        pnlCATSLayout.setHorizontalGroup(
            pnlCATSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCATSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCATSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPRESTAMOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCONDONACIONS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCONSUMIBLES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblELECTRICIDADS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGASOLINAS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGASTOSVARIOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHERRAMIENTAS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHONORARIOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIMPUESTOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMANTENIMIENTOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPAGOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPROVEEDORES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPAPELERIA, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(lblSUBCONTRATADOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPLIMPIEZA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRENTA, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(lblSUELDOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCATSLayout.setVerticalGroup(
            pnlCATSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCATSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCONDONACIONS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCONSUMIBLES)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblELECTRICIDADS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGASOLINAS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGASTOSVARIOS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHERRAMIENTAS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHONORARIOS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIMPUESTOS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMANTENIMIENTOS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPAGOS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPAPELERIA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPROVEEDORES)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPRESTAMOS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPLIMPIEZA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRENTA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSUBCONTRATADOS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSUELDOS)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCHEQUES.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCHEQUES.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCHEQUES.setText("$ 0.00");
        lblCHEQUES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CHEQUE:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTOTALS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTOTALS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTOTALS.setText("$ 0.00");
        lblTOTALS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        tbtnDS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbtnDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/details.png"))); // NOI18N
        tbtnDS.setToolTipText("Mostrar Detalles");
        tbtnDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnDSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pblSALIDASLayout = new javax.swing.GroupLayout(pblSALIDAS);
        pblSALIDAS.setLayout(pblSALIDASLayout);
        pblSALIDASLayout.setHorizontalGroup(
            pblSALIDASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblSALIDASLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pblSALIDASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pblSALIDASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblTARJETAS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblOXXOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTRANSFERENCIAS, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addComponent(lblEFECTIVOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTOTALS, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addComponent(lblCHEQUES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tbtnDS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCATS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pblSALIDASLayout.setVerticalGroup(
            pblSALIDASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblSALIDASLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pblSALIDASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCATS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pblSALIDASLayout.createSequentialGroup()
                        .addComponent(lblEFECTIVOS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTRANSFERENCIAS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOXXOS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTARJETAS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCHEQUES)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTOTALS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbtnDS)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pblUTILIDADES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Utilidades:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lblTE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTE.setText("$ 0.00");
        lblTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entradas:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTS.setText("$ 0.00");
        lblTS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Saldias:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTT.setText("$ 0.00");
        lblTT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Socios:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblOSCAR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblOSCAR.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOSCAR.setText("$ 0.00");
        lblOSCAR.setToolTipText("");
        lblOSCAR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OSCAR:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblENRIQUE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblENRIQUE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblENRIQUE.setText("$ 0.00");
        lblENRIQUE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ENRIQUE:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblMONI.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMONI.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMONI.setText("$ 0.00");
        lblMONI.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MONI:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jpnlDE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblENRIQUEUT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblENRIQUEUT.setForeground(new java.awt.Color(0, 51, 153));
        lblENRIQUEUT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblENRIQUEUT.setText("$ 0.00");
        lblENRIQUEUT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tomadas:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblENRIQUEUR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblENRIQUEUR.setForeground(new java.awt.Color(204, 0, 51));
        lblENRIQUEUR.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblENRIQUEUR.setText("$ 0.00");
        lblENRIQUEUR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Restantes:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        javax.swing.GroupLayout jpnlDELayout = new javax.swing.GroupLayout(jpnlDE);
        jpnlDE.setLayout(jpnlDELayout);
        jpnlDELayout.setHorizontalGroup(
            jpnlDELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlDELayout.createSequentialGroup()
                .addGroup(jpnlDELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblENRIQUEUT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(lblENRIQUEUR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnlDELayout.setVerticalGroup(
            jpnlDELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlDELayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblENRIQUEUT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblENRIQUEUR)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnlDM.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblMONIUT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMONIUT.setForeground(new java.awt.Color(0, 51, 153));
        lblMONIUT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMONIUT.setText("$ 0.00");
        lblMONIUT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tomadas:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblMONIUR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMONIUR.setForeground(new java.awt.Color(204, 0, 51));
        lblMONIUR.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMONIUR.setText("$ 0.00");
        lblMONIUR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Restantes:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        javax.swing.GroupLayout jpnlDMLayout = new javax.swing.GroupLayout(jpnlDM);
        jpnlDM.setLayout(jpnlDMLayout);
        jpnlDMLayout.setHorizontalGroup(
            jpnlDMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlDMLayout.createSequentialGroup()
                .addGroup(jpnlDMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMONIUR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMONIUT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnlDMLayout.setVerticalGroup(
            jpnlDMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlDMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMONIUT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMONIUR)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnlDO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblOSCARUT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblOSCARUT.setForeground(new java.awt.Color(0, 51, 153));
        lblOSCARUT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOSCARUT.setText("$ 0.00");
        lblOSCARUT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tomadas:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblOSCARUR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblOSCARUR.setForeground(new java.awt.Color(204, 0, 51));
        lblOSCARUR.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOSCARUR.setText("$ 0.00");
        lblOSCARUR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Restantes:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        javax.swing.GroupLayout jpnlDOLayout = new javax.swing.GroupLayout(jpnlDO);
        jpnlDO.setLayout(jpnlDOLayout);
        jpnlDOLayout.setHorizontalGroup(
            jpnlDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlDOLayout.createSequentialGroup()
                .addGroup(jpnlDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOSCARUR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblOSCARUT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnlDOLayout.setVerticalGroup(
            jpnlDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlDOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOSCARUT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOSCARUR)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblENRIQUE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlDE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMONI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlDM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblOSCAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlDO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblENRIQUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlDE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMONI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOSCAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlDO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTP.setText("$ 0.00");
        lblTP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pagos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        javax.swing.GroupLayout pblUTILIDADESLayout = new javax.swing.GroupLayout(pblUTILIDADES);
        pblUTILIDADES.setLayout(pblUTILIDADESLayout);
        pblUTILIDADESLayout.setHorizontalGroup(
            pblUTILIDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblUTILIDADESLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pblUTILIDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pblUTILIDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTE, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                    .addComponent(lblTP, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pblUTILIDADESLayout.setVerticalGroup(
            pblUTILIDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblUTILIDADESLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pblUTILIDADESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pblUTILIDADESLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblTP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTT)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPAGOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pblENTRADAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pblSALIDAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pblUTILIDADES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pblSALIDAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pnlPAGOS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pblENTRADAS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pblUTILIDADES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCONSULTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCONSULTAActionPerformed
        try {
            rpagos(mes(),Ano());
            rentradas(mes(),Ano());
            rsalidas(mes(),Ano());
            totales();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VRepUtilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VRepUtilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCONSULTAActionPerformed

    private void tbtnDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnDSActionPerformed
        if(this.tbtnDS.isSelected())        
            this.pnlCATS.setVisible(true);        
        else
            this.pnlCATS.setVisible(false);
    }//GEN-LAST:event_tbtnDSActionPerformed

    private void tbtnDEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnDEActionPerformed
        if(this.tbtnDE.isSelected())        
            this.pnlCATE.setVisible(true);        
        else
            this.pnlCATE.setVisible(false);
    }//GEN-LAST:event_tbtnDEActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCONSULTA;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jpnlDE;
    private javax.swing.JPanel jpnlDM;
    private javax.swing.JPanel jpnlDO;
    private javax.swing.JLabel lblCHEQUEE;
    private javax.swing.JLabel lblCHEQUES;
    private javax.swing.JLabel lblCONDONACIONS;
    private javax.swing.JLabel lblCONSUMIBLES;
    private javax.swing.JLabel lblEFECTIVO;
    private javax.swing.JLabel lblEFECTIVOE;
    private javax.swing.JLabel lblEFECTIVOS;
    private javax.swing.JLabel lblELECTRICIDADS;
    private javax.swing.JLabel lblENRIQUE;
    private javax.swing.JLabel lblENRIQUEUR;
    private javax.swing.JLabel lblENRIQUEUT;
    private javax.swing.JLabel lblGASOLINAS;
    private javax.swing.JLabel lblGASTOSVARIOS;
    private javax.swing.JLabel lblGENERAL;
    private javax.swing.JLabel lblHERRAMIENTAS;
    private javax.swing.JLabel lblHONORARIOS;
    private javax.swing.JLabel lblIMPUESTOS;
    private javax.swing.JLabel lblMAGISTV;
    private javax.swing.JLabel lblMANTENIMIENTOS;
    private javax.swing.JLabel lblMONI;
    private javax.swing.JLabel lblMONIUR;
    private javax.swing.JLabel lblMONIUT;
    private javax.swing.JLabel lblOSCAR;
    private javax.swing.JLabel lblOSCARUR;
    private javax.swing.JLabel lblOSCARUT;
    private javax.swing.JLabel lblOXXO;
    private javax.swing.JLabel lblOXXOE;
    private javax.swing.JLabel lblOXXOS;
    private javax.swing.JLabel lblPAGO;
    private javax.swing.JLabel lblPAGOS;
    private javax.swing.JLabel lblPAPELERIA;
    private javax.swing.JLabel lblPLIMPIEZA;
    private javax.swing.JLabel lblPRESTAMO;
    private javax.swing.JLabel lblPRESTAMOS;
    private javax.swing.JLabel lblPROVEEDORES;
    private javax.swing.JLabel lblRENTA;
    private javax.swing.JLabel lblSUBCONTRATADOS;
    private javax.swing.JLabel lblSUELDOS;
    private javax.swing.JLabel lblTARJETA;
    private javax.swing.JLabel lblTARJETAE;
    private javax.swing.JLabel lblTARJETAS;
    private javax.swing.JLabel lblTE;
    private javax.swing.JLabel lblTOTAL;
    private javax.swing.JLabel lblTOTALE;
    private javax.swing.JLabel lblTOTALS;
    private javax.swing.JLabel lblTP;
    private javax.swing.JLabel lblTRANSFERENCIA;
    private javax.swing.JLabel lblTRANSFERENCIAE;
    private javax.swing.JLabel lblTRANSFERENCIAS;
    private javax.swing.JLabel lblTS;
    private javax.swing.JLabel lblTT;
    private javax.swing.JPanel pblENTRADAS;
    private javax.swing.JPanel pblSALIDAS;
    private javax.swing.JPanel pblUTILIDADES;
    private javax.swing.JPanel pnlCATE;
    private javax.swing.JPanel pnlCATS;
    private javax.swing.JPanel pnlPAGOS;
    private javax.swing.JToggleButton tbtnDE;
    private javax.swing.JToggleButton tbtnDS;
    private javax.swing.JComboBox<String> txtANO;
    private javax.swing.JComboBox<String> txtMES;
    // End of variables declaration//GEN-END:variables

    
}

