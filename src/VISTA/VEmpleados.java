/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VClientes.java
 * * Created on 11-nov-2013, 17:33:35
 */
package VISTA;

import CONTROLADOR.ControlEmpleados;
import CONTROLADOR.F;
import DHNv2.G;
import MODELO.ModeloEmpleadosTabla;
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
public final class VEmpleados extends javax.swing.JInternalFrame {

    private int IDEMPLEADO;
    private String NOMBRE;
    private String APELLIDOS;
    private float SUELDO;
    private String EMPRESA;
    private float BONO;
    private float BONOP;
    private String FECHAALTA;
    private String ESTADO = "ACTIVO";

    /** Creates new form VClientes */
    public VEmpleados(){       
    }
    
    public VEmpleados(String titulo) throws ClassNotFoundException, SQLException, ParseException, Exception {            
        initComponents();
        this.setTitle(titulo);
        permisos(Principal.PRIVILEGIOS);        
        this.Bar.setFloatable(false);
        actualizarTablas(ESTADO);
        this.txtBUSCAR.requestFocus();
        F.teclas(this);
        F.noENTER(tblTABLA);
    }
    
    private void seleccion() throws ClassNotFoundException, SQLException
    {
        if(tblTABLA.getRowCount() > 0)
        {
            int fila = tblTABLA.getSelectedRow();
            IDEMPLEADO = (int) tblTABLA.getValueAt(fila, 0);     
            NOMBRE = tblTABLA.getValueAt(fila, 1).toString();
            APELLIDOS = tblTABLA.getValueAt(fila, 2).toString();
            SUELDO = (float)(tblTABLA.getValueAt(fila, 4));
            EMPRESA = tblTABLA.getValueAt(fila, 5).toString();
            BONO = (float)(tblTABLA.getValueAt(fila, 6));
            BONOP = (float)(tblTABLA.getValueAt(fila, 7));
            FECHAALTA = tblTABLA.getValueAt(fila, 8).toString();
        }
        
    }
    
    private void actualizarTablas(String ESTADO) throws ClassNotFoundException, SQLException, Exception
    {        
        ModeloEmpleadosTabla mct;
        mct = new ModeloEmpleadosTabla(ESTADO);
        tblTABLA.setModel(mct);
        F.dimensionar(tblTABLA,"EMPLEADOS");
        
        ControlEmpleados ctrl = new ControlEmpleados();
        double tndhn = ctrl.totalNOMINA("DHN");
        this.lblTNDHN.setText("$ "+G.numero.format(tndhn)+".00");
        ctrl = new ControlEmpleados();
        double tnedh = ctrl.totalNOMINA("ELECTRONICS DIGITAL HOME");
        this.lblTNEDH.setText("$ "+G.numero.format(tnedh)+".00");
    }

    private void accion(String ACCION) throws ClassNotFoundException, SQLException, Exception
    {        
        try
        {
            if(ACCION.equals("NUEVO"))            
                IDEMPLEADO = 0;
          
            RegEmpleados p = new RegEmpleados(null,true);
            p.mostrar(IDEMPLEADO,ACCION);
            p.setVisible(true);
            
        }
        catch (ParseException ex)
        {
            JOptionPane.showMessageDialog( null, "PARA PODER EDITAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
        }
        
        actualizarTablas(txtESTADO.getSelectedItem().toString());
    }
    
    private void permisos(String PRIVILEGIOS)
    {
        if(PRIVILEGIOS.equals("SADMIN")||PRIVILEGIOS.equals("ADMIN"))
        {
            btnEDITAR.setEnabled(true);
            //btnBAJA.setEnabled(true);
        }
    }
    
    private void nomina() throws SQLException, Exception
    {
        if("ACTIVO".equals(txtESTADO.getSelectedItem().toString()))
        {
            JInternalFrame ji = validador.getJInternalFrame(VRegNomina.class.getName());
            if(ji != null )
                ji.dispose();
            ji = new VRegNomina(IDEMPLEADO, NOMBRE+" "+APELLIDOS, SUELDO, EMPRESA, BONO, BONOP, FECHAALTA);
            Principal.getCONT().add(ji);
            validador.addJIframe(VRegNomina.class.getName(), ji);            
            
            ji.setMaximum(true);
        
            ji.setVisible(true);
            ji.moveToFront();
        }
        else        
            JOptionPane.showMessageDialog( null, "ESTA SELECCIONADO UN EMPLEADO INACTIVO", "VERIFICAR", JOptionPane.WARNING_MESSAGE);                 
    }
    
    private void movimiento(String MOVIMIENTO) throws ParseException, ClassNotFoundException, SQLException, Exception
    {
        switch(MOVIMIENTO)
        {
            case "PRESTAMO":
                RegPrestamos vista;            
                vista = new RegPrestamos(null,true);
                vista.accion(0,IDEMPLEADO,"NUEVO");
                vista.setVisible(true);
                break;
            case "ABONO":
                RegAbonos vistaA;            
                vistaA = new RegAbonos(null,true);
                vistaA.accion(0,IDEMPLEADO,"NUEVO");
                vistaA.setVisible(true);
                break;
            case "AHORRO":
                RegAhorros vistaH;            
                vistaH = new RegAhorros(null,true);
                vistaH.accion(0,IDEMPLEADO,"NUEVO");
                vistaH.setVisible(true);
                break;
            case "VACACION":
                RegVacaciones vistaV;
                vistaV = new RegVacaciones(null,true);
                vistaV.accion(0,IDEMPLEADO,"NUEVO");
                vistaV.setVisible(true);
                break;
            default:
                break;
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

        jmpACCIONES = new javax.swing.JPopupMenu();
        jmmVACACIONES = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmmABONOS = new javax.swing.JMenuItem();
        jmmAHORROS = new javax.swing.JMenuItem();
        jmmPRESTAMOS = new javax.swing.JMenuItem();
        Bar = new javax.swing.JToolBar();
        btnNuevo = new javax.swing.JButton();
        btnEDITAR = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEXCEL = new javax.swing.JButton();
        btnVACACIONES = new javax.swing.JButton();
        btnABONOS = new javax.swing.JButton();
        btnAHORROS = new javax.swing.JButton();
        btnPRESTAMOS = new javax.swing.JButton();
        jspTABLA = new javax.swing.JScrollPane();
        tblTABLA = new javax.swing.JTable();
        txtBUSCAR = new javax.swing.JTextField();
        txtESTADO = new javax.swing.JComboBox<>();
        lblTNEDH = new javax.swing.JLabel();
        lblTNDHN = new javax.swing.JLabel();

        jmmVACACIONES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmVACACIONES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vacaciones1.png"))); // NOI18N
        jmmVACACIONES.setText("Vacaciones y Permisos");
        jmmVACACIONES.setToolTipText("Registro de Vacaciones y permisos especiales");
        jmmVACACIONES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmVACACIONESActionPerformed(evt);
            }
        });
        jmpACCIONES.add(jmmVACACIONES);
        jmpACCIONES.add(jSeparator1);

        jmmABONOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmABONOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/prestamos.png"))); // NOI18N
        jmmABONOS.setText("Abonos");
        jmmABONOS.setToolTipText("Registro de Abonos");
        jmmABONOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmABONOSActionPerformed(evt);
            }
        });
        jmpACCIONES.add(jmmABONOS);

        jmmAHORROS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmAHORROS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ahorro.png"))); // NOI18N
        jmmAHORROS.setText("Ahorros");
        jmmAHORROS.setToolTipText("Registro de ahorros");
        jmmAHORROS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmAHORROSActionPerformed(evt);
            }
        });
        jmpACCIONES.add(jmmAHORROS);

        jmmPRESTAMOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jmmPRESTAMOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/prestamo.png"))); // NOI18N
        jmmPRESTAMOS.setText("Prestamos");
        jmmPRESTAMOS.setToolTipText("Registro de Prestamos");
        jmmPRESTAMOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmmPRESTAMOSActionPerformed(evt);
            }
        });
        jmpACCIONES.add(jmmPRESTAMOS);

        setClosable(true);
        setMaximizable(true);
        setTitle("Empleados");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tecnico.png"))); // NOI18N
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

        btnEDITAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEDITAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/page_edit.png"))); // NOI18N
        btnEDITAR.setText("EDITAR");
        btnEDITAR.setEnabled(false);
        btnEDITAR.setFocusable(false);
        btnEDITAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEDITAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEDITAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEDITARActionPerformed(evt);
            }
        });
        Bar.add(btnEDITAR);

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

        btnVACACIONES.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVACACIONES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vacaciones1.png"))); // NOI18N
        btnVACACIONES.setText("VACACIONES");
        btnVACACIONES.setFocusable(false);
        btnVACACIONES.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVACACIONES.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVACACIONES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVACACIONESActionPerformed(evt);
            }
        });
        Bar.add(btnVACACIONES);

        btnABONOS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnABONOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/prestamos.png"))); // NOI18N
        btnABONOS.setText("ABONOS");
        btnABONOS.setFocusable(false);
        btnABONOS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnABONOS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnABONOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnABONOSActionPerformed(evt);
            }
        });
        Bar.add(btnABONOS);

        btnAHORROS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAHORROS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ahorros.png"))); // NOI18N
        btnAHORROS.setText("AHORROS");
        btnAHORROS.setFocusable(false);
        btnAHORROS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAHORROS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAHORROS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAHORROSActionPerformed(evt);
            }
        });
        Bar.add(btnAHORROS);

        btnPRESTAMOS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPRESTAMOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/prestamo.png"))); // NOI18N
        btnPRESTAMOS.setText("PRESTAMOS");
        btnPRESTAMOS.setFocusable(false);
        btnPRESTAMOS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPRESTAMOS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPRESTAMOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPRESTAMOSActionPerformed(evt);
            }
        });
        Bar.add(btnPRESTAMOS);

        jspTABLA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblTABLA.setAutoCreateRowSorter(true);
        tblTABLA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblTABLA.setComponentPopupMenu(jmpACCIONES);
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

        txtESTADO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtESTADO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        txtESTADO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        txtESTADO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtESTADOActionPerformed(evt);
            }
        });

        lblTNEDH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTNEDH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTNEDH.setText("00000");
        lblTNEDH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EDH", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        lblTNDHN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTNDHN.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTNDHN.setText("00000");
        lblTNDHN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DHN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bar, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            .addComponent(jspTABLA)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtBUSCAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTNDHN, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTNEDH, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtESTADO, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtESTADO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTNEDH))
                        .addComponent(lblTNDHN))
                    .addComponent(txtBUSCAR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTABLA, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            accion("NUEVO");
        } catch (SQLException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEDITARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEDITARActionPerformed
        try {
            seleccion();
            accion("EDITAR");
        } catch (SQLException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEDITARActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {            
            actualizarTablas(txtESTADO.getSelectedItem().toString());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tblTABLAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMouseClicked
        try {
            seleccion();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTABLAMouseClicked

    private void tblTABLAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTABLAMousePressed
        try 
        {
            if(F.clickD(evt, tblTABLA))
                seleccion();
            else
                if(F.dosClick(evt, tblTABLA))
                {
                    seleccion();
                    nomina();
                }
        } 
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTABLAMousePressed

    private void txtBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBUSCARKeyTyped
        F.MAYUS(evt);
        F.buscar(tblTABLA, txtBUSCAR.getText(),null,0);
    }//GEN-LAST:event_txtBUSCARKeyTyped

    private void btnEXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXCELActionPerformed
        F.exportarXLS(tblTABLA,"\\REPORTES\\EMPLEADOS");
    }//GEN-LAST:event_btnEXCELActionPerformed

    private void btnPRESTAMOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPRESTAMOSActionPerformed
        try {
            seleccion();
            movimiento("PRESTAMO");
            actualizarTablas(txtESTADO.getSelectedItem().toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }//GEN-LAST:event_btnPRESTAMOSActionPerformed

    private void btnVACACIONESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVACACIONESActionPerformed
        // TODO add your handling code here:
        try {
            seleccion();
            movimiento("VACACION");
            actualizarTablas(txtESTADO.getSelectedItem().toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_btnVACACIONESActionPerformed

    private void btnABONOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnABONOSActionPerformed
        try {
            seleccion();
            movimiento("ABONO");
            actualizarTablas(txtESTADO.getSelectedItem().toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnABONOSActionPerformed

    private void btnAHORROSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAHORROSActionPerformed
        try {
            seleccion();
            movimiento("AHORRO");
            actualizarTablas(txtESTADO.getSelectedItem().toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAHORROSActionPerformed

    private void txtESTADOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtESTADOActionPerformed
        try {
            actualizarTablas(txtESTADO.getSelectedItem().toString());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtESTADOActionPerformed

    private void jmmABONOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmABONOSActionPerformed
        try {
            movimiento("ABONO");
            actualizarTablas(txtESTADO.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmABONOSActionPerformed

    private void jmmVACACIONESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmVACACIONESActionPerformed
        try {
            movimiento("VACACION");
            actualizarTablas(txtESTADO.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmVACACIONESActionPerformed

    private void jmmAHORROSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmAHORROSActionPerformed
        try {
            movimiento("AHORRO");
            actualizarTablas(txtESTADO.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmAHORROSActionPerformed

    private void jmmPRESTAMOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmmPRESTAMOSActionPerformed
        try {
            movimiento("PRESTAMO");
            actualizarTablas(txtESTADO.getSelectedItem().toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmmPRESTAMOSActionPerformed

    private void tblTABLAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTABLAKeyReleased
        if(F.NAVEGARTABLA(evt))
        {
            try 
            {
                seleccion();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(VProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblTABLAKeyReleased

    private void tblTABLAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTABLAKeyPressed
        if(F.ENTER(evt))
        {
            try {
                seleccion();
                nomina();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            if(F.SHIFT(evt))
            {
                try {
                    seleccion();
                    Rectangle rect = tblTABLA.getCellRect(tblTABLA.getSelectedRow(), 0, true);
                    jmpACCIONES.show(tblTABLA, rect.x, rect.y + rect.height);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_tblTABLAKeyPressed

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
                        Logger.getLogger(VEmpleados.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });    
            }            
        }
    }//GEN-LAST:event_txtBUSCARKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Bar;
    private javax.swing.JButton btnABONOS;
    private javax.swing.JButton btnAHORROS;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEDITAR;
    private javax.swing.JButton btnEXCEL;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnPRESTAMOS;
    private javax.swing.JButton btnVACACIONES;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem jmmABONOS;
    private javax.swing.JMenuItem jmmAHORROS;
    private javax.swing.JMenuItem jmmPRESTAMOS;
    private javax.swing.JMenuItem jmmVACACIONES;
    private javax.swing.JPopupMenu jmpACCIONES;
    private javax.swing.JScrollPane jspTABLA;
    private javax.swing.JLabel lblTNDHN;
    private javax.swing.JLabel lblTNEDH;
    private javax.swing.JTable tblTABLA;
    private javax.swing.JTextField txtBUSCAR;
    private javax.swing.JComboBox<String> txtESTADO;
    // End of variables declaration//GEN-END:variables

}

