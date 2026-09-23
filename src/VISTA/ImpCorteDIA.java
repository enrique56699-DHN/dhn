/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor..
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
import MODELO.ModeloEntradasTabla;
import MODELO.ModeloPagosTabla;
import MODELO.ModeloSalidasTabla;
import java.awt.*;
import java.awt.print.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.TableModel;

/**
 *
 * @author Oscar E Gonzalez G
 */
public final class ImpCorteDIA extends javax.swing.JPanel implements Printable
{

    private JOptionPane op;
    private final JViewport vp = new JViewport();
    private Integer opcion;
    private ModeloPagosTabla mrpt = new ModeloPagosTabla();
    /**
     * Creates new form ImpPago
     * @param FDE
     * @param FAL
     * @param TOTAL
     * @param PAGOS
     * @param ENTRADAS
     * @param SALIDAS
     * @param pagos
     * @param entradas
     * @param salidas
     * @param Saldo_anterior
     * @param Total_Movimientos
     * @param NCORTE
     * @param CAJA
     * @param ttarjeta
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public ImpCorteDIA(String FDE, String FAL, String TOTAL, TableModel PAGOS,TableModel ENTRADAS, TableModel SALIDAS, 
            String pagos, String entradas, String salidas,String Saldo_anterior, String Total_Movimientos, int NCORTE, String CAJA, 
            String ttarjeta) throws ClassNotFoundException, SQLException {
        vp.setBackground(Color.WHITE);
        mrpt = new ModeloPagosTabla();
        initComponents();

        this.lblFDE.setText(FDE);
        this.lblFAL.setText(FAL);
        this.lblTOTAL.setText(TOTAL);
        this.lblPAGOS.setText(pagos);
        this.lblSALIDAS.setText(salidas);
        this.lblENTRADAS.setText(entradas);    
        this.jtblPAGOS.setModel(PAGOS);
        this.jtblENTRADAS.setModel(ENTRADAS);
        this.jtblSALIDAS.setModel(SALIDAS);
        this.lblSALDOANT.setText(Saldo_anterior);
        this.lblSALDONUEVO.setText(Total_Movimientos);
        this.lblNCORTE.setText(""+NCORTE);
        this.lblNCAJA.setText(CAJA);
        this.lblTOTALT.setText(ttarjeta);
        
        resizer(jspPAGOS, jtblPAGOS);
        resizer(jspENTRADAS, jtblENTRADAS);
        resizer(jspSALIDAS, jtblSALIDAS);
        
        F.dimensionar(jtblPAGOS,"CORTESD");
        F.dimensionar(jtblENTRADAS,"CORTESD");
        F.dimensionar(jtblSALIDAS,"CORTESD");
    }
    
    public ImpCorteDIA(int IDCORTE) throws ClassNotFoundException, SQLException, Exception {
        vp.setBackground(Color.WHITE);
        //mrpt = new ModeloPagosTabla();
        initComponents();        
        
        datos(IDCORTE);
    }
    
    private void resizer(JScrollPane jsp, JTable tabla) throws ClassNotFoundException, SQLException
    {
        /*int v = (tblTABLA.getRowCount()*20)+70;
        tblTABLA.setFillsViewportHeight(true);
        tblTABLA.setPreferredScrollableViewportSize(new Dimension(180,v));*/        
                
        revalidate();
        int a = tabla.getRowCount();
        //System.out.println("cantida de registros "+a);
        int w = jsp.getPreferredSize().width;                   //ancho scroll pane
        //System.out.println("original de scrol ancho "+w);
        int h1 = jsp.getViewport().getViewSize().height;        //altura viewport
        //System.out.println("original de scrol alto "+h1);
        int h2 = tabla.getPreferredScrollableViewportSize().height; //altura tabla
        //System.out.println("original de tabla "+h2);
        
        if(h1<h2)
            jsp.setSize(new Dimension(w, h1+27));
        else
            jsp.setSize(tabla.getPreferredScrollableViewportSize());
        
        int b=a*20;
        /*jspTABLA.setPreferredSize(new Dimension(190,b));
        jspTABLA.setMaximumSize(new Dimension(190,b));
        jspTABLA.setMinimumSize(new Dimension(190,b));*/
        //jspTABLA.setSize(185, 300);      
        tabla.setPreferredSize(new Dimension(190,b));
        tabla.setMaximumSize(new Dimension(190,b));
        tabla.setMinimumSize(new Dimension(190,b));
        tabla.setSize(190, b);
        
        h2=a*30;
        int h3=h2+300;
        //System.out.println("tamaño panel nuevo"+h3);
        int w1 = 200;
        
        this.setSize(new Dimension(w1,h3));
        this.setPreferredSize(new Dimension(w1,h3));
        this.setMaximumSize(new Dimension(w1,h3));
        this.setMinimumSize(new Dimension(w1,h3));
        
        this.repaint();

        //this.tblTABLA.setModel(model);
    }
    
    private void datos(int IDCORTE) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloCortes mdl;
        ControlCortes ctrl = new ControlCortes();
        mdl=ctrl.getRegistrosID(0);
        int IDCAJA = mdl.getIDCAJA();
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
        //String DE = mc.getFINI();
        //String AL = mc.getFFIN();
        float MONTOINI = mc.getMONTOINI();
                
        ControlEntradas ca = new ControlEntradas();
        float TENTRADAS = ca.suma(IDENTRADAI+1, IDENTRADAF, "EFECTIVO", IDCAJA);
        
        ControlSalidas cs = new ControlSalidas();
        float TSALIDAS = cs.suma(IDSALIDAI+1, IDSALIDAF, "EFECTIVO", IDCAJA);
        
        ControlPagos cp = new ControlPagos();
        float TPAGOS = cp.suma(IDPAGOI+1, IDPAGOF, "EFECTIVO", IDCAJA);
        
        float total = TPAGOS+TENTRADAS-TSALIDAS;
        float NTOTAL = MONTOINI+total;
        
        ModeloPagosTabla mdlpt;
        mdlpt = new ModeloPagosTabla(IDPAGOI+1,IDPAGOF,"EFECTIVO",IDCAJA);
        ModeloEntradasTabla mdlet;
        mdlet = new ModeloEntradasTabla(IDENTRADAI+1,IDENTRADAF , "EFECTIVO",IDCAJA);
        ModeloSalidasTabla mdlst;
        mdlst = new ModeloSalidasTabla(IDSALIDAI+1,IDSALIDAF,"EFECTIVO",IDCAJA);
        
        this.jtblPAGOS.setModel(mdlpt);
        this.jtblENTRADAS.setModel(mdlet);
        this.jtblSALIDAS.setModel(mdlst);        
        
        this.lblFDE.setText(mc.getFINI());
        this.lblFAL.setText(mc.getFFIN());
        this.lblTOTAL.setText("$ "+G.numero.format(NTOTAL));
        this.lblPAGOS.setText("$ "+G.numero.format(TPAGOS));
        this.lblSALIDAS.setText("$ "+G.numero.format(TSALIDAS));
        this.lblENTRADAS.setText("$ "+G.numero.format(TENTRADAS));    
        
        this.lblSALDOANT.setText("$ "+G.numero.format(MONTOINI));
        this.lblSALDONUEVO.setText("$ "+G.numero.format(total));
        this.lblNCORTE.setText(""+IDCORTE);
        this.lblNCAJA.setText(""+IDCAJA);
        
        resizer(jspPAGOS, jtblPAGOS);
        resizer(jspENTRADAS, jtblENTRADAS);
        resizer(jspSALIDAS, jtblSALIDAS);
        
        F.dimensionar(jtblPAGOS,"CORTESD");
        F.dimensionar(jtblENTRADAS,"CORTESD");
        F.dimensionar(jtblSALIDAS,"CORTESD");
    }
    
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
        lblFDE = new javax.swing.JLabel();
        lblTOTAL = new javax.swing.JLabel();
        lblFAL = new javax.swing.JLabel();
        jspPAGOS = new javax.swing.JScrollPane();
        jtblPAGOS = new javax.swing.JTable();
        jspENTRADAS = new javax.swing.JScrollPane();
        jtblENTRADAS = new javax.swing.JTable();
        jspSALIDAS = new javax.swing.JScrollPane();
        jtblSALIDAS = new javax.swing.JTable();
        lblPAGOS = new javax.swing.JLabel();
        lblSALIDAS = new javax.swing.JLabel();
        lblENTRADAS = new javax.swing.JLabel();
        lblSALDOANT = new javax.swing.JLabel();
        lblSALDONUEVO = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lblNCORTE = new javax.swing.JLabel();
        lblNCAJA = new javax.swing.JLabel();
        lblTOTALT = new javax.swing.JLabel();

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

        setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        setMaximumSize(new java.awt.Dimension(200, 1700));
        setMinimumSize(new java.awt.Dimension(80, 210));
        setPreferredSize(new java.awt.Dimension(200, 650));

        lblLOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/DHN-SMALL.png"))); // NOI18N

        lblFDE.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        lblFDE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFDE.setText("00/00/0000");
        lblFDE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DE:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 8))); // NOI18N

        lblTOTAL.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblTOTAL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTOTAL.setText("TOTAL");
        lblTOTAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Efectivo:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblFAL.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        lblFAL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFAL.setText("00/00/0000");
        lblFAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "AL:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 8))); // NOI18N

        jspPAGOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pagos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        jspPAGOS.setFont(new java.awt.Font("Tahoma", 0, 7)); // NOI18N

        jtblPAGOS.setFont(new java.awt.Font("Tahoma", 0, 7)); // NOI18N
        jtblPAGOS.setModel(mrpt);
        jtblPAGOS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspPAGOS.setViewportView(jtblPAGOS);

        jspENTRADAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entradas:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        jspENTRADAS.setFont(new java.awt.Font("Tahoma", 0, 7)); // NOI18N

        jtblENTRADAS.setFont(new java.awt.Font("Tahoma", 0, 7)); // NOI18N
        jtblENTRADAS.setModel(mrpt);
        jtblENTRADAS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspENTRADAS.setViewportView(jtblENTRADAS);

        jspSALIDAS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salidas:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        jspSALIDAS.setFont(new java.awt.Font("Tahoma", 0, 7)); // NOI18N

        jtblSALIDAS.setFont(new java.awt.Font("Tahoma", 0, 7)); // NOI18N
        jtblSALIDAS.setModel(mrpt);
        jtblSALIDAS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspSALIDAS.setViewportView(jtblSALIDAS);

        lblPAGOS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblPAGOS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPAGOS.setText("TOTAL");

        lblSALIDAS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblSALIDAS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSALIDAS.setText("TOTAL");

        lblENTRADAS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblENTRADAS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblENTRADAS.setText("TOTAL");

        lblSALDOANT.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblSALDOANT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSALDOANT.setText("TOTAL");

        lblSALDONUEVO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblSALDONUEVO.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSALDONUEVO.setText("TOTAL");

        lbl1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lbl1.setText("Saldo Anterior:");

        lbl2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lbl2.setText("Total Mov:");

        lblNCORTE.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblNCORTE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNCORTE.setText("No. Corte");
        lblNCORTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. Corte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N

        lblNCAJA.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblNCAJA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNCAJA.setText("No. Caja");
        lblNCAJA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. Caja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N

        lblTOTALT.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblTOTALT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTOTALT.setText("TOTAL TARJETAS");
        lblTOTALT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Tarjetas:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblFDE, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                            .addComponent(lblFAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspPAGOS, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jspSALIDAS, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jspENTRADAS, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lblPAGOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSALIDAS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblENTRADAS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNCORTE, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNCAJA, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblTOTALT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTOTAL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblSALDONUEVO, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblSALDOANT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblFDE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFAL)))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNCORTE)
                    .addComponent(lblNCAJA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSALDOANT, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSALDONUEVO)
                    .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTOTAL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTOTALT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspPAGOS, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPAGOS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspSALIDAS, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSALIDAS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspENTRADAS, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblENTRADAS)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bntImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntImprimirActionPerformed
        // TODO add your handling code here:
        getOptionPane();
        opcion = 1;
        op.setValue(1);
        F.copiarPanel(this);
    }//GEN-LAST:event_bntImprimirActionPerformed

    private void bntCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelarActionPerformed
        // TODO add your handling code here:
        getOptionPane();
        op.setValue(0);
        F.copiarPanel(this);
    }//GEN-LAST:event_bntCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCancelar;
    private javax.swing.JButton bntImprimir;
    private javax.swing.JScrollPane jspENTRADAS;
    private javax.swing.JScrollPane jspPAGOS;
    private javax.swing.JScrollPane jspSALIDAS;
    private javax.swing.JTable jtblENTRADAS;
    private javax.swing.JTable jtblPAGOS;
    private javax.swing.JTable jtblSALIDAS;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lblENTRADAS;
    private javax.swing.JLabel lblFAL;
    private javax.swing.JLabel lblFDE;
    private javax.swing.JLabel lblLOGO;
    private javax.swing.JLabel lblNCAJA;
    private javax.swing.JLabel lblNCORTE;
    private javax.swing.JLabel lblPAGOS;
    private javax.swing.JLabel lblSALDOANT;
    private javax.swing.JLabel lblSALDONUEVO;
    private javax.swing.JLabel lblSALIDAS;
    private javax.swing.JLabel lblTOTAL;
    private javax.swing.JLabel lblTOTALT;
    // End of variables declaration//GEN-END:variables
}
