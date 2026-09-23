/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloMovD 
{
    private int IDMOVD;
    private String MOVIMIENTO;
    private int IDMOVIMIENTO;
    private String REF;
    private boolean COTEJADO;
    private String FAC;

    public ModeloMovD() {
    }

    public ModeloMovD(int IDMOVD, String MOVIMIENTO, int IDMOVIMIENTO, String REF, boolean COTEJADO, String FAC) {
        this.IDMOVD = IDMOVD;
        this.MOVIMIENTO = MOVIMIENTO;
        this.IDMOVIMIENTO = IDMOVIMIENTO;
        this.REF = REF;
        this.COTEJADO = COTEJADO;
        this.FAC = FAC;
    }

    public ModeloMovD(String MOVIMIENTO, int IDMOVIMIENTO, String REF, boolean COTEJADO, String FAC) {
        this.MOVIMIENTO = MOVIMIENTO;
        this.IDMOVIMIENTO = IDMOVIMIENTO;
        this.REF = REF;
        this.COTEJADO = COTEJADO;
        this.FAC = FAC;
    }

    public int getIDMOVD() {
        return IDMOVD;
    }

    public void setIDMOVD(int IDMOVD) {
        this.IDMOVD = IDMOVD;
    }

    public String getMOVIMIENTO() {
        return MOVIMIENTO;
    }

    public void setMOVIMIENTO(String MOVIMIENTO) {
        this.MOVIMIENTO = MOVIMIENTO;
    }

    public int getIDMOVIMIENTO() {
        return IDMOVIMIENTO;
    }

    public void setIDMOVIMIENTO(int IDMOVIMIENTO) {
        this.IDMOVIMIENTO = IDMOVIMIENTO;
    }

    public String getREF() {
        return REF;
    }

    public void setREF(String REF) {
        this.REF = REF;
    }

    public boolean getCOTEJADO() {
        return COTEJADO;
    }

    public void setCOTEJADO(boolean COTEJADO) {
        this.COTEJADO = COTEJADO;
    }

    public String getFAC() {
        return FAC;
    }

    public void setFAC(String FAC) {
        this.FAC = FAC;
    }
    
    
    
}
