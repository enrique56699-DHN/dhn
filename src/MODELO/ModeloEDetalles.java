/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloEDetalles
{
    private int IDEDETALLE;
    private int IDEMPLEADO;
    private String PINI;
    private String PFIN;
    private boolean VPAGADAS;
    private boolean APAGADOS;
    private int PERIODOV;
    
    private String NOMBRE;
    private String APPELLIDOS;

    public ModeloEDetalles() {
    }

    public ModeloEDetalles(int IDEDETALLE, int IDEMPLEADO, String PINI, String PFIN, boolean VPAGADAS, boolean APAGADOS, int PERIODOV, String NOMBRE, String APPELLIDOS) {
        this.IDEDETALLE = IDEDETALLE;
        this.IDEMPLEADO = IDEMPLEADO;
        this.PINI = PINI;
        this.PFIN = PFIN;
        this.VPAGADAS = VPAGADAS;
        this.APAGADOS = APAGADOS;
        this.PERIODOV = PERIODOV;
        this.NOMBRE = NOMBRE;
        this.APPELLIDOS = APPELLIDOS;
    }

    public ModeloEDetalles(int IDEDETALLE, int IDEMPLEADO, String PINI, String PFIN, boolean VPAGADAS, boolean APAGADOS, int PERIODOV) {
        this.IDEDETALLE = IDEDETALLE;
        this.IDEMPLEADO = IDEMPLEADO;
        this.PINI = PINI;
        this.PFIN = PFIN;
        this.VPAGADAS = VPAGADAS;
        this.APAGADOS = APAGADOS;
        this.PERIODOV = PERIODOV;
    }

    public ModeloEDetalles(int IDEMPLEADO, String PINI, String PFIN, boolean VPAGADAS, boolean APAGADOS, int PERIODOV) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.PINI = PINI;
        this.PFIN = PFIN;
        this.VPAGADAS = VPAGADAS;
        this.APAGADOS = APAGADOS;
        this.PERIODOV = PERIODOV;
    }

    public ModeloEDetalles(int PERIODOV) {
        this.PERIODOV = PERIODOV;
    }

    public ModeloEDetalles(int IDEDETALLE, int IDEMPLEADO, String PINI, String PFIN, boolean VPAGADAS, boolean APAGADOS, String NOMBRE, String APPELLIDOS) {
        this.IDEDETALLE = IDEDETALLE;
        this.IDEMPLEADO = IDEMPLEADO;
        this.PINI = PINI;
        this.PFIN = PFIN;
        this.VPAGADAS = VPAGADAS;
        this.APAGADOS = APAGADOS;
        this.NOMBRE = NOMBRE;
        this.APPELLIDOS = APPELLIDOS;
    }

    public ModeloEDetalles(int IDEDETALLE, String PINI, String PFIN, boolean VPAGADAS, boolean APAGADOS, String NOMBRE, String APPELLIDOS) {
        this.IDEDETALLE = IDEDETALLE;
        this.PINI = PINI;
        this.PFIN = PFIN;
        this.VPAGADAS = VPAGADAS;
        this.APAGADOS = APAGADOS;
        this.NOMBRE = NOMBRE;
        this.APPELLIDOS = APPELLIDOS;
    }

    
    public int getIDEDETALLE() {
        return IDEDETALLE;
    }

    public void setIDEDETALLE(int IDEDETALLE) {
        this.IDEDETALLE = IDEDETALLE;
    }

    public int getIDEMPLEADO() {
        return IDEMPLEADO;
    }

    public void setIDEMPLEADO(int IDEMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
    }

    public String getPINI() {
        return PINI;
    }

    public void setPINI(String PINI) {
        this.PINI = PINI;
    }

    public String getPFIN() {
        return PFIN;
    }

    public void setPFIN(String PFIN) {
        this.PFIN = PFIN;
    }

    public boolean getVPAGADAS() {
        return VPAGADAS;
    }

    public void setVPAGADAS(boolean VPAGADAS) {
        this.VPAGADAS = VPAGADAS;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPPELLIDOS() {
        return APPELLIDOS;
    }

    public void setAPPELLIDOS(String APPELLIDOS) {
        this.APPELLIDOS = APPELLIDOS;
    }

    public boolean getAPAGADOS() {
        return APAGADOS;
    }

    public void setAPAGADOS(boolean APAGADOS) {
        this.APAGADOS = APAGADOS;
    }

    public int getPERIODOV() {
        return PERIODOV;
    }

    public void setPERIODOV(int PERIODOV) {
        this.PERIODOV = PERIODOV;
    }
    
    
}
