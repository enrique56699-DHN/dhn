/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloCortes 
{   
    private int IDCORTE;
    private float MONTOINI;
    private String FINI;
    private String FFIN;
    private float MONTOFIN;
    private int IDCAJA;
    
    private int IDPAGOI;
    private int IDPAGOF;
    private int IDENTRADAI;
    private int IDENTRADAF;
    private int IDSALIDAI;
    private int IDSALIDAF;

    public ModeloCortes(int IDCORTE, float MONTOINI, String FINI, String FFIN, float MONTOFIN, int IDPAGOI, int IDPAGOF, int IDENTRADAI, int IDENTRADAF, int IDSALIDAI, int IDSALIDAF, int IDCAJA) {
        this.IDCORTE = IDCORTE;
        this.MONTOINI = MONTOINI;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.MONTOFIN = MONTOFIN;
        this.IDPAGOI = IDPAGOI;
        this.IDPAGOF = IDPAGOF;
        this.IDENTRADAI = IDENTRADAI;
        this.IDENTRADAF = IDENTRADAF;
        this.IDSALIDAI = IDSALIDAI;
        this.IDSALIDAF = IDSALIDAF;
        this.IDCAJA = IDCAJA;
    }
       
    public ModeloCortes() {
    }   

    public ModeloCortes(int IDCORTE, float MONTOINI, String FINI, String FFIN, float MONTOFIN, int IDCAJA) {
        this.IDCORTE = IDCORTE;
        this.MONTOINI = MONTOINI;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.MONTOFIN = MONTOFIN;
        this.IDCAJA = IDCAJA;
    }

    public ModeloCortes(float MONTOINI, String FINI, String FFIN, float MONTOFIN, int IDCAJA) {
        this.MONTOINI = MONTOINI;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.MONTOFIN = MONTOFIN;
        this.IDCAJA = IDCAJA;
    }

    public ModeloCortes(int IDCORTE, float MONTOINI, String FINI, int IDCAJA) {
        this.IDCORTE = IDCORTE;
        this.MONTOINI = MONTOINI;
        this.FINI = FINI;
        this.IDCAJA = IDCAJA;
    }    

    public int getIDCORTE() {
        return IDCORTE;
    }

    public void setIDCORTE(int IDCORTE) {
        this.IDCORTE = IDCORTE;
    }

    public float getMONTOINI() {
        return MONTOINI;
    }

    public void setMONTOINI(float MONTOINI) {
        this.MONTOINI = MONTOINI;
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

    public float getMONTOFIN() {
        return MONTOFIN;
    }

    public void setMONTOFIN(float MONTOFIN) {
        this.MONTOFIN = MONTOFIN;
    }    

    public int getIDPAGOI() {
        return IDPAGOI;
    }

    public void setIDPAGOI(int IDPAGOI) {
        this.IDPAGOI = IDPAGOI;
    }

    public int getIDPAGOF() {
        return IDPAGOF;
    }

    public void setIDPAGOF(int IDPAGOF) {
        this.IDPAGOF = IDPAGOF;
    }

    public int getIDENTRADAI() {
        return IDENTRADAI;
    }

    public void setIDENTRADAI(int IDENTRADAI) {
        this.IDENTRADAI = IDENTRADAI;
    }

    public int getIDENTRADAF() {
        return IDENTRADAF;
    }

    public void setIDENTRADAF(int IDENTRADAF) {
        this.IDENTRADAF = IDENTRADAF;
    }

    public int getIDSALIDAI() {
        return IDSALIDAI;
    }

    public void setIDSALIDAI(int IDSALIDAI) {
        this.IDSALIDAI = IDSALIDAI;
    }

    public int getIDSALIDAF() {
        return IDSALIDAF;
    }

    public void setIDSALIDAF(int IDSALIDAF) {
        this.IDSALIDAF = IDSALIDAF;
    }

    public int getIDCAJA() {
        return IDCAJA;
    }

    public void setIDCAJA(int IDCAJA) {
        this.IDCAJA = IDCAJA;
    }
    
    
}
