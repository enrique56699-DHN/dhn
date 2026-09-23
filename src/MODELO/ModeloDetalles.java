/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloDetalles
{
    private int IDDETALLE;
    private int DIASPRE;
    private String CONCEPTO;
    private float SMINIMO;
    private float TC;
    private float IVA;
    private int GP;
    private int GI;

    public ModeloDetalles(float TC, float IVA, int GP, int GI) {
        this.TC = TC;
        this.IVA = IVA;
        this.GP = GP;
        this.GI = GI;
    }
    
    public ModeloDetalles() {
    }

    public ModeloDetalles(float TC, float IVA) {
        this.TC = TC;
        this.IVA = IVA;
    }

    public ModeloDetalles(int IDDETALLE, int DIASPRE, String CONCEPTO, float SMINIMO, float TC, float IVA, int GP, int GI) {
        this.IDDETALLE = IDDETALLE;
        this.DIASPRE = DIASPRE;
        this.CONCEPTO = CONCEPTO;
        this.SMINIMO = SMINIMO;
        this.TC = TC;
        this.IVA = IVA;
        this.GP = GP;
        this.GI = GI;
    }

    public ModeloDetalles(int DIASPRE, String CONCEPTO, float SMINIMO, float TC, float IVA, int GP, int GI) {
        this.DIASPRE = DIASPRE;
        this.CONCEPTO = CONCEPTO;
        this.SMINIMO = SMINIMO;
        this.TC = TC;
        this.IVA = IVA;
        this.GP = GP;
        this.GI = GI;
    }  
        
    public ModeloDetalles(int IDDETALLE, int DIASPRE, String CONCEPTO, float SMINIMO, float TC, float IVA) {
        this.IDDETALLE = IDDETALLE;
        this.DIASPRE = DIASPRE;
        this.CONCEPTO = CONCEPTO;
        this.SMINIMO = SMINIMO;
        this.TC = TC;
        this.IVA = IVA;
    }

    public ModeloDetalles(int IDDETALLE, float IVA) {
        this.IDDETALLE = IDDETALLE;
        this.IVA = IVA;
    }     

    public ModeloDetalles(int IDDETALLE, int DIASPRE, String CONCEPTO, float SMINIMO) {
        this.IDDETALLE = IDDETALLE;
        this.DIASPRE = DIASPRE;
        this.CONCEPTO = CONCEPTO;
        this.SMINIMO = SMINIMO;
    }

    public ModeloDetalles(int DIASPRE, String CONCEPTO, float SMINIMO) {
        this.DIASPRE = DIASPRE;
        this.CONCEPTO = CONCEPTO;
        this.SMINIMO = SMINIMO;
    }

    public int getIDDETALLE() {
        return IDDETALLE;
    }

    public void setIDDETALLE(int IDDETALLE) {
        this.IDDETALLE = IDDETALLE;
    }

    public int getDIASPRE() {
        return DIASPRE;
    }

    public void setDIASPRE(int DIASPRE) {
        this.DIASPRE = DIASPRE;
    }

    public String getCONCEPTO() {
        return CONCEPTO;
    }

    public void setCONCEPTO(String CONCEPTO) {
        this.CONCEPTO = CONCEPTO;
    }

    public float getSMINIMO() {
        return SMINIMO;
    }

    public void setSMINIMO(float SMINIMO) {
        this.SMINIMO = SMINIMO;
    }

    public float getTC() {
        return TC;
    }

    public void setTC(float TC) {
        this.TC = TC;
    }

    public float getIVA() {
        return IVA;
    }

    public void setIVA(float IVA) {
        this.IVA = IVA;
    }

    public int getGP() {
        return GP;
    }

    public void setGP(int GP) {
        this.GP = GP;
    }

    public int getGI() {
        return GI;
    }

    public void setGI(int GI) {
        this.GI = GI;
    }
    
    
}
