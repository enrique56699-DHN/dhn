/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloNominas
{
    private int IDNOMINA;
    private int IDEMPLEADO;
    private String FECHA;
    private int SEMANA;
    private int ANO;
    private float DIAS;
    private double NSUELDO;
    private double BONO;
    private String CONCEPTO;
    private int IDAHORRO;
    private int IDABONO;
    private double TOTAL;
    
    private String NOMBRE;
    private String APELLIDOS;
    private String EMPRESA;
    private double AHORRO;
    private double ABONO;
    private double SUELDO;

    public ModeloNominas(int IDNOMINA, String FECHA, int SEMANA, int ANO, float DIAS, double NSUELDO, double BONO, double TOTAL, String NOMBRE, String APELLIDOS, String EMPRESA, double AHORRO, double ABONO, double SUELDO) {
        this.IDNOMINA = IDNOMINA;
        this.FECHA = FECHA;
        this.SEMANA = SEMANA;
        this.ANO = ANO;
        this.DIAS = DIAS;
        this.NSUELDO = NSUELDO;
        this.BONO = BONO;
        this.TOTAL = TOTAL;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.EMPRESA = EMPRESA;
        this.AHORRO = AHORRO;
        this.ABONO = ABONO;
        this.SUELDO = SUELDO;
    }
        
    public ModeloNominas() {
    }

    public ModeloNominas(int IDNOMINA, int IDEMPLEADO, String FECHA, int SEMANA,int ANO, float DIAS, double NSUELDO, double BONO, String CONCEPTO, int IDAHORRO, int IDABONO, double TOTAL) {
        this.IDNOMINA = IDNOMINA;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.SEMANA = SEMANA;
        this.ANO = ANO;
        this.DIAS = DIAS;
        this.NSUELDO = NSUELDO;
        this.BONO = BONO;
        this.CONCEPTO = CONCEPTO;
        this.IDAHORRO = IDAHORRO;
        this.IDABONO = IDABONO;
        this.TOTAL = TOTAL;
    }

    public ModeloNominas(int IDEMPLEADO, String FECHA, int SEMANA, int ANO, float DIAS, double NSUELDO, double BONO, String CONCEPTO, int IDAHORRO, int IDABONO, double TOTAL) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.SEMANA = SEMANA;
        this.ANO = ANO;
        this.DIAS = DIAS;
        this.NSUELDO = NSUELDO;
        this.BONO = BONO;
        this.CONCEPTO = CONCEPTO;
        this.IDAHORRO = IDAHORRO;
        this.IDABONO = IDABONO;
        this.TOTAL = TOTAL;
    }

    public int getIDNOMINA() {
        return IDNOMINA;
    }

    public void setIDNOMINA(int IDNOMINA) {
        this.IDNOMINA = IDNOMINA;
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

    public int getSEMANA() {
        return SEMANA;
    }

    public void setSEMANA(int SEMANA) {
        this.SEMANA = SEMANA;
    }

    public float getDIAS() {
        return DIAS;
    }

    public void setDIAS(float DIAS) {
        this.DIAS = DIAS;
    }

    public double getNSUELDO() {
        return NSUELDO;
    }

    public void setNSUELDO(double NSUELDO) {
        this.NSUELDO = NSUELDO;
    }

    public double getBONO() {
        return BONO;
    }

    public void setBONO(double BONO) {
        this.BONO = BONO;
    }

    public String getCONCEPTO() {
        return CONCEPTO;
    }

    public void setCONCEPTO(String CONCEPTO) {
        this.CONCEPTO = CONCEPTO;
    }

    public int getIDAHORRO() {
        return IDAHORRO;
    }

    public void setIDAHORRO(int IDAHORRO) {
        this.IDAHORRO = IDAHORRO;
    }

    public int getIDABONO() {
        return IDABONO;
    }

    public void setIDABONO(int IDABONO) {
        this.IDABONO = IDABONO;
    }

    public double getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getEMPRESA() {
        return EMPRESA;
    }

    public void setEMPRESA(String EMPRESA) {
        this.EMPRESA = EMPRESA;
    }

    public double getAHORRO() {
        return AHORRO;
    }

    public void setAHORRO(double AHORRO) {
        this.AHORRO = AHORRO;
    }

    public double getABONO() {
        return ABONO;
    }

    public void setABONO(double ABONO) {
        this.ABONO = ABONO;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }

    public double getSUELDO() {
        return SUELDO;
    }

    public void setSUELDO(double SUELDO) {
        this.SUELDO = SUELDO;
    }

    public int getANO() {
        return ANO;
    }

    public void setANO(int ANO) {
        this.ANO = ANO;
    }    
    

}
