/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloVehiculosSER
{
    private int IDVEHICULOSER;
    private int IDVEHICULO;
    private String TIPO;
    private String COMENTARIOS;
    private String FECHA;
    private int KM;
    private String EMPRESA;
    private float COSTO;
    
    private String MARCA;
    private String MODELO;

    public ModeloVehiculosSER() {
    }

    public ModeloVehiculosSER(int IDVEHICULOSER, int IDVEHICULO, String TIPO, String COMENTARIOS, String FECHA, int KM, String EMPRESA, float COSTO, String MARCA, String MODELO) {
        this.IDVEHICULOSER = IDVEHICULOSER;
        this.IDVEHICULO = IDVEHICULO;
        this.TIPO = TIPO;
        this.COMENTARIOS = COMENTARIOS;
        this.FECHA = FECHA;
        this.KM = KM;
        this.EMPRESA = EMPRESA;
        this.COSTO = COSTO;
        this.MARCA = MARCA;
        this.MODELO = MODELO;
    }

    public ModeloVehiculosSER(int IDVEHICULO, String TIPO, String COMENTARIOS, String FECHA, int KM, String EMPRESA, float COSTO, String MARCA, String MODELO) {
        this.IDVEHICULO = IDVEHICULO;
        this.TIPO = TIPO;
        this.COMENTARIOS = COMENTARIOS;
        this.FECHA = FECHA;
        this.KM = KM;
        this.EMPRESA = EMPRESA;
        this.COSTO = COSTO;
        this.MARCA = MARCA;
        this.MODELO = MODELO;
    }

    

    public ModeloVehiculosSER(int IDVEHICULOSER, int IDVEHICULO, String TIPO, String COMENTARIOS, String FECHA, int KM) {
        this.IDVEHICULOSER = IDVEHICULOSER;
        this.IDVEHICULO = IDVEHICULO;
        this.TIPO = TIPO;
        this.COMENTARIOS = COMENTARIOS;
        this.FECHA = FECHA;
        this.KM = KM;
    }

    public ModeloVehiculosSER(int IDVEHICULO, String TIPO, String COMENTARIOS, String FECHA, int KM) {
        this.IDVEHICULO = IDVEHICULO;
        this.TIPO = TIPO;
        this.COMENTARIOS = COMENTARIOS;
        this.FECHA = FECHA;
        this.KM = KM;
    }

    

    public int getIDVEHICULOSER() {
        return IDVEHICULOSER;
    }

    public void setIDVEHICULOSER(int IDVEHICULOSER) {
        this.IDVEHICULOSER = IDVEHICULOSER;
    }

    public int getIDVEHICULO() {
        return IDVEHICULO;
    }

    public void setIDVEHICULO(int IDVEHICULO) {
        this.IDVEHICULO = IDVEHICULO;
    }

    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public String getCOMENTARIOS() {
        return COMENTARIOS;
    }

    public void setCOMENTARIOS(String COMENTARIOS) {
        this.COMENTARIOS = COMENTARIOS;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public int getKM() {
        return KM;
    }

    public void setKM(int KM) {
        this.KM = KM;
    }

    public String getEMPRESA() {
        return EMPRESA;
    }

    public void setEMPRESA(String EMPRESA) {
        this.EMPRESA = EMPRESA;
    }

    public float getCOSTO() {
        return COSTO;
    }

    public void setCOSTO(float COSTO) {
        this.COSTO = COSTO;
    }

    public String getMARCA() {
        return MARCA;
    }

    public void setMARCA(String MARCA) {
        this.MARCA = MARCA;
    }

    public String getMODELO() {
        return MODELO;
    }

    public void setMODELO(String MODELO) {
        this.MODELO = MODELO;
    }

    
    
    
}
