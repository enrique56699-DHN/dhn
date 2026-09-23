/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloPrestaciones
{
    private int IDPRESTACION;
    private String CONCEPTO;
    private int PERIODO;
    private String FINI;
    private String FFIN;
    private String ESTADO;

    public ModeloPrestaciones(int PERIODO, String FINI, String FFIN) {
        this.PERIODO = PERIODO;
        this.FINI = FINI;
        this.FFIN = FFIN;
    }    
    
    public ModeloPrestaciones() {
    }

    public ModeloPrestaciones(int IDPRESTACION, String CONCEPTO, int PERIODO, String FINI, String FFIN, String ESTADO) {
        this.IDPRESTACION = IDPRESTACION;
        this.CONCEPTO = CONCEPTO;
        this.PERIODO = PERIODO;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.ESTADO = ESTADO;
    }

    public ModeloPrestaciones(String CONCEPTO, int PERIODO, String FINI, String FFIN, String ESTADO) {
        this.CONCEPTO = CONCEPTO;
        this.PERIODO = PERIODO;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.ESTADO = ESTADO;
    }
    
    public int getIDPRESTACION() {
        return IDPRESTACION;
    }

    public void setIDPRESTACION(int IDPRESTACION) {
        this.IDPRESTACION = IDPRESTACION;
    }

    public String getCONCEPTO() {
        return CONCEPTO;
    }

    public void setCONCEPTO(String CONCEPTO) {
        this.CONCEPTO = CONCEPTO;
    }

    public int getPERIODO() {
        return PERIODO;
    }

    public void setPERIODO(int PERIODO) {
        this.PERIODO = PERIODO;
    }

    public String getFINI() {
        return FINI;
    }

    public void setFINI(String FINI) {
        this.FINI = FINI;
    }

    public String getFFIN() {
        return FFIN;
    }

    public void setFFIN(String FFIN) {
        this.FFIN = FFIN;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
    
}
