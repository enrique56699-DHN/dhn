/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloPagosProveedores 
{
    private int IDPAGOPROV;
    private int IDPROVEEDOR;
    private String FECHA;
    private int IDPENDIENTE;
    private float CARGO;
    private float ABONO;
    private float SALDO;
    private String DESCRIPCION;
    private String ESTADO;
    
    private String NOMBRE;

    public ModeloPagosProveedores() {
    }

    public ModeloPagosProveedores(int IDPAGOPROV, int IDPROVEEDOR, String FECHA, int IDPENDIENTE, float CARGO, float ABONO, float SALDO, String DESCRIPCION, String ESTADO, String NOMBRE) {
        this.IDPAGOPROV = IDPAGOPROV;
        this.IDPROVEEDOR = IDPROVEEDOR;
        this.FECHA = FECHA;
        this.IDPENDIENTE = IDPENDIENTE;
        this.CARGO = CARGO;
        this.ABONO = ABONO;
        this.SALDO = SALDO;
        this.DESCRIPCION = DESCRIPCION;
        this.ESTADO = ESTADO;
        this.NOMBRE = NOMBRE;
    }

    public ModeloPagosProveedores(int IDPROVEEDOR, String FECHA, int IDPENDIENTE, float CARGO, float ABONO, float SALDO, String DESCRIPCION, String ESTADO, String NOMBRE) {
        this.IDPROVEEDOR = IDPROVEEDOR;
        this.FECHA = FECHA;
        this.IDPENDIENTE = IDPENDIENTE;
        this.CARGO = CARGO;
        this.ABONO = ABONO;
        this.SALDO = SALDO;
        this.DESCRIPCION = DESCRIPCION;
        this.ESTADO = ESTADO;
        this.NOMBRE = NOMBRE;
    }

    public int getIDPAGOPROV() {
        return IDPAGOPROV;
    }

    public void setIDPAGOPROV(int IDPAGOPROV) {
        this.IDPAGOPROV = IDPAGOPROV;
    }

    public int getIDPROVEEDOR() {
        return IDPROVEEDOR;
    }

    public void setIDPROVEEDOR(int IDPROVEEDOR) {
        this.IDPROVEEDOR = IDPROVEEDOR;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public int getIDPENDIENTE() {
        return IDPENDIENTE;
    }

    public void setIDPENDIENTE(int IDPENDIENTE) {
        this.IDPENDIENTE = IDPENDIENTE;
    }

    public float getCARGO() {
        return CARGO;
    }

    public void setCARGO(float CARGO) {
        this.CARGO = CARGO;
    }

    public float getABONO() {
        return ABONO;
    }

    public void setABONO(float ABONO) {
        this.ABONO = ABONO;
    }

    public float getSALDO() {
        return SALDO;
    }

    public void setSALDO(float SALDO) {
        this.SALDO = SALDO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }
    
    
    
}
