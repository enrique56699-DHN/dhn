/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author win 10
 */
public class ModeloVales 
{
    private int IDVALE;
    private String FECHA;
    private String DESCRIPCION;
    private String TIPO;
    private float MONTO;
    private boolean ESTADO;

    public ModeloVales() {
    }

    public ModeloVales(int IDVALE, String FECHA, String DESCRIPCION, String TIPO, float MONTO, boolean ESTADO) {
        this.IDVALE = IDVALE;
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.TIPO = TIPO;
        this.MONTO = MONTO;
        this.ESTADO = ESTADO;
    }

    public ModeloVales(String FECHA, String DESCRIPCION, String TIPO, float MONTO, boolean ESTADO) {
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.TIPO = TIPO;
        this.MONTO = MONTO;
        this.ESTADO = ESTADO;
    }
    
    

    public ModeloVales(int IDVALE, String FECHA, String DESCRIPCION, String TIPO, float MONTO) {
        this.IDVALE = IDVALE;
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.TIPO = TIPO;
        this.MONTO = MONTO;
    }

    public ModeloVales(String FECHA, String DESCRIPCION, String TIPO, float MONTO) {
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.TIPO = TIPO;
        this.MONTO = MONTO;
    }

    public int getIDVALE() {
        return IDVALE;
    }

    public void setIDVALE(int IDVALE) {
        this.IDVALE = IDVALE;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public float getMONTO() {
        return MONTO;
    }

    public void setMONTO(float MONTO) {
        this.MONTO = MONTO;
    }

    public boolean getESTADO() {
        return ESTADO;
    }

    public void setESTADO(boolean ESTADO) {
        this.ESTADO = ESTADO;
    }
    
    
}
