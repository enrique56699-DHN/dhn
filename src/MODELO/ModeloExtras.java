/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author win 10
 */
public class ModeloExtras 
{
    private int IDEXTRA;
    private String FECHA;
    private String DESCRIPCION;
    private String TIPO;
    private float MONTO;

    public ModeloExtras() {
    }

    public ModeloExtras(int IDEXTRA, String FECHA, String DESCRIPCION, String TIPO, float MONTO) {
        this.IDEXTRA = IDEXTRA;
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.TIPO = TIPO;
        this.MONTO = MONTO;
    }

    public ModeloExtras(String FECHA, String DESCRIPCION, String TIPO, float MONTO) {
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.TIPO = TIPO;
        this.MONTO = MONTO;
    }

    public int getIDEXTRA() {
        return IDEXTRA;
    }

    public void setIDEXTRA(int IDEXTRA) {
        this.IDEXTRA = IDEXTRA;
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
    
}
