/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloVehiculos
{
    private int IDVEHICULO;
    private String MARCA;
    private String MODELO;
    private String NS;
    private String NMOTOR; 
    private String PLACA;
    private String EMPRESA;

    public ModeloVehiculos() {
    }

    public ModeloVehiculos(int IDVEHICULO, String MARCA, String MODELO, String NS, String NMOTOR, String PLACA, String EMPRESA) {
        this.IDVEHICULO = IDVEHICULO;
        this.MARCA = MARCA;
        this.MODELO = MODELO;
        this.NS = NS;
        this.NMOTOR = NMOTOR;
        this.PLACA = PLACA;
        this.EMPRESA = EMPRESA;
    }

    public ModeloVehiculos(String MARCA, String MODELO, String NS, String NMOTOR, String PLACA, String EMPRESA) {
        this.MARCA = MARCA;
        this.MODELO = MODELO;
        this.NS = NS;
        this.NMOTOR = NMOTOR;
        this.PLACA = PLACA;
        this.EMPRESA = EMPRESA;
    }
   
    public int getIDVEHICULO() {
        return IDVEHICULO;
    }

    public void setIDVEHICULO(int IDVEHICULO) {
        this.IDVEHICULO = IDVEHICULO;
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

    public String getPLACA() {
        return PLACA;
    }

    public void setPLACA(String PLACA) {
        this.PLACA = PLACA;
    }

    public String getEMPRESA() {
        return EMPRESA;
    }

    public void setEMPRESA(String EMPRESA) {
        this.EMPRESA = EMPRESA;
    }

    public String getNS() {
        return NS;
    }

    public void setNS(String NS) {
        this.NS = NS;
    }

    public String getNMOTOR() {
        return NMOTOR;
    }

    public void setNMOTOR(String NMOTOR) {
        this.NMOTOR = NMOTOR;
    }
    
    
}
