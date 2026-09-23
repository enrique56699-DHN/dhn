/*

 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;

import CONTROLADOR.ControlCable;
import CONTROLADOR.ControlCarrito;
import CONTROLADOR.ControlEmpleados;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloCable;
import MODELO.ModeloCarritoTabla;
import MODELO.ModeloEmpleadosTabla;
import java.awt.Color;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;


/**
 *
 * @author KIKE FRENCH
 */
public final class VAlmacen extends javax.swing.JInternalFrame {
    
    /** Creates new form VRepPagos
     * @throws java.lang.Exception
        */
    private int IDPENDIENTE,IDEMPLEADO,IDCABLE;
    private String TITULO, ACCION;
    private ModeloEmpleadosTabla met;
    
    private String IMP;
       
    public VAlmacen(){
       
    }
    
    public VAlmacen(String titulo) throws Exception{
        met = new ModeloEmpleadosTabla("ACTIVO","ASC");         
        initComponents();
        this.setTitle(titulo);
        this.Bar.setFloatable(false);   
        empleados();
        this.txtEMPLEADO.requestFocus();
        F.teclas(this);
    }  
    
    private void atualizarTablas(String ESTADO, String ESTADO1,String ESTADO2) throws ClassNotFoundException, SQLException, Exception
    {
        
        ModeloCarritoTabla mct;
        if(ESTADO.equals("REQUERIDO"))
        {
            Date d;
            Calendar c = Calendar.getInstance();
            
            d=G.hoy();//formateador.parse(FECHA);       
            c.setTime(d);
            c.add(Calendar.DATE,+1);
            d = c.getTime();
            String FECHA1=""+G.formato.format(d);
            
            jspENTREGADOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REQUERIDOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
            jspENTREGADOS.setFont(new java.awt.Font("Tahoma", 0, 10));
            jspDEVUELTOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HOY", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
            jspDEVUELTOS.setFont(new java.awt.Font("Tahoma", 0, 10));
            jspUSADOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MAÑANA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
            jspUSADOS.setFont(new java.awt.Font("Tahoma", 0, 10));       
        
            //REQUERIDOS
            mct = new ModeloCarritoTabla( ESTADO,"",0);        
            tblENTREGADOS.setModel(mct);        
            F.dimensionar(tblENTREGADOS,"ALMACEN");

            //REQUERIDOS HOY
            mct = new ModeloCarritoTabla(ESTADO1,G.FECHA(G.hoy(),false), IDEMPLEADO);        
            tblDEVUELTOS.setModel(mct);    
            F.dimensionar(tblDEVUELTOS,"ALMACEN");

            //REQUERIDOS MAÑANA
            mct = new ModeloCarritoTabla(ESTADO2,FECHA1, IDEMPLEADO);        
            tblUSADOS.setModel(mct);        
            F.dimensionar(tblUSADOS,"ALMACEN");
        }
        else
        {
            jspENTREGADOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ENTREGADOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
            jspENTREGADOS.setFont(new java.awt.Font("Tahoma", 0, 10));
            jspDEVUELTOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DEVUELTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
            jspDEVUELTOS.setFont(new java.awt.Font("Tahoma", 0, 10));
            jspUSADOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "USADOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
            jspUSADOS.setFont(new java.awt.Font("Tahoma", 0, 10));
            
            //ENTREGADO
            mct = new ModeloCarritoTabla( ESTADO,"",IDEMPLEADO);        
            tblENTREGADOS.setModel(mct);        
            F.dimensionar(tblENTREGADOS,"ALMACEN");

            //DEVUELTO
            mct = new ModeloCarritoTabla(ESTADO1,G.FECHA(G.hoy(),false), IDEMPLEADO);        
            tblDEVUELTOS.setModel(mct);    
            F.dimensionar(tblDEVUELTOS,"ALMACEN");

            //USADO
            mct = new ModeloCarritoTabla(ESTADO2,G.FECHA(G.hoy(),false), IDEMPLEADO);        
            tblUSADOS.setModel(mct);        
            F.dimensionar(tblUSADOS,"ALMACEN");
        
        }
        
    }    
    
    private void empleados() throws ClassNotFoundException, SQLException, Exception
    {
        txtEMPLEADO.removeAllItems();
        int NUM = met.getRowCount();
        for(int i=0;i<NUM;i++)
        {
            txtEMPLEADO.addItem(""+ met.getValueAt(i, 1));
        }        
    }
        
    private void seleccion(JTable tabla) throws ClassNotFoundException, SQLException, Exception
    {
        int fila = tabla.getSelectedRow();
        IDPENDIENTE = (int) tabla.getValueAt(fila, 3);        
        
    }
    
    private void abrir(int IDPENDIENTE, int IDORDENP) throws ParseException, Exception
    {
        RegOrdenesPD reg;            
        reg = new RegOrdenesPD(null,true);
        reg.mostrar(IDPENDIENTE,IDORDENP);
        reg.setVisible(true);
            
    }
    
    private void pregunta(JTable TABLA) throws SQLException, Exception
    {
        String EMPLEADO = txtEMPLEADO.getSelectedItem().toString();
        Object[] inputFields = {"¿IMPRIMIR REPORTE?"};

        int option = JOptionPane.showConfirmDialog(this, inputFields, "Impresion de REPORTE", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (option == JOptionPane.OK_OPTION)
        {          
            if(IMP.equals("OTROS"))            
                atualizarTablas("ENTREGADO","DEVUELTO","USADO");            
            else
                atualizarTablas("REQUERIDO","REQUERIDO","REQUERIDO");
            
            imprimir(TABLA,TITULO,EMPLEADO);
            //this.dispose();
        }
        guardarCABLE();
    }
    
    private void imprimir(JTable TABLA, String TITULO, String EMPLEADO) throws ClassNotFoundException, SQLException
   {
       ImpALMACEN pca = new ImpALMACEN(TABLA.getModel(),TITULO,EMPLEADO,txtCENTREGADO.getText(), lblCUSADO.getText(), txtCDEVUELTO.getText());
       JButton[] opciones = pca.getButtons();
       JOptionPane.showInternalOptionDialog(this, pca, "Imprimir REPORTE",JOptionPane.OK_CANCEL_OPTION,-1, null, opciones, opciones[0]);
       if(pca.getOpcion() != null)       
           pca.Imprimir();
       
   }
    
    private void marcar(JTable TABLA, String ESTADO, String CUANTOS) throws ClassNotFoundException, SQLException, Exception
    {   
        int fila = TABLA.getSelectedRow();
        int IDCARRITO = (int)TABLA.getValueAt(fila, 0);
        ControlCarrito ctrl;
        ctrl = new ControlCarrito();
        int eleccion;
        
        switch(CUANTOS)
        {
            case "UNO":
                eleccion = JOptionPane.showConfirmDialog(null, "DESEAS DEVOLVER ESTE ARTICULO");
                if ( eleccion == 0)
                {
                    if(IDEMPLEADO != 0)                                            
                        ctrl.actualizarESTADO(IDCARRITO,ESTADO,G.FECHA(G.hoy(),false), IDEMPLEADO);                 
                    else
                        JOptionPane.showMessageDialog(null, "No se cuenta con un empleado a quien asignar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                    ctrl.mane.limpiarRecursos();
                break;
            case "TODOS":
                eleccion = JOptionPane.showConfirmDialog(null, "DESEAS DEVOLVER TODOS LOS ARTICULOS");
                if ( eleccion == 0)
                {
                    if(IDEMPLEADO != 0)
                    {
                        for (int i = 0; i<TABLA.getRowCount(); i++)
                        {      
                            ctrl = new ControlCarrito();
                            IDCARRITO = (int)TABLA.getValueAt(i, 0);
                            ctrl.actualizarESTADO(IDCARRITO,ESTADO,G.FECHA(G.hoy(),false), IDEMPLEADO);     
                        }
                    }
                }
                else
                    ctrl.mane.limpiarRecursos();
                break;
            default:
                break;
        }
        
    }
    
    private double gruparYContar(JTable tabla) {
       
        int columnaAgrupar = 2;
        int columnaContar = 1;
        double cantidad = 0;
       
        for (int row = 0; row < tabla.getRowCount(); row++) 
        {
            Object claveGrupo = tabla.getValueAt(row, columnaAgrupar);
            Object valor = tabla.getValueAt(row, columnaContar);
            if(claveGrupo.equals("CABLE DE RED PARA EXTERIOR COLOR BLANCO POR METRO"))            
                cantidad = cantidad + (double)valor;
        }
        return cantidad;
    }
 
    private void responseCABLE() throws Exception
    {         
        lblCENTREGADO.setText(""+gruparYContar(tblENTREGADOS));          
        //lblCDEVUELTO.setText(""+gruparYContar(tblDEVUELTOS));
        lblCUSADO.setText(""+gruparYContar(tblUSADOS));        
        
        ControlEmpleados ce = new ControlEmpleados();
        IDEMPLEADO = ce.getRegistros(txtEMPLEADO.getSelectedItem().toString());                             
        
        if(F.existe(IDEMPLEADO,G.FECHA(G.hoy(),false)) == true)  
        {
            ACCION = "EDITAR";
            
            ModeloCable mdl;
            ControlCable ctrl = new ControlCable();
            
            mdl=ctrl.getRegistro(IDEMPLEADO,G.FECHA(G.hoy(),false));
            IDCABLE = mdl.getIDCABLE();
            this.txtCENTREGADO.setText(mdl.getENTREGADO()+"");
            this.txtCDEVUELTO.setText(mdl.getDEVUELTO()+"");
            
        }                 
        else
        {
            ACCION = "NUEVO";
            this.txtCENTREGADO.setText(""+0);
            this.txtCDEVUELTO.setText(""+0);
        }            
        
        double entregado = Double.parseDouble(txtCENTREGADO.getText());
        double usado = gruparYContar(tblUSADOS);
        double devuelto = entregado - usado;
        lblCDEVUELTO.setText(""+devuelto);
        //txtCDEVUELTO.setText(""+devuelto);
    }
    
    private void guardarCABLE() throws ClassNotFoundException, SQLException, Exception
    {
        ModeloCable mdl = new ModeloCable();
        ControlCable ctrl = new ControlCable();
        
        mdl.setIDEMPLEADO(IDEMPLEADO);
        mdl.setFECHA(G.FECHA(G.hoy(),false));
        mdl.setENTREGADO(Double.parseDouble(txtCENTREGADO.getText()));
        mdl.setUSADO(Double.parseDouble(lblCUSADO.getText()));
        mdl.setDEVUELTO(Double.parseDouble(txtCDEVUELTO.getText()));
        
        switch(ACCION)
        {
            case "EDITAR":
                mdl.setIDCABLE(IDCABLE);
                ctrl.actualizarRegistro(mdl);
                break;
            case "NUEVO":
                ctrl.guardarRegistro(mdl);
                break;
            default:
                break;
        }
    }
    
    private void contaCABLE()
    {
        double entregado = 0;
        double devuelto = 0;
        if(txtCENTREGADO.getText()!=null)
            entregado = Double.parseDouble(txtCENTREGADO.getText());
        if(txtCDEVUELTO.getText()!=null)
            devuelto = Double.parseDouble(txtCDEVUELTO.getText());
        
        double diferencia = entregado - devuelto;
        if(diferencia>1)        
            lblDIFERENCIA.setForeground(Color.red);
        else
            lblDIFERENCIA.setForeground(Color.black);
        lblDIFERENCIA.setText(""+diferencia);
        
    }
    
    private void devolver(String CUANTOS)
    {
        try {
            marcar(tblENTREGADOS,"DEVUELTO",CUANTOS);
            if(IMP.equals("OTROS"))            
                atualizarTablas("ENTREGADO","DEVUELTO","USADO");            
            else
                atualizarTablas("REQUERIDO","REQUERIDO","REQUERIDO");            
        } catch (SQLException ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegOrdenesPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void consultaAlmacen() throws ClassNotFoundException, SQLException, Exception
    {
        ControlEmpleados ce = new ControlEmpleados();
        IDEMPLEADO = ce.getRegistros(txtEMPLEADO.getSelectedItem().toString());
        this.btnDEVOLVER.setEnabled(true);
        this.btnDEVOLVERT.setEnabled(true);
        atualizarTablas("ENTREGADO","DEVUELTO","USADO");
        IMP = "OTROS";
        responseCABLE();
        
    }
    
    private void cable() throws ParseException, ClassNotFoundException, SQLException
    {
        RegCableUsado reg;            
        reg = new RegCableUsado(null,true);
        reg.setVisible(true);
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
        jButton1 = new javax.swing.JButton();
        btnREQUERIDOS = new javax.swing.JButton();
        btnACTUALIZAR = new javax.swing.JButton();
        pnlLISTA = new javax.swing.JPanel();
        jspENTREGADOS = new javax.swing.JScrollPane();
        tblENTREGADOS = new javax.swing.JTable();
        jspDEVUELTOS = new javax.swing.JScrollPane();
        tblDEVUELTOS = new javax.swing.JTable();
        jspUSADOS = new javax.swing.JScrollPane();
        tblUSADOS = new javax.swing.JTable();
        bntIMPRIMIR1 = new javax.swing.JButton();
        bntIMPRIMIR2 = new javax.swing.JButton();
        bntIMPRIMIR3 = new javax.swing.JButton();
        btnDEVOLVER = new javax.swing.JButton();
        txtCENTREGADO = new javax.swing.JTextField();
        txtCDEVUELTO = new javax.swing.JTextField();
        lblCENTREGADO = new javax.swing.JLabel();
        lblCDEVUELTO = new javax.swing.JLabel();
        lblCUSADO = new javax.swing.JLabel();
        lblDIFERENCIA = new javax.swing.JLabel();
        btnDEVOLVERT = new javax.swing.JButton();
        txtEMPLEADO = new javax.swing.JComboBox<>();
        txtBUSCAR = new javax.swing.JTextField();

        setClosable(true);
        setMaximizable(true);
        setTitle("Listas de Almacen");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/almacen.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        Bar.setRollover(true);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/UTP.png"))); // NOI18N
        jButton1.setText("CABLE");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Bar.add(jButton1);

        btnREQUERIDOS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnREQUERIDOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/requerido.png"))); // NOI18N
        btnREQUERIDOS.setText("REQUERIDOS");
        btnREQUERIDOS.setFocusable(false);
        btnREQUERIDOS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnREQUERIDOS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnREQUERIDOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnREQUERIDOSActionPerformed(evt);
            }
        });
        Bar.add(btnREQUERIDOS);

        btnACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/consulta.png"))); // NOI18N
        btnACTUALIZAR.setText("ESTADOS");
        btnACTUALIZAR.setToolTipText("Consultar");
        btnACTUALIZAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnACTUALIZAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnACTUALIZARActionPerformed(evt);
            }
        });
        Bar.add(btnACTUALIZAR);

        pnlLISTA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        pnlLISTA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jspENTREGADOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ENTREGADOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jspENTREGADOS.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspENTREGADOS.setMaximumSize(new java.awt.Dimension(185, 185));
        jspENTREGADOS.setMinimumSize(new java.awt.Dimension(185, 185));
        jspENTREGADOS.setPreferredSize(new java.awt.Dimension(185, 185));

        tblENTREGADOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblENTREGADOS.setRowHeight(75);
        tblENTREGADOS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblENTREGADOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblENTREGADOSMousePressed(evt);
            }
        });
        jspENTREGADOS.setViewportView(tblENTREGADOS);

        jspDEVUELTOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DEVUELTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jspDEVUELTOS.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspDEVUELTOS.setMaximumSize(new java.awt.Dimension(185, 185));
        jspDEVUELTOS.setMinimumSize(new java.awt.Dimension(185, 185));
        jspDEVUELTOS.setPreferredSize(new java.awt.Dimension(185, 185));

        tblDEVUELTOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblDEVUELTOS.setRowHeight(40);
        tblDEVUELTOS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDEVUELTOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDEVUELTOSMousePressed(evt);
            }
        });
        jspDEVUELTOS.setViewportView(tblDEVUELTOS);

        jspUSADOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "USADOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jspUSADOS.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jspUSADOS.setMaximumSize(new java.awt.Dimension(185, 185));
        jspUSADOS.setMinimumSize(new java.awt.Dimension(185, 185));
        jspUSADOS.setPreferredSize(new java.awt.Dimension(185, 185));

        tblUSADOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblUSADOS.setRowHeight(40);
        tblUSADOS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblUSADOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblUSADOSMousePressed(evt);
            }
        });
        jspUSADOS.setViewportView(tblUSADOS);

        bntIMPRIMIR1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer.png"))); // NOI18N
        bntIMPRIMIR1.setToolTipText("");
        bntIMPRIMIR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntIMPRIMIR1ActionPerformed(evt);
            }
        });

        bntIMPRIMIR2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer.png"))); // NOI18N
        bntIMPRIMIR2.setToolTipText("");
        bntIMPRIMIR2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntIMPRIMIR2ActionPerformed(evt);
            }
        });

        bntIMPRIMIR3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/printer.png"))); // NOI18N
        bntIMPRIMIR3.setToolTipText("");
        bntIMPRIMIR3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntIMPRIMIR3ActionPerformed(evt);
            }
        });

        btnDEVOLVER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/-.png"))); // NOI18N
        btnDEVOLVER.setToolTipText("Devolver Producto");
        btnDEVOLVER.setEnabled(false);
        btnDEVOLVER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDEVOLVERActionPerformed(evt);
            }
        });

        txtCENTREGADO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCENTREGADO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCENTREGADO.setText("0");
        txtCENTREGADO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cable Entregado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtCENTREGADO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCENTREGADOKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCENTREGADOKeyTyped(evt);
            }
        });

        txtCDEVUELTO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCDEVUELTO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCDEVUELTO.setText("0");
        txtCDEVUELTO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cable Devuelto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtCDEVUELTO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCDEVUELTOKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCDEVUELTOKeyTyped(evt);
            }
        });

        lblCENTREGADO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCENTREGADO.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCENTREGADO.setText("0");
        lblCENTREGADO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cable por entregar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblCDEVUELTO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCDEVUELTO.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCDEVUELTO.setText("0");
        lblCDEVUELTO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cable por Devolver", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblCUSADO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCUSADO.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCUSADO.setText("0");
        lblCUSADO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cable Usado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        lblDIFERENCIA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDIFERENCIA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDIFERENCIA.setText("0");
        lblDIFERENCIA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Diferencia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        btnDEVOLVERT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/almacen.png"))); // NOI18N
        btnDEVOLVERT.setToolTipText("Devolver Todos lo Productos");
        btnDEVOLVERT.setEnabled(false);
        btnDEVOLVERT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDEVOLVERTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLISTALayout = new javax.swing.GroupLayout(pnlLISTA);
        pnlLISTA.setLayout(pnlLISTALayout);
        pnlLISTALayout.setHorizontalGroup(
            pnlLISTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLISTALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLISTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspENTREGADOS, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlLISTALayout.createSequentialGroup()
                        .addComponent(bntIMPRIMIR1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDEVOLVER)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDEVOLVERT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCENTREGADO, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCENTREGADO, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLISTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspDEVUELTOS, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlLISTALayout.createSequentialGroup()
                        .addComponent(bntIMPRIMIR2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCDEVUELTO, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCDEVUELTO, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDIFERENCIA, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLISTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLISTALayout.createSequentialGroup()
                        .addComponent(bntIMPRIMIR3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCUSADO, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspUSADOS, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLISTALayout.setVerticalGroup(
            pnlLISTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLISTALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLISTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlLISTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bntIMPRIMIR1)
                        .addComponent(bntIMPRIMIR2)
                        .addComponent(bntIMPRIMIR3))
                    .addComponent(btnDEVOLVER)
                    .addComponent(lblCUSADO)
                    .addGroup(pnlLISTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCDEVUELTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCDEVUELTO)
                        .addComponent(lblDIFERENCIA))
                    .addComponent(btnDEVOLVERT)
                    .addGroup(pnlLISTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCENTREGADO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCENTREGADO)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLISTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspENTREGADOS, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                    .addComponent(jspDEVUELTOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspUSADOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        txtEMPLEADO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtEMPLEADO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EMPLEADO" }));
        txtEMPLEADO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Empleado:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtEMPLEADO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtEMPLEADOItemStateChanged(evt);
            }
        });
        txtEMPLEADO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEMPLEADOKeyPressed(evt);
            }
        });

        txtBUSCAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBUSCAR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtBUSCAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBUSCARKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtEMPLEADO, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBUSCAR))
            .addComponent(pnlLISTA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEMPLEADO, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(pnlLISTA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void tblENTREGADOSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblENTREGADOSMousePressed
        if (evt.getClickCount() > 1)
        {
            try {
                seleccion(tblENTREGADOS);
                int IDORDENP = F.SERVICIO(IDPENDIENTE);
                abrir(IDPENDIENTE,IDORDENP);
            } catch (SQLException ex) {
                Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblENTREGADOSMousePressed

    private void tblDEVUELTOSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDEVUELTOSMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() > 1)
        {
            try {
                seleccion(tblDEVUELTOS);
                int IDORDENP = F.SERVICIO(IDPENDIENTE);
                abrir(IDPENDIENTE,IDORDENP);
            } catch (Exception ex) {
                Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblDEVUELTOSMousePressed

    private void tblUSADOSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUSADOSMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() > 1)
        {
            try {
                seleccion(tblUSADOS);
                int IDORDENP = F.SERVICIO(IDPENDIENTE);
                abrir(IDPENDIENTE,IDORDENP);
            } catch (Exception ex) {
                Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblUSADOSMousePressed

    private void btnACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnACTUALIZARActionPerformed
        try {
            consultaAlmacen();
        } catch (SQLException ex) {
            Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnACTUALIZARActionPerformed

    private void btnREQUERIDOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnREQUERIDOSActionPerformed
        try {
            this.btnDEVOLVER.setEnabled(false);
            this.btnDEVOLVERT.setEnabled(false);
            atualizarTablas("REQUERIDO","REQUERIDO","REQUERIDO");
            IMP = "REQUERIDO";
        } catch (SQLException ex) {
            Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnREQUERIDOSActionPerformed

    private void bntIMPRIMIR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntIMPRIMIR1ActionPerformed
        try {
            if(IMP.equals("OTROS"))            
                TITULO = "ENTREGADO";            
            else
                TITULO = "REQUERIDO TOTAL";
            pregunta(tblENTREGADOS);
        } catch (Exception ex) {
            Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntIMPRIMIR1ActionPerformed

    private void bntIMPRIMIR2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntIMPRIMIR2ActionPerformed
        // TODO add your handling code here:
        try {
            if(IMP.equals("OTROS"))            
                TITULO = "DEVUELTO";            
            else
                TITULO = "RERQUERIDO HOY";
            pregunta(tblDEVUELTOS);
        } catch (Exception ex) {
            Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntIMPRIMIR2ActionPerformed

    private void bntIMPRIMIR3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntIMPRIMIR3ActionPerformed
        // TODO add your handling code here:
        try {
            if(IMP.equals("OTROS"))            
                TITULO = "USADO";            
            else
                TITULO = "RERQUERIDO MAÑANA";
            pregunta(tblUSADOS);
        } catch (Exception ex) {
            Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntIMPRIMIR3ActionPerformed

    private void btnDEVOLVERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDEVOLVERActionPerformed
        devolver("UNO");
    }//GEN-LAST:event_btnDEVOLVERActionPerformed

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        F.MAYUS(evt);
        F.buscar(tblENTREGADOS, txtBUSCAR.getText(),null,0);
        F.buscar(tblUSADOS, txtBUSCAR.getText(),null,0);
        F.buscar(tblDEVUELTOS, txtBUSCAR.getText(),null,0);
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void txtCENTREGADOKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCENTREGADOKeyReleased
        contaCABLE();
    }//GEN-LAST:event_txtCENTREGADOKeyReleased

    private void txtCDEVUELTOKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCDEVUELTOKeyReleased
        contaCABLE();
    }//GEN-LAST:event_txtCDEVUELTOKeyReleased

    private void btnDEVOLVERTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDEVOLVERTActionPerformed
        devolver("TODOS");
    }//GEN-LAST:event_btnDEVOLVERTActionPerformed

    private void txtCENTREGADOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCENTREGADOKeyTyped
        F.NUM(evt);
    }//GEN-LAST:event_txtCENTREGADOKeyTyped

    private void txtCDEVUELTOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCDEVUELTOKeyTyped
        F.NUM(evt);
    }//GEN-LAST:event_txtCDEVUELTOKeyTyped

    private void txtEMPLEADOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEMPLEADOKeyPressed
        try {
            if (F.ENTER(evt))
                consultaAlmacen();
        } catch (SQLException ex) {
            Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtEMPLEADOKeyPressed

    private void txtEMPLEADOItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtEMPLEADOItemStateChanged
        try {
            consultaAlmacen();
        } catch (SQLException ex) {
            Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtEMPLEADOItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            cable();
        } catch (ParseException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VAlmacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton bntIMPRIMIR1;
    private javax.swing.JButton bntIMPRIMIR2;
    private javax.swing.JButton bntIMPRIMIR3;
    private javax.swing.JButton btnACTUALIZAR;
    private javax.swing.JButton btnDEVOLVER;
    private javax.swing.JButton btnDEVOLVERT;
    private javax.swing.JButton btnREQUERIDOS;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jspDEVUELTOS;
    private javax.swing.JScrollPane jspENTREGADOS;
    private javax.swing.JScrollPane jspUSADOS;
    private javax.swing.JLabel lblCDEVUELTO;
    private javax.swing.JLabel lblCENTREGADO;
    private javax.swing.JLabel lblCUSADO;
    private javax.swing.JLabel lblDIFERENCIA;
    private javax.swing.JPanel pnlLISTA;
    private javax.swing.JTable tblDEVUELTOS;
    private javax.swing.JTable tblENTREGADOS;
    private javax.swing.JTable tblUSADOS;
    private javax.swing.JTextField txtBUSCAR;
    private javax.swing.JTextField txtCDEVUELTO;
    private javax.swing.JTextField txtCENTREGADO;
    private javax.swing.JComboBox<String> txtEMPLEADO;
    // End of variables declaration//GEN-END:variables

}

