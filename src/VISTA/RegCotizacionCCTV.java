
/*
 * RegClientes.java
 *
 * Created on 11-nov-2013, 18:18:42
 */
package VISTA;


import CONTROLADOR.ControlClientes;
import CONTROLADOR.ControlDirecciones;
import CONTROLADOR.F;
import MODELO.ModeloClientes;
import MODELO.ModeloDirecciones;
import MODELO.ModeloDireccionesTabla;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KIKE FRENCH
 */

public final class RegCotizacionCCTV extends javax.swing.JDialog /*implements JRDataSource*/ /*implements Printable*/
{
    private int cli0,dir0;
    private String ESTADO;
    private String FEST,FFIN;
    private ModeloClientes mcli;
    private ModeloDirecciones mdir;
   
    private String CALLE,NUMERO,COLONIA,LOCALIDAD,LATITUD,LONGITUD,REFERENCIA, NOMBRE2;
    private int IDDIR,DIR2;
    private String IDENTIFICACION, TELEFONO;
    private int ID;
    private String ACCION;
    private int IDPENDIENTE;
    private int IDCLIENTE, IDCOTIZACION;
    private String NOMBRE;
    private String DOMICILIO;
    private String CAMBC,CAMBA,CAMBFC,CAMBFCA, CAMDC,CAMDA,CAMDFC,CAMDFCA, DVRE, HDDE;
    private int TBC , TBA , TBFC , TBFCA , TDC , TDA , TDFC , TDFCA;
    
    
    int mpx;
    int precio_base;
    int totalC;
    /** Creates new form RegClientes 
    * @param parent
     * @param modal
     * @throws java.text.ParseException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException */
    public RegCotizacionCCTV(java.awt.Frame parent, boolean modal) throws ParseException, ClassNotFoundException, SQLException {
        super(parent, modal);               
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/camara.png")); 
        setIconImage(icon);
        setLocationRelativeTo(null);
                
        this.Bar.setFloatable(false); 
        this.Bar1.setFloatable(false); 
        
    }
    
    public void mostrar(int IDCOTIZACION, int IDCLIENTE, String ACCION) throws ParseException, Exception
    {
        this.ACCION = ACCION;
        this.IDCOTIZACION = IDCOTIZACION;
        this.IDCLIENTE = IDCLIENTE;
        cliente(IDCLIENTE);
        
        switch(ACCION)
        {
            case "NUEVO":
                direcciones(IDCLIENTE,0);
                //paquetes(0);
                break;
            case "EDITAR":
                               
                
                break;
            default: break;
        }        
    }
    
    private void describir()
    {
        
    }
    
    private void elegirDVR(int totalC)
    {
        
    } 
    
    private void totalC()
    {
        /*
        DEBERAS ELEGIR EL PRECIO BASE A PARTIR DE LA CANTIDAD DE CAMARAS Y LOS MPX        
        */
    }
    
    private void selectmpx(int mpx)
    {
        this.mpx = mpx;
        this.txtTCAM.setEnabled(true);
        this.txtTCAM.requestFocus();
    }
    
    private void totalCAM()
    {
        totalC = Integer.parseInt(txtTCAM.getSelectedItem().toString());
        this.txtBC.setValue(totalC);
        
        switch(mpx)
        {
            case 1:
                habilitar(true);
                Mini(totalC);
                break;
            case 2:
                habilitar(true);
                Mini(totalC);
                break;
            case 3:
                habilitar(true);
                Mini(totalC);
                break;
            case 4:
                habilitar(true);
                Mini(totalC);
                break;    
            case 5:
                habilitar(true);
                Mini(totalC);
                break;    
            case 6:
                habilitar(true);
                Mini(totalC);
                break;
            case 7:
                habilitar(true);
                Mini(totalC);
                break;
            case 8:
                habilitar(true);
                Mini(totalC);
                break;    
            default: break;
        }
        
    }
    
    private void Mini(int totalC)
    {
        if(totalC>0&&totalC<=4)
        {
            rbtn4CH.setSelected(true);
            rbtn1TB.setSelected(true);
        }
        else
            if(totalC>5&&totalC<=8)
            {
                rbtn8CH.setSelected(true);
                rbtn2TB.setSelected(true);
            }
        else
            if(totalC>8&&totalC<=16)
            {
                rbtn16CH.setSelected(true);
                rbtn4TB.setSelected(true);
            }
    }
    
    private void desCAM()
    {                
        if(txtBC.getValue().toString().equals(""))
        {
            TBC = 0;
        }
        else
        {
            TBC = (int) txtBC.getValue();
            CAMBC = "CAMBC"+mpx;     
            
        }            
        
        if(txtBA.getValue().toString().equals(""))
        {
            TBA = 0;
        }
        else
        {
            TBA = (int) txtBA.getValue();
            CAMBA = "CAMBA"+mpx;
        }            
        
        if(txtBFC.getValue().toString().equals(""))
        {
            TBFC = 0;
        }
        else
        {
            TBFC = (int) txtBFC.getValue();
            CAMBFC = "CAMBFC"+mpx;
        }            
        
        if(txtBFCA.getValue().toString().equals(""))
        {
            TBFCA = 0;
        }
        else
        {
            TBFCA = (int) txtBFCA.getValue();
            CAMBFCA = "CAMBFCA"+mpx;
        }
        //--------------------------------------------------------------------//
        if(txtDC.getValue().toString().equals(""))
        {
            TDC = 0;
        }
        else
        {
            TDC = (int) txtDC.getValue();
            CAMDC = "CAMDC"+mpx;
        }            
        
        if(txtDA.getValue().toString().equals(""))
        {
            TDA = 0;
        }
        else
        {
            TDA = (int) txtDA.getValue();
            CAMDA = "CAMDA"+mpx;
        }
        
        if(txtDFC.getValue().toString().equals(""))
        {
            TDFC = 0;
        }
        else
        {
            TDFC = (int) txtDFC.getValue();
            CAMDFC = "CAMDFC"+mpx;
        }            
        
        if(txtDFCA.getValue().toString().equals(""))
        {
            TDFCA = 0;
        }
        else
        {
            TDFCA = (int) txtBC.getValue();
            CAMDFCA = "CAMDFCA"+mpx;
        }
        
        int TCAM = TBC + TBA + TBFC + TBFCA + TDC + TDA + TDFC + TDFCA;
        
        if(totalC != TCAM)
        {
            
        }
    }
    
    private void habilitar(boolean accion)
    {
        this.rbtn1TB.setEnabled(accion);
        this.rbtn2TB.setEnabled(accion);
        this.rbtn4TB.setEnabled(accion);
        
        this.rbtn4CH.setEnabled(accion);
        this.rbtn8CH.setEnabled(accion);
        this.rbtn16CH.setEnabled(accion);
        
        this.txtBC.setEnabled(accion);
        this.txtBA.setEnabled(accion);
        this.txtBFC.setEnabled(accion);
        this.txtBFCA.setEnabled(accion);
        
        this.txtDC.setEnabled(accion);
        this.txtDA.setEnabled(accion);
        this.txtDFC.setEnabled(accion);
        this.txtDFCA.setEnabled(accion);
        
    }
    
    private void activar(boolean ACCION)
    {
        this.btnGUARDAR.setEnabled(ACCION);
        if(Principal.PRIVILEGIOS.equals("SADMIN"))
        {
            /*this.btnIMPRIMIR.setEnabled(ACCION);
            this.bntSALIDAALMACEN.setEnabled(ACCION);
            this.btnDEVOLVER.setEnabled(ACCION);  */          
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

        MPX = new javax.swing.ButtonGroup();
        DVR = new javax.swing.ButtonGroup();
        HDD = new javax.swing.ButtonGroup();
        jpnCONTENEDOR = new javax.swing.JPanel();
        txtNPENDIENTE = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Bar = new javax.swing.JToolBar();
        btnGUARDAR = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtNOMBRE = new javax.swing.JTextField();
        txtDIR = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDESCRIPCION = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtCOMENTARIOS = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        rbtn2 = new javax.swing.JRadioButton();
        rbtn5 = new javax.swing.JRadioButton();
        rbtn8 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        txtTCAM = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtBC = new javax.swing.JSpinner();
        txtBA = new javax.swing.JSpinner();
        txtBFC = new javax.swing.JSpinner();
        txtBFCA = new javax.swing.JSpinner();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDC = new javax.swing.JSpinner();
        txtDA = new javax.swing.JSpinner();
        txtDFC = new javax.swing.JSpinner();
        txtDFCA = new javax.swing.JSpinner();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        rbtn4CH = new javax.swing.JRadioButton();
        rbtn8CH = new javax.swing.JRadioButton();
        rbtn16CH = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        rbtn4TB = new javax.swing.JRadioButton();
        rbtn1TB = new javax.swing.JRadioButton();
        rbtn2TB = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        Bar1 = new javax.swing.JToolBar();
        tgbtnSADMIN = new javax.swing.JToggleButton();
        btnCALCULAR = new javax.swing.JButton();
        btnAGENDAR = new javax.swing.JButton();
        btnNEXT = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cotizacion de CCTV");
        setMaximumSize(new java.awt.Dimension(887, 620));
        setMinimumSize(new java.awt.Dimension(887, 620));
        setPreferredSize(new java.awt.Dimension(887, 620));
        setResizable(false);

        jpnCONTENEDOR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cotizacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jpnCONTENEDOR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtNPENDIENTE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNPENDIENTE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNPENDIENTE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Numero", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtNPENDIENTE.setEnabled(false);
        txtNPENDIENTE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NUM(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("00/00/0000");
        jLabel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        javax.swing.GroupLayout jpnCONTENEDORLayout = new javax.swing.GroupLayout(jpnCONTENEDOR);
        jpnCONTENEDOR.setLayout(jpnCONTENEDORLayout);
        jpnCONTENEDORLayout.setHorizontalGroup(
            jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCONTENEDORLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNPENDIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        jpnCONTENEDORLayout.setVerticalGroup(
            jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCONTENEDORLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtNPENDIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel9))
        );

        Bar.setRollover(true);

        btnGUARDAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disk.png"))); // NOI18N
        btnGUARDAR.setText("GUARDAR");
        btnGUARDAR.setEnabled(false);
        btnGUARDAR.setFocusable(false);
        btnGUARDAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGUARDAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Bar.add(btnGUARDAR);

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnCancel.setText("CANCELAR");
        btnCancel.setFocusable(false);
        btnCancel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        btnCancel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelKeyPressed(evt);
            }
        });
        Bar.add(btnCancel);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion de Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtNOMBRE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNOMBRE.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtNOMBRE.setEnabled(false);

        txtDIR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDIR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecciones Direccion:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 10))); // NOI18N
        txtDIR.setMaximumSize(new java.awt.Dimension(392, 40));
        txtDIR.setMinimumSize(new java.awt.Dimension(392, 40));
        txtDIR.setPreferredSize(new java.awt.Dimension(392, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNOMBRE)
                    .addComponent(txtDIR, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDIR, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos extras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jPanel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtDESCRIPCION.setColumns(20);
        txtDESCRIPCION.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDESCRIPCION.setLineWrap(true);
        txtDESCRIPCION.setRows(5);
        txtDESCRIPCION.setWrapStyleWord(true);
        txtDESCRIPCION.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripcion:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtDESCRIPCION.setEnabled(false);
        txtDESCRIPCION.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MAYUS(evt);
            }
        });
        jScrollPane2.setViewportView(txtDESCRIPCION);

        txtCOMENTARIOS.setColumns(20);
        txtCOMENTARIOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCOMENTARIOS.setLineWrap(true);
        txtCOMENTARIOS.setRows(5);
        txtCOMENTARIOS.setWrapStyleWord(true);
        txtCOMENTARIOS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comentarios:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        txtCOMENTARIOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCOMENTARIOSKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(txtCOMENTARIOS);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forma tu kit", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mpx.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        MPX.add(rbtn2);
        rbtn2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtn2.setText("2 Mpx.");
        rbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn2ActionPerformed(evt);
            }
        });

        MPX.add(rbtn5);
        rbtn5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtn5.setText("5 Mpx.");
        rbtn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn5ActionPerformed(evt);
            }
        });

        MPX.add(rbtn8);
        rbtn8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtn8.setText("8 Mpx. (4K)");
        rbtn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtn2)
                    .addComponent(rbtn5)
                    .addComponent(rbtn8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtn2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtn5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtn8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cantidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        txtTCAM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTCAM.setMaximumRowCount(8);
        txtTCAM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));
        txtTCAM.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total de camaras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtTCAM.setEnabled(false);
        txtTCAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTCAMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTCAM, 0, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(txtTCAM, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de camaras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bullet", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        jPanel7.setMaximumSize(new java.awt.Dimension(180, 135));
        jPanel7.setMinimumSize(new java.awt.Dimension(180, 135));
        jPanel7.setPreferredSize(new java.awt.Dimension(180, 135));

        jLabel1.setText("Audio");

        jLabel2.setText("Full-Color");

        jLabel3.setText("Full-Color + Audio");

        jLabel11.setText("Convencionales");

        txtBC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBC.setEnabled(false);
        txtBC.setMaximumSize(new java.awt.Dimension(50, 21));
        txtBC.setMinimumSize(new java.awt.Dimension(50, 21));
        txtBC.setPreferredSize(new java.awt.Dimension(50, 21));

        txtBA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBA.setEnabled(false);
        txtBA.setMaximumSize(new java.awt.Dimension(50, 21));
        txtBA.setMinimumSize(new java.awt.Dimension(50, 21));
        txtBA.setPreferredSize(new java.awt.Dimension(50, 21));

        txtBFC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBFC.setEnabled(false);
        txtBFC.setMaximumSize(new java.awt.Dimension(50, 21));
        txtBFC.setMinimumSize(new java.awt.Dimension(50, 21));
        txtBFC.setPreferredSize(new java.awt.Dimension(50, 21));

        txtBFCA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBFCA.setEnabled(false);
        txtBFCA.setMaximumSize(new java.awt.Dimension(50, 21));
        txtBFCA.setMinimumSize(new java.awt.Dimension(50, 21));
        txtBFCA.setPreferredSize(new java.awt.Dimension(50, 21));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtBA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtBFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtBFCA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtBC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtBC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBFCA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Domo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        jPanel9.setMaximumSize(new java.awt.Dimension(180, 135));
        jPanel9.setMinimumSize(new java.awt.Dimension(180, 135));
        jPanel9.setName(""); // NOI18N
        jPanel9.setPreferredSize(new java.awt.Dimension(180, 135));

        jLabel4.setText("Audio");

        jLabel5.setText("Full-Color");

        jLabel6.setText("Full-Color + Audio");

        jLabel12.setText("Convencionales");

        txtDC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDC.setEnabled(false);
        txtDC.setMaximumSize(new java.awt.Dimension(50, 21));
        txtDC.setMinimumSize(new java.awt.Dimension(50, 21));
        txtDC.setPreferredSize(new java.awt.Dimension(50, 21));

        txtDA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDA.setEnabled(false);
        txtDA.setMaximumSize(new java.awt.Dimension(50, 21));
        txtDA.setMinimumSize(new java.awt.Dimension(50, 21));
        txtDA.setPreferredSize(new java.awt.Dimension(50, 21));

        txtDFC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDFC.setEnabled(false);
        txtDFC.setMaximumSize(new java.awt.Dimension(50, 21));
        txtDFC.setMinimumSize(new java.awt.Dimension(50, 21));
        txtDFC.setPreferredSize(new java.awt.Dimension(50, 21));

        txtDFCA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDFCA.setEnabled(false);
        txtDFCA.setMaximumSize(new java.awt.Dimension(50, 21));
        txtDFCA.setMinimumSize(new java.awt.Dimension(50, 21));
        txtDFCA.setPreferredSize(new java.awt.Dimension(50, 21));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtDFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtDFCA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDFCA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Upgrades", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel8.setText("NOTA: El dvr tendra la capacidad de MPX elegida en el inicio");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DVR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        DVR.add(rbtn4CH);
        rbtn4CH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtn4CH.setSelected(true);
        rbtn4CH.setText("DVR 4 Canales");
        rbtn4CH.setEnabled(false);

        DVR.add(rbtn8CH);
        rbtn8CH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtn8CH.setText("DVR 8 Canales");
        rbtn8CH.setEnabled(false);

        DVR.add(rbtn16CH);
        rbtn16CH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtn16CH.setText("DVR 16 Canales");
        rbtn16CH.setEnabled(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtn16CH)
                    .addComponent(rbtn4CH)
                    .addComponent(rbtn8CH))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtn4CH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtn8CH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtn16CH)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HDD", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        HDD.add(rbtn4TB);
        rbtn4TB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtn4TB.setText("HDD 4 TB");
        rbtn4TB.setEnabled(false);

        HDD.add(rbtn1TB);
        rbtn1TB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtn1TB.setSelected(true);
        rbtn1TB.setText("HDD 1 TB");
        rbtn1TB.setEnabled(false);

        HDD.add(rbtn2TB);
        rbtn2TB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtn2TB.setText("HDD 2 TB");
        rbtn2TB.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel7.setText("Dias de grabacion Apoximados: ");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtn4TB)
                    .addComponent(rbtn1TB)
                    .addComponent(rbtn2TB)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtn1TB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtn2TB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtn4TB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabel7))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        Bar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Bar1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tgbtnSADMIN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tgbtnSADMIN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pass.png"))); // NOI18N
        tgbtnSADMIN.setToolTipText("PRIVILEGIOS");
        tgbtnSADMIN.setFocusable(false);
        tgbtnSADMIN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tgbtnSADMIN.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tgbtnSADMIN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgbtnSADMINItemStateChanged(evt);
            }
        });
        Bar1.add(tgbtnSADMIN);

        btnCALCULAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCALCULAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calcular.png"))); // NOI18N
        btnCALCULAR.setToolTipText("Calcular");
        btnCALCULAR.setEnabled(false);
        btnCALCULAR.setFocusable(false);
        btnCALCULAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCALCULAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Bar1.add(btnCALCULAR);

        btnAGENDAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAGENDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reloj.png"))); // NOI18N
        btnAGENDAR.setToolTipText("AGENDAR");
        btnAGENDAR.setEnabled(false);
        btnAGENDAR.setFocusable(false);
        btnAGENDAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAGENDAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Bar1.add(btnAGENDAR);

        btnNEXT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNEXT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/next_green.png"))); // NOI18N
        btnNEXT.setToolTipText("Reagendar");
        btnNEXT.setFocusable(false);
        btnNEXT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNEXT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Bar1.add(btnNEXT);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("$ 0,000.0");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnCONTENEDOR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Bar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jpnCONTENEDOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Registro de Pagos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
      
    private void direcciones(int IDCLIENTE,int IDDIR) throws Exception
    {
        txtDIR.removeAllItems();
        ModeloDireccionesTabla mdt;
        mdt = new ModeloDireccionesTabla(IDCLIENTE);        
        int NUM = mdt.getRowCount();
        for(int i=0;i<NUM;i++)
        {
            DIR2= (int) mdt.getValueAt(i, 0);
            //NOMBRE2 =mdt.getValueAt(i, 1).toString();
            CALLE=mdt.getValueAt(i, 1).toString();
            NUMERO=mdt.getValueAt(i, 2).toString();
            COLONIA=mdt.getValueAt(i, 3).toString();
            LOCALIDAD= mdt.getValueAt(i, 4).toString();
            
            DOMICILIO = CALLE +" "+ NUMERO +" "+ COLONIA +" "+ LOCALIDAD;
            
            txtDIR.addItem(DOMICILIO);            

        }
        
        if(IDDIR==0)
        {
            txtDIR.setSelectedIndex(NUM-1);
        }
        else
        {
            ModeloDirecciones mdlD;
            ControlDirecciones ctrlD = new ControlDirecciones();
            mdlD = ctrlD.getRegistrosIDa(IDDIR); 
            DOMICILIO = mdlD.getCALLE()+" "+mdlD.getNUMERO()+" "+mdlD.getCOLONIA()+" "+mdlD.getLOCALIDAD();
            
            txtDIR.setSelectedItem(DOMICILIO);
        }     
    }
    
    private int IDDIRECCION(int IDCLIENTE) throws ClassNotFoundException, SQLException, Exception
    {
        String DOMICILIO2 = txtDIR.getSelectedItem().toString().replaceAll("\\s*$","");
        ModeloDireccionesTabla mdt;
        mdt = new ModeloDireccionesTabla(IDCLIENTE);        
        int i = 0;        
        do
        {
            DIR2 = (int) mdt.getValueAt(i, 0);
            //NOMBRE2 = mdt.getValueAt(i, 1).toString();
            CALLE = mdt.getValueAt(i, 1).toString();
            NUMERO = mdt.getValueAt(i, 2).toString();
            COLONIA = mdt.getValueAt(i, 3).toString();
            LOCALIDAD = mdt.getValueAt(i, 4).toString();            
            DOMICILIO = CALLE +" "+ NUMERO +" "+ COLONIA +" "+ LOCALIDAD;
            DOMICILIO = DOMICILIO.replaceAll("\\s*$","");
            i++;
        }
        while(!DOMICILIO2.equals(DOMICILIO));
        
        return DIR2;
    }
   
    private void cliente(int IDCLIENTE) throws ClassNotFoundException, SQLException, Exception
    {
        ModeloClientes mdlC;
        ControlClientes ctrlC = new ControlClientes();
        mdlC = ctrlC.getRegistrosID(IDCLIENTE);
        NOMBRE = mdlC.getNOMBRE();
        this.txtNOMBRE.setText(NOMBRE);
    }

    
  
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void NUM(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NUM
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_NUM

    private void MAYUS(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MAYUS
        // TODO add your handling code here:
        Character c = evt.getKeyChar();
         if(Character.isLetter(c)) 
         {
            evt.setKeyChar(Character.toUpperCase(c));
         }
    }//GEN-LAST:event_MAYUS

    private void tgbtnSADMINItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgbtnSADMINItemStateChanged
        // TODO add your handling code here:
        int estado = evt.getStateChange();
        if(estado == ItemEvent.SELECTED){
            activar(true);
        } else {
            activar(false);
        }
    }//GEN-LAST:event_tgbtnSADMINItemStateChanged

    private void rbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn2ActionPerformed
        // TODO add your handling code here:
        selectmpx(2);
    }//GEN-LAST:event_rbtn2ActionPerformed

    private void btnCancelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
            this.dispose();
        }
    }//GEN-LAST:event_btnCancelKeyPressed

    private void rbtn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn5ActionPerformed
        // TODO add your handling code here:
        selectmpx(5);
    }//GEN-LAST:event_rbtn5ActionPerformed

    private void rbtn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn8ActionPerformed
        // TODO add your handling code here:
        selectmpx(8);
    }//GEN-LAST:event_rbtn8ActionPerformed

    private void txtTCAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTCAMActionPerformed
        // TODO add your handling code here:
        totalCAM();
    }//GEN-LAST:event_txtTCAMActionPerformed

    private void txtCOMENTARIOSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCOMENTARIOSKeyTyped
        F.MAYUS(evt);
    }//GEN-LAST:event_txtCOMENTARIOSKeyTyped
  
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
            java.util.logging.Logger.getLogger(RegCotizacionCCTV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    RegCotizacionCCTV dialog = new RegCotizacionCCTV(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (ParseException | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(RegCotizacionCCTV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JToolBar Bar1;
    private javax.swing.ButtonGroup DVR;
    private javax.swing.ButtonGroup HDD;
    private javax.swing.ButtonGroup MPX;
    private javax.swing.JButton btnAGENDAR;
    private javax.swing.JButton btnCALCULAR;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGUARDAR;
    private javax.swing.JButton btnNEXT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpnCONTENEDOR;
    private javax.swing.JRadioButton rbtn16CH;
    private javax.swing.JRadioButton rbtn1TB;
    private javax.swing.JRadioButton rbtn2;
    private javax.swing.JRadioButton rbtn2TB;
    private javax.swing.JRadioButton rbtn4CH;
    private javax.swing.JRadioButton rbtn4TB;
    private javax.swing.JRadioButton rbtn5;
    private javax.swing.JRadioButton rbtn8;
    private javax.swing.JRadioButton rbtn8CH;
    private javax.swing.JToggleButton tgbtnSADMIN;
    private javax.swing.JSpinner txtBA;
    private javax.swing.JSpinner txtBC;
    private javax.swing.JSpinner txtBFC;
    private javax.swing.JSpinner txtBFCA;
    private javax.swing.JTextArea txtCOMENTARIOS;
    private javax.swing.JSpinner txtDA;
    private javax.swing.JSpinner txtDC;
    private javax.swing.JTextArea txtDESCRIPCION;
    private javax.swing.JSpinner txtDFC;
    private javax.swing.JSpinner txtDFCA;
    private javax.swing.JComboBox<String> txtDIR;
    private javax.swing.JTextField txtNOMBRE;
    private javax.swing.JTextField txtNPENDIENTE;
    private javax.swing.JComboBox<String> txtTCAM;
    // End of variables declaration//GEN-END:variables

}
