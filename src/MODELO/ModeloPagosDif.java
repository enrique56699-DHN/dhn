/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloPagosDif 
{
    private int IDPAGODIF;
    private int IDNOTA;
    private String METODO;
    private float MONTO;
    private String DOC;
    private String REF;
    private boolean ESTADO;

    public ModeloPagosDif(int IDPAGODIF, int IDNOTA, String METODO, float MONTO, String DOC, String REF, boolean ESTADO) {
        this.IDPAGODIF = IDPAGODIF;
        this.IDNOTA = IDNOTA;
        this.METODO = METODO;
        this.MONTO = MONTO;
        this.DOC = DOC;
        this.REF = REF;
        this.ESTADO = ESTADO;
    }

    public ModeloPagosDif(int IDNOTA, String METODO, float MONTO, String DOC, String REF, boolean ESTADO) {
        this.IDNOTA = IDNOTA;
        this.METODO = METODO;
        this.MONTO = MONTO;
        this.DOC = DOC;
        this.REF = REF;
        this.ESTADO = ESTADO;
    }

    public int getIDPAGODIF() {
        return IDPAGODIF;
    }

    public void setIDPAGODIF(int IDPAGODIF) {
        this.IDPAGODIF = IDPAGODIF;
    }

    public int getIDNOTA() {
        return IDNOTA;
    }

    public void setIDNOTA(int IDNOTA) {
        this.IDNOTA = IDNOTA;
    }

    public String getMETODO() {
        return METODO;
    }

    public void setMETODO(String METODO) {
        this.METODO = METODO;
    }

    public float getMONTO() {
        return MONTO;
    }

    public void setMONTO(float MONTO) {
        this.MONTO = MONTO;
    }

    public String getDOC() {
        return DOC;
    }

    public void setDOC(String DOC) {
        this.DOC = DOC;
    }

    public String getREF() {
        return REF;
    }

    public void setREF(String REF) {
        this.REF = REF;
    }

    public boolean isESTADO() {
        return ESTADO;
    }

    public void setESTADO(boolean ESTADO) {
        this.ESTADO = ESTADO;
    }
    
    
    
    
}
