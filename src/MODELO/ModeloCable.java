/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author win 10
 */
public class ModeloCable {
    
    private int IDCABLE;
    private int IDEMPLEADO;
    private String FECHA;
    private double ENTREGADO, USADO, DEVUELTO;

    public ModeloCable() {
    }

    public ModeloCable(int IDCABLE, int IDEMPLEADO, String FECHA, double ENTREGADO, double USADO, double DEVUELTO) {
        this.IDCABLE = IDCABLE;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.ENTREGADO = ENTREGADO;
        this.USADO = USADO;
        this.DEVUELTO = DEVUELTO;
    }

    public ModeloCable(int IDEMPLEADO, String FECHA, double ENTREGADO, double USADO, double DEVUELTO) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.ENTREGADO = ENTREGADO;
        this.USADO = USADO;
        this.DEVUELTO = DEVUELTO;
    }

    public int getIDCABLE() {
        return IDCABLE;
    }

    public void setIDCABLE(int IDCABLE) {
        this.IDCABLE = IDCABLE;
    }

    public int getIDEMPLEADO() {
        return IDEMPLEADO;
    }

    public void setIDEMPLEADO(int IDEMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public double getENTREGADO() {
        return ENTREGADO;
    }

    public void setENTREGADO(double ENTREGADO) {
        this.ENTREGADO = ENTREGADO;
    }

    public double getUSADO() {
        return USADO;
    }

    public void setUSADO(double USADO) {
        this.USADO = USADO;
    }

    public double getDEVUELTO() {
        return DEVUELTO;
    }

    public void setDEVUELTO(double DEVUELTO) {
        this.DEVUELTO = DEVUELTO;
    }
    
    
    
}
