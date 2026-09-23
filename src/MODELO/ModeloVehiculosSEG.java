/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloVehiculosSEG
{
    private int IDVEHICULOSEG;
    private int IDVEHICULO;
    private String ASEGURADORA;
    private String NP;
    private String FECHA;
    private String VIGENCIA;
    private String ARCHIVO;
    private String PROPIETARIO;
    private float COSTO;
    private String EMPRESA;

    private String MARCA;
    private String MODELO;
    
    public ModeloVehiculosSEG() {
    }

    public ModeloVehiculosSEG(int IDVEHICULOSEG, int IDVEHICULO, String ASEGURADORA, String NP, String FECHA, String VIGENCIA, String ARCHIVO, String PROPIETARIO, float COSTO) {
        this.IDVEHICULOSEG = IDVEHICULOSEG;
        this.IDVEHICULO = IDVEHICULO;
        this.ASEGURADORA = ASEGURADORA;
        this.NP = NP;
        this.FECHA = FECHA;
        this.VIGENCIA = VIGENCIA;
        this.ARCHIVO = ARCHIVO;
        this.PROPIETARIO = PROPIETARIO;
        this.COSTO = COSTO;
    }

    public ModeloVehiculosSEG(int IDVEHICULO, String ASEGURADORA, String NP, String FECHA, String VIGENCIA, String ARCHIVO, String PROPIETARIO, float COSTO) {
        this.IDVEHICULO = IDVEHICULO;
        this.ASEGURADORA = ASEGURADORA;
        this.NP = NP;
        this.FECHA = FECHA;
        this.VIGENCIA = VIGENCIA;
        this.ARCHIVO = ARCHIVO;
        this.PROPIETARIO = PROPIETARIO;
        this.COSTO = COSTO;
    }

    public ModeloVehiculosSEG(int IDVEHICULOSEG, int IDVEHICULO, String ASEGURADORA, String NP, String FECHA, String VIGENCIA, String ARCHIVO, String PROPIETARIO, float COSTO, String MARCA, String MODELO) {
        this.IDVEHICULOSEG = IDVEHICULOSEG;
        this.IDVEHICULO = IDVEHICULO;
        this.ASEGURADORA = ASEGURADORA;
        this.NP = NP;
        this.FECHA = FECHA;
        this.VIGENCIA = VIGENCIA;
        this.ARCHIVO = ARCHIVO;
        this.PROPIETARIO = PROPIETARIO;
        this.COSTO = COSTO;
        this.MARCA = MARCA;
        this.MODELO = MODELO;
    }

    public ModeloVehiculosSEG(String ASEGURADORA, String NP, String FECHA, String VIGENCIA, String ARCHIVO, String PROPIETARIO, float COSTO) {
        this.ASEGURADORA = ASEGURADORA;
        this.NP = NP;
        this.FECHA = FECHA;
        this.VIGENCIA = VIGENCIA;
        this.ARCHIVO = ARCHIVO;
        this.PROPIETARIO = PROPIETARIO;
        this.COSTO = COSTO;
    }

    public ModeloVehiculosSEG(int IDVEHICULO, String ASEGURADORA, String NP, String FECHA, String VIGENCIA, String ARCHIVO, String PROPIETARIO, float COSTO, String MARCA, String MODELO) {
        this.IDVEHICULO = IDVEHICULO;
        this.ASEGURADORA = ASEGURADORA;
        this.NP = NP;
        this.FECHA = FECHA;
        this.VIGENCIA = VIGENCIA;
        this.ARCHIVO = ARCHIVO;
        this.PROPIETARIO = PROPIETARIO;
        this.COSTO = COSTO;
        this.MARCA = MARCA;
        this.MODELO = MODELO;
    }

    public ModeloVehiculosSEG(String ASEGURADORA, String NP, String FECHA, String VIGENCIA, String ARCHIVO, String PROPIETARIO, float COSTO, String MARCA, String MODELO) {
        this.ASEGURADORA = ASEGURADORA;
        this.NP = NP;
        this.FECHA = FECHA;
        this.VIGENCIA = VIGENCIA;
        this.ARCHIVO = ARCHIVO;
        this.PROPIETARIO = PROPIETARIO;
        this.COSTO = COSTO;
        this.MARCA = MARCA;
        this.MODELO = MODELO;
    }

    public int getIDVEHICULOSEG() {
        return IDVEHICULOSEG;
    }

    public void setIDVEHICULOSEG(int IDVEHICULOSEG) {
        this.IDVEHICULOSEG = IDVEHICULOSEG;
    }

    public int getIDVEHICULO() {
        return IDVEHICULO;
    }

    public void setIDVEHICULO(int IDVEHICULO) {
        this.IDVEHICULO = IDVEHICULO;
    }

    public String getASEGURADORA() {
        return ASEGURADORA;
    }

    public void setASEGURADORA(String ASEGURADORA) {
        this.ASEGURADORA = ASEGURADORA;
    }

    public String getNP() {
        return NP;
    }

    public void setNP(String NP) {
        this.NP = NP;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getVIGENCIA() {
        return VIGENCIA;
    }

    public void setVIGENCIA(String VIGENCIA) {
        this.VIGENCIA = VIGENCIA;
    }

    public String getARCHIVO() {
        return ARCHIVO;
    }

    public void setARCHIVO(String ARCHIVO) {
        this.ARCHIVO = ARCHIVO;
    }

    public String getPROPIETARIO() {
        return PROPIETARIO;
    }

    public void setPROPIETARIO(String PROPIETARIO) {
        this.PROPIETARIO = PROPIETARIO;
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

    public String getEMPRESA() {
        return EMPRESA;
    }

    public void setEMPRESA(String EMPRESA) {
        this.EMPRESA = EMPRESA;
    }
    
    
}
