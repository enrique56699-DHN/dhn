/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author win 10
 */
public class ModeloCortesGlobal 
{
    private int IDCORTEG;
    private float DHN;
    private float EDH;
    private float VALES;
    private float CF;
    private String FECHA;
    private float MONTOG;
    private float MONTOR;

    public ModeloCortesGlobal() {
    }

    public ModeloCortesGlobal(int IDCORTEG, float DHN, float EDH, float VALES, float CF, String FECHA, float MONTOG, float MONTOR) {
        this.IDCORTEG = IDCORTEG;
        this.DHN = DHN;
        this.EDH = EDH;
        this.VALES = VALES;
        this.CF = CF;
        this.FECHA = FECHA;
        this.MONTOG = MONTOG;
        this.MONTOR = MONTOR;
    }

    public ModeloCortesGlobal(float DHN, float EDH, float VALES, float CF, String FECHA, float MONTOG, float MONTOR) {
        this.DHN = DHN;
        this.EDH = EDH;
        this.VALES = VALES;
        this.CF = CF;
        this.FECHA = FECHA;
        this.MONTOG = MONTOG;
        this.MONTOR = MONTOR;
    }

    public int getIDCORTEG() {
        return IDCORTEG;
    }

    public void setIDCORTEG(int IDCORTEG) {
        this.IDCORTEG = IDCORTEG;
    }

    public float getDHN() {
        return DHN;
    }

    public void setDHN(float DHN) {
        this.DHN = DHN;
    }

    public float getEDH() {
        return EDH;
    }

    public void setEDH(float EDH) {
        this.EDH = EDH;
    }

    public float getVALES() {
        return VALES;
    }

    public void setVALES(float VALES) {
        this.VALES = VALES;
    }

    public float getCF() {
        return CF;
    }

    public void setCF(float CF) {
        this.CF = CF;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public float getMONTOG() {
        return MONTOG;
    }

    public void setMONTOG(float MONTOG) {
        this.MONTOG = MONTOG;
    }

    public float getMONTOR() {
        return MONTOR;
    }

    public void setMONTOR(float MONTOR) {
        this.MONTOR = MONTOR;
    }
}
