
/*
 * RegClientes.java
 *.
 * Created on 11-nov-2013, 18:18:42
 */

package VISTA;
import CONTROLADOR.ControlCarrito;
import CONTROLADOR.ControlKitsD;
import CONTROLADOR.ControlProductosD;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloCarrito;
import MODELO.ModeloKitsD;
import MODELO.ModeloProductosD;
import MODELO.ModeloProductosTabla;
import javax.swing.JOptionPane;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author KIKE FRENCH
 */
public final class RegArticuloCarrito extends javax.swing.JDialog 
{    
    private int IDPRODUCTO, IDPENDIENTE, IDEMPLEADO, IDCARRITO;
    private double PU, CANT;
    private double TOTAL;
    private String ACCION, ESTADO, FEST, DESCRIPCION, CB, NARCHIVO;
    
    /** Creates new form RegClientes 
    * @param parent
     * @param modal
     * @throws java.text.ParseException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException*/
    public RegArticuloCarrito(java.awt.Frame parent, boolean modal) throws ParseException, ClassNotFoundException, SQLException{
        super(parent, modal);
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/carrito.png")); 
        setIconImage(icon);
        setLocationRelativeTo(null);
        actualizarTablas();
        this.Bar.setFloatable(false);
        this.btnSELECT.setVisible(false);
        F.teclas(this);
        F.noENTER(tblTABLA);
    }
    
    private void actualizarTablas() throws ClassNotFoundException, SQLException 
    {
        String estado = "TODOS"; // Valor por defecto

        Object selectedItem = cmbESTADO.getSelectedItem();
        if (selectedItem != null) {
            String selectedValue = selectedItem.toString().trim();
            if (!selectedValue.isEmpty() && !selectedValue.equalsIgnoreCase("TODOS")) {
                estado = selectedValue;
            }
        }

        ModeloProductosTabla met = new ModeloProductosTabla(estado, "TODAS", false);
        tblTABLA.setModel(met);
        F.dimensionar(tblTABLA, "ARTICULOS");
    }
    
    public void mostrar(int IDPENDIENTE, String ACCION, int IDEMPLEADO, String FEST) throws ClassNotFoundException, SQLException, Exception
    {
        this.ACCION=ACCION;
        this.IDPENDIENTE = IDPENDIENTE;
        this.IDEMPLEADO = IDEMPLEADO;        
        this.FEST = FEST;
        
        switch(ACCION)
        {
            case "NUEVO":
                ESTADO = "REQUERIDO";                
                break;                
            case "EDITAR":
                IDCARRITO = IDPENDIENTE;
                ModeloCarrito mdl;
                ControlCarrito ctrl = new ControlCarrito();                
                mdl = ctrl.getRegistros(IDCARRITO);                    
                this.IDPENDIENTE = mdl.getIDPENDIENTE();
                IDPRODUCTO=mdl.getIDPRODUCTO();
                txtCANT.setText(""+mdl.getCANTIDAD());
                txtTOTAL.setText(""+mdl.getTOTAL());
                this.ESTADO= mdl.getESTADO();
                this.txtBUSCAR.setText(mdl.getDESCRIPCION());
                this.txtBUSCAR.requestFocus();                                
                break;
            case "NUEVO_PKIT":
                        
                    break;
                    
            case "EDITAR_PKIT":
                ModeloKitsD mdlD;
                ControlKitsD ctrlD = new ControlKitsD();                
                mdlD = ctrlD.getRegistroID(IDPENDIENTE);
                
                this.txtCANT.setText(""+mdlD.getCANT());
                this.txtBUSCAR.setText(mdlD.getDESCRIPCION());
                this.txtBUSCAR.requestFocus();
                break;        
                
            case "SELECT":
                this.btnSELECT.setVisible(true);
                this.btnADD.setVisible(false);
                this.btnAGREGAR.setVisible(false);
                this.txtBUSCARCB.setText("");
                this.txtBUSCARCB.requestFocus();
                
                this.pnlACCIONES.setVisible(false);
                //this.txtCANT.setVisible(false);
                //this.txtTOTAL.setVisible(false);
                
                break;
                
            case "EDIT_SELECT":
                this.CB = FEST;
                this.btnSELECT.setVisible(true);
                this.btnADD.setVisible(false);
                this.btnAGREGAR.setVisible(false);
                
                this.pnlACCIONES.setVisible(false);
                //this.txtCANT.setVisible(false);
                //this.txtTOTAL.setVisible(false);
                
                this.txtBUSCARCB.setText(CB);
                this.txtBUSCARCB.setEnabled(false);
                this.txtBUSCAR.requestFocus();
                
                break;
                
            default:
                    break;
        }
    }
    
    private void seleccion() throws ClassNotFoundException, SQLException, Exception
    {
        if(tblTABLA.getRowCount() > 0)
        {
            int fila = tblTABLA.getSelectedRow();
            IDPRODUCTO = (int)tblTABLA.getValueAt(fila, 0);
            PU = (double)tblTABLA.getValueAt(fila, 4);
            DESCRIPCION = tblTABLA.getValueAt(fila, 3).toString();
            if(tblTABLA.getValueAt(fila, 5)!=null)
                if(!tblTABLA.getValueAt(fila, 5).toString().equals(""))
                    NARCHIVO = tblTABLA.getValueAt(fila, 5).toString();
                else
                    NARCHIVO = "0.png";
        }        
    }
     
    private void selectPROD() throws SQLException, Exception
    {
        seleccion();
        this.txtCANT.setText(""+1);
        this.txtTOTAL.setText(""+PU);
        this.txtBUSCAR.setText(DESCRIPCION);
        SwingUtilities.invokeLater(() -> {
                    txtCANT.requestFocusInWindow(); // 1. Garantiza el foco
                    txtCANT.selectAll();            // 2. Selecciona de forma segura
                });
    }
    
    private void calcular()
    {
        if(!txtCANT.getText().equals(""))
        {
            CANT = Double.parseDouble(txtCANT.getText());
            TOTAL = PU * CANT;
            this.txtTOTAL.setText(""+TOTAL);
        }
        
    }
    
    private void guardar(String ACCION) throws ClassNotFoundException, SQLException, Exception
    {        
        int IDKITD = F.Registro("KITSD","IDKITD","DESC");
        ModeloCarrito mdl = new ModeloCarrito();
        ModeloKitsD mdlD = new ModeloKitsD();
        
        if(IDPRODUCTO == 0)
        {
            JOptionPane.showMessageDialog( null, "No se puede agregar al carrito sin un producto o servicio seleccionado", "VERIFICAR", JOptionPane.ERROR_MESSAGE);

        }
        else
        {
            mdl.setIDCARRITO(IDCARRITO);
            mdl.setIDPENDIENTE(IDPENDIENTE);
            mdl.setIDPRODUCTO(IDPRODUCTO);
            mdl.setCANTIDAD(Double.parseDouble(txtCANT.getText()));
            mdl.setTOTAL(Double.parseDouble(txtTOTAL.getText()));
            mdl.setESTADO(ESTADO);
            mdl.setIDEMPLEADO(IDEMPLEADO);
            mdl.setFECHA(FEST);
            
            
            mdlD.setIDKITD(IDPENDIENTE);
            mdlD.setIDKIT(IDEMPLEADO);
            mdlD.setIDPRODUCTO(IDPRODUCTO);
            mdlD.setCANT(Integer.parseInt(txtCANT.getText()));
                      
            
            switch(ACCION)
            {
                case "NUEVO":
                    try
                    { 
                        IDCARRITO = F.Registro("CARRITO","IDCARRITO","DESC");
                        mdl.setIDCARRITO(IDCARRITO);
                        //System.out.println("----------------------------");
                        mdl.setIDPENDIENTE(IDPENDIENTE);
                        ControlCarrito ctrl = new ControlCarrito();
                        ctrl.guardarRegistro(mdl);
                        //Funciones.bitacora("CARRITO",IDCARRITO,ACCION,G.IDUSER,"AGREGADO EL DIA: "+FEST,"");
        
                        this.dispose();
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR GUARDAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);

                    }
                    break;
                case "EDITAR":
                    try
                    {                       
                        
                        ControlCarrito ctrl = new ControlCarrito();
                        ctrl.actualizarRegistro(mdl);
        
                        this.dispose();
                    } 
                    catch (Exception ex) 
                    {
                        
                        JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR ACTUALIZAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                        
                    }
                    break;
                case "NUEVO_PKIT":
                    try
                    { 
                        ControlKitsD ctrl = new ControlKitsD();
                        ctrl.guardarRegistro(mdlD);
                        //Funciones.bitacora("KITSD",IDKITD,ACCION,G.IDUSER,"AGREGADO EL DIA: "+FEST,"");
        
                        this.dispose();
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR GUARDAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);

                    }
                    break;
                    
                case "EDITAR_PKIT":
                    try
                    {
                        /*System.out.println("IDKITD: "+IDKITD);
                        System.out.println("IDKIT: "+IDEMPLEADO);
                        System.out.println("IDPRODUCTO: "+IDPRODUCTO);
                        System.out.println("CANTIDAD: "+Integer.valueOf(txtCANT.getText()));*/
                        
                        ControlKitsD ctrl = new ControlKitsD();
                        ctrl.actualizarRegistro(mdlD);
                        //System.out.println("...............2..............");
                        //Funciones.bitacora("KITSD",IDKITD,ACCION,G.IDUSER,"SE MODIFICO DEL KIT: "+IDKITD,"");
        
                        this.dispose();
                    } 
                    catch (Exception ex) 
                    {
                        JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR ACTUALIZAR EL REGISTRO", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                    
                default: break;

            }
        }
        
    }
    
    private void accion(String ACCION) throws ClassNotFoundException, SQLException, Exception
    {
        try
        {
            if(ACCION.equals("BORRAR"))
            {
                seleccion();
                int eleccion = JOptionPane.showConfirmDialog(null, "Realmente Desea Eliminar el registro?");
                if ( eleccion == 0)
                {
                    try
                    {
                        try
                        {                            
                            F.eliminarRegistro(IDPRODUCTO, "PRODUCTOS", "IDSALIDA");
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog( null, "ERROR AL INTENTAR ELIMINAR LOS DATOS DE LA TABLA", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch (HeadlessException ex)
                    {
                        JOptionPane.showMessageDialog( null, "PARA PODER ELIMINAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            else
            {
                try
                {
                    if(ACCION.equals("NUEVO"))                    
                        IDPRODUCTO = 0;
                    
                    RegProductos p = new RegProductos(null,true);
                    p.mostrar(IDPRODUCTO,ACCION);
                    p.setVisible(true);

                }
                catch (ParseException ex)
                {
                    JOptionPane.showMessageDialog( null, "PARA PODER EDITAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
                }
                actualizarTablas();
            }
        }
        catch (ParseException ex)
        {
            JOptionPane.showMessageDialog( null, "PARA PODER EDITAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
        }      
        
    }    
    
    private void selectPRODUCTOD() throws ClassNotFoundException, SQLException, Exception
    {
        seleccion();
        ModeloProductosD mdl = new ModeloProductosD();
        mdl.setCB(txtBUSCARCB.getText());
        mdl.setIDPRODUCTO(IDPRODUCTO);
        
        if(ACCION.equals("EDIT_SELECT"))
        {                        
            ControlProductosD ctrl = new ControlProductosD();
            ctrl.actualizarRegistro(mdl);
        }
        else
            if(ACCION.equals("SELECT"))
            {
                ControlProductosD ctrl = new ControlProductosD();
                ctrl.guardarRegistro(mdl);
            }
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
        btnADD = new javax.swing.JButton();
        btnCANCEL = new javax.swing.JButton();
        btnAGREGAR = new javax.swing.JButton();
        txtBUSCARCB = new javax.swing.JTextField();
        btnSELECT = new javax.swing.JButton();
        jspTABLA = new javax.swing.JScrollPane();
        tblTABLA = new javax.swing.JTable();
        pnlACCIONES = new javax.swing.JPanel();
        txtTOTAL = new javax.swing.JTextField();
        txtCANT = new javax.swing.JTextField();
        pnlFILTROS = new javax.swing.JPanel();
        lblIMAGEN = new javax.swing.JLabel();
        txtBUSCAR = new javax.swing.JTextField();
        cmbESTADO = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar al Carrito");
        setResizable(false);

        Bar.setRollover(true);

        btnADD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/+.png"))); // NOI18N
        btnADD.setText("AÑADIR");
        btnADD.setToolTipText("");
        btnADD.setFocusable(false);
        btnADD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnADD.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDActionPerformed(evt);
            }
        });
        Bar.add(btnADD);

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

        btnAGREGAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAGREGAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/produtos.png"))); // NOI18N
        btnAGREGAR.setText("AGREGAR");
        btnAGREGAR.setFocusable(false);
        btnAGREGAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAGREGAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAGREGAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAGREGARActionPerformed(evt);
            }
        });
        Bar.add(btnAGREGAR);

        txtBUSCARCB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBUSCARCB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBUSCARCB.setText("CLAVE");
        txtBUSCARCB.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Codigo de barras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtBUSCARCB.setDoubleBuffered(true);
        txtBUSCARCB.setMaximumSize(new java.awt.Dimension(240, 120));
        txtBUSCARCB.setMinimumSize(new java.awt.Dimension(240, 120));
        txtBUSCARCB.setName(""); // NOI18N
        txtBUSCARCB.setPreferredSize(new java.awt.Dimension(240, 120));
        txtBUSCARCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBUSCARCBActionPerformed(evt);
            }
        });
        Bar.add(txtBUSCARCB);

        btnSELECT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSELECT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/productosd.png"))); // NOI18N
        btnSELECT.setText("SELECCIONAR");
        btnSELECT.setToolTipText("Selecciona producto correspondiente");
        btnSELECT.setFocusable(false);
        btnSELECT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSELECT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSELECT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSELECTActionPerformed(evt);
            }
        });
        Bar.add(btnSELECT);

        jspTABLA.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jspTABLA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tblTABLA.setAutoCreateRowSorter(true);
        tblTABLA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTABLA.setName("tblTABLA"); // NOI18N
        tblTABLA.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTABLA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTABLAMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTABLAMousePressed(evt);
            }
        });
        tblTABLA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblTABLAKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblTABLAKeyReleased(evt);
            }
        });
        jspTABLA.setViewportView(tblTABLA);

        pnlACCIONES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cantidades:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        pnlACCIONES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtTOTAL.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTOTAL.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTOTAL.setText("0.0");
        txtTOTAL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Precio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtTOTAL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTOTALKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTOTALKeyTyped(evt);
            }
        });

        txtCANT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCANT.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCANT.setText("0.0");
        txtCANT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cantidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtCANT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCANTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCANTKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCANTKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlACCIONESLayout = new javax.swing.GroupLayout(pnlACCIONES);
        pnlACCIONES.setLayout(pnlACCIONESLayout);
        pnlACCIONESLayout.setHorizontalGroup(
            pnlACCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlACCIONESLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCANT, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlACCIONESLayout.setVerticalGroup(
            pnlACCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlACCIONESLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlACCIONESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCANT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblIMAGEN.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblIMAGEN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIMAGEN.setText("Imagen");
        lblIMAGEN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 3, true));

        txtBUSCAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBUSCAR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtBUSCAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBUSCARKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBUSCARKeyTyped(evt);
            }
        });

        cmbESTADO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbESTADO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO", "TODOS" }));
        cmbESTADO.setSelectedIndex(2);
        cmbESTADO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        cmbESTADO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbESTADOItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlFILTROSLayout = new javax.swing.GroupLayout(pnlFILTROS);
        pnlFILTROS.setLayout(pnlFILTROSLayout);
        pnlFILTROSLayout.setHorizontalGroup(
            pnlFILTROSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFILTROSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIMAGEN, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBUSCAR, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbESTADO, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlFILTROSLayout.setVerticalGroup(
            pnlFILTROSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFILTROSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFILTROSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIMAGEN, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFILTROSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtBUSCAR, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cmbESTADO, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
            .addComponent(pnlACCIONES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlFILTROS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlFILTROS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlACCIONES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed
        try {
            guardar(ACCION);
            } catch (SQLException ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnADDActionPerformed

    private void btnCANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCANCELActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCANCELActionPerformed

    private void tblTABLAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMousePressed
        // TODO add your handling code here:
        /*if (evt.getClickCount() > 1)
        {
            try {
                selectPROD();
            } catch (Exception ex) {
                Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }*/
        try
        {
            if(F.clickD(evt, tblTABLA))
                seleccion();
            else
                if(F.dosClick(evt, tblTABLA))                
                     selectPROD();
        }
        catch (Exception ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }//GEN-LAST:event_tblTABLAMousePressed

    private void txtCANTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCANTKeyReleased
        // TODO add your handling code here:
        calcular();
    }//GEN-LAST:event_txtCANTKeyReleased

    private void btnAGREGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAGREGARActionPerformed
        try {
            // TODO add your handling code here:
            accion("NUEVO");
        } catch (SQLException ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAGREGARActionPerformed

    private void txtBUSCARCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBUSCARCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBUSCARCBActionPerformed

    private void btnSELECTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSELECTActionPerformed
        try {
            selectPRODUCTOD();
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSELECTActionPerformed

    private void txtCANTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCANTKeyTyped
       F.NUM(evt, txtCANT.getText());
    }//GEN-LAST:event_txtCANTKeyTyped

    private void txtTOTALKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTOTALKeyTyped
       F.NUM(evt, txtTOTAL.getText());
    }//GEN-LAST:event_txtTOTALKeyTyped

    private void txtCANTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCANTKeyPressed
        try {
            if(F.ENTER(evt))
                guardar(ACCION);
        } catch (SQLException ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtCANTKeyPressed

    private void txtTOTALKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTOTALKeyPressed
        try {
            if(F.ENTER(evt))
                guardar(ACCION);
        } catch (SQLException ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtTOTALKeyPressed

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        F.MAYUS(evt);
        F.buscar(tblTABLA, txtBUSCAR.getText(),null,0);
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void cmbESTADOItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbESTADOItemStateChanged
        try {
            actualizarTablas();
            txtBUSCAR.requestFocus();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbESTADOItemStateChanged

    private void tblTABLAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMouseClicked
        try {
            seleccion();
            lblIMAGEN.setIcon(F.previsualizar(lblIMAGEN,NARCHIVO,"\\"+"\\"+G.ip+"\\dhn\\PRODUCTOS\\"));
        } catch (IOException ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTABLAMouseClicked

    private void txtBUSCARKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyPressed
        if(F.ENTER(evt))
        {
            if(tblTABLA.getRowCount() > 0)
            {
                tblTABLA.requestFocusInWindow();
                SwingUtilities.invokeLater(() -> 
                {                   
                    tblTABLA.setRowSelectionInterval(0, 0);
                    tblTABLA.scrollRectToVisible(tblTABLA.getCellRect(0, 0, true));
                });           
                SwingUtilities.invokeLater(() -> 
                {                   
                    try 
                    {                
                        seleccion();
                        lblIMAGEN.setIcon(F.previsualizar(lblIMAGEN,NARCHIVO,"\\"+"\\"+G.ip+"\\dhn\\PRODUCTOS\\"));
                    } catch (IOException ex) {
                        Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });   
            }
        }                        
    }//GEN-LAST:event_txtBUSCARKeyPressed

    private void tblTABLAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTABLAKeyReleased
        if(F.NAVEGARTABLA(evt))
        {
            try 
            {
                seleccion();
                lblIMAGEN.setIcon(F.previsualizar(lblIMAGEN,NARCHIVO,"\\"+"\\"+G.ip+"\\dhn\\PRODUCTOS\\"));
            } catch (SQLException ex) {
                Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //else
            
    }//GEN-LAST:event_tblTABLAKeyReleased

    private void tblTABLAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTABLAKeyPressed
        if(F.ENTER(evt))
            try {
                selectPROD();
        } catch (Exception ex) {
            Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }
        else
            if(F.SHIFT(evt))
            {
                try {
                    seleccion();
                    accion("EDITAR");
                    actualizarTablas();
                    txtBUSCAR.requestFocus();
                } catch (SQLException ex) {
                    Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_tblTABLAKeyPressed

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
            java.util.logging.Logger.getLogger(RegArticuloCarrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    RegArticuloCarrito dialog = new RegArticuloCarrito(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (ParseException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(RegArticuloCarrito.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnAGREGAR;
    private javax.swing.JButton btnCANCEL;
    private javax.swing.JButton btnSELECT;
    private javax.swing.JComboBox<String> cmbESTADO;
    private javax.swing.JScrollPane jspTABLA;
    private javax.swing.JLabel lblIMAGEN;
    private javax.swing.JPanel pnlACCIONES;
    private javax.swing.JPanel pnlFILTROS;
    private javax.swing.JTable tblTABLA;
    private javax.swing.JTextField txtBUSCAR;
    private javax.swing.JTextField txtBUSCARCB;
    private javax.swing.JTextField txtCANT;
    private javax.swing.JTextField txtTOTAL;
    // End of variables declaration//GEN-END:variables
    
}
