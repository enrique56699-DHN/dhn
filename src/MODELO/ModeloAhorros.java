/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloAhorros
{
    private int IDAHORRO;
    private int IDEMPLEADO;
    private String FECHA;
    private float MONTO;
    private int PERIODO;
    
    private String NOMBRE;
    private String APELLIDOS;

    
    public ModeloAhorros() {
    }

    public ModeloAhorros(int IDAHORRO, int IDEMPLEADO, String FECHA, float MONTO, int PERIODO) {
        this.IDAHORRO = IDAHORRO;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.MONTO = MONTO;
        this.PERIODO = PERIODO;
    }

    public ModeloAhorros(int IDEMPLEADO, String FECHA, float MONTO, int PERIODO) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.MONTO = MONTO;
        this.PERIODO = PERIODO;
    }

    public ModeloAhorros(int IDAHORRO, int IDEMPLEADO, String FECHA, float MONTO, String NOMBRE, String APELLIDOS, int PERIODO) {
        this.IDAHORRO = IDAHORRO;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.MONTO = MONTO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.PERIODO = PERIODO;
    }

    public ModeloAhorros(int IDEMPLEADO, String FECHA, float MONTO, String NOMBRE, String APELLIDOS, int PERIODO) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.MONTO = MONTO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.PERIODO = PERIODO;
    }

    public int getIDAHORRO() {
        return IDAHORRO;
    }

    public void setIDAHORRO(int IDAHORRO) {
        this.IDAHORRO = IDAHORRO;
    }

    public int getIDEMPLEADO() {
        return IDEMPLEADO;
    }

    public void setIDEMPLEADO(int IDEMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public float getMONTO() {
        return MONTO;
    }

    public void setMONTO(float MONTO) {
        this.MONTO = MONTO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }

    public int getPERIODO() {
        return PERIODO;
    }

    public void setPERIODO(int PERIODO) {
        this.PERIODO = PERIODO;
    }
    
    
}
