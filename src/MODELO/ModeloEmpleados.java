/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloEmpleados
{
    private int IDEMPLEADO;
    private String NOMBRE;
    private String APELLIDOS;
    private String TEL;
    private float SUELDO;
    private String EMPRESA;
    private float BONO;
    private float BONOP;
    private String FECHA;
    private String ESTADO;
    private int IDEPUESTO;
    private String DPTO;
    private String PUESTO;

    public ModeloEmpleados(int IDEMPLEADO, String NOMBRE, String APELLIDOS, String TEL, float SUELDO, String EMPRESA, float BONO, float BONOP, String FECHA, String DPTO, String PUESTO) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.TEL = TEL;
        this.SUELDO = SUELDO;
        this.EMPRESA = EMPRESA;
        this.BONO = BONO;
        this.BONOP = BONOP;
        this.FECHA = FECHA;
        this.DPTO = DPTO;
        this.PUESTO = PUESTO;
    }

    public ModeloEmpleados(int IDEMPLEADO, String NOMBRE, String APELLIDOS, String TEL, float SUELDO, String EMPRESA, float BONO, float BONOP, String FECHA, String ESTADO, int IDEPUESTO) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.TEL = TEL;
        this.SUELDO = SUELDO;
        this.EMPRESA = EMPRESA;
        this.BONO = BONO;
        this.BONOP = BONOP;
        this.FECHA = FECHA;
        this.ESTADO = ESTADO;
        this.IDEPUESTO = IDEPUESTO;
    }

    public ModeloEmpleados(String NOMBRE, String APELLIDOS, String TEL, float SUELDO, String EMPRESA, float BONO, float BONOP, String FECHA, String ESTADO, int IDEPUESTO) {
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.TEL = TEL;
        this.SUELDO = SUELDO;
        this.EMPRESA = EMPRESA;
        this.BONO = BONO;
        this.BONOP = BONOP;
        this.FECHA = FECHA;
        this.ESTADO = ESTADO;
        this.IDEPUESTO = IDEPUESTO;
    }

    public ModeloEmpleados(int IDEMPLEADO, String NOMBRE, String APELLIDOS, String TEL, float SUELDO, String EMPRESA, float BONO,float BONOP, String FECHA, String ESTADO) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.TEL = TEL;
        this.SUELDO = SUELDO;
        this.EMPRESA = EMPRESA;
        this.BONO = BONO;
        this.BONOP = BONOP;
        this.FECHA = FECHA;
        this.ESTADO = ESTADO;
    }

    public ModeloEmpleados(String NOMBRE, String APELLIDOS, String TEL, float SUELDO, String EMPRESA, float BONO,float BONOP, String FECHA, String ESTADO) {
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.TEL = TEL;
        this.SUELDO = SUELDO;
        this.EMPRESA = EMPRESA;
        this.BONO = BONO;
        this.BONOP = BONOP;
        this.BONOP = BONOP;
        this.FECHA = FECHA;
        this.ESTADO = ESTADO;
    }
    
    public ModeloEmpleados() {
    }

    public ModeloEmpleados(int IDEMPLEADO, String NOMBRE, String APELLIDOS, String TEL) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.TEL = TEL;
    }

    public ModeloEmpleados(String NOMBRE, String APELLIDOS, String TEL) {
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.TEL = TEL;
    }

    public int getIDEMPLEADO() {
        return IDEMPLEADO;
    }

    public void setIDEMPLEADO(int IDEMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
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

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public float getSUELDO() {
        return SUELDO;
    }

    public void setSUELDO(float SUELDO) {
        this.SUELDO = SUELDO;
    }

    public String getEMPRESA() {
        return EMPRESA;
    }

    public void setEMPRESA(String EMPRESA) {
        this.EMPRESA = EMPRESA;
    }

    public float getBONO() {
        return BONO;
    }

    public void setBONO(float BONO) {
        this.BONO = BONO;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public float getBONOP() {
        return BONOP;
    }

    public void setBONOP(float BONOP) {
        this.BONOP = BONOP;
    }

    public int getIDEPUESTO() {
        return IDEPUESTO;
    }

    public void setIDEPUESTO(int IDEPUESTO) {
        this.IDEPUESTO = IDEPUESTO;
    }

    public String getDPTO() {
        return DPTO;
    }

    public void setDPTO(String DPTO) {
        this.DPTO = DPTO;
    }

    public String getPUESTO() {
        return PUESTO;
    }

    public void setPUESTO(String PUESTO) {
        this.PUESTO = PUESTO;
    }
    
    
}
