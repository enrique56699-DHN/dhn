/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author win 10
 */
public class ModeloReportes {
    
    private int IDREPORTE;
    private int IDCONTRATO;
    private String DESCRIPCION;
    private String COMENTARIOS;
    private String SOLUCION;
    private String FINI;
    private String FFIN;
    private String ESTATUS;

    public ModeloReportes() {
    }

    public ModeloReportes(int IDCONTRATO, String DESCRIPCION, String COMENTARIOS, String SOLUCION, String FINI, String FFIN, String ESTATUS) {
        this.IDCONTRATO = IDCONTRATO;
        this.DESCRIPCION = DESCRIPCION;
        this.COMENTARIOS = COMENTARIOS;
        this.SOLUCION = SOLUCION;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.ESTATUS = ESTATUS;
    }

    public ModeloReportes(int IDREPORTE, int IDCONTRATO, String DESCRIPCION, String COMENTARIOS, String SOLUCION, String FINI, String FFIN, String ESTATUS) {
        this.IDREPORTE = IDREPORTE;
        this.IDCONTRATO = IDCONTRATO;
        this.DESCRIPCION = DESCRIPCION;
        this.COMENTARIOS = COMENTARIOS;
        this.SOLUCION = SOLUCION;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.ESTATUS = ESTATUS;
    }

    public int getIDREPORTE() {
        return IDREPORTE;
    }

    public void setIDREPORTE(int IDREPORTE) {
        this.IDREPORTE = IDREPORTE;
    }

    public int getIDCONTRATO() {
        return IDCONTRATO;
    }

    public void setIDCONTRATO(int IDCONTRATO) {
        this.IDCONTRATO = IDCONTRATO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getCOMENTARIOS() {
        return COMENTARIOS;
    }

    public void setCOMENTARIOS(String COMENTARIOS) {
        this.COMENTARIOS = COMENTARIOS;
    }

    public String getSOLUCION() {
        return SOLUCION;
    }

    public void setSOLUCION(String SOLUCION) {
        this.SOLUCION = SOLUCION;
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

    public String getESTATUS() {
        return ESTATUS;
    }

    public void setESTATUS(String ESTATUS) {
        this.ESTATUS = ESTATUS;
    }
    
}
