/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloPrestacionesP
{
    private int IDPRESTACIONP;
    private int IDEMPLEADO;
    private int IDPRESTACION;
    private String FECHA;
    private float MONTO;
    private int PERIODO;
    
    private String NOMBRE;
    private String APELLIDOS;
    private String PRESTACION;
    private String EMPRESA;
    private String FINI;
    private String FFIN;

    public ModeloPrestacionesP() {
    }
    
    public ModeloPrestacionesP(int IDPRESTACIONP, String FECHA, float MONTO, int PERIODO, String NOMBRE, String APELLIDOS, String PRESTACION, String EMPRESA, String FINI, String FFIN) {
        this.IDPRESTACIONP = IDPRESTACIONP;
        this.FECHA = FECHA;
        this.MONTO = MONTO;
        this.PERIODO = PERIODO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.PRESTACION = PRESTACION;
        this.EMPRESA = EMPRESA;
        this.FINI = FINI;
        this.FFIN = FFIN;
    } 

    public ModeloPrestacionesP(int IDPRESTACIONP, String FECHA, float MONTO, int PERIODO, String NOMBRE, String APELLIDOS, String PRESTACION, String EMPRESA) {
        this.IDPRESTACIONP = IDPRESTACIONP;
        this.FECHA = FECHA;
        this.MONTO = MONTO;
        this.PERIODO = PERIODO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.PRESTACION = PRESTACION;
        this.EMPRESA = EMPRESA;
    }    
    
    public ModeloPrestacionesP(int IDPRESTACIONP, String FECHA, float MONTO, int PERIODO, String NOMBRE, String APELLIDOS, String PRESTACION) {
        this.IDPRESTACIONP = IDPRESTACIONP;
        this.FECHA = FECHA;
        this.MONTO = MONTO;
        this.PERIODO = PERIODO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.PRESTACION = PRESTACION;
    }    
    
    public ModeloPrestacionesP(int IDPRESTACIONP, int IDEMPLEADO, int IDPRESTACION, String FECHA, float MONTO, int PERIODO) {
        this.IDPRESTACIONP = IDPRESTACIONP;
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDPRESTACION = IDPRESTACION;
        this.FECHA = FECHA;
        this.MONTO = MONTO;
        this.PERIODO = PERIODO;
    }

    public ModeloPrestacionesP(int IDEMPLEADO, int IDPRESTACION, String FECHA, float MONTO, int PERIODO) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDPRESTACION = IDPRESTACION;
        this.FECHA = FECHA;
        this.MONTO = MONTO;
        this.PERIODO = PERIODO;
    }

    public int getIDPRESTACIONP() {
        return IDPRESTACIONP;
    }

    public void setIDPRESTACIONP(int IDPRESTACIONP) {
        this.IDPRESTACIONP = IDPRESTACIONP;
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

    public String getFREG() {
        return FECHA;
    }

    public void setFREG(String FREG) {
        this.FECHA = FREG;
    }

    public float getMONTO() {
        return MONTO;
    }

    public void setMONTO(float MONTO) {
        this.MONTO = MONTO;
    }

    public int getPERIODO() {
        return PERIODO;
    }

    public void setPERIODO(int PERIODO) {
        this.PERIODO = PERIODO;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
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

    public String getPRESTACION() {
        return PRESTACION;
    }

    public void setPRESTACION(String PRESTACION) {
        this.PRESTACION = PRESTACION;
    }

    public String getEMPRESA() {
        return EMPRESA;
    }

    public void setEMPRESA(String EMPRESA) {
        this.EMPRESA = EMPRESA;
    }

    public String getFINI() {
        return FINI;
    }

    public void setFINI(String FINI) {
        this.FINI = FINI;
    }

    public String getFFIN() {
        return FFIN;
    }

    public void setFFIN(String FFIN) {
        this.FFIN = FFIN;
    }
    
    
}
