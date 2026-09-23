/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author win 10
 */
public class ModeloProveedores 
{    
    private int IDPROVEEDOR;
    private String NOMBRE;
    private String TEL1;
    private String TEL2;
    private String REFERENCIA;

    public ModeloProveedores() {
    }

    public ModeloProveedores(int IDPROVEEDOR, String NOMBRE, String TEL1, String TEL2, String REFERENCIA) {
        this.IDPROVEEDOR = IDPROVEEDOR;
        this.NOMBRE = NOMBRE;
        this.TEL1 = TEL1;
        this.TEL2 = TEL2;
        this.REFERENCIA = REFERENCIA;
    }

    public ModeloProveedores(String NOMBRE, String TEL1, String TEL2, String REFERENCIA) {
        this.NOMBRE = NOMBRE;
        this.TEL1 = TEL1;
        this.TEL2 = TEL2;
        this.REFERENCIA = REFERENCIA;
    }

    public int getIDPROVEEDOR() {
        return IDPROVEEDOR;
    }

    public void setIDPROVEEDOR(int IDPROVEEDOR) {
        this.IDPROVEEDOR = IDPROVEEDOR;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getTEL1() {
        return TEL1;
    }

    public void setTEL1(String TEL1) {
        this.TEL1 = TEL1;
    }

    public String getTEL2() {
        return TEL2;
    }

    public void setTEL2(String TEL2) {
        this.TEL2 = TEL2;
    }

    public String getREFERENCIA() {
        return REFERENCIA;
    }

    public void setREFERENCIA(String REFERENCIA) {
        this.REFERENCIA = REFERENCIA;
    }
    
    
}
