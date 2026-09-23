/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author win 10
 */
public class ModeloCF 
{
    private int IDCF;
    private String FECHA;
    private String TIPO;
    private float MONTO;

    public ModeloCF() {
    }

    public ModeloCF(int IDCF, String FECHA, String TIPO, float MONTO) {
        this.IDCF = IDCF;
        this.FECHA = FECHA;
        this.TIPO = TIPO;
        this.MONTO = MONTO;
    }

    public ModeloCF(String FECHA, String TIPO, float MONTO) {
        this.FECHA = FECHA;
        this.TIPO = TIPO;
        this.MONTO = MONTO;
    }

    public int getIDCF() {
        return IDCF;
    }

    public void setIDCF(int IDCF) {
        this.IDCF = IDCF;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
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
