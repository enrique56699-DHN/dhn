/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloBonosD
{
    private int IDBONOD;
    private int IDEMPLEADO;
    private int IDPRESTACION;
    private String EMPRESA;
    private float MONTO;
    private String FECHA;
    
    private String NOMBRE;
    private int PERIODO;
    private String PRESTACION;
    private String APELLIDOS;
    
    public ModeloBonosD() {
    }

    public ModeloBonosD(int IDBONOD, int IDEMPLEADO, int IDPRESTACION, String EMPRESA, float MONTO, String FECHA) {
        this.IDBONOD = IDBONOD;
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDPRESTACION = IDPRESTACION;
        this.EMPRESA = EMPRESA;
        this.MONTO = MONTO;
        this.FECHA = FECHA;
    }

    public ModeloBonosD(int IDBONOD, int IDEMPLEADO, int IDPRESTACION, String EMPRESA, float MONTO, String FECHA, String NOMBRE, int PERIODO, String PRESTACION, String APELLIDOS) {
        this.IDBONOD = IDBONOD;
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDPRESTACION = IDPRESTACION;
        this.EMPRESA = EMPRESA;
        this.MONTO = MONTO;
        this.FECHA = FECHA;
        this.NOMBRE = NOMBRE;
        this.PERIODO = PERIODO;
        this.PRESTACION = PRESTACION;
        this.APELLIDOS = APELLIDOS;
    }

    public ModeloBonosD(int IDEMPLEADO, int IDPRESTACION, String EMPRESA, float MONTO, String FECHA, String NOMBRE, int PERIODO, String PRESTACION, String APELLIDOS) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDPRESTACION = IDPRESTACION;
        this.EMPRESA = EMPRESA;
        this.MONTO = MONTO;
        this.FECHA = FECHA;
        this.NOMBRE = NOMBRE;
        this.PERIODO = PERIODO;
        this.PRESTACION = PRESTACION;
        this.APELLIDOS = APELLIDOS;
    }

    public ModeloBonosD(String EMPRESA, float MONTO, String FECHA, String NOMBRE, int PERIODO, String PRESTACION, String APELLIDOS) {
        this.EMPRESA = EMPRESA;
        this.MONTO = MONTO;
        this.FECHA = FECHA;
        this.NOMBRE = NOMBRE;
        this.PERIODO = PERIODO;
        this.PRESTACION = PRESTACION;
        this.APELLIDOS = APELLIDOS;
    }
    
    public int getIDBONOD() {
        return IDBONOD;
    }

    public void setIDBONOD(int IDBONOD) {
        this.IDBONOD = IDBONOD;
    }

    public int getIDEMPLEADO() {
        return IDEMPLEADO;
    }

    public void setIDEMPLEADO(int IDEMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
    }

    public int getIDPRESTACION() {
        return IDPRESTACION;
    }

    public void setIDPRESTACION(int IDPRESTACION) {
        this.IDPRESTACION = IDPRESTACION;
    }

    public String getEMPRESA() {
        return EMPRESA;
    }

    public void setEMPRESA(String EMPRESA) {
        this.EMPRESA = EMPRESA;
    }

    public float getMONTO() {
        return MONTO;
    }

    public void setMONTO(float MONTO) {
        this.MONTO = MONTO;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public int getPERIODO() {
        return PERIODO;
    }

    public void setPERIODO(int PERIODO) {
        this.PERIODO = PERIODO;
    }

    public String getPRESTACION() {
        return PRESTACION;
    }

    public void setPRESTACION(String PRESTACION) {
        this.PRESTACION = PRESTACION;
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
    
    
    
}
