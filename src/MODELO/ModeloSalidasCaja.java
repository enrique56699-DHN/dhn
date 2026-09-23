/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloSalidasCaja
{    
    private int IDSALIDACAJA;
    private String DESCRIPCION;
    private float MONTO;
    private String EMPRESA;
    private String FECHA;

    public ModeloSalidasCaja() {
    }

    public ModeloSalidasCaja(int IDSALIDACAJA, String DESCRIPCION, float MONTO, String EMPRESA, String FECHA) {
        this.IDSALIDACAJA = IDSALIDACAJA;
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.EMPRESA = EMPRESA;
        this.FECHA = FECHA;
    }

    public ModeloSalidasCaja(String DESCRIPCION, float MONTO, String EMPRESA, String FECHA) {
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.EMPRESA = EMPRESA;
        this.FECHA = FECHA;
    }

    public int getIDSALIDACAJA() {
        return IDSALIDACAJA;
    }

    public void setIDSALIDACAJA(int IDSALIDACAJA) {
        this.IDSALIDACAJA = IDSALIDACAJA;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public float getMONTO() {
        return MONTO;
    }

    public void setMONTO(float MONTO) {
        this.MONTO = MONTO;
    }

    public String getEMPRESA() {
        return EMPRESA;
    }

    public void setEMPRESA(String EMPRESA) {
        this.EMPRESA = EMPRESA;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    
}
