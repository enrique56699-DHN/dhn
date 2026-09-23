/*

 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;

import CONTROLADOR.ControlAbonos;
import CONTROLADOR.ControlAhorros;
import CONTROLADOR.ControlBonosD;
import CONTROLADOR.ControlEmpleados;
import CONTROLADOR.ControlEntradasCaja;
import CONTROLADOR.ControlPrestaciones;
import CONTROLADOR.ControlPrestamos;
import CONTROLADOR.ControlSalidasCaja;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloPrestaciones;


/**
 *
 * @author KIKE FRENCH
 */
public final class VRepFinanzas extends javax.swing.JInternalFrame {
    
    /** Creates new form VRepPagos
     * @throws java.lang.Exception
        */    
    /*private double total,disponible,deuda,aportaciones,disponiblesDHN,disponiblesEDH,aguinaldosDHN, aguinaldosEDH;
    private double prestamos, abonos, ahorros, dhn, edh,entradasCajaDHN,salidasCajaDHN,entradasCajaEDH,salidasCajaEDH,tcdhn,tcedh,entradasCajaCAJA,salidasCajaCAJA,tccaja,bonosddhn, bonosdedh,dhnp,edhp;
    private int sdhn,sedh;*/
    
    public VRepFinanzas(){
        //initComponents();     
    }
    
    public VRepFinanzas(String titulo) throws Exception{
        initComponents();
        this.setTitle(titulo);
        totales();    
        mostrar();
        F.teclas(this);
    }

    /*private void totales() throws ClassNotFoundException, SQLException, Exception
    {

        lblDHN.setText("$ 0.00");
        lblELECTRONICS.setText("$ 0.00");
        lblPRESTAMOS.setText("$ 0.00");
        lblABONOS.setText("$ 0.00");
        lblAHORROS.setText("$ 0.00");        
        lblTOTAL.setText("$ 0.00");
        
        //TOTAL APORTACION POR EMPRESA POR BONOS O BONOSP
        ControlEmpleados ce = new ControlEmpleados();
        
        dhn=ce.suma("empleados","BONO","empresa","DHN");
        dhnp=ce.suma("empleados","BONOP","empresa","DHN");
        
        edh=ce.suma("empleados","BONO","empresa","ELECTRONICS DIGITAL HOME");
        edhp=ce.suma("empleados","BONOP","empresa","ELECTRONICS DIGITAL HOME");
        
        lblDHN.setText("$ "+G.numero.format(dhn)+".00");
        lblELECTRONICS.setText("$ "+G.numero.format(edh)+".00");
        
        lblDHNP.setText("$ "+G.numero.format(dhnp)+".00");
        lblELECTRONICSP.setText("$ "+G.numero.format(edhp)+".00");
        
        lblDHN.setText("$ "+G.numero.format(dhn)+".00");
        lblELECTRONICS.setText("$ "+G.numero.format(edh)+".00");
        
        //TOTAL DE ABONOS
        ControlAbonos ca = new ControlAbonos();
        abonos=ca.suma();
        lblABONOS.setText("$ "+G.numero.format(abonos)+".00");
        
        //TOTAL AHORROS
        ControlAhorros ch = new ControlAhorros();
        ahorros=ch.suma();
        lblAHORROS.setText("$ "+G.numero.format(ahorros)+".00");
        
        //TOTAL PRESTAMOS
        ControlPrestamos cp = new ControlPrestamos();
        prestamos=cp.suma()*-1; 
        lblPRESTAMOS.setText("$ "+G.numero.format(prestamos)+".00");
        
        //TOTAL DEUDA
        deuda=prestamos+abonos;
        lblDEUDA.setText("$ "+G.numero.format(deuda)+".00");
        
        //TOTAL DISPONIBLES
        disponible=ahorros+deuda;
        lblDISPONIBLES.setText("$ "+G.numero.format(disponible)+".00");
        
        //MOVIMIENTOS DE CAJA DE DHN
        ControlEntradasCaja cec = new ControlEntradasCaja();
        entradasCajaDHN=cec.suma("DHN");
        lblECAJADHN.setText("$ "+G.numero.format(entradasCajaDHN)+".00");
        
        ControlSalidasCaja cac = new ControlSalidasCaja();      
        salidasCajaDHN=cac.suma("DHN");
        lblSCAJADHN.setText("$ "+G.numero.format(salidasCajaDHN)+".00");
        
        //MOVIMIENTOS DE CAJA DE ELECTRONICS
        ControlEntradasCaja cece = new ControlEntradasCaja();
        entradasCajaEDH=cece.suma("ELECTRONICS DIGITAL HOME");
        lblECAJAELE.setText("$ "+G.numero.format(entradasCajaEDH)+".00");
        
        ControlSalidasCaja cace = new ControlSalidasCaja();      
        salidasCajaEDH=cace.suma("ELECTRONICS DIGITAL HOME");
        lblSCAJAELE.setText("$ "+G.numero.format(salidasCajaEDH)+".00");
        
        //MOVIMIENTOS DE CAJA DE CAJA DE AHORRO
        ControlEntradasCaja cecc = new ControlEntradasCaja();
        entradasCajaCAJA=cecc.suma("CAJA DE AHORRO");
        lblECAJACAJA.setText("$ "+G.numero.format(entradasCajaCAJA)+".00");
        
        ControlSalidasCaja cacc = new ControlSalidasCaja();      
        salidasCajaCAJA=cacc.suma("CAJA DE AHORRO");
        lblSCAJACAJA.setText("$ "+G.numero.format(salidasCajaCAJA)+".00");
        
        //TOTAL MOVIMIENTOS DE DHN
        tcdhn=entradasCajaDHN-salidasCajaDHN;
        lblTCDHN.setText("$ "+G.numero.format(tcdhn)+".00");
        
        //TOTAL DE MOVIMIENTOS ELECTRONICS
        tcedh=entradasCajaEDH-salidasCajaEDH;
        lblTCELE.setText("$ "+G.numero.format(tcedh)+".00");
        
        //TOTAL DE MOVIMIENTOS DE CAJA DE AHORRO
        tccaja=entradasCajaCAJA-salidasCajaCAJA;
        lblTCCAJA.setText("$ "+G.numero.format(tccaja)+".00");
        
        //TOTAL APORTACIONES
        aportaciones=tcdhn+tcedh+tccaja;
        lblTAPORTACIONES.setText("$ "+G.numero.format(aportaciones)+".00");
        
        //TOTAL GLOBAL EN CAJA
        total=prestamos+abonos+ahorros+entradasCajaDHN-salidasCajaDHN+entradasCajaEDH-salidasCajaEDH+entradasCajaCAJA-salidasCajaCAJA;
        lblTOTAL.setText("$ "+G.numero.format(total)+".00");
        
        //CALCULO DE AGUINALDOS
        ControlEmpleados cead = new ControlEmpleados();
        aguinaldosDHN = cead.aguinaldo(0, "DHN");
        lblAGUINALDODHN.setText("$ "+G.numero.format(aguinaldosDHN));
        
        ControlEmpleados ceae = new ControlEmpleados();
        aguinaldosEDH = ceae.aguinaldo(0, "ELECTRONICS DIGITAL HOME");
        lblAGUINALDOELE.setText("$ "+G.numero.format(aguinaldosEDH));
        
        //TOTAL DE BONOS PAGADOS
        ControlBonosD cbdc = new ControlBonosD();
        bonosddhn=cbdc.suma("DHN");
        lblBONOSDHN.setText("$ "+G.numero.format(bonosddhn)+".00");
        
        ControlBonosD cbec = new ControlBonosD();
        bonosdedh=cbec.suma("ELECTRONICS DIGITAL HOME");
        lblBONOSEDH.setText("$ "+G.numero.format(bonosdedh)+".00");
        
        //DISPONIBLES 
        disponiblesDHN=tcdhn-dhn-aguinaldosDHN-bonosddhn-dhnp;
        lblDISPDHN.setText("$ "+G.numero.format(disponiblesDHN));
        
        disponiblesEDH=tcedh-edh-aguinaldosEDH-bonosdedh-edhp;
        lblDISPELE.setText("$ "+G.numero.format(disponiblesEDH));
        
        //SUGERIDOS SEMANALES
        sdhn=(int)(dhn+dhnp+aguinaldosDHN)/52;
        lblDHNSUGERIDO.setText("$ "+G.numero.format(sdhn)+".00");
        sedh=(int)(edh+edhp+aguinaldosEDH)/52;
        lblELESUGERIDO.setText("$ "+G.numero.format(sedh)+".00");        
        
    }
    
    private void mostrar() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloPrestaciones mdlp; 
        ControlPrestaciones ctrlp = new ControlPrestaciones();
        mdlp=ctrlp.Registros("AGUINALDO", "DESC");
        this.txtAGUINALDO.setText(mdlp.getPERIODO()+" - "+mdlp.getFINI()+ " -- "+mdlp.getFFIN());

        ModeloPrestaciones mdlb;
        ControlPrestaciones ctrlb = new ControlPrestaciones();
        mdlb=ctrlb.Registros("BONO DE CAJA", "DESC");
        this.txtBONO.setText(mdlb.getPERIODO()+" - "+mdlb.getFINI()+ " -- "+mdlb.getFFIN());
        
        ModeloPrestaciones mdlbp;
        ControlPrestaciones ctrlbp = new ControlPrestaciones();
        mdlbp=ctrlbp.Registros("BONO DE PRODUCTIVIDAD", "DESC");
        this.txtBONOP.setText(mdlbp.getPERIODO()+" - "+mdlbp.getFINI()+ " -- "+mdlbp.getFFIN());
    }*/
    
    private static final String EDH_COMPANY = "ELECTRONICS DIGITAL HOME";
    private static final String DHN_COMPANY = "DHN";

    private void totales() throws Exception 
    {
        
        double total,disponible,deuda,aportaciones,disponiblesDHN,disponiblesEDH,aguinaldosDHN, aguinaldosEDH;
        double prestamos, abonos, ahorros, dhn, edh,entradasCajaDHN,salidasCajaDHN,entradasCajaEDH,salidasCajaEDH,tcdhn,tcedh,entradasCajaCAJA,salidasCajaCAJA,tccaja,bonosddhn, bonosdedh,dhnp,edhp;
        int sdhn,sedh;
        // 1. Reutilización de instancias de controladores
        ControlEmpleados controlEmpleados = new ControlEmpleados();
        ControlEntradasCaja controlEntradas = new ControlEntradasCaja();
        ControlSalidasCaja controlSalidas = new ControlSalidasCaja();
        ControlBonosD controlBonos = new ControlBonosD();

        // 2. Cálculos de Empleados
        dhn = controlEmpleados.suma("empleados", "BONO", "empresa", DHN_COMPANY);
        dhnp = controlEmpleados.suma("empleados", "BONOP", "empresa", DHN_COMPANY);
        edh = controlEmpleados.suma("empleados", "BONO", "empresa", EDH_COMPANY);
        edhp = controlEmpleados.suma("empleados", "BONOP", "empresa", EDH_COMPANY);

        // 3. Cálculos de Movimientos Finacieros
        abonos = new ControlAbonos().suma();
        ahorros = new ControlAhorros().suma();
        prestamos = new ControlPrestamos().suma() * -1;
        deuda = prestamos + abonos;
        disponible = ahorros + deuda;

        // 4. Movimientos de Caja por Empresa
        entradasCajaDHN = controlEntradas.suma(DHN_COMPANY);
        salidasCajaDHN = controlSalidas.suma(DHN_COMPANY);
        tcdhn = entradasCajaDHN - salidasCajaDHN;

        entradasCajaEDH = controlEntradas.suma(EDH_COMPANY);
        salidasCajaEDH = controlSalidas.suma(EDH_COMPANY);
        tcedh = entradasCajaEDH - salidasCajaEDH;

        entradasCajaCAJA = controlEntradas.suma("CAJA DE AHORRO");
        salidasCajaCAJA = controlSalidas.suma("CAJA DE AHORRO");
        tccaja = entradasCajaCAJA - salidasCajaCAJA;

        aportaciones = tcdhn + tcedh + tccaja;
        total = prestamos + abonos + ahorros + aportaciones;

        // 5. Aguinaldos y Bonos
        aguinaldosDHN = controlEmpleados.aguinaldo(0, DHN_COMPANY);
        aguinaldosEDH = controlEmpleados.aguinaldo(0, EDH_COMPANY);

        bonosddhn = controlBonos.suma(DHN_COMPANY);
        bonosdedh = controlBonos.suma(EDH_COMPANY);

        // 6. Disponibles y Sugeridos
        disponiblesDHN = tcdhn - dhn - aguinaldosDHN - bonosddhn - dhnp;
        disponiblesEDH = tcedh - edh - aguinaldosEDH - bonosdedh - edhp;

        sdhn = (int) (dhn + dhnp + aguinaldosDHN) / 52;
        sedh = (int) (edh + edhp + aguinaldosEDH) / 52;

        // 7. Actualización de UI
        lblDHN.setText(formatearMoneda(dhn));
        lblELECTRONICS.setText(formatearMoneda(edh));
        lblDHNP.setText(formatearMoneda(dhnp));
        lblELECTRONICSP.setText(formatearMoneda(edhp));
        lblABONOS.setText(formatearMoneda(abonos));
        lblAHORROS.setText(formatearMoneda(ahorros));
        lblPRESTAMOS.setText(formatearMoneda(prestamos));
        lblDEUDA.setText(formatearMoneda(deuda));
        lblDISPONIBLES.setText(formatearMoneda(disponible));

        lblECAJADHN.setText(formatearMoneda(entradasCajaDHN));
        lblSCAJADHN.setText(formatearMoneda(salidasCajaDHN));
        lblECAJAELE.setText(formatearMoneda(entradasCajaEDH));
        lblSCAJAELE.setText(formatearMoneda(salidasCajaEDH));
        lblECAJACAJA.setText(formatearMoneda(entradasCajaCAJA));
        lblSCAJACAJA.setText(formatearMoneda(salidasCajaCAJA));

        lblTCDHN.setText(formatearMoneda(tcdhn));
        lblTCELE.setText(formatearMoneda(tcedh));
        lblTCCAJA.setText(formatearMoneda(tccaja));
        lblTAPORTACIONES.setText(formatearMoneda(aportaciones));
        lblTOTAL.setText(formatearMoneda(total));

        lblAGUINALDODHN.setText(formatearMoneda(aguinaldosDHN));
        lblAGUINALDOELE.setText(formatearMoneda(aguinaldosEDH));
        lblBONOSDHN.setText(formatearMoneda(bonosddhn));
        lblBONOSEDH.setText(formatearMoneda(bonosdedh));

        lblDISPDHN.setText(formatearMoneda(disponiblesDHN));
        lblDISPELE.setText(formatearMoneda(disponiblesEDH));

        lblDHNSUGERIDO.setText(formatearMoneda(sdhn));
        lblELESUGERIDO.setText(formatearMoneda(sedh));
    }

    private void mostrar() throws Exception {
        ControlPrestaciones ctrl = new ControlPrestaciones();

        txtAGUINALDO.setText(obtenerRangoPrestacion(ctrl, "AGUINALDO"));
        txtBONO.setText(obtenerRangoPrestacion(ctrl, "BONO DE CAJA"));
        txtBONOP.setText(obtenerRangoPrestacion(ctrl, "BONO DE PRODUCTIVIDAD"));
    }

    // Métodos auxiliares de limpieza
    private String formatearMoneda(double monto) {
        return "$ " + G.numero.format(monto) + ".00";
    }

    private String obtenerRangoPrestacion(ControlPrestaciones ctrl, String tipo) throws Exception {
        ModeloPrestaciones mdl = ctrl.Registros(tipo, "DESC");
        return mdl.getPERIODO() + " - " + mdl.getFINI() + " -- " + mdl.getFFIN();
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
        jpCAJA2 = new javax.swing.JPanel();
        jpDHN = new javax.swing.JPanel();
        lblDHN = new javax.swing.JLabel();
        lblDHNSUGERIDO = new javax.swing.JLabel();
        lblECAJADHN = new javax.swing.JLabel();
        lblSCAJADHN = new javax.swing.JLabel();
        lblTCDHN = new javax.swing.JLabel();
        lblDISPDHN = new javax.swing.JLabel();
        lblAGUINALDODHN = new javax.swing.JLabel();
        lblBONOSDHN = new javax.swing.JLabel();
        lblDHNP = new javax.swing.JLabel();
        jpCAJA = new javax.swing.JPanel();
        lblTOTAL = new javax.swing.JLabel();
        lblPRESTAMOS = new javax.swing.JLabel();
        lblABONOS = new javax.swing.JLabel();
        lblAHORROS = new javax.swing.JLabel();
        lblDEUDA = new javax.swing.JLabel();
        lblDISPONIBLES = new javax.swing.JLabel();
        lblTAPORTACIONES = new javax.swing.JLabel();
        jpELE = new javax.swing.JPanel();
        lblELECTRONICS = new javax.swing.JLabel();
        lblELESUGERIDO = new javax.swing.JLabel();
        lblECAJAELE = new javax.swing.JLabel();
        lblSCAJAELE = new javax.swing.JLabel();
        lblTCELE = new javax.swing.JLabel();
        lblDISPELE = new javax.swing.JLabel();
        lblAGUINALDOELE = new javax.swing.JLabel();
        lblBONOSEDH = new javax.swing.JLabel();
        lblELECTRONICSP = new javax.swing.JLabel();
        jpRCAJA = new javax.swing.JPanel();
        lblSCAJACAJA = new javax.swing.JLabel();
        lblTCCAJA = new javax.swing.JLabel();
        lblECAJACAJA = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtAGUINALDO = new javax.swing.JLabel();
        txtBONO = new javax.swing.JLabel();
        txtBONOP = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setTitle("Reporte de Finanzas");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/finanzas.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        jpCAJA2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movimientos Caja de Ahorro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jpCAJA2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jpDHN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RELACION DHN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jpDHN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblDHN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDHN.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDHN.setText("DHN");
        lblDHN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bono de Caja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblDHNSUGERIDO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDHNSUGERIDO.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDHNSUGERIDO.setText("SUGERIDO");
        lblDHNSUGERIDO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sugerido Semanal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblECAJADHN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblECAJADHN.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblECAJADHN.setText("APORTACIONES");
        lblECAJADHN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aportaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblSCAJADHN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSCAJADHN.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSCAJADHN.setText("RETIROS");
        lblSCAJADHN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Retiros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblTCDHN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTCDHN.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTCDHN.setText("TOTAL");
        lblTCDHN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aportaciones y Retiros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblDISPDHN.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblDISPDHN.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDISPDHN.setText("DISPONIBLES");
        lblDISPDHN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lblAGUINALDODHN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAGUINALDODHN.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAGUINALDODHN.setText("AGUINALDO");
        lblAGUINALDODHN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aguinaldos DHN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblBONOSDHN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblBONOSDHN.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBONOSDHN.setText("BONOS");
        lblBONOSDHN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bono de Ahorro DHN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblDHNP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDHNP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDHNP.setText("BONOS");
        lblDHNP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bono de Produtividad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        javax.swing.GroupLayout jpDHNLayout = new javax.swing.GroupLayout(jpDHN);
        jpDHN.setLayout(jpDHNLayout);
        jpDHNLayout.setHorizontalGroup(
            jpDHNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDISPDHN, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
            .addGroup(jpDHNLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDHNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDHNSUGERIDO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDHN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAGUINALDODHN, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(lblBONOSDHN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblECAJADHN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSCAJADHN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTCDHN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDHNP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpDHNLayout.setVerticalGroup(
            jpDHNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDHNLayout.createSequentialGroup()
                .addComponent(lblDHN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDHNP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAGUINALDODHN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDHNSUGERIDO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblECAJADHN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSCAJADHN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTCDHN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBONOSDHN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(lblDISPDHN))
        );

        jpCAJA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informe de Caja de Ahorro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jpCAJA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTOTAL.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblTOTAL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTOTAL.setText("TOTAL");
        lblTOTAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL EN CAJA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lblPRESTAMOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPRESTAMOS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPRESTAMOS.setText("PRESTAMOS");
        lblPRESTAMOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total de Prestamos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblABONOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblABONOS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblABONOS.setText("ABONOS");
        lblABONOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total de Abonos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblAHORROS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAHORROS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAHORROS.setText("AHORROS");
        lblAHORROS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total de Ahorros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblDEUDA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDEUDA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDEUDA.setText("DEUDA");
        lblDEUDA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Se Deben:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblDISPONIBLES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDISPONIBLES.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDISPONIBLES.setText("DISPONIBLES");
        lblDISPONIBLES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblTAPORTACIONES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTAPORTACIONES.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTAPORTACIONES.setText("APORTACIONES");
        lblTAPORTACIONES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Aportaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        javax.swing.GroupLayout jpCAJALayout = new javax.swing.GroupLayout(jpCAJA);
        jpCAJA.setLayout(jpCAJALayout);
        jpCAJALayout.setHorizontalGroup(
            jpCAJALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCAJALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCAJALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDEUDA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDISPONIBLES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpCAJALayout.createSequentialGroup()
                        .addGroup(jpCAJALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblPRESTAMOS, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(lblAHORROS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblABONOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblTOTAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTAPORTACIONES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpCAJALayout.setVerticalGroup(
            jpCAJALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCAJALayout.createSequentialGroup()
                .addComponent(lblPRESTAMOS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblABONOS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDEUDA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAHORROS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDISPONIBLES)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTAPORTACIONES)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addComponent(lblTOTAL))
        );

        jpELE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EDH", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jpELE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lblELECTRONICS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblELECTRONICS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblELECTRONICS.setText("ELECTRONICS");
        lblELECTRONICS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bono de Caja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblELESUGERIDO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblELESUGERIDO.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblELESUGERIDO.setText("SUGERIDO");
        lblELESUGERIDO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sugerido Semanal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblECAJAELE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblECAJAELE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblECAJAELE.setText("APORTACIONES");
        lblECAJAELE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aportaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblSCAJAELE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSCAJAELE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSCAJAELE.setText("RETIROS");
        lblSCAJAELE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Retiros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblTCELE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTCELE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTCELE.setText("TOTAL");
        lblTCELE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aportaciones y Retiro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblDISPELE.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblDISPELE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDISPELE.setText("DISPONIBLES");
        lblDISPELE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lblAGUINALDOELE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAGUINALDOELE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAGUINALDOELE.setText("AGUINALDO");
        lblAGUINALDOELE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aguinaldos EDH", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblBONOSEDH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblBONOSEDH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBONOSEDH.setText("BONOS");
        lblBONOSEDH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bono de Ahorro EDH", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblELECTRONICSP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblELECTRONICSP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblELECTRONICSP.setText("BONOS");
        lblELECTRONICSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bono de Productividad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        javax.swing.GroupLayout jpELELayout = new javax.swing.GroupLayout(jpELE);
        jpELE.setLayout(jpELELayout);
        jpELELayout.setHorizontalGroup(
            jpELELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpELELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpELELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblELECTRONICS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblELESUGERIDO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblECAJAELE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSCAJAELE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDISPELE, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(lblTCELE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAGUINALDOELE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBONOSEDH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblELECTRONICSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpELELayout.setVerticalGroup(
            jpELELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpELELayout.createSequentialGroup()
                .addComponent(lblELECTRONICS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblELECTRONICSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAGUINALDOELE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblELESUGERIDO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblECAJAELE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSCAJAELE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTCELE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBONOSEDH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDISPELE))
        );

        jpRCAJA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RELACION CAJA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lblSCAJACAJA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSCAJACAJA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSCAJACAJA.setText("SALIDAS");
        lblSCAJACAJA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salidas Caja CAJA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        lblTCCAJA.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblTCCAJA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTCCAJA.setText("TOTAL");
        lblTCCAJA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movimientos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lblECAJACAJA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblECAJACAJA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblECAJACAJA.setText("ENTRADAS");
        lblECAJACAJA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entradas Caja CAJA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        javax.swing.GroupLayout jpRCAJALayout = new javax.swing.GroupLayout(jpRCAJA);
        jpRCAJA.setLayout(jpRCAJALayout);
        jpRCAJALayout.setHorizontalGroup(
            jpRCAJALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRCAJALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRCAJALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblECAJACAJA, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(lblSCAJACAJA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTCCAJA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpRCAJALayout.setVerticalGroup(
            jpRCAJALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRCAJALayout.createSequentialGroup()
                .addComponent(lblECAJACAJA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSCAJACAJA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTCCAJA))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Periodos y Fechas de entregas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtAGUINALDO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAGUINALDO.setText("AGUINALDO");
        txtAGUINALDO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion de Aguinaldo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        txtBONO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBONO.setText("BONO");
        txtBONO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion de Bono de Caja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        txtBONOP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBONOP.setText("BONO DE PRODUCTIVIDAD");
        txtBONOP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion de Bono de Productividad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 352, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtAGUINALDO, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBONO, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBONOP, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtAGUINALDO)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtBONO)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtBONOP)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jpCAJA2Layout = new javax.swing.GroupLayout(jpCAJA2);
        jpCAJA2.setLayout(jpCAJA2Layout);
        jpCAJA2Layout.setHorizontalGroup(
            jpCAJA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCAJA2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpDHN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpELE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpCAJA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCAJA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpRCAJA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(526, Short.MAX_VALUE))
        );
        jpCAJA2Layout.setVerticalGroup(
            jpCAJA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCAJA2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCAJA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpCAJA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpCAJA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jpDHN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpELE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpCAJA2Layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jpRCAJA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 329, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpCAJA2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpCAJA2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jpCAJA;
    private javax.swing.JPanel jpCAJA2;
    private javax.swing.JPanel jpDHN;
    private javax.swing.JPanel jpELE;
    private javax.swing.JPanel jpRCAJA;
    private javax.swing.JLabel lblABONOS;
    private javax.swing.JLabel lblAGUINALDODHN;
    private javax.swing.JLabel lblAGUINALDOELE;
    private javax.swing.JLabel lblAHORROS;
    private javax.swing.JLabel lblBONOSDHN;
    private javax.swing.JLabel lblBONOSEDH;
    private javax.swing.JLabel lblDEUDA;
    private javax.swing.JLabel lblDHN;
    private javax.swing.JLabel lblDHNP;
    private javax.swing.JLabel lblDHNSUGERIDO;
    private javax.swing.JLabel lblDISPDHN;
    private javax.swing.JLabel lblDISPELE;
    private javax.swing.JLabel lblDISPONIBLES;
    private javax.swing.JLabel lblECAJACAJA;
    private javax.swing.JLabel lblECAJADHN;
    private javax.swing.JLabel lblECAJAELE;
    private javax.swing.JLabel lblELECTRONICS;
    private javax.swing.JLabel lblELECTRONICSP;
    private javax.swing.JLabel lblELESUGERIDO;
    private javax.swing.JLabel lblPRESTAMOS;
    private javax.swing.JLabel lblSCAJACAJA;
    private javax.swing.JLabel lblSCAJADHN;
    private javax.swing.JLabel lblSCAJAELE;
    private javax.swing.JLabel lblTAPORTACIONES;
    private javax.swing.JLabel lblTCCAJA;
    private javax.swing.JLabel lblTCDHN;
    private javax.swing.JLabel lblTCELE;
    private javax.swing.JLabel lblTOTAL;
    private javax.swing.JLabel txtAGUINALDO;
    private javax.swing.JLabel txtBONO;
    private javax.swing.JLabel txtBONOP;
    // End of variables declaration//GEN-END:variables

}

