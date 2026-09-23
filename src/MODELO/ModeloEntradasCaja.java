/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloEntradasCaja
{
    private int IDENTRADACAJA;
    private String DESCRIPCION;
    private float MONTO;
    private String EMPRESA;
    private String FECHA;

    public ModeloEntradasCaja() {
    }

    public ModeloEntradasCaja(int IDENTRADACAJA, String DESCRIPCION, float MONTO, String EMPRESA, String FECHA) {
        this.IDENTRADACAJA = IDENTRADACAJA;
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.EMPRESA = EMPRESA;
        this.FECHA = FECHA;
    }

    public ModeloEntradasCaja(String DESCRIPCION, float MONTO, String EMPRESA, String FECHA) {
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.EMPRESA = EMPRESA;
        this.FECHA = FECHA;
    }

    public int getIDENTRADACAJA() {
        return IDENTRADACAJA;
    }

    public void setIDENTRADACAJA(int IDENTRADACAJA) {
        this.IDENTRADACAJA = IDENTRADACAJA;
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
