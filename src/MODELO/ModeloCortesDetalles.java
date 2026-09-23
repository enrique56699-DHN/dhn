/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloCortesDetalles
{
    private int IDCORTED;
    private int IDCORTE;
    private int IDPAGOI;
    private int IDPAGOF;
    private int IDENTRADAI;
    private int IDENTRADAF;
    private int IDSALIDAI;
    private int IDSALIDAF;

    public ModeloCortesDetalles() {
    }

    public ModeloCortesDetalles(int IDCORTED, int IDCORTE, int IDPAGOI, int IDPAGOF, int IDENTRADAI, int IDENTRADAF, int IDSALIDAI, int IDSALIDAF) {
        this.IDCORTED = IDCORTED;
        this.IDCORTE = IDCORTE;
        this.IDPAGOI = IDPAGOI;
        this.IDPAGOF = IDPAGOF;
        this.IDENTRADAI = IDENTRADAI;
        this.IDENTRADAF = IDENTRADAF;
        this.IDSALIDAI = IDSALIDAI;
        this.IDSALIDAF = IDSALIDAF;
    }

    public ModeloCortesDetalles(int IDCORTE, int IDPAGOI, int IDPAGOF, int IDENTRADAI, int IDENTRADAF, int IDSALIDAI, int IDSALIDAF) {
        this.IDCORTE = IDCORTE;
        this.IDPAGOI = IDPAGOI;
        this.IDPAGOF = IDPAGOF;
        this.IDENTRADAI = IDENTRADAI;
        this.IDENTRADAF = IDENTRADAF;
        this.IDSALIDAI = IDSALIDAI;
        this.IDSALIDAF = IDSALIDAF;
    }

    public ModeloCortesDetalles(int IDCORTED, int IDCORTE, int IDPAGOI, int IDENTRADAI, int IDSALIDAI) {
        this.IDCORTED = IDCORTED;
        this.IDCORTE = IDCORTE;
        this.IDPAGOI = IDPAGOI;
        this.IDENTRADAI = IDENTRADAI;
        this.IDSALIDAI = IDSALIDAI;
    }    

    public int getIDCORTED() {
        return IDCORTED;
    }

    public void setIDCORTED(int IDCORTED) {
        this.IDCORTED = IDCORTED;
    }

    public int getIDPAGOI() {
        return IDPAGOI;
    }

    public void setIDPAGOI(int IDPAGOI) {
        this.IDPAGOI = IDPAGOI;
    }

    public int getIDENTRADAI() {
        return IDENTRADAI;
    }

    public void setIDENTRADAI(int IDENTRADAI) {
        this.IDENTRADAI = IDENTRADAI;
    }

    public int getIDSALIDAI() {
        return IDSALIDAI;
    }

    public void setIDSALIDAI(int IDSALIDAI) {
        this.IDSALIDAI = IDSALIDAI;
    }

    public int getIDCORTE() {
        return IDCORTE;
    }

    public void setIDCORTE(int IDCORTE) {
        this.IDCORTE = IDCORTE;
    }

    public int getIDPAGOF() {
        return IDPAGOF;
    }

    public void setIDPAGOF(int IDPAGOF) {
        this.IDPAGOF = IDPAGOF;
    }

    public int getIDENTRADAF() {
        return IDENTRADAF;
    }

    public void setIDENTRADAF(int IDENTRADAF) {
        this.IDENTRADAF = IDENTRADAF;
    }

    public int getIDSALIDAF() {
        return IDSALIDAF;
    }

    public void setIDSALIDAF(int IDSALIDAF) {
        this.IDSALIDAF = IDSALIDAF;
    }
    
    
    
}
