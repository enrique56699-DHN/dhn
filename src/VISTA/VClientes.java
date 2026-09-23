/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;


import CONTROLADOR.F;
import MODELO.ModeloClientesTabla;
import java.awt.Rectangle;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author KIKE FRENCH
 */
public final class VClientes extends javax.swing.JInternalFrame {

    private int IDCLIENTE;
    private String CLIENTE;
    private final String ruta = "\\REPORTES\\CLIENTES";

    public VClientes(){            
        
    }
    
    public VClientes(String titulo) throws ClassNotFoundException, SQLException{
        initComponents();
        this.setTitle(titulo);
        permisos(Principal.PRIVILEGIOS);
        this.Bar.setFloatable(false);
        actualizarTablas();
        F.teclas(this);
        F.noENTER(tblTABLA);
    }
    
    /*private void teclasL() 
    {
        JComponent panel = (JComponent) this.getContentPane();

        // Configurar diferentes combinaciones de teclas
        configurarTecla(panel, KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK, "ACTIVAR", () -> activar(true));
        //configurarTecla(panel, KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK, "ACTIVAR_DOBLE", () -> activarDoble());
        configurarTecla(panel, KeyEvent.VK_ESCAPE, 0, "CERRAR", () -> cerrar());
        //configurarTecla(panel, KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK, "GUARDAR", () -> guardar());
        //configurarTecla(panel, KeyEvent.VK_F5, 0, "REFRESCAR", () -> refrescar());
        //configurarTecla(panel, KeyEvent.VK_F1, 0, "AYUDA", () -> mostrarAyuda());
        //configurarTecla(panel, KeyEvent.VK_ENTER, KeyEvent.SHIFT_DOWN_MASK, "ENTRAR_SHIFT", () -> entradaConShift());
    }
    
    private void configurarTecla(JComponent panel, int keyCode, int modifiers, String accionNombre, Runnable accion) 
    {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, modifiers);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, accionNombre);
        panel.getActionMap().put(accionNombre, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accion.run();
            }
        });
    }*/
    
    private void permisos(String PRIVILEGIOS)
    {
        if(PRIVILEGIOS.equals("SADMIN"))
        {
            btnBORRAR.setEnabled(true);
        }
    }
        
    private void actualizarTablas() throws ClassNotFoundException, SQLException
    {
        ModeloClientesTabla mct;
        mct = new ModeloClientesTabla();
        tblTABLA.setModel(mct);
        F.dimensionar(tblTABLA,"CLIENTES");
    }
    
    private void accion(String ACCION) throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        try
        {
            if(ACCION.equals("NUEVO"))            
                IDCLIENTE = 0;
                            
            RegClientes reg;            
            reg = new RegClientes(null,true);
            reg.mostrar(IDCLIENTE,ACCION);
            reg.setVisible(true);
            actualizarTablas();
        }
        catch (ParseException ex)
        {
            JOptionPane.showMessageDialog( null, "PARA PODER EDITAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
        }
    }

    
    private void historial() throws ClassNotFoundException, SQLException, Exception
    {        
       InfoClientes p = new InfoClientes(null,true);
       p.mostrar(IDCLIENTE);
       p.setVisible(true);
    }
    
    private void seleccion() throws ClassNotFoundException, SQLException
    {
        if(tblTABLA.getRowCount() > 0)
        {
            int fila = tblTABLA.getSelectedRow();
            IDCLIENTE = (int) tblTABLA.getValueAt(fila, 0);
            CLIENTE = tblTABLA.getValueAt(fila, 1).toString();
        }
        
    }
    
    private void servicio() throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        RegPendientes datos;
        datos = new RegPendientes(null,true);
        datos.mostrar(0,IDCLIENTE,"NUEVO", "SERVICIO", null, 0);
        datos.setVisible(true);
    }
    
    private void internet() throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        RegContratos datos;
        datos = new RegContratos(null,true);
        datos.mostrar(0,IDCLIENTE,"NUEVO");
        datos.setVisible(true);
    }
    
    private void cctv() throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        RegCotizacionCCTV datos;
        datos = new RegCotizacionCCTV(null,true);
        datos.mostrar(0,IDCLIENTE,"NUEVO");
        datos.setVisible(true);
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmpACCIONES = new javax.swing.JPopupMenu();
        jmmSERVICIO = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmmCONTRATO = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmmFLUJO = new javax.swing.JMenuItem();
        Bar = new javax.swing.JToolBar();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnBORRAR = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEXCEL = new javax.swing.JButton();
        btnSERVICIO = new javax.swing.JButton();
        btnCONTRATO = new javax.swing.JButton();
        btnCCTV = new javax.swing.JButton();
        bntFLUJOTV = new javax.swing.JButton();
        jspTABLA = new javax.swing.JScrollPane();
        tblTABLA = new javax.swing.JTable();
        txtBUSCAR = new javax.swing.JTextField();

        jmmSERVICIO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmSERVICIO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agenda.png"))); // NOI18N
        jmmSERVICIO.setText("Agendar Servicio");
        jmmSERVICIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmSERVICIOActionPerformed(evt);
            }
        });
        jmpACCIONES.add(jmmSERVICIO);
        jmpACCIONES.add(jSeparator1);

        jmmCONTRATO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmCONTRATO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/contrato.png"))); // NOI18N
        jmmCONTRATO.setText("Generar Contrato de Internet");
        jmmCONTRATO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmCONTRATOActionPerformed(evt);
            }
        });
        jmpACCIONES.add(jmmCONTRATO);
        jmpACCIONES.add(jSeparator2);

        jmmFLUJO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmFLUJO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FLUJO.png"))); // NOI18N
        jmmFLUJO.setText("Pagar FlujoTV");
        jmmFLUJO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmFLUJOActionPerformed(evt);
            }
        });
        jmpACCIONES.add(jmmFLUJO);

        setClosable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        Bar.setRollover(true);

        btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_add.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.setFocusable(false);
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        Bar.add(btnNuevo);

        btnEditar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_edit.png"))); // NOI18N
        btnEditar.setText("EDITAR");
        btnEditar.setFocusable(false);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        Bar.add(btnEditar);

        btnBORRAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBORRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_delete.png"))); // NOI18N
        btnBORRAR.setText("BORRAR");
        btnBORRAR.setEnabled(false);
        btnBORRAR.setFocusable(false);
        btnBORRAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBORRAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBORRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBORRARActionPerformed(evt);
            }
        });
        Bar.add(btnBORRAR);

        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_refresh.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setFocusable(false);
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        Bar.add(btnActualizar);

        btnEXCEL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel.png"))); // NOI18N
        btnEXCEL.setText("EXPORTAR");
        btnEXCEL.setFocusable(false);
        btnEXCEL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEXCEL.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEXCELActionPerformed(evt);
            }
        });
        Bar.add(btnEXCEL);

        btnSERVICIO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSERVICIO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agenda.png"))); // NOI18N
        btnSERVICIO.setText("SERVICIO");
        btnSERVICIO.setFocusable(false);
        btnSERVICIO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSERVICIO.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSERVICIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSERVICIOActionPerformed(evt);
            }
        });
        Bar.add(btnSERVICIO);

        btnCONTRATO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCONTRATO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/contrato.png"))); // NOI18N
        btnCONTRATO.setText("CONTRATO");
        btnCONTRATO.setFocusable(false);
        btnCONTRATO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCONTRATO.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCONTRATO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCONTRATOActionPerformed(evt);
            }
        });
        Bar.add(btnCONTRATO);

        btnCCTV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCCTV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/camara.png"))); // NOI18N
        btnCCTV.setText("COT CCTV");
        btnCCTV.setFocusable(false);
        btnCCTV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCCTV.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCCTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCCTVActionPerformed(evt);
            }
        });
        Bar.add(btnCCTV);

        bntFLUJOTV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntFLUJOTV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FLUJO.png"))); // NOI18N
        bntFLUJOTV.setText("FLUJOTV");
        bntFLUJOTV.setToolTipText("Pago de Magistv o Flujotv");
        bntFLUJOTV.setFocusable(false);
        bntFLUJOTV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntFLUJOTV.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntFLUJOTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntFLUJOTVActionPerformed(evt);
            }
        });
        Bar.add(bntFLUJOTV);

        jspTABLA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblTABLA.setAutoCreateRowSorter(true);
        tblTABLA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTABLA.setComponentPopupMenu(jmpACCIONES);
        tblTABLA.setName("tblTABLA"); // NOI18N
        tblTABLA.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTABLA.addMouseListener(new java.awt.event.MouseAdapter() {
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            .addComponent(txtBUSCAR)
            .addComponent(jspTABLA)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            accion("NUEVO");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            seleccion();
            accion("EDITAR");        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnBORRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBORRARActionPerformed

    }//GEN-LAST:event_btnBORRARActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            actualizarTablas();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tblTABLAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMousePressed
        try 
        {
            if(F.clickD(evt, tblTABLA))
                seleccion();
            else
                if(F.dosClick(evt, tblTABLA))
                {
                    seleccion();
                    historial();
                }
            } 
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTABLAMousePressed

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        F.MAYUS(evt);
        F.buscar(tblTABLA, txtBUSCAR.getText(),null,0);
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void btnEXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXCELActionPerformed
        F.exportarXLS(tblTABLA,ruta);
    }//GEN-LAST:event_btnEXCELActionPerformed

    private void btnSERVICIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSERVICIOActionPerformed
        try {
            seleccion();
            servicio();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSERVICIOActionPerformed

    private void btnCONTRATOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCONTRATOActionPerformed
        try {
            seleccion();
            internet();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCONTRATOActionPerformed

    private void btnCCTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCCTVActionPerformed
        try {
            seleccion();
            cctv();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCCTVActionPerformed

    private void bntFLUJOTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntFLUJOTVActionPerformed
        try {
            seleccion();
            RegEntradas p = new RegEntradas(null,true);
            p.mostrar(0,"MAGISTV","PAGO DE MAGIS DE "+CLIENTE);
            p.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntFLUJOTVActionPerformed

    private void jmmSERVICIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmSERVICIOActionPerformed
        try {
            servicio();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmSERVICIOActionPerformed

    private void jmmCONTRATOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmCONTRATOActionPerformed
        try {
            internet();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmCONTRATOActionPerformed

    private void jmmFLUJOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmFLUJOActionPerformed
        try {
            RegEntradas p = new RegEntradas(null,true);
            p.mostrar(0,"MAGISTV","PAGO DE MAGIS DE "+CLIENTE);
            p.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmFLUJOActionPerformed

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
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
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
                //lblIMAGEN.setIcon(F.previsualizar(lblIMAGEN,NARCHIVO,"\\"+"\\"+G.ip+"\\dhn\\PRODUCTOS\\"));
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(VProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }//GEN-LAST:event_tblTABLAKeyReleased

    private void tblTABLAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTABLAKeyPressed
        if(F.ENTER(evt)) {
            Rectangle rect = tblTABLA.getCellRect(tblTABLA.getSelectedRow(), 0, true);
            jmpACCIONES.show(tblTABLA, rect.x, rect.y + rect.height);
        }
        else
            if(F.SHIFT(evt))
            {
                try {
                    seleccion();
                    accion("EDITAR"); 
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(VClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_tblTABLAKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton bntFLUJOTV;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBORRAR;
    private javax.swing.JButton btnCCTV;
    private javax.swing.JButton btnCONTRATO;
    private javax.swing.JButton btnEXCEL;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSERVICIO;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem jmmCONTRATO;
    private javax.swing.JMenuItem jmmFLUJO;
    private javax.swing.JMenuItem jmmSERVICIO;
    private javax.swing.JPopupMenu jmpACCIONES;
    private javax.swing.JScrollPane jspTABLA;
    private javax.swing.JTable tblTABLA;
    private javax.swing.JTextField txtBUSCAR;
    // End of variables declaration//GEN-END:variables

}

