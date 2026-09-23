
/*
 * RegClientes.java
 *.
 * Created on 11-nov-2013, 18:18:42
 */
package VISTA;

import CONTROLADOR.ControlCarrito;
import CONTROLADOR.ControlClientes;
import CONTROLADOR.ControlDirecciones;
import CONTROLADOR.ControlPendientes;
import CONTROLADOR.ControlProductos;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloCarrito;
import MODELO.ModeloClientes;
import MODELO.ModeloDirecciones;
import MODELO.ModeloPendientes;
import MODELO.ModeloProductos;
import MODELO.ModeloProductosTabla;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;


/**
 *
 * @author KIKE FRENCH
 */
public final class ListProd extends javax.swing.JDialog 
{

    private int IDPRODUCTO, IDDIRECCION, IDCLIENTE;
    private double CANT, PP;
    private String DESCRIPCION, CB, MARCA = "MIRAGE", VOLTAJE="110V", TECNOLOGIA="INVERTER", PRESTACION="FRIO/CALOR", CAPACIDAD="1 TONELADA", PRODUCTO;
   
    
    /** Creates new form RegClientes 
    * @param parent
     * @param modal
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException*/
    public ListProd(java.awt.Frame parent, boolean modal) throws ClassNotFoundException, SQLException{
        super(parent, modal);
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/carrito.png")); 
        setIconImage(icon);
        setLocationRelativeTo(null);
        
        this.Bar.setFloatable(false);

    }
    
    private void actualizarTablas(String PRODUCTO) throws ClassNotFoundException, SQLException
    {
        Object met = null;
        switch(PRODUCTO)
        {
            case "HVAC":
                met = new ModeloProductosTabla(TECNOLOGIA,CAPACIDAD,VOLTAJE,PRESTACION, MARCA);
                break;
            case "CALSOL":
                met = new ModeloProductosTabla("","CALENTADORES SOLARES");
                break;
            case "CCTV":
                break;
            default: 
                met = null;
                break;
        }
        
        tblTABLA.setModel((TableModel) met);
        F.dimensionar(tblTABLA,"PRODUCTOSL");
    }
        
    public void mostrar(int IDCLIENTE, int IDDIRECCION, String PRODUCTO) throws ClassNotFoundException, SQLException, Exception
    {
        this.IDCLIENTE = IDCLIENTE;
        this.IDDIRECCION = IDDIRECCION;
        this.PRODUCTO = PRODUCTO;
        switch(PRODUCTO)
        {
            case "HVAC":
                break;
            case "CALSOL":
                pnlFILTROSCALSOL.setVisible(false);
                pack();
                break;
            default:
                break;
        }
        actualizarTablas(PRODUCTO);
        
    }
    
    private void FILTROS() throws ClassNotFoundException, SQLException
    {
        MARCA = txtMARCA.getSelectedItem().toString();
        TECNOLOGIA = txtTECNOLOGIA.getSelectedItem().toString();
        VOLTAJE = txtVOLTAJE.getSelectedItem().toString();
        PRESTACION = txtPRESTACION.getSelectedItem().toString();
        CAPACIDAD = txtCAPACIDAD.getSelectedItem().toString();
        actualizarTablas(PRODUCTO);
    }

    private void seleccion() throws ClassNotFoundException, SQLException, Exception
    {
        int fila = tblTABLA.getSelectedRow();
        IDPRODUCTO = (int)tblTABLA.getValueAt(fila, 0);
        DESCRIPCION = tblTABLA.getValueAt(fila, 1).toString();
    }    
    
    private void guardar(String ACCION, String PRODUCTO) throws ClassNotFoundException, SQLException, Exception
    {
        seleccion();

        ModeloPendientes x = new ModeloPendientes();
        
        x.setIDCLIENTE(IDCLIENTE);
        x.setIDDIRECCION(IDDIRECCION);
        x.setNPEDIDO("");
        x.setNPEDIDOF("");
        x.setCATEGORIA("VENTA");        
        x.setSUBCATEGORIA("GENERAL");        
        DESCRIPCION = "INSTALACION DE:\n"+txtCANT.getText()+" "+DESCRIPCION;
        x.setDESCRIPCION(DESCRIPCION);    

        x.setCOMENTARIOS(txtCOMENTARIOS.getText());   
        x.setFREG(G.FECHA(G.hoy(),false));   
        x.setFEST(G.FECHA(G.hoy(),false));
        x.setFFIN("");            
        x.setESTADO("ABIERTO");
        
        int IDPENDIENTE = 0;
        try
        { 
            IDPENDIENTE = F.Registro("PENDIENTES","IDPENDIENTE","DESC");
            x.setIDPENDIENTE(IDPENDIENTE);

            ControlPendientes cp = new ControlPendientes();
            cp.guardarRegistro(x);                    
            this.dispose();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR GUARDAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);

        }
        
        ModeloCarrito mdl = new ModeloCarrito();

        mdl.setIDPENDIENTE(IDPENDIENTE);
        mdl.setIDPRODUCTO(IDPRODUCTO);
        mdl.setCANTIDAD(Double.parseDouble(txtCANT.getText()));
        mdl.setTOTAL(0);
        mdl.setESTADO("REQUERIDO");
        mdl.setIDEMPLEADO(0);
        mdl.setFECHA(G.FECHA(G.hoy(),false));
        int IDCARRITO = F.Registro("CARRITO","IDCARRITO","DESC");        
        mdl.setIDCARRITO(IDCARRITO);
        mdl.setIDPENDIENTE(IDPENDIENTE);
        
        ControlCarrito ctrl = new ControlCarrito();
        ctrl.guardarRegistro(mdl);
        
        //Funciones.bitacora("CARRITO",IDCARRITO,ACCION,G.IDUSER,"AGREGADO EL DIA: "+G.FECHA(G.hoy(),false),"");
        
        String rutaExcel = "\\"+"\\" +G.ip +"\\dhn\\MACHOTES\\"+PRODUCTO+".xlsx";
        String rutaWord = "\\"+"\\" +G.ip +"\\dhn\\MACHOTES\\"+PRODUCTO+".docx";
        String rutaWordtmp = "\\"+"\\" +G.ip +"\\dhn\\MACHOTES\\"+PRODUCTO+"tmp.docx";
        String rutaPDF = "\\"+"\\" +G.ip +"\\dhn\\VENTAS\\"+IDPENDIENTE+".pdf";
        String[] titulos = {"«FOLIO»", 
        "«FECHA»", 
        "«NOMBRE»", 
        "«DOMICILIO»", 
        "«PRODUCTO»",  
        "«COSTO»"};
        String[] datos = {""+IDPENDIENTE, G.FECHA(G.hoy(),false), CLIENTE(IDCLIENTE), DOMICILIO(IDDIRECCION),txtCANT.getText()+" "+PRODUCTO(IDPRODUCTO),""+PP};       
        F.imprimir(titulos,datos, rutaExcel, rutaWord, rutaWordtmp, rutaPDF);
        
        F.bitacora("PENDIENTES",IDPENDIENTE,ACCION,G.IDUSER,"",DESCRIPCION);        
    }
    
    private String CLIENTE(int IDCLIENTE) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloClientes mdl;
        ControlClientes ctrl = new ControlClientes();
        mdl = ctrl.getRegistrosID(IDCLIENTE);
        return mdl.getNOMBRE();
    }
    
    private String DOMICILIO(int IDDIRECCION) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloDirecciones mdl;
        ControlDirecciones ctrl = new ControlDirecciones();
        mdl = ctrl.getRegistrosIDa(IDDIRECCION);
        return mdl.getCALLE()+" "+mdl.getNUMERO()+" "+mdl.getCOLONIA()+" "+mdl.getLOCALIDAD()+" "+mdl.getREFERENCIA();
    }
    
    private String PRODUCTO(int IDPRODUCTO) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloProductos mdl;
        ControlProductos ctrl = new ControlProductos();
        mdl = ctrl.getRegistrosID(IDPRODUCTO);
        PP = mdl.getPP() * Integer.parseInt(txtCANT.getText());
        return mdl.getDESCRIPCION();
    }
    
    private void selectPROD() throws SQLException, Exception
    {
        seleccion();
        this.txtCANT.setText(""+1);
        this.txtCANT.requestFocus();
    }
    
   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Bar = new javax.swing.JToolBar();
        btnGUARDAR = new javax.swing.JButton();
        btnCANCEL = new javax.swing.JButton();
        jspTABLA = new javax.swing.JScrollPane();
        tblTABLA = new javax.swing.JTable();
        pnlACCIONES = new javax.swing.JPanel();
        txtCANT = new javax.swing.JTextField();
        pnlFILTROSCALSOL = new javax.swing.JPanel();
        txtMARCA = new javax.swing.JComboBox<>();
        txtTECNOLOGIA = new javax.swing.JComboBox<>();
        txtVOLTAJE = new javax.swing.JComboBox<>();
        txtPRESTACION = new javax.swing.JComboBox<>();
        txtCAPACIDAD = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCOMENTARIOS = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar al Carrito");
        setResizable(false);

        Bar.setRollover(true);

        btnGUARDAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disk.png"))); // NOI18N
        btnGUARDAR.setText("GUARDAR");
        btnGUARDAR.setToolTipText("");
        btnGUARDAR.setFocusable(false);
        btnGUARDAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGUARDAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGUARDARActionPerformed(evt);
            }
        });
        Bar.add(btnGUARDAR);

        btnCANCEL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCANCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnCANCEL.setText("CANCELAR");
        btnCANCEL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCANCEL.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCANCELActionPerformed(evt);
            }
        });
        Bar.add(btnCANCEL);

        jspTABLA.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jspTABLA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tblTABLA.setAutoCreateRowSorter(true);
        tblTABLA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTABLA.setName("tblTABLA"); // NOI18N
        tblTABLA.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTABLA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTABLAMousePressed(evt);
            }
        });
        jspTABLA.setViewportView(tblTABLA);

        pnlACCIONES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cantidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        pnlACCIONES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtCANT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCANT.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCANT.setText("0");
        txtCANT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cantidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtCANT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCANTKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnlACCIONESLayout = new javax.swing.GroupLayout(pnlACCIONES);
        pnlACCIONES.setLayout(pnlACCIONESLayout);
        pnlACCIONESLayout.setHorizontalGroup(
            pnlACCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlACCIONESLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCANT, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlACCIONESLayout.setVerticalGroup(
            pnlACCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlACCIONESLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCANT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlFILTROSCALSOL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtMARCA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMARCA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AUFIT", "MIRAGE", "YORK" }));
        txtMARCA.setSelectedIndex(1);
        txtMARCA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Marca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtMARCA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtMARCAItemStateChanged(evt);
            }
        });

        txtTECNOLOGIA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTECNOLOGIA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CONVENCIONAL", "INVERTER" }));
        txtTECNOLOGIA.setSelectedIndex(1);
        txtTECNOLOGIA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tecnologia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtTECNOLOGIA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtTECNOLOGIAItemStateChanged(evt);
            }
        });

        txtVOLTAJE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtVOLTAJE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "110V", "220V" }));
        txtVOLTAJE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Voltaje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtVOLTAJE.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtVOLTAJEItemStateChanged(evt);
            }
        });

        txtPRESTACION.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPRESTACION.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SOLO FRIO", "FRIO/CALOR" }));
        txtPRESTACION.setSelectedIndex(1);
        txtPRESTACION.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prestaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtPRESTACION.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtPRESTACIONItemStateChanged(evt);
            }
        });

        txtCAPACIDAD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCAPACIDAD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 TONELADA", "1.5 TONELADAS", "2 TONELADAS", "2.5 TONELADAS", "3 TONELADAS" }));
        txtCAPACIDAD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capacidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtCAPACIDAD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtCAPACIDADItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlFILTROSCALSOLLayout = new javax.swing.GroupLayout(pnlFILTROSCALSOL);
        pnlFILTROSCALSOL.setLayout(pnlFILTROSCALSOLLayout);
        pnlFILTROSCALSOLLayout.setHorizontalGroup(
            pnlFILTROSCALSOLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFILTROSCALSOLLayout.createSequentialGroup()
                .addComponent(txtMARCA, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTECNOLOGIA, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVOLTAJE, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPRESTACION, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCAPACIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlFILTROSCALSOLLayout.setVerticalGroup(
            pnlFILTROSCALSOLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFILTROSCALSOLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFILTROSCALSOLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMARCA, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTECNOLOGIA, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVOLTAJE, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPRESTACION, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCAPACIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtCOMENTARIOS.setColumns(20);
        txtCOMENTARIOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCOMENTARIOS.setRows(5);
        txtCOMENTARIOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comentarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jScrollPane1.setViewportView(txtCOMENTARIOS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlACCIONES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jspTABLA)
            .addComponent(pnlFILTROSCALSOL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlFILTROSCALSOL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTABLA, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlACCIONES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGUARDARActionPerformed
        try {
            guardar("NUEVO",PRODUCTO);
            RegPendientes.btnCancel.doClick();
        } catch (SQLException ex) {
            Logger.getLogger(ListProd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ListProd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGUARDARActionPerformed

    private void btnCANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCANCELActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCANCELActionPerformed

    private void tblTABLAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMousePressed
        try
        {
            if(F.clickD(evt, tblTABLA))
                    seleccion();
                else
                    if(F.dosClick(evt, tblTABLA))
                        selectPROD();
        }catch (SQLException ex) {
            Logger.getLogger(ListProd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ListProd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTABLAMousePressed

    private void txtMARCAItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtMARCAItemStateChanged
        try {
            // TODO add your handling code here:
            FILTROS();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ListProd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtMARCAItemStateChanged

    private void txtTECNOLOGIAItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtTECNOLOGIAItemStateChanged
        try {
            // TODO add your handling code here:
            FILTROS();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ListProd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtTECNOLOGIAItemStateChanged

    private void txtVOLTAJEItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtVOLTAJEItemStateChanged
        try {
            // TODO add your handling code here:
            FILTROS();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ListProd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtVOLTAJEItemStateChanged

    private void txtPRESTACIONItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtPRESTACIONItemStateChanged
        try {
            // TODO add your handling code here:
            FILTROS();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ListProd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtPRESTACIONItemStateChanged

    private void txtCAPACIDADItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtCAPACIDADItemStateChanged
        try {
            // TODO add your handling code here:
            FILTROS();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ListProd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtCAPACIDADItemStateChanged

    private void txtCANTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCANTKeyPressed
        if(F.ENTER(evt))
            try {
                guardar("NUEVO",PRODUCTO);
        } catch (SQLException ex) {
            Logger.getLogger(ListProd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ListProd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtCANTKeyPressed

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
            java.util.logging.Logger.getLogger(ListProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    ListProd dialog = new ListProd(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ListProd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnCANCEL;
    private javax.swing.JButton btnGUARDAR;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jspTABLA;
    private javax.swing.JPanel pnlACCIONES;
    private javax.swing.JPanel pnlFILTROSCALSOL;
    private javax.swing.JTable tblTABLA;
    private javax.swing.JTextField txtCANT;
    private javax.swing.JComboBox<String> txtCAPACIDAD;
    private javax.swing.JTextArea txtCOMENTARIOS;
    private javax.swing.JComboBox<String> txtMARCA;
    private javax.swing.JComboBox<String> txtPRESTACION;
    private javax.swing.JComboBox<String> txtTECNOLOGIA;
    private javax.swing.JComboBox<String> txtVOLTAJE;
    // End of variables declaration//GEN-END:variables
    
}
