/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package VISTA;

import CONTROLADOR.ControlAntenas;
import MODELO.ModeloAntenas;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class VImport extends javax.swing.JInternalFrame {

    /**
     * Creates new form VImport
     */
    
    private static DefaultTableModel modelo;

    //para exportar
    private final JFileChooser FileChooser = new JFileChooser();
    private final Vector columna = new Vector();
    private final Vector filas = new Vector();

    private final String appPath = System.getProperties().getProperty("user.dir");
    private String separador = System.getProperty("file.separator");

    private File file;
    
    private final FileFilter XLS = new FileNameExtensionFilter("Archivos Excel 1997-2003", "xls");

    public VImport() {
    }
 
    
    public VImport(String titulo) {
        initComponents();
        this.setTitle(titulo);
        buscaCarpeta();
    }
        
    private void ModeloTablas(int a)
    {
        switch(a)
        {
            case 0:
                /*modelo = new DefaultTableModel();
                modelo.addColumn("IDPAQUETE");
                modelo.addColumn("COSTO");
                modelo.addColumn("DESCRIPCION");
                this.tblPAQUETES.setModel(modelo);
                this.tblPAQUETES.setAutoCreateRowSorter(true);
                this.tblPAQUETES.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);*/
                modelo = new DefaultTableModel();
                modelo.addColumn("IDANTENA");
                modelo.addColumn("MODELO");
                modelo.addColumn("MARCA");
                modelo.addColumn("IP");
                modelo.addColumn("SITIO");
                modelo.addColumn("IPPADRE");
                this.tblPAQUETES.setModel(modelo);
                this.tblPAQUETES.setAutoCreateRowSorter(true);
                this.tblPAQUETES.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                break;
            /*case 1:
                modelo = new DefaultTableModel();
                modelo.addColumn("IDCLIENTE");
                modelo.addColumn("NOMBRE");
                //modelo.addColumn("APELLIDOS");
                modelo.addColumn("TEL1");
                modelo.addColumn("TEL2");
                modelo.addColumn("TEL3");
                modelo.addColumn("IDENTIFICACION");
                modelo.addColumn("INE");
                modelo.addColumn("CFE");
                this.tblCLIENTES.setModel(modelo);
                this.tblCLIENTES.setAutoCreateRowSorter(true);
                this.tblCLIENTES.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                break;
            case 2:
                modelo = new DefaultTableModel();
                modelo.addColumn("IDDIRECCION");
                modelo.addColumn("IDCLIENTE");
                modelo.addColumn("CALLE");
                modelo.addColumn("NUMERO");
                modelo.addColumn("COLONIA");
                modelo.addColumn("LOCALIDAD");
                modelo.addColumn("LATITUD");
                modelo.addColumn("LONGITUD");
                modelo.addColumn("REFERENCIA");
                this.tblDIRECCIONES.setModel(modelo);
                this.tblDIRECCIONES.setAutoCreateRowSorter(true);
                this.tblDIRECCIONES.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                break;
            case 3:
                modelo = new DefaultTableModel();
                modelo.addColumn("IDANTENA");
                modelo.addColumn("NOMBRE");
                modelo.addColumn("MARCA");
                modelo.addColumn("MAC");
                modelo.addColumn("IP");
                modelo.addColumn("TIPO");
                modelo.addColumn("ESTADO");
                modelo.addColumn("OBSERVACIONES");              
                this.tblANTENAS.setModel(modelo);
                this.tblANTENAS.setAutoCreateRowSorter(true);
                this.tblANTENAS.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                break;
                
            case 4:
                modelo = new DefaultTableModel();
                modelo.addColumn("IDCONTRATO");
                modelo.addColumn("IDCLIENTE");
                modelo.addColumn("IDDIRECCION");
                modelo.addColumn("IDPAQUETE");
                modelo.addColumn("FREG");
                modelo.addColumn("FINS");
                modelo.addColumn("COMENTARIOS");
                modelo.addColumn("ESTADO");
                modelo.addColumn("FCOR");
                modelo.addColumn("IDANTENA");
                modelo.addColumn("IDROUTER");
                modelo.addColumn("CONT");
                this.tblCONTRATOS.setModel(modelo);
                this.tblCONTRATOS.setAutoCreateRowSorter(true);
                this.tblCONTRATOS.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                break;
                
            case 5:
                modelo = new DefaultTableModel();
                modelo.addColumn("IDCONTRATO");
                modelo.addColumn("FCOR");
                this.tblUPDATECONTRATOS.setModel(modelo);
                this.tblUPDATECONTRATOS.setAutoCreateRowSorter(true);
                this.tblUPDATECONTRATOS.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                break;
                
            default:
                break;*/
        }
    }
    
    private void Importar(int a) throws Exception
    {
        switch(a)
        {
            case 0:
                for(int i = 0; i<this.tblPAQUETES.getRowCount();i++)
                {
                    ModeloAntenas x = new ModeloAntenas();
                    
                    //x.setIDPAQUETE(Integer.parseInt((String) this.tblPAQUETES.getValueAt(i, 0)));
                    x.setMODELO( this.tblPAQUETES.getValueAt(i, 0).toString().toUpperCase());
                    x.setNOMBRE( this.tblPAQUETES.getValueAt(i, 1).toString());
                    x.setMARCA( this.tblPAQUETES.getValueAt(i, 2).toString());
                    x.setIP( this.tblPAQUETES.getValueAt(i, 3).toString());
                    x.setSITIO( this.tblPAQUETES.getValueAt(i, 4).toString());
                    x.setIPPADRE( this.tblPAQUETES.getValueAt(i, 5).toString());                    
                    
                    ControlAntenas cc = new ControlAntenas();
                    cc.guardarRegistro(x);
                }
                break;
            /*case 1:
                for(int i = 0; i<this.tblCLIENTES.getRowCount();i++)
                {
                    ModeloClientes x = new ModeloClientes();
                    
                    x.setIDCLIENTE(Integer.parseInt((String) this.tblCLIENTES.getValueAt(i, 0)));
                    x.setNOMBRE((String) this.tblCLIENTES.getValueAt(i, 1));
                    //x.setAPELLIDOS((String) this.tblCLIENTES.getValueAt(i, 2));
                    x.setTEL1((String) this.tblCLIENTES.getValueAt(i, 2));
                    x.setTEL2((String) this.tblCLIENTES.getValueAt(i, 3));
                    x.setTEL3((String) this.tblCLIENTES.getValueAt(i, 4));
                    x.setIDENTIFICACION((String) this.tblCLIENTES.getValueAt(i, 5));
                    x.setINE((String) this.tblCLIENTES.getValueAt(i, 6));
                    x.setCFE((String) this.tblCLIENTES.getValueAt(i, 7));
                    
                    ControlClientes cc = new ControlClientes();
                    cc.guardarRegistro(x);
                }
                break;
            case 2:
                for(int i = 0; i<this.tblDIRECCIONES.getRowCount();i++)
                {
                    ModeloDirecciones x = new ModeloDirecciones();
                    
                    x.setIDDIRECCION(Integer.parseInt((String) this.tblDIRECCIONES.getValueAt(i, 0)));
                    x.setIDCLIENTE(Integer.parseInt((String) this.tblDIRECCIONES.getValueAt(i, 1)));
                    x.setCALLE((String) this.tblDIRECCIONES.getValueAt(i, 2));
                    x.setNUMERO((String) this.tblDIRECCIONES.getValueAt(i, 3));
                    x.setCOLONIA((String) this.tblDIRECCIONES.getValueAt(i, 4));
                    x.setLOCALIDAD((String) this.tblDIRECCIONES.getValueAt(i, 5));
                    x.setLATITUD((String) this.tblDIRECCIONES.getValueAt(i, 6));
                    x.setLONGITUD((String) this.tblDIRECCIONES.getValueAt(i, 7));
                    x.setREFERENCIA((String) this.tblDIRECCIONES.getValueAt(i, 8));
                    
                    ControlDirecciones cc = new ControlDirecciones();
                    cc.guardarRegistro(x);
                }
                break;
            case 3:
                for(int i = 0; i<this.tblANTENAS.getRowCount();i++)
                {
                    ModeloAntenas x = new ModeloAntenas();
                    
                    x.setIDANTENA(Integer.valueOf((String) this.tblANTENAS.getValueAt(i, 0)));
                    x.setNOMBRE((String) this.tblANTENAS.getValueAt(i, 1));
                    x.setMARCA((String) this.tblANTENAS.getValueAt(i, 2));
                    x.setMAC((String) this.tblANTENAS.getValueAt(i, 3));
                    x.setIP((String) this.tblANTENAS.getValueAt(i, 4));
                    x.setTIPO((String) this.tblANTENAS.getValueAt(i, 5));
                    x.setESTADO((String) this.tblANTENAS.getValueAt(i, 6));
                    x.setOBSERVACIONES((String) this.tblANTENAS.getValueAt(i, 7));

                    ControlAntenas ca = new ControlAntenas();
                    ca.guardarRegistro(x);
                }
                
                break;
            case 4:
                for(int i = 0; i<this.tblCONTRATOS.getRowCount();i++)
                {
                    ModeloContratos x = new ModeloContratos();
                    
                    x.setIDCONTRATO(Integer.parseInt((String) this.tblCONTRATOS.getValueAt(i, 0)));
                    x.setIDCLIENTE(Integer.parseInt((String) this.tblCONTRATOS.getValueAt(i, 1)));
                    x.setIDDIRECCION(Integer.parseInt((String) this.tblCONTRATOS.getValueAt(i, 2)));
                    x.setIDPAQUETE(Integer.parseInt((String) this.tblCONTRATOS.getValueAt(i, 3)));
                    x.setFREG((String) this.tblCONTRATOS.getValueAt(i, 4));
                    x.setFINS((String) this.tblCONTRATOS.getValueAt(i, 5));
                    x.setCOMENTARIOS((String) this.tblCONTRATOS.getValueAt(i, 6));
                    x.setESTADO((String) this.tblCONTRATOS.getValueAt(i, 7));
                    x.setFCOR((String) this.tblCONTRATOS.getValueAt(i, 8));
                    x.setIDANTENA(Integer.parseInt((String) this.tblCONTRATOS.getValueAt(i, 9)));
                    x.setIDROUTER(Integer.parseInt((String) this.tblCONTRATOS.getValueAt(i, 10)));
                    x.setCONT(Boolean.parseBoolean(this.tblCONTRATOS.getValueAt(i, 11).toString()) );
                    
                    ControlContratos cc = new ControlContratos();
                    cc.guardarRegistro(x);
                }
                break;
            case 5:
                for(int i = 0; i<this.tblUPDATECONTRATOS.getRowCount();i++)
                {
                    int IDCONTRATO = (Integer.parseInt((String) this.tblUPDATECONTRATOS.getValueAt(i, 0)));
                    
                    String FCOR=this.tblUPDATECONTRATOS.getValueAt(i, 1).toString();
                    ControlContratos cc = new ControlContratos();
                    cc.actualizarCorte(FCOR, IDCONTRATO);
                }
                break;
            default:
                break;*/
        }
    }
    
    //CREAR TABLA POR POI 3.1.6
    /*public void CrearTablaX(File file, int hojaE) throws IOException
    {

        InputStream File = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(File);
        XSSFSheet hoja = wb.getSheetAt(hojaE);

        for(Row r : hoja)
        {
            Vector d = new Vector();
            for(org.apache.poi.ss.usermodel.Cell c : r)
            {
               switch (c.getCellType()) 
               {
                    case XSSFCell.CELL_TYPE_STRING:
                        d.add(c.getStringCellValue());
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        d.add(c.getNumericCellValue());                        
                        break;
                    default:
                        break;
                }
                
            }
            modelo.addRow(d);
        }
        modelo.removeRow(0);
    }*/
    
    private void CrearTabla(File file, int hojaE) throws IOException 
    {
        Workbook workbook;

        try {
            workbook = Workbook.getWorkbook(file);

            Sheet sheet = workbook.getSheet(hojaE);
            columna.clear();

            for (int i = 0; i < sheet.getColumns(); i++) 
            {
                Cell cell1 = sheet.getCell(i, 0);
                columna.add(cell1.getContents());
            }
            filas.clear();

            for (int j = 0; j < sheet.getRows(); j++) 
            {
                Vector d = new Vector();

                for (int i = 0; i < sheet.getColumns(); i++) 
                {
                    Cell cell = sheet.getCell(i, j);
                    d.add(cell.getContents());
                }
                modelo.addRow(d);
            }
            modelo.removeRow(0);
        } 
        catch (BiffException e) 
        {
            e.printStackTrace();
        }
    }
    
    private void abrir()
    {
        Runtime r = Runtime.getRuntime();
        try 
        {
            r.exec("explorer.exe "+appPath + separador + "Archivo");
        } 
        catch (IOException e) 
        {
            System.out.println("Error al abrir Explorador de Windows "+e);
        }
    }

    private void buscaCarpeta()
    {
        File bCarpeta = new File(appPath + "\\Archivo");
        if(!bCarpeta.exists())
        {
            bCarpeta.mkdirs();
        }
    }
    
    private void ejecutar(File file) throws Exception
    {
        if(file.getName().endsWith("xls"))
            {
                try 
                {
                    for(int i=0; i<1;i++)
                    {
                        ModeloTablas(i);
                        CrearTabla(file,i);
                        Importar(i);
                    }
                }
                catch (IOException ex)
                {
                    Logger.getLogger(VImport.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            /*else
            {
                try 
                {
                    for(int i=0; i<5;i++)
                    {
                        ModeloTablas(i);
                        CrearTablaX(file,i);
                        Importar(i);
                    }
                }
                catch (IOException ex)
                {
                    Logger.getLogger(VImport.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/

    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnImportarTabla = new javax.swing.JButton();
        Tablas = new javax.swing.JTabbedPane();
        jspPAQUETES = new javax.swing.JScrollPane();
        tblPAQUETES = new javax.swing.JTable();
        jspCLIENTES = new javax.swing.JScrollPane();
        tblCLIENTES = new javax.swing.JTable();
        jspDIRECCIONES = new javax.swing.JScrollPane();
        tblDIRECCIONES = new javax.swing.JTable();
        jspANTENAS = new javax.swing.JScrollPane();
        tblANTENAS = new javax.swing.JTable();
        jspCONTRATOS = new javax.swing.JScrollPane();
        tblCONTRATOS = new javax.swing.JTable();
        jspUPDATECONTRATOS = new javax.swing.JScrollPane();
        tblUPDATECONTRATOS = new javax.swing.JTable();

        setClosable(true);
        setMaximizable(true);
        setTitle("Importacion de datos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/importar_S.png"))); // NOI18N

        btnImportarTabla.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnImportarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/importar_S.png"))); // NOI18N
        btnImportarTabla.setText("Importar");
        btnImportarTabla.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImportarTabla.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnImportarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarTablaActionPerformed(evt);
            }
        });

        Tablas.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jspPAQUETES.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblPAQUETES.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblPAQUETES.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jspPAQUETES.setViewportView(tblPAQUETES);

        Tablas.addTab("PAQUETES", jspPAQUETES);

        jspCLIENTES.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblCLIENTES.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCLIENTES.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jspCLIENTES.setViewportView(tblCLIENTES);

        Tablas.addTab("CLIENTES", jspCLIENTES);

        jspDIRECCIONES.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblDIRECCIONES.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblDIRECCIONES.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jspDIRECCIONES.setViewportView(tblDIRECCIONES);

        Tablas.addTab("DIRECCIONES", jspDIRECCIONES);

        jspANTENAS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblANTENAS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblANTENAS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jspANTENAS.setViewportView(tblANTENAS);

        Tablas.addTab("ANTENAS", jspANTENAS);

        jspCONTRATOS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblCONTRATOS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCONTRATOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jspCONTRATOS.setViewportView(tblCONTRATOS);

        Tablas.addTab("CONTRATOS", jspCONTRATOS);

        jspUPDATECONTRATOS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblUPDATECONTRATOS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblUPDATECONTRATOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jspUPDATECONTRATOS.setViewportView(tblUPDATECONTRATOS);

        Tablas.addTab("ACTUALIZAR CONTRATOS", jspUPDATECONTRATOS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Tablas, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnImportarTabla)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tablas, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnImportarTabla)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarTablaActionPerformed
        // TODO add your handling code here:

        //Objeto creado para dar icono y asignar los filtros al JFileChosser
        JFrame dummyFrame = new JFrame();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/IMAGENES/folder.png"));
        dummyFrame.setIconImage(icon);
        FileChooser.addChoosableFileFilter(XLS);
        //FileChooser.addChoosableFileFilter(XLSX);
        FileChooser.setFileFilter(XLS);

        FileChooser.showDialog(dummyFrame, "Importar Tabla");

        file = FileChooser.getSelectedFile();

        //while(!file.getName().endsWith("xlsx")&&!file.getName().endsWith("xls"))
        while(!file.getName().endsWith("xls"))
        {
            JOptionPane.showMessageDialog(null, "Seleccione un archivo excel 1997-2003", "Error", JOptionPane.ERROR_MESSAGE);
            FileChooser.showDialog(null, "Importar Tabla");
            file = FileChooser.getSelectedFile();
        }
        try {
            ejecutar(file);
        } catch (Exception ex) {
            Logger.getLogger(VImport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImportarTablaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Tablas;
    private javax.swing.JButton btnImportarTabla;
    private javax.swing.JScrollPane jspANTENAS;
    private javax.swing.JScrollPane jspCLIENTES;
    private javax.swing.JScrollPane jspCONTRATOS;
    private javax.swing.JScrollPane jspDIRECCIONES;
    private javax.swing.JScrollPane jspPAQUETES;
    private javax.swing.JScrollPane jspUPDATECONTRATOS;
    private javax.swing.JTable tblANTENAS;
    private javax.swing.JTable tblCLIENTES;
    private javax.swing.JTable tblCONTRATOS;
    private javax.swing.JTable tblDIRECCIONES;
    private javax.swing.JTable tblPAQUETES;
    private javax.swing.JTable tblUPDATECONTRATOS;
    // End of variables declaration//GEN-END:variables
}
