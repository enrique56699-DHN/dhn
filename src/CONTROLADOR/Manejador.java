/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

//import com.sun.rowset.CachedRowSetImpl;

import DHNv2.G;
//import DHNv2.config;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;
/**
 *
 * @author KIKE FRENCH
 */
public class Manejador 
{
    /*private Connection conn = null;
    private Statement St = null;
    private ResultSet Rs = null;
    
    public Manejador() throws ClassNotFoundException, SQLException
    {
        Class.forName(G.driver);
        conn = DriverManager.getConnection(G.url,G.loginDB,G.passwordDB);
    }
    
    public void cerrarConexion() throws SQLException, Exception
    {
        try {
        if (Rs != null) {
            Rs.close();
        }
        if (St != null) {
            St.close();
        }
        if (conn != null) {
            conn.close();
        }
        
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Error cerrando la conexion!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
 
    public int Ejecutar(String sql) throws Exception
    {
        int exito = 0;
        try 
        {
            St = conn.createStatement();
            St.executeUpdate(sql);
            //exito = St.executeUpdate(sql);
            exito = 0;
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog( null, "ERROR AL APLICAR ACTUALIZACION\nREVISE EL CODIGO SQL", "VERIFICAR", JOptionPane.ERROR_MESSAGE);
            exito = 1;
            System.out.println(ex.getSQLState());
        }
        return exito;
        
    }
            
    public ResultSet consultar(String sql) throws Exception
    {
       try 
       {
            //MYSQL PHPMYADMIN
            conn = DriverManager.getConnection(G.url,G.loginDB,G.passwordDB);
            St = conn.createStatement();
            Rs = St.executeQuery(sql);
            //conn.close();
        } 
        catch (SQLException ex)
        {
            //JOptionPane.showMessageDialog(null, "NO EXISTE INFORMACION", "DHN", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(ex.getMessage());
        }
        return Rs;
            
    }*/
    
    private static final Logger LOGGER = Logger.getLogger(Manejador.class.getName());
    
    // Variables de instancia (se mantienen para compatibilidad)
    private Connection conn = null;
    private Statement St = null;
    private ResultSet Rs = null;
    
    // NUEVO: Bandera para saber si la conexión es "compartida" o "propia"
    private boolean conexionCompartida = false;
    
    public Manejador() throws ClassNotFoundException, SQLException {
        Class.forName(G.driver);
        // LAZY INITIALIZATION: No abrir conexión aquí
        // conn = DriverManager.getConnection(G.url, G.loginDB, G.passwordDB);
        //LOGGER.info("Manejador inicializado (conexión lazy)");
    }
    
    // NUEVO: Obtener conexión (con reutilización)
    private Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(G.url, G.loginDB, G.passwordDB);
            conexionCompartida = false;
            LOGGER.fine("Nueva conexión abierta");
        }
        return conn;
    }
    
    // NUEVO: Método para compartir conexión (para evitar múltiples aperturas)
    public void usarConexion(Connection conexionExistente) throws SQLException {
        if (this.conn != null && !this.conn.isClosed()) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error cerrando conexión anterior", e);
            }
        }
        this.conn = conexionExistente;
        this.conexionCompartida = true;
        LOGGER.fine("Usando conexión compartida");
    }
    
    // MÉTODO ORIGINAL (se mantiene igual para compatibilidad)
    public void cerrarConexion() throws SQLException, Exception {
        try {
            if (Rs != null) {
                Rs.close();
                Rs = null;
            }
            if (St != null) {
                St.close();
                St = null;
            }
            if (conn != null && !conexionCompartida) {
                conn.close();
                conn = null;
                LOGGER.fine("Conexión cerrada");
            } else if (conn != null && conexionCompartida) {
                // No cerrar conexión compartida, solo limpiar referencias
                conn = null;
                conexionCompartida = false;
                LOGGER.fine("Referencia a conexión compartida liberada");
            }
        } catch (SQLException sqle) {
            LOGGER.log(Level.SEVERE, "Error cerrando la conexión", sqle);
            throw sqle; // Relanzar para que el llamador sepa
        }
    }
    
    // MÉTODO EJECUTAR MEJORADO (con PreparedStatement)
    public int Ejecutar(String sql) throws Exception {
        // Primero, intentar usar PreparedStatement si es posible
        // Pero manteniendo compatibilidad
        try {
            St = getConnection().createStatement();
            int resultado = St.executeUpdate(sql);
            // Guardar el resultado (pero no cerrar recursos aún)
            return resultado;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "ERROR AL APLICAR ACTUALIZACION: " + sql, ex);
            throw new SQLException("Error en actualización: " + ex.getMessage(), ex);
        }
    }
    
    // NUEVO: Método seguro con PreparedStatement (PARA NUEVO CÓDIGO)
    public int EjecutarSeguro(String sql, Object... parametros) throws SQLException {
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) {
                pstmt.setObject(i + 1, parametros[i]);
            }
            return pstmt.executeUpdate();
        }
    }
    
    // MÉTODO CONSULTAR MEJORADO
    public ResultSet consultar(String sql) throws Exception {
        try {
            // IMPORTANTE: Usar la misma conexión, no crear nueva
            Connection connLocal = getConnection();
            St = connLocal.createStatement();
            Rs = St.executeQuery(sql);
            return Rs;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error en consulta: " + sql, ex);
            throw new SQLException("Error en consulta: " + ex.getMessage(), ex);
        }
    }
    
    // NUEVO: Consulta con PreparedStatement (para nuevo código)
    public ResultSet consultarSeguro(String sql, Object... parametros) throws SQLException {
        PreparedStatement pstmt = getConnection().prepareStatement(sql);
        for (int i = 0; i < parametros.length; i++) {
            pstmt.setObject(i + 1, parametros[i]);
        }
        // Guardar referencia para cierre
        this.St = pstmt;
        this.Rs = pstmt.executeQuery();
        return Rs;
    }
    
    // NUEVO: Limpiar recursos sin cerrar conexión (para reutilizar)
    public void limpiarRecursos() {
        try {
            if (Rs != null && !Rs.isClosed()) {
                Rs.close();
                Rs = null;
            }
            if (St != null && !St.isClosed()) {
                St.close();
                St = null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error limpiando recursos", e);
        }
    }    
    

}

