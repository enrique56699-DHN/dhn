/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VISTA;

import CONTROLADOR.F;
import DHNv2.G;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Monitoreo extends javax.swing.JFrame {
    
    //private JTable ipTable;
    private DefaultTableModel mdlTODAS;
    private DefaultTableModel mdlCAIDAS;
    private String IP, IPPADRE;
    private int cont;

    private ScheduledExecutorService scheduler;
    private List<String> ipList = new ArrayList<>();
    
    private Connection conexion;
    
    private JTable table;
    
    //private boolean monitoring = false;
    //private ExecutorService executorService;
    //private List<PingWorker> workers = new ArrayList<>();
    
    /**
     * Creates new form Monitoreo
     */
    public Monitoreo() {
        
    }
    
    public Monitoreo(String PRIVILEGIOS) throws Exception {
        initComponents();       
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/monitoreo.png")); 
        setIconImage(icon);
        
        //Damos opcion al boton de cerrar de no hacer nada
        setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
        
        //metodo para agregar accion al boton de cerrado
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {                
                stopMonitoring();      
                dispose();
            }
        });
        
        this.Bar.setFloatable(false);
        iniciar();
    }
    
    private void mdlDefault()
    {
        // Modelo de tabla
        mdlTODAS = new DefaultTableModel(new Object[]{"NOMBRE", "MARCA", "IP", "Estado","MS", "Última Respuesta","Sitio","IPPADRE"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable
            }
        };

        mdlCAIDAS = new DefaultTableModel(new Object[]{"NOMBRE", "MARCA", "IP", "Estado","MS", "Última Respuesta","Sitio","IPPADRE"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable
            }
        };
    }
    
    private void iniciar()
    {
        conectarBD();
        mdlDefault();
        actualizarTablas();
        loadIPsFromDatabase();        
        
        for( cont = 0; cont<1000; cont++)
            startMonitoring();
        
        this.TpnlTABLAS.setSelectedIndex(1);
    }
    
    private void actualizarTablas()
    {
        DefaultTableModel modeloVacio = new DefaultTableModel();
        tblTODAS.setModel(modeloVacio);
        
        tblTODAS.setModel(mdlTODAS);
        //ipTable.setRowHeight(25);
        dimensionar(tblTODAS);
        tblTODAS.getColumnModel().getColumn(3).setCellRenderer(new StatusRenderer());
        
        //DefaultTableModel modeloVacio = new DefaultTableModel();
        tblCAIDAS.setModel(modeloVacio);
        
        tblCAIDAS.setModel(mdlCAIDAS);
        //ipTable.setRowHeight(25);
        dimensionar(tblCAIDAS);
        tblCAIDAS.getColumnModel().getColumn(3).setCellRenderer(new StatusRenderer());
    }
    
    private void dimensionar(JTable tabla)
    {        
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(600);  
        //tabla.setRowHeight(30);   
        
        tabla.setAutoCreateRowSorter(true);  
    }
    
    // Renderer para colorear las celdas de estado
    private static class StatusRenderer extends DefaultTableCellRenderer 
    {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, 
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            if (value != null && value.equals("Activo")) {
                c.setBackground(Color.GREEN);
                c.setForeground(Color.BLACK);
            } else if (value != null && value.equals("Inactivo")) {
                c.setBackground(Color.RED);
                c.setForeground(Color.WHITE);
            } else {
                c.setBackground(table.getBackground());
                c.setForeground(table.getForeground());
            }
            
            return c;
        }
    }
    
    private void conectarBD() {
        try {
            // Cambiar estos valores según tu configuración de BD
            String url = G.url;
            String usuario = G.loginDB;
            String contrasena = G.passwordDB;
            
            conexion = DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar a la BD: " + ex.getMessage(), 
                                          "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    private void loadIPsFromDatabase() 
    {
        ipList.clear();
        mdlTODAS.setRowCount(0);
        
        if (conexion == null) 
        {
            JOptionPane.showMessageDialog(this, "No hay conexión a la base de datos", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try 
        (
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nombre, marca, ip, sitio, ippadre FROM antenas")) {

            while (rs.next()) 
            {
                String nombre = rs.getString("nombre");
                String marca = rs.getString("marca");
                String ip = rs.getString("ip");
                String sitio = rs.getString("sitio");
                String ippadre = rs.getString("ippadre");
                ipList.add(ip);
                mdlTODAS.addRow(new Object[]{nombre, marca, ip, "Desconocido", "","",sitio,ippadre});
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startMonitoring() 
    {
        btnINICIAR.setEnabled(false);
        btnDETENER.setEnabled(true);
        
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }

        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            for (int i = 0; i < ipList.size(); i++) {
                final int row = i;
                final String ip = ipList.get(i);
                
                boolean reachable = F.pingIP(ip,2);
                String finalResponseTime = F.pingMS(ip,2);
                
                SwingUtilities.invokeLater(() -> {
                    mdlTODAS.setValueAt(reachable ? "Activo" : "Inactivo", row, 3);
                    mdlTODAS.setValueAt(new java.util.Date().toString(), row, 5);
                    mdlTODAS.setValueAt(finalResponseTime, row, 4);                    
                    
                    //String MS = tblTODAS.getValueAt(row, 4).toString();
                    if("Sin respuesta".equals(finalResponseTime))
                    {
                        Object nombre = tblTODAS.getValueAt(row, 0);
                        Object marca = tblTODAS.getValueAt(row, 1);
                        Object ip2 = tblTODAS.getValueAt(row, 2);
                        Object estado = tblTODAS.getValueAt(row, 3);
                        Object ms = tblTODAS.getValueAt(row, 4);
                        Object resp = tblTODAS.getValueAt(row, 5);
                        Object sitio = tblTODAS.getValueAt(row, 6);
                        Object padre = tblTODAS.getValueAt(row, 7);

                        mdlCAIDAS.addRow(new Object[]{nombre, marca, ip2, estado, ms,resp,sitio,padre});
                        
                    }
                    
                    
                    // Cambiar color de fondo según el estado
                    tblTODAS.repaint();
                    tblCAIDAS.repaint();
                    
                });
                
                
            }
        }, 0, 5000, TimeUnit.SECONDS); // Ejecutar cada 5 segundos
        
    }
    
    /*private void startMonitoring() {
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Agregue al menos una dirección IP", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        monitoring = true;

        executorService = Executors.newFixedThreadPool(tableModel.getRowCount());

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String ip = (String) tableModel.getValueAt(i, 3);
            PingWorker worker = new PingWorker(ip, i);
            workers.add(worker);
            executorService.execute(worker);
        }
    }*/

    /*private boolean pingIP(String ip) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            String command = os.contains("win") ? "ping -n 4 " + ip : "ping -c 4 " + ip;
            
            Process process = Runtime.getRuntime().exec(command);
            int returnVal = process.waitFor();
            
            return (returnVal == 0);
        } catch (IOException | InterruptedException ex) {
            return false;
        }
    }
    
    private String pingMS(String ip)
    {
        String finalResponseTime = null;
        try {
                    String command;
                    if (System.getProperty("os.name").toLowerCase().contains("win")) {
                        command = "ping -n 4 " + ip;
                    } else {
                        command = "ping -c 4 " + ip;
                    }

                    Process process = Runtime.getRuntime().exec(command);
                    BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;
                    boolean reachable = false;
                    String responseTime = "N/A";

                    while ((line = input.readLine()) != null) {
                        if (line.contains("TTL=") || line.contains("ttl=")) {
                            reachable = true;
                            // Extraer tiempo de respuesta (formato puede variar según SO)
                            if (line.contains("time=") || line.contains("tiempo=")) {
                                int timeIndex = line.indexOf("time=");
                                if (timeIndex == -1) timeIndex = line.indexOf("tiempo=");
                                if (timeIndex != -1) {
                                    String timePart = line.substring(timeIndex);
                                    responseTime = timePart.split("=")[1].split(" ")[0];
                                }
                            }
                        }
                    }

                    //int exitValue = process.waitFor();
                    //final String status = (exitValue == 0 || reachable) ? "Activo" : "Inactivo";
                    finalResponseTime = reachable ? responseTime : "Sin respuesta";

                    
                    // Esperar antes del siguiente ping (ej. 5 segundos)
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {
                    
                }
        return finalResponseTime;
    }*/
    
    private void stopMonitoring() {
        //monitoring = false;
        cont=1000;
        btnINICIAR.setEnabled(true);
        btnDETENER.setEnabled(false);

        /*for (PingWorker worker : workers) {
            worker.stop();
        }*/

        if (scheduler != null) {
            scheduler.shutdownNow();
        }
        ipList.clear();
    }
    
    private void pestana() throws SQLException, Exception
    {
        int pestana = this.TpnlTABLAS.getSelectedIndex();
        switch(pestana)
        {
            case 0:
                table = tblTODAS;
                break;
            case 1:
                table = tblCAIDAS;
                break;
            
            default: 
                break;
        }
        //seleccion(table);
    }
    
    private void seleccion(JTable tabla) throws ClassNotFoundException, SQLException, Exception
    {
        int fila = tabla.getSelectedRow();
        IP = tabla.getValueAt(fila, 2).toString();          
        
    }
    
    private void cfgubnt(String ACCION) throws ClassNotFoundException, SQLException, Exception
    {
        //seleccion();
        Tools cfg; 
        cfg = new Tools(null,true);
        cfg.mostrar(0,ACCION,IP);
        cfg.setVisible(true);
    }
    
    /*private class PingWorker implements Runnable {
        private String ip;
        private int row;
        private volatile boolean running = true;

        public PingWorker(String ip, int row) {
            this.ip = ip;
            this.row = row;
        }

        public void stop() {
            running = false;
        }

        @Override
        public void run() {
            while (running && monitoring) {
                try {
                    String command;
                    if (System.getProperty("os.name").toLowerCase().contains("win")) {
                        command = "ping -n 1 " + ip;
                    } else {
                        command = "ping -c 1 " + ip;
                    }

                    Process process = Runtime.getRuntime().exec(command);
                    BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;
                    boolean reachable = false;
                    String responseTime = "N/A";

                    while ((line = input.readLine()) != null) {
                        if (line.contains("TTL=") || line.contains("ttl=")) {
                            reachable = true;
                            // Extraer tiempo de respuesta (formato puede variar según SO)
                            if (line.contains("time=") || line.contains("tiempo=")) {
                                int timeIndex = line.indexOf("time=");
                                if (timeIndex == -1) timeIndex = line.indexOf("tiempo=");
                                if (timeIndex != -1) {
                                    String timePart = line.substring(timeIndex);
                                    responseTime = timePart.split("=")[1].split(" ")[0];
                                }
                            }
                        }
                    }

                    int exitValue = process.waitFor();
                    final String status = (exitValue == 0 || reachable) ? "Activo" : "Inactivo";
                    final String finalResponseTime = reachable ? responseTime : "Sin respuesta";

                    SwingUtilities.invokeLater(() -> {
                        tableModel.setValueAt(status, row, 3);
                        tableModel.setValueAt(finalResponseTime, row, 4);
                    });

                    // Esperar antes del siguiente ping (ej. 5 segundos)
                    Thread.sleep(5000);
                } catch (IOException | InterruptedException e) {
                    if (running) {
                        SwingUtilities.invokeLater(() -> {
                            tableModel.setValueAt("Error", row, 3);
                            tableModel.setValueAt(e.getMessage(), row, 4);
                        });
                    }
                }
            }
        }
    }
*/
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmpACCIONES = new javax.swing.JPopupMenu();
        jmmDIAGNOSTICAR = new javax.swing.JMenuItem();
        jmmWEB = new javax.swing.JMenuItem();
        Bar = new javax.swing.JToolBar();
        btnINICIAR = new javax.swing.JButton();
        btnDETENER = new javax.swing.JButton();
        btnDIAGNOSTICAR = new javax.swing.JButton();
        btnWEB = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        TpnlTABLAS = new javax.swing.JTabbedPane();
        jspTODAS = new javax.swing.JScrollPane();
        tblTODAS = new javax.swing.JTable();
        jspCAIDAS = new javax.swing.JScrollPane();
        tblCAIDAS = new javax.swing.JTable();
        txtBUSCAR = new javax.swing.JTextField();

        jmpACCIONES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jmmDIAGNOSTICAR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmDIAGNOSTICAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/diagnosticar.png"))); // NOI18N
        jmmDIAGNOSTICAR.setText("Diagnosticar");
        jmmDIAGNOSTICAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmDIAGNOSTICARActionPerformed(evt);
            }
        });
        jmpACCIONES.add(jmmDIAGNOSTICAR);

        jmmWEB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmWEB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/web.png"))); // NOI18N
        jmmWEB.setText("Abrir WEB");
        jmmWEB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmWEBActionPerformed(evt);
            }
        });
        jmpACCIONES.add(jmmWEB);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Monitoreo");

        Bar.setRollover(true);

        btnINICIAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnINICIAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/play.png"))); // NOI18N
        btnINICIAR.setText("INICIAR");
        btnINICIAR.setToolTipText("Iniciar Monitoreo ");
        btnINICIAR.setFocusable(false);
        btnINICIAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnINICIAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnINICIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnINICIARActionPerformed(evt);
            }
        });
        Bar.add(btnINICIAR);

        btnDETENER.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDETENER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/stop.png"))); // NOI18N
        btnDETENER.setText("DETENER");
        btnDETENER.setFocusable(false);
        btnDETENER.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDETENER.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDETENER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDETENERActionPerformed(evt);
            }
        });
        Bar.add(btnDETENER);

        btnDIAGNOSTICAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDIAGNOSTICAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/diagnosticar.png"))); // NOI18N
        btnDIAGNOSTICAR.setText("DIAGNOSTICAR");
        btnDIAGNOSTICAR.setFocusable(false);
        btnDIAGNOSTICAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDIAGNOSTICAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDIAGNOSTICAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDIAGNOSTICARActionPerformed(evt);
            }
        });
        Bar.add(btnDIAGNOSTICAR);

        btnWEB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnWEB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/web.png"))); // NOI18N
        btnWEB.setText("ABRIR WEB");
        btnWEB.setFocusable(false);
        btnWEB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnWEB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnWEB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWEBActionPerformed(evt);
            }
        });
        Bar.add(btnWEB);

        TpnlTABLAS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TpnlTABLASMouseClicked(evt);
            }
        });

        tblTODAS.setModel(new javax.swing.table.DefaultTableModel(
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
        jspTODAS.setViewportView(tblTODAS);

        TpnlTABLAS.addTab("TODAS", jspTODAS);

        tblCAIDAS.setModel(new javax.swing.table.DefaultTableModel(
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
        jspCAIDAS.setViewportView(tblCAIDAS);

        TpnlTABLAS.addTab("CAIDAS", jspCAIDAS);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TpnlTABLAS, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TpnlTABLAS, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
        );

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
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtBUSCAR, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnINICIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnINICIARActionPerformed
        //startMonitoring();
        iniciar();
    }//GEN-LAST:event_btnINICIARActionPerformed

    private void btnDETENERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDETENERActionPerformed
        // TODO add your handling code here:
        stopMonitoring();
    }//GEN-LAST:event_btnDETENERActionPerformed

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        try {
            F.MAYUS(evt);
            F.buscar(table, txtBUSCAR.getText(),null,0);
        } catch (Exception ex) {
            Logger.getLogger(Monitoreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void jmmDIAGNOSTICARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmDIAGNOSTICARActionPerformed
        try {
            pestana();        
            cfgubnt("MONITOR");
        } catch (SQLException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmDIAGNOSTICARActionPerformed

    private void jmmWEBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmWEBActionPerformed
        try {
            pestana();            
            F.openweb(IP,"IP");
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(VContratosL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Monitoreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmWEBActionPerformed

    private void btnDIAGNOSTICARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDIAGNOSTICARActionPerformed
        try {
            // TODO add your handling code here:
            pestana();
            seleccion(table);
            cfgubnt("MONITOR");
        } catch (Exception ex) {
            Logger.getLogger(Monitoreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDIAGNOSTICARActionPerformed

    private void btnWEBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWEBActionPerformed
        try {
            // TODO add your handling code here:
            pestana();
            seleccion(table);
            F.openweb(IP,"IP");
            F.clipboard("012837DHN.*,");
        } catch (IOException ex) {
            Logger.getLogger(Monitoreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Monitoreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Monitoreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnWEBActionPerformed

    private void TpnlTABLASMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TpnlTABLASMouseClicked
        try {
            // TODO add your handling code here:
            pestana();
        } catch (Exception ex) {
            Logger.getLogger(Monitoreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TpnlTABLASMouseClicked

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Monitoreo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Monitoreo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Monitoreo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Monitoreo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                new Monitoreo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JTabbedPane TpnlTABLAS;
    private javax.swing.JButton btnDETENER;
    private javax.swing.JButton btnDIAGNOSTICAR;
    private javax.swing.JButton btnINICIAR;
    private javax.swing.JButton btnWEB;
    private javax.swing.JMenuItem jmmDIAGNOSTICAR;
    private javax.swing.JMenuItem jmmWEB;
    private javax.swing.JPopupMenu jmpACCIONES;
    private javax.swing.JScrollPane jspCAIDAS;
    private javax.swing.JScrollPane jspTODAS;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable tblCAIDAS;
    private javax.swing.JTable tblTODAS;
    private javax.swing.JTextField txtBUSCAR;
    // End of variables declaration//GEN-END:variables
    
}
