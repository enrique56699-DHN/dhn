/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor..
 */
package VISTA;

import CONTROLADOR.F;
import UTILERIAS.RenderCelda;
import DHNv2.G;
import java.awt.*;
import java.awt.print.*;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
//import javax.swing.table.TableRowSorter;

/**
 *
 * @author Oscar E Gonzalez G
 */
public final class ImpALMACEN extends javax.swing.JPanel implements Printable
{

    private JOptionPane op;
    private final JViewport vp = new JViewport();
    private Integer opcion;
    //private ModeloCarritoTabla mdl = new ModeloCarritoTabla();
    public DefaultTableModel resultadoModel;
    //public Map<Object, GrupoResultado> resultados;
    
    /**
     * Creates new form ImpPago
     * @param TABLA
     * @param TITULO
     * @param EMPLEADO
     * @param CENTREGADO
     * @param CUSADO
     * @param CDEVUELTO
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public ImpALMACEN(TableModel TABLA,String TITULO, String EMPLEADO, String CENTREGADO, String CUSADO, String CDEVUELTO) throws ClassNotFoundException, SQLException {
        vp.setBackground(Color.WHITE);
        initComponents();
        this.lblEMPLEADO.setText(EMPLEADO);
        this.lblTITULO.setText(TITULO);
        this.tblTABLA.setModel(TABLA);
        this.lblFECHA.setText(G.FECHA(G.hoy(),false));
        this.lblCENTREGADO.setText(CENTREGADO);
        this.lblCUSADO.setText(CUSADO);
        this.lblCDEVUELTO.setText(CDEVUELTO);

        dimensionar(tblTABLA);
        resizer(jspTABLA,tblTABLA);
    }
    
    private void dimensionar(JTable tabla)
    {
        TableColumnModel tcm = tabla.getTableHeader().getColumnModel();
        tcm.removeColumn(tcm.getColumn(3));
        tcm.removeColumn(tcm.getColumn(0));
        tabla.setTableHeader(null);        
        agruparYContar(tabla);        
        tabla.setModel(resultadoModel);
        tabla.setRowHeight(20);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(20);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(200);        
        tabla.getColumnModel().getColumn(0).setCellRenderer(new RenderCelda(true,true,"") );        
        
        //TableRowSorter sorter = new TableRowSorter<>(tabla.getModel());
        //tabla.setRowSorter(sorter); 
        /*List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);*/
        
        
        //sorter.setSortable(0, true);
        //sorter.sort();
        
        
    }
    
    private void agruparYContar(JTable tabla) {
        //String campoAgrupar = (String) campoAgruparCombo.getSelectedItem();
        //String campoContar = (String) campoContarCombo.getSelectedItem();

        int columnaAgrupar = 1;
        int columnaContar = 0;

        // Estructuras para almacenar resultados
        Map<Object, GrupoResultado> resultados = new LinkedHashMap<>();
        //resultados = new LinkedHashMap<>();

        for (int row = 0; row < tabla.getRowCount(); row++) {
            Object claveGrupo = tabla.getValueAt(row, columnaAgrupar);
            Object valor = tabla.getValueAt(row, columnaContar);

            if (valor instanceof Number) {
                double numValor = ((Number) valor).doubleValue();
                //System.out.println(claveGrupo+" "+numValor);
                GrupoResultado grupo = resultados.computeIfAbsent(claveGrupo, 
                    k -> new GrupoResultado(claveGrupo.toString()));
                
                grupo.agregarValor(numValor);
            }
        }

        //System.out.println(" result: "+resultados);
        // Mostrar resultados en la tabla
        mostrarResultadosEnTabla(resultados);
        
        
        /*for (GrupoResultado grupo : resultados.values()) {         
            resultadoModel.addRow(new Object[]{
                grupo.getNombre(),
                grupo.getCantidad()
            });
        }*/
        
    }
     
    private void mostrarResultadosEnTabla(Map<Object, GrupoResultado> resultados) {
        //resultadoModel.setRowCount(0); // Limpiar tabla anterior
        String[] resultColumns = {"Producto", "Cantidad"};
        resultadoModel = new DefaultTableModel(resultColumns, 0);
        //resultadoModel = new DefaultTableModel(1,0);
        
        for (GrupoResultado grupo : resultados.values()) {
            //resultadoModel.addRow(rowData);

            resultadoModel.addRow(new Object[]{
                grupo.getNombre(),
                grupo.getCantidad()/*,
                String.format("%.2f", grupo.getSuma()),
                String.format("%.2f", grupo.getPromedio()),
                String.format("%.2f", grupo.getMinimo()),
                String.format("%.2f", grupo.getMaximo())*/
            });
        }
    } 
    
    private void resizer(JScrollPane jsp, JTable tabla) throws ClassNotFoundException, SQLException
    {
        /*int v = (tblTABLA.getRowCount()*20)+70;
        tblTABLA.setFillsViewportHeight(true);
        tblTABLA.setPreferredScrollableViewportSize(new Dimension(180,v));*/        
                
        revalidate();
        int a = tabla.getRowCount();
        //System.out.println("cantida de desos "+a);
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
    
    public class GrupoResultado 
    {
        private String nombre;
        private double cantidad;
        /*private double suma;
        private double minimo = Double.MAX_VALUE;
        private double maximo = Double.MIN_VALUE;*/

        public GrupoResultado(String nombre) {
            this.nombre = nombre;
        }

        public void agregarValor(double valor) {
            cantidad=cantidad+valor;
            /*suma += valor;
            minimo = Math.min(minimo, valor);
            maximo = Math.max(maximo, valor);*/
        }

        public String getNombre() { return nombre; }
        public double getCantidad() { return cantidad; }
        /*public double getSuma() { return suma; }
        public double getPromedio() { return cantidad > 0 ? suma / cantidad : 0; }
        public double getMinimo() { return minimo; }
        public double getMaximo() { return maximo; }*/
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
        jspTABLA = new javax.swing.JScrollPane();
        tblTABLA = new javax.swing.JTable();
        lblEMPLEADO = new javax.swing.JLabel();
        lblTITULO = new javax.swing.JLabel();
        lblFECHA = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblCENTREGADO = new javax.swing.JLabel();
        lblCUSADO = new javax.swing.JLabel();
        lblCDEVUELTO = new javax.swing.JLabel();

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

        lblLOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/DHN-SMALL.png"))); // NOI18N

        jspTABLA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 7))); // NOI18N
        jspTABLA.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N

        tblTABLA.setFont(new java.awt.Font("Tahoma", 0, 6)); // NOI18N
        tblTABLA.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspTABLA.setViewportView(tblTABLA);

        lblEMPLEADO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEMPLEADO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEMPLEADO.setText("EMPLEADO");

        lblTITULO.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblTITULO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTITULO.setText("TABLA");

        lblFECHA.setFont(new java.awt.Font("Tahoma", 1, 7)); // NOI18N
        lblFECHA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFECHA.setText("00/00/000");
        lblFECHA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblCENTREGADO.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        lblCENTREGADO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCENTREGADO.setText("ENTREGADO");
        lblCENTREGADO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cable Entregado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 6))); // NOI18N

        lblCUSADO.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        lblCUSADO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCUSADO.setText("USADO");
        lblCUSADO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cable Usado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 6))); // NOI18N

        lblCDEVUELTO.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        lblCDEVUELTO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCDEVUELTO.setText("DEVUELTO");
        lblCDEVUELTO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cable Devuelto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 6))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jspTABLA, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lblTITULO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEMPLEADO, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFECHA, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblCENTREGADO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCUSADO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCDEVUELTO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFECHA)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCENTREGADO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCUSADO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCDEVUELTO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEMPLEADO, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTITULO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bntImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntImprimirActionPerformed
        getOptionPane();
        opcion = 1;
        op.setValue(1);
        F.copiarPanel(this);
    }//GEN-LAST:event_bntImprimirActionPerformed

    private void bntCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelarActionPerformed
        getOptionPane();
        op.setValue(0);
        F.copiarPanel(this);
    }//GEN-LAST:event_bntCancelarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Imprimir();
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCancelar;
    private javax.swing.JButton bntImprimir;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jspTABLA;
    private javax.swing.JLabel lblCDEVUELTO;
    private javax.swing.JLabel lblCENTREGADO;
    private javax.swing.JLabel lblCUSADO;
    private javax.swing.JLabel lblEMPLEADO;
    private javax.swing.JLabel lblFECHA;
    private javax.swing.JLabel lblLOGO;
    private javax.swing.JLabel lblTITULO;
    private javax.swing.JTable tblTABLA;
    // End of variables declaration//GEN-END:variables
}
// Clase interna para almacenar resultados de grupo
